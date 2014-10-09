
/*-------------------------------------------------------------------------------*/
/*--  user repo 用户库列表  */
/*-------------------------------------------------------------------------------*/
CREATE TABLE USER_REPO(
        ID BIGINT auto_increment,
		CODE VARCHAR(50),
        NAME VARCHAR(50),
		REF varchar(64),
		SCOPE_ID VARCHAR(50),
        CONSTRAINT PK_U_USER_REPO PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/* user base 用户表   */
/*==============================================================*/
CREATE TABLE USER_BASE(
        ID BIGINT auto_increment,
        USERNAME VARCHAR(50),
		DISPLAY_NAME VARCHAR(50),
        PASSWORD VARCHAR(50),
        STATUS INTEGER,
		REF varchar(64),
		USER_REPO_ID BIGINT,
		SCOPE_ID VARCHAR(50),
		email varchar(100),
		mobile varchar(50),
		JOB_INFO_ID BIGINT,
		ROLE_DEF_ID BIGINT,
		CSS varchar(64),
        CONSTRAINT PK_USER_BASE PRIMARY KEY(ID),
        CONSTRAINT FK_USER_BASE_REPO FOREIGN KEY(USER_REPO_ID) REFERENCES USER_REPO(ID)
) engine=innodb;
