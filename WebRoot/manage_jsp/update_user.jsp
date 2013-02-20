<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhnjy.util.db.AdminDB" %>
<%@ page import="com.zhnjy.entity.User" %>
<%@ page import="com.zhnjy.util.db.SiteDB"%>
<%@ page import="com.zhnjy.entity.Site"%>
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
	<%
		String id = request.getParameter("id");
		int _id = Integer.parseInt(id);
		User user = AdminDB.getAdmin(_id);
	%>
	<script type="text/javascript">
		$(function(){
			between('userName',5,20,'用户名需在5-20位之间');
			between('password',5,20,'密码需在5-20位之间');
			form('ff');
		});
	</script>
	<div style="width:300px; margin: 0 auto;">
		<form id="ff" action="updateAdmin" method="post">
			<input type="hidden" name="id" value="<%=id%>">
			<p>
				用 户 名： <input type="text" name="userName" class="input_text" id="userName"
						value="<%=user.getUserName()%>" required/>
			</p>
			<p>
				密码： <input type="password" name="password" class="input_text" id="password"
						value="<%=user.getPassword()%>" required/>
			</p>
			<p>
				权限等级： <select id="level" name="level" class="select">
					<c:if test="${user.level == 1}">
						<option value="2" <%if(user.getLevel()==2) out.print("selected");%>>高级操作员</option>
						<option value="3" <%if(user.getLevel()==3) out.print("selected");%>>站点管理员</option>
						<option value="4" <%if(user.getLevel()==4) out.print("selected");%>>站点操作员</option>
						<option value="5" <%if(user.getLevel()==5) out.print("selected");%>>财务管理员</option>
					</c:if>
					<c:if test="${user.level == 3}">
						<option value="3" <%if(user.getLevel()==3) out.print("selected");%>>站点管理员</option>
						<option value="4" <%if(user.getLevel()==4) out.print("selected");%>>站点操作员</option>
					</c:if>
				</select>
			</p>
			<p>
				站点： <select id="siteId" name="siteId" class="select">
					<%
						if(user.getLevel() == 1) {
							List sites = SiteDB.query();
							for (int i = 0; i < sites.size(); i++) {
								Site site = (Site) sites.get(i);
					%>
					<option value="<%=site.getId()%>" <%if(site.getId()==user.getSiteId()) out.print("selected"); %>><%=site.getName()%></option>
					<%
							}
						}
					%>
					<%
						if(user.getLevel() == 3) {
					%>
						<option value="<%=user.getSiteId() %>" selected><%=SiteDB.getSiteNameById(user.getSiteId()) %></option>
					<%
						}
					%>
					
				</select>
			</p>
			<p>
				备注: <input type="text" name="remark" class="input_text" value="<%=user.getRemark()%>"/>
			</p>
			<input type="submit" class="input_button" value="提交"></input>
		</form>
	</div>
</body>
</html>
