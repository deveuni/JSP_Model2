<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

a {color: #333;}

</style>
</head>
<body>
<h1>WebContent/member/main.jsp</h1>

<%
	//메인페이지는 로그인 없이 이동할 수 없는 페이지 
	// 로그인 없이 접근할 경우 -> 로그인 페이지로 이동 
	
     String id = (String)session.getAttribute("id");
	if(id == null){
		response.sendRedirect("./MemberLogin.me");
	}
	

%>

	<h3>로그인 : <%=id %></h3>
	<h3>로그인(session-EL표현식) : ${sessionScope.id } </h3>

	<input type ="button" value="로그아웃" onclick="location.href='./MemberLogout.me'">	

<hr>
	
	<h3><a href ="./MemberInfo.me"> 회원 정보 조회 </a></h3>
	
	<hr>
	<h3><a href="./MemberUpdate.me"> 회원정보 수정 </a></h3>
	
	<hr>
	<h3><a href="./MemberDelete.me"> 회원정보 삭제(탈퇴) </a></h3>
	
	
	<hr>
	<h3><a href="./BoardList.bo"> ITWILL 게시판 </a></h3>
	
	<hr>
	<h3><a href="./GoodsList.go"> ITWILL 쇼핑몰 </a></h3>
	
	<hr>
	<h3><a href="./BasketList.ba"> ITWILL 쇼핑몰 - 장바구니 </a></h3>
	
	<hr>
	
	<h3><a href="./OrderList.or">ITWILL 쇼핑몰 - 주문목록</a></h3>
	
	
	<%
	  //if(id != null){ // 아이디가 null이게 되면 비교자체가 안되기 때문에! 가능.
		if(id != null && id.equals("admin")){ // 연산할 때는 순서가 중요하다!! 확실하게 하기 위해 첫번째 방법씀
	
	%>
	
	<hr>
	<h3><a href="./MemberList.me"> 회원 전체 목록(관리자) </a></h3>
	
	<hr>
	<h3><a href="./AdminGoodsList.ag"> 상품 목록(관리자)</a></h3>
	
	<hr>
	<h3><a href="./AdminOrderList.ao"> 주문 목록(관리자)</a></h3>

	<%
		}
	  //}
	%>

</body>
</html>