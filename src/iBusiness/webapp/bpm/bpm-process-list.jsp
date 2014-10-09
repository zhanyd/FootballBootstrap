<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>流程列表</title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		var config = {
			    id: 'formGrid',
			    pageNo: ${page.pageNo},
			    pageSize: ${page.pageSize},
			    totalCount: ${page.totalCount},
			    resultSize: ${page.resultSize},
			    pageCount: ${page.pageCount},
			    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
			    asc: ${page.asc},
			    params: {
			    	'packageName': '${packageName}'
			    },
				selectedItemClass: 'selectedItem',
				gridFormId: 'bpmFlowGridForm'
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
        <div class="panel-heading"><h4 class="panel-title">流程列表</h4></div>
          <div class="panel-body">
			    <div class="pull-left">
			        <p>
					    <button class="btn btn-default btn-sm a-insert" onclick="location.href='bpm-process-input.do?packageName=${packageName}'">新建</button>
					    <button class="btn btn-default btn-sm a-remove" onclick="table.removeAll()">删除</button>
				    </p>
				</div>
				<div class="pull-right">
				  每页显示
				  <select class="m-page-size">
				    <option value="10">10</option>
				    <option value="50">50</option>
				    <option value="100">100</option>
				  </select>
				  条
				</div>
				<div class="m-clear"></div>
	    </div>
		<div class="content">
			<form id="bpmFlowGridForm" name="bpmFlowGridForm" method='post' action="bpm-process-remove.do?packageName=${packageName}" class="m-form-blank">
			  <table id="formGrid" class="table table-hover table-bordered">
			    <thead>
			      <tr>
			        <th width="10" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
			        <th class="sorting">包名</th>
			        <th class="sorting" >流程名</th>
			        <th class="sorting">流程标题</th>
			        <th width="60">&nbsp;</th>
			        <th width="60">&nbsp;</th>
			        <th width="60">&nbsp;</th>
			      </tr>
			    </thead>
			    
			    <tbody>
			      <c:forEach items="${page.result}" var="item">
			      <tr>
			        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
			        <td>${item.packageName}</td>
			        <td>${item.flowName}</td>
			        <td>${item.flowTitle}</td>
			        <td>
			            <a href="bpm-process-input.do?id=${item.id}&packageName=${packageName}" class="a-update">编辑</a>
			        </td>
			        <td>
			            <a href="${scopePrefix}/bpm-process/bpm-process-graph.do?bpmProcessId=${item.id}" target="_blank" >图形</a>
			        </td>
			        <td>
			            <a href="bpm-conf-node-list.do?flowVersionId=${item.versionId}" >配置</a>
			        </td>
			      </tr>
			      </c:forEach>
			    </tbody>
			  </table>
			</form>
        </div>
	  <article>
	    <div class="m-page-info pull-left">
		  共100条记录 显示1到10条记录
		</div>
		<div class="btn-group m-pagination pull-right">
		  <button class="btn btn-default btn-sm">&lt;</button>
		  <button class="btn btn-default btn-sm">1</button>
		  <button class="btn btn-default btn-sm">&gt;</button>
		</div>
	    <div class="m-clear"></div>
      </article>
    </div>
    
	<!-- end of main -->
  </body>
</html>