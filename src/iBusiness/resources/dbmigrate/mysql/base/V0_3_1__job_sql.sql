
/*==============================================================*/
/* 公司 company   */
/*==============================================================*/
CREATE TABLE IB_COMPANY(
        ID BIGINT auto_increment,
        NAME VARCHAR(200),
	    DESCN VARCHAR(200),
        STATUS INTEGER,
	    REF VARCHAR(200),
	    SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_ORG_COMPANY PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* 部门 department  */
/*==============================================================*/
CREATE TABLE IB_DEPARTMENT(
        ID BIGINT auto_increment,
        COMPANYID BIGINT,
        NAME VARCHAR(200),
	    DESCN VARCHAR(200),
        STATUS INTEGER,
	    REF VARCHAR(200),
	    SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_ORG_DEPARTMENT PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* 小组 group  */
/*==============================================================*/
CREATE TABLE IB_GROUP(
        ID BIGINT auto_increment,
        COMPANYID BIGINT,
        DEPTID BIGINT,
        NAME VARCHAR(200),
	    DESCN VARCHAR(200),
        STATUS INTEGER,
	    REF VARCHAR(200),
	    SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_ORG_GROUP PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* job type 职务类型表  */
/*==============================================================*/
CREATE TABLE IB_JOB_TYPE(
        ID BIGINT auto_increment,
	    NAME VARCHAR(50),
	    PARENT_ID BIGINT,
	    SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_IB_JOB_TYPE PRIMARY KEY(ID),
        CONSTRAINT FK_IB_JOB_TYPE_PARENT FOREIGN KEY(PARENT_ID) REFERENCES IB_JOB_TYPE(ID)
) engine=innodb;

/*==============================================================*/
/* job title 职位名称管理表 */
/*==============================================================*/
CREATE TABLE IB_JOB_TITLE(
        ID BIGINT auto_increment,
	    NAME VARCHAR(50),
	    SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_IB_JOB_TITLE PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* job info 职务管理表 */
/*==============================================================*/
CREATE TABLE IB_JOB_INFO(
        ID BIGINT auto_increment,
	    NAME VARCHAR(50),
	    TYPE_ID BIGINT,
	    TITLE_ID BIGINT,
	    SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_IB_JOB_INFO PRIMARY KEY(ID),
        CONSTRAINT FK_IB_JOB_INFO_TYPE FOREIGN KEY(TYPE_ID) REFERENCES IB_JOB_TYPE(ID),
        CONSTRAINT FK_IB_JOB_INFO_TITLE FOREIGN KEY(TITLE_ID) REFERENCES IB_JOB_TITLE(ID)
) engine=innodb;

