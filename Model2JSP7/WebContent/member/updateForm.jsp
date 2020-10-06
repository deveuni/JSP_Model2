

<%@page import="com.itwillbs.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/member/updateForm.jsp</h1>
	
	<%
		// WebContent/JSP6/updateForm.jsp 참고해서 페이지 생성
		// DB 처리를 DAO 객체로 전달해서 처리
		
		// 로그인 세션
		String id = (String)session.getAttribute("id");
		if(id == null){
			response.sendRedirect("./MemberLogin.me"); // 항상 jsp 페이지 바꾸기 
		}
		
		// MemberDAO 객체 생성 - 메서드 getMember() 사용 X
		
		// Action 페이지에서 저장했던 회원 정보를 받아오기.
		MemberBean mb = (MemberBean)request.getAttribute("mb");
		
		System.out.println("V : 액션 페이지에서 정보를 전달 받음");
		System.out.println("V : " +mb);
	%>
	
	
	<h1> 회원 정보 수정 </h1>
 	<fieldset>
	 	<legend>정보수정</legend>
	 		<form action="./MemberUpdatePro.me" method="post" name="fr">
	 		아이디 : <input type="text" name="id" value = "<%=mb.getId() %>" readonly><br> 
	 		비밀번호 : <input type="text" name ="pw" ><br>
	 		이름 : <input type="text" name="name" value = "<%=mb.getName()%>"><br>
	 		나이 : <input type="text" name="age"value = "<%=mb.getAge()%>"><br>
	 		성별 : <input type ="radio" name="gender" value="남"
	 			 	<% if(mb.getGender().equals("남")){ %>
	 				checked
	 				<% } %> 
	 				>남
	 			 <input type="radio" name ="gender" value="여"
	 			  	<% if(mb.getGender().equals("여")){ %>
	 				checked
	 				<% } %> 
	 			 >여<br>
	 		이메일 : <input type="text" name="email" value = "<%=mb.getEmail()%>"><br>	
	 		
	 		<input type="submit" value="정보수정하기"> 
	 		</form>
	 </fieldset>
	
	
</body>
</html>