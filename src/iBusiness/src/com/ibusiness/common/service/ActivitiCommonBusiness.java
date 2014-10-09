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
public class ActivitiCommonBusiness {
    private ActivitiCommonBusiness () {}
    private static ActivitiCommonBusiness instance = new ActivitiCommonBusiness();
    public static ActivitiCommonBusiness getInstance() {
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
    // 表单对应表管理表DAO
    private ConfFormTableColumnDao confFormTableColumnDao;
    

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
