package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardReWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : BoardReWriteAction_execute() 호출");

		// 한글 처리 
		request.setCharacterEncoding("UTF-8");
		
		// 전달되는 정보 받기
		int bno = Integer.parseInt(request.getParameter("bno"));
		int re_ref = Integer.parseInt(request.getParameter("re_ref"));
		int re_lev = Integer.parseInt(request.getParameter("re_lev"));
		int re_seq = Integer.parseInt(request.getParameter("re_seq"));
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// BoardDTO 객체 생성 후 저장
		BoardDTO bdto = new BoardDTO();
		bdto.setBno(bno);
		bdto.setName(name);
		bdto.setPasswd(passwd);
		bdto.setSubject(subject);
		bdto.setContent(content);
		bdto.setRe_ref(re_ref);
		bdto.setRe_lev(re_lev);
		bdto.setRe_seq(re_seq);

		// BoardDAO 객체 생성
		BoardDAO bdao = new BoardDAO();
		
		bdao.reInsertBoard(bdto);
		
		System.out.println("M : 답글 작성 완료!!");
		
		// 페이지 이동 (./BoardList.bo)
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
