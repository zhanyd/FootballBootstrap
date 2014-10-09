<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "serviceModule");%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑表单组件</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <%@include file="/header/header-portal.jsp"%>
    <div class="span2"></div>
	<!-- start of main -->
	<div class="panel panel-default span10"> 
        <div class="panel-heading"><h4 class="panel-title">编辑表单组件</h4></div>
        <div class="panel-body">
				<form id="formLabelForm" method="post" action="conf-formLabel-save.do" class="form-horizontal">
				    <c:if test="${model != null}">
				        <input type="hidden" name="formName" value="${model.formName}">
				        <input type="hidden" name="formColumn" value="${model.formColumn}">
				        <input type="hidden" name="packageName" value="${model.packageName}">
				        <input type="hidden" name="tableName" value="${model.tableName}">
				        <input type="hidden" name="tableColumn" value="${model.tableColumn}">
				    </c:if>
				    
					  <div class="form-group">
					      <label class="control-label col-lg-2" for="form-column">字段:</label>
					      <label id="form-column" >${model.formColumn}</label>
					  </div>
					  <div class="form-group">
					      <label class="control-label col-lg-2" for="form-column-title">显示标题:</label>
					      <input id="form-column-title" type="text" name="formColumnTitle" value="${model.formColumnTitle}"  class="text required" >
					  </div>
					  <div class="form-group">
					      <label class="control-label col-lg-2" for="form-fcType">组件类型:</label>
					      <div class="col-lg-2">
						      <select id="form-fcType" name="fcType"  class="form-control">
							        <option value="1" ${model.fcType==1 ? 'selected' : ''}>单行</option>
								    <option value="2" ${model.fcType==2 ? 'selected' : ''}>多行</option>
								    <option value="3" ${model.fcType==3 ? 'selected' : ''}>日期</option>
								    <option value="4" ${model.fcType==4 ? 'selected' : ''}>时间</option>
								    <option value="5" ${model.fcType==5 ? 'selected' : ''}>数值</option>
								    <option value="6" ${model.fcType==6 ? 'selected' : ''}>数据字典</option>
							   </select>
						   </div>
					  </div>
					  <div class="form-group">
					      <label class="control-label col-lg-2" for="form-fcWidth">录入宽度:</label>
					      <input id="form-fcWidth" type="text" name="fcWidth" value="${model.fcWidth}"  class="text required" >
					  </div>
					  <div class="form-group">
					      <label class="control-label col-lg-2" for="form-fcHeight">录入高度:</label>
					      <input id="form-fcHeight" type="text" name="fcHeight" value="${model.fcHeight}"  class="text required" >
					  </div>
					  <div class="form-group">
					      <label class="col-lg-2 control-label " for="form-fcDisplay">是否显示:</label>
					      <div class="col-lg-2">
						      <select id="form-fcDisplay" name="fcDisplay"  class="form-control">
							        <option value="1" ${model.fcDisplay==1 ? 'selected' : ''}>是</option>
								    <option value="2" ${model.fcDisplay==2 ? 'selected' : ''}>否</option>
							   </select>
						   </div>
					  </div>
					  <div class="form-group">
					      <label class="col-lg-2 control-label" for="form-fcEdit">是否编辑:</label>
						  <div class="col-lg-2">
					          <select id="form-fcEdit" name="fcEdit"  class="form-control">
							        <option value="1" ${model.fcEdit==1 ? 'selected' : ''}>是</option>
								    <option value="2" ${model.fcEdit==2 ? 'selected' : ''}>否</option>
							   </select>
					      </div>
					  </div>
					  <div class="form-group">
					      <label class="col-lg-2 control-label" for="form-fcEdit">是否查询字段:</label>
						  <div class="col-lg-2">
					          <select id="form-fcQuery" name="fcQuery"  class="form-control">
							        <option value="1" ${model.fcQuery==1 ? 'selected' : ''}>是</option>
								    <option value="2" ${model.fcQuery==2 ? 'selected' : ''}>否</option>
							   </select>
					      </div>
					  </div>
					  <div class="form-group">
					      <label class="control-label col-lg-2" for="form-fcDefault">默认值:</label>
					      <input id="form-fcDefault" type="text" name="fcDefault" value="${model.fcDefault}"  class="text required" >
					      <button type="button" class="btn btn-default  btn-sm">公式</button>
					  </div>
				  
				  <div class="form-group">
				    <div class="controls">
				      <button id="submitButton" class="btn btn-default a-submit"><spring:message code='core.input.save' text='保存'/></button>
				      <button type="button" onclick="history.back();" class="btn btn-default a-cancel"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
				</form>
		</div>
      </div>
	<!-- end of main -->
  </body>
</html>
