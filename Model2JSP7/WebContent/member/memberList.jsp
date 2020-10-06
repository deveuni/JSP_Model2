<%@page import="com.itwillbs.member.db.MemberBean"%>
<%@page import="java.util.List"%>
<%@page import="com.itwillbs.member.db.MemberDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/member/memberList.jsp</h1>
	
	<!-- DB에서 모든 회원의 정보를 가져와서 페이지에 출력  -->
	
	<%
		// 로그인 세션 제어 (로그인 한 사람 중에서 "admin")

		
		String id = (String)session.getAttribute("id");
		
		if(id == null || !id.equals("admin")){
			
			response.sendRedirect("loginForm.jsp");
			
		}
		
		 // 회원 정보를 저장해서 
		//request.setAttribute("memberList", memberList);
		
		 List<MemberBean> memberList = (List<MemberBean>)request.getAttribute("memberList");	
	%>
	
	<!-- JSTL/EL표현식 사용 출력-->
	${ requestScope.memberbean.email}
	
	<c:forEach var="mb" items="${memberList }">
		<c:out value="${mb.id }"></c:out>
	</c:forEach>
	
	
	<table border ="1">
	   <tr>
	     <td>ID</td>
	     <td>PW</td>
	     <td>NAME</td>
	     <td>AGE</td>
	     <td>GENDER</td>
	     <td>EMAIL</td>
	     <td>REG_DATE</td>
	   </tr>
	   
	 <%
	 	// ArrayList - size() : 배열의 요소의 개수 리턴
	 	for(int i=0;i<memberList.size(); i++){
	 	MemberBean mb =	(MemberBean)memberList.get(i);
	 %>  
	   <tr>
	     <td><%=mb.getId() %></td>
	     <td><%=mb.getPw() %></td>
	     <td><%=mb.getName() %></td>
	     <td><%=mb.getAge()%></td>
	     <td><%=mb.getGender() %></td>
	     <td><%=mb.getEmail() %></td>
	     <td><%=mb.getReg_date() %></td>
	   </tr>
	  <%
	 	}
	  %> 
	
	</table>
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>