<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhnjy.entity.Message" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

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
				<th data-options="field:'title'">标题</th>
				<th data-options="field:'content'">内容</th>
				<th data-options="field:'operaterId'">发布人id</th>
				<th data-options="field:'time'">发布时间</th>
				<th data-options="field:'method'">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${messages}" var="message">
				<tr>
					<td>${message.id}</td>
					<td>${message.title}</td>
					<td>${message.content}</td>
					<td>${message.operaterName }</td>
					<td>${message.time}</td>
					<td><a href="javascript:delete_message(${message.id})">删除</a> <a href="javascript:update_message(${message.id});">修改</a></td>
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
			<a href="/zk/getDatasMessage?<%=queryString%>page=1&d=<%=new Date().getTime()%>">首页</a>
			<a href="/zk/getDatasMessage?<%=queryString%>page=
				<%if(currentPage==1) out.print(currentPage); else out.print(currentPage-1);%>
				&d=<%=new Date().getTime()%>">上一页</a>
			<a href="/zk/getDatasMessage?<%=queryString%>page=
				<%if(currentPage==pageCount) out.print(currentPage); else out.print(currentPage+1);%>
				&d=<%=new Date().getTime()%>">下一页</a>
			<a href="/zk/getDatasMessage?<%=queryString%>page=<%=pageCount%>&d=<%=new Date().getTime()%>">尾页</a>
		</p>
	</div>
</body>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/zk/js/message_manage.js"></script>
</html>
