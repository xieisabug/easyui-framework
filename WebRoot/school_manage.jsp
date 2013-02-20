<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
</head>

<body>
	<div id="tools" style="background: #fafafa; height: 40px;">
		<a href="#" class="easyui-linkbutton menu_button" iconCls="icon-add" 
			plain="true" onclick="add_school();">添加学校</a>
		<a href="#" class="easyui-linkbutton menu_button" iconCls="icon-reload"
			plain="true" onclick="refresh();">刷新数据</a> 
	</div>
	<div class="easyui-accordion" id="accordion"
		style="background: #efefef;">

		<div title="查询选项" id="menu">
			<div class="item">
				名称： <input type="text" id="name" class="input_text" />
			</div>
			<div class="item">
				等级： <select name="level" id="level" class="select">
					<option value="" selected>全部</option>
					<option value="zk">专科</option>
					<option value="bk">本科</option>
				</select>
			</div>
			<input type="button" onclick="refresh();" class="input_button"
				value="查询" />
		</div>

	</div>
	<div id="iframeContent">
		<iframe id="dataFrame" src="/zk/getDatasSchool?name=&level=&page=1&d=<%=new Date().getTime() %>" frameborder="0" 
			height="100%" width="100%"></iframe>
	</div>
</body>
</html>
