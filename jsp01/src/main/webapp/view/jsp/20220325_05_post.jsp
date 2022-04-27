<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>데이터 전송 (post)</h2>
	<form action="" method="get">
		아이디: <input type="text" name="userid"> <br>
		비밀번호: <input type="password" name="userpw"> <br>
		<button>로그인</button>
	</form>
	<fieldset>
		<legend>로그인정보</legend>
		아이디 : <%= request.getParameter("userid") %>
		비밀번호 : <%= request.getParameter("userpw") %>
	</fieldset>
</body>
</html>