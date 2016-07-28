<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-panel" data-options="title:'功能导航',border:false,fit:true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="系统菜单" data-options="iconCls:'icon-tip'">
			<ul id="layout_west_tree" class="easyui-tree" data-options="
					url : '${pageContext.request.contextPath}/menuAction!getTreeNode.action'
					,lines : true
					,onClick : function(node) {
						addTab({title : node.text});
					}"></ul>
		</div>
		<div title="Title2" data-options="iconCls:'icon-search'"></div>
	</div>
</div>