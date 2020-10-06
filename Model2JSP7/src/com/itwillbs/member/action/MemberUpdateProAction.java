package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("Model : MemberUpdateProAction_execute() 호출 ");
		
		// 세션 제어 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		// 한글 처리 
		request.setCharacterEncoding("UTF-8");
		
		// 전달되는 정보(수정할 정보)를 모두 저장 
		// MemberBean 생성
		MemberBean mb = new MemberBean();
		mb.setAge(Integer.parseInt(request.getParameter("age")));
		mb.setEmail(request.getParameter("email"));
		mb.setGender(request.getParameter("gender"));
		mb.setId(id);
		mb.setName(request.getParameter("name"));
		mb.setPw(request.getParameter("pw"));
		
		// DAO 객체 생성 -> 정보수정 메서드구현 처리(updateMember(mb))
		MemberDAO mdao = new MemberDAO();
		int check = mdao.updateMember(mb);
		
		System.out.println("Model : 정보 수정후 처리 결과 : " +check);
		
		// 페이지 이동 (Javascript 이동- return값 null로 넘겨서 C에서 이동)
		
		if(check == 0){
			// 응답결과를 html 형식으로 처리 
			response.setContentType("text/html; charset=UTF-8"); // 응답결과! response
			
			// 출력 스트림
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print(" alert('비밀번호 오류!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			System.out.println("Model : 자바스크립트 사용 페이지 이동");
			//자바스크립트로 이동 완료했기 때문에 
			// controller에서 이동을 하지 않게 하는 방법 
			return null;
			
		}else if(check == -1){
			
			// 응답결과를 html 형식으로 처리 
						response.setContentType("text/html; charset=UTF-8"); // 응답결과! response
						
						// 출력 스트림
						PrintWriter out = response.getWriter();
						out.print("<script>");
						out.print(" alert('아이디 없음 오류!'); ");
						out.print(" history.back(); ");
						out.print("</script>");
						
						out.close();
						
						System.out.println("Model : 자바스크립트 사용 페이지 이동");
						//자바스크립트로 이동 완료했기 때문에 
						// controller에서 이동을 하지 않게 하는 방법 
						return null;
			
		}
			
		// check == 1 
		
		// 응답결과를 html 형식으로 처리 
					response.setContentType("text/html; charset=UTF-8"); // 응답결과! response
					
					// 출력 스트림
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print(" alert('회원정보 수정완료!'); ");
					out.print(" location.href='./Main.me' ");
					out.print("</script>");
					
					out.close();
					System.out.println("Model : 자바스크립트 사용 페이지 이동");
					//자바스크립트로 이동 완료했기 때문에 
					// controller에서 이동을 하지 않게 하는 방법 
					return null;
		
	}

}
