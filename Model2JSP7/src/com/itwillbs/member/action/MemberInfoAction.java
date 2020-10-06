package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("@@@ Action : MemberInfoAction_execute 실행");
		
		// 아이디는 세션 객체에 있으므로 파라미터로 안 가져와도 됨.
		// 세션값을 가져오기 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		// 페이지 이동 정보 저장 객체 
		ActionForward forward = new ActionForward();
		
		// 없을 경우 -> 로그인 페이지 이동 
		if(id == null){
			//response.sendRedirect("./MemberLogin.me"); (x)
			// sendRedirect 로 보내버리면 컨트롤러 메서드 호출한 상태에서 보내져 버리므로 문제가 발생할 수 있음. 
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true); // MemberInfo주소가 아니라 MemberLogin.me주소로 바꿔야 되므로!!
			
			return forward; // id가 null일 때, 밑에 작업들은 수행하지 않고 forward로 리턴!
			
		}
		
		System.out.println("@@@ Action : id -> " +id);
		
		System.out.println("@@@ Action : MemberDAO 객체 생성");
		// DAO 객체를 생성해서 정보를 가져오기 
		MemberDAO mdao = new MemberDAO();
		
		System.out.println("@@@ Action : 회원정보 가져오는 메서드");
		// id에 해당하는 회원 정보(MemberBean)를 가져오기
		MemberBean mb = mdao.getMember(id);
		
	
		
		// java -> jsp 데이터 전달 
		// request 객체에 저장 (요청할때만 정보를 전달하고 받으므로 낭비 없음)/ (session 객체- 정보가 필요없는 경우에도 세션객체는 유지가 되므로 낭비를 초래) 
		System.out.println("@@@ Action : request 영역에 저장하고 페이지 이동");
		
		request.setAttribute("memberBean", mb);
		
		System.out.println("@@@ Action : 페이지이동");
		
		// 페이지이동
		// ./member/memberInfo.jsp
		forward.setPath("./member/memberInfo.jsp"); // 주소는 ./MemberInfo.me 이면서 jsp 페이지로 이동하므로 포워딩!!(false)
		forward.setRedirect(false);
		
		
		return forward;
	}

}
