<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>
<script type="text/javascript" src="/zk/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css"
	type="text/css"></link>
</head>

<body>
	<div id="gi_wait" style="width:400px; text-align: center;">
	</div>
	<div class="main_content">
		<div id="grade_input_content">
			<div id="file">
				<form id="gi_update_grade" action="addGrade" method="post"
					enctype="multipart/form-data">
					<h2>
						请找到目标文件：<input type="file" name="file">
					</h2>
					<h2>
						请输入成绩的考期时间：<input type="text" name="examDate" class="input_text">
					</h2>
					<input type="submit" value="上传" class="input_button">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
