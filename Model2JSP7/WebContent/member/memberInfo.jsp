
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
  <h1>WebContent/member/memberInfo.jsp</h1>
  
  <%
  
	//메인페이지는 로그인 없이 이동할 수 없는 페이지 
	// 로그인 없이 접근할 경우 -> 로그인 페이지로 이동 
	
     String id = (String)session.getAttribute("id");
	if(id == null){
		response.sendRedirect("./MemberLogin.me");
	}
  
	// MemberInfoAction() 에서 저장해서 전달한 값
	// request.setAttribute("memberBean", mb);
	MemberBean mb = (MemberBean)request.getAttribute("memberBean");
  
	
	if(mb != null){
  	
  %>
  
  	EL표현식 사용 이메일: ${requestScope.memberBean.email }
  
  <h2> 회원정보 출력</h2>
  
  <table border ="1">
  	<tr>
  	  <td>아이디</td>
  	  <td><%=mb.getId() %></td>
  	</tr>
  	<tr>
  	  <td>비밀번호</td>
  	  <td><%=mb.getPw() %></td>
  	</tr>
  	<tr>
  	  <td>이름</td>
  	  <td><%=mb.getName() %></td>
  	</tr>
  	<tr>
  	  <td>회원가입일</td>
  	  <td><%=mb.getReg_date() %></td>
  	</tr>
  	<tr>
  	  <td>나이</td>
  	  <td><%=mb.getAge() %></td>
  	</tr>
  	<tr>
  	  <td>성별</td>
  	  <td><%=mb.getGender() %></td>
  	</tr>
  	<tr>
  	  <td>이메일</td>
  	  <td><%=mb.getEmail() %></td>
  	</tr>
  </table>
  
  <% } %>
  
  <h2><a href ="./Main.me"> main 페이지로...</a></h2>
  
  

</body>
</html>