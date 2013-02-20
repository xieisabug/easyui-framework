<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.entity.School" %>
<%@ page import="com.zhnjy.entity.Major" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/zk/jBox/Skins2/Gray/jbox.css" />
<script type="text/javascript" src="/zk/jBox/jquery.jBox-2.3.min.js"></script>
<link rel="stylesheet" href="/zk/css/form.css" type="text/css"></link>
<link rel="stylesheet" href="/zk/css/setSchoolMajor.css" type="text/css"></link>
<script type="text/javascript" src="/zk/js/school_setmajor.js"></script>
</head>

<body style="background:#efefef;">
	<%
		School school = (School)request.getAttribute("school");
		String level = school.getLevel();
		List majors = (List)request.getAttribute("majorList");
	%>
	<div style="width:500px;  margin: 0 auto;">
		<div id="schoolName">
			<h3><%=school.getName() %></h3>
		</div>
		<div id="level">
			<h4>
			<%
				if(level.equals("zk"))
					out.println("专科");
				else
					out.println("本科"); 
			%>
			</h4>
		</div>
		<div id="majorContent">
			<table style="width: 100%;">
				<tr>
					<td colspan="2">已有专业</td>
				</tr>
				<%
					for(int i = 0; i < majors.size(); i++) {
						Major major = (Major)majors.get(i);
				%>
				<tr>
					<td style="width: 70%;"><%=major.getName() %></td>
					<td style="width: 30%;">
						<a href="javascript:delete_sm(<%=school.getId()%>,<%=major .getId() %>)">删除</a>
					</td>
				</tr>
				<%
					} 
				%>
			</table>
		</div>
		<div id="majorInput">
			<p>输入所需添加的专业代码（以空格分隔）：</p>
			<form action="/zk/addSM">
				<input type="hidden" name="schoolId" value="<%=school.getId()%>">
				<input type="text" name="majorNos" id="majorNos" class="input_text"> 
				<input type="submit" name="submit" value="添加" class="input_button">
			</form>
		</div>
	</div>
</body>
</html>
