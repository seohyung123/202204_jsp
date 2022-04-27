<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//msg : 계열 메시지 
		String name = request.getParameter("name");
		String major = request.getParameter("major");
		System.out.println(name);
		System.out.println(major);
		String m =major.substring(0, 1);// 문자열 추출
		System.out.println(m);
		
		// 만약에 m이 'A'라면 공학계열, 'B'라면 자연계열
		String result = null;
		if(m.equals("A")){
			result = "공학계열";
		}else if(m.equals("B")){
			result = "자연계열";
		}else {
			result = "없는 계열입니다";
		}
		System.out.println(result);
		
		//redirect 이동
		//자바의 인코딩과 url 인코딩이 다르다 
		name = URLEncoder.encode(name, "utf-8");
		result = URLEncoder.encode(result,"utf-8");
		response.sendRedirect("20220328_15_result.jsp?name="+name+"&result="+result);
		
	
	%>
</body>
</html>