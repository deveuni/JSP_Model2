<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>WebContent/admingoods/admin_goods_write.jsp</h1>
	<!-- 도메인이랑 비슷하게 만들어서 파일의 이름으로도 알 수있게 ~ -->
	
	<%
	 	String id = (String)session.getAttribute("id");
	
		if(id == null || !id.equals("admin")){
			response.sendRedirect("./Main.me");
		}
	
	%>
	
	<h1> 상품 등록 </h1>
	
	<form action="./GoodsAddAction.ag" method="post" enctype="multipart/form-data"> <!-- post로 이동하는 이유는 파일업로드를 해야하기 때문에 -->
		<table border="1">
			<tr>
				<td>카테고리</td> 
				<td>
					<select name="category">
						<option value="">선택하시오</option>
						<option value="outware">겉옷</option>
						<option value="fulldress">정장/신사복</option>
						<option value="Tshirts">티셔츠</option>
						<option value="shirts">와이셔츠</option>
						<option value="pants">바지</option>
						<option value="shoes">신발</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>상품이름</td> 
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>판매가격</td> 
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td>색상</td> 
				<td><input type="text" name="color"></td>
			</tr>
			<tr>
				<td>수량</td> 
				<td><input type="text" name="amount"></td>
			</tr>
			<tr>
				<td>사이즈</td> 
				<td><input type="text" name="size"></td>
			</tr>
			<tr>
				<td>제품 정보</td> 
				<td><input type="text" name="content"></td>
			</tr>
			<tr>
				<td>메인 이미지</td> 
				<td><input type="file" name="file1"></td>
			</tr>
			<tr>
				<td>제품 이미지1</td> 
				<td><input type="file" name="file2"></td>
			</tr>
			<tr>
				<td>제품 이미지2</td> 
				<td><input type="file" name="file3"></td>
			</tr>
			<tr>
				<td>제품 이미지3</td> 
				<td><input type="file" name="file4"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="상품등록">
					<input type="reset" value="상품등록취소">
				</td>
			</tr>
		</table>
	</form>
	

</body>
</html>