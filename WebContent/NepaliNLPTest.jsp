<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>नेपाली भासा प्रशिक्षण प्रणाली </title>
</head>
<body>

	<p align="center">
	<form action="<%=request.getContextPath()%>/processNepaliText" method="post">
	<label for="text">केही लेखनुहोस : </label><br />
		<textarea name="text"></textarea>
		<br /> <input type="submit" name="submit" value="जाच गर्नु होस" />
	</form>
	<%
		request.setCharacterEncoding("UTF-8");
		String outResult = (String) request.getAttribute("result");
		if (outResult != null) {
	%>
	<%=outResult%>
	<%
		}
	%>
	</p>
</body>
</html>