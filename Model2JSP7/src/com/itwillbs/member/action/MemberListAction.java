package com.itwillbs.member.action;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MemberListAction_execute() 호출!");
		
		// 세션제어(로그인 여부, 관리자인지 판단)
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		
		if(id == null || !id.equals("admin")){
			
			
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DAO 객체 생성 -> getMemberList() 메서드 구현 
		MemberDAO mdao = new MemberDAO();
		
		List<MemberBean> memberList = mdao.getMemberList();
		
		// 회원 정보를 저장해서 
		request.setAttribute("memberList", memberList);
		
		// jsp(view) 페이지로 이동 
		forward.setPath("./member/memberList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
