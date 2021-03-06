<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<body>
<script type="text/javascript">
	$(function() {
		$('#user_login_loginForm').form({
			url : '${pageContext.request.contextPath}/userAction!login.action',
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#user_login_loginDialog').dialog('close');
				}
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		});
		$('#user_login_loginForm input').bind('keyup', function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				$('#user_login_loginForm').submit();
			}
		});

		window.setTimeout(function() {
			$('#user_login_loginForm input[name=name]').focus();
		}, 0);
	});
	
</script>
    <div id="user_login_loginDialog" class="easyui-dialog" data-options="title:'登录',modal:true,closable:false,buttons:[{
				text:'登录',
				iconCls:'icon-ok',
				handler:function(){
					$('#user_login_loginForm').submit();
				}
			}]">
	<form id="user_login_loginForm" method="post">
		<table>
			<tr>
				<th>登录名</th>
				<td><input name="account" class="easyui-validatebox" data-options="required:true,missingMessage:'登陆名称必填'"  />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input type="password" name="pwd" class="easyui-validatebox" data-options="required:true,missingMessage:'密码必填'" />
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>

