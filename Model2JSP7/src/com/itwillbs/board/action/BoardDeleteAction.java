package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : BoardDeleteAction_execute() 호출");
		
		// 전달정보 (pageNum, bno, passwd)
		String pageNum = request.getParameter("pageNum");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String passwd = request.getParameter("passwd");
		
		// BoardDAO 객체 생성 - deleteBoard(bno,passwd)
		BoardDAO bdao = new BoardDAO();
		int result = bdao.deleteBoard(bno, passwd);
		
		System.out.println("M : DB 처리 완료 -> "+result);
		
		// 처리 결과를 바탕으로 js 사용 페이지 이동
		
		response.setContentType("text/html; charset=UTF-8");
		// 출력 통로 생성 
		PrintWriter out = response.getWriter();
		
		if(result == 0){
		    out.print("<script>");
		    out.print(" alert('비밀번호 오류'); ");
		    out.print(" history.back(); ");
		    out.print("</script>");
		    out.close();
			
		    return null;
		}else if(result == -1){
			out.print("<script>");
		    out.print(" alert('삭제할 글 없음'); ");
		    out.print(" history.back(); ");
		    out.print("</script>");
		    out.close();
			
		    return null;
		}
		
		    out.print("<script>");
		    out.print(" alert('글 삭제 완료!'); ");
		    out.print(" location.href='./BoardList.bo?pageNum="+pageNum+"' ");
		    out.print("</script>");
		    out.close();
			
		    return null;
	}

}

