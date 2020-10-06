package com.itwillbs.member.action;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet{
	
	// 1. 서블릿으로 생성
		// 2. get/post 방식에 대한 처리 가능하도록 준비
		
		protected void doProcess(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			// get/post 방식 상관없이 특정 주소에 따른 처리 
			System.out.println("doProcess() 호출");
			
			// 가상주소를 가져오기---------------------------------
			// http://localhost:8088/Model2JSP7/test.me
			//StringBuffer requestURL = request.getRequestURL();
			//System.out.println(" requestURL : "+requestURL);
			
			// requestURI : /Model2JSP7/test.me
			// 프로젝트명 + 주소
			String requestURI = request.getRequestURI();
			System.out.println(" requestURI : "+requestURI);
			
			// contextPath : /Model2JSP7
			// 프로젝트명
			String contextPath = request.getContextPath();
			System.out.println(" contextPath : "+contextPath);
			
			// 가상주소 
			// /test.me
			String command = requestURI.substring(contextPath.length());
			System.out.println(" command(가상주소) : "+command);
			
			System.out.println("-------------------------------------------");
			
			// 가상주소를 가져오기---------------------------------
			
			// 특정 주소에 대한 처리 --------------------------------------------

			Action action = null;
			ActionForward forward = null;
			
			// http://localhost:8088/Model2JSP7/MemberJoin.me
			
			// *********************** 회원가입 처리 **********************************
			if(command.equals("/MemberJoin.me")){
				System.out.println("/MemberJoin.me 호출");
				// 회원가입처리  ->회원 정보 입력창(view)
				// WebContent/member/insertForm.jsp 페이지로 이동
				
				// 1.response
				//response.sendRedirect("./member/insertForm.jsp");
				// 2.forward
				// RequestDispatcher dis
				// = request.getRequestDispatcher("./member/insertForm.jsp");
				//
				// dis.forward(request, response);
				
				// 페이지로 이동 => ActionForward 객체 사용	
				forward = new ActionForward();
				// 이동할 주소
				forward.setPath("./member/insertForm.jsp"); // jsp 페이지에서 값을 입력한 다음 memberjoinaction(pro역할)하는 페이지로!
				// 이동할 방법 (forward)
				forward.setRedirect(false);
			}else if(command.equals("/MemberJoinAction.me")){
				// Action 페이지 => Pro페이지 역할
				System.out.println("/MemberJoinAction.me  주소 호출");
				
				// 전달되는 정보를 저장 -> DB 전달
				// Action 페이지를 통해서 처리
				// MemberJoinAction 객체가 필요하다.
				//MemberJoinAction action = new MemberJoinAction();
				action = new MemberJoinAction();
				System.out.println("@@@ Controller : MemberJoinAction 객체 생성");
				
				try {
					System.out.println("@@@ Controller : execute(request, response) 메서드 호출");
					 forward = action.execute(request, response);
					 
					 System.out.println("@@@ Controller : 처리완료 후 페이지 이동 / 이동정보는 forward 안에 " + forward);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // 회원가입 처리 	
			
			// *********************** 로그인 처리 **********************************
				else if(command.equals("/MemberLogin.me")){
					System.out.println("@@@ Controller : /MemberLogin.me 호출");
					System.out.println("@@@ Controller : ./member/loginForm.jsp 이동");
					
					// /MemberLogin.me -> ./member.loginForm.jsp 
					
					// 페이지 이동 (ActionForward 객체 생성)
					 forward = new ActionForward();
					
					 // 페이지 이동 주소 
					 forward.setPath("./member/loginForm.jsp");
					 // 페이지 이동 방식
					 forward.setRedirect(false);
					 
				}else if(command.equals("/MemberLoginAction.me")){
					
					// 로그인 처리 - MemberLoginAction() 객체 생성 
					System.out.println("@@@ Controller : /MemberLoginAction.me 호출");
					System.out.println("@@@ Controller : 로그인 데이터 처리");
					
					
					action =  new MemberLoginAction();
					System.out.println("@@@ Controller : MemberLoginAction 객체 생성");
					
					try {
						System.out.println("@@@ Controller : execute()메서드 호출");
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					System.out.println("@@@ Controller : 데이터 처리 후 페이지 이동");
					System.out.println("@@@ Controller : ActionForward 객체 생성해서 처리");
				} // 로그인 처리
			
			// *********************** 메인페이지 이동 처리 **********************************
				else if(command.equals("/Main.me")){ // command 에는 . 붙이면 안됨.
					//http://localhost:8088/Model2JSP7/Main.me
					System.out.println("@@@ Controller : ./Main.me 호출");
					System.out.println("@@@ Controller : ./Main.me -> ./member/main.jsp ");
					System.out.println("@@@ Controller : 메이페이지 호출");
					
					// 페이지 이동 
					forward = new ActionForward();
					forward.setPath("./member/main.jsp");
					forward.setRedirect(false); // 포워딩 : Main.me 주소이면서 페이지는 main.jsp페이지로!
					
				}//메인페이지 이동
			
			// *********************** 로그아웃 처리 **********************************
				else if(command.equals("/MemberLogout.me")){
					// 로그아웃
					// MemberLogoutAction() 객체 생성 후 처리 - 로그아웃 뷰 페이지는 필요없으므로! 바로 action페이지로!
					
					System.out.println("@@@ Controller : /MemberLoout.me (로그아웃) 호출");
					System.out.println("@@@ Controller : 로그아웃 처리동작");
					System.out.println("@@@ Controller : MemberLogoutAction 객체 생성");
					action = new MemberLogoutAction();
					System.out.println("@@@ Controller : execute() 호출");
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				} // 로그아웃
			
			// *********************** 회원정보 보기 처리 **********************************	
				else if(command.equals("/MemberInfo.me")){
					
					System.out.println("@@@ Controller : ./MemberInfo.me 호출");
					// 사용자의 정보를 가져와서 출력 
					// DB안에서 사용자 정보 가져와야함! - action페이지가서 정보를 가져온 다음 jsp 페이지로 이동 
					
					
					// MemberInfoAction() 객체 생성 
					System.out.println("@@@ Controller : MemberInfoAction() 객체 생성");
					action = new MemberInfoAction();
					
					// 1) DB에서 정보 가져오기 
					// 2) 정보를 가지고 jsp 페이지로 이동
					
					try {
						forward  = action.execute(request, response);

					} catch (Exception e) {
						e.printStackTrace();
					}
					}// MemberInfo.me
			
			// *********************** 회원정보 수정 처리 **********************************	
					
					else if(command.equals("/MemberUpdate.me")){
						// DB 정보를 가지고와서 JSP 페이지 이동 
						// MemberUpdateAction 객체 
						System.out.println("***C : Action객체 생성_execute() 호출 ");
						
						action = new MemberUpdateAction();
						
						try {
							
						// action페이지 작업한것 리턴	
						forward = action.execute(request, response);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}// MemberUpdate.me
				// *********************** 회원정보 수정 처리 **********************************
					else if(command.equals("/MemberUpdatePro.me")){
						// updateForm.jsp -> /MemberUpdatePro.me
						// 수정할 정보를 입력받아서 DB에 저장(Action)
						System.out.println("*** C : /MemberUpdatePro.me 호출 ");
						// MemberUpdateProAction() 객체 
					action = new MemberUpdateProAction(); 
						
					try {
						// 실제 DB에 접근해서 처리하는 동작 메서드
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
						
					}
			 //*********************** 회원정보 삭제 처리 **********************************
					else if(command.equals("/MemberDelete.me")){
						// Main.me -> /MemberDelete.me -> deleteForm.jsp
						System.out.println("C : /MemberDelete.me 호출");
						
						// DB 정보가 필요없기 때문에 바로 View 페이지로 변환
						// 페이지 이동하기 위한 객체 ActionForward() 객체 생성
						forward = new ActionForward();
						
						forward.setPath("./member/deleteForm.jsp");
						forward.setRedirect(false); // 주소가 delete.me 이면서 화면은 jsp페이지로 이동 
						
					}
					else if(command.equals("/MemberDeleteAction.me")){
						
						// new MemberDeleteAction() 객체 생성
						action = new MemberDeleteAction();
						System.out.println("@@@ Controller : MemberDeleteAction() 객체 생성");
						try {
							forward = action.execute(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("@@@ Controller : 데이터 처리 후 페이지 이동");
						System.out.println("@@@ Controller : ActionForward 객체 생성해서 처리");
					}
			//*********************** 회원정보 보기 처리  **********************************
					else if(command.trim().equals("/MemberList.me")){
						//trim() -> 문자열 데이터 앞뒤의 공백제거
						
						// MemberListAction() 객체 생성 
						action = new MemberListAction();
						
						try {
							forward = action.execute(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
			
						
					
					
					
					
					
					
					
					
					
					
				
					
				
					
					
					
					
				
				
			
			
			
			
			// 특정 주소에 대한 처리 --------------------------------------------
			
			
			
			
			
			// ***************** 페이지 이동 ***************************
			// 이동정보 (ActionForward)가 있을때
			// 여러번의 페이지 이동처리를 한번에 효율적으로 처리 하기위함
			// 1) response - true  2) forward - false
			
			if(forward != null){
				System.out.println("@@@@ 페이지 이동 @@@@");
				
				if(forward.isRedirect()){//1) response - true 
					System.out.println("이동할 페이지 : "+forward.getPath());
					System.out.println("sendRedirect() 방식 이동");
					response.sendRedirect(forward.getPath());
				}else{ // 2) forward - false
					RequestDispatcher dis 
					 = request.getRequestDispatcher(forward.getPath());
					System.out.println("이동할 페이지 : "+forward.getPath());
					System.out.println("forward() 방식 이동");
					dis.forward(request, response);				
				}
				
			}
			
			
			
			
			// ***************** 페이지 이동 ***************************
			
			
			
			
			
		}
		
		@Override
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			System.out.println("doGet() 호출");
			doProcess(request, response);
		}

		@Override
		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			System.out.println("doPost() 호출");
			doProcess(request, response);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
