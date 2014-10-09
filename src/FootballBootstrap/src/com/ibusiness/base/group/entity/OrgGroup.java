package com.ibusiness.base.group.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 小组实体Group .
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_GROUP")
public class OrgGroup implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** 编号. */
    private Long id;

    /** 公司编号. */
    private Long companyid;
    
    /** 部门编号. */
    private Long deptid;
    /** 小组名称. */
    private String name;
    /** 备注. */
    private String descn;

    /** 状态. */
    private Integer status;

    /** null. */
    private String ref;

    /** null. */
    private String scopeId;

    public OrgGroup() {
    }

    public OrgGroup(Long companyid, Long deptid, String name, String descn, Integer status, String ref,
            String scopeId) {
        this.companyid = companyid;
        this.deptid = deptid;
        this.name = name;
        this.descn = descn;
        this.status = status;
        this.ref = ref;
        this.scopeId = scopeId;
    }

    /** @return null. */
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     *            null.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return null. */
    @Column(name = "NAME", length = 200)
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            null.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** @return null. */
    @Column(name = "DESCN", length = 200)
    public String getDescn() {
        return this.descn;
    }

    /**
     * @param descn
     *            null.
     */
    public void setDescn(String descn) {
        this.descn = descn;
    }

    /** @return null. */
    @Column(name = "STATUS")
    public Integer getStatus() {
        return this.status;
    }

    /**
     * @param status
     *            null.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** @return null. */
    @Column(name = "REF", length = 200)
    public String getRef() {
        return this.ref;
    }

    /**
     * @param ref
     *            null.
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /** @return null. */
    @Column(name = "SCOPE_ID", length = 50)
    public String getScopeId() {
        return this.scopeId;
    }

    /**
     * @param scopeId
     *            null.
     */
    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    /**
     * @return the companyid
     */
    public Long getCompanyid() {
        return companyid;
    }

    /**
     * @param companyid the companyid to set
     */
    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    /**
     * @return the deptid
     */
    public Long getDeptid() {
        return deptid;
    }

    /**
     * @param deptid the deptid to set
     */
    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }
}
