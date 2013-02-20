<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.zhnjy.entity.Student"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css"
	type="text/css"></link>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
</head>

<body>
	<%
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		List list = (List)request.getAttribute("list");
	%>
	<table class="easyui-datagrid" singleSelect="true">
		<thead>
			<tr>
				<th data-options="field:'id'">姓名</th>
				<th data-options="field:'name'">身份证号</th> 
				<th data-options="field:'method'">电话</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(int i = 0; i < list.size(); i++) {
					Student stu = (Student)list.get(i);
			%>
			<tr>
				<td><%=stu.getName() %></td>
				<td><%=stu.getIdCard() %></td>
				<td><%=stu.getPhone1() %></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>
