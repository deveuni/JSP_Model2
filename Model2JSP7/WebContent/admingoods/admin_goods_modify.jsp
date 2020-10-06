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

	<h1>WebContent/admingoods/admin_goods_modify.jsp</h1>
	
	<%
	
	String id = (String) session.getAttribute("id");
	
	   if(id == null || !id.equals("admin")){
		   response.sendRedirect("./Main.me");
	   }
	
		// AdminGoodsModifyAction에서 저장된 정보 가져오기
		// request 영역에 저장
		// request.setAttribute("gdto", gdto);
		GoodsDTO gdto = (GoodsDTO)request.getAttribute("gdto");
	
	
	%>
	
	
	<h1> 상품 수정  </h1>
	
	<form action="./AdminGoodsModifyAction.ag" method="post"> <!-- 이미지 수정안하기 때문에 enctype안해도됨-->
	<input type="hidden" name="gno" value="<%=gdto.getGno()%>">
		<table border="1">
			<tr>
				<td>카테고리</td> 
				<td>
				
			
					<select name="category">
						<option value=""
						<% if(gdto.getCategory().equals("")){ %> selected<%}%> 
						>선택하시오</option>
						<option value="outware" 
						<% if(gdto.getCategory().equals("outware")){ %> selected<%}%>
						>겉옷
						</option>
						<option value="fulldress"
						<% if(gdto.getCategory().equals("fulldress")){ %> selected <% }%>
						>정장/신사복
						</option>
						<option value="Tshirts" <% if(gdto.getCategory().equals("Tshirts")){ %> selected <% } %>
						>티셔츠
						</option>
						<option value="shirts"<% if(gdto.getCategory().equals("shirts")){%> selected <% } %>
						>와이셔츠</option>
						<option value="pants"<% if(gdto.getCategory().equals("pants")){ %> selected <% }%>
						>바지</option>
						<option value="shoes" <% if(gdto.getCategory().equals("shoes")){ %> selected <% } %>
						>신발</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>상품이름</td> 
				<td><input type="text" name="name" value="<%=gdto.getName()%>"></td>
			</tr>
			<tr>
				<td>판매가격</td> 
				<td><input type="text" name="price" value="<%=gdto.getPrice()%>"></td>
			</tr>
			<tr>
				<td>색상</td> 
				<td><input type="text" name="color" value="<%=gdto.getColor()%>"></td>
			</tr>
			<tr>
				<td>수량</td> 
				<td><input type="text" name="amount" value="${requestScope.gdto.amount }"></td>
			</tr>
			<tr>
				<td>사이즈</td> 
				<td><input type="text" name="size" value="${requestScope.gdto.size }"></td>
			</tr>
			<tr>
				<td>제품 정보</td> 
				<td><input type="text" name="content" value="${requestScope.gdto.content }"></td>
			</tr>
			
			<tr>
				<td>인기상품 등록</td> 
				<td>
					<input type="radio" name="best" value="1"
					<%if(gdto.getBest() == 1) { %> checked <%} %>
					>예
					<input type="radio" name="best" value="0"
					<%if(gdto.getBest() == 0) { %> checked <%} %>
					>아니오
				</td>
			</tr>
			
			
			<tr>
				<td colspan="2">
					<input type="submit" value="정보 수정하기">
					<input type="reset" value="처음으로">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>