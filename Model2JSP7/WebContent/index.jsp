<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	
	
	
	   // 프로젝트의 시작페이지
	   // /Model2JSP7/MemberJoin.me
	   //response.sendRedirect("./MemberJoin.me");
	
	
	   // 로그인 페이지
	   //http://localhost:8088/Model2JSP7/MemberLogin.me
	   //response.sendRedirect("./MemberLogin.me");
	   
		  
	   // 메인페이지
	   //http://localhost:8088/Model2JSP7/Main.me
	   //response.sendRedirect("./Main.me");
	   
		// 게시판
	   //http://localhost:8088/Model2JSP7/BoardWrite.bo
	  // response.sendRedirect("./BoardWrite.bo");
		
		
		// 게시판 목록 
		//http://localhost:8088/Model2JSP7/BoardList.bo
		//response.sendRedirect("./BoardList.bo");
		
		
		///////////////////////////////////////////////////////
		// 쇼핑몰 구조 추가 
		// 상품, 구매자, 판매자, 주문(결제, 장바구니), 재고관리 
		
		// 관리자- 상품등록
		// http://localhost:8088/Model2JSP7/GoodsAdd.ag
		//response.sendRedirect("./GoodsAdd.ag");
		
		// 관리자 - 등록된 상품 리스트
		// http://localhost:8088/Model2JSP7/AdminGoodsList.ag
		//response.sendRedirect("./AdminGoodsList.ag");
		
		// 사용자 - 구매 가능한 상품 리스트 보기 
		response.sendRedirect("./GoodsList.go");
		
		
		
		
		
		
		
		
		
		
		//에러 405 - 컨트롤러가 서버 연결 준비 안된 상태
	   
	%>

</body>
</html>