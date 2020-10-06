package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;



public class BoardWriteAction implements Action {

			@Override
			public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
				System.out.println("M : BoardWriteAction_execute() 호출");
				
				// 한글 처리 
				request.setCharacterEncoding("UTF-8");
				
				
				// 전달하는 정보를 저장 (파라미터 값 저장)
				// -> BoardDTO 객체 생성 -> DB테이블 생성 
				BoardDTO bdto = new BoardDTO();
				bdto.setName(request.getParameter("name"));
				bdto.setPasswd(request.getParameter("passwd"));
				bdto.setSubject(request.getParameter("subject"));
				bdto.setContent(request.getParameter("content"));
				// ip
				bdto.setIp(request.getRemoteAddr());
				
				
				// BoardDAO 객체 생성 
				BoardDAO bdao = new BoardDAO();
				// 글쓰기 동작(insertBoard())
				int result =  bdao.insertBoard(bdto);
				
				// 페이지 이동
				ActionForward forward = new ActionForward();
				
				if(result == 1){ // 글 하나 작성 완료 
					forward.setPath("./BoardList.bo"); // 현재 BoardWriteAction.bo 
					forward.setRedirect(true);
					
				}else{
					// 에러 상황 발생 (자바스크립트 / 페이지 이동)
					forward.setPath("./Main.me");
					forward.setRedirect(true);
				}
					return forward;
				}



}
