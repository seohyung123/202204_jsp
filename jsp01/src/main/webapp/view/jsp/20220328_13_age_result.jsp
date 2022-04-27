<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>결과창</h2>
	<hr>
<!-- 	당신은 24살 입니다
	성인입니다. -->
	<%
		int age = (int)request.getAttribute("age");
		String ageCal = (String)request.getAttribute("ageCal");
	%>
	당신은 <%=age %>살 입니다.
	<%=ageCal %>입니다.
</body>
</html>