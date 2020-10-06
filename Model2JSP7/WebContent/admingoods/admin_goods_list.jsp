<%@page import="java.util.List"%>
<%@page import="com.itwillbs.admin.goods.db.GoodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>WebContent/admingoods/admin_goods_list.jsp</h1>
	
	
	<%
	
	String id = (String)session.getAttribute("id");
	

	 if(id == null || !id.equals("admin")){
		response.sendRedirect("./Main.me");
	} 
	
	
		// request 영역에 저장
		//request.setAttribute("goodsList", goodsList);
	
		// request영역에서 정보를 꺼내서 테이블에 추가
		List<GoodsDTO> goodsList = (List)request.getAttribute("goodsList");
		
	
		
		
	
	%>
	
	<h1> 관리자 - 상품 목록 </h1>
	
	
	
	<h2><a href="./GoodsAdd.ag"> 관리자 상품 등록 </a></h2>
	
	
	<table border="1">
		<tr>
			<td>번호</td>
			<td>카테고리</td>
			<td>사진</td>
			<td>상품명</td>
			<td>가격</td>
			<td>수량</td>
			<td>등록일</td>
			<td>수정/삭제</td>
		</tr>
		
		<%
		 for(int i=0; i<goodsList.size(); i++){
			 
		 	GoodsDTO gd = (GoodsDTO)goodsList.get(i); 
		
		%>
		
		
		<tr>
			<td><%=gd.getGno() %></td>
			<td><%=gd.getCategory() %></td>
			<td>
				<img src="./upload/<%=gd.getImage().split(",")[0]%>"
					width="100" height="100">
			</td>
			<td><%=gd.getName() %></td>
			<td><%=gd.getPrice() %>원</td>
			<td><%=gd.getAmount() %>개</td>
			<td><%=gd.getDate() %></td>
			<td>
			
			
			<a href="./AdminGoodsModify.ag?gno=<%=gd.getGno()%>">수정</a>
			/
			<a href = "./AdminGoodsDelete.ag?gno=<%=gd.getGno()%>">삭제</a>
			
			</td>
		</tr>
		
		 <%} %> 
	</table>
	

	
	
	
	
	
	
	
	
	
	
	

</body>
</html>