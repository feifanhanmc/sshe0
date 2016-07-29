<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$('#admin_yhgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/userAction!datagrid.action',
			fit : true,
			border : false,
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 150			
			},{
				field : 'name',
				title : '登陆名称',
				width : 150				
			},{
				field : 'pwd',
				title : '密码',
				width : 150	
			},{
				field : 'createdatetime',
				title : '创建时间',
				width : 150	
			},{
				field : 'modifydatetime',
				title : '最后修改时间',
				width : 150												
			}]]
		});
	});
</script>
<table id="admin_yhgl_datagrid"></table>