<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "auth");%>
<%pageContext.setAttribute("currentMenu", "auth");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="auth.role.perm.title" text="菜单设置权限"/></title>
    <%@include file="/common/center.jsp"%>
  </head>

  <body>
    <%@include file="/header/header-portal.jsp"%>

    <div class="span2"></div>

	<!-- start of main -->
	<div class="panel panel-default span10">
        <div class="panel-heading"><h4 class="panel-title">菜单设置权限</h4></div>
        <div class="panel-body">
       	 <form id="roleForm" method="post" action="role-menu-save.do" class="form-horizontal">
			  <input type="hidden" name="id" value="${id}">
				  <c:forEach items="${menus}" var="item">
					  <div class="control-group">
					        <input id="selectedItem-${item.id}" type="checkbox" name="selectedItem" value="${item.id}" <tags:contains items="${selectedItem}" item="${item.id}">checked</tags:contains>>
					        <label for="selectedItem-${item.id}" style="display:inline;">${item.menuName}</label>
							<c:if test="${item.chiledItems != '[]'}">
							    <div class="controls">
								      <c:forEach items="${item.chiledItems}" var="levelTwo">
									        <input id="selectedItem-${levelTwo.id}" type="checkbox" name="selectedItem" value="${levelTwo.id}" <tags:contains items="${selectedItem}" item="${levelTwo.id}">checked</tags:contains>>
									        <label for="selectedItem-${levelTwo.id}" style="display:inline;">${levelTwo.menuName}</label>
											&nbsp;
											<c:if test="${levelTwo.chiledItems != '[]'}">
											        <div class="controls">
												        <c:forEach items="${levelTwo.chiledItems}" var="levelThree">
												            <input id="selectedItem-${levelThree.id}" type="checkbox" name="selectedItem" value="${levelThree.id}" <tags:contains items="${selectedItem}" item="${levelThree.id}">checked</tags:contains>>
									                        <label for="selectedItem-${levelThree.id}" style="display:inline;">${levelThree.menuName}</label>
												        </c:forEach>
											        </div>
											</c:if>
								      </c:forEach>
							    </div>
						    </c:if>
					  </div>
				  </c:forEach>
				  <div class="control-group">
				    <div class="controls">
				      <button id="submitButton" class="btn btn-default a-submit"><spring:message code='core.input.save' text='保存'/></button>
					  &nbsp;
				      <button type="button" onclick="history.back();" class="btn btn-default"><spring:message code='core.input.back' text='返回'/></button>
				    </div>
				  </div>
			</form>
        </div>
     </div>
     
	<!-- end of main -->
  </body>
</html>
