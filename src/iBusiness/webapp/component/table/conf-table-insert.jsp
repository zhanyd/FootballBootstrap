<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑评论</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		$(function() {
		    $("#tableCommentForm").validate({
		        submitHandler: function(form) {
					bootbox.animate(false);
					var box = bootbox.dialog('<div class="progress progress-striped active" style="margin:0px;"><div class="bar" style="width: 100%;"></div></div>');
		            form.submit();
		        },
		        errorClass: 'validate-error'
		    });
		})
    </script>
  </head>

  <body>
    <%@include file="/header/header-portal.jsp"%>
	<%@include file="/component/portal/component-leftmenu.jsp"%>

	<!-- start of main -->
	<div class="panel panel-default span10">
        <div class="panel-heading"><h4 class="panel-title">新建表存储模型</h4></div>
        <div class="panel-body">
        
			<div class="content content-inner">
				<form id="tableCommentForm" method="post" action="conf-table-save.do" class="form-horizontal">
				   <input id="table-isBpmTable" type="hidden" name="isBpmTable" value="${isBpmTable}">
				  <c:if test="${model != null}">
				      <input id="table-uuId" type="hidden" name="uuId" value="${model.uuId}">
				  </c:if>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="table-packageName">所在模块:</label>
					<div class="controls">
					    <label class="control-label"  id="table-packageName" >${packageName}</label>
					    <input id="hidden-packageName" type="hidden" name="packageName" value="${packageName}">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="table-tableName">表名称(大写):</label>
					<div class="controls">
					  IB_<input id="table-tableName" type="text" name="tableName" value="${model.tableName}"  class="text required"  >
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="table-tableNameComment">标 题:</label>
					<div class="controls">
					  <input id="table-tableNameComment" type="text" name="tableNameComment" value="${model.tableNameComment}"  class="text required"  >
				    </div>
				  </div>
				  <!--  -->
				  <div class="form-group">
				    <label class="col-lg-2 control-label" for="table-type">表类型:</label>
					<div class="col-lg-3">
				          <select id="table-type" name="tableType"  class="form-control">
						        <option value="1">单表</option>
							    <option value="2">主表</option>
							    <option value="3">子表</option>
						   </select>
				    </div>
				  </div>
				  <div class="control-group">
				      <label class="col-lg-2 control-label" for="table-mainTable">子表对应主表:</label>
					  <div class="controls">
					      <input id="table-mainTable" type="text" name="mainTable" value=""  class="text required"  >
				      </div>
				  </div>
				  
				  <div class="control-group">
				    <div class="controls">
				      <button id="submitButton" class="btn btn-default btn-sm a-submit"><spring:message code='core.input.save' text='保存'/></button>
				      <button type="button" onclick="history.back();" class="btn btn-default btn-sm a-cancel"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
				</form>
			</div>
        </div>
    </div>
	<!-- end of main -->

  </body>

</html>
