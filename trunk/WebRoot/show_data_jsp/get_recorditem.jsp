<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.entity.RecordItem" %>
<%@ page import="com.zhnjy.util.db.RecordItemDB" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css" type="text/css"></link>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
</head>

<body>
	<%
		String name = request.getParameter("name");
		List list = RecordItemDB.getList(name);
	%>
	<table class="easyui-datagrid" singleSelect="true">
		<thead>
			<tr>
				<th data-options="field:'id'">项目编号</th>
				<th data-options="field:'name'">名称</th>
				<th data-options="field:'method'">操作</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(int i = 0; i < list.size(); i++) {
					RecordItem ri = (RecordItem)list.get(i);
			%>
			<tr>
				<td><%=ri.getId() %></td>
				<td><%=ri.getName() %></td>
				<td><a href="javascript:delete_recorditem(<%=ri.getId()%>);">删除</a> 
					<a href="javascript:update_recorditem(<%=ri.getId()%>);">修改</a>
				</td>
			</tr>
			<% 
				}
			%>
		</tbody>
	</table>
</body>
</html>
