<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/board/fWriteForm.jsp</h1>

	<h1> 게시판 파일 업로드 글쓰기 </h1>
	
	<%
		// p305 파일 업로드
		
		// 1. cos.jar 라이브러리 파일 설치 
		// 2. 폼태그 속성 enctype = "multipart/form-data", post 
		// 3. 가상경로 /upload 사용 
		
	
	%>
	<fieldset>
	<form action="./FileBoardWriteAction.bo" method ="post" enctype="multipart/form-data">
	 글쓴이 : <input type ="text" name="name"><br>
	 비밀번호 : <input type="password" name ="passwd"><br>
	 제목 : <input type="text" name="subject"><br>
	 내용 : <br> 
	 		<textarea rows="10" cols ="25" name = "content"></textarea><br>
	 파일 : <input type="file" name = "file"><br>
	 
	 <input type="submit" value="글쓰기">
	 
	</form>
	</fieldset>


</body>
</html>