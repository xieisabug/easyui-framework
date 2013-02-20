<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.entity.Major"%>
<%@ page import="com.zhnjy.entity.Course"%>
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
<link rel="stylesheet" href="/zk/css/setMajorCourse.css" type="text/css"></link>
<script type="text/javascript" src="/zk/js/major_setcourse.js"></script>
</head>

<body style="background:#efefef;">
	<%
		request.setAttribute("decorator", "none");
	    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	    response.setHeader("Pragma","no-cache"); //HTTP 1.0
	    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
		List courses = (List) request.getAttribute("courseList");
		Major major = (Major) request.getAttribute("major");
		int unitNum = (Integer) request.getAttribute("unitNum");
	%>
	<div style="width:500px;  margin: 0 auto;">
		<div id="majorName">
			<h3><%=major.getName()%></h3>
		</div>
		<div id="level">
			<h4>
				<%
					if (major.getLevel().equals("zk"))
						out.println("专科");
					else
						out.println("本科");
				%>
			</h4>
		</div>
		<div id="courseContent">
			<form action="/zk/setMC">
				<input type="hidden" name="majorId" value="<%=major.getId()%>">
				<input type="hidden" name="courseSize" value="<%=courses.size() %>" >
				<table style="width: 100%;">
					<tr>
						<td colspan="4">已有课程</td>
					</tr>
					<%
						for (int i = 0; i < courses.size(); i++) {
							Course course = (Course) courses.get(i);
					%>
					<tr>
						<td style="width: 40%;"><%=course.getName()%><input
							type="hidden" name="id<%=i%>" value="<%=course.getId()%>" /></td>
						<td style="width: 10%;">
							<a href="javascript:delete_mc(<%=course.getId()%>,<%=major.getId() %>)">删除</a>
						</td>
						<td style="width: 20%"><input type="checkbox"
							name="valid<%=i%>" value="true" <%if(course.getFlag()==1) out.println("checked"); %>>是否可考</td>
						<td style="width: 30%"><select name="unit<%=i%>">
								<%
									for (int j = 1; j <= unitNum; j++) {
								%>
								<option value="<%=j%>" <%if(course.getUnit()==j) out.println("selected"); %>>
									第<%=j%>单元
								</option>
								<%
									}
								%>
						</select></td>
					</tr>
					<%
						}
					%>
				</table>
				<input type="submit" id="submit" value="设置" class="input_button">
			</form>
		</div>
		<div id="courseInput">
			<p>输入所需添加的课程代码（以空格分隔）：</p>
			<form action="/zk/addMC">
				<input type="hidden" name="majorId" value="<%=major.getId()%>">
				<input type="text" name="courseNos" id="courseNos" class="input_text"> <input
					type="submit" name="submit" value="添加" class="input_button">
			</form>
		</div>
	</div>
</body>
</html>
