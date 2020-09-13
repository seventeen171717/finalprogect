<!-- 功能：超级管理员主页面
<!-- 作者：朱海潮 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>超级图书管理员界面</title>
</head>

<body>
	<table width="100%" border="1" cellspacing="0" cellpadding="0"
		id="surface">
		<tr>
			<td bgcolor="#AFDBDC" height="150" class="top">
				<h1 align="center" style="margin-top: 40px">超级管理员页面</h1>
				<h2>欢迎超级管理员：<%=session.getAttribute("name")%></h2>
				<button style="float: right; font-size: 23px">
					<a href="<%=basePath%>login.jsp">退出</a>
				</button>
			</td>
		</tr>
		<tr>
			<td height="600" bgcolor="#E8F7F7" id="surface2">
				<table width="100%" border="1" cellspacing="0" cellpadding="0"
					height="100%" class="center">
					<tr>
						<td width="20%" align="center" class="left">
							<ul>
								<li style="font-size: 30px"><a
									href="supermanager/design.jsp" target="nav">管理员信息</a></li>
							</ul>
							<ul>
								<li style="font-size: 30px"><a href="supermanager/root.jsp"
									target="nav">管理员权限</a></li>
							</ul>
						</td>
						<td width="85%" class="right"><iframe src="" name="nav"
								width=100% height="500" frameborder="0" class="nav"> </iframe>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
		</tr>
	</table>
</body>
</html>