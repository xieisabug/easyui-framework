<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title></title>
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css" type="text/css"></link>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
#course_data {
	width: 100%;
}
</style>
</head>


<body>
	<script type="text/javascript">
		$(function(){
			setTimeout(refresh(), 200);
		});
	</script>
	<div id="tools" style="background: #fafafa; height: 40px;">
		<a href="#" class="easyui-linkbutton menu_button" iconCls="icon-add" 
			plain="true" onclick="add_course();">添加课程</a>
		<a href="#" class="easyui-linkbutton menu_button" iconCls="icon-reload"
			plain="true" onclick="refresh();">刷新数据</a> 
	</div>
	<div class="easyui-accordion" id="accordion"
		style="background: #fafafa;">

		<div title="查询选项" id="menu">
			<div class="item">
				名称： <input type="text" id="name" class="input_text" />
			</div>
			<div class="item">
				编号： <input type="text" id="number" class="input_text" />
			</div>
			<div class="item">
				等级： <select name="level" id="level" class="select">
					<option value="" selected>全部</option>
					<option value="zk">专科</option>
					<option value="bk">本科</option>
				</select>
			</div>
			<input type="button" class="input_button" onclick="refresh();"
				value="查询" />
		</div>
	</div>
	<table id="course_data"></table>
</body>
</html>
