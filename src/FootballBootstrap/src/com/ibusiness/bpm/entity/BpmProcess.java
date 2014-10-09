package com.ibusiness.bpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IB_CONF_FORM BPM流程管理表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_BPM_PROCESS")
public class BpmProcess {
    // UUID
    private String id;
    // 模块包名
    private String packageName;
    // 流程名称
    private String flowName;
    // 流程标题
    private String flowTitle;
    // 流程版本编号
    private String versionId;
    // 流程用户任务设置,是否可配置下一节点给谁办理
    private String useTaskConf;
    /**
     * @return the id
     */
    @Id
    @Column(name = "ID", nullable = false)
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the flowName
     */
    @Column(name = "FLOWNAME")
    public String getFlowName() {
        return flowName;
    }
    /**
     * @param flowName the flowName to set
     */
    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }
    /**
     * @return the flowTitle
     */
    @Column(name = "FLOWTITLE")
    public String getFlowTitle() {
        return flowTitle;
    }
    /**
     * @param flowTitle the flowTitle to set
     */
    public void setFlowTitle(String flowTitle) {
        this.flowTitle = flowTitle;
    }
    /**
     * @return the versionId
     */
    @Column(name = "VERSIONID")
    public String getVersionId() {
        return versionId;
    }
    /**
     * @param versionId the versionId to set
     */
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
    /**
     * @return the useTaskConf
     */
    @Column(name = "USETASKCONF")
    public String getUseTaskConf() {
        return useTaskConf;
    }
    /**
     * @param useTaskConf the useTaskConf to set
     */
    public void setUseTaskConf(String useTaskConf) {
        this.useTaskConf = useTaskConf;
    }
}
