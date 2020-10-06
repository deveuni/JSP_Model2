package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardUpdateAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : BoardUpdateAction_execute() 호출");
		
		// 전달되는 정보 가져오기 (bno=1&pageNum=1 파라미터 정보 저장)
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		// 세션값 제어 
		//HttpSession session = request.getSession();
		/*String id = (String)session.getAttribute("id");
		
		
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}*/
		
		// BoardDAO 객체 생성 
		BoardDAO bdao = new BoardDAO();
		
		// bno에 해당하는 글정보 가져오는 getBoard() 메서드
		BoardDTO bdto = bdao.getBoard(bno);
		
		// request 영역에 글정보 저장
		request.setAttribute("bdto", bdto);
		
		//get방식으로 pageNum 가지고 이동
		//페이지이동(./board/updateForm.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/updateForm.jsp?pageNum="+pageNum);
		forward.setRedirect(false);
		
		System.out.println("M : DB에서 글정보 가져와서 저장, 페이지 이동");
		
		return forward;
	}

}
