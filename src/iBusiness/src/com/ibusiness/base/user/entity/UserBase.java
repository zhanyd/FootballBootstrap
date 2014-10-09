package com.ibusiness.base.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ibusiness.base.auth.entity.RoleDef;
import com.ibusiness.base.group.entity.JobInfo;

/**
 * UserBase用户表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "USER_BASE")
public class UserBase implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** 编号. */
    private Long id;

    /** 用户库列表 */
    private UserRepo userRepo;

    /** 帐号 */
    private String username;

    /** 显示名 */
    private String displayName;

    /** 密码 */
    private String password;

    /** 状态. */
    private Integer status;

    /** 引用. */
    private String ref;

    /** 范围. */
    private String scopeId;

    /** 邮箱. */
    private String email;

    /** 手机. */
    private String mobile;
    
    /** 职务管理表. */
    private JobInfo jobInfo;
    
    /** 角色模板管理表. */
    private RoleDef roleDef;
    
    /** 对应页面的css样式 */
    private String css;

    public UserBase() {
    }

    public UserBase(UserRepo userRepo, String username, String displayName,
            String password, Integer status, String ref, String scopeId,
            String email, String mobile, JobInfo jobInfo, String css, RoleDef roleDef) {
        this.userRepo = userRepo;
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.status = status;
        this.ref = ref;
        this.scopeId = scopeId;
        this.email = email;
        this.mobile = mobile;
        this.jobInfo = jobInfo;
        this.css = css;
        this.roleDef = roleDef;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_REPO_ID")
    public UserRepo getUserRepo() {
        return this.userRepo;
    }

    /**
     * @param userRepo
     *            null.
     */
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /** @return null. */
    @Column(name = "USERNAME", length = 50)
    public String getUsername() {
        return this.username;
    }

    /**
     * @param username
     *            null.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** @return null. */
    @Column(name = "DISPLAY_NAME", length = 50)
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * @param displayName
     *            null.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /** @return null. */
    @Column(name = "PASSWORD", length = 50)
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password
     *            null.
     */
    public void setPassword(String password) {
        this.password = password;
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

    /** @return null. */
    @Column(name = "EMAIL", length = 100)
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email
     *            null.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return null. */
    @Column(name = "MOBILE", length = 50)
    public String getMobile() {
        return this.mobile;
    }

    /**
     * @param mobile
     *            null.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /** @return null. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_INFO_ID")
    public JobInfo getJobInfo() {
        return this.jobInfo;
    }

    /**
     * @param jobInfo
     *            null.
     */
    public void setJobInfo(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }

    /**
     * @return the css
     */
    @Column(name = "CSS", length = 64)
    public String getCss() {
        return css;
    }

    /**
     * @param css the css to set
     */
    public void setCss(String css) {
        this.css = css;
    }

    /**
     * @return the roleDef
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_DEF_ID")
    public RoleDef getRoleDef() {
        return roleDef;
    }

    /**
     * @param roleDef the roleDef to set
     */
    public void setRoleDef(RoleDef roleDef) {
        this.roleDef = roleDef;
    }
}
