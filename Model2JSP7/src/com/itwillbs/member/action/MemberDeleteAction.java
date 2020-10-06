package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("Model : MemberDeleteAction() 호출");
		
		// 세션값 제어 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id"); // 세션값, 파라미터 둘다 가능.
		
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 전달정보 받아서 저장(id, pw)
		String pw = request.getParameter("pw"); // 폼태그에서 전달받았기 때문에 파라미터!!
		
		
		// 한글처리 
		request.setCharacterEncoding("UTF-8");
		
		// MemberDAO 객체 생성 - deleteMemeber(id, pw)
		MemberDAO mdao = new MemberDAO();
		
		// 삭제 결과를 리턴
		int result =  mdao.deleteMember(id, pw);
		
		response.setContentType("text/html; charset=UTF-8"); // 응답결과! response
		
		// 출력 스트림
		PrintWriter out = response.getWriter();
		
		//리턴에 따른 페이지 이동 결정
		// 페이지 이동(Javascript)
		if(result == 0){ 
			// 비번 오류 
			
	
			out.print("<script>");
			out.print(" alert('비밀번호 오류!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			System.out.println("Model : 자바스크립트 사용 페이지 이동");
			//자바스크립트로 이동 완료했기 때문에 
			// controller에서 이동을 하지 않게 하는 방법 
			return null;
			
			
		}else if(result == -1){
			// 아이디 없음
			
		
			out.print("<script>");
			out.print(" alert('아이디없음!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			System.out.println("Model : 자바스크립트 사용 페이지 이동");
			//자바스크립트로 이동 완료했기 때문에 
			// controller에서 이동을 하지 않게 하는 방법 
			return null;
		}
		
		//result == 1
		session.invalidate();
		out.print("<script>");
		out.print(" alert('회원삭제(탈퇴완료!)'); ");
		out.print(" location.href='./Main.me' ");
		out.print("</script>");
		
		out.close();
		
		return null;
	}

}
