package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("M : BoardContentAction_execute() 호출");
		
		// DAO -> DB -> .jsp
		
		// 한글처리 (post방식 전달) - content로 들어오는 방식은 get방식이기 때문에 굳이 안해도됨.
		
		// 전달받은 데이터 저장(제일먼저!!!!)
		int bno = Integer.parseInt(request.getParameter("bno")); // DB까지 가야하니까 정수형으로 바꿔주기!
		String pageNum = request.getParameter("pageNum"); // DB에 없으니까 안만들어도됨
		
		// DB 객체 생성
		BoardDAO bdao = new BoardDAO();
		
		// 1) 조회수 1 증가(updateReadCount())
		bdao.updateReadCount(bno);
		
		// 2) 특정 번호에 해당하는 글 정보를 가져오기 (getBoard())
		BoardDTO bdto = bdao.getBoard(bno);
		
		// 정보 저장 (request영역)
		request.setAttribute("bdto", bdto);
		request.setAttribute("pageNum", pageNum);
		
		// 정보를 보여주는 페이지(view - .jsp) 전달
		// ./board/content.jsp 페이지 이동
		// 페이지 이동 
		ActionForward forward = new ActionForward();
		forward.setPath("./board/content.jsp");
		forward.setRedirect(false); // BoardContent.bo -> .jsp 페이지로 화면만 변환 
		
		return forward;
	}

}
