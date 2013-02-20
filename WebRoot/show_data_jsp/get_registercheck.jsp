<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.util.db.StudentDB"%>
<%@ page import="com.zhnjy.entity.Student"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css"
	type="text/css"></link>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
</head>

<body bgcolor="#fafafa">
	<%
		Map resultMap = (Map) request.getAttribute("resultMap");
		Iterator it = resultMap.keySet().iterator();
		String miss = "没有注册";
		String erro = "专业错误";
	%>
	<table class="easyui-datagrid" singleSelect="true">
		<thead>
			<tr>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'idCard'">身份证</th>
				<th data-options="field:'phone1'">联系电话</th>
				<th data-options="field:'level'">等级</th>
				<th data-options="field:'examNumber'">考籍号</th>
				<th data-options="field:'reason'">错误原因</th>
			</tr>
		</thead>
		<tbody>
			<%
				while (it.hasNext()) {
					String idCard = (String) it.next();
					String type = (String) resultMap.get(idCard);
					Student student = StudentDB.getStudent(idCard);
					String level = student.getStudyLevel();
			%>
			<tr>
				<td><%=student.getName()%></td>
				<td><%=idCard%></td>
				<td><%=student.getPhone1()%></td>
				<td>
				<%
					if(level.equals("zk"))
						out.println("专科");
					else if(level.equals("bk"))
						out.println("本科");	
				%>
				</td>
				<td>
					<%
						if (level.equals("zk"))
							out.println(student.getzExamNumber());
						else
							out.println(student.getbExamNumber());
					%>
				</td>
				<td>
					<%
						if(type.equals("0"))
							out.println(miss);
						else
							out.println(erro);
					%>
				</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>
