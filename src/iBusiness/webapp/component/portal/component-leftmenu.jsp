<%@ page language="java" pageEncoding="UTF-8" %>

      <!-- start of sidebar -->
      <aside id="m-sidebar" class="accordion col-md-2" data-spy="affix" data-offset-top="100">

        <div class="accordion-group">
          <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse" data-parent="#m-sidebar" href="#collapse-group">
              <i class="icon-user"></i>
              <span class="title">业务模块组件</span>
            </a>
          </div>
        <!--   <div id="collapse-group" class="accordion-body collapse  in}"> -->
          <div id="collapse-group">
            <ul id="treeMenu" class="ztree"></ul>
          </div>
        </div>

		<footer id="m-footer" class="text-center">
		  <hr>
		  &copy;天翔
		</footer>
      </aside>
      <!-- end of sidebar -->

<script type="text/javascript">
		var setting = {
			async: {
				enable: true,
				url: "${scopePrefix}/rs/component/tree?parentId=${parentId}"
			},
			callback: {
				onClick: function(event, treeId, treeNode) {
					location.href = '${scopePrefix}/component/component-action.do?packageName=' +  treeNode.packageName + '&typeId=' + treeNode.typeId
							   + '&tableName=' + treeNode.tableName + '&formId=' + treeNode.formId + '&flowId='  + treeNode.flowId;
				}
			}
		};

		var zNodes =[
		             ];

		$(function(){
			$.fn.zTree.init($("#treeMenu"), setting, zNodes);
		});
</script>
