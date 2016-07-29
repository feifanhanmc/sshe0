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
			}]]
		});
	});
</script>
<table id="admin_yhgl_datagrid"></table>