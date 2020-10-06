<%@page import="com.itwillbs.order.db.OrderDTO"%>
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
    <h1>WebContent/goods_order/order_detail.jsp</h1>

  <%
	 //저장
	 //request.setAttribute("orderDetailList", ordao.getOrderDetail(trade_num));
     
     List<OrderDTO> orderDetailList 
       = (List<OrderDTO>)request.getAttribute("orderDetailList");
  
  %>

   <table border="1">
     <tr>
      <td>상품명</td>
      <td>상품크기</td>
      <td>상품색상</td>
      <td>주문개수</td>
      <td>주문금액</td>
     </tr>
     <%
     int total =0;
     for(OrderDTO ordto :orderDetailList){
    	 total += ordto.getO_sum_money();
    	 // total = total + ordto.getO_sum_money();
    	 %>
	     <tr>
	      <td><%=ordto.getO_g_name() %></td>
	      <td><%=ordto.getO_g_size() %></td>
	      <td><%=ordto.getO_g_color() %></td>
	      <td><%=ordto.getO_g_amount() %></td>
	      <td><%=ordto.getO_sum_money() %></td>
	     </tr>
     <%} %>
     <tr>
       <td colspan="5"> 총 주문 금액 : <%=total %> 원</td>
     </tr>
   </table>    
    
   <a href="./Main.me">메인페이지</a> 
    <a href="./GoodsList.go">계속 구매하기</a>
    
    

</body>
</html>
