/* =============================  IB_CONF_COMPONENT 业务模块组件管理表 ---   设置测试数据  =============================  */
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10001','test','测试模块','0','sModule');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10002','test','表存储设计器','10001','Table');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10003','test','表单设计器','10001','Form');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10004','test','流程表设计器','10001','BpmTable');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10005','test','流程表单设计器','10001','BpmForm');
INSERT INTO IB_CONF_COMPONENT(ID,PACKAGENAME,MODULENAME,PARENTID,TYPEID) VALUES('10006','test','流程设计器','10001','Bpm');

/* ============================= 业务表管理表 =============================   */
INSERT INTO IB_CONF_TABLE(ID,PACKAGENAME,TABLENAME,TABLENAMECOMMENT,ISBPMTABLE) VALUES('10001','test','IB_TEST','测试练习表',2);
/* ======================== 业务模块组件管理表 ====================  */
CREATE TABLE IB_TEST (
	     ID  VARCHAR(64),
		 NAME VARCHAR(64),
		 REMARK VARCHAR(1024),
        CONSTRAINT PK_IB_TEST PRIMARY KEY(ID)
) ENGINE=INNODB;
/* ============================= 表列字段管理表  ============================= */
INSERT INTO ib_conf_table_columns(tablename,columnvalue,columnname,columntype,columnsize,isnull,columnno) VALUES('IB_TEST','ID','UUID主键','VARCHAR','64','否',1);
INSERT INTO ib_conf_table_columns(tablename,columnvalue,columnname,columntype,columnsize,isnull,columnno) VALUES('IB_TEST','NAME','姓名','VARCHAR','64','是',2);
INSERT INTO ib_conf_table_columns(tablename,columnvalue,columnname,columntype,columnsize,isnull,columnno) VALUES('IB_TEST','REMARK','备注','VARCHAR','1024','是',8);
/* ============================= 表单管理表 =============================  */
INSERT INTO ib_conf_form(ID,PACKAGENAME,FORMNAME,FORMTITLE,ISEDIT,ISADD,ISDELETE,ISQUERY,ISBPMFORM) VALUES('10001','test','testForm','测试练习表单',1,1,1,1,2);
/* ============================= 表单对应数据表管理表 =============================  */
INSERT INTO ib_conf_form_table(PACKAGENAME,FORMNAME,TABLENAME,TABLETYPE) VALUES('test','testForm','IB_TEST','main');
/* ============================= 表单对应字段管理表 =============================  */
INSERT INTO ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN,TABLENAME,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCDEFAULT) 
VALUES('test','testForm','IB_TEST.ID','UUID主键','ID','IB_TEST','','','','1','1','2','');
INSERT INTO ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN,TABLENAME,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCDEFAULT) 
VALUES('test','testForm','IB_TEST.NAME','姓名','NAME','IB_TEST','','','','1','1','1','');
INSERT INTO ib_conf_form_table_colums(PACKAGENAME,FORMNAME,FORMCOLUMN,FORMCOLUMNTITLE,TABLECOLUMN,TABLENAME,FCTYPE,FCWIDTH,FCHEIGHT,FCDISPLAY,FCEDIT,FCQUERY,FCDEFAULT) 
VALUES('test','testForm','IB_TEST.REMARK','备注','REMARK','IB_TEST','','','','1','1','2','');


