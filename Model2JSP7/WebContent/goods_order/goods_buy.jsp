<%@page import="com.itwillbs.admin.goods.db.GoodsDTO"%>
<%@page import="com.itwillbs.basket.db.BasketDTO"%>
<%@page import="com.itwillbs.member.db.MemberBean"%>
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

	<% 	
	// OrderStarAction에 저장한 정보를 가져오기
	
	List basketList = (List)request.getAttribute("basketList");
	List goodsList = (List)request.getAttribute("goodsList");
	MemberBean mb = (MemberBean)request.getAttribute("memberBean");
	
	%>
	<h1>WebContent/goods_order/goods_buy.jsp</h1>
	
	
	
	<h1> 주문 상세내역 </h1>
	
	<table border="1">
		<tr>
			<td>사진</td>
			<td>상품명</td>
			<td>수량</td>
			<td>색상</td>
			<td>크기</td>
			<td>가격</td>
		</tr>
		
		<%
			for(int i=0;i<basketList.size();i++){
				
				BasketDTO bkdto = (BasketDTO)basketList.get(i);
				GoodsDTO gdto = (GoodsDTO)goodsList.get(i);
				
		%>
		<tr>
			<td><img src="./upload/<%=gdto.getImage().split(",")[0] %>" width="50" height="50"></td>
			<td><%=gdto.getName() %></td>
			<td><%=bkdto.getB_g_amount() %></td>
			<td><%=bkdto.getB_g_color() %></td>
			<td><%=bkdto.getB_g_size() %></td>
			<td><%=gdto.getPrice() %></td>
		</tr>
		<%
			}
		%>

	</table>	
	
	<hr>
	<!-- 원래는 유효성 체크 필수 -->
	
	<h1> 주문자 정보 </h1>
	
	<fieldset>
		<legend> 주문자 정보 </legend>
		<form action="./OrderAdd.or" method="post">
		  이름 : <%=mb.getName() %><br>
		  연락처 : <input type="text" name="tel"><br>
		  이메일 : <%=mb.getEmail() %><br>
		  <h2> 배송지 정보 </h2>
		  받는사람 : <input type="text" name="o_receive_name"><br>
		  연락처 : <input type="text" name="o_receive_phone"><br>
		  배송지 주소 : <input type="text" name="o_receive_addr1"><br>
		  나머지 주소 : <input type="text" name="o_receive_addr2"><br>
		  기타 요구사항 : <input type="text" name="o_memo"><br>
		  
		  <h2> 결제 정보 </h2>
		  <input type="radio" name="o"> 신용카드
		  <input type="radio" name="o"> 온라인 입금<br>
		  <input type="radio" name="o"> 휴대폰 결제
		  <input type="radio" name="o"> 문화상품권<br><br>
		  
		  입금자명(온라인 입금전용) : <input type="text" name="o_trade_payer" value="<%=mb.getName()%>"><br><br>
		  <hr><hr><br>
		  <input type="submit" value="주문하기">
		  <input type="reset" value="취소하기">
		
		</form>
	</fieldset>
	
	
	
	
	
	
	

</body>
</html>