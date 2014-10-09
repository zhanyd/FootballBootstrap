package com.ibusiness.component.form.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ibusiness.component.table.entity.ConfTableColumns;

/**
 * IB_CONF_FORM_TABLE 表单对应数据表管理表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CONF_FORM_TABLE")
public class ConfFormTable implements java.io.Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    // 表单名称
    private String formName;
    // 表名
    private String tableName;
    // 表类型：主表/子表/单表
    private String tableType;
    // 模块包名
    private String packageName;
    // 对应表
    private List<ConfTableColumns> tableColumns;
    
    @Id
    @Column(name = "TABLENAME", nullable = false)
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    /**
     * @return the formName
     */
    @Id
    @Column(name = "FORMNAME", nullable = false)
    public String getFormName() {
        return formName;
    }
    /**
     * @param formName the formName to set
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }
    /**
     * @return the packageName
     */
    @Column(name = "PACKAGENAME")
    public String getPackageName() {
        return packageName;
    }
    /**
     * @param packageName the packageName to set
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    /**
     * 用于标识不在数据库表中映射的属性
     * @return the tbleColumns
     */
    @Transient
    public List<ConfTableColumns> getTableColumns() {
        return tableColumns;
    }
    /**
     * @param tbleColumns the tbleColumns to set
     */
    public void setTableColumns(List<ConfTableColumns> tableColumns) {
        this.tableColumns = tableColumns;
    }
    /**
     * @return the tableType
     */
    @Column(name = "TABLETYPE")
    public String getTableType() {
        return tableType;
    }
    /**
     * @param tableType the tableType to set
     */
    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
}
