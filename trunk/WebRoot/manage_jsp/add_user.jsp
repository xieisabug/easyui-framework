<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhnjy.util.db.SiteDB"%>
<%@ page import="com.zhnjy.entity.Site"%>
<%@ page import="com.zhnjy.entity.User"%>
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
			between('userName',5,20,'用户名需在5-20位之间');
			between('password',5,20,'密码需在5-20位之间');
			reapet('password','password2','两次输入不同');
			form('ff');
		});
	</script>
	<div style="width:300px; margin: 0 auto;">
		<%
			User u = (User)session.getAttribute("user"); 
		%>
		<form id="ff" action="addAdmin" method="post">
			<p>
				用 户 名<span>(必填)</span>： 
				<input id="userName" name="userName"
					class="easyui-validatebox input_text" required/>
			</p>
			<p>
				密码<span>(必填)</span>： 
				<input type="password" name="password" id="password"
					class="easyui-validatebox input_text" required />
			</p>
			<p>
				密码确认<span>(必填)</span>： 
				<input type="password" name="password2" id="password2"
					class="easyui-validatebox input_text" required/>
			</p>
			<p>
				权限等级<span>(必填)</span>： 
				<select id="level" name="level" class="select">
					<c:if test="${user.level == 1}">
						<option value="2">高级操作员</option>
						<option value="3">站点管理员</option>
						<option value="4">站点操作员</option>
						<option value="5">财务管理员</option>
					</c:if>
					<c:if test="${user.level == 3}">
						<option value="3">站点管理员</option>
						<option value="4">站点操作员</option>
					</c:if>
				</select>
			</p>
			<p>
				站点<span>(必填)</span>： 
				<select id="siteId" name="siteId" class="select">
					<%
						if(u.getLevel() == 1) {
							List sites = SiteDB.query();
							for (int i = 0; i < sites.size(); i++) {
								Site site = (Site) sites.get(i);
					%>
					<option value="<%=site.getId()%>"><%=site.getName()%></option>
					<%
							}
						}
					%>
					<%
						if(u.getLevel() == 3) {
					%>
						<option value="<%=u.getSiteId() %>"><%=SiteDB.getSiteNameById(u.getSiteId()) %></option>
					<%
						}
					%>
				</select>
			</p>
			<p>
				备注: <input type="text" name="remark" class="input_text" />
			</p>
			<input type="submit" id="submit" class="input_button" value="提交">
		</form>
	</div>
</body>
</html>
