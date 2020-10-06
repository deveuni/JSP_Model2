<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/board/boardList.jsp</h1>
	
   <%
      	// 디비에서 전체 글목록을 읽어서 가져오기
           
           // 게시판의 글의 수를 모두 화면에 출력
           
           // request 영역에 boardList 저장
      	 //request.setAttribute("boardList", boardList);
      	 //request.setAttribute("cnt", boardCount);
      	 ArrayList<BoardDTO> boardList =
      	  (ArrayList<BoardDTO>)request.getAttribute("boardList");
           
      	 int cnt =
      	 (int)request.getAttribute("cnt");
      	 
      	 // 페이징 처리 정보 받아오기
         // request.setAttribute("pageNum", pageNum);
         // request.setAttribute("pageCount", pageCount);
      	 // request.setAttribute("pageBlock", pageBlock);
      	 // request.setAttribute("startPage", startPage);
      	 // request.setAttribute("endPage", endPage);
      	
      	 String pageNum = (String)request.getAttribute("pageNum");
      	 int pageCount = (Integer)request.getAttribute("pageCount");
      	 int pageBlock = (Integer)request.getAttribute("pageBlock");
      	 int startPage = (Integer)request.getAttribute("startPage");
      	 int endPage = (Integer)request.getAttribute("endPage");      	 
      	 
      	
      %>
   <h2> 게시판 글의 수 : <%=cnt %> 개</h2>
   <h2><a href="./BoardWrite.bo">글쓰기</a></h2>
   <h2><a href="./FileBoardWrite.bo">파일 글쓰기</a></h2>
   
   <table border="1">
     <tr>
       <td>번호</td>
       <td>제목</td>
       <td>작성자</td>
       <td>작성일</td>
       <td>조회수</td>
       <td>IP</td>
     </tr>
     
	     <%
	      for(int i=0;i<boardList.size();i++){
	    	  // ArrayList 한칸의 정보 -> BoardBean 객체 하나로 이동
	    	  BoardDTO bb = (BoardDTO)boardList.get(i);
	    	  
	     %>
		      <tr>
		       <td><%=bb.getBno() %></td>
		       <td>
		       <% 
		        // 답글 들여쓰기 처리
		        int wid = 0;
		        // 답글일때
		        if(bb.getRe_lev()>0){
		        	wid = 10 * bb.getRe_lev();
		       %>
		         <img src="./board/level.gif" width="<%=wid%>" height="15">
		         <img src="./board/re.gif">
		       <%
		        }
		       %>
		         <a href="./BoardContent.bo?bno=<%=bb.getBno()%>&pageNum=<%=pageNum%>">
		          <%=bb.getSubject() %>
		         </a>
		       </td>
		       <td><%=bb.getName() %></td>
		       <td><%=bb.getDate() %></td>
		       <td><%=bb.getReadcount() %></td>
		       <td><%=bb.getIp() %></td>
		     </tr>
	     <%
	      }
	     %>
   
   
   </table>
   
   <%
     // 다른 페이지 이동 버튼
     if(cnt != 0){
    	 
    	 // 이전
    	 if(startPage > pageBlock){
    		 %>
    		    <a href="./BoardList.bo?pageNum=<%=startPage-pageBlock%>">[이전]</a>
    		 <%
    	 }
    	 // 숫자 (1...10/11...20/.....)
    	 for(int i=startPage;i<=endPage;i++){
    		 %>
    		   <a href="./BoardList.bo?pageNum=<%=i%>">[<%=i %>]</a>
    		 <%
    	 }
    	 // 다음
    	 if(endPage < pageCount){
    		 %>
    		   <a href="./BoardList.bo?pageNum=<%=startPage+pageBlock%>">[다음]</a>
    		 <%
    	 }
    	 
     }     
   
   %>
	


</body>
</html>