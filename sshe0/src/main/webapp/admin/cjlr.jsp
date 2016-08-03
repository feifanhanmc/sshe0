<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$('#admin_cjlr_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/courseStuAction!datagrid.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'sid',
			pageSize : 10 ,
			pageList : [10, 20, 30, 40, 50],
			rownumbers : true,
			sortName : 'name',
			sortOrder : 'asc',
			pagePosition : 'bottom',
			checkOnSelect : false ,
			selectOnCheck : false ,
			frozenColumns : [ [ {
				field : 'sid',
				title : '学生ID',
				width : 150,
				checkbox : true
				//hidden : true	
			},{
				field : 'sname',
				title : '学生姓名',
				width : 80,
				sortable : true				
			}]],
			columns : [ [ {
				field : 'grade',
				title : '分数',
				width : 80,
				sortable : true
			},{
				field : 'rank',
				title : '排名',
				width : 50,
				sortable : true
				
			},{
				field : 'cname',
				title : '课程名称',
				width : 50,
				formatter : function(value, row, index){
					//return '<span title="' + row.name + ' : ' +value + '">' + value + '</span>';
					return '******';
				}								
			}]],
			toolbar : [ {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function(){
					editFun();
				}
			},'-',{
				text : '保存为Excel',
				iconCls : 'icon-ok',
				handler : function(){
					exportExcel();
				}
			},'-']
		});
	});
	
	

	function exportExcel(){
		var postForm = document.getElementById("excelForm");
		postForm.action = "${pageContext.request.contextPath}/userAction!exportExcel.action";
		postForm.submit();
	}		

	function editFun(){
		var rows = $('#admin_cjlr_datagrid').datagrid('getChecked');
		if(rows.length == 1){
			var d = $('<div/>').dialog({
				width : 220,
				height : 200,
				href : '${pageContext.request.contextPath}/admin/cjlrEdit.jsp',
				modal : true,
				title : '编辑用户',
				buttons : [{
					text:'确定',
					iconCls : 'icon-ok',
					handler: function(){
						$('#admin_cjlrEdit_editForm').form('submit',{
							url : '${pageContext.request.contextPath}/userAction!edit.action',
							success : function(r) {
								var o = jQuery.parseJSON(r);
								if (o.success) {
									d.dialog('close');
									//$('#admin_cjlr_datagrid').datagrid('reload');
									$('#admin_cjlr_datagrid').datagrid('updateRow',{
										index : $('#admin_cjlr_datagrid').datagrid('getRowIndex',rows[0].id),
										row : o.obj
									});
								}
								$.messager.show({
									title : '提示',
									msg : o.msg
								});
							}
						});
					}
				}],
				onClose : function(){
					$(this).dialog('destroy');
				},
				onLoad : function(){
					//$('#admin_cjlrEdit_editForm input[name=id]').val(rows[0].id);
					//$('#admin_cjlrEdit_editForm input[name=name]').val(rows[0].name);
					$('#admin_cjlrEdit_editForm').form('load', rows[0]);
					$('#admin_cjlrEdit_editForm input[name=pwd]').val(''); 
				}
			});
		}else{
			if(rows.length > 1){
				$.messager.show({
				title : '提示',
				msg : '不能同时修改多条记录！'
			});
			}else{
				$.messager.show({
				title : '提示',
				msg : '请勾选要修改的记录！'
			});
			}
		}	
	}
	
	function searchFun(){
		$('#admin_cjlr_datagrid').datagrid('load',serializeObject($('#admin_cjlr_searchForm')));
	}
	
	function clearFun(){
		$('#admin_cjlr_layout input[name=name]').val('');
		$('#admin_cjlr_datagrid').datagrid('load',{});
	}

</script>

<div id = "admin_cjlr_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询',border:false" style="height:60px;">
		<form id="admin_cjlr_searchForm">
			查询条件:<input name="name"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="clearFun();">清空</a>	
		</form>
	</div>
	<div data-options="region:'center'">
		<table id="admin_cjlr_datagrid"></table>
	</div>
</div>

<form id="excelForm" method="post">
</form>