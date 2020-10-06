package com.itwillbs.goods.action;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoodsFrontController extends HttpServlet{
	
	
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doProcess() 호출");
		
		
		System.out.println("-----------Controller 진입------------------------");
		
		System.out.println("-------------------@ 주소 계산 @--------------------");
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
		
		System.out.println("-------------------@ 주소 계산 @----------------------");
		
		
		System.out.println("-------------------@ 주소 비교 @----------------------");
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/GoodsList.go")){
			//http://localhost:8088/Model2JSP7/GoodsList.go
			System.out.println("C: /GoodsList.go 호출");
			
			// .go -> Action -> DB -> .jsp 
			// GoodsListAction 객체 생성 
			
			action = new GoodsListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/GoodsDetail.go")){
			
			System.out.println("C : /GoodsDetail.go 호출");
			
			// .go -> Action -> DB - > .jsp
			// GoodsDetailAction 객체 생성
			action = new GoodsDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("-------------------@ 주소 비교 @----------------------");
		
		System.out.println("-------------------@ 주소 이동 @-----------------------");
		
		if(forward != null){ // 이동할 정보가 있다.
			
			if(forward.isRedirect()){ // true - sendRedirect()
				// 가상주소(.bo -> .bo), 화면 전환(주소변경, 화면도 변경)
				// 모델 2 - jsp로 이동하는 sendRedirect 이동 하면 잘못된 것
				System.out.println("C: "+forward.getPath()+ "주소로 이동(Redirect)");
				response.sendRedirect(forward.getPath());
				
			}else{ // false - forward() 
				System.out.println("C: "+forward.getPath()+ "주소로 이동(forward)");
				// 가상주소 -> 실제페이지 (.bo -> .jsp) + request 객체 정보를 가지고 이동
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				
				dis.forward(request, response);
			}
		}
		
		System.out.println("-------------------@ 주소 이동 @-----------------------");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출");
		doProcess(request, response);
	}
	
	

}
