<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>댓글의 수정</h2>
	<%-- ${reply} --%>
	<form action="${path}/reply/modify">
		<input type="text" name="bnum" value="${reply.bnum}">
		댓글번호 : <input type="text" name="rnum" value="${reply.rnum}"> <br>
		내용 : <textarea rows="5" cols="20" name="content">${reply.content}</textarea> <br>
		<button>수정</button>
	</form>
	
</body>
</html>