<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  
<title></title>

</head>

<body>
	<div id="tools" style="background: #efefef; height: 40px;" >
		<a href="#" class="easyui-linkbutton menu_button" iconCls="icon-add"
			plain="true" onclick="add_message();">添加通知</a> 
		<a href="#" class="easyui-linkbutton menu_button" iconCls="icon-reload"
			plain="true" onclick="refresh();">刷新数据</a> 
	</div>
	<div id="iframeContent">
		<iframe id="dataFrame" src="/zk/getDatasMessage?page=1&d=<%=new Date().getTime() %>" frameborder="0" 
			height="100%" width="100%"></iframe>
	</div>
	
</body>
</html>
