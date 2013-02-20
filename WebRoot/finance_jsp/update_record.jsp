<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.util.db.StudentDB" %>
<%@ page import="com.zhnjy.util.db.RecordDB" %>
<%@ page import="com.zhnjy.util.db.RecordItemDB" %>
<%@ page import="com.zhnjy.entity.Student" %>
<%@ page import="com.zhnjy.entity.Record" %>
<%@ page import="com.zhnjy.entity.RecordItem" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'pay.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
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
			maxLength('money', 11);
			form('ff');
		});
	</script>
	<%
		String id = request.getParameter("id");
		Record r = RecordDB.getRecord(Integer.parseInt(id));
		String idCard = r.getIdCard();
		Student stu = StudentDB.getStudent(idCard);
	%>
	<div style="width:300px; margin: 0 auto;">
		<div id="stuName">
			<h3><%=stu.getName()%></h3>
		</div>
		<div id="stuIdCard">
			<h4><%=stu.getIdCard() %></h4>
		</div>
		<form id="ff" action="addRecord" method="post">
			<input type="hidden" name="idCard" value="<%=stu.getIdCard()%>">
			<p>
				交费项目<span>(必填)</span>：<select id="recordItemId" name="recordItemId" class="select" >
				<%
					List items = RecordItemDB.getAll();
					for(int i = 0; i < items.size(); i++) { 
						RecordItem ri = (RecordItem)items.get(i);
				%>
					<option value="<%=ri.getId()%>" <%if(ri.getId()==r.getRecordItemId()) out.println("selected"); %>>
						<%=ri.getName() %>
					</option>
				<%
					} 
				%>
				</select>
			</p>
			<p>
				金额<span>(必填)</span>： <input type="text" name="money" id="money"
					class="easyui-validatebox input_text" value="<%=r.getMoney() %>" required />
			</p>
			<p>
				学年： <input type="text" name="yearth" id="yearth" 
					value="<%=r.getDate() %>" class="input_text"/>
			</p>
			<p>
				票号： <input type="text" name="bill" id="bill" 
					value="<%=r.getBill() %>" class="input_text"/>
			</p>
			<p>
				结算： <input type="text" name="goUp" id="goUp" 
					value="<%=r.getGoUp() %>" class="input_text"/>
			</p>
			<p>
				结算票号： <input type="text" name="goUpBill" id="goUpBill" 
					value="<%=r.getGoUpBill() %>" class="input_text"/>
			</p>
			<p>
				返还： <input type="text" name="goDown" id="goDown"
					value="<%=r.getGoDown() %>" class="input_text"/>
			</p>
			<p>
				返还票号： <input type="text" name="goDownBill" id="goDownBill"
					value="<%=r.getGoDownBill() %>" class="input_text"/>
			</p>
			<p>
				费用去向：<input type="text" name="goMoneyWhere"
					value="<%=r.getGoMoneyWhere() %>" class="input_text">
			</p>
			<p>
				结算去向：<input type="text" name="goUpWhere"
					value="<%=r.getGoUpWhere() %>" class="input_text">
			</p>
			<p>
				返还去向：<input type="text" name="goDownWhere"
					value="<%=r.getGoDownWhere() %>" class="input_text">
			</p>
			<p>
				备注：<input type="text" name="remark"
					value="<%=r.getRemark() %>" class="input_text">
			</p>
			<input type="submit" class="input_button" value="提交"></input>
		</form>
	</div>
</body>
</html>
