<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>리다이렉트 결과창</h2>
	아이디 : <%=request.getParameter("userid") %>
	태어난연도 : <%=request.getParameter("birthyear")%>
</body>
</html>