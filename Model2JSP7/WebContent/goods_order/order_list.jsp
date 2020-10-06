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
  <h1>WebContent/goods_order/order_list.jsp</h1>
  
  <%
    System.out.println("V : 주문목록 출력페이지");
   // OrderListAction에서 전달된
   // orderList 정보를 저장사용
   // 저장
   //request.setAttribute("orderList",ordao.getOrderList(id));
   
   List orderList = (List)request.getAttribute("orderList");
   
  
  
  %>
  <table border="1">
    <tr>
       <td>주문번호</td>
       <td>상품명</td>
       <td>결재 방법</td>
       <td>주문금액</td>
       <td>주문상태</td>
       <td>주문일시</td>
       <td>운송장번호</td>
    </tr>
    
    <%
     for(int i=0;i<orderList.size();i++){
    	 OrderDTO ordto = (OrderDTO)orderList.get(i);
    %>
    <tr>
       <td>
       <a href="./OrderDetail.or?trade_num=<%=ordto.getO_trade_num() %>">
         <%=ordto.getO_trade_num() %>
       </a> 
       </td>
       <td><%=ordto.getO_g_name() %></td>
       <td><%=ordto.getO_trade_type() %></td>
       <td><%=ordto.getO_sum_money() %></td>
       <%
       // 주문상태
       // 0 - "대기중"
       // 1 - "발송준비"
       // 2 - "발송완료"
       // 3 - "배송중"
       // 4 - "배송완료"
       // 5 - "주문취소"   
       String status="";
       switch(ordto.getO_status()){
       case 0:
    	   status="대기중"; break;
       case 1:
    	   status="발송준비"; break;
       case 2:
    	   status="발송완료"; break;
       case 3:
    	   status="배송중"; break;
       case 4:
    	   status="배송완료"; break;
       case 5:
    	   status="주문취소"; break;
    	   default:
    	   status="관리자 문의";
       }       
         
       %>       
       <td><%=status %></td>
       
       <td><%=ordto.getO_date() %></td>
       <td><%=ordto.getO_trans_num() %></td>
    </tr>
    <%} %>
  
  </table>
  
  <h2><a href="./Main.me"> 메인페이지 </a></h2>
  
  
  
  
  
  
  
  
  
  
  
  

</body>
</html>