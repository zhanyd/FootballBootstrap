package com.ibusiness.security.impl;

import com.ibusiness.security.api.MethodSourceFetcher;

import org.springframework.beans.factory.InitializingBean;

/**
 * 从数据库取得AUTH_ACCESS表type类型为'METHOD'的数据
 * AUTH_ACCESS：资源访问权限表
 * AUTH_PERM：授权管理表
 * 
 * @author JiangBo
 * 
 */
public class DatabaseMethodSourceFetcher extends AbstractDatabaseSourceFetcher implements MethodSourceFetcher,
        InitializingBean {
    public void afterPropertiesSet() throws Exception {
        if (getQuery() != null) {
            return;
        }
        String sql = "select ac.value as access,p.code as perm" + " from AUTH_ACCESS ac,AUTH_PERM p"
                + " where ac.perm_id=p.id and ac.type='METHOD'" + " order by ac.priority";
        this.setQuery(sql);
    }
}
