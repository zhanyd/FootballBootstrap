<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "table");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑表列字段信息</title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/header/header-portal.jsp"%>
	<%@include file="/component/portal/component-leftmenu.jsp"%>

	<!-- start of main -->
	<div class="panel panel-default span10">
          <div class="panel-heading"><h4 class="panel-title">编辑列字段</h4></div>
          <div class="panel-body">
	          <form id="tableForm" method="post" action="conf-table-columns-update.do?tableName=${tableName}" class="form-horizontal">
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-columnValue">字段名:</label>
				      <c:if test="${beanInfo != null}">
				          <label id="table-columnValue" class="control-label">${beanInfo.columnValue}</label>
				          <input id="hidden-columnValue" type="hidden" name="columnValue" value="${beanInfo.columnValue}">
				      </c:if>
				      <c:if test="${beanInfo == null}">
					  	  <input id="table-columnValue" type="text" name="columnValue" value="${beanInfo.columnValue}"  class="text required" >
				      </c:if>
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-columnName">字段标题:</label>
					  <input id="table-columnName" type="text" name="columnName" value="${beanInfo.columnName}"  class="text required" >
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-columnType">字段类型:</label>
				      <div class="col-lg-3">
							 <select id="table-columnType" name="columnType" >
							      <option ${beanInfo.columnType == "VARCHAR" ? 'selected' : ''} >VARCHAR</option>
							      <option ${beanInfo.columnType == "INT" ? 'selected' : ''} >INT</option>
							      <option ${beanInfo.columnType == "DOUBLE" ? 'selected' : ''} >DOUBLE</option>
							      <option ${beanInfo.columnType == "NUMBER" ? 'selected' : ''} >NUMBER</option>
							 </select>
				       </div>
				   </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-columnSize">长度:</label>
					  <input id="table-columnSize" type="text" name="columnSize" value="${beanInfo.columnSize}"  class="text required" >
				  </div>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="table-isNull">允许空</label>
					<div class="col-lg-3">
						<select id="table-isNull" name="isNull"  >
						      <option ${beanInfo.isNull == "是" ? 'selected' : ''}  value="是">是</option>
						      <option ${beanInfo.isNull == "否" ? 'selected' : ''}  value="否">否</option>
						  </select>
				    </div>
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-defaultValue">默认值:</label>
					  <input id="table-defaultValue" type="text" name="defaultValue" value="${beanInfo.defaultValue}">
				  </div>
				  <div class="form-group">
				      <label class="col-lg-2 control-label" for="table-columnNo">字段排序编号:</label>
					  <input id="table-columnNo" type="text" name="columnNo" value="${beanInfo.columnNo}">
				  </div>
				  
				  <div class="form-group">
				      <button id="submitButton" class="btn btn-default a-submit"><spring:message code='core.input.save' text='保存'/></button>
				      <button type="button" onclick="history.back();" class="btn btn-default a-cancel"><spring:message code='core.input.back' text='返回'/></button>
				  </div>
				</form>
          </div>
     </div>
	<!-- end of main -->
  </body>
</html>
