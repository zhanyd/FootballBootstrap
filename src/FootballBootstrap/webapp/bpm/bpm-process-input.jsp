<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑</title>
    <%@include file="/common/center.jsp"%>
  </head>
  <body>
    <%@include file="/header/header-portal.jsp"%>
    <div class="row">
	<%@include file="/component/portal/component-leftmenu.jsp"%>
	<!-- start of main -->
	<div class="panel panel-default span10"> 
        <div class="panel-heading"><h4 class="panel-title">编辑</h4></div>
        <div class="panel-body">
		<div class="content content-inner">
				<form id="userForm" method="post" action="bpm-process-save.do" class="form-horizontal">
				  <input id="user-base_userRepoId" type="hidden" name="userRepoId" value="1">
				  <c:if test="${model != null}">
				      <input id="code_id" type="hidden" name="id" value="${model.id}">
				      <input id="code_id" type="hidden" name="packageName" value="${model.packageName}">
				  </c:if>
			      <div class="form-group">
					      <label class="col-lg-2 control-label" for="code-packageName">包名:</label>
					      <label class="control-label" id="code-packageName">${model.packageName}</label>
			       </div>
				   <div class="form-group">
					      <label class="col-lg-2 control-label" for="code-flowName">流程名:</label>
					      <c:if test="${model.flowName == null }">
						      <div class="col-lg-3">
							      <input id="code-flowName" type="text" name="flowName" value="${model.flowName}"  class="form-control" >
						      </div>
					      </c:if>
					      <c:if test="${model.flowName != null}">
					           <label class="control-label" >${model.flowName}</label>
					           <input id="code-flowName" type="hidden" name="flowName" value="${model.flowName}">
					      </c:if>
				   </div>
				   <div class="form-group">
					    <label class="col-lg-2 control-label" for="code-flowTitle">流程标题:</label>
						<div class="col-lg-3">
						  <input id="code-flowTitle" type="text" name="flowTitle" value="${model.flowTitle}"  class="form-control" >
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-lg-2 control-label" for="bpm-graph">发起流程:</label>
						<div class="col-lg-3">
						    <a class="btn btn-primary" href="form-viewStartForm.do?bpmProcessId=${model.id}" target="_blank" >发起</a>
					    </div>
					</div>
					<div class="form-group">
					    <label class="col-lg-2 control-label" for="bpm-graph">配置:</label>
						<div class="col-lg-3">
						    <a class="btn btn-primary" href="${scopePrefix}/bpm-process/bpm-conf-node-list.do?flowVersionId=${model.versionId}" >配置</a>
					    </div>
					</div>
					<div class="form-group">
					    <label class="col-lg-2 control-label" for="bpm-graph">图形:</label>
						<div class="col-lg-3">
						    <a class="btn btn-primary" href="${scopePrefix}/bpm-process/bpm-process-graph.do?bpmProcessId=${model.id}" target="_blank" >图形</a>
					    </div>
					</div>
				  
				  <div class="col-lg-10 col-lg-offset-2">
				      <button id="submitButton" class="btn a-submit"><spring:message code='core.input.save' text='保存'/></button>
				      <button type="button" onclick="history.back();" class="btn a-cancel"><spring:message code='core.input.back' text='返回'/></button>
				  </div>
				</form>
		</div>
        </div>
    </div>
	<!-- end of main -->
	</div>
  </body>
</html>
