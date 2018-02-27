<%@page import="com.baobaotao.loginNlog.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	String language = request.getParameter("language");
	if (language != null)
		session.getAttribute("language").toString();
	if (language == null)
		language = "zh_CN";
	session.setAttribute("language", language);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>宝宝淘论坛</title>
<script type='text/javascript'>
	<%-- <%User user = (User) session.getAttribute("user");
		if (user != null) {%>
			window.location.href = "<%=basePath%>JSP/main.jsp";
		<%}%> --%>
	</script>
</head>
<body>
    欢迎您进入宝宝淘论坛;
    登入成功!
<br>
您好!${user.user_name}
<br>
<a href="<%=basePath%>JSP/login.jsp">返回</a>
</body>
</html>