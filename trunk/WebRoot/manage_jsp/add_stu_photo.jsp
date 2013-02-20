<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'add_stu_photo.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="/zk/css/form.css" type="text/css"></link>
</head>

<body style="background:#efefef;">
	<div style="text-align: center; margin: 0 auto;">
		<form id="stu_photo" action="uploadPhoto" method="post"
			enctype="multipart/form-data">
			<%
				String idCard = (String)request.getAttribute("idCard"); 
			%>
			<input id="idCard" name="idCard" type="hidden" value="<%=idCard%>">
			<h2>
				上传学生相片：<input type="file" name="photoFile" id="photo">
			</h2>
			<h2>
				上传学生身份证：<input type="file" name="idCardFile" id="idCard">
			</h2>
			<input type="submit" value="上传" class="input_button">
		</form>
	</div>
</body>
</html>
