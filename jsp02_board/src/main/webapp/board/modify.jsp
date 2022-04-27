<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

/* 	function modifyCheck(){
		var seq = document.getElementById('seq');
		var writer = document.getElementById('writer');
		var subject = document.getElementById('subject');
		var content = document.getElementById('content');
		if (writer.value ==''){
			alert('작성자를 입력해주세요');
			writer.focus();
			return;
		}else if (subject.value==''){
			alert('제목을 입력해주세요');
			subject.focus();
			return;
		}else if (content.value==''){
			alert('내용을 입력해주세요');
			content.focus();
			return;
		}
		document.getElementById('frmmodify').submit();
	} */
	

</script>
<body>
	<%@ include file="../header.jsp" %>
	<h2>수정</h2>
	<% 
		Board board = (Board) request.getAttribute("board"); 
		Date regidate = board.getRegidate();
		Date modidate = board.getModidate();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
		String fmtRegidate = sf.format(regidate);
		String fmtModidate = sf.format(modidate);
	%>
	<form name="frmModify" action="/jsp02_board/modify.board" method="post">
		<table>
			<tr>
				<th>순번</th>
				<td><input type="text" id="seq" name="seq" readonly value="<%= board.getSeq() %>"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" id="writer" name="writer" value="<%= board.getWriter()%>"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" id="subject" name="subject" value="<%=board.getSubject()%>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="25" id="content" name="content"><%=board.getContent()%></textarea> </td>
			</tr>
			<tr>
				<th>작성일자</th>
				<td><%=fmtRegidate%></td>
			</tr>
			<tr>
				<th>수정일자</th>
				<td><%=fmtModidate%></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button >저장</button>
					<button type="reset">취소</button>
				</td>
			</tr>
		</table>
	
	</form>
</body>
</html>