
/*==============================================================*/
/*  菜单管理表 */
/*==============================================================*/
CREATE TABLE IB_MENU(
        ID VARCHAR(64),
        MENUNAME VARCHAR(256),
		MENULEVEL VARCHAR(16),
		MENUURL VARCHAR(256),
		MENUIFRAME VARCHAR(16),
		MENUORDER VARCHAR(16),
		PARENTID VARCHAR(64),
		DESKTOPICON  VARCHAR(8),
		ICONURL  VARCHAR(128),
        CONSTRAINT PK_IB_MENU PRIMARY KEY(ID)
) engine=innodb;

/*==============================================================*/
/*  菜单和角色模板关联表*/
/*==============================================================*/
CREATE TABLE IB_MENU_ROLE_DEF(
        MENU_ID VARCHAR(64) NOT NULL,
        ROLE_DEF_ID BIGINT NOT NULL,
        CONSTRAINT PK_AUTH_PERM_ROLE_DEF PRIMARY KEY(MENU_ID,ROLE_DEF_ID)
) engine=innodb;
