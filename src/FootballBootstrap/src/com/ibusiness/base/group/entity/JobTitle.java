package com.ibusiness.base.group.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobTitle 职位名称管理表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_JOB_TITLE")
public class JobTitle implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private String name;

    /** null. */
    private String scopeId;

    /** . */
    private Set<JobInfo> jobInfos = new HashSet<JobInfo>(0);

    public JobTitle() {
    }

    public JobTitle(String name, String scopeId, Set<JobInfo> jobInfos) {
        this.name = name;
        this.scopeId = scopeId;
        this.jobInfos = jobInfos;
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
    @Column(name = "NAME", length = 50)
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

    /** @return . */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobTitle")
    public Set<JobInfo> getJobInfos() {
        return this.jobInfos;
    }

    /**
     * @param jobInfos
     *            .
     */
    public void setJobInfos(Set<JobInfo> jobInfos) {
        this.jobInfos = jobInfos;
    }
}
