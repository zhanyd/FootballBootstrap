<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑表单</title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
	    <%@include file="/header/header-portal.jsp"%>
	    <div class="row">
	    <%@include file="/component/portal/component-leftmenu.jsp"%>
		<!-- start of main -->
		<div class="panel panel-default col-md-10 " > 
		    <!-- tabs  -->
	        <ul class="nav nav-tabs">
			  <li class="${tabType == 'formBase' ? 'active' : ''} "><a href="${scopePrefix}/form/conf-form-input.do?packageName=${packageName}&formId=${formId}&isBpmForm=${isBpmForm}" >表单基础信息</a></li>
			  <li class="${tabType == 'formTables' ? 'active' : ''}"><a href="${scopePrefix}/form/conf-formTables-input.do?packageName=${packageName}&formId=${formId}" >关联表字段</a></li>
			  <li class="${tabType == 'formLabel' ? 'active' : ''}"><a href="${scopePrefix}/form/conf-formLabel-list.do?packageName=${packageName}&formId=${formId}">控件类型</a></li>
			  <c:if test="${isBpmForm == '2'}">
			      <li class="${tabType == 'formCode' ? 'active' : ''}"><a href="${scopePrefix}/code/code-generate-input.do?packageName=${packageName}&formId=${formId}">代码生成</a></li>
			  </c:if>
			</ul>
			<div id="tabContent" class="tab-content">
			  <!-- ========================== 表单基础信息 =================================================== -->
			  <div id="formBase" class="tab-pane fade ${tabType == 'formBase' ? 'active in' : ''}">
			  <c:if test="${tabType == 'formBase'}">
			        <div class="panel-body">
				        <form id="formForm" method="post" action="conf-form-save.do" class="form-horizontal">
							  <c:if test="${model != null}">
							  	  <input id="conf-form_id" type="hidden" name="id" value="${formId}">
							  	  <input id="conf-form-isBpmForm" type="hidden" name="isBpmForm" value="${isBpmForm}">
							  	  <input id="conf-form_packagename" type="hidden" name="packageName" value="${model.packageName}">
							  </c:if>
							  <div class="form-group">
							    <label class="col-lg-2 control-label" for="form_package_name">包名:</label>
							    <label class="control-label" id="form_package_name">${model.packageName}</label>
							  </div>
							  <div class="form-group">
							      <label class="col-lg-2 control-label" for="form-formname">表单名称:</label>
							      <c:if test="${model.formName == null }">
								      <div class="col-lg-3">
									      <input id="form-formname" type="text" name="formName" value="${model.formName}"  class="form-control" >
									  </div>
							      </c:if>
							      <c:if test="${model.formName != null}">
							           <label class="control-label" >${model.formName}</label>
							           <input id="form_form_name" type="hidden" name="formName" value="${model.formName}">
							      </c:if>
							  </div>
							  <div class="form-group">
							      <label class="col-lg-2 control-label" for="form-formtitle">表单标题:</label>
							      <div class="col-lg-3">
								      <input id="form-formtitle" type="text" name="formTitle" value="${model.formTitle}"  class="form-control" >
								  </div>
							  </div>
							  
							  <div class="form-group">
							          <label class="col-lg-2 control-label" >表单操作设置:</label>
								      <div class="checkbox">
								          <label><input type="checkbox" name="selectedItem" value="isEdit" <tags:contains items="${selectedItem}" item="isEdit">checked</tags:contains>> 是否可修改</label>
								          <label><input type="checkbox" name="selectedItem"  value="isAdd" <tags:contains items="${selectedItem}" item="isAdd">checked</tags:contains>> 是否可新增</label>
								          <label><input type="checkbox" name="selectedItem"  value="isDelete" <tags:contains items="${selectedItem}" item="isDelete">checked</tags:contains>> 是否可删除</label>
								          <label><input type="checkbox" name="selectedItem"  value="isQuery" <tags:contains items="${selectedItem}" item="isQuery">checked</tags:contains>> 是否可查询</label>
								       </div>
							  </div>
							  <div class="col-lg-10 col-lg-offset-2">
							      <button id="submitButton" class="btn btn-default a-submit"><spring:message code='core.input.save' text='保存'/></button>
							      <button type="button" onclick="history.back();" class="btn btn-default a-cancel"><spring:message code='core.input.back' text='返回'/></button>
							  </div>
						</form>
	        		</div>
	        	  </c:if>
	        	  </div>
	        	  <!-- ==================== 关联表设置 ===================================================== -->
	        	  <div id="formTables" class="tab-pane fade ${tabType == 'formTables' ? 'active in' : ''}">
	        	  <c:if test="${tabType == 'formTables'}">
	        	      <div class="panel panel-primary">
		        		  <div class="panel-heading"><h4 class="panel-title">选择主表/单表：</h4></div>
					          <div class="panel-body">
								   <form name="formTablesForm" method="post" action="conf-formTables-save.do?tableType=main&packageName=${packageName}" class="form-inline">
								      <c:if test="${formName != null}">
								              <input id="conf-form_id" type="hidden" name="formId" value="${formId}">
						  	          </c:if>
								      <div class="form-group">
									      <label class="control-label" for="role_def">表名:</label>
										  <select id="role_def" name="tableId" class="form-control">
										        <option value="" >请选择</option>
											  <c:forEach items="${mainTable}" var="item">
											    <option value="${item.id}" >${item.tableNameComment}</option>
											  </c:forEach>
										  </select>
										  <span class="input-group-btn">
										      <button class="btn btn-default btn-sm a-submit" ><spring:message code='core.success.save' text='添加'/></button>
										  </span>
									   </div>
									 </form>
									 <!-- 添加表后生成字段 -->
									 <div class="form-group">
										  <c:forEach items="${mainFormTable}" var="mainFormTable">
										      <div class="row-fluid">
										          <div class="well ">
										              <div class="">${mainFormTable.tableName}</div>
										              <button class="btn btn-default btn-sm a-remove" onclick="location.href='conf-formTables-remove.do?packageName=${packageName}&tableName=${mainFormTable.tableName}&formName=${mainFormTable.formName}'" >删除</button>
												      <table class="table table-striped table-hover table-bordered">
														  <thead>
														    <tr>
														         <th>标题：</th>
															      <c:forEach items="${mainFormTable.tableColumns}" var="tableColumn">
																      <th>${tableColumn.columnName}</th>
															      </c:forEach>
														    </tr>
														  </thead>
														  <tbody>
														    <tr>
														        <td>字段:</td>
														        <c:forEach items="${mainFormTable.tableColumns}" var="tableColumn">
														      		<td>${tableColumn.columnValue}</td>
														        </c:forEach>
														    </tr>
														  </tbody>
														</table> 
										          </div>
									      	  </div>
											</c:forEach>
									  </div>
								  
					          </div>
					          <div class="panel-heading"><h4 class="panel-title">选择子表：</h4></div>
					          <div class="panel-body">
								  <form name="formTablesForm" method="post" action="conf-formTables-save.do?tableType=sub" class="form-inline">
				  	                  <input id="conf-form_name" type="hidden" name="formId" value="${formId}">
				  	                  <input id="conf-package-name" type="hidden" name="packageName" value="${packageName}">
								      <div class="form-group">
								          <label class="control-label" for="role_def">表名:</label>
										  <select id="sub_table_id" name="tableId" class="form-control">
										      <option value="" >请选择</option>
											  <c:forEach items="${subTable}" var="item">
											    <option value="${item.id}" >${item.tableNameComment}</option>
											  </c:forEach>
										  </select>
										  <span class="input-group-btn">
										      <button class="btn btn-default btn-sm a-submit" >添加</button>
										  </span>
									    </div>
								    </form>
							    	<div class="form-group">
									  <!-- 添加表后生成字段 -->
									  <c:forEach items="${subFormTables}" var="subFormTable">
									      <div class="row-fluid col-lg-4">
									          <div class="well ">
									              <div class="">${subFormTable.tableName}</div>
									              <button class="btn btn-default btn-sm a-remove" onclick="location.href='conf-formTables-remove.do?packageName=${packageName}&tableName=${subFormTable.tableName}&formName=${subFormTable.formName}'" >删除</button>
											      <table class="table table-striped table-hover table-bordered">
													  <thead>
															<tr>
																<th>字段</th>
																<th>标题</th>
															</tr>
														</thead>
														<tbody>
														<c:forEach items="${subFormTable.tableColumns}" var="tableColumn">
														    <tr>
																<td>${tableColumn.columnValue}</td>
																<td>${tableColumn.columnName}</td>
														    </tr>
														  </c:forEach>
														</tbody>
													</table> 
									          </div>
								      	  </div>
										</c:forEach>
								     </div>
					          </div>
				         </div>
		          </c:if>
	        	  </div>
	        	  <!-- ================================= 控件类型 =============================================== -->
	        	  <div id="formLabel" class="tab-pane fade ${tabType == 'formLabel' ? 'active in' : ''}">
	        	  <c:if test="${tabType == 'formLabel'}">
	        	      <table class="table table-hover table-bordered" id=“tableModelGrid” >
							<thead>
								<tr>
								    <th>字段</th>
								    <th>显示标题</th>
								    <th class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)">是否显示</th>
								    <th class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems2(this.checked)">是否编辑</th>
								    <th class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems3(this.checked)">查询字段</th>
									<th width="120">&nbsp;</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${formTableColumns}" var="item">
							    <tr>
							        <td>${item.formColumn}</td>
							        <td>${item.formColumnTitle}</td>
							        <td><input type="checkbox" class="selectedItem a-check" name="selectedFcDisplayItem" value="${item.formColumn}" ${item.fcDisplay==1 ? 'checked' : ''}>显示</td>
									<td><input type="checkbox" class="selectedItem2 a-check" name="selectedFcEditItem" value="${item.formColumn}" ${item.fcEdit==1 ? 'checked' : ''}>可编辑</td>
									<td><input type="checkbox" class="selectedItem3 a-check" name="selectedFcQueryItem" value="${item.formColumn}" ${item.fcQuery==1 ? 'checked' : ''}>查询条件</td>
									<td>
							            <a href="conf-formLabel-input.do?packageName=${item.packageName}&formName=${item.formName}&formColumn=${item.formColumn}" class="a-update"><spring:message code="core.list.edit" text="编辑"/></a>
							        </td>
							    </tr>
							  </c:forEach>
							</tbody>
					  </table>
				  </c:if>
	        	  </div>
	        	  <!-- ======================= 代码生成=================================================== -->
	        	  <div id="formCode" class="tab-pane fade ${tabType == 'formCode' ? 'active in' : ''}">
	        	  <c:if test="${tabType == 'formCode'}">
		        	    <div class="panel-heading"><h4 class="panel-title">一对一生成器</h4></div>
					        <div class="panel-body">
					              <form id="menuForm" method="post" action="code-generate-save.do" class="form-horizontal">
									  <c:if test="${model != null}">
									      <input type="hidden" name="formId" value="${formId}">
									      <input type="hidden" name="packageName" value="${model.packageName}">
									      <input type="hidden" name="formName" value="${model.formName}">
									  </c:if>
									  <div class="form-group">
										  <label class="col-lg-2 control-label" >包名(小写):</label>
										  <label class="control-label" >${model.packageName}</label>
									  </div>
									  <div class="form-group">
										  <label class="col-lg-2 control-label" for="code_entityName">实体类名(首字母大写):</label>
									      <input id="code_entityName" type="text" name="entityName" value="${model.entityName}" class="text">
									  </div>
									  <div class="form-group">
										  <label class="col-lg-2 control-label" for="code_tableName">表名:</label>
										  <input id="code_tableName" type="text" name="tableName" value="${model.tableName}"  class="text required"  >
									  </div>
									  <div class="form-group">
										  <label class="col-lg-2 control-label" >主键生成策略:</label>
										  <label class="control-label" >${model.keyType}</label>
									  </div>
									  <div class="form-group">
										  <label class="col-lg-2 control-label" for="code_entityTitle">功能描述:</label>
										  <input id="code_entityTitle" type="text" name="entityTitle" value="${model.entityTitle}"  class="text required"  >
									  </div>
									  <div class="form-group">
										  <label class="col-lg-2 control-label" >行字段数目:</label>
										  <label class="control-label" >${model.rowNumber}</label>
									  </div>
									  <div class="form-group">
									        <label class="col-lg-2 control-label" >需要生成的代码:</label>
									      <div class="checkbox">
									          <label><input type="checkbox" name="selectedItem" value="checkboxController" <tags:contains items="${selectedItem}" item="checkboxController">checked</tags:contains>> Controller</label>
									          <label><input type="checkbox" name="selectedItem"  value="checkboxEntity" <tags:contains items="${selectedItem}" item="checkboxEntity">checked</tags:contains>> Entity</label>
									          <label><input type="checkbox" name="selectedItem"  value="checkboxJsp" <tags:contains items="${selectedItem}" item="checkboxJsp">checked</tags:contains>> Jsp</label>
									          <label><input type="checkbox" name="selectedItem"  value="checkboxService" <tags:contains items="${selectedItem}" item="checkboxService">checked</tags:contains>> Service</label>
									          <label><input type="checkbox" name="selectedItem"  value="checkboxServiceImp" <tags:contains items="${selectedItem}" item="checkboxServiceImp">checked</tags:contains>> ServiceImp</label>
									       </div>
									  </div>
									  <div class="form-group">
										  <label class="col-lg-2 control-label" for="code_menuUrl">菜单URL:</label>
										  <div class="col-lg-3">
										      <textarea class="form-control" id="code_menuUrl" name="menuUrl" rows="2">${model.menuUrl}</textarea>
										  </div>
									  </div>
									  <div class="form-group">
										  <label class="col-lg-2 control-label" >风格:</label>
										  <label class="control-label" >${model.formStyle}</label>
									  </div>
									  <div class="control-group">
									    <div class="controls">
									      <button id="submitButton" class=" btn btn-default a-submit"><spring:message code='core.input.save' text='生成'/></button>
										  &nbsp;
									      <button type="button" onclick="history.back();" class="btn btn-default"><spring:message code='core.input.back' text='返回'/></button>
									    </div>
									  </div>
									</form>
					        </div>
			        </c:if>
	        	  </div>
	        	  
	         </div>
	    </div>
		<!-- end of main -->
		</div>
  </body>

</html>
