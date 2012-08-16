<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Login Area</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/LoginServlet" method="post">
		<fieldset>
		<legend>User Login</legend>
		<label for="username">Username : </label><input type="text" name="username" id="username" value="${param.value}" /><br />
		<label for="password">Password : </label><input type="password" name="password" id="password" value="${param.value}" /><br />
			<%
		String error=(String)request.getAttribute("error");
		if(error!=null){
			%>
			<%=error %>
			<%
		} 
		%><br />
		<input type="submit" name="login" value="Login" /><input type="button" name="register" value="Register" />	
		</fieldset>
	</form>
</body>
</html>