package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardUpdateProAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
System.out.println("M : BoardUpdateProAction_execute 호출");
		
		// 한글처리 
		request.setCharacterEncoding("UTF-8");
		// 전달되는값들 저장  (이름,비밀번호,제목, 내용, bno,pageNum)
		
		String pageNum = request.getParameter("pageNum");		
		// BoardDTO 객체 생성
		BoardDTO bdto = new BoardDTO();
		bdto.setBno(Integer.parseInt(request.getParameter("bno")));
		bdto.setName(request.getParameter("name"));
		bdto.setPasswd(request.getParameter("passwd"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setContent(request.getParameter("content"));
		
		// BoardDAO 객체 생성
		BoardDAO bdao = new BoardDAO();
		
		
		// bno에 해당하는 글 정보 수정하는 메서드 updateBoard(bdto)
		int result = bdao.updateBoard(bdto);
		
System.out.println("M : DB 처리 완료후 결과  -> "+result);
		
		// 결과를 리턴받아서 처리 (1, 0 ,-1)
		// 페이지이동 (js)	
		
		// 페이지 출력준비 
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result == 0){
			out.print("<script>");
			out.print(" alert('비밀번호 오류');");
			out.print(" history.back();");
			out.print("</script>");
		    out.close();
		    
		    return null;			
		}else if(result == -1){
			out.print("<script>");
			out.print(" alert('해당 글 없음!');");
			out.print(" history.back();");
			out.print("</script>");
		    out.close();
		    
		    return null;
		}
		
		out.print("<script>");
		out.print(" alert('글 수정완료!');");
		out.print(" location.href='./BoardList.bo?pageNum="+pageNum+"';");
		out.print("</script>");
	    out.close();
		
		return null;
	}

}
