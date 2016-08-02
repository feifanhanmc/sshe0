<%@ page language="java" pageEncoding="UTF-8"%>
<form id="admin_kcglEdit_editForm" method="post">
	<table>
		<tr>
			<th>课程编号</th>
			<td><input name="cid" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<th>课程名称</th>
			<td><input name="cname" class="easyui-validatebox" />
			</td>
		</tr>
		<tr>
			<th>课程教师</th>
			<td><input name="tname" class="easyui-validatebox" />
			</td>
		</tr>
		<tr>
			<th>平均分</th>
			<td><input name="avg"  class="easyui-validatebox" readonly="readonly"/>
			</td>
		</tr>
	</table>
</form>
