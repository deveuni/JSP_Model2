<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/member/deleteForm.jsp</h1>
	
	<h1> 회원 탈퇴 </h1>
	
	<%
		// 로그인 처리 -> 로그인 x (로그인 페이지로 이동)
		
		String id = (String)session.getAttribute("id");
		
		if(id == null){
			response.sendRedirect("./MemberLogin.me");
		}

		// 회원 비밀번호만 입력받아서 deletePro.jsp로 이동 후 삭제
		// 비밀번호만으로 탈퇴 X 아이디 & 비밀번호를 같이 받아야함.
	
	%>
	
	<fieldset>
		<legend>회원 탈퇴 하기</legend>
			<form action = "./MemberDeleteAction.me" method ="post">
				<!-- 화면에 해당 input 태그를 숨겨서 정보를 전달 -->
				아이디 : ${id }<input type ="hidden" name="id" value="<%=id %>" readonly><br>
				비밀번호 : <input type="password" name="pw"><br>
				
				<input type="submit" value="탈퇴하기">
	
			</form>		
	</fieldset>
	
	
	
	
	

</body>
</html>