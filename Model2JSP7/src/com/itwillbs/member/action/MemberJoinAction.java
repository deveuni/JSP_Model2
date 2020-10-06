package com.itwillbs.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberJoinAction implements Action  {
	
	// ~Action 객체는 항상 Action 인터페이스를 구현해서 사용 
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("MemberJoinAction_execute() 실행!");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달정보(회원정보) 저장 - MemberBean
		MemberBean mb = new MemberBean();
		mb.setAge(Integer.parseInt(request.getParameter("age")));
		mb.setEmail(request.getParameter("email"));
		mb.setGender(request.getParameter("gender"));
		mb.setId(request.getParameter("id"));
		mb.setName(request.getParameter("name"));
		mb.setPw(request.getParameter("pw"));
		mb.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("@@@@ Action : 전달받은 정보 확인");
		System.out.println("@@@@ Action : MemberBean -> "+mb);
		
				
		// DB 처리 객체 - MemberDAO 
		MemberDAO mdao = new MemberDAO();
				
		// 회원가입 처리
		mdao.insertMember(mb);
		
		System.out.println("@@@ Action : DB작업 처리 완료!");
		System.out.println("@@@ Action : 페이지이동!(컨트롤러이동)"); // 컨트롤러 거쳐야 페이지 이동가능
				
		// 페이지이동
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true); // 주소바뀌기 때문에 sendRedirect 
		
		//sendRedirect - 페이지화면, 주소 다 바뀜 
		//forward - 페이지 화면바뀌나 주소 안바뀜
		
		// 리턴 하면 콘트롤러 try 구문까지 감 - > 
		
		return forward; // 저장한 정보들을 컨트롤러페이지로 가지고 감!! 거기서 페이징 처리!(로그인 페이지로!!)
	}
	
	
	
	
	
	
	
	
	
	

}
