<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("${path}/member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}
	//주소팝업 호출후 실행할 함수
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.frmJoin.addrload.value = roadAddrPart1;
		document.frmJoin.addrdetail.value = addrDetail;
		document.frmJoin.zipcode.value = zipNo;
	}

	function joinCheck(e) {
		//기본이벤트제거(submit기능 없애줌)
		e.preventDefault();
		let userid = frmJoin.userid;
		let passwd = frmJoin.passwd;

		if (userid.value==''){
			alert('아이디를 입력해 주세요!');
			userid.focus();
			return ;
		}else if (passwd.value==''){
			alert('비밀번호를 입력해 주세요!');
			passwd.focus();
			return ;			
		}
		
		frmJoin.submit();
		
	}
	
	

</script>
</head>
<body>
<%@ include file="../header.jsp" %>
	<h2>회원가입</h2>
	<!-- enctype:전송데이터의 타입(단순문자열)-->
	<!-- 파일전송시 : post , multipart/form-data -->
	<form name="frmJoin" action="${path}/join.member" method="post" 
				enctype="multipart/form-data">
		<table>
			<tr>
				<th>아이디</th>
				<td> <input type="text" name="userid"> </td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td> <input type="password" name="passwd"> </td>
			</tr>		
			<tr>
				<th>우편번호</th>
				<td> 
					<input type="text" name="zipcode" size="5">
					<button type="button" onclick="goPopup()">검색</button> 
				</td>
			</tr>		
			<tr>
				<th>도로명주소</th>
				<td> <input type="text" name="addrload" size="30"> </td>
			</tr>		
			<tr>
				<th>상세주소</th>
				<td> <input type="text" name="addrdetail"> </td>
			</tr>
			<tr>
				<th>사진</th>
				<td> <input type="file" name="photo"> </td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					 <button onclick="joinCheck(event)">가입</button>
					 <button type="reset">취소</button>
				</td>
			</tr>
		</table>
	
	
	</form>
</body>
</html>