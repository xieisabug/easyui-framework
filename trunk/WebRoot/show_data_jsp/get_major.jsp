<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhnjy.entity.Major" %>
<%@ page import="com.zhnjy.util.db.MajorDB" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'get_admin.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="/zk/jBox/Skins2/Gray/jbox.css" type="text/css"></link>
<link rel="stylesheet" href="/zk/css/get.css" type="text/css"></link>
</head>

<body>
	<table class="easyui-datagrid" singleSelect="true">
		<thead>
			<tr>
				<th data-options="field:'id'">id</th>
				<th data-options="field:'name'">名称</th>
				<th data-options="field:'number'">代码</th>
				<th data-options="field:'level'">等级</th>
				<th data-options="field:'method'">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${majors}" var="major">
				<tr>
					<td>${major.id}</td>
					<td>${major.name}</td>
					<td>${major.number}</td>
					<td>${major.levelName}</td>
					<td>
						<a href="javascript:delete_major(${major.id});">删除</a> 
						<a href="javascript:update_major(${major.id});">修改</a> 
						<a href="javascript:set_course(${major.id});">设置课程</a>
					</td>
				</tr>
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
			<a href="/zk/getDatasMajor?<%=queryString%>page=1&d=<%=new Date().getTime()%>">首页</a>
			<a href="/zk/getDatasMajor?<%=queryString%>page=
				<%if(currentPage==1) out.print(currentPage); else out.print(currentPage-1);%>
				&d=<%=new Date().getTime()%>">上一页</a>
			<a href="/zk/getDatasMajor?<%=queryString%>page=
				<%if(currentPage==pageCount) out.print(currentPage); else out.print(currentPage+1);%>
				&d=<%=new Date().getTime()%>">下一页</a>
			<a href="/zk/getDatasMajor?<%=queryString%>page=<%=pageCount%>&d=<%=new Date().getTime()%>">尾页</a>
		</p>
	</div>
</body>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/zk/js/major_manage.js"></script>
</html>
