<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 주말 과제 -->
<!--입력항목 
 	아이디, 비밀번호, 이메일, 이름, 성별(radio), 가입경로(select), 
	알바가능시간대(checkbox):새벽,오전,오후,저녁
	
	버튼을 눌렀을때 결과창에 가입정보 출력 
 -->	  
	<h2>회원가입</h2>
	
	<form action="20220325_10_result.jsp" method="post">
			아이디 <input type="text" name="userid"> <br>
			비밀번호 <input type="password" name="passwd"> <br>
			이메일 <input type="email" name="email"> <br>
			이름 <input type="text" name="username"> <br>
		<fieldset>
			<legend>성별</legend>
			<input type="radio" name="gender" value="man"> 남자
			<input type="radio" name="gender" value="woman"> 여자<br>
		</fieldset>
		<fieldset>
			<legend>가입경로</legend>
			<select name="route">
				<option>인터넷</option>
				<option>지인소개</option>
				<option>홈페이지</option>
			</select> 
		</fieldset>
		<fieldset>
			<legend>알바 가능 시간대</legend>
			<input type="checkbox" name="time" value="야간">야간 
			<input type="checkbox" name="time" value="오전">오전 
			<input type="checkbox" name="time" value="오후">오후 
			<input type="checkbox" name="time" value="저녁">저녁 
		</fieldset>
		<hr>
		<button>가입</button>
	</form>
</body>
</html>