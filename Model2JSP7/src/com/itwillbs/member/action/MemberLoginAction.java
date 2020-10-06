package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;

public class MemberLoginAction implements Action{
	// 처리 페이지 -> Action 인터페이스 구현 
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("@@@ Action : Controller -> Action 이동");
		System.out.println("@@@ Action : MemberLoginAction_execute() 호출");
		
		System.out.println("@@@ Action : 로그인처리");
		System.out.println("@@@ Action : 파라미터값 저장");
		// id, pw 
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("@@@ Action : DAO객체 생성 - 메서드 구현");
		MemberDAO mdao = new MemberDAO();
		
		int check = mdao.idCheck(id,pw);
		System.out.println("@@@ Action : DB처리 결과에 따른 페이지 이동");
		
		
		// check == 0 (비밀번호오류)
		if(check == 0){
			// javascript 사용 alert()
			response.setContentType("text/html; charset=UTF-8");
			
			// 화면에 출력하는 출력스트림 생성
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('비밀번호오류!!');");
			out.print("history.back();");
			out.print("</script>");
			
			out.close();
			
			return null; // 모든 페이지는 컨트롤러를 거치므로 ! 자바스트립은 컨트롤러 이동하면 안되므로 null로 리턴 
			
		}
		
		// check == -1 (아이디 없음)
		else if(check == -1){
			response.setContentType("text/html; charset=UTF-8");
			
			// 화면에 출력하는 출력스트림 생성
			PrintWriter out = response.getWriter();
						
			out.print("<script>");
			out.print("alert('아이디없음!!');");
			out.print("history.back();");
			out.print("</script>");
						
			out.close();
						
			return null;
			
		}
		
		
		if(check == 1){
			// check == 1 (정상처리)
			// 아아디 값을 세션객체에 저장 
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			
			// main페이지 -> ./Main.me 이동
		}
		
		
		// 장바구니 담고 로그인 한 다음에 다시 정보 가지고 장바구니 가는 동작 제어해보기 
				// 페이지 이동 객체 생성
				ActionForward forward = new ActionForward();
					forward.setPath("./Main.me");
					forward.setRedirect(true);
					return forward;
		
		
		
		
	}
	
	
	
}
