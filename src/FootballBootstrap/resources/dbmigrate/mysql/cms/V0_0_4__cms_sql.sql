
-------------------------------------------------------------------------------
--  cms catalog
-------------------------------------------------------------------------------
CREATE TABLE CMS_CATALOG(
        ID BIGINT auto_increment,
	NAME VARCHAR(50),
	CODE VARCHAR(200),
	LOGO VARCHAR(200),
	TYPE INT,
	TEMPLATE_INDEX VARCHAR(200),
	TEMPLATE_LIST VARCHAR(200),
	TEMPLATE_DETAIL VARCHAR(200),
	KEYWORD VARCHAR(200),
	DESCRIPTION VARCHAR(200),
	PARENT_ID BIGINT,
        CONSTRAINT PK_CMS_CATALOG PRIMARY KEY(ID),
	CONSTRAINT FK_CMS_CATALOG_PARENT FOREIGN KEY(PARENT_ID) REFERENCES CMS_CATALOG(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  cms article
-------------------------------------------------------------------------------
CREATE TABLE CMS_ARTICLE(
        ID BIGINT auto_increment,
	TITLE VARCHAR(200),
	SHORT_TITLE VARCHAR(200),
	SUB_TITLE VARCHAR(200),
	CONTENT TEXT,
	SUMMARY VARCHAR(200),
	LOGO VARCHAR(200),
	KEYWORD VARCHAR(200),
	TAGS VARCHAR(200),
	SOURCE VARCHAR(200),
	ALLOW_COMMENT INT,
	STATUS INT,
	PUBLISH_TIME TIMESTAMP,
	CLOSE_TIME TIMESTAMP,
	TYPE INT,
	TOP INT,
	WEIGHT INT,
	CREATE_TIME TIMESTAMP,
	TEMPLATE VARCHAR(200),
	VIEW_COUNT INT,
	RECOMMEND_ID BIGINT,
	RECOMMEND_STATUS INT,
	USER_ID VARCHAR(200),
	CATALOG_ID BIGINT,
        CONSTRAINT PK_CMS_ARTICLE PRIMARY KEY(ID),
	CONSTRAINT FK_CMS_ARTICLE_CATALOG FOREIGN KEY(CATALOG_ID) REFERENCES CMS_CATALOG(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  cms comment
-------------------------------------------------------------------------------
CREATE TABLE CMS_COMMENT(
        ID BIGINT auto_increment,
	TITLE VARCHAR(200),
	CONTENT VARCHAR(200),
	STATUS INT,
	CREATE_TIME TIMESTAMP,
	USER_ID VARCHAR(200),
	ARTICLE_ID BIGINT,
        CONSTRAINT PK_CMS_COMMENT PRIMARY KEY(ID),
	CONSTRAINT FK_CMS_COMMENT_ARTICLE FOREIGN KEY(ARTICLE_ID) REFERENCES CMS_ARTICLE(ID)
) engine=innodb;

-------------------------------------------------------------------------------
--  cms favorite
-------------------------------------------------------------------------------
CREATE TABLE CMS_FAVORITE(
        ID BIGINT auto_increment,
	SUBJECT VARCHAR(200),
	CREATE_TIME TIMESTAMP,
	USER_ID VARCHAR(200),
	ARTICLE_ID BIGINT,
	COMMENT_ID BIGINT,
        CONSTRAINT PK_CMS_FAVORITE PRIMARY KEY(ID),
	CONSTRAINT FK_CMS_FAVORITE_ARTICLE FOREIGN KEY(ARTICLE_ID) REFERENCES CMS_ARTICLE(ID),
	CONSTRAINT FK_CMS_FAVORITE_COMMENT FOREIGN KEY(COMMENT_ID) REFERENCES CMS_COMMENT(ID)
) engine=innodb;
