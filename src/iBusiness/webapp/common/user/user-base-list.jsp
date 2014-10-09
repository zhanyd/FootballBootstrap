<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

  <head>
    <%@include file="/common/meta.jsp"%>
    <title><spring:message code="user.user.list.title" text="用户列表"/></title>
    <%@include file="/common/center.jsp"%>
    <script type="text/javascript">
		var config = {
		    id: 'userGrid',
		    pageNo: ${page.pageNo},
		    pageSize: ${page.pageSize},
		    totalCount: ${page.totalCount},
		    resultSize: ${page.resultSize},
		    pageCount: ${page.pageCount},
		    orderBy: '${page.orderBy == null ? "" : page.orderBy}',
		    asc: ${page.asc},
		    params: {
		        'filter_LIKES_username': '${param.filter_LIKES_username}',
		        'filter_EQI_status': '${param.filter_EQI_status}'
		    },
			selectedItemClass: 'selectedItem',
			gridFormId: 'userGridForm'
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

    <div class="row">
    <div class="span2"></div>

	<!-- start of main -->
	<div class="panel panel-default span10">
        <div class="panel-heading"><h4 class="panel-title">查询</h4></div>
          <div class="panel-body">
	          <div id="userSearch" class="content content-inner">
			  <form name="userForm" method="post" action="user-base-list.do" class="form-inline">
			     <div class="form-group">
				    <label for="user_username"><spring:message code='user.user.list.search.username' text='账号'/>:</label>
				    <input type="text" id="user_username" name="filter_LIKES_username" value="${param.filter_LIKES_username}">
				    
				    <label for="user_enabled"><spring:message code='user.user.list.search.status' text='状态'/>:</label>
				    <select id="user_enabled" name="filter_EQI_status" class="">
					  <option value=""></option>
					  <option value="1" ${param.filter_EQI_status == 1 ? 'selected' : ''}><spring:message code='user.user.list.search.enabled.true' text='启用'/></option>
					  <option value="0" ${param.filter_EQI_status == 0 ? 'selected' : ''}><spring:message code='user.user.list.search.enabled.false' text='禁用'/></option>
				    </select>
				    <button class="btn btn-default btn-sm" onclick="document.userForm.submit()">查询</button>
			    </div>
			  </form>
			</div>
          
          </div>
        <div class="panel-heading"><h4 class="panel-title">用户列表</h4></div>
          <div class="panel-body">
			    <div class="pull-left">
			        <p>
					    <button class="btn btn-default btn-sm a-insert" onclick="location.href='user-base-input.do'">新建</button>
					    <button class="btn btn-default btn-sm a-remove" onclick="table.removeAll()">删除</button>
				    </p>
				</div>
				<div class="pull-right">
				  每页显示
				  <select class="m-page-size">
				    <option value="5">5</option>
				    <option value="10">10</option>
				    <option value="50">50</option>
				    <option value="100">100</option>
				  </select>
				  条
				</div>
				<div class="m-clear"></div>
	    </div>
		<div class="content">
			<form id="userGridForm" name="userGridForm" method='post' action="user-base-remove.do" class="m-form-blank">
			  <table id="userGrid" class="table table-hover table-bordered">
			    <thead>
			      <tr>
			        <th width="10" class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
			        <th class="sorting" name="id"><spring:message code="user.user.list.id" text="编号"/></th>
			        <th class="sorting" name="username"><spring:message code="user.user.list.username" text="账号"/></th>
			        <th class="sorting" name="displayName">显示名</th>
			        <th class="sorting" name="status"><spring:message code="user.user.list.status" text="状态"/></th>
			        <th class="sorting" name="ref"><spring:message code="user.user.list.ref" text="职务"/></th>
			        <th width="60">&nbsp;</th>
			      </tr>
			    </thead>
			
			    <tbody>
			      <c:forEach items="${page.result}" var="item">
			      <tr>
			        <td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
			        <td>${item.id}</td>
			        <td>${item.username}</td>
			        <td>${item.displayName}</td>
			        <td>${item.status == 1 ? '启用' : '禁用'}</td>
			        <td>${item.jobInfo.jobTitle.name}</td>
			        <td>
			          <a href="user-base-input.do?id=${item.id}" class="a-update"><spring:message code="core.list.edit" text="编辑"/></a>
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
	</div>

  </body>

</html>
