<%@page import="com.itwillbs.admin.goods.db.GoodsDTO"%>
<%@page import="com.itwillbs.basket.db.BasketDTO"%>
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
     boolean listIsNull 
       = (Boolean)request.getAttribute("basketListNull"); 
    // 리스트의 값이 없을경우 basketListNull - false 
   
    if(!listIsNull){
    	//true -> false // 전달된 리스트 정보가 없음
    	%>
    	  <h1>WebContent/goods_order/goods_basket.jsp</h1>
    	  <h1> <%=session.getAttribute("id")%> 장바구니 </h1>
		    <table border="1">
		      <tr>
		         <td>번호</td>
		         <td>사진</td>
		         <td>상품명</td>
		         <td>크기</td>
		         <td>색상</td>
		         <td>수량</td>
		         <td>가격</td>
		         <td>삭제</td>
		      </tr>
    	    </table>
    	
    	<%
    	
    }else{
    	//false -> true // 전달된 리스트가 있는 경우
    	// BasketListACtion 구현한 정보를 전달받아서 처리 
       	//request.setAttribute("basketList", totalData.get(0));
       	//request.setAttribute("goodsList", totalData.get(1));
       	
       	List basketList = (List) request.getAttribute("basketList");
       	List goodsList = (List) request.getAttribute("goodsList");
   %>

   <h1>WebContent/goods_order/goods_basket.jsp</h1>
   
   <%
     BasketDTO bk =(BasketDTO) basketList.get(0);
   %>
   
   <h1> <%=bk.getB_m_id()%> 장바구니 </h1>
   <!-- 장바구니 번호, 사진(대표이미지), 상품명,
       사이즈,색상,수량,가격, 삭제
    -->
    <table border="1">
      <tr>
         <td>번호</td>
         <td>사진</td>
         <td>상품명</td>
         <td>크기</td>
         <td>색상</td>
         <td>수량</td>
         <td>가격</td>
         <td>삭제</td>
      </tr>
      
      <% for(int i=0;i<basketList.size();i++){
    	    BasketDTO bkdto = (BasketDTO)basketList.get(i);
    	    GoodsDTO gdto = (GoodsDTO) goodsList.get(i);
    	  %>
      <tr>
         <td><%=bkdto.getB_num() %></td>
         <td> <img src="./upload/<%=gdto.getImage().split(",")[0]%>"
                   width="50" height="50"
         > </td>
         <td><%=gdto.getName() %></td>
         <td><%=bkdto.getB_g_size() %></td>
         <td><%=bkdto.getB_g_color() %></td>
         <td><%=bkdto.getB_g_amount() %></td>
         <td><%=gdto.getPrice() %></td>
         <td>
           <a href="./BasketDelete.ba?b_num=<%=bkdto.getB_num()%>">삭제</a>
         </td>
      </tr>
      <%
        }
      }
      %>
    
    </table>
   
    <h2><a href="./OrderStar.or">[구매하기]</a></h2>
    <h2><a href="./GoodsList.go">[계속 쇼핑하기]</a></h2>
   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>