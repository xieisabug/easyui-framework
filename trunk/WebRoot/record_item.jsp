<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>
</head>

<body>
	<script type="text/javascript">
		setTimeout(refresh(), 20);
	</script>
	<div id="tools" style="background: #fafafa; height: 40px;">
		<a href="#" class="easyui-linkbutton menu_button" iconCls="icon-add" 
			plain="true" onclick="add_recorditem();">添加项目</a>
		<a href="#" class="easyui-linkbutton menu_button" iconCls="icon-reload"
			plain="true" onclick="refresh();">刷新数据</a> 
	</div>
	<div class="easyui-accordion" id="accordion"
		style="background: #fafafa;">

		<div title="查询选项" id="menu">
			<div class="item">
				项目名称： <input type="text" id="name" name="name" class="input_text"
					value="" />
			</div>
			<input type="button" class="input_button" value="查询"
				onclick="refresh();" style="float:left;" />
		</div>
	</div>
	<table id="recorditem_data"></table>
</body>
</html>