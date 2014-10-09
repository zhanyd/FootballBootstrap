package com.ibusiness.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ibusiness.component.form.dao.ConfFormDao;
import com.ibusiness.component.form.dao.ConfFormTableColumnDao;
import com.ibusiness.component.form.entity.ConfForm;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.component.table.dao.TableColumnsDao;
import com.ibusiness.component.table.entity.ConfTableColumns;

/**
 * CommonBusiness单例对象类
 * 
 * @author JiangBo
 *
 */
public class CommonBusiness {
    private CommonBusiness () {}
    private static CommonBusiness instance = new CommonBusiness();
    public static CommonBusiness getInstance() {
        return instance;
    }
    // 流水表表结构管理DAO
    private TableColumnsDao tableColumnsDao;
    // 流水表表结构管理List
    private List<ConfTableColumns> tableColumnsList = new ArrayList<ConfTableColumns>();
    // 流水表表结构管理Map
    private Map<String, ConfTableColumns> tableColumnsMap = new HashMap<String, ConfTableColumns>();

    // 表单DAO
    private ConfFormDao confFormDao;
    // 表单List
    private List<ConfForm> formList = new ArrayList<ConfForm>();
    
    // 表单对应表管理表DAO
    private ConfFormTableColumnDao confFormTableColumnDao;
    // 表单对应表管理表List
    private List<ConfFormTableColumn> formTableColumnList = new ArrayList<ConfFormTableColumn>();
    
    /**
     * 取得流水表表结构管理List
     * @return the tableColumnsList
     */
    @SuppressWarnings("unchecked")
    public List<ConfTableColumns> getTableColumnsList(String tableName) {
        String hql = "from ConfTableColumns where tableName=?";
        tableColumnsList = tableColumnsDao.find(hql, tableName);
        return tableColumnsList;
    }
    /**
     * 取得业务表字段结构管理Map
     * @return the tableColumnsMap
     */
    public Map<String, ConfTableColumns> getTableColumnsMap(String tableName) {
        List<ConfTableColumns> tableColumnsList = getTableColumnsList(tableName);
        for (ConfTableColumns bean : tableColumnsList) {
            tableColumnsMap.put(bean.getColumnValue(), bean);
        }
        return tableColumnsMap;
    }
    /**
     * 取得表单list
     * @return the formList
     */
    @SuppressWarnings("unchecked")
    public List<ConfForm> getFormList(String formName) {
        String hql = "from ConfForm where formName=?";
        this.formList = confFormDao.find(hql, formName);
        return this.formList;
    }
    /**
     * 取得表单对应表管理表List
     * @return the tableColumnsList
     */
    @SuppressWarnings("unchecked")
    public List<ConfFormTableColumn> getFormTableColumnList(String tableName, String formName) {
        String hql = "from ConfFormTableColumn where tableName=? AND formName=?";
        this.formTableColumnList = confFormTableColumnDao.find(hql, tableName, formName);
        return this.formTableColumnList;
    }
    // ======================================================================
    @Resource
    public void setTableColumnsDao(TableColumnsDao tableColumnsDao) {
        this.tableColumnsDao = tableColumnsDao;
    }
    @Resource
    public void setConfFormDao(ConfFormDao confFormDao) {
        this.confFormDao = confFormDao;
    }
    @Resource
    public void setConfFormTableColumnDao(ConfFormTableColumnDao confFormTableColumnDao) {
        this.confFormTableColumnDao = confFormTableColumnDao;
    }
}
