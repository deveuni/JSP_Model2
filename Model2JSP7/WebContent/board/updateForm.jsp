<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/board/updateForm.jsp</h1>
	<h1> 글 정보 수정하기 </h1>
	
	<%
	BoardDTO bdto = (BoardDTO)request.getAttribute("bdto");
	
	// get방식으로 전달된 pageNum 저장 
	String pageNum = request.getParameter("pageNum");
	
	%>
	
	
	<fieldset>
	<form action="./BoardUpdateProAction.bo?pageNum=<%=pageNum %>" method ="post">
	<!-- input type(hidden) : 화면에는 안보이지만 실제 input 타입처럼 데이터 저장 및 전달 가능하다. -->
	<%--
		폼 태그 안에는 가능하면 BoardBean 형태의 데이터만 저장해서 처리할 수 있도록 해야한다.(액션태그)
		BoardBean에 포함되지 않는 데이터는 액션페이지 주소줄에 get방식으로 전달 
		* 만약 전달되는 데이터가 BoardBean에 포함 안 되는 데이터지만
		    중요한 정보(개인정보)이라면 get방식으로 전달을 피해야 한다.
		    -> scope 영역 으로 전달 하는 방법, BoardBean에 멤버변수 추가하는 방법 등이 있다.
		<input type="hidden" name="pageNum" value="<%=pageNum%>">
	 --%>
	
		<input type="hidden" name="bno" value="<%=bdto.getBno()%>">
	 글쓴이 : <input type ="text" name="name" value = "<%=bdto.getName() %>"><br>
	 비밀번호 : <input type="password" name ="passwd"><br>
	 제목 : <input type="text" name="subject" value="<%=bdto.getSubject() %>"><br>
	 내용 : <br> 
	 <textarea rows="10" cols ="25" name = "content" ><%=bdto.getContent() %></textarea><br>
	 
	 <br>
	 <input type="submit" value="수정하기">
	 
	</form>
	</fieldset>
	
	
	
	
	

</body>
</html>