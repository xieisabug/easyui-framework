<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'show_stu_photo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body style="background:#efefef;">
	<%
		String idCard = (String)request.getAttribute("idCard"); 
	%>
	<div style="text-align: center; margin: 0 auto;">
		<p>学生照片：<img id="photoFile" src="/zk/photos/p<%=idCard%>.jpg"/></p>
		<p>学生身份证：<img id="idCardFile" src="/zk/photos/id<%=idCard%>.jpg"/></p>
	</div>
</body>
</html>
