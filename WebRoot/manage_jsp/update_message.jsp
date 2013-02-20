<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zhnjy.util.db.MessageDB" %>
<%@ page import="com.zhnjy.entity.Message" %>
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
	<%
		String id = request.getParameter("id");
		Message message = MessageDB.getMessage(Integer.parseInt(id));

		User user = (User)session.getAttribute("user");
	%>
	<input type="hidden" name="operaterId" value="<%=user.getId() %>">
	<form action="updateMessage" method="post">
		<input type="text" id="title" name="title" class="input_title" value="<%=message.getTitle() %>">

		<textarea name="content" id="content">
		<%=message.getContent() %>
		</textarea>

		<input type="submit" name="submit" class="input_button" value="修改">
	</form>
</body>
</html>
