<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%pageContext.setAttribute("currentHeader", "table");%>
<%pageContext.setAttribute("currentMenu", "table");%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>流程表列表</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		var config = {
		    id: 'tableModelGrid',
			selectedItemClass: 'selectedItem',
			gridFormId: 'tableGridForm'
		};
		
		var table;
		
		$(function() {
			table = new Table(config);
		    table.configPagination('.m-pagination');
		    table.configPageInfo('.m-page-info');
		    table.configPageSize('.m-page-size');
		});
    </script>
  </head>

  <body>
    <%@include file="/header/header-portal.jsp"%>
    <%@include file="/component/portal/component-leftmenu.jsp"%>

	<!-- start of main -->
	<div class="panel panel-default span10">
          <div class="panel-heading"><h4 class="panel-title">流程表模型</h4></div>
          <div class="panel-body">
                <div id="tableCategorySearch" class="content content-inner">
				  	<button class="btn btn-default btn-sm a-insert" onclick="location.href='conf-table-insert.do?packageName=${packageName}&isBpmTable=1'">新建</button>
				  	<button class="btn btn-default btn-sm a-remove" onclick="table.removeAll()">删除</button>
				</div>
            </div>
            <form id="tableGridForm" name="tableGridForm" method='post' action="conf-table-remove.do?packageName=${packageName}" class="m-form-blank">
				  <table class="table table-hover table-bordered" id=“tableModelGrid” >
						<thead>
							<tr>
							    <th width="10" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
								<th>表名</th>
								<th>标题</th>
								<th>所在模块</th>
								<th>唯一标识</th>
								<th width="120">&nbsp;</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${tableInfoList}" var="item">
						    <tr>
							    <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
								<td>${item.tableName}</td>
								<td>${item.tableNameComment}</td>
								<td>${item.packageName}</td>
								<td>${item.id}</td>
								<td>
						          <a href="conf-table-column-list.do?tableName=${item.tableName}" class="a-update"><spring:message code="core.list.edit" text="编辑表字段"/></a>
						        </td>
						    </tr>
						  </c:forEach>
						</tbody>
				  </table>
			</form>
     </div>
     
	<!-- end of main -->
  </body>
</html>