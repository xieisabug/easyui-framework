<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zhnjy.util.db.SchoolDB" %>
<%@ page import="com.zhnjy.entity.School" %>
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
</head>

<body style="background:#efefef;">
	<% 
		String _id = request.getParameter("id");
		int id = Integer.parseInt(_id);
		School school = SchoolDB.getSchool(id);
		String level = school.getLevel();
	%>
	<script type="text/javascript">
		$(function(){
			maxLength('name', 20);
			form('ff');
		});
	</script>
	<div style="width:300px; margin: 0 auto;">
		<form id="ff" action="updateSchool" method="post">
			<input type="hidden" name="id" value="<%=id%>">
			<p>
				名称： <input type="text" name="name" class="input_text" id="name"
						value="<%=school.getName() %>" required/>
			</p>
			<p>
				等级：<select name="level" class="select">
					<option value="bk" <%if(level.equals("bk")) out.print("selected"); %>>本科</option>
					<option value="zk" <%if(level.equals("zk")) out.print("selected"); %>>专科</option>
				</select>
			</p>
			<input type="submit" class="input_button" value="提交"></input>
		</form>
	</div>
</body>
</html>
