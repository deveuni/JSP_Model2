package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : FileBoardWriteAction_execute() 호출");
	 
		// 1) 파일 업로드 (서버 : HDD)
		String realpath =  request.getRealPath("upload");
		System.out.println("realpath : " + realpath);

		int maxSize = 10 * 1024 * 1024; // 10MB
		
		MultipartRequest multi = 
				new MultipartRequest(
						request,
						realpath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()
						);
		System.out.println("파일 업로드 성공 !" + multi);
		
		
		// 2) 글 정보 저장 (DB)
		// request(파라미터) -> multi(파일업로드객체) -> 저장
		
		// BoardDTO 객체 생성 -> 정보 저장
		BoardDTO bdto = new BoardDTO();
		
		bdto.setName(multi.getParameter("name"));
		bdto.setPasswd(multi.getParameter("passwd"));
		bdto.setSubject(multi.getParameter("subject"));
		bdto.setContent(multi.getParameter("content"));
		bdto.setFile(multi.getFilesystemName("file"));
		bdto.setIp(request.getRemoteAddr());
		
		System.out.println(" 업로드 할 객체 정보 : " +bdto);
		
		// BoardDAO 객체 생성 -> insertBoard() 처리 
		BoardDAO bdao = new BoardDAO();
		
		bdao.insertBoard(bdto);
		
		// 페이지 이동 
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true); // bo -> bo로
		
		return forward;
	}

}
