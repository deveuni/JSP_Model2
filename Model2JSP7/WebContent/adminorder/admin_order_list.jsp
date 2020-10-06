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
	<h1>WebContent/adminorder/admin_order_list.jsp</h1>
	
	<%
	  List<OrderDTO> adminOrderList = (List<OrderDTO>) request.getAttribute("adminOrderList");
	%>
	
	<h1> 관리자 주문 목록 </h1>
	
	<table border="1">
		<tr>
			<td>주문번호</td>		
			<td>주문자</td>		
			<td>결제방법</td>		
			<td>주문금액</td>		
			<td>주문상태</td>		
			<td>주문일시</td>		
			<td>수정 / 삭제</td>		
		</tr>
		<%
			for(OrderDTO odto : adminOrderList){ // :꺼내올 데이터		
		%>
		<tr>
			<td><%=odto.getO_trade_num() %></td>		
			<td><%=odto.getO_m_id() %></td>		
			<td><%=odto.getO_trade_type() %></td>		
			<td><%=odto.getO_sum_money() %></td>
			<%
			/* 
				// if~else 로 쓰는 방법
			   if(odto.getO_status() == 0){ status="대기중"; }
	           else if(odto.getO_status() == 1){ status="발송준비"; }
	           else if(odto.getO_status() == 2){ status="발송완료"; }
	           else if(odto.getO_status() == 3){ status="배송중"; }
	           else if(odto.getO_status() == 4){ status="배송완료"; }
	           else if(odto.getO_status() == 5){ status="주문취소"; }
	           else {  status="문제발생!";  }   */
	           
       			String status="";
       			switch(odto.getO_status()){
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
			<td><%=odto.getO_date() %></td>		
			<td>
				<a href="./AdminOrderDetail.ao?trade_num=<%=odto.getO_trade_num()%>">수정</a> 
				/ 
				<a href="./AdminOrderDelete.ao?trade_num=<%=odto.getO_trade_num()%>">삭제</a>
			</td>		
		</tr>
		<%
			}
		%>
	</table>
	
	<h2> <a href="./Main.me">메인페이지</a> </h2>
	
	
	
	
	

</body>
</html>