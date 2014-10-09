<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "group-sys");%>
<%pageContext.setAttribute("currentMenu", "job");%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑职务</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#orgForm").validate({
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

    <div class="span2"></div>

	<!-- start of main -->
	<div class="panel panel-default span10"> 
        <div class="panel-heading"><h4 class="panel-title">编辑职务</h4></div>
        <div class="panel-body">
	        <form id="orgForm" method="post" action="job-info-save.do" class="form-horizontal">
			  <c:if test="${model != null}">
			  	<input id="org_id" type="hidden" name="id" value="${model.id}">
			  </c:if>
			  <p>
			    <label class="control-label" for="job_title">职务名称:</label>
				  <select id="job_title" name="jobTitleId">
				  <c:forEach items="${jobTitles}" var="item">
				    <option value="${item.id}">${item.name}</option>
				  </c:forEach>
				  </select>
			  </p>
			  <p>
			    <label class="control-label" for="job_type">职称分类:</label>
				  <select id="job_type" name="jobTypeId">
				  <c:forEach items="${jobTypes}" var="item">
				    <option value="${item.id}">${item.name}</option>
				  </c:forEach>
				  </select>
			  </p>
			  <p>
			      <button id="submitButton" class="btn btn-default a-submit"><spring:message code='core.input.save' text='保存'/></button>
			      <button type="button" onclick="history.back();" class="btn btn-default"><spring:message code='core.input.back' text='返回'/></button>
			  </p>
			</form>
        </div>
	<!-- end of main -->
	</div>

  </body>

</html>
