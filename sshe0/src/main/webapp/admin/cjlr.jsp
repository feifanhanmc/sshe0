<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		var datagrid;
		var editRow = undefined;
		datagrid = $('#admin_cjlr_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/courseStuAction!datagrid.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'csid',
			pageSize : 10 ,
			pageList : [10, 20, 30, 40, 50],
			rownumbers : true,
			sortName : 'name',
			sortOrder : 'asc',
			pagePosition : 'bottom',
			checkOnSelect : false ,
			selectOnCheck : false ,
			frozenColumns : [ [ {
				field : 'csid',
				title : 'ID',
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
				sortable : true,
				editor: { type: 'validatebox', options: { required: false} }
			},{
				field : 'rank',
				title : '排名',
				width : 50,
				sortable : true
				
			},{
				field : 'cname',
				title : '课程名称',
				width : 50,
				//formatter : function(value, row, index){
					//return '<span title="' + row.name + ' : ' +value + '">' + value + '</span>';
					//return '******';
				//}								
			}]],
			toolbar : [ {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function(){
					//修改时要获取选择到的行
                    var rows = datagrid.datagrid("getChecked");
                    //如果只选择了一行则可以进行修改，否则不操作
                    if (rows.length == 1) {
                    	 //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
                       	 if (editRow != undefined) {
                       		 datagrid.datagrid("endEdit", editRow);
                         }
                         //当无编辑行时
                         if (editRow == undefined) {
                             //获取到当前选择行的下标
                             var index = datagrid.datagrid("getRowIndex", rows[0]);
                             //开启编辑
                             datagrid.datagrid("beginEdit", index);
                             //把当前开启编辑的行赋值给全局变量editRow
                             editRow = index;
                             //当开启了当前选择行的编辑状态之后，
                             //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                             datagrid.datagrid("unselectAll");
                         }
                     }
				}
			},'-',{ 
				text: '保存', iconCls: 'icon-save', handler: function () {
                     //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
                     datagrid.datagrid("endEdit", editRow);
                 }
            }, '-',{ 
            	text: '取消编辑', iconCls: 'icon-redo', handler: function () {
                     //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
                     editRow = undefined;
                     datagrid.datagrid("rejectChanges");
                     datagrid.datagrid("unselectAll");
                 }
             }, '-',{
				text : '保存为Excel',
				iconCls : 'icon-ok',
				handler : function(){
					exportExcel();
				}
			},'-'],
			onAfterEdit: function (rowIndex, rowData, changes) {
                    //endEdit该方法触发此事件
                    console.info(rowData);
                    editRow = undefined;
                    $.ajax({
						url : '${pageContext.request.contextPath}/courseStuAction!edit.action',
						data : {
							csid : rowData.csid,
							grade : rowData.grade
						},
						dataType : 'json',
						success : function(r) {
							//$('#admin_cjlr_datagrid').datagrid('reload');
							$('#admin_cjlr_datagrid').datagrid('load');						
							$('#admin_cjlr_datagrid').datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						}
					});
            },
           	onDblClickRow: function (rowIndex, rowData) {
                //双击开启编辑行
                    if (editRow != undefined) {
                        datagrid.datagrid("endEdit", editRow);
                    }
                    if (editRow == undefined) {
                        datagrid.datagrid("beginEdit", rowIndex);
                        editRow = rowIndex;
                    }
            }
		});
	});
	
	

	function exportExcel(){
		var postForm = document.getElementById("excelForm");
		postForm.action = "${pageContext.request.contextPath}/courseStuAction!exportExcel.action";
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