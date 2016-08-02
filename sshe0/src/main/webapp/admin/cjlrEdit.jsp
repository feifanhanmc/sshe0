<%@ page language="java" pageEncoding="UTF-8"%>
<form id="admin_cjlrEdit_editForm" method="post">
	<table>
		<tr>
			<th>编号</th>
			<td><input name="id" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<th>账号</th>
			<td><input name="account" class="easyui-validatebox" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<th>姓名</th>
			<td><input name="name" class="easyui-validatebox" />
			</td>
		</tr>
		<tr>
			<th>密码</th>
			<td><input name="pwd" type="password" value="" class="easyui-validatebox" />
			</td>
		</tr>
		<tr>
			<th>角色</th>
			<td>
				<select class="easyui-combobox" name="role"  editable="false" style="width:143px;">
    				<option value="student">student</option>
    				<option value="teacher">teacher</option>
    				<option value="admin">admin</option>
				</select> 
			</td>
		</tr>
	</table>
</form>
