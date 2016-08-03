<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$('#admin_kcgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/courseAction!datagrid.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'cid',
			pageSize : 10 ,
			pageList : [10, 20, 30, 40, 50],
			rownumbers : true,
			sortName : 'cname',
			sortOrder : 'asc',
			pagePosition : 'bottom',
			checkOnSelect : false ,
			selectOnCheck : false ,
			frozenColumns : [ [ {
				field : 'cid',
				title : '编号',
				width : 150,
				checkbox : true
				//hidden : true	
			},{
				field : 'cname',
				title : '课程名称',
				width : 100,
				sortable : true				
			}]],
			columns : [ [ {
				field : 'tname',
				title : '课程教师',
				width : 80,
				sortable : true
			},{
				field : 'avg',
				title : '平均分',
				width : 50,
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
		postForm.action = "${pageContext.request.contextPath}/courseAction!exportExcel.action";
		postForm.submit();
	}		

	function editFun(){
		var rows = $('#admin_kcgl_datagrid').datagrid('getChecked');
		if(rows.length == 1){
			var d = $('<div/>').dialog({
				width : 220,
				height : 200,
				href : '${pageContext.request.contextPath}/admin/kcglEdit.jsp',
				modal : true,
				title : '编辑用户',
				buttons : [{
					text:'确定',
					iconCls : 'icon-ok',
					handler: function(){
						$('#admin_kcglEdit_editForm').form('submit',{
							url : '${pageContext.request.contextPath}/courseAction!edit.action',
							success : function(r) {
								var o = jQuery.parseJSON(r);
								if (o.success) {
									d.dialog('close');
									//$('#admin_kcgl_datagrid').datagrid('reload');
									$('#admin_kcgl_datagrid').datagrid('updateRow',{
										index : $('#admin_kcgl_datagrid').datagrid('getRowIndex',rows[0].cid),
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
					//$('#admin_kcglEdit_editForm input[name=id]').val(rows[0].id);
					//$('#admin_kcglEdit_editForm input[name=name]').val(rows[0].name);
					$('#admin_kcglEdit_editForm').form('load', rows[0]);
					$('#admin_kcglEdit_editForm input[name=pwd]').val(''); 
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
		$('#admin_kcgl_datagrid').datagrid('load',serializeObject($('#admin_kcgl_searchForm')));
	}
	
	function clearFun(){
		$('#admin_kcgl_layout input[name=name]').val('');
		$('#admin_kcgl_datagrid').datagrid('load',{});
	}
	
	function append(){
		$('#admin_kcgl_addForm input').val('');
		//动态加载<ComboBox>所需要的数据
		$.ajax({
				url : '${pageContext.request.contextPath}/userAction!getTname.action',
				success : function(r) {
					var data = jQuery.parseJSON(r);
					$('#admin_kcgl_addForm_tname').combobox('loadData', data);
				}
		});
		
		$('#admin_kcgl_addDialog').dialog('open');
		
	}
	
	function remove(){
		var rows = $('#admin_kcgl_datagrid').datagrid('getChecked');
		var cids = [];
		if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						cids.push(rows[i].cid);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/courseAction!remove.action',
						data : {
							cids : cids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							//$('#admin_kcgl_datagrid').datagrid('reload');
							$('#admin_kcgl_datagrid').datagrid('load');						
							$('#admin_kcgl_datagrid').datagrid('unselectAll');
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

</script>

<div id = "admin_kcgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询',border:false" style="height:60px;">
		<form id="admin_kcgl_searchForm">
			查询条件:<input name="name"/>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="clearFun();">清空</a>	
		</form>
	</div>
	<div data-options="region:'center'">
		<table id="admin_kcgl_datagrid"></table>
	</div>
</div>
<div id="admin_kcgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '清空',
				iconCls : 'icon-undo',
				handler : function(){
					$('#admin_kcgl_addForm input').val('');
				}
				},{
				text : '确定',
				iconCls : 'icon-ok',
				handler : function(){
					$('#admin_kcgl_addForm').form('submit',{
							url:'${pageContext.request.contextPath}/courseAction!add.action',
							success:function(r){
									var o = jQuery.parseJSON(r);
									if(o.success){
										/*$('#admin_kcgl_datagrid').datagrid('load');*/
										/*$('#admin_kcgl_datagrid').datagrid('appendRow',o.obj);*/
										$('#admin_kcgl_datagrid').datagrid('insertRow',{
											index:0,
											row:o.obj
										});
										$('#admin_kcgl_addDialog').dialog('close');
									}
									$.messager.show({
										title : '提示',
										msg : o.msg
									});
							}
					});
				}
			}]" style="width:220px;height:190px;" align="center">
	<form id="admin_kcgl_addForm" method="post">
	<table>
		<tr>
			<th>课程名称</th>
			<td><input name="cname" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>课程教师</th>
			<td>
				<select class="easyui-combobox" name="tname" id="admin_kcgl_addForm_tname" 
						data-options="valueField:'kcgl_id', textField:'kegl_text', panelHeight:'auto',editable:false">
				</select>
			</td>
		</tr>
	</table>
	</form>
</div>

<form id="excelForm" method="post">
</form>