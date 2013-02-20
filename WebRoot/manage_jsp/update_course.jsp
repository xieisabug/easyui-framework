<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zhnjy.util.db.CourseDB"%>
<%@ page import="com.zhnjy.entity.Course"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title></title>

<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css"
	type="text/css"></link>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="/zk/css/form.css" type="text/css"></link>
<script type="text/javascript" src="/zk/js/validate.js"></script>
<script type="text/javascript" src="/zk/js/level_school_major_course.js"></script>
</head>

<body style="background:#efefef;">
	<%
		String id = request.getParameter("id");
		Course course = CourseDB.getCourse(Integer.parseInt(id));
		String level = course.getLevel();
	%>
	<script type="text/javascript">
		$(function(){
			maxLength('number', 10);
			maxLength('name', 20);
			form('ff');
		});
	</script>
	<div style="width:300px; margin: 0 auto;">
		<form id="ff" action="updateCourse" method="post">
			<input type="hidden" name="id" value="<%=id%>">
			<p>
				课程代码：<input type="text" name="number" class="input_text" id="number"
					value="<%=course.getNumber()%>" required/>
			</p>
			<p>
				名称： <input type="text" name="name" class="input_text" id="name"
					value="<%=course.getName()%>" required/>
			</p>
			<p>
				等级：<select id="level" name="level" class="select" onchange="level_school();">
					<option value="bk" <%if (level.equals("bk")) out.println("selected");%>>本科</option>
					<option value="zk" <%if (level.equals("zk")) out.println("selected");%>>专科</option>
				</select>
			</p>
			<p>
				备注：<input type="text" name="remark" class="input_text" value="<%=course.getRemark() %>">
			</p>
			<input type="submit" class="input_button" value="提交"></input>
		</form>
	</div>
</body>
</html>
