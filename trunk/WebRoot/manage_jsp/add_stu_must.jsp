<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zhnjy.entity.User"%>
<%@ page import="com.zhnjy.entity.Site"%>
<%@ page import="com.zhnjy.util.db.SiteDB"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title></title>
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css"
	type="text/css"></link>
<link rel="stylesheet" href="/zk/jBox/Skins2/Gray/jbox.css" type="text/css"></link>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="/zk/css/form.css" type="text/css"></link>
<script type="text/javascript" src="/zk/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/zk/js/validate.js"></script>
</head>

<body style="background:#efefef;">
	<script type="text/javascript">
		$(function(){  
			//equals_two('idCard',15,18,'身份证只有15和18位');
			validateIdCard(15,18,'可用');
			maxLength('name',8);
			mobile('phone1');
			form('ff'); 
		});  
	</script>

	<div style="width:300px; margin: 0 auto;">
		<form id="ff" action="addStudent" method="post">
			<p>
				身份证<span>(必填)</span>： <input type="text" name="idCard" id="idCard"
					class="easyui-validatebox input_text" required/><div id="idCardVali"></div>
			</p>
			<p>
				姓名<span>(必填)</span>： <input type="text" name="name" id="name"
					class="easyui-validatebox input_text" required/>
			</p>
			<p>
				推荐人： <input type="text" name="number" id="number"
					class="easyui-validatebox input_text"/>
			</p>
			<p>
				民族<span>(必填)</span>：<select id="nationality" name="nationality">
					<option value="汉族" selected>汉族</option>
					<option value="满族">满族</option>
					<option value="藏族">藏族</option>
					<option value="怒族">怒族</option>
					<option value="仡佬族">仡佬族</option>
					<option value="朝鲜族">朝鲜族</option>
					<option value="撒拉族">撒拉族</option>
					<option value="东乡族">东乡族</option>
					<option value="白族">白族</option>
					<option value="羌族">羌族</option>
					<option value="壮族">壮族</option>
					<option value="阿昌族">阿昌族</option>
					<option value="珞巴族">珞巴族</option>
					<option value="塔吉克">塔吉克</option>
					<option value="景颇族">景颇族</option>
					<option value="侗族">侗族</option>
					<option value="畲族">畲族</option>
					<option value="回族">回族</option>
					<option value="保安族">保安族</option>
					<option value="毛南族">毛南族</option>
					<option value="塔塔尔">塔塔尔</option>
					<option value="德昂族">德昂族</option>
					<option value="京族">京族</option>
					<option value="水族">水族</option>
					<option value="独龙族">独龙族</option>
					<option value="布朗族">布朗族</option>
					<option value="仫佬族">仫佬族</option>
					<option value="土家族">土家族</option>
					<option value="赫哲族">赫哲族</option>
					<option value="黎族">黎族</option>
					<option value="土族">土族</option>
					<option value="鄂伦春">鄂伦春</option>
					<option value="基诺族">基诺族</option>
					<option value="门巴族">门巴族</option>
					<option value="锡伯族">锡伯族</option>
					<option value="维吾尔族">维吾尔族</option>
					<option value="佤族">佤族</option>
					<option value="俄罗斯">俄罗斯</option>
					<option value="拉祜族">拉祜族</option>
					<option value="蒙古族">蒙古族</option>
					<option value="裕固族">裕固族</option>
					<option value="乌孜别克">乌孜别克</option>
					<option value="傣族">傣族</option>
					<option value="瑶族">瑶族</option>
					<option value="鄂温克">鄂温克</option>
					<option value="布依族">布依族</option>
					<option value="纳西族">纳西族</option>
					<option value="哈尼族">哈尼族</option>
					<option value="柯尔克孜">柯尔克孜</option>
					<option value="苗族">苗族</option>
					<option value="彝族">彝族</option>
					<option value="高山族">高山族</option>
					<option value="傈僳族">傈僳族</option>
					<option value="普米族">普米族</option>
					<option value="哈萨克">哈萨克</option>
					<option value="达斡尔">达斡尔</option>
				</select>
			</p>
			<p>
				性别<span>(必填)</span>： <select id="sex" name="sex" class="select">
					<option value="男" selected>男</option>
					<option value="女">女</option>
				</select>
			</p>
			<p>
				联系电话1<span>(必填)</span>： <input type="text" name="phone1" id="phone1"
					class="easyui-validatebox input_text" required/>
			</p>
			<p>
				联系电话2： <input type="text" name="phone2" id="phone2"
					class="easyui-validatebox input_text"
					data-options="validType:'maxLength[11]'" />
			</p>
			<p>
				学习层次<span>(必填)</span>： 
				<select id="studyLevel" name="studyLevel" class="select">
					<option value="zk">专科</option>
					<option value="bk">本科</option>
					<option value="td">套读</option>
				</select>
			</p>
			<p>
				助学点<span>(必填)</span>： <select id="siteId" name="siteId" class="select">
					<%
						User user = (User) session.getAttribute("user");
						List sites = null;
						if (user.getLevel() != 1) {
							sites = new ArrayList();
							sites.add(SiteDB.getSite(user.getSiteId()));
						} else {
							sites = SiteDB.query();
						}
						for (int i = 0; i < sites.size(); i++) {
							Site site = (Site) sites.get(i);
					%>
					<option value="<%=site.getId()%>"><%=site.getName()%></option>
					<%
						}
					%>
				</select>
			</p>
			<p>
				备注： <input type="text" name="remark" class="input_text" />
			</p>
			<input type="submit" id="submit" class="input_button" value="提交">
		</form>
	</div>
</body>
</html>
