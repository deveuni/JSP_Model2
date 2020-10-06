<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/board/reWriteForm.jsp</h1>
	
   <%
	  System.out.println("V : WebContent/board/reWriteForm.jsp 이동완료");

		// 전달된 파라미터값 저장 (bno, re_ref, re_lev, re_seq)
		// -> hidden 타입으로 저장
		
	  int bno = Integer.parseInt(request.getParameter("bno"));
	  int re_ref = Integer.parseInt(request.getParameter("re_ref"));
	  int re_lev = Integer.parseInt(request.getParameter("re_lev"));
	  int re_seq = Integer.parseInt(request.getParameter("re_seq"));
	

	%>

	<h1>게시판 답글쓰기</h1>

   <fieldset>
	<form action="./BoardReWriteAction.bo" method="post">
		<input type="hidden" name="bno" value="<%=bno%>">
		<input type="hidden" name="re_ref" value="<%=re_ref%>">
		<input type="hidden" name="re_lev" value="<%=re_lev%>">
		<input type="hidden" name="re_seq" value="<%=re_seq%>">
		
	
	
	
	   글쓴이 : <input type="text" name="name"> <br>
	   비밀번호 : <input type="password" name="passwd"><br>
	   제목 : <input type="text" name="subject" value="[답글]"><br>
	   내용 : <br> 
	   <textarea rows="10" cols="25" name="content"></textarea><br><br><br>
	   
	  <input type="submit" value="글쓰기">	
	</form>
   </fieldset>


</body>
</html>