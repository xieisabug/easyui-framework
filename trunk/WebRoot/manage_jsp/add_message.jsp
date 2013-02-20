<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zhnjy.entity.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>添加通知</title>
<script type="text/javascript" src="/zk/ueditor/editor_config.js"></script>
<script type="text/javascript" src="/zk/ueditor/editor.min.js"></script>
<link rel="stylesheet" href="/zk/ueditor/themes/default/ueditor.css" />
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<link rel="stylesheet" href="/zk/css/form.css" type="text/css"></link>
<script type="text/javascript">
$(function(){
	var editor = new baidu.editor.ui.Editor();
	editor.render("content");
});
</script>
</head>

<body style="background:#efefef;">
	<form action="addMessage" method="post">
		<% 
			User user = (User)session.getAttribute("user");
		%>
		<input type="hidden" name="operaterId" value="<%=user.getId() %>">
		<input type="text" id="title" name="title" class="input_title" value="请输入标题">

		<textarea name="content" id="content">
		</textarea>

		<input type="submit" name="submit" class="input_button" value="提交">
	</form>
</body>
</html>
