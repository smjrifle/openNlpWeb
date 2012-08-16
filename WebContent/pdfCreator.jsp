<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/pdfCreate" method="post">
		<textarea name="text"></textarea>
		<br /> <input type="submit" name="submit" value="Test" />
</form>


<%
		String outResult = (String) request.getAttribute("result");
		if (outResult != null) {
	%>
	<%=outResult%>
	<%
		}
	%>

</body>
</html>