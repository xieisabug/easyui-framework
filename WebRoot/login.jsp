<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登陆</title>
<link rel="stylesheet" href="/zk/css/form.css" type="text/css"></link>
<link rel="stylesheet" href="css/login.css" type="text/css"></link>
</head>

<body>
	<div id="backimg">
		<div id="login_title">
			<p>自学考试信息化管理平台 初级版</p>
		</div>
		<div id="login">
			<div id="form">
				<div id="input">
					<form action="login" method="post">
						<p>
							用户名：<input type="text" name="userName" class="login_input">
						</p>
						<p>
							密码：<input type="password" name="password" class="login_input">
						</p>
						<input type="submit" class="input_button" value="登陆">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
