<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhnjy.entity.Course" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="/zk/jBox/Skins2/Gray/jbox.css" type="text/css"></link>
<link rel="stylesheet" href="/zk/css/get.css" type="text/css"></link>
</head>

<body>
	<div>
		<table class="easyui-datagrid" singleSelect="true" fit="true">
			<thead>
				<tr>
					<th data-options="field:'number'">课程代码</th>
					<th data-options="field:'name'">课程名</th>
					<th data-options="field:'level'">等级</th>
					<th data-options="field:'remark'">备注</th>
					<th data-options="field:'method'">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${courses}" var="course" >
					<tr>
						<td>${course.number}</td>
						<td>${course.name}</td>
						<td>
							<c:if test="${course.level=='zk'}">专科</c:if>
							<c:if test="${course.level=='bk'}">本科</c:if>
						</td>
						<td>${course.remark}</td>
						<td>
							<a href="javascript:delete_course(${course.id});">删除</a> | 
							<a href="javascript:update_course(${course.id});">修改</a>
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
				<a href="/zk/getDatasCourse?<%=queryString%>page=1&d=<%=new Date().getTime()%>">首页</a>
				<a href="/zk/getDatasCourse?<%=queryString%>page=
					<%if(currentPage==1) out.print(currentPage); else out.print(currentPage-1);%>
					&d=<%=new Date().getTime()%>">上一页</a>
				<a href="/zk/getDatasCourse?<%=queryString%>page=
					<%if(currentPage==pageCount) out.print(currentPage); else out.print(currentPage+1);%>
					&d=<%=new Date().getTime()%>">下一页</a>
				<a href="/zk/getDatasCourse?<%=queryString%>page=<%=pageCount%>&d=<%=new Date().getTime()%>">尾页</a>
			</p>
		</div>
	</div>
</body>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/zk/js/course_manage.js"></script>
</html>
