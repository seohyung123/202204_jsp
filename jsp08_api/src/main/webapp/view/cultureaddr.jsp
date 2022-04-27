<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>0
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=3eb41zest2"></script>
</head>
<body>
	<h2>서울시 문화위치정보 명칭 검색</h2>
	<form action="${path}/addr.culture">
		명칭 <input type="text" name="name" value="${param.name}">
		<button>검색</button>
	</form>
	<hr>
	<%-- ${map} --%>
	<table border="1">
		<tr>
			<th>문화공간코드</th>
			<td>${map.FAC_CODE}</td>
		</tr>
		<tr>
			<th>장르분류코드</th>
			<td>${map.SUBJCODE}</td>
		</tr>
		<tr>
			<th>장르분류명</th>
			<td>${map.CODENAME}</td>
		</tr>		
		<tr>
			<th>문화공간명</th>
			<td>${map.FAC_NAME}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${map.ADDR}</td>
		</tr>
	</table>
</body>
</html>