<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhnjy.entity.User" %>
<!DOCTYPE HTML>
<html>
<head>
<title>后台管理主页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="/zk/easyui/themes/icon.css" type="text/css" />
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css" type="text/css" />

<link rel="stylesheet" href="/zk/css/form.css" type="text/css" />
<link rel="stylesheet" href="/zk/css/index.css" type="text/css" />
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function(){
		login_user.level = ${user.level};
		login_user.name = '${user.userName}';
		login_user.site = ${user.siteId};
	})
</script>
<script type="text/javascript" src="/zk/js/index.js"></script>
<script type="text/javascript" src="/zk/js/app/datagrid.js"></script>

<link rel="stylesheet" type="text/css" href="/zk/jBox/Skins2/Gray/jbox.css" />
<script type="text/javascript" src="/zk/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/zk/jBox/i18n/jquery.jBox-zh-CN.js"></script>

<script type="text/javascript" src="js/level_school_major_course.js"></script>
</head>

<body>
	<div class="easyui-layout" id="main_window" fit="true">

		<div id="menus" data-options="region:'west'">
			<div class="easyui-accordion" id="accordion" fit="true" style="background: #fafafa;">
				<c:if test="${user.level != 5}">
					<div title="管理系统" id="menu1">
						<div class="center_helper">
						<a href="#" class="easyui-linkbutton menu_button"
							onclick="tabs.open('学生管理','stu_manage.jsp','stu_manage.js');">学生管理</a>
						<c:if test="${user.level == 1 || user.level == 3}">
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="tabs.open('用户管理','user_manage.jsp','user_manage.js');">用户管理</a>
						</c:if>	
						<c:if test="${user.level == 1}">
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="tabs.open('课程管理','course_manage.jsp','course_manage.js');">课程管理</a>
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="tabs.open('专业管理','major_manage.jsp','major_manage.js');;">专业管理</a>
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="tabs.open('学校管理','school_manage.jsp','school_manage.js');">学校管理</a>
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="tabs.open('站点管理','site_manage.jsp','site_manage.js');">站点管理</a>
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="tabs.open('公告管理','message_manage.jsp','message_manage.js');">公告管理</a>
						</c:if>
						</div>
					</div>
				</c:if>
				
				<c:if test="${user.level != 5}">
					<div title="考务系统" id="menu2">
						<div class="center_helper">
						<c:if test="${user.level == 1}">
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="jbox.open('注册核对','iframe','register_check.jsp');">全局设置</a>
						</c:if>
						<a href="#" class="easyui-linkbutton menu_button"
							onclick="tabs.open('学生报考','getDateSC','stu_chooseclass.js');">学生报考</a>
						<a href="#" class="easyui-linkbutton menu_button"
							onclick="tabs.open('报考管理','chooseclass_manage.jsp','chooseclass_manage.js');">报考管理</a>
						<c:if test="${user.level == 1}">
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="jbox.open('报考核对','iframe','exam_check.jsp');">报考核对</a>
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="tabs.open('未报统计','notchoose_manage.jsp','notchoose_manage.js');">未报统计</a>
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="jbox.open('注册核对','iframe','register_check.jsp');">注册核对</a>
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="tabs.open('成绩导入','grade_input.jsp');">成绩导入</a>
							<a href="#" class="easyui-linkbutton menu_button"
								onclick="tabs.open('查看成绩','grade_manage.jsp','grade_manage.js');">查看成绩</a>
						</c:if>
						<a href="#" class="easyui-linkbutton menu_button"
							onclick="tabs.open('单科统计','jumpSingleCount','single_count.js');">单科统计</a>
					</div>
					</div>
				</c:if>
					
				<c:if test="${user.level == 5 || user.level == 1}">
					<div title="财务系统" id="menu3">
					<div class="center_helper">
						<a href="#" class="easyui-linkbutton menu_button"
							onclick="tabs.open('费用项目','record_item.jsp','record_item.js');">费用项目</a>
						<a href="#" class="easyui-linkbutton menu_button"
							onclick="tabs.open('费用缴纳','finance_manage.jsp','finance_manage.js');">费用缴纳</a>
						<a href="#" class="easyui-linkbutton menu_button"
							onclick="tabs.open('统计查询','finance_count.jsp','finance_count.js');">统计查询</a>
						<a href="#" class="easyui-linkbutton menu_button"
							onclick="tabs.open('缺交查询','finance_lack.jsp','finance_lack.js');">缺交查询</a>
					</div>
					</div>
				</c:if>
			</div>
		</div>
		
		<div id="content" class="easyui-tabs" data-options="region:'center'">
			<div title="欢迎" id="welcome" data-options="closable:true">
				<div id="bg_img">
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>