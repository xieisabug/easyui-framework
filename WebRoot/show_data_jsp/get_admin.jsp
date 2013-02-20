<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.zhnjy.entity.User"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css"
	type="text/css"></link>
<link rel="stylesheet" href="/zk/jBox/Skins2/Gray/jbox.css"
	type="text/css"></link>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/zk/js/admin_manage.js"></script>
<link rel="stylesheet" href="/zk/css/get.css" type="text/css"></link>
</head>

<body>
	<table class="easyui-datagrid" singleSelect="true">
		<thead>
			<tr>
				<th data-options="field:'id'">用户编号</th>
				<th data-options="field:'userName'">用户名</th>
				<th data-options="field:'password'">密码</th>
				<th data-options="field:'level'">权限</th>
				<th data-options="field:'siteId'">站点</th>
				<th data-options="field:'remark'">备注</th>
				<th data-options="field:'method'">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${admins}" var="r_user">
				<c:if
					test="${sessionScope.user.level==1 || (r_user.level >= sessionScope.user.level && r_user.siteId == sessionScope.user.siteId)}">
					<tr>
						<td>${r_user.id}</td>
						<td>${r_user.userName}</td>
						<td>${r_user.password}</td>
						<td>${r_user.levelName}</td>
						<td>${r_user.siteName}</td>
						<td>${r_user.remark}</td>
						<td><a href="javascript:delete_admin(${r_user.id});">删除</a> <a
							href="javascript:update_admin(${r_user.id});">修改</a>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<div id="pageSelect">
		<p>
			共${pageCount}页 
			<%
				int pageCount = (Integer)request.getAttribute("pageCount");
				String queryString = request.getQueryString();
				int currentPage = Integer.parseInt(queryString.substring(queryString.indexOf("&d=")-1,
									queryString.indexOf("&d=")));
				queryString = queryString.substring(0, queryString.indexOf("page"));
			%>
			<a href="/zk/getDatasAdmin?<%=queryString%>page=1&d=<%=new Date().getTime()%>">首页</a>
			<a href="/zk/getDatasAdmin?<%=queryString%>page=
				<%if(currentPage==1) out.print(currentPage); else out.print(currentPage-1);%>
				&d=<%=new Date().getTime()%>">上一页</a>
			<a href="/zk/getDatasAdmin?<%=queryString%>page=
				<%if(currentPage==pageCount) out.print(currentPage); else out.print(currentPage+1);%>
				&d=<%=new Date().getTime()%>">下一页</a>
			<a href="/zk/getDatasAdmin?<%=queryString%>page=<%=pageCount%>&d=<%=new Date().getTime()%>">尾页</a>
		</p>
	</div>
</body>
</html>
