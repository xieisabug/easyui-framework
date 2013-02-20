<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.util.file.JspFileUpload"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'updating.jsp' starting page</title>
<script type="text/javascript" src="../easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../js/grade_input.js"></script>
</head>

<body>
	<%
		//初始化
		JspFileUpload jfu = new JspFileUpload();
		//设定request对象
		jfu.setRequest(request);
		//设定上传的文件路径
		jfu.setUploadPath("D:\\");
		jfu.setFileName("1.xls");
		//上传处理
		int rtn = jfu.process();
		//取得form中其他input控件参数的值
		//String username = jfu.getParameter("username");
		//如果对应同一个参数有多个input控件，返回数组
		//String[] usernameArr = jfu.getParameters("username");
		//取得上传的文件的名字
		//String[] fileArr = jfu.getUpdFileNames();
		//取得上传文件的个数，这个方法有点鸡肋
		//int fileNumber = jfu.getUpdFileSize();
		//下面的是测试输出的代码。
		//out.println("parameter:" + username);
		//out.println("parameter size:" + usernameArr.length);
		//out.println("fileArr size:" + fileArr.length);
		//if (fileArr.length > 0)
		//out.println("fileArr 0:" + fileArr[0]);
		if (rtn == 0) {
	%>
	<h3>上传成功！</h3>
	<script type="text/javascript">
		$('#gi_wait', parent.document).html();

		window.alert("aaa");
		setTimeout("$('#gi_wait',parent.document).dialog('close');",
				2000);
	</script>
	<%
		} else {
	%>
	<h3>上传失败！</h3>
	<%
		}
	%>
</body>
</html>
