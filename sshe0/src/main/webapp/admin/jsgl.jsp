<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$('#admin_jsgl_datagrid').datagrid({
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
			checkOnSelect : false ,
			selectOnCheck : false ,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : true
				//hidden : true	
			},{
				field : 'account',
				title : '账号',
				width : 80,
				sortable : true				
			}]],
			columns : [ [ {
				field : 'name',
				title : '姓名',
				width : 80,
				sortable : true
			},{
				field : 'role',
				title : '角色',
				width : 50,
				sortable : true
				
			},{
				field : 'pwd',
				title : '密码',
				width : 50,
				formatter : function(value, row, index){
					//return '<span title="' + row.name + ' : ' +value + '">' + value + '</span>';
					return '******';
				}
			},{
				field : 'modifytime',
				title : '修改时间',
				width : 150,
				sortable : true													
			}]],
			toolbar : [ { 
				text : '增加',
				iconCls : 'icon-add',
				handler : function(){
					append();
				}
			},'-',{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function(){
					remove();
				}
			},'-',{
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
		var rows = $('#admin_jsgl_datagrid').datagrid('getChecked');
		if(rows.length == 1){
			var d = $('<div/>').dialog({
				width : 220,
				height : 200,
				href : '${pageContext.request.contextPath}/admin/jsglEdit.jsp',
				modal : true,
				title : '编辑用户',
				buttons : [{
					text:'确定',
					iconCls : 'icon-ok',
					handler: function(){
						$('#admin_jsglEdit_editForm').form('submit',{
							url : '${pageContext.request.contextPath}/userAction!edit.action',
							success : function(r) {
								var o = jQuery.parseJSON(r);
								if (o.success) {
									d.dialog('close');
									//$('#admin_jsgl_datagrid').datagrid('reload');
									$('#admin_jsgl_datagrid').datagrid('updateRow',{
										index : $('#admin_jsgl_datagrid').datagrid('getRowIndex',rows[0].id),
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
					//$('#admin_jsglEdit_editForm input[name=id]').val(rows[0].id);
					//$('#admin_jsglEdit_editForm input[name=name]').val(rows[0].name);
					$('#admin_jsglEdit_editForm').form('load', rows[0]);
					$('#admin_jsglEdit_editForm input[name=pwd]').val(''); 
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
		$('#admin_jsgl_datagrid').datagrid('load',serializeObject($('#admin_jsgl_searchForm')));
	}
	
	function clearFun(){
		$('#admin_jsgl_layout input[name=name]').val('');
		$('#admin_jsgl_datagrid').datagrid('load',{});
	}
	
	function append(){
		$('#admin_jsgl_addForm input').val('');
		$('#admin_jsgl_addDialog').dialog('open');
	}
	
	function remove(){
		var rows = $('#admin_jsgl_datagrid').datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/userAction!remove.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							//$('#admin_jsgl_datagrid').datagrid('reload');
							$('#admin_jsgl_datagrid').datagrid('load');						
							$('#admin_jsgl_datagrid').datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						}
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	}

//	$(function() {
//		$('#admin_jsgl_searchForm input').bind('keyup', function(event) {/* 增加回车提交功能 */
//			if (event.keyCode == '13') {
//				$('#admin_jsgl_datagrid').datagrid('load',serializeObject($('#admin_jsgl_searchForm')));
//			}
//		});
//	});

</script>

<div id = "admin_jsgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询',border:false" style="height:60px;">
		<form id="admin_jsgl_searchForm">
			查询条件:<input name="name"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="clearFun();">清空</a>	
		</form>
	</div>
	<div data-options="region:'center'">
		<table id="admin_jsgl_datagrid"></table>
	</div>
</div>
<div id="admin_jsgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '清空',
				iconCls : 'icon-undo',
				handler : function(){
					$('#admin_jsgl_addForm input').val('');
				}
				},{
				text : '确定',
				iconCls : 'icon-ok',
				handler : function(){
					$('#admin_jsgl_addForm').form('submit',{
							url:'${pageContext.request.contextPath}/userAction!add.action',
							success:function(r){
									var o = jQuery.parseJSON(r);
									if(o.success){
										/*$('#admin_jsgl_datagrid').datagrid('load');*/
										/*$('#admin_jsgl_datagrid').datagrid('appendRow',o.obj);*/
										$('#admin_jsgl_datagrid').datagrid('insertRow',{
											index:0,
											row:o.obj
										});
										$('#admin_jsgl_addDialog').dialog('close');
									}
									$.messager.show({
										title : '提示',
										msg : o.msg
									});
							}
					});
				}
			}]" style="width:220px;height:190px;" align="center">
	<form id="admin_jsgl_addForm" method="post">
	<table>
		<tr>
			<th>账号</th>
			<td><input name="account" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>密码</th>
			<td><input name="pwd" class="easyui-validatebox" data-options="required:true"/>
			</td>
		</tr>
		<tr>
			<th>姓名</th>
			<td><input name="name" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>角色</th>
			<td>
				<select class="easyui-combobox" name="role" panelHeight="auto" editable="false" style="width:143px; ">
    				<option value="student">student</option>
    				<option value="teacher">teacher</option>
    				<option value="admin">admin</option>
				</select> 
			</td>
		</tr>
	</table>
	</form>
</div>

<form id="excelForm" method="post">
</form>