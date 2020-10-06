<%@page import="com.itwillbs.order.db.OrderDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">



</style>
</head>
<body>

	<h1>WebContent/adminorder/admin_order_modify.jsp</h1>
	
	<%
		System.out.println("V : 관리자 주문정보 수정페이지");
		List<OrderDTO> adminOrderDetailList = (List<OrderDTO>) request.getAttribute("adminOrderDetailList");
		
		// 공통 항목을 처리하는 객체 
		OrderDTO odto_total = adminOrderDetailList.get(0);
		// 상품명만 다르고 배송지 정보 등 다른 정보는 똑같으므로 굳이 반복문 처리를 할 필요가 없어서
		
		// 합계금액
		int total_Money = 0;
	%>
	
	<h1> 관리자 - 주문 상세보기 / 수정하기 </h1>
	
	<form action="./AdminOrderModify.ao" method="post"> <!-- form 태그는 input 태그 안의 정보만 넘길 수 있음. -->
	<input type="hidden" name="trade_num" value="<%=odto_total.getO_trade_num()%>">
	<table border="1">
	  <!-- 주문정보 -->
	  	<tr>
			<td bgcolor="green">주문번호</td>
			<td colspan="4"><%=odto_total.getO_trade_num() %></td>
		</tr>
		<tr>
			<td colspan="5" bgcolor="pink">구매 상품 정보</td>
		</tr>
		<tr>
			<td bgcolor="pink">상품명</td>
			<td bgcolor="pink">수량</td>
			<td bgcolor="pink">크기</td>
			<td bgcolor="pink">색상</td>
			<td bgcolor="pink">가격</td>
		</tr>
		<% for(OrderDTO odto:adminOrderDetailList){
				total_Money += odto.getO_sum_money();	
		%>
		<tr>
			<td><%=odto.getO_g_name() %></td>
			<td><%=odto.getO_g_amount() %></td>
			<td><%=odto.getO_g_size() %></td>
			<td><%=odto.getO_g_color() %></td>
			<td><%=odto.getO_sum_money() %></td>
		</tr>
		<%} %>
	  <!-- 배송지 정보 -->
	  	<tr>
			<td colspan="5" bgcolor="yellow">배송지 정보</td>
		</tr>
		<tr>
			<td bgcolor="yellow">받는 사람</td>
			<td bgcolor="yellow">연락처</td>
			<td bgcolor="yellow">배송주소</td>
			<td bgcolor="yellow">세부 배송주소</td>
			<td bgcolor="yellow">요구사항</td>
		</tr>	  
		<tr>
			<td><%=odto_total.getO_receive_name() %></td>
			<td><%=odto_total.getO_receive_phone() %></td>
			<td><%=odto_total.getO_receive_addr1() %></td>
			<td><%=odto_total.getO_receive_addr2() %></td>
			<td><%=odto_total.getO_receive_memo() %></td>
		</tr>	  
	  <!-- 결제정보 -->
	 	 <tr>
			<td colspan="5" bgcolor="skyblue">결제 정보</td>
		</tr>
	  	<tr>
	  		<td bgcolor="skyblue">주문 합계 금액</td>
	  		<td bgcolor="skyblue">결제 방법</td>
	  		<td bgcolor="skyblue">입금자(구매자)</td>
	  		<td bgcolor="red" >주문상태</td>
	  		<td bgcolor="red">운송장번호</td>
	 	 </tr>
	 	 <tr>
	  		<td><%=total_Money %>원</td>
	  		<td><%=odto_total.getO_trade_type() %></td>
	  		<td><%=odto_total.getO_trade_payer() %></td>
	  		<td>
	  		<%=odto_total.getO_status() %>
	  			<select name="status">
	  				<option value="0"
	  				<% if(odto_total.getO_status()==0){ %>  selected <%} %>
	  				>대기중</option>
	  				<option value="1"
	  				<% if(odto_total.getO_status()==1){ %>  selected <%} %> 
	  				>발송준비</option>
	  				<option value="2" 
	  				<% if(odto_total.getO_status()==2){ %>  selected <%} %> 
	  				>발송완료</option>
	  				<option value="3" 
	  				<% if(odto_total.getO_status()==3){ %>  selected <%} %> 
	  				>배송중</option>
	  				<option value="4"
	  				<% if(odto_total.getO_status()==4){ %>  selected <%} %> 
	  				>배송완료</option>
	  				<option value="5"
	  				<% if(odto_total.getO_status()==5){ %>  selected <%} %> 
	  				>주문취소</option>
	  			</select>
	  		</td>
	  		<td>
	  		  <input type="text" name="trans_num" value="<%=odto_total.getO_trans_num() %>">
	  		</td>
	 	 </tr>
	 	 
	 	 <tr>
			<td colspan="3"></td>	 	 
			<td colspan="2">
				<input type="submit" value="수정하기">
			</td>	 	 
	 	 </tr>
	</table>
	</form>
</body>
</html>