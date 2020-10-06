<%@page import="com.itwillbs.admin.goods.db.GoodsDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/goods/goods_list.jsp</h1>
	
	<%
		// 정보 request 영역에 저장 
		//request.setAttribute("goodsList", goodsList);
		List<GoodsDTO> goodsList = (List<GoodsDTO>)request.getAttribute("goodsList");
	
		System.out.println("goodsList : " + goodsList);
	
	%>
	
	<table border="1">
		<tr>
			<td><a href="./GoodsList.go">전체</a></td>
			<td><a href="./GoodsList.go?item=best">인기상품(best)</a></td>
			<td><a href="./GoodsList.go?item=outware">겉옷</a></td>
			<td><a href="./GoodsList.go?item=fulldress">정장</a></td>
			<td><a href="./GoodsList.go?item=Tshirts">티셔츠</a></td>
			<td><a href="./GoodsList.go?item=shirts">와이셔츠</a></td>
			<td><a href="./GoodsList.go?item=pants">바지</a></td>
			<td><a href="./GoodsList.go?item=shoes">신발</a></td>
		</tr>
		
		 
		 <%
		 	int goodsCount = goodsList.size();
		 	int col = 8;
		 	int row = (goodsCount/col)+(goodsCount % col > 0? 1:0);
		 	int checkNum = 0; // 출력 갯수를 총갯수보다 많지 않게 처리하는 값을 저장
		 	
		 	for(int a=0; a<row; a++){
		 		//System.out.println("행");
		 %>
		 	 <tr>
		 	 <% for(int b =0;b<col;b++){
		 		GoodsDTO gdto = goodsList.get(checkNum);
		 	 %>
		 	   <td>
		 	   	<img src="./upload/<%=gdto.getImage().split(",")[0]%>"
					width="100" height="100"
				><br>
				<a href="./GoodsDetail.go?gno=<%=gdto.getGno()%>">
				<%=gdto.getName() %>
				</a> <br>
				
				<%=gdto.getPrice() %>원<br>
		 	   
		 	   </td>
		 	   
		 	  <%
		 	 	checkNum++;
		 	  	//System.out.println("checkNum : "+checkNum);
		 	 	if(checkNum == goodsCount) break;
		 	 }%> 
		 	 </tr>	
		 <% 		
		 	}
		 	
		 %>
		 
		 
		
		 
	</table>
	
	
	
	
	
	
	
	
	

</body>
</html>