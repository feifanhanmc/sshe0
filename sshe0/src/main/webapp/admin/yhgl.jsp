<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$('#admin_yhgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/userAction!datagrid.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10 ,
			pageList : [10, 20, 30, 40, 50],
			rownumbers : true,
			sortName : 'name',
			sortOrder : 'asc',
			pagePosition : 'bottom',
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true			
			},{
				field : 'name',
				title : '登陆名称',
				width : 150,
				sortable : true				
			}]],
			columns : [ [ {
				field : 'pwd',
				title : '密码',
				width : 150,
				formatter : function(value, row, index){
					return '<span title="' + row.name + ' : ' +value + '">' + value + '</span>';
				}
			},{
				field : 'createdatetime',
				title : '创建时间',
				width : 150,
				sortable : true		
			},{
				field : 'modifydatetime',
				title : '最后修改时间',
				width : 150,
				sortable : true													
			}]],
			toolbar : '#admin_yhgl_toolbar'
		});
	});
	
	function searchFun(){
		$('#admin_yhgl_datagrid').datagrid('load',{
			name : $('#admin_yhgl_layout input[name=name]').val()
		});
	}
	
	function clearFun(){
		$('#admin_yhgl_layout input[name=name]').val('');
		$('#admin_yhgl_datagrid').datagrid('load',{});
	}
</script>
<div id = "admin_yhgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height:100px;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" style="float:left;">编辑</a>
			<div class="datagrid-btn-separator"></div>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">帮助</a>
		检索用户名称(可模糊查询):<input name="name"/>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="clearFun();">清空</a>	
	</div>
	<div data-options="region:'center'">
		<table id="admin_yhgl_datagrid"></table>
	</div>
</div>


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	