<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

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
	<script type="text/javascript">
		$(function(){
			maxLength('name', 20);
			form('ff');
		});
	</script>
	<div style="width:300px; margin: 0 auto;">
		<form id="ff" action="addSite" method="post">
			<p>
				名称<span>(必填)</span>：  <input type="text" name="name" id="name"
					class="easyui-validatebox input_text" required />
			</p>
			<input type="submit" class="input_button" value="提交"></input>
		</form>
	</div>
</body>
</html>
