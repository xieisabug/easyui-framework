<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.entity.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>

</head>

<body>
<%
	User user = (User)session.getAttribute("user"); 
%>
	<table width="100%">
		<tr>
			<td width="8%" rowspan="7" class="table_p">
				<p>个人信息报考</p></td>
			<td width="10%">
				<p>考籍号：</p></td>
			<td width="15%"><input type="text" name="examNumber"
				id="examNumber" class="input_text" onblur="getInfoByExamNumber();" /></td>
			<td width="10%">老考籍号：</td>
			<td width="15%"><input name="old_examNumber" type="text"
				id="old_examNumber" class="input_text" disabled /></td>
			<td width="10%">身份证号码：</td>
			<td width="15%"><input type="text" name="idCard" id="idCard"
				class="input_text" onblur="getInfoByIdCard();" /></td>
			<td width="17%" rowspan="7"><div id="photo"></div></td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td><input type="text" name="name" id="name" class="input_text"
				disabled /></td>
			<td>性别：</td>
			<td><input type="text" name="sex" id="sex" class="input_text"
				disabled /></td>
			<td>注册账号：</td>
			<td><input type="text" name="postCode" id="postCode"
				class="input_text" disabled /></td>
		</tr>
		<tr>
			<td>联系地址：</td>
			<td><input type="text" name="address" id="address"
				class="input_text" disabled />
			</td>
			<td>民族：</td>
			<td><input type="text" name="nationality" id="nationality"
				class="input_text" disabled /></td>
			<td>入学时间：</td>
			<td><input type="text" name="joinTime"
				id="joinTime" class="input_text" disabled /></td>
		</tr>
		<tr>
			<td>报考专业：</td>
			<td><input type="text" name="major"
				id="major" class="input_text" disabled /></td>
			<td>助学方式：</td>
			<td><input name="studyForm" type="text" id="
				studyForm" class="input_text" disabled /></td>
			<td>联系电话1：</td>
			<td><input type="text" name="phone1" id="phone1"
				class="input_text" disabled/></td>
		</tr>
		<tr>
			<td>联系电话2：</td>
			<td><input type="text" name="phone2" id="phone2"
				class="input_text" disabled/></td>
			<td>电子邮件：</td>
			<td><input type="text" name="email" id="email"
				class="input_text" disabled/></td>
			
			<td>考期：</td>
			<td><input type="text" name="examDate" id="examDate"
				class="input_text" disabled/></td>
		</tr>
	</table>
	<form action="addSC" method="post">
		<input type="hidden" name="idCard" id="post_idCard" value="">
		<input type="hidden" name="examDate" id="post_examDate" value="">
		<input type="hidden" name="operatorId" value="<%=user.getId()%>">
		<table width="100%">
			<tr>
				<td height="32" colspan="2" class="table_p">考试时间</td>
				<td width="73%" class="table_p">开考课程</td>
				<td width="11%" class="table_p">操作员</td>
			</tr>
			<%
				 Map properties = (Map)request.getAttribute("resultMap");
				 int unitNum = Integer.parseInt((String)properties.get("unitNum"));
				 int dayNum = 0;
				 if(unitNum % 2 == 0)
				 	dayNum = unitNum / 2;
				 else
				 	dayNum = (unitNum + 1) / 2;
				 for(int i = 1; i <= dayNum; i++) {
				 	
			%>
				<tr>
					<td width="8%" rowspan="2" id="day<%=i %>"><%=(String)properties.get("day"+i) %></td>
					<td width="8%" id="unit<%=i*2-1 %>time">
						<%=(String)properties.get("unit" + (i*2-1) + "time") %>
					</td>
					<td id="unit<%=i*2-1 %>">
					</td>
					<td id="operator"></td>
				</tr>
				<tr>
					<td id="unit<%=i*2 %>time"><%=(String)properties.get("unit" + (i*2) + "time") %></td>
					<td id="unit<%=i*2 %>">
					</td>
					<td id="operator"></td>
				</tr>
			<%
				} 
			%>
			<tr>
				<td colspan="2" id="status"></td>
				<td><input type="submit" value="报考" class="input_button" id="submit" /></td>
				<td><input type="button" value="重新报考" onclick="rechoose();" class="input_button"/></td>
			</tr>
		</table>
	</form>
</body>
</html>
