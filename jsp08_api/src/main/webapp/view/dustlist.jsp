<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		if ('${param.msg}' != ''){
			alert('${param.msg}');
		}
		
		function pasingSave(e){
			e.preventDefault();
			frmDust.action = '${path}/dbsave.dust';
			frmDust.submit();
		}
		
		function listCheck(e){
			e.preventDefault();
			frmDust.action = '${path}/list.dust';
			frmDust.submit();
		}
		
	</script>
</head>
<body>
	<h2>미세먼지 경보 발령 현황</h2>
	<form name="frmDust" action="${path}/list.dust">
		발령 연도
		<input type="text" name="year" value="${param.year}">
		<button onclick="listCheck(event)">조회</button>
		<button onclick="pasingSave(event)">파싱 후 db저장</button>
	</form>
	<table border="1">
		<tr>
			<th>해제농도</th>
			<th>일련번호</th>
			<th>지역명</th>
			<th>발령일</th>
			<th>발령농도</th>
			<th>발령시간</th>
			<th>해제일</th>
			<th>발령일</th>
			<th>권역명</th>
			<th>해제시간</th>
			<th>경보단계</th>
			<th>항목명</th>
		</tr>
		<c:forEach var="dust" items="${dlist}">
			<tr>
				<td>${dust.clearVal}</td>
				<td>${dust.sn}</td>
				<td>${dust.districtName}</td>
				<td>${dust.dataDate}</td>
				<td>${dust.issueVal}</td>
				<td>${dust.issueTime}</td>
				<td>${dust.clearDate}</td>
				<td>${dust.issueDate}</td>
				<td>${dust.moveName}</td>
				<td>${dust.clearTime}</td>
				<td>${dust.issueGbn}</td>
				<td>${dust.itemCode}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>