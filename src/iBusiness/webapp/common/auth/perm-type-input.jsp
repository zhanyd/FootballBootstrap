<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "auth");%>
<%pageContext.setAttribute("currentMenu", "auth");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>编辑权限类型</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
$(function() {
    $("#permTypeForm").validate({
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

    <div class="span2">

	<!-- start of main -->
	<div class="panel panel-default span10"> 
        <div class="panel-heading"><h4 class="panel-title">编辑权限类型</h4></div>
        <div class="panel-body">
        	 <form id="permTypeForm" method="post" action="perm-type-save.do" class="form-horizontal">
				  <c:if test="${model != null}">
				  <input id="permType_id" type="hidden" name="id" value="${model.id}">
				  </c:if>
				  <p>
					  <label class="control-label" for="permType_name"><spring:message code='auth.permType.input.name' text='名称'/>:</label>
				      <input id="permType_name" type="text" name="name" value="${model.name}"  class="text required"  maxlength="50">
				  </p>
				  <p>
					<label class="control-label" for="permType_type"><spring:message code='auth.permType.input.type' text='类型'/>:</label>
				 </p>
				 <p>
					 <div class="radio">
					  <label for="permType_type_0" >
					    <input id="permType_type_0" type="radio" name="type"  value="0" ${model.type != 1 ? 'checked' : ''}>
					      显示
					  </label>
					</div>
				    <div class="radio">
					  <label for="permType_type_1" >
					    <input id="permType_type_1" type="radio" name="type"  value="1" ${model.type == 1 ? 'checked' : ''}>
					      隐藏
					  </label>
					</div>
					  <label for="permType_type_0" class="validate-error" generated="true" style="display:none;"></label>
				  </p>
				  <p>
				    <label class="control-label" for="permType_descn"><spring:message code='auth.permType.input.description' text='描述'/>:</label>
				      <textarea id="permType_descn" name="descn" maxlength="60" rows="4">${model.descn}</textarea>
				  </p>
				  <p>
				      <button id="submitButton" class="btn btn-default a-submit"><spring:message code='core.input.save' text='保存'/></button>
					  &nbsp;
				      <button type="button" onclick="history.back();" class="btn btn-default"><spring:message code='core.input.back' text='返回'/></button>
				  </p>
				</form>
        </div>
     </div>
	<!-- end of main -->

  </body>

</html>
