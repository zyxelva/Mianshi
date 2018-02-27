<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<html>
	<head>
		<title>宝宝淘论坛登录</title>
		<base href="<%=basePath%>">
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type='text/javascript' src='<%=basePath%>dwr/engine.js'></script>
		<script type="text/javascript" src='<%=basePath %>js/jquery.min.js'/>
		<script type="text/javascript" src='<%=basePath %>js/jquery.md5.js'/>
		<script type='text/javascript' src='<%=basePath%>dwr/util.js'></script>
		<script type='text/javascript' src='<%=basePath%>dwr/interface/DwrService.js'></script>
		
		
	</head>
	<body onkeydown="keyLogin(event);">
	<script type="text/javascript">
	function login() {
		var userName = $('#userName').val();
		var password = $('#password').val();
		var requestData = {
			"serviceName" : "userLoginAction",
			"method" : "login",
			"userName" : userName,
			"password" : password,
			"basePath" : "<%=path%>"
		};
		
		DwrService.request(JSON.stringify(requestData), function (str) {
			var data=$.parseJSON(str);
			if (data) {
				if (data.resultCode > -1) {
					window.location.href = "<%=basePath%>JSP/main.jsp";
				}else{
					window.location.href = "<%=basePath%>JSP/error.jsp";
					alert("Login Error!");
					}
				}
			});
		}

	$(document).ready(function(event) { 
		$("#button").bind("click", function() {
			//alert("++++++++");
			login();
		});

		$("#button").mouseover(function(){
		     $(this).css("background-color","#f45438");
	    }).mouseout(function(){
		     $(this).css("background","");
	    });
		
	}); 

	function keyLogin(event) {
		if ($("div:has(.panel-tool)").length<=0&&event.keyCode == 13){ //回车键的键值为13
			$("#button").click(); //调用登录按钮的登录事件
		}
		closeMessager(event);
		return false;
	};

	function closeMessager(event){
		if ($("div:has(.panel-tool)").length>0) {
			if (event.keyCode == 13) {
				$(".messager-body").window('close');
				return false;
			}
		}
		return false;
	};
	</script>
		<c:if test="${!empty error}">
	        <font color="red"><c:out value="${error}" /></font>
		</c:if>        
		<form>
			<label>用户名：</label>
			<input type="text" id="userName" name="userName">
			<br>
			<label>密 码：</label>
			<input type="password" id="password" name="password">
			<br>
			<input type="button" id="button" value="登录" />
			<input type="reset" value="重置" />
		</form>
	</body>
</html>
