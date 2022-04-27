<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입 정보</h2>
	<hr>
	<%
/*  	아이디, 비밀번호, 이메일, 이름, 성별(radio), 가입경로(select), 
	알바가능시간대(checkbox):새벽,오전,오후,저녁 */
		
	// post 방식일 경우 인코딩 utf-8
	request.setCharacterEncoding("utf-8");
	String userid = request.getParameter("userid");
	String passwd = request.getParameter("passwd");
	String email = request.getParameter("email");
	String username = request.getParameter("username");
	String gender = request.getParameter("gender");
	String route = request.getParameter("route");
	String[] times = request.getParameterValues("time");
	
	%>
	
	<ul>
		<li> 아이디: <%=userid %> 
		<li> 비밀번호: <%=passwd %> 
		<li> 이메일: <%=email %> 
		<li> 이름: <%=username %>  
		<li> 성별: <%=gender %> 
		<li> 가입경로: <%=route %> 
		<li> 가능시간대 : 
		<%
			for(int i=0; i<times.length;i++){
		%>		<%= times[i] %>
		<%	}
		%>
	</ul>
</body>
</html>