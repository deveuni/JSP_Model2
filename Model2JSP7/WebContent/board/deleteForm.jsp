<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/board/deleteForm.jsp</h1>

	 
   <h1> 글 삭제  </h1>
   <%
     // 전달된 데이터 bno,pageNum 저장
     int bno = Integer.parseInt(request.getParameter("bno"));
     String pageNum = request.getParameter("pageNum");
   
     // 사용자가 삭제하는 동작을 인지하도록하는 페이지
     // form 태그 사용
     // 해당 글 비밀번호를 입력 -> deletePro.jsp 페이지 이동
   %>
   <fieldset>
     <form action="./BoardDeleteAction.bo?pageNum=<%=pageNum %>" method="post">
      <input type="hidden" name="bno" value="<%=bno %>">
            비밀번호 : <input type="password" name="passwd">
       <input type="submit" value="삭제하기"> 
     </form>   
   </fieldset>
   

</body>
</html>