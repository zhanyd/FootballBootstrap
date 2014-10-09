
/*===============  USER_REPO  用户库表 =============================*/
INSERT INTO USER_REPO(ID,CODE,NAME,REF,SCOPE_ID) VALUES(1,'default','默认','1','1');

/*===============  USER_BASE  用户基础表 =============================*/
INSERT INTO USER_BASE(ID,USERNAME,PASSWORD,DISPLAY_NAME,STATUS,REF,USER_REPO_ID,SCOPE_ID,JOB_INFO_ID,CSS,ROLE_DEF_ID) VALUES(1,'root','a1ccdbc7f295e0aeda5dc4e0f2677ea3','管理员',1,1,1,'1',1,'Cerulean',2);
INSERT INTO USER_BASE(ID,USERNAME,PASSWORD,DISPLAY_NAME,STATUS,REF,USER_REPO_ID,SCOPE_ID,JOB_INFO_ID,CSS,ROLE_DEF_ID) VALUES(2,'admin','a1ccdbc7f295e0aeda5dc4e0f2677ea3','系统管理员',1,2,1,'1',1,'Cerulean',2);
INSERT INTO USER_BASE(ID,USERNAME,PASSWORD,DISPLAY_NAME,STATUS,REF,USER_REPO_ID,SCOPE_ID,JOB_INFO_ID,CSS,ROLE_DEF_ID) VALUES(3,'cuitianyu','a1ccdbc7f295e0aeda5dc4e0f2677ea3','催天宇',1,3,1,'1',1,'Cerulean',2);
INSERT INTO USER_BASE(ID,USERNAME,PASSWORD,DISPLAY_NAME,STATUS,REF,USER_REPO_ID,SCOPE_ID,JOB_INFO_ID,CSS,ROLE_DEF_ID) VALUES(4,'jiangbo','a1ccdbc7f295e0aeda5dc4e0f2677ea3','姜博',1,4,1,'1',3,'Spacelab',3);
INSERT INTO USER_BASE(ID,USERNAME,PASSWORD,DISPLAY_NAME,STATUS,REF,USER_REPO_ID,SCOPE_ID,JOB_INFO_ID,CSS,ROLE_DEF_ID) VALUES(5,'yanlei','a1ccdbc7f295e0aeda5dc4e0f2677ea3','严磊',1,5,1,'1',2,'Cerulean',3);
INSERT INTO USER_BASE(ID,USERNAME,PASSWORD,DISPLAY_NAME,STATUS,REF,USER_REPO_ID,SCOPE_ID,JOB_INFO_ID,CSS,ROLE_DEF_ID) VALUES(6,'zhanyingda','a1ccdbc7f295e0aeda5dc4e0f2677ea3','詹应答',1,6,1,'1',3,'Cerulean',3);
INSERT INTO USER_BASE(ID,USERNAME,PASSWORD,DISPLAY_NAME,STATUS,REF,USER_REPO_ID,SCOPE_ID,JOB_INFO_ID,CSS,ROLE_DEF_ID) VALUES(7,'lvdeyang','a1ccdbc7f295e0aeda5dc4e0f2677ea3','吕德阳',1,7,1,'1',3,'Cerulean',3);
UPDATE USER_BASE set mobile='18212345678';
